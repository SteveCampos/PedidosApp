package energigas.apps.systemstrategy.energigas.activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Observable;
import java.util.Observer;

import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.broadcast.ObservableObject;
import energigas.apps.systemstrategy.energigas.interfaces.DialogGeneralListener;
import energigas.apps.systemstrategy.energigas.utils.Utils;


public class ModalActivity extends AppCompatActivity implements Observer {

    private AlertDialog alertDialog;
    private static String TAG = "ModalActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ObservableObject.getInstance().addObserver(this);
        alertDialog = initDialogGeneral();

    }

    private AlertDialog initDialogGeneral() {
        AlertDialog alertDialog = new DialogGeneral(this).setMessages("GPS Desactivado", "Es necesario activar el gps para el buen funcionamiento").setTextButtons("Activar").setCancelable(false).showDialog(new DialogGeneralListener() {
            @Override
            public void onSavePressed(AlertDialog alertDialog) {
                Toast.makeText(ModalActivity.this, "Activar", Toast.LENGTH_SHORT).show();
                Utils.setIntentLocationSetting(ModalActivity.this);
                if ((alertDialog != null) && alertDialog.isShowing()) {
                    alertDialog.dismiss();
                }
                alertDialog = null;

            }

            @Override
            public void onCancelPressed(AlertDialog alertDialog) {
                alertDialog.findViewById(R.id.btn_cancel).setVisibility(View.GONE);
                Toast.makeText(ModalActivity.this, "Es necesario activar el gps", Toast.LENGTH_SHORT).show();
            }
        });

        return alertDialog;
    }




    @Override
    public void update(Observable o, Object arg) {
        boolean estado = (boolean) arg;
        if (estado) {

            if (alertDialog != null) {
                alertDialog.dismiss();
                ModalActivity.this.finish();
            } else {

            }


        } else {

        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.d(TAG, "REUQESTDATA: " + requestCode + " ESTADO GPS: " + Utils.getGpsEnable(getApplicationContext()));
        if (Utils.getGpsEnable(getApplicationContext())) {
            ModalActivity.this.finish();
        } else {
            alertDialog = initDialogGeneral();
        }
    }


    public class DialogGeneral {


        private String title;
        private String message;
        private boolean estado = false;
        private DialogGeneralListener dialogGeneralListener;

        LayoutInflater layoutInflater;
        View view;
        Activity activity;
        Button btnOk;
        Button btnCancel;
        TextView textTitle;
        TextView textMessage;

        public DialogGeneral(Activity activity) {
            this.activity = activity;
            LayoutInflater layoutInflater = LayoutInflater.from(activity);
            view = layoutInflater.inflate(R.layout.dialog_general, null);
            btnOk = (Button) view.findViewById(R.id.btn_ok);

            btnCancel = (Button) view.findViewById(R.id.btn_cancel);
            btnCancel.setVisibility(View.GONE);
            textTitle = (TextView) view.findViewById(R.id.textTitle);
            textMessage = (TextView) view.findViewById(R.id.textMessage);

        }

        public DialogGeneral setCancelable(boolean estado) {
            this.estado = estado;
            return this;
        }

        public DialogGeneral setTextButtons(String textYes) {
            btnOk.setText(textYes);
            return this;
        }

        public DialogGeneral setMessages(String title, String message) {
            textTitle.setText(title);
            textMessage.setText(message);
            return this;
        }


        public AlertDialog showDialog(DialogGeneralListener dialogGeneral) {

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
            return alertD;
        }

    }


}
