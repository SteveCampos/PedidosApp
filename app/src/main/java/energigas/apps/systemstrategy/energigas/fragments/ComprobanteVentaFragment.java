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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.activities.PrintFacturaActivity;
import energigas.apps.systemstrategy.energigas.adapters.ComprobanteVentaAdapter;
import energigas.apps.systemstrategy.energigas.entities.ComprobanteVenta;
import energigas.apps.systemstrategy.energigas.entities.ComprobanteVentaDetalle;
import energigas.apps.systemstrategy.energigas.entities.Establecimiento;
import energigas.apps.systemstrategy.energigas.interfaces.OnComprobanteVentaClickListener;
import energigas.apps.systemstrategy.energigas.utils.Session;
import energigas.apps.systemstrategy.energigas.utils.Utils;

/**
 * A simple {@link Fragment} subclass.
 */
public class ComprobanteVentaFragment extends Fragment implements OnComprobanteVentaClickListener {

    private RecyclerView recyclerView;


    private List<ComprobanteVenta> comprobanteVentas = new ArrayList<>();
    private ComprobanteVentaAdapter adapter;
    private Establecimiento establecimiento;

    public ComprobanteVentaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // View view = inflater.inflate(R.layout.recycler_view, container, false);
        recyclerView = (RecyclerView) inflater.inflate(
                R.layout.recycler_view, container, false);
        // ButterKnife.bind(this, view);

//        adapter = new ComprobanteVentaAdapter(comprobanteVentas, getActivity(), this);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        recyclerView.setAdapter(adapter);
        establecimiento = Establecimiento.find(Establecimiento.class, " est_I_Establecimiento_Id = ? ", new String[]{Session.getSessionEstablecimiento(getActivity()).getEstIEstablecimientoId() + ""}).get(0);
        comprobanteVentas = ComprobanteVenta.findWithQuery(ComprobanteVenta.class, Utils.getQueryListComproVenta(establecimiento.getEstIEstablecimientoId() + "", ""), null);
        adapter = new ComprobanteVentaAdapter(comprobanteVentas, getActivity(), this);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return recyclerView;

//        initViews();

        // return view;
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
