package energigas.apps.systemstrategy.energigas.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.entities.ComprobanteVenta;

/**
 * Created by Steve on 26/07/2016.
 */

public class PrintFacturaActivity extends AppCompatActivity{


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.textViewImprimirCabecera)
    TextView cabecera_empresa;

    @BindView(R.id.textViewVentaCabecera)
    TextView venta_cabecera;



    ComprobanteVenta comprobanteVenta;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_print);

        ButterKnife.bind(this);
        initViews();
    }


    private void initViews() {
        // Adding Toolbar to Main screen
        setSupportActionBar(toolbar);

        // Adding menu icon to Toolbar
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
            supportActionBar.setTitle("Imprimir Factura");
        }
    }




}
