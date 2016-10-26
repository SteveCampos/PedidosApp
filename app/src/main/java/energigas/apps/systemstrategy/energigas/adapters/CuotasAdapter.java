package energigas.apps.systemstrategy.energigas.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;

import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.entities.PlanPagoDetalle;
import energigas.apps.systemstrategy.energigas.holders.ItemCuotasHolder;

/**
 * Created by kelvi on 30/09/2016.
 */

public class CuotasAdapter extends RecyclerView.Adapter<ItemCuotasHolder> {
    private OnChangeDateListener onChangeDateListener;
    private List<PlanPagoDetalle> planPagoDetalles;
    private Context context;
    private String TAG = "CuotasAdapter";


    public CuotasAdapter(List<PlanPagoDetalle> planPagoDetalles, Context context, OnChangeDateListener onChangeDateListener) {
        this.planPagoDetalles = planPagoDetalles;
        this.context = context;
        this.onChangeDateListener = onChangeDateListener;
    }

    @Override
    public ItemCuotasHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_cuotas, parent, false);
        return new ItemCuotasHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemCuotasHolder holder, final int position) {
        final int positionFinal = position;
        final ItemCuotasHolder cuotasHolder = holder;
        final PlanPagoDetalle planPagoDetalle = planPagoDetalles.get(position);
        holder.fechaCuota.setText(planPagoDetalle.getFechaCobro());
        holder.precioCuota.setText(planPagoDetalle.getMontoAPagar() + "");
        holder.cambiarFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onChangeDateListener.onChangeDateListener(position, planPagoDetalles);

            }
        });

       /* final Calendar date =Calendar.getInstance();
        date.setTimeInMillis(Utils.getDateMills(planPagoDetalle.getFechaCobro()));

        cuotasHolder.datePickerFecha.updateDate(date.get(Calendar.YEAR),date.get(Calendar.MONTH),date.get(Calendar.DAY_OF_MONTH));

        holder.datePickerFecha.setMinDate(Calendar.getInstance().getTimeInMillis() - 1000);

        holder.datePickerFecha.setMaxDate(Utils.getDateMills(planPagoDetalle.getFechaCobro()));



        cuotasHolder.buttonSaveDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                cuotasHolder.datePickerFecha.setVisibility(View.GONE);
                cuotasHolder.buttonSaveDate.setVisibility(View.GONE);
                String date = Utils.getStringDate(Utils.getDateFromDatePicker(cuotasHolder.datePickerFecha));
                Toast.makeText(context, "DATE: " + date, Toast.LENGTH_SHORT).show();
                planPagoDetalle.setFechaCobro(date);
                cuotasHolder.fechaCuota.setText(planPagoDetalle.getFechaCobro());
                planPagoDetalles.get(positionFinal).setFechaCobro(date);
            }
        });*/


    }


    @Override
    public int getItemCount() {
        return planPagoDetalles.size();
    }

    public List<PlanPagoDetalle> getPlanPagoDetalles() {
        return planPagoDetalles;
    }

    public interface OnChangeDateListener {
        void onChangeDateListener(int position, List<PlanPagoDetalle> planPagoDetalles);
    }
}
