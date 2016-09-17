package energigas.apps.systemstrategy.energigas.threads;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.util.Log;

import java.io.IOException;
import java.util.UUID;

/**
 * Created by Steve on 16/09/2016.
 */

public class BTConnectThread extends Thread {
    private static final String TAG = BTConnectThread.class.getSimpleName();
    private final BluetoothSocket mmSocket;
    private final BluetoothDevice mmDevice;

    public static final String BT_SERVICE_UUID = "81a612ba-ca32-4ac7-89d9-60a39f3cc668";

    public BTConnectThread(BluetoothDevice device) {

        BluetoothSocket tmp = null;
        mmDevice = device;

        try{
            tmp = device.createRfcommSocketToServiceRecord(UUID.fromString(BT_SERVICE_UUID));
        }catch (IOException e){
            Log.d(TAG, "device.createRfcommSocketToServiceRecord IOException: "+ e);
        }
        mmSocket = tmp;
    }

    public void run(){
        //don´t forget cancel discovery
        try{
            //Connect the device through the socket, This will block
            //until it succeds or throws and exception
            mmSocket.connect();
        } catch (IOException e) {
            //Unable to connect; close the socket and get out

            Log.d(TAG, " mmSocket.connect() IOException: " + e);
            try {
                mmSocket.close();
            } catch (IOException closeException) {
                Log.d(TAG, "mmSocket.close() IOException: " + closeException);
                return;
            }
        }

        //Do work to manage the connection, (in a separate thread)
        manageConnectedSocket(mmSocket);
    }

    private void manageConnectedSocket(BluetoothSocket mmSocket) {
        BTConnectedThread btConnectedThread = new BTConnectedThread(mmSocket);
        btConnectedThread.run();
        btConnectedThread.write("Some Data".getBytes());
    }

    public void cancel(){
        try {
            mmSocket.close();
        } catch (IOException e) {
            Log.d(TAG, "mmSocket.close() IOException: " + e);
            e.printStackTrace();
        }
    }

}
