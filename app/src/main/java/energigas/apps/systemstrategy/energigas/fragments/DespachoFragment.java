package energigas.apps.systemstrategy.energigas.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.activities.PrintDispatch;
import energigas.apps.systemstrategy.energigas.adapters.DespachoAdapter;
import energigas.apps.systemstrategy.energigas.entities.Despacho;
import energigas.apps.systemstrategy.energigas.entities.Pedido;
import energigas.apps.systemstrategy.energigas.utils.Session;

/**
 * A simple {@link Fragment} subclass.
 */
public class DespachoFragment extends Fragment implements DespachoAdapter.OnDespachoClickListener {

    private List<Despacho> list;
    private RecyclerView recyclerView;
    private DespachoAdapter adapter;
    private Pedido pedido;
    public DespachoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        pedido = Session.getPedido(getActivity());
        recyclerView = (RecyclerView) inflater.inflate(R.layout.recycler_view, container, false);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        list = Despacho.find(Despacho.class," pe_Id=?  ",new String[]{pedido.getPeId()+""});
        adapter = new DespachoAdapter(list, getActivity(), this);

        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);


        return recyclerView;
    }


    @Override
    public void onDespachoClickListener(Despacho despacho, View view) {
        Session.saveDespacho(getActivity(),despacho);
        startActivity(new Intent(getActivity(), PrintDispatch.class));
    }
}
