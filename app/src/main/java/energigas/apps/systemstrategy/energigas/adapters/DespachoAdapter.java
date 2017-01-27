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
import energigas.apps.systemstrategy.energigas.entities.Estado;
import energigas.apps.systemstrategy.energigas.entities.Unidad;
import energigas.apps.systemstrategy.energigas.holders.DespachoHolder;
import energigas.apps.systemstrategy.energigas.utils.Constants;
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
        Unidad unidad = Unidad.getUnidadProductobyUnidadMedidaId(despacho.getUnId() + "");
        Almacen almacen = Almacen.getAlmacenById(despacho.getAlmacenEstId() + "");
        Estado estado = Estado.getEstado(despacho.getEstadoId() + "");
        holder.title.setText(despacho.getSerie() + "-" + despacho.getNumero());
        holder.quantity.setText(Utils.formatDoublePrint(despacho.getCantidadDespachada()) + " " + unidad.getAbreviatura());
        holder.information.setText(
                "Tanque: " + almacen.getNombre() + "\n" +
                        "C. Inicial: " + Utils.formatDoublePrint(despacho.getContadorInicialDestino()) + " - " + " C. Final: " + Utils.formatDoublePrint(despacho.getContadorFinalDestino()) + "\n" +
                        "P. Inicial: " + despacho.getpITDestino() + " - " + "P. Final: " + despacho.getpFTDestino() + "\n" +
                        "Hora de despacho: " + despacho.getHoraFin() + "\n"
        );
        holder.state.setText(estado.getDescripcion());

        if (despacho.getEstadoId() == Constants.ESTADO_DESPACHO_CREADO) {

        }
        if (despacho.getEstadoId() == Constants.ESTADO_ELIMINAR_DESPACHO) {

        }
        if (despacho.getEstadoId() == Constants.ESTADO_DESPACHO_FACTURADO) {

        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "CLICKED: " + view);
                if (despacho.getEstadoId() == Constants.ESTADO_DESPACHO_CREADO || despacho.getEstadoId() == Constants.ESTADO_DESPACHO_FACTURADO) {
                    listener.onDespachoClickListener(despacho, view);
                }

            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                if (despacho.getEstadoId() == Constants.ESTADO_DESPACHO_CREADO) {
                    listener.onLongDespachoClickListener(despacho, v);
                }


                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface OnDespachoClickListener {
        void onDespachoClickListener(Despacho despacho, View view);

        void onLongDespachoClickListener(Despacho despacho, View view);
    }
}
