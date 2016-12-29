package energigas.apps.systemstrategy.energigas.services;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.Nullable;

import energigas.apps.systemstrategy.energigas.asyntask.ExportTask;
import energigas.apps.systemstrategy.energigas.broadcast.ScreenReceiver;
import energigas.apps.systemstrategy.energigas.interfaces.ExportObjectsListener;
import energigas.apps.systemstrategy.energigas.utils.Constants;

/**
 * Created by kelvi on 25/12/2016.
 */


public class ServiceSync extends Service implements ExportObjectsListener {

    private BroadcastReceiver mReceiver;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new ExportTask(this, this).execute(Constants.EXPORTAR_TODO, Constants.S_CREADO);
        IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);

        filter.addAction(Intent.ACTION_SCREEN_OFF);
        filter.addAction(Intent.ACTION_USER_PRESENT);

        mReceiver = new ScreenReceiver();
        registerReceiver(mReceiver, filter);


        return Service.START_STICKY;
    }

    @Override
    public void onLoadSuccess(String message) {

    }

    @Override
    public void onLoadError(String message) {

    }

    @Override
    public void onLoadErrorProcedure(String message) {

    }
}
