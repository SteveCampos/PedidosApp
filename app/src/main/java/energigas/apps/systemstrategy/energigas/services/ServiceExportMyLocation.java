package energigas.apps.systemstrategy.energigas.services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONObject;

import energigas.apps.systemstrategy.energigas.LocationVehiculeListener;
import energigas.apps.systemstrategy.energigas.apiRest.RestAPI;
import energigas.apps.systemstrategy.energigas.interfaces.OnLocationListener;
import energigas.apps.systemstrategy.energigas.utils.Constants;

/**
 * Created by kelvi on 26/10/2016.
 */

public class ServiceExportMyLocation extends Service implements OnLocationListener {

    private static final String TAG = "ServiceExport";
    private LocationVehiculeListener locationVehiculeListener;


    @Override
    public void onCreate() {
        Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand");
        locationVehiculeListener = new LocationVehiculeListener(this, Constants.MIN_TIME_BW_UPDATES_5, new Long(0));
        return Service.START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy");
    }

    @Override
    public void setLatAndLong(Location latAndLong) {
        new UpdateLocation().execute(latAndLong);
        Log.d(TAG, "Lat: " + latAndLong.getLatitude() + "- Long" + latAndLong.getLongitude());
    }

    @Override
    public Context getContextActivity() {
        return getApplicationContext();
    }


    class UpdateLocation extends AsyncTask<Location, String, String> {
        private RestAPI restAPI;
        private JSONObject jsonObject;

        @Override
        protected String doInBackground(Location... params) {
          //  restAPI = new RestAPI();
            //jsonObject = restAPI.fins_GuardarCobro();

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            locationVehiculeListener.stopLocationUpdates();
            super.onPostExecute(s);
        }
    }
}
