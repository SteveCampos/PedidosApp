package energigas.apps.systemstrategy.energigas.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.adapters.StationOrdersAdapter;
import energigas.apps.systemstrategy.energigas.adapters.StationProductsAdapter;
import energigas.apps.systemstrategy.energigas.entities.Order;
import energigas.apps.systemstrategy.energigas.entities.OrderProduct;

/**
 * A simple {@link Fragment} subclass.
 */
public class StationOrderFragment extends Fragment implements StationOrdersAdapter.OnOrderClickListener {


    private List<Order> orders = new ArrayList<>();
    private RecyclerView recyclerView;
    private StationOrdersAdapter adapter;
    private OnStationOrderClickListener listener;

    public interface OnStationOrderClickListener{
        void onStationOrderClickListener(Order order);
    }

    public StationOrderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        recyclerView= (RecyclerView) inflater.inflate(
                R.layout.recycler_view, container, false);

        orders = Order.getOrders();
        adapter = new StationOrdersAdapter(orders, getActivity(), this);
        recyclerView.setAdapter(adapter);
        //recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return recyclerView;
    }

    @Override
    public void onOrderClickListener(Order order) {
        if (listener!=null){
            listener.onStationOrderClickListener(order);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnStationOrderClickListener){
            listener = (OnStationOrderClickListener) context;
        }else{
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
