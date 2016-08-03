package energigas.apps.systemstrategy.energigas.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.entities.OrderProduct;
import energigas.apps.systemstrategy.energigas.holders.StationProductsHolder;

/**
 * Created by Steve on 3/08/2016.
 */

public class StationProductsAdapter extends RecyclerView.Adapter<StationProductsHolder> {
    private static final String TAG = StationProductsAdapter.class.getSimpleName();
    // Store a member variable for the list;
    private List<OrderProduct> stationProducts;
    // Store the context for easy access
    private Context mContext;

    public StationProductsAdapter(List<OrderProduct> stationProducts, Context mContext) {
        this.stationProducts = stationProducts;
        this.mContext = mContext;
    }

    @Override
    public StationProductsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        // Inflate the custom layout
        View view = inflater.inflate(R.layout.item_station_product, parent, false);
        // Return a new holder instance
        return new StationProductsHolder(view);
    }

    @Override
    public void onBindViewHolder(StationProductsHolder holder, int position) {
        OrderProduct stationProduct = stationProducts.get(position);
        holder.productTitle.setText(stationProduct.getProduct().getName());
        holder.productQuantity.setText(stationProduct.getQuantityProduct() + stationProduct.getProduct().getUnidadMedida());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "holder.itemView.setOnClickListener");
            }
        });
    }

    @Override
    public int getItemCount() {
        return stationProducts.size();
    }
}
