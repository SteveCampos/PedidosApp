package energigas.apps.systemstrategy.energigas.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.BoolRes;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.activities.DispatchActivity;
import energigas.apps.systemstrategy.energigas.activities.PrintDispatch;
import energigas.apps.systemstrategy.energigas.adapters.AlmacenAdapter;
import energigas.apps.systemstrategy.energigas.dialogs.AlertDialogFragment;
import energigas.apps.systemstrategy.energigas.entities.Almacen;
import energigas.apps.systemstrategy.energigas.entities.Establecimiento;
import energigas.apps.systemstrategy.energigas.entities.Pedido;
import energigas.apps.systemstrategy.energigas.utils.Session;

/**
 * A simple {@link Fragment} subclass.
 */
public class AlmacenFragment extends Fragment implements AlmacenAdapter.OnAlmacenClickListener {

    private List<Almacen> almacenList ;
    private RecyclerView recyclerView;
    private AlmacenAdapter adapter;
    private Pedido pedido;
    private Establecimiento establecimiento;

    public AlmacenFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        recyclerView= (RecyclerView) inflater.inflate(
                R.layout.recycler_view, container, false);
        pedido = Pedido.find(Pedido.class," pe_Id=? ",new String[]{Session.getPedido(getActivity()).getPeId()+""}).get(0);
        almacenList = Almacen.find(Almacen.class," establecimiento_Id = ?  ",new String[]{pedido.getEstablecimientoId()+""});
        adapter = new AlmacenAdapter(almacenList, getActivity(), this);
        recyclerView.setAdapter(adapter);
        //recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return recyclerView;
    }

    @Override
    public void onAlmacenClickListener(Almacen almacen, View view, int type) {

        int position = recyclerView.getChildAdapterPosition(view);

        if (type ==1 && position == 2) {
            showAlertDialog();
            return;
        }

        String message = "REDIRECT";
        boolean redirect = false;
        Class clase = DispatchActivity.class;
        switch (position){
            case 0://DESPACHADO, REDIRECT TO PRINT
                message = "DESPACHADO";
                redirect = true;
                clase = PrintDispatch.class;
                break;
            case 1://EN PROCESO
                message = "EN PROCESO";
                redirect = true;
                clase = DispatchActivity.class;
                break;
            case 2:
                //NO PROGRAMADO
                message = "No Programado, mantenga presionado para Autoprogramar";
                break;
        }
        clase = DispatchActivity.class;
        Session.saveAlmacen(getActivity(),almacen);
        Snackbar.make(recyclerView, message, Snackbar.LENGTH_LONG).show();

        if (redirect){
            startActivity(new Intent(getActivity(), clase));
        }
    }

    private void showAlertDialog() {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        AlertDialogFragment alertDialog = AlertDialogFragment.newInstance("Autoprogramar");
        alertDialog.show(fm, "fragment_alert");
    }
}
