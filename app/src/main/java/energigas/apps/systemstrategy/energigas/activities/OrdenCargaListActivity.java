package energigas.apps.systemstrategy.energigas.activities;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.adapters.OrdenCargaAdapter;
import energigas.apps.systemstrategy.energigas.entities.Concepto;
import energigas.apps.systemstrategy.energigas.entities.OrdenCargo;
import energigas.apps.systemstrategy.energigas.entities.Persona;
import energigas.apps.systemstrategy.energigas.entities.Usuario;
import energigas.apps.systemstrategy.energigas.entities.Vehiculo;
import energigas.apps.systemstrategy.energigas.holders.OrdenCargoHolder;
import energigas.apps.systemstrategy.energigas.interfaces.OrdenCargoListener;
import energigas.apps.systemstrategy.energigas.utils.Constants;
import energigas.apps.systemstrategy.energigas.utils.Session;
import energigas.apps.systemstrategy.energigas.utils.Utils;

public class OrdenCargaListActivity extends AppCompatActivity implements OrdenCargoListener {

    private static final String TAG = "OrdenCargaListActivity" ;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.TView_Agente)
    TextView textViewAgente;
    @BindView(R.id.TView_placa)
    TextView textViewPlaca;
    OrdenCargaAdapter ordenCargaAdapter;
    private Resources resources;

    private Persona mAgente;
    private Usuario mUsuario;
    private Vehiculo mVehiculo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling_orden_carga);
        mAgente = Persona.findWithQuery(Persona.class, "SELECT P.* FROM PERSONA P, USUARIO U WHERE P.PER_I_PERSONA_ID = U.USU_I_PERSONA_ID AND U.USU_I_USUARIO_ID = " + Session.getSession(this).getUsuIUsuarioId() + " ;", null).get(Constants.CURRENT);
        mUsuario = Usuario.getUsuario(Session.getSession(this).getUsuIUsuarioId() + "");
        mVehiculo = Vehiculo.getVehiculo(mUsuario.getUsuIUsuarioId() + "");
        resources = getResources();
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), OrdenCargaActivity.class);
                startActivity(intent);
            }
        });

        textViewAgente.setText(mAgente.getPerVNombres()+" "+mAgente.getPerVApellidoPaterno()+" "+mAgente.getPerVApellidoMaterno());
        textViewPlaca.setText(Utils.getDateDescription(Utils.getDatePhone()));
        initRecycler();
        //toolBarImageBack();
        collapSingTitle();
    }

    private void toolBarImageBack(){

        if (getSupportActionBar() != null) //
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            //getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
    }


    private void initRecycler() {
        ordenCargaAdapter = new OrdenCargaAdapter(this, OrdenCargo.findWithQuery(OrdenCargo.class, "select * from orden_Cargo ORDER BY id DESC"), this);
        recycler.setAdapter(ordenCargaAdapter);
        recycler.setLayoutManager(new LinearLayoutManager(this));
    }


    @Override
    public void onOrdenCargoClickListener(OrdenCargo ordenCargo) {
        Intent intent = new Intent(this, PrintOrdenCarga.class);
        intent.putExtra("ORDECARGAID", ordenCargo.getOrdeCargaId()+"");
        startActivity(intent);
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
        super.onBackPressed();
    }

    @Override
    protected void onResume() {
        Log.d(TAG,"onResume");
        initRecycler();
        super.onResume();
    }

    private void collapSingTitle(){
        final CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.app_bar);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                    toolBarImageBack();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbarLayout.setTitle("Orden Carga");
                    toolBarImageBack();
                    isShow = true;
                } else if(isShow) {
                    collapsingToolbarLayout.setTitle(" ");//carefull there should a space between double quote otherwise it wont work
                    toolBarImageBack();
                    isShow = false;
                }
            }
        });
    }


}
