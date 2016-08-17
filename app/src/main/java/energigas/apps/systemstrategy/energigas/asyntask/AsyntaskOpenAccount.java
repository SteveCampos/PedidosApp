package energigas.apps.systemstrategy.energigas.asyntask;

import android.os.AsyncTask;
import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONObject;

import energigas.apps.systemstrategy.energigas.apiRest.RestAPI;
import energigas.apps.systemstrategy.energigas.entities.CajaLiquidacion;
import energigas.apps.systemstrategy.energigas.interfaces.OnAsyntaskListener;
import energigas.apps.systemstrategy.energigas.utils.Utils;

/**
 * Created by kelvi on 3/08/2016.
 */

public class AsyntaskOpenAccount extends AsyncTask<String, Void, Void> {
private static final String TAG ="AsyntaskOpenAccount";
    private OnAsyntaskListener onAsyntaskListener;
    private RestAPI restAPI;
    private JSONObject jsonObject;

    public AsyntaskOpenAccount(OnAsyntaskListener onAsyntaskListener) {
        this.onAsyntaskListener = onAsyntaskListener;
        this.restAPI = new RestAPI();
    }


    @Override
    protected Void doInBackground(String... strings) {

        try {
            ObjectMapper mapper = new ObjectMapper();
            jsonObject = restAPI.fins_GuardarLiquidacion(strings[0]);
            Log.d(TAG," :"+strings);
            Log.d(TAG,": "+jsonObject.toString());
            CajaLiquidacion cajaLiquidacion = mapper.readValue(Utils.getJsonObResult(jsonObject),CajaLiquidacion.class);
            Log.d(TAG,""+cajaLiquidacion.getLiqId());
            if (cajaLiquidacion.getLiqId()>0){
                Log.d(TAG,""+cajaLiquidacion.getFechaActualizacion());
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG,": ERROR : "+jsonObject.toString());
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        //onAsyntaskListener.onLoadSuccess("Ok");
        onAsyntaskListener.onLoadError("Error");
    }
}
