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
import energigas.apps.systemstrategy.energigas.entities.Order;
import energigas.apps.systemstrategy.energigas.entities.OrderProduct;
import energigas.apps.systemstrategy.energigas.holders.StationOrdersHolder;
import energigas.apps.systemstrategy.energigas.holders.StationProductsHolder;
import energigas.apps.systemstrategy.energigas.utils.Utils;

/**
 * Created by Steve on 8/08/2016.
 */

public class StationOrdersAdapter extends RecyclerView.Adapter<StationOrdersHolder> {
    private static final String TAG = StationProductsAdapter.class.getSimpleName();
    // Store a member variable for the list;
    private List<Order> orderList;
    // Store the context for easy access
    private Context mContext;
    public OnOrderClickListener listener;

    public interface OnOrderClickListener{
        public void onOrderClickListener(Order order);
    }

    public StationOrdersAdapter(List<Order> orderList, Context mContext, OnOrderClickListener listener) {
        this.orderList = orderList;
        this.mContext = mContext;
        this.listener = listener;
    }

    @Override
    public StationOrdersHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        // Inflate the custom layout
        View view = inflater.inflate(R.layout.item_station_order, parent, false);
        // Return a new holder instance
        return new StationOrdersHolder(view);
    }

    @Override
    public void onBindViewHolder(StationOrdersHolder holder, int position) {
        final Order order = orderList.get(position);

        holder.orderTitle.setText("PEDIDO 3000" + position);
        holder.programedDate.setText(Utils.getNameOfDay(new Date(order.getOrderDate())));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "holder.itemView.setOnClickListener");
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onOrderClickListener(order);
            }
        });
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

}