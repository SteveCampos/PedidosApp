package energigas.apps.systemstrategy.energigas.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.adapters.ConceptoAdapter;
import energigas.apps.systemstrategy.energigas.adapters.DespachoFacturaAdapter;
import energigas.apps.systemstrategy.energigas.apiRest.ManipuleData;
import energigas.apps.systemstrategy.energigas.entities.CajaComprobante;
import energigas.apps.systemstrategy.energigas.entities.CajaLiquidacion;
import energigas.apps.systemstrategy.energigas.entities.CajaMovimiento;
import energigas.apps.systemstrategy.energigas.entities.CajaPago;
import energigas.apps.systemstrategy.energigas.entities.Cliente;
import energigas.apps.systemstrategy.energigas.entities.ComprobanteVenta;
import energigas.apps.systemstrategy.energigas.entities.ComprobanteVentaDetalle;
import energigas.apps.systemstrategy.energigas.entities.Concepto;
import energigas.apps.systemstrategy.energigas.entities.Despacho;
import energigas.apps.systemstrategy.energigas.entities.Establecimiento;
import energigas.apps.systemstrategy.energigas.entities.Persona;
import energigas.apps.systemstrategy.energigas.entities.PlanPago;
import energigas.apps.systemstrategy.energigas.entities.PlanPagoDetalle;
import energigas.apps.systemstrategy.energigas.entities.ProductoUnidad;
import energigas.apps.systemstrategy.energigas.entities.Serie;
import energigas.apps.systemstrategy.energigas.entities.Usuario;
import energigas.apps.systemstrategy.energigas.fragments.DialogGeneral;
import energigas.apps.systemstrategy.energigas.interfaces.DialogGeneralListener;
import energigas.apps.systemstrategy.energigas.utils.Constants;
import energigas.apps.systemstrategy.energigas.utils.Log;
import energigas.apps.systemstrategy.energigas.utils.Session;
import energigas.apps.systemstrategy.energigas.utils.Utils;

