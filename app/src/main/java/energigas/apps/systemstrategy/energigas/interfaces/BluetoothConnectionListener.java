package energigas.apps.systemstrategy.energigas.interfaces;

/**
 * Created by Steve on 19/09/2016.
 */

public interface BluetoothConnectionListener {
    void onConnected(boolean connected);
    void onRead(byte[] bytes);
}
