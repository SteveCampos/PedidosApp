package energigas.apps.systemstrategy.energigas.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.activities.DespachoActivity;
import energigas.apps.systemstrategy.energigas.activities.PrintDispatch;
import energigas.apps.systemstrategy.energigas.adapters.DespachoAdapter;
import energigas.apps.systemstrategy.energigas.entities.AccessFragment;
import energigas.apps.systemstrategy.energigas.entities.Despacho;
import energigas.apps.systemstrategy.energigas.entities.Pedido;
import energigas.apps.systemstrategy.energigas.entities.Usuario;
import energigas.apps.systemstrategy.energigas.interfaces.IntentListenerAccess;
import energigas.apps.systemstrategy.energigas.utils.AccessPrivilegesManager;
import energigas.apps.systemstrategy.energigas.utils.Session;

/**
 * A simple {@link Fragment} subclass.
 */
public class DespachoFragment extends Fragment implements DespachoAdapter.OnDespachoClickListener, IntentListenerAccess {

    private List<Despacho> list;
    @BindView(R.id.my_recycler_view) RecyclerView recyclerView;
    private View view;
    private DespachoAdapter adapter;
    private Pedido pedido;
    private Usuario usuario;
    private HashMap<String, Boolean> booleanHashMap;
    public DespachoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        pedido = Session.getPedido(getActivity());
        view = inflater.inflate(R.layout.recycler_view, container, false);
        ButterKnife.bind(this, view);
    usuario = Session.getSession(getActivity());

        new AccessPrivilegesManager(getClass())
                .setListenerIntent(this)
                .setPrivilegesIsEnable(usuario.getUsuIUsuarioId() + "")
                .setClassIntent(PrintDispatch.class)
                .isIntentEnable();


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
        if (booleanHashMap !=null){
            if (booleanHashMap.get( PrintDispatch.class.getSimpleName())){
                startActivity(new Intent(getActivity(), PrintDispatch.class));
            }
        }

    }

    @Override
    public void onIntentListenerAcces(HashMap<String, Boolean> booleanHashMap) {
        this.booleanHashMap=booleanHashMap;
    }

    @Override
    public void onFragmentAccess(List<AccessFragment> accessFragmentList) {
//Only for fragment
    }
}
