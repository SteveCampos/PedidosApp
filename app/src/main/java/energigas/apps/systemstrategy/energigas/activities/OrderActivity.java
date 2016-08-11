package energigas.apps.systemstrategy.energigas.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.entities.OrderDispatch;
import energigas.apps.systemstrategy.energigas.entities.OrderProduct;
import energigas.apps.systemstrategy.energigas.entities.Establecimiento;
import energigas.apps.systemstrategy.energigas.fragments.EstablecimientoFragment;
import energigas.apps.systemstrategy.energigas.fragments.OrderedProductFragment;

public class OrderActivity extends AppCompatActivity implements View.OnClickListener,
        EstablecimientoFragment.OnEstablecimientoClickListener,
        OrderedProductFragment.OnOrderedProductClickListener,
        OrderedProductFragment.OnDispatchClickListener{

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.tabs)
    TabLayout tabs;

    @BindView(R.id.fab)
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        ButterKnife.bind(this);
        initViews();
    }

    private void initViews() {
        // Adding Toolbar to Main screen
        setSupportActionBar(toolbar);
        // Setting ViewPager for each Tabs
        setupViewPager(viewPager);
        // Set Tabs inside Toolbar
        tabs.setupWithViewPager(viewPager);
        //setupTabIcons(tabs);


        // Adding menu icon to Toolbar
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
            supportActionBar.setTitle("Prioridad Alta");
        }


        //
        //viewPager.addOnPageChangeListener(this);

        // Adding Floating Action Button to bottom right of main view
    }


    // Add Fragments to Tabs
    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getSupportFragmentManager());
        adapter.addFragment(new OrderedProductFragment(), getString(R.string.ordered_product_title_name));
        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_order, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.action_print:
                startActivity(new Intent(OrderActivity.this, PrintFacturaActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.fab)
    void onFabClicke(){
        //Initiate Wizard
        startActivity(new Intent(OrderActivity.this, WizardOrderActivity.class));
    }

    @Override
    public void onClick(View view) {
        Snackbar.make(viewPager, "Pedido Go!", Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onEstablecimientoClickListener(Establecimiento establecimiento, View view) {
        Snackbar.make(viewPager, establecimiento.getEstVDescripcion(), Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onOrderedProductClickListener(OrderProduct orderProduct, View view, int typeClick) {
        Snackbar.make(viewPager, orderProduct.getProductOrderedTitle(), Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onDispatchClickListener(OrderDispatch orderDispatch, View view) {
        Snackbar.make(viewPager, orderDispatch.getTitleTank(), Snackbar.LENGTH_LONG).show();
    }

    private static class Adapter extends FragmentPagerAdapter {
        private static final String TAG = "FragmentPagerAdapter";
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        Adapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        void addFragment(Fragment fragment, String title) {
            Log.d(TAG, "addFragment" + title);
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }


    }
}
