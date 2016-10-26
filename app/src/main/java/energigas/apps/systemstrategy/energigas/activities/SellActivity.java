package energigas.apps.systemstrategy.energigas.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
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

import com.orm.SugarRecord;
import com.orm.SugarTransactionHelper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.adapters.ConceptoAdapter;
import energigas.apps.systemstrategy.energigas.adapters.DespachoFacturaAdapter;
import energigas.apps.systemstrategy.energigas.apiRest.ManipuleData;
import energigas.apps.systemstrategy.energigas.entities.CajaComprobante;
import energigas.apps.systemstrategy.energigas.entities.CajaLiquidacion;
import energigas.apps.systemstrategy.energigas.entities.CajaMovimiento;
import energigas.apps.systemstrategy.energigas.entities.CajaPago;
import energigas.apps.systemstrategy.energigas.entities.Cliente;
import energigas.apps.systemstrategy.energigas.entities.ComprobanteVenta;
import energigas.apps.systemstrategy.energigas.entities.ComprobanteVentaDetalle;
import energigas.apps.systemstrategy.energigas.entities.Concepto;
import energigas.apps.systemstrategy.energigas.entities.Despacho;
import energigas.apps.systemstrategy.energigas.entities.Establecimiento;
import energigas.apps.systemstrategy.energigas.entities.GeoUbicacion;
import energigas.apps.systemstrategy.energigas.entities.Pedido;
import energigas.apps.systemstrategy.energigas.entities.Persona;
import energigas.apps.systemstrategy.energigas.entities.PlanPago;
import energigas.apps.systemstrategy.energigas.entities.PlanPagoDetalle;
import energigas.apps.systemstrategy.energigas.entities.ProductoUnidad;
import energigas.apps.systemstrategy.energigas.entities.Serie;
import energigas.apps.systemstrategy.energigas.entities.SyncEstado;
import energigas.apps.systemstrategy.energigas.entities.Usuario;
import energigas.apps.systemstrategy.energigas.fragments.DialogGeneral;
import energigas.apps.systemstrategy.energigas.interfaces.DialogGeneralListener;
import energigas.apps.systemstrategy.energigas.utils.Constants;
import energigas.apps.systemstrategy.energigas.utils.NumberToLetterConverter;
import energigas.apps.systemstrategy.energigas.utils.Session;
import energigas.apps.systemstrategy.energigas.utils.Utils;

public class SellActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener, SugarTransactionHelper.Callback {
    private static final String TAG = "SellActivity";

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

    /**
     * Obtener Objectos
     **/

