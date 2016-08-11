package energigas.apps.systemstrategy.energigas.adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.entities.CajaGasto;
import energigas.apps.systemstrategy.energigas.entities.Expenses;
import energigas.apps.systemstrategy.energigas.holders.CajaGastoHolder;
import energigas.apps.systemstrategy.energigas.utils.Utils;

/**
 * Created by kikerojas on 30/07/2016.
 */

public class CajaGastoAdapter extends RecyclerView.Adapter<CajaGastoHolder> {



    private OnAddnewCajaGasto listenerAdd;
    // Store a member variable for the contacts
    private List<CajaGasto> mListCajaGasto;
    // Store the context for easy access
    private Activity mactivity;

    public interface OnCajaGastoClickListener{
        void onCajaGastoClickListener(CajaGasto CajaGasto, View view);
    }

    public OnCajaGastoClickListener listener;


    public CajaGastoAdapter(List<CajaGasto> mListCajaGasto, Activity mactivity, OnCajaGastoClickListener listener, OnAddnewCajaGasto listenerAdd) {
        this.mListCajaGasto = mListCajaGasto;
        this.mactivity = mactivity;
        this.listener = listener;
        this.listenerAdd = listenerAdd;
    }

    public void addnewCajaGasto(CajaGasto cajaGasto){
        mListCajaGasto.add(cajaGasto);
        int position = getItemCount();
        notifyItemInserted(--position);//3 o 2 position
        listenerAdd.onAddnewCajaGasto("10/20/11", getTotal());
    }


    public Double getTotal(){
        Double total = 0.0;
        for (int i=0; i < mListCajaGasto.size(); i++){
            total += mListCajaGasto.get(i).getValorVenta();
        }
        return total;
    }

    @Override
    public CajaGastoHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Log.d(Utils.TAG, "onCreateViewHolder");
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_expenses, parent, false);
        // Return a new holder instance
        return new CajaGastoHolder(contactView);
    }

    @Override
    public void onBindViewHolder(CajaGastoHolder holder, int position) {
        // Get the data model based on position

        final CajaGasto cajaGasto = mListCajaGasto.get(position);
        Log.d(Utils.TAG, "onBindViewHolder: "+ position);

        holder.mdocument.setText(cajaGasto.getFechaActualizacion());
        holder.mdescription.setText(cajaGasto.getFechaCreacion());
        holder.mtotal.setText("S./ " + Utils.formatDouble(cajaGasto.getImporte()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onCajaGastoClickListener(cajaGasto, v);
            }
        });

    }

    @Override
    public int getItemCount() {
        Log.d(Utils.TAG, "getItemCount");
        return mListCajaGasto.size();
    }


//    static class ViewHolder extends RecyclerView.ViewHolder {
//        // Your holder should contain a member variable
//        // for any view that will be set as you render a row
//        TextView mdescription;
//        TextView mdocument;
//        TextView mtotal;
//
//        // We also create a constructor that accepts the entire item row
//        // and does the view lookups to find each subview
//        ViewHolder(View itemView) {
//            // Stores the itemView in a public final member variable that can be used
//            // to access the context from any ViewHolder instance.
//            super(itemView);
//
//            mdescription = (TextView) itemView.findViewById(R.id.tv_description);
//            mdocument = (TextView) itemView.findViewById(R.id.tv_category);
//            mtotal = (TextView) itemView.findViewById(R.id.tv_total);
//
//
//        }
//
//        void bind(final Expenses expenses, Context context, final OnExpensesClickListener listener) {
//            mdocument.setText(expenses.getmType());
//            mdescription.setText(expenses.getmDescription());
//            mtotal.setText("S./ " + Utils.formatDouble(expenses.getmTotal()));
//
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    listener.onExpensesClickListener(expenses, v);
//                }
//            });
//        }
//    }

    public interface OnAddnewCajaGasto{
        void onAddnewCajaGasto (String date,Double total);
    }


}
