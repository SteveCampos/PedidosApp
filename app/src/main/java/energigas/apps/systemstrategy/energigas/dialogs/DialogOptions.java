package energigas.apps.systemstrategy.energigas.dialogs;

import android.app.Activity;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.interfaces.DialogGeneralListener;

/**
 * Created by kelvi on 19/01/2017.
 */

public class DialogOptions {

    private String title;
    private String message;
    private boolean estado = false;

    LayoutInflater layoutInflater;
    View view;
    Activity activity;
    Button btnEliminar;
    Button btnEditar;

    public DialogOptions(Activity activity) {
        this.activity = activity;
        LayoutInflater layoutInflater = LayoutInflater.from(activity);
        view = layoutInflater.inflate(R.layout.dialog_option_orden_carga, null);
        btnEliminar = (Button) view.findViewById(R.id.btn_delete_ordencarga);
        btnEditar = (Button) view.findViewById(R.id.btn_edit_ordencarga);

    }

    public DialogOptions setCancelable(boolean estado) {
        this.estado = estado;
        return this;
    }


    public void showDialog(OnDialogOpetions dialogGeneral) {

        final OnDialogOpetions dialogGeneralListener = dialogGeneral;

        final AlertDialog alertD = new AlertDialog.Builder(activity).create();

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                dialogGeneralListener.onDeleteItemClickListener(alertD);

            }
        });

        btnEditar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dialogGeneralListener.onEditItemClickListener(alertD);

            }
        });
        alertD.setCancelable(this.estado);
        alertD.setView(view);
        alertD.show();

    }

    public interface OnDialogOpetions {
        void onEditItemClickListener(AlertDialog alertDialog);

        void onDeleteItemClickListener(AlertDialog alertDialog);
    }
}
