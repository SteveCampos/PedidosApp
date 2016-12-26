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
import energigas.apps.systemstrategy.energigas.entities.Almacen;
import energigas.apps.systemstrategy.energigas.entities.Servidores;
import energigas.apps.systemstrategy.energigas.holders.AlmacenHolder;
import energigas.apps.systemstrategy.energigas.holders.ServidoresHolder;

/**
 * Created by Steve on 10/08/2016.
 */

public class ServidoresAdapter extends RecyclerView.Adapter<ServidoresHolder> {

    private static final String TAG = ServidoresAdapter.class.getSimpleName();
    // Store a member variable for the list;
    private List<Servidores> servidoresList;
    // Store the context for easy access
    private Context mContext;

    private OnServidoresClickListener listener;

    public ServidoresAdapter(List<Servidores> servidoresList, Context mContext, OnServidoresClickListener listener) {
        this.servidoresList = servidoresList;
        this.mContext = mContext;
        this.listener = listener;
    }

    @Override
    public ServidoresHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        // Inflate the custom layout
        View view = inflater.inflate(R.layout.item_servidores, parent, false);
        // Return a new holder instance
        return new ServidoresHolder(view);
    }

    @Override
    public void onBindViewHolder(ServidoresHolder holder, int position) {

        final Servidores servidores = servidoresList.get(position);

        holder.title.setText(servidores.getName());
        holder.descripcion.setText(servidores.getDescription());

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                listener.onServidoresClickListener(servidores, view, 1);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return servidoresList.size();
    }

    public interface OnServidoresClickListener {
        void onServidoresClickListener(Servidores servidores, View view, int typeClick);
    }
}
