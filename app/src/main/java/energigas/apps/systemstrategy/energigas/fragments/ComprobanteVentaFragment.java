package energigas.apps.systemstrategy.energigas.fragments;


import android.content.Context;
import android.content.Intent;
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
import energigas.apps.systemstrategy.energigas.activities.PrintFacturaActivity;
import energigas.apps.systemstrategy.energigas.adapters.ComprobanteVentaAdapter;
import energigas.apps.systemstrategy.energigas.entities.Comprobante;
import energigas.apps.systemstrategy.energigas.entities.ComprobanteVenta;
import energigas.apps.systemstrategy.energigas.interfaces.OnComprobanteVentaClickListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class ComprobanteVentaFragment extends Fragment implements OnComprobanteVentaClickListener {

    @BindView(R.id.my_recycler_view) RecyclerView recyclerView;


    private List<ComprobanteVenta> list;
    private ComprobanteVentaAdapter adapter;

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
        adapter = new ComprobanteVentaAdapter(ComprobanteVenta.getList(), getActivity(), this);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onComprobanteVentaClickListener(ComprobanteVenta comprobanteVenta, View view) {
        startActivity(new Intent(getActivity(), PrintFacturaActivity.class));
    }

    /*
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnComprobanteVentaClickListener) {
            mListener = (OnComprobanteVentaClickListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnComprobanteVentaClickListener");
        }
    }
    */
}
