package energigas.apps.systemstrategy.energigas.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.entities.Almacen;
import energigas.apps.systemstrategy.energigas.entities.Cliente;
import energigas.apps.systemstrategy.energigas.entities.ComprobanteVenta;
import energigas.apps.systemstrategy.energigas.entities.Despacho;
import energigas.apps.systemstrategy.energigas.entities.Estado;
import energigas.apps.systemstrategy.energigas.entities.Producto;
import energigas.apps.systemstrategy.energigas.entities.Unidad;
import energigas.apps.systemstrategy.energigas.holders.DespachoHolder;
import energigas.apps.systemstrategy.energigas.holders.StationDispatchsHolder;
import energigas.apps.systemstrategy.energigas.utils.Constants;
import energigas.apps.systemstrategy.energigas.utils.Utils;

/**
 * Created by Steve on 3/08/2016.
 */

public class StationDispatchsAdapter extends RecyclerView.Adapter<DespachoHolder> {

    private static final String TAG = StationDispatchsAdapter.class.getSimpleName();
    // Store a member variable for the list;
    private List<Despacho> stationDispatches;
    // Store the context for easy access
    private Context mContext;

    private SparseBooleanArray mSelectedItemsIds;
    private OnStationDispatchClickListener listener;
    private HashMap<Long, Despacho> longDespachoHashMap;

    public interface OnStationDispatchClickListener {
        void onStationDispatchClickListener(Despacho orderDispatch, View view, int typeListener);
    }

    public StationDispatchsAdapter(List<Despacho> stationDispatches, Context mContext, OnStationDispatchClickListener listener) {
        this.stationDispatches = stationDispatches;
        this.mContext = mContext;
        this.listener = listener;
        mSelectedItemsIds = new SparseBooleanArray();
        longDespachoHashMap = new HashMap<>();
    }

    @Override
    public DespachoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        // Inflate the custom layout
        View view = inflater.inflate(R.layout.item_despacho, parent, false);
        // Return a new holder instance
        return new DespachoHolder(view);
    }

    @Override
    public void onBindViewHolder(DespachoHolder holder, int position) {
        final Despacho despacho = stationDispatches.get(position);
        final Cliente cliente = Cliente.getCliente(despacho.getClienteId() + "");
        Producto producto = Producto.find(Producto.class, " pro_Id = ?", new String[]{despacho.getProId() + ""}).get(Constants.CURRENT);
        Log.d(TAG, "produclist" + producto);
        Unidad unidad = Unidad.getUnidadProductobyUnidadMedidaId(despacho.getUnId() + "");
        Almacen almacen = Almacen.getAlmacenById(despacho.getAlmacenEstId() + "");
        Estado estado = Estado.getEstado(despacho.getEstadoId() + "");
        holder.title.setText(despacho.getSerie() + "-" + despacho.getNumero());
        holder.quantity.setText(Utils.formatDoublePrint(despacho.getCantidadDespachada()) + " " + unidad.getAbreviatura());
        String facturadoString = "";

        if (despacho.getCompId() > 0) {
            ComprobanteVenta comprobanteVenta = ComprobanteVenta.getComprobanteVentaId(despacho.getCompId() + "");
            facturadoString = "Factura: " + comprobanteVenta.getSerie() + "-" + comprobanteVenta.getNumDoc();

        }
        holder.state.setText(estado.getDescripcion());


        if (despacho.getEstadoId() == Constants.ESTADO_DESPACHO_CREADO) {

        }
        if (despacho.getEstadoId() == Constants.ESTADO_ELIMINAR_DESPACHO) {

        }
        if (despacho.getEstadoId() == Constants.ESTADO_DESPACHO_FACTURADO) {

        }


        holder.information.setText(
                "Tanque: " + almacen.getNombre() + "\n" +
                        "C. Inicial: " + Utils.formatDoublePrint(despacho.getContadorInicialOrigen()) + " - " + " C. Final: " + Utils.formatDoublePrint(despacho.getContadorFinalOrigen()) + "\n" +
                        "P. Inicial: " + despacho.getpITDestino() + " - " + "P. Final: " + despacho.getpFTDestino() + "\n" +
                        "Hora de despacho: " + despacho.getHoraFin() + "\n" + facturadoString
        );

        int colorAccent = ContextCompat.getColor(mContext, R.color.colorAccent);
        int colorWhite = ContextCompat.getColor(mContext, R.color.white);
        /** Change background color of the selected items  **/
        holder.itemView
                .setBackgroundColor(mSelectedItemsIds.get(position, false) ? colorAccent
                        : Color.WHITE);
        holder.quantity.setTextColor(mSelectedItemsIds.get(position, false) ? colorWhite : colorAccent);


        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                if (cliente.getCliITipoClienteId() != Constants.ESTABLECIMIENTO_EXTERNO) {

                    return true;
                }

                if (despacho.getEstadoId() == Constants.ESTADO_DESPACHO_CREADO) {
                    listener.onStationDispatchClickListener(despacho, view, 1);

                } else {
                    Toast.makeText(mContext, "No puede realizar la factura para este despacho", Toast.LENGTH_SHORT).show();

                }

                return true;
            }
        });
    }


    @Override
    public int getItemCount() {
        return stationDispatches.size();
    }


    /***
     * Methods required for do selections, remove selections, etc.
     */

    //Toggle selection methods
    public void toggleSelection(int position, Despacho despacho) {
        selectView(position, !mSelectedItemsIds.get(position), despacho);
    }


    //Remove selected selections
    public void removeSelection(SparseBooleanArray mStaticSelecteds) {

        /*
        for (int i = 0; i < mStaticSelecteds.size(); i++) {
            int position = mStaticSelecteds.keyAt(i);
            toggleSelection(position);
        }*/
        mSelectedItemsIds = new SparseBooleanArray();
        longDespachoHashMap = new HashMap<>();
        notifyDataSetChanged();
    }


    //Put or delete selected position into SparseBooleanArray
    private void selectView(int position, boolean value, Despacho despacho) {
        if (value) {
            mSelectedItemsIds.put(position, true);
            longDespachoHashMap.put(despacho.getDespachoId(), despacho);

        } else {
            mSelectedItemsIds.delete(position);
            longDespachoHashMap.remove(despacho.getDespachoId());
        }

        notifyItemChanged(position);
    }

    public List<Despacho> getStationDispatchesSelected() {
        return new ArrayList<Despacho>(longDespachoHashMap.values());
    }

    public List<String> getIdsDispatchesSelected() {
        List<String> result = new ArrayList<>();
        for (Despacho despacho : getStationDispatchesSelected()) {
            result.add(despacho.getDespachoId() + "");
        }
        return result;
    }

    //Get total selected count
    public int getSelectedCount() {
        return mSelectedItemsIds.size();
    }

    //Return all selected ids
    public SparseBooleanArray getSelectedIds() {
        return mSelectedItemsIds;
    }

}
