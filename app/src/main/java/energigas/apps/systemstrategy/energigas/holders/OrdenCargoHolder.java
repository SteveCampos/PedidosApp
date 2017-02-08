package energigas.apps.systemstrategy.energigas.holders;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.vision.text.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.entities.Concepto;
import energigas.apps.systemstrategy.energigas.entities.OrdenCargo;
import energigas.apps.systemstrategy.energigas.entities.Producto;
import energigas.apps.systemstrategy.energigas.entities.Proveedor;
import energigas.apps.systemstrategy.energigas.entities.Unidad;
import energigas.apps.systemstrategy.energigas.interfaces.OrdenCargoListener;
import energigas.apps.systemstrategy.energigas.utils.Constants;
import energigas.apps.systemstrategy.energigas.utils.Utils;

/**
 * Created by Steve on 28/12/2016.
 */

public class OrdenCargoHolder extends RecyclerView.ViewHolder {
    /*@BindView(R.id.txt_ordencargo_title)
    TextView txtTitle;
    @BindView(R.id.txt_ordencargo_subtitle)
    TextView txtSubtitle;
    @BindView(R.id.txt_ordencargo_date)
    TextView txtDate;*/
    @BindView(R.id.txtnombre)
    TextView txtNombre;
    @BindView(R.id.txtnombreTrasciego)
    TextView txtTrasciego;
    @BindView(R.id.text_orden_cargo_cantidad)
    TextView txtCantidad;
    @BindView(R.id.txt_info_orden_carga)
    TextView txtInfoOrdenCarga;
    @BindView(R.id.text_orden_cargo_cantidad_trasciego)
    TextView txtCantidadTrasciego;
    @BindView(R.id.txt_info_orden_carga_trasciego)
    TextView txtInfoOrdenCargaTrasciego;
    @BindView(R.id.idLinearCompra)
    RelativeLayout relativeLayoutCompra;
    @BindView(R.id.idLinearTrasciego)
    RelativeLayout relativeLayoutTrasciego;
    @BindView(R.id.imageView2)
    ImageView imageView2;
    //Resources resources;


    public OrdenCargoHolder(View itemView) {
        super(itemView);
       // resources = context.getResources();
        ButterKnife.bind(this, itemView);
    }

    public void bind(OrdenCargo ordenCargo, Context context, OrdenCargoListener listener) {

        Concepto  mconceptoTipoCarga = Concepto.getConcepto(ordenCargo.getTipoCargaId() + "");
        Producto mProducto= Producto.getProductoById(ordenCargo.getProId()+"");
        imageView2.setImageDrawable(context.getResources().getDrawable(getImage(mconceptoTipoCarga.getIdConcepto())));

        switch (mconceptoTipoCarga.getDescripcion().toLowerCase()) {
            case "compra":

                Proveedor mproveedor = Proveedor.getProveedorById(ordenCargo.getProveedorId());
                txtCantidad.setText(Utils.formatDoublePrint(ordenCargo.getCantidadTransformada()) + " " + Unidad.find(Unidad.class, "un_Id = ? ", ordenCargo.getUnIdTransformada() + "").get(0).getAbreviatura());
                txtNombre.setText(mconceptoTipoCarga.getDescripcion());
                relativeLayoutCompra.setVisibility(View.VISIBLE);
                relativeLayoutTrasciego.setVisibility(View.GONE);
                String tetextinfCompra = String.format(context.getString(R.string.string_info_orden_compra),mproveedor.getPersona().getNombreComercial()+"",mproveedor.getPersona().getPerVDocIdentidad()+"",
                        mProducto.getDescripcion()+"", ordenCargo.getFechaAccion(), ordenCargo.getNroGuia());
                txtInfoOrdenCarga.setText(tetextinfCompra);
                break;
            case "trasciego":
                txtTrasciego.setText(mconceptoTipoCarga.getDescripcion());
               // imageView2.setImageDrawable(context.getResources().getDrawable(getImage(mconceptoTipoCarga.getDescripcion().toLowerCase())));
                Concepto mconcepto = Concepto.find(Concepto.class, " id_Concepto= ? ", new String[]{ ordenCargo.getTipoOrigenId()+ ""}).get(Constants.CURRENT);
                txtCantidadTrasciego.setText(Utils.formatDoublePrint(ordenCargo.getCantidadTransformada()) + " " + Unidad.find(Unidad.class, "un_Id = ? ", ordenCargo.getUnIdTransformada() + "").get(0).getAbreviatura());
                relativeLayoutCompra.setVisibility(View.GONE);
                relativeLayoutTrasciego.setVisibility(View.VISIBLE);
                String tetextinfTrasciego = String.format(context.getString(R.string.string_info_orden_trasciego),mconcepto.getDescripcion(),
                        mProducto.getDescripcion()+"", ordenCargo.getFechaAccion(), ordenCargo.getNroGuia());
                txtInfoOrdenCargaTrasciego.setText(tetextinfTrasciego);
                break;
            default:
                Log.d("OrdenCargoHolder", "Warning");
                break;

        }

        /*
        txtTitle.setText(Utils.formatDouble(ordenCargo.getCantidadTransformada())+ " " + Unidad.find(Unidad.class, "un_Id = ? ", ordenCargo.getUnIdTransformada()+"").get(0).getAbreviatura());
        if (!TextUtils.isEmpty(ordenCargo.getNroComprobante())){
            txtSubtitle.setText(ordenCargo.getNroComprobante());
        }else{
            txtSubtitle.setText(ordenCargo.getNroGuia());
        }
        txtDate.setText(ordenCargo.getFechaCreacion());*/

    }

    int getImage(int tipocarga) {
        if (Constants.ORDENCARGA_COMPRA == tipocarga) {
            return R.drawable.ordencarga_compra;
        } else {
            return R.drawable.ordencarga_trasciego;
        }
    }




}
