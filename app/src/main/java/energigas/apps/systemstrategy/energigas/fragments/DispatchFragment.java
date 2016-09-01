package energigas.apps.systemstrategy.energigas.fragments;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import energigas.apps.systemstrategy.energigas.LocationVehiculeListener;
import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.activities.DispatchActivity;
import energigas.apps.systemstrategy.energigas.activities.PrintDispatch;
import energigas.apps.systemstrategy.energigas.entities.Almacen;
import energigas.apps.systemstrategy.energigas.entities.Despacho;
import energigas.apps.systemstrategy.energigas.entities.Establecimiento;
import energigas.apps.systemstrategy.energigas.entities.PedidoDetalle;
import energigas.apps.systemstrategy.energigas.entities.Usuario;
import energigas.apps.systemstrategy.energigas.interfaces.DialogGeneralListener;
import energigas.apps.systemstrategy.energigas.interfaces.OnLocationListener;
import energigas.apps.systemstrategy.energigas.utils.Constants;
import energigas.apps.systemstrategy.energigas.utils.Session;
import energigas.apps.systemstrategy.energigas.utils.Utils;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DispatchFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DispatchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DispatchFragment extends Fragment implements DispatchActivity.onNextActivity, OnLocationListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    //BINDING VIEW

    @BindView(R.id.input_requested_quantity)
    TextInputEditText inputRequestedQuantity;
    @BindView(R.id.input_dispensed_quantity)
    TextInputEditText inputDispensedQuantity;
    @BindView(R.id.input_quantity_initial)
    TextInputEditText inputQuantityInitial;
    @BindView(R.id.input_quantity_final)
    TextInputEditText inputQuantityFinal;
    @BindView(R.id.input_tank_initial_percent)
    TextInputEditText input_tank_initial;
    @BindView(R.id.input_tank_final_percent)
    TextInputEditText input_tank_final;
    @BindView(R.id.buttonContometer)
    AppCompatButton buttonContometer;


    private Unbinder unbinder;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    private Despacho despacho;
    private PedidoDetalle pedidoDetalle;
    private Establecimiento establecimiento;
    private Almacen almacen;
    private Usuario usuario;

    private OnFragmentInteractionListener mListener;
    private LocationVehiculeListener locationVehiculeListener;
    private Location latAndLong;

    public DispatchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DispatchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DispatchFragment newInstance(String param1, String param2) {
        DispatchFragment fragment = new DispatchFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(
                R.layout.layout_dispatch, container, false);
        unbinder = ButterKnife.bind(this, view);
        locationVehiculeListener = new LocationVehiculeListener(this);
        pedidoDetalle = PedidoDetalle.find(PedidoDetalle.class, " pe_Id = ? ", new String[]{Session.getPedido(getActivity()).getPeId() + ""}).get(Constants.CURRENT);
        establecimiento = Establecimiento.find(Establecimiento.class, " est_I_Establecimiento_Id = ?  ", new String[]{Session.getSessionEstablecimiento(getActivity()).getEstIEstablecimientoId() + ""}).get(Constants.CURRENT);
        almacen = Almacen.find(Almacen.class, " alm_Id = ?  ", new String[]{Session.getAlmacen(getActivity()).getAlmId() + ""}).get(Constants.CURRENT);
        usuario = Usuario.find(Usuario.class, " usu_I_Usuario_Id = ? ", new String[]{Session.getSession(getActivity()).getUsuIUsuarioId() + ""}).get(Constants.CURRENT);
        fillInputs();
        return view;
    }

    private void fillInputs() {
        inputRequestedQuantity.setText("100.00");
        enabledInputs(false);
    }

    private void enabledInputs(boolean enabled) {
        inputDispensedQuantity.setEnabled(enabled);
        inputQuantityInitial.setEnabled(enabled);
        inputQuantityFinal.setEnabled(enabled);
        input_tank_initial.setEnabled(enabled);
        input_tank_final.setEnabled(enabled);
    }

    @OnClick(R.id.buttonContometer)
    void readContometer() {
        inputDispensedQuantity.setText("100.00");
        inputQuantityInitial.setText("900.00");
        inputQuantityFinal.setText("1000.00");
        input_tank_initial.setText("56.25");
        input_tank_final.setText("62.5");


    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onNextListener() {

        if (latAndLong != null) {

            despacho = new Despacho(
                    12,
                    pedidoDetalle.getPeId(),
                    pedidoDetalle.getPdId(),
                    establecimiento.getEstIClienteId(),
                    establecimiento.getEstIEstablecimientoId(),
                    almacen.getAlmId(),
                    usuario.getUsuIUsuarioId(),
                    almacen.getPlaca(),
                    Double.parseDouble(inputQuantityInitial.getText().toString()),
                    Double.parseDouble(inputQuantityFinal.getText().toString()),
                    Double.parseDouble(inputDispensedQuantity.getText().toString()),
                    Utils.getDatePhone(),
                    Utils.getDatePhone(),
                    Utils.getDatePhone(),
                    pedidoDetalle.getProductoId(),
                    pedidoDetalle.getUnidadId(),
                    Double.parseDouble(input_tank_initial.getText().toString()),
                    Double.parseDouble(input_tank_final.getText().toString()),
                    latAndLong.getLatitude()+"",
                    latAndLong.getLongitude()+"",
                    almacen.getAlmId(),
                    pedidoDetalle.getFechaAccion(),
                    pedidoDetalle.getCantidad(),
                    Utils.getDatePhone(),
                    usuario.getUsuVUsuario(),
                    1,
                    almacen.getVehiculoId(),
                    ""

                    );


            Toast.makeText(getActivity(), "NEXT", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getActivity(), PrintDispatch.class));
            getActivity().finish();
        }

    }

    @Override
    public void setLatAndLong(Location latAndLong) {
        this.latAndLong = latAndLong;
    }

    @Override
    public Context getContextActivity() {
        return getActivity();
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);

        //void onNextListener(Despacho despacho);
    }


}
