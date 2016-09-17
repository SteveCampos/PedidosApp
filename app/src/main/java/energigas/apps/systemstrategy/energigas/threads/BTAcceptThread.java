package energigas.apps.systemstrategy.energigas.threads;

import android.bluetooth.BluetoothServerSocket;

/**
 * Created by Steve on 16/09/2016.
 */

public class BTAcceptThread extends Thread {
    //private final BluetoothServerSocket mmServerSocket;

    /*
    public BTAcceptThread() {
        // Use a temporary object that is later assigned to mmServerSocket,
        // because mmServerSocket is final
        BluetoothServerSocket tmp = null;
        try {
            // MY_UUID is the app's UUID string, also used by the client code
            tmp = mBluetoothAdapter.listenUsingRfcommWithServiceRecord(NAME, MY_UUID);
        } catch (IOException e) { }
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
                // Do work to manage the connection (in a separate thread)
                manageConnectedSocket(socket);
                mmServerSocket.close();
                break;
            }
        }
    }

    /** Will cancel the listening socket, and cause the thread to finish
    public void cancel() {
        try {
            mmServerSocket.close();
        } catch (IOException e) { }
    }
    */
}
