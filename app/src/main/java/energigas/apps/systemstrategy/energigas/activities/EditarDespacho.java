package energigas.apps.systemstrategy.energigas.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.orm.SugarTransactionHelper;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.entities.Almacen;
import energigas.apps.systemstrategy.energigas.entities.CajaLiquidacion;
import energigas.apps.systemstrategy.energigas.entities.CajaLiquidacionDetalle;
import energigas.apps.systemstrategy.energigas.entities.Despacho;
import energigas.apps.systemstrategy.energigas.entities.Establecimiento;
import energigas.apps.systemstrategy.energigas.entities.Pedido;
import energigas.apps.systemstrategy.energigas.entities.PedidoDetalle;
import energigas.apps.systemstrategy.energigas.entities.Producto;
import energigas.apps.systemstrategy.energigas.entities.Serie;
import energigas.apps.systemstrategy.energigas.entities.Unidad;
import energigas.apps.systemstrategy.energigas.entities.Usuario;
import energigas.apps.systemstrategy.energigas.entities.Vehiculo;
import energigas.apps.systemstrategy.energigas.fragments.DialogGeneral;
import energigas.apps.systemstrategy.energigas.interfaces.DialogGeneralListener;
import energigas.apps.systemstrategy.energigas.utils.Constants;
import energigas.apps.systemstrategy.energigas.utils.Session;
import energigas.apps.systemstrategy.energigas.utils.Utils;

