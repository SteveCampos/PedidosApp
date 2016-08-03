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

public class StationProductsHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.text_station_product_title) public TextView productTitle;
    @BindView(R.id.text_station_product_quantity) public TextView productQuantity;

    public StationProductsHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
