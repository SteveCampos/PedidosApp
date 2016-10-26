package energigas.apps.systemstrategy.energigas.activities;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.common.base.Charsets;
import com.google.common.primitives.Chars;

import java.util.Date;

import app.akexorcist.bluetotohspp.library.BluetoothSPP;
import app.akexorcist.bluetotohspp.library.BluetoothState;
import app.akexorcist.bluetotohspp.library.DeviceList;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.adapters.BluetoothDeviceAdapter;
import energigas.apps.systemstrategy.energigas.broadcast.BluetoothBroadCast;
import energigas.apps.systemstrategy.energigas.firebase.BluetoothFire;
import energigas.apps.systemstrategy.energigas.firebase.MessageBluetooth;
import energigas.apps.systemstrategy.energigas.interfaces.BluetoothConnectionListener;
import energigas.apps.systemstrategy.energigas.interfaces.BluetoothDeviceListener;
import energigas.apps.systemstrategy.energigas.threads.BTAcceptThread;
import energigas.apps.systemstrategy.energigas.utils.Utils;

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

    boolean DEVICE_ANDROID = false;

    private BluetoothFire fireBluetooth;
    private String androidID;

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
        setupBluetoothSPP();
        initFirebase();
    }

    private void initFirebase() {

        androidID = Utils.getAndroidID(this);
        fireBluetooth = new BluetoothFire(androidID);

    }

    private void setupBluetoothSPP(){

        bt = new BluetoothSPP(getActivity());
        if(!bt.isBluetoothAvailable()) {
            // any command for bluetooth is not available
            Toast.makeText(getActivity(), "!bt.isBluetoothAvailable()", Toast.LENGTH_LONG).show();
        }



        bt.setOnDataReceivedListener(new BluetoothSPP.OnDataReceivedListener() {
            public void onDataReceived(byte[] data, String message) {
                // Do something when data incoming
                Log.d(TAG, "data: "+ data + ", message>: " + message);

                MessageBluetooth messageBluetooth = new MessageBluetooth(
                        message,
                        getBytesToString(data),
                        new String(data, Charsets.US_ASCII),
                        "US_ASCII",
                        new Date().getTime(),
                        "RECEIVED",
                        "",
                        bt.getConnectedDeviceAddress()
                );

                fireBluetooth.saveMessage(messageBluetooth);
                textReaded.append("\n " + new String(Utils.clearUnsigned(data), Charsets.US_ASCII));

                scrollText();

            }
        });

        bt.setBluetoothConnectionListener(new BluetoothSPP.BluetoothConnectionListener() {
            @Override
            public void onDeviceConnected(String name, String address) {
                initText(name, address);
            }

            @Override
            public void onDeviceDisconnected() {
                initText(null, null);
                Toast.makeText(getActivity(), "DESCONECTADO.", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onDeviceConnectionFailed() {
                initText(null, null);
                Toast.makeText(getActivity(), "FALLÓ LA CONEXIÓN. ASEGURESE QUE EL BLUETOOTH ESTÉ ENCENDIDO.", Toast.LENGTH_LONG).show();
            }
        });


        if(!bt.isBluetoothEnabled()) {
            // Do somthing if bluetooth is disable
            enableBluetooth();
        } else {

            bt.setupService();
            bt.startService(DEVICE_ANDROID);

            initViews();
            //startConnect();
            // Do something if bluetooth is already enable
        }
    }

    public void initText(String device, String adress){
        textDevice.setText("DEVICE: " + device);
        textAdress.setText("ADRESS: " + adress);
        textReaded.setText("LOG: \n");
    }

    private void setDeviceAndroid(boolean isAndroid){
        DEVICE_ANDROID = isAndroid;
        bt.setDeviceTarget(DEVICE_ANDROID);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_bluetooth, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        String comando = "#UD";
        switch (item.getItemId()) {
            case R.id.action_programar:
                // User chose the "Settings" item, show the app settings UI...
                comando = "#PP00010.0";
                break;
            case R.id.action_iniciar:
                // User chose the "Settings" item, show the app settings UI...
                comando = "#ID";
                break;
            case R.id.action_terminar:
                comando = "#TD";
                // User chose the "Settings" item, show the app settings UI...
                break;
            case R.id.action_get_last:
                comando = "#UD";
                // User chose the "Settings" item, show the app settings UI...
                break;
            case R.id.action_target_android:

                setDeviceAndroid(true);
                Snackbar.make(toolbar, "Modo Android Seleccionado.", Snackbar.LENGTH_LONG).show();
                break;
            case R.id.action_target_other:
                setDeviceAndroid(false);
                Snackbar.make(toolbar, "Modo Serial Port Profile Seleccionado. Para Microcontroladores.", Snackbar.LENGTH_LONG).show();
                break;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                break;
        }
        eTCommands.setText(comando);
        scrollText();
        return true;
    }

    private void initViews() {
        textReaded.setMovementMethod(new ScrollingMovementMethod());
        initText(bt.getConnectedDeviceName(), bt.getConnectedDeviceAddress());
    }


    private void scrollText(){
        final int scrollAmount = textReaded.getLayout().getLineTop(textReaded.getLineCount()) - textReaded.getHeight();
        // if there is no need to scroll, scrollAmount will be <=0
        if (scrollAmount > 0)
            textReaded.scrollTo(0, scrollAmount);
        else
            textReaded.scrollTo(0, 0);
    }
    /*
    private void setRecycler() {
        bluetoothDeviceAdapter = new BluetoothDeviceAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(bluetoothDeviceAdapter);
    }*/

    @OnClick(R.id.buttonScan)
    public void buttonScan(){
        startConnect();
    }





    @OnClick(R.id.buttonSend)
    public void buttonSend(){


        char STX = (char) 2;
        char ETX = (char) 3;
        String comando = eTCommands.getText().toString();


        if (TextUtils.isEmpty(bt.getConnectedDeviceAddress())){
            initText(null, null);
            Snackbar.make(toolbar, "Seleccionar Dispositivo", Snackbar.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(comando)){
            Snackbar.make(toolbar, "Introducir comando", Snackbar.LENGTH_SHORT).show();
            return;
        }


        byte[] bytes  = (STX + comando + ETX).getBytes(Charsets.US_ASCII);

        MessageBluetooth messageBluetooth = new MessageBluetooth(
                comando,
                getBytesToString(bytes),
                new String(bytes, Charsets.US_ASCII),
                "US_ASCII",
                 new Date().getTime(),
                "SEND",
                "",
                bt.getConnectedDeviceAddress()
        );

        fireBluetooth.saveMessage(messageBluetooth);


        Log.d(TAG, "buttonSend new String(bytes, Charsets.US_ASCII): " + new String(bytes, Charsets.US_ASCII));


        bt.send(bytes, true);
        eTCommands.setText("");

        textReaded.append("\n> " + comando);
    }

    public String getBytesToString(byte[] data){
        String x = "";
        for (byte aData : data) {
            x += "_" + aData;
        }
        return x;
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
                        bt.startService(DEVICE_ANDROID);
                        initViews();


                    }else if (resultCode == RESULT_CANCELED){
                        Toast.makeText(this, "RESULT_CANCELED", Toast.LENGTH_SHORT).show();
                    }
                break;
            case BluetoothState.REQUEST_CONNECT_DEVICE:


                if(resultCode == Activity.RESULT_OK)

                    if (data != null) {

                        Snackbar.make(toolbar, "DISPOSITIVO: "  +data.getStringExtra(BluetoothState.EXTRA_DEVICE_ADDRESS) , Snackbar.LENGTH_LONG).show();

                        if (bt==null){
                            Toast.makeText(getActivity(), "bt==null", Toast.LENGTH_LONG).show();
                        }


                        /*
                        bt.setupService();
                        bt.startService(BluetoothState.DEVICE_ANDROID);*/

                        initViews();
                        bt.connect(data);
                    }else{

                        Snackbar.make(toolbar, "No selecciono ningún dispositivo", Snackbar.LENGTH_LONG).show();
                    }

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
