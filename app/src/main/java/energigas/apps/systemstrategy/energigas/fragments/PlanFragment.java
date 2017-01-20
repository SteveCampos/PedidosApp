package energigas.apps.systemstrategy.energigas.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.race604.drawable.wave.WaveDrawable;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.adapters.AdapterHistorial;
import energigas.apps.systemstrategy.energigas.adapters.EstablecimientoAdapter;
import energigas.apps.systemstrategy.energigas.entities.Establecimiento;
import energigas.apps.systemstrategy.energigas.entities.Historial;
import energigas.apps.systemstrategy.energigas.interfaces.ListenerClickItemHistorial;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PlanFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PlanFragment extends Fragment implements EstablecimientoAdapter.OnEstablecimientoClickListener, OnDateSelectedListener, ListenerClickItemHistorial {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Unbinder unbinder;

   /* @BindView(R.id.calendarView)
    MaterialCalendarView calendarView;*/

   /* @BindView(R.id.scrollView)
    NestedScrollView scrollView;*/

    @BindView(R.id.listViewHIstorial)
    ListView listViewHistorial;

    @BindView(R.id.imgIndicador1)
    ImageView imgIndicador1;

    @BindView(R.id.imgIndicador2)
    ImageView imgIndicador2;

    @BindView(R.id.imgIndicador3)
    ImageView imgIndicador3;

    @BindView(R.id.imgIndicador4)
    ImageView imgIndicador4;

    private WaveDrawable mWaveDrawable1;
    private WaveDrawable mWaveDrawable2;
    private WaveDrawable mWaveDrawable3;
    private WaveDrawable mWaveDrawable4;

    private static final String TAG = "PlanFragment";

    public PlanFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PlanFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PlanFragment newInstance(String param1, String param2) {
        PlanFragment fragment = new PlanFragment();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(
                R.layout.fragment_plan, container, false);
        unbinder = ButterKnife.bind(this, view);

        //VIEWS

        mWaveDrawable1 = new WaveDrawable(getActivity(), R.drawable.ic_rich);
        mWaveDrawable2 = new WaveDrawable(getActivity(), R.drawable.ic_refresh);
        mWaveDrawable3 = new WaveDrawable(getActivity(), R.drawable.ic_briefcase);
        mWaveDrawable4 = new WaveDrawable(getActivity(), R.drawable.ic_coin);

        imgIndicador1.setImageDrawable(mWaveDrawable1);
        imgIndicador2.setImageDrawable(mWaveDrawable2);
        imgIndicador3.setImageDrawable(mWaveDrawable3);
        imgIndicador4.setImageDrawable(mWaveDrawable4);

        mWaveDrawable1.setLevel(5418);
        mWaveDrawable1.setWaveAmplitude(3);
        mWaveDrawable1.setWaveLength(128);
        mWaveDrawable1.setWaveSpeed(3);

        mWaveDrawable2.setLevel(5418);
        mWaveDrawable2.setWaveAmplitude(3);
        mWaveDrawable2.setWaveLength(128);
        mWaveDrawable2.setWaveSpeed(3);


        mWaveDrawable3.setLevel(5418);
        mWaveDrawable3.setWaveAmplitude(3);
        mWaveDrawable3.setWaveLength(128);
        mWaveDrawable3.setWaveSpeed(3);


        mWaveDrawable4.setLevel(5418);
        mWaveDrawable4.setWaveAmplitude(3);
        mWaveDrawable4.setWaveLength(128);
        mWaveDrawable4.setWaveSpeed(3);


        setIndeterminateMode(false);
        initListeView();


        initListeView();
        return view;
    }



    private void initListeView() {
        listViewHistorial.setAdapter(new AdapterHistorial(getActivity(), 0, Historial.getHistorialList(), this));
        // View view = LayoutInflater.from(getActivity()).inflate(R.layout.layout_cabecera_item, null);
        // listViewHistorial.addHeaderView(view);
    }

    private void setIndeterminateMode(boolean indeterminate) {
        mWaveDrawable1.setIndeterminate(indeterminate);
        mWaveDrawable2.setIndeterminate(indeterminate);
        mWaveDrawable3.setIndeterminate(indeterminate);
        mWaveDrawable4.setIndeterminate(indeterminate);

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onEstablecimientoClickListener(Establecimiento establecimiento, View view) {

    }

    @Override
    public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
        Toast.makeText(getActivity(), DateFormat.format("yyyy-MM-dd", date.getDate()), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void OnItemClick(Historial historial) {
        Log.d(TAG, "" + historial.getCantidad());
    }
}
