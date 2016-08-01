package energigas.apps.systemstrategy.energigas.adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.entities.Inventory;

/**
 * Created by kelvi on 20/07/2016.
 */

public class LoadInventoryAdapter extends RecyclerView.Adapter<LoadInventoryAdapter.ViewHolder> {
    List<Inventory> inventories;
    Context context;

    public LoadInventoryAdapter(List<Inventory> inventories, Context context){
        this.inventories=inventories;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        // Inflate the custom layout
        View contactView = null;
        if (viewType==0){
            contactView = inflater.inflate(R.layout.layout_not_found_inventory, parent, false);

        }else{
            contactView = inflater.inflate(R.layout.layout_item_item_inventory, parent, false);
        }

        // Return a new holder instance
        return new LoadInventoryAdapter.ViewHolder(contactView);
    }

    @Override
    public int getItemViewType(int position) {
        if (inventories.size() ==0){
            return 0;
        }else{
            return inventories.size();
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(inventories.get(position));
    }

    @Override
    public int getItemCount() {

        if (inventories.size() ==0){
            return 1;
        }else{
            return inventories.size();
        }


    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        TextView nameTextView;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
           super(itemView);
           nameTextView = (TextView) itemView.findViewById(R.id.textNameProduct);
        }
        void bind(Inventory inventory){

           nameTextView.setText(inventory.getNombre());
        }
    }
}
