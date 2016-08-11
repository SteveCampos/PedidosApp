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

public class OrderDetailHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.text_order_detail_title) public TextView title;
    @BindView(R.id.text_order_detail_quantity) public TextView quantity;
    @BindView(R.id.text_order_detail_price) public TextView precio;
    @BindView(R.id.text_order_detail_cost) public TextView cost;


    public OrderDetailHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
