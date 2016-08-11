package energigas.apps.systemstrategy.energigas.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.adapters.StationOrderTankAdapter;
import energigas.apps.systemstrategy.energigas.adapters.StationProductsAdapter;
import energigas.apps.systemstrategy.energigas.entities.Almacen;
import energigas.apps.systemstrategy.energigas.entities.OrderProduct;
import energigas.apps.systemstrategy.energigas.entities.Tank;

/**
 * A simple {@link Fragment} subclass.
 */
public class TankFragment extends Fragment {

    private List<Almacen> almacenList = new ArrayList<>();
    private RecyclerView recyclerView;
    private StationOrderTankAdapter adapter;

    public TankFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        recyclerView= (RecyclerView) inflater.inflate(
                R.layout.recycler_view, container, false);

        almacenList = Almacen.getList();
        adapter = new StationOrderTankAdapter(almacenList, getActivity());
        recyclerView.setAdapter(adapter);
        //recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return recyclerView;
    }

}
