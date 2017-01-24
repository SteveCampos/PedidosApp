package energigas.apps.systemstrategy.energigas.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import energigas.apps.systemstrategy.energigas.R;

/**
 * Created by Kike on 9/08/2016.
 */

public class CajaGastoHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tv_referencia) public TextView tv_referencia;
   // @BindView(R.id.tv_category) public TextView mdocument;
    @BindView(R.id.tv_total) public TextView mtotal;
    //@BindView(R.id.tv_date) public TextView tv_date;
    @BindView(R.id.tv_ruc) public TextView tv_ruc;
    //@BindView(R.id.tv_nm_comprobante)public TextView tv_nm_comprobante;
    @BindView(R.id.tv_razonSocial)public  TextView tv_razonSocial;
    @BindView(R.id.txt_info_cajagasto) public TextView txt_info_cajagasto;



    public CajaGastoHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
}
