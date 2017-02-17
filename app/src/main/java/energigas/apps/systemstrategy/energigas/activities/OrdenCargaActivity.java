package energigas.apps.systemstrategy.energigas.activities;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.adapters.ConceptoAdapter;
import energigas.apps.systemstrategy.energigas.adapters.CustomTabsAdapter;
import energigas.apps.systemstrategy.energigas.adapters.OrdenCargaAdapter;
import energigas.apps.systemstrategy.energigas.adapters.ProductoAdapter;
import energigas.apps.systemstrategy.energigas.adapters.ProveedorAdapter;
import energigas.apps.systemstrategy.energigas.adapters.UnidadAdapter;
import energigas.apps.systemstrategy.energigas.entities.Almacen;
import energigas.apps.systemstrategy.energigas.entities.CajaLiquidacion;
import energigas.apps.systemstrategy.energigas.entities.Concepto;
import energigas.apps.systemstrategy.energigas.entities.Despacho;
import energigas.apps.systemstrategy.energigas.entities.OrdenCargo;
import energigas.apps.systemstrategy.energigas.entities.Persona;
import energigas.apps.systemstrategy.energigas.entities.Producto;
import energigas.apps.systemstrategy.energigas.entities.Proveedor;
import energigas.apps.systemstrategy.energigas.entities.SyncEstado;
import energigas.apps.systemstrategy.energigas.entities.Unidad;
import energigas.apps.systemstrategy.energigas.entities.Usuario;
import energigas.apps.systemstrategy.energigas.entities.Vehiculo;
import energigas.apps.systemstrategy.energigas.entities.VehiculoUsuario;
import energigas.apps.systemstrategy.energigas.fragments.CajaGastoFragment;
import energigas.apps.systemstrategy.energigas.interfaces.OrdenCargoListener;
import energigas.apps.systemstrategy.energigas.ordencarga.OrdenCargaView;
import energigas.apps.systemstrategy.energigas.utils.Constants;
import energigas.apps.systemstrategy.energigas.utils.Session;
import energigas.apps.systemstrategy.energigas.utils.Utils;

public class OrdenCargaActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, OrdenCargaView, OrdenCargoListener {

    private static final String TAG = OrdenCargaActivity.class.getSimpleName();

    @BindView(R.id.spn_tipocarga)
    Spinner spnTipoCarga;
    @BindView(R.id.spn_trasciego_tipoorigen)
    Spinner spnTipoOrigen;
    @BindView(R.id.spn_producto)
    Spinner spnProduto;
    @BindView(R.id.spn_unidadmedida)
    Spinner spnUnidadMedida;
    @BindView(R.id.container_compra)
    LinearLayout lytCompra;
    @BindView(R.id.container_trasciego)
    LinearLayout lytTrasciego;


    @BindView(R.id.til_compra_ruc)
    TextInputLayout tilCompraRuc;
    @BindView(R.id.et_compra_ruc)
    AppCompatAutoCompleteTextView actCompraRuc;
    @BindView(R.id.et_compra_nombrecomercial)
    AppCompatAutoCompleteTextView actCompraNombreComercial;
    @BindView(R.id.et_compra_factura_serie)
    EditText etCompraFacturaSerie;
    @BindView(R.id.til_compra_factura_serie)
    TextInputLayout tilCompraFacturaSerie;

    @BindView(R.id.til_compra_factura_correlativo)
    TextInputLayout tilCompraFacturaCorrelativo;
    @BindView(R.id.et_compra_factura_correlativo)
    EditText etCompraFacturaCorrelativo;


    @BindView(R.id.til_compra_guia_serie)
    TextInputLayout tilCompraGuiaSerie;
    @BindView(R.id.et_compra_guia_serie)
    EditText etCompraGuiaSerie;
    @BindView(R.id.til_compra_guia_correlativo)
    TextInputLayout tilCompraGuiaCorrelativo;
    @BindView(R.id.et_compra_guia_correlativo)
    EditText etCompraGuiaCorrelativo;

