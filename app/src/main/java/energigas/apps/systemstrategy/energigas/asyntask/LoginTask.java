package energigas.apps.systemstrategy.energigas.asyntask;


import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.orm.SugarRecord;
import com.orm.SugarTransactionHelper;


import org.json.JSONObject;


import java.util.List;


import energigas.apps.systemstrategy.energigas.apiRest.RestAPI;
import energigas.apps.systemstrategy.energigas.entities.Acceso;
import energigas.apps.systemstrategy.energigas.entities.Almacen;
import energigas.apps.systemstrategy.energigas.entities.BEGeneral;
import energigas.apps.systemstrategy.energigas.entities.CajaLiquidacion;
import energigas.apps.systemstrategy.energigas.entities.Cliente;
import energigas.apps.systemstrategy.energigas.entities.Establecimiento;
import energigas.apps.systemstrategy.energigas.entities.GeoUbicacion;
import energigas.apps.systemstrategy.energigas.entities.Persona;
import energigas.apps.systemstrategy.energigas.entities.PlanDistribucion;
import energigas.apps.systemstrategy.energigas.entities.PlanDistribucionDetalle;
import energigas.apps.systemstrategy.energigas.entities.Privilegio;
import energigas.apps.systemstrategy.energigas.entities.Rol;
import energigas.apps.systemstrategy.energigas.entities.RolAcceso;
import energigas.apps.systemstrategy.energigas.entities.RolPrivilegio;
import energigas.apps.systemstrategy.energigas.entities.RolUsuario;
import energigas.apps.systemstrategy.energigas.entities.UbicacionGeoreferencia;
import energigas.apps.systemstrategy.energigas.entities.Usuario;
import energigas.apps.systemstrategy.energigas.interfaces.OnLoginAsyntaskListener;
import energigas.apps.systemstrategy.energigas.utils.Constants;
import energigas.apps.systemstrategy.energigas.utils.Utils;

/**
 * Created by kelvi on 09/08/2016.
 */

public class LoginTask extends AsyncTask<String, String, String> implements SugarTransactionHelper.Callback {
    private static final String TAG = "LoginTask";

    private JSONObject jsonObjectUsuario = null;
    private JSONObject jsonObjectConceptos = null;
    private JSONObject jsonLiquidacion = null;

    private OnLoginAsyntaskListener aListener;
    private Context context;
    private int result = 0;
    private Usuario objUsuario;
    private BEGeneral objGeneral;
    private CajaLiquidacion cajaLiquidacion;

    public LoginTask(OnLoginAsyntaskListener loginAsyntaskListener) {

        this.aListener = loginAsyntaskListener;
        this.context = aListener.getContextActivity();

    }

    @Override
    protected String doInBackground(String... strings) {
        RestAPI restAPI = new RestAPI();
        String usuario = strings[0];
        String clave = strings[1];
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try {

            jsonObjectUsuario = restAPI.fobj_ObtenerUsuario(usuario, clave);
            objUsuario = mapper.readValue(Utils.getJsonObResult(jsonObjectUsuario), Usuario.class);

            jsonObjectConceptos = restAPI.fobj_ObtenerDatosGenerales();
            objGeneral = mapper.readValue(Utils.getJsonObResult(jsonObjectConceptos), BEGeneral.class);
            if (Utils.isSuccessful(jsonObjectUsuario) && Utils.isSuccessful(jsonObjectConceptos)) {

                if (objUsuario.getUsuIUsuarioId() < 0) {
                    result = 1;
                } else {
                    jsonLiquidacion = restAPI.fobj_ObtenerLiquidacion(objUsuario.getUsuIUsuarioId());
                    cajaLiquidacion = mapper.readValue(Utils.getJsonObResult(jsonLiquidacion), CajaLiquidacion.class);
                    SugarTransactionHelper.doInTransaction(this);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "Error: " + e.getMessage());
            result = 2;
        }


        return null;
    }


    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        switch (result) {
            case 1://Credenciales incorrectas
                aListener.onCredentialsFail();
                break;
            case 2://Error en la conexion
                aListener.onError("Error en la conexion al servidor");
                break;
            case 3://Error al insertar data
                aListener.onError("Error en guardar los datos");
                break;
            case 4://Error en la base de datos del servidor
                aListener.onErrorProcedure("Erroe en la base de datos del servidor");
                break;
            case 5://Ejecuto correctamente
                aListener.onSuccess();
                break;
        }
    }


