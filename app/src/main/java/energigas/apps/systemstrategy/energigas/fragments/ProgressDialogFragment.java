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

import com.orm.SugarContext;

import java.util.List;

import butterknife.ButterKnife;
import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.activities.MainActivity;
import energigas.apps.systemstrategy.energigas.asyntask.LoginTask;
import energigas.apps.systemstrategy.energigas.entities.Usuario;
import energigas.apps.systemstrategy.energigas.interfaces.OnLoginAsyntaskListener;

/**
 * Created by kelvi on 10/08/2016.
 */

public class ProgressDialogFragment extends DialogFragment implements OnLoginAsyntaskListener {
    private static final String TAG = "ProgressDialogFragment";


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
        String usuario = getArguments().getString("usuario");
        String clave = getArguments().getString("clave");
        SugarContext.init(getActivity());
        List<Usuario> ss = Usuario.listAll(Usuario.class);
        Log.d(TAG, "COUNT : " + ss.size());
        if (ss.size() > 0) {
            List<Usuario> dbUsuarioa = Usuario.find(Usuario.class, " usu_V_Usuario = ?  and usu_V_Password = ? ", new String[]{usuario, clave});
           // Log.d(TAG, "LOGIN : " + dbUsuarioa.get(0).getUsuVUsuario());
            if (dbUsuarioa.size() > 0) {
                initMain();
            } else {
                new LoginTask(this).execute(usuario, clave);
            }

        } else {
            new LoginTask(this).execute(usuario, clave);
        }

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
        dismiss();
    }

    @Override
    public void onSuccess() {
        initMain();
    }

    @Override
    public void onErrorProcedure(@NonNull String message) {
        Toast.makeText(getActivity(), "" + message, Toast.LENGTH_SHORT).show();
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
       // getActivity().finish();
    }

    @Override
    public void onStop() {
        SugarContext.terminate();
        super.onStop();
    }
}
