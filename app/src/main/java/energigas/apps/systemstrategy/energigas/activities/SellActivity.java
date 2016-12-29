package energigas.apps.systemstrategy.energigas.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.orm.SugarRecord;
import com.orm.SugarTransactionHelper;


import java.io.IOException;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.adapters.ConceptoAdapter;
import energigas.apps.systemstrategy.energigas.adapters.DespachoFacturaAdapter;
import energigas.apps.systemstrategy.energigas.apiRest.ManipuleData;
import energigas.apps.systemstrategy.energigas.asyntask.AtencionesAsyntask;
import energigas.apps.systemstrategy.energigas.entities.Almacen;

import energigas.apps.systemstrategy.energigas.entities.CajaComprobante;
import energigas.apps.systemstrategy.energigas.entities.CajaLiquidacion;
import energigas.apps.systemstrategy.energigas.entities.CajaLiquidacionDetalle;
import energigas.apps.systemstrategy.energigas.entities.CajaMovimiento;
import energigas.apps.systemstrategy.energigas.entities.CajaPago;
import energigas.apps.systemstrategy.energigas.entities.Cliente;
import energigas.apps.systemstrategy.energigas.entities.ComprobanteVenta;
import energigas.apps.systemstrategy.energigas.entities.ComprobanteVentaDetalle;
import energigas.apps.systemstrategy.energigas.entities.Concepto;
import energigas.apps.systemstrategy.energigas.entities.DEEntidad;
import energigas.apps.systemstrategy.energigas.entities.DETipo;
import energigas.apps.systemstrategy.energigas.entities.Despacho;
import energigas.apps.systemstrategy.energigas.entities.Establecimiento;
import energigas.apps.systemstrategy.energigas.entities.FBRegistroPedido;
import energigas.apps.systemstrategy.energigas.entities.GeoUbicacion;
import energigas.apps.systemstrategy.energigas.entities.NotificacionCajaDetalle;
import energigas.apps.systemstrategy.energigas.entities.Pedido;
import energigas.apps.systemstrategy.energigas.entities.PedidoDetalle;
import energigas.apps.systemstrategy.energigas.entities.Persona;
import energigas.apps.systemstrategy.energigas.entities.PlanPago;
import energigas.apps.systemstrategy.energigas.entities.PlanPagoDetalle;
import energigas.apps.systemstrategy.energigas.entities.Producto;
import energigas.apps.systemstrategy.energigas.entities.ProductoUnidad;
import energigas.apps.systemstrategy.energigas.entities.Serie;
import energigas.apps.systemstrategy.energigas.entities.SyncEstado;
import energigas.apps.systemstrategy.energigas.entities.UbicacionGeoreferencia;
import energigas.apps.systemstrategy.energigas.entities.Usuario;
import energigas.apps.systemstrategy.energigas.entities.Vehiculo;
import energigas.apps.systemstrategy.energigas.entities.BeDocElectronico;
import energigas.apps.systemstrategy.energigas.entities.BeDocElectronicoDetalle;
import energigas.apps.systemstrategy.energigas.entities.fe.CertificadoDigital;
import energigas.apps.systemstrategy.energigas.entities.fe.Contribuyente;
import energigas.apps.systemstrategy.energigas.entities.fe.DetalleDocumento;
import energigas.apps.systemstrategy.energigas.entities.fe.DocumentoElectronico;
import energigas.apps.systemstrategy.energigas.entities.fe.FirmadoResponse;
import energigas.apps.systemstrategy.energigas.fragments.DialogGeneral;
import energigas.apps.systemstrategy.energigas.interfaces.DialogGeneralListener;
import energigas.apps.systemstrategy.energigas.interfaces.ExportObjectsListener;
import energigas.apps.systemstrategy.energigas.utils.AccessPrivilegesManager;
import energigas.apps.systemstrategy.energigas.utils.Constants;
import energigas.apps.systemstrategy.energigas.utils.NumberToLetterConverter;
import energigas.apps.systemstrategy.energigas.utils.Session;
import energigas.apps.systemstrategy.energigas.utils.Utils;

public class SellActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener, SugarTransactionHelper.Callback, ExportObjectsListener {
    private static final String TAG = "SellActivity";

    private static final int GENERAR_XML_REQUEST_CODE = 25;

    @BindView(R.id.spinnerTipoComprobante)
    Spinner spinnerTipoComprobante;

    @BindView(R.id.spinnerFormaPago)
    Spinner spinnerFormaPago;

    @BindView(R.id.spinnerTipoPago)
    Spinner spinnerTipoPago;

    @BindView(R.id.fab)
    FloatingActionButton fab;

    @BindView(R.id.recycler_despacho)
    RecyclerView recyclerView;
    @BindView(R.id.btnVender)
    Button buttonVender;
    @BindView(R.id.direccion)
    RadioButton radioDireccion;
    @BindView(R.id.direccionFiscal)
    RadioButton radioDireccionFical;
    @BindView(R.id.groupRadio)
    RadioGroup radioGroup;
    @BindView(R.id.editDireccion)
    EditText editTextDireccion;
    @BindView(R.id.checkboxResumen)
    CheckBox checkBoxResumen;
    @BindView(R.id.editScop)
    EditText editTextScop;


    @BindView(R.id.textTotal)
    TextView textTotal;
    @BindView(R.id.textBaseImponible)
    TextView textBaseImponible;
    @BindView(R.id.textIGV)
    TextView textIGV;
    @BindView(R.id.btnDefinirCuotas)
    Button buttonCuotas;

    Cliente cliente;
    Establecimiento establecimiento;

    /**
     * Entidades para generar la venta
     **/
    ComprobanteVenta comprobanteVenta;
    List<ComprobanteVentaDetalle> comprobanteVentaDetalles;
    Usuario usuario;
    CajaLiquidacion cajaLiquidacion;
    Serie serie;
    Pedido pedido;
    PlanPago planPago;
    List<PlanPagoDetalle> planPagoDetalles;
    CajaMovimiento cajaMovimiento;

    //--
    CajaComprobante cajaComprobante;
    CajaPago cajaPago;
    private String descorasc;
    private DocumentoElectronico documentoElectronico;
    private CertificadoDigital certificadoDigital;
    private Almacen almacenDistribuidor;
    private Vehiculo vehiculo;
    private String placaDocElectronico;
    private String nombreDatosCliente;
    private CajaLiquidacionDetalle cajaLiquidacionDetalle;

    /**
     * Obtener Objectos
     **/
    private BeDocElectronico beDocElectronico;
    Concepto conceptoCredito;

    private ProgressDialog progressDialog;
    private DatabaseReference mDatabase;
    private DatabaseReference myRef;
    /*Accesos*/

    private HashMap<String, Boolean> booleanHashMap;


    @BindView(R.id.text_despacho_serie_numero)
    TextView textViewSerieNumero;

    @BindView(R.id.textViewTanque)
    TextView textViewTanque;

    @BindView(R.id.textViewProducto)
    TextView textViewProducto;

