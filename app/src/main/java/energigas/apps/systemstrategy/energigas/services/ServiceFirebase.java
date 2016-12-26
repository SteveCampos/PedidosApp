package energigas.apps.systemstrategy.energigas.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import energigas.apps.systemstrategy.energigas.utils.Constants;

/**
 * Created by kelvi on 26/10/2016.
 */

public class ServiceFirebase extends Service {

    private static final String TAG = "ServiceExport";
    private DatabaseReference mDatabase;
    private DatabaseReference myRefPedido;


    @Override
    public void onCreate() {
        Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onCreate");

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand");
        if (FirebaseApp.getApps(this).isEmpty()) {
            FirebaseApp.initializeApp(getApplicationContext(), FirebaseOptions.fromResource(getApplicationContext()));
            mDatabase = FirebaseDatabase.getInstance().getReference();
            return Service.START_STICKY;
        }

        myRefPedido = mDatabase.child(Constants.FIREBASE_CHILD_NOTIFICACIONES).child(Constants.FIREBASE_CHILD_ATENCION_PEDIDO);
        myRefPedido.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


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
