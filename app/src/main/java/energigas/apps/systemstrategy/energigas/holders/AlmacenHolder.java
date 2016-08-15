package energigas.apps.systemstrategy.energigas.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import energigas.apps.systemstrategy.energigas.R;

/**
 * Created by Steve on 10/08/2016.
 */

public class AlmacenHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.text_station_tank_title) public TextView title;
    @BindView(R.id.text_station_tank_capacity) public TextView capacity;
    @BindView(R.id.text_station_tank_politica) public TextView politica;
    @BindView(R.id.text_station_tank_capacity_neta) public TextView capacidadNeta;
    @BindView(R.id.text_almacen_estado) public TextView estado;
    @BindView(R.id.text_station_programed) public TextView programado;


    public AlmacenHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
