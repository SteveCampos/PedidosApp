package energigas.apps.systemstrategy.energigas.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.entities.CajaPago;
import energigas.apps.systemstrategy.energigas.holders.CajaPagoHolder;
import energigas.apps.systemstrategy.energigas.utils.Utils;

/**
 * Created by Kike on 1/08/2016.
 */

public class CajaPagoAdapter extends RecyclerView.Adapter<CajaPagoHolder> {


    // Store a member variable for the contacts
    private List<CajaPago> mCajaPagoList;
    // Store the context for easy access

    public interface OnCajaPagoClickListener {
        void onCajaPagoClickListener(CajaPago cajaPago, View view);
    }

    public OnCajaPagoClickListener listener;


    public CajaPagoAdapter(List<CajaPago> mCajaPagoList, OnCajaPagoClickListener listener) {
        this.mCajaPagoList = mCajaPagoList;
        this.listener = listener;
    }

    @Override
    public CajaPagoHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Log.d(Utils.TAG, "onCreateViewHolder");
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_charges, parent, false);
        // Return a new holder instance
        return new CajaPagoHolder(contactView);
    }

    @Override
    public void onBindViewHolder(CajaPagoHolder holder, int position) {
        // Get the data model based on position

        final CajaPago cajaPago = mCajaPagoList.get(position);

        holder.mcomprobante.setText(cajaPago.getFechaAccion() + "-" + cajaPago.getFechaAccion());
        //  mserie.setText(charges.getmSerie());
        holder.mdate.setText(cajaPago.getFechaAccion());
        holder.mtotal.setText("S./ " + Utils.formatDouble(cajaPago.getImporte()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onCajaPagoClickListener(cajaPago, v);
            }
        });
        Log.d(Utils.TAG, "onBindViewHolder: " + position);

    }

    @Override
    public int getItemCount() {
        Log.d(Utils.TAG, "getItemCount");
        return mCajaPagoList.size();
    }


}
