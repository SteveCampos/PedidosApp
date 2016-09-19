package energigas.apps.systemstrategy.energigas.activities;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.design.widget.TextInputEditText;
import android.support.v4.text.TextUtilsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.common.base.Charsets;

import java.nio.charset.Charset;

import app.akexorcist.bluetotohspp.library.BluetoothSPP;
import app.akexorcist.bluetotohspp.library.BluetoothState;
import app.akexorcist.bluetotohspp.library.DeviceList;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.adapters.BluetoothDeviceAdapter;
import energigas.apps.systemstrategy.energigas.broadcast.BluetoothBroadCast;
import energigas.apps.systemstrategy.energigas.interfaces.BluetoothConnectionListener;
import energigas.apps.systemstrategy.energigas.interfaces.BluetoothDeviceListener;
import energigas.apps.systemstrategy.energigas.threads.BTAcceptThread;
import energigas.apps.systemstrategy.energigas.threads.BTConnectThread;
import energigas.apps.systemstrategy.energigas.utils.Log;

public class BluetoothActivity extends AppCompatActivity implements BluetoothDeviceListener, BluetoothConnectionListener {

    private static final String TAG = "BluetoothActivity";
    private static final int REQUEST_ENABLE_BT = 1000;
    private BluetoothBroadCast bluetoothBroadCast;
    private BluetoothAdapter mBluetoothAdapter;
    private BluetoothDeviceAdapter bluetoothDeviceAdapter;

