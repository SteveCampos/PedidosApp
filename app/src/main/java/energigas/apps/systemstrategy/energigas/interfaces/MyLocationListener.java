package energigas.apps.systemstrategy.energigas.interfaces;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

import energigas.apps.systemstrategy.energigas.services.ServiceSync;

/**
 * Created by kike on 30/01/2017.
 */

public class MyLocationListener implements LocationListener {


    private static final String TAG = "MyLocationListener";


    public mUbicacionLT listener;


    public interface mUbicacionLT {
        void onLocalPosition(Location location);
    }

    public MyLocationListener(mUbicacionLT listener) {
        this.listener = listener;

    }


    @Override
    public void onLocationChanged(Location location) {
        location.getLatitude();
        location.getLongitude();
        String Text = "Mi ubicacion actual es: " + "\n Lat = "
                + location.getLatitude() + "\n Long = " + location.getLongitude();
        Log.d(TAG, "MyLocationListenerGPSUPDATE: " + Text);


        listener.onLocalPosition(location);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.d(TAG, "GPS Activado" + provider);

    }

    @Override
    public void onProviderDisabled(String provider) {
        Log.d(TAG, "GPS Desactivado" + provider);

    }
}
