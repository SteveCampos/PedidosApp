package energigas.apps.systemstrategy.energigas.asyntask;

import android.os.AsyncTask;

import energigas.apps.systemstrategy.energigas.interfaces.BluetoothConnectionListener;

/**
 * Created by kelvi on 13/10/2016.
 */

public class ConectarDispositivoAsyn extends AsyncTask<String, String, String> {
    BluetoothConnectionListener bluetoothConnectionListener;
    boolean estado = false;

    public ConectarDispositivoAsyn(BluetoothConnectionListener bluetoothConnectionListener) {
        this.bluetoothConnectionListener = bluetoothConnectionListener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {

        estado = true;

        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        bluetoothConnectionListener.onConnected(estado);
    }
}
