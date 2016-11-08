package energigas.apps.systemstrategy.energigas.activities;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import energigas.apps.systemstrategy.energigas.LocationVehiculeListener;
import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.asyntask.ConectarDispositivoAsyn;
import energigas.apps.systemstrategy.energigas.asyntask.ExportTask;
import energigas.apps.systemstrategy.energigas.entities.Almacen;
import energigas.apps.systemstrategy.energigas.entities.CajaLiquidacion;
import energigas.apps.systemstrategy.energigas.entities.Despacho;
import energigas.apps.systemstrategy.energigas.entities.Establecimiento;
import energigas.apps.systemstrategy.energigas.entities.Pedido;
import energigas.apps.systemstrategy.energigas.entities.PedidoDetalle;
import energigas.apps.systemstrategy.energigas.entities.Serie;
import energigas.apps.systemstrategy.energigas.entities.SyncEstado;
import energigas.apps.systemstrategy.energigas.entities.Usuario;
import energigas.apps.systemstrategy.energigas.fragments.DialogGeneral;
import energigas.apps.systemstrategy.energigas.interfaces.BluetoothConnectionListener;
import energigas.apps.systemstrategy.energigas.interfaces.DialogGeneralListener;
import energigas.apps.systemstrategy.energigas.interfaces.ExportObjectsListener;
import energigas.apps.systemstrategy.energigas.interfaces.OnLocationListener;
import energigas.apps.systemstrategy.energigas.utils.Constants;
import energigas.apps.systemstrategy.energigas.utils.Session;
import energigas.apps.systemstrategy.energigas.utils.Utils;


import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.location.Location;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.orm.SugarTransactionHelper;

import java.util.ArrayList;
import java.util.List;

public class DespachoActivity extends AppCompatActivity implements BluetoothConnectionListener, SugarTransactionHelper.Callback, OnLocationListener, ExportObjectsListener {

    private Boolean isConnected = false;
    private Boolean isFabOpen = false;
    /**
     * --1--
     */
    @BindView(R.id.fabTanqueOrigen)
    FloatingActionButton actionButtonTanqueOrigen;
    @BindView(R.id.fabOrigenManual)
    FloatingActionButton actionButtonOrigenManual;
    @BindView(R.id.fabOrigenRemoto)
    FloatingActionButton actionButtonOrigenRemoto;
    @BindView(R.id.fabOrigenDisconect)
    FloatingActionButton actionButtonOrigenDesconectar;


    @BindView(R.id.fabTanqueDestino)
    FloatingActionButton actionButtonTanqueDestino;
    @BindView(R.id.fabDestinoManual)
    FloatingActionButton actionButtonDestinoManual;
    @BindView(R.id.fabDestinoRemoto)
    FloatingActionButton actionButtonDestinoRemoto;
    @BindView(R.id.fabDestinoDisconect)
    FloatingActionButton actionButtonDestinoDesconectar;

    @BindView(R.id.imgIniciarDespacho)
    ImageView imageViewInicarDespacho;


    @BindView(R.id.progressdespacho)
    ProgressBar progressBarDespacho;
    /**
     * --3--
     */
    @BindView(R.id.fabTanqueOrigen2)
    FloatingActionButton actionButtonTanqueOrigen2;
    @BindView(R.id.fabOrigenManual2)
    FloatingActionButton fabOrigenManual2;
    @BindView(R.id.fabOrigenRemoto2)
    FloatingActionButton fabOrigenRemoto2;
    @BindView(R.id.fabOrigenDisconect2)
    FloatingActionButton fabOrigenDesconectar2;

    /***
     * 4
     **/

    @BindView(R.id.fabTanqueDestino2)
    FloatingActionButton fabTanqueDestino2;
    @BindView(R.id.fabDestinoManual2)
    FloatingActionButton fabDestinoManual2;
    @BindView(R.id.fabDestinoRemoto2)
    FloatingActionButton fabDestinoRemoto2;
    @BindView(R.id.fabDestinoDisconect2)
    FloatingActionButton fabDestinoDesconectar2;


    @BindView(R.id.toolbar)
    Toolbar toolbar;


    @BindView(R.id.editTextContadorInicial)
    EditText editTextContadorInicial;
    @BindView(R.id.editTextPorcentajeIncial)
    EditText editTextPorcentajeInicial;


    @BindView(R.id.editTextDestinoContIncial)
    EditText editTextDestinoContadorInicial;
    @BindView(R.id.editDestinoPorcentajeInicial)
    EditText editTextDestinoPorcentajeInicial;