    @Override
    public void manipulateInTransaction() {

        objUsuario.save();
        objUsuario.getPersona().save();
        objUsuario.getPersona().getUbicacion().save();
        List<Rol> rols = objUsuario.getItemsRoles();
        SugarRecord.saveInTx(rols);
        int count = 0;
        for (Rol rol : rols) {
            List<Acceso> accesos = rol.getItemsAccesos();
            RolUsuario rolUsuario = new RolUsuario(objUsuario.getUsuIUsuarioId(), rol.getIdRol());
            rolUsuario.save();
            SugarRecord.saveInTx(accesos);
            for (Acceso acceso : accesos) {

                RolAcceso rolAcceso = new RolAcceso(rol.getIdRol(), acceso.getIdAcceso(), true);
                rolAcceso.save();
                SugarRecord.saveInTx(acceso.getItemsPrivielgios());

                for (Privilegio privilegio : acceso.getItemsPrivielgios()) {
                    RolPrivilegio rolPrivilegio = new RolPrivilegio(rol.getIdRol(), privilegio.getAccesoId());
                    rolPrivilegio.save();
                    count++;
                }
            }
        }
        Log.d(TAG, " DATOS INSERTADOS : " + count);
        /**DATOS GENERALES**/

        SugarRecord.saveInTx(objGeneral.getItemsConceptos());
        SugarRecord.saveInTx(objGeneral.getItemsEstados());
        SugarRecord.saveInTx(objGeneral.getItemUbigeos());

        List<UbicacionGeoreferencia> ubicacionGeoreferencias = UbicacionGeoreferencia.listAll(UbicacionGeoreferencia.class);

        for (UbicacionGeoreferencia georeferencia : ubicacionGeoreferencias) {
            Log.d(TAG, " : " + georeferencia.getDescripcion());
        }
        Utils.saveStateLogin(context, true);


        if (cajaLiquidacion.getLiqId() > 0) {
            saveLiquidacion();
        }


        result = 5;
    }

    public void saveLiquidacion() {

        Long insert = cajaLiquidacion.save();
        boolean estadoB = true;
        if (insert > 0) {
            PlanDistribucion planDistribucion = cajaLiquidacion.getPlanDistribucionD();
            Long a = planDistribucion.save();
            Log.d(TAG, "ID PlanDistribucion " + a);

            List<PlanDistribucionDetalle> planDistribucionDetalles = planDistribucion.getItems();
            for (PlanDistribucionDetalle detalle : planDistribucionDetalles) {

                Long deta = detalle.save();
                if (deta < 0) {
                    estadoB = false;
                }
                Log.d(TAG, " PlanDistribucionDetalle " + deta);

            }

            Log.d(TAG, " INSERTO PLAN DISTRIBUCION DETALLE " + estadoB);
            estadoB = true;
            boolean estadoAl = true;
            boolean estadoEst = true;
            List<Cliente> clientes = cajaLiquidacion.getItemsClientes();

            for (Cliente cliente : clientes) {
                Long cli = cliente.save();
                if (cli < 0) {
                    estadoB = false;
                }
                Log.d(TAG, " INSERTO CLIENTE " + cli);
                /**Insertar Establecimientos**/

                for (Establecimiento establecimiento : cliente.getItemsEstablecimientos()) {
                    Long est = establecimiento.save();
                    if (est < 0) {
                        estadoEst = false;
                    }
                    Log.d(TAG, " INSERTO ESTABLECIMIENTO " + est);

                    /**Insertar Almacen**/

                    List<Almacen> almacens = establecimiento.getItemsAlmacen();
                    for (Almacen almacen : almacens) {
                        Long alm = almacen.save();
                        if (est < 0) {
                            estadoAl = false;
                        }
                        Log.d(TAG, " INSERTO ALMACEN " + alm);

                        Long geo = establecimiento.getUbicacion().save();
                        Log.d(TAG, " INSERTO GEOUBICACION " + geo);
                    }
                }


            }

            Log.d(TAG, " INSERTO CLIENTE " + estadoB);
            Log.d(TAG, " INSERTO ESTABLECIMIENTO " + estadoEst);
            Log.d(TAG, " INSERTO ALMACEN " + estadoAl);

            for (Cliente cliente : cajaLiquidacion.getItemsClientes()) {


                List<Establecimiento> establecimientos = cliente.getItemsEstablecimientos();
                SugarRecord.saveInTx(establecimientos);


                for (Establecimiento establecimiento : establecimientos) {

                    List<Almacen> almacens = establecimiento.getItemsAlmacen();
                    SugarRecord.saveInTx(almacens);
                    GeoUbicacion geoUbicacion = establecimiento.getUbicacion();
                    geoUbicacion.save();
                }

                Persona persona = cliente.getPersona();
                persona.save();
            }


        }
    }

    @Override
    public void errorInTransaction(String error) {
        result = 3;
    }
}
