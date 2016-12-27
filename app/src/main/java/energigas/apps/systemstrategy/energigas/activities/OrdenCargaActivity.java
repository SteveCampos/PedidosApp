package energigas.apps.systemstrategy.energigas.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import energigas.apps.systemstrategy.energigas.R;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orden_carga);
        ButterKnife.bind(this);
        initVies();
        List<Proveedor> list = Proveedor.getProveedorList();
        Log.d(TAG, "getProveedorList() size: " + list.size());
        for (Proveedor p :
                list) {
            Log.d(TAG, "proveedor persona razonsocial: " + p.getPersona().getPerVRazonSocial());
            Log.d(TAG, "proveedor persona getPerVDocIdentidad: " + p.getPersona().getPerVDocIdentidad());
        }
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
    }

    @Override
    public void handleSpinnerTipoCarga() {
        spnTipoCarga.setOnItemSelectedListener(tipoCargaSelectedItemListener);
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
