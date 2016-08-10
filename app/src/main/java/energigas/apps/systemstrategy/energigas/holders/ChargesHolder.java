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

public class ChargesHolder extends RecyclerView.ViewHolder {


    @BindView(R.id.txt_compserie) public TextView mcomprobante;
    @BindView(R.id.txt_date) public TextView mdate;
    @BindView(R.id.txt_total) public TextView mtotal;
    public ChargesHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
}
