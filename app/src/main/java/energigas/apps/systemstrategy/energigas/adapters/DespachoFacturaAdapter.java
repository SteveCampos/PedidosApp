package energigas.apps.systemstrategy.energigas.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.entities.Despacho;
import energigas.apps.systemstrategy.energigas.entities.Producto;
import energigas.apps.systemstrategy.energigas.entities.Unidad;
import energigas.apps.systemstrategy.energigas.holders.DespachoFacturaHolder;
import energigas.apps.systemstrategy.energigas.utils.Utils;

/**
 * Created by kelvi on 12/09/2016.
 */

public class DespachoFacturaAdapter extends RecyclerView.Adapter<DespachoFacturaHolder> {
    List<Despacho> despachos;
    Context context;

    public DespachoFacturaAdapter(List<Despacho> despachos, Context context) {
        this.despachos = despachos;
        this.context = context;
    }

    @Override
    public DespachoFacturaHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_item_venta_despacho, parent, false);
        // Return a new holder instance
        return new DespachoFacturaHolder(view);
    }

    @Override
    public void onBindViewHolder(DespachoFacturaHolder holder, int position) {
        final Despacho despacho = despachos.get(position);

        Unidad unidad = Unidad.getUnidadProductobyUnidadMedidaId(despacho.getUnId() + "");
        Producto producto = Producto.getProductoById(despacho.getProId() + "");
        holder.textDespachoNro.setText("" + despacho.getSerie() + " - " + despacho.getNumero());
        holder.textProducto.setText(producto.getNombre());
        holder.textViewUnidadMedida.setText(unidad.getAbreviatura());
        holder.textCantidad.setText(Utils.formatDouble(despacho.getCantidadDespachada()));
    }

    @Override
    public int getItemCount() {
        return despachos.size();
    }
}
