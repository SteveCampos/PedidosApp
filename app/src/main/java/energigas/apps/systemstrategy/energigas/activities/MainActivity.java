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
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


import com.orm.SugarContext;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.entities.Agent;
import energigas.apps.systemstrategy.energigas.entities.Establecimiento;
import energigas.apps.systemstrategy.energigas.entities.PlanDistribucion;
import energigas.apps.systemstrategy.energigas.fragments.AccountDialog;
import energigas.apps.systemstrategy.energigas.fragments.EstablecimientoFragment;
import energigas.apps.systemstrategy.energigas.fragments.PlanFragment;
import energigas.apps.systemstrategy.energigas.utils.Utils;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener,
        ViewPager.OnPageChangeListener,
        EstablecimientoFragment.OnEstablecimientoClickListener{
        //OrdersFragment.OnOrdersClickListener

    private static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.drawer)
    DrawerLayout drawer;
    Agent agent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        SugarContext.init(this);
        hideFloatingButton();
        initViews();
    }


    @Override
    protected void onDestroy() {
        SugarContext.terminate();
        super.onDestroy();
    }

    private void hideFloatingButton(){
        List<PlanDistribucion> planDistribucion = PlanDistribucion.find(PlanDistribucion.class," fecha_Inicio=? ",new String[]{Utils.getDatePhone()});
        if (planDistribucion.size()>0){
            fab.hide();
        }
    }

    private void showDialogAccount() {

        new AccountDialog()
                .setFloating(fab)
                .show(getSupportFragmentManager(),null);
    }

    private void initViews() {
        agent = Agent.getAgent();
        // Adding Toolbar to Main screen
        setSupportActionBar(toolbar);
        // Setting ViewPager for each Tabs
        setupViewPager(viewPager);
        // Set Tabs inside Toolbar
        tabs.setupWithViewPager(viewPager);
        setupTabIcons(tabs);

        // Adding menu icon to Toolbar
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }

        //
        viewPager.addOnPageChangeListener(this);

        Log.d(TAG, "navigationView.getHeaderCount(): " + navigationView.getHeaderCount());
        Log.d(TAG, "navigationView.getMenu().size(): " + navigationView.getMenu().size());
        for (int i = 0; i < navigationView.getMenu().size(); i++) {
            Log.d(TAG, "navigationView.getMenu().getItem(" + i + "): " + navigationView.getMenu().getItem(i));
        }
        // Set behavior of Navigation drawer
        navigationView.setNavigationItemSelectedListener(this);
        // Adding Floating Action Button to bottom right of main view
        fab.setOnClickListener(this);
    }

    private void setupTabIcons(TabLayout tabLayout) {
        TabLayout.Tab tabStations = tabLayout.getTabAt(0);
        //TabLayout.Tab tabOrders = tabLayout.getTabAt(1);
        TabLayout.Tab tabPlan = tabLayout.getTabAt(1);

        if (tabStations != null) {
            tabStations.setIcon(R.drawable.ic_gas_station);
        }
        /*
        if (tabOrders != null) {
            tabOrders.setIcon(R.drawable.ic_gas_filter);
        }
        */
        if (tabPlan != null) {
            tabPlan.setIcon(R.drawable.ic_calendar);
        }

    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    // Add Fragments to Tabs
    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getSupportFragmentManager());
      adapter.addFragment(new EstablecimientoFragment(), getString(R.string.estb_title_name));
     //  adapter.addFragment(new PedidoFragment(), getString(R.string.order_title_name));
        adapter.addFragment(new PlanFragment(), getString(R.string.plan_title_name));
        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        if (id == R.id.action_settings) {
            return true;
        } else if (id == android.R.id.home) {
            drawer.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        // Set item in checked state
        item.setChecked(true);
        // TODO: handle navigation
        // Closing drawer on item click
        drawer.closeDrawers();

        // Handle navigation view item clicks here.
        switch (item.getItemId()) {
            case R.id.nav_station:
                setCurrentItem(0);
                break;
            /*
            case R.id.nav_orders:
                setCurrentItem(1);
                break;*/
            case R.id.nav_plan:
                setCurrentItem(1);
                break;
            case R.id.nav_expenses:
                startActivity(new Intent(this, CajaGastoActivity.class));
                break;
            case R.id.nav_collect:
                startActivity(new Intent(this, CargarInventarioActivity.class));
                //showToast(item);
                break;
            case R.id.nav_summary:
                startActivity(new Intent(this, CuentaResumenActivity.class));
                break;
            case R.id.nav_close_account:
                showToast(item);
                closeAccount();
                //startActivity(new Intent(this, LoginActivity.class));
                break;
            case R.id.nav_close_session:
                Toast.makeText(MainActivity.this, R.string.action_close_session, Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                break;
            case R.id.nav_settings:
                startActivity(new Intent(this, SettingsActivity.class));
                break;
        }
        return true;
    }
    private void closeAccount() {
/*
        View viewDialog = getLayoutInflater().inflate(R.layout.layout_dialog_close_account, null);

        final ContentLoadingProgressBar loadingProgressBar = (ContentLoadingProgressBar) viewDialog.findViewById(R.id.loanding);


        final ViewGroup viewGroupInfo = (ViewGroup) viewDialog.findViewById(R.id.viewinfo);
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        // builder.setTitle(getString(R.string.main_dialog_title));
        builder.setView(viewDialog);
        builder.setCancelable(false);
        final Button buttonAcceptar = (Button) viewDialog.findViewById(R.id.btn_ok);
        final Button buttonCancel = (Button) viewDialog.findViewById(R.id.btn_cancel);

        final AlertDialog dialog = builder.create();
        buttonAcceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTitle(agent.getmRuta());
                TextView nameAgent = (TextView) navigationView.findViewById(R.id.nvtxtagente);
                TextView rutaAgent = (TextView) navigationView.findViewById(R.id.nvtxtruta);
                nameAgent.setText(agent.getmName());
                rutaAgent.setText(agent.getmRuta());
                viewGroupInfo.setVisibility(View.GONE);
                loadingProgressBar.setVisibility(View.VISIBLE);
                buttonAcceptar.setVisibility(View.GONE);
                buttonCancel.setVisibility(View.GONE);
                new AsyntaskOpenAccount(MainActivity.this, fab, dialog).execute();
            }
        });
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
        dialog.show();*/
    }

    private void setCurrentItem(int position) {
        if (viewPager.getCurrentItem() != position) {
            viewPager.setCurrentItem(position);
        }
    }

    private void showToast(MenuItem item) {
        Snackbar.make(fab, item.getTitle(),
                Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab:
                showDialogAccount();
                break;
        }
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    // This method will be invoked when a new page becomes selected.
    @Override
    public void onPageSelected(int position) {
        //Snackbar.make(fab, "Page Selected position: "+ position, Snackbar.LENGTH_LONG).show();
        navigationView.getMenu().getItem(position).setChecked(true);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onEstablecimientoClickListener(Establecimiento establecimiento, View view) {
        Snackbar.make(fab, establecimiento.getEstVDescripcion(), Snackbar.LENGTH_LONG).show();
        startActivity(new Intent(MainActivity.this, MainStationActivity.class));
    }

    /*
    @Override
    public void onPedidoClickListener(Pedido order, View view) {
        //Snackbar.make(fab, order.getProductsName(), Snackbar.LENGTH_LONG).show();
        startActivity(new Intent(this, OrderActivity.class));
    }
    */

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
