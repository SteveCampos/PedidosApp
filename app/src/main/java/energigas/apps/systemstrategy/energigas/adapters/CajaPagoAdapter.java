package energigas.apps.systemstrategy.energigas.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.entities.CajaMovimiento;
import energigas.apps.systemstrategy.energigas.entities.CajaPago;
import energigas.apps.systemstrategy.energigas.entities.ComprobanteVenta;
import energigas.apps.systemstrategy.energigas.entities.Estado;
import energigas.apps.systemstrategy.energigas.entities.PlanPago;
import energigas.apps.systemstrategy.energigas.entities.PlanPagoDetalle;
import energigas.apps.systemstrategy.energigas.holders.CajaPagoHolder;
import energigas.apps.systemstrategy.energigas.utils.Constants;
import energigas.apps.systemstrategy.energigas.utils.Utils;

/**
 * Created by Kike on 1/08/2016.
 */

public class CajaPagoAdapter extends RecyclerView.Adapter<CajaPagoHolder> {

    // Store a member variable for the contacts
    private List<PlanPagoDetalle> mPlanPagDetalle;
    // Store the context for easy access
    private Context mContext;

    private boolean mestado;

    public OnCajaPagoClickListener listener;

    public interface OnCajaPagoClickListener {
        void onCajaPagoClickListener(TextView mEstado, TextView mtotal,PlanPago planpago, ComprobanteVenta venta ,PlanPagoDetalle planPagoDetalle, View view);
        void onCajaPagoListener(int action,PlanPagoDetalle pagoDetalle,ComprobanteVenta mComp, TextView mtotal,TextView mestadoo);
    }


    public CajaPagoAdapter(List<PlanPagoDetalle> mPlanPagoDetalles,Context mContext,OnCajaPagoClickListener listener,boolean mestado) {
        this.mPlanPagDetalle = mPlanPagoDetalles;
        this.listener = listener;
        this.mContext = mContext;
        this.mestado = mestado;
    }

    @Override
    public CajaPagoHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Log.d(Utils.TAG, "onCreateViewHolder");
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_charges, parent, false);
        // Return a new holder instance
        return new CajaPagoHolder(contactView);
    }

    @Override
    public void onBindViewHolder(final CajaPagoHolder holder, int position) {
        // Get the data model based on position

        final PlanPagoDetalle planPagoDetalle = mPlanPagDetalle.get(position);
        final PlanPago planpago = PlanPago.find(PlanPago.class, "plan_Pa_Id = ?",new String[]{planPagoDetalle.getPlanPaId()+""}).get(Constants.CURRENT);
        final ComprobanteVenta comprobanteVenta= ComprobanteVenta.find(ComprobanteVenta.class, "comp_Id= ? ",new String[]{planpago.getCompId()+""}).get(Constants.CURRENT);
           holder.mdate.setText(planPagoDetalle.getFechaCobro());
        holder.mtotal.setText("S./ " + Utils.formatDouble(planPagoDetalle.getMontoAPagar()));
        holder.mcomprobante.setText(comprobanteVenta.getSerie()+"-"+comprobanteVenta.getNumDoc());

        //comprobanteVenta.getSerie()+"-"+comprobanteVenta.getNumDoc()
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!mestado){
                    return;
                }


                final CharSequence[] items = {"Pago Total", "Pago Porroga"};

                final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        mContext);
                alertDialogBuilder.setTitle("Seleccione tipo de Pago");
                alertDialogBuilder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // the user clicked on colors[which]


                        if (items[which].equals("Pago Total")) {
                            listener.onCajaPagoListener(Constants.CLICK_ELIMINAR_CAJA_GASTO, planPagoDetalle, comprobanteVenta,holder.mtotal,holder.mEstado);
                        } else if (items[which].equals("Pago Porroga")) {
                            listener.onCajaPagoListener(Constants.CLICK_EDITAR_CAJA_GASTO, planPagoDetalle,comprobanteVenta,holder.mtotal,holder.mEstado);
                        } else if (items[which].equals("Cancel")) {
                            dialog.dismiss();
                        }
                    }
                });
                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialogBuilder.show();
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                listener.onCajaPagoClickListener(holder.mEstado,holder.mtotal,planpago,comprobanteVenta,planPagoDetalle, holder.itemView);
                return false;
            }
        });
      //  holder.mEstado.setText(mEstado.getIdEstado());
        int color = R.color.dark_grey;
        String estado= String.valueOf(planPagoDetalle.isEstado());
        switch (estado) {
            //true=Pendiente; false=Cobrado
            case "true":
                estado = "Pendiente";
                color = R.color.md_red_500;
                break;
            case "false":
                estado = "Cobrado";
                color = R.color.md_green_up;
                break;

        }
        holder.mEstado.setText(estado);
        holder.mEstado.setTextColor(ContextCompat.getColor(mContext,color));

        //holder.mcomprobante.setText("");
        //holder.mdate.setText("");
        //Cholder.mtotal.setText("S./ " + Utils.formatDouble(cajaPago.getEnRuta()));
/*

        int resto = position % 3;

        int color = R.color.dark_grey;
        String estado = "COBRADO";
        Double total = 0.00;
        switch (resto){
            case 0:
                total = 1500.00;
                estado = "POR COBRAR";
                color =R.color.md_red_500;
                break;
            case 1:
                total = 1200.00;
                estado = "COBRADO";
                color =R.color.md_green_500;
                break;
            case 2:
                total = 2800.00;
                estado = "COBRADO";
                color = R.color.md_green_500;
                break;

        }

        holder.mtotal.setText("S/. " + Utils.formatDouble(total));
        holder.mEstado.setText(estado);
        holder.mEstado.setTextColor(ContextCompat.getColor(mContext, color));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onCajaPagoClickListener(cajaPago, v);
            }
        });
        Log.d(Utils.TAG, "onBindViewHolder: " + position);
*/
    }

    @Override
    public int getItemCount() {
        Log.d(Utils.TAG, "getItemCount");
        return mPlanPagDetalle.size();
    }


}
