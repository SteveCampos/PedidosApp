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
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.entities.OrderDispatch;
import energigas.apps.systemstrategy.energigas.holders.DispatchHolder;
import energigas.apps.systemstrategy.energigas.utils.Utils;

/**
 * Created by Steve on 22/07/2016.
 */

public class DisptachAdapter extends RecyclerView.Adapter<DispatchHolder> {

    // Store a member variable for the list;
    private List<OrderDispatch> dispatchList;
    // Store the context for easy access
    private Context mContext;
    private OnDispatchClickListener listener;
    private SparseBooleanArray mSelectedItemsIds = new SparseBooleanArray();;

    public interface OnDispatchClickListener{
        void onDispatchClickListener(OrderDispatch orderDispatch, View view);
    }

    public DisptachAdapter(List<OrderDispatch> dispatchList, Context mContext, OnDispatchClickListener listener) {
        this.dispatchList = dispatchList;
        this.mContext = mContext;
        this.listener = listener;
    }

    @Override
    public DispatchHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        // Inflate the custom layout
        View dispatchView = inflater.inflate(R.layout.item_ordered_dispatch, parent, false);
        // Return a new holder instance
        return new DispatchHolder(dispatchView);
    }

    @Override
    public void onBindViewHolder(DispatchHolder holder, int position) {
        //holder.bind(dispatchList.get(position), mContext, listener, position);
        final OrderDispatch orderDispatch = dispatchList.get(position);
        holder.textViewTank.setText(orderDispatch.getTitleTank());
        holder.textViewQuantity.setText(Utils.formatDouble(orderDispatch.getQuantityDisptach()) + " GALONES");

        int positionToColor = position % 3 ;
        String hexColor = "#ffd6d7d7";

        Log.d(Utils.TAG, "POSITION : " + position);
        Log.d(Utils.TAG, "RESTO : " + positionToColor);
        boolean setCheckd = false;
        switch (positionToColor){
            case 0:
                hexColor = "#0F000000";
                setCheckd = true;
                break;
            case 1:
                hexColor = "#ffffff";
                setCheckd = false;
                break;
            case 2:
                hexColor = "#0A000000";
                setCheckd = false;
                break;
        }
        if (!setCheckd){
            //imageViewChecked.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_check_colored));
            holder.imageViewChecked.setVisibility(View.GONE);
        }

        int colorAccent = ContextCompat.getColor(mContext, R.color.colorAccent);
            /*
            .setBackgroundColor(mSelectedItemsIds.get(position) ? colorAccent
                    : Color.TRANSPARENT);*/

        //holder.relativeLayout.setBackgroundColor(Color.parseColor(hexColor));
        holder.itemView.setBackgroundColor(mSelectedItemsIds.get(position, false) ? colorAccent: Color.parseColor(hexColor));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onDispatchClickListener(orderDispatch, view);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dispatchList.size();
    }

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
//    public static class ViewHolder extends RecyclerView.ViewHolder {
//        // Your holder should contain a member variable
//        // for any view that will be set as you render a row
//        public TextView textViewTank;
//        public TextView textViewQuantity;
//        public RelativeLayout relativeLayout;
//        public ImageView imageViewChecked;
//
//        // We also create a constructor that accepts the entire item row
//        // and does the view lookups to find each subview
//        public ViewHolder(View itemView) {
//            // Stores the itemView in a public final member variable that can be used
//            // to access the context from any ViewHolder instance.
//            super(itemView);
//
//            textViewTank = (TextView) itemView.findViewById(R.id.textViewTanque);
//            textViewQuantity = (TextView) itemView.findViewById(R.id.textviewQuantity);
//            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.layoutBackground);
//            imageViewChecked = (ImageView) itemView.findViewById(R.id.imageView);
//        }
///*
//        public void bind(final OrderDispatch orderDispatch, Context context, final OnDispatchClickListener listener, int position){
//            textViewTank.setText(orderDispatch.getTitleTank());
//            textViewQuantity.setText(Utils.formatDouble(orderDispatch.getQuantityDisptach()) + " GALONES");
//
//            int positionToColor = position % 3 ;
//            String hexColor = "#ffd6d7d7";
//
//            Log.d(Utils.TAG, "POSITION : " + position);
//            Log.d(Utils.TAG, "RESTO : " + positionToColor);
//            boolean setCheckd = false;
//            switch (positionToColor){
//                case 0:
//                    hexColor = "#0F000000";
//                    setCheckd = true;
//                    break;
//                case 1:
//                    hexColor = "#ffffff";
//                    setCheckd = false;
//                    break;
//                case 2:
//                    hexColor = "#0A000000";
//                    setCheckd = false;
//                    break;
//            }
//            if (!setCheckd){
//                //imageViewChecked.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_check_colored));
//                imageViewChecked.setVisibility(View.GONE);
//            }
//
//
//
//            relativeLayout.setBackgroundColor(Color.parseColor(hexColor));
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    listener.onDispatchClickListener(orderDispatch, view);
//                }
//            });
//
//        }*/
//    }


    /***
     * Methods required for do selections, remove selections, etc.
     */

    //Toggle selection methods
    public void toggleSelection(int position) {
        selectView(position, !mSelectedItemsIds.get(position));
    }


    //Remove selected selections
    public void removeSelection() {
        mSelectedItemsIds = new SparseBooleanArray();
        notifyDataSetChanged();
    }


    //Put or delete selected position into SparseBooleanArray
    public void selectView(int position, boolean value) {
        if (value)
            mSelectedItemsIds.put(position, value);
        else
            mSelectedItemsIds.delete(position);

        notifyDataSetChanged();
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
