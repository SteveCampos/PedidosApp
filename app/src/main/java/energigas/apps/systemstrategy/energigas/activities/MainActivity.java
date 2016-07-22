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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.entities.Order;
import energigas.apps.systemstrategy.energigas.entities.Station;
import energigas.apps.systemstrategy.energigas.fragments.OrdersFragment;
import energigas.apps.systemstrategy.energigas.fragments.StationFragment;
import energigas.apps.systemstrategy.energigas.utils.Utils;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener, ViewPager.OnPageChangeListener, StationFragment.OnStationClickListener, OrdersFragment.OnOrdersClickListener {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        for (int i= 0; i< navigationView.getMenu().size(); i++){
            Log.d(TAG, "navigationView.getMenu().getItem("+i+"): " + navigationView.getMenu().getItem(i));
        }
        // Set behavior of Navigation drawer
        navigationView.setNavigationItemSelectedListener(this);
        // Adding Floating Action Button to bottom right of main view
        fab.setOnClickListener(this);
    }

    private void setupTabIcons(TabLayout tabLayout) {
        TabLayout.Tab tabStations = tabLayout.getTabAt(0);
        TabLayout.Tab tabOrders = tabLayout.getTabAt(1);
        TabLayout.Tab tabPlan = tabLayout.getTabAt(2);

        if (tabStations != null) {
            tabStations.setIcon(R.drawable.ic_gas_station);
        }
        if (tabOrders != null) {
            tabOrders.setIcon(R.drawable.ic_gas_filter);
        }
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
        adapter.addFragment(new StationFragment(), getString(R.string.estb_title_name));
        adapter.addFragment(new OrdersFragment(), getString(R.string.order_title_name));
        adapter.addFragment(new StationFragment(), getString(R.string.plan_title_name));
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
            case R.id.nav_orders:
                setCurrentItem(1);
                break;
            case R.id.nav_plan:
                setCurrentItem(2);
                break;
            case R.id.nav_expenses:
                showToast(item);
                break;
            case R.id.nav_collect:
                showToast(item);
                break;
            case R.id.nav_summary:
                startActivity(new Intent(this, AccountSummary.class));
                break;
            case R.id.nav_close_account:
                showToast(item);
                break;
        }
        return true;
    }

    private void setCurrentItem(int position){
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
                Snackbar.make(view, "Current Item Page: " + viewPager.getCurrentItem(),
                        Snackbar.LENGTH_LONG).show();
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
    public void onStationClickListener(Station station, View view) {
        Snackbar.make(fab, station.getEstVName(), Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onOrdersClickListener(Order order, View view) {
        //Snackbar.make(fab, order.getProductsName(), Snackbar.LENGTH_LONG).show();
        startActivity(new Intent(this, OrderActivity.class));
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