public class SellActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "SellActivity";

    @BindView(R.id.spinnerTipoComprobante)
    Spinner spinnerTipoComprobante;

    @BindView(R.id.spinnerFormaPago)
    Spinner spinnerFormaPago;

    @BindView(R.id.spinnerTipoPago)
    Spinner spinnerTipoPago;

    @BindView(R.id.fab)
    FloatingActionButton fab;

    @BindView(R.id.recycler_despacho)
    RecyclerView recyclerView;

    Cliente cliente;
    Establecimiento establecimiento;

    /**
     * Entidades para generar la venta
     **/
    ComprobanteVenta comprobanteVenta;
    List<ComprobanteVentaDetalle> comprobanteVentaDetalles;
    Usuario usuario;
    CajaLiquidacion cajaLiquidacion;
    Serie serie;


    /**
     * Obtener Objectos
     **/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell);
        Toast.makeText(this, ""+Session.getCajaLiquidacion(this).getLiqId()+ "", Toast.LENGTH_SHORT).show();
        usuario = Usuario.find(Usuario.class, " usu_I_Usuario_Id = ? ", new String[]{Session.getSession(this).getUsuIUsuarioId() + ""}).get(Constants.CURRENT);
        establecimiento = Establecimiento.find(Establecimiento.class, " est_I_Establecimiento_Id = ?  ", new String[]{Session.getSessionEstablecimiento(this).getEstIEstablecimientoId() + ""}).get(Constants.CURRENT);
        cliente = Cliente.find(Cliente.class, " CLI_I_CLIENTE_ID = ? ", new String[]{establecimiento.getEstIClienteId() + ""}).get(Constants.CURRENT);
        Persona persona = Persona.find(Persona.class, " per_I_Persona_Id=? ", new String[]{cliente.getCliIPersonaId() + ""}).get(Constants.CURRENT);
        cliente.setPersona(persona);
        cajaLiquidacion = CajaLiquidacion.find(CajaLiquidacion.class, " liq_Id=? ", new String[]{Session.getCajaLiquidacion(this).getLiqId() + ""}).get(Constants.CURRENT);
        ButterKnife.bind(this);
        initViews();
        fab.setOnClickListener(this);

    }

    private void dialogConfirmarVenta() {


        DialogGeneral.isConfirm(this, "Atencion..!!!", "¿Esta seguro de guardar ?", new DialogGeneralListener() {
            @Override
            public void onSavePressed() {
                generarVenta();
            }

            @Override
            public void onCancelPressed() {

            }
        });
    }

    private void generarVenta() {


        serie = Serie.findWithQuery(Serie.class, Utils.getQueryForSerie(usuario.getUsuIUsuarioId(), Constants.TIPO_ID_DEVICE_CELULAR,getTipoComprobante().getIdConcepto()), null).get(Constants.CURRENT);


        /**Comprobante de venta**/
        comprobanteVenta = new ComprobanteVenta();
        comprobanteVenta.setCompId(0);
        comprobanteVenta.setSerie(serie.getCompVSerie());
        comprobanteVenta.setNumDoc("");
        comprobanteVenta.setFormaPagoId(getFormaPago().getIdConcepto());
        comprobanteVenta.setEstadoId(Constants.COMPROBANTE_CREADO);
        comprobanteVenta.setBaseImponible(0.0);
        comprobanteVenta.setIgv(0.0);
        comprobanteVenta.setTotal(0.0);
        comprobanteVenta.setTipoComprobanteId(getTipoComprobante().getIdConcepto());
        comprobanteVenta.setClienteId(cliente.getCliIClienteId());
        comprobanteVenta.setComIUsuarioId(usuario.getUsuIUsuarioId());
        comprobanteVenta.setAnulado(Constants.COMPROBANTE_NO_ANULADO);
        comprobanteVenta.setSaldo(0.0);
        comprobanteVenta.setLqId(cajaLiquidacion.getLiqId());
        comprobanteVenta.setTipoVentaId(Constants.TIPO_VENTA_NORMAL);
        comprobanteVenta.setEstablecimientoId(establecimiento.getEstIEstablecimientoId());
        comprobanteVenta.setExportado(Constants.NO_EXPORTADO);
        comprobanteVenta.setDocIdentidad(cliente.getPersona().getPerVDocIdentidad());
        comprobanteVenta.setValorResumen("");
        comprobanteVenta.setCliente("");
        comprobanteVenta.setDireccionCliente("");
        comprobanteVenta.setFechaCreacion(Utils.getDatePhone());
        comprobanteVenta.setPorImpuesto(0);
        /**Comprobante de venta detalle**/
        comprobanteVentaDetalles = getComprobanteVentaDetalleList(comprobanteVenta);
        CajaMovimiento cajaMovimiento = new CajaMovimiento(0, cajaLiquidacion.getLiqId(), 0, "", comprobanteVenta.getBaseImponible(), true, Utils.getDatePhone(), "", "", usuario.getUsuIUsuarioId(), Utils.getDatePhone(), "", 0);


        switch (getFormaPago().getDescripcion()) {

            case Constants.FORMA_PAGO_CREDITO:
                generarVentaCredito(cajaMovimiento);
                break;

            case Constants.FORMA_PAGO_CONTADO:
                generarVentaContado(cajaMovimiento);
                break;
        }





        Long compro = comprobanteVenta.save();
        Log.d(TAG, " comprobanteVenta " + compro);
        for (ComprobanteVentaDetalle comprobanteVentaDetalle : comprobanteVentaDetalles) {
            Long comproVenDeta = comprobanteVentaDetalle.save();
            Log.d(TAG, " ComprobanteVentaDetalle " + comproVenDeta);
        }


    }

    private void generarVentaCredito(CajaMovimiento cajaMovimiento) {

        PlanPago planPago = new PlanPago(0, comprobanteVenta.getCompId(), "", "", "", "", true, 0.0, usuario.getUsuIUsuarioId(), Utils.getDatePhone());
        PlanPagoDetalle planPagoDetalle = new PlanPagoDetalle(planPago.getPlanPaId(), 0, Utils.getDatePhone(), comprobanteVenta.getBaseImponible(), true, comprobanteVenta.getBaseImponible(), 0.0, 0.0, "", usuario.getUsuIUsuarioId(), Utils.getDatePhone(), cajaMovimiento.getCajMovId());
        Long insplanPago = planPago.save();
        Long insplanPagoDetalle = planPagoDetalle.save();
        Log.d(TAG, " PlanPago " + insplanPago);
        Log.d(TAG, " PlanPagoDetalle " + insplanPagoDetalle);
    }

    private void generarVentaContado(CajaMovimiento cajaMovimiento) {
        CajaComprobante cajaComprobante = new CajaComprobante(0, cajaMovimiento.getCajMovId(), comprobanteVenta.getCompId(), comprobanteVenta.getBaseImponible(), usuario.getUsuIUsuarioId(), Utils.getDatePhone());
        CajaPago cajaPago = new CajaPago(0, comprobanteVenta.getBaseImponible(), cajaMovimiento.getCajMovId(), usuario.getUsuIUsuarioId(), Utils.getDatePhone(), Constants.NO_EXPORTADO, getTipoPago().getIdConcepto(), false);
        Long cajaCom = cajaComprobante.save();
        Long cajaPag = cajaPago.save();
        Log.d(TAG, " CajaComprobante " + cajaCom);
        Log.d(TAG, " CajaPago " + cajaPag);
    }

    private List<ComprobanteVentaDetalle> getComprobanteVentaDetalleList(ComprobanteVenta comprobanteVenta) {
        List<ComprobanteVentaDetalle> comprobanteVentaDetalles = new ArrayList<>();
        List<Despacho> despachos = new ManipuleData().getDespachosToFactura(this);
        for (Despacho despacho : despachos) {
            ProductoUnidad productoUnidad = ProductoUnidad.find(ProductoUnidad.class, " pro_Id=? ", new String[]{despacho.getProId() + ""}).get(Constants.CURRENT);
            comprobanteVentaDetalles.add(new ComprobanteVentaDetalle(0, comprobanteVenta.getCompId(), despacho.getProId(), productoUnidad.getUnId(), despacho.getCantidadDespachada(), despacho.getPrecioUnitarioSIGV(), despacho.getPrecioUNitarioCIGV(), despacho.getCostoVenta(), despacho.getImporte(), usuario.getUsuIUsuarioId(), Utils.getDatePhone(), despacho.getDespachoId()));
        }
        return comprobanteVentaDetalles;
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

    private void toolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void initViews() {
        toolbar();
        initDespachos();
        initTipoComprobante();
        initFormaPago();
        initTipoPago();

    }

    private void initTipoComprobante() {

        List<Concepto> conceptoList = Concepto.find(Concepto.class, " OBJETO = ? ", new String[]{Constants.CONCEPTO_TIPO_COMPROBANTE_VENTA});
        Log.d(TAG, "SIZE: " + conceptoList.size());
        ConceptoAdapter conceptoArrayAdapter = new ConceptoAdapter(this, 0, conceptoList);
        spinnerTipoComprobante.setAdapter(conceptoArrayAdapter);

    }

    private void initFormaPago() {
        List<Concepto> conceptoList = Concepto.find(Concepto.class, " CONCEPTO = ? AND  OBJETO = ? ", new String[]{Constants.CONCEPTO_FORMA_PAGO, Constants.CONCEPTO_FORMA_PAGO_COMPROBANTE_VENTA});
        Log.d(TAG, "SIZE: " + conceptoList.size());
        ConceptoAdapter conceptoArrayAdapter = new ConceptoAdapter(this, 0, conceptoList);
        spinnerFormaPago.setAdapter(conceptoArrayAdapter);
    }

    private void initTipoPago() {
        List<Concepto> conceptoList = Concepto.find(Concepto.class, " CONCEPTO = ? AND  OBJETO = ? ", new String[]{Constants.CONCEPTO_TIPO_PAGO, Constants.CONCEPTO_CAJA_PAGO});
        Log.d(TAG, "SIZE: " + conceptoList.size());
        ConceptoAdapter conceptoArrayAdapter = new ConceptoAdapter(this, 0, conceptoList);
        spinnerTipoPago.setAdapter(conceptoArrayAdapter);
    }

    private void initDespachos() {
        List<Despacho> despachos = new ManipuleData().getDespachosToFactura(this);
        DespachoFacturaAdapter despachoFacturaAdapter = new DespachoFacturaAdapter(despachos, this);
        recyclerView.setAdapter(despachoFacturaAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    private Concepto getTipoComprobante() {
        return (Concepto) spinnerTipoComprobante.getSelectedItem();
    }

    private Concepto getFormaPago() {
        return (Concepto) spinnerFormaPago.getSelectedItem();
    }

    private Concepto getTipoPago() {
        return (Concepto) spinnerTipoPago.getSelectedItem();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab:
                dialogConfirmarVenta();
                break;
        }
    }
}
