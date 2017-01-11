package energigas.apps.systemstrategy.energigas.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.adapters.CustomTabsAdapter;
import energigas.apps.systemstrategy.energigas.asyntask.ExportTask;
import energigas.apps.systemstrategy.energigas.entities.AccessFragment;
import energigas.apps.systemstrategy.energigas.entities.CajaLiquidacionDetalle;
import energigas.apps.systemstrategy.energigas.entities.Cliente;
import energigas.apps.systemstrategy.energigas.entities.ComprobanteVenta;
import energigas.apps.systemstrategy.energigas.entities.Establecimiento;
import energigas.apps.systemstrategy.energigas.entities.Pedido;
import energigas.apps.systemstrategy.energigas.entities.OrderDispatch;
import energigas.apps.systemstrategy.energigas.entities.OrderProduct;
import energigas.apps.systemstrategy.energigas.entities.Usuario;
import energigas.apps.systemstrategy.energigas.fragments.AgregarDespachoFragment;
import energigas.apps.systemstrategy.energigas.fragments.CajaPagoFragment;
import energigas.apps.systemstrategy.energigas.fragments.ComprobanteVentaFragment;
import energigas.apps.systemstrategy.energigas.fragments.EstablecimientoFragment;
import energigas.apps.systemstrategy.energigas.fragments.FragmentStationInformation;
import energigas.apps.systemstrategy.energigas.fragments.OrderedProductFragment;
import energigas.apps.systemstrategy.energigas.fragments.PlanFragment;
import energigas.apps.systemstrategy.energigas.fragments.StationDispatchsFragment;
import energigas.apps.systemstrategy.energigas.fragments.StationOrderFragment;
import energigas.apps.systemstrategy.energigas.fragments.StationProductsFragment;
import energigas.apps.systemstrategy.energigas.interfaces.ExportObjectsListener;
import energigas.apps.systemstrategy.energigas.interfaces.IntentListenerAccess;
import energigas.apps.systemstrategy.energigas.interfaces.OnComprobanteVentaClickListener;
import energigas.apps.systemstrategy.energigas.services.ServiceSync;
import energigas.apps.systemstrategy.energigas.utils.AccessPrivilegesManager;
import energigas.apps.systemstrategy.energigas.utils.Constants;
import energigas.apps.systemstrategy.energigas.utils.Session;

