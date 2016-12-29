package energigas.apps.systemstrategy.energigas.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.entities.Almacen;
import energigas.apps.systemstrategy.energigas.entities.Despacho;
import energigas.apps.systemstrategy.energigas.holders.DespachoHolder;
import energigas.apps.systemstrategy.energigas.utils.Utils;

/**
 * Created by Steve on 11/08/2016.
 */

public class DespachoAdapter extends RecyclerView.Adapter<DespachoHolder> {

    private static final String TAG = DespachoAdapter.class.getSimpleName();
    private List<Despacho> list;
    private Context mContext;
    private OnDespachoClickListener listener;

    public DespachoAdapter(List<Despacho> list, Context mContext, OnDespachoClickListener listener) {
        this.list = list;
        this.mContext = mContext;
        this.listener = listener;
    }

    @Override
    public DespachoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_despacho, parent, false);
        return new DespachoHolder(view);
    }

    @Override
    public void onBindViewHolder(DespachoHolder holder, int position) {

        final Despacho despacho = list.get(position);
        Almacen almacen = Almacen.getAlmacenById(despacho.getAlmacenEstId() + "");
        holder.title.setText(despacho.getSerie() + "-" + despacho.getNumero());
        holder.quantity.setText(Utils.formatDoublePrint(despacho.getCantidadDespachada()) + "");
        holder.information.setText(
                "Tanque: " + almacen.getNombre() + "\n" +
                        "C. Inicial: " + Utils.formatDoublePrint(despacho.getContadorInicialOrigen()) + " - " + " C. Final: " + Utils.formatDoublePrint(despacho.getContadorFinalOrigen()) + "\n" +
                        "P. Inicial: " + despacho.getpITOrigen() + " - " + "P. Final: " + despacho.getpFTOrigen() + "\n" +
                        "Hora de despacho: " + despacho.getHoraFin() + "\n"
        );
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "CLICKED: " + view);
                listener.onDespachoClickListener(despacho, view);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface OnDespachoClickListener {
        void onDespachoClickListener(Despacho despacho, View view);
    }
}
