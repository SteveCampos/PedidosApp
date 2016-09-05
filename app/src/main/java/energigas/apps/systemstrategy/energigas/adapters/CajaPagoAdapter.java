package energigas.apps.systemstrategy.energigas.adapters;

import android.content.Context;
import android.support.v4.content.ContextCompat;
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
    private Context mContext;

    public interface OnCajaPagoClickListener {
        void onCajaPagoClickListener(CajaPago cajaPago, View view);
    }

    public OnCajaPagoClickListener listener;


    public CajaPagoAdapter(List<CajaPago> mCajaPagoList,Context mContext,OnCajaPagoClickListener listener) {
        this.mCajaPagoList = mCajaPagoList;
        this.listener = listener;
        this.mContext = mContext;
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

        //holder.mcomprobante.setText("");
        //holder.mdate.setText("");
        //Cholder.mtotal.setText("S./ " + Utils.formatDouble(cajaPago.getImporte()));
/*

        int resto = position % 3;

        int color = R.color.dark_grey;
        String estado = "COBRADO";
        Double total = 0.00;
        switch (resto){
            case 0:
                total = 1500.00;
                estado = "POR COBRAR";
                color =R.color.md_red_500;
                break;
            case 1:
                total = 1200.00;
                estado = "COBRADO";
                color =R.color.md_green_500;
                break;
            case 2:
                total = 2800.00;
                estado = "COBRADO";
                color = R.color.md_green_500;
                break;

        }

        holder.mtotal.setText("S/. " + Utils.formatDouble(total));
        holder.mEstado.setText(estado);
        holder.mEstado.setTextColor(ContextCompat.getColor(mContext, color));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onCajaPagoClickListener(cajaPago, v);
            }
        });
        Log.d(Utils.TAG, "onBindViewHolder: " + position);
*/
    }

    @Override
    public int getItemCount() {
        Log.d(Utils.TAG, "getItemCount");
        return mCajaPagoList.size();
    }


}