    @BindView(R.id.text_despacho_estacion)
    TextView textDespachoEstacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell);
        progressDialog = new ProgressDialog(this);
        progressDialog.setIndeterminate(true);
        progressDialog.setTitle("Realizar Venta");

        Toast.makeText(this, "" + Session.getCajaLiquidacion(this).getLiqId() + "", Toast.LENGTH_SHORT).show();
        if (!Session.getTipoDespachoSN(this)) {
            pedido = Pedido.find(Pedido.class, "pe_Id=?", new String[]{Session.getPedido(this).getPeId() + ""}).get(Constants.CURRENT);
        } else {
            pedido = Session.getPedido(this);
        }

        beDocElectronico = new BeDocElectronico();
        usuario = Usuario.find(Usuario.class, " usu_I_Usuario_Id = ? ", new String[]{Session.getSession(this).getUsuIUsuarioId() + ""}).get(Constants.CURRENT);

        establecimiento = Establecimiento.find(Establecimiento.class, " est_I_Establecimiento_Id = ?  ", new String[]{Session.getSessionEstablecimiento(this).getEstIEstablecimientoId() + ""}).get(Constants.CURRENT);
        establecimiento.setUbicacion(GeoUbicacion.find(GeoUbicacion.class, "ub_Id = ?", new String[]{establecimiento.getUbId() + ""}).get(Constants.CURRENT));
        cliente = Cliente.find(Cliente.class, " CLI_I_CLIENTE_ID = ? ", new String[]{establecimiento.getEstIClienteId() + ""}).get(Constants.CURRENT);
        Persona persona = Persona.find(Persona.class, " per_I_Persona_Id=? ", new String[]{cliente.getCliIPersonaId() + ""}).get(Constants.CURRENT);
        cliente.setPersona(persona);
        cliente.getPersona().setUbicacion(GeoUbicacion.find(GeoUbicacion.class, " persona_Id=? ", new String[]{persona.getPerIPersonaId() + ""}).get(Constants.CURRENT));
        if (cliente.getPersona().getPerITipoPersonaId() == 42)//
        {
            nombreDatosCliente = cliente.getPersona().getPerVNombres() + ", " + cliente.getPersona().getPerVApellidoPaterno() + " " + cliente.getPersona().getPerVApellidoMaterno();

        } else {
            nombreDatosCliente = cliente.getPersona().getPerVRazonSocial();

        }

        cajaLiquidacion = CajaLiquidacion.find(CajaLiquidacion.class, " liq_Id=? ", new String[]{Session.getCajaLiquidacion(this).getLiqId() + ""}).get(Constants.CURRENT);
        cajaLiquidacionDetalle = CajaLiquidacionDetalle.getLiquidacionDetalleByEstablecAndPedido(establecimiento.getEstIEstablecimientoId() + "", pedido.getPeId() + "");

        almacenDistribuidor = Almacen.findWithQuery(Almacen.class, Utils.getQuerDespachoVehiculo(usuario.getUsuIUsuarioId()), null).get(Constants.CURRENT);
        vehiculo = Vehiculo.getVehiculo(usuario.getUsuIUsuarioId() + "");

        if (vehiculo.getClaseId() == 55) {
            placaDocElectronico = vehiculo.getPlaca();
        } else {
            placaDocElectronico = almacenDistribuidor.getPlaca();
        }


        cajaLiquidacion = CajaLiquidacion.getCajaLiquidacion(Session.getCajaLiquidacion(this).getLiqId() + "");

        Session.setDefineCuotas(this, Constants.ESTADO_FALSE, "");
        ButterKnife.bind(this);

        setTitle("Generar comprobante");
        initViews();

        usuario = Usuario.find(Usuario.class, " usu_I_Usuario_Id = ? ", new String[]{Session.getSession(this).getUsuIUsuarioId() + ""}).get(Constants.CURRENT);
        new AccessPrivilegesManager(getClass())
                .setViews(buttonVender, buttonCuotas)
                .setPrivilegesIsEnable(usuario.getUsuIUsuarioId() + "");


        fab.setOnClickListener(this);
        buttonVender.setOnClickListener(this);
        hideLinear(getFormaPago());
        mDatabase = FirebaseDatabase.getInstance().getReference();
        myRef = mDatabase.child(Constants.FIREBASE_CHILD_ATEN_PEDIDO);
        serie = Serie.findWithQuery(Serie.class, Utils.getQueryForSerie(usuario.getUsuIUsuarioId(), Constants.TIPO_ID_DEVICE_CELULAR, getTipoComprobante().getIdConcepto()), null).get(Constants.CURRENT);

        setTextDescripcion();

    }

    private void setTextDescripcion() {
        PedidoDetalle pedidoDetalle = PedidoDetalle.getPedidoDetalleByPedido(pedido.getPeId() + "").get(0);
        textViewSerieNumero.setText(": " + serie.getCompVSerie() + "-" + Utils.completaZeros(getNumeroComprobante(), serie.getParametro()));
        textViewProducto.setText(Producto.getNameProducto(pedidoDetalle.getProductoId() + ""));
        textDespachoEstacion.setText(establecimiento.getEstVDescripcion());
    }


    private void notificarAtencionPedido() {


        new SyncEstado(0, Utils.separteUpperCase(CajaLiquidacionDetalle.class.getSimpleName()), Integer.parseInt(cajaLiquidacionDetalle.getLidId() + ""), Constants.S_ACTUALIZADO).save();
        new AtencionesAsyntask().execute();

        myRef.child(cajaLiquidacion.getLiqId() + "-" + cajaLiquidacionDetalle.getLidId()).setValue(new NotificacionCajaDetalle(establecimiento.getEstIEstablecimientoId(),
                cajaLiquidacionDetalle.getEstadoFacId(), cajaLiquidacionDetalle.getEstadoId(), Utils.getDatePhone(),
                Integer.parseInt(cajaLiquidacion.getLiqId() + ""),
                Integer.parseInt(cajaLiquidacionDetalle.getLidId() + ""),
                cajaLiquidacionDetalle.getOrdenAtencion(),
                Integer.parseInt(pedido.getPeId() + ""),
                cajaLiquidacionDetalle.getPorDespacho(),
                cajaLiquidacionDetalle.getPorEntrega(),
                cajaLiquidacionDetalle.getPorFacturado()));
    }


    private void updatLiqDetalle() {
        List<Despacho> despachoList = Despacho.getListDespachoByPedidoNoCompIdEstablec(establecimiento.getEstIEstablecimientoId() + "");
        List<Despacho> despachoListFacturado = Despacho.getListDespachoByPedidoNoCompIdFacturado(establecimiento.getEstIEstablecimientoId() + "");
        Log.d(TAG, "TAG :  " + despachoList.size() + "-" + despachoListFacturado.size());

        if (comprobanteVentaDetalles.size() > 0) {
            int estadoFacturado = Constants.FACTURADO;
            Double porcentaje = new Double(despachoList.size() / despachoListFacturado.size()) * 100;
            if (porcentaje == 100.00) {
                estadoFacturado = Constants.FACTURADO_TOTAL;
            }

            cajaLiquidacionDetalle.setEstadoFacId(estadoFacturado);
            cajaLiquidacionDetalle.setPorFacturado(porcentaje);


            cajaLiquidacionDetalle.save();
        }

        notificarAtencionPedido();
    }


    private void dialogConfirmarVenta() {

        new DialogGeneral(SellActivity.this).setTextButtons("GUARDAR", "CANCELAR").setMessages("Atencion", "¿Esta seguro de guardar?").setCancelable(true).showDialog(new DialogGeneralListener() {
            @Override
            public void onSavePressed(AlertDialog alertDialog) {
                alertDialog.dismiss();
                if (Session.getDefineCuotas(getApplicationContext())) {

                    if (cliente.getCliDOCreditoDisponible() > obtenerCalculos()[Constants.VENTA_TOTAL]) {

                        SugarTransactionHelper.doInTransaction(SellActivity.this);
                        progressDialog.show();
                        documentoElectronico = getDocumentoEletronico();

                        Log.d(TAG, "OBJECTO COMPROBANTE: " + documentoElectronico.getMoneda());
                        //intentPrint();
                        generarDocumentoElectronico();

                    } else {
                        Toast.makeText(SellActivity.this, "No Cuenta con credito", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    SugarTransactionHelper.doInTransaction(SellActivity.this);
                    progressDialog.show();
                    documentoElectronico = getDocumentoEletronico();
                    System.out.println("OBJECTO COMPROBANTE");
                    System.out.print(documentoElectronico);
                    //intentPrint();
                    generarDocumentoElectronico();
                }


            }

            @Override
            public void onCancelPressed(AlertDialog alertDialog) {
                alertDialog.dismiss();
            }
        });

    }

    private void generarDocumentoElectronico() {

        certificadoDigital = getCertificadoDigital();
        Log.d("SellActivity", documentoElectronico.getIdDocumento() + "-" + certificadoDigital.getPassword());
        if (documentoElectronico != null && certificadoDigital != null) {

            ObjectMapper objectMapper = new ObjectMapper();
            StringWriter stringWriter = new StringWriter();


            ObjectMapper objectMappercer = new ObjectMapper();
            StringWriter stringWritercer = new StringWriter();

            String jsonDocumentoElectronico = "";
            String jsonCertificado = "";
            try {

                objectMapper.writeValue(stringWriter, documentoElectronico);
                jsonDocumentoElectronico = stringWriter.toString();


                objectMappercer.writeValue(stringWritercer, certificadoDigital);
                jsonCertificado = stringWritercer.toString();

                Log.d(TAG, "DOC: " + jsonDocumentoElectronico);
                Log.d(TAG, "CERT: " + jsonCertificado);


                Intent intent = new Intent();
                intent.setAction("fe.apps.systemstrategy.GENERAR_XML");
                intent.setType("text/plain");
                intent.putExtra("doc", jsonDocumentoElectronico);
                intent.putExtra("cert", jsonCertificado);


                // Verify it resolves
                PackageManager packageManager = getPackageManager();
                List<ResolveInfo> activities = packageManager.queryIntentActivities(intent, 0);
                boolean isIntentSafe = activities.size() > 0;
                Log.d(TAG, "Existe App: " + isIntentSafe + "");
                // Start an activity if it's safe
                if (isIntentSafe) {
                    startActivityForResult(intent, GENERAR_XML_REQUEST_CODE);
                } else {
                    //Compose a message, you need to install "Facturacion app".

                    Toast.makeText(this, "NO INSTALADA", Toast.LENGTH_SHORT).show();
                }


            } catch (IOException e) {

                e.printStackTrace();
                return;
            }


        }

    }

    @Override
    protected void onPause() {
        progressDialog.dismiss();
        super.onPause();
    }

    private void saveVenta() {

        generarVenta();
        guardarVenta();


    }

    private void intentPrint() {
        startActivity(new Intent(getApplicationContext(), SellPrintActivity.class));
        this.finish();
    }

    private String getNumeroComprobante() {
        String numero = ComprobanteVenta.findWithQuery(ComprobanteVenta.class, Utils.getQueryNumberSell(), null).get(Constants.CURRENT).getNumDoc();
        return numero;
    }

    private void generarVenta() {


        String numero = ComprobanteVenta.findWithQuery(ComprobanteVenta.class, Utils.getQueryNumberSell(), null).get(Constants.CURRENT).getNumDoc();
        int compId = Integer.parseInt(numero);

        /**Comprobante de venta**/
        comprobanteVenta = new ComprobanteVenta();
        comprobanteVenta.setCompId(compId);
        comprobanteVenta.setSerie(serie.getCompVSerie());
        comprobanteVenta.setNumDoc(Utils.completaZeros(numero, serie.getParametro()));
        comprobanteVenta.setFormaPagoId(getFormaPago().getIdConcepto());
        comprobanteVenta.setEstadoId(Constants.COMPROBANTE_CREADO_PENDIENTE);
        comprobanteVenta.setBaseImponible(obtenerCalculos()[Constants.VENTA_BASE_IMPONIBLE]); //sin igv de todos los detalles sin igv
        comprobanteVenta.setIgv(obtenerCalculos()[Constants.VENTA_IMPORTE_IGV]); // importe del  sum
        comprobanteVenta.setTotal(obtenerCalculos()[Constants.VENTA_TOTAL]); // suma
        comprobanteVenta.setTipoComprobanteId(getTipoComprobante().getIdConcepto());
        comprobanteVenta.setClienteId(cliente.getCliIClienteId());
        comprobanteVenta.setComIUsuarioId(usuario.getUsuIUsuarioId());
        comprobanteVenta.setAnulado(Constants.COMPROBANTE_NO_ANULADO);
        comprobanteVenta.setSaldo(obtenerCalculos()[Constants.VENTA_SALDO]); // si al contado 0 / si es al credito es total
        comprobanteVenta.setLqId(cajaLiquidacion.getLiqId());
        comprobanteVenta.setTipoVentaId(Constants.TIPO_VENTA_NORMAL);
        comprobanteVenta.setEstablecimientoId(establecimiento.getEstIEstablecimientoId());
        comprobanteVenta.setExportado(Constants.NO_EXPORTADO);
        comprobanteVenta.setDocIdentidad(cliente.getPersona().getPerVDocIdentidad());
        comprobanteVenta.setValorResumen(String.valueOf(obtenerCalculos()[Constants.VENTA_TOTAL]));
        comprobanteVenta.setCliente(cliente.getPersona().getPerVDocIdentidad());
        comprobanteVenta.setDireccionCliente(cliente.getPersona().getUbicacion().getDescripcion());
        comprobanteVenta.setFechaCreacion(Utils.getDatePhoneWithTime());

        comprobanteVenta.setPorImpuesto(obtenerCalculos()[Constants.VENTA_POR_IMPUESTO]);
        /**Comprobante de venta detalle**/
        comprobanteVenta.setFechaDoc(Utils.getDatePhoneWithTime());

        comprobanteVenta.setFechaActualizacion(Utils.getDatePhoneWithTime());
        comprobanteVentaDetalles = getComprobanteVentaDetalleList(comprobanteVenta);


        switch (getFormaPago().getDescripcion()) {

            case Constants.FORMA_PAGO_CREDITO:
                generarVentaCredito();
                break;

            case Constants.FORMA_PAGO_CONTADO:
                generarVentaContado();
                break;
        }


        int scop = Integer.parseInt(editTextScop.getText().toString());
        pedido.setScop(scop + "");
        /*
        for (ComprobanteVentaDetalle comprobanteVentaDetalle : comprobanteVentaDetalles) {
            Long comproVenDeta = comprobanteVentaDetalle.save();
            Log.d(TAG, " ComprobanteVentaDetalle " + comproVenDeta);
        }*/


    }

    public void guardarVenta() {

        if (!Session.getTipoDespachoSN(this)) {
            Long pedidoId = pedido.save();
        } else {
        }


        Long comproVentaId = comprobanteVenta.save();
        //SugarRecord.saveInTx(comprobanteVentaDetalles);//


        Log.d(TAG, " comprobanteVenta " + comproVentaId);
        pedido.setCompId(comproVentaId);
        pedido.save();
        //  Log.d(TAG, " pedidoId " + pedidoId);

        /**Guardar para la exportacion**/

        //new SyncEstado(Integer.parseInt(pedido.getId() + ""),Utils.separteUpperCase(Pedido.class.getSimpleName()) , Integer.parseInt(pedido.getPeId() + ""), Constants.S_ACTUALIZADO).save();
        new SyncEstado(0, Utils.separteUpperCase(ComprobanteVenta.class.getSimpleName()), Integer.parseInt(comprobanteVenta.getId() + ""), Constants.S_CREADO).save();
        for (ComprobanteVentaDetalle ventaDetalle : comprobanteVentaDetalles) {
            Long idVentaDetalle = ventaDetalle.save();
            ventaDetalle.setCompdId(Integer.parseInt(idVentaDetalle + ""));
            ventaDetalle.save();
            Long id = new SyncEstado(0, Utils.separteUpperCase(ComprobanteVentaDetalle.class.getSimpleName()), Integer.parseInt(idVentaDetalle + ""), Constants.S_CREADO).save();
            Log.d(TAG, " SyncEstado VENTA DETALLE " + id);

        }


        switch (getFormaPago().getDescripcion()) {

            case Constants.FORMA_PAGO_CREDITO:
                planPago.setCompId(comproVentaId);
                Long insplanPago = planPago.save();
                planPago.setPlanPaId(insplanPago);
                planPago.save();
                Log.d(TAG, " PlanPago " + insplanPago);
                if (!Session.getDefineCuotas(SellActivity.this)) {
                    int diasCredito = Integer.parseInt(conceptoCredito.getAbreviatura());
                    String fechaPago = Utils.getStringDate(Utils.sumarFechasDias(Calendar.getInstance().getTime(), diasCredito));
                    int idPlanPagoDetalle = PlanPagoDetalle.findWithQuery(PlanPagoDetalle.class, Utils.getQueryNumberPlanPagoDetalle(), null).get(Constants.CURRENT).getPlanPaDeId();
                    this.planPagoDetalles = new ArrayList<>();
                    this.planPagoDetalles.add(new PlanPagoDetalle(planPago.getPlanPaId(), idPlanPagoDetalle, Utils.getDatePhoneWithTime(), obtenerCalculos()[Constants.VENTA_TOTAL], Constants.ESTADO_TRUE, obtenerCalculos()[Constants.VENTA_BASE_IMPONIBLE], Double.parseDouble(getPorcentajeInteresMes().getDescripcion()), obtenerCalculos()[Constants.VENTA_TOTAL], fechaPago, usuario.getUsuIUsuarioId(), Utils.getDatePhoneWithTime(), 0));
                }

                for (PlanPagoDetalle pagoDetalle : planPagoDetalles) {
                    Long idPlanPagoDetalle = pagoDetalle.save();
                    pagoDetalle.setPlanPaDeId(Integer.parseInt("" + idPlanPagoDetalle));
                    pagoDetalle.setPlanPaId(insplanPago);
                    pagoDetalle.save();
                }


                SugarRecord.saveInTx(this.planPagoDetalles);

                new SyncEstado(0, Utils.separteUpperCase(PlanPago.class.getSimpleName()), Integer.parseInt(planPago.getId() + ""), Constants.S_CREADO).save();
                for (PlanPagoDetalle pagoDetalle : this.planPagoDetalles) {
                    new SyncEstado(0, Utils.separteUpperCase(PlanPagoDetalle.class.getSimpleName()), Integer.parseInt(pagoDetalle.getId() + ""), Constants.S_CREADO).save();
                }

                break;

            case Constants.FORMA_PAGO_CONTADO:
                Long idMov = cajaMovimiento.save();
                cajaMovimiento.setCajMovId(idMov);
                cajaMovimiento.save();
                cajaComprobante.setCajMovId(idMov);
                cajaComprobante.setCajCompId(comproVentaId);
                cajaComprobante.save();
                cajaPago.setCajMovId(idMov);
                Long idcajaPag = cajaPago.save();
                cajaPago.setCajPagId(idcajaPag);
                cajaPago.save();
                Log.d(TAG, " CajaComprobante " + comproVentaId);
                Log.d(TAG, " CajaPago " + idcajaPag);
                new SyncEstado(0, Utils.separteUpperCase(CajaMovimiento.class.getSimpleName()), Integer.parseInt(cajaMovimiento.getId() + ""), Constants.S_CREADO).save();
                new SyncEstado(0, Utils.separteUpperCase(CajaComprobante.class.getSimpleName()), Integer.parseInt(cajaComprobante.getId() + ""), Constants.S_CREADO).save();
                new SyncEstado(0, Utils.separteUpperCase(CajaPago.class.getSimpleName()), Integer.parseInt(cajaPago.getId() + ""), Constants.S_CREADO).save();


                break;
        }

        //Vincular el comprobante con  los depachos
        List<Despacho> despachos = new ManipuleData().getDespachosToFactura(this);

        for (int i = 0; i < despachos.size(); i++) {
            despachos.get(i).setCompId(comprobanteVenta.getCompId());
            Long despachoId = despachos.get(i).save();
            Log.d(TAG, "Actualizo despacho para agregar el comprobante: " + despachoId);
        }


    }

    private DocumentoElectronico getDocumentoEletronico() {

        UbicacionGeoreferencia georeferencia = null;
        UbicacionGeoreferencia georeferenciaProvincia = null;
        UbicacionGeoreferencia georeferenciaDepartamento = null;


        DocumentoElectronico doc = new DocumentoElectronico();

        doc.setIdDocumento(comprobanteVenta.getSerie() + "-" + comprobanteVenta.getNumDoc());
        doc.setFechaEmision(Utils.getDatePhoneFE());
        Log.e("ERRORMAIN", "" + comprobanteVenta.getTipoComprobanteId());
        doc.setTipoDocumento(getTipoDocumentoFE(comprobanteVenta.getTipoComprobanteId()).getCodigo());
        doc.setMoneda(Constants.DOCUMENTO_ELECTRONICO_PEN);
        doc.setMontoEnLetras(NumberToLetterConverter.convertNumberToLetter(comprobanteVenta.getTotal()));
        doc.setGravadas(new BigDecimal(comprobanteVenta.getTotal()));
        doc.setInafectas(new BigDecimal(0.00));
        doc.setExoneradas(new BigDecimal(0.00));
        doc.setGratuitas(new BigDecimal(0.00));

        doc.setPlacaVehiculo(placaDocElectronico);


        /** EMISOR **/
        DEEntidad deContribuyente = cajaLiquidacion.getEntidad();
        georeferencia = UbicacionGeoreferencia.getUbicacionGeoreferencia(deContribuyente.getDistritoId() + "");
        georeferenciaProvincia = UbicacionGeoreferencia.getUbicacionGeoreferencia(georeferencia.getParentId() + "");
        georeferenciaDepartamento = UbicacionGeoreferencia.getUbicacionGeoreferencia(georeferenciaProvincia.getParentId() + "");
        Contribuyente emisor = new Contribuyente();
        emisor.setNombreComercial(deContribuyente.getNombreComercial());
        emisor.setNombreLegal(deContribuyente.getNombreComercial());
        emisor.setNroDocumento(deContribuyente.getrUC());
        emisor.setTipoDocumento(deContribuyente.getTipoDocumento() + "");

        emisor.setUbigeo(georeferencia.getCodigo());
        emisor.setDireccion(deContribuyente.getDireccionFiscal());
        emisor.setUrbanizacion(deContribuyente.getUrbanizacion());
        emisor.setDistrito(georeferencia.getDescripcion());
        emisor.setProvincia(georeferenciaProvincia.getDescripcion());
        emisor.setDepartamento(georeferenciaDepartamento.getDescripcion());

        /**--------------------------------------------**/
        /** RECEPTOR **/
        Contribuyente receptor = new Contribuyente();
        receptor.setTipoDocumento(getTipoDocumentoFE(cliente.getPersona().getPerITipoDocIdentidadId()).getCodigo());
        receptor.setNroDocumento(cliente.getPersona().getPerVDocIdentidad());
        receptor.setNombreComercial(nombreDatosCliente);
        receptor.setNombreLegal(cliente.getPersona().getPerVRazonSocial());

        georeferencia = UbicacionGeoreferencia.getUbicacionGeoreferencia(cliente.getPersona().getUbicacion().getUgId() + "");
        georeferenciaProvincia = UbicacionGeoreferencia.getUbicacionGeoreferencia(georeferencia.getParentId() + "");
        georeferenciaDepartamento = UbicacionGeoreferencia.getUbicacionGeoreferencia(georeferenciaProvincia.getParentId() + "");


        receptor.setDistrito(georeferencia.getDescripcion());
        receptor.setProvincia(georeferenciaProvincia.getDescripcion());
        receptor.setDepartamento(georeferenciaDepartamento.getDescripcion());
        /**--------------------------------------------**/

        doc.setTotalVenta(new BigDecimal(comprobanteVenta.getTotal()));
        doc.setTotalIgv(new BigDecimal(comprobanteVenta.getIgv()));

        List<DetalleDocumento> items = new ArrayList<>();

        for (ComprobanteVentaDetalle ventaDetalle : comprobanteVentaDetalles) {

            DetalleDocumento item1 = new DetalleDocumento();
            item1.setId(ventaDetalle.getCompdId());
            item1.setCantidad(new BigDecimal(Utils.formatDoubleNumber(ventaDetalle.getCantidad())));
            item1.setUnidadMedida(getUnidadTipo(Constants.DOCUMENTO_ELECTRONICO_NIU).getCodigo());
            item1.setTotalVenta(new BigDecimal(Utils.formatDoubleNumber(ventaDetalle.getCostoVenta())));
            item1.setDescripcion(Producto.getNameProducto(ventaDetalle.getProId() + ""));
            item1.setCodigoItem(ventaDetalle.getProId() + "");
            item1.setPrecioUnitario(new BigDecimal(Utils.formatDoubleNumber(ventaDetalle.getPrecioUnitario())));
            item1.setTipoPrecio(Constants.DOCUMENTO_ELECTRONICO_TIPO_PRECIO);// buscar
            item1.setImpuesto(new BigDecimal(Utils.formatDouble(comprobanteVenta.getPorImpuesto())));
            item1.setTipoImpuesto(getTipoDocumentoFE(Constants.DOCUMENTO_ELECTRONICO_OPERACION_ONEROSA).getCodigo());
            item1.setImpuestoSelectivo(BigDecimal.ZERO);//ISC
            item1.setSuma(new BigDecimal(ventaDetalle.getImporte()));
            item1.setOtroImpuesto(new BigDecimal(0.00));
            item1.setPrecioReferencial(new BigDecimal(0.00));
            item1.setDescuento(new BigDecimal(0.00));
            items.add(item1);

        }
        doc.setMontoAnticipo(new BigDecimal(0.0));
        doc.setReceptor(receptor);
        doc.setEmisor(emisor);
        doc.setItems(items);
        return doc;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        Log.d(TAG, "CODE: " + requestCode);
        switch (requestCode) {
            case GENERAR_XML_REQUEST_CODE:
                if (resultCode == RESULT_OK) {
                    //retrieve the data
                    if (data.hasExtra("response")) {
                        Log.d(TAG, "response");
                        FirmadoResponse response;
                        ObjectMapper mapper = new ObjectMapper();
                        try {
                            Log.d(TAG, "RESPONSE: " + data.getStringExtra("response"));
                            response = mapper.readValue(data.getStringExtra("response"), FirmadoResponse.class);
                            Log.e(TAG, "Error:" + response.getTramaXmlFirmado());
                        } catch (IOException e) {
                            e.printStackTrace();
                            Log.d(TAG, "DAT:" + e.getMessage());
                            return;
                        }

                        if (response.isExito()) {
                            //Genial se ha generado con éxito el firmado
                            //en la variable tramaXmlFirmado está el xml en un String base64
                            Toast.makeText(getApplicationContext(), "SHA1: " + response.getResumenFirma(), Toast.LENGTH_SHORT).show();
                            beDocElectronico.setDatosXml(response.getTramaXmlFirmado());
                            beDocElectronico.setResumenFirma(response.getResumenFirma());

                            BeDocElectronico beDocElectronico = generarDocumentoElectronicoFE();

                            Long idDocEletr = beDocElectronico.save();
                            beDocElectronico.setDocElectronicoId(idDocEletr);
                            beDocElectronico.save();
                            for (BeDocElectronicoDetalle beDocElectronicoDetalle : beDocElectronico.getDetalle()) {
                                beDocElectronicoDetalle.setDocElectronicoId(Integer.parseInt(idDocEletr + ""));
                                beDocElectronicoDetalle.save();
                            }


                            updatLiqDetalle();

                            new SyncEstado(0, Utils.separteUpperCase(BeDocElectronico.class.getSimpleName()), Integer.parseInt(beDocElectronico.getId() + ""), Constants.S_CREADO).save();
                            Session.saveComprobanteVenta(this, comprobanteVenta);
                            intentPrint();

                        } else {
                            //Ha habido algún error, en la variable mensajeError de FirmadoResponse estará el error.

                        }
                    }
                } else if (resultCode == RESULT_CANCELED) {
                    //Hubo una excepción irrecuperable.
                    Log.d(TAG, " Hubo una excepción irrecuperable");
                }
                break;
        }

    }

    private BeDocElectronico generarDocumentoElectronicoFE() {


        List<BeDocElectronicoDetalle> beDocElectronicoDetalles = new ArrayList<>();

        for (int i = 0; i < documentoElectronico.getItems().size(); i++) {

            DetalleDocumento detalleDocumento = documentoElectronico.getItems().get(i);

            ComprobanteVentaDetalle ventaDetalle = comprobanteVentaDetalles.get(i);

            BeDocElectronicoDetalle electronicoDetalle = new BeDocElectronicoDetalle();
            Log.d(TAG, "DOC ELECTRO DE : " + detalleDocumento.getCantidad().doubleValue());
            electronicoDetalle.setDocElectronicoId(Integer.parseInt(comprobanteVenta.getCompId() + ""));
            electronicoDetalle.setDocdetalleId(detalleDocumento.getId());
            electronicoDetalle.setCantidad(detalleDocumento.getCantidad().doubleValue());
            electronicoDetalle.setUnidadMedidaId(Integer.parseInt(getUnidadTipo(Constants.DOCUMENTO_ELECTRONICO_NIU).getTipoId() + ""));
            electronicoDetalle.setSuma(detalleDocumento.getSuma().doubleValue());
            electronicoDetalle.setTotalVenta(detalleDocumento.getTotalVenta().doubleValue());
            electronicoDetalle.setPrecioUnitario(detalleDocumento.getPrecioUnitario().doubleValue());
            electronicoDetalle.setTipoPrecioId(Integer.parseInt(detalleDocumento.getTipoPrecio()));
            electronicoDetalle.setTipoImpuestoId(Integer.parseInt(detalleDocumento.getTipoImpuesto()));
            electronicoDetalle.setImpuesto(detalleDocumento.getImpuesto().doubleValue());
            electronicoDetalle.setImpuestoSelectivo(detalleDocumento.getImpuestoSelectivo().doubleValue());
            electronicoDetalle.setOtroImpuesto(detalleDocumento.getOtroImpuesto().doubleValue());

            electronicoDetalle.setProductoId(ventaDetalle.getProId());
            electronicoDetalle.setDescripcion(detalleDocumento.getDescripcion());
            electronicoDetalle.setCodigoItem(detalleDocumento.getCodigoItem());
            electronicoDetalle.setPrecioReferencial(detalleDocumento.getPrecioReferencial().doubleValue() + "");
            electronicoDetalle.setDescuento(detalleDocumento.getDescuento().doubleValue() + "");

            beDocElectronicoDetalles.add(electronicoDetalle);
        }

        beDocElectronico.setTipoDocCodigo(getTipoDocumentoFE(comprobanteVenta.getTipoComprobanteId()).getCodigo());
        beDocElectronico.setDocElectronicoId(comprobanteVenta.getCompId());
        beDocElectronico.setTipoDocumentoId(getTipoDocumentoFE(comprobanteVenta.getTipoComprobanteId()).getTipoId());
        beDocElectronico.setNumeroDoc(comprobanteVenta.getSerie() + "-" + comprobanteVenta.getNumDoc());
        beDocElectronico.setFechaEmision(documentoElectronico.getFechaEmision());
        beDocElectronico.setMonedaId(getTipoMoneda(documentoElectronico.getMoneda()));
        beDocElectronico.setGravadas(comprobanteVenta.getTotal());
        beDocElectronico.setGratuitas(0.00);
        beDocElectronico.setInafectas(0.00);
        beDocElectronico.setExoneradas(0.00);
        beDocElectronico.setDescuentoGlobal(0.00);
        beDocElectronico.setTotalVenta(comprobanteVenta.getTotal());
        beDocElectronico.setTotalIgv(comprobanteVenta.getIgv());
        beDocElectronico.setTotalIsc(0.00);
        beDocElectronico.setTotalOtrosTributos(0.00);
        beDocElectronico.setMontoEnLetras(documentoElectronico.getMontoEnLetras());
        beDocElectronico.setTipoOperacionId(40); //Buscar consultar a Sully
        beDocElectronico.setCalculoIgv(documentoElectronico.getCalculoIgv().doubleValue());
        beDocElectronico.setCalculoIsc(0.00);
        beDocElectronico.setCalculoDetraccion(0.00);
        beDocElectronico.setMontoPercepcion(0.00);
        beDocElectronico.setMontoDetraccion(0.00);
        beDocElectronico.setTipoDocAnticipoId(0);//Buscar consultar a Sully
        beDocElectronico.setDocAnticipo(documentoElectronico.getDocAnticipo());
        beDocElectronico.setMonedaAnticipoId(0);
        beDocElectronico.setMontoAnticipo(documentoElectronico.getMontoAnticipo().doubleValue());
        beDocElectronico.setClienteId(cliente.getCliIClienteId());
        beDocElectronico.setNombreFacturacion(nombreDatosCliente);
        beDocElectronico.setDireccionFacturacion(comprobanteVenta.getDireccionCliente() + ", " + documentoElectronico.getReceptor().getDistrito() + ", " + documentoElectronico.getReceptor().getProvincia() + ", " + documentoElectronico.getReceptor().getDepartamento());
        beDocElectronico.setCorporacionId(cajaLiquidacion.getEntidad().getCorporacionId());
        beDocElectronico.setEntidadId(cajaLiquidacion.getEntidadId());
        beDocElectronico.setUnidadNegocioId(cajaLiquidacion.getEntidadId());
        beDocElectronico.setUsuarioCreacionId(usuario.getUsuIUsuarioId());
        beDocElectronico.setFechaCreacion(Utils.getDatePhone());
        beDocElectronico.setUsuarioAccionId(usuario.getUsuIUsuarioId());
        beDocElectronico.setFechaAccion(Utils.getDatePhone());
        beDocElectronico.setEstadoTrackId(1);
        beDocElectronico.setEstadoId(1);
        beDocElectronico.setSistemaId(2);
        beDocElectronico.setTotalDescuentos(0.00);
        beDocElectronico.setCorrelativo(comprobanteVenta.getClienteId());
        beDocElectronico.setSerie(comprobanteVenta.getSerie());
        //beDocElectronico.setResumenFirma(comprobanteVenta.getValorResumen());
        beDocElectronico.setScop(pedido.getScop());
        beDocElectronico.setPlacaVehiculo(placaDocElectronico);
        beDocElectronico.setDetalle(beDocElectronicoDetalles);
        //beDocElectronico.setDatosXml("");
        beDocElectronico.setRucEntidad(documentoElectronico.getEmisor().getNroDocumento());
        beDocElectronico.setComprobanteVentaId(comprobanteVenta.getCompId());

        return beDocElectronico;


    }

    private int getTipoMoneda(String codigo) {
        if (codigo.equals("PEN")) {
            return Constants.TIPO_MONEDA_SOLES;
        }
        if (codigo.equals("USD")) {
            return Constants.TIPO_MONEDA_DOLARES;
        }
        return Constants.TIPO_MONEDA_SOLES;
    }

    private CertificadoDigital getCertificadoDigital() {

        DEEntidad deEntidad = cajaLiquidacion.getEntidad();

        return deEntidad.getCertificado();

    }


    private DETipo getTipoDocumentoFE(int tipoDoc) {

        DETipo deTipo = null;

        switch (tipoDoc) {
            case Constants.TIPO_DOCUMENTO_RUC:
                deTipo = DETipo.getDeTipo(Constants.TIPO_DOCUMENTO_ELECTRONICO_RUC);
                break;
            case Constants.TIPO_DOCUMENTO_DNI:
                deTipo = DETipo.getDeTipo(Constants.TIPO_DOCUMENTO_ELECTRONICO_DNI);
                break;
            case Constants.TIPO_DOCUMENTO_BOLETA:
                deTipo = DETipo.getDeTipo(Constants.TIPO_DOCUMENTO_ELECTRONICO_BOLETA);
                break;
            case Constants.TIPO_DOCUMENTO_FACTURA:
                deTipo = DETipo.getDeTipo(Constants.TIPO_DOCUMENTO_ELECTRONICO_FACTURA);
                break;
            case Constants.DOCUMENTO_ELECTRONICO_OPERACION_ONEROSA:
                deTipo = DETipo.getDeTipo(Constants.DOCUMENTO_ELECTRONICO_OPERACION_ONEROSA + "");
                break;
            case Constants._CREADO:
                break;
        }

        return deTipo;
    }

    private DETipo getUnidadTipo(String tipoId) {
        return DETipo.getDeTipo(tipoId);
    }

    @Override
    public void onBackPressed() {

        new DialogGeneral(SellActivity.this).setCancelable(false).setMessages("Retroceder", "¿Seguro de retroceder?").setTextButtons("SI", "NO").showDialog(new DialogGeneralListener() {
            @Override
            public void onSavePressed(AlertDialog alertDialog) {
                SellActivity.super.onBackPressed();
                alertDialog.dismiss();
            }

            @Override
            public void onCancelPressed(AlertDialog alertDialog) {
                alertDialog.dismiss();
            }
        });


    }

    private Concepto getCatMov() {

        Concepto concepto = new Concepto();

        switch (getFormaPago().getDescripcion()) {

            case Constants.FORMA_PAGO_CREDITO:
                concepto = Concepto.find(Concepto.class, " ID_CONCEPTO = ? ", new String[]{Constants.CONCEPTO_CAT_MOV_VENTA_PLAN_PAGOS + ""}).get(Constants.CURRENT);
                break;

            case Constants.FORMA_PAGO_CONTADO:
                concepto = Concepto.find(Concepto.class, "ID_CONCEPTO = ? ", new String[]{Constants.CONCEPTO_CAT_MOV_VENTA_CONTADO + ""}).get(Constants.CURRENT);
                break;
        }
        return concepto;
    }

    private String getGlosa() {

        if (getFormaPago().getDescripcion().equals(Constants.FORMA_PAGO_CONTADO)) {
            return "VENTA AL CONTADO";
        }
        return "VENTA AL CREDITO";

    }

    private double[] obtenerCalculos() {

        double[] doubles = new double[5];

        List<Despacho> despachos = new ManipuleData().getDespachosToFactura(this);

        double baseImponible = 0.0;
        double importeIgv = 0.0;
        double total = 0.0;


        for (Despacho despacho : despachos) {

            double baseImponibleDespacho = despacho.getPrecioUnitarioSIGV() * despacho.getCantidadDespachada();
            double importeIgvDespacho = baseImponibleDespacho * despacho.getPorImpuesto();


            baseImponible = baseImponible + baseImponibleDespacho;
            importeIgv = importeIgv + importeIgvDespacho;


        }
        total = baseImponible + importeIgv;
        doubles[Constants.VENTA_BASE_IMPONIBLE] = Utils.getDoubleFormat(baseImponible);
        doubles[Constants.VENTA_IMPORTE_IGV] = Utils.getDoubleFormat(importeIgv);
        doubles[Constants.VENTA_TOTAL] = Utils.getDoubleFormat(total);
        doubles[Constants.VENTA_SALDO] = Utils.getDoubleFormat(0.0);


        if (!getFormaPago().getDescripcion().equals(Constants.FORMA_PAGO_CONTADO)) {
            doubles[Constants.VENTA_SALDO] = Utils.getDoubleFormat(total);
        }

        doubles[Constants.VENTA_POR_IMPUESTO] = Double.parseDouble(Session.getConceptoIGV().getDescripcion());
        return doubles;

    }

    private void initImportesDetalle() {

        double[] doubles = obtenerCalculos();
        textTotal.setText(Utils.formatDoublePrint(doubles[Constants.VENTA_TOTAL]) + "");
        textBaseImponible.setText(Utils.formatDoublePrint(doubles[Constants.VENTA_BASE_IMPONIBLE]) + "");
        textIGV.setText(Utils.formatDoublePrint(doubles[Constants.VENTA_IMPORTE_IGV]) + "");

    }

    private Concepto getPorcentajeInteresMes() {
        // return Concepto.find(Concepto.class, " ID_CONCEPTO = ? ", new String[]{"120"}).get(Constants.CURRENT);

        return Concepto.find(Concepto.class, " CONCEPTO = ? AND  OBJETO = ? AND ESTADO = ? ", new String[]{Constants.CONCEPTO_CONCEPTO_PORCENTAJE_MES, Constants.CONCEPTO_OBJETO_PORCENTAJE_MES, Constants.CONCEPTO_ESTADO_PORCENTAJE_MES + ""}).get(Constants.CURRENT);
    }

    private void generarVentaCredito() {

        Long planPagoId = PlanPago.findWithQuery(PlanPago.class, Utils.getQueryNumberPlanPago(), null).get(Constants.CURRENT).getPlanPaId();
        planPago = new PlanPago(planPagoId, comprobanteVenta.getCompId(), "", comprobanteVenta.getSerie(), comprobanteVenta.getNumDoc(), getGlosa(), Constants.ESTADO_TRUE, Double.parseDouble(getPorcentajeInteresMes().getDescripcion()), usuario.getUsuIUsuarioId(), Utils.getDatePhoneWithTime(), null);

    }

    private void generarVentaContado() {

        Long cajaMovId = CajaMovimiento.findWithQuery(CajaMovimiento.class, Utils.getQueryNumberCajaMov(), null).get(Constants.CURRENT).getCajMovId();
        cajaMovimiento = new CajaMovimiento(cajaMovId, cajaLiquidacion.getLiqId(), getCatMov().getIdConcepto(), "SOLES", comprobanteVenta.getBaseImponible(), true, Utils.getDatePhoneWithTime(), "", "", usuario.getUsuIUsuarioId(), Utils.getDatePhoneWithTime(), "", Constants.CONCEPTO_TIPO_MOV_INGRESO, null, null, null, null);


        Long cajaComprobanteId = CajaComprobante.findWithQuery(CajaComprobante.class, Utils.getQueryNumberCajaComprobante(), null).get(Constants.CURRENT).getCajCompId();
        Long cajaPagoId = CajaPago.findWithQuery(CajaPago.class, Utils.getQueryNumberCajaPago(), null).get(Constants.CURRENT).getCajPagId();
        cajaComprobante = new CajaComprobante(cajaComprobanteId, cajaMovimiento.getCajMovId(), comprobanteVenta.getCompId(), comprobanteVenta.getBaseImponible(), usuario.getUsuIUsuarioId(), Utils.getDatePhoneWithTime());
        cajaPago = new CajaPago(cajaPagoId, comprobanteVenta.getBaseImponible(), cajaMovimiento.getCajMovId(), usuario.getUsuIUsuarioId(), Utils.getDatePhoneWithTime(), Constants.NO_EXPORTADO, getTipoPago().getIdConcepto(), Constants.ESTADO_FALSE);

    }

    private List<Despacho> getDespachos() {
        List<Despacho> despachos = new ManipuleData().getDespachosToFactura(this);
        return despachos;
    }

    private List<ComprobanteVentaDetalle> getComprobanteVentaDetalleList(ComprobanteVenta comprobanteVenta) {
        List<ComprobanteVentaDetalle> comprobanteVentaDetalles = new ArrayList<>();
        List<Despacho> despachos = getDespachos();

        //  int comprobanteVentaId = Integer.parseInt(ComprobanteVentaDetalle.findWithQuery(ComprobanteVentaDetalle.class, Utils.getQueryNumberComprobanteVentaDetalle(), null).get(Constants.CURRENT).getId()+"");

        for (Despacho despacho : despachos) {
            ProductoUnidad productoUnidad = ProductoUnidad.find(ProductoUnidad.class, " pro_Id=? ", new String[]{despacho.getProId() + ""}).get(Constants.CURRENT);
            comprobanteVentaDetalles.add(new ComprobanteVentaDetalle(0, comprobanteVenta.getCompId(), despacho.getProId(), productoUnidad.getUnId(), despacho.getCantidadDespachada(), despacho.getPrecioUnitarioSIGV(), despacho.getPrecioUnitarioCIGV(), Utils.formatDoubleNumber(despacho.getCostoVenta()), Utils.formatDoubleNumber(despacho.getImporte()), usuario.getUsuIUsuarioId(), Utils.getDatePhoneWithTime(), despacho.getDespachoId()));
            // comprobanteVentaId++;
        }


        return comprobanteVentaDetalles;
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

    private void toolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void initViews() {
        toolbar();
        initCheckBox();
        List<Despacho> despachoList = new ManipuleData().getDespachosToFactura(this);
        if (despachoList.size() == 1) {
            checkBoxResumen.setChecked(false);
            checkBoxResumen.setEnabled(false);
        } else {
            despachoList = new ManipuleData().getDespachoResumen(this);
        }

        initDespachos(despachoList);
        initTipoComprobante();


        if (cliente.getCliIModalidadCreditoId() <= 0) {
            conceptoCredito = new Concepto();
            conceptoCredito.setAbreviatura("0");
            descorasc = "0";
            Toast.makeText(SellActivity.this, "No tiene modalidad de credito", Toast.LENGTH_SHORT).show();
            enableButtonCredito(false);
        } else {
            conceptoCredito = Concepto.find(Concepto.class, " ID_CONCEPTO = ? ", new String[]{cliente.getCliIModalidadCreditoId() + ""}).get(Constants.CURRENT);
            Log.d(TAG, "ABREVIATURA: MODALIDAD: " + conceptoCredito.getAbreviatura() + "-" + cliente.getCliIModalidadCreditoId());
            descorasc = "1";

        }

        initFormaPago();
        initTipoPago();
        initRadios();
        setTextDireccion();

        initImportesDetalle();
        setScop();
        buttonCuotas.setOnClickListener(this);

    }

    private void enableButtonCredito(boolean estado) {
        buttonCuotas.setEnabled(estado);
        buttonCuotas.setBackgroundColor(getResources().getColor(R.color.md_red_100));
    }


    private void setScop() {


        editTextScop.setText(pedido.getScop() + "");
        editTextScop.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //  Toast.makeText(SellActivity.this, "Count - " + s.toString().length(), Toast.LENGTH_SHORT).show();
                if (s.toString().length() >= 9) {
                    Log.d(TAG, "onTextChanged > 8 : " + s);
                    editTextScop.setText(s.toString().substring(0, 8));
                    System.out.println(TAG + "   onTextChanged > 8 : " + s);
                    // Toast.makeText(SellActivity.this, ""+TAG+ "   onTextChanged > 8 : "+s, Toast.LENGTH_SHORT).show();
                } else {
                    Log.d(TAG, "onTextChanged < 8 : " + s);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private void initCheckBox() {
        checkBoxResumen.setChecked(true);
        checkBoxResumen.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    initDespachos(new ManipuleData().getDespachoResumen(getApplicationContext()));
                } else {
                    initDespachos(new ManipuleData().getDespachosToFactura(getApplicationContext()));
                }
            }
        });
    }

    private void initRadios() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                setTextDireccion();
            }
        });
    }

    private void setTextDireccion() {

        boolean isCheckedD = radioDireccion.isChecked();
        boolean isCheckedDF = radioDireccionFical.isChecked();
        String strings = "";
        if (isCheckedD) {
            strings = establecimiento.getUbicacion().getDescripcion();
        }
        if (isCheckedDF) {
            strings = cliente.getPersona().getUbicacion().getDescripcion();
        }
        editTextDireccion.setText(strings);

    }


    private void initTipoComprobante() {

        List<Concepto> conceptoList = Concepto.find(Concepto.class, " OBJETO = ? ", new String[]{Constants.CONCEPTO_TIPO_COMPROBANTE_VENTA});
        Log.d(TAG, "SIZE: " + conceptoList.size());
        ConceptoAdapter conceptoArrayAdapter = new ConceptoAdapter(this, 0, conceptoList);
        spinnerTipoComprobante.setAdapter(conceptoArrayAdapter);

    }

    private void initFormaPago() {

        List<Concepto> conceptoList = null;
        // if (!descorasc.equals("0")) {
        conceptoList = Concepto.findWithQuery(Concepto.class, " SELECT * FROM CONCEPTO WHERE CONCEPTO = ? AND  OBJETO = ?  ORDER BY id_Concepto desc ", new String[]{Constants.CONCEPTO_FORMA_PAGO, Constants.CONCEPTO_FORMA_PAGO_COMPROBANTE_VENTA});

        /*} else {
            conceptoList = Concepto.findWithQuery(Concepto.class, " SELECT * FROM CONCEPTO WHERE CONCEPTO = ? AND  OBJETO = ? ", new String[]{Constants.CONCEPTO_FORMA_PAGO, Constants.CONCEPTO_FORMA_PAGO_COMPROBANTE_VENTA});

        }*/
        // List<Concepto> conceptoList = Concepto.find(Concepto.class, " CONCEPTO = ? AND  OBJETO = ? ", new String[]{Constants.CONCEPTO_FORMA_PAGO, Constants.CONCEPTO_FORMA_PAGO_COMPROBANTE_VENTA});

        Log.d(TAG, "SIZE: " + conceptoList.size());
        ConceptoAdapter conceptoArrayAdapter = new ConceptoAdapter(this, 0, conceptoList);
        spinnerFormaPago.setAdapter(conceptoArrayAdapter);
        spinnerFormaPago.setOnItemSelectedListener(this);
    }

    private void initTipoPago() {
        List<Concepto> conceptoList = Concepto.find(Concepto.class, " CONCEPTO = ? AND  OBJETO = ? ", new String[]{Constants.CONCEPTO_TIPO_PAGO, Constants.CONCEPTO_CAJA_PAGO});
        Log.d(TAG, "SIZE: " + conceptoList.size());
        ConceptoAdapter conceptoArrayAdapter = new ConceptoAdapter(this, 0, conceptoList);
        spinnerTipoPago.setAdapter(conceptoArrayAdapter);
    }

    private void initDespachos(List<Despacho> despachos) {

        DespachoFacturaAdapter despachoFacturaAdapter = new DespachoFacturaAdapter(despachos, this);
        recyclerView.setAdapter(despachoFacturaAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }


    private Concepto getTipoComprobante() {
        return (Concepto) spinnerTipoComprobante.getSelectedItem();
    }

    private Concepto getFormaPago() {
        return (Concepto) spinnerFormaPago.getSelectedItem();
    }

    private Concepto getTipoPago() {
        return (Concepto) spinnerTipoPago.getSelectedItem();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab:

                break;
            case R.id.btnVender:

                if (editTextScop.getText().toString().length() < 8) {
                    Toast.makeText(SellActivity.this, "Ingrese SCOP", Toast.LENGTH_SHORT).show();
                    break;
                }

                if (getFormaPago().getIdConcepto() == Constants.CREDITO_ID) {

                    Log.d(TAG, "IF: " + 1);

                    if (conceptoCredito.getAbreviatura().equals("0")) {
                        Log.d(TAG, "IF: " + 2);
                        if (!Session.getDefineCuotas(getApplicationContext())) {
                            Log.d(TAG, "IF: " + 3);
                            if (cliente.getCliDOCreditoDisponible() >= obtenerCalculos()[Constants.VENTA_TOTAL]) {
                                Log.d(TAG, "IF: " + 4);
                                dialogConfirmarVenta();
                            } else {
                                Log.d(TAG, "IF: " + 5);
                                Toast.makeText(SellActivity.this, "No cuenta con credito suficiente", Toast.LENGTH_SHORT).show();
                                break;
                            }
                            Log.d(TAG, "IF: " + 6);

                        } else {
                            Log.d(TAG, "IF: " + 7);
                            Toast.makeText(SellActivity.this, "No cuenta con modalidad de credito", Toast.LENGTH_SHORT).show();
                            break;
                        }

                    }

                    if (cliente.getCliDOCreditoDisponible() < obtenerCalculos()[Constants.VENTA_TOTAL]) {
                        Toast.makeText(this, "No cuenta con credito disponible para realizar la venta", Toast.LENGTH_SHORT).show();
                    }
                    if (cliente.getCliDOCreditoDisponible() >= obtenerCalculos()[Constants.VENTA_TOTAL]) {
                        dialogConfirmarVenta();
                    }
                    break;
                }

                dialogConfirmarVenta();
                break;
            case R.id.btnDefinirCuotas:

                if (!Session.getDefineCuotas(getApplicationContext()) && validarCampos()) {

                    if (cliente.getCliDOCreditoDisponible() > obtenerCalculos()[Constants.VENTA_TOTAL]) {


                        generarVenta();
                        //  new EstablecerCuotasFragment().setParams(obtenerCalculos()).show(getSupportFragmentManager(), "");
                        Intent intent = new Intent(getApplicationContext(), DefinirCuotasActivity.class);
                        intent.putExtra(Constants.OBTENER_CUOTAS, obtenerCalculos());
                        startActivity(intent);

                    } else {
                        Toast.makeText(SellActivity.this, "No Cuenta con credito", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }
    }

    private boolean validarCampos() {


        if (editTextScop.getText().toString().length() == 8) {
            return true;
        } else {
            Toast.makeText(this, "Ingrese correctamenre el Scop", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Concepto concepto = (Concepto) adapterView.getSelectedItem();
        //cliente.setCliDOCreditoDisponible(20.00);
        hideLinear(concepto);
        if (concepto.getIdConcepto() == Constants.CREDITO_ID) {
            buttonCuotas.setVisibility(View.VISIBLE);
            Log.d(TAG, "CREDITO DISPONIBLE: " + cliente.getCliDOCreditoDisponible());
            if (cliente.getCliDOCreditoDisponible() < obtenerCalculos()[Constants.VENTA_TOTAL]) {

                /*new DialogGeneral(SellActivity.this).setTextButtons("SI", "NO").setMessages("Atencion", "¿Esta seguro de solicitar credito?").setCancelable(true).showDialog(new DialogGeneralListener() {
                    @Override
                    public void onSavePressed(AlertDialog alertDialog) {
                        cliente.setCliDOCreditoDisponible(10000.00);
                        alertDialog.dismiss();
                    }

                    @Override
                    public void onCancelPressed(AlertDialog alertDialog) {
                        alertDialog.dismiss();
                    }
                });*/

                Toast.makeText(SellActivity.this, "No cuenta con credito suficiente", Toast.LENGTH_SHORT).show();

            }
        } else {
            buttonCuotas.setVisibility(View.GONE);
        }

    }


    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void hideLinear(Concepto concepto) {

        if (concepto.getIdConcepto() == Constants.CREDITO_ID) {
            spinnerTipoPago.setVisibility(View.GONE);
        } else {
            spinnerTipoPago.setVisibility(View.VISIBLE);
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        if (Session.getDefineCuotas(getApplicationContext())) {
            buttonCuotas.setEnabled(false);
            List<PlanPagoDetalle> planPagoDetalles = Session.getListCuotas(getApplicationContext());
            if (planPagoDetalles != null) {

                this.planPagoDetalles = planPagoDetalles;
                for (int i = 0; i < this.planPagoDetalles.size(); i++) {

                    this.planPagoDetalles.get(i).setPlanPaId(planPago.getPlanPaId());
                    this.planPagoDetalles.get(i).setUsuarioAccion(usuario.getUsuIUsuarioId());
                    // this.planPagoDetalles.get(i).setCajMovId(cajaMovimiento.getCajMovId());
                }

                Toast.makeText(this, "TAMAÑO" + this.planPagoDetalles.size(), Toast.LENGTH_SHORT).show();

            }

        }
    }

    @Override
    public void manipulateInTransaction() {
        saveVenta();
        HashMap<Long, Long> integerList = new HashMap<>();
        for (Despacho despacho : getDespachos()) {
            integerList.put(despacho.getPeId(), despacho.getPeId());
        }
        HashMap<Long, List<Despacho>> despachoHashMap = new HashMap<>();
        List<Long> longList = new ArrayList<>(integerList.values());
        List<Despacho> despachos = new ArrayList<>();
        for (Long aLong : longList) {
            List<Despacho> despachoList = Despacho.getListDespachoByPedido(aLong + "");
            CajaLiquidacionDetalle cajaLiquidacionDetalle = CajaLiquidacionDetalle.getLiquidacionDetalleByEstablecAndPedido(establecimiento.getEstIEstablecimientoId() + "", aLong + "");
            double sumTotal = 0.0;
            double sumAgrupado = 0.0;
            for (Despacho despacho : despachoList) {

                if (aLong.equals(despacho.getPeId())) {
                    sumAgrupado = sumAgrupado + despacho.getCantidadDespachada();
                    despachos.add(despacho);
                    despachoHashMap.put(aLong, despachos);
                    if (despacho.getCompId() != -1) {
                        sumTotal = sumTotal + despacho.getCantidadDespachada();
                    }
                }
            }
            double totalFac = sumTotal / sumAgrupado;
            int estadoFac = Constants.FACTURADO;
            if (sumAgrupado == sumTotal) {
                estadoFac = Constants.FACTURADO_TOTAL;
            }

            cajaLiquidacionDetalle.setPorFacturado(totalFac);
            cajaLiquidacionDetalle.setEstadoFactId(estadoFac);
            cajaLiquidacionDetalle.save();
            despachos = new ArrayList<>();
        }


    }

    @Override
    public void errorInTransaction(String error) {
        Toast.makeText(SellActivity.this, "Error: " + error, Toast.LENGTH_LONG).show();
        progressDialog.dismiss();
    }

    @Override
    public void onLoadSuccess(String message) {
        // Toast.makeText(SellActivity.this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onLoadError(String message) {
        // Toast.makeText(SellActivity.this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onLoadErrorProcedure(String message) {
        // Toast.makeText(SellActivity.this, message, Toast.LENGTH_LONG).show();
    }


}
