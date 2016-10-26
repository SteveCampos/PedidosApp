package energigas.apps.systemstrategy.energigas.activities;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.orm.SugarTransactionHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.entities.Almacen;
import energigas.apps.systemstrategy.energigas.entities.Establecimiento;
import energigas.apps.systemstrategy.energigas.fragments.DialogGeneral;
import energigas.apps.systemstrategy.energigas.fragments.DispatchFragment;
import energigas.apps.systemstrategy.energigas.interfaces.DialogGeneralListener;
import energigas.apps.systemstrategy.energigas.utils.Constants;
import energigas.apps.systemstrategy.energigas.utils.Session;

public class DispatchActivity extends AppCompatActivity {

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
    private Establecimiento establecimiento;
    private Almacen almacen;
    private onNextActivity mListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispatch);
        establecimiento = Establecimiento.find(Establecimiento.class, " est_I_Establecimiento_Id = ?  ", new String[]{Session.getSessionEstablecimiento(this).getEstIEstablecimientoId() + ""}).get(0);
        almacen = Almacen.find(Almacen.class, " alm_Id = ? ", new String[]{Session.getAlmacen(this).getAlmId() + ""}).get(Constants.CURRENT);
        ButterKnife.bind(this);
        initViews();
    }


    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        if (fragment instanceof onNextActivity) {
            mListener = (onNextActivity) fragment;
        } else {
            throw new RuntimeException(fragment.toString()
                    + " must implement onNextActivity");
        }
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
        textViewTank.setText(almacen.getNombre());
        textViewProduct.setText(almacen.getNombre());
        textviewStation.setText(establecimiento.getEstVDescripcion());
    }

    @OnClick(R.id.fab)
    void dispatch() {


    }


    // Add Fragments to Tabs
    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getSupportFragmentManager());
        adapter.addFragment(new DispatchFragment(), getString(R.string.dispatch_name));
        viewPager.setAdapter(adapter);
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


    public interface onNextActivity {
        void onNextListener();
    }


}
