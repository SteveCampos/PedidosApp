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
import energigas.apps.systemstrategy.energigas.adapters.StationAdapter;
import energigas.apps.systemstrategy.energigas.entities.Station;
import energigas.apps.systemstrategy.energigas.utils.Utils;

/**
 * Created by Steve on 19/07/2016.
 */

public class StationFragment extends Fragment implements StationAdapter.OnStationClickListener {


    public OnStationClickListener listener;
    private StationAdapter adapter;
    private List<Station> stationList = new ArrayList<>();
    private RecyclerView recyclerView;

    public StationFragment() {

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

        stationList = Station.getEstablishments();
        adapter = new StationAdapter(stationList, getActivity(), this);
        recyclerView.setAdapter(adapter);
        //recyclerView.setHasFixedSize(true);
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
    public void onStationClickListener(Station station, View view) {
        //Log.d(Utils.TAG, "StationFragment onStationClickListener: "+ position);
        int childAdapterPosition = recyclerView.getChildAdapterPosition(view);
        Log.d(Utils.TAG, "recyclerView.childAdapterPosition(): "+ childAdapterPosition);
        if (stationList.size()>childAdapterPosition && childAdapterPosition >= 0){
            stationList.remove(childAdapterPosition);
            adapter.notifyItemRemoved(childAdapterPosition);
            listener.onStationClickListener(station, view);
        }

    }

    public interface OnStationClickListener{
        void onStationClickListener(Station station, View view);
    }

}
