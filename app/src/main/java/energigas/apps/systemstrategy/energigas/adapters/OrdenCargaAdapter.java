package energigas.apps.systemstrategy.energigas.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.entities.OrdenCargo;
import energigas.apps.systemstrategy.energigas.holders.OrdenCargoHolder;
import energigas.apps.systemstrategy.energigas.interfaces.OrdenCargoListener;

/**
 * Created by Steve on 28/12/2016.
 */

public class OrdenCargaAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context mContext;
    private List<OrdenCargo> list = new ArrayList<>();
    private OrdenCargoListener listener;

    public OrdenCargaAdapter(Context mContext, List<OrdenCargo> list, OrdenCargoListener listener) {
        this.mContext = mContext;
        this.list = list;
        this.listener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        RecyclerView.ViewHolder viewHolder = null;
        View view = inflater.inflate(R.layout.item_orden_cargo, parent, false);
        viewHolder = new OrdenCargoHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final OrdenCargo ordenCargo = list.get(position);
        OrdenCargoHolder ordenCargoHolder = (OrdenCargoHolder) holder;

        ordenCargoHolder.bind(ordenCargo, mContext, listener);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onOrdenCargoClickListener(ordenCargo);
            }
        });
    }


    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

}
