package energigas.apps.systemstrategy.energigas.broadcast;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

import energigas.apps.systemstrategy.energigas.activities.ModalActivity;
import energigas.apps.systemstrategy.energigas.fragments.DialogGeneral;
import energigas.apps.systemstrategy.energigas.interfaces.DialogGeneralListener;
import energigas.apps.systemstrategy.energigas.utils.Utils;

/**
 * Created by kelvi on 09/12/2016.
 */

public class GpsLocationReceiver extends BroadcastReceiver {


    private Context context;
    private static final String TAG = "GpsLocationReceiver";


    @Override
    public void onReceive(Context context1, Intent intent) {
        this.context = context1;

        Log.d(TAG, "TIPO: " + intent.getAction());

        if (intent.getAction().equals("android.location.PROVIDERS_CHANGED")) {


            if (!Utils.getGpsEnable(context)) {
                Toast.makeText(context, "GPS Desactivado",
                        Toast.LENGTH_SHORT).show();
                Intent myIntent = new Intent(context, ModalActivity.class);
                myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(myIntent);


            } else {
                Toast.makeText(context, "GPS Activado", Toast.LENGTH_SHORT).show();
            }

            ObservableObject.getInstance().updateValue(Utils.getGpsEnable(context));
        }

    }


}
