package energigas.apps.systemstrategy.energigas.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.activities.DespachoActivity;
import energigas.apps.systemstrategy.energigas.activities.EditarDespacho;
import energigas.apps.systemstrategy.energigas.activities.PrintDispatch;
import energigas.apps.systemstrategy.energigas.adapters.DespachoAdapter;
import energigas.apps.systemstrategy.energigas.dialogs.DialogOptions;
import energigas.apps.systemstrategy.energigas.entities.AccessFragment;
import energigas.apps.systemstrategy.energigas.entities.Despacho;
import energigas.apps.systemstrategy.energigas.entities.Pedido;
import energigas.apps.systemstrategy.energigas.entities.Usuario;
import energigas.apps.systemstrategy.energigas.interfaces.DialogGeneralListener;
import energigas.apps.systemstrategy.energigas.interfaces.IntentListenerAccess;
import energigas.apps.systemstrategy.energigas.utils.AccessPrivilegesManager;
import energigas.apps.systemstrategy.energigas.utils.Constants;
import energigas.apps.systemstrategy.energigas.utils.Session;

/**
 * A simple {@link Fragment} subclass.
 */
public class DespachoFragment extends Fragment implements DespachoAdapter.OnDespachoClickListener, IntentListenerAccess {

    private List<Despacho> list;
    @BindView(R.id.my_recycler_view)
    RecyclerView recyclerView;
    private View view;
    private DespachoAdapter adapter;
    private Pedido pedido;
    private Usuario usuario;
    private HashMap<String, Boolean> booleanHashMap;

    public DespachoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        pedido = Session.getPedido(getActivity());
        view = inflater.inflate(R.layout.recycler_view, container, false);
        ButterKnife.bind(this, view);
        usuario = Session.getSession(getActivity());

        new AccessPrivilegesManager(getClass())
                .setListenerIntent(this)
                .setPrivilegesIsEnable(usuario.getUsuIUsuarioId() + "")
                .setClassIntent(PrintDispatch.class)
                .isIntentEnable();


        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        list = Despacho.find(Despacho.class, " pe_Id=?  ", new String[]{pedido.getPeId() + ""});
        adapter = new DespachoAdapter(list, getActivity(), this);

        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);

        return view;
    }


    @Override
    public void onDespachoClickListener(Despacho despacho, View view) {
        Session.saveDespacho(getActivity(), despacho);
        if (booleanHashMap != null) {
            if (booleanHashMap.get(PrintDispatch.class.getSimpleName())) {
                startActivity(new Intent(getActivity(), PrintDispatch.class));
            }
        }

    }

    @Override
    public void onLongDespachoClickListener(Despacho despacho, View view) {
        initDialogOptions(despacho, view);
    }

    @Override
    public void onIntentListenerAcces(HashMap<String, Boolean> booleanHashMap) {
        this.booleanHashMap = booleanHashMap;
    }

    @Override
    public void onFragmentAccess(List<AccessFragment> accessFragmentList) {
//Only for fragment
    }

    private void initDialogOptions(Despacho des, View view) {
        final Despacho despacho = des;
        if (despacho.getCompId() > 0) {
            Toast.makeText(getActivity(), "Despacho ya facturado", Toast.LENGTH_SHORT).show();
            return;
        }

        new DialogOptions(getActivity()).setCancelable(true).showDialog(new DialogOptions.OnDialogOpetions() {
            @Override
            public void onEditItemClickListener(AlertDialog alertDialog) {

                String listStrings = "";

                ObjectMapper mapper = new ObjectMapper();
                StringWriter sw = new StringWriter();


                try {
                    mapper.writeValue(sw, despacho);
                    listStrings = sw.toString();
                    Intent intent = new Intent(getActivity(), EditarDespacho.class);
                    intent.putExtra("PEDIDO", listStrings);
                    getActivity().startActivity(intent);
                    alertDialog.dismiss();


                } catch (IOException e) {
                    e.printStackTrace();

                }


            }

            @Override
            public void onDeleteItemClickListener(AlertDialog alertDialog) {
                alertDialog.dismiss();
                eliminarDespacho(despacho);


            }
        });
    }

    private void eliminarDespacho(final Despacho despacho) {
        new DialogGeneral(getActivity()).setCancelable(false).setTextButtons("SI", "NO").setMessages("Atencion", "¿Esta seguro de eliminar el despacho " + despacho.getSerie() + "-" + despacho.getNumero() + "?").showDialog(new DialogGeneralListener() {
            @Override
            public void onSavePressed(AlertDialog alertDialog) {

                despacho.setEstadoId(Constants.ESTADO_ELIMINAR_DESPACHO);
                despacho.save();
                alertDialog.dismiss();
            }

            @Override
            public void onCancelPressed(AlertDialog alertDialog) {
                alertDialog.dismiss();
            }
        });
    }
}
