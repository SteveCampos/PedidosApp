package energigas.apps.systemstrategy.energigas.fragments;


import android.content.Context;
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

import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.activities.StationOrderActivity;
import energigas.apps.systemstrategy.energigas.adapters.StationOrdersAdapter;
import energigas.apps.systemstrategy.energigas.entities.AccessFragment;
import energigas.apps.systemstrategy.energigas.entities.Estado;
import energigas.apps.systemstrategy.energigas.entities.Pedido;
import energigas.apps.systemstrategy.energigas.entities.Usuario;
import energigas.apps.systemstrategy.energigas.interfaces.IntentListenerAccess;
import energigas.apps.systemstrategy.energigas.utils.AccessPrivilegesManager;
import energigas.apps.systemstrategy.energigas.utils.Session;

/**
 * A simple {@link Fragment} subclass.
 */
public class StationOrderFragment extends Fragment implements StationOrdersAdapter.OnOrderClickListener, IntentListenerAccess {


    private List<Pedido> pedidos = new ArrayList<>();
    private List<Estado> estado = new ArrayList<>();
    //private Estado estado;
    private RecyclerView recyclerView;
    private StationOrdersAdapter adapter;
    private OnStationOrderClickListener listener;
    private static final String TAG = "StationOrderFragment";
    private Usuario usuario;
    private HashMap<String, Boolean> booleanHashMap;


    public interface OnStationOrderClickListener {
        void onStationOrderClickListener(Pedido pedido);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        recyclerView = (RecyclerView) inflater.inflate(
                R.layout.recycler_view, container, false);
        //  pedidos= getPedidos();
        usuario = Session.getSession(getActivity());
        pedidos = Pedido.find(Pedido.class, " establecimiento_Id = ?", new String[]{Session.getSessionEstablecimiento(getActivity()).getEstIEstablecimientoId() + ""});
        Log.d(TAG, Session.getSessionEstablecimiento(getActivity()).getEstIEstablecimientoId() + "");
        new AccessPrivilegesManager(getClass())
                .setListenerIntent(this)
                .setPrivilegesIsEnable(usuario.getUsuIUsuarioId() + "")
                .setClassIntent(StationOrderActivity.class)
                .isIntentEnable();
        //estado=Estado.find(Estado.class," id_Estado = ?",new String[]{pedidos.get()});


        adapter = new StationOrdersAdapter(pedidos, getActivity(), this);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return recyclerView;
    }

    @Override
    public void onIntentListenerAcces(HashMap<String, Boolean> booleanHashMap) {
        this.booleanHashMap = booleanHashMap;
        Log.d(TAG, "TAMAÑO HASH: " + booleanHashMap.size());
    }

    @Override
    public void onFragmentAccess(List<AccessFragment> accessFragmentList) {
//Only for fragment
    }


    @Override
    public void onOrderClickListener(Pedido pedido) {
        if (listener != null) {

            if (booleanHashMap !=null){

                if (booleanHashMap.get(StationOrderActivity.class.getSimpleName())) {
                    //if (pedido.getEstado().getId())
                    listener.onStationOrderClickListener(pedido);
                }
            }



        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnStationOrderClickListener) {
            listener = (OnStationOrderClickListener) context;
        } else {
            throw new RuntimeException(context.toString() +
                    " must implement OnStationOrderClickListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}
