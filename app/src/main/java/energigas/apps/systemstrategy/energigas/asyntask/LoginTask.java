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
import energigas.apps.systemstrategy.energigas.entities.BEGeneral;
import energigas.apps.systemstrategy.energigas.entities.Privilegio;
import energigas.apps.systemstrategy.energigas.entities.Rol;
import energigas.apps.systemstrategy.energigas.entities.RolAcceso;
import energigas.apps.systemstrategy.energigas.entities.RolPrivilegio;
import energigas.apps.systemstrategy.energigas.entities.RolUsuario;
import energigas.apps.systemstrategy.energigas.entities.UbicacionGeoreferencia;
import energigas.apps.systemstrategy.energigas.entities.Usuario;
import energigas.apps.systemstrategy.energigas.interfaces.OnLoginAsyntaskListener;
import energigas.apps.systemstrategy.energigas.utils.Utils;

/**
 * Created by kelvi on 09/08/2016.
 */

public class LoginTask extends AsyncTask<String, String, String> implements SugarTransactionHelper.Callback {
    private static final String TAG = "LoginTask";

    private JSONObject jsonObjectUsuario = null;
    private JSONObject jsonObjectConceptos = null;

    private OnLoginAsyntaskListener aListener;
    private Context context;
    private int result = 0;
    private Usuario objUsuario;
    private BEGeneral objGeneral;

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


        result = 5;
    }

    @Override
    public void errorInTransaction(String error) {
        result = 3;
    }
}
