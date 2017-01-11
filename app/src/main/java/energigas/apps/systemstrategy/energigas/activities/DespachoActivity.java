package energigas.apps.systemstrategy.energigas.activities;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import energigas.apps.systemstrategy.energigas.LocationVehiculeListener;
import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.asyntask.AtencionesAsyntask;
import energigas.apps.systemstrategy.energigas.asyntask.ConectarDispositivoAsyn;
import energigas.apps.systemstrategy.energigas.asyntask.ExportTask;
import energigas.apps.systemstrategy.energigas.entities.AccessFragment;
import energigas.apps.systemstrategy.energigas.entities.Almacen;
import energigas.apps.systemstrategy.energigas.entities.CajaLiquidacion;
import energigas.apps.systemstrategy.energigas.entities.CajaLiquidacionDetalle;
import energigas.apps.systemstrategy.energigas.entities.Concepto;
import energigas.apps.systemstrategy.energigas.entities.Despacho;
import energigas.apps.systemstrategy.energigas.entities.Establecimiento;
import energigas.apps.systemstrategy.energigas.entities.FBRegistroPedido;
import energigas.apps.systemstrategy.energigas.entities.NotificacionCajaDetalle;
import energigas.apps.systemstrategy.energigas.entities.Pedido;
import energigas.apps.systemstrategy.energigas.entities.PedidoDetalle;
import energigas.apps.systemstrategy.energigas.entities.Producto;
import energigas.apps.systemstrategy.energigas.entities.Serie;
import energigas.apps.systemstrategy.energigas.entities.SyncEstado;
import energigas.apps.systemstrategy.energigas.entities.Usuario;
import energigas.apps.systemstrategy.energigas.fragments.DialogGeneral;
import energigas.apps.systemstrategy.energigas.fragments.EstablecimientoFragment;
import energigas.apps.systemstrategy.energigas.fragments.PlanFragment;
import energigas.apps.systemstrategy.energigas.interfaces.BluetoothConnectionListener;
import energigas.apps.systemstrategy.energigas.interfaces.DialogGeneralListener;
import energigas.apps.systemstrategy.energigas.interfaces.ExportObjectsListener;
import energigas.apps.systemstrategy.energigas.interfaces.IntentListenerAccess;
import energigas.apps.systemstrategy.energigas.interfaces.OnLocationListener;
import energigas.apps.systemstrategy.energigas.utils.AccessPrivilegesManager;
import energigas.apps.systemstrategy.energigas.utils.Constants;
import energigas.apps.systemstrategy.energigas.utils.Session;
import energigas.apps.systemstrategy.energigas.utils.Utils;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.location.Location;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.orm.SugarTransactionHelper;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DespachoActivity extends AppCompatActivity implements BluetoothConnectionListener, SugarTransactionHelper.Callback, OnLocationListener, ExportObjectsListener, IntentListenerAccess {

    private Boolean isConnected = false;
    private Boolean isFabOpen = false;
    /**
     * --1--
     */
    @BindView(R.id.fabTanqueOrigen)
    FloatingActionButton actionButtonTanqueOrigen;
    @BindView(R.id.fabOrigenManual)
    FloatingActionButton actionButtonOrigenManual;
    @BindView(R.id.fabOrigenRemoto)
    FloatingActionButton actionButtonOrigenRemoto;
    @BindView(R.id.fabOrigenDisconect)
    FloatingActionButton actionButtonOrigenDesconectar;


    @BindView(R.id.fabTanqueDestino)
    FloatingActionButton actionButtonTanqueDestino;
    @BindView(R.id.fabDestinoManual)
    FloatingActionButton actionButtonDestinoManual;
    @BindView(R.id.fabDestinoRemoto)
    FloatingActionButton actionButtonDestinoRemoto;
    @BindView(R.id.fabDestinoDisconect)
    FloatingActionButton actionButtonDestinoDesconectar;

    @BindView(R.id.imgIniciarDespacho)
    ImageView imageViewInicarDespacho;
    @BindView(R.id.imgconnect)
    ImageView imageViewConnect;


    @BindView(R.id.progressdespacho)
    ProgressBar progressBarDespacho;
    /**
     * --3--
     */
    @BindView(R.id.fabTanqueOrigen2)
    FloatingActionButton actionButtonTanqueOrigen2;
    @BindView(R.id.fabOrigenManual2)
    FloatingActionButton fabOrigenManual2;
    @BindView(R.id.fabOrigenRemoto2)
    FloatingActionButton fabOrigenRemoto2;
    @BindView(R.id.fabOrigenDisconect2)
    FloatingActionButton fabOrigenDesconectar2;

    /***
     * 4
     **/

    @BindView(R.id.fabTanqueDestino2)
    FloatingActionButton fabTanqueDestino2;
    @BindView(R.id.fabDestinoManual2)
    FloatingActionButton fabDestinoManual2;
    @BindView(R.id.fabDestinoRemoto2)
    FloatingActionButton fabDestinoRemoto2;
    @BindView(R.id.fabDestinoDisconect2)
    FloatingActionButton fabDestinoDesconectar2;


    @BindView(R.id.toolbar)
    Toolbar toolbar;


    @BindView(R.id.editTextContadorInicial)
    EditText editTextContadorInicial;
    @BindView(R.id.editTextPorcentajeIncial)
    EditText editTextPorcentajeInicial;


    @BindView(R.id.editTextDestinoContIncial)
    EditText editTextDestinoContadorInicial;
    @BindView(R.id.editDestinoPorcentajeInicial)
    EditText editTextDestinoPorcentajeInicial;


    @BindView(R.id.editOrigen2CF)
    EditText editOrigen2CF;
    @BindView(R.id.editOrigen2PF)
    EditText editOrigen2PF;

    @BindView(R.id.editDestino2CF)
    EditText editDestino2CF;
    @BindView(R.id.editDestino2PF)
    EditText editDestino2PF;

    @BindView(R.id.editCantidadDespachada)
    EditText editTextCantidadDespachada;


    @BindView(R.id.text_despacho_serie_numero)
    TextView textViewSerieNumero;

    @BindView(R.id.textViewTanque)
    TextView textViewTanque;

    @BindView(R.id.textViewProducto)
    TextView textViewProducto;

    @BindView(R.id.text_despacho_estacion)
    TextView textDespachoEstacion;


    private int typeWidgets = 1;

    private Despacho despacho;
    private PedidoDetalle pedidoDetalle;
    private Establecimiento establecimiento;
    private Almacen almacen;
    private Usuario usuario;
    private Pedido pedido;
    private Serie serie;
    private CajaLiquidacion cajaLiquidacion;
    private CajaLiquidacionDetalle cajaLiquidacionDetalle;
    private Almacen almacenDistribuidor;


    @BindView(R.id.btnGuardar)
    Button buttonGuardar;
    private DatabaseReference mDatabase;
    private DatabaseReference myRef;


    private ProgressDialog progressDialog;

    /**
     * Animation
     **/
    private Animation fab_open, fab_close, rotate_forward, rotate_backward;
    private LocationVehiculeListener locationVehiculeListener;
    private Location latAndLong = null;


    private Concepto conceptoIGV;


    private static final String TAG = "DespachoActivity";


    @BindView(R.id.tanq_destino_capacidad)
    TextView textViewDestinoCapacidad;

    @BindView(R.id.tanq_destino_programado)
    TextView textViewDestinoProgramado;


    @BindView(R.id.tanq_destino_orden_sugerencia)
    TextView textViewOrdenSugerencia;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_despacho);
        ButterKnife.bind(this);
        usuario = Usuario.find(Usuario.class, " usu_I_Usuario_Id = ? ", new String[]{Session.getSession(this).getUsuIUsuarioId() + ""}).get(Constants.CURRENT);
        conceptoIGV = Session.getConceptoIGV();

        new AccessPrivilegesManager(getClass())
                .setViews(
                        actionButtonTanqueOrigen,
                        actionButtonOrigenManual,
                        actionButtonOrigenRemoto,
                        actionButtonOrigenDesconectar,
                        actionButtonTanqueDestino,
                        actionButtonDestinoManual,
                        actionButtonDestinoRemoto,
                        actionButtonDestinoDesconectar,
                        actionButtonTanqueOrigen2,
                        fabOrigenManual2,
                        fabOrigenRemoto2,
                        fabOrigenDesconectar2,
                        fabTanqueDestino2,
                        fabDestinoManual2,
                        fabDestinoRemoto2,
                        fabDestinoDesconectar2,
                        editTextCantidadDespachada,
                        buttonGuardar,
                        imageViewInicarDespacho,
                        imageViewConnect)
                .setListenerIntent(this)
                .setPrivilegesIsEnable(usuario.getUsuIUsuarioId() + "");


        progressDialog = new ProgressDialog(this);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Cargando...");
        mDatabase = FirebaseDatabase.getInstance().getReference();
        myRef = mDatabase.child(Constants.FIREBASE_CHILD_ATEN_PEDIDO);
        locationVehiculeListener = new LocationVehiculeListener(this, Constants.MIN_TIME_BW_UPDATES, new Long(0));
        cajaLiquidacion = CajaLiquidacion.find(CajaLiquidacion.class, " liq_Id = ? ", new String[]{Session.getCajaLiquidacion(this).getLiqId() + ""}).get(Constants.CURRENT);

        establecimiento = Establecimiento.find(Establecimiento.class, " est_I_Establecimiento_Id = ?  ", new String[]{Session.getSessionEstablecimiento(this).getEstIEstablecimientoId() + ""}).get(Constants.CURRENT);

        serie = Serie.findWithQuery(Serie.class, Utils.getQueryForSerie(usuario.getUsuIUsuarioId(), Constants.TIPO_ID_DEVICE_CELULAR, Constants.TIPO_ID_COMPROBANTE_DESPACHO), null).get(Constants.CURRENT);
        almacenDistribuidor = Almacen.findWithQuery(Almacen.class, Utils.getQuerDespachoVehiculo(usuario.getUsuIUsuarioId()), null).get(Constants.CURRENT);
        almacen = Almacen.find(Almacen.class, " alm_Id = ?  ", new String[]{Session.getAlmacen(this).getAlmId() + ""}).get(Constants.CURRENT);
        if (!Session.getTipoDespachoSN(this)) {

            pedido = Pedido.find(Pedido.class, " pe_Id = ? ", new String[]{Session.getPedido(this).getPeId() + ""}).get(Constants.CURRENT);
            pedidoDetalle = PedidoDetalle.find(PedidoDetalle.class, " pe_Id = ? ", new String[]{Session.getPedido(this).getPeId() + ""}).get(Constants.CURRENT);
            cajaLiquidacionDetalle = CajaLiquidacionDetalle.getLiquidacionDetalleByEstablecAndPedido(establecimiento.getEstIEstablecimientoId() + "", pedido.getPeId() + "");

        } else {
            pedido = Session.getPedido(this);
            pedidoDetalle = Session.getPedidoDetalle(this);
            cajaLiquidacionDetalle = new CajaLiquidacionDetalle();
        }
        Log.d(TAG, "POR IMPUESTO: " + conceptoIGV.getDescripcion());
        intanceAnimation();
        toolbar();
        setTextField();
        textCantidadValida();

    }

    private void textCantidadValida() {
        editTextCantidadDespachada.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count > 0) {
                    Double cantidad = Double.parseDouble(s.toString());
                    if (cantidad > almacen.getCapacidadReal()) {
                        editTextCantidadDespachada.setText("");
                        Toast.makeText(DespachoActivity.this, "La Cantidad supera lo programado", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    @Override
    public void onIntentListenerAcces(HashMap<String, Boolean> booleanHashMap) {

    }

    @Override
    public void onFragmentAccess(List<AccessFragment> accessFragmentList) {

    }

    private void notificarAtencionPedido() {

        myRef.child(cajaLiquidacion.getLiqId() + "-" + cajaLiquidacionDetalle.getLidId()).setValue(new NotificacionCajaDetalle(establecimiento.getEstIEstablecimientoId(),
                Constants.NO_FACTURADO, cajaLiquidacionDetalle.getEstadoId(), cajaLiquidacion.getFechaApertura(), Integer.parseInt(cajaLiquidacion.getLiqId() + ""), Integer.parseInt(cajaLiquidacionDetalle.getLidId() + ""),
                cajaLiquidacionDetalle.getOrdenAtencion(), Integer.parseInt(pedido.getId() + ""), cajaLiquidacionDetalle.getPorDespacho(), cajaLiquidacionDetalle.getPorEntrega(), cajaLiquidacionDetalle.getPorFacturado()));

        new SyncEstado(0, Utils.separteUpperCase(CajaLiquidacionDetalle.class.getSimpleName()), Integer.parseInt(cajaLiquidacionDetalle.getLidId() + ""), Constants.S_ACTUALIZADO).save();


        new AtencionesAsyntask().execute();
    }

    public int getOrden(Integer[] args) {
        int may = -10000;
        String cad = "";
        int Num[] = new int[args.length];
        for (int i = 0; i < args.length; i++) {
            Num[i] = args[i];
            if (Num[i] > may) {
                may = Num[i];
            }
            cad = cad + " " + Num[i];
        }
        System.out.println(cad);
        System.out.println("El mayor es:" + may);
        return may + 1;
    }

    private void updatLiqDetalle() {
        List<Despacho> despachoList = Despacho.getListDespachoByPedido(pedido.getPeId() + "");
        double sumDespachado = 0.0;
        for (Despacho despacho : despachoList) {
            sumDespachado = sumDespachado + despacho.getCantidadDespachada();
        }
        if (pedidoDetalle.getAlmId() == 0) {
            Integer[] integers = new Integer[CajaLiquidacionDetalle.listAll(CajaLiquidacionDetalle.class).size()];
            for (int i = 0; i < CajaLiquidacionDetalle.listAll(CajaLiquidacionDetalle.class).size(); i++) {
                integers[i] = CajaLiquidacionDetalle.listAll(CajaLiquidacionDetalle.class).get(i).getOrdenAtencion();
            }
            int ordenAtencion = getOrden(integers);
            if (cajaLiquidacionDetalle.getOrdenAtencion() != 0) {
                ordenAtencion = cajaLiquidacionDetalle.getOrdenAtencion() + 1;
            }

            double porEntrega = sumDespachado / pedidoDetalle.getCantidad();
            cajaLiquidacionDetalle.setPorDespacho(100);
            cajaLiquidacionDetalle.setPorEntrega(porEntrega * 100);
            cajaLiquidacionDetalle.setEstadoFactId(Constants.NO_FACTURADO);
            cajaLiquidacionDetalle.setPorFacturado(0.0);
            cajaLiquidacionDetalle.setEstadoId(42);
            cajaLiquidacionDetalle.setEstadoFacId(Constants.NO_FACTURADO);
            cajaLiquidacionDetalle.setOrdenAtencion(ordenAtencion);
            Log.d("AtencionesAsyntask", "Orden: " + cajaLiquidacionDetalle.getOrdenAtencion());
            cajaLiquidacionDetalle.save();
        } else {

            List<PedidoDetalle> pedidoDetalles = PedidoDetalle.getPedidoDetalleByPedido(pedido.getPeId() + "");
            double sumCanPedDetalle = 0.0;
            for (PedidoDetalle pedidoDetalle : pedidoDetalles) {
                sumCanPedDetalle = sumCanPedDetalle + pedidoDetalle.getCantidad();
            }

            double porDespacho = despachoList.size() / pedidoDetalles.size();
            double porEntrega = sumDespachado / sumCanPedDetalle;

            cajaLiquidacionDetalle.setPorDespacho(porDespacho);
            cajaLiquidacionDetalle.setPorEntrega(porEntrega);
            cajaLiquidacionDetalle.setEstadoFactId(Constants.NO_FACTURADO);
            cajaLiquidacionDetalle.setPorFacturado(0.0);
            cajaLiquidacionDetalle.setEstadoId(42);
            cajaLiquidacionDetalle.setEstadoFacId(Constants.NO_FACTURADO);
            cajaLiquidacionDetalle.save();
        }

        // establecimiento.setEstIEstadoId(Constants.ESTADO_ESTABLECIMIENTO_ATENDIDO);
        //  establecimiento.save();

        List<Establecimiento> establecimientos = Establecimiento.getListEstablecimiento(cajaLiquidacion.getLiqId() + "");
        Integer[] integers = new Integer[establecimientos.size()];
        for (int i = 0; i < establecimientos.size(); i++) {
            integers[i] = establecimientos.get(i).getOrdenAtencionAndroid();
        }
        int ordenAtencion = getOrden(integers);


        cajaLiquidacionDetalle.setOrdenAtencion(CajaLiquidacionDetalle.getOrdenAtencion(cajaLiquidacion.getLiqId() + ""));

        establecimiento.setOrdenAtencionAndroid(ordenAtencion);
        establecimiento.save();
        pedidoDetalle.setEstadoAtencionId(Constants.ESTADO_DESPACHO_ATENDIDO);
        pedidoDetalle.save();
    }


    private void toolbar() {

        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @OnClick(R.id.fabOrigenDisconect)
    public void origenDisconect(View view) {
        isConnected = false;
    }

    @OnClick(R.id.fabDestinoDisconect)
    public void fabDestinoDisconect(View view) {
        isConnected = false;
    }


    @OnClick(R.id.fabOrigenDisconect2)
    public void fabOrigenDisconect2(View view) {
        isConnected = false;
    }


    @OnClick(R.id.fabDestinoDisconect2)
    public void fabDestinoDisconect2(View view) {
        isConnected = false;
    }

    private void setTextField() {


        textViewDestinoCapacidad.setText(Utils.formatDoublePrint(almacen.getCapacidadReal()) + "");
        textViewDestinoProgramado.setText(Utils.formatDoublePrint(pedidoDetalle.getCantidad()) + "");
        textViewOrdenSugerencia.setText(": " + Utils.formatDoublePrint(getCapacidadSugerencia()) + "");

        textViewSerieNumero.setText(": " + serie.getCompVSerie() + "-" + Utils.completaZeros(getNumeroDespacho(), serie.getParametro()));
        textViewTanque.setText(almacen.getPlaca());
        textViewProducto.setText(Producto.getNameProducto(almacen.getProductoId() + ""));
        textDespachoEstacion.setText(establecimiento.getEstVDescripcion());

    }

    private double getCapacidadSugerencia() {
        double d = almacen.getCapacidadReal() - almacen.getStockPermanente();
        return d;
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

        new DialogGeneral(DespachoActivity.this).setCancelable(false).setMessages("Retroceder", "¿Seguro de retroceder?").setTextButtons("SI", "NO").showDialog(new DialogGeneralListener() {
            @Override
            public void onSavePressed(AlertDialog alertDialog) {
                DespachoActivity.super.onBackPressed();
                alertDialog.dismiss();
                locationVehiculeListener.stopLocationUpdates();
            }

            @Override
            public void onCancelPressed(AlertDialog alertDialog) {
                alertDialog.dismiss();
            }
        });


    }

    @OnClick(R.id.btnGuardar)
    public void clickBtnGuardar() {

        if (!validateField()) {
            return;
        }

        new DialogGeneral(DespachoActivity.this).setTextButtons("GUARDAR", "CANCELAR").setMessages("Atencion", "¿Seguro de guardar?").setCancelable(true).showDialog(new DialogGeneralListener() {
            @Override
            public void onSavePressed(AlertDialog alertDialog) {
                if (validateField()) {
                    SugarTransactionHelper.doInTransaction(DespachoActivity.this);

                    // new ExportTask(DespachoActivity.this, DespachoActivity.this).execute(Constants.TABLA_DESPACHO, Constants.S_CREADO);
                } else {
                    Toast.makeText(DespachoActivity.this, "Datos vacios", Toast.LENGTH_SHORT).show();
                }
                alertDialog.dismiss();

            }

            @Override
            public void onCancelPressed(AlertDialog alertDialog) {
                alertDialog.dismiss();
            }
        });

    }

    private boolean validateField() {

        if (TextUtils.isEmpty(editTextCantidadDespachada.getText().toString())) {
            Toast.makeText(this, "Ingrese cantidad", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (Double.parseDouble(editTextCantidadDespachada.getText().toString()) < 1.00) {
            Toast.makeText(this, "Ingrese cantidad", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (latAndLong == null) {
            Toast.makeText(this, "Ubicacion desconoida, por favor intente nuevamente", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (TextUtils.isEmpty(editTextCantidadDespachada.getText().toString()) ||
                TextUtils.isEmpty(editTextContadorInicial.getText().toString()) ||
                TextUtils.isEmpty(editTextPorcentajeInicial.getText().toString()) ||
                TextUtils.isEmpty(editTextDestinoContadorInicial.getText().toString()) ||
                TextUtils.isEmpty(editTextDestinoPorcentajeInicial.getText().toString()) ||
                TextUtils.isEmpty(editOrigen2CF.getText().toString()) ||
                TextUtils.isEmpty(editOrigen2PF.getText().toString()) ||
                TextUtils.isEmpty(editDestino2CF.getText().toString()) ||
                TextUtils.isEmpty(editDestino2PF.getText().toString())) {
            Toast.makeText(this, "Por favor obtenga los datos de los tanques", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private String getNumeroDespacho() {
        String numeroDespacho = Despacho.findWithQuery(Despacho.class, Utils.getQueryForNumberDistPach(), null).get(Constants.CURRENT).getNumero();
        return numeroDespacho;
    }

    private void saveDespacho() {

        if (latAndLong == null) {
            errorInTransaction("Update no Location");
            return;
        }

        almacenDistribuidor = Almacen.find(Almacen.class, "", new String[]{}).get(Constants.CURRENT);


        despacho = new Despacho(
                0,
                pedido.getPeId(),
                pedidoDetalle.getPdId(),
                pedido.getClienteId(),
                establecimiento.getEstIEstablecimientoId(),
                almacen.getAlmId(),
                usuario.getUsuIUsuarioId(),
                almacen.getPlaca(),
                Double.parseDouble(editTextContadorInicial.getText().toString()),
                Double.parseDouble(editOrigen2CF.getText().toString()),
                Double.parseDouble(editTextCantidadDespachada.getText().toString()),
                "17:35",
                "18:00",
                Utils.getDatePhone(),
                pedidoDetalle.getProductoId(),
                pedidoDetalle.getUnidadId(),
                Double.parseDouble(editTextPorcentajeInicial.getText().toString()),
                Double.parseDouble(editOrigen2PF.getText().toString()),
                latAndLong.getLatitude() + "",
                latAndLong.getLongitude() + "",
                almacenDistribuidor.getAlmId(),
                serie.getCompVSerie(),
                Utils.completaZeros(getNumeroDespacho(), serie.getParametro()),
                Utils.getDatePhoneWithTime(),
                usuario.getUsuIUsuarioId(),
                Constants.DESPACHO_CREADO,
                almacenDistribuidor.getVehiculoId(),
                pedido.getGuiaRemision(),
                cajaLiquidacion.getLiqId(),
                pedidoDetalle.getPrecio(),
                pedidoDetalle.getPrecioUnitario(),
                Double.parseDouble(conceptoIGV.getDescripcion()),
                pedidoDetalle.getCostoVenta(),
                Double.parseDouble(editTextCantidadDespachada.getText().toString()) * pedidoDetalle.getPrecio(),
                Double.parseDouble(editTextDestinoContadorInicial.getText().toString()),
                Double.parseDouble(editTextPorcentajeInicial.getText().toString()),
                Double.parseDouble(editTextDestinoPorcentajeInicial.getText().toString()),
                Double.parseDouble(editDestino2PF.getText().toString()),
                -1
        );


        Long despachoId = despacho.save();
        despacho.setDespachoId(despachoId);
        despacho.save();


        double cantidadStock = almacenDistribuidor.getStockActual() - Double.parseDouble(editTextCantidadDespachada.getText().toString());
        almacenDistribuidor.setStockActual(cantidadStock);
        almacenDistribuidor.save();
        updatLiqDetalle();
        notificarAtencionPedido();
        Session.saveDespacho(this, despacho);
        new SyncEstado(0, Utils.separteUpperCase(Despacho.class.getSimpleName()), Integer.parseInt(despacho.getId() + ""), Constants.S_CREADO).save();


    }

    private void isEnableEditText(List<EditText> editTexts, boolean estado) {

        for (EditText editText : editTexts) {
            editText.setEnabled(estado);
        }
    }

    private void intanceAnimation() {

        fab_open = AnimationUtils.loadAnimation(this, R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(this, R.anim.fab_close);
        rotate_backward = AnimationUtils.loadAnimation(this, R.anim.rotate_backward);
        rotate_forward = AnimationUtils.loadAnimation(this, R.anim.rotate_forward);
    }

    private void setClickableOrigen(FloatingActionButton[] fabss, boolean estado) {
        for (int i = 0; i < fabss.length; i++) {
            fabss[i].setEnabled(estado);
        }
    }

    /**
     * Primera Lectura
     **/

    @OnClick(R.id.fabTanqueOrigen)
    public void clickTanqueOrigen(View view) {

        actionButtonTanqueOrigen.setImageResource(R.drawable.ic_sync);
        actionButtonTanqueOrigen.startAnimation(rotate_forward);
        if (!isConnected) {
            typeWidgets = 1;
            comenzarConexion();
        } else {
            setAnimationOrigen(actionButtonTanqueOrigen, actionButtonOrigenManual, actionButtonOrigenRemoto, actionButtonOrigenDesconectar);
        }


    }

    @OnClick(R.id.fabOrigenManual)
    public void clickTanqueOrigenManual(View view) {
        List<EditText> editTexts = new ArrayList<EditText>();
        editTexts.add(editTextContadorInicial);
        editTexts.add(editTextPorcentajeInicial);
        isEnableEditText(editTexts, true);
    }

    @OnClick(R.id.fabOrigenRemoto)
    public void clickTanqueOrigenRemoto(View view) {
        List<EditText> editTexts = new ArrayList<EditText>();
        editTexts.add(editTextContadorInicial);
        editTexts.add(editTextPorcentajeInicial);
        isEnableEditText(editTexts, false);
        setValueTanqueOrigen(editTexts, "3000", "100");
    }
    /** ----end  ---- primera lectura**/


    /**
     * Segunda Lectura
     **/

    @OnClick(R.id.fabTanqueDestino)
    public void clickTanqueDestino(View view) {


        actionButtonTanqueDestino.setImageResource(R.drawable.ic_sync);
        actionButtonTanqueDestino.startAnimation(rotate_forward);
        if (!isConnected) {
            typeWidgets = 2;
            comenzarConexion();
        } else {
            setAnimationOrigen(actionButtonTanqueDestino, actionButtonDestinoManual, actionButtonDestinoRemoto, actionButtonDestinoDesconectar);
        }

    }

    @OnClick(R.id.fabDestinoManual)
    public void clickTanqueDestinoManual(View view) {
        List<EditText> editTexts = new ArrayList<EditText>();
        editTexts.add(editTextDestinoContadorInicial);
        editTexts.add(editTextDestinoPorcentajeInicial);
        isEnableEditText(editTexts, true);
    }

    @OnClick(R.id.fabDestinoRemoto)
    public void clickTanqueDestinoRemoto(View view) {
        List<EditText> editTexts = new ArrayList<EditText>();
        editTexts.add(editTextDestinoContadorInicial);
        editTexts.add(editTextDestinoPorcentajeInicial);
        isEnableEditText(editTexts, false);
        setValueTanqueOrigen(editTexts, "1000", "50");
    }


    /**
     * ----end  ---- Segunda lectura
     **/


    /**
     * Tercera Lectura
     **/

    @OnClick(R.id.fabTanqueOrigen2)
    public void clickTanqueOrigen2(View view) {


        actionButtonTanqueOrigen2.setImageResource(R.drawable.ic_sync);
        actionButtonTanqueOrigen2.startAnimation(rotate_forward);
        if (!isConnected) {
            typeWidgets = 3;
            comenzarConexion();
        } else {
            setAnimationOrigen(actionButtonTanqueOrigen2, fabOrigenManual2, fabOrigenRemoto2, fabOrigenDesconectar2);
        }

    }

    @OnClick(R.id.fabOrigenManual2)
    public void clickTanqueOrigenManual2(View view) {
        List<EditText> editTexts = new ArrayList<EditText>();
        editTexts.add(editOrigen2CF);
        editTexts.add(editOrigen2PF);
        isEnableEditText(editTexts, true);
    }

    @OnClick(R.id.fabOrigenRemoto2)
    public void clickTanqueOrigenRemoto2(View view) {
        List<EditText> editTexts = new ArrayList<EditText>();
        editTexts.add(editOrigen2CF);
        editTexts.add(editOrigen2PF);
        isEnableEditText(editTexts, false);
        setValueTanqueOrigen(editTexts, "2000", "60");
    }


    /**
     * ----end  ---- Tercera lectura
     **/

    /**
     * Cuarta Lectura
     **/

    @OnClick(R.id.fabTanqueDestino2)
    public void clickTanqueDestino2(View view) {


        fabTanqueDestino2.setImageResource(R.drawable.ic_sync);
        fabTanqueDestino2.startAnimation(rotate_forward);
        if (!isConnected) {
            typeWidgets = 4;
            comenzarConexion();
        } else {
            setAnimationOrigen(fabTanqueDestino2, fabDestinoManual2, fabDestinoRemoto2, fabDestinoDesconectar2);
        }

    }

    @OnClick(R.id.fabDestinoManual2)
    public void clickTanqueDestinoManual2(View view) {
        List<EditText> editTexts = new ArrayList<EditText>();
        editTexts.add(editDestino2CF);
        editTexts.add(editDestino2PF);
        isEnableEditText(editTexts, true);
    }

    @OnClick(R.id.fabDestinoRemoto2)
    public void clickTanqueDestinoRemoto2(View view) {
        List<EditText> editTexts = new ArrayList<EditText>();
        editTexts.add(editDestino2CF);
        editTexts.add(editDestino2PF);
        isEnableEditText(editTexts, false);
        setValueTanqueOrigen(editTexts, "3000", "100");
    }


    /**
     * ----end  ---- Segunda lectura
     **/


    private void setValueTanqueOrigen(List<EditText> editexts, String contadorInicial, String porcentajeInicial) {
        editexts.get(0).setText(contadorInicial);
        editexts.get(1).setText(porcentajeInicial);
    }


    private void setAnimationOrigen(FloatingActionButton tanque, FloatingActionButton manual, FloatingActionButton remoto, FloatingActionButton desconectar) {

        FloatingActionButton[] fabs = new FloatingActionButton[]{manual, remoto, desconectar};

        if (isConnected) {


            if (isFabOpen) {

                manual.startAnimation(fab_close);
                remoto.startAnimation(fab_close);
                desconectar.startAnimation(fab_close);
                isFabOpen = false;
                setClickableOrigen(fabs, false);
                affterAnimationConnected(tanque);
            } else {

                manual.startAnimation(fab_open);
                remoto.startAnimation(fab_open);
                desconectar.startAnimation(fab_open);
                isFabOpen = true;
                setClickableOrigen(fabs, true);
                affterAnimationConnected(tanque);
            }


        } else {
            affterAnimationError(tanque);


        }

    }

    private void comenzarConexion() {
        new ConectarDispositivoAsyn(this).execute();
    }

    private void affterAnimationError(FloatingActionButton tanque) {

        tanque.setImageResource(R.drawable.ic_wireless_signal);
        tanque.startAnimation(rotate_backward);
        tanque.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.colorAccent)));
    }

    private void affterAnimationConnected(FloatingActionButton tanque) {

        tanque.setImageResource(R.drawable.ic_wireless_signal);
        tanque.startAnimation(rotate_backward);
        tanque.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.greem_background_item)));
    }


    @Override
    public void onConnected(boolean connected) {
        isConnected = connected;


        switch (typeWidgets) {
            case 1:
                setAnimationOrigen(actionButtonTanqueOrigen, actionButtonOrigenManual, actionButtonOrigenRemoto, actionButtonOrigenDesconectar);
                break;
            case 2:
                setAnimationOrigen(actionButtonTanqueDestino, actionButtonDestinoManual, actionButtonDestinoRemoto, actionButtonDestinoDesconectar);
                break;
            case 3:
                setAnimationOrigen(actionButtonTanqueOrigen2, fabOrigenManual2, fabOrigenRemoto2, fabOrigenDesconectar2);
                break;
            case 4:
                setAnimationOrigen(fabTanqueDestino2, fabDestinoManual2, fabDestinoRemoto2, fabDestinoDesconectar2);
                break;
        }

    }

    @Override
    public void onRead(byte[] bytes) {

    }

    @Override
    public void manipulateInTransaction() {
        saveDespacho();

        progressDialog.dismiss();
        startActivity(new Intent(this, PrintDispatch.class));
        this.finish();
        locationVehiculeListener.stopLocationUpdates();
    }


    @Override
    public void errorInTransaction(String error) {
        Toast.makeText(DespachoActivity.this, error, Toast.LENGTH_SHORT).show();
        progressDialog.dismiss();
        locationVehiculeListener.stopLocationUpdates();
    }

    @Override
    public void setLatAndLong(Location latAndLong) {
        this.latAndLong = latAndLong;
    }

    @Override
    public Context getContextActivity() {
        return DespachoActivity.this;
    }


    @Override
    public void onLoadSuccess(String message) {
        // Toast.makeText(DespachoActivity.this,message,Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onLoadError(String message) {
        // Toast.makeText(DespachoActivity.this,message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoadErrorProcedure(String message) {
        // Toast.makeText(DespachoActivity.this,message,Toast.LENGTH_SHORT).show();
    }


}
