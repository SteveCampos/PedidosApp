package energigas.apps.systemstrategy.energigas.fragments;

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

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.adapters.SummaryAdapter;
import energigas.apps.systemstrategy.energigas.entities.Concepto;
import energigas.apps.systemstrategy.energigas.entities.Costs;
import energigas.apps.systemstrategy.energigas.entities.Summary;
import energigas.apps.systemstrategy.energigas.entities.SummaryIncome;
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

/*
    @BindView(R.id.list)
    ListView listView;*/

    private Summary summary;
    private Resources res;

    private static final String TAG = "ResumentFragment";

    public static ResumentFragment newInstance() {
        ResumentFragment summary = new ResumentFragment();
        return summary;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.layout_fragment_summary, container, false);
        ButterKnife.bind(this, rootView);
        textViewDetalleItemsTotales.setVisibility(View.GONE);
        //SummaryAdapter adapter = new SummaryAdapter(getActivity(), 0, Summary.getListSummary(getActivity()));
        summary = Summary.getListSummary(getActivity());
        res = getActivity().getResources();
        // listView.setAdapter(adapter);
        setResumen();
        setIngresos();
        setCostos();
        return rootView;
    }

    private void setResumen() {
        if (summary == null || summary.getEfectivoRendir() <= 0.00) {
            return;
        }
        cardViewResumen.setVisibility(View.VISIBLE);

        textEfectivoRendir.setText(summary.getEfectivoRendir() + "");
        textViewTotalGastos.setText(summary.getGastos() + "");
        textIngresosTotales.setText(summary.getIngresosTotales() + "");
        textViewSaldoInicial.setText(summary.getSaldoInicial() + "");

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

        TableRow tableRow1 = new TableRow(getContext());

        TextView textDescripcion1 = new TextView(getContext());
        TextView textCantidad1 = new TextView(getContext());
        TextView textTotalEmitidos1 = new TextView(getContext());
        TextView textTotalPagados1 = new TextView(getContext());
        TextView textTotalCobrados1 = new TextView(getContext());

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

            TableRow tableRow = new TableRow(getContext());
            //tableRow.removeAllViews();

            TextView textDescripcion = new TextView(getContext());
            TextView textCantidad = new TextView(getContext());
            TextView textTotalEmitidos = new TextView(getContext());
            TextView textTotalPagados = new TextView(getContext());
            TextView textTotalCobrados = new TextView(getContext());

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
            textCantidad.setText(aCantidad.intValue() + "");
            textTotalEmitidos.setText(Utils.formatDouble(Double.parseDouble(income.getEmitidos())));
            textTotalPagados.setText(Utils.formatDouble(Double.parseDouble(income.getPagados())));
            textTotalCobrados.setText(Utils.formatDouble(Double.parseDouble(income.getCobrados())));


            tableLayoutIngresos.addView(tableRow, count);
        }


        TableRow tableRow = new TableRow(getContext());
        tableRow.setBackgroundColor(Color.parseColor("#bdbdbd"));
        TextView textDescripcion = new TextView(getContext());
        TextView textCantidad = new TextView(getContext());
        TextView textTotalEmitidos = new TextView(getContext());
        TextView textTotalPagados = new TextView(getContext());
        TextView textTotalCobrados = new TextView(getContext());

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
        textTotalEmitidos.setText(Utils.formatDouble(sumaTotalEmitidos));
        textTotalPagados.setText(Utils.formatDouble(sumaTotalPagados));
        textTotalCobrados.setText(Utils.formatDouble(sumaTotalCobradas));

        tableLayoutIngresos.addView(tableRow, sumaCount + 1);
    }

    private void setCostos() {
        if (summary == null || summary.getCostsList().size() < 1) {
            return;
        }
        cardViewCostos.setVisibility(View.VISIBLE);

        TableRow tableRow2 = new TableRow(getContext());

        TextView textDescrip = new TextView(getContext());
        TextView textRuta = new TextView(getContext());
        TextView textImporte = new TextView(getContext());

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

            TableRow tableRow3 = new TableRow(getContext());

            TextView textViewDES = new TextView(getContext());
            TextView textViewRUTA = new TextView(getContext());
            TextView textViewIMPORTE = new TextView(getContext());

            textViewDES.setGravity(Gravity.CENTER);
            textViewRUTA.setGravity(Gravity.CENTER);
            textViewIMPORTE.setGravity(Gravity.CENTER);


            textViewDES.setTextSize(12);
            textViewRUTA.setTextSize(12);
            textViewIMPORTE.setTextSize(12);

            textViewDES.setText(costs.getDescripcion());
            textViewRUTA.setText(costs.getEnRuta());
            textViewIMPORTE.setText(Utils.formatDouble(costs.getTotal()));

            tableRow3.addView(textViewDES);
            tableRow3.addView(textViewRUTA);
            tableRow3.addView(textViewIMPORTE);

            tableLayoutGastos.addView(tableRow3, count);
        }

        TableRow tableRow3 = new TableRow(getContext());
        tableRow3.setBackgroundColor(Color.parseColor("#bdbdbd"));
        TextView textDescrip3 = new TextView(getContext());
        TextView textRuta3 = new TextView(getContext());
        TextView textImporte3 = new TextView(getContext());

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
        textImporte3.setText(Utils.formatDouble(sumaImporte));

        tableRow3.addView(textDescrip3);
        tableRow3.addView(textRuta3);
        tableRow3.addView(textImporte3);

        tableLayoutGastos.addView(tableRow3, sumaCountGastos + 1);
    }
}
