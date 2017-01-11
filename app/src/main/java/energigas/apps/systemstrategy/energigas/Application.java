package energigas.apps.systemstrategy.energigas;


import android.content.Intent;
import android.util.Log;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.FirebaseDatabase;
import com.orm.SugarApp;

import energigas.apps.systemstrategy.energigas.services.ServiceSync;

/**
 * Created by kelvi on 17/08/2016.
 */

public class Application extends SugarApp {

    private static final String TAG = "ENERGIGASAPP";

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate");
        Fresco.initialize(this);
        if (!FirebaseApp.getApps(this).isEmpty()) {
//            FirebaseApp.initializeApp(this, FirebaseOptions.fromResource(this));
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        }
        startService(new Intent(this, ServiceSync.class));
        super.onCreate();//  //   //1/0
    }

    @Override
    public void onTerminate() {

        Log.d(TAG, "onTerminate");
        super.onTerminate();
    }


}