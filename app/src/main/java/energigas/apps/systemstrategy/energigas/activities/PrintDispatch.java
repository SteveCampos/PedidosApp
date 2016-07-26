package energigas.apps.systemstrategy.energigas.activities;

import android.app.ProgressDialog;
import android.bluetooth.BluetoothA2dp;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothProfile;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Icon;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.sewoo.port.android.BluetoothPort;
import com.sewoo.request.android.RequestHandler;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.adapters.FABScrollBehavior;
import energigas.apps.systemstrategy.energigas.entities.Dispatch;
import energigas.apps.systemstrategy.energigas.printingsheets.SheetsPrintDispatch;

public class PrintDispatch extends AppCompatActivity implements View.OnClickListener {
    private Boolean isFabOpen = false;
    private BluetoothPort bp;
    private BluetoothAdapter mBluetoothAdapter;
    private static final int REQUEST_ENABLE_BT = 2;
    private Vector<BluetoothDevice> remoteDevices;
    private Thread hThread;
    private boolean connected;

    private String MACADDRES = "00:12:6F:36:6B:90";
    private Animation fab_open, fab_close, rotate_forward, rotate_backward;


    @BindView(R.id.fabDisconec)
    FloatingActionButton floatingDisconect;
    @BindView(R.id.fabPrint)
    FloatingActionButton floatingPrint;
    @BindView(R.id.fab)
    FloatingActionButton floatingActionButton;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private Dispatch mainDispatch;

    private static final String BLUETOOTH_PREFERENCE = "BLUETOOTH_PREFERENCE";
    //******* Widgets  to see preview print






    private static final String TAG = "aPrintDispatch";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print_dispatch);
        mainDispatch = new Dispatch(1,"Energigas SAC","Av. Santo Toribio # 173, cruce con Av. Vía Central, Centro Empresarial, Edificio Real 8 Of. 502","San Isidro","(511) 2033001","Lima","Peru","20145852413","10 - San Fernando","Calle Santa Teresa 171 Urbanizacion Los Sauces - Ate","-12.5484, -71.9824","PE-7845","Julio Paredes","F001-000008","26/07/2016","8:00 am","9:00 am","5000","3000","2000","100%","60%","TANK-002825","143810");
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


/*
        BluetoothDevice bluetoothDevice ;
        Set<BluetoothDevice> pairedDevices = mBluetoothAdapter
                .getBondedDevices();
        if (pairedDevices.isEmpty()) {
            Log.e(TAG,
                    "No devices paired...");
            return ;
        }

        for (BluetoothDevice device : pairedDevices) {
            Log.d(TAG, "Device : address : " + device.getAddress() + " name :"
                    + device.getName());
            if (MACADDRES.equals(device.getAddress())) {
                Log.d(TAG, "EXITO : address : " + device.getAddress() + " name :");
                bluetoothDevice = device;
                break;
            }
        }

*/






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

    @Override
    public void onClick(View view) {


        switch (view.getId()) {
            case R.id.fab:

                bluetoothConnect();
                break;
            case R.id.fabPrint:
                SheetsPrintDispatch printDispatch = new SheetsPrintDispatch();
                printDispatch.printNow(mainDispatch);

                break;
            case R.id.fabDisconec:
                floatingPrint.startAnimation(fab_close);
                floatingDisconect.startAnimation(fab_close);
                floatingPrint.setClickable(false);
                floatingDisconect.setClickable(false);
                isFabOpen=false;
                btDisconn();
                break;
        }
    }


    private void bluetoothConnect() {

        CoordinatorLayout.LayoutParams pDes = (CoordinatorLayout.LayoutParams) floatingDisconect.getLayoutParams();
        CoordinatorLayout.LayoutParams pPrint = (CoordinatorLayout.LayoutParams) floatingPrint.getLayoutParams();





        if (!connected) {


            connectInit();
            try {
                btConn(mBluetoothAdapter.getRemoteDevice(MACADDRES));
            } catch (IllegalArgumentException e) {
                Log.d(TAG + " ERROR: ", "" + e.getMessage());
                // Bluetooth Address Format [OO:OO:OO:OO:OO:OO]
                //  AlertView.showAlert(e.getMessage(), getApplicationContext());
                return;
            } catch (IOException e) {
                Toast.makeText(this, "ERROR: " + e.getMessage(), Toast.LENGTH_SHORT).show();
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

                pPrint.setBehavior(new FABScrollBehavior(this,null));
                pDes.setBehavior(new FABScrollBehavior(this,null));
                floatingPrint.setLayoutParams(pPrint);
                floatingDisconect.setLayoutParams(pDes);

                floatingPrint.startAnimation(fab_open);
                floatingDisconect.startAnimation(fab_open);
                floatingPrint.setClickable(true);
                floatingDisconect.setClickable(true);
                isFabOpen = true;

            }


            // Always run.
            // btDisconn();
        }
    }


    private void saveInPreferences(boolean estado){

        SharedPreferences prefs =
                getSharedPreferences(BLUETOOTH_PREFERENCE, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("isConnected", estado);
        editor.commit();
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
//		mBluetoothAdapter.disable();
        try {
            bp.disconnect();

            Thread.sleep(1200);
        } catch (Exception e) {
            Toast toast = Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 8);
            toast.show();
        }

        if ((hThread != null) && (hThread.isAlive()))
            hThread.interrupt();
        connected = false;
        floatingActionButton.setImageResource(R.drawable.ic_printer_sync_print);
        floatingActionButton.startAnimation(rotate_backward);
        saveInPreferences(false);
        floatingActionButton.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(PrintDispatch.this, R.color.colorAccent)));
        Toast toast = Toast.makeText(getApplicationContext(), "DESCONECTADO", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 8);
        toast.show();
    }


    public class connTask extends AsyncTask<BluetoothDevice, Void, Integer> {


        private final ProgressDialog dialog = new ProgressDialog(PrintDispatch.this);
//		private BluetoothPort bp;

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
//			bp = BluetoothPort.getInstance();
            floatingActionButton.setImageResource(R.drawable.ic_sync);
            floatingActionButton.startAnimation(rotate_forward);
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
                Log.d("FAB",e.getMessage());
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
                        floatingActionButton.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(PrintDispatch.this, R.color.greem_background_item)));
                        saveInPreferences(true);
                        //TOAST
                        Toast toast = Toast.makeText(PrintDispatch.this, "SINCRONIZADO", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 8);
                        toast.show();
                    }
                });
                /*list.setEnabled(false);
                editText.setEnabled(false);
                searchButton.setEnabled(false);*/
            } else if (result.intValue() == -1) {


                floatingActionButton.setImageResource(R.drawable.ic_printer_sync_print);
                floatingActionButton.startAnimation(rotate_backward);
                floatingActionButton.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(PrintDispatch.this, R.color.colorAccent)));
                Toast toast = Toast.makeText(PrintDispatch.this, "ERROR, REVISE LA IMPRESORA.", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 8);
                toast.show();

            }

            super.onPostExecute(result);
        }
    }
}
