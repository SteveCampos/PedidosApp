package energigas.apps.systemstrategy.energigas.asyntask;

import android.os.AsyncTask;
import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.orm.SugarRecord;
import com.orm.SugarTransactionHelper;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import energigas.apps.systemstrategy.energigas.apiRest.RestAPI;
import energigas.apps.systemstrategy.energigas.entities.BERespuestaAtencion;
import energigas.apps.systemstrategy.energigas.entities.CajaLiquidacion;
import energigas.apps.systemstrategy.energigas.entities.CajaLiquidacionDetalle;
import energigas.apps.systemstrategy.energigas.entities.SyncEstado;
import energigas.apps.systemstrategy.energigas.utils.Constants;
import energigas.apps.systemstrategy.energigas.utils.Utils;

/**
 * Created by kike on 21/12/2016.
 */

public class AtencionesAsyntask extends AsyncTask<String, String, String> implements SugarTransactionHelper.Callback {


    private RestAPI restAPI;
    private JSONObject jsonObjectResponse;
    private ObjectMapper mapper;
    private List<CajaLiquidacionDetalle> listCajeLiquidacionDetalle;
    private static final String TAG = "AtencionesAsyntask";

    private List<CajaLiquidacionDetalle> mCajaLiquidacionDetalle;

    public AtencionesAsyntask() {

    }


    @Override
    protected String doInBackground(String... lists) {
        jsonObjectResponse = null;
        restAPI = new RestAPI();
        mapper = new ObjectMapper();
        /* Trae a todo CajaLiquidacionDetalle.listAll(CajaLiquidacionDetalle.class); */
        mCajaLiquidacionDetalle = Utils.getListForExIn(CajaLiquidacionDetalle.class, Constants.S_ACTUALIZADO);
        Log.d(TAG, "mCajaLiquidacionDetalle" + mCajaLiquidacionDetalle.size());
        if (mCajaLiquidacionDetalle.size() > 0) {
            for (CajaLiquidacionDetalle liquidacion : mCajaLiquidacionDetalle) {
                Log.d(TAG, "CajaLiquidacionDetalle liquidacion: " + liquidacion.getLidId());
            }

            try {
                jsonObjectResponse = restAPI.flst_UpdateDetalleLiquidacion(new ArrayList<Object>(mCajaLiquidacionDetalle));
                Log.d(TAG, "JSON jsonObjectResponse: " + jsonObjectResponse.toString());
                List<BERespuestaAtencion> beRespuestaAtencions = mapper.readValue(Utils.getJsonArResult(jsonObjectResponse), TypeFactory.defaultInstance().constructCollectionType(List.class,
                        BERespuestaAtencion.class));
                Log.d(TAG, "JSON LIQUIDACION: " + jsonObjectResponse);

                for (BERespuestaAtencion beRespuestaAtencion : beRespuestaAtencions) {

                    for (CajaLiquidacionDetalle cajaLiquidacionDetalle : mCajaLiquidacionDetalle) {


                        Log.d(TAG, "CajaLiq: " + cajaLiquidacionDetalle.getLidId() + "- respuesta : " + beRespuestaAtencion.getIdLiqDetalle());

                        if (new Long(cajaLiquidacionDetalle.getLidId()).equals(beRespuestaAtencion.getIdLiqDetalle())) {

                            Log.d(TAG, "ORDEN: " + cajaLiquidacionDetalle.getOrden() + "");
                            if (beRespuestaAtencion.getRespuesta() > 0) {
                                saveEstado(cajaLiquidacionDetalle.getLidId() + "", cajaLiquidacionDetalle.getLidId() + "", CajaLiquidacionDetalle.class);
                            }
                        }
                    }
                }
            } catch (Exception ex) {
                Log.d(TAG, "EXEPTION: " + ex.getMessage());
            }

        }

        return null;
    }

    private void saveEstado(String idAndroid, String idRemoto, Class aClass) {
        SyncEstado estado = SyncEstado.find(SyncEstado.class, " campo_Id=? and nombre_Tabla = ? ", new String[]{idAndroid, Utils.separteUpperCase(aClass.getSimpleName())}).get(0);
        estado.setSyncIdRemoto(Integer.parseInt(idRemoto));
        estado.setEstadoSync(Constants.S_IMPORTADO);
        estado.save();
    }


    @Override
    public void manipulateInTransaction() {
        for (int i = 0; i < listCajeLiquidacionDetalle.size(); i++) {
            Log.d(TAG, "OBJETO LIQUIDACION" + listCajeLiquidacionDetalle.get(i).toString());
        }
        SugarRecord.saveInTx(listCajeLiquidacionDetalle);
    }

    @Override
    public void errorInTransaction(String error) {
        Log.d(TAG, "GUARDAR ERROR: " + error);
    }
}
