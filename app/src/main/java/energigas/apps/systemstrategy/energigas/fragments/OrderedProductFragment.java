package energigas.apps.systemstrategy.energigas.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.adapters.OrderedProductAdapter;
import energigas.apps.systemstrategy.energigas.entities.OrderDispatch;
import energigas.apps.systemstrategy.energigas.entities.OrderProduct;

/**
 * Created by Steve on 22/07/2016.
 */

public class OrderedProductFragment extends Fragment implements OrderedProductAdapter.OnOrderedProductClickListener, OrderedProductAdapter.OnDispatchClickListener {


    private OnOrderedProductClickListener listener;
    private OnDispatchClickListener listenerDispatch;
    private OrderedProductAdapter adapter;
    private List<OrderProduct> orderProductList = new ArrayList<>();
    @BindView(R.id.my_recycler_view) RecyclerView recyclerView;
    private View view;
    GestureDetectorCompat gestureDetector;
    ActionMode actionMode;

    public interface OnOrderedProductClickListener{
        void onOrderedProductClickListener(OrderProduct orderProduct, View view, int typeClick);
    }

    public interface OnDispatchClickListener{
        void onDispatchClickListener(OrderDispatch orderDispatch, View view);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=  inflater.inflate(
                R.layout.recycler_view, container, false);
        ButterKnife.bind(this, view);
        orderProductList = OrderProduct.getList();
        adapter = new OrderedProductAdapter(orderProductList, getActivity(), this, this);
        recyclerView.setAdapter(adapter);
        //recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnOrderedProductClickListener){
            listener = (OnOrderedProductClickListener) context;
        }else{
            throw new RuntimeException(context.toString() +
                    " must implement OnEstablecimientoClickListener");
        }
        if (context instanceof OnDispatchClickListener){
            listenerDispatch = (OnDispatchClickListener) context;
        }else{
            throw new RuntimeException(context.toString() +
                    " must implement OnDispatchClickListener");
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
        listenerDispatch = null;
    }

    @Override
    public void onOrderedProductClickListener(OrderProduct orderProduct, View view, int typeClick) {
            listener.onOrderedProductClickListener(orderProduct, view, typeClick);
    }

    @Override
    public void onDispatchClicckListener(OrderDispatch orderDispatch, View view) {
        listenerDispatch.onDispatchClickListener(orderDispatch, view);
    }

    private void myToggleSelection(int idx) {
        adapter.toggleSelection(idx);
        String title = getString(R.string.selected_count, adapter.getSelectedItemCount());
        actionMode.setTitle(title);
    }


}
