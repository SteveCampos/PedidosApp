package energigas.apps.systemstrategy.energigas.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.adapters.EstablecimientoAdapter;
import energigas.apps.systemstrategy.energigas.entities.Establecimiento;
import energigas.apps.systemstrategy.energigas.utils.Utils;

/**
 * Created by Steve on 19/07/2016.
 */

public class EstablecimientoFragment extends Fragment implements EstablecimientoAdapter.OnEstablecimientoClickListener {


    public OnEstablecimientoClickListener listener;
    private EstablecimientoAdapter adapter;
    private List<Establecimiento> establecimientoList = new ArrayList<>();
    private RecyclerView recyclerView;

    public EstablecimientoFragment() {

    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        recyclerView= (RecyclerView) inflater.inflate(
                R.layout.recycler_view, container, false);

        establecimientoList = Establecimiento.getList();
        adapter = new EstablecimientoAdapter(establecimientoList, getActivity(), this);
        recyclerView.setAdapter(adapter);
        //recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return recyclerView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnEstablecimientoClickListener){
            listener = (OnEstablecimientoClickListener) context;
        }else{
            throw new RuntimeException(context.toString() +
                    " must implement OnEstablecimientoClickListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    @Override
    public void onEstablecimientoClickListener(Establecimiento establecimiento, View view) {
        //Log.d(Utils.TAG, "EstablecimientoFragment onEstablecimientoClickListener: "+ position);
        int childAdapterPosition = recyclerView.getChildAdapterPosition(view);
        Log.d(Utils.TAG, "recyclerView.childAdapterPosition(): "+ childAdapterPosition);
        if (establecimientoList.size()>childAdapterPosition && childAdapterPosition >= 0){
            /*establecimientoList.remove(childAdapterPosition);
            adapter.notifyItemRemoved(childAdapterPosition);*/
            listener.onEstablecimientoClickListener(establecimiento, view);
        }

    }

    public interface OnEstablecimientoClickListener {
        void onEstablecimientoClickListener(Establecimiento establecimiento, View view);
    }

}
