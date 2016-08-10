package energigas.apps.systemstrategy.energigas.asyntask;

import android.os.AsyncTask;

import energigas.apps.systemstrategy.energigas.interfaces.OnAsyntaskListener;

/**
 * Created by kelvi on 3/08/2016.
 */

public class AsyntaskOpenAccount extends AsyncTask<String, Void, Void> {

    OnAsyntaskListener onAsyntaskListener;

    public AsyntaskOpenAccount(OnAsyntaskListener onAsyntaskListener) {
        this.onAsyntaskListener = onAsyntaskListener;
    }


    @Override
    protected Void doInBackground(String... strings) {


        try {
            Thread.sleep(1600);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        //onAsyntaskListener.onLoadSuccess("Ok");
        onAsyntaskListener.onLoadError("Error");
    }
}
