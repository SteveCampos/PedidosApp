package energigas.apps.systemstrategy.energigas.fragments;

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
import energigas.apps.systemstrategy.energigas.adapters.SummaryAdapter;
import energigas.apps.systemstrategy.energigas.entities.Summary;

/**
 * Created by kelvi on 19/07/2016.
 */

public class ResumentFragment extends Fragment {


    @BindView(R.id.racyclerInventory)
    RecyclerView recyclerView;

    public static ResumentFragment newInstance (){
        ResumentFragment summary = new ResumentFragment();
        return summary;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.layout_fragment_summary, container, false);
        ButterKnife.bind(this,rootView);
        SummaryAdapter adapter = new SummaryAdapter(Summary.getListSummary(), getActivity());
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return rootView;
    }
}
