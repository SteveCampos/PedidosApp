package energigas.apps.systemstrategy.energigas.activities;


import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.sewoo.port.android.BluetoothPort;
import com.sewoo.request.android.RequestHandler;

import java.io.IOException;
import java.util.Vector;

import butterknife.BindView;
import butterknife.ButterKnife;
import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.adapters.FABScrollBehavior;
import energigas.apps.systemstrategy.energigas.entities.Almacen;
import energigas.apps.systemstrategy.energigas.entities.Cliente;
import energigas.apps.systemstrategy.energigas.entities.Despacho;
import energigas.apps.systemstrategy.energigas.entities.Dispatch;
import energigas.apps.systemstrategy.energigas.entities.Establecimiento;
import energigas.apps.systemstrategy.energigas.entities.Persona;
import energigas.apps.systemstrategy.energigas.entities.Vehiculo;
import energigas.apps.systemstrategy.energigas.printingsheets.SheetsPrintDispatch;
import energigas.apps.systemstrategy.energigas.utils.Constants;
import energigas.apps.systemstrategy.energigas.utils.Session;

public class PrintDispatch extends AppCompatActivity implements View.OnClickListener {
    private Boolean isFabOpen = false;
    private BluetoothPort bp;
    private BluetoothAdapter mBluetoothAdapter;
    private static final int REQUEST_ENABLE_BT = 2;
    private Vector<BluetoothDevice> remoteDevices;
    private Thread hThread;
    private boolean connected;

    private String MACADDRES = "00:12:6F:36:6B:90";
    private Animation fab_open, fab_close, rotate_forward, rotate_backward;


    @BindView(R.id.fabDisconec)
    FloatingActionButton floatingDisconect;
    @BindView(R.id.fabPrint)
    FloatingActionButton floatingPrint;
    @BindView(R.id.fab)
    FloatingActionButton floatingActionButton;
    @BindView(R.id.fabNext)
    FloatingActionButton floatingNext;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private Despacho mainDispatch;
    private Almacen almacen;
    private Establecimiento establecimiento;
    private Vehiculo vehiculo;
    private Persona agente;
    private Cliente cliente;


    //******* Widgets  to see preview print
/*
    @BindView(R.id.textNombre)
    TextView textNombre;
    @BindView(R.id.textDireccin)
    TextView textDireccin;
    @BindView(R.id.textInstalacion)
    TextView textInstalacion;
    @BindView(R.id.textCoordenadas)
    TextView textCoordenadas;
    @BindView(R.id.textPlaca)
    TextView textPlaca;
    @BindView(R.id.textChofer)
    TextView textChofer;
    @BindView(R.id.textNroComprobante)
    TextView textNroComprobante;
    @BindView(R.id.textFecha)
    TextView textFecha;
    @BindView(R.id.textHoraInicio)
    TextView textHoraInicio;
    @BindView(R.id.textHoraFin)
    TextView textHoraFin;
    @BindView(R.id.textContadorInicial)
    TextView textContadorInicial;
    @BindView(R.id.textContadorFinal)
    TextView textContadorFinal;
    @BindView(R.id.textCantidadDespachada)
    TextView textCantidadDespachada;
    @BindView(R.id.textPorInicial)
    TextView textPorInicial;
    @BindView(R.id.textPorFinal)
    TextView textPorFinal;
    @BindView(R.id.textSerieTanque)
    TextView textSerieTanque;
    @BindView(R.id.textNroTransporte)
    TextView textNroTransporte;*/


