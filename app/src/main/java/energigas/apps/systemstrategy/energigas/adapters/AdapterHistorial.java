package energigas.apps.systemstrategy.energigas.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.entities.Historial;
import energigas.apps.systemstrategy.energigas.interfaces.ListenerClickItemHistorial;

/**
 * Created by kelvin on 12/25/16.
 */

public class AdapterHistorial extends ArrayAdapter<Historial> {
    private List<Historial> historials;
    private ListenerClickItemHistorial listenerClickItemHistorial;

    public AdapterHistorial(Context context, int resource, List<Historial> objects, ListenerClickItemHistorial listenerClickItemHistorial) {
        super(context, resource, objects);
        historials = objects;
        this.listenerClickItemHistorial = listenerClickItemHistorial;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Historial historial = historials.get(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.layout_item_historial, null);
            TextView textViewMes = (TextView) convertView.findViewById(R.id.textMes);
            TextView textViewDia = (TextView) convertView.findViewById(R.id.textDia);
            TextView textViewAnno = (TextView) convertView.findViewById(R.id.textAnno);
            TextView textViewDiaString = (TextView) convertView.findViewById(R.id.textDiaString);
            Button textViewTotal = (Button) convertView.findViewById(R.id.btnTotal);

            textViewMes.setText(historial.getMes());
            textViewDia.setText(historial.getDiaNUmero());
            textViewAnno.setText(historial.getAnno());
            textViewDiaString.setText(historial.getDia());
            textViewTotal.setText(historial.getCantidad());
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listenerClickItemHistorial.OnItemClick(historial);
                }
            });

        }
        return convertView;
    }
}
