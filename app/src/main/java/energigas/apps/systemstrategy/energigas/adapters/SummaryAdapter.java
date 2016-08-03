package energigas.apps.systemstrategy.energigas.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.entities.Summary;

/**
 * Created by kelvi on 20/07/2016.
 */

public class SummaryAdapter extends RecyclerView.Adapter<SummaryAdapter.ViewHolder> {

    // Store a member variable for the contacts
    private List<Summary> summaries;
    // Store the context for easy access
    private Context mContext;

    public SummaryAdapter(List<Summary> summaries, Context mContext) {
        this.summaries = summaries;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        // Inflate the custom layout
        View contactView = null;
        // Return a new holder instance

        switch (viewType) {
            case 0:
                contactView = inflater.inflate(R.layout.layout_item_all_sumary, parent, false);
                break;
            case 1:
                contactView = inflater.inflate(R.layout.layout_item_all_income, parent, false);
                break;
            case 2:
                contactView = inflater.inflate(R.layout.layout_item_all_costs, parent, false);
                break;
        }


        return new ViewHolder(contactView);
    }

    @Override
    public int getItemViewType(int position) {
        return summaries.get(position).getType();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(summaries.get(position));
    }

    @Override
    public int getItemCount() {
        return summaries.size();
    }

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

        void bind(Summary summary) {
            nameTextView.setText(summary.getEfectivoRendir()+"");
        }
    }
}
