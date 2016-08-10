package energigas.apps.systemstrategy.energigas.fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.asyntask.LoginTask;
import energigas.apps.systemstrategy.energigas.interfaces.OnLoginAsyntaskListener;

/**
 * Created by kelvi on 10/08/2016.
 */

public class ProgressDialogFragment extends DialogFragment implements LoaderManager.LoaderCallbacks<String>, OnLoginAsyntaskListener {
    private static final String TAG = "ProgressDialogFragment";



    public static  ProgressDialogFragment newIntance (@NonNull String usuario,@NonNull String clave) {

        ProgressDialogFragment fragment = new ProgressDialogFragment();
        Bundle args = new Bundle();
        args.putString("usuario", usuario);
        args.putString("clave", clave);
        fragment.setArguments(args);
        return  fragment;
    }



    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, 0);
        getActivity().getSupportLoaderManager().initLoader(0, null, this).forceLoad();
        return dialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.layout_dialog_progress, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public Loader<String> onCreateLoader(int id, Bundle args) {
        Log.d(TAG, "onCreateLoader" + " ");


        return new LoginTask(getActivity(), this);
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String data) {
        Log.d(TAG, "onLoadFinished " + data);
    }

    @Override
    public void onLoaderReset(Loader<String> loader) {
        Log.d(TAG, "onLoaderReset" + " ");
    }

    @Override
    public void onError(@NonNull String message) {

    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onErrorProcedure(@NonNull String message) {
        Log.d(TAG, "onErrorProcedure" + " "+message);
        dismiss();

    }

    @Override
    public void onCredentialsFail() {

    }
}
