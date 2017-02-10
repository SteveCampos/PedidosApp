package energigas.apps.systemstrategy.energigas.activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.adapters.CuotasAdapter;
import energigas.apps.systemstrategy.energigas.entities.Cliente;
import energigas.apps.systemstrategy.energigas.entities.Concepto;
import energigas.apps.systemstrategy.energigas.entities.Establecimiento;
import energigas.apps.systemstrategy.energigas.entities.GeoUbicacion;
import energigas.apps.systemstrategy.energigas.entities.PlanPagoDetalle;
import energigas.apps.systemstrategy.energigas.fragments.DialogGeneral;
import energigas.apps.systemstrategy.energigas.interfaces.DialogGeneralListener;
import energigas.apps.systemstrategy.energigas.utils.Constants;
import energigas.apps.systemstrategy.energigas.utils.Session;
import energigas.apps.systemstrategy.energigas.utils.Utils;

public class DefinirCuotasActivity extends AppCompatActivity implements View.OnClickListener, CuotasAdapter.OnChangeDateListener {

    @BindView(R.id.btn_ok)
    Button btnOk;
    @BindView(R.id.spinnerCuotas)
    Spinner spinnerCuotas;
    @BindView(R.id.recycler_viewMostrarCuotas)
    RecyclerView recyclerViewMostrarCuotas;
    @BindView(R.id.textViewDeuda)
    TextView textViewDeuda;

    private CuotasAdapter cuotasAdapter;
    private Cliente cliente;
    private Establecimiento establecimiento;
    private Concepto conceptoCredito;
    private double[] params;

    private List<PlanPagoDetalle> listDetalle;

    private static final String TAG = "DefinirCuotasActivity";

