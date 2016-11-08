package energigas.apps.systemstrategy.energigas.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import energigas.apps.systemstrategy.energigas.utils.NetworkUtil;

/**
 * Created by kelvi on 28/10/2016.
 */

public class NetworkStateReceiver extends BroadcastReceiver {

    private static final String TAG = "NetworkStateReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "Network connectivity change");
        if (intent.getExtras() != null) {
            NetworkInfo ni = (NetworkInfo) intent.getExtras().get(ConnectivityManager.EXTRA_NETWORK_INFO);
            if (ni != null && ni.getState() == NetworkInfo.State.CONNECTED) {
                Log.d(TAG, "Network " + ni.getTypeName() + " connected");

                if (NetworkUtil.hasActiveInternetConnection(context)){
                    Log.d(TAG, "conectado con internet");
                }else {
                    Log.d(TAG, "conectado pero sin internet");
                }

            }
        }
        if (intent.getExtras().getBoolean(ConnectivityManager.EXTRA_NO_CONNECTIVITY, Boolean.FALSE)) {
            Log.d(TAG, "There's no network connectivity");
        }
    }
}
