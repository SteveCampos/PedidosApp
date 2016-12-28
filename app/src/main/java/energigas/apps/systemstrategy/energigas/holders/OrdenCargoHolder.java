package energigas.apps.systemstrategy.energigas.holders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.entities.Concepto;
import energigas.apps.systemstrategy.energigas.entities.OrdenCargo;
import energigas.apps.systemstrategy.energigas.entities.Unidad;
import energigas.apps.systemstrategy.energigas.interfaces.OrdenCargoListener;
import energigas.apps.systemstrategy.energigas.utils.Utils;

/**
 * Created by Steve on 28/12/2016.
 */

public class OrdenCargoHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.txt_ordencargo_title)
    TextView txtTitle;
    @BindView(R.id.txt_ordencargo_subtitle)
    TextView txtSubtitle;
    @BindView(R.id.txt_ordencargo_date)
    TextView txtDate;


    public OrdenCargoHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(OrdenCargo ordenCargo, Context context, OrdenCargoListener listener){
        txtTitle.setText(Utils.formatDouble(ordenCargo.getCantidadTransformada())+ " " + Unidad.find(Unidad.class, "un_Id = ? ", ordenCargo.getUnIdTransformada()+"").get(0).getAbreviatura());
        if (!TextUtils.isEmpty(ordenCargo.getNroComprobante())){
            txtSubtitle.setText(ordenCargo.getNroComprobante());
        }else{
            txtSubtitle.setText(ordenCargo.getNroGuia());
        }
        txtDate.setText(ordenCargo.getFechaCreacion());

    }
}
