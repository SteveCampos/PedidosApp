package energigas.apps.systemstrategy.energigas.activities;


import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
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
import com.google.android.gms.vision.text.Text;
import com.sewoo.port.android.BluetoothPort;
import com.sewoo.request.android.RequestHandler;

import java.io.IOException;
import java.util.List;
import java.util.Vector;

import butterknife.BindView;
import butterknife.ButterKnife;
import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.adapters.FABScrollBehavior;
import energigas.apps.systemstrategy.energigas.asyntask.ExportTask;
import energigas.apps.systemstrategy.energigas.entities.CajaLiquidacion;
import energigas.apps.systemstrategy.energigas.entities.Cliente;
import energigas.apps.systemstrategy.energigas.entities.ComprobanteVenta;
import energigas.apps.systemstrategy.energigas.entities.ComprobanteVentaDetalle;
import energigas.apps.systemstrategy.energigas.entities.DEEntidad;
import energigas.apps.systemstrategy.energigas.entities.DatosEmpresa;
import energigas.apps.systemstrategy.energigas.entities.Producto;
import energigas.apps.systemstrategy.energigas.entities.Usuario;
import energigas.apps.systemstrategy.energigas.entities.BeDocElectronico;
import energigas.apps.systemstrategy.energigas.interfaces.ExportObjectsListener;
import energigas.apps.systemstrategy.energigas.printingsheets.SheetsPrintDispatch;
import energigas.apps.systemstrategy.energigas.utils.Constants;
import energigas.apps.systemstrategy.energigas.utils.NumberToLetterConverter;
import energigas.apps.systemstrategy.energigas.utils.Session;
import energigas.apps.systemstrategy.energigas.utils.Utils;

public class SellPrintActivity extends AppCompatActivity implements View.OnClickListener, ExportObjectsListener {
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


    /**/

    @BindView(R.id.textview_title)
    TextView textViewTitle;
    @BindView(R.id.textview_telf)
    TextView textViewTelf;
    @BindView(R.id.textviewruc)
    TextView textViewRuc;
    @BindView(R.id.textviewimei)
    TextView textViewImei;
    @BindView(R.id.textviewcompro)
    TextView textViewCompro;
    @BindView(R.id.textviewfecha)
    TextView textViewFecha;
    @BindView(R.id.textviewnum)
    TextView textViewNum;
    @BindView(R.id.texviewprecioimportestring)
    TextView textViewImporeString;
    /**/

    /*Items*/
    @BindView(R.id.tableLayoutFactura)
    TableLayout tableLayoutFactura;

    /**/
    private static final String TAG = "SellPrintActivity";
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    private Resources res;
    private BeDocElectronico beDocElectronico;
    private Double igv;
    private DatosEmpresa datosEmpresa;

