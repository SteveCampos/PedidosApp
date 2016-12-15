package energigas.apps.systemstrategy.energigas.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.ButterKnife;
import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.activities.CajaGastoActivity;
import energigas.apps.systemstrategy.energigas.adapters.CajaGastoAdapter;
import energigas.apps.systemstrategy.energigas.entities.AccessFragment;
import energigas.apps.systemstrategy.energigas.entities.CajaGasto;
import energigas.apps.systemstrategy.energigas.entities.Usuario;
import energigas.apps.systemstrategy.energigas.interfaces.IntentListenerAccess;
import energigas.apps.systemstrategy.energigas.utils.AccessPrivilegesManager;
import energigas.apps.systemstrategy.energigas.utils.Session;

/**
 * Created by kikerojas on 30/07/2016.
 */


public class CajaGastoFragment extends Fragment implements CajaGastoAdapter.OnCajaGastoClickListener,IntentListenerAccess{


    private static final String  TAG = "CajaGastoFragment";
    public OnCajaGastoClickListener listener;

   // private OnAddnewCajaGasto listenerAdd;


    private RecyclerView recyclerView;

    public CajaGastoFragment() {

    }
    CajaGastoAdapter adapter;

    List<CajaGasto> cajaGastoList = new ArrayList<>();
    private Usuario mUsuario;

    private HashMap<String, Boolean> booleanHashMap;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_expenses, container, false);
        ButterKnife.bind(this,rootView);

        recyclerView = (RecyclerView)rootView.findViewById(R.id.rv_expenses);
        mUsuario= Session.getSession(getActivity());
        new AccessPrivilegesManager(getClass())
                .setListenerIntent(this)
                .setPrivilegesIsEnable(mUsuario.getUsuIUsuarioId() + "")
                .setClassDialog("CajaGastoAdapter")
                .isDialogEnable();

        cajaGastoList=getCajaGastoList();
        if(booleanHashMap!=null) {
            Log.d(TAG,"booleanHashMap"+booleanHashMap.get("CajaGastoAdapter")+"");
            adapter = new CajaGastoAdapter(cajaGastoList, getActivity(), this,booleanHashMap.get("CajaGastoAdapter"));
        }
        //adapter = new CajaGastoAdapter(CajaGasto.getListCajaGastos(),getActivity(),this,this); //
        recyclerView.setAdapter(adapter);
        //recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return rootView;
    }

    @Override
    public void onCajaGastoClickListener(int action, CajaGasto expenses, View view) {
        //Snackbar.make(view,expenses.getFechaCreacion(),Snackbar.LENGTH_LONG).show();
        listener.onCajaGastoClickListener(action,expenses,view);
    }

    @Override
    public void onAddnewCajaGasto(String date, Double total) {
      listener.onAddnewCajaGasto(date,total);
    }

    @Override
    public void onIntentListenerAcces(HashMap<String, Boolean> booleanHashMap) {
        this.booleanHashMap = booleanHashMap;
        Log.d(TAG, "TAMAÑO HASH onCajaGastoFragment: " + booleanHashMap.size());
    }

    @Override
    public void onFragmentAccess(List<AccessFragment> accessFragmentList) {

    }


    public interface OnCajaGastoClickListener{
        void onCajaGastoClickListener(int action,CajaGasto expenses, View view);
        void onAddnewCajaGasto(String date,Double total);
    }

    public void  addnewExpenses(CajaGasto expenses){
        adapter.addnewCajaGasto(expenses);
    }

    public void updateNewExpenses(View view){
        int position = recyclerView.getChildAdapterPosition(view);
        adapter.notifyItemChanged(position);
    }
    public void removeExpense(CajaGasto cajaGasto, View view){
        int position = recyclerView.getChildAdapterPosition(view);
        adapter.remove(cajaGasto, position);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnCajaGastoClickListener){
            //listenerAdd = (OnAddnewCajaGasto) context;
            listener = (OnCajaGastoClickListener) context;
        }else{
            throw new RuntimeException(context.toString() +
                    " must implement OnAddnewExpenses");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
       // listenerAdd = null;
        listener = null;
    }



    private List<CajaGasto> getCajaGastoList(){
        List<CajaGasto> list= CajaGasto.findWithQuery(CajaGasto.class, "SELECT * FROM CAJA_GASTO CG,INFORME_GASTO IG WHERE CG.CAJ_GAS_ID=IG.CAJ_GAS_ID" +
                " AND IG.ESTADO_ID=58");

        for (int i=0; i<list.size(); i++){
            CajaGasto cajaGasto = list.get(i);

            list.set(i, cajaGasto);
        }
        return  list;
    }


}
