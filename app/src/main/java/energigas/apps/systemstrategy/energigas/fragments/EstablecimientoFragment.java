package energigas.apps.systemstrategy.energigas.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orm.SugarContext;
import com.orm.SugarRecord;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.adapters.EstablecimientoAdapter;
import energigas.apps.systemstrategy.energigas.entities.CajaLiquidacion;
import energigas.apps.systemstrategy.energigas.entities.CajaLiquidacionDetalle;
import energigas.apps.systemstrategy.energigas.entities.Establecimiento;
import energigas.apps.systemstrategy.energigas.entities.GeoUbicacion;
import energigas.apps.systemstrategy.energigas.utils.Session;
import energigas.apps.systemstrategy.energigas.utils.Utils;

/**
 * Created by Steve on 19/07/2016.
 */

public class EstablecimientoFragment extends Fragment implements EstablecimientoAdapter.OnEstablecimientoClickListener {


    private static final String TAG = EstablecimientoFragment.class.getSimpleName();
    public OnEstablecimientoClickListener listener;
    private EstablecimientoAdapter adapter;
    private List<Establecimiento> establecimientoList = new ArrayList<>();


    private CajaLiquidacion cajaLiquidacion;
    @BindView(R.id.my_recycler_view)
    RecyclerView recyclerView;
    private View view;

    public EstablecimientoFragment() {
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        //SugarContext.init(getActivity());
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStop() {
        // SugarContext.terminate();
        super.onStop();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(
                R.layout.recycler_view, container, false);
        ButterKnife.bind(this, view);
        cajaLiquidacion = CajaLiquidacion.getCajaLiquidacion(Session.getCajaLiquidacion(getActivity()).getLiqId() + "");
        establecimientoList = getEstablecimientoList();
        adapter = new EstablecimientoAdapter(establecimientoList, getActivity(), this);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }


    private List<Establecimiento> getEstablecimientoList() {

        String query = " select * from Establecimiento where liquidacion_Id_Android=?  order by orden_Atencion_Android "; //GROUP BY cd.establecimiento_Id

        List<Establecimiento> list = Establecimiento.findWithQuery(Establecimiento.class, query, new String[]{cajaLiquidacion.getLiqId() + ""});
        for (int i = 0; i < list.size(); i++) {
            List<GeoUbicacion> geoUbicacions = GeoUbicacion.find(GeoUbicacion.class, "ub_id = ?", "" + list.get(i).getUbId());
            Log.d(TAG, "List<GeoUbicacion> size: " + geoUbicacions.size());
            //
            if (geoUbicacions.size() > 0) {
                Log.d(TAG, "geoUbicacions.get(0).getDescripcion(): " + geoUbicacions.get(0).getDescripcion());
                list.get(i).setUbicacion(geoUbicacions.get(0));
            }
        }



/*
        for (int i = 0; i < list.size(); i++) {
            Establecimiento establecimiento = list.get(i);
            for (CajaLiquidacionDetalle liquidacionDetalle : CajaLiquidacionDetalle.getLiquidacionDetalle(Session.getCajaLiquidacion(getActivity()).getLiqId() + "")) {

                if (liquidacionDetalle.getEstablecimientoId() == establecimiento.getEstIEstablecimientoId()) {
                    List<GeoUbicacion> geoUbicacions = GeoUbicacion.find(GeoUbicacion.class, "ub_id = ?", "" + establecimiento.getUbId());
                    Log.d(TAG, "List<GeoUbicacion> size: " + geoUbicacions.size());
                    //
                    if (geoUbicacions.size() > 0) {
                        Log.d(TAG, "geoUbicacions.get(0).getDescripcion(): " + geoUbicacions.get(0).getDescripcion());
                        establecimiento.setUbicacion(geoUbicacions.get(0));
                    }
                    list.set(i, establecimiento);
                }
            }


        }*/
        return list;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnEstablecimientoClickListener) {
            listener = (OnEstablecimientoClickListener) context;
        } else {
            throw new RuntimeException(context.toString() +
                    " must implement OnEstablecimientoClickListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    @Override
    public void onEstablecimientoClickListener(Establecimiento establecimiento, View view) {
        //Log.d(Utils.TAG, "EstablecimientoFragment onEstablecimientoClickListener: "+ position);
        int childAdapterPosition = recyclerView.getChildAdapterPosition(view);
        Log.d(Utils.TAG, "recyclerView.childAdapterPosition(): " + childAdapterPosition);
        if (establecimientoList.size() > childAdapterPosition && childAdapterPosition >= 0) {
            /*establecimientoList.remove(childAdapterPosition);
            adapter.notifyItemRemoved(childAdapterPosition);*/
            listener.onEstablecimientoClickListener(establecimiento, view);
        }

    }

    public interface OnEstablecimientoClickListener {
        void onEstablecimientoClickListener(Establecimiento establecimiento, View view);
    }

}
