package energigas.apps.systemstrategy.energigas.adapters;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.entities.OrderDispatch;
import energigas.apps.systemstrategy.energigas.entities.OrderProduct;
import energigas.apps.systemstrategy.energigas.holders.OrderedProductHolder;

/**
 * Created by Steve on 22/07/2016.
 */

public class OrderedProductAdapter extends RecyclerView.Adapter<OrderedProductHolder> implements DisptachAdapter.OnDispatchClickListener {

    // Store a member variable for the list;
    private List<OrderProduct> orderProductList;
    // Store the context for easy access
    private Context mContext;
    private OnOrderedProductClickListener listener;
    private OnDispatchClickListener listenerOrderedDispatch;
    private SparseBooleanArray selectedItems;
    private DisptachAdapter disptachAdapter;
    private RecyclerView recyclerView;

    public OrderedProductAdapter(List<OrderProduct> orderProductList, Context mContext, OnOrderedProductClickListener listener, OnDispatchClickListener listenerOrderedDispatch) {
        this.orderProductList = orderProductList;
        this.mContext = mContext;
        this.listener = listener;
        this.listenerOrderedDispatch = listenerOrderedDispatch;
    }

    @Override
    public void onDispatchClickListener(OrderDispatch orderDispatch, View view) {
        disptachAdapter.toggleSelection(recyclerView.getChildAdapterPosition(view));
        listenerOrderedDispatch.onDispatchClicckListener(orderDispatch, view);
    }

    public interface OnOrderedProductClickListener{
        void onOrderedProductClickListener(OrderProduct orderProduct, View view, int typeClick);
    }

    public interface OnDispatchClickListener{
        void onDispatchClicckListener(OrderDispatch orderDispatch, View view);
    }

    @Override
    public OrderedProductHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_ordered_product, parent, false);
        // Return a new holder instance
        return new OrderedProductHolder(contactView);
    }

    @Override
    public void onBindViewHolder(final OrderedProductHolder holder, final int position) {

//            holder.bind(orderProductList.get(position), mContext, listener);
        TextView textViewOrderedProduct = holder.textViewProductOrdered;
        textViewOrderedProduct.setText(orderProductList.get(position).getProductOrderedTitle());


        textViewOrderedProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onOrderedProductClickListener(orderProductList.get(position), view, 0);
            }
        });

        textViewOrderedProduct.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                listener.onOrderedProductClickListener(orderProductList.get(position), view, 1);
                return false;
            }
        });

        recyclerView = holder.recyclerViewDisptaches;
        //DISPLAY ITEM DISPATCHES IN HORIZONTAL MODE
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(false);

        disptachAdapter = new DisptachAdapter(orderProductList.get(position).getDispatchList(), mContext, this);
        recyclerView.setAdapter(disptachAdapter);
    }

    @Override
    public int getItemCount() {
        return orderProductList.size();
    }

//    static class ViewHolder extends RecyclerView.ViewHolder {
//
//        TextView textViewProductOrdered;
//        RecyclerView recyclerViewDisptaches;
//        public ViewHolder(View itemView) {
//            super(itemView);
//            recyclerViewDisptaches = (RecyclerView) itemView.findViewById(R.id.recycler_view);
//            textViewProductOrdered = (TextView) itemView.findViewById(R.id.textView);
//        }
///*
//        void bind(final OrderProduct orderedProduct, Context context, final OnOrderedProductClickListener listener){
//            textViewProductOrdered.setText(orderedProduct.getProductOrderedTitle());
//
//            //DISPLAY ITEM DISPATCHES IN HORIZONTAL MODE
//            LinearLayoutManager layoutManager
//                    = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
//
//            recyclerViewDisptaches.setLayoutManager(layoutManager);
//
//            DisptachAdapter disptachAdapter = new DisptachAdapter(orderedProduct.getDispatchList(), context, (DisptachAdapter.OnDispatchClickListener) context);
//            recyclerViewDisptaches.setAdapter(disptachAdapter);
//
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    listener.onOrderedProductClickListener(orderedProduct, view);
//                }
//            });
//
//        }
//*/
//    }
    public void toggleSelection(int pos) {
        if (selectedItems.get(pos, false)) {
            selectedItems.delete(pos);
        }
        else {
            selectedItems.put(pos, true);
        }
        notifyItemChanged(pos);
    }

    public void clearSelections() {
        selectedItems.clear();
        notifyDataSetChanged();
    }

    public int getSelectedItemCount() {
        return selectedItems.size();
    }

    public List<Integer> getSelectedItems() {
        List<Integer> items =
                new ArrayList<Integer>(selectedItems.size());
        for (int i = 0; i < selectedItems.size(); i++) {
            items.add(selectedItems.keyAt(i));
        }
        return items;
    }
}
