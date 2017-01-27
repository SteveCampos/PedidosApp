package energigas.apps.systemstrategy.energigas.fragments;

import android.app.Service;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.adapters.SummaryAdapter;
import energigas.apps.systemstrategy.energigas.entities.CajaLiquidacion;
import energigas.apps.systemstrategy.energigas.entities.Concepto;
import energigas.apps.systemstrategy.energigas.entities.Costs;
import energigas.apps.systemstrategy.energigas.entities.Summary;
import energigas.apps.systemstrategy.energigas.entities.SummaryIncome;
import energigas.apps.systemstrategy.energigas.utils.Constants;
import energigas.apps.systemstrategy.energigas.utils.Session;
import energigas.apps.systemstrategy.energigas.utils.Utils;

/**
 * Created by kelvi on 19/07/2016.
 */

public class ResumentFragment extends Fragment {

    /**
     * Includes
     **/
    @BindView(R.id.cardResumen)
    CardView cardViewResumen;

    @BindView(R.id.cardIngresos)
    CardView cardViewIngreso;

    @BindView(R.id.cardCostos)
    CardView cardViewCostos;

    /**
     * Resumen
     **/
    @BindView(R.id.textEfectivoRendir)
    TextView textEfectivoRendir;
    @BindView(R.id.textTotalGastos)
    TextView textViewTotalGastos;
    @BindView(R.id.textDetalleItemTotales)
    TextView textViewDetalleItemsTotales;
    @BindView(R.id.textIngresosTotales)
    TextView textIngresosTotales;
    @BindView(R.id.textSaldoInicial)
    TextView textViewSaldoInicial;

    /**
     * Ingresos
     **/
    @BindView(R.id.tableLayoutIngresos)
    TableLayout tableLayoutIngresos;

    /**
     * Costos
     **/
    @BindView(R.id.tablayoutGastos)
    TableLayout tableLayoutGastos;

    private DatabaseReference mDatabase;
    private DatabaseReference myRefFondos;


/*
    @BindView(R.id.list)
    ListView listView;*/

    private Summary summary;
    private Resources res;

    private static final String TAG = "ResumentFragment";
    CajaLiquidacion cajaLiquidacion;

