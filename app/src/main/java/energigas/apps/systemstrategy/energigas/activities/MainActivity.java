package energigas.apps.systemstrategy.energigas.activities;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.entities.AccessFragment;
import energigas.apps.systemstrategy.energigas.entities.Agent;
import energigas.apps.systemstrategy.energigas.entities.CajaLiquidacion;
import energigas.apps.systemstrategy.energigas.entities.DEEntidad;
import energigas.apps.systemstrategy.energigas.entities.DatosEmpresa;
import energigas.apps.systemstrategy.energigas.entities.Establecimiento;
import energigas.apps.systemstrategy.energigas.entities.Usuario;
import energigas.apps.systemstrategy.energigas.entities.Vehiculo;
import energigas.apps.systemstrategy.energigas.fragments.AccountDialog;
import energigas.apps.systemstrategy.energigas.fragments.DialogGeneral;
import energigas.apps.systemstrategy.energigas.fragments.EstablecimientoFragment;
import energigas.apps.systemstrategy.energigas.fragments.PlanFragment;
import energigas.apps.systemstrategy.energigas.interfaces.DialogGeneralListener;
import energigas.apps.systemstrategy.energigas.interfaces.ExportObjectsListener;
import energigas.apps.systemstrategy.energigas.interfaces.IntentListenerAccess;
import energigas.apps.systemstrategy.energigas.utils.AccessPrivilegesManager;
import energigas.apps.systemstrategy.energigas.utils.Constants;
import energigas.apps.systemstrategy.energigas.utils.Session;
import energigas.apps.systemstrategy.energigas.utils.Utils;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener,
        ViewPager.OnPageChangeListener,
        EstablecimientoFragment.OnEstablecimientoClickListener, ExportObjectsListener, IntentListenerAccess, TabLayout.OnTabSelectedListener, AccountDialog.ListenerOpenAccount {
    //OrdersFragment.OnOrdersClickListener

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int RESULT_LOAD_IMG = 5;

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
    Agent agent;

    @BindView(R.id.textNombreAgente)
    TextView textViewNombreAgente;

    @BindView(R.id.textVehiculoPlaca)
    TextView textViewPlaca;

    @BindView(R.id.textFecha)
    TextView textViewFecha;

    @BindView(R.id.layoutProfile)
    LinearLayout linearLayoutProfile;

    @BindView(R.id.layoutBsc)
    LinearLayout linearLayoutBsc;

    @BindView(R.id.imageViewAgente)
    ImageView imageViewAgente;

    @BindView(R.id.imageViewProfile2)
    ImageView imageViewAgente2;


    @BindView(R.id.textBSC)
    TextView textViewBsc;

    @BindView(R.id.textAgente)
    TextView textViewAgente;

    @BindView(R.id.textUnidadTransporte)
    TextView textViewUnidadTransporte;

    private int colorInicial;

    private Usuario usuario;
    private HashMap<String, Boolean> booleanHashMap;
    private List<AccessFragment> accessFragments;
    SimpleDraweeView draweeViewPerfil;

    ImageView imageView;

    AccessPrivilegesManager accessPrivilegesManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        colorInicial = R.color.colorAccent;
        Log.d(TAG, "Usuario: " + Session.getSession(this).getUsuIUsuarioId() + "");
        usuario = Session.getSession(this);
        ButterKnife.bind(this);
        if (!Utils.getGpsEnable(this)) {
            Intent intent = new Intent(this, ModalActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        validaExistencia();


        if (hideFloatingButton()) {

            accessPrivilegesManager = new AccessPrivilegesManager(getClass())
                    .setViews(fab)
                    .setListenerIntent(this)
                    .setPrivilegesIsEnable(usuario.getUsuIUsuarioId() + "")
                    .setClassIntent(MainStationActivity.class)
                    .isIntentEnable()
                    .setFragment(
                            new AccessFragment(getString(R.string.estb_title_name),
                                    new EstablecimientoFragment(), R.drawable.ic_calendar, 1),
                            new AccessFragment(getString(R.string.plan_title_name),
                                    new PlanFragment(), R.drawable.ic_heat, 2))
            ;

            accessPrivilegesManager.isFragmentEnable();


            //new ExportTask(this, this).execute(Constants.TABLA_COMPROBANTE, Constants.S_CREADO);
            //new ExportTask(this, this).execute(Constants.TABLA_GASTO, Constants.S_CREADO);
            // startService(new Intent(MainActivity.this, ServiceSync.class));

        }

        initHeaderViewNavigation();


    }

    private void initHeaderViewNavigation() {

        if (CajaLiquidacion.getCajaLiquidacion(Session.getCajaLiquidacion(this).getLiqId() + "") != null) {
            DEEntidad deEntidad = CajaLiquidacion.getCajaLiquidacion(Session.getCajaLiquidacion(this).getLiqId() + "").getEntidad();
            DatosEmpresa datosEmpresa = new DatosEmpresa(deEntidad);
            View view = navigationView.getHeaderView(0);
            draweeViewPerfil = (SimpleDraweeView) view.findViewById(R.id.imgPerfil);
            imageView = (ImageView) view.findViewById(R.id.imageView2);
            TextView textViewInfoEmpresa = (TextView) view.findViewById(R.id.textInforEmpresa);
            TextView textNombreAgente = (TextView) view.findViewById(R.id.textNombreAgente);
            TextView textCorreoAgente = (TextView) view.findViewById(R.id.textCorreoAgente);

            String informacionEmpre = String.format(getResources().getString(R.string.header_energigas), datosEmpresa.getEntidad().getRazonSocial(), datosEmpresa.getEntidad().getrUC(), datosEmpresa.getEntidad().getDireccionFiscal());
            textViewInfoEmpresa.setText(informacionEmpre);
            textNombreAgente.setText(usuario.getPersona().getPerVNombres() + " " + usuario.getPersona().getPerVApellidoPaterno());
            textCorreoAgente.setText(usuario.getPersona().getPerVEmail());
            draweeViewPerfil.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    loadImagefromGallery(v);
                }
            });

            if (Session.getImageUsuario(this) != null) {
                draweeViewPerfil.setImageURI(Session.getImageUsuario(this));
            }


        }


    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            // When an Image is picked
            if (requestCode == RESULT_LOAD_IMG && resultCode == RESULT_OK
                    && null != data) {
                // Get the Image from data
                Uri selectedImage = data.getData();


                BitmapFactory.Options opts = new BitmapFactory.Options();
                opts.inSampleSize = 4;
                //  Bitmap bitmap = BitmapFactory.decodeFile(selectedImage.getPath(), opts);

                Session.guardarImagenUsuario(selectedImage, getApplicationContext());
                draweeViewPerfil.setImageURI(selectedImage);
                // draweeViewPerfil.setImageBitmap(bitmap);
                // imageView.setImageBitmap(ImageUtils.decodeSampledBitmapFromResource(selectedImage.getPath(), 100, 100));


            } else {
                Toast.makeText(MainActivity.this, "No ha seleccionado niguna imagen",
                        Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Log.d("ERRORIMAGEN", e.getMessage());
            Toast.makeText(MainActivity.this, "Vuelva a intentarlo", Toast.LENGTH_LONG)
                    .show();
        }
    }

    public void loadImagefromGallery(View view) {
        // Create intent to Open Image applications like Gallery, Google Photos
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        // Start the Intent
        startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
    }


    private void initProfileAgente() {
        CajaLiquidacion caja = CajaLiquidacion.getCajaLiquidacion(Session.getCajaLiquidacion(this).getLiqId() + "");
        String fecha;
        if (caja != null) {
            fecha = caja.getFechaApertura();
        } else {

            fecha = "Aun no abrio caja";
        }
        Usuario usuario = Usuario.getUsuario(Session.getSession(this).getUsuIUsuarioId() + "");
        Vehiculo vehiculo = Vehiculo.getVehiculo(usuario.getUsuIUsuarioId() + "");
        String placa = "Sin vehiculo asignada";
        if (vehiculo != null) {
            placa = vehiculo.getPlaca();
        }
        textViewNombreAgente.setText(usuario.getPersona().getPerVNombres() + " " + usuario.getPersona().getPerVApellidoPaterno());
        textViewPlaca.setText("Placa V.: " + placa);
        String fechaMain = Utils.getDateDescription(fecha);
        textViewFecha.setText(fechaMain);


        textViewBsc.setText("BSC del Agente");

        textViewAgente.setText(usuario.getPersona().getPerVNombres() + " " + usuario.getPersona().getPerVApellidoPaterno());
        textViewUnidadTransporte.setText("Placa V.: " + placa);


        viewToolbaViews(R.color.colorPrimaryDark, 1);
    }

    private void viewToolbaViews(int color, int tipo) {

        tabs.setBackgroundColor(getResources().getColor(color));
        toolbar.setBackgroundColor(getResources().getColor(color));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(color));
        }


        if (tipo == 1) {
            linearLayoutProfile.setVisibility(View.VISIBLE);
            linearLayoutBsc.setVisibility(View.GONE);
            tabs.setSelectedTabIndicatorColor(getResources().getColor(colorInicial));
            colorInicial = color;
        } else {
            tabs.setSelectedTabIndicatorColor(getResources().getColor(colorInicial));
            linearLayoutProfile.setVisibility(View.GONE);
            linearLayoutBsc.setVisibility(View.VISIBLE);
            colorInicial = color;
        }
    }

    private void validaExistencia() {
        Intent intent = new Intent();
        intent.setAction("fe.apps.systemstrategy.GENERAR_XML");
        intent.setType("text/plain");


        // Verify it resolves
        PackageManager packageManager = getPackageManager();
        List<ResolveInfo> activities = packageManager.queryIntentActivities(intent, 0);
        boolean isIntentSafe = activities.size() > 0;
        if (!isIntentSafe) {
            new DialogGeneral(this).setCancelable(false).setMessages("Atencion", "Es necesario tener instala la aplicacion de facturacion electronica").setTextButtons("Salir", "Instalar").showDialog(new DialogGeneralListener() {
                @Override
                public void onSavePressed(AlertDialog alertDialog) {
                    MainActivity.this.finish();
                }

                @Override
                public void onCancelPressed(AlertDialog alertDialog) {
                    try {
                        MainActivity.this.finalize();
                    } catch (Throwable throwable) {
                        throwable.printStackTrace();
                    }
                }
            });
        }
    }

    @Override
    public void onIntentListenerAcces(HashMap<String, Boolean> booleanHashMap) {
        Log.d(TAG, "TAMAÑO HASH: " + booleanHashMap.size());
        this.booleanHashMap = booleanHashMap;

    }

    @Override
    public void onFragmentAccess(List<AccessFragment> accessFragmentList) {
        this.accessFragments = accessFragmentList;

        Log.d(TAG, "TAMAÑO FRAG: " + accessFragments.size());
        initViews();
        initProfileAgente();

    }

    private boolean hideFloatingButton() {

        List<CajaLiquidacion> cajaLiquidacions = CajaLiquidacion.find(CajaLiquidacion.class, "estado_Id=?", new String[]{Constants.CAJA_ABIERTA + ""});
        if (cajaLiquidacions.size() > 0) {
            fab.hide();
            return true;
        } else {
            showDialogAccount();
            return false;
            // CajaExistenteFragment.newIntance(usuario.getUsuIUsuarioId() + "").show(getSupportFragmentManager(), "");
        }

    }

    private void showDialogAccount() {
        new AccountDialog()
                .setUser(usuario)
                .setFloating(fab)
                .show(getSupportFragmentManager(), null);
    }

    private void initViews() {
        agent = Agent.getAgent();
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
        accessPrivilegesManager.verificarAccesosNavigationView(navigationView.getMenu());
        for (int i = 0; i < navigationView.getMenu().size(); i++) {
            Log.d(TAG, "navigationView.getMenu().getItem(" + i + "): " + navigationView.getMenu().getItem(i));
        }
        // Set behavior of Navigation drawer
        navigationView.setNavigationItemSelectedListener(this);
        // Adding Floating Action Button to bottom right of main view
        fab.setOnClickListener(this);
    }

    private void setupTabIcons(TabLayout tabLayout) {
      /*  TabLayout.Tab tabStations = tabLayout.getTabAt(0);
        TabLayout.Tab tabPlan = tabLayout.getTabAt(1);

        if (tabStations != null) {

            tabStations.setIcon(R.drawable.ic_gas_station);
        }

        if (tabPlan != null) {
            tabPlan.setIcon(R.drawable.ic_calendar);
        }
*/
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            int count = i + 1;
            for (AccessFragment accessFragment : accessFragments) {
                if (count == accessFragment.getOrden()) {
                    tabLayout.getTabAt(i).setIcon(accessFragments.get(i).getDrawable());
                }
            }
        }

        tabLayout.addOnTabSelectedListener(this);


    }


    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            MainActivity.this.finish();
        }
    }

    // Add Fragments to Tabs
    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getSupportFragmentManager());

        for (int i = 0; i < accessFragments.size(); i++) {
            int count = i + 1;
            for (AccessFragment fragment : accessFragments) {

                if (count == fragment.getOrden()) {
                    adapter.addFragment(fragment.getFragment(), fragment.getId());
                }
            }


        }


        //adapter.addFragment(new EstablecimientoFragment(), getString(R.string.estb_title_name));
        //adapter.addFragment(new PlanFragment(), getString(R.string.plan_title_name));
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
            startActivity(new Intent(getApplicationContext(), MainConfiguraciones.class));
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
            /*
            case R.id.nav_orders:
                setCurrentItem(1);
                break;*/
            case R.id.nav_plan:
                setCurrentItem(1);
                break;
            case R.id.nav_expenses:
                startActivity(new Intent(this, CajaGastoActivity.class));
                break;
            case R.id.nav_collect:
                //startActivity(new Intent(this, CargarInventarioActivity.class));
                //startActivity(new Intent(this, OrdenCargaActivity.class));
                startActivity(new Intent(this, OrdenCargaListActivity.class));
                break;
            case R.id.nav_summary:
                startActivity(new Intent(this, CuentaResumenActivity.class));
                break;
            case R.id.nav_close_account:
                showToast(item);
                startActivity(new Intent(MainActivity.this, CuentaResumenActivity.class));
                break;
            case R.id.nav_close_session:
                Toast.makeText(MainActivity.this, R.string.action_close_session, Toast.LENGTH_SHORT).show();

                break;
            case R.id.nav_settings:
                startActivity(new Intent(this, SettingsActivity.class));
                break;
            case R.id.nav_bluetooth:
                startActivity(new Intent(this, BluetoothActivity.class));
                break;
        }
        return true;
    }


    private void setCurrentItem(int position) {
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
                showDialogAccount();
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
    public void onEstablecimientoClickListener(Establecimiento establecimiento, View view) {
        Snackbar.make(fab, establecimiento.getEstVDescripcion(), Snackbar.LENGTH_LONG).show();
        Session.saveEstablecimiento(getApplicationContext(), establecimiento);

        if (booleanHashMap != null) {
            Log.d(TAG, "Not  null");

            if (booleanHashMap.get(MainStationActivity.class.getSimpleName())) {

                startActivity(new Intent(MainActivity.this, MainStationActivity.class));
            }
        }


    }


    @Override
    public void onLoadSuccess(String message) {
        Log.d(TAG, message);
    }

    @Override
    public void onLoadError(String message) {
        Log.d(TAG, message);
    }

    @Override
    public void onLoadErrorProcedure(String message) {
        Log.d(TAG, message);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        if (tab.getPosition() == 0) {
            viewToolbaViews(R.color.colorPrimaryDark, 1);
        }
        if (tab.getPosition() == 1) {
            viewToolbaViews(R.color.colorAccent, 2);
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    public void onSuccessOpenAccount() {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
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