    @BindView(R.id.editOrigen2CF)
    EditText editOrigen2CF;
    @BindView(R.id.editOrigen2PF)
    EditText editOrigen2PF;

    @BindView(R.id.editDestino2CF)
    EditText editDestino2CF;
    @BindView(R.id.editDestino2PF)
    EditText editDestino2PF;

    @BindView(R.id.editCantidadDespachada)
    EditText editTextCantidadDespachada;

    @BindView(R.id.tanq_destino_orden_sugerencia)
    TextView textViewOrdenSugerencia;

    @BindView(R.id.text_despacho_serie_numero)
    TextView textViewSerieNumero;


    private int typeWidgets = 1;

    private Despacho despacho;
    private PedidoDetalle pedidoDetalle;
    private Establecimiento establecimiento;
    private Almacen almacen;
    private Usuario usuario;
    private Pedido pedido;
    private Serie serie;
    private CajaLiquidacion cajaLiquidacion;
    private Almacen almacenDistribuidor;


    @BindView(R.id.btnGuardar)
    Button buttonGuardar;

    /**
     * Animation
     **/
    private Animation fab_open, fab_close, rotate_forward, rotate_backward;
    private LocationVehiculeListener locationVehiculeListener;
    private Location latAndLong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_despacho);
        ButterKnife.bind(this);
        locationVehiculeListener = new LocationVehiculeListener(this);
        cajaLiquidacion = CajaLiquidacion.find(CajaLiquidacion.class, " liq_Id = ? ", new String[]{Session.getCajaLiquidacion(this).getLiqId() + ""}).get(Constants.CURRENT);
        pedido = Pedido.find(Pedido.class, " pe_Id = ? ", new String[]{Session.getPedido(this).getPeId() + ""}).get(Constants.CURRENT);
        pedidoDetalle = PedidoDetalle.find(PedidoDetalle.class, " pe_Id = ? ", new String[]{Session.getPedido(this).getPeId() + ""}).get(Constants.CURRENT);
        establecimiento = Establecimiento.find(Establecimiento.class, " est_I_Establecimiento_Id = ?  ", new String[]{Session.getSessionEstablecimiento(this).getEstIEstablecimientoId() + ""}).get(Constants.CURRENT);
        almacen = Almacen.find(Almacen.class, " alm_Id = ?  ", new String[]{Session.getAlmacen(this).getAlmId() + ""}).get(Constants.CURRENT);
        usuario = Usuario.find(Usuario.class, " usu_I_Usuario_Id = ? ", new String[]{Session.getSession(this).getUsuIUsuarioId() + ""}).get(Constants.CURRENT);
        serie = Serie.findWithQuery(Serie.class, Utils.getQueryForSerie(usuario.getUsuIUsuarioId(), Constants.TIPO_ID_DEVICE_CELULAR, Constants.TIPO_ID_COMPROBANTE_DESPACHO), null).get(Constants.CURRENT);
        almacenDistribuidor = Almacen.findWithQuery(Almacen.class, Utils.getQuerDespachoVehiculo(usuario.getUsuIUsuarioId()), new String[]{}).get(Constants.CURRENT);
        intanceAnimation();
        toolbar();
        setTextField();

    }

    private void toolbar() {

        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @OnClick(R.id.fabOrigenDisconect)
    public void origenDisconect(View view) {
        isConnected = false;
    }

    @OnClick(R.id.fabDestinoDisconect)
    public void fabDestinoDisconect(View view) {
        isConnected = false;
    }


    @OnClick(R.id.fabOrigenDisconect2)
    public void fabOrigenDisconect2(View view) {
        isConnected = false;
    }


    @OnClick(R.id.fabDestinoDisconect2)
    public void fabDestinoDisconect2(View view) {
        isConnected = false;
    }

    private void setTextField() {

        textViewOrdenSugerencia.setText(": " + getCapacidadSugerencia() + "");
        textViewSerieNumero.setText(": " + serie.getCompVSerie() + "-" + Utils.completaZeros(getNumeroDespacho(), serie.getParametro()));

    }

    private double getCapacidadSugerencia() {
        double d = almacen.getCapacidadReal() - almacen.getStockPermanente();
        return d;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_menu_accountsummary, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

        new DialogGeneral(DespachoActivity.this).setCancelable(false).setMessages("Retroceder", "¿Seguro de retroceder?").setTextButtons("SI", "NO").showDialog(new DialogGeneralListener() {
            @Override
            public void onSavePressed() {
                DespachoActivity.super.onBackPressed();
            }

            @Override
            public void onCancelPressed() {

            }
        });
    }

    @OnClick(R.id.btnGuardar)
    public void clickBtnGuardar() {

        new DialogGeneral(DespachoActivity.this).setTextButtons("GUARDAR", "CANCELAR").setMessages("Atencion", "¿Seguro de guardar?").setCancelable(true).showDialog(new DialogGeneralListener() {
            @Override
            public void onSavePressed() {
                if (validateField()) {
                    SugarTransactionHelper.doInTransaction(DespachoActivity.this);
                } else {
                    Toast.makeText(DespachoActivity.this, "Datos vacios", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelPressed() {

            }
        });

    }

    private boolean validateField() {

        if (editTextCantidadDespachada.getText().toString().length() < 0 ||
                editTextContadorInicial.getText().toString().length() < 0 ||
                editTextPorcentajeInicial.getText().toString().length() < 0 ||
                editTextDestinoContadorInicial.getText().toString().length() < 0 ||
                editTextDestinoPorcentajeInicial.getText().toString().length() < 0 ||
                editOrigen2CF.getText().toString().length() < 0 ||
                editOrigen2PF.getText().toString().length() < 0 ||
                editDestino2CF.getText().toString().length() < 0 ||
                editDestino2PF.getText().toString().length() < 0) {

            return false;
        }

        return true;
    }

    private String getNumeroDespacho() {
        String numeroDespacho = Despacho.findWithQuery(Despacho.class, Utils.getQueryForNumberDistPach(), null).get(Constants.CURRENT).getNumero();
        return numeroDespacho;
    }

    private void saveDespacho() {

        if (latAndLong == null) {
            errorInTransaction("Update no Location");
            return;
        }

        almacenDistribuidor = Almacen.find(Almacen.class, "", new String[]{}).get(Constants.CURRENT);


        despacho = new Despacho(
                Long.parseLong(getNumeroDespacho()),
                pedido.getPeId(),
                pedidoDetalle.getPdId(),
                pedido.getClienteId(),
                establecimiento.getEstIEstablecimientoId(),
                almacen.getAlmId(),
                usuario.getUsuIUsuarioId(),
                almacen.getPlaca(),
                Double.parseDouble(editTextContadorInicial.getText().toString()),
                Double.parseDouble(editOrigen2CF.getText().toString()),
                Double.parseDouble(editTextCantidadDespachada.getText().toString()),
                "17:35",
                "18:00",
                Utils.getDatePhone(),
                pedidoDetalle.getProductoId(),
                pedidoDetalle.getUnidadId(),
                Double.parseDouble(editTextPorcentajeInicial.getText().toString()),
                Double.parseDouble(editOrigen2PF.getText().toString()),
                latAndLong.getLatitude() + "",
                latAndLong.getLongitude() + "",
                almacenDistribuidor.getAlmId(),
                serie.getCompVSerie(),
                Utils.completaZeros(getNumeroDespacho(), serie.getParametro()),
                Utils.getDatePhone(),
                usuario.getUsuIUsuarioId(),
                Constants.DESPACHO_CREADO,
                almacenDistribuidor.getVehiculoId(),
                pedido.getGuiaRemision(),
                cajaLiquidacion.getLiqId(),
                pedidoDetalle.getPrecio(),
                pedidoDetalle.getPrecioUnitario(),
                pedido.getPorImpuesto(),
                pedidoDetalle.getCostoVenta(),
                pedidoDetalle.getImporte(),
                Double.parseDouble(editTextDestinoContadorInicial.getText().toString()),
                Double.parseDouble(editTextPorcentajeInicial.getText().toString()),
                Double.parseDouble(editTextDestinoPorcentajeInicial.getText().toString()),
                Double.parseDouble(editDestino2PF.getText().toString()),
                -1
        );


        despacho.save();


        double cantidadStock = almacenDistribuidor.getStockActual() - Double.parseDouble(editTextCantidadDespachada.getText().toString());
        almacenDistribuidor.setStockActual(cantidadStock);
        almacenDistribuidor.save();
        new SyncEstado(0, Utils.separteUpperCase(Despacho.class.getSimpleName()), Integer.parseInt(despacho.getId() + ""), Constants.S_CREADO).save();
        new ExportTask(this,this).execute(Constants.TABLA_DESPACHO,Constants.S_CREADO);
        Session.saveDespacho(this, despacho);
        startActivity(new Intent(this, PrintDispatch.class));
        this.finish();

    }

    private void isEnableEditText(List<EditText> editTexts, boolean estado) {

        for (EditText editText : editTexts) {
            editText.setEnabled(estado);
        }
    }

    private void intanceAnimation() {

        fab_open = AnimationUtils.loadAnimation(this, R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(this, R.anim.fab_close);
        rotate_backward = AnimationUtils.loadAnimation(this, R.anim.rotate_backward);
        rotate_forward = AnimationUtils.loadAnimation(this, R.anim.rotate_forward);
    }

    private void setClickableOrigen(FloatingActionButton[] fabss, boolean estado) {
        for (int i = 0; i < fabss.length; i++) {
            fabss[i].setEnabled(estado);
        }
    }

    /**
     * Primera Lectura
     **/

    @OnClick(R.id.fabTanqueOrigen)
    public void clickTanqueOrigen(View view) {

        actionButtonTanqueOrigen.setImageResource(R.drawable.ic_sync);
        actionButtonTanqueOrigen.startAnimation(rotate_forward);
        if (!isConnected) {
            typeWidgets = 1;
            comenzarConexion();
        } else {
            setAnimationOrigen(actionButtonTanqueOrigen, actionButtonOrigenManual, actionButtonOrigenRemoto, actionButtonOrigenDesconectar);
        }


    }

    @OnClick(R.id.fabOrigenManual)
    public void clickTanqueOrigenManual(View view) {
        List<EditText> editTexts = new ArrayList<EditText>();
        editTexts.add(editTextContadorInicial);
        editTexts.add(editTextPorcentajeInicial);
        isEnableEditText(editTexts, true);
    }

    @OnClick(R.id.fabOrigenRemoto)
    public void clickTanqueOrigenRemoto(View view) {
        List<EditText> editTexts = new ArrayList<EditText>();
        editTexts.add(editTextContadorInicial);
        editTexts.add(editTextPorcentajeInicial);
        isEnableEditText(editTexts, false);
        setValueTanqueOrigen(editTexts, "3000", "100");
    }
    /** ----end  ---- primera lectura**/


    /**
     * Segunda Lectura
     **/

    @OnClick(R.id.fabTanqueDestino)
    public void clickTanqueDestino(View view) {


        actionButtonTanqueDestino.setImageResource(R.drawable.ic_sync);
        actionButtonTanqueDestino.startAnimation(rotate_forward);
        if (!isConnected) {
            typeWidgets = 2;
            comenzarConexion();
        } else {
            setAnimationOrigen(actionButtonTanqueDestino, actionButtonDestinoManual, actionButtonDestinoRemoto, actionButtonDestinoDesconectar);
        }

    }

    @OnClick(R.id.fabDestinoManual)
    public void clickTanqueDestinoManual(View view) {
        List<EditText> editTexts = new ArrayList<EditText>();
        editTexts.add(editTextDestinoContadorInicial);
        editTexts.add(editTextDestinoPorcentajeInicial);
        isEnableEditText(editTexts, true);
    }

    @OnClick(R.id.fabDestinoRemoto)
    public void clickTanqueDestinoRemoto(View view) {
        List<EditText> editTexts = new ArrayList<EditText>();
        editTexts.add(editTextDestinoContadorInicial);
        editTexts.add(editTextDestinoPorcentajeInicial);
        isEnableEditText(editTexts, false);
        setValueTanqueOrigen(editTexts, "1000", "50");
    }


    /**
     * ----end  ---- Segunda lectura
     **/


    /**
     * Tercera Lectura
     **/

    @OnClick(R.id.fabTanqueOrigen2)
    public void clickTanqueOrigen2(View view) {


        actionButtonTanqueOrigen2.setImageResource(R.drawable.ic_sync);
        actionButtonTanqueOrigen2.startAnimation(rotate_forward);
        if (!isConnected) {
            typeWidgets = 3;
            comenzarConexion();
        } else {
            setAnimationOrigen(actionButtonTanqueOrigen2, fabOrigenManual2, fabOrigenRemoto2, fabOrigenDesconectar2);
        }

    }

    @OnClick(R.id.fabOrigenManual2)
    public void clickTanqueOrigenManual2(View view) {
        List<EditText> editTexts = new ArrayList<EditText>();
        editTexts.add(editOrigen2CF);
        editTexts.add(editOrigen2PF);
        isEnableEditText(editTexts, true);
    }

    @OnClick(R.id.fabOrigenRemoto2)
    public void clickTanqueOrigenRemoto2(View view) {
        List<EditText> editTexts = new ArrayList<EditText>();
        editTexts.add(editOrigen2CF);
        editTexts.add(editOrigen2PF);
        isEnableEditText(editTexts, false);
        setValueTanqueOrigen(editTexts, "2000", "60");
    }


    /**
     * ----end  ---- Tercera lectura
     **/

    /**
     * Cuarta Lectura
     **/

    @OnClick(R.id.fabTanqueDestino2)
    public void clickTanqueDestino2(View view) {


        fabTanqueDestino2.setImageResource(R.drawable.ic_sync);
        fabTanqueDestino2.startAnimation(rotate_forward);
        if (!isConnected) {
            typeWidgets = 4;
            comenzarConexion();
        } else {
            setAnimationOrigen(fabTanqueDestino2, fabDestinoManual2, fabDestinoRemoto2, fabDestinoDesconectar2);
        }

    }

    @OnClick(R.id.fabDestinoManual2)
    public void clickTanqueDestinoManual2(View view) {
        List<EditText> editTexts = new ArrayList<EditText>();
        editTexts.add(editDestino2CF);
        editTexts.add(editDestino2PF);
        isEnableEditText(editTexts, true);
    }

    @OnClick(R.id.fabDestinoRemoto2)
    public void clickTanqueDestinoRemoto2(View view) {
        List<EditText> editTexts = new ArrayList<EditText>();
        editTexts.add(editDestino2CF);
        editTexts.add(editDestino2PF);
        isEnableEditText(editTexts, false);
        setValueTanqueOrigen(editTexts, "3000", "100");
    }


    /**
     * ----end  ---- Segunda lectura
     **/


    private void setValueTanqueOrigen(List<EditText> editexts, String contadorInicial, String porcentajeInicial) {
        editexts.get(0).setText(contadorInicial);
        editexts.get(1).setText(porcentajeInicial);
    }


    private void setAnimationOrigen(FloatingActionButton tanque, FloatingActionButton manual, FloatingActionButton remoto, FloatingActionButton desconectar) {

        FloatingActionButton[] fabs = new FloatingActionButton[]{manual, remoto, desconectar};

        if (isConnected) {


            if (isFabOpen) {

                manual.startAnimation(fab_close);
                remoto.startAnimation(fab_close);
                desconectar.startAnimation(fab_close);
                isFabOpen = false;
                setClickableOrigen(fabs, false);
                affterAnimationConnected(tanque);
            } else {

                manual.startAnimation(fab_open);
                remoto.startAnimation(fab_open);
                desconectar.startAnimation(fab_open);
                isFabOpen = true;
                setClickableOrigen(fabs, true);
                affterAnimationConnected(tanque);
            }


        } else {
            affterAnimationError(tanque);


        }

    }

    private void comenzarConexion() {
        new ConectarDispositivoAsyn(this).execute();
    }

    private void affterAnimationError(FloatingActionButton tanque) {

        tanque.setImageResource(R.drawable.ic_wireless_signal);
        tanque.startAnimation(rotate_backward);
        tanque.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.colorAccent)));
    }

    private void affterAnimationConnected(FloatingActionButton tanque) {

        tanque.setImageResource(R.drawable.ic_wireless_signal);
        tanque.startAnimation(rotate_backward);
        tanque.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.greem_background_item)));
    }


    @Override
    public void onConnected(boolean connected) {
        isConnected = connected;


        switch (typeWidgets) {
            case 1:
                setAnimationOrigen(actionButtonTanqueOrigen, actionButtonOrigenManual, actionButtonOrigenRemoto, actionButtonOrigenDesconectar);
                break;
            case 2:
                setAnimationOrigen(actionButtonTanqueDestino, actionButtonDestinoManual, actionButtonDestinoRemoto, actionButtonDestinoDesconectar);
                break;
            case 3:
                setAnimationOrigen(actionButtonTanqueOrigen2, fabOrigenManual2, fabOrigenRemoto2, fabOrigenDesconectar2);
                break;
            case 4:
                setAnimationOrigen(fabTanqueDestino2, fabDestinoManual2, fabDestinoRemoto2, fabDestinoDesconectar2);
                break;
        }

    }

    @Override
    public void onRead(byte[] bytes) {

    }

    @Override
    public void manipulateInTransaction() {
        saveDespacho();

    }

    @Override
    public void errorInTransaction(String error) {
        Toast.makeText(DespachoActivity.this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setLatAndLong(Location latAndLong) {
        this.latAndLong = latAndLong;
    }

    @Override
    public Context getContextActivity() {
        return DespachoActivity.this;
    }




    @Override
    public void onLoadSuccess(String message) {
       // Toast.makeText(DespachoActivity.this,message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoadError(String message) {
       // Toast.makeText(DespachoActivity.this,message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoadErrorProcedure(String message) {
       // Toast.makeText(DespachoActivity.this,message,Toast.LENGTH_SHORT).show();
    }
}
