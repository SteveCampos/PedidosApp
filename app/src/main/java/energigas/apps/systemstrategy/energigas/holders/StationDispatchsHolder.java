package energigas.apps.systemstrategy.energigas.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import energigas.apps.systemstrategy.energigas.R;

/**
 * Created by Steve on 3/08/2016.
 */

public class StationDispatchsHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.text_station_dispatch_product)
    public TextView dispatchProduct;
    @BindView(R.id.text_station_dispatch_quantity)
    public TextView dispatchQuantity;
    @BindView(R.id.text_station_dispatch_tank)
    public TextView dispatchTank;
    @BindView(R.id.text_station_dispatch_state)
    public TextView dispatchState;

    public StationDispatchsHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