    @BindView(R.id.til_trasciego_guia_serie)
    TextInputLayout tilTrasciegoGuiaSerie;
    @BindView(R.id.et_trasciego_guia_serie)
    EditText etTrasciegoGuiaSerie;
    @BindView(R.id.til_trasciego_guia_correlativo)
    TextInputLayout tilTrasciegoGuiaCorrelativo;
    @BindView(R.id.et_trasciego_guia_correlativo)
    EditText etTrasciegoGuiaCorrelativo;


    @BindView(R.id.btn_compra_factura_fechaemision)
    AppCompatButton btnCompraFacturaEmision;
    @BindView(R.id.btn_compra_guia_fechaemision)
    AppCompatButton btnCompraGuiaEmision;


    @BindView(R.id.btn_trasciego_guia_fechaemision)
    AppCompatButton btnTrasciegoGuiaEmision;


    @BindView(R.id.et_factorconversion)
    EditText etFactorConversion;
    @BindView(R.id.et_cantidad)
    EditText etCantidad;
    @BindView(R.id.et_densidad)
    EditText etDensidad;
    @BindView(R.id.et_precio)
    EditText etPrecio;

    @BindView(R.id.til_cantidad)
    TextInputLayout tilCantidad;
    @BindView(R.id.til_factorconversion)
    TextInputLayout tilFactorconversion;
    @BindView(R.id.til_densidad)
    TextInputLayout tilDensidad;
    @BindView(R.id.til_precio)
    TextInputLayout tilPrecio;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    ProveedorAdapter proveedorAdapterRuc;
    ProveedorAdapter proveedorAdapterNombreComercial;

    private final static int COMPRA_FECHA_COMPROBANTE = 100;
    private final static int COMPRA_FECHA_GUIA = 101;
    private final static int TRASCIEGO_FECHA_GUIA = 102;

    private int tipoFecha;

    private CajaLiquidacion cajaLiquidacion;
    private Almacen almacenes;
    private Usuario usuario;

