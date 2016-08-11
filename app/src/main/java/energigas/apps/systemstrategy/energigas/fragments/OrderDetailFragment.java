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
import energigas.apps.systemstrategy.energigas.adapters.OrderDetailAdapter;
import energigas.apps.systemstrategy.energigas.entities.PedidoDetalle;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrderDetailFragment extends Fragment {


    private List<PedidoDetalle> list = new ArrayList<>();
    private RecyclerView recyclerView;
    private OrderDetailAdapter adapter;


    public OrderDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        recyclerView= (RecyclerView) inflater.inflate(
                R.layout.recycler_view, container, false);

        list = PedidoDetalle.getList();
        adapter = new OrderDetailAdapter(list, getActivity());
        recyclerView.setAdapter(adapter);
        //recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return recyclerView;
    }

}
