package energigas.apps.systemstrategy.energigas.broadcast;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import energigas.apps.systemstrategy.energigas.interfaces.BluetoothDeviceListener;

/**
 * Created by Steve on 16/09/2016.
 */

public class BluetoothBroadCast extends BroadcastReceiver {

    private BluetoothDeviceListener listener;

    public void setListener(BluetoothDeviceListener listener) {
        this.listener = listener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (BluetoothDevice.ACTION_FOUND.equals(action)){
            //Get the BluetoothDevice object from the intent
            BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
            if (listener!=null){
                listener.onNewDeviceDiscovered(device);
            }
        }
    }
}
