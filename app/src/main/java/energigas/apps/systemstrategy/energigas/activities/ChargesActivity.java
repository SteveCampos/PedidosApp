package energigas.apps.systemstrategy.energigas.activities;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import butterknife.ButterKnife;
import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.adapters.ChargesAdapter;
import energigas.apps.systemstrategy.energigas.entities.Charges;
import energigas.apps.systemstrategy.energigas.fragments.ChargesFragment;

/**
 * Created by Kike on 1/08/2016.
 */

public class ChargesActivity extends AppCompatActivity implements ChargesAdapter.OnChargesClickListener{


    public ChargesFragment.OnChargesClickListener listener;


    private RecyclerView recyclerView;

    public ChargesActivity() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragmen_charges);
        ButterKnife.bind(this);

        recyclerView = (RecyclerView)findViewById(R.id.rv_charges);

        ChargesAdapter adapter = new ChargesAdapter(Charges.getCharges(),getApplicationContext(),this);
        recyclerView.setAdapter(adapter);
        //recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_menu_accountsummary, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onChargesClickListener(Charges charges, View view) {
        Snackbar.make(view,charges.getmComprobante(),Snackbar.LENGTH_LONG).show();
    }
    public interface OnChargesClickListener{
        void onCharfesClickListener(Charges charges, View view);
    }
}
