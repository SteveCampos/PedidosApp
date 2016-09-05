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
public class ComprobanteVentaHolder extends RecyclerView.ViewHolder{

   @BindView(R.id.txt_factura) public TextView txt_factura;
    public ComprobanteVentaHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
}
