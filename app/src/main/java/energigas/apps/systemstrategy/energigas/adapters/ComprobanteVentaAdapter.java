package energigas.apps.systemstrategy.energigas.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.entities.ComprobanteVenta;
import energigas.apps.systemstrategy.energigas.holders.ComprobanteVentaHolder;
import energigas.apps.systemstrategy.energigas.interfaces.OnComprobanteVentaClickListener;

/**
 * Created by Steve on 12/08/2016.
 */
public class ComprobanteVentaAdapter extends RecyclerView.Adapter<ComprobanteVentaHolder>{

    private static final String TAG = ComprobanteVenta.class.getSimpleName();
    private List<ComprobanteVenta> mList;
    private Context mContext;
    private OnComprobanteVentaClickListener listener;

    public ComprobanteVentaAdapter(List<ComprobanteVenta> mList, Context mContext, OnComprobanteVentaClickListener listener) {
        this.mList = mList;
        this.mContext = mContext;
        this.listener = listener;
    }

    @Override
    public ComprobanteVentaHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_comprobante_venta, parent, false );
        return new ComprobanteVentaHolder(view);
    }

    @Override
    public void onBindViewHolder(ComprobanteVentaHolder holder, int position) {
        final ComprobanteVenta comprobanteVenta = mList.get(position);


        holder.txt_factura.setText("F0001");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "CLICKED: " + view);
                listener.onComprobanteVentaClickListener(comprobanteVenta, view);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
       // return mList == null ? 0: mList.size();
        //if(mlist == null){
        // return 0}ekse {
        // return mlist.size}
    }
}

