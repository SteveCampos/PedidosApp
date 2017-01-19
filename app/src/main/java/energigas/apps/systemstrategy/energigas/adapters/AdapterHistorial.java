package energigas.apps.systemstrategy.energigas.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
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

            Button btnVerde = (Button) convertView.findViewById(R.id.btnVerde);
            Button btnAmbar = (Button) convertView.findViewById(R.id.btnAmbar);
            Button btnRojo = (Button) convertView.findViewById(R.id.btnRojo);


            GradientDrawable bgShapeVerde1 = (GradientDrawable) btnVerde.getBackground();
            bgShapeVerde1.setColor(Color.parseColor("#A9F5A9"));

            GradientDrawable bgShapeAmbar1 = (GradientDrawable) btnAmbar.getBackground();
            bgShapeAmbar1.setColor(Color.parseColor("#F2F5A9"));

            GradientDrawable bgShapeRojo1 = (GradientDrawable) btnRojo.getBackground();
            bgShapeRojo1.setColor(Color.parseColor("#F5A9A9"));

            switch (historial.getSemaforo()) {
                case 1:
                    GradientDrawable bgShapeVerde = (GradientDrawable) btnVerde.getBackground();
                    bgShapeVerde.setColor(Color.GREEN);
                    break;
                case 2:
                    GradientDrawable bgShapeAmbar = (GradientDrawable) btnAmbar.getBackground();
                    bgShapeAmbar.setColor(Color.YELLOW);
                    break;
                case 3:
                    GradientDrawable bgShapeRojo = (GradientDrawable) btnRojo.getBackground();
                    bgShapeRojo.setColor(Color.RED);
                    break;
            }


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
