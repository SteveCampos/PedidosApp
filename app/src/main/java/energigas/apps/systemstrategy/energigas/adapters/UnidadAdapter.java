package energigas.apps.systemstrategy.energigas.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import energigas.apps.systemstrategy.energigas.entities.Unidad;


/**
 * Created by kelvi on 12/09/2016.
 */

public class UnidadAdapter extends ArrayAdapter<Unidad> {
    private Context context;
    private List<Unidad> unidadList;
    public UnidadAdapter(Context context, int resource, List<Unidad> objects) {

        super(context, resource, objects);
        this.context = context;
        this.unidadList = objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        TextView txt = (TextView) inflater.inflate(android.R.layout.simple_spinner_item, parent,false);
        txt.setPadding(16, 16, 16, 16);
        txt.setTextSize(18);
        txt.setGravity(Gravity.CENTER_VERTICAL);
        txt.setText(unidadList.get(position).getAbreviatura());
        return  txt;
        //return super.getView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getView(position, convertView, parent);
    }
}
