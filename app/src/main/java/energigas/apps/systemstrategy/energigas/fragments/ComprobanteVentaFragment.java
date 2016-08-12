package energigas.apps.systemstrategy.energigas.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.adapters.ComprobanteVentaAdapter;
import energigas.apps.systemstrategy.energigas.entities.Comprobante;
import energigas.apps.systemstrategy.energigas.entities.ComprobanteVenta;

/**
 * A simple {@link Fragment} subclass.
 */
public class ComprobanteVentaFragment extends Fragment {

    private List<ComprobanteVenta> list;
    private ComprobanteVentaAdapter adapter;
    @BindView(R.id.my_recycler_view) RecyclerView recyclerView;

    public ComprobanteVentaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.recycler_view, container, false);
        ButterKnife.bind(this, view);
        initViews();
        return view;
    }

    private void initViews() {
        adapter = new ComprobanteVentaAdapter(ComprobanteVenta.getList(), getActivity());

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
    }

}
