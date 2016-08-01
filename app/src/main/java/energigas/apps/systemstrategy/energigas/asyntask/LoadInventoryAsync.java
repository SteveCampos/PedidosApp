package energigas.apps.systemstrategy.energigas.asyntask;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;

import energigas.apps.systemstrategy.energigas.activities.LoadInventory;

/**
 * Created by kelvi on 1/08/2016.
 */

public class LoadInventoryAsync  extends AsyncTask<String,Void,Void>{

    private ProgressDialog progressDialog;

    public LoadInventoryAsync (Activity activity){
        progressDialog = new ProgressDialog(activity);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(true);
        progressDialog.setTitle("Cargando");
        progressDialog.show();

    }

    @Override
    protected Void doInBackground(String... strings) {
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }
}
