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

    @BindView(R.id.tv_description) public TextView mdescription;
    @BindView(R.id.tv_category) public TextView mdocument;
    @BindView(R.id.tv_total) public TextView mtotal;

    public CajaGastoHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
}
