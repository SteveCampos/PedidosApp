package energigas.apps.systemstrategy.energigas.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import energigas.apps.systemstrategy.energigas.R;

/**
 * Created by Kike on 12/08/2016.
 */

public class SummaryHolder extends RecyclerView.ViewHolder{

    @BindView(R.id.text_establishment_name) public TextView nameTextView;
    public SummaryHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
}
