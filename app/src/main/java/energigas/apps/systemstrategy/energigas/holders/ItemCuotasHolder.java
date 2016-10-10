package energigas.apps.systemstrategy.energigas.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import energigas.apps.systemstrategy.energigas.R;

/**
 * Created by kelvi on 30/09/2016.
 */

public class ItemCuotasHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.fecha_cuota)
    public TextView fechaCuota;
    @BindView(R.id.precio_cuota)
    public TextView precioCuota;
    @BindView(R.id.cambiar_fecha)
    public TextView cambiarFecha;
    @BindView(R.id.datePickerFecha)
    public DatePicker datePickerFecha;
    @BindView(R.id.btnSaveDate)
    public ImageButton buttonSaveDate;

    public ItemCuotasHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);

    }
}
