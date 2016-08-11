package energigas.apps.systemstrategy.energigas.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.entities.Pedido;
import energigas.apps.systemstrategy.energigas.holders.PedidoHolder;

/**
 * Created by Kike on 21/07/2016.
 */

public class PedidoAdapter extends RecyclerView.Adapter<PedidoHolder> {

    // Store a member variable for the contacts
    private List<Pedido> mListPedidos;
    // Store the context for easy access
    private Context mContext;


    public interface OnPedidoClickListener {
        void onPedidoClickListener(Pedido pedido, View view);
    }

    private OnPedidoClickListener listener;

    public PedidoAdapter(List<Pedido> mListPedidos, Context mContext, OnPedidoClickListener listener) {
        this.mListPedidos = mListPedidos;
        this.mContext = mContext;
        this.listener = listener;
    }


    @Override
    public PedidoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_orders, parent, false);
        // Return a new holder instance
        return new PedidoHolder(contactView);
    }

    @Override
    public void onBindViewHolder(PedidoHolder holder, int position) {
        // Get the data model based on position
        //
        //holder.bind(mListPedidos.get(position), mContext, listener);

        final Pedido pedido = mListPedidos.get(position);

        /*
        holder.mProducts.setText(Utils.capitalize(pedido.getProductsName()[0]));
        holder.mAddress.setText(Utils.capitalize(pedido.getEstablecimiento().getEstVName()));
        holder.mLocation.setText(mContext.getString(R.string.estable_default_marker));
        holder.mClock.setText(new SimpleDateFormat("hh:mm", Locale.ENGLISH).format(new Date(pedido.getEndTime())));

        holder.btnDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onPedidoClickListener(pedido, view);
            }
        });
        holder.imageViewStation.setImageDrawable(ContextCompat.getDrawable(mContext, getImage(pedido.getEstablecimiento().getEstTipoOperacion())));
        */
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onPedidoClickListener(pedido, view);
            }
        });
    }


    int getImage(String tipoestacion) {
        switch (tipoestacion) {
            case "externo":
                return R.drawable.ic_gas_station_c;
            case "interno":
                return R.drawable.logo;
            default:
                return R.drawable.logo;
        }
    }





    @Override
    public int getItemCount() {
        return mListPedidos.size();
    }
}
