package energigas.apps.systemstrategy.energigas.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import energigas.apps.systemstrategy.energigas.R;

/**
 * Created by Steve on 12/08/2016.
 */
public class ComprobanteVentaHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.txt_factura)
    public TextView txt_factura;
    @BindView(R.id.text_cv_details)
    public TextView textViewDetalle;
    @BindView(R.id.text_cv_total)
    public TextView textViewTotal;
    @BindView(R.id.textHorario)
    public TextView textViewHorario;

    public ComprobanteVentaHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
