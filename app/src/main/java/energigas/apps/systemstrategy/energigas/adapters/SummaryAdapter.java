package energigas.apps.systemstrategy.energigas.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.List;

import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.entities.ComprobanteVenta;
import energigas.apps.systemstrategy.energigas.entities.Concepto;
import energigas.apps.systemstrategy.energigas.entities.Costs;
import energigas.apps.systemstrategy.energigas.entities.Summary;
import energigas.apps.systemstrategy.energigas.entities.SummaryIncome;
import energigas.apps.systemstrategy.energigas.holders.SummaryHolder;
import energigas.apps.systemstrategy.energigas.utils.Utils;

/**
 * Created by kelvi on 20/07/2016.
 */

public class SummaryAdapter extends ArrayAdapter<Summary> {

    private List<Summary> summaries;
    private Resources res;

    public SummaryAdapter(Context context, int resource, List<Summary> objects) {
        super(context, resource, objects);
        this.summaries = objects;
        res = context.getResources();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtener inflater.
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = null;
        Summary summary = summaries.get(position);
        int viewType = summary.getType();


        switch (viewType) {
            case 0:

                view = inflater.inflate(R.layout.layout_item_all_sumary, parent, false);
                TextView textEfectivoRendir = (TextView) view.findViewById(R.id.textEfectivoRendir);
                TextView textViewTotalGastos = (TextView) view.findViewById(R.id.textTotalGastos);
                TextView textViewDetalleItemsTotales = (TextView) view.findViewById(R.id.textDetalleItemTotales);
                TextView textIngresosTotales = (TextView) view.findViewById(R.id.textIngresosTotales);
                TextView textViewSaldoInicial = (TextView) view.findViewById(R.id.textSaldoInicial);

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

                String stringFormarItems = String.format(res.getString(R.string.text_detalle_ingresos), detalleStrings);
                textViewDetalleItemsTotales.setText(stringFormarItems);


                break;
            case 1:

                view = inflater.inflate(R.layout.layout_item_all_income, parent, false);
                TableLayout tableLayoutIngresos = (TableLayout) view.findViewById(R.id.tableLayoutIngresos);




                break;
            case 2:
                view = inflater.inflate(R.layout.layout_item_all_costs, parent, false);

                TableLayout tableLayoutGastos = (TableLayout) view.findViewById(R.id.tablayoutGastos);




                break;
        }


        return view;
    }

}