public class MainStationActivity extends AppCompatActivity
        implements
        OrderedProductFragment.OnOrderedProductClickListener,
        OrderedProductFragment.OnDispatchClickListener,
        StationOrderFragment.OnStationOrderClickListener, TabLayout.OnTabSelectedListener, ExportObjectsListener, IntentListenerAccess {

    private static final String TAG = "MainStationActivity";
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.tablayout)
    TabLayout tabLayout;
    @BindView(R.id.fab)
    FloatingActionButton fab;

    @BindView(R.id.tv_description)
    TextView textViewDescripcion;

    private ActionMode actionMode;


    private Establecimiento establecimiento;

    private CajaLiquidacionDetalle liquidacionDetalle;
    private Usuario usuario;
    private HashMap<String, Boolean> booleanHashMap;
    private List<AccessFragment> accessFragmentList;
    private Cliente cliente;

    @BindView(R.id.textViewTel)
    TextView textViewTelefono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_station);

        establecimiento = Establecimiento.find(Establecimiento.class, " est_I_Establecimiento_Id = ? ", new String[]{Session.getSessionEstablecimiento(this).getEstIEstablecimientoId() + ""}).get(Constants.CURRENT);
        liquidacionDetalle = CajaLiquidacionDetalle.getLiquidacionDetalleByEstablec(establecimiento.getEstIEstablecimientoId() + "");
        ButterKnife.bind(this);
        usuario = Session.getSession(this);
        new AccessPrivilegesManager(getClass())
                .setViews(fab)
                .setListenerIntent(this)
                .setPrivilegesIsEnable(usuario.getUsuIUsuarioId() + "")
                .setClassIntent(StationOrderActivity.class)
                .isIntentEnable()
                .setFragment(
                        new AccessFragment(getString(R.string.title_activity_main_station),
                                new FragmentStationInformation(), 0, 1),
                        new AccessFragment(getString(R.string.order_title_name),
                                new StationOrderFragment(), 0, 2),
                        new AccessFragment(getString(R.string.title_activity_dispatch),
                                new StationDispatchsFragment(), 0, 3),
                        new AccessFragment(getString(R.string.activity_charges_account),
                                new CajaPagoFragment(), 0, 4),
                        new AccessFragment(getString(R.string.activity_charges_fac),
                                new ComprobanteVentaFragment(), 0, 5))
                .isFragmentEnable();
        cliente = Cliente.getCliente(establecimiento.getEstIClienteId() + "");
        setTipoCliente();

    }





    private void setTipoCliente() {

        if (cliente.getCliITipoClienteId() == Constants.ESTABLECIMIENTO_EXTERNO) {
            setTitle("Cliente");
        } else {
            setTitle("Estación");
        }

        textViewTelefono.setText(cliente.getCliVTelefono());
        textViewTelefono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!cliente.getCliVTelefono().equals("SN")) {
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + cliente.getCliVTelefono()));
                    startActivity(intent);
                }


            }
        });

    }


    @Override
    public void onIntentListenerAcces(HashMap<String, Boolean> booleanHashMap) {
        this.booleanHashMap = booleanHashMap;
    }

    @Override
    public void onFragmentAccess(List<AccessFragment> accessFragmentList) {
        this.accessFragmentList = accessFragmentList;
        initViews();
    }


    @OnClick(R.id.fab)
    public void agregarDespacho(View view) {
        new AgregarDespachoFragment().show(getSupportFragmentManager(), "");
    }

    private void initViews() {
        setToolbar();
        setTabsAdapterFragment();
        viewpager.setCurrentItem(1);
        textViewDescripcion.setText(establecimiento.getEstVDescripcion());
    }

    private void setToolbar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) //
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

    private void setTabsAdapterFragment() {

        MainStationActivity.Adapter adapter = new MainStationActivity.Adapter(getSupportFragmentManager());


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
        tabLayout.addOnTabSelectedListener(this);
    }

    @Override
    public void onDispatchClickListener(OrderDispatch orderDispatch, View view) {
        Snackbar.make(fab, orderDispatch.getTitleTank(), Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onOrderedProductClickListener(OrderProduct orderProduct, View view, int typeClick) {
        Snackbar.make(fab, orderProduct.getProductOrderedTitle() + typeClick, Snackbar.LENGTH_LONG).show();
        startSupportActionMode(mActionModeCallback);
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
            //Sometimes the meu will not be visible so for that we need to set their visibility manually in this method
            //So here show action menu according to SDK Levels
            if (Build.VERSION.SDK_INT < 11) {
                MenuItemCompat.setShowAsAction(menu.findItem(R.id.print), MenuItemCompat.SHOW_AS_ACTION_NEVER);
            } else {
                menu.findItem(R.id.print).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
            }

            return true;
        }

        // Called when the user selects a contextual menu item
        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()) {
                case R.id.print:
                    print();
                    mode.finish();
                    return true;
                default:
                    return false;
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            mode.finish();
        }

    };

    private void print() {
        Snackbar.make(fab, R.string.action_print, Snackbar.LENGTH_LONG).show();
    }


    @Override
    public void onStationOrderClickListener(Pedido pedido) {
        Session.savePedido(getApplicationContext(), pedido);
        startActivity(new Intent(MainStationActivity.this, StationOrderActivity.class));
        this.finish();
    }

    public void showMap(Uri geoLocation) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(geoLocation);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }


    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        Log.d(TAG, tab.getPosition() + "");

        if (tab.getPosition() == 2) {
            fab.setVisibility(View.VISIBLE);
            fab.setVisibility(View.GONE);
        } else {
            fab.setVisibility(View.GONE);
        }
    }


    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    public void onLoadSuccess(String message) {

    }

    @Override
    public void onLoadError(String message) {

    }

    @Override
    public void onLoadErrorProcedure(String message) {

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
