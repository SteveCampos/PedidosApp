package energigas.apps.systemstrategy.energigas.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Date;
import java.util.List;

import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.entities.Estado;
import energigas.apps.systemstrategy.energigas.entities.Pedido;
import energigas.apps.systemstrategy.energigas.entities.PedidoDetalle;
import energigas.apps.systemstrategy.energigas.entities.Producto;
import energigas.apps.systemstrategy.energigas.entities.Unidad;
import energigas.apps.systemstrategy.energigas.holders.EstablecimientoPedidoHolder;
import energigas.apps.systemstrategy.energigas.utils.Constants;
import energigas.apps.systemstrategy.energigas.utils.Utils;

/**
 * Created by Steve on 8/08/2016.
 */

public class StationOrdersAdapter extends RecyclerView.Adapter<EstablecimientoPedidoHolder> {
    private static final String TAG = StationProductsAdapter.class.getSimpleName();
    // Store a member variable for the list;
    private List<Pedido> pedidoList;
    // Store the context for easy access
    private Context mContext;
    public OnOrderClickListener listener;

    public interface OnOrderClickListener {
        public void onOrderClickListener(Pedido pedido);
    }

    public StationOrdersAdapter(List<Pedido> pedidoList, Context mContext, OnOrderClickListener listener) {
        this.pedidoList = pedidoList;
        this.mContext = mContext;
        this.listener = listener;
    }

    @Override
    public EstablecimientoPedidoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        // Inflate the custom layout
        View view = inflater.inflate(R.layout.item_pedido, parent, false);
        // Return a new holder instance
        return new EstablecimientoPedidoHolder(view);
    }

    @Override
    public void onBindViewHolder(EstablecimientoPedidoHolder holder, int position) {

        final Pedido pedido = pedidoList.get(position);
        Estado estados = Estado.find(Estado.class, " id_Estado = ?", new String[]{pedido.getEstadoId() + ""}).get(Constants.CURRENT);
        PedidoDetalle pedidoDetalle = PedidoDetalle.find(PedidoDetalle.class, " pe_Id = ?", new String[]{pedido.getPeId() + ""}).get(Constants.CURRENT);
        Producto producto = Producto.find(Producto.class, " pro_Id = ?", new String[]{pedidoDetalle.getProductoId() + ""}).get(Constants.CURRENT);
        Unidad unidad = Unidad.getUnidadProductobyUnidadMedidaId(pedidoDetalle.getUnidadId() + "");
        holder.scope.setText("SCOP: " + pedido.getScop() + "");
        holder.state.setText(estados.getDescripcion() + "");
        holder.quantity.setText(Utils.formatDoublePrint(pedidoDetalle.getCantidad()) + " " + unidad.getAbreviatura());
        holder.title.setText(producto.getNombre() + "");
//        holder.programedDate.setText(Utils.getNameOfDay(new Date(pedido.getFechaEntregaProgramada())));
        holder.textPrecio.setText("Precio+IGV : " + Utils.formatDouble(pedidoDetalle.getPrecioUnitario()) + " \n Precio-IGV : " + Utils.formatDouble(pedidoDetalle.getPrecio()));
        holder.textHorarioAtencion.setText("Horario Entrega: " + pedido.getHoraEntrega());
        holder.fechaProgramed.setText("Fecha de Entrega: "+pedido.getFechaEntrega());
        holder.textNumeroPedidos.setText("Nro de Pedidos: " +pedido.getNumero());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onOrderClickListener(pedido);
            }
        });
    }

    @Override
    public int getItemCount() {
        return pedidoList.size();
    }

}