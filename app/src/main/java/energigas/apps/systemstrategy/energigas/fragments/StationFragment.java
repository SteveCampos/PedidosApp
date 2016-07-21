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
import energigas.apps.systemstrategy.energigas.adapters.StationAdapter;
import energigas.apps.systemstrategy.energigas.entities.Establishment;

/**
 * Created by Steve on 19/07/2016.
 */

public class StationFragment extends Fragment implements StationAdapter.OnStationClickListener {


    public OnStationClickListener listener;

    public StationFragment() {

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

        StationAdapter adapter = new StationAdapter(Establishment.getEstablishments(), getActivity(), this);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return recyclerView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnStationClickListener){
            listener = (OnStationClickListener) context;
        }else{
            throw new RuntimeException(context.toString() +
                    " must implement OnStationClickListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    @Override
    public void onStationClickListener(Establishment establishment, View view) {
        listener.onStationClickListener(establishment, view);
    }

    public interface OnStationClickListener{
        void onStationClickListener(Establishment establishment, View view);
    }
}
