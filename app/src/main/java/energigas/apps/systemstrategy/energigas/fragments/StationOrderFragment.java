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

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.activities.StationOrderActivity;
import energigas.apps.systemstrategy.energigas.adapters.StationOrdersAdapter;
import energigas.apps.systemstrategy.energigas.entities.AccessFragment;
import energigas.apps.systemstrategy.energigas.entities.CajaLiquidacion;
import energigas.apps.systemstrategy.energigas.entities.Concepto;
import energigas.apps.systemstrategy.energigas.entities.Estado;
import energigas.apps.systemstrategy.energigas.entities.NotificacionCajaDetalle;
import energigas.apps.systemstrategy.energigas.entities.Pedido;
import energigas.apps.systemstrategy.energigas.entities.PedidoDetalle;
import energigas.apps.systemstrategy.energigas.entities.Usuario;
import energigas.apps.systemstrategy.energigas.interfaces.IntentListenerAccess;
import energigas.apps.systemstrategy.energigas.utils.AccessPrivilegesManager;
import energigas.apps.systemstrategy.energigas.utils.Constants;
import energigas.apps.systemstrategy.energigas.utils.Session;

/**
 * A simple {@link Fragment} subclass.
 */
public class StationOrderFragment extends Fragment implements StationOrdersAdapter.OnOrderClickListener, IntentListenerAccess {


    private List<Pedido> pedidos = new ArrayList<>();
    private List<Estado> estado = new ArrayList<>();
    //private Estado estado;
    @BindView(R.id.my_recycler_view)
    RecyclerView recyclerView;
    private View view;
    private StationOrdersAdapter adapter;
    private OnStationOrderClickListener listener;
    private static final String TAG = "StationOrderFragment";
    private Usuario usuario;
    private HashMap<String, Boolean> booleanHashMap;

    private DatabaseReference mDatabase;
    private DatabaseReference mAtenPedidosPrecios;
    private ChildEventListener childEventListenerPrecios;

    private Concepto conceptoIGV;

    public interface OnStationOrderClickListener {
        void onStationOrderClickListener(Pedido pedido);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(
                R.layout.recycler_view, container, false);
        ButterKnife.bind(this, view);
        usuario = Session.getSession(getActivity());
        pedidos = Pedido.find(Pedido.class, " establecimiento_Id = ?", new String[]{Session.getSessionEstablecimiento(getActivity()).getEstIEstablecimientoId() + ""});
        Log.d(TAG, Session.getSessionEstablecimiento(getActivity()).getEstIEstablecimientoId() + "");
        new AccessPrivilegesManager(getClass())
                .setListenerIntent(this)
                .setPrivilegesIsEnable(usuario.getUsuIUsuarioId() + "")
                .setClassIntent(StationOrderActivity.class)
                .isIntentEnable();

        initView();
        listenerFirebasePrecios();
        return view;
    }

    private void initView() {
        adapter = new StationOrdersAdapter(pedidos, getActivity(), this);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

    }

    private void listenerFirebasePrecios() {
        CajaLiquidacion cajaLiquidacion = CajaLiquidacion.getCajaLiquidacion(Session.getCajaLiquidacion(getActivity()).getLiqId() + "");
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAtenPedidosPrecios = mDatabase.child(Constants.FIREBASE_CHILD_ATEN_PEDIDO).child(cajaLiquidacion.getLiqId() + "");
        childEventListenerPrecios = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                //Log.d(TAG, "PRECIO: -> " + dataSnapshot.getKey());
                //Log.d(TAG, "PRECIO: -> " + s);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Log.d(TAG, "PRECIO: -> " + dataSnapshot.getKey());
                Log.d(TAG, "PRECIO: -> " + s);
                System.out.print("Hola Objeto");
                System.out.print(dataSnapshot);
                conceptoIGV = Session.getConceptoIGV();

                NotificacionCajaDetalle notificacionCajaDetalle = dataSnapshot.getValue(NotificacionCajaDetalle.class);
                Pedido pedido = Pedido.getPedidoById(notificacionCajaDetalle.getPeId() + "");
                PedidoDetalle pedidoDetalle = PedidoDetalle.getPedidoDetalleByPedido(pedido.getPeId() + "").get(0);
                double precioUnitario = (Double.parseDouble(conceptoIGV.getDescripcion()) * notificacionCajaDetalle.getPrecio()) + notificacionCajaDetalle.getPrecio();
                pedidoDetalle.setPrecio(notificacionCajaDetalle.getPrecio());
                pedidoDetalle.setPrecioUnitario(precioUnitario);
                pedidoDetalle.save();
                initView();

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        mAtenPedidosPrecios.addChildEventListener(childEventListenerPrecios);
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

            if (booleanHashMap != null) {

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