    /*
    @BindView(R.id.button)
    Button button;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    */
    @BindView(R.id.textDevice)
    TextView textDevice;
    @BindView(R.id.textAdress)
    TextView textAdress;
    @BindView(R.id.etCommands)
    AppCompatEditText eTCommands;
    @BindView(R.id.textReaded) TextView textReaded;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    BluetoothSPP bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);
        Log.d(TAG, "onCreate");
        ButterKnife.bind(this);
        /*enableBluetooth();
        upBroadCast();
        setRecycler();*/
        // Make sure the toolbar exists in the activity and is not null
        setSupportActionBar(toolbar);
        toolbar.setTitle("Despacho Comandos");

        bt = new BluetoothSPP(getActivity());
        if(!bt.isBluetoothAvailable()) {
            // any command for bluetooth is not available
            Toast.makeText(getActivity(), "!bt.isBluetoothAvailable()", Toast.LENGTH_LONG).show();
        }



        bt.setOnDataReceivedListener(new BluetoothSPP.OnDataReceivedListener() {
            public void onDataReceived(byte[] data, String message) {
                // Do something when data incoming
                Log.d(TAG, "data: "+ new String(data) + ", message: " + message);
                textReaded.append("\nRECIBIDO: " + new String(data, Charsets.US_ASCII));
            }
        });

        bt.setBluetoothConnectionListener(new BluetoothSPP.BluetoothConnectionListener() {
            @Override
            public void onDeviceConnected(String name, String address) {

                textDevice.setText("DEVICE: " + name);
                textAdress.setText("ADRESS: " + address);
            }

            @Override
            public void onDeviceDisconnected() {
                Toast.makeText(getActivity(), "onDeviceDisconnected", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onDeviceConnectionFailed() {
                Toast.makeText(getActivity(), "onDeviceConnectionFailed", Toast.LENGTH_LONG).show();
            }
        });


        if(!bt.isBluetoothEnabled()) {
            // Do somthing if bluetooth is disable
            enableBluetooth();
        } else {

            bt.setupService();
            bt.startService(BluetoothState.DEVICE_ANDROID);

            initViews();
            //startConnect();
            // Do something if bluetooth is already enable
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_bluetooth, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String comando = "*#UD";
        switch (item.getItemId()) {
            case R.id.action_programar:
                // User chose the "Settings" item, show the app settings UI...
                comando = "*#PP99999.0*";
                break;
            case R.id.action_iniciar:
                // User chose the "Settings" item, show the app settings UI...
                comando = "*#ID*";
                break;
            case R.id.action_terminar:
                comando = "*#TD*";
                // User chose the "Settings" item, show the app settings UI...
                break;
            case R.id.action_get_last:
                comando = "*#UD*";
                // User chose the "Settings" item, show the app settings UI...
                break;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                break;
        }
        eTCommands.setText(comando);
        return true;
    }

    private void initViews() {
      textReaded.setMovementMethod(new ScrollingMovementMethod());

        textDevice.setText("DEVICE: " + bt.getConnectedDeviceName());
        textAdress.setText("ADRESS: " + bt.getConnectedDeviceAddress());

    }


    /*
    private void setRecycler() {
        bluetoothDeviceAdapter = new BluetoothDeviceAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(bluetoothDeviceAdapter);
    }*/

    @OnClick(R.id.buttonScan)
    public void fabClicked(){
        startConnect();
    }

    @OnClick(R.id.buttonSend)
    public void onClick(){

        bt.send(eTCommands.getText().toString().getBytes(Charsets.US_ASCII), true);
        eTCommands.setText("");
        textReaded.append("\nENVIADO: " + eTCommands.getText().toString());
        /*
        if (mBluetoothAdapter!=null && mBluetoothAdapter.isEnabled()){
            if (!mBluetoothAdapter.isDiscovering()){
                bluetoothDeviceAdapter.clear();
                if (mBluetoothAdapter.startDiscovery()){
                    //upBroadCast();
                    button.setText("SCANNING...");
                }
            }else {
                if (mBluetoothAdapter.cancelDiscovery()){
                    //offBroadcast();
                    button.setText("START.");
                }
            }

        }*/
    }

    private void upBroadCast(){
        bluetoothBroadCast = new BluetoothBroadCast();
        bluetoothBroadCast.setListener(this);
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(bluetoothBroadCast, filter);
    }

    private void offBroadcast(){
        if (bluetoothBroadCast!=null){
            unregisterReceiver(bluetoothBroadCast);
            bluetoothBroadCast = null;
        }
    }


    private void enableBluetooth(){
        /*
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter != null){
            if (!mBluetoothAdapter.isEnabled()){
            }
        }*/

        Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
    }

    private void startConnect(){

        Intent intent = new Intent(getApplicationContext(), DeviceList.class);
        startActivityForResult(intent, BluetoothState.REQUEST_CONNECT_DEVICE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode){

            case REQUEST_ENABLE_BT:
                    if (resultCode == RESULT_OK){
                        Toast.makeText(this, "RESULT_OK", Toast.LENGTH_SHORT).show();

                        bt.setupService();
                        bt.startService(BluetoothState.DEVICE_ANDROID);
                        initViews();


                    }else if (resultCode == RESULT_CANCELED){
                        Toast.makeText(this, "RESULT_CANCELED", Toast.LENGTH_SHORT).show();
                    }
                break;
            case BluetoothState.REQUEST_CONNECT_DEVICE:
                if(resultCode == Activity.RESULT_OK)
                    Toast.makeText(getActivity(), "SELECTED: " + data.getStringExtra(BluetoothState.EXTRA_DEVICE_ADDRESS), Toast.LENGTH_LONG).show();

                if (bt==null){
                    Toast.makeText(getActivity(), "bt==null", Toast.LENGTH_LONG).show();
                }


                bt.setupService();
                bt.startService(BluetoothState.DEVICE_ANDROID);

                initViews();
                    bt.connect(data);

                break;
            /*
            case BluetoothState.REQUEST_ENABLE_BT:
                if(resultCode == Activity.RESULT_OK) {
                    //setup();
                } else {
                    // Do something if user doesn't choose any device (Pressed back)
                    Toast.makeText(getActivity(), "User doesn't choose any device", Toast.LENGTH_LONG).show();
                }
                break;*/
        }
        //super.onActivityResult(requestCode, resultCode, data);
    }

    private void startConnection(boolean isAndroid){
            bt.startService(isAndroid);
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        //unregisterReceiver(bluetoothBroadCast);
        bt.stopService();
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    @Override
    public void onNewDeviceDiscovered(BluetoothDevice device) {
        Log.d(TAG, "onNewDeviceDiscovered BluetoothDevice" + device);
        bluetoothDeviceAdapter.addDevice(device);
    }

    @Override
    public void onDeviceClickListener(BluetoothDevice device) {
        Toast.makeText(getActivity(), device.getName(), Toast.LENGTH_LONG).show();
        BTAcceptThread thread = new BTAcceptThread(mBluetoothAdapter);
        thread.run();

    }

    private AppCompatActivity getActivity(){
        return this;
    }


    @Override
    public void onConnected(boolean connected) {
        Toast.makeText(getActivity(), "connected: " + connected, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRead(byte[] bytes) {

    }
}
