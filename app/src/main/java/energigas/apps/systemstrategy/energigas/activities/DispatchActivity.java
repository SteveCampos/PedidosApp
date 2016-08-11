package energigas.apps.systemstrategy.energigas.activities;

import android.content.Intent;
import android.net.Uri;
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
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.fragments.DispatchFragment;

public class DispatchActivity extends AppCompatActivity implements DispatchFragment.OnFragmentInteractionListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.textview_dispatch_title)
    TextView textViewTank;
    @BindView(R.id.textview_dispatch_product)
    TextView textViewProduct;
    @BindView(R.id.textview_dispatch_station)
    TextView textviewStation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispatch);
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

        setTexts();

/*
        String textProductsCount = getString(R.string.wizard_text_products_count);
        int default_count_products = Integer.parseInt(getString(R.string.default_values_count_products));
        int default_count_tanks = Integer.parseInt(getString(R.string.default_values_count_tanks));

        String textCountFormatted = String.format(textProductsCount, default_count_products, default_count_tanks);
        textViewProductsCount.setText(textCountFormatted);


        String textWizardWelcome = getString(R.string.wizard_title_name);
        String default_name = getString(R.string.default_values_name);

        String textWelcomeFormatted = String.format(textWizardWelcome, default_name);
        textViewWizardWelcome.setText(textWelcomeFormatted);*/


        // Adding menu icon to Toolbar
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            setTitle("");
            supportActionBar.setDisplayHomeAsUpEnabled(true);
            //supportActionBar.setTitle(R.string.wizard_title_activity);
        }
    }

    private void setTexts() {
        /*textViewTank.setText("Tanque T100");
        textViewProduct.setText("GLP");
        textviewStation.setText("Est. La Molina");*/
    }

    @OnClick(R.id.fab)
    void dispatch(){
        finish();
        startActivity(new Intent(DispatchActivity.this, PrintDispatch.class));
    }


    // Add Fragments to Tabs
    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getSupportFragmentManager());
        adapter.addFragment(new DispatchFragment(), getString(R.string.dispatch_name));
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }


    private class Adapter extends FragmentPagerAdapter {
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
