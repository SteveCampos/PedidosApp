package energigas.apps.systemstrategy.energigas.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import energigas.apps.systemstrategy.energigas.R;

/**
 * Created by Steve on 16/09/2016.
 */

public class BluetoothDeviceHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.textName)
    public TextView name;
    @BindView(R.id.textAdress)
    public TextView adress;

    public BluetoothDeviceHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
