package energigas.apps.systemstrategy.energigas.asyntask;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.view.View;

/**
 * Created by kelvi on 3/08/2016.
 */

public  class AsyntaskOpenAccount extends AsyncTask<String,Void,Void> {
    private ProgressDialog progressDialog;
    private FloatingActionButton floatingActionButton;
    public  AsyntaskOpenAccount (Activity activity, FloatingActionButton floatingActionButton){
        progressDialog = new ProgressDialog(activity);
        this.floatingActionButton = floatingActionButton;
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
        progressDialog.dismiss();
        floatingActionButton.setVisibility(View.GONE);
    }
}