    /*Variables Globales*/
    private String idProducto = "";
    private String CantidProducto = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comprobante_print);
        ButterKnife.bind(this);
        res = getResources();
        comprobanteVenta = ComprobanteVenta.getComprobanteVentaId(Session.getComprobanteVenta(this).getCompId() + "");
        beDocElectronico = BeDocElectronico.getBeDocElectronico(comprobanteVenta.getCompId() + "");
        igv = Double.parseDouble(Session.getConceptoIGV().getDescripcion());
        comprobanteVentaDetalles = ComprobanteVentaDetalle.comprobanteVentaDetalles(comprobanteVenta.getCompId() + "");
        comprobanteVenta.setItemsDetalle(comprobanteVentaDetalles);
        usuario = Usuario.getUsuario(Session.getSession(this).getUsuIUsuarioId() + "");
        cliente = Cliente.getCliente(comprobanteVenta.getClienteId() + "");
        fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);
        rotate_backward = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_backward);
        rotate_forward = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_forward);
        bluetoothSetup();
        setSupportActionBar(toolbar);
        floatingActionButton.setOnClickListener(this);
        floatingPrint.setOnClickListener(this);
        floatingDisconect.setOnClickListener(this);
        DEEntidad deEntidad = new CajaLiquidacion().getCajaLiquidacion(Session.getCajaLiquidacion(this).getLiqId() + "").getEntidad();
        datosEmpresa = new DatosEmpresa(deEntidad);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        setTextCabecera();
        ListItemsFactura();
        //setTextItems();
        //listItems();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

        new ExportTask(this, this).execute(Constants.TABLA_COMPROBANTE, Constants.S_CREADO);
    }


    private void setTextCabecera() {

        String compVar = comprobanteVenta.getSerie() + "-" + comprobanteVenta.getNumDoc();
        String clienteVar = cliente.getPersona().getPerVNombres() + " " + cliente.getPersona().getPerVApellidoPaterno();
        String compro = "BOLETA ELECTRONICA";
        if (comprobanteVenta.getTipoComprobanteId() == Constants.TIPO_DOCUMENTO_FACTURA) {
            compro = "FACTURA ELECTRONICA";
        }
        String text = String.format(res.getString(R.string.print_factura_empresa), "Av. Santo Toribio # 173, cruce con Av. Vía Central, Centro Empresarial, Edificio Real 8 Of. 502", "San Isidro Lima");
        cabecera_empresa.setText(text);

        textViewTitle.setText("Energigas S.A.C");
       /* textViewTelf.setText("(511)3601953");
        textViewRuc.setText("R.U.C: 2050615154");
        textViewImei.setText("IMEI: 01234567894125014");
        textViewCompro.setText(compro);
        textViewFecha.setText(Utils.getDatePhoneWithTime());
        textViewNum.setText(compVar);*/

        String textextelefono = String.format(res.getString(R.string.print_factura_telefono), "(511)3601953");
        textViewTelf.setText(textextelefono);
        String textexRuc = String.format(res.getString(R.string.print_factura_ruc), "2050615154");
        textViewRuc.setText(textexRuc);
        String textexImei = String.format(res.getString(R.string.print_factura_imei), "01234567894125014");
        textViewImei.setText(textexImei);
        String textexFecha = String.format(res.getString(R.string.print_factura_fecha), Utils.getDatePhoneWithTime());
        textViewFecha.setText(textexFecha);
        String textexNum = String.format(res.getString(R.string.print_factura_num), compVar);
        textViewNum.setText(textexNum);
        textViewCompro.setText(compro);

        String textVC = String.format(res.getString(R.string.print_factura_header), clienteVar, cliente.getPersona().getPerVDocIdentidad(), cliente.getPersona().getUbicacion().getDescripcion());
        venta_cabecera.setText(textVC);
    }


    private void setTextItems() {

        String costoUnidad = "";
        String cantidadNombre = "";
        double importeTotal = 0.0;

        for (int i = 0; i < comprobanteVentaDetalles.size(); i++) {
            importeTotal = importeTotal + Utils.formatDoubleNumber(comprobanteVentaDetalles.get(i).getImporte());

            if (i == (comprobanteVentaDetalles.size() - 1)) {

                costoUnidad = costoUnidad + Utils.formatDoublePrint(comprobanteVentaDetalles.get(i).getImporte());
                cantidadNombre = cantidadNombre + comprobanteVentaDetalles.get(i).getCantidad() + "   " + Producto.getNameProducto(comprobanteVentaDetalles.get(i).getProId() + "") + " ";


            } else {

                costoUnidad = costoUnidad + Utils.formatDoublePrint(comprobanteVentaDetalles.get(i).getImporte()) + " \n";
                cantidadNombre = cantidadNombre + comprobanteVentaDetalles.get(i).getCantidad() + "   " + Producto.getNameProducto(comprobanteVentaDetalles.get(i).getProId() + "") + " \n";


            }

        }
        Double importeIgv = importeTotal * igv;

        Double importeTotalIgv = new Double(importeTotal + importeIgv);

        String textImporte = String.format(res.getString(R.string.print_factura_items_importe2), Utils.formatDoublePrint(importeTotalIgv) + "", "0.00", "0.00", "0.00", "0.00", Utils.formatDoublePrint(importeIgv), Utils.formatDoublePrint(importeTotalIgv));
        textViewImprimirContenidoRight.setText(textImporte);

//        String textCNombre = String.format(res.getString(R.string.print_factura_items), cantidadNombre);
        String textCNombre = String.format(res.getString(R.string.print_factura_items2) + "");
        /*textViewCodigo.setText(idProducto);
        textViewCantidad.setText(CantidProducto);
        textViewUm.setText();*/

       textViewImprimirContenidoLeft.setText(textCNombre);
        String textTipoVenta = "VENTA AL CONTADO";
        if (comprobanteVenta.getPlanPago() != null) {
            textTipoVenta = "VENTA AL CREDITO";
        }
        String codigoVenta = beDocElectronico.getResumenFirma();
        Log.d("", codigoVenta);
        textViewImporeString.setText("SON:"+NumberToLetterConverter.convertNumberToLetter(importeTotalIgv));
        String comproFooter = String.format(res.getString(R.string.print_factura_footer), beDocElectronico.getResumenFirma(), textTipoVenta, usuario.getPersona().getPerVNombres() + " " + usuario.getPersona().getPerVApellidoPaterno(), codigoVenta, "http://www.energigas.com.pe");
        textViewFooterComp.setText(comproFooter);
    }

    private void listItems() {

        TableRow tableRow1 = new TableRow(getApplicationContext());

        TextView textCodigo = new TextView(getApplicationContext());
        TextView textCantidad = new TextView(getApplicationContext());
        TextView textUm = new TextView(getApplicationContext());
        TextView textDescripcion = new TextView(getApplicationContext());
        TextView textPrecioUnitario = new TextView(getApplicationContext());
        TextView textImporte = new TextView(getApplicationContext());

        textCodigo.setGravity(Gravity.CENTER);
        textCantidad.setGravity(Gravity.CENTER);
        textUm.setGravity(Gravity.CENTER);
        textDescripcion.setGravity(Gravity.CENTER);
        textPrecioUnitario.setGravity(Gravity.CENTER);
        textImporte.setGravity(Gravity.CENTER);

        textCodigo.setTypeface(Typeface.DEFAULT_BOLD);
        textCantidad.setTypeface(Typeface.DEFAULT_BOLD);
        textUm.setTypeface(Typeface.DEFAULT_BOLD);
        textDescripcion.setTypeface(Typeface.DEFAULT_BOLD);
        textPrecioUnitario.setTypeface(Typeface.DEFAULT_BOLD);
        textImporte.setTypeface(Typeface.DEFAULT_BOLD);

        textCodigo.setTextSize(12);
        textCantidad.setTextSize(12);
        textUm.setTextSize(12);
        textDescripcion.setTextSize(12);
        textPrecioUnitario.setTextSize(12);

        tableRow1.addView(textCodigo);
        tableRow1.addView(textCantidad);
        tableRow1.addView(textUm);
        tableRow1.addView(textDescripcion);
        tableRow1.addView(textPrecioUnitario);
        tableRow1.addView(textImporte);

        textCodigo.setText("COD");
        textCantidad.setText("CANTI.");
        textUm.setText("U.M.");
        textDescripcion.setText("DESCRIP.");
        textPrecioUnitario.setText("P.U.");
        textImporte.setText("IMPORTE");

        tableLayoutFactura.addView(tableRow1, 0);
/*
        int sumaCount = 0;
        String costoUnidad = "";
        String cantidadNombre = "";
        double importeTotal = 0.0;

        for (int i = 0; i < comprobanteVentaDetalles.size(); i++) {

            int count = i + 1;
            sumaCount = count;
            //SummaryIncome income = summary.getSummaryIncomeList().get(i);

            TableRow tableRow = new TableRow(getApplicationContext());
            //tableRow.removeAllViews();
            costoUnidad = costoUnidad + Utils.formatDoublePrint(comprobanteVentaDetalles.get(i).getImporte());
            cantidadNombre = cantidadNombre + comprobanteVentaDetalles.get(i).getCantidad() + "   " + Producto.getNameProducto(comprobanteVentaDetalles.get(i).getProId() + "") + " ";

            TextView textCodigo1 = new TextView(getApplicationContext());
            TextView textCantidad1 = new TextView(getApplicationContext());
            TextView textUm1 = new TextView(getApplicationContext());
            TextView textDescripcion1 = new TextView(getApplicationContext());
            TextView textPrecioUnitario1 = new TextView(getApplicationContext());
            TextView textImporte1 = new TextView(getApplicationContext());

            textCodigo1.setGravity(Gravity.CENTER);
            textCantidad1.setGravity(Gravity.CENTER);
            textUm1.setGravity(Gravity.CENTER);
            textDescripcion1.setGravity(Gravity.CENTER);
            textPrecioUnitario1.setGravity(Gravity.CENTER);
            textImporte1.setGravity(Gravity.CENTER);


            textCodigo1.setTextSize(12);
            textCantidad1.setTextSize(12);
            textUm1.setTextSize(12);
            textDescripcion1.setTextSize(12);
            textPrecioUnitario1.setTextSize(12);


            tableRow1.addView(textCodigo1);
            tableRow1.addView(textCantidad1);
            tableRow1.addView(textUm1);
            tableRow1.addView(textDescripcion1);
            tableRow1.addView(textPrecioUnitario1);
            tableRow1.addView(textImporte1);



            if (i == (comprobanteVentaDetalles.size() - 1)) {

                costoUnidad = costoUnidad + Utils.formatDoublePrint(comprobanteVentaDetalles.get(i).getImporte());
                cantidadNombre = cantidadNombre + comprobanteVentaDetalles.get(i).getCantidad() + "   " + Producto.getNameProducto(comprobanteVentaDetalles.get(i).getProId() + "") + " ";
                textCodigo1.setText(Integer.toString(comprobanteVentaDetalles.get(i).getProId()));
                textCantidad1.setText(comprobanteVentaDetalles.get(i).getCantidad()+"");
                textUm1.setText("U.M.");
                textDescripcion1.setText("DESCRIP.");
                textPrecioUnitario1.setText(comprobanteVentaDetalles.get(i).getPrecioUnitario()+"");
                textImporte1.setText("IMPORTE");

            } else {

                costoUnidad = costoUnidad + Utils.formatDoublePrint(comprobanteVentaDetalles.get(i).getImporte()) + " \n";
                cantidadNombre = cantidadNombre + comprobanteVentaDetalles.get(i).getCantidad() + "   " + Producto.getNameProducto(comprobanteVentaDetalles.get(i).getProId() + "") + " \n";
                textCodigo1.setText(Integer.toString(comprobanteVentaDetalles.get(i).getProId()));
                textCantidad1.setText(comprobanteVentaDetalles.get(i).getCantidad()+"");
                textUm1.setText("U.M.");
                textDescripcion1.setText("DESCRIP.");
                textPrecioUnitario1.setText(comprobanteVentaDetalles.get(i).getPrecioUnitario()+"");
                textImporte1.setText("IMPORTE");

            }*/
       /* }
        /*Double importeIgv = importeTotal * igv;

        Double importeTotalIgv = new Double(importeTotal + importeIgv);

        String textImporte2 = String.format(res.getString(R.string.print_factura_items_importe2), Utils.formatDoublePrint(importeTotalIgv) + "", "0.00", "0.00", "0.00", "0.00", Utils.formatDoublePrint(importeIgv), Utils.formatDoublePrint(importeTotalIgv));
        textViewImprimirContenidoRight.setText(textImporte2);

//        String textCNombre = String.format(res.getString(R.string.print_factura_items), cantidadNombre);
        String textCNombre = String.format(res.getString(R.string.print_factura_items2) + "");
        /*textViewCodigo.setText(idProducto);
        textViewCantidad.setText(CantidProducto);
        textViewUm.setText();*/

        /*textViewImprimirContenidoLeft.setText(textCNombre);
        String textTipoVenta = "VENTA AL CONTADO";
        if (comprobanteVenta.getPlanPago() != null) {
            textTipoVenta = "VENTA AL CREDITO";
        }
        String codigoVenta = beDocElectronico.getResumenFirma();
        Log.d("", codigoVenta);
        String comproFooter = String.format(res.getString(R.string.print_factura_footer), beDocElectronico.getResumenFirma(), textTipoVenta, usuario.getPersona().getPerVNombres() + " " + usuario.getPersona().getPerVApellidoPaterno(), codigoVenta, "http://www.energigas.com.pe");
        textViewFooterComp.setText(comproFooter);*/

    }

    private void ListItemsFactura (){
        String costoUnidad = "";
        String cantidadNombre = "";
        double importeTotal = 0.0;
        View tableRow = LayoutInflater.from(this).inflate(R.layout.item_facturas,null,false);
        TextView item_codigo  = (TextView) tableRow.findViewById(R.id.textviewcodigo);
        TextView item_cant  = (TextView) tableRow.findViewById(R.id.textviewcantidad);
        TextView item_um  = (TextView) tableRow.findViewById(R.id.textviewum);
        TextView item_descp = (TextView) tableRow.findViewById(R.id.textviewdescr);
        TextView item_pu  = (TextView) tableRow.findViewById(R.id.textviewpreciouni);
        TextView item_importe  = (TextView) tableRow.findViewById(R.id.textRespPrecioImporte);


        for (int i = 0; i < comprobanteVentaDetalles.size(); i++) {
            importeTotal = importeTotal + Utils.formatDoubleNumber(comprobanteVentaDetalles.get(i).getImporte());

            if (i == (comprobanteVentaDetalles.size() - 1)) {

                costoUnidad = costoUnidad + Utils.formatDoublePrint(comprobanteVentaDetalles.get(i).getImporte());
                cantidadNombre = cantidadNombre + comprobanteVentaDetalles.get(i).getCantidad() + "   " + Producto.getNameProducto(comprobanteVentaDetalles.get(i).getProId() + "") + " ";
                /*item_codigo.setText(""+(i+1));
                item_cant.setText("2014-02-05");
                item_um.setText("S0"+(i+1));
                item_descp.setText(""+(20+(i+1)));
                item_pu.setText(""+(20+(i+1)));
                item_importe.setText();
*/
                item_codigo.setText(comprobanteVentaDetalles.get(i).getProId()+"");
                item_cant.setText(comprobanteVentaDetalles.get(i).getCantidad()+"");
                item_um.setText("S0"+(i+1));
                item_descp.setText(Producto.getNameProducto(comprobanteVentaDetalles.get(i).getProId()+""));
                item_pu.setText(Utils.formatDoublePrint(comprobanteVentaDetalles.get(i).getPrecioUnitario())+"");
            } else {

                costoUnidad = costoUnidad + Utils.formatDoublePrint(comprobanteVentaDetalles.get(i).getImporte()) + " \n";
                cantidadNombre = cantidadNombre + comprobanteVentaDetalles.get(i).getCantidad() + "   " + Producto.getNameProducto(comprobanteVentaDetalles.get(i).getProId() + "") + " \n";

                item_codigo.setText(comprobanteVentaDetalles.get(i).getProId());
                item_cant.setText(comprobanteVentaDetalles.get(i).getCantidad()+"");
                item_um.setText("S0"+(i+1));
                item_descp.setText(Producto.getNameProducto(comprobanteVentaDetalles.get(i).getProId()+""));
                item_pu.setText(Utils.formatDoublePrint(comprobanteVentaDetalles.get(i).getPrecioUnitario())+"");

            }

        }
        /*
        for (int i=0;i<5;i++){
            View tableRow = LayoutInflater.from(this).inflate(R.layout.item_facturas,null,false);
            TextView history_display_no  = (TextView) tableRow.findViewById(R.id.history_display_no);
            TextView history_display_date  = (TextView) tableRow.findViewById(R.id.history_display_date);
            TextView history_display_orderid  = (TextView) tableRow.findViewById(R.id.history_display_orderid);
            TextView history_display_quantity  = (TextView) tableRow.findViewById(R.id.history_display_quantity);

            history_display_no.setText(""+(i+1));
            history_display_date.setText("2014-02-05");
            history_display_orderid.setText("S0"+(i+1));
            history_display_quantity.setText(""+(20+(i+1)));
            tableLayoutFactura.addView(tableRow);
        }*/





        Double importeIgv = importeTotal * igv;

        Double importeTotalIgv = new Double(importeTotal + importeIgv);
        item_importe.setText(importeTotalIgv+"");
        String textImporte = String.format(res.getString(R.string.print_factura_items_importe2), Utils.formatDoublePrint(importeTotalIgv) + "", "0.00", "0.00", "0.00", "0.00", Utils.formatDoublePrint(importeIgv), Utils.formatDoublePrint(importeTotalIgv));
        textViewImprimirContenidoRight.setText(textImporte);
        tableLayoutFactura.addView(tableRow);
//        String textCNombre = String.format(res.getString(R.string.print_factura_items), cantidadNombre);
        String textCNombre = String.format(res.getString(R.string.print_factura_items2) + "");
        /*textViewCodigo.setText(idProducto);
        textViewCantidad.setText(CantidProducto);
        textViewUm.setText();*/

        textViewImprimirContenidoLeft.setText(textCNombre);
        String textTipoVenta = "VENTA AL CONTADO";
        if (comprobanteVenta.getPlanPago() != null) {
            textTipoVenta = "VENTA AL CREDITO";
        }
        String codigoVenta = beDocElectronico.getResumenFirma();
        Log.d("", codigoVenta);
        textViewImporeString.setText("SON:"+NumberToLetterConverter.convertNumberToLetter(importeTotalIgv));
        String comproFooter = String.format(res.getString(R.string.print_factura_footer), beDocElectronico.getResumenFirma(), textTipoVenta, usuario.getPersona().getPerVNombres() + " " + usuario.getPersona().getPerVApellidoPaterno(), codigoVenta, "http://www.energigas.com.pe");
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
                printDispatch.printTipoDoc(cliente, comprobanteVenta, usuario, beDocElectronico, datosEmpresa);
                floatingActionButton.setImageResource(R.drawable.ic_printer_sync_ok);
                floatingActionButton.startAnimation(rotate_backward);
                floatingActionButton.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(SellPrintActivity.this, R.color.greem_background_item)));
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
