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
    private boolean estado = false;
    private DialogGeneralListener dialogGeneralListener;

    LayoutInflater layoutInflater ;
    View view;
    Activity activity;
    Button btnOk;
    Button btnCancel;
    TextView textTitle ;
    TextView textMessage ;

    public DialogGeneral (Activity activity){
        this.activity = activity;
        LayoutInflater layoutInflater = LayoutInflater.from(activity);
        view = layoutInflater.inflate(R.layout.dialog_general, null);
        btnOk = (Button) view.findViewById(R.id.btn_ok);
        btnCancel = (Button) view.findViewById(R.id.btn_cancel);
        textTitle = (TextView) view.findViewById(R.id.textTitle);
        textMessage = (TextView) view.findViewById(R.id.textMessage);

    }

    public DialogGeneral setCancelable(boolean estado) {
        this.estado = estado;
        return this;
    }

    public DialogGeneral setTextButtons(String textYes,String textNo){
        btnOk.setText(textYes);
        btnCancel.setText(textNo);
        return this;
    }

    public DialogGeneral setMessages(String title,String message){
        textTitle.setText(title);
        textMessage.setText(message);
        return this;
    }


    public  void showDialog(DialogGeneralListener dialogGeneral) {

        final DialogGeneralListener dialogGeneralListener = dialogGeneral;

        final AlertDialog alertD = new AlertDialog.Builder(activity).create();

        btnOk.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                dialogGeneralListener.onSavePressed(alertD);

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dialogGeneralListener.onCancelPressed(alertD);

            }
        });
        alertD.setCancelable(this.estado);
        alertD.setView(view);
        alertD.show();

    }

}