public class EditarDespacho extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;


    @BindView(R.id.tanq_destino_capacidad)
    TextView textViewDestinoCapacidad;

    @BindView(R.id.tanq_destino_programado)
    TextView textViewDestinoProgramado;


    @BindView(R.id.tanq_destino_orden_sugerencia)
    TextView textViewOrdenSugerencia;

    @BindView(R.id.text_unidad_medida)
    TextView text_unidad_medida;

    @BindView(R.id.text_despacho_serie_numero)
    TextView textViewSerieNumero;

    @BindView(R.id.textViewTanque)
    TextView textViewTanque;

    @BindView(R.id.textViewProducto)
    TextView textViewProducto;

    @BindView(R.id.text_despacho_estacion)
    TextView textDespachoEstacion;

    /* EditText Instance */

    @BindView(R.id.editTextContadorInicial)
    EditText editTextOrigenContadorInicial;
    @BindView(R.id.editTextPorcentajeIncial)
    EditText editTextOrigenPorcentajeInicial;


    @BindView(R.id.editTextDestinoContIncial)
    EditText editTextDestinoContadorInicial;
    @BindView(R.id.editDestinoPorcentajeInicial)
    EditText editTextDestinoPorcentajeInicial;


    @BindView(R.id.editOrigen2CF)
    EditText editTextOrigenContadorFinal;
    @BindView(R.id.editOrigen2PF)
    EditText editTextOrigenPorcentajeFinal;

    @BindView(R.id.editDestino2CF)
    EditText editTextDestinoContadorFinal;
    @BindView(R.id.editDestino2PF)
    EditText editTextDestinoPorcentajeFinal;

    @BindView(R.id.editCantidadDespachada)
    EditText editTextCantidadDespachada;

    /*==========================================*/


    private Despacho despacho;
    private PedidoDetalle pedidoDetalle;
    private Establecimiento establecimiento;
    private Almacen almacen;
    private Usuario usuario;
    private Pedido pedido;
    private Serie serie;
    private CajaLiquidacion cajaLiquidacion;
    private CajaLiquidacionDetalle cajaLiquidacionDetalle;
    private Almacen almacenDistribuidor;
    private Vehiculo vehiculo;
    private Unidad unidad;

    @BindView(R.id.btnGuardar)
    Button buttonGuardar;


    private double factorConvercion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_despacho);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        String jsonDespacho = getIntent().getExtras().getString("PEDIDO");
        ObjectMapper mapper = new ObjectMapper();
        factorConvercion = Utils.getFactorConvercion();
        usuario = Usuario.find(Usuario.class, " usu_I_Usuario_Id = ? ", new String[]{Session.getSession(this).getUsuIUsuarioId() + ""}).get(Constants.CURRENT);


        cajaLiquidacion = CajaLiquidacion.find(CajaLiquidacion.class, " liq_Id = ? ", new String[]{Session.getCajaLiquidacion(this).getLiqId() + ""}).get(Constants.CURRENT);
        establecimiento = Establecimiento.find(Establecimiento.class, " est_I_Establecimiento_Id = ?  ", new String[]{Session.getSessionEstablecimiento(this).getEstIEstablecimientoId() + ""}).get(Constants.CURRENT);


        serie = Serie.findWithQuery(Serie.class, Utils.getQueryForSerie(usuario.getUsuIUsuarioId(), Constants.TIPO_ID_DEVICE_CELULAR, Constants.TIPO_ID_COMPROBANTE_DESPACHO), null).get(Constants.CURRENT);
        almacenDistribuidor = Almacen.findWithQuery(Almacen.class, Utils.getQuerDespachoVehiculo(usuario.getUsuIUsuarioId()), null).get(Constants.CURRENT);
        almacen = Almacen.find(Almacen.class, " alm_Id = ?  ", new String[]{Session.getAlmacen(this).getAlmId() + ""}).get(Constants.CURRENT);


        pedido = Pedido.find(Pedido.class, " pe_Id = ? ", new String[]{Session.getPedido(this).getPeId() + ""}).get(Constants.CURRENT);
        pedidoDetalle = PedidoDetalle.find(PedidoDetalle.class, " pe_Id = ? ", new String[]{Session.getPedido(this).getPeId() + ""}).get(Constants.CURRENT);
        cajaLiquidacionDetalle = CajaLiquidacionDetalle.getLiquidacionDetalleByEstablecAndPedido(establecimiento.getEstIEstablecimientoId() + "", pedido.getPeId() + "");
        unidad = Unidad.getUnidadProductobyUnidadMedidaId(pedidoDetalle.getUnidadId() + "");
        try {
            despacho = mapper.readValue(jsonDespacho, Despacho.class);

            if (despacho != null) {
                initViews();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void textCantidadListener() {
        editTextCantidadDespachada.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count > 0) {
                    Double cantidad = Double.parseDouble(s.toString());
                    if (unidad.getAbreviatura().equals("Gl")) {
                        if (cantidad > almacen.getCapacidadReal()) {
                            editTextCantidadDespachada.setText("");
                            Toast.makeText(EditarDespacho.this, "La Cantidad supera la capacidad real", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Double cantidadProgramaConv = pedidoDetalle.getCantidad() / factorConvercion;
                        if (cantidad > almacen.getCapacidadReal() && cantidad >= cantidadProgramaConv) {
                            editTextCantidadDespachada.setText("");
                            Toast.makeText(EditarDespacho.this, "La Cantidad supera la capacidad real", Toast.LENGTH_SHORT).show();
                        }
                    }


                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @OnClick(R.id.btnGuardar)
    public void guardarEditarDespacho(View view) {
        if (validateText()) {


            new DialogGeneral(this).setCancelable(false).setMessages("Atencion", "¿Esta seguro de guardar?").setTextButtons("Si", "No").showDialog(new DialogGeneralListener() {
                @Override
                public void onSavePressed(AlertDialog alertDialog) {
                    SugarTransactionHelper.doInTransaction(new SugarTransactionHelper.Callback() {
                        @Override
                        public void manipulateInTransaction() {
                            guardarDespacho();
                        }

                        @Override
                        public void errorInTransaction(String error) {
                            Toast.makeText(EditarDespacho.this, "" + error, Toast.LENGTH_SHORT).show();
                        }
                    });
                    alertDialog.dismiss();
                }

                @Override
                public void onCancelPressed(AlertDialog alertDialog) {
                    alertDialog.dismiss();
                }
            });


        }
    }

    private void guardarDespacho() {
        despacho.setCantidadDespachada(Utils.formatDoubleNumber(Double.parseDouble(editTextCantidadDespachada.getText().toString())));

        despacho.setContadorInicialOrigen(Utils.formatDoubleNumber(Double.parseDouble(editTextOrigenContadorInicial.getText().toString())));
        despacho.setpITOrigen(Utils.formatDoubleNumber(Double.parseDouble(editTextOrigenPorcentajeInicial.getText().toString())));


        despacho.setContadorInicialDestino(Utils.formatDoubleNumber(Double.parseDouble(editTextDestinoContadorInicial.getText().toString())));
        despacho.setpITDestino(Utils.formatDoubleNumber(Double.parseDouble(editTextDestinoPorcentajeInicial.getText().toString())));


        despacho.setContadorFinalOrigen(Utils.formatDoubleNumber(Double.parseDouble(editTextOrigenContadorFinal.getText().toString())));
        despacho.setpFTOrigen(Utils.formatDoubleNumber(Double.parseDouble(editTextOrigenPorcentajeFinal.getText().toString())));

        despacho.setContadorFinalDestino(Utils.formatDoubleNumber(Double.parseDouble(editTextDestinoContadorFinal.getText().toString())));
        despacho.setpFTDestino(Utils.formatDoubleNumber(Double.parseDouble(editTextDestinoPorcentajeFinal.getText().toString())));

        Long estadoAc = despacho.save();
        if (estadoAc > 0) {
            Toast.makeText(this, "Despacho Actualizado", Toast.LENGTH_SHORT).show();
            startActivityReturn();
        }

    }

    private void startActivityReturn() {
        startActivity(new Intent(getApplicationContext(), StationOrderActivity.class));
        this.finish();
    }


    private void initViews() {

        setToolbar();
        setTextField();
        setValuesToText();
        textCantidadListener();


    }

    private void setValuesToText() {
        editTextOrigenContadorInicial.setText(despacho.getContadorInicialOrigen() + "");
        editTextOrigenPorcentajeInicial.setText(despacho.getpITOrigen() + "");


        editTextDestinoContadorInicial.setText(despacho.getContadorInicialDestino() + "");
        editTextDestinoPorcentajeInicial.setText(despacho.getpITDestino() + "");


        editTextOrigenContadorFinal.setText(despacho.getContadorFinalOrigen() + "");
        editTextOrigenPorcentajeFinal.setText(despacho.getpFTOrigen() + "");

        editTextDestinoContadorFinal.setText(despacho.getContadorFinalDestino() + "");
        editTextDestinoPorcentajeFinal.setText(despacho.getpFTDestino() + "");

        editTextCantidadDespachada.setText(despacho.getCantidadDespachada() + "");
    }

    private boolean validateText() {

        if (TextUtils.isEmpty(editTextOrigenContadorInicial.getText().toString())) {
            Toast.makeText(this, "Ingrese contador inicial origen", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(editTextOrigenPorcentajeInicial.getText().toString())) {
            Toast.makeText(this, "Ingrese porcentaje inicial origen", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(editTextDestinoContadorInicial.getText().toString())) {
            Toast.makeText(this, "Ingrese contador inicial destino", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(editTextDestinoPorcentajeInicial.getText().toString())) {
            Toast.makeText(this, "Ingrese porcentaje inicial destino", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(editTextOrigenContadorFinal.getText().toString())) {
            Toast.makeText(this, "Ingrese contador final origen", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(editTextOrigenPorcentajeFinal.getText().toString())) {
            Toast.makeText(this, "Ingrese pordentaje final origen", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(editTextDestinoContadorFinal.getText().toString())) {
            Toast.makeText(this, "Ingrese contador final destino", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(editTextDestinoPorcentajeFinal.getText().toString())) {
            Toast.makeText(this, "Ingrese porcentaje final destino", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(editTextCantidadDespachada.getText().toString())) {
            Toast.makeText(this, "Ingrese cantidad", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void setTextField() {
        //Unidad unidadMeTanque = Unidad.getUnidadProductobyUnidadMedidaId(almace);

        textViewDestinoCapacidad.setText(": " + Utils.formatDoublePrint(almacen.getCapacidadReal()) + " GL");
        textViewDestinoProgramado.setText(": " + Utils.formatDoublePrint(pedidoDetalle.getCantidad()) + " GL");
        textViewOrdenSugerencia.setText(": " + Utils.formatDoublePrint(getCapacidadSugerencia()) + " GL");

        textViewSerieNumero.setText(": " + serie.getCompVSerie() + "-" + despacho.getNumero());
        textViewTanque.setText(": " + almacen.getPlaca());
        textViewProducto.setText(": " + Producto.getNameProducto(almacen.getProductoId() + ""));
        textDespachoEstacion.setText(": " + establecimiento.getEstVDescripcion());
        text_unidad_medida.setText(": " + unidad.getDescripcion());

    }

    private String getNumeroDespacho() {
        String numeroDespacho = Despacho.findWithQuery(Despacho.class, Utils.getQueryForNumberDistPach(), null).get(Constants.CURRENT).getNumero();
        return numeroDespacho;
    }

    private double getCapacidadSugerencia() {
        double d = almacen.getCapacidadReal() - almacen.getStockPermanente();
        return d;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }

    private void setToolbar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) //
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }


}
