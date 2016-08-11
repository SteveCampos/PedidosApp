package energigas.apps.systemstrategy.energigas.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.adapters.ChargesAdapter;
import energigas.apps.systemstrategy.energigas.entities.Charges;

/**
 * Created by Kike on 1/08/2016.
 */

public class ChargesFragment extends Fragment implements ChargesAdapter.OnChargesClickListener {

    public OnChargesClickListener listener;


    private RecyclerView recyclerView;

    public ChargesFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragmen_charges, container, false);
        ButterKnife.bind(this, rootView);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.rv_charges);

        ChargesAdapter adapter = new ChargesAdapter(Charges.getCharges(), this);
        recyclerView.setAdapter(adapter);
        //recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return rootView;
    }

    @Override
    public void onChargesClickListener(Charges charges, View view) {
        Snackbar.make(view, charges.getmDate(), Snackbar.LENGTH_LONG).show();
    }

    public interface OnChargesClickListener {
        void onCharfesClickListener(Charges charges, View view);
    }
}
