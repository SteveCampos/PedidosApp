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

import energigas.apps.systemstrategy.energigas.entities.Concepto;

/**
 * Created by kelvi on 12/09/2016.
 */

public class ConceptoAdapter extends ArrayAdapter<Concepto> {
    private Context context;
    private List<Concepto> conceptos;
    public ConceptoAdapter(Context context, int resource, List<Concepto> objects) {

        super(context, resource, objects);
        this.context = context;
        this.conceptos = objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        TextView txt = (TextView) inflater.inflate(android.R.layout.simple_spinner_item, parent,false);
        txt.setPadding(16, 16, 16, 16);
        txt.setTextSize(18);
        txt.setGravity(Gravity.CENTER_VERTICAL);
        txt.setText(conceptos.get(position).getDescripcion());
        return  txt;
        //return super.getView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getView(position, convertView, parent);
    }
}
