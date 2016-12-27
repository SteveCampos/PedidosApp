package energigas.apps.systemstrategy.energigas.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.adapters.ServidoresAdapter;
import energigas.apps.systemstrategy.energigas.asyntask.AsynObtenerDatosGenerales;
import energigas.apps.systemstrategy.energigas.entities.CajaLiquidacion;
import energigas.apps.systemstrategy.energigas.entities.Servidores;
import energigas.apps.systemstrategy.energigas.fragments.DialogGeneral;
import energigas.apps.systemstrategy.energigas.interfaces.DialogGeneralListener;
import energigas.apps.systemstrategy.energigas.interfaces.OnAsyntaskListener;
import energigas.apps.systemstrategy.energigas.utils.Session;

public class MainConfiguraciones extends AppCompatActivity implements ServidoresAdapter.OnServidoresClickListener, OnAsyntaskListener {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.btnImportarDatosGenerales)
    Button buttonImportar;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_configuraciones);
        ButterKnife.bind(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setIndeterminate(true);
        progressDialog.setTitle("Importando");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initViews();


    }

    private void initViews() {


        List<Servidores> servidores = Servidores.listAll(Servidores.class);
        ServidoresAdapter servidoresAdapter = new ServidoresAdapter(servidores, this, this);
        recyclerView.setAdapter(servidoresAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        buttonImportar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                new AsynObtenerDatosGenerales(MainConfiguraciones.this).execute();
            }
        });
    }

    @Override
    public void onServidoresClickListener(Servidores servidores, View view, int typeClick) {
        initDialog(servidores);
    }

    private void initDialog(final Servidores servidores) {
        Log.d("MainConfiguraciones", servidores.getName());
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.layout_editar_servidores, null);
        dialogBuilder.setView(dialogView);
        TextView textViewTitulo = (TextView) dialogView.findViewById(R.id.textTitle);
        final EditText editText = (EditText) dialogView.findViewById(R.id.editDireccion);
        editText.setText(servidores.getDescription());
        Button buttonCancel = (Button) dialogView.findViewById(R.id.btn_cancel);
        Button buttonOk = (Button) dialogView.findViewById(R.id.btn_ok);
        textViewTitulo.setText(servidores.getName());
        final AlertDialog alertDialog = dialogBuilder.create();
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!editText.getText().equals("")) {
                    servidores.setDescription(editText.getText().toString());
                    servidores.save();
                }
                alertDialog.dismiss();
                initViews();
            }
        });

        alertDialog.show();
    }

    @Override
    public void onLoadSuccess(String message, CajaLiquidacion cajaLiquidacion) {
        progressDialog.dismiss();
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoadError(String message) {
        progressDialog.dismiss();
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoadErrorProcedure(String message) {
        progressDialog.dismiss();
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
