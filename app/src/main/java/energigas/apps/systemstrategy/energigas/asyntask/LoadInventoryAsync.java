package energigas.apps.systemstrategy.energigas.asyntask;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import energigas.apps.systemstrategy.energigas.adapters.LoadInventoryAdapter;
import energigas.apps.systemstrategy.energigas.entities.Inventory;

/**
 * Created by kelvi on 1/08/2016.
 */

public class LoadInventoryAsync extends AsyncTask<String, Void, Void> {

    private ProgressDialog progressDialog;
    private Inventory inventory;
    private RecyclerView recyclerView;
    private Context context;

    public LoadInventoryAsync(Context context) {
        progressDialog = new ProgressDialog(context);
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
        /*LoadInventoryAdapter loadInventoryAdapter = new LoadInventoryAdapter(inventory.getInventoryList(), activity);
        recyclerView.setAdapter(loadInventoryAdapter);
        recyclerView.setHasFixedSize(true);*/
       // recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        progressDialog.dismiss();
    }
}
