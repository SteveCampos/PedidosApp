package energigas.apps.systemstrategy.energigas.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.adapters.InventoryAdapter;
import energigas.apps.systemstrategy.energigas.entities.Inventory;

/**
 * Created by kelvi on 19/07/2016.
 */

public class InventarioFragment extends Fragment {

    @BindView(R.id.racyclerInventory)
    RecyclerView recyclerView;


    public static InventarioFragment newIntance() {
        InventarioFragment inventarioFragment = new InventarioFragment();
        return inventarioFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.layout_fragment_inventory, container, false);
        ButterKnife.bind(this, rootView);
        InventoryAdapter adapter = new InventoryAdapter(Inventory.getInventoryList(getActivity()), getActivity());
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return rootView;
    }

}
