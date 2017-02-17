package energigas.apps.systemstrategy.energigas.activities;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.adapters.ConceptoAdapter;
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
import energigas.apps.systemstrategy.energigas.holders.OrdenCargoHolder;
import energigas.apps.systemstrategy.energigas.interfaces.OrdenCargoListener;
import energigas.apps.systemstrategy.energigas.ordencarga.OrdenCargaView;
import energigas.apps.systemstrategy.energigas.utils.Constants;
import energigas.apps.systemstrategy.energigas.utils.Session;
import energigas.apps.systemstrategy.energigas.utils.Utils;

import static android.R.attr.width;

public class OrdenCargaListActivity extends AppCompatActivity implements OrdenCargoListener, DatePickerDialog.OnDateSetListener {

    private static final String TAG = "OrdenCargaListActivity";
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.TView_Agente)
    TextView textViewAgente;
    @BindView(R.id.TView_placa)
    TextView textViewPlaca;
    /*@BindView(R.id.ordencargo_serie_tanque)
    TextView textViewSerieTanque;*/
    OrdenCargaAdapter ordenCargaAdapter;

//    @BindView(R.id.container_compra)
//    LinearLayout lytCompra2;
//    @BindView(R.id.container_trasciego)
//    LinearLayout lytTrasciego;


    private Resources resources;

    private Persona mAgente;
    private Usuario mUsuario;
    private Vehiculo mVehiculo;
    private Despacho mainDispatch;
    private Almacen almacenes;
    AlertDialog alertDialog;
    /*Item Compra*/
    private LinearLayout lytCompra;
    private AutoCompleteTextView tilCompraRuc;
    private AutoCompleteTextView tilRazonSocial;
    private EditText etCompraFacturaSerie;
    private EditText etCompraFacturaCorrelativo;
    private EditText etCompraGuiaSerie;
    private EditText etCompraGuiaCorrelativo;
    /*Fin Compra*/
    private Spinner spnProduto;
    private Spinner spnUnidadMedida;
    private Spinner spnTipoOrigen;

    private TextView txtfconversion;
    private TextView txtdensidad;
    private TextView txtprecio;
    private TextView txtcantidad;
    private Button btAceptar;
    private Button btCancelar;
    private AppCompatButton btnCompraFacturaEmision;
    private AppCompatButton btnCompraGuiaEmision;


    private TextInputLayout tilCompraFacturaSerie;
    private TextInputLayout tilCompraFacturaCorrelativo;

    private TextInputLayout tilCompraGuiaSerie;
    private TextInputLayout tilCompraGuiaCorrelativo;

    private TextInputLayout tilCantidad;
    private TextInputLayout tilFactorconversion;
    private TextInputLayout tilDensidad;
    private TextInputLayout tilPrecio;


    /*Item Trasciego*/
    private LinearLayout lytTrasCiego;
    private TextInputLayout til_trasciego_guia_serie;
    private EditText et_trasciego_guia_serie;
    private TextInputLayout til_trasciego_guia_correlativo;
    private EditText et_trasciego_guia_correlativo;
    private AppCompatButton btnTrasciegoGuiaEmision;
    /*Fin TrasCiego*/

    private OrdenCargaAdapter adapter;


    private int tipoFecha;

    private final static int COMPRA_FECHA_COMPROBANTE = 100;
    private final static int COMPRA_FECHA_GUIA = 101;
    private final static int TRASCIEGO_FECHA_GUIA = 102;


    private ProveedorAdapter proveedorAdapterRuc;
    private ProveedorAdapter proveedorAdapterNombreComercial;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling_orden_carga);
        mAgente = Persona.findWithQuery(Persona.class, "SELECT P.* FROM PERSONA P, USUARIO U WHERE P.PER_I_PERSONA_ID = U.USU_I_PERSONA_ID AND U.USU_I_USUARIO_ID = " + Session.getSession(this).getUsuIUsuarioId() + " ;", null).get(Constants.CURRENT);
        mUsuario = Usuario.getUsuario(Session.getSession(this).getUsuIUsuarioId() + "");
        mVehiculo = Vehiculo.getVehiculo(mUsuario.getUsuIUsuarioId() + "");
        almacenes = almacenes.getAlmacenByUser(mUsuario.getUsuIUsuarioId()+"");
        Log.d(TAG, "VEHICULOID: " + almacenes.getVehiculoId());
