package energigas.apps.systemstrategy.energigas.adapters;
import android.content.Context;
import android.media.Image;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.entities.Charges;
import energigas.apps.systemstrategy.energigas.utils.Utils;

/**
 * Created by Kike on 1/08/2016.
 */

public class ChargesAdapter extends RecyclerView.Adapter<ChargesAdapter.ViewHolder> {


    // Store a member variable for the contacts
    private List<Charges> mChargesList;
    // Store the context for easy access
    private Context context;

    public interface OnChargesClickListener{
        void onChargesClickListener(Charges charges, View view);
    }

    public OnChargesClickListener listener;


    public ChargesAdapter(List<Charges> mChargesList, Context context, OnChargesClickListener listener) {
        this.mChargesList = mChargesList;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public ChargesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Log.d(Utils.TAG, "onCreateViewHolder");
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_charges, parent, false);
        // Return a new holder instance
        return new ChargesAdapter.ViewHolder(contactView);
    }

    @Override
    public void onBindViewHolder(ChargesAdapter.ViewHolder holder, int position) {
        // Get the data model based on position
        //

        Log.d(Utils.TAG, "onBindViewHolder: "+ position);
        holder.bind(mChargesList.get(position), context, listener);
    }

    @Override
    public int getItemCount() {
        Log.d(Utils.TAG, "getItemCount");
        return mChargesList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        TextView mcomprobante;
        TextView mserie;
        TextView mdate;
        TextView mtotal;


        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);


            mcomprobante = (TextView) itemView.findViewById(R.id.txt_compserie);
           // mserie = (TextView) itemView.findViewById(R.id.txt_serie);
            mdate = (TextView) itemView.findViewById(R.id.txt_date);
            mtotal = (TextView) itemView.findViewById(R.id.txt_total);



        }

        void bind(final Charges charges, Context context, final ChargesAdapter.OnChargesClickListener listener) {
            mcomprobante.setText(charges.getmComprobante()+"-" +charges.getmSerie());
          //  mserie.setText(charges.getmSerie());
            mdate.setText(charges.getmDate());
            mtotal.setText("S./ " + Utils.formatDouble(charges.getmTotal()));

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onChargesClickListener(charges, v);
                }
            });
        }
    }
}
