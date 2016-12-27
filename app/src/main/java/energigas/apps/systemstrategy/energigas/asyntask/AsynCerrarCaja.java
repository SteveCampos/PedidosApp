package energigas.apps.systemstrategy.energigas.asyntask;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import energigas.apps.systemstrategy.energigas.apiRest.RestAPI;
import energigas.apps.systemstrategy.energigas.entities.CajaLiquidacion;
import energigas.apps.systemstrategy.energigas.interfaces.OnAsyntaskListener;
import energigas.apps.systemstrategy.energigas.utils.NetworkUtil;

/**
 * Created by kelvi on 27/12/2016.
 */

public class AsynCerrarCaja extends AsyncTask<String, String, String> {
    private RestAPI restAPI;
    private OnAsyntaskListener onAsyntaskListener;
    private JSONObject jsonObject;
    private CajaLiquidacion cajaLiquidacion;
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

            return null;
        }
        cajaLiquidacion = CajaLiquidacion.getCajaLiquidacion(params[0]);
        try {
            jsonObject = restAPI.fupd_CajaLiquidacion(cajaLiquidacion);
            Log.d(TAG, jsonObject.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    @Override
    protected void onPostExecute(String s) {
        switch (mainEstado) {
            case -1:
                break;
            case 1:
                break;
            case 2:
                break;
        }
    }
}
