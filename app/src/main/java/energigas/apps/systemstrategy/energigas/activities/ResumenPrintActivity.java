package energigas.apps.systemstrategy.energigas.activities;


import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.sewoo.port.android.BluetoothPort;
import com.sewoo.request.android.RequestHandler;

import java.io.IOException;
import java.util.List;
import java.util.Vector;

import butterknife.BindView;
import butterknife.ButterKnife;
import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.adapters.FABScrollBehavior;
import energigas.apps.systemstrategy.energigas.entities.BeDocElectronico;
import energigas.apps.systemstrategy.energigas.entities.CajaLiquidacion;
import energigas.apps.systemstrategy.energigas.entities.Cliente;
import energigas.apps.systemstrategy.energigas.entities.ComprobanteVenta;
import energigas.apps.systemstrategy.energigas.entities.ComprobanteVentaDetalle;
import energigas.apps.systemstrategy.energigas.entities.Concepto;
import energigas.apps.systemstrategy.energigas.entities.Costs;
import energigas.apps.systemstrategy.energigas.entities.DEEntidad;
import energigas.apps.systemstrategy.energigas.entities.DatosEmpresa;
import energigas.apps.systemstrategy.energigas.entities.Inventory;
import energigas.apps.systemstrategy.energigas.entities.Producto;
import energigas.apps.systemstrategy.energigas.entities.Summary;
import energigas.apps.systemstrategy.energigas.entities.SummaryIncome;
import energigas.apps.systemstrategy.energigas.entities.Usuario;
import energigas.apps.systemstrategy.energigas.interfaces.ExportObjectsListener;
import energigas.apps.systemstrategy.energigas.printingsheets.SheetsPrintDispatch;
import energigas.apps.systemstrategy.energigas.utils.Constants;
import energigas.apps.systemstrategy.energigas.utils.Session;
import energigas.apps.systemstrategy.energigas.utils.Utils;

public class ResumenPrintActivity extends AppCompatActivity implements View.OnClickListener, ExportObjectsListener {
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


    @BindView(R.id.textViewVentaCabecera)
    TextView textViewCabecera;


    @BindView(R.id.toolbar)
    Toolbar toolbar;


    @BindView(R.id.textViewImprimirCabecera)
    TextView cabecera_empresa;

    @BindView(R.id.textDetalle_resumen)
    TextView textViewDetalleResumen;


    @BindView(R.id.cardIngresos)
    CardView cardViewIngreso;

    @BindView(R.id.tableLayoutIngresos)
    TableLayout tableLayoutIngresos;


    @BindView(R.id.cardCostos)
    CardView cardViewCostos;

    @BindView(R.id.tablayoutGastos)
    TableLayout tableLayoutGastos;

    @BindView(R.id.textDescripcion)
    TextView textViewInvetarioDescripcion;

    @BindView(R.id.textInicio)
    TextView textViewInvetarioInicio;

    @BindView(R.id.textVenta)
    TextView textViewInvetarioVenta;

    @BindView(R.id.textFinal)
    TextView textViewInvetarioFinal;


