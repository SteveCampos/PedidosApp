package energigas.apps.systemstrategy.energigas.fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.activities.PrintFacturaActivity;
import energigas.apps.systemstrategy.energigas.activities.SellPrintActivity;
import energigas.apps.systemstrategy.energigas.activities.StationOrderActivity;
import energigas.apps.systemstrategy.energigas.adapters.ComprobanteVentaAdapter;
import energigas.apps.systemstrategy.energigas.entities.AccessFragment;
import energigas.apps.systemstrategy.energigas.entities.ComprobanteVenta;
import energigas.apps.systemstrategy.energigas.entities.ComprobanteVentaDetalle;
import energigas.apps.systemstrategy.energigas.entities.Establecimiento;
import energigas.apps.systemstrategy.energigas.entities.Usuario;
import energigas.apps.systemstrategy.energigas.interfaces.IntentListenerAccess;
import energigas.apps.systemstrategy.energigas.interfaces.OnComprobanteVentaClickListener;
import energigas.apps.systemstrategy.energigas.utils.AccessPrivilegesManager;
import energigas.apps.systemstrategy.energigas.utils.Session;
import energigas.apps.systemstrategy.energigas.utils.Utils;

/**
 * A simple {@link Fragment} subclass.
 */
public class ComprobanteVentaFragment extends Fragment implements OnComprobanteVentaClickListener, IntentListenerAccess {



    private List<ComprobanteVenta> comprobanteVentas = new ArrayList<>();
    private ComprobanteVentaAdapter adapter;
    private Establecimiento establecimiento;

    private Usuario mUsuario;
    private HashMap<String, Boolean> booleanHashMap;
    private static final String TAG = "StationOrderFragment";
    private OnComprobanteVentaClickListener listener;


    @BindView(R.id.my_recycler_view) RecyclerView recyclerView;
    private View view;

    public interface OnComprobanteVentaClickListener {
        // Required empty public constructor
        void nComprobanteVentaClickListener(ComprobanteVenta Comprobante);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // View view = inflater.inflate(R.layout.recycler_view, container, false);
        view =  inflater.inflate(
                R.layout.recycler_view, container, false);
        ButterKnife.bind(this, view);
        // ButterKnife.bind(this, view);

//        adapter = new ComprobanteVentaAdapter(comprobanteVentas, getActivity(), this);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        recyclerView.setAdapter(adapter);
        mUsuario = Session.getSession(getActivity());
        new AccessPrivilegesManager(getClass())
                .setListenerIntent(this)
                .setPrivilegesIsEnable(mUsuario.getUsuIUsuarioId() + "")
                .setClassIntent(SellPrintActivity.class)
                .isIntentEnable();
        establecimiento = Establecimiento.find(Establecimiento.class, " est_I_Establecimiento_Id = ? ", new String[]{Session.getSessionEstablecimiento(getActivity()).getEstIEstablecimientoId() + ""}).get(0);
        comprobanteVentas = ComprobanteVenta.findWithQuery(ComprobanteVenta.class, Utils.getQueryListComproVenta(establecimiento.getEstIEstablecimientoId() + "", ""), null);
        adapter = new ComprobanteVentaAdapter(comprobanteVentas, getActivity(), this);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;

//        initViews();

        // return view;
    }


    @Override
    public void onComprobanteVentaClickListener(ComprobanteVenta comprobanteVenta, View view) {

        //  startActivity(new Intent(getActivity(), SellPrintActivity.class));


        if (booleanHashMap != null) {

            if (booleanHashMap.get(SellPrintActivity.class.getSimpleName())) {
                Log.d("FACTURASID", comprobanteVenta.getCompId() + "");
                Session.saveComprobanteVenta(getActivity(), comprobanteVenta);
                startActivity(new Intent(getActivity(), SellPrintActivity.class));
            }
        }


    }

    @Override
    public void onIntentListenerAcces(HashMap<String, Boolean> booleanHashMap) {
        this.booleanHashMap = booleanHashMap;
        Log.d(TAG, "TAMAÑO HASH: " + booleanHashMap.size());
        System.out.print(booleanHashMap);
    }

    @Override
    public void onFragmentAccess(List<AccessFragment> accessFragmentList) {

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
