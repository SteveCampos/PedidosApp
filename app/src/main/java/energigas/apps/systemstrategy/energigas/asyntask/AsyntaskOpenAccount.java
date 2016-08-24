package energigas.apps.systemstrategy.energigas.asyntask;

import android.os.AsyncTask;
import android.util.Log;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.orm.SugarRecord;
import com.orm.SugarTransactionHelper;

import org.json.JSONObject;

import java.util.List;

import energigas.apps.systemstrategy.energigas.apiRest.RestAPI;
import energigas.apps.systemstrategy.energigas.entities.Almacen;
import energigas.apps.systemstrategy.energigas.entities.CajaLiquidacion;
import energigas.apps.systemstrategy.energigas.entities.CajaLiquidacionDetalle;
import energigas.apps.systemstrategy.energigas.entities.Cliente;
import energigas.apps.systemstrategy.energigas.entities.Establecimiento;
import energigas.apps.systemstrategy.energigas.entities.GeoUbicacion;
import energigas.apps.systemstrategy.energigas.entities.Pedido;
import energigas.apps.systemstrategy.energigas.entities.PedidoDetalle;
import energigas.apps.systemstrategy.energigas.entities.Persona;
import energigas.apps.systemstrategy.energigas.entities.PlanDistribucion;
import energigas.apps.systemstrategy.energigas.entities.PlanDistribucionDetalle;
import energigas.apps.systemstrategy.energigas.entities.Serie;
import energigas.apps.systemstrategy.energigas.interfaces.OnAsyntaskListener;
import energigas.apps.systemstrategy.energigas.utils.Constants;
import energigas.apps.systemstrategy.energigas.utils.Utils;

/**
 * Created by kelvi on 3/08/2016.
 */

public class AsyntaskOpenAccount extends AsyncTask<String, Void, Void> implements SugarTransactionHelper.Callback {
    private static final String TAG = "AsyntaskOpenAccount";
    private OnAsyntaskListener onAsyntaskListener;
    private RestAPI restAPI;
    private JSONObject jsonObject;
    private int estado = 0;
    private String message = "";
    private CajaLiquidacion cajaLiquidacion;

    public AsyntaskOpenAccount(OnAsyntaskListener onAsyntaskListener) {
        this.onAsyntaskListener = onAsyntaskListener;
        this.restAPI = new RestAPI();
    }


    @Override
    protected Void doInBackground(String... strings) {

        try {
            ObjectMapper mapper = new ObjectMapper();
            jsonObject = restAPI.fins_GuardarLiquidacion(strings[0]);
            Log.d(TAG, " :" + strings[0]);
            Log.d(TAG, ": " + jsonObject.toString());

            if (Utils.isSuccessful(jsonObject)) {
                mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                cajaLiquidacion = mapper.readValue(Utils.getJsonObResult(jsonObject), CajaLiquidacion.class);
                Log.d(TAG, "" + cajaLiquidacion.getLiqId());
                if (cajaLiquidacion.getLiqId() > 0) {
                    Log.d(TAG, "" + cajaLiquidacion.getFechaActualizacion());
                    SugarTransactionHelper.doInTransaction(this);
                } else {
                    estado = Constants.ERROR_PROCEDIMIENTO;
                    message = "Error de procedimiento";
                }

            } else {
                estado = Constants.ERROR_PROCEDIMIENTO;
                message = "Error de procedimiento";
            }


        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, ": ERROR : " + e.getMessage());
            estado = Constants.ERROR_CONEXION;
            message = "" + e.getMessage();

        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        switch (estado) {
            case Constants.ERROR_PROCEDIMIENTO:
                onAsyntaskListener.onLoadErrorProcedure(message);
                break;
            case Constants.ERROR_CONEXION:
                onAsyntaskListener.onLoadError(message);
                break;
            case Constants.ERROR_GUARDAR:
                onAsyntaskListener.onLoadError(message);
                break;
            case Constants.OPERACION_EXITOSA:
                onAsyntaskListener.onLoadSuccess(message);
                break;
            default:
                onAsyntaskListener.onLoadError(message);
                break;
        }

    }

    @Override
    public void manipulateInTransaction() {


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

            boolean pedidos = true;
            boolean pedidodetalle = true;
            List<Pedido> pedidoList = cajaLiquidacion.getItemsPedidos();
            for (Pedido pedido : pedidoList){
                Long p =pedido.save();
                if (p<0){
                    pedidos = false;
                }                List<PedidoDetalle> pedidoDetalles = pedido.getItems();
                for (PedidoDetalle pedidoDetalle : pedidoDetalles){
                       Long pd = pedidoDetalle.save();
                    if (pd<0){
                        pedidodetalle = false;
                    }
                }
            }
            Log.d(TAG, " INSERTO PEDIDO " + pedidos);
            Log.d(TAG, " INSERTO PEDIDO DETALLE " + pedidodetalle);
            estado = Constants.OPERACION_EXITOSA;

        }
    }

    @Override
    public void
    errorInTransaction(String error) {
        this.message = error;
        estado = Constants.ERROR_GUARDAR;
        Log.d(TAG, "CORRECTAMENTE");
    }
}
