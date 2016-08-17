package energigas.apps.systemstrategy.energigas;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import energigas.apps.systemstrategy.energigas.interfaces.OnLocationListener;

/**
 * Created by kelvi on 15/08/2016.
 */

public class LocationVehiculeListener implements LocationListener {

    private static final String TAG = "LocationVehiculeListener";
    private OnLocationListener onLocationListener;
    private static final long MIN_TIME_BW_UPDATES = 20*1000;
    private Context context;
    protected LocationManager locationManager;

    public LocationVehiculeListener(OnLocationListener onLocationListener) {
        this.onLocationListener = onLocationListener;
        context = onLocationListener.getContextActivity();
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);


        // TODO check is network/gps is enabled and display the system settings
        // see
        // https://github.com/unchiujar/Umbra/blob/master/src/main/java/org/unchiujar/umbra/activities/FogOfExplore.java#L599
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling

            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                MIN_TIME_BW_UPDATES, 0, this);


    }

    public void stopLocationUpdates() {
        if (locationManager != null) {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            locationManager.removeUpdates(LocationVehiculeListener.this);
        }
    }



    @Override
    public void onLocationChanged(Location location) {
        Log.d(TAG, "onLocationChanged");
        onLocationListener.setLatAndLong(location);
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {
        Log.d(TAG, "onStatusChanged");
    }

    @Override
    public void onProviderEnabled(String s) {
        Log.d(TAG, "onProviderEnabled: " + s);
    }

    @Override
    public void onProviderDisabled(String s) {
        Log.d(TAG, "onProviderDisabled: " + s);
    }
}
