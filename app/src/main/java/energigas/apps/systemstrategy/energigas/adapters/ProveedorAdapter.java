package energigas.apps.systemstrategy.energigas.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.entities.Proveedor;

/**
 * Created by Steve on 27/12/2016.
 */

public class ProveedorAdapter extends ArrayAdapter<Proveedor> {
    Context context;
    private static final String TAG = ProveedorAdapter.class.getSimpleName();
    private List<Proveedor> originalData = new ArrayList<>();
    private List<Proveedor> filteredData = new ArrayList<>();

    public final static int DOCUMENTO_IDENTIDAD = 100;
    public final static int NOMBRE_COMERCIAL = 101;

    private int typeSearch;

    public ProveedorAdapter(Context context, int type,
                         List<Proveedor> mList) {
        super(context, R.layout.item_proveedor_autocomplete, R.id.text_proveedor_title);
        this.context = context;
        this.originalData = mList;
        this.filteredData = mList;
        this.typeSearch = type;

    }

    public int getCount() {
        if (filteredData != null){
            return filteredData.size();
        }else{
            return 0;
        }
    }

    public Proveedor getItem(int position) {
        return filteredData.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = super.getView(position, convertView, parent);

        Log.d(TAG, "getView position: " + position);
        Proveedor proveedor = getItem(position);
        if (proveedor==null){
            return row;
        }

        /*
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.item_proveedor_autocomplete, parent, false);
        }*/
            TextView textTitle = (TextView) row.findViewById(R.id.text_proveedor_title);
            TextView textSmall = (TextView) row.findViewById(R.id.text_proveedor_subtitle);
            textTitle.setText(proveedor.getPersona().getNombreComercial());
            textSmall.setText(proveedor.getPersona().getPerVDocIdentidad());

        return row;
    }

    @Override
    public Filter getFilter() {

        return nameFilter;
    }

    Filter nameFilter = new Filter() {

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {
            filteredData = (List<Proveedor>) results.values;
            notifyDataSetChanged();
            /*
            if (results != null && results.count > 0) {
                clear();
                for (Proveedor Proveedor : filteredList) {
                    add(Proveedor);
                }
                notifyDataSetChanged();
            } else {
                notifyDataSetInvalidated();
            }*/
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            Log.d(TAG, "constraint : " + constraint);
            String filterString = constraint.toString().toLowerCase();
            FilterResults results = new FilterResults();
            final List<Proveedor> list = originalData;
            int count = list.size();
            final ArrayList<Proveedor> nlist = new ArrayList<>(count);
            String filterableString = "";
            for (int i = 0; i < count; i++) {
                switch (typeSearch){
                    case DOCUMENTO_IDENTIDAD:
                        filterableString = list.get(i).getPersona().getPerVDocIdentidad();
                        break;
                    case NOMBRE_COMERCIAL:
                        filterableString = list.get(i).getPersona().getNombreComercial();
                        break;
                }
                if (filterableString.toLowerCase().contains(filterString)) {
                    nlist.add(list.get(i));
                }
            }

            results.values = nlist;
            results.count = nlist.size();

            return results;
        }
    };
}
