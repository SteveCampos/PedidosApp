package energigas.apps.systemstrategy.energigas.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseBooleanArray;
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

    private SparseBooleanArray mSelectedItemsIds;
    private OnStationDispatchClickListener listener;

    public interface OnStationDispatchClickListener{
        void onStationDispatchClickListener(OrderDispatch orderDispatch, View view, int typeListener);
    }

    public StationDispatchsAdapter(List<OrderDispatch> stationDispatches, Context mContext, OnStationDispatchClickListener listener) {
        this.stationDispatches = stationDispatches;
        this.mContext = mContext;
        this.listener = listener;
        mSelectedItemsIds = new SparseBooleanArray();
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
        final OrderDispatch dispatch = stationDispatches.get(position);


        int number = position;
        number++;

        holder.dispatchTank.setText("Tanque " + (number));
        holder.dispatchProduct.setText("GLP");
        holder.dispatchQuantity.setText(dispatch.getQuantityDisptach() + " Gal");

        int colorAccent = ContextCompat.getColor(mContext, R.color.colorAccent);
        int colorWhite = ContextCompat.getColor(mContext, R.color.white);
        /** Change background color of the selected items  **/
        holder.itemView
                .setBackgroundColor(mSelectedItemsIds.get(position, false) ? colorAccent
                        : Color.WHITE);
        holder.dispatchQuantity.setTextColor(mSelectedItemsIds.get(position, false) ? colorWhite : colorAccent);



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onStationDispatchClickListener(dispatch, view, 0);
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                listener.onStationDispatchClickListener(dispatch, view, 1);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return stationDispatches.size();
    }


    /***
     * Methods required for do selections, remove selections, etc.
     */

    //Toggle selection methods
    public void toggleSelection(int position) {
        selectView(position, !mSelectedItemsIds.get(position));
    }


    //Remove selected selections
    public void removeSelection(SparseBooleanArray mStaticSelecteds) {

        /*
        for (int i = 0; i < mStaticSelecteds.size(); i++) {
            int position = mStaticSelecteds.keyAt(i);
            toggleSelection(position);
        }*/
        mSelectedItemsIds = new SparseBooleanArray();
        notifyDataSetChanged();
    }


    //Put or delete selected position into SparseBooleanArray
    private void selectView(int position, boolean value) {
        if (value)
            mSelectedItemsIds.put(position, true);
        else
            mSelectedItemsIds.delete(position);

        notifyItemChanged(position);
    }

    //Get total selected count
    public int getSelectedCount() {
        return mSelectedItemsIds.size();
    }

    //Return all selected ids
    public SparseBooleanArray getSelectedIds() {
        return mSelectedItemsIds;
    }

}
