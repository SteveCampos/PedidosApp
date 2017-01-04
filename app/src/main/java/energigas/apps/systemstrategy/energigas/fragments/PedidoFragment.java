package energigas.apps.systemstrategy.energigas.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.adapters.PedidoAdapter;

import energigas.apps.systemstrategy.energigas.entities.Pedido;


/**
 * Created by Kike on 21/07/2016.
 */

public class PedidoFragment extends Fragment implements PedidoAdapter.OnPedidoClickListener {

    public OnPedidoClickListener listener;
    @BindView(R.id.my_recycler_view) RecyclerView recyclerView;
    private View view;
    public PedidoFragment() {

    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view =  inflater.inflate(
                R.layout.recycler_view, container, false);
        ButterKnife.bind(this, view);
       /* PedidoAdapter adapter = new PedidoAdapter(Pedido.getList(),getActivity(),this);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));*/
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnPedidoClickListener){
            listener = (OnPedidoClickListener) context;
        }else{
            throw new RuntimeException(context.toString() +
                    " must implement OnPedidoClickListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    @Override
    public void onPedidoClickListener(Pedido pedido, View view) {
        listener.onPedidoClickListener(pedido, view);
    }

    public interface OnPedidoClickListener {
        void onPedidoClickListener(Pedido pedido, View view);
    }
}
