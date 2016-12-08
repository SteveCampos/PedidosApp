package energigas.apps.systemstrategy.energigas.fragments;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

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
import energigas.apps.systemstrategy.energigas.entities.PlanPagoDetalle;
import energigas.apps.systemstrategy.energigas.utils.Constants;
import energigas.apps.systemstrategy.energigas.utils.Session;
import energigas.apps.systemstrategy.energigas.utils.Utils;

/**
 * Created by kelvi on 29/09/2016.
 */

public class EstablecerCuotasFragment extends DialogFragment implements View.OnClickListener, CuotasAdapter.OnChangeDateListener {


    private static final String TAG = "EstablecerCuotasFragment";
    @BindView(R.id.btn_ok)
    Button btnOk;
    @BindView(R.id.btn_cancel)
    Button btnCancel;
    @BindView(R.id.spinnerCuotas)
    Spinner spinnerCuotas;
    @BindView(R.id.recycler_viewMostrarCuotas)
    RecyclerView recyclerViewMostrarCuotas;

    CuotasAdapter cuotasAdapter;
    /*@BindView(R.id.layoutRecycler)
    LinearLayout linearLayoutRecycler;
    @BindView(R.id.layoutCalendar)
    LinearLayout linearLayoutCalendar;*/


    DefineCuotasListener listener;
    Cliente cliente;
    Establecimiento establecimiento;
    Concepto conceptoCredito;
    Double[] params;


    public EstablecerCuotasFragment setParams(Double[] params) {
        this.params = params;
        return this;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        setCancelable(false);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.AppTheme);
        return dialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.layout_definir_cuotas, container, false);
        ButterKnife.bind(this, rootView);
        establecimiento = Establecimiento.find(Establecimiento.class, " est_I_Establecimiento_Id = ?  ", new String[]{Session.getSessionEstablecimiento(getActivity()).getEstIEstablecimientoId() + ""}).get(Constants.CURRENT);
        cliente = Cliente.find(Cliente.class, " CLI_I_CLIENTE_ID = ? ", new String[]{establecimiento.getEstIClienteId() + ""}).get(Constants.CURRENT);
        conceptoCredito = Concepto.find(Concepto.class, " ID_CONCEPTO = ? ", new String[]{cliente.getCliIModalidadCreditoId() + ""}).get(Constants.CURRENT);
        btnOk.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        initDefinirCuotas();
        initMostrarDetalle(getPlanPagoDetalle());
        return rootView;
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
            detalles.add(new PlanPagoDetalle(-1, planPagoDetalleId, Utils.getDatePhoneWithTime(), importePorCuota,Constants.ESTADO_TRUE,0, 0, importePorCuota, fechaPago, 0, Utils.getDatePhoneWithTime(), 0));
            fecha = Utils.sumarFechasDias(fecha, diasCredito);
            planPagoDetalleId++;
        }

        return detalles;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_cancel:
                dismiss();
                break;
            case R.id.btn_ok:
               // Toast.makeText(getActivity(), cuotasAdapter.getPlanPagoDetalles().get(0).getFechaCobro(), Toast.LENGTH_SHORT).show();
                listener.cuotasDefinidas(cuotasAdapter.getPlanPagoDetalles());
                dismiss();
                break;
        }
    }

    private void initDefinirCuotas() {
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, new String[]{"1", "2", "3", "4", "5", "6", "7"});
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

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof DefineCuotasListener) {
            listener = (DefineCuotasListener) context;
        } else {
            throw new RuntimeException(context.toString() +
                    " must implement OnStationOrderClickListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    @Override
    public void onChangeDateListener(int position, List<PlanPagoDetalle> planPagoDetalles) {

    }


    public interface DefineCuotasListener {
        void cuotasDefinidas(List<PlanPagoDetalle> planPagoDetalles);
    }

    private void initMostrarDetalle(List<PlanPagoDetalle> planPagoDetalles) {

        cuotasAdapter = new CuotasAdapter(planPagoDetalles, getActivity(),this);
        recyclerViewMostrarCuotas.setAdapter(cuotasAdapter);
        recyclerViewMostrarCuotas.setLayoutManager(new LinearLayoutManager(getActivity()));
    }


}
