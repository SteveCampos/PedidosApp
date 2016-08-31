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

import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.adapters.EstablecimientoAdapter;
import energigas.apps.systemstrategy.energigas.entities.Establecimiento;
import energigas.apps.systemstrategy.energigas.entities.GeoUbicacion;
import energigas.apps.systemstrategy.energigas.utils.Utils;

/**
 * Created by Steve on 19/07/2016.
 */

public class EstablecimientoFragment extends Fragment implements EstablecimientoAdapter.OnEstablecimientoClickListener {


    private static final String TAG = EstablecimientoFragment.class.getSimpleName();
    public OnEstablecimientoClickListener listener;
    private EstablecimientoAdapter adapter;
    private List<Establecimiento> establecimientoList = new ArrayList<>();
    private RecyclerView recyclerView;

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
        recyclerView= (RecyclerView) inflater.inflate(
                R.layout.recycler_view, container, false);
        establecimientoList = getEstablecimientoList();
        adapter = new EstablecimientoAdapter(establecimientoList, getActivity(), this);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return recyclerView;
    }


    private List<Establecimiento> getEstablecimientoList(){
        List<Establecimiento> list= Establecimiento.findWithQuery(Establecimiento.class, "Select * from Establecimiento");

        for (int i=0; i<list.size(); i++){
            Establecimiento establecimiento = list.get(i);

            List<GeoUbicacion> geoUbicacions = GeoUbicacion.find(GeoUbicacion.class, "ub_id = ?", ""+establecimiento.getUbId());
            Log.d(TAG, "List<GeoUbicacion> size: " + geoUbicacions.size());
            //CHECKAR QUE LA LISTA DE OBJETOS ANIDADOS, NO ESTÉ VACÍA.
            if (geoUbicacions.size()>0){
                Log.d(TAG, "geoUbicacions.get(0).getDescripcion(): "+ geoUbicacions.get(0).getDescripcion());
                establecimiento.setUbicacion(geoUbicacions.get(0));
            }
            list.set(i, establecimiento);
        }
        return  list;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnEstablecimientoClickListener){
            listener = (OnEstablecimientoClickListener) context;
        }else{
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
        Log.d(Utils.TAG, "recyclerView.childAdapterPosition(): "+ childAdapterPosition);
        if (establecimientoList.size()>childAdapterPosition && childAdapterPosition >= 0){
            /*establecimientoList.remove(childAdapterPosition);
            adapter.notifyItemRemoved(childAdapterPosition);*/
            listener.onEstablecimientoClickListener(establecimiento, view);
        }

    }

    public interface OnEstablecimientoClickListener {
        void onEstablecimientoClickListener(Establecimiento establecimiento, View view);
    }

}