    /*
        @BindView(R.id.recycler)
        RecyclerView recycler;*/
    OrdenCargaAdapter ordenCargaAdapter;
    private List<OrdenCargo> ordenCargos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orden_carga);
        ButterKnife.bind(this);
        cajaLiquidacion = CajaLiquidacion.getCajaLiquidacion(Session.getCajaLiquidacion(this).getLiqId() + "");
        usuario = Usuario.getUsuario(Session.getSession(this).getUsuIUsuarioId() + "");
        almacenes = almacenes.getAlmacenByUser(usuario.getUsuIUsuarioId()+"");
        Log.d(TAG, "VEHICULOID: " + almacenes.getVehiculoId());
        initVies();

    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        setTitle(R.string.collect_title_inventory);
        toolbar.setTitle(R.string.collect_title_inventory);
        if (getSupportActionBar() != null) //
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

    }


    @OnClick(R.id.btn_compra_factura_fechaemision)
    public void selectCompraFacturaFechaEmision() {
        tipoFecha = COMPRA_FECHA_COMPROBANTE;
        createTimePicker();
    }

    @OnClick(R.id.btn_compra_guia_fechaemision)
    public void selectCompraGuiaFechaEmision() {
        tipoFecha = COMPRA_FECHA_GUIA;
        createTimePicker();
    }

    @OnClick(R.id.btn_trasciego_guia_fechaemision)
    public void selectTrasciegoGuiaFechaEmision() {
        tipoFecha = TRASCIEGO_FECHA_GUIA;
        createTimePicker();
    }


    private void createTimePicker() {
        Calendar now = Calendar.getInstance();
        DatePickerDialog dpd = DatePickerDialog.newInstance(
                OrdenCargaActivity.this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );
        dpd.show(getFragmentManager(), "Datepickerdialog");
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        Log.d(TAG, " int year, int monthOfYear, int dayOfMonth; " + year + ", " + monthOfYear + ", " + dayOfMonth);
        switch (tipoFecha) {
            case COMPRA_FECHA_COMPROBANTE:
                btnCompraFacturaEmision.setText(dayOfMonth + "/" + monthOfYear + "/" + year);
                btnCompraFacturaEmision.setError(null);
                break;
            case COMPRA_FECHA_GUIA:
                btnCompraGuiaEmision.setText(dayOfMonth + "/" + monthOfYear + "/" + year);
                btnCompraGuiaEmision.setError(null);
                break;
            case TRASCIEGO_FECHA_GUIA:
                btnTrasciegoGuiaEmision.setText(dayOfMonth + "/" + monthOfYear + "/" + year);
                btnTrasciegoGuiaEmision.setError(null);
                break;
        }
    }

    @Override
    public void initVies() {
        initAutocompleteRuc();
        initAutoCumpleteNombreComercial();
        initSpinnerTipoCarga();
        initSpinnerTipoOrigen();
        initSpinnerProducto();
        initSpinnerUnidadMedia();
        initEdittext();
        initToolbar();
        // initRecycler();

    }

    /*
        private void initRecycler() {
            ordenCargaAdapter = new OrdenCargaAdapter(this, OrdenCargo.findWithQuery(OrdenCargo.class, "select * from orden_Cargo ORDER BY id DESC"), this);
            recycler.setAdapter(ordenCargaAdapter);
            recycler.setLayoutManager(new LinearLayoutManager(this));
        }
    */
    private void initEdittext() {
        actCompraRuc.addTextChangedListener(watcherCompraRuc);
        etCompraFacturaSerie.addTextChangedListener(watcherCompraFacturaSerie);
        etCompraFacturaCorrelativo.addTextChangedListener(watcherCompraFacturaCorrelativo);
        etCompraGuiaSerie.addTextChangedListener(watcherCompraGuiaSerie);
        etCompraGuiaCorrelativo.addTextChangedListener(watcherCompraGuiaCorrelativo);
        etTrasciegoGuiaSerie.addTextChangedListener(watcherTrasciegoGuiaSerie);
        etTrasciegoGuiaCorrelativo.addTextChangedListener(watcherTrasciegoGuiaCorrelativo);
        etCantidad.addTextChangedListener(watcherCantidad);
        etDensidad.addTextChangedListener(watcherDensidad);
        etFactorConversion.addTextChangedListener(watcherFactor);
        etPrecio.addTextChangedListener(watcherPrecio);
    }

    @Override
    public void initSpinnerTipoCarga() {
        ConceptoAdapter conceptoAdapter = new ConceptoAdapter(this, 0, Concepto.getConceptos("Orden Cargo", "Tipo Orden"));
        spnTipoCarga.setAdapter(conceptoAdapter);
        spnTipoCarga.setOnItemSelectedListener(tipoCargaSelectedItemListener);
    }

    @Override
    public void initSpinnerTipoOrigen() {
        ConceptoAdapter conceptoAdapter = new ConceptoAdapter(this, 0, Concepto.getConceptos("Orden Cargo", "Tipo Origen"));
        spnTipoOrigen.setAdapter(conceptoAdapter);
    }

    @Override
    public void initSpinnerProducto() {
        ProductoAdapter productoAdapter = new ProductoAdapter(this, 0, Producto.getAllProducto());
        spnProduto.setAdapter(productoAdapter);
    }

    @Override
    public void initSpinnerUnidadMedia() {
        UnidadAdapter adapter = new UnidadAdapter(this, 0, Unidad.getAllUnidad());
        spnUnidadMedida.setAdapter(adapter);
        spnUnidadMedida.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Unidad unidad = (Unidad) adapterView.getItemAtPosition(i);
                String abrev = unidad.getAbreviatura().toLowerCase();
                Log.d(TAG, "abrev: " + abrev);
                switch (abrev) {
                    case "kg":
                        etFactorConversion.setText("");
                        break;
                    case "gl":
                        etFactorConversion.setText("1");
                        break;
                    default:
                        Log.d(TAG, " tipoCargaSelectedItemListener onItemSelected default");

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void initAutocompleteRuc() {
        proveedorAdapterRuc = new ProveedorAdapter(this, ProveedorAdapter.DOCUMENTO_IDENTIDAD, Proveedor.getProveedorList());
        actCompraRuc.setAdapter(proveedorAdapterRuc);
        actCompraRuc.setOnItemClickListener(proveedorItemSelectedListener);
    }

    AdapterView.OnItemClickListener proveedorItemSelectedListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            Proveedor proveedor = proveedorAdapterRuc.getItem(i);
            actCompraRuc.setText(proveedor.getPersona().getPerVDocIdentidad());
            actCompraNombreComercial.setText(proveedor.getPersona().getNombreComercial());
        }

    };

    AdapterView.OnItemClickListener proveedorNCItemSelectedListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            Proveedor proveedor = proveedorAdapterNombreComercial.getItem(i);
            actCompraRuc.setText(proveedor.getPersona().getPerVDocIdentidad());
            actCompraNombreComercial.setText(proveedor.getPersona().getNombreComercial());
        }

    };


    @Override
    public void initAutoCumpleteNombreComercial() {
        proveedorAdapterNombreComercial = new ProveedorAdapter(this, ProveedorAdapter.NOMBRE_COMERCIAL, Proveedor.getProveedorList());
        actCompraNombreComercial.setAdapter(proveedorAdapterNombreComercial);
        actCompraNombreComercial.setOnItemClickListener(proveedorNCItemSelectedListener);

    }

    private AdapterView.OnItemSelectedListener tipoCargaSelectedItemListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            Concepto concepto = (Concepto) adapterView.getItemAtPosition(i);
            String descripcion = concepto.getDescripcion();
            Log.d(TAG, "descripcion: " + descripcion);
            switch (descripcion) {
                case "Compra":
                    mostrarCompraView();
                    break;
                case "Trasciego":
                    mostrarTrasciegoView();
                    break;
                default:
                    Log.d(TAG, " tipoCargaSelectedItemListener onItemSelected default");
                    break;
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {
            Log.d(TAG, "tipoCargaSelectedItemListener onNothingSelected");
        }
    };

    @Override
    public void mostrarCompraView() {
        lytCompra.setVisibility(View.VISIBLE);
        lytTrasciego.setVisibility(View.GONE);
    }

    @Override
    public void mostrarTrasciegoView() {
        lytTrasciego.setVisibility(View.VISIBLE);
        lytCompra.setVisibility(View.GONE);
    }


    @OnClick(R.id.btn_ordencompra_cancelar)
    public void cancelar() {
        startActivity(new Intent(this, OrdenCargaListActivity.class));
    }

    @OnClick(R.id.btn_ordencompra_save)
    @Override
    public void guardarOrdenCarga() {
        Concepto concepto = (Concepto) spnTipoCarga.getSelectedItem();
        switch (concepto.getDescripcion().toLowerCase()) {
            case "compra":
                guardarCompra();
                break;
            case "trasciego":
                guardarTrasciego();
                break;
        }
    }

    private boolean validarTrasciego() {
        return false;
    }

    private boolean esProveedorValido(String ruc) {
        List<Persona> personas = Persona.find(Persona.class, "per_V_Doc_Identidad = ?", ruc);
        if (personas == null || personas.size() < 1) {
            tilCompraRuc.setError("Ruc Inválido");
            return false;
        }
        tilCompraRuc.setError(null);
        return true;
    }

    public void guardarTrasciego() {
        Concepto tipoCarga = (Concepto) spnTipoCarga.getSelectedItem();
        Producto producto = (Producto) spnProduto.getSelectedItem();
        Unidad unidad = (Unidad) spnUnidadMedida.getSelectedItem();
        Concepto tipoOrigen = (Concepto) spnTipoOrigen.getSelectedItem();

        String serieGuia = etTrasciegoGuiaSerie.getText().toString();
        String correlativoGuia = etTrasciegoGuiaCorrelativo.getText().toString();
        String guiaFecha = btnTrasciegoGuiaEmision.getText().toString();


        boolean esGuiaSerieValida = esGuiaTrasciegoSerieValida(serieGuia);
        boolean esGuiaCorrelativoValido = esGuiaTrasciegoCorrelativoValido(correlativoGuia);
        boolean esFechaGuiaValida = esGuiaTrasciegoComprobanteValida(guiaFecha);
        boolean sonCamposGeneralesValidos = validarCamposGenerales();
        Log.d(TAG, "esGuiaSerieValida:" + esGuiaSerieValida + "esGuiaCorrelativoValido:" + esGuiaCorrelativoValido + "esFechaGuiaValida:" + esFechaGuiaValida + "sonCamposGeneralesValidos:" + sonCamposGeneralesValidos);
        if (esGuiaSerieValida && esGuiaCorrelativoValido && esFechaGuiaValida &&
                sonCamposGeneralesValidos) {

            String strCantidad = etCantidad.getText().toString();
            String strDensidad = etDensidad.getText().toString();
            String strPrecio = etPrecio.getText().toString();
            String strFactor = etFactorConversion.getText().toString();

            Double cantidad = Double.parseDouble(strCantidad);
            Double densidad = Double.parseDouble(strDensidad);
            Double precio = Double.parseDouble(strPrecio);
            Double factor = Double.parseDouble(strFactor);


            String datetime = Utils.getDatePhoneWithTime();
            Double cantidadTransformada = cantidad / factor;

            String format = "%.4f";

            int usuarioId = Session.getSession(this).getUsuIUsuarioId();
            Double StockMaximo = almacenes.getCapacidadReal() - almacenes.getStockMinimo();
            Log.d(TAG, "StockMaximo : " + StockMaximo);
            if (cantidadTransformada < StockMaximo) {


                Long getOrdeCarga_Id = OrdenCargo.findWithQuery(OrdenCargo.class, Utils.getQueryNumberOrderCargo(), null).get(Constants.CURRENT).getOrdeCargaId();
                OrdenCargo ordenCargo = new OrdenCargo(
                        0,
                        getOrdeCarga_Id,
                        datetime,
                        "",
                        0,
                        "",
                        serieGuia + "-" + correlativoGuia,
                        tipoCarga.getIdConcepto(),
                        tipoOrigen.getIdConcepto(),
                        Utils.formatDouble(format, factor),
                        guiaFecha,
                        Utils.formatDouble(format, densidad),
                        producto.getProId(),
                        unidad.getUnId(),
                        Utils.formatDouble(format, cantidad),
                        1,
                        cantidadTransformada,
                        usuarioId,
                        datetime,
                        69,
                        usuarioId,
                        datetime,
                        Utils.formatDouble(format, precio),
                        cajaLiquidacion.getLiqId()
                );
                saveOrdenCargo(ordenCargo);
            }else if (cantidadTransformada>StockMaximo){
                etCantidad.setError("Stock Maximo");
            }
            else {
                    Snackbar.make(etCantidad, "Revise los campos", Snackbar.LENGTH_LONG).show();
            }
        }
    }

    private void saveOrdenCargo(OrdenCargo ordenCargo) {
        long id = ordenCargo.save();
        Log.d(TAG, "ordenCargo.save : " + id);
        ordenCargo.setOrdenCargaId(id);
        ordenCargo.save();
        CajaLiquidacion liquidacion = CajaLiquidacion.getCajaLiquidacion(Session.getCajaLiquidacion(this).getLiqId() + "");
        if (liquidacion != null) {

            liquidacion.setStockInicial(ordenCargo.getCantidadTransformada());
            liquidacion.save();

            Almacen almacen = Almacen.find(Almacen.class, "alm_Id = ?", "" + liquidacion.getAlmId()).get(0);
            Double stockActual = almacen.getStockActual();
            almacen.setStockActual(stockActual + ordenCargo.getCantidadTransformada());
            almacen.save();
            startActivity(new Intent(this, OrdenCargaListActivity.class));
            Snackbar.make(etCantidad, "Guardado", Snackbar.LENGTH_LONG).show();
        } else {
            Snackbar.make(etCantidad, "Error al guardar Orden de Cargo", Snackbar.LENGTH_LONG).show();
        }
        //Orden de cargo
        new SyncEstado(0, Utils.separteUpperCase(OrdenCargo.class.getSimpleName()), Integer.parseInt(ordenCargo.getId() + ""), Constants.S_CREADO).save();

        cleanViews();
        //initRecycler();
    }

    private boolean esGuiaTrasciegoComprobanteValida(String guiaFecha) {
        if (guiaFecha.toLowerCase().contains("fecha")) {
            btnTrasciegoGuiaEmision.setError("Inválido");
            return false;
        }
        btnTrasciegoGuiaEmision.setError(null);
        return true;
    }

    private boolean esGuiaTrasciegoCorrelativoValido(String correlativoGuia) {
        if (correlativoGuia.length() > 8) {
            tilTrasciegoGuiaCorrelativo.setError("Inválido");
            return false;
        }
        tilTrasciegoGuiaCorrelativo.setError(null);
        return true;
    }

    private boolean esGuiaTrasciegoSerieValida(String serieGuia) {
        if (serieGuia.length() != 4) {
            tilTrasciegoGuiaSerie.setError("Inválido");
            return false;
        }
        tilTrasciegoGuiaSerie.setError(null);
        return true;
    }

    private boolean validarCamposGenerales() {
        String cantidad = etCantidad.getText().toString();
        String densidad = etDensidad.getText().toString();
        String precio = etPrecio.getText().toString();
        String factor = etFactorConversion.getText().toString();


        boolean esCantidadValido = esCantidadValido(cantidad);
        boolean esDensidadValido = esDensidadValido(densidad);
        boolean esPrecioValido = esPrecioValido(precio);
        boolean esFactorValido = esFactorValido(factor);
        return (esCantidadValido && esDensidadValido && esPrecioValido && esFactorValido);
    }

    private AppCompatActivity getActivity() {
        return this;
    }

    public void guardarCompra() {
        Concepto tipoCarga = (Concepto) spnTipoCarga.getSelectedItem();
        Producto producto = (Producto) spnProduto.getSelectedItem();
        Unidad unidad = (Unidad) spnUnidadMedida.getSelectedItem();

        String ruc = actCompraRuc.getText().toString();
        String serieFactura = etCompraFacturaSerie.getText().toString();
        String correlativoFactura = etCompraFacturaCorrelativo.getText().toString();
        String serieGuia = etCompraGuiaSerie.getText().toString();
        String correlativoGuia = etCompraGuiaCorrelativo.getText().toString();
        String comprobanteFecha = btnCompraFacturaEmision.getText().toString();
        String guiaFecha = btnCompraGuiaEmision.getText().toString();


        boolean esProveedorValido = esProveedorValido(ruc);
        boolean esSerieValida = esSerieValida(serieFactura);
        boolean esCorrelativoValido = esCorrelativoValido(correlativoFactura);
        boolean esGuiaSerieValida = esGuiaSerieValida(serieGuia);
        boolean esGuiaCorrelativoValido = esGuiaCorrelativoValido(correlativoGuia);
        boolean esFechaComprobanteValida = esFechaComprobanteValida(comprobanteFecha);
        boolean esFechaGuiaValida = esGuiaComprobanteValida(guiaFecha);
        boolean sonCamposGeneralesValidos = validarCamposGenerales();

        if (esProveedorValido && esSerieValida && esCorrelativoValido && esGuiaSerieValida &&
                esGuiaCorrelativoValido && esFechaComprobanteValida && esFechaGuiaValida &&
                sonCamposGeneralesValidos) {
            //SAVE!
            Proveedor proveedor = getProveedor(ruc);

            String datetime = Utils.getDatePhoneWithTime();
            Log.d(TAG, "fechaRegistro: " + datetime);


            String strCantidad = etCantidad.getText().toString();
            String strDensidad = etDensidad.getText().toString();
            String strPrecio = etPrecio.getText().toString();
            String strFactor = etFactorConversion.getText().toString();

            Double cantidad = Double.parseDouble(strCantidad);
            Double densidad = Double.parseDouble(strDensidad);
            Double precio = Double.parseDouble(strPrecio);
            Double factor = Double.parseDouble(strFactor);


            Double cantidadTransformada = cantidad / factor;

            String format = "%.4f";

            int usuarioId = Session.getSession(this).getUsuIUsuarioId();
            long getOrdeCarga_Id = OrdenCargo.findWithQuery(OrdenCargo.class, Utils.getQueryNumberOrderCargo(), null).get(Constants.CURRENT).getOrdeCargaId();
            OrdenCargo ordenCargo = new OrdenCargo(
                    0,
                    getOrdeCarga_Id,
                    datetime,
                    comprobanteFecha,
                    proveedor.getProveedorId(),
                    serieFactura + "-" + correlativoFactura,
                    serieGuia + "-" + correlativoGuia,
                    tipoCarga.getIdConcepto(),
                    0,
                    Utils.formatDouble(format, factor),
                    guiaFecha,
                    Utils.formatDouble(format, densidad),
                    producto.getProId(),
                    unidad.getUnId(),
                    Utils.formatDouble(format, cantidad),
                    1,
                    Utils.formatDouble(format, cantidadTransformada),
                    usuarioId,
                    datetime,
                    69,
                    usuarioId,
                    datetime,
                    Utils.formatDouble(format, precio),
                    cajaLiquidacion.getLiqId()
            );
            saveOrdenCargo(ordenCargo);
            Log.d(TAG, " getOrdeCarga_Id :" + ordenCargo.getOrdeCargaId());
        } else {
            Snackbar.make(etCantidad, "Revise los campos", Snackbar.LENGTH_LONG).show();
        }


    }


    private Proveedor getProveedor(String ruc) {
        List<Persona> personas = Persona.find(Persona.class, "per_V_Doc_Identidad = ?", ruc);
        Persona persona = personas.get(0);
        return Proveedor.find(Proveedor.class, "persona_id = ?", "" + persona.getPerIPersonaId()).get(0);
    }

    private boolean esFactorValido(String factor) {
        Double x = 0.00;
        try {
            x = Double.parseDouble(factor);
        } catch (Exception e) {
            tilFactorconversion.setError("Inválido");
            return false;
        }
        tilFactorconversion.setError(null);
        return true;
    }

    private boolean esPrecioValido(String precio) {
        Double x = 0.00;
        try {
            x = Double.parseDouble(precio);
        } catch (Exception e) {
            tilPrecio.setError("Inválido");
            return false;
        }
        tilPrecio.setError(null);
        return true;
    }

    private boolean esDensidadValido(String densidad) {
        Double x = 0.00;
        try {
            x = Double.parseDouble(densidad);
        } catch (Exception e) {
            tilDensidad.setError("Inválido");
            return false;
        }
        tilDensidad.setError(null);
        return true;
    }

    private boolean esCantidadValido(String cantidad) {
        Double x = 0.00;
        try {
            x = Double.parseDouble(cantidad);
        } catch (Exception e) {
            tilCantidad.setError("Inválido");
            return false;
        }
        tilCantidad.setError(null);
        return true;
    }

    private boolean esGuiaComprobanteValida(String guiaFecha) {
        if (guiaFecha.toLowerCase().contains("fecha")) {
            btnCompraGuiaEmision.setError("Inválido");
            return false;
        }
        btnCompraGuiaEmision.setError(null);
        return true;
    }

    private boolean esFechaComprobanteValida(String comprobanteFecha) {
        if (comprobanteFecha.toLowerCase().contains("fecha")) {
            btnCompraFacturaEmision.setError("Inválido");
            return false;
        }
        btnCompraFacturaEmision.setError(null);
        return true;
    }

    private boolean esGuiaCorrelativoValido(String correlativoGuia) {
        if (correlativoGuia.length() > 8) {
            tilCompraGuiaCorrelativo.setError("Inválido");
            return false;
        }
        tilCompraGuiaCorrelativo.setError(null);
        return true;
    }

    private boolean esGuiaSerieValida(String serieGuia) {
        if (serieGuia.length() != 4) {
            tilCompraGuiaSerie.setError("Inválido");
            return false;
        }
        tilCompraGuiaSerie.setError(null);
        return true;
    }

    private boolean esCorrelativoValido(String correlativoFactura) {
        if (correlativoFactura.length() > 8) {
            tilCompraFacturaCorrelativo.setError("Inválido");
            return false;
        }
        tilCompraFacturaCorrelativo.setError(null);
        return true;
    }

    private boolean esSerieValida(String serieFactura) {
        if (serieFactura.length() != 4) {
            tilCompraFacturaSerie.setError("Inválido");
            return false;
        }
        tilCompraFacturaSerie.setError(null);
        return true;
    }


    TextWatcher watcherCompraRuc = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            Log.d(TAG, "watcherCompraRuc charSequence: " + charSequence);
            tilCompraRuc.setError(null);
        }


        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    TextWatcher watcherCompraFacturaSerie = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            Log.d(TAG, "watcherCompraRuc charSequence: " + charSequence);
            esSerieValida(charSequence.toString());
        }


        @Override
        public void afterTextChanged(Editable editable) {

        }
    };
    TextWatcher watcherCompraFacturaCorrelativo = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            Log.d(TAG, "watcherCompraFacturaCorrelativo charSequence: " + charSequence);
            esCorrelativoValido(charSequence.toString());
        }


        @Override
        public void afterTextChanged(Editable editable) {

        }
    };


    TextWatcher watcherCompraGuiaSerie = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            Log.d(TAG, "watcherCompraGuiaSerie charSequence: " + charSequence);
            esGuiaSerieValida(charSequence.toString());
        }


        @Override
        public void afterTextChanged(Editable editable) {

        }
    };


    TextWatcher watcherCompraGuiaCorrelativo = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            Log.d(TAG, "watcherCompraGuiaCorrelativo charSequence: " + charSequence);
            esGuiaCorrelativoValido(charSequence.toString());
        }


        @Override
        public void afterTextChanged(Editable editable) {

        }
    };
    TextWatcher watcherTrasciegoGuiaSerie = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            Log.d(TAG, "watcherTrasciegoGuiaSerie charSequence: " + charSequence);
            esGuiaTrasciegoSerieValida(charSequence.toString());
        }


        @Override
        public void afterTextChanged(Editable editable) {

        }
    };


    TextWatcher watcherTrasciegoGuiaCorrelativo = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            Log.d(TAG, "watcherTrasciegoGuiaCorrelativo charSequence: " + charSequence);
            esGuiaTrasciegoCorrelativoValido(charSequence.toString());
        }


        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    TextWatcher watcherCantidad = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            Log.d(TAG, "watcherCantidad charSequence: " + charSequence);
            esCantidadValido(charSequence.toString());
        }


        @Override
        public void afterTextChanged(Editable editable) {

        }
    };
    TextWatcher watcherPrecio = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            Log.d(TAG, "watcherPrecio charSequence: " + charSequence);
            esPrecioValido(charSequence.toString());
        }


        @Override
        public void afterTextChanged(Editable editable) {

        }
    };
    TextWatcher watcherFactor = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            Log.d(TAG, "watcherFactor charSequence: " + charSequence);
            esFactorValido(charSequence.toString());
        }


        @Override
        public void afterTextChanged(Editable editable) {

        }
    };
    TextWatcher watcherDensidad = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            Log.d(TAG, "watcherDensidad charSequence: " + charSequence);
            esDensidadValido(charSequence.toString());
        }


        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    private void cleanViews() {
        actCompraRuc.setText("");
        actCompraNombreComercial.setText("");
        etCompraFacturaSerie.setText("");
        etCompraFacturaCorrelativo.setText("");
        etCompraGuiaSerie.setText("");
        etCompraGuiaCorrelativo.setText("");
        etCantidad.setText("");
        etFactorConversion.setText("");
        etDensidad.setText("");
        etPrecio.setText("");

        btnCompraFacturaEmision.setError(null);
        btnCompraGuiaEmision.setError(null);
        btnTrasciegoGuiaEmision.setError(null);

        tilCompraRuc.setError(null);
        tilCompraFacturaSerie.setError(null);
        tilCompraFacturaCorrelativo.setError(null);
        tilCompraGuiaSerie.setError(null);
        tilCompraGuiaCorrelativo.setError(null);
        tilFactorconversion.setError(null);
        tilTrasciegoGuiaSerie.setError(null);
        tilTrasciegoGuiaCorrelativo.setError(null);
        tilCantidad.setError(null);
        tilFactorconversion.setError(null);
        tilDensidad.setError(null);
        tilPrecio.setError(null);
    }

    @Override
    public void onOrdenCargoClickListener(OrdenCargo ordenCargo) {
        Intent intent = new Intent(this, PrintOrdenCarga.class);
        intent.putExtra("ORDECARGAID", ordenCargo.getOrdeCargaId() + "");
        startActivity(intent);
    }

    @Override
    public void onOrdenCargoLongClickListener(int position, OrdenCargo ordenCargo, View view, AlertDialog alertDialog) {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            startActivity(new Intent(this, OrdenCargaListActivity.class));
            //onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }




}
