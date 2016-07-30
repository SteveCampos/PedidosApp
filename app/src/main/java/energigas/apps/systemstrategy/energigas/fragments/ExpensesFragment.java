package energigas.apps.systemstrategy.energigas.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.adapters.ExpensesAdapter;
import energigas.apps.systemstrategy.energigas.entities.Expenses;

/**
 * Created by kikerojas on 30/07/2016.
 */

public class ExpensesFragment extends Fragment implements ExpensesAdapter.OnExpensesClickListener{


    public OnExpensesClickListener listener;


    private RecyclerView recyclerView;

    public ExpensesFragment() {

    }
    private TextView tvtotal;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_expenses, container, false);
        ButterKnife.bind(this,rootView);

        recyclerView = (RecyclerView)rootView.findViewById(R.id.rv_expenses);

        ExpensesAdapter adapter = new ExpensesAdapter(Expenses.getListExpenses(),getActivity(),this,tvtotal);
        recyclerView.setAdapter(adapter);
        //recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return rootView;
    }

    @Override
    public void onExpensesClickListener(Expenses expenses, View view) {
        Snackbar.make(view,expenses.getmType(),Snackbar.LENGTH_LONG).show();
    }

    public interface OnExpensesClickListener{
        void onExpensesClickListener(Expenses expenses, View view);
    }
}
