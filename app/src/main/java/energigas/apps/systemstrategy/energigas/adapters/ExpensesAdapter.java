package energigas.apps.systemstrategy.energigas.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.entities.Expenses;
import energigas.apps.systemstrategy.energigas.utils.Utils;

/**
 * Created by kikerojas on 30/07/2016.
 */

public class ExpensesAdapter extends RecyclerView.Adapter<ExpensesAdapter.ViewHolder> {



    private OnAddnewExpenses listenerAdd;
    // Store a member variable for the contacts
    private List<Expenses> mListExpenses;
    // Store the context for easy access
    private Activity mactivity;

    public interface OnExpensesClickListener{
        void onExpensesClickListener(Expenses expenses, View view);
    }

    public OnExpensesClickListener listener;


    public ExpensesAdapter(List<Expenses> mListExpenses, Activity mactivity, OnExpensesClickListener listener,OnAddnewExpenses listenerAdd) {
        this.mListExpenses = mListExpenses;
        this.mactivity = mactivity;
        this.listener = listener;
        this.listenerAdd = listenerAdd;
    }

    public void addnewExpenses(Expenses expenses){
        mListExpenses.add(expenses);
        int position = getItemCount();
        notifyItemInserted(--position);//3 o 2 position
        listenerAdd.onAddnewExpenses("10/20/11", getTotal());
    }


    public Double getTotal(){
        Double total = 0.0;
        for (int i=0; i < mListExpenses.size(); i++){
            total += mListExpenses.get(i).getmTotal();
        }
        return total;
    }

    @Override
    public ExpensesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Log.d(Utils.TAG, "onCreateViewHolder");
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_expenses, parent, false);
        // Return a new holder instance
        return new ExpensesAdapter.ViewHolder(contactView);
    }

    @Override
    public void onBindViewHolder(ExpensesAdapter.ViewHolder holder, int position) {
        // Get the data model based on position
        //

        Log.d(Utils.TAG, "onBindViewHolder: "+ position);
        holder.bind(mListExpenses.get(position), mactivity, listener);
    }

    @Override
    public int getItemCount() {
        Log.d(Utils.TAG, "getItemCount");
        return mListExpenses.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        TextView mdescription;
        TextView mdocument;
        TextView mtotal;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            mdescription = (TextView) itemView.findViewById(R.id.tv_description);
            mdocument = (TextView) itemView.findViewById(R.id.tv_category);
            mtotal = (TextView) itemView.findViewById(R.id.tv_total);


        }

        void bind(final Expenses expenses, Context context, final OnExpensesClickListener listener) {
            mdocument.setText(expenses.getmType());
            mdescription.setText(expenses.getmDescription());
            mtotal.setText("S./ " + Utils.formatDouble(expenses.getmTotal()));

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onExpensesClickListener(expenses, v);
                }
            });
        }
    }

    public interface OnAddnewExpenses{
        void onAddnewExpenses (String date,Double total);
    }


}
