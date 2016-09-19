package energigas.apps.systemstrategy.energigas.adapters;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.holders.BluetoothDeviceHolder;
import energigas.apps.systemstrategy.energigas.interfaces.BluetoothDeviceListener;

/**
 * Created by Steve on 16/09/2016.
 */

public class BluetoothDeviceAdapter extends RecyclerView.Adapter<BluetoothDeviceHolder> {

    private List<BluetoothDevice> bluetoothDevices = new ArrayList<>();
    private Context context;
    private BluetoothDeviceListener listener;

    public BluetoothDeviceAdapter(BluetoothDeviceListener listener) {
        this.context = (Context) listener;
        this.listener = listener;
    }

    @Override
    public BluetoothDeviceHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        // Inflate the custom layout
        View view = inflater.inflate(R.layout.item_bluetooth_device, parent, false);
        // Return a new holder instance
        return new BluetoothDeviceHolder(view);
    }

    @Override
    public void onBindViewHolder(BluetoothDeviceHolder holder, int position) {
        final BluetoothDevice device = bluetoothDevices.get(position);

        holder.name.setText(device.getName());
        holder.adress.setText(device.getAddress());
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                listener.onDeviceClickListener(device);
                return true;
            }
        });

    }

    public void addDevice(BluetoothDevice device){
        bluetoothDevices.add(device);
        notifyDataSetChanged();
    }

    public void clear(){
        bluetoothDevices.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return bluetoothDevices==null ? 0: bluetoothDevices.size();
    }
}
