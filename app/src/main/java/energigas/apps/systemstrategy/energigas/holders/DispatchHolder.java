package energigas.apps.systemstrategy.energigas.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import energigas.apps.systemstrategy.energigas.R;

/**
 * Created by Kike on 9/08/2016.
 */

public class DispatchHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.textViewTanque) public TextView textViewTank;
    @BindView(R.id.textviewQuantity) public TextView textViewQuantity;
    @BindView(R.id.layoutBackground) public RelativeLayout relativeLayout;
    @BindView(R.id.imageView) public ImageView imageViewChecked;

    public DispatchHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
}
