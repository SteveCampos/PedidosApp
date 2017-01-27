package energigas.apps.systemstrategy.energigas.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.common.collect.ComparisonChain;

import butterknife.BindView;
import butterknife.ButterKnife;
import energigas.apps.systemstrategy.energigas.LocationVehiculeListener;
import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.adapters.CustomTabsAdapter;
import energigas.apps.systemstrategy.energigas.asyntask.AsynCerrarCaja;
import energigas.apps.systemstrategy.energigas.entities.CajaLiquidacion;
import energigas.apps.systemstrategy.energigas.entities.Usuario;
import energigas.apps.systemstrategy.energigas.fragments.DialogGeneral;
import energigas.apps.systemstrategy.energigas.fragments.InventarioFragment;
import energigas.apps.systemstrategy.energigas.fragments.ResumentFragment;
import energigas.apps.systemstrategy.energigas.interfaces.DialogGeneralListener;
import energigas.apps.systemstrategy.energigas.interfaces.OnAsyntaskListener;
import energigas.apps.systemstrategy.energigas.interfaces.OnLocationListener;
import energigas.apps.systemstrategy.energigas.utils.Constants;
import energigas.apps.systemstrategy.energigas.utils.Session;
import energigas.apps.systemstrategy.energigas.utils.Utils;

public class CuentaResumenActivity extends AppCompatActivity implements OnAsyntaskListener, OnLocationListener {
    private static final String TAG = CuentaResumenActivity.class.getSimpleName();

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.viewPagerSummary)
    ViewPager mainViewPager;
    @BindView(R.id.tablayout)
    TabLayout tabLayout;
    @BindView(R.id.textAgente)
    TextView textViewAgente;
    @BindView(R.id.textDate)
    TextView textViewFecha;
    @BindView(R.id.textAccountNro)
    TextView textViewNro;

    @BindView(R.id.fabImprimirResumen)
    FloatingActionButton actionButtonResumen;
    private ProgressDialog progressDialog;


    private CustomTabsAdapter tabsAdapter;
    private Usuario usuario;
    private CajaLiquidacion cajaLiquidacion;
    private LocationVehiculeListener locationVehiculeListener;
    private Location location;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_summary);
        ButterKnife.bind(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(true);
        progressDialog.setTitle("Por favor espere");

        usuario = Usuario.getUsuario(Session.getSession(this).getUsuIUsuarioId() + "");
        cajaLiquidacion = CajaLiquidacion.getCajaLiquidacion(Session.getCajaLiquidacion(this).getLiqId() + "");
        setTabsAdapterFragment();
        setToolbar();
        setupCollapsingToolbar();
        setCaberceraText();
        imprimirResumen();
        locationVehiculeListener = new LocationVehiculeListener(this, Constants.MIN_TIME_BW_UPDATES, Constants.LONG_DISTANCE_0);

    }

    private void imprimirResumen() {
        actionButtonResumen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (location != null) {
                    initDialogCerrarLiquidacion();
                } else {
                    Toast.makeText(CuentaResumenActivity.this, "Obteniendo coordenadas GPS", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void initDialogCerrarLiquidacion() {
        new DialogGeneral(this).setCancelable(false).setTextButtons("SI", "NO").setMessages("ATENCION...!!!", "¿ Esta seguro de cerrar caja ?").showDialog(new DialogGeneralListener() {
            @Override
            public void onSavePressed(AlertDialog alertDialog) {
                alertDialog.dismiss();
                progressDialog.show();
                new AsynCerrarCaja(getApplicationContext(), CuentaResumenActivity.this).execute(cajaLiquidacion.getLiqId() + "", location.getLatitude() + "", location.getLongitude() + "");

            }

            @Override
            public void onCancelPressed(AlertDialog alertDialog) {
                alertDialog.dismiss();
            }
        });
    }

    private void initPrintResumen() {
        Toast.makeText(this, "Caja cerrada correctamente", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(), ResumenPrintActivity.class));

        locationVehiculeListener.stopLocationUpdates();

    }


    private void setupCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(
                R.id.collapse_toolbar);
        collapsingToolbar.setTitleEnabled(false);
    }

    private void setCaberceraText() {
        textViewAgente.setText(usuario.getPersona().getPerVNombres() + ", " + usuario.getPersona().getPerVApellidoPaterno());
        textViewFecha.setText(Utils.getDatePhone());
        textViewNro.setText(cajaLiquidacion.getLiqId() + "");
    }


    private void setTabsAdapterFragment() {
        tabsAdapter = new CustomTabsAdapter(getSupportFragmentManager());
        tabsAdapter.addFragment(ResumentFragment.newInstance(), getString(R.string.fragment_summary));
        tabsAdapter.addFragment(InventarioFragment.newIntance(), getString(R.string.fragment_inventory));
        mainViewPager.setAdapter(tabsAdapter);
        tabLayout.setupWithViewPager(mainViewPager);
    }

    private void setToolbar() {
        // Añadir la Toolbar
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) //
        {
            Log.d(TAG, "TOOLBAR DISPONIBLE");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_menu_accountsummary, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        this.finish();
        //super.onBackPressed();
    }

    @Override
    public void onLoadSuccess(String message, CajaLiquidacion cajaLiquidacion) {
        progressDialog.dismiss();
        initPrintResumen();

    }

    @Override
    public void onLoadError(String message) {
        progressDialog.dismiss();

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoadErrorProcedure(String message) {

    }

    @Override
    public void setLatAndLong(Location latAndLong) {
        this.location = latAndLong;
    }

    @Override
    public Context getContextActivity() {
        return getApplicationContext();
    }
}
