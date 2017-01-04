package energigas.apps.systemstrategy.energigas.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.activities.PrintFacturaActivity;
import energigas.apps.systemstrategy.energigas.activities.SellActivity;
import energigas.apps.systemstrategy.energigas.activities.StationOrderActivity;
import energigas.apps.systemstrategy.energigas.adapters.StationDispatchsAdapter;
import energigas.apps.systemstrategy.energigas.entities.AccessFragment;
import energigas.apps.systemstrategy.energigas.entities.Despacho;
import energigas.apps.systemstrategy.energigas.entities.Usuario;
import energigas.apps.systemstrategy.energigas.interfaces.DialogGeneralListener;
import energigas.apps.systemstrategy.energigas.interfaces.IntentListenerAccess;
import energigas.apps.systemstrategy.energigas.utils.AccessPrivilegesManager;
import energigas.apps.systemstrategy.energigas.utils.Session;


public class StationDispatchsFragment extends Fragment implements ActionMode.Callback, StationDispatchsAdapter.OnStationDispatchClickListener, IntentListenerAccess {

    private static final String TAG = StationDispatchsFragment.class.getSimpleName();
    private List<Despacho> dispatchList = new ArrayList<>();
    @BindView(R.id.my_recycler_view) RecyclerView recyclerView;
    private View view;
    private StationDispatchsAdapter adapter;
    private ActionMode mActionMode;
    private Usuario usuario;
    private HashMap<String, Boolean> booleanHashMap;

    /*
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;*/

    public StationDispatchsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     */
    /*
    // TODO: Rename and change types and number of parameters
    public static StationDispatchsFragment newInstance(String param1, String param2) {
        StationDispatchsFragment fragment = new StationDispatchsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }*/
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(
                R.layout.recycler_view, container, false);
        ButterKnife.bind(this, view);
        usuario = Session.getSession(getActivity());
        new AccessPrivilegesManager(getClass())
                .setListenerIntent(this)
                .setPrivilegesIsEnable(usuario.getUsuIUsuarioId() + "")
                .setClassIntent(StationOrderActivity.class)
                .isIntentEnable();
        initiViews();
        return view;
    }


    @Override
    public void onIntentListenerAcces(HashMap<String, Boolean> booleanHashMap) {
        this.booleanHashMap = booleanHashMap;
    }

    @Override
    public void onFragmentAccess(List<AccessFragment> accessFragmentList) {

    }

    private void initiViews() {
        dispatchList = Despacho.find(Despacho.class, " establecimiento_Id = ? ", new String[]{Session.getSessionEstablecimiento(getActivity()).getEstIEstablecimientoId() + ""});

        adapter = new StationDispatchsAdapter(dispatchList, getActivity(), this);
        recyclerView.setAdapter(adapter);
        //recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //implementRecyclerViewClickListeners();
    }

    @Override
    public void onStationDispatchClickListener(Despacho orderDispatch, View view, int type) {
        //If ActionMode not null select item

        int position = recyclerView.getChildAdapterPosition(view);
        switch (type) {
            case 0:
                if (mActionMode != null)
                    onListItemSelect(position, orderDispatch);
                else
                    Log.d(TAG, "ON CLICK LISTENER");
                //startActivity(new Intent(getActivity(), DispatchActivity.class));
                break;
            case 1:
                onListItemSelect(position, orderDispatch);
                break;
        }
    }
    /*
    //Implement item click and long click over recycler view
    private void implementRecyclerViewClickListeners() {

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new RecyclerTouchListener.RecyclerClick_Listener() {
            @Override
            public void onClick(View view, int position) {
                //If ActionMode not null select item
                if (mActionMode != null)
                    onListItemSelect(position);
            }

            @Override
            public void onLongClick(View view, int position) {
                //Select item on long click
                onListItemSelect(position);
            }
        }));
    }*/

    //List item select method
    private void onListItemSelect(int position, Despacho despacho) {
        adapter.toggleSelection(position, despacho);//Toggle the selection
        setActionMode(adapter.getSelectedCount());
    }

    private void setActionMode(int countSelected) {
        boolean hasCheckedItems = countSelected > 0;//Check if any items are already selected or not

        if (hasCheckedItems && mActionMode == null)
            // there are some selected items, start the actionMode
            mActionMode = ((AppCompatActivity) getActivity()).startSupportActionMode(this);
        else if (!hasCheckedItems && mActionMode != null)
            // there no selected items, finish the actionMode
            mActionMode.finish();
        if (mActionMode != null)
            //set action mode title on item selection
            mActionMode.setTitle(String.valueOf(countSelected) + " Despachos");
    }

    //Set action mode null after use
    public void setNullToActionMode() {
        if (mActionMode != null)
            mActionMode = null;
    }


    // Called when the action mode is created; startActionMode() was called
    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        // Inflate a menu resource providing context menu items
        MenuInflater inflater = mode.getMenuInflater();
        inflater.inflate(R.menu.dispatc_menu_print, menu);
        return true;
    }

    // Called each time the action mode is shown. Always called after onCreateActionMode, but
    // may be called multiple times if the mode is invalidated.
    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        //Sometimes the meu will not be visible so for that we need to set their visibility manually in this method
        //So here show action menu according to SDK Levels
        if (Build.VERSION.SDK_INT < 11) {
            MenuItemCompat.setShowAsAction(menu.findItem(R.id.print), MenuItemCompat.SHOW_AS_ACTION_NEVER);
        } else {
            menu.findItem(R.id.print).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        }

        return true;
    }


    // Called when the user selects a contextual menu item
    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
        switch (item.getItemId()) {
            case R.id.print:
                createAndShowAlertDialog();

                return true;
            default:
                return false;
        }
    }

    @Override
    public void onDestroyActionMode(ActionMode mode) {
        adapter.removeSelection(adapter.getSelectedIds());
        mActionMode.finish();
        setNullToActionMode();
    }


    private void createAndShowAlertDialog() {
        new DialogGeneral(getActivity()).setMessages("Generar comprobante", "¿Esta seguro de realizar la venta para " + adapter.getStationDispatchesSelected().size() + " despachos?").setTextButtons("SI", "NO").setCancelable(false).showDialog(new DialogGeneralListener() {
            @Override
            public void onSavePressed(AlertDialog alertDialog) {
                List<String> strings = adapter.getIdsDispatchesSelected();
                iniciarVenta(strings);
                onDestroyActionMode(mActionMode);
                alertDialog.dismiss();
            }

            @Override
            public void onCancelPressed(AlertDialog alertDialog) {
                alertDialog.dismiss();
            }
        });
    }

    private void createAndShowaaAlertDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Generar Vista Previa Factura");
        builder.setMessage(adapter.getStationDispatchesSelected().size() + " Despachos Seleccionados");

        builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //TODO
                List<String> strings = adapter.getIdsDispatchesSelected();
                dialog.dismiss();
                iniciarVenta(strings);
                onDestroyActionMode(mActionMode);
                // print();
            }
        });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //TODO
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void iniciarVenta(List<String> strings) {
        Session.saveIdsDespachos(strings, getActivity());
        startActivity(new Intent(getActivity(), SellActivity.class));
    }



    /*
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }*/

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.

     public interface OnFragmentInteractionListener {
     // TODO: Update argument type and name
     void onFragmentInteraction(Uri uri);
     }
     */
}
