package energigas.apps.systemstrategy.energigas.fragments;

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
 * Created by jairc on 19/07/2016.
 */

public class StationFragment extends Fragment {

    public StationFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        RecyclerView recyclerView = (RecyclerView) inflater.inflate(
                R.layout.recycler_view, container, false);

        StationAdapter adapter = new StationAdapter(Establishment.getEstablishments(), getActivity());
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return recyclerView;
    }

}
