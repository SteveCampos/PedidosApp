package energigas.apps.systemstrategy.energigas.threads;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.util.Log;

import com.google.common.base.Charsets;

import java.io.IOException;
import java.util.UUID;

import energigas.apps.systemstrategy.energigas.interfaces.BluetoothConnectionListener;

/**
 * Created by Steve on 16/09/2016.
 */

public class BTConnectThread extends Thread{
    private static final String TAG = BTConnectThread.class.getSimpleName();
    private final BluetoothSocket mmSocket;
    private final BluetoothDevice mmDevice;

    BTConnectedThread btConnectedThread;
    BluetoothConnectionListener listener;

    public static final String BT_SERVICE_UUID = "81a612ba-ca32-4ac7-89d9-60a39f3cc668";

    public BTConnectThread(BluetoothDevice device,  BluetoothConnectionListener listener) {
        this.listener =  listener;
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
        boolean success = true;
        try{
            //Connect the device through the socket, This will block
            //until it succeds or throws and exception
            mmSocket.connect();
        } catch (IOException e) {
            //Unable to connect; close the socket and get out

            success = false;
            Log.d(TAG, " mmSocket.connect() IOException: " + e);
            try {
                mmSocket.close();
            } catch (IOException closeException) {
                Log.d(TAG, "mmSocket.close() IOException: " + closeException);
                return;
            }
        }

        connected(success);

        //Do work to manage the connection, (in a separate thread)
        manageConnectedSocket(mmSocket);
    }

    private void connected(boolean success){
        if (listener!=null){
            listener.onConnected(success);
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



    public void cancel(){
        try {
            mmSocket.close();
        } catch (IOException e) {
            Log.d(TAG, "mmSocket.close() IOException: " + e);
            e.printStackTrace();
        }
    }

}
