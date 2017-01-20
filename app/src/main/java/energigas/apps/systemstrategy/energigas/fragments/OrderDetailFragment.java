package energigas.apps.systemstrategy.energigas.fragments;


import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.adapters.OrderDetailAdapter;
import energigas.apps.systemstrategy.energigas.entities.Almacen;
import energigas.apps.systemstrategy.energigas.entities.ComprobanteVenta;
import energigas.apps.systemstrategy.energigas.entities.Concepto;
import energigas.apps.systemstrategy.energigas.entities.Despacho;
import energigas.apps.systemstrategy.energigas.entities.Pedido;
import energigas.apps.systemstrategy.energigas.entities.PedidoDetalle;
import energigas.apps.systemstrategy.energigas.entities.Producto;
import energigas.apps.systemstrategy.energigas.entities.SummaryIncome;
import energigas.apps.systemstrategy.energigas.entities.Unidad;
import energigas.apps.systemstrategy.energigas.utils.Constants;
import energigas.apps.systemstrategy.energigas.utils.Session;
import energigas.apps.systemstrategy.energigas.utils.Utils;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrderDetailFragment extends Fragment {


    private List<PedidoDetalle> list;

    /*@BindView(R.id.my_recycler_view)
    RecyclerView recyclerView;*/
    private OrderDetailAdapter adapter;
    private static final String TAG = "OrderDetailFragment";

    private View view;
    @BindView(R.id.tableLayoutItem)
    TableLayout tableLayoutDetails;

    private Pedido pedido;
    private List<Despacho> despachoList;

    @BindView(R.id.textPrecioIgv)
    TextView textViewPrecioIgv;

    @BindView(R.id.textPrecioSinIgv)
    TextView textViewPrecioSinIgv;

    private Concepto concepto;

    public OrderDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(
                R.layout.layout_item_order_detail, container, false);
        ButterKnife.bind(this, view);
      /*  pedido = Session.getPedido(getActivity());
        list = PedidoDetalle.find(PedidoDetalle.class, " pe_Id = ? ", new String[]{pedido.getPeId() + ""});
        adapter = new OrderDetailAdapter(list, getActivity());
        recyclerView.setAdapter(adapter);
        //recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));*/
        pedido = Pedido.getPedidoById(Session.getPedido(getActivity()).getPeId() + "");
        PedidoDetalle pedidoDetalle = PedidoDetalle.find(PedidoDetalle.class, " pe_Id = ?", new String[]{pedido.getPeId() + ""}).get(Constants.CURRENT);
        concepto = Session.getConceptoIGV();
        textViewPrecioIgv.setText("Precio + IGV : S/. " + Utils.formatDoublePrint(pedidoDetalle.getPrecioUnitario()));
        textViewPrecioSinIgv.setText("Precio - IGV : S/. " + Utils.formatDoublePrint(pedidoDetalle.getPrecio()));
        despachoList = Despacho.getListDespachoByPedido(pedido.getPeId() + "");

        if (despachoList != null) {
            initCabecera();
            initBody();
        }


        return view;
    }


    private void initCabecera() {

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

        textDescripcion1.setText("Cantidad");
        textCantidad1.setText("U.M");
        textTotalEmitidos1.setText("Producto");
        textTotalPagados1.setText("Tanque");
        textTotalCobrados1.setText("Importe");

        tableLayoutDetails.addView(tableRow1, 0);

    }

    private void initBody() {
        int sumaCount = 0;
        Double sumaCantidad = 0.00;
        Double sumaImporte = 0.00;
        Double sumaIgv = 0.00;


        for (int i = 0; i < despachoList.size(); i++) {


            int count = i + 1;
            sumaCount = count;
            Despacho despacho = despachoList.get(i);

            sumaCantidad = sumaCantidad + Double.parseDouble(despacho.getCantidadDespachada() + "");
            sumaImporte = Utils.formatDoubleNumber(despacho.getCantidadDespachada() * despacho.getPrecioUnitarioSIGV());
            sumaIgv = sumaImporte * Double.parseDouble(concepto.getDescripcion());


            TableRow tableRow = new TableRow(getContext());
            //tableRow.removeAllViews();

            TextView textCantidad = new TextView(getContext());
            TextView textUnidadMedida = new TextView(getContext());
            TextView textProducto = new TextView(getContext());
            TextView textTanque = new TextView(getContext());
            TextView textImporte = new TextView(getContext());

            textCantidad.setGravity(Gravity.CENTER);
            textUnidadMedida.setGravity(Gravity.CENTER);
            textProducto.setGravity(Gravity.CENTER);
            textTanque.setGravity(Gravity.CENTER);
            textImporte.setGravity(Gravity.CENTER);


            textCantidad.setTextSize(12);
            textUnidadMedida.setTextSize(12);
            textProducto.setTextSize(12);
            textTanque.setTextSize(12);
            textImporte.setTextSize(12);


            tableRow.addView(textCantidad);
            tableRow.addView(textUnidadMedida);
            tableRow.addView(textProducto);
            tableRow.addView(textTanque);
            tableRow.addView(textImporte);

            Unidad unidad = Unidad.getUnidadProductobyUnidadMedidaId(despacho.getUnId() + "");
            Producto producto = Producto.getProductoById(despacho.getProId() + "");
            Almacen almacen = Almacen.getAlmacenById(despacho.getAlmacenEstId() + "");


            textCantidad.setText(despacho.getCantidadDespachada() + "");
            textUnidadMedida.setText(unidad.getAbreviatura() + "");
            textProducto.setText(producto.getNombre() + "");
            textTanque.setText(almacen.getNombre() + "");

            textImporte.setText("" + Utils.formatDoubleNumber(despacho.getCantidadDespachada() * despacho.getPrecioUnitarioSIGV()));


            tableLayoutDetails.addView(tableRow, count);
        }


        tableLayoutDetails.addView(getBottom(Utils.formatDouble(sumaIgv), "I.G.V"), sumaCount + 1);
        tableLayoutDetails.addView(getBottom(Utils.formatDouble(sumaImporte + sumaIgv), "TOTAL"), sumaCount + 2);

    }

    private TableRow getBottom(String importe, String textDescripcion) {
        TableRow tableRow = new TableRow(getContext());
        //  tableRow.setBackgroundColor(Color.parseColor("#bdbdbd"));
        TextView textCantidad = new TextView(getContext());
        TextView textUnidadMedida = new TextView(getContext());
        TextView textProducto = new TextView(getContext());
        TextView textTanque = new TextView(getContext());
        TextView textImporte = new TextView(getContext());

        textCantidad.setGravity(Gravity.CENTER);
        textUnidadMedida.setGravity(Gravity.CENTER);
        textProducto.setGravity(Gravity.CENTER);
        textTanque.setGravity(Gravity.CENTER);
        textImporte.setGravity(Gravity.CENTER);

        textCantidad.setTextSize(12);
        textUnidadMedida.setTextSize(12);
        textProducto.setTextSize(12);
        textTanque.setTextSize(12);
        textImporte.setTextSize(12);

        tableRow.addView(textCantidad);
        tableRow.addView(textUnidadMedida);
        tableRow.addView(textProducto);
        tableRow.addView(textTanque);
        tableRow.addView(textImporte);

        textTanque.setBackgroundColor(Color.parseColor("#bdbdbd"));
        textImporte.setBackgroundColor(Color.parseColor("#bdbdbd"));
        textTanque.setText(textDescripcion);
        textImporte.setText(importe);
        return tableRow;
    }

}
