package energigas.apps.systemstrategy.energigas.activities;


import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.adapters.LoadInventoryAdapter;
import energigas.apps.systemstrategy.energigas.asyntask.LoadInventoryAsync;
import energigas.apps.systemstrategy.energigas.entities.Inventory;

public class LoadInventory extends AppCompatActivity {


    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private CollapsingToolbarLayout collapsingToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_inventory);
        ButterKnife.bind(this);
        LoadInventoryAdapter loadInventoryAdapter = new LoadInventoryAdapter(Inventory.getInventoryList(),this);
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

    private void setLoadInventory(){
        new LoadInventoryAsync(this).execute();
    }



}
