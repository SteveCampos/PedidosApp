package energigas.apps.systemstrategy.energigas.adapters;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.entities.Order;
import energigas.apps.systemstrategy.energigas.utils.Utils;

/**
 * Created by Kike on 21/07/2016.
 */

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.ViewHolder> {

    // Store a member variable for the contacts
    private List<Order> mListOrders;
    // Store the context for easy access
    private Context mContext;




    public interface OnOrdersClickListener{
        void onOrdersClickListener(Order order, View view);
    }

    public OnOrdersClickListener listener;

    public OrdersAdapter(List<Order> mListOrders, Context mContext, OnOrdersClickListener listener) {
        this.mListOrders = mListOrders;
        this.mContext = mContext;
        this.listener = listener;
    }





    @Override
    public OrdersAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_orders, parent, false);
        // Return a new holder instance
        return new OrdersAdapter.ViewHolder(contactView);
    }

    @Override
    public void onBindViewHolder(OrdersAdapter.ViewHolder holder, int position) {
        // Get the data model based on position
        //
        holder.bind(mListOrders.get(position), mContext, listener);
    }

    @Override
    public int getItemCount() {
        return mListOrders.size();
    }

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    static class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
//        TextView nameTextView;
        TextView mProducts;
        TextView mAddress;
        TextView mLocation;
        TextView mClock;
        ImageView imageViewStation;
        AppCompatButton btnDetails;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);
            mProducts = (TextView) itemView.findViewById(R.id.txtproducts);
            mAddress=(TextView) itemView.findViewById(R.id.txtaddress);
            mLocation=(TextView) itemView.findViewById(R.id.txtlocation);
            mClock=(TextView) itemView.findViewById(R.id.txtclock);
            imageViewStation = (ImageView)itemView.findViewById(R.id.imageViewStation);
            btnDetails = (AppCompatButton) itemView.findViewById(R.id.btnDetails);


        }
        void bind(final Order order, Context context, final OnOrdersClickListener listener) {
            mProducts.setText(Utils.capitalize(order.getProductsName()));
            mAddress.setText(Utils.capitalize(order.getStation().getEstVName()));
            mLocation.setText("2.4 km");
            mClock.setText(new SimpleDateFormat("hh:mm", Locale.ENGLISH).format(new Date(order.getEndTime())));

            imageViewStation.setImageDrawable(ContextCompat.getDrawable(context, getImage(order.getStation().getEstTipoOperacion())));
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onOrdersClickListener(order, view);
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
    }


}
