package energigas.apps.systemstrategy.energigas.interfaces;

import android.bluetooth.BluetoothDevice;

/**
 * Created by Steve on 16/09/2016.
 */

public interface BluetoothDeviceListener {
    void onNewDeviceDiscovered(BluetoothDevice device);
    void onDeviceClickListener(BluetoothDevice device);
}
