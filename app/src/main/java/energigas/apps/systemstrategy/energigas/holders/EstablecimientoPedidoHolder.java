package energigas.apps.systemstrategy.energigas.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.vision.text.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import energigas.apps.systemstrategy.energigas.R;

/**
 * Created by Steve on 10/08/2016.
 */

public class EstablecimientoPedidoHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.text_station_order_title) public TextView title;
    @BindView(R.id.text_station_order_quantity) public TextView quantity;
    @BindView(R.id.text_station_order_scope) public TextView scope;
    @BindView(R.id.text_station_order_state) public TextView state;
    //@BindView(R.id.text_station_order_programed_date) public TextView programedDate;
    @BindView(R.id.textPrecio) public TextView textPrecio;
    @BindView(R.id.horarioAtencion) public TextView textHorarioAtencion;
    @BindView(R.id.text_station_numero_pedidos) public TextView textNumeroPedidos;

    @BindView(R.id.text_station_fecha_entrega) public TextView fechaProgramed;



    public EstablecimientoPedidoHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
