package energigas.apps.systemstrategy.energigas.adapters;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

import android.widget.Button;

import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.entities.OrdenCargo;
import energigas.apps.systemstrategy.energigas.holders.OrdenCargoHolder;
import energigas.apps.systemstrategy.energigas.interfaces.OrdenCargoListener;
import energigas.apps.systemstrategy.energigas.utils.Constants;

/**
 * Created by Steve on 28/12/2016.
 */

public class OrdenCargaAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context mContext;
    private List<OrdenCargo> list = new ArrayList<>();
    private OrdenCargoListener listener;
    AlertDialog alertDialog;

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
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        final OrdenCargo ordenCargo = list.get(position);
        final OrdenCargoHolder ordenCargoHolder = (OrdenCargoHolder) holder;

        ordenCargoHolder.bind(ordenCargo, mContext, listener);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onOrdenCargoClickListener(ordenCargo);
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                final View layout_dialog_ordenCarga = View.inflate(mContext, R.layout.dialog_option_orden_carga, null);
                final Button btnEdit = (Button) layout_dialog_ordenCarga.findViewById(R.id.btn_edit_ordencarga);
                final Button btnDelete = (Button) layout_dialog_ordenCarga.findViewById(R.id.btn_delete_ordencarga);

                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setView(layout_dialog_ordenCarga);

                btnEdit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //Snackbar.make(holder.itemView,"OnCliclBtnEdit",Snackbar.LENGTH_LONG).show();
                        listener.onOrdenCargoLongClickListener(Constants.CLICK_EDITAR_CAJA_GASTO,ordenCargo,holder.itemView,alertDialog);
                        alertDialog.dismiss();
                    }
                });
                btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                       // Snackbar.make(holder.itemView,"OnCliclBtnDelete",Snackbar.LENGTH_LONG).show();
                       listener.onOrdenCargoLongClickListener(Constants.CLICK_ELIMINAR_CAJA_GASTO,ordenCargo,holder.itemView,alertDialog);
                      //  metDelete(ordenCargo);
                        //removeAt(position);
                       // alertDialog.dismiss();
                    }
                });


                //  builder.setTitle("Title");
                alertDialog = builder.create();
                alertDialog.getWindow().setLayout(200,40); //Controlling width and height.
                alertDialog.show();


                return false;
            }
        });
    }


    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }



    /*
    private void removeItem(Information infoData) {

        int currPosition = data.indexOf(infoData);
        data.remove(currPosition);
        notifyItemRemoved(currPosition);
    }*/
    public void removeAt(int position) {
        list.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, list.size());
    }

    public void updateOrder(int position){
        notifyItemRangeChanged(position, list.size());
    }

}
