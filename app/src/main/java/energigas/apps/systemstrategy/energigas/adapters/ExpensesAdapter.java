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



    // Store a member variable for the contacts
    private List<Expenses> mListExpenses;
    // Store the context for easy access
    private Activity mactivity;

    public interface OnExpensesClickListener{
        void onExpensesClickListener(Expenses expenses, View view);
    }

    public OnExpensesClickListener listener;


    private TextView tvtotal;
    private Activity activity;

    public ExpensesAdapter(List<Expenses> mListExpenses, Activity mactivity, OnExpensesClickListener listener,TextView  tvtotal) {
        this.mListExpenses = mListExpenses;
        this.mactivity = mactivity;
        this.listener = listener;
        this.tvtotal = tvtotal;
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





    /****************************VISTA DIALOGGG**************************************************/
    public void inflate_dialog() {

        final View layout_dialog_expenses = View.inflate(activity, R.layout.fragment_dialog_new_expense, null);
        final Button btnsave = (Button) layout_dialog_expenses.findViewById(R.id.btn_save);


        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                activity);

        // set title
        alertDialogBuilder.setTitle("New Expenses");

        // set dialog message
        final AlertDialog.Builder builder = alertDialogBuilder
                .setView(layout_dialog_expenses)
                .setCancelable(true);
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Expenses expenses=new Expenses("Comida","20/10/16","arroz con pollo",20.0,10.0);
                mListExpenses.add(expenses);


                double cantidadtotal=0.00;
                for(Expenses item: mListExpenses){
                    cantidadtotal=cantidadtotal+item.getmTotal();
                }
                tvtotal.setText(cantidadtotal+"");
                Log.d("inflate","cantidadtotat:"+cantidadtotal);


            }
        });
        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        // show it
        alertDialog.show();

    }
}
