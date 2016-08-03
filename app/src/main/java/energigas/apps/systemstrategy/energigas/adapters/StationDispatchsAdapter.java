package energigas.apps.systemstrategy.energigas.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.entities.OrderDispatch;
import energigas.apps.systemstrategy.energigas.entities.OrderProduct;
import energigas.apps.systemstrategy.energigas.holders.StationDispatchsHolder;
import energigas.apps.systemstrategy.energigas.holders.StationProductsHolder;

/**
 * Created by Steve on 3/08/2016.
 */

public class StationDispatchsAdapter extends RecyclerView.Adapter<StationDispatchsHolder> {

    private static final String TAG = StationDispatchsAdapter.class.getSimpleName();
    // Store a member variable for the list;
    private List<OrderDispatch> stationDispatches;
    // Store the context for easy access
    private Context mContext;

    public StationDispatchsAdapter(List<OrderDispatch> stationDispatches, Context mContext) {
        this.stationDispatches = stationDispatches;
        this.mContext = mContext;
    }

    @Override
    public StationDispatchsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        // Inflate the custom layout
        View view = inflater.inflate(R.layout.item_station_dispatch, parent, false);
        // Return a new holder instance
        return new StationDispatchsHolder(view);
    }

    @Override
    public void onBindViewHolder(StationDispatchsHolder holder, int position) {
        OrderDispatch dispatch = stationDispatches.get(position);
        holder.dispatchTank.setText(dispatch.getTitleTank());
        holder.dispatchProduct.setText("GLP");
        holder.dispatchQuantity.setText(dispatch.getQuantityDisptach() + " Gal");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "holder.itemView.setOnClickListener");
            }
        });
    }

    @Override
    public int getItemCount() {
        return stationDispatches.size();
    }
}
