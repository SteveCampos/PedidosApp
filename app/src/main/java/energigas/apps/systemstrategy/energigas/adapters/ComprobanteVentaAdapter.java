package energigas.apps.systemstrategy.energigas.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.entities.Comprobante;
import energigas.apps.systemstrategy.energigas.entities.ComprobanteVenta;
import energigas.apps.systemstrategy.energigas.holders.ComprobanteVentaHolder;

/**
 * Created by Steve on 12/08/2016.
 */
public class ComprobanteVentaAdapter extends RecyclerView.Adapter<ComprobanteVentaHolder>{

    private static final String TAG = ComprobanteVenta.class.getSimpleName();
    private List<ComprobanteVenta> mList;
    private Context mContext;

    public ComprobanteVentaAdapter(List<ComprobanteVenta> mList, Context mContext) {
        this.mList = mList;
        this.mContext = mContext;
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

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "CLICKED: " + view);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