    @BindView(R.id.txtpoints)
    TextView textViewPoints;
    @BindView(R.id.txtnombre)
    TextView textViewNombre;
    @BindView(R.id.txtdireccion)
    TextView textViewDireccion;
    @BindView(R.id.txtubicacion)
    TextView textViewUbicacion;
    @BindView(R.id.textAtendido)
    TextView textViewAtencion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_definir_cuotas);
        ButterKnife.bind(this);
        params = getIntent().getExtras().getDoubleArray(Constants.OBTENER_CUOTAS);
        textViewDeuda.setText(params[Constants.VENTA_TOTAL] + "");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        establecimiento = Establecimiento.find(Establecimiento.class, " est_I_Establecimiento_Id = ?  ", new String[]{Session.getSessionEstablecimiento(this).getEstIEstablecimientoId() + ""}).get(Constants.CURRENT);
        cliente = Cliente.find(Cliente.class, " CLI_I_CLIENTE_ID = ? ", new String[]{establecimiento.getEstIClienteId() + ""}).get(Constants.CURRENT);
        conceptoCredito = Concepto.find(Concepto.class, " ID_CONCEPTO = ? ", new String[]{cliente.getCliIModalidadCreditoId() + ""}).get(Constants.CURRENT);
        establecimiento.setUbicacion(GeoUbicacion.find(GeoUbicacion.class, " ub_Id = ?", new String[]{establecimiento.getUbId() + ""}).get(Constants.CURRENT));
        btnOk.setOnClickListener(this);
        toolbar();
        initViewsDisable();
        initDefinirCuotas();
        initMostrarDetalle(getPlanPagoDetalle());
        setTextTextViews();
    }

    private void toolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void setTextTextViews() {
        textViewNombre.setText(establecimiento.getEstVDescripcion());
        textViewDireccion.setText(establecimiento.getUbicacion().getDescripcion());
    }

    private void initViewsDisable() {
        textViewPoints.setVisibility(View.INVISIBLE);
        textViewUbicacion.setVisibility(View.GONE);
        textViewAtencion.setVisibility(View.GONE);
    }

    private void initDefinirCuotas() {
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, new String[]{"1", "2", "3", "4", "5", "6", "7"});
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerCuotas.setAdapter(spinnerArrayAdapter);
        spinnerCuotas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                initMostrarDetalle(getPlanPagoDetalle());
                Log.d(TAG, "ITEM" + i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private String getItemCuotas() {
        return (String) spinnerCuotas.getSelectedItem();
    }

    private List<PlanPagoDetalle> getPlanPagoDetalle() {

        int diasCredito = Integer.parseInt(conceptoCredito.getAbreviatura());
        int cuotas = Integer.parseInt(getItemCuotas());
        Log.d(TAG, Utils.getStringDate(Utils.sumarFechasDias(Calendar.getInstance().getTime(), diasCredito)));

        Double importePorCuota = params[Constants.VENTA_TOTAL] / cuotas;

        List<PlanPagoDetalle> detalles = new ArrayList<>();
        Date fecha = Calendar.getInstance().getTime();
        int planPagoDetalleId = PlanPagoDetalle.findWithQuery(PlanPagoDetalle.class, Utils.getQueryNumberPlanPagoDetalle(), null).get(Constants.CURRENT).getPlanPaDeId();
        for (int i = 0; i < cuotas; i++) {


            String fechaPago = Utils.getStringDate(Utils.sumarFechasDias(fecha, diasCredito));
            detalles.add(new PlanPagoDetalle(-1, planPagoDetalleId, fechaPago, importePorCuota, Constants.ESTADO_TRUE, 0, 0, importePorCuota, fechaPago, 0, Utils.getDatePhoneWithTime(), 0));
            fecha = Utils.sumarFechasDias(fecha, diasCredito);
            planPagoDetalleId++;
        }

        return detalles;
    }


    private void initMostrarDetalle(List<PlanPagoDetalle> planPagoDetalles) {
        listDetalle = planPagoDetalles;
        cuotasAdapter = new CuotasAdapter(planPagoDetalles, this, this);
        recyclerViewMostrarCuotas.setAdapter(cuotasAdapter);
        recyclerViewMostrarCuotas.setLayoutManager(new LinearLayoutManager(this));
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_ok:

                ObjectMapper mapper = new ObjectMapper();
                StringWriter sw = new StringWriter();
                try {
                    mapper.writeValue(sw, listDetalle);
                    String listObject = sw.toString();
                    Session.setDefineCuotas(this, Constants.ESTADO_TRUE, listObject);
                   // Toast.makeText(this, "" + listObject, Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                onBackPressed();

                break;
        }
    }

    @Override
    public void onChangeDateListener(int position, List<PlanPagoDetalle> planPagoDetalles) {

        dialogChangeDate(planPagoDetalles, position);
    }

    private void dialogChangeDate(List<PlanPagoDetalle> planPagoDetallesInit, int positionInit) {

        final List<PlanPagoDetalle> planPagoDetalles = planPagoDetallesInit;
        final int position = positionInit;
        final Calendar date = Calendar.getInstance();
        date.setTimeInMillis(Utils.getDateMills(planPagoDetalles.get(position).getFechaCobro()));
        int diasCredito = Integer.parseInt(conceptoCredito.getAbreviatura());
        View view = getLayoutInflater().inflate(R.layout.layout_dialog_datepicker, null);
        final DatePicker datePickerFecha = (DatePicker) view.findViewById(R.id.datePickerFecha);

        datePickerFecha.init(date.get(Calendar.YEAR), date.get(Calendar.MONTH), date.get(Calendar.DAY_OF_MONTH), null);
        // datePickerFecha.updateDate(date.get(Calendar.YEAR), date.get(Calendar.MONTH), date.get(Calendar.DAY_OF_MONTH));
        datePickerFecha.setMinDate(Utils.restarFechasDias(Utils.getDateFromString(planPagoDetalles.get(position).getFecha()), diasCredito).getTime());
        datePickerFecha.setMaxDate(Utils.getDateFromString(planPagoDetalles.get(position).getFecha()).getTime());

        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setView(view);
        dialog.setPositiveButton("Guardar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (Utils.getDateFromDatePickerMills(datePickerFecha) > datePickerFecha.getMaxDate()) {
                    Toast.makeText(DefinirCuotasActivity.this, "La Fecha no puede ser mayor", Toast.LENGTH_SHORT).show();
                    datePickerFecha.updateDate(date.get(Calendar.YEAR), date.get(Calendar.MONTH), date.get(Calendar.DAY_OF_MONTH));
                    dialogChangeDate(planPagoDetalles, position);
                    return;

                }
                if (Utils.getDateFromDatePickerMills(datePickerFecha) < datePickerFecha.getMinDate()) {
                    Toast.makeText(DefinirCuotasActivity.this, "La Fecha no puede ser menor", Toast.LENGTH_SHORT).show();
                    datePickerFecha.updateDate(date.get(Calendar.YEAR), date.get(Calendar.MONTH), date.get(Calendar.DAY_OF_MONTH));
                    dialogChangeDate(planPagoDetalles, position);
                    return;
                }
                if ((Utils.getDateFromDatePickerMills(datePickerFecha) <= datePickerFecha.getMaxDate()) && (Utils.getDateFromDatePickerMills(datePickerFecha) >= datePickerFecha.getMinDate())) {
                    String date = Utils.getStringDate(Utils.getDateFromDatePicker(datePickerFecha));
                    planPagoDetalles.get(position).setFechaCobro(date);
                    initMostrarDetalle(planPagoDetalles);
                }


            }
        });
        dialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialog.show();
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

        new DialogGeneral(DefinirCuotasActivity.this).setCancelable(false).setMessages("Retroceder", "¿Seguro de retroceder?").setTextButtons("SI", "NO").showDialog(new DialogGeneralListener() {
            @Override
            public void onSavePressed(AlertDialog alertDialog) {
                DefinirCuotasActivity.super.onBackPressed();
                alertDialog.dismiss();
                DefinirCuotasActivity.this.finish();
            }

            @Override
            public void onCancelPressed(AlertDialog alertDialog) {
                alertDialog.dismiss();
            }
        });
    }


}