//        mainDispatch = Despacho.find(Despacho.class, " despacho_Id=? ", new String[]{Session.getDespacho(this).getDespachoId() + ""}).get(Constants.CURRENT);

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

        textViewAgente.setText(mAgente.getPerVNombres() + " " + mAgente.getPerVApellidoPaterno() + " " + mAgente.getPerVApellidoMaterno());
        textViewPlaca.setText(Utils.getDateDescription(Utils.getDatePhone()));
        //textViewSerieTanque.setText("Serie");
        initRecycler();
        //toolBarImageBack();
        collapSingTitle();
    }

    private void toolBarImageBack() {

        if (getSupportActionBar() != null) //
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            //getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
    }


    private void initRecycler() {
//        ordenCargaAdapter = new OrdenCargaAdapter(this, OrdenCargo.findWithQuery(OrdenCargo.class, "select * from orden_Cargo ORDER BY id DESC"), this);
        ordenCargaAdapter = new OrdenCargaAdapter(this, OrdenCargo.findWithQuery(OrdenCargo.class, "select * from orden_Cargo where estado_id=69 ORDER BY id DESC"), this);
        Log.d(TAG, "CountList: " + ordenCargaAdapter.getItemCount());
        recycler.setAdapter(ordenCargaAdapter);
        recycler.setLayoutManager(new LinearLayoutManager(this));
    }


    @Override
    public void onOrdenCargoClickListener(OrdenCargo ordenCargo) {
        Intent intent = new Intent(this, PrintOrdenCarga.class);
        intent.putExtra("ORDECARGAID", ordenCargo.getOrdeCargaId() + "");
        startActivity(intent);
    }

    @Override
    public void onOrdenCargoLongClickListener(int position, OrdenCargo ordenCargo, View view, AlertDialog alertDialog) {
        switch (position) {
            case 1:
                metEdit(ordenCargo, view);
                break;
            case 2:
                if (ordenCargo != null) {
                    ordenCargo.setEstadoId(70);
                    Log.d(TAG, "OrdenCargoESTADOID: " + ordenCargo.getEstadoId());
                    ordenCargo.save();
                    int position2 = recycler.getChildAdapterPosition(view);
                    ((OrdenCargaAdapter) recycler.getAdapter()).removeAt(position2);
                    alertDialog.dismiss();
                    Snackbar.make(textViewPlaca, "Eliminado Exitosamente!", Snackbar.LENGTH_SHORT).show();
                } else {
                    Snackbar.make(textViewPlaca, "Error al Eliminar", Snackbar.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            startActivity(new Intent(this, MainActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

  /*  @Override
    public void onBackPressed() {
        super.onBackPressed();
    }*/

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume");
        initRecycler();
        super.onResume();
    }

    private void collapSingTitle() {
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
                    collapsingToolbarLayout.setTitle("Orden de Carga");
                    toolBarImageBack();
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbarLayout.setTitle(" ");//carefull there should a space between double quote otherwise it wont work
                    toolBarImageBack();
                    isShow = false;
                }
            }
        });
    }

    private void metEdit(final OrdenCargo ordenCargo, final View viewposition) {

        View layout_dialog_ordenCarga = View.inflate(this, R.layout.dialog_edit_ordencarga, null);

        spnProduto = (Spinner) layout_dialog_ordenCarga.findViewById(R.id.spn_producto);
        spnUnidadMedida = (Spinner) layout_dialog_ordenCarga.findViewById(R.id.spn_unidadmedida);
        spnTipoOrigen = (Spinner) layout_dialog_ordenCarga.findViewById(R.id.spn_trasciego_tipoorigen);
        /*Items Compra*/
        lytCompra = (LinearLayout) layout_dialog_ordenCarga.findViewById(R.id.container_compra);
        tilCompraRuc = (AutoCompleteTextView) layout_dialog_ordenCarga.findViewById(R.id.et_compra_ruc);
        tilRazonSocial = (AutoCompleteTextView) layout_dialog_ordenCarga.findViewById(R.id.et_compra_nombrecomercial);
        etCompraFacturaSerie = (EditText) layout_dialog_ordenCarga.findViewById(R.id.et_compra_factura_serie);
        etCompraFacturaCorrelativo = (EditText) layout_dialog_ordenCarga.findViewById(R.id.et_compra_factura_correlativo);
        btnCompraFacturaEmision = (AppCompatButton) layout_dialog_ordenCarga.findViewById(R.id.btn_compra_factura_fechaemision);
        btnCompraGuiaEmision = (AppCompatButton) layout_dialog_ordenCarga.findViewById(R.id.btn_compra_guia_fechaemision);
        etCompraGuiaSerie = (EditText) layout_dialog_ordenCarga.findViewById(R.id.et_compra_guia_serie);
        etCompraGuiaCorrelativo = (EditText) layout_dialog_ordenCarga.findViewById(R.id.et_compra_guia_correlativo);

        tilCompraFacturaSerie = (TextInputLayout) layout_dialog_ordenCarga.findViewById(R.id.til_compra_factura_serie);
        tilCompraFacturaCorrelativo = (TextInputLayout) layout_dialog_ordenCarga.findViewById(R.id.til_compra_factura_correlativo);

        tilCantidad = (TextInputLayout) layout_dialog_ordenCarga.findViewById(R.id.til_cantidad);
        tilFactorconversion = (TextInputLayout) layout_dialog_ordenCarga.findViewById(R.id.til_factorconversion);
        tilDensidad = (TextInputLayout) layout_dialog_ordenCarga.findViewById(R.id.til_densidad);
        tilPrecio = (TextInputLayout) layout_dialog_ordenCarga.findViewById(R.id.til_precio);


        tilCompraGuiaSerie = (TextInputLayout) layout_dialog_ordenCarga.findViewById(R.id.til_compra_guia_serie);
        tilCompraGuiaCorrelativo = (TextInputLayout) layout_dialog_ordenCarga.findViewById(R.id.til_compra_guia_correlativo);
        /*items TrasCiego*/
        til_trasciego_guia_serie = (TextInputLayout) layout_dialog_ordenCarga.findViewById(R.id.til_trasciego_guia_serie);
        et_trasciego_guia_serie = (EditText) layout_dialog_ordenCarga.findViewById(R.id.et_trasciego_guia_serie);
        til_trasciego_guia_correlativo = (TextInputLayout) layout_dialog_ordenCarga.findViewById(R.id.til_trasciego_guia_correlativo);
        et_trasciego_guia_correlativo = (EditText) layout_dialog_ordenCarga.findViewById(R.id.et_trasciego_guia_correlativo);
        btnTrasciegoGuiaEmision = (AppCompatButton) layout_dialog_ordenCarga.findViewById(R.id.btn_trasciego_guia_fechaemision);

        btAceptar = (Button) layout_dialog_ordenCarga.findViewById(R.id.btn_order_guardar);
        btCancelar = (Button) layout_dialog_ordenCarga.findViewById(R.id.btn_cancel);


        lytTrasCiego = (LinearLayout) layout_dialog_ordenCarga.findViewById(R.id.container_trasciego);


        txtfconversion = (TextView) layout_dialog_ordenCarga.findViewById(R.id.et_factorconversion);
        txtdensidad = (TextView) layout_dialog_ordenCarga.findViewById(R.id.et_densidad);
        txtprecio = (TextView) layout_dialog_ordenCarga.findViewById(R.id.et_precio);
        txtcantidad = (TextView) layout_dialog_ordenCarga.findViewById(R.id.et_cantidad);

        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        final Dialog alertDialog = adb.setView(layout_dialog_ordenCarga).create();
        // (That new View is just there to have something inside the dialog that can grow big enough to cover the whole screen.)


        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(alertDialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        alertDialog.show();
        alertDialog.getWindow().setAttributes(lp);

        Concepto concepto = Concepto.getConcepto(ordenCargo.getTipoCargaId() + "");

        String nmrGuia = ordenCargo.getNroGuia();
        String[] parts2 = nmrGuia.split("-");
        String nmr_guia = parts2[0]; // 004
        String nmr_relativo = parts2[1]; // 034556


        String CutDateCreacion = ordenCargo.getFechaCreacion();
        String [] cutcreacion2=CutDateCreacion.split(":");
        String part100 = cutcreacion2[0]; // 004
//
        String Date2= part100.substring(0,11);

        switch (concepto.getDescripcion().toLowerCase()) {
            case "compra":
                Proveedor mproveedor = Proveedor.getProveedorById(ordenCargo.getProveedorId());
                lytCompra.setVisibility(View.VISIBLE);
                lytTrasCiego.setVisibility(View.GONE);
                //seteamos
                String nmroComprobante = ordenCargo.getNroComprobante();
                String[] parts = nmroComprobante.split("-");
                String comproS = parts[0]; // 004
                String comproRela = parts[1]; // 034556

                /*String nmrGuia = ordenCargo.getNroGuia();
                String[] parts2 = nmrGuia.split("-");
                String nmr_guia = parts2[0]; // 004
                String nmr_relativo = parts2[1]; // 034556


                String CutDateCreacion = ordenCargo.getFechaCreacion();
                String [] cutcreacion2=CutDateCreacion.split(":");
                String part100 = cutcreacion2[0]; // 004*/

               // String Date2= part100.substring(0,11);
                etCompraGuiaSerie.setText(nmr_guia);
                etCompraGuiaCorrelativo.setText(nmr_relativo);

                etCompraFacturaSerie.setText(comproS);
                etCompraFacturaCorrelativo.setText(comproRela);

                txtfconversion.setText(Utils.formatDouble(ordenCargo.getFactorConversion()));
                txtdensidad.setText(Utils.formatDouble(ordenCargo.getDensidad()));
                txtprecio.setText(Utils.formatDouble(ordenCargo.getPrecio()));
                txtcantidad.setText(ordenCargo.getCantidadDoc() + "");
                //etCompraFacturaSerie.setText(ordenCargo.getFechaAccion());
                String cutDateAccion= ordenCargo.getFechaAccion();
                String [] cutDates=cutDateAccion.split(":");
                String part12 = cutDates[0]; // 004
                String Date= part12.substring(0,11);
                btnCompraFacturaEmision.setText(Date2);
                btnCompraFacturaEmision.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        selectCompraFacturaFechaEmision();
                    }
                });
                btnCompraGuiaEmision.setText(Date);
                btnCompraGuiaEmision.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        selectCompraGuiaFechaEmision();
                    }
                });
                initSpinnerUnidadMedia();
                initSpinnerProducto();
                tilCompraRuc.setText(mproveedor.getPersona().getPerVDocIdentidad());
                tilRazonSocial.setText(mproveedor.getPersona().getNombreComercial());
                initAutocompleteRuc();
                //guardarCompra(ordenCargo, spnProduto, spnUnidadMedida, spnTipoCarga, tilCompraRuc, tilRazonSocial);
                initEdittextCompra();
                break;
            case "trasciego":

                lytTrasCiego.setVisibility(View.VISIBLE);
                lytCompra.setVisibility(View.GONE);
                //seteamos
           /*     String nmrGuiaTras = ordenCargo.getNroGuia();

                String[] parts2Tras = nmrGuiaTras.split("-");
                String nmr_guiaTras = parts2Tras[0]; // 004
                String nmr_relativoTras = parts2Tras[1]; // 034556
                Log.d(TAG, "nmr_guiaTras: "+nmr_guiaTras+ "nmr_relativoTras"+ nmr_relativoTras);

                String CutDateCreacionTras = ordenCargo.getFechaCreacion();
                String [] cutcreacion2Tras=CutDateCreacionTras.split(":");
                String part100Tras = cutcreacion2Tras[0]; // 004

                String Date2Tras= part100Tras.substring(0,11);
                */
                et_trasciego_guia_serie.setText(nmr_guia);
                et_trasciego_guia_correlativo.setText(nmr_relativo);
                txtfconversion.setText(Utils.formatDouble(ordenCargo.getFactorConversion()));
                txtdensidad.setText(Utils.formatDouble(ordenCargo.getDensidad()));
                txtprecio.setText(Utils.formatDouble(ordenCargo.getPrecio()));
                txtcantidad.setText(ordenCargo.getCantidadDoc() + "");
                initSpinnerProducto();
                initSpinnerUnidadMedia();
                initSpinnerTipoOrigen();
                initEdittextTrasciego();

                btnTrasciegoGuiaEmision.setText(Date2);
                btnTrasciegoGuiaEmision.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        selectTrasciegoGuiaFechaEmision();
                    }
                });
                break;
        }

        btAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Concepto mconceptoTipoCarga = Concepto.getConcepto(ordenCargo.getTipoCargaId() + "");
                switch (mconceptoTipoCarga.getDescripcion().toLowerCase()) {
                    case "compra":
                        guardarCompra(ordenCargo, viewposition, alertDialog);
                        break;
                    case "trasciego":
                        guardarTrasciego(ordenCargo, viewposition, alertDialog);
                        break;
                }
            }
        });
        btCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });


    }


    public void guardarCompra(OrdenCargo ordenCargo, View view, Dialog alertDialog) {

        Producto producto = (Producto) spnProduto.getSelectedItem();
        Unidad unidad = (Unidad) spnUnidadMedida.getSelectedItem();

        String ruc = tilCompraRuc.getText().toString();
        String correlativoFactura = etCompraFacturaCorrelativo.getText().toString();
        String serieFactura = etCompraFacturaSerie.getText().toString();
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
                esGuiaCorrelativoValido && esFechaComprobanteValida && esFechaGuiaValida && sonCamposGeneralesValidos) {
            //SAVE!
            Proveedor proveedor = getProveedor(ruc);

            String datetime = Utils.getDatePhoneWithTime();
            Log.d(TAG, "fechaRegistro: " + datetime);

            String strCantidad = txtfconversion.getText().toString();
            String strDensidad = txtdensidad.getText().toString();
            String strPrecio = txtprecio.getText().toString();
            String strFactor = txtfconversion.getText().toString();

           /* Double cantidad = Double.parseDouble(strCantidad);
            Double densidad = Double.parseDouble(strDensidad);
            Double precio = Double.parseDouble(strPrecio);
           */// Double factor = Double.parseDouble(strFactor);

            Double factor = Double.parseDouble(txtfconversion.getText().toString());
            Double precio = Double.parseDouble(txtprecio.getText().toString());
            Double densidad = Double.parseDouble(txtdensidad.getText().toString());
            Double cantidad = Double.parseDouble(txtcantidad.getText().toString());
            //cajaGasto.setImporte(Double.parseDouble(txtttotal.getText().toString()));

            Double cantidadTransformada = cantidad / factor;
            String format = "%.4f";

            int usuarioId = Session.getSession(this).getUsuIUsuarioId();
            Double stockMaximo = almacenes.getCapacidadReal() - almacenes.getStockMinimo();
            if (cantidadTransformada < stockMaximo) {
                ordenCargo.setFechaRegistro(datetime);
                ordenCargo.setFechaComprobante(comprobanteFecha);
                ordenCargo.setProveedorId(proveedor.getProveedorId());
                ordenCargo.setNroComprobante(serieFactura + "-" + correlativoFactura);
                ordenCargo.setNroGuia(serieGuia + "-" + correlativoGuia);
                //ordenCargo.setTipoCargaId(tipoCarga.getIdConcepto());
                // ordenCargo.setFactorConversion(Double.parseDouble());
                ordenCargo.setFactorConversion(Utils.formatDouble(format, factor));
                ordenCargo.setFechaGuia(guiaFecha);
                ordenCargo.setDensidad(Utils.formatDouble(format, densidad));
                ordenCargo.setProId(producto.getProId());
                ordenCargo.setUnIdComprobante(unidad.getUnId());
                ordenCargo.setCantidadDoc(Utils.formatDouble(format, cantidad));
                ordenCargo.setCantidadTransformada(Utils.formatDouble(format, cantidadTransformada));
                ordenCargo.setUsuarioCreacionId(usuarioId);
                ordenCargo.setFechaCreacion(datetime);
                //ordenCargo.setEstadoId();
                ordenCargo.setUsuarioAccionId(usuarioId);
                ordenCargo.setFechaAccion(datetime);
                ordenCargo.setPrecio(Utils.formatDouble(format, precio));

                saveOrdenCargo(ordenCargo, alertDialog, view);
            }else if (cantidadTransformada>stockMaximo){
                txtcantidad.setError("Stock Maximo");
            } else {
                Snackbar.make(textViewPlaca, "Revise los campos", Snackbar.LENGTH_LONG).show();
            }

        }

    }
    public void guardarTrasciego(OrdenCargo ordenCargo, View view, Dialog alertDialog) {

        Producto producto = (Producto) spnProduto.getSelectedItem();
        Unidad unidad = (Unidad) spnUnidadMedida.getSelectedItem();
        Concepto tipoOrigen = (Concepto) spnTipoOrigen.getSelectedItem();


        String serieGuia = et_trasciego_guia_serie.getText().toString();
        String correlativoGuia = et_trasciego_guia_correlativo.getText().toString();
        String guiaFecha = btnTrasciegoGuiaEmision.getText().toString();


        boolean esGuiaSerieValida = esGuiaTrasciegoSerieValida(serieGuia);
        boolean esGuiaCorrelativoValido = esGuiaTrasciegoCorrelativoValido(correlativoGuia);
        boolean esFechaGuiaValida = esGuiaTrasciegoComprobanteValida(guiaFecha);
        boolean sonCamposGeneralesValidos = validarCamposGenerales();

        if (esGuiaSerieValida && esGuiaCorrelativoValido && esFechaGuiaValida && sonCamposGeneralesValidos) {

            String strCantidad = txtfconversion.getText().toString();
            String strDensidad = txtdensidad.getText().toString();
            String strPrecio = txtprecio.getText().toString();
            String strFactor = txtfconversion.getText().toString();

            Double factor = Double.parseDouble(txtfconversion.getText().toString());
            Double precio = Double.parseDouble(txtprecio.getText().toString());
            Double densidad = Double.parseDouble(txtdensidad.getText().toString());
            Double cantidad = Double.parseDouble(txtcantidad.getText().toString());

            String datetime = Utils.getDatePhoneWithTime();
            Double cantidadTransformada = cantidad / factor;

            String format = "%.4f";

            int usuarioId = Session.getSession(this).getUsuIUsuarioId();

            Double stockMaximo = almacenes.getCapacidadReal() - almacenes.getStockMinimo();
            //200 <500
            if (cantidadTransformada < stockMaximo) {
                ordenCargo.setFechaRegistro(datetime);
                ordenCargo.setNroGuia(serieGuia + "-" + correlativoGuia);
                //ordenCargo.setTipoCargaId(tipoCarga.getIdConcepto());
                ordenCargo.setFactorConversion(Utils.formatDouble(format, factor));
                ordenCargo.setFechaGuia(guiaFecha);
                ordenCargo.setTipoOrigenId(tipoOrigen.getIdConcepto());
                ordenCargo.setDensidad(Utils.formatDouble(format, densidad));
                ordenCargo.setProId(producto.getProId());
                ordenCargo.setUnIdComprobante(unidad.getUnId());
                ordenCargo.setCantidadDoc(Utils.formatDouble(format, cantidad));
                ordenCargo.setCantidadTransformada(Utils.formatDouble(format, cantidadTransformada));
                ordenCargo.setUsuarioCreacionId(usuarioId);
                ordenCargo.setFechaCreacion(datetime);
                //ordenCargo.setEstadoId();
                ordenCargo.setUsuarioAccionId(usuarioId);
                ordenCargo.setFechaAccion(datetime);
                ordenCargo.setPrecio(Utils.formatDouble(format, precio));

                //ordenCargo.save();

          /*  int position = recycler.getChildAdapterPosition(view);
            ((OrdenCargaAdapter) recycler.getAdapter()).updateOrder(position);
            alertDialog.dismiss();
            Snackbar.make(textViewPlaca, "Editado Exitosamente!", Snackbar.LENGTH_SHORT).show();
           */
                saveOrdenCargo(ordenCargo, alertDialog, view);
            } else if (cantidadTransformada>stockMaximo){
                txtcantidad.setError("Stock Maximo");
            }else {
                Snackbar.make(textViewPlaca, "Revise los campos", Snackbar.LENGTH_LONG).show();
            }
        }
    }
    private void saveOrdenCargo(OrdenCargo ordenCargo, Dialog alertDialog, View view) {
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
            int position = recycler.getChildAdapterPosition(view);
            ((OrdenCargaAdapter) recycler.getAdapter()).updateOrder(position);
            alertDialog.dismiss();
            Snackbar.make(textViewPlaca, "Editado Exitosamente!", Snackbar.LENGTH_SHORT).show();
        } else {
            Snackbar.make(textViewPlaca, "Error al guardar Orden de Cargo", Snackbar.LENGTH_LONG).show();
        }
        //Orden de cargo
        // new SyncEstado(0, Utils.separteUpperCase(OrdenCargo.class.getSimpleName()), Integer.parseInt(ordenCargo.getId() + ""), Constants.S_CREADO).save();

        //initRecycler();
    }


    public void initSpinnerTipoOrigen() {
        ConceptoAdapter conceptoAdapter = new ConceptoAdapter(this, 0, Concepto.getConceptos("Orden Cargo", "Tipo Origen"));
        spnTipoOrigen.setAdapter(conceptoAdapter);
    }


    public void initSpinnerProducto() {
        ProductoAdapter productoAdapter = new ProductoAdapter(this, 0, Producto.getAllProducto());
        spnProduto.setAdapter(productoAdapter);
    }


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
                        //  etFactorConversion.setText("");
                        break;
                    case "gl":
                        //etFactorConversion.setText("1");
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


    public void initAutocompleteRuc() {

        proveedorAdapterRuc = new ProveedorAdapter(this, ProveedorAdapter.DOCUMENTO_IDENTIDAD, Proveedor.getProveedorList());
        tilCompraRuc.setAdapter(proveedorAdapterRuc);

        tilCompraRuc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                tilCompraRuc.setText("");
                tilCompraRuc.setOnItemClickListener(proveedorItemSelectedListener);
                /// tilCompraRuc.setText("");

            }

        });
    }

    AdapterView.OnItemClickListener proveedorItemSelectedListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            Proveedor proveedor = proveedorAdapterRuc.getItem(i);
            tilCompraRuc.setText(proveedor.getPersona().getPerVDocIdentidad());
            tilRazonSocial.setText(proveedor.getPersona().getNombreComercial());
        }

    };


    public void initEdittextTrasciego() {
        /*TrasCiego*/
        et_trasciego_guia_serie.addTextChangedListener(watcherTrasciegoGuiaSerie);
        et_trasciego_guia_correlativo.addTextChangedListener(watcherTrasciegoGuiaCorrelativo);

    }

    public void initEdittextCompra() {
        etCompraFacturaSerie.addTextChangedListener(watcherCompraFacturaSerie);
        etCompraFacturaCorrelativo.addTextChangedListener(watcherCompraFacturaCorrelativo);
        etCompraGuiaSerie.addTextChangedListener(watcherCompraGuiaSerie);
        etCompraGuiaCorrelativo.addTextChangedListener(watcherCompraGuiaCorrelativo);

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


    public void selectCompraFacturaFechaEmision() {
        tipoFecha = COMPRA_FECHA_COMPROBANTE;
        createTimePicker();
    }


    public void selectCompraGuiaFechaEmision() {
        tipoFecha = COMPRA_FECHA_GUIA;
        createTimePicker();
    }

    public void selectTrasciegoGuiaFechaEmision() {
        tipoFecha = TRASCIEGO_FECHA_GUIA;
        createTimePicker();
    }

    private void createTimePicker() {
        Calendar now = Calendar.getInstance();
        DatePickerDialog dpd = DatePickerDialog.newInstance(
                OrdenCargaListActivity.this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );
        dpd.show(getFragmentManager(), "Datepickerdialog");
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

    private boolean esSerieValida(String serieFactura) {
        if (serieFactura.length() != 4) {
            tilCompraFacturaSerie.setError("Inválido");
            return false;
        }
        tilCompraFacturaSerie.setError(null);
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

    private boolean esGuiaSerieValida(String serieGuia) {
        if (serieGuia.length() != 4) {
            tilCompraGuiaSerie.setError("Inválido");
            return false;
        }
        tilCompraGuiaSerie.setError(null);
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

    private boolean esFechaComprobanteValida(String comprobanteFecha) {
        if (comprobanteFecha.toLowerCase().contains("fecha")) {
            btnCompraFacturaEmision.setError("Inválido");
            return false;
        }
        btnCompraFacturaEmision.setError(null);
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

    private Proveedor getProveedor(String ruc) {
        List<Persona> personas = Persona.find(Persona.class, "per_V_Doc_Identidad = ?", ruc);
        Persona persona = personas.get(0);
        return Proveedor.find(Proveedor.class, "persona_id = ?", "" + persona.getPerIPersonaId()).get(0);
    }


    private boolean esGuiaTrasciegoSerieValida(String serieGuia) {
        if (serieGuia.length() != 4) {
            til_trasciego_guia_serie.setError("Inválido");
            return false;
        }
        til_trasciego_guia_serie.setError(null);
        return true;
    }

    private boolean esGuiaTrasciegoCorrelativoValido(String correlativoGuia) {
        if (correlativoGuia.length() > 8) {
            til_trasciego_guia_correlativo.setError("Inválido");
            return false;
        }
        til_trasciego_guia_correlativo.setError(null);
        return true;
    }

    private boolean esGuiaTrasciegoComprobanteValida(String guiaFecha) {
        if (guiaFecha.toLowerCase().contains("fecha")) {
            btnTrasciegoGuiaEmision.setError("Inválido");
            return false;
        }
        btnTrasciegoGuiaEmision.setError(null);
        return true;
    }


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


    TextWatcher watcherCompraFacturaSerie = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            Log.d(TAG, "watcherCompraFacturaSerie charSequence: " + charSequence);
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

    private boolean validarCamposGenerales() {
        String cantidad = txtcantidad.getText().toString();
        String densidad = txtdensidad.getText().toString();
        String precio = txtprecio.getText().toString();
        String factor = txtfconversion.getText().toString();


        boolean esCantidadValido = esCantidadValido(cantidad);
        boolean esDensidadValido = esDensidadValido(densidad);
        boolean esPrecioValido = esPrecioValido(precio);
        boolean esFactorValido = esFactorValido(factor);
        return (esCantidadValido && esDensidadValido && esPrecioValido && esFactorValido);

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

}
