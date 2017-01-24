package energigas.apps.systemstrategy.energigas.services;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import energigas.apps.systemstrategy.energigas.broadcast.ScreenReceiver;
import energigas.apps.systemstrategy.energigas.entities.CajaLiquidacion;
import energigas.apps.systemstrategy.energigas.interfaces.ExportObjectsListener;
import energigas.apps.systemstrategy.energigas.utils.Constants;
import energigas.apps.systemstrategy.energigas.utils.Session;

/**
 * Created by kelvi on 25/12/2016.
 */


public class ServiceSync extends Service implements ExportObjectsListener {

    private BroadcastReceiver mReceiver;

    private DatabaseReference mDatabase;
    private DatabaseReference myRefFondos;
    private CajaLiquidacion cajaLiquidacion;
    private static final String TAG = "ServiceSync";


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

        cajaLiquidacion = CajaLiquidacion.getCajaLiquidacion(Session.getCajaLiquidacion(this).getLiqId() + "");

        if (cajaLiquidacion != null) {
            if (FirebaseApp.getApps(this).isEmpty()) {
                FirebaseApp.initializeApp(getApplicationContext(), FirebaseOptions.fromResource(getApplicationContext()));

                return Service.START_STICKY;
            }
            mDatabase = FirebaseDatabase.getInstance().getReference();

            myRefFondos = mDatabase.child(Constants.FIREBASE_CHILD_FONDOS).child(cajaLiquidacion.getLiqId() + "");
            myRefFondos.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Log.d("ServiceSync", "SERVICE_SYNC: " + cajaLiquidacion.getLiqId());
                    CajaLiquidacion liquidacion = dataSnapshot.getValue(CajaLiquidacion.class);
                    CajaLiquidacion liquidacionGuardar = CajaLiquidacion.getCajaLiquidacion(liquidacion.getLiqId() + "");
                    liquidacionGuardar.setSaldoInicial(liquidacion.getSaldoInicial());
                    Long aLong = liquidacionGuardar.save();
                    Log.d(TAG, "ACTUALIZO: " + aLong);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }


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