    private static final String TAG = "PrintDispatch";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print_dispatch);
        mainDispatch = Despacho.find(Despacho.class," despacho_Id=? ",new String[]{Session.getDespacho(this).getDespachoId()+""}).get(Constants.CURRENT);
        establecimiento = Establecimiento.find(Establecimiento.class, " est_I_Establecimiento_Id = ?  ", new String[]{Session.getSessionEstablecimiento(this).getEstIEstablecimientoId() + ""}).get(Constants.CURRENT);
        almacen = Almacen.find(Almacen.class, " alm_Id = ?  ", new String[]{Session.getAlmacen(this).getAlmId() + ""}).get(Constants.CURRENT);
        vehiculo = Vehiculo.findWithQuery(Vehiculo.class," SELECT V.* FROM VEHICULO_USUARIO VU, VEHICULO V WHERE VU.VE_ID = V.VE_ID AND VU.USUARIO_ID="+Session.getSession(this).getUsuIUsuarioId()+"; ",new String[]{}).get(Constants.CURRENT);
        agente = Persona.findWithQuery(Persona.class,"SELECT P.* FROM PERSONA P, USUARIO U WHERE P.PER_I_PERSONA_ID = U.USU_I_PERSONA_ID AND U.USU_I_USUARIO_ID = "+Session.getSession(this).getUsuIUsuarioId()+" ;",null).get(Constants.CURRENT);
        cliente = Cliente.find(Cliente.class," CLI_I_CLIENTE_ID = ? ",new String[]{establecimiento.getEstIEstablecimientoId()+""}).get(Constants.CURRENT);
        Persona persona = Persona.find(Persona.class," CLI_I_PERSONA_ID=? ",new String[]{cliente.getCliIPersonaId()+""}).get(Constants.CURRENT);
        cliente.setPersona(persona);
        ButterKnife.bind(this);
        fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);
        rotate_backward = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_backward);
        rotate_forward = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_forward);
        bluetoothSetup();
        setSupportActionBar(toolbar);
        floatingActionButton.setOnClickListener(this);
        floatingPrint.setOnClickListener(this);
        floatingDisconect.setOnClickListener(this);
        floatingNext.setOnClickListener(this);

        if (getSupportActionBar() != null) {
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
        super.onBackPressed();
        disconnectPrinter();
    }


    private void disconnectPrinter() {
        if (connected) {
            btDisconn();
        } else {

        }
    }

    private void bluetoothSetup() {
        // Initialize
        clearBtDevData();
        bp = BluetoothPort.getInstance();
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            // Device does not support Bluetooth
        }
        if (!mBluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);

            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }
    }

    private void clearBtDevData() {
        remoteDevices = new Vector<BluetoothDevice>();
    }

    @Override
    public void onClick(View view) {


        switch (view.getId()) {
            case R.id.fab:

                floatingActionButton.setImageResource(R.drawable.ic_sync);
                floatingActionButton.startAnimation(AnimationUtils.loadAnimation(PrintDispatch.this, R.anim.rotate_forward));
                bluetoothConnect();
                break;
            case R.id.fabPrint:
                SheetsPrintDispatch printDispatch = new SheetsPrintDispatch();
                printDispatch.printNow(mainDispatch,almacen,establecimiento,vehiculo,agente,cliente);

                break;
            case R.id.fabDisconec:
                floatingPrint.startAnimation(fab_close);
                floatingDisconect.startAnimation(fab_close);
                floatingNext.startAnimation(fab_close);

                floatingNext.setClickable(false);
                floatingPrint.setClickable(false);
                floatingDisconect.setClickable(false);
                isFabOpen = false;
                btDisconn();
                break;
            case R.id.fabNext:
                //ORDER
                startActivity(new Intent(PrintDispatch.this, PrintFacturaActivity.class));
                break;
        }
    }


    private void bluetoothConnect() {

        CoordinatorLayout.LayoutParams pDes = (CoordinatorLayout.LayoutParams) floatingDisconect.getLayoutParams();
        CoordinatorLayout.LayoutParams pPrint = (CoordinatorLayout.LayoutParams) floatingPrint.getLayoutParams();
        CoordinatorLayout.LayoutParams pNext = (CoordinatorLayout.LayoutParams) floatingNext.getLayoutParams();


        if (!connected) {


            connectInit();
            try {
                btConn(mBluetoothAdapter.getRemoteDevice(MACADDRES));
            } catch (IllegalArgumentException e) {

                return;
            } catch (IOException e) {
                Snackbar.make(floatingActionButton, R.string.print_snack_error, Snackbar.LENGTH_SHORT).show();
                return;
            }
        } else {

            if (isFabOpen) {

                pPrint.setBehavior(null);
                pDes.setBehavior(null);
                pNext.setBehavior(null);
                floatingPrint.setLayoutParams(pPrint);
                floatingDisconect.setLayoutParams(pDes);
                floatingNext.setLayoutParams(pNext);

                floatingPrint.startAnimation(fab_close);
                floatingDisconect.startAnimation(fab_close);
                floatingNext.startAnimation(fab_close);

                floatingPrint.setClickable(false);
                floatingDisconect.setClickable(false);
                floatingNext.setClickable(false);
                isFabOpen = false;
            } else {

                pPrint.setBehavior(new FABScrollBehavior(this, null));
                pDes.setBehavior(new FABScrollBehavior(this, null));
                pNext.setBehavior(new FABScrollBehavior(this, null));

                floatingPrint.setLayoutParams(pPrint);
                floatingDisconect.setLayoutParams(pDes);
                floatingNext.setLayoutParams(pNext);

                floatingNext.startAnimation(fab_open);
                floatingPrint.startAnimation(fab_open);
                floatingDisconect.startAnimation(fab_open);

                floatingNext.setClickable(true);
                floatingPrint.setClickable(true);
                floatingDisconect.setClickable(true);
                isFabOpen = true;

            }


        }
    }


    private void connectInit() {
        if (!mBluetoothAdapter.isEnabled()) {
            mBluetoothAdapter.enable();
            try {
                Thread.sleep(3600);
            } catch (Exception e) {
            }
        }
    }

    private void btConn(final BluetoothDevice btDev) throws IOException {
        new connTask().execute(btDev);
    }

    private void btDisconn() {
        try {
            bp.disconnect();

            Thread.sleep(1200);
        } catch (Exception e) {

            Snackbar.make(floatingActionButton, R.string.print_snack_error, Snackbar.LENGTH_SHORT).show();

        }

        if ((hThread != null) && (hThread.isAlive()))
            hThread.interrupt();
        connected = false;
        floatingActionButton.setImageResource(R.drawable.ic_printer_sync_print);
        floatingActionButton.startAnimation(rotate_backward);
        floatingActionButton.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(PrintDispatch.this, R.color.colorAccent)));


    }


    public class connTask extends AsyncTask<BluetoothDevice, Void, Integer> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Integer doInBackground(BluetoothDevice... params) {
            // TODO Auto-generated method stub
            Integer retVal = null;
            try {
                bp.connect(params[0]);
                retVal = new Integer(0);
            } catch (IOException e) {
                retVal = new Integer(-1);
            }
            return retVal;
        }

        @Override
        protected void onPostExecute(Integer result) {
            // TODO Auto-generated method stub
            if (result.intValue() == 0) {
                RequestHandler rh = new RequestHandler();
                hThread = new Thread(rh);
                hThread.start();
                connected = true;


                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // UI

                        floatingActionButton.setImageResource(R.drawable.ic_printer_sync_ok);
                        floatingActionButton.startAnimation(rotate_backward);
                        floatingActionButton.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(PrintDispatch.this, R.color.greem_background_item)));

                    }
                });
                /*list.setEnabled(false);
                editText.setEnabled(false);
                searchButton.setEnabled(false);*/
            } else if (result.intValue() == -1) {


                floatingActionButton.setImageResource(R.drawable.ic_printer_sync_print);
                floatingActionButton.startAnimation(rotate_backward);
                floatingActionButton.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(PrintDispatch.this, R.color.colorAccent)));
                Snackbar.make(floatingActionButton, R.string.print_snack_sync_success, Snackbar.LENGTH_SHORT).show();


            }

            super.onPostExecute(result);
        }
    }
}
