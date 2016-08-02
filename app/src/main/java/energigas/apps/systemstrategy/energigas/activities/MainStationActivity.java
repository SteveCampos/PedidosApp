package energigas.apps.systemstrategy.energigas.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.adapters.CustomTabsAdapter;
import energigas.apps.systemstrategy.energigas.entities.OrderDispatch;
import energigas.apps.systemstrategy.energigas.entities.OrderProduct;
import energigas.apps.systemstrategy.energigas.fragments.ChargesFragment;
import energigas.apps.systemstrategy.energigas.fragments.DispatchFragment;
import energigas.apps.systemstrategy.energigas.fragments.ExpensesFragment;
import energigas.apps.systemstrategy.energigas.fragments.FragmentStationInformation;
import energigas.apps.systemstrategy.energigas.fragments.OrderedProductFragment;

public class MainStationActivity extends AppCompatActivity implements OrderedProductFragment.OnOrderedProductClickListener, OrderedProductFragment.OnDispatchClickListener{


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.tablayout)
    TabLayout tabLayout;
    @BindView(R.id.fab)
    FloatingActionButton fab;

    private ActionMode actionMode;

    private CustomTabsAdapter tabsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_station);
        ButterKnife.bind(this);
        initViews();
    }

    private void initViews() {
        setToolbar();
        setTabsAdapterFragment();
        viewpager.setCurrentItem(1);
    }

    private void setToolbar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) //
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }
    private void setTabsAdapterFragment (){
        tabsAdapter = new CustomTabsAdapter(getSupportFragmentManager());
        tabsAdapter.addFragment(new FragmentStationInformation(), getString(R.string.title_activity_main_station));
        tabsAdapter.addFragment(new OrderedProductFragment(), getString(R.string.title_activity_dispatch));
        tabsAdapter.addFragment(new ChargesFragment(), getString(R.string.activity_charges_account));
        viewpager.setAdapter(tabsAdapter);
        tabLayout.setupWithViewPager(viewpager);
    }

    @Override
    public void onDispatchClickListener(OrderDispatch orderDispatch, View view) {
        Snackbar.make(fab, orderDispatch.getTitleTank(), Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onOrderedProductClickListener(OrderProduct orderProduct, View view, int typeClick) {
        Snackbar.make(fab, orderProduct.getProductOrderedTitle() + typeClick, Snackbar.LENGTH_LONG).show();
        startActionMode(mActionModeCallback);
    }
    private ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {


        // Called when the action mode is created; startActionMode() was called
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            // Inflate a menu resource providing context menu items
            MenuInflater inflater = mode.getMenuInflater();
            inflater.inflate(R.menu.dispatc_menu_print, menu);
            return true;
        }

        // Called each time the action mode is shown. Always called after onCreateActionMode, but
        // may be called multiple times if the mode is invalidated.
        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false; // Return false if nothing is done
        }

        // Called when the user selects a contextual menu item
        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()) {
                case R.id.print:
                    print();
                    return true;
                default:
                    return false;
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {

        }
    };

    private void print() {
        Snackbar.make(fab, R.string.action_print, Snackbar.LENGTH_LONG).show();
    }


}
