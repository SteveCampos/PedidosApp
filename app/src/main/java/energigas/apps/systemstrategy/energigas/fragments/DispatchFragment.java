package energigas.apps.systemstrategy.energigas.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import energigas.apps.systemstrategy.energigas.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DispatchFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DispatchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DispatchFragment extends Fragment {
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

    private Unbinder unbinder;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

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
        View view =  inflater.inflate(
                R.layout.layout_dispatch, container, false);
        unbinder = ButterKnife.bind(this, view);

        fillInputs();
        return view;
    }

    private void fillInputs() {
        inputRequestedQuantity.setText("100.00");

    }
    private void enabledInputs(boolean enabled){
        inputDispensedQuantity.setEnabled(enabled);
        inputQuantityInitial.setEnabled(enabled);
        inputQuantityFinal.setEnabled(enabled);
        input_tank_initial.setEnabled(enabled);
        input_tank_final.setEnabled(enabled);
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
    }
}
