package energigas.apps.systemstrategy.energigas.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import energigas.apps.systemstrategy.energigas.R;

/**
 * Created by kelvi on 12/09/2016.
 */

public class DespachoFacturaHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.textDespachoNro)
    public TextView textDespachoNro;
    @BindView(R.id.textSerieNumero)
    public TextView textSerieNumero;
    @BindView(R.id.textCantidad)
    public TextView textCantidad;
    public DespachoFacturaHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
}
