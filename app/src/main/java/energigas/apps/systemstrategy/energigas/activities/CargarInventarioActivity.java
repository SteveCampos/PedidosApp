package energigas.apps.systemstrategy.energigas.activities;


import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;


import butterknife.BindView;
import butterknife.ButterKnife;
import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.adapters.LoadInventoryAdapter;
import energigas.apps.systemstrategy.energigas.asyntask.LoadInventoryAsync;
import energigas.apps.systemstrategy.energigas.entities.Inventory;

public class CargarInventarioActivity extends AppCompatActivity {


    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.layout_not_found)
    LinearLayout layout_not_found;
    private CollapsingToolbarLayout collapsingToolbar;
    LoadInventoryAdapter loadInventoryAdapter;
    Inventory inventory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_inventory);
        ButterKnife.bind(this);
        inventory = new Inventory();
        loadInventoryAdapter = new LoadInventoryAdapter(inventory.instanceListInventory(), this);

        showEmptyView();

        recyclerView.setAdapter(loadInventoryAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        Toolbar toolbar = (Toolbar) findViewById(R.id.anim_toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setLoadInventory();
            }
        });
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setExpandedTitleTextAppearance(R.style.CustomToolbar);

    }

    private void setLoadInventory() {
        new LoadInventoryAsync(this).execute();
        loadInventoryAdapter.addNewItem(new Inventory("Gas licuado de petroleo", 2000, 500, 1500, 455, null));
        showEmptyView();
    }

    private void showEmptyView() {
        if (loadInventoryAdapter.getItemCount() > 0) {
            layout_not_found.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        } else {

            layout_not_found.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }
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


}
