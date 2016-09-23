package energigas.apps.systemstrategy.energigas.adapters;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;
import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.activities.CajaGastoActivity;
import energigas.apps.systemstrategy.energigas.entities.CajaGasto;
import energigas.apps.systemstrategy.energigas.holders.CajaGastoHolder;
import energigas.apps.systemstrategy.energigas.utils.Constants;
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
        void onCajaGastoClickListener(int action, CajaGasto CajaGasto, View view);
    }

    public OnCajaGastoClickListener listener;


    public CajaGastoAdapter(List<CajaGasto> mListCajaGasto, Activity mactivity, OnCajaGastoClickListener listener, OnAddnewCajaGasto listenerAdd) {
        this.mListCajaGasto = mListCajaGasto;
        this.mactivity = mactivity;
        this.listener = listener;
        this.listenerAdd = listenerAdd;
        notifyDataSetChanged();
    }

    public void addnewCajaGasto(CajaGasto cajaGasto){
        mListCajaGasto.add(cajaGasto);
        int position = getItemCount();
        notifyItemInserted(--position);//3 o 2 position
        listenerAdd.onAddnewCajaGasto("10/20/11", getTotal());
    }
    public void remove(CajaGasto cajaGasto, int position){
        mListCajaGasto.remove(cajaGasto);
        notifyItemRemoved(position);
    }


    public Double getTotal(){
        Double total = 0.0;
        for (int i=0; i < mListCajaGasto.size(); i++){
            total += mListCajaGasto.get(i).getImporte();
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
    public void onBindViewHolder(final CajaGastoHolder holder, final int position) {
        // Get the data model based on position

        final CajaGasto cajaGasto = mListCajaGasto.get(position);
        Log.d(Utils.TAG, "onBindViewHolder: "+ position);

        holder.mdocument.setText(cajaGasto.getTipoGastoId()+"");
        holder.mdescription.setText(cajaGasto.getFechaCreacion());
        holder.mtotal.setText("S./ " + Utils.formatDouble(cajaGasto.getImporte()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {


              //  CharSequence items[] = new CharSequence[] {"Editar", "Eliminar"};
                final CharSequence[] items = {"Editar", "Eliminar"};

                final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        mactivity);
                alertDialogBuilder.setTitle("Selecione Opción");
                alertDialogBuilder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // the user clicked on colors[which]


                        if (items[which].equals("Eliminar")) {
                            alertDialogBuilder.setTitle("Eliminar");

                            // set dialog message
                            alertDialogBuilder
                                    .setMessage("Desea eliminar!")
                                    .setCancelable(false)
                                    .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog,int id) {
                                            // if this button is clicked, close
                                            // current activity
                                            //CajaGastoActivity.this.finish();
                                            /*
                                            CajaGasto cajaGasto1=CajaGasto.findById(CajaGasto.class,cajaGasto.getId());
                                            cajaGasto1.delete();
                                            Log.d("TESTING","GetIDCajaGasto: "+cajaGasto1);
                                            mListCajaGasto.remove(position);
                                            notifyItemRemoved(position);*/
                                            listener.onCajaGastoClickListener(Constants.CLICK_ELIMINAR, cajaGasto, holder.itemView);

                                        }
                                    })
                                    .setNegativeButton("No",new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog,int id) {
                                            // if this button is clicked, just close
                                            // the dialog box and do nothing
                                            dialog.cancel();
                                        }
                                    });


                            // create alert dialog
                            AlertDialog alertDialog = alertDialogBuilder.create();

                            // show it
                            alertDialog.show();

                        } else if (items[which].equals("Editar")) {
                            //getProfilePicFromGallery();

                            /*
                            CajaGasto cajaGasto1=CajaGasto.findById(CajaGasto.class,cajaGasto.getId());
                            cajaGasto1.save();
                            Log.d("EDITAR","idgetId"+cajaGasto1);*/
                            listener.onCajaGastoClickListener(Constants.CLICK_EDITAR, cajaGasto, holder.itemView);

                        } else if (items[which].equals("Cancel")) {
                            dialog.dismiss();
                        }

                    }
                });
                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialogBuilder.show();
                return false;
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
