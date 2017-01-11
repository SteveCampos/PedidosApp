package energigas.apps.systemstrategy.energigas.activities;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.content.res.Resources;

import com.google.android.gms.vision.text.Text;
import com.sewoo.port.android.BluetoothPort;
import com.sewoo.request.android.RequestHandler;

import java.io.IOException;
import java.util.Vector;

import butterknife.BindView;
import butterknife.ButterKnife;
import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.adapters.FABScrollBehavior;
import energigas.apps.systemstrategy.energigas.entities.Almacen;
import energigas.apps.systemstrategy.energigas.entities.CajaLiquidacion;
import energigas.apps.systemstrategy.energigas.entities.Concepto;
import energigas.apps.systemstrategy.energigas.entities.DEEntidad;
import energigas.apps.systemstrategy.energigas.entities.DatosEmpresa;
import energigas.apps.systemstrategy.energigas.entities.OrdenCargo;
import energigas.apps.systemstrategy.energigas.entities.Persona;
import energigas.apps.systemstrategy.energigas.entities.Producto;
import energigas.apps.systemstrategy.energigas.entities.Proveedor;
import energigas.apps.systemstrategy.energigas.entities.Unidad;
import energigas.apps.systemstrategy.energigas.interfaces.OrdenCargoListener;
import energigas.apps.systemstrategy.energigas.printingsheets.SheetsPrintDispatch;
import energigas.apps.systemstrategy.energigas.utils.Constants;
import energigas.apps.systemstrategy.energigas.utils.Session;

/**
 * Created by CCIE on 04/01/2017.
 */

public class PrintOrdenCarga extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "PrintOrdenCarga" ;
    private Boolean isFabOpen = false;
    private BluetoothPort bp;
    private BluetoothAdapter mBluetoothAdapter;
    private static final int REQUEST_ENABLE_BT = 2;
    private Vector<BluetoothDevice> remoteDevices;
    private Thread hThread;
    private boolean connected;

    private String MACADDRES = "00:12:6F:36:6B:90";

    @BindView(R.id.txt_tipo_carga_orden)
    TextView tipoCarga;
    @BindView(R.id.txt_info_empresa)
    TextView txt_info_empresa;
    @BindView(R.id.txt_head_orden_carga)
    TextView txt_head_orden_carga;
    @BindView(R.id.et_resp_ordencarga_body1)
    TextView txt_body1_orden_carga;
    @BindView(R.id.et_agente_order_carga)
    TextView txt_agente_order_carga;
    @BindView(R.id.fabDisconec)
    FloatingActionButton floatingDisconect;
    @BindView(R.id.fabPrint)
    FloatingActionButton floatingPrint;
    @BindView(R.id.fab)
    FloatingActionButton floatingActionButton;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.Lineer_Orden_Compra)
    LinearLayout linearLayoutCompra ;
    @BindView(R.id.Lineer_Orden_TrasCiego)
    LinearLayout linearLayoutTrasciego ;
    @BindView(R.id.txt_head_orden_carga_trasciego)
    TextView textViewTrasCiego;
    @BindView(R.id.et_resp_ordencarga_body1_trasciego)
    TextView textViewResTrasCiego;




    /*Tables*/
    private DatosEmpresa datosEmpresa;
    private OrdenCargo mOrdenCargo;
    private CajaLiquidacion mCajaLiquidacion;
    private Almacen mAlmacen;

    private Proveedor mProveedor;
    private Producto mProducto;
    private Unidad mUnidad;
    private Concepto mConceptoTipoCarga;
    private Concepto mConceptoTipoOrigen;
    private Persona mAgente;
    private Resources resources;
    //private LinearLayout Lineer_Orden_Compra;

    private Animation fab_open, fab_close, rotate_forward, rotate_backward;

    String titleTrasCiego= "";
    String idOrdeCarga="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print_orden_carga);
        resources = getResources();  //ordeCargaId
        idOrdeCarga = getIntent().getStringExtra("ORDECARGAID");
        mOrdenCargo = OrdenCargo.find(OrdenCargo.class, " orde_Carga_Id= ? ", new String[]{ idOrdeCarga+ ""}).get(Constants.CURRENT);

     // mOrdenCargo = OrdenCargo.getOrdenCargo(Session.getOrdenCargo(this)+ "");

        mConceptoTipoCarga = Concepto.getConcepto(mOrdenCargo.getTipoCargaId() + "");
       // mConcepto = Concepto.find(Concepto.class, " id_Concepto= ? ", new String[]{ mOrdenCargo.getTipoOrigenId()+ ""}).get(Constants.CURRENT);
       // mProveedor = Proveedor.getProveedorById(mOrdenCargo.getProveedorId());
        mProducto = Producto.getProductoById(mOrdenCargo.getProId()+"");
        mUnidad = Unidad.getUnidadProductobyUnidadMedidaId(mOrdenCargo.getUnIdComprobante()+"");
        mCajaLiquidacion = energigas.apps.systemstrategy.energigas.entities.CajaLiquidacion.getCajaLiquidacion(Session.getCajaLiquidacion(this).getLiqId() + "");
        mAlmacen = Almacen.find(Almacen.class," alm_Id = ?", new String[]{mCajaLiquidacion.getAlmId()+""}).get(Constants.CURRENT);
        mAgente = Persona.findWithQuery(Persona.class, "SELECT P.* FROM PERSONA P, USUARIO U WHERE P.PER_I_PERSONA_ID = U.USU_I_PERSONA_ID AND U.USU_I_USUARIO_ID = " + Session.getSession(this).getUsuIUsuarioId() + " ;", null).get(Constants.CURRENT);
        ButterKnife.bind(this);

        fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);
        rotate_backward = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_backward);
        rotate_forward = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_forward);
        bluetoothSetup();
        setSupportActionBar(toolbar);
        floatingActionButton.setOnClickListener(this);
        floatingPrint.setOnClickListener(this);
        floatingDisconect.setOnClickListener(this);


        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


       /*Datos Empresa*/
        DEEntidad deEntidad = mCajaLiquidacion.getEntidad();
        datosEmpresa = new DatosEmpresa(deEntidad);
