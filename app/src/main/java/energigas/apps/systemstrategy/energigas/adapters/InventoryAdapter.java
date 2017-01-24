package energigas.apps.systemstrategy.energigas.adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.entities.Inventory;
import energigas.apps.systemstrategy.energigas.holders.InventoryHolder;
import energigas.apps.systemstrategy.energigas.utils.Utils;

/**
 * Created by kelvi on 20/07/2016.
 */

public class InventoryAdapter extends RecyclerView.Adapter<InventoryHolder> {
    List<Inventory> inventories;
    Context context;

    public InventoryAdapter(List<Inventory> inventories, Context context) {

        this.inventories = inventories;
        this.context = context;
    }

    @Override
    public InventoryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.layout_item_item_inventory, parent, false);
        // Return a new holder instance
        return new InventoryHolder(contactView);
    }

    @Override
    public void onBindViewHolder(InventoryHolder holder, int position) {
        final Inventory inventory = inventories.get(position);
        //holder.bind(inventories.get(position));
        holder.textNameProduct.setText(inventory.getNombre());
        holder.inicio.setText(Utils.formatDoublePrint(inventory.getCantidadInicial()) + "");
        holder.carga.setText(Utils.formatDoublePrint(inventory.getCantidadOrden()) + "");
        holder.despacho.setText(Utils.formatDoublePrint(inventory.getCantidadDespachada()) + "");
        holder.inFinal.setText(Utils.formatDoublePrint(inventory.getCantidadFinal()) + "");
    }

    @Override
    public int getItemCount() {
        return inventories.size();
    }

//    static class ViewHolder extends RecyclerView.ViewHolder {
//        // Your holder should contain a member variable
//        // for any view that will be set as you render a row
//        TextView nameTextView;
//
//        // We also create a constructor that accepts the entire item row
//        // and does the view lookups to find each subview
//        ViewHolder(View itemView) {
//            // Stores the itemView in a public final member variable that can be used
//            // to access the context from any ViewHolder instance.
//            super(itemView);
//            nameTextView = (TextView) itemView.findViewById(R.id.textNameProduct);
//        }
//        void bind(Inventory inventory){
//            nameTextView.setText(inventory.getNombre());
//        }
//    }
}
