package energigas.apps.systemstrategy.energigas.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.entities.Establishment;

/**
 * Created by jairc on 19/07/2016.
 */

public class StationAdapter extends RecyclerView.Adapter<StationAdapter.ViewHolder> {


    // Store a member variable for the contacts
    private List<Establishment> mListEstablishments;
    // Store the context for easy access
    private Context mContext;

    public StationAdapter(List<Establishment> mListEstablishments, Context mContext) {
        this.mListEstablishments = mListEstablishments;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_establishment, parent, false);
        // Return a new holder instance
        return new ViewHolder(contactView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // Get the data model based on position
        //
        holder.bind(mListEstablishments.get(position));
    }

    @Override
    public int getItemCount() {
        return mListEstablishments.size();
    }

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    static class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        TextView nameTextView;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);
            nameTextView = (TextView) itemView.findViewById(R.id.text_establishment_name);
        }
        void bind(Establishment establishment){
            nameTextView.setText(establishment.getName());
        }
    }


}
