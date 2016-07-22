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

import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.adapters.OrdersAdapter;

import energigas.apps.systemstrategy.energigas.entities.Order;


/**
 * Created by Kike on 21/07/2016.
 */

public class OrdersFragment extends Fragment implements OrdersAdapter.OnOrdersClickListener{

    public OnOrdersClickListener listener;

    public OrdersFragment() {

    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        RecyclerView recyclerView = (RecyclerView) inflater.inflate(
                R.layout.recycler_view, container, false);

        OrdersAdapter adapter = new OrdersAdapter(Order.getOrders(),getActivity(),this);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return recyclerView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnOrdersClickListener){
            listener = (OnOrdersClickListener) context;
        }else{
            throw new RuntimeException(context.toString() +
                    " must implement OnOrdersClickListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    @Override
    public void onOrdersClickListener(Order order, View view) {
        listener.onOrdersClickListener(order, view);
    }

    public interface OnOrdersClickListener{
        void onOrdersClickListener(Order order, View view);
    }
}