    public static ResumentFragment newInstance() {
        ResumentFragment summary = new ResumentFragment();
        return summary;
    }

    Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.layout_fragment_summary, container, false);
        ButterKnife.bind(this, rootView);
        context = getActivity();
        init(context);
        setResumen();
        setIngresos();
        setCostos();
        cajaLiquidacion = CajaLiquidacion.getCajaLiquidacion(Session.getCajaLiquidacion(getActivity()).getLiqId() + "");

        if (cajaLiquidacion != null) {
            if (FirebaseApp.getApps(getActivity()).isEmpty()) {
                FirebaseApp.initializeApp(getActivity().getApplicationContext(), FirebaseOptions.fromResource(getActivity().getApplicationContext()));

            }
            mDatabase = FirebaseDatabase.getInstance().getReference();

            myRefFondos = mDatabase.child(Constants.FIREBASE_CHILD_FONDOS).child(cajaLiquidacion.getLiqId() + "");
            myRefFondos.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Log.d("ServiceSync", "RESUMEN_FRAGMENT: " + cajaLiquidacion.getLiqId());
                    CajaLiquidacion liquidacion = dataSnapshot.getValue(CajaLiquidacion.class);
                    CajaLiquidacion liquidacionGuardar = CajaLiquidacion.getCajaLiquidacion(liquidacion.getLiqId() + "");
                    liquidacionGuardar.setSaldoInicial(liquidacion.getSaldoInicial());
                    Long aLong = liquidacionGuardar.save();
                    Log.d(TAG, "ACTUALIZO: " + aLong);

                    try {

                        init(context);


                    } catch (NullPointerException e) {

                    }


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }


        return rootView;
    }

    private void init(Context context) {
        textViewDetalleItemsTotales.setVisibility(View.GONE);
        //SummaryAdapter adapter = new SummaryAdapter(getActivity(), 0, Summary.getListSummary(getActivity()));
        summary = Summary.getListSummary(getActivity());
        res = getActivity().getResources();
        // listView.setAdapter(adapter);
        setResumen();
    }

    private void setResumen() {
        /*if (summary == null || summary.getEfectivoRendir() <= 0.00) {
            return;
        }*/
        cardViewResumen.setVisibility(View.VISIBLE);

        textEfectivoRendir.setText(Utils.formatDoublePrint(summary.getEfectivoRendir()) + "");
        textViewTotalGastos.setText(Utils.formatDoublePrint(summary.getGastos()) + "");
        textIngresosTotales.setText(Utils.formatDoublePrint(summary.getIngresosTotales()) + "");
        textViewSaldoInicial.setText(Utils.formatDoublePrint(summary.getSaldoInicial()) + "");

        String detalleStrings = "";
        for (Concepto concepto : summary.getIncome().keySet()) {
            String s = "";
            double importeItem = 0.0;
            for (Double importeTotal : summary.getIncome().get(concepto)) {
                importeItem = importeItem + importeTotal;
            }
            s = concepto.getDescripcion() + "                                                           " + Utils.formatDouble(importeItem) + "\n";
            detalleStrings = detalleStrings + s;
        }

        if (!detalleStrings.equals("")) {
            textViewDetalleItemsTotales.setVisibility(View.VISIBLE);
            String stringFormarItems = String.format(res.getString(R.string.text_detalle_ingresos), detalleStrings);
            textViewDetalleItemsTotales.setText(stringFormarItems);
        }


    }


    private void setIngresos() {
        Log.d(TAG, "" + summary.getSummaryIncomeList().size());

        if (Integer.parseInt(summary.getSummaryIncomeList().get(0).getCantidad()) < 1) {
            return;
        }
        cardViewIngreso.setVisibility(View.VISIBLE);

        TableRow tableRow1 = new TableRow(getActivity());

        TextView textDescripcion1 = new TextView(getActivity());
        TextView textCantidad1 = new TextView(getActivity());
        TextView textTotalEmitidos1 = new TextView(getActivity());
        TextView textTotalPagados1 = new TextView(getActivity());
        TextView textTotalCobrados1 = new TextView(getActivity());

        textDescripcion1.setGravity(Gravity.CENTER);
        textCantidad1.setGravity(Gravity.CENTER);
        textTotalEmitidos1.setGravity(Gravity.CENTER);
        textTotalPagados1.setGravity(Gravity.CENTER);
        textTotalCobrados1.setGravity(Gravity.CENTER);

        textDescripcion1.setTypeface(Typeface.DEFAULT_BOLD);
        textCantidad1.setTypeface(Typeface.DEFAULT_BOLD);
        textTotalEmitidos1.setTypeface(Typeface.DEFAULT_BOLD);
        textTotalPagados1.setTypeface(Typeface.DEFAULT_BOLD);
        textTotalCobrados1.setTypeface(Typeface.DEFAULT_BOLD);


        textDescripcion1.setTextSize(12);
        textCantidad1.setTextSize(12);
        textTotalEmitidos1.setTextSize(12);
        textTotalPagados1.setTextSize(12);
        textTotalCobrados1.setTextSize(12);

        tableRow1.addView(textDescripcion1);
        tableRow1.addView(textCantidad1);
        tableRow1.addView(textTotalEmitidos1);
        tableRow1.addView(textTotalPagados1);
        tableRow1.addView(textTotalCobrados1);

        textDescripcion1.setText("Descripcion");
        textCantidad1.setText("Cantidad");
        textTotalEmitidos1.setText("Emitidos");
        textTotalPagados1.setText("Pagados");
        textTotalCobrados1.setText("Cobrados");

        tableLayoutIngresos.addView(tableRow1, 0);

        int sumaCount = 0;
        Double sumaCantidad = 0.00;
        Double sumaTotalEmitidos = 0.00;
        Double sumaTotalPagados = 0.00;
        Double sumaTotalCobradas = 0.00;


        for (int i = 0; i < summary.getSummaryIncomeList().size(); i++) {


            int count = i + 1;
            sumaCount = count;
            SummaryIncome income = summary.getSummaryIncomeList().get(i);

            sumaCantidad = sumaCantidad + Double.parseDouble(income.getCantidad());
            sumaTotalEmitidos = sumaTotalEmitidos + Double.parseDouble(income.getEmitidos());
            sumaTotalPagados = sumaTotalPagados + Double.parseDouble(income.getPagados());
            sumaTotalCobradas = sumaTotalCobradas + Double.parseDouble(income.getCobrados());

            TableRow tableRow = new TableRow(getActivity());
            //tableRow.removeAllViews();

            TextView textDescripcion = new TextView(getActivity());
            TextView textCantidad = new TextView(getActivity());
            TextView textTotalEmitidos = new TextView(getActivity());
            TextView textTotalPagados = new TextView(getActivity());
            TextView textTotalCobrados = new TextView(getActivity());

            textDescripcion.setGravity(Gravity.CENTER);
            textCantidad.setGravity(Gravity.CENTER);
            textTotalEmitidos.setGravity(Gravity.CENTER);
            textTotalPagados.setGravity(Gravity.CENTER);
            textTotalCobrados.setGravity(Gravity.CENTER);


            textDescripcion.setTextSize(12);
            textCantidad.setTextSize(12);
            textTotalEmitidos.setTextSize(12);
            textTotalPagados.setTextSize(12);
            textTotalCobrados.setTextSize(12);


            tableRow.addView(textDescripcion);
            tableRow.addView(textCantidad);
            tableRow.addView(textTotalEmitidos);
            tableRow.addView(textTotalPagados);
            tableRow.addView(textTotalCobrados);

            Double aCantidad = (Double.parseDouble(income.getCantidad()));
            textDescripcion.setText(income.getConcepto());
            textCantidad.setText(Utils.formatDoublePrint(Double.parseDouble(aCantidad.intValue() + "")) + "");
            textTotalEmitidos.setText(Utils.formatDoublePrint(Double.parseDouble(income.getEmitidos())));
            textTotalPagados.setText(Utils.formatDoublePrint(Double.parseDouble(income.getPagados())));
            textTotalCobrados.setText(Utils.formatDoublePrint(Double.parseDouble(income.getCobrados())));


            tableLayoutIngresos.addView(tableRow, count);
        }


        TableRow tableRow = new TableRow(getActivity());
        tableRow.setBackgroundColor(Color.parseColor("#bdbdbd"));
        TextView textDescripcion = new TextView(getActivity());
        TextView textCantidad = new TextView(getActivity());
        TextView textTotalEmitidos = new TextView(getActivity());
        TextView textTotalPagados = new TextView(getActivity());
        TextView textTotalCobrados = new TextView(getActivity());

        textDescripcion.setGravity(Gravity.CENTER);
        textCantidad.setGravity(Gravity.CENTER);
        textTotalEmitidos.setGravity(Gravity.CENTER);
        textTotalPagados.setGravity(Gravity.CENTER);
        textTotalCobrados.setGravity(Gravity.CENTER);

        textDescripcion.setTextSize(12);
        textCantidad.setTextSize(12);
        textTotalEmitidos.setTextSize(12);
        textTotalPagados.setTextSize(12);
        textTotalCobrados.setTextSize(12);

        tableRow.addView(textDescripcion);
        tableRow.addView(textCantidad);
        tableRow.addView(textTotalEmitidos);
        tableRow.addView(textTotalPagados);
        tableRow.addView(textTotalCobrados);

        textDescripcion.setText("Total");
        textCantidad.setText("" + sumaCantidad.intValue());
        textTotalEmitidos.setText(Utils.formatDoublePrint(sumaTotalEmitidos));
        textTotalPagados.setText(Utils.formatDoublePrint(sumaTotalPagados));
        textTotalCobrados.setText(Utils.formatDoublePrint(sumaTotalCobradas));

        tableLayoutIngresos.addView(tableRow, sumaCount + 1);
    }

    private void setCostos() {
        if (summary == null || summary.getCostsList().size() < 1) {
            return;
        }
        cardViewCostos.setVisibility(View.VISIBLE);

        TableRow tableRow2 = new TableRow(getActivity());

        TextView textDescrip = new TextView(getActivity());
        TextView textRuta = new TextView(getActivity());
        TextView textImporte = new TextView(getActivity());

        textDescrip.setGravity(Gravity.CENTER);
        textRuta.setGravity(Gravity.CENTER);
        textImporte.setGravity(Gravity.CENTER);

        textDescrip.setTypeface(Typeface.DEFAULT_BOLD);
        textRuta.setTypeface(Typeface.DEFAULT_BOLD);
        textImporte.setTypeface(Typeface.DEFAULT_BOLD);


        textDescrip.setTextSize(12);
        textRuta.setTextSize(12);
        textImporte.setTextSize(12);

        textDescrip.setText("Descripcion");
        textRuta.setText("En Ruta");
        textImporte.setText("Importe");

        tableRow2.addView(textDescrip);
        tableRow2.addView(textRuta);
        tableRow2.addView(textImporte);

        tableLayoutGastos.addView(tableRow2, 0);
        int sumaCountGastos = 0;

        double sumaImporte = 0.00;

        for (int i = 0; i < summary.getCostsList().size(); i++) {
            Costs costs = summary.getCostsList().get(i);

            sumaImporte = sumaImporte + costs.getTotal();

            int count = i + 1;
            sumaCountGastos = count;

            TableRow tableRow3 = new TableRow(getActivity());

            TextView textViewDES = new TextView(getActivity());
            TextView textViewRUTA = new TextView(getActivity());
            TextView textViewIMPORTE = new TextView(getActivity());

            textViewDES.setGravity(Gravity.CENTER);
            textViewRUTA.setGravity(Gravity.CENTER);
            textViewIMPORTE.setGravity(Gravity.CENTER);


            textViewDES.setTextSize(12);
            textViewRUTA.setTextSize(12);
            textViewIMPORTE.setTextSize(12);

            textViewDES.setText(costs.getDescripcion());
            textViewRUTA.setText(costs.getEnRuta());
            textViewIMPORTE.setText(Utils.formatDoublePrint(costs.getTotal()));

            tableRow3.addView(textViewDES);
            tableRow3.addView(textViewRUTA);
            tableRow3.addView(textViewIMPORTE);

            tableLayoutGastos.addView(tableRow3, count);
        }

        TableRow tableRow3 = new TableRow(getActivity());
        tableRow3.setBackgroundColor(Color.parseColor("#bdbdbd"));
        TextView textDescrip3 = new TextView(getActivity());
        TextView textRuta3 = new TextView(getActivity());
        TextView textImporte3 = new TextView(getActivity());

        textDescrip3.setGravity(Gravity.CENTER);
        textRuta3.setGravity(Gravity.CENTER);
        textImporte3.setGravity(Gravity.CENTER);

        textDescrip3.setTypeface(Typeface.DEFAULT_BOLD);
        textRuta3.setTypeface(Typeface.DEFAULT_BOLD);
        textImporte3.setTypeface(Typeface.DEFAULT_BOLD);


        textDescrip3.setTextSize(12);
        textRuta3.setTextSize(12);
        textImporte3.setTextSize(12);

        textDescrip3.setText("Total");
        textRuta3.setText("");
        textImporte3.setText(Utils.formatDoublePrint(sumaImporte));

        tableRow3.addView(textDescrip3);
        tableRow3.addView(textRuta3);
        tableRow3.addView(textImporte3);

        tableLayoutGastos.addView(tableRow3, sumaCountGastos + 1);
    }
}
