package energigas.apps.systemstrategy.energigas.services;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.Nullable;

import energigas.apps.systemstrategy.energigas.broadcast.ScreenReceiver;

/**
 * Created by kelvi on 25/12/2016.
 */


public class ServiceSync extends Service {

    private BroadcastReceiver mReceiver;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);

        filter.addAction(Intent.ACTION_SCREEN_OFF);
        filter.addAction(Intent.ACTION_USER_PRESENT);

        mReceiver = new ScreenReceiver();
        registerReceiver(mReceiver, filter);


        return Service.START_STICKY;
    }
}
