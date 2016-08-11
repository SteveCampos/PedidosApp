package energigas.apps.systemstrategy.energigas.fragments;

import android.content.Context;
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
import energigas.apps.systemstrategy.energigas.adapters.CajaGastoAdapter;
import energigas.apps.systemstrategy.energigas.entities.CajaGasto;
import energigas.apps.systemstrategy.energigas.entities.Expenses;

/**
 * Created by kikerojas on 30/07/2016.
 */


public class CajaGastoFragment extends Fragment implements CajaGastoAdapter.OnCajaGastoClickListener,CajaGastoAdapter.OnAddnewCajaGasto{


    public OnCajaGastoClickListener listener;

    private OnAddnewCajaGasto listenerAdd;

    private RecyclerView recyclerView;

    public CajaGastoFragment() {

    }
    CajaGastoAdapter adapter;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_expenses, container, false);
        ButterKnife.bind(this,rootView);

        recyclerView = (RecyclerView)rootView.findViewById(R.id.rv_expenses);

        adapter = new CajaGastoAdapter(CajaGasto.getListCajaGastos(),getActivity(),this,this); //
        recyclerView.setAdapter(adapter);
        //recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return rootView;
    }

    @Override
    public void onCajaGastoClickListener(CajaGasto expenses, View view) {
        Snackbar.make(view,expenses.getFechaCreacion(),Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onAddnewCajaGasto(String date, Double total) {
        listenerAdd.onAddnewCajaGasto(date,total);
    }

    public interface OnCajaGastoClickListener{
        void onCajaGastoClickListener(CajaGasto expenses, View view);
    }


    public void  addnewExpenses(CajaGasto expenses){
        adapter.addnewCajaGasto(expenses);
    }

    public interface OnAddnewCajaGasto{
        void onAddnewCajaGasto (String date,Double total);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnAddnewCajaGasto){
            listenerAdd = (OnAddnewCajaGasto) context;
        }else{
            throw new RuntimeException(context.toString() +
                    " must implement OnAddnewExpenses");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listenerAdd = null;
    }
}
