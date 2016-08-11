package energigas.apps.systemstrategy.energigas.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import energigas.apps.systemstrategy.energigas.R;

/**
 * Created by Steve on 11/08/2016.
 */

public class DespachoHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.text_dispatch_title) public TextView title;
    @BindView(R.id.text_dispatch_requested_quantity) public TextView quantity;
    @BindView(R.id.text_dispatch_information) public TextView information;
    @BindView(R.id.text_dispatch_state) public TextView state;

    public DespachoHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}

