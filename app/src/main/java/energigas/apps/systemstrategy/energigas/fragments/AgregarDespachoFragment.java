package energigas.apps.systemstrategy.energigas.fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.adapters.ProductoAdapter;
import energigas.apps.systemstrategy.energigas.adapters.UnidadAdapter;
import energigas.apps.systemstrategy.energigas.entities.Establecimiento;
import energigas.apps.systemstrategy.energigas.entities.ListaPrecio;
import energigas.apps.systemstrategy.energigas.entities.ListaPrecioDetalle;
import energigas.apps.systemstrategy.energigas.entities.Producto;
import energigas.apps.systemstrategy.energigas.entities.Unidad;
import energigas.apps.systemstrategy.energigas.entities.ValuesCost;
import energigas.apps.systemstrategy.energigas.utils.Session;

/**
 * Created by kelvi on 02/11/2016.
 */

public class AgregarDespachoFragment extends DialogFragment implements TextWatcher {

    @BindView(R.id.spinnerProducto)
    Spinner spinnerProdcuto;

    @BindView(R.id.spinnerUnidad)
    Spinner spinnerUnidad;

    @BindView(R.id.textCantidad)
    TextView textViewCantidad;

    @BindView(R.id.textPrecioUnitario)
    TextView textViewPrecioUnitario;

    @BindView(R.id.textSubTotal)
    TextView textSubTotal;

    @BindView(R.id.textIgv)
    TextView textIgv;

    @BindView(R.id.textTotal)
    TextView textTotal;

    private static final String TAG = "AgregarDespachoFragment";

    private Establecimiento establecimiento;
    private ListaPrecio listaPrecio;
    private ListaPrecioDetalle listaPrecioDetalle;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        setCancelable(true);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.AppTheme);
        establecimiento = Establecimiento.getEstablecimientoById(Session.getSessionEstablecimiento(getActivity()).getEstIEstablecimientoId() + "");
        return dialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.content_agregar_pedido_despacho, container, false);
        ButterKnife.bind(this, rootView);
        initWidgets();
        return rootView;
    }


    private void initWidgets() {
        initProductos();
        initUnidad();
        initTexCantidad();
    }

    private void initProductos() {
        List<Producto> productoList = Producto.getAllProducto();
        spinnerProdcuto.setAdapter(new ProductoAdapter(getActivity(), 0, productoList));

    }

    private void initUnidad() {
        List<Unidad> unidadList = Unidad.getAllUnidad();
        spinnerUnidad.setAdapter(new UnidadAdapter(getActivity(), 0, unidadList));

    }

    private void calculeCost(){
        listaPrecio = ListaPrecio.getPrecioByProductoId(getProducto().getProId()+"");
        listaPrecioDetalle = ListaPrecioDetalle.getPrecioDetalle(establecimiento.getEstIEstablecimientoId()+"",getUnidad().getUnId()+"",getProducto().getProId()+"",listaPrecio.getLpId()+"");
    }

    private void setValuesTextViews(ValuesCost values) {

        textViewPrecioUnitario.setText(values.getPrecioUnitario() + "");
        textSubTotal.setText(values.getSubTotal() + "");
        textIgv.setText(values.getIgv() + "");
        textTotal.setText(values.getTotal() + "");
    }

    private void initTexCantidad() {
        textViewCantidad.addTextChangedListener(this);
    }

    private Producto getProducto() {
        return (Producto) spinnerProdcuto.getSelectedItem();
    }

    private Unidad getUnidad() {
        return (Unidad) spinnerUnidad.getSelectedItem();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        Log.d(TAG, s.toString());
    }

    @Override
    public void afterTextChanged(Editable s) {
    }


}
