package energigas.apps.systemstrategy.energigas.holders;

import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import energigas.apps.systemstrategy.energigas.R;

/**
 * Created by Kike on 9/08/2016.
 */

public class PedidoHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.txtproducts) public TextView mProducts;
    @BindView(R.id.txtaddress) public TextView mAddress;
    @BindView(R.id.txtlocation) public TextView mLocation;
    @BindView(R.id.txtclock) public TextView mClock;
    @BindView(R.id.imageViewStation) public ImageView imageViewStation;
    @BindView(R.id.btnDetails) public AppCompatButton btnDetails;

    public PedidoHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
}
