package energigas.apps.systemstrategy.energigas.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.entities.Almacen;
import energigas.apps.systemstrategy.energigas.entities.Tank;
import energigas.apps.systemstrategy.energigas.holders.AlmacenHolder;

/**
 * Created by Steve on 10/08/2016.
 */

public class StationOrderTankAdapter extends RecyclerView.Adapter<AlmacenHolder>{

    private static final String TAG = StationOrderTankAdapter.class.getSimpleName();
    // Store a member variable for the list;
    private List<Almacen> almacenList;
    // Store the context for easy access
    private Context mContext;

    public StationOrderTankAdapter(List<Almacen> almacenList, Context mContext) {
        this.almacenList = almacenList;
        this.mContext = mContext;
    }

    @Override
    public AlmacenHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        // Inflate the custom layout
        View view = inflater.inflate(R.layout.item_tanque, parent, false);
        // Return a new holder instance
        return new AlmacenHolder(view);
    }

    @Override
    public void onBindViewHolder(AlmacenHolder holder, int position) {
        final Almacen almacen = almacenList.get(position);
        holder.title.setText("76679526 - " + position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "CLICKED: " + view);
            }
        });
    }

    @Override
    public int getItemCount() {
        return almacenList.size();
    }
}
