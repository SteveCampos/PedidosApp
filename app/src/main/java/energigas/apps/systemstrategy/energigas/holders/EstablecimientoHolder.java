package energigas.apps.systemstrategy.energigas.holders;

import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import energigas.apps.systemstrategy.energigas.R;

/**
 * Created by Kike on 9/08/2016.
 */

public class EstablecimientoHolder extends RecyclerView.ViewHolder {


    @BindView(R.id.txtnombre)
    public TextView mname;
    @BindView(R.id.txtdireccion)
    public TextView maddress;
    @BindView(R.id.txtpoints)
    public TextView mpoint;
    @BindView(R.id.txtubicacion)
    public TextView mubicacion;
    @BindView(R.id.imageView2)
    public ImageView imageView2;
    @BindView(R.id.textAtendido)
    public TextView textAtendido;
    @BindView(R.id.indiceorden)
    public Button button;

    public EstablecimientoHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