    private static final String TAG = "SellPrintActivity";
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    private Resources res;
    private CajaLiquidacion cajaLiquidacion;
    private Usuario usuario;
    private Summary summary;
    private Inventory inventoryList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_print_resumen);
        ButterKnife.bind(this);
        cajaLiquidacion = CajaLiquidacion.getCajaLiquidacion(Session.getCajaLiquidacion(this).getLiqId() + "");
        inventoryList = Inventory.getInventoryList(this).get(0);
        res = getResources();
        usuario = Usuario.getUsuario(Session.getSession(this).getUsuIUsuarioId() + "");
        fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);
        rotate_backward = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_backward);
        rotate_forward = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_forward);
        bluetoothSetup();
        setSupportActionBar(toolbar);
        floatingActionButton.setOnClickListener(this);
        floatingPrint.setOnClickListener(this);
        floatingDisconect.setOnClickListener(this);
        summary = Summary.getListSummary(this);


        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        setTextCabecera();
        setDetalleResumen();
        setIngresos();
        setCostos();
        setTextInventario();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void setTextInventario() {
        textViewInvetarioDescripcion.setText("" + inventoryList.getNombre());

        textViewInvetarioInicio.setText("" + inventoryList.getCantidadInicial());

        textViewInvetarioVenta.setText("" + inventoryList.getCantidadVendida());

        textViewInvetarioFinal.setText("" + inventoryList.getCantidadFinal());
    }


    private void setTextCabecera() {

        DEEntidad deEntidad = cajaLiquidacion.getEntidad();

        String text = String.format(res.getString(R.string.print_factura_empresa), deEntidad.getRazonSocial(), deEntidad.getDireccionFiscal(), "San Isidro Lima", "RUC: " + deEntidad.getrUC(), "Telf: (511) 2033001", "");
        cabecera_empresa.setText(text);


        String textCabeceraResumen = String.format(res.getString(R.string.print_factura_header_resumen), usuario.getPersona().getPerVNombres() + ", " + usuario.getPersona().getPerVApellidoPaterno(), "" + cajaLiquidacion.getLiqId(), "" + Utils.getDateDescription(cajaLiquidacion.getFechaApertura()));
        textViewCabecera.setText(textCabeceraResumen);
    }

    private void setDetalleResumen() {


        String stringdetallle = String.format(res.getString(R.string.print_factura_detalle_resumen), Utils.formatDoublePrint(summary.getSaldoInicial()) + "", Utils.formatDoublePrint(summary.getIngresosTotales()) + "", "" + Utils.formatDoublePrint(summary.getIngresosTotales()), "" + Utils.formatDoublePrint(summary.getGastos()), Utils.formatDoublePrint(summary.getEfectivoRendir()) + "");
        textViewDetalleResumen.setText(stringdetallle);


    }

    private void setIngresos() {
        Log.d(TAG, "" + summary.getSummaryIncomeList().size());

        if (Integer.parseInt(summary.getSummaryIncomeList().get(0).getCantidad()) < 1) {
            return;
        }
        cardViewIngreso.setVisibility(View.VISIBLE);

        TableRow tableRow1 = new TableRow(this);

        TextView textDescripcion1 = new TextView(this);
        TextView textCantidad1 = new TextView(this);
        TextView textTotalEmitidos1 = new TextView(this);
        TextView textTotalPagados1 = new TextView(this);
        TextView textTotalCobrados1 = new TextView(this);

        textDescripcion1.setGravity(Gravity.CENTER);
        textCantidad1.setGravity(Gravity.CENTER);
        textTotalEmitidos1.setGravity(Gravity.CENTER);
        textTotalPagados1.setGravity(Gravity.CENTER);
        textTotalCobrados1.setGravity(Gravity.CENTER);

        textDescripcion1.setTypeface(Typeface.DEFAULT_BOLD);
        textCantidad1.setTypeface(Typeface.DEFAULT_BOLD);
        textTotalEmitidos1.setTypeface(Typeface.DEFAULT_BOLD);
        textTotalPagados1.setTypeface(Typeface.DEFAULT_BOLD);
        textTotalCobrados1.setTypeface(Typeface.DEFAULT_BOLD);


        textDescripcion1.setTextSize(12);
        textCantidad1.setTextSize(12);
        textTotalEmitidos1.setTextSize(12);
        textTotalPagados1.setTextSize(12);
        textTotalCobrados1.setTextSize(12);

        tableRow1.addView(textDescripcion1);
        tableRow1.addView(textCantidad1);
        tableRow1.addView(textTotalEmitidos1);
        tableRow1.addView(textTotalPagados1);
        tableRow1.addView(textTotalCobrados1);

        textDescripcion1.setText("Descripcion");
        textCantidad1.setText("Cantidad");
        textTotalEmitidos1.setText("Emitidos");
        textTotalPagados1.setText("Pagados");
        textTotalCobrados1.setText("Cobrados");

        tableLayoutIngresos.addView(tableRow1, 0);

        int sumaCount = 0;
        Double sumaCantidad = 0.00;
        Double sumaTotalEmitidos = 0.00;
        Double sumaTotalPagados = 0.00;
        Double sumaTotalCobradas = 0.00;


        for (int i = 0; i < summary.getSummaryIncomeList().size(); i++) {


            int count = i + 1;
            sumaCount = count;
            SummaryIncome income = summary.getSummaryIncomeList().get(i);

            sumaCantidad = sumaCantidad + Double.parseDouble(income.getCantidad());
            sumaTotalEmitidos = sumaTotalEmitidos + Double.parseDouble(income.getEmitidos());
            sumaTotalPagados = sumaTotalPagados + Double.parseDouble(income.getPagados());
            sumaTotalCobradas = sumaTotalCobradas + Double.parseDouble(income.getCobrados());

            TableRow tableRow = new TableRow(this);
            //tableRow.removeAllViews();

            TextView textDescripcion = new TextView(this);
            TextView textCantidad = new TextView(this);
            TextView textTotalEmitidos = new TextView(this);
            TextView textTotalPagados = new TextView(this);
            TextView textTotalCobrados = new TextView(this);

            textDescripcion.setGravity(Gravity.CENTER);
            textCantidad.setGravity(Gravity.CENTER);
            textTotalEmitidos.setGravity(Gravity.CENTER);
            textTotalPagados.setGravity(Gravity.CENTER);
            textTotalCobrados.setGravity(Gravity.CENTER);


            textDescripcion.setTextSize(12);
            textCantidad.setTextSize(12);
            textTotalEmitidos.setTextSize(12);
            textTotalPagados.setTextSize(12);
            textTotalCobrados.setTextSize(12);


            tableRow.addView(textDescripcion);
            tableRow.addView(textCantidad);
            tableRow.addView(textTotalEmitidos);
            tableRow.addView(textTotalPagados);
            tableRow.addView(textTotalCobrados);

            Double aCantidad = (Double.parseDouble(income.getCantidad()));
            textDescripcion.setText(income.getConcepto());
            textCantidad.setText(aCantidad.intValue() + "");
            textTotalEmitidos.setText(Utils.formatDouble(Double.parseDouble(income.getEmitidos())));
            textTotalPagados.setText(Utils.formatDouble(Double.parseDouble(income.getPagados())));
            textTotalCobrados.setText(Utils.formatDouble(Double.parseDouble(income.getCobrados())));


            tableLayoutIngresos.addView(tableRow, count);
        }


        TableRow tableRow = new TableRow(this);
        tableRow.setBackgroundColor(Color.parseColor("#bdbdbd"));
        TextView textDescripcion = new TextView(this);
        TextView textCantidad = new TextView(this);
        TextView textTotalEmitidos = new TextView(this);
        TextView textTotalPagados = new TextView(this);
        TextView textTotalCobrados = new TextView(this);

        textDescripcion.setGravity(Gravity.CENTER);
        textCantidad.setGravity(Gravity.CENTER);
        textTotalEmitidos.setGravity(Gravity.CENTER);
        textTotalPagados.setGravity(Gravity.CENTER);
        textTotalCobrados.setGravity(Gravity.CENTER);

        textDescripcion.setTextSize(12);
        textCantidad.setTextSize(12);
        textTotalEmitidos.setTextSize(12);
        textTotalPagados.setTextSize(12);
        textTotalCobrados.setTextSize(12);

        tableRow.addView(textDescripcion);
        tableRow.addView(textCantidad);
        tableRow.addView(textTotalEmitidos);
        tableRow.addView(textTotalPagados);
        tableRow.addView(textTotalCobrados);

        textDescripcion.setText("Total");
        textCantidad.setText("" + sumaCantidad.intValue());
        textTotalEmitidos.setText(Utils.formatDouble(sumaTotalEmitidos));
        textTotalPagados.setText(Utils.formatDouble(sumaTotalPagados));
        textTotalCobrados.setText(Utils.formatDouble(sumaTotalCobradas));

        tableLayoutIngresos.addView(tableRow, sumaCount + 1);
    }

    private void setCostos() {
        if (summary == null || summary.getCostsList().size() < 1) {
            return;
        }
        cardViewCostos.setVisibility(View.VISIBLE);

        TableRow tableRow2 = new TableRow(this);

        TextView textDescrip = new TextView(this);
        TextView textRuta = new TextView(this);
        TextView textImporte = new TextView(this);

        textDescrip.setGravity(Gravity.CENTER);
        textRuta.setGravity(Gravity.CENTER);
        textImporte.setGravity(Gravity.CENTER);

        textDescrip.setTypeface(Typeface.DEFAULT_BOLD);
        textRuta.setTypeface(Typeface.DEFAULT_BOLD);
        textImporte.setTypeface(Typeface.DEFAULT_BOLD);


        textDescrip.setTextSize(12);
        textRuta.setTextSize(12);
        textImporte.setTextSize(12);

        textDescrip.setText("Descripcion");
        textRuta.setText("En Ruta");
        textImporte.setText("Importe");

        tableRow2.addView(textDescrip);
        tableRow2.addView(textRuta);
        tableRow2.addView(textImporte);

        tableLayoutGastos.addView(tableRow2, 0);
        int sumaCountGastos = 0;

        double sumaImporte = 0.00;

        for (int i = 0; i < summary.getCostsList().size(); i++) {
            Costs costs = summary.getCostsList().get(i);

            sumaImporte = sumaImporte + costs.getTotal();

            int count = i + 1;
            sumaCountGastos = count;

            TableRow tableRow3 = new TableRow(this);

            TextView textViewDES = new TextView(this);
            TextView textViewRUTA = new TextView(this);
            TextView textViewIMPORTE = new TextView(this);

            textViewDES.setGravity(Gravity.CENTER);
            textViewRUTA.setGravity(Gravity.CENTER);
            textViewIMPORTE.setGravity(Gravity.CENTER);


            textViewDES.setTextSize(12);
            textViewRUTA.setTextSize(12);
            textViewIMPORTE.setTextSize(12);

            textViewDES.setText(costs.getDescripcion());
            textViewRUTA.setText(costs.getEnRuta());
            textViewIMPORTE.setText(Utils.formatDouble(costs.getTotal()));

            tableRow3.addView(textViewDES);
            tableRow3.addView(textViewRUTA);
            tableRow3.addView(textViewIMPORTE);

            tableLayoutGastos.addView(tableRow3, count);
        }

        TableRow tableRow3 = new TableRow(this);
        tableRow3.setBackgroundColor(Color.parseColor("#bdbdbd"));
        TextView textDescrip3 = new TextView(this);
        TextView textRuta3 = new TextView(this);
        TextView textImporte3 = new TextView(this);

        textDescrip3.setGravity(Gravity.CENTER);
        textRuta3.setGravity(Gravity.CENTER);
        textImporte3.setGravity(Gravity.CENTER);

        textDescrip3.setTypeface(Typeface.DEFAULT_BOLD);
        textRuta3.setTypeface(Typeface.DEFAULT_BOLD);
        textImporte3.setTypeface(Typeface.DEFAULT_BOLD);


        textDescrip3.setTextSize(12);
        textRuta3.setTextSize(12);
        textImporte3.setTextSize(12);

        textDescrip3.setText("Total");
        textRuta3.setText("");
        textImporte3.setText(Utils.formatDouble(sumaImporte));

        tableRow3.addView(textDescrip3);
        tableRow3.addView(textRuta3);
        tableRow3.addView(textImporte3);

        tableLayoutGastos.addView(tableRow3, sumaCountGastos + 1);
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
                floatingActionButton.startAnimation(AnimationUtils.loadAnimation(ResumenPrintActivity.this, R.anim.rotate_forward));
                bluetoothConnect();
                break;
            case R.id.fabPrint:
                DEEntidad deEntidad = new CajaLiquidacion().getCajaLiquidacion(Session.getCajaLiquidacion(getApplicationContext()).getLiqId() + "").getEntidad();
                DatosEmpresa datosEmpresa = new DatosEmpresa(deEntidad);
                SheetsPrintDispatch printDispatch = new SheetsPrintDispatch();
                printDispatch.printResumen(cajaLiquidacion, usuario, summary, inventoryList, datosEmpresa);
                floatingActionButton.setImageResource(R.drawable.ic_printer_sync_ok);
                floatingActionButton.startAnimation(rotate_backward);
                floatingActionButton.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(ResumenPrintActivity.this, R.color.greem_background_item)));
                break;
            case R.id.fabDisconec:
                floatingPrint.startAnimation(fab_close);
                floatingDisconect.startAnimation(fab_close);

                floatingPrint.setClickable(false);
                floatingDisconect.setClickable(false);
                isFabOpen = false;
                btDisconn();
                break;

        }
    }


    private void bluetoothConnect() {

        CoordinatorLayout.LayoutParams pDes = (CoordinatorLayout.LayoutParams) floatingDisconect.getLayoutParams();
        CoordinatorLayout.LayoutParams pPrint = (CoordinatorLayout.LayoutParams) floatingPrint.getLayoutParams();


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

                floatingPrint.setLayoutParams(pPrint);
                floatingDisconect.setLayoutParams(pDes);


                floatingPrint.startAnimation(fab_close);
                floatingDisconect.startAnimation(fab_close);


                floatingPrint.setClickable(false);
                floatingDisconect.setClickable(false);

                isFabOpen = false;
            } else {

                pPrint.setBehavior(new FABScrollBehavior(this, null));
                pDes.setBehavior(new FABScrollBehavior(this, null));


                floatingPrint.setLayoutParams(pPrint);
                floatingDisconect.setLayoutParams(pDes);

                floatingPrint.startAnimation(fab_open);
                floatingDisconect.startAnimation(fab_open);


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
        floatingActionButton.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(ResumenPrintActivity.this, R.color.colorAccent)));


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

    @Override
    public void onLoadSuccess(String message) {

    }

    @Override
    public void onLoadError(String message) {

    }

    @Override
    public void onLoadErrorProcedure(String message) {

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
                        floatingActionButton.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(ResumenPrintActivity.this, R.color.greem_background_item)));

                    }
                });
                /*list.setEnabled(false);
                editText.setEnabled(false);
                searchButton.setEnabled(false);*/
            } else if (result.intValue() == -1) {


                floatingActionButton.setImageResource(R.drawable.ic_printer_sync_print);
                floatingActionButton.startAnimation(rotate_backward);
                floatingActionButton.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(ResumenPrintActivity.this, R.color.colorAccent)));
                Snackbar.make(floatingActionButton, R.string.print_snack_sync_success, Snackbar.LENGTH_SHORT).show();


            }

            super.onPostExecute(result);
        }
    }
}