    Concepto conceptoCredito;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell);
        Toast.makeText(this, "" + Session.getCajaLiquidacion(this).getLiqId() + "", Toast.LENGTH_SHORT).show();
        pedido = Pedido.find(Pedido.class, "pe_Id=?", new String[]{Session.getPedido(this).getPeId() + ""}).get(Constants.CURRENT);
        usuario = Usuario.find(Usuario.class, " usu_I_Usuario_Id = ? ", new String[]{Session.getSession(this).getUsuIUsuarioId() + ""}).get(Constants.CURRENT);
        establecimiento = Establecimiento.find(Establecimiento.class, " est_I_Establecimiento_Id = ?  ", new String[]{Session.getSessionEstablecimiento(this).getEstIEstablecimientoId() + ""}).get(Constants.CURRENT);
        establecimiento.setUbicacion(GeoUbicacion.find(GeoUbicacion.class, "ub_Id = ?", new String[]{establecimiento.getUbId() + ""}).get(Constants.CURRENT));
        cliente = Cliente.find(Cliente.class, " CLI_I_CLIENTE_ID = ? ", new String[]{establecimiento.getEstIClienteId() + ""}).get(Constants.CURRENT);
        Persona persona = Persona.find(Persona.class, " per_I_Persona_Id=? ", new String[]{cliente.getCliIPersonaId() + ""}).get(Constants.CURRENT);
        cliente.setPersona(persona);
        cliente.getPersona().setUbicacion(GeoUbicacion.find(GeoUbicacion.class, " persona_Id=? ", new String[]{persona.getPerIPersonaId() + ""}).get(Constants.CURRENT));

        cajaLiquidacion = CajaLiquidacion.find(CajaLiquidacion.class, " liq_Id=? ", new String[]{Session.getCajaLiquidacion(this).getLiqId() + ""}).get(Constants.CURRENT);
        Session.setDefineCuotas(this, Constants.ESTADO_FALSE, "");
        ButterKnife.bind(this);
        initViews();
        fab.setOnClickListener(this);
        buttonVender.setOnClickListener(this);
        hideLinear(getFormaPago());

    }

    private void dialogConfirmarVenta() {

        new DialogGeneral(SellActivity.this).setTextButtons("GUARDAR", "CANCELAR").setMessages("Atencion", "¿Esta seguro de guardar?").setCancelable(true).showDialog(new DialogGeneralListener() {
            @Override
            public void onSavePressed() {

                if (cliente.getCliDOCreditoDisponible() > obtenerCalculos()[Constants.VENTA_TOTAL]) {

                    SugarTransactionHelper.doInTransaction(SellActivity.this);

                } else {
                    Toast.makeText(SellActivity.this, "No Cuenta con credito", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onCancelPressed() {

            }
        });

    }


    private void saveVenta() {

        generarVenta();
        guardarVenta();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        this.finish();

    }

    private void generarVenta() {


        serie = Serie.findWithQuery(Serie.class, Utils.getQueryForSerie(usuario.getUsuIUsuarioId(), Constants.TIPO_ID_DEVICE_CELULAR, getTipoComprobante().getIdConcepto()), null).get(Constants.CURRENT);
        String numero = ComprobanteVenta.findWithQuery(ComprobanteVenta.class, Utils.getQueryNumberSell(), null).get(Constants.CURRENT).getNumDoc();
        int compId = Integer.parseInt(numero);

        /**Comprobante de venta**/
        comprobanteVenta = new ComprobanteVenta();
        comprobanteVenta.setCompId(compId);
        comprobanteVenta.setSerie(serie.getCompVSerie());
        comprobanteVenta.setNumDoc(Utils.completaZeros(numero, serie.getParametro()));
        comprobanteVenta.setFormaPagoId(getFormaPago().getIdConcepto());
        comprobanteVenta.setEstadoId(Constants.COMPROBANTE_CREADO);
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
        comprobanteVenta.setValorResumen(NumberToLetterConverter.convertNumberToLetter(obtenerCalculos()[Constants.VENTA_TOTAL]));
        comprobanteVenta.setCliente(cliente.getPersona().getPerVDocIdentidad());
        comprobanteVenta.setDireccionCliente(cliente.getPersona().getUbicacion().getDescripcion());
        comprobanteVenta.setFechaCreacion(Utils.getDatePhone());
        comprobanteVenta.setPorImpuesto(obtenerCalculos()[Constants.VENTA_POR_IMPUESTO]);
        /**Comprobante de venta detalle**/
        comprobanteVentaDetalles = getComprobanteVentaDetalleList(comprobanteVenta);
        Long cajaMovId = CajaMovimiento.findWithQuery(CajaMovimiento.class, Utils.getQueryNumberCajaMov(), null).get(Constants.CURRENT).getCajMovId();
        cajaMovimiento = new CajaMovimiento(cajaMovId, cajaLiquidacion.getLiqId(), getCatMov().getIdConcepto(), "SOLES", comprobanteVenta.getBaseImponible(), true, Utils.getDatePhone(), "", "", usuario.getUsuIUsuarioId(), Utils.getDatePhone(), "", Constants.CONCEPTO_TIPO_MOV_INGRESO);


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

        Long pedidoId = pedido.save();
        Long cajaId = cajaMovimiento.save();
        Long comproVentaId = comprobanteVenta.save();
        SugarRecord.saveInTx(comprobanteVentaDetalles);//

        Log.d(TAG, " cajaMovimiento " + cajaId);
        Log.d(TAG, " comprobanteVenta " + comproVentaId);
        Log.d(TAG, " pedidoId " + pedidoId);

        /**Guardar para la exportacion**/

        new SyncEstado(Integer.parseInt(pedido.getPeId() + ""), "Pedido", Integer.parseInt(pedido.getPeId() + ""), Constants.EXPORTAR).save();
        new SyncEstado(0, "Caja_Movimiento", Integer.parseInt(cajaMovimiento.getCajMovId() + ""), Constants.EXPORTAR).save();
        new SyncEstado(0, "Comprobante_Venta", Integer.parseInt(comprobanteVenta.getCompId() + ""), Constants.EXPORTAR).save();
        for (ComprobanteVentaDetalle ventaDetalle : comprobanteVentaDetalles) {
            Long id = new SyncEstado(0, "Comprobante_Venta_Detalle", ventaDetalle.getCompdId(), Constants.EXPORTAR).save();
            Log.d(TAG, " SyncEstado VENTA DETALLE " + id);
        }


        switch (getFormaPago().getDescripcion()) {

            case Constants.FORMA_PAGO_CREDITO:

                Long insplanPago = planPago.save();
                Log.d(TAG, " PlanPago " + insplanPago);

                if (!Session.getDefineCuotas(SellActivity.this)) {
                    Date fecha = Calendar.getInstance().getTime();
                    int diasCredito = Integer.parseInt(conceptoCredito.getAbreviatura());
                    String fechaPago = Utils.getStringDate(Utils.sumarFechasDias(fecha, diasCredito));
                    int idPlanPagoDetalle = PlanPagoDetalle.findWithQuery(PlanPagoDetalle.class, Utils.getQueryNumberPlanPagoDetalle(), null).get(Constants.CURRENT).getPlanPaDeId();
                    this.planPagoDetalles = new ArrayList<>();
                    this.planPagoDetalles.add(new PlanPagoDetalle(planPago.getPlanPaId(), idPlanPagoDetalle, Utils.getDatePhone(), obtenerCalculos()[Constants.VENTA_TOTAL], Constants.ESTADO_TRUE, obtenerCalculos()[Constants.VENTA_BASE_IMPONIBLE], Double.parseDouble(getPorcentajeInteresMes().getDescripcion()), obtenerCalculos()[Constants.VENTA_TOTAL], fechaPago, usuario.getUsuIUsuarioId(), Utils.getDatePhone(), cajaMovimiento.getCajMovId()));
                }


                SugarRecord.saveInTx(this.planPagoDetalles);

                new SyncEstado(0, "Plan_Pago", Integer.parseInt(planPago.getPlanPaId() + ""), Constants.EXPORTAR).save();
                for (PlanPagoDetalle pagoDetalle : this.planPagoDetalles) {
                    new SyncEstado(0, "Plan_pago_detalle", Integer.parseInt(pagoDetalle.getPlanPaDeId() + ""), Constants.EXPORTAR).save();
                }

                break;

            case Constants.FORMA_PAGO_CONTADO:

                Long cajaCom = cajaComprobante.save();
                Long cajaPag = cajaPago.save();
                Log.d(TAG, " CajaComprobante " + cajaCom);
                Log.d(TAG, " CajaPago " + cajaPag);
                new SyncEstado(0, "Caja_Comprobante", Integer.parseInt(cajaComprobante.getCajCompId() + ""), Constants.EXPORTAR).save();
                new SyncEstado(0, "caja_Pago", Integer.parseInt(cajaPago.getCajPagId() + ""), Constants.EXPORTAR).save();


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

    @Override
    public void onBackPressed() {

        new DialogGeneral(SellActivity.this).setCancelable(false).setMessages("Retroceder", "¿Seguro de retroceder?").setTextButtons("SI", "NO").showDialog(new DialogGeneralListener() {
            @Override
            public void onSavePressed() {
                SellActivity.super.onBackPressed();
            }

            @Override
            public void onCancelPressed() {

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
        double porImpuesto = 0.0;


        for (Despacho despacho : despachos) {

            double baseImponibleDespacho = despacho.getPrecioUnitarioSIGV() * despacho.getCantidadDespachada();
            double importeIgvDespacho = baseImponibleDespacho * despacho.getPorImpuesto();

            baseImponible = baseImponible + baseImponibleDespacho;
            importeIgv = importeIgv + importeIgvDespacho;
            porImpuesto = despacho.getPorImpuesto();


        }
        total = baseImponible + importeIgv;
        doubles[Constants.VENTA_BASE_IMPONIBLE] = Utils.getDoubleFormat(baseImponible);
        doubles[Constants.VENTA_IMPORTE_IGV] = Utils.getDoubleFormat(importeIgv);
        doubles[Constants.VENTA_TOTAL] = Utils.getDoubleFormat(total);
        doubles[Constants.VENTA_SALDO] = Utils.getDoubleFormat(0.0);


        if (!getFormaPago().getDescripcion().equals(Constants.FORMA_PAGO_CONTADO)) {
            doubles[Constants.VENTA_SALDO] = Utils.getDoubleFormat(total);
        }

        doubles[Constants.VENTA_POR_IMPUESTO] = porImpuesto;
        return doubles;

    }

    private void initImportesDetalle() {

        double[] doubles = obtenerCalculos();
        textTotal.setText(doubles[Constants.VENTA_TOTAL] + "");
        textBaseImponible.setText(doubles[Constants.VENTA_BASE_IMPONIBLE] + "");
        textIGV.setText(doubles[Constants.VENTA_IMPORTE_IGV] + "");

    }

    private Concepto getPorcentajeInteresMes() {
        // return Concepto.find(Concepto.class, " ID_CONCEPTO = ? ", new String[]{"120"}).get(Constants.CURRENT);

        return Concepto.find(Concepto.class, " CONCEPTO = ? AND  OBJETO = ? AND ESTADO = ? ", new String[]{Constants.CONCEPTO_CONCEPTO_PORCENTAJE_MES, Constants.CONCEPTO_OBJETO_PORCENTAJE_MES, Constants.CONCEPTO_ESTADO_PORCENTAJE_MES + ""}).get(Constants.CURRENT);
    }

    private void generarVentaCredito() {

        Long planPagoId = PlanPago.findWithQuery(PlanPago.class, Utils.getQueryNumberPlanPago(), null).get(Constants.CURRENT).getPlanPaId();
        planPago = new PlanPago(planPagoId, comprobanteVenta.getCompId(), "", comprobanteVenta.getSerie(), comprobanteVenta.getNumDoc(), getGlosa(), Constants.ESTADO_TRUE, Double.parseDouble(getPorcentajeInteresMes().getDescripcion()), usuario.getUsuIUsuarioId(), Utils.getDatePhone(), null);

    }

    private void generarVentaContado() {

        Long cajaComprobanteId = CajaComprobante.findWithQuery(CajaComprobante.class, Utils.getQueryNumberCajaComprobante(), null).get(Constants.CURRENT).getCajCompId();
        Long cajaPagoId = CajaPago.findWithQuery(CajaPago.class, Utils.getQueryNumberCajaPago(), null).get(Constants.CURRENT).getCajPagId();
        cajaComprobante = new CajaComprobante(cajaComprobanteId, cajaMovimiento.getCajMovId(), comprobanteVenta.getCompId(), comprobanteVenta.getBaseImponible(), usuario.getUsuIUsuarioId(), Utils.getDatePhone());
        cajaPago = new CajaPago(cajaPagoId, comprobanteVenta.getBaseImponible(), cajaMovimiento.getCajMovId(), usuario.getUsuIUsuarioId(), Utils.getDatePhone(), Constants.NO_EXPORTADO, getTipoPago().getIdConcepto(), Constants.ESTADO_FALSE);

    }


    private List<ComprobanteVentaDetalle> getComprobanteVentaDetalleList(ComprobanteVenta comprobanteVenta) {
        List<ComprobanteVentaDetalle> comprobanteVentaDetalles = new ArrayList<>();
        List<Despacho> despachos = new ManipuleData().getDespachosToFactura(this);

        int comprobanteVentaId = ComprobanteVentaDetalle.findWithQuery(ComprobanteVentaDetalle.class, Utils.getQueryNumberComprobanteVentaDetalle(), null).get(Constants.CURRENT).getCompdId();

        for (Despacho despacho : despachos) {
            ProductoUnidad productoUnidad = ProductoUnidad.find(ProductoUnidad.class, " pro_Id=? ", new String[]{despacho.getProId() + ""}).get(Constants.CURRENT);
            comprobanteVentaDetalles.add(new ComprobanteVentaDetalle(comprobanteVentaId, comprobanteVenta.getCompId(), despacho.getProId(), productoUnidad.getUnId(), despacho.getCantidadDespachada(), despacho.getPrecioUnitarioSIGV(), despacho.getPrecioUNitarioCIGV(), despacho.getCostoVenta(), despacho.getImporte(), usuario.getUsuIUsuarioId(), Utils.getDatePhone(), despacho.getDespachoId()));
            comprobanteVentaId++;
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
        initDespachos(new ManipuleData().getDespachoResumen(this));
        initTipoComprobante();
        initFormaPago();
        initTipoPago();
        initRadios();
        setTextDireccion();
        initCheckBox();
        initImportesDetalle();
        setScop();
        buttonCuotas.setOnClickListener(this);

        if (cliente.getCliIModalidadCreditoId() <= 0) {
            conceptoCredito = new Concepto();
            conceptoCredito.setAbreviatura("0");

            Toast.makeText(SellActivity.this, "No tiene modalidad de credito", Toast.LENGTH_SHORT).show();
            enableButtonCredito(false);
        } else {
            conceptoCredito = Concepto.find(Concepto.class, " ID_CONCEPTO = ? ", new String[]{cliente.getCliIModalidadCreditoId() + ""}).get(Constants.CURRENT);

        }
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
        // List<Concepto> conceptoList = Concepto.find(Concepto.class, " CONCEPTO = ? AND  OBJETO = ? ", new String[]{Constants.CONCEPTO_FORMA_PAGO, Constants.CONCEPTO_FORMA_PAGO_COMPROBANTE_VENTA});
        List<Concepto> conceptoList = Concepto.findWithQuery(Concepto.class, " SELECT * FROM CONCEPTO WHERE CONCEPTO = ? AND  OBJETO = ?  ORDER BY id_Concepto desc ", new String[]{Constants.CONCEPTO_FORMA_PAGO, Constants.CONCEPTO_FORMA_PAGO_COMPROBANTE_VENTA});

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

            if (cliente.getCliDOCreditoDisponible() < obtenerCalculos()[Constants.VENTA_TOTAL]) {

                new DialogGeneral(SellActivity.this).setTextButtons("SI", "NO").setMessages("Atencion", "¿Esta seguro de solicitar credito?").setCancelable(true).showDialog(new DialogGeneralListener() {
                    @Override
                    public void onSavePressed() {
                        cliente.setCliDOCreditoDisponible(10000.00);

                    }

                    @Override
                    public void onCancelPressed() {

                    }
                });

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
                    this.planPagoDetalles.get(i).setCajMovId(cajaMovimiento.getCajMovId());
                }

                Toast.makeText(this, "TAMAÑO" + this.planPagoDetalles.size(), Toast.LENGTH_SHORT).show();

            }

        }
    }

    @Override
    public void manipulateInTransaction() {
        saveVenta();
    }

    @Override
    public void errorInTransaction(String error) {
        Toast.makeText(SellActivity.this, "Error: " + error, Toast.LENGTH_LONG).show();
    }
}
