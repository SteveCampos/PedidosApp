package energigas.apps.systemstrategy.energigas.threads;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.util.Log;

import com.google.common.base.Charsets;

import java.io.IOException;
import java.util.UUID;


/**
 * Created by Steve on 16/09/2016.
 */

public class BTAcceptThread extends Thread {
    private static final UUID MY_UUID = UUID.fromString("0a0b5e83-15f5-42c6-918e-7ebd4172dd6e");
    private static final String TAG = BTAcceptThread.class.getSimpleName();
    private final BluetoothServerSocket mmServerSocket;
    private BluetoothAdapter mBluetoothAdapter;

    public static final String NAME = "BT_ENERGIGAS_SERVICE";

    BTConnectedThread btConnectedThread;

    public BTAcceptThread(BluetoothAdapter mBluetoothAdapter) {
        this.mBluetoothAdapter = mBluetoothAdapter;
        // Use a temporary object that is later assigned to mmServerSocket,
        // because mmServerSocket is final
        BluetoothServerSocket tmp = null;
        try {
            // MY_UUID is the app's UUID string, also used by the client code
            tmp = mBluetoothAdapter.listenUsingRfcommWithServiceRecord(NAME, MY_UUID);
        } catch (IOException e) {
            Log.d(TAG, "mBluetoothAdapter.listenUsingRfcommWithServiceRecord IOException " + e);
        }
        mmServerSocket = tmp;
    }

    public void run() {
        BluetoothSocket socket = null;
        // Keep listening until exception occurs or a socket is returned
        while (true) {
            try {
                socket = mmServerSocket.accept();
            } catch (IOException e) {
                break;
            }
            // If a connection was accepted
            if (socket != null) {
                Log.d(TAG, "a connection was accepted");
                // Do work to manage the connection (in a separate thread)
                manageConnectedSocket(socket);
//                cancel();
                break;
            }
        }
    }

    private void manageConnectedSocket(BluetoothSocket mmSocket) {
        btConnectedThread = new BTConnectedThread(mmSocket);
        btConnectedThread.run();
    }

    public void write(byte[] bytes){
        Log.d(TAG, "write: "+ new String(bytes, Charsets.US_ASCII));
        if (btConnectedThread!=null){
            btConnectedThread.write("Some Data".getBytes());
        }
    }


    /** Will cancel the listening socket, and cause the thread to finish*/
    public void cancel() {
        try {
            mmServerSocket.close();
        } catch (IOException e) { }
    }

}
