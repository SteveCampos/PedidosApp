package energigas.apps.systemstrategy.energigas.fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import butterknife.ButterKnife;
import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.activities.MainActivity;
import energigas.apps.systemstrategy.energigas.asyntask.LoginTask;
import energigas.apps.systemstrategy.energigas.interfaces.OnLoginAsyntaskListener;
import energigas.apps.systemstrategy.energigas.persistence.DB_Usuario;

/**
 * Created by kelvi on 10/08/2016.
 */

public class ProgressDialogFragment extends DialogFragment implements OnLoginAsyntaskListener {
    private static final String TAG = "ProgressDialogFragment";
    private DB_Usuario db_usuario;

    public static ProgressDialogFragment newIntance(@NonNull String usuario, @NonNull String clave) {

        ProgressDialogFragment fragment = new ProgressDialogFragment();
        Bundle args = new Bundle();
        args.putString("usuario", usuario);
        args.putString("clave", clave);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, 0);
        db_usuario = new DB_Usuario(getActivity());
        db_usuario.open();
        String usuario = getArguments().getString("usuario");
        String clave = getArguments().getString("clave");
        if (db_usuario.validateUserAndPassword(usuario, clave)) {
            dismiss();
            initMain();
        } else {
            new LoginTask(this).execute(usuario, clave);
        }
        db_usuario.close();
        return dialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.layout_dialog_progress, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }


    @Override
    public void onError(@NonNull String message) {
        Log.d(TAG, "onError" + " " + message);
        Toast.makeText(getActivity(), "" + message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess() {
        initMain();
    }

    @Override
    public void onErrorProcedure(@NonNull String message) {

        dismiss();

    }

    @Override
    public void onCredentialsFail() {
        dismiss();
        Toast.makeText(getActivity(), "Credenciales erroneas", Toast.LENGTH_SHORT).show();
    }

    @Override
    public Context getContextActivity() {
        return getActivity();
    }

    private void initMain() {
        startActivity(new Intent(getActivity(), MainActivity.class));
        getActivity().finish();
    }

}
