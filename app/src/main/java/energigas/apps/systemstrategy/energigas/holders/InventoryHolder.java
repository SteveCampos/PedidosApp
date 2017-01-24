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

public class InventoryHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.textNameProduct)
    public TextView textNameProduct;
    @BindView(R.id.inicio)
    public TextView inicio;
    @BindView(R.id.carga)
    public TextView carga;
    @BindView(R.id.despacho)
    public TextView despacho;
    @BindView(R.id.inFinal)
    public TextView inFinal;

    public InventoryHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
