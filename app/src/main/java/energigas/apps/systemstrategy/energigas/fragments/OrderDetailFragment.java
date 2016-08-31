package energigas.apps.systemstrategy.energigas.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.adapters.OrderDetailAdapter;
import energigas.apps.systemstrategy.energigas.entities.Pedido;
import energigas.apps.systemstrategy.energigas.entities.PedidoDetalle;
import energigas.apps.systemstrategy.energigas.utils.Log;
import energigas.apps.systemstrategy.energigas.utils.Session;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrderDetailFragment extends Fragment {


    private List<PedidoDetalle> list;
    private RecyclerView recyclerView;
    private OrderDetailAdapter adapter;
    private Pedido pedido;
    private static final String TAG = "OrderDetailFragment";

    public OrderDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        recyclerView= (RecyclerView) inflater.inflate(
                R.layout.recycler_view, container, false);
        pedido = Session.getPedido(getActivity());
       list  = PedidoDetalle.find(PedidoDetalle.class," pe_Id = ? ",new String[]{pedido.getPeId()+""});
        adapter = new OrderDetailAdapter(list, getActivity());
        recyclerView.setAdapter(adapter);
        //recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return recyclerView;
    }

}
