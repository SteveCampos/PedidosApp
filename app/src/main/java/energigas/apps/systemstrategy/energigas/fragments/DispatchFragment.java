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
import energigas.apps.systemstrategy.energigas.entities.CajaLiquidacion;
import energigas.apps.systemstrategy.energigas.entities.Despacho;
import energigas.apps.systemstrategy.energigas.entities.Establecimiento;
import energigas.apps.systemstrategy.energigas.entities.Pedido;
import energigas.apps.systemstrategy.energigas.entities.PedidoDetalle;
import energigas.apps.systemstrategy.energigas.entities.Serie;
import energigas.apps.systemstrategy.energigas.entities.Usuario;
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


    private static final String TAG = "DispatchFragment";
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
    @BindView(R.id.btnDistpach)
    AppCompatButton compatButtonDistpach;

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
    private Pedido pedido;
    private Serie serie;
    private CajaLiquidacion cajaLiquidacion;

    private OnFragmentInteractionListener mListener;
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
        cajaLiquidacion = CajaLiquidacion.find(CajaLiquidacion.class," liq_Id = ? ",new String[]{Session.getCajaLiquidacion(getActivity()).getLiqId()+""}).get(Constants.CURRENT);
        pedido = Pedido.find(Pedido.class, " pe_Id = ? ", new String[]{Session.getPedido(getActivity()).getPeId() + ""}).get(Constants.CURRENT);
        pedidoDetalle = PedidoDetalle.find(PedidoDetalle.class, " pe_Id = ? ", new String[]{Session.getPedido(getActivity()).getPeId() + ""}).get(Constants.CURRENT);
        establecimiento = Establecimiento.find(Establecimiento.class, " est_I_Establecimiento_Id = ?  ", new String[]{Session.getSessionEstablecimiento(getActivity()).getEstIEstablecimientoId() + ""}).get(Constants.CURRENT);
        almacen = Almacen.find(Almacen.class, " alm_Id = ?  ", new String[]{Session.getAlmacen(getActivity()).getAlmId() + ""}).get(Constants.CURRENT);
        usuario = Usuario.find(Usuario.class, " usu_I_Usuario_Id = ? ", new String[]{Session.getSession(getActivity()).getUsuIUsuarioId() + ""}).get(Constants.CURRENT);
        serie = Serie.findWithQuery(Serie.class, Utils.getQueryForSerie(usuario.getUsuIUsuarioId(), Constants.TIPO_ID_DEVICE_CELULAR, Constants.TIPO_ID_COMPROBANTE_DESPACHO), null).get(Constants.CURRENT);
        fillInputs();
        saveDespacho();
        return view;
    }

    private void saveDespacho() {

        String numeroDespacho = Despacho.findWithQuery(Despacho.class, Utils.getQueryForNumberDistPach(), new String[]{}).get(Constants.CURRENT).getNumero();


    }

    @OnClick(R.id.btnDistpach)
    public void btnDistpach(View view) {
        Toast.makeText(getActivity(), inputRequestedQuantity.getText().toString(), Toast.LENGTH_SHORT).show();
        despacho.setCantidadDespachada(Double.parseDouble(inputRequestedQuantity.getText().toString()));
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

        /** Guardar los datos del cronometro**/



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

            /**Guardar los datos  de posicion gps**/
            despacho.setLatitud(latAndLong.getLatitude() + "");
            despacho.setLongitud(latAndLong.getLongitude() + "");
            /**Enviar Importe**/
            despacho.setImporte(despacho.getPrecioUnitarioCIGV() * despacho.getCantidadDespachada());
            pedidoDetalle.setCantidadAtendida(despacho.getCantidadDespachada());
            Toast.makeText(getActivity(), "NEXT", Toast.LENGTH_SHORT).show();
            Long estad = despacho.save();
            Toast.makeText(getActivity(), "ESTADO: " + estad, Toast.LENGTH_SHORT).show();
            Session.saveDespacho(getActivity(), despacho);
            if (estad > 0)
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
