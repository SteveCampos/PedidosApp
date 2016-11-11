package energigas.apps.systemstrategy.energigas.fragments;

import android.app.Dialog;
import android.content.Intent;
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
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.activities.DespachoActivity;
import energigas.apps.systemstrategy.energigas.adapters.ProductoAdapter;
import energigas.apps.systemstrategy.energigas.adapters.TanqueAdapter;
import energigas.apps.systemstrategy.energigas.adapters.UnidadAdapter;
import energigas.apps.systemstrategy.energigas.entities.Almacen;
import energigas.apps.systemstrategy.energigas.entities.Despacho;
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

    @BindView(R.id.spinnerTanque)
    Spinner spinnerTanque;

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
        initTanque();
        initProductos();
        initUnidad();
        initTexCantidad();
    }

    private void initTanque(){
        List<Almacen> almacenList =  Almacen.getListTanque(establecimiento.getEstIEstablecimientoId()+"");
        spinnerTanque.setAdapter(new TanqueAdapter(getActivity(),0,almacenList));
    }

    private void initProductos() {
        List<Producto> productoList = Producto.getAllProducto();
        spinnerProdcuto.setAdapter(new ProductoAdapter(getActivity(), 0, productoList));

    }

    private void initUnidad() {
        List<Unidad> unidadList = Unidad.getAllUnidad();
        spinnerUnidad.setAdapter(new UnidadAdapter(getActivity(), 0, unidadList));

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

    private Almacen getTanque(){
        return (Almacen) spinnerTanque.getSelectedItem();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        Log.d(TAG, s.toString());
        String cantidadString = s.toString();
        if (cantidadString.length() >= 1) {
            if (cantidadString.length() == 1 && cantidadString.equals("0")  && !cantidadString.equals("") && !cantidadString.equals(".")) {
                textViewCantidad.setText("");
                return;
            }

            try {
                double cantidad = Double.parseDouble(cantidadString);
                setValuesTextViews(valuesCost(cantidad));

            }catch (NumberFormatException e){
                textViewCantidad.setText("");
            }


        } else {

            setValuesTextViews(new ValuesCost(0.0,0.0,0.0,0.0));
        }
    }

    @Override
    public void afterTextChanged(Editable s) {
    }

    private ValuesCost valuesCost(double cantidad) {

        listaPrecio = ListaPrecio.getPrecioByProductoId(getProducto().getProId() + "");

        Log.d(TAG,establecimiento.getEstIEstablecimientoId() + "-"+ getUnidad().getUnId() + "-"+ getProducto().getProId() + "-"+ listaPrecio.getLpId() + "");

        listaPrecioDetalle = ListaPrecioDetalle.getPrecioDetalle(establecimiento.getEstIEstablecimientoId() + "", getUnidad().getUnId() + "", getProducto().getProId() + "", listaPrecio.getLpId() + "");

        Log.d(TAG, "listaPrecio: " + listaPrecio.getNombre());

        if (listaPrecioDetalle != null) {
            double subTotal = listaPrecioDetalle.getPrecio() * cantidad;
            double total = listaPrecioDetalle.getPrecioImp() * cantidad;
            double igv = total * listaPrecioDetalle.getPorImpuesto();
            return new ValuesCost(listaPrecioDetalle.getPrecio(), subTotal, igv, total);
        }
        Toast.makeText(getActivity(), "No cuenta con precios configurados para el establecimiento", Toast.LENGTH_SHORT).show();
        return new ValuesCost(0.0, 0.0, 0.0, 0.0);
    }

    private void guardarPedido(){


        iniciarDespacho();

    }

    private void iniciarDespacho(){
        startActivity(new Intent(getActivity(), DespachoActivity.class));
        getActivity().finish();
    }


}
