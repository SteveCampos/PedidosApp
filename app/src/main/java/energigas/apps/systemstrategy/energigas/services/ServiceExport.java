package energigas.apps.systemstrategy.energigas.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.orm.SugarRecord;

import java.util.List;

import energigas.apps.systemstrategy.energigas.apiRest.RestAPI;
import energigas.apps.systemstrategy.energigas.entities.Despacho;
import energigas.apps.systemstrategy.energigas.entities.SyncEstado;
import energigas.apps.systemstrategy.energigas.utils.Constants;
import energigas.apps.systemstrategy.energigas.utils.Utils;

/**
 * Created by kelvi on 26/10/2016.
 */

public class ServiceExport extends Service {

    private static final String TAG = "ServiceExport";
    private RestAPI restAPI;
    private SyncEstado syncEstado;


    @Override
    public void onCreate() {
        Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onCreate");
        restAPI = new RestAPI();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.d(TAG, "onStartCommand");
        Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show();
        /**Use Reflection**/
        List<Despacho> inIdDespacho = Utils.getListForExIn(Despacho.class, Constants.EXPORTAR);
        Log.d (TAG, "cantidad despacho: "+inIdDespacho.size());
        if (inIdDespacho.size()>0){
            String listString = Utils.getListStringFrom(inIdDespacho);
            Log.d(TAG,listString);
        }


        for (Despacho despacho : inIdDespacho){
            Log.d(TAG,despacho.getSerie()+"-"+despacho.getNumero());
            Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show();
        }

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
}
