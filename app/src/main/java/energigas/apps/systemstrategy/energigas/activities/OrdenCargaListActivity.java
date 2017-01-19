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
import energigas.apps.systemstrategy.energigas.adapters.OrdenCargaAdapter;
import energigas.apps.systemstrategy.energigas.adapters.ProveedorAdapter;
import energigas.apps.systemstrategy.energigas.adapters.UnidadAdapter;
import energigas.apps.systemstrategy.energigas.entities.Concepto;
import energigas.apps.systemstrategy.energigas.entities.OrdenCargo;
import energigas.apps.systemstrategy.energigas.entities.Persona;
import energigas.apps.systemstrategy.energigas.entities.Producto;
import energigas.apps.systemstrategy.energigas.entities.Proveedor;
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
    OrdenCargaAdapter ordenCargaAdapter;

//    @BindView(R.id.container_compra)
//    LinearLayout lytCompra2;
//    @BindView(R.id.container_trasciego)
//    LinearLayout lytTrasciego;


    private Resources resources;

    private Persona mAgente;
    private Usuario mUsuario;
    private Vehiculo mVehiculo;
    AlertDialog alertDialog;
    /*Item Compra*/
    LinearLayout lytCompra;
    AutoCompleteTextView tilCompraRuc;
    AutoCompleteTextView tilRazonSocial;
    EditText etCompraFacturaSerie;
    EditText etCompraFacturaCorrelativo;
    EditText etCompraGuiaSerie;
    EditText etCompraGuiaCorrelativo;
    /*Fin Compra*/
    Spinner spnProduto;
    Spinner spnUnidadMedida;
    Spinner spnTipoCarga;

    TextView txtfconversion;
    TextView txtdensidad;
    TextView txtprecio;
    TextView txtcantidad;
    AppCompatButton btnCompraFacturaEmision;
    AppCompatButton btnCompraGuiaEmision;
    AppCompatButton btnTrasciegoGuiaEmision;


    TextInputLayout tilCompraFacturaSerie;
    TextInputLayout tilCompraFacturaCorrelativo;
    TextInputLayout tilCompraGuiaSerie;
    TextInputLayout tilCompraGuiaCorrelativo;






    /*Item Trasciego*/
    LinearLayout lytTrasCiego;
    /*Fin TrasCiego*/

    OrdenCargaAdapter adapter;


    private int tipoFecha;

    private final static int COMPRA_FECHA_COMPROBANTE = 100;
    private final static int COMPRA_FECHA_GUIA = 101;
    private final static int TRASCIEGO_FECHA_GUIA = 102;


    ProveedorAdapter proveedorAdapterRuc;
    ProveedorAdapter proveedorAdapterNombreComercial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling_orden_carga);
        mAgente = Persona.findWithQuery(Persona.class, "SELECT P.* FROM PERSONA P, USUARIO U WHERE P.PER_I_PERSONA_ID = U.USU_I_PERSONA_ID AND U.USU_I_USUARIO_ID = " + Session.getSession(this).getUsuIUsuarioId() + " ;", null).get(Constants.CURRENT);
        mUsuario = Usuario.getUsuario(Session.getSession(this).getUsuIUsuarioId() + "");
        mVehiculo = Vehiculo.getVehiculo(mUsuario.getUsuIUsuarioId() + "");
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
        ordenCargaAdapter = new OrdenCargaAdapter(this, OrdenCargo.findWithQuery(OrdenCargo.class, "select * from orden_Cargo ORDER BY id DESC"), this);
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
    public void onOrdenCargoLongClickListener(int position, OrdenCargo ordenCargo, View view) {
        switch (position) {

            case 1:
                Snackbar.make(textViewPlaca, "ONclickListenerEditar", Snackbar.LENGTH_SHORT).show();
                metEdit(ordenCargo);
                break;
            case 2:
                //metDelete(ordenCargo,view);
                Snackbar.make(textViewPlaca, "ONclickListenerDelete", Snackbar.LENGTH_SHORT).show();
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
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

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
                    collapsingToolbarLayout.setTitle("Orden Carga");
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

    private void metEdit(OrdenCargo ordenCargo) {

        View layout_dialog_ordenCarga = View.inflate(this, R.layout.dialog_edit_ordencarga, null);

        spnProduto = (Spinner) layout_dialog_ordenCarga.findViewById(R.id.spn_producto);
        spnUnidadMedida = (Spinner) layout_dialog_ordenCarga.findViewById(R.id.spn_unidadmedida);
        spnTipoCarga = (Spinner) layout_dialog_ordenCarga.findViewById(R.id.spn_tipocarga);
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
        tilCompraGuiaSerie = (TextInputLayout) layout_dialog_ordenCarga.findViewById(R.id.til_compra_guia_serie);
        tilCompraGuiaCorrelativo = (TextInputLayout) layout_dialog_ordenCarga.findViewById(R.id.til_compra_guia_correlativo);


        Button btAceptar = (Button) layout_dialog_ordenCarga.findViewById(R.id.btn_aceptar);
        Button btCancel = (Button) layout_dialog_ordenCarga.findViewById(R.id.btn_cancel);


        lytTrasCiego = (LinearLayout) layout_dialog_ordenCarga.findViewById(R.id.container_trasciego);


        txtfconversion = (TextView) layout_dialog_ordenCarga.findViewById(R.id.et_factorconversion);
        txtdensidad = (TextView) layout_dialog_ordenCarga.findViewById(R.id.et_densidad);
        txtprecio = (TextView) layout_dialog_ordenCarga.findViewById(R.id.et_precio);
        txtcantidad = (TextView) layout_dialog_ordenCarga.findViewById(R.id.et_cantidad);

        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        final Dialog d = adb.setView(layout_dialog_ordenCarga).create();
        // (That new View is just there to have something inside the dialog that can grow big enough to cover the whole screen.)


        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(d.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        d.show();
        d.getWindow().setAttributes(lp);

        Concepto mconceptoTipoCarga = Concepto.getConcepto(ordenCargo.getTipoCargaId() + "");
        switch (mconceptoTipoCarga.getDescripcion().toLowerCase()) {
            case "compra":
                lytCompra.setVisibility(View.VISIBLE);
                lytTrasCiego.setVisibility(View.GONE);
                //seteamos
                txtfconversion.setText(Utils.formatDouble(ordenCargo.getFactorConversion()));
                txtdensidad.setText(Utils.formatDouble(ordenCargo.getDensidad()));
                txtprecio.setText(Utils.formatDouble(ordenCargo.getPrecio()));
                txtcantidad.setText(ordenCargo.getCantidadDoc() + "");
                btnCompraFacturaEmision.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        selectCompraFacturaFechaEmision();
                    }
                });
                btnCompraGuiaEmision.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        selectCompraGuiaFechaEmision();
                    }
                });
                initSpinnerUnidadMedia(spnUnidadMedida);
                //guardarCompra(ordenCargo, spnProduto, spnUnidadMedida, spnTipoCarga, tilCompraRuc, tilRazonSocial);
                guardarCompra(ordenCargo);
                break;
            case "trasciego":

                lytTrasCiego.setVisibility(View.VISIBLE);
                lytCompra.setVisibility(View.GONE);
                //seteamos
                txtfconversion.setText(Utils.formatDouble(ordenCargo.getFactorConversion()));
                txtdensidad.setText(Utils.formatDouble(ordenCargo.getDensidad()));
                txtprecio.setText(Utils.formatDouble(ordenCargo.getPrecio()));
                txtcantidad.setText(ordenCargo.getCantidadDoc() + "");
                initSpinnerUnidadMedia(spnUnidadMedida);
                break;
        }

        btAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                d.dismiss();
            }
        });


    }

    private void metDelete(OrdenCargo ordenCargo, View view) {
        int position = recycler.getChildAdapterPosition(view);
        adapter.remove(ordenCargo, position);

    }


    //public void guardarCompra(OrdenCargo ordenCargo, Spinner spnUnidadMedida, Spinner spnTipoCarga, Spinner spnProduto, final AutoCompleteTextView tilCompraRuc, final AutoCompleteTextView tilRazonSocial) {
    public void guardarCompra(OrdenCargo ordenCargo) {
        Proveedor mproveedor = Proveedor.getProveedorById(ordenCargo.getProveedorId());

        Concepto tipoCarga = (Concepto) spnTipoCarga.getSelectedItem();
        Producto producto = (Producto) spnProduto.getSelectedItem();
        Unidad unidad = (Unidad) spnUnidadMedida.getSelectedItem();

        // String ruc2 = ordenCargo.setimporte(Double.parseDouble(txtttotal.getText().toString()));
        tilCompraRuc.setText(mproveedor.getPersona().getPerVDocIdentidad());
        tilRazonSocial.setText(mproveedor.getPersona().getNombreComercial());
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
       // boolean sonCamposGeneralesValidos = validarCamposGenerales();

        if (esProveedorValido && esSerieValida && esCorrelativoValido && esGuiaSerieValida &&
                esGuiaCorrelativoValido && esFechaComprobanteValida && esFechaGuiaValida ) {
            //SAVE!
            Proveedor proveedor = getProveedor(ruc);

            String datetime = Utils.getDatePhoneWithTime();
            Log.d(TAG, "fechaRegistro: " + datetime);

            String strCantidad = txtfconversion.getText().toString();
            String strDensidad = txtdensidad.getText().toString();
            String strPrecio = txtprecio.getText().toString();
            String strFactor = txtfconversion.getText().toString();

            Double cantidad = Double.parseDouble(strCantidad);
            Double densidad = Double.parseDouble(strDensidad);
            Double precio = Double.parseDouble(strPrecio);
            Double factor = Double.parseDouble(strFactor);


            Double cantidadTransformada = cantidad / factor;

            String format = "%.4f";

            int usuarioId = Session.getSession(this).getUsuIUsuarioId();

            ordenCargo.setFechaRegistro(datetime);
            ordenCargo.setFechaComprobante(comprobanteFecha);
            ordenCargo.setProveedorId(proveedor.getProveedorId());
            ordenCargo.setNroComprobante(serieFactura + "-" + correlativoFactura);
            ordenCargo.setNroGuia(serieGuia + "-" + correlativoGuia);
            ordenCargo.setTipoCargaId(tipoCarga.getIdConcepto());
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

            ordenCargo.save();
            Log.d(TAG," getOrdeCarga_Id :"+ordenCargo.getOrdeCargaId() );
        }else{
            Snackbar.make(etCompraFacturaCorrelativo, "Revise los campos", Snackbar.LENGTH_LONG).show();
        }

    }

    AdapterView.OnItemClickListener proveedorItemSelectedListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            Proveedor proveedor = proveedorAdapterRuc.getItem(i);
            tilCompraRuc.setText(proveedor.getPersona().getPerVDocIdentidad());
            tilRazonSocial.setText(proveedor.getPersona().getNombreComercial());
        }

    };


    public void initSpinnerTipoCarga() {

    }


    public void initSpinnerTipoOrigen() {

    }


    public void initSpinnerProducto() {

    }


    public void initSpinnerUnidadMedia(Spinner spnUnidadMedida) {
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

    }


    public void initAutoCumpleteNombreComercial() {

    }


    public void mostrarCompraView() {

    }


    public void mostrarTrasciegoView() {

    }


    public void guardarOrdenCarga() {

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

    // @OnClick(R.id.btn_compra_factura_fechaemision)
    public void selectCompraFacturaFechaEmision() {
        tipoFecha = COMPRA_FECHA_COMPROBANTE;
        createTimePicker();
    }

    // @OnClick(R.id.btn_compra_guia_fechaemision)
    public void selectCompraGuiaFechaEmision() {
        tipoFecha = COMPRA_FECHA_GUIA;
        createTimePicker();
    }

    // @OnClick(R.id.btn_trasciego_guia_fechaemision)
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

}
