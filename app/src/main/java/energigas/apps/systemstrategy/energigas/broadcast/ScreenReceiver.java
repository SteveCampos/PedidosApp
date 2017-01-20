package energigas.apps.systemstrategy.energigas.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import energigas.apps.systemstrategy.energigas.asyntask.AtencionesAsyntask;
import energigas.apps.systemstrategy.energigas.asyntask.ExportTask;
import energigas.apps.systemstrategy.energigas.interfaces.ExportObjectsListener;
import energigas.apps.systemstrategy.energigas.services.SyncData;
import energigas.apps.systemstrategy.energigas.utils.Constants;

/**
 * Created by kelvi on 25/12/2016.
 */

public class ScreenReceiver extends BroadcastReceiver implements ExportObjectsListener {

    @Override
    public void onReceive(final Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
            Log.v("KELVINLOCK", "In Method:  ACTION_SCREEN_OFF");
            // onPause() will be called.


                    Intent intentExportService = new Intent(context, SyncData.class);
                    intentExportService.setAction(Constants.ACTION_EXPORT_SERVICE);
                    context.startService(intentExportService);



        } else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
            Log.v("KELVINLOCK", "In Method:  ACTION_SCREEN_ON");
            //onResume() will be called.

            //Better check for whether the screen was already locked
            // if locked, do not take any resuming action in onResume()

            //Suggest you, not to take any resuming action here.
        } else if (intent.getAction().equals(Intent.ACTION_USER_PRESENT)) {
            Log.v("KELVINLOCK", "In Method:  ACTION_USER_PRESENT");
            //Handle resuming events
        }

    }


    @Override
    public void onLoadSuccess(String message) {
        Log.d("KELVINLOCK", "onLoadSuccess: " + message);
    }

    @Override
    public void onLoadError(String message) {
        Log.d("KELVINLOCK", "onLoadError: " + message);
    }

    @Override
    public void onLoadErrorProcedure(String message) {
        Log.d("KELVINLOCK", "onLoadErrorProcedure: " + message);
    }
}
