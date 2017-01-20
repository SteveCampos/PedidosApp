package energigas.apps.systemstrategy.energigas.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.activities.StationOrderActivity;
import energigas.apps.systemstrategy.energigas.entities.AccessFragment;
import energigas.apps.systemstrategy.energigas.entities.Cliente;
import energigas.apps.systemstrategy.energigas.entities.Establecimiento;
import energigas.apps.systemstrategy.energigas.entities.GeoUbicacion;
import energigas.apps.systemstrategy.energigas.entities.Persona;
import energigas.apps.systemstrategy.energigas.entities.Usuario;
import energigas.apps.systemstrategy.energigas.interfaces.IntentListenerAccess;
import energigas.apps.systemstrategy.energigas.utils.AccessPrivilegesManager;
import energigas.apps.systemstrategy.energigas.utils.Constants;
import energigas.apps.systemstrategy.energigas.utils.Session;
import energigas.apps.systemstrategy.energigas.utils.Utils;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentStationInformation.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentStationInformation#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentStationInformation extends Fragment implements OnMapReadyCallback {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Establecimiento establecimiento;
    private GeoUbicacion geoUbicacion;
    private Persona persona;
    private OnFragmentInteractionListener mListener;
    private static final String TAG = "FragmentInfo";
    /*@BindView(R.id.btn_map)
    AppCompatButton buttonMap;*/
    @BindView(R.id.frag_st_title)
    TextView frag_st_title;
    /*@BindView(R.id.frag_st_description)
    AppCompatTextView frag_st_description;
    @BindView(R.id.frag_st_lt_long)
    AppCompatTextView frag_st_lt_long;
    @BindView(R.id.frag_st_cliente_name)
    AppCompatTextView frag_st_cliente_name;
    @BindView(R.id.est_frag_description)
    AppCompatTextView est_frag_description;
    @BindView(R.id.est_frag_iddocument)
    AppCompatTextView est_frag_iddocument;
    @BindView(R.id.est_frag_lati_long)
    AppCompatTextView est_frag_lati_long;*/

    private Usuario usuario;


    MapView mapView;
    GoogleMap map;

   /* @BindView(R.id.scroll)
    NestedScrollView nestedScrollView;*/

    public FragmentStationInformation() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentStationInformation.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentStationInformation newInstance(String param1, String param2) {
        FragmentStationInformation fragment = new FragmentStationInformation();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttachFragment(Fragment childFragment) {
        Log.d(TAG, "onAttachFragment");
        super.onAttachFragment(childFragment);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_station_information, container, false);
        ButterKnife.bind(this, view);
        // Needs to call MapsInitializer before doing any CameraUpdateFactory calls


        MapsInitializer.initialize(getActivity());

        mapView = (MapView) view.findViewById(R.id.mapview);
        mapView.onCreate(savedInstanceState);
//        Log.d("MAPVIEW", "GoogleMap: " + mapView.getAccessibilityClassName());
        mapView.onResume();
        // Gets to GoogleMap from the MapView and does initialization stuff
        mapView.getMapAsync(this);
        //mapView.setOnTouchListener(this);

        usuario = Session.getSession(getActivity());
        new AccessPrivilegesManager(getClass())
                .setViews(frag_st_title)
                .setPrivilegesIsEnable(usuario.getUsuIUsuarioId() + "");


        establecimiento = Establecimiento.find(Establecimiento.class, " est_I_Establecimiento_Id = ? ", Session.getSessionEstablecimiento(getActivity()).getEstIEstablecimientoId() + "").get(Constants.CURRENT);
        geoUbicacion = GeoUbicacion.find(GeoUbicacion.class, " ub_Id = ? ", new String[]{establecimiento.getUbId() + ""}).get(Constants.CURRENT);
        Cliente cliente = Cliente.find(Cliente.class, " cli_I_Cliente_Id=?", new String[]{establecimiento.getEstIClienteId() + ""}).get(Constants.CURRENT);
        persona = Persona.find(Persona.class, " per_I_Persona_Id = ? ", new String[]{cliente.getCliIPersonaId() + ""}).get(Constants.CURRENT);

        Log.d(TAG, "OBJECT CLIENTE" + cliente);
        senddata();
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onActivityCreated");
        super.onActivityCreated(savedInstanceState);
    }


    @Override
    public void onResume() {
        Log.d(TAG, "onResume");
        super.onResume();
    }
    //fragment is active


    @Override
    public void onDetach() {
        Log.d(TAG, "onDetach");
        super.onDetach();
        mListener = null;
    }

    public void senddata() {
        frag_st_title.setText(establecimiento.getEstVDescripcion() + "");
      /*  frag_st_description.setText(geoUbicacion.getDescripcion() + "");
        frag_st_lt_long.setText(geoUbicacion.getLatitud() + "," + geoUbicacion.getLongitud() + "");
        frag_st_cliente_name.setText(persona.getPerVRazonSocial() + "");
        est_frag_description.setText(establecimiento.getEstVDescripcion());
        est_frag_iddocument.setText(persona.getPerVDocIdentidad() + "");
        est_frag_lati_long.setText(geoUbicacion.getLatitud() + "," + geoUbicacion.getLongitud() + "");*/
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
        /*
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Log.d("MAPVIEW", "GoogleMap: " + googleMap.getCameraPosition());
        map = googleMap;
        map.getUiSettings().setMyLocationButtonEnabled(false);
        map.setMyLocationEnabled(true);
        LatLng latLng = new LatLng(Double.parseDouble(geoUbicacion.getLatitud()), Double.parseDouble(geoUbicacion.getLongitud()));
        MarkerOptions markerOptions = new MarkerOptions().position(new LatLng(latLng.latitude, latLng.longitude)).title(geoUbicacion.getDescripcion());
        markerOptions.icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_marker_gasoline));
        map.addMarker(markerOptions);
        CameraUpdate center = CameraUpdateFactory.newLatLng(new LatLng(latLng.latitude, latLng.longitude));
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(15);
        map.moveCamera(center);
        map.animateCamera(zoom);
        map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                String uri = "geo: " + marker.getPosition().latitude + "," + marker.getPosition().longitude + "";
                startActivity(new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse(uri)));

                return false;
            }
        });
    }


    /* @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                nestedScrollView.requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_UP:
                nestedScrollView.requestDisallowInterceptTouchEvent(false);
            case MotionEvent.ACTION_CANCEL:
                nestedScrollView.requestDisallowInterceptTouchEvent(false);
                break;
        }
        return mapView.onTouchEvent(event);
    }*/


    /*@OnClick(R.id.btn_map)
    public void showMap() {
        float latitude = (float) Double.parseDouble(geoUbicacion.getLatitud());
        float longitude = (float) Double.parseDouble(geoUbicacion.getLongitud());
        String uri = String.format(Locale.getDefault(), "geo:%1$f,%2$f?q=%1$f,%2$f(%3$s)", latitude, longitude, establecimiento.getEstVDescripcion());
        Log.d(TAG, "URI: " + uri);
        Utils.showMap(getActivity(), Uri.parse(uri));
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
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
