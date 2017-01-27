package energigas.apps.systemstrategy.energigas.asyntask;

import android.os.AsyncTask;
import android.util.Log;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.orm.SugarTransactionHelper;

import org.json.JSONObject;

import energigas.apps.systemstrategy.energigas.apiRest.ManipuleData;
import energigas.apps.systemstrategy.energigas.apiRest.RestAPI;
import energigas.apps.systemstrategy.energigas.entities.CajaLiquidacion;
import energigas.apps.systemstrategy.energigas.utils.Utils;

/**
 * Created by kelvi on 19/01/2017.
 */

public class ObtenerLiquidacion extends AsyncTask<String, String, String> implements SugarTransactionHelper.Callback {

    private CajaLiquidacion cajaLiquidacion;
    private ObjectMapper mapper;
    private static final String TAG = "ObtenerLiquidacion";

    @Override
    protected String doInBackground(String... params) {

        RestAPI restAPI = new RestAPI();
        JSONObject jsonObject = null;
        mapper = new ObjectMapper();
        try {
            jsonObject = restAPI.fobj_ObtenerLiquidacion(Integer.parseInt(params[0]));
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            Log.d(TAG, "" + jsonObject.toString());
            if (Utils.isSuccessful(jsonObject)) {

                cajaLiquidacion = mapper.readValue(Utils.getJsonObResult(jsonObject), CajaLiquidacion.class);

                SugarTransactionHelper.doInTransaction(this);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void manipulateInTransaction() {
        new ManipuleData().saveLiquidacion(cajaLiquidacion);
    }

    @Override
    public void errorInTransaction(String error) {

    }
}
