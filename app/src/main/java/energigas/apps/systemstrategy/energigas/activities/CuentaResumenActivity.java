package energigas.apps.systemstrategy.energigas.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.adapters.CustomTabsAdapter;
import energigas.apps.systemstrategy.energigas.entities.CajaLiquidacion;
import energigas.apps.systemstrategy.energigas.entities.Usuario;
import energigas.apps.systemstrategy.energigas.fragments.InventarioFragment;
import energigas.apps.systemstrategy.energigas.fragments.ResumentFragment;
import energigas.apps.systemstrategy.energigas.utils.Session;
import energigas.apps.systemstrategy.energigas.utils.Utils;

public class CuentaResumenActivity extends AppCompatActivity {
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


    private CustomTabsAdapter tabsAdapter;
    private Usuario usuario;
    private CajaLiquidacion cajaLiquidacion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_summary);
        ButterKnife.bind(this);
        usuario = Usuario.getUsuario(Session.getSession(this).getUsuIUsuarioId() + "");
        cajaLiquidacion = CajaLiquidacion.getCajaLiquidacion(Session.getCajaLiquidacion(this).getLiqId() + "");
        setTabsAdapterFragment();
        setToolbar();
        setupCollapsingToolbar();
        setCaberceraText();
        imprimirResumen();
    }

    private void imprimirResumen() {
        actionButtonResumen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ResumenPrintActivity.class));
            }
        });
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

}
