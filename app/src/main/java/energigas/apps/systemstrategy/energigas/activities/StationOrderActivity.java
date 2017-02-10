package energigas.apps.systemstrategy.energigas.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
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
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.adapters.CustomTabsAdapter;
import energigas.apps.systemstrategy.energigas.entities.AccessFragment;
import energigas.apps.systemstrategy.energigas.entities.CajaLiquidacionDetalle;
import energigas.apps.systemstrategy.energigas.entities.Cliente;
import energigas.apps.systemstrategy.energigas.entities.Establecimiento;
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
import energigas.apps.systemstrategy.energigas.utils.Constants;
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


    @BindView(R.id.textHorasPendientes)
    TextView textViewHorasPendientes;
    @BindView(R.id.textClienteEstacion)
    TextView textViewClienteEstacion;
    @BindView(R.id.textNombreClienteEstacion)
    TextView textViewNombreClienteEstacion;
    @BindView(R.id.tv_time)
    TextView textViewVentanaTiempo;
    @BindView(R.id.textHoraProgramada)
    TextView textViewHoraProgramada;

    private Cliente cliente;
    private Establecimiento establecimiento;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //
        setContentView(R.layout.activity_station_order);
        long idPedido;
        long idEstablecimiento;
        if (getIntent().getExtras() == null) {
            idPedido = Session.getPedido(this).getPeId();
            idEstablecimiento = Session.getSessionEstablecimiento(this).getEstIEstablecimientoId();

        } else {
            idPedido = getIntent().getExtras().getLong("PEDIDO");
            idEstablecimiento = getIntent().getExtras().getLong("ESTABLECIMIENTO");

        }


        pedido = Pedido.find(Pedido.class, " pe_Id = ?  ", new String[]{idPedido + ""}).get(0);
        usuario = Session.getSession(this);
        establecimiento = Establecimiento.getEstablecimientoById(idEstablecimiento + "");

        Session.savePedido(this, pedido);
        Session.saveEstablecimiento(this, establecimiento);


        cliente = Cliente.getCliente(establecimiento.getEstIClienteId() + "");

        ButterKnife.bind(this);
        setTimeText(Utils.getHoursToCheck(pedido.getHoraEntrega()));
        new AccessPrivilegesManager(getClass())
                .setViews(fab)
                .setListenerIntent(this)
                .setPrivilegesIsEnable(usuario.getUsuIUsuarioId() + "")
                .setFragment(
                        new AccessFragment("ITEM",
                                new OrderDetailFragment(), R.drawable.ic_tank, 1),
                        new AccessFragment("TANQUES",
                                new AlmacenFragment(), R.drawable.ic_invoice, 2),
                        new AccessFragment("DESPACHOS",
                                new DespachoFragment(), R.drawable.ic_sell_product_fragment, 3))
                .isFragmentEnable();

        ScheduledExecutorService exec = Executors.newScheduledThreadPool(1);
        exec.scheduleAtFixedRate(drawRunnable, 0, 1, TimeUnit.MINUTES);
        CajaLiquidacionDetalle cajaLiquidacionDetalle = CajaLiquidacionDetalle.getLiquidacionDetalleByEstablecAndEstado(establecimiento.getEstIEstablecimientoId() + "");
        if (cajaLiquidacionDetalle.getEstadoId() == Constants.ESTADO_ESTABLECIMIENTO_ATENDIDO) {
            textViewHorasPendientes.setVisibility(View.GONE);
        }
    }


    private void initHead() {
        setToolbar();
        if (cliente.getCliITipoClienteId() == Constants.ESTABLECIMIENTO_EXTERNO) {
            textViewClienteEstacion.setText("Cliente");
        } else {
            textViewClienteEstacion.setText("Estación");
        }

        textViewNombreClienteEstacion.setText(establecimiento.getEstVDescripcion());
        textViewFechaDescripcion.setText(Utils.getDateDescription(pedido.getFechaEntregaProgramada()));
        textViewVentanaTiempo.setText("Ventana de Tiempo : " + pedido.getHoraInicio() + " - " + pedido.getHoraFin());
        textViewHoraProgramada.setText("Hora Programada: " + pedido.getHoraEntrega() + " ");


    }

    private void setTimeText(String timeText) {


        textViewHorasPendientes.setText("" + timeText + " Hrs.");
    }

    Runnable drawRunnable = new Runnable() {
        public void run() {
            setTimeText(Utils.getHoursToCheck(pedido.getHoraEntrega()));
        }
    };

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            finish();
        }

        return super.onOptionsItemSelected(item);
    }


    private void initViews() {
        initHead();
        setTabsAdapterFragment();
        viewpager.setCurrentItem(0);
    }


    private void setToolbar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) //
        {
            setTitle("Pedido Nro" + pedido.getNumero());
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


        if (tabLayout.getTabAt(0).getPosition() == 0) {
            tabLayout.getTabAt(0).setIcon(accessFragmentList.get(0).getDrawable());
        }
        if (tabLayout.getTabAt(1).getPosition() == 1) {
            tabLayout.getTabAt(1).setIcon(accessFragmentList.get(1).getDrawable());
        }
        if (tabLayout.getTabAt(2).getPosition() == 2) {
            tabLayout.getTabAt(2).setIcon(accessFragmentList.get(2).getDrawable());
        }


    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        startActivity(new Intent(getApplicationContext(), MainStationActivity.class));
        this.finish();
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
