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

public class ServidoresHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.textTitulo) public TextView title;
    @BindView(R.id.textDescripcion) public TextView descripcion;


    public ServidoresHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
