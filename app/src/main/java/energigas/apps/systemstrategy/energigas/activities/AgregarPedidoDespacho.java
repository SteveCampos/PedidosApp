package energigas.apps.systemstrategy.energigas.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Spinner;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.adapters.ProductoAdapter;
import energigas.apps.systemstrategy.energigas.entities.Producto;

public class AgregarPedidoDespacho extends AppCompatActivity {
    @BindView(R.id.spinnerProducto)
    Spinner spinnerProdcuto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_pedido_despacho);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        initWidgets();
    }

    private void initWidgets() {
        initProductos();
    }

    private void initProductos() {
        List<Producto> productoList = Producto.getAllProducto();
        spinnerProdcuto.setAdapter(new ProductoAdapter(this, 0, productoList));

    }

    private Producto getProducto() {
        return (Producto) spinnerProdcuto.getSelectedItem();
    }

}
