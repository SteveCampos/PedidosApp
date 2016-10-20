package energigas.apps.systemstrategy.energigas.asyntask;

import android.os.AsyncTask;

import energigas.apps.systemstrategy.energigas.interfaces.BluetoothConnectionListener;

/**
 * Created by kelvi on 13/10/2016.
 */

public class ConectarDispositivoAsyn extends AsyncTask<String,String,String>{
    BluetoothConnectionListener bluetoothConnectionListener;
    boolean estado = false;
    public ConectarDispositivoAsyn (BluetoothConnectionListener bluetoothConnectionListener){
        this.bluetoothConnectionListener = bluetoothConnectionListener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {

        estado = Math.random() < 0.5;
        try {
            Thread.sleep(5600);

        } catch (Exception e) {
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        bluetoothConnectionListener.onConnected(estado);
    }
}
