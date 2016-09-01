package energigas.apps.systemstrategy.energigas.fragments;

import android.app.Activity;
import android.app.Dialog;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.interfaces.DialogGeneralListener;

/**
 * Created by kelvi on 01/09/2016.
 */

public class DialogGeneral {


    private String title;
    private String message;

    private DialogGeneralListener dialogGeneralListener;


    public DialogGeneral setTitle(String title) {
        this.title = title;
        return this;
    }

    public DialogGeneral setMessage(String message) {
        this.message = message;
        return this;
    }


    public static void isConfirm(Activity activity, String title, String message, DialogGeneralListener dialogGeneral) {


        final DialogGeneralListener dialogGeneralListener = dialogGeneral;

        LayoutInflater layoutInflater = LayoutInflater.from(activity);
        View view = layoutInflater.inflate(R.layout.dialog_general, null);

        final AlertDialog alertD = new AlertDialog.Builder(activity).create();

        Button btnOk = (Button) view.findViewById(R.id.btn_ok);
        Button btnCancel = (Button) view.findViewById(R.id.btn_cancel);


        TextView textTitle = (TextView) view.findViewById(R.id.textTitle);
        TextView textMessage = (TextView) view.findViewById(R.id.textMessage);

        textTitle.setText(title);
        textMessage.setText(message);

        btnOk.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                dialogGeneralListener.onSavePressed();
                alertD.dismiss();

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dialogGeneralListener.onCancelPressed();
                alertD.dismiss();

            }
        });
        alertD.setCancelable(false);
        alertD.setView(view);
        alertD.show();

    }


}
