package energigas.apps.systemstrategy.energigas.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.entities.Pedido;
import energigas.apps.systemstrategy.energigas.entities.PedidoDetalle;
import energigas.apps.systemstrategy.energigas.entities.Producto;
import energigas.apps.systemstrategy.energigas.entities.Unidad;
import energigas.apps.systemstrategy.energigas.holders.OrderDetailHolder;
import energigas.apps.systemstrategy.energigas.utils.Constants;
import energigas.apps.systemstrategy.energigas.utils.Utils;

/**
 * Created by Steve on 10/08/2016.
 */

public class OrderDetailAdapter extends RecyclerView.Adapter<OrderDetailHolder> {

    private static final String TAG = OrderDetailAdapter.class.getSimpleName();
    // Store a member variable for the list;
    private List<PedidoDetalle> list;
    // Store the context for easy access
    private Context mContext;

    public OrderDetailAdapter(List<PedidoDetalle> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
    }

    @Override
    public OrderDetailHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        // Inflate the custom layout
        View view = inflater.inflate(R.layout.item_order_detail, parent, false);
        // Return a new holder instance
        return new OrderDetailHolder(view);
    }

    @Override
    public void onBindViewHolder(OrderDetailHolder holder, int position) {
        final PedidoDetalle pedidoDetalle = list.get(position);
        Producto producto = Producto.find(Producto.class, " pro_Id = ?", new String[]{pedidoDetalle.getProductoId() + ""}).get(Constants.CURRENT);
        //PedidoDetalle pedidoDetalle1 = PedidoDetalle.find(PedidoDetalle.class," id_Detalle = ?", new String[]{pedidoDetalle.getIdDetalle()+""}).get(Constants.CURRENT);
        Pedido pedido = Pedido.find(Pedido.class, " pe_Id = ?", new String[]{pedidoDetalle.getPeId() + ""}).get(Constants.CURRENT);
        Log.d(TAG, "sentence list:" + producto.getProId());
        Unidad unidad = Unidad.getUnidadProductobyUnidadMedidaId(pedidoDetalle.getUnidadId() + "");        /* SIN IGV*/
        holder.title.setText(producto.getNombre() + "");
        holder.quantity.setText(Utils.formatDoublePrint(pedidoDetalle.getCantidad()) + " " + unidad.getAbreviatura());
        /*holder.cost.setText("S/." + Utils.formatDouble(pedidoDetalle.getPrecioUnitario()) + "");
        holder.precio.setText("TOTAL: S/." + Utils.formatDouble(pedido.getTotal()) + "\n" +
                "IGV: S/." + Utils.formatDouble(pedido.getiGV()));*/

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "CLICKED: " + view);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
