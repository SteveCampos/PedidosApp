package energigas.apps.systemstrategy.energigas.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.adapters.CustomTabsAdapter;
import energigas.apps.systemstrategy.energigas.entities.Pedido;
import energigas.apps.systemstrategy.energigas.fragments.DespachoFragment;
import energigas.apps.systemstrategy.energigas.fragments.OrderDetailFragment;
import energigas.apps.systemstrategy.energigas.fragments.AlmacenFragment;
import energigas.apps.systemstrategy.energigas.utils.Session;

public class StationOrderActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.tablayout)
    TabLayout tabLayout;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    private CustomTabsAdapter tabsAdapter;

    private Pedido pedido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_order);
        pedido = Pedido.find(Pedido.class," pe_Id = ?  ",new String[]{Session.getPedido(this).getPeId()+""}).get(0);

        ButterKnife.bind(this);
        initViews();
    }


    private void initViews() {
        setToolbar();
        setTabsAdapterFragment();
        viewpager.setCurrentItem(0);
    }

    private void setToolbar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) //
        {
            setTitle("Pedido "+pedido.getNumero());
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }
    private void setTabsAdapterFragment (){
        tabsAdapter = new CustomTabsAdapter(getSupportFragmentManager());
        tabsAdapter.addFragment(new OrderDetailFragment(), "ITEM");
        tabsAdapter.addFragment(new AlmacenFragment(), "TANQUES");
        tabsAdapter.addFragment(new DespachoFragment(), "DESPACHOS");

        viewpager.setAdapter(tabsAdapter);
        tabLayout.setupWithViewPager(viewpager);
    }


}