//        /*Tipo de Carga*/
        switch (mConceptoTipoCarga.getDescripcion().toLowerCase()) {

            case "compra":
              //  mConcepto = Concepto.getConcepto(mOrdenCargo.getTipoCargaId() + "");
                mProveedor = Proveedor.getProveedorById(mOrdenCargo.getProveedorId());
                tipoCarga.setText(mConceptoTipoCarga.getDescripcion());
                linearLayoutCompra.setVisibility(View.VISIBLE);
                linearLayoutTrasciego.setVisibility(View.GONE);
                txt_head_orden_carga.setVisibility(View.VISIBLE);
                viewCompra();
                break;
            case "trasciego":
                tipoCarga.setText(mConceptoTipoCarga.getDescripcion()+"");
                titleTrasCiego=mConceptoTipoCarga.getDescripcion();
                mConceptoTipoOrigen = Concepto.find(Concepto.class, " id_Concepto= ? ", new String[]{ mOrdenCargo.getTipoOrigenId()+ ""}).get(Constants.CURRENT);
                linearLayoutCompra.setVisibility(View.GONE);
                linearLayoutTrasciego.setVisibility(View.VISIBLE);
                textViewTrasCiego.setVisibility(View.VISIBLE);
                viewTrasCiego();
                break;
            default:
                Log.d(TAG, " tipoCargaSelectedItemListener onItemSelected default");
                break;
        }

        viewTextCabecera();
        txt_agente_order_carga.setText(mAgente.getPerVNombres()+" "+mAgente.getPerVApellidoPaterno()+" "+mAgente.getPerVApellidoMaterno());
    }



    private void viewTextCabecera() {
      //  txttitle.setText(datosEmpresa.getEntidad().getRazonSocial());
        String tetextCabeceraxt = String.format(resources.getString(R.string.print_orden_carga_empresa), datosEmpresa.getEntidad().getDireccionFiscal(), datosEmpresa.getDistrito() + ", " + datosEmpresa.getProvincia() + " \n " + datosEmpresa.getDepartamento(), "RUC: " + datosEmpresa.getEntidad().getrUC(), "Telf: " + datosEmpresa.getEntidad().getTelefono());
        txt_info_empresa.setText(tetextCabeceraxt);
    }


    private void viewCompra(){
        String tetextInfoOrdenCarga = String.format(resources.getString(R.string.print_orden_carga),mProveedor.getPersona().getNombreComercial()+"",mProveedor.getPersona().getPerVDocIdentidad()+"",
                mOrdenCargo.getNroComprobante(),mOrdenCargo.getNroGuia(),mOrdenCargo.getFechaCreacion(),mOrdenCargo.getFechaAccion());
        txt_head_orden_carga.setText(tetextInfoOrdenCarga);

        String tetextbody1ordencarga = String.format(resources.getString(R.string.print_resp_orden_carga_body1),mUnidad.getDescripcion(),mOrdenCargo.getCantidadDoc()+"",mOrdenCargo.getFactorConversion()+"",
                mOrdenCargo.getDensidad()+"",mOrdenCargo.getPrecio()+"");
        txt_body1_orden_carga.setText(tetextbody1ordencarga);

    }

    private void viewTrasCiego(){
        String tetextInforOrdenCarga = String.format(resources.getString(R.string.print_orden_carga_trasciego),mConceptoTipoOrigen.getDescripcion(),mProducto.getDescripcion(),mOrdenCargo.getNroGuia()
                ,mOrdenCargo.getFechaCreacion(),mOrdenCargo.getFechaAccion());
        textViewTrasCiego.setText(tetextInforOrdenCarga);

        String tetextrespordencarga = String.format(resources.getString(R.string.print_resp_orden_carga_body1_trasciego),mOrdenCargo.getCantidadDoc()+"",mUnidad.getDescripcion(),mOrdenCargo.getFactorConversion()+""
                ,mOrdenCargo.getDensidad()+"",mOrdenCargo.getPrecio()+"");

        textViewResTrasCiego.setText(tetextrespordencarga);
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
        super.onBackPressed();
        disconnectPrinter();
    }


    private void disconnectPrinter() {
        if (connected) {
            btDisconn();
        } else {

        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab:

                floatingActionButton.setImageResource(R.drawable.ic_sync);
                floatingActionButton.startAnimation(AnimationUtils.loadAnimation(PrintOrdenCarga.this, R.anim.rotate_forward));
                 bluetoothConnect();
                break;
            case R.id.fabPrint:
                SheetsPrintDispatch printOrdenCarga = new SheetsPrintDispatch();
              /*  if (mConcepto.getDescripcion().toLowerCase() =="compra"){
                   // printOrdenCarga.printCompra(mOrdenCargo,mConcepto,mProveedor,mProducto,mUnidad,mCajaLiquidacion,mAlmacen,datosEmpresa,mAgente);
                    Log.d(TAG,"getDescripcion: "+mConcepto.getDescripcion());
                }else {
                    String tipoOrigen = "trasciego";
                    Log.d(TAG,"getDescripcion: "+mConcepto.getDescripcion());
                   // printOrdenCarga.printTrasciego(mOrdenCargo,tipoOrigen,mConcepto,mProducto,mUnidad,datosEmpresa,mAgente);
                }*/
                switch (mConceptoTipoCarga.getDescripcion().toLowerCase()) {
                    case "compra":
                        Log.d(TAG,"mConceptoTipoCarga: "+ mConceptoTipoCarga.getDescripcion()+ " TIPOCARGAID:"+mConceptoTipoCarga.getIdConcepto());
                        printOrdenCarga.printCompra(mOrdenCargo,mConceptoTipoCarga,mProveedor,mProducto,mUnidad,mCajaLiquidacion,mAlmacen,datosEmpresa,mAgente);
                        break;
                    case "trasciego":
                        String tipoOrigen = mConceptoTipoOrigen.getDescripcion();
                        printOrdenCarga.printTrasciego(mOrdenCargo,tipoOrigen,mConceptoTipoCarga,mProducto,mUnidad,datosEmpresa,mAgente);
                        Log.d(TAG,"mConceptoTipoCarga: "+ mConceptoTipoCarga.getDescripcion()+"   /  mConceptoTipoOrigen: "+ mConceptoTipoOrigen.getDescripcion()+" TIPOCARGAID:"+mConceptoTipoCarga.getIdConcepto());
                        break;
                    default:
                        Log.d(TAG, " tipoCargaSelectedItemListener onItemSelected default");
                        break;

                }
                //printOrdenCarga.printOrdencarga(mOrdenCargo,mConcepto,mProveedor,mProducto,mUnidad,mCajaLiquidacion,mAlmacen,datosEmpresa,mAgente);
                break;
            case R.id.fabDisconec:
                floatingPrint.startAnimation(fab_close);
                floatingDisconect.startAnimation(fab_close);

                floatingPrint.setClickable(false);
                floatingDisconect.setClickable(false);
                isFabOpen = false;
                btDisconn();
                break;

        }
    }

    private void bluetoothSetup() {
        // Initialize
        clearBtDevData();
        bp = BluetoothPort.getInstance();
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            // Device does not support Bluetooth
        }
        if (!mBluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);

            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }
    }
    private void clearBtDevData() {
        remoteDevices = new Vector<BluetoothDevice>();
    }
    private void bluetoothConnect() {

        CoordinatorLayout.LayoutParams pDes = (CoordinatorLayout.LayoutParams) floatingDisconect.getLayoutParams();
        CoordinatorLayout.LayoutParams pPrint = (CoordinatorLayout.LayoutParams) floatingPrint.getLayoutParams();


        if (!connected) {


            connectInit();
            try {
                btConn(mBluetoothAdapter.getRemoteDevice(MACADDRES));
            } catch (IllegalArgumentException e) {

                return;
            } catch (IOException e) {
                Snackbar.make(floatingActionButton, R.string.print_snack_error, Snackbar.LENGTH_SHORT).show();
                return;
            }
        } else {

            if (isFabOpen) {

                pPrint.setBehavior(null);
                pDes.setBehavior(null);

                floatingPrint.setLayoutParams(pPrint);
                floatingDisconect.setLayoutParams(pDes);


                floatingPrint.startAnimation(fab_close);
                floatingDisconect.startAnimation(fab_close);

                floatingPrint.setClickable(false);
                floatingDisconect.setClickable(false);

                isFabOpen = false;
            } else {

                pPrint.setBehavior(new FABScrollBehavior(this, null));
                pDes.setBehavior(new FABScrollBehavior(this, null));


                floatingPrint.setLayoutParams(pPrint);
                floatingDisconect.setLayoutParams(pDes);

                floatingPrint.startAnimation(fab_open);
                floatingDisconect.startAnimation(fab_open);


                floatingPrint.setClickable(true);
                floatingDisconect.setClickable(true);
                isFabOpen = true;

            }


        }
    }

    private void connectInit() {
        if (!mBluetoothAdapter.isEnabled()) {
            mBluetoothAdapter.enable();
            try {
                Thread.sleep(3600);
            } catch (Exception e) {
            }
        }
    }

    private void btConn(final BluetoothDevice btDev) throws IOException {
        new connTask().execute(btDev);
    }



    private void btDisconn() {
        try {
            bp.disconnect();

            Thread.sleep(1200);
        } catch (Exception e) {

            Snackbar.make(floatingActionButton, R.string.print_snack_error, Snackbar.LENGTH_SHORT).show();

        }

        if ((hThread != null) && (hThread.isAlive()))
            hThread.interrupt();
        connected = false;
        floatingActionButton.setImageResource(R.drawable.ic_printer_sync_print);
        floatingActionButton.startAnimation(rotate_backward);
        floatingActionButton.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(PrintOrdenCarga.this, R.color.colorAccent)));


    }


    public class connTask extends AsyncTask<BluetoothDevice, Void, Integer> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override

        protected Integer doInBackground(BluetoothDevice... params) {
            // TODO Auto-generated method stub
            Integer retVal = null;
            try {
                bp.connect(params[0]);
                retVal = new Integer(0);
            } catch (IOException e) {
                retVal = new Integer(-1);
            }
            return retVal;
        }

        @Override
        protected void onPostExecute(Integer result) {
            // TODO Auto-generated method stub
            if (result.intValue() == 0) {
                RequestHandler rh = new RequestHandler();
                hThread = new Thread(rh);
                hThread.start();
                connected = true;


                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // UI

                        floatingActionButton.setImageResource(R.drawable.ic_printer_sync_ok);
                        floatingActionButton.startAnimation(rotate_backward);
                        floatingActionButton.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(PrintOrdenCarga.this, R.color.greem_background_item)));

                    }
                });
                /*list.setEnabled(false);
                editText.setEnabled(false);
                searchButton.setEnabled(false);*/
            } else if (result.intValue() == -1) {


                floatingActionButton.setImageResource(R.drawable.ic_printer_sync_print);
                floatingActionButton.startAnimation(rotate_backward);
                floatingActionButton.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(PrintOrdenCarga.this, R.color.colorAccent)));
                Snackbar.make(floatingActionButton, R.string.print_snack_sync_success, Snackbar.LENGTH_SHORT).show();


            }

            super.onPostExecute(result);
        }
    }
}
