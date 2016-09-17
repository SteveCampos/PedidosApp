package energigas.apps.systemstrategy.energigas.activities;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.adapters.BluetoothDeviceAdapter;
import energigas.apps.systemstrategy.energigas.broadcast.BluetoothBroadCast;
import energigas.apps.systemstrategy.energigas.interfaces.BluetoothDeviceListener;
import energigas.apps.systemstrategy.energigas.utils.Log;

public class BluetoothActivity extends AppCompatActivity implements BluetoothDeviceListener {

    private static final String TAG = "BluetoothActivity";
    private static final int REQUEST_ENABLE_BT = 1000;
    private BluetoothBroadCast bluetoothBroadCast;
    private BluetoothAdapter mBluetoothAdapter;
    private BluetoothDeviceAdapter bluetoothDeviceAdapter;

    @BindView(R.id.button)
    Button button;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);
        Log.d(TAG, "onCreate");
        ButterKnife.bind(this);
        enableBluetooth();
        upBroadCast();
        setRecycler();
    }

    private void setRecycler() {
        bluetoothDeviceAdapter = new BluetoothDeviceAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(bluetoothDeviceAdapter);
    }

    @OnClick(R.id.button)
    public void onClick(){

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

        }
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
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter != null){
            if (!mBluetoothAdapter.isEnabled()){
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode){
            case REQUEST_ENABLE_BT:
                    if (resultCode == RESULT_OK){
                        Toast.makeText(this, "RESULT_OK", Toast.LENGTH_SHORT).show();
                    }else if (resultCode == RESULT_CANCELED){
                        Toast.makeText(this, "RESULT_CANCELED+", Toast.LENGTH_SHORT).show();
                    }
                break;
        }
        //super.onActivityResult(requestCode, resultCode, data);
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
        unregisterReceiver(bluetoothBroadCast);
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
    }

    private AppCompatActivity getActivity(){
        return this;
    }


}
