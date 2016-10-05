package energigas.apps.systemstrategy.energigas.firebase;

import android.content.Context;
import android.provider.SyncStateContract;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Steve on 29/09/2016.
 */

public class BluetoothFire {

    private String androidID;

    private static final String CHILD_DEVICE = "device";


    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference devicesReference;
    private DatabaseReference meDeviceReference;

    public BluetoothFire(String androidID) {
        this.androidID = androidID;
        this.firebaseDatabase = FirebaseDatabase.getInstance();
        this.devicesReference = firebaseDatabase.getReference(CHILD_DEVICE);
        this.meDeviceReference = devicesReference.child(androidID);

    }

    public void saveMessage(MessageBluetooth message){
        String keyMessage = meDeviceReference.push().getKey();
        Map<String, Object> messageBluetooth = new HashMap<>();
        messageBluetooth.put(keyMessage, message.toMap());
        meDeviceReference.updateChildren(messageBluetooth);
    }



}
