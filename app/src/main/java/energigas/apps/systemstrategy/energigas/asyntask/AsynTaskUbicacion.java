package energigas.apps.systemstrategy.energigas.asyntask;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import energigas.apps.systemstrategy.energigas.apiRest.RestAPI;
import energigas.apps.systemstrategy.energigas.entities.Usuario;
import energigas.apps.systemstrategy.energigas.interfaces.MyLocationListener;
import energigas.apps.systemstrategy.energigas.utils.Constants;
import energigas.apps.systemstrategy.energigas.utils.Session;


/**
 * Created by kike on 30/01/2017.
 */

public class AsynTaskUbicacion extends AsyncTask<String, String, String> {

    private static final String TAG = "AsynTaskUbicacion";
    private RestAPI restAPI;
    private JSONObject jsonObjectResponse;
    /*Variables Gps*/
    private String mLatitud;
    private String mLong;
    private int idAgente;



    public AsynTaskUbicacion() {
    }

    @Override
    protected String doInBackground(String... params) {
        jsonObjectResponse = null;
        restAPI = new RestAPI();
        try {
            idAgente = Integer.parseInt(params[0]);
            mLatitud = params[1];
            mLong = params[2];
            jsonObjectResponse = restAPI.fupd_ActualizarRecorrido(idAgente,mLatitud,mLong);
            Log.d(TAG, "jsonObjectResponse:  "+jsonObjectResponse.toString());
           // Log.d(TAG, "latitud:  "+mLatitud +mLong);
        } catch (Exception ex) {
            Log.d(TAG, "Exception" + ex);
        }
        return null;
    }

    @Override
    protected void onCancelled(String s) {
        super.onCancelled(s);
        Log.d(TAG, "onCancelled: " + s);

    }
}
