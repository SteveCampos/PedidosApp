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
import energigas.apps.systemstrategy.energigas.holders.AlmacenHolder;

/**
 * Created by Steve on 10/08/2016.
 */

public class AlmacenAdapter extends RecyclerView.Adapter<AlmacenHolder>{

    private static final String TAG = AlmacenAdapter.class.getSimpleName();
    // Store a member variable for the list;
    private List<Almacen> almacenList;
    // Store the context for easy access
    private Context mContext;

    private OnAlmacenClickListener listener;

    public  AlmacenAdapter(List<Almacen> almacenList, Context mContext, OnAlmacenClickListener listener) {
        this.almacenList = almacenList;
        this.mContext = mContext;
        this.listener = listener;
    }

    @Override
    public AlmacenHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        // Inflate the custom layout
        View view = inflater.inflate(R.layout.item_almacen, parent, false);
        // Return a new holder instance
        return new AlmacenHolder(view);
    }

    @Override
    public void onBindViewHolder(AlmacenHolder holder, int position) {
        final Almacen almacen = almacenList.get(position);
        holder.title.setText("Tanque " +  (++position));

        holder.capacity.setText(almacen.getStockMinimo()+" - "+almacen.getCapacidadNeta());
        holder.politica.setText(almacen.getPolitica()+" - "+almacen.getCapacidadReal());
        holder.capacidadNeta.setText(almacen.getCapacidadNeta()+"");
        holder.programado.setText(almacen.getNombre());

        int color = R.color.dark_grey;
       /* int resto = position % 3;


        String estado = "EN PROCESO";
        String programado = "";

        switch (resto){
            case 0:
                color = R.color.dark_grey;
                estado = "NO PROGRAMADO";
                break;
            case 2:
                programado = "500.00 GAL";
                color = R.color.md_yellow_500;
                estado = "EN PROCESO";
                break;
            case 1:
                programado = "300.00 GAL";
                color = R.color.md_green_500;
                estado = "DESPACHADO";
                break;

        }
        */

       // holder.programado.setText(programado);
        holder.estado.setText("");
        holder.estado.setTextColor(ContextCompat.getColor(mContext, color));
        holder.capacidadNeta.setText(almacen.getCapacidadNeta()+" GAL");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "CLICKED: " + view);
                listener.onAlmacenClickListener(almacen, view, 0);
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                listener.onAlmacenClickListener(almacen, view, 1);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return almacenList.size();
    }

    public interface OnAlmacenClickListener{
        void onAlmacenClickListener(Almacen almacen, View view, int typeClick);
    }
}
