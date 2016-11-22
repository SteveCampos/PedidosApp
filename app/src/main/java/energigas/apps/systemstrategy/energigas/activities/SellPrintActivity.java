package energigas.apps.systemstrategy.energigas.activities;


import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.net.Uri;
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
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.sewoo.port.android.BluetoothPort;
import com.sewoo.request.android.RequestHandler;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Vector;

import butterknife.BindView;
import butterknife.ButterKnife;
import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.adapters.FABScrollBehavior;
import energigas.apps.systemstrategy.energigas.entities.Almacen;
import energigas.apps.systemstrategy.energigas.entities.Cliente;
import energigas.apps.systemstrategy.energigas.entities.ComprobanteVenta;
import energigas.apps.systemstrategy.energigas.entities.ComprobanteVentaDetalle;
import energigas.apps.systemstrategy.energigas.entities.Despacho;
import energigas.apps.systemstrategy.energigas.entities.Establecimiento;
import energigas.apps.systemstrategy.energigas.entities.Persona;
import energigas.apps.systemstrategy.energigas.entities.Producto;
import energigas.apps.systemstrategy.energigas.entities.Usuario;
import energigas.apps.systemstrategy.energigas.entities.Vehiculo;
import energigas.apps.systemstrategy.energigas.printingsheets.SheetsPrintDispatch;
import energigas.apps.systemstrategy.energigas.utils.Constants;
import energigas.apps.systemstrategy.energigas.utils.Session;
import energigas.apps.systemstrategy.energigas.utils.Utils;

public class SellPrintActivity extends AppCompatActivity implements View.OnClickListener {
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


    @BindView(R.id.textViewImprimirCabecera)
    TextView cabecera_empresa;

    @BindView(R.id.textViewVentaCabecera)
    TextView venta_cabecera;



    @BindView(R.id.textViewImprimirContenidoRight)
    TextView textViewImprimirContenidoRight;

    @BindView(R.id.textViewImprimirContenidoLeft)
    TextView textViewImprimirContenidoLeft;


    @BindView(R.id.textViewFooterComp)
    TextView textViewFooterComp;

    private ComprobanteVenta comprobanteVenta;
    private List<ComprobanteVentaDetalle> comprobanteVentaDetalles;
    private Cliente cliente;
    private Usuario usuario;

    private static final String TAG = "SellPrintActivity";
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    private Resources res ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comprobante_print);
        ButterKnife.bind(this);
        res = getResources();
        comprobanteVenta = ComprobanteVenta.getComprobanteVentaId(Session.getComprobanteVenta(this).getCompId() + "");
        comprobanteVentaDetalles = ComprobanteVentaDetalle.comprobanteVentaDetalles(comprobanteVenta.getCompId()+"");
        comprobanteVenta.setItemsDetalle(comprobanteVentaDetalles);
        usuario = Usuario.getUsuario(Session.getSession(this).getUsuIUsuarioId()+"");
        cliente = Cliente.getCliente(comprobanteVenta.getClienteId()+"");
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
        setTextCabecera();
        setTextItems();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void setTextCabecera() {

        String compVar = comprobanteVenta.getSerie() + "-" + comprobanteVenta.getNumDoc();
        String clienteVar = cliente.getPersona().getPerVNombres()+" "+cliente.getPersona().getPerVApellidoPaterno();

        String text = String.format(res.getString(R.string.print_factura_empresa),"Energigas S.A.C", "Av. Santo Toribio # 173, cruce con Av. Vía Central, Centro Empresarial, Edificio Real 8 Of. 502","San Isidro Lima","RUC: 20506151547","Telf: (511) 2033001");
        cabecera_empresa.setText(text);

        String textVC = String.format(res.getString(R.string.print_factura_header),compVar,Utils.getDatePhone(),clienteVar,cliente.getPersona().getPerVDocIdentidad(),cliente.getPersona().getUbicacion().getDescripcion());
        venta_cabecera.setText(textVC);
    }

    private void setTextItems(){
        DecimalFormat df = new DecimalFormat("#.00");
        String costoUnidad = "";
        String cantidadNombre = "";
        double importeTotal = 0.0;
        for (int i = 0; i < comprobanteVentaDetalles.size(); i++) {
            importeTotal = importeTotal + comprobanteVentaDetalles.get(i).getImporte();
            if (i == (comprobanteVentaDetalles.size() - 1)) {

                costoUnidad = costoUnidad + df.format(comprobanteVentaDetalles.get(i).getImporte());
                cantidadNombre = cantidadNombre+ comprobanteVentaDetalles.get(i).getCantidad()+"   "+Producto.getNameProducto(comprobanteVentaDetalles.get(i).getProId()+"")+" ";
            } else {

                costoUnidad = costoUnidad + df.format(comprobanteVentaDetalles.get(i).getImporte())+ " \n";
                cantidadNombre = cantidadNombre+ comprobanteVentaDetalles.get(i).getCantidad()+"   "+Producto.getNameProducto(comprobanteVentaDetalles.get(i).getProId()+"")+" \n";
            }

        }

        String textImporte = String.format(res.getString(R.string.print_factura_items_importe),costoUnidad,df.format(importeTotal),"12.12","13.13","14.14","15.15","16.16",df.format(importeTotal)+"");
        textViewImprimirContenidoRight.setText(textImporte);

        String textCNombre = String.format(res.getString(R.string.print_factura_items),cantidadNombre);
        textViewImprimirContenidoLeft.setText(textCNombre);
        String textTipoVenta = "VENTA AL CONTADO";
        if(comprobanteVenta.getPlanPago() !=null){
            textTipoVenta = "VENTA AL CREDITO";
        }
        String codigoVenta = "MUOy4w/Q6VGqEBNiwQOhMYLCvm8";

        String comproFooter = String.format(res.getString(R.string.print_factura_footer),comprobanteVenta.getValorResumen(),textTipoVenta,usuario.getPersona().getPerVNombres()+" "+usuario.getPersona().getPerVApellidoPaterno(),codigoVenta,"http://www.energigas.com.pe");
        textViewFooterComp.setText(comproFooter);




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
                floatingActionButton.startAnimation(AnimationUtils.loadAnimation(SellPrintActivity.this, R.anim.rotate_forward));
                bluetoothConnect();
                break;
            case R.id.fabPrint:
                SheetsPrintDispatch printDispatch = new SheetsPrintDispatch();
                printDispatch.printNowComprobante(cliente,comprobanteVenta,usuario);
                floatingActionButton.setImageResource(R.drawable.ic_printer_sync_ok);
                floatingActionButton.startAnimation(rotate_backward);
                floatingActionButton.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(SellPrintActivity.this, R.color.greem_background_item)));
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
                startActivity(new Intent(SellPrintActivity.this, PrintFacturaActivity.class));
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
        floatingActionButton.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(SellPrintActivity.this, R.color.colorAccent)));


    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("SellPrint Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
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
                        floatingActionButton.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(SellPrintActivity.this, R.color.greem_background_item)));

                    }
                });
                /*list.setEnabled(false);
                editText.setEnabled(false);
                searchButton.setEnabled(false);*/
            } else if (result.intValue() == -1) {


                floatingActionButton.setImageResource(R.drawable.ic_printer_sync_print);
                floatingActionButton.startAnimation(rotate_backward);
                floatingActionButton.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(SellPrintActivity.this, R.color.colorAccent)));
                Snackbar.make(floatingActionButton, R.string.print_snack_sync_success, Snackbar.LENGTH_SHORT).show();


            }

            super.onPostExecute(result);
        }
    }
}
