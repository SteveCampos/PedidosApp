package energigas.apps.systemstrategy.energigas.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.entities.Establecimiento;
import energigas.apps.systemstrategy.energigas.entities.GeoUbicacion;
import energigas.apps.systemstrategy.energigas.holders.EstablecimientoHolder;
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

        Log.d(Utils.TAG, "onBindViewHolder: "+ position);
      //  holder.bind(mListEstablecimientos.get(position), mContext, listener);
        final Establecimiento establecimiento = mListEstablecimientos.get(position);

        holder.mname.setText(Utils.capitalize(establecimiento.getEstVDescripcion()));
        GeoUbicacion geoUbicacion = establecimiento.getUbicacion();
        Log.d(TAG, "GeoUbicacion: "+ geoUbicacion);
        //VALIDAR QUE LOS OBJETOS ANIDADOS, NO SEAN NULOS.
        if (geoUbicacion!=null){

            holder.maddress.setText(geoUbicacion.getDescripcion());
        }

        //holder.mpoint.setText(Utils.capitalize(""+establecimiento.getEstIClienteId()));
        //holder.mubicacion.setText(Utils.capitalize(establecimiento.getEstVTelefono()));
        //holder.imageView2.setImageDrawable(ContextCompat.getDrawable(mContext, getImage(establecimiento.getEstVCodigo())));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onEstablecimientoClickListener(establecimiento, view);
            }
        });

    }

    int getImage(String tipoestacion) {
        switch (tipoestacion) {
            case "externo":
                return R.drawable.ic_gas_station_c;
            case "interno":
                return R.drawable.logo;
            default:
                return R.drawable.logo;
        }
    }

    @Override
    public int getItemCount() {
        Log.d(Utils.TAG, "getItemCount");
        return mListEstablecimientos.size();
    }

}
