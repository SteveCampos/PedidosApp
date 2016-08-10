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
import energigas.apps.systemstrategy.energigas.holders.ChargesHolder;
import energigas.apps.systemstrategy.energigas.utils.Utils;

/**
 * Created by Kike on 1/08/2016.
 */

public class ChargesAdapter extends RecyclerView.Adapter<ChargesHolder> {


    // Store a member variable for the contacts
    private List<Charges> mChargesList;
    // Store the context for easy access

    public interface OnChargesClickListener {
        void onChargesClickListener(Charges charges, View view);
    }

    public OnChargesClickListener listener;


    public ChargesAdapter(List<Charges> mChargesList, OnChargesClickListener listener) {
        this.mChargesList = mChargesList;
        this.listener = listener;
    }

    @Override
    public ChargesHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Log.d(Utils.TAG, "onCreateViewHolder");
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_charges, parent, false);
        // Return a new holder instance
        return new ChargesHolder(contactView);
    }

    @Override
    public void onBindViewHolder(ChargesHolder holder, int position) {
        // Get the data model based on position

        final Charges charges = mChargesList.get(position);

        holder.mcomprobante.setText(charges.getmComprobante() + "-" + charges.getmSerie());
        //  mserie.setText(charges.getmSerie());
        holder.mdate.setText(charges.getmDate());
        holder.mtotal.setText("S./ " + Utils.formatDouble(charges.getmTotal()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onChargesClickListener(charges, v);
            }
        });
        Log.d(Utils.TAG, "onBindViewHolder: " + position);

    }

    @Override
    public int getItemCount() {
        Log.d(Utils.TAG, "getItemCount");
        return mChargesList.size();
    }


}
