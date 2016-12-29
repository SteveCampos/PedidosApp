package energigas.apps.systemstrategy.energigas.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.adapters.CustomTabsAdapter;
import energigas.apps.systemstrategy.energigas.entities.AccessFragment;
import energigas.apps.systemstrategy.energigas.entities.Pedido;
import energigas.apps.systemstrategy.energigas.entities.Usuario;
import energigas.apps.systemstrategy.energigas.fragments.CajaPagoFragment;
import energigas.apps.systemstrategy.energigas.fragments.ComprobanteVentaFragment;
import energigas.apps.systemstrategy.energigas.fragments.DespachoFragment;
import energigas.apps.systemstrategy.energigas.fragments.FragmentStationInformation;
import energigas.apps.systemstrategy.energigas.fragments.OrderDetailFragment;
import energigas.apps.systemstrategy.energigas.fragments.AlmacenFragment;
import energigas.apps.systemstrategy.energigas.fragments.StationDispatchsFragment;
import energigas.apps.systemstrategy.energigas.fragments.StationOrderFragment;
import energigas.apps.systemstrategy.energigas.interfaces.IntentListenerAccess;
import energigas.apps.systemstrategy.energigas.utils.AccessPrivilegesManager;
import energigas.apps.systemstrategy.energigas.utils.Session;
import energigas.apps.systemstrategy.energigas.utils.Utils;

public class StationOrderActivity extends AppCompatActivity implements IntentListenerAccess {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.tablayout)
    TabLayout tabLayout;
    @BindView(R.id.fab)
    FloatingActionButton fab;

    @BindView(R.id.tv_description)
    TextView textViewFechaDescripcion;
    private Usuario usuario;

    private Pedido pedido;
    private List<AccessFragment> accessFragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //
        setContentView(R.layout.activity_station_order);
        pedido = Pedido.find(Pedido.class, " pe_Id = ?  ", new String[]{Session.getPedido(this).getPeId() + ""}).get(0);
        usuario = Session.getSession(this);
        /*
         tabsAdapter.addFragment(new OrderDetailFragment(), "ITEM");
        tabsAdapter.addFragment(new AlmacenFragment(), "TANQUES");
        tabsAdapter.addFragment(new DespachoFragment(), "DESPACHOS");
        */

        ButterKnife.bind(this);

        new AccessPrivilegesManager(getClass())
                .setViews(fab)
                .setListenerIntent(this)
                .setPrivilegesIsEnable(usuario.getUsuIUsuarioId() + "")
                .setFragment(
                        new AccessFragment("ITEM",
                                new OrderDetailFragment(), 0, 1),
                        new AccessFragment("TANQUES",
                                new AlmacenFragment(), 0, 2),
                        new AccessFragment("DESPACHOS",
                                new DespachoFragment(), 0, 3))
                .isFragmentEnable();


    }

    @Override
    public void onIntentListenerAcces(HashMap<String, Boolean> booleanHashMap) {


    }

    @Override
    public void onFragmentAccess(List<AccessFragment> accessFragmentList) {
        this.accessFragmentList = accessFragmentList;
        initViews();
    }

    @Override
    protected void onResume() {
        super.onResume();
        ButterKnife.bind(this);
        initViews();
    }

    private void initViews() {
        setToolbar();
        setTabsAdapterFragment();
        viewpager.setCurrentItem(0);
        textViewFechaDescripcion.setText(Utils.getDateDescription(pedido.getFechaEntregaProgramada()));
    }

    private void setToolbar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) //
        {
            setTitle("Pedido " + pedido.getNumero());
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void setTabsAdapterFragment() {
        Adapter adapter = new Adapter(getSupportFragmentManager());
        tabLayout.setupWithViewPager(viewpager);

        for (int i = 0; i < accessFragmentList.size(); i++) {
            int count = i + 1;
            for (AccessFragment fragment2 : accessFragmentList) {
                if (count == fragment2.getOrden()) {
                    adapter.addFragment(fragment2.getFragment(), fragment2.getId());
                }
            }

        }


        viewpager.setAdapter(adapter);

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
