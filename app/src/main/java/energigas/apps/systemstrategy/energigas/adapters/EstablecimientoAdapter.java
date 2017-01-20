package energigas.apps.systemstrategy.energigas.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.entities.CajaLiquidacionDetalle;
import energigas.apps.systemstrategy.energigas.entities.Cliente;
import energigas.apps.systemstrategy.energigas.entities.Establecimiento;
import energigas.apps.systemstrategy.energigas.entities.Estado;
import energigas.apps.systemstrategy.energigas.entities.GeoUbicacion;
import energigas.apps.systemstrategy.energigas.entities.Pedido;
import energigas.apps.systemstrategy.energigas.holders.EstablecimientoHolder;
import energigas.apps.systemstrategy.energigas.utils.Constants;
import energigas.apps.systemstrategy.energigas.utils.Utils;

/**
 * Created by Steve on 19/07/2016.
 */

public class EstablecimientoAdapter extends RecyclerView.Adapter<EstablecimientoHolder> {


    private static final String TAG = EstablecimientoAdapter.class.getSimpleName();
    // Store a member variable for the contacts
    private List<Establecimiento> mListEstablecimientos;
    // Store the context for easy access
    private Context mContext;

    public interface OnEstablecimientoClickListener {
        void onEstablecimientoClickListener(Establecimiento establecimiento, View view);
    }

    public OnEstablecimientoClickListener listener;

    public EstablecimientoAdapter(List<Establecimiento> mListEstablecimientos, Context mContext, OnEstablecimientoClickListener listener) {
        this.mListEstablecimientos = mListEstablecimientos;
        this.mContext = mContext;
        this.listener = listener;
    }

    @Override
    public EstablecimientoHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Log.d(Utils.TAG, "onCreateViewHolder");
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_establecimiento, parent, false);
        // Return a new holder instance
        return new EstablecimientoHolder(contactView);
    }

    @Override
    public void onBindViewHolder(EstablecimientoHolder holder, int position) {
        // Get the data model based on position
        //

        Log.d(Utils.TAG, "onBindViewHolder: " + position);
        //  holder.bind(mListEstablecimientos.get(position), mContext, listener);
        final Establecimiento establecimiento = mListEstablecimientos.get(position);
        CajaLiquidacionDetalle cajaLiquidacionDetalle = CajaLiquidacionDetalle.getLiquidacionDetalleByEstablecAndEstado(establecimiento.getEstIEstablecimientoId() + "");

        Estado estado = Estado.getEstado(cajaLiquidacionDetalle.getEstadoId() + "");
        Cliente cliente = Cliente.getCliente(establecimiento.getEstIClienteId() + "");
        Pedido pedido = Pedido.getPedido(establecimiento.getEstIEstablecimientoId() + "");
        holder.textAtendido.setText(estado.getDescripcion());
        holder.mname.setText(establecimiento.getEstVDescripcion().toUpperCase());
        holder.button.setText(cajaLiquidacionDetalle.getOrden() + "");
        GradientDrawable bgShape = (GradientDrawable) holder.button.getBackground();
        String color = "";
        switch (cajaLiquidacionDetalle.getEstadoId()) {
            case Constants.ESTADO_ESTABLECIMIENTO_ATENDIDO:
                color = "#5882FA";
                break;
            case Constants.ESTADO_ESTABLECIMIENTO_NO_ATENDIDO:
                color = "#F7819F";
                break;
            case Constants.ESTADO_ESTABLECIMIENTO_PENDIENTE:
                color = "#585858";
                break;
        }

        bgShape.setColor(Color.parseColor(color));

        GeoUbicacion geoUbicacion = establecimiento.getUbicacion();
        Log.d(TAG, "GeoUbicacion: " + geoUbicacion);
        //VALIDAR QUE LOS OBJETOS ANIDADOS, NO SEAN NULOS.
        if (geoUbicacion != null && pedido != null) {
            holder.mubicacion.setText("Hora Entrega: " + pedido.getHoraProgramada());
            holder.maddress.setText(Utils.capitalize(geoUbicacion.getDescripcion()));

        }

        holder.imageView2.setImageDrawable(mContext.getResources().getDrawable(getImage(cliente.getCliITipoClienteId())));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onEstablecimientoClickListener(establecimiento, view);
            }
        });

    }

    int getImage(int tipoestacion) {
        if (Constants.ESTABLECIMIENTO_EXTERNO == tipoestacion) {
            return R.drawable.gas_station_b;
        } else {
            return R.drawable.logo;
        }
    }

    @Override
    public int getItemCount() {
        Log.d(Utils.TAG, "getItemCount");
        return mListEstablecimientos.size();
    }

}
