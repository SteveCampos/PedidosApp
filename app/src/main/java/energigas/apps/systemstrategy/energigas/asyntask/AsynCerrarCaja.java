package energigas.apps.systemstrategy.energigas.asyntask;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import energigas.apps.systemstrategy.energigas.apiRest.RestAPI;
import energigas.apps.systemstrategy.energigas.entities.CajaLiquidacion;
import energigas.apps.systemstrategy.energigas.entities.Summary;
import energigas.apps.systemstrategy.energigas.interfaces.OnAsyntaskListener;
import energigas.apps.systemstrategy.energigas.utils.Constants;
import energigas.apps.systemstrategy.energigas.utils.NetworkUtil;
import energigas.apps.systemstrategy.energigas.utils.Utils;

/**
 * Created by kelvi on 27/12/2016.
 */

public class AsynCerrarCaja extends AsyncTask<String, String, String> {
    private RestAPI restAPI;
    private OnAsyntaskListener onAsyntaskListener;
    private JSONObject jsonObject;
    private CajaLiquidacion cajaLiquidacion;
    private String mensajeRespuesta = "";
    private static final String TAG = "AsynCerrarCaja";
    private Context context;
    private int mainEstado;

    public AsynCerrarCaja(Context context, OnAsyntaskListener onAsyntaskListener) {
        this.restAPI = new RestAPI();
        this.onAsyntaskListener = onAsyntaskListener;
        this.context = context;

    }

    @Override
    protected String doInBackground(String... params) {

        if (!NetworkUtil.hasActiveInternetConnection(context)) {
            Log.d(TAG, "Error pin ");
            mainEstado = -1;
            mensajeRespuesta = "Error conexion a internet";
            return null;
        }
        cajaLiquidacion = CajaLiquidacion.getCajaLiquidacionCerrarCaja(params[0], params[1], params[2]);
        cajaLiquidacion.setEstadoId(Constants.CAJA_LIQUIDACION_CERRADO);
        Summary summary = Summary.getListSummary(context);
        cajaLiquidacion.setIngresos(summary.getIngresosTotales());
        cajaLiquidacion.setGastos(summary.getGastos());
        cajaLiquidacion.setSaldoFinal(summary.getEfectivoRendir());


        if (cajaLiquidacion == null) {
            mensajeRespuesta = "Error al obtener el objeto";
            return null;
        }

        try {
            cajaLiquidacion.save();
            jsonObject = restAPI.fupd_CajaLiquidacion(cajaLiquidacion);
            Log.d(TAG, "DATO: " + jsonObject);
            if (Utils.isSuccessful(jsonObject)) {
                mainEstado = jsonObject.getInt("Value");
                if (mainEstado < 0) {
                    mensajeRespuesta = "Error en cerrar caja";
                } else {
                    mensajeRespuesta = "Caja cerrada correctamente";
                }
            }
            Log.d(TAG, jsonObject.toString());
        } catch (Exception e) {
            e.printStackTrace();
            mainEstado = -1;
            mensajeRespuesta = e.getMessage();
        }
        Log.d(TAG, mensajeRespuesta);

        return null;
    }


    @Override
    protected void onPostExecute(String s) {
        if (mainEstado > 0) {
            onAsyntaskListener.onLoadSuccess(mensajeRespuesta, null);
        } else {
            onAsyntaskListener.onLoadError(mensajeRespuesta);
        }
    }
}
