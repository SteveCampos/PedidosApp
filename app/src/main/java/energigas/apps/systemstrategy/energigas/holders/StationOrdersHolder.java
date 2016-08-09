package energigas.apps.systemstrategy.energigas.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import energigas.apps.systemstrategy.energigas.R;

/**
 * Created by Steve on 8/08/2016.
 */

public class StationOrdersHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.text_station_order_title) public TextView orderTitle;
    @BindView(R.id.text_station_order_programed_date) public TextView programedDate;

    public StationOrdersHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
