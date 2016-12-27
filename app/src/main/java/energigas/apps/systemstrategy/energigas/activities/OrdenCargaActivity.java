package energigas.apps.systemstrategy.energigas.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.adapters.ProveedorAdapter;
import energigas.apps.systemstrategy.energigas.entities.OrdenCargo;
import energigas.apps.systemstrategy.energigas.entities.Persona;
import energigas.apps.systemstrategy.energigas.entities.Proveedor;
import energigas.apps.systemstrategy.energigas.ordencarga.OrdenCargaView;

public class OrdenCargaActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, OrdenCargaView {

    private static final String TAG = OrdenCargaActivity.class.getSimpleName();

    @BindView(R.id.spn_tipocarga)
    Spinner spnTipoCarga;
    @BindView(R.id.container_compra)
    LinearLayout lytCompra;
    @BindView(R.id.container_trasciego)
    LinearLayout lytTrasciego;
    @BindView(R.id.et_compra_ruc)
    AppCompatAutoCompleteTextView actCompraRuc;
    @BindView(R.id.et_compra_nombrecomercial)
    AppCompatAutoCompleteTextView actCompraNombreComercial;
    @BindView(R.id.et_compra_factura)
    EditText etCompraFactura;
    @BindView(R.id.et_compra_guia)
    EditText etCompraGuia;
    @BindView(R.id.text_compra_emision)
    TextView txtCompraEmision;
    @BindView(R.id.text_compra_entrega)
    TextView txtCompraEntrega;
    @BindView(R.id.btn_compra_fechaemision)
    AppCompatButton btnCompraEmision;
    @BindView(R.id.btn_compra_fechaentrega)
    AppCompatButton btnCompraEntrega;


    ProveedorAdapter proveedorAdapterRuc;
    ProveedorAdapter proveedorAdapterNombreComercial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orden_carga);
        ButterKnife.bind(this);
        initVies();
    }


    @OnClick(R.id.btn_compra_fechaemision)
    public void selectFechaEntrega() {
        createTimePicker();
    }

    @OnClick(R.id.btn_compra_fechaentrega)
    public void selectFechaEmision() {
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
    }

    @Override
    public void initVies() {
        handleSpinnerTipoCarga();
        initAutocompleteRuc();
        initAutoCumpleteNombreComercial();
    }

    @Override
    public void handleSpinnerTipoCarga() {
        spnTipoCarga.setOnItemSelectedListener(tipoCargaSelectedItemListener);
    }

    @Override
    public void initAutocompleteRuc() {
        proveedorAdapterRuc = new ProveedorAdapter(this, ProveedorAdapter.DOCUMENTO_IDENTIDAD ,Proveedor.getProveedorList());
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
            String item = (String) adapterView.getItemAtPosition(i);
            switch (item) {
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

    @Override
    public void guardarOrdenCarga() {
        OrdenCargo ordenCargo = new OrdenCargo();

    }
}
