package energigas.apps.systemstrategy.energigas.fragments;


import android.app.Dialog;
import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import energigas.apps.systemstrategy.energigas.LocationVehiculeListener;
import energigas.apps.systemstrategy.energigas.interfaces.OnAsyntaskListener;
import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.asyntask.AsyntaskOpenAccount;
import energigas.apps.systemstrategy.energigas.interfaces.OnLocationListener;

/**
 * Created by kelvi on 8/08/2016.
 */

public class AccountDialog extends DialogFragment implements View.OnClickListener, OnAsyntaskListener, OnLocationListener {

    public static final String TAG = "AccountDialog";

    @BindView(R.id.loanding)
    ContentLoadingProgressBar loadingProgressBar;
    @BindView(R.id.viewinfo)
    ViewGroup viewGroupInfo;
    @BindView(R.id.editTextKI)
    EditText editTextKI;
    @BindView(R.id.ediTextWI)
    EditText editTextWI;
    @BindView(R.id.editTextPI)
    EditText editTextPI;
    @BindView(R.id.btn_ok)
    Button btnOk;
    @BindView(R.id.btn_cancel)
    Button btnCancel;
    FloatingActionButton fab;
    private LocationVehiculeListener locationVehiculeListener;
    private Location location;

    public AccountDialog setFloating(FloatingActionButton fab) {
        this.fab = fab;
        return this;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.AppTheme);
        locationVehiculeListener = new LocationVehiculeListener(this);
        return dialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.layout_dialog_open_account, container, false);
        ButterKnife.bind(this, rootView);
        btnOk.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_ok:
                saveIsAvibleData();
                break;
            case R.id.btn_cancel:
                locationVehiculeListener.stopLocationUpdates();
                dismiss();
                break;
        }
    }

    private void saveIsAvibleData() {
        if (isNotEmtyEditText()) {

            showSendTask();
        } else {
            Snackbar.make(btnOk, "Complete los campos", Snackbar.LENGTH_SHORT).show();
        }
    }

    private void showAnimation() {
        viewGroupInfo.setVisibility(View.GONE);
        loadingProgressBar.setVisibility(View.VISIBLE);
        btnOk.setVisibility(View.GONE);
        btnCancel.setVisibility(View.GONE);
    }

    private void hideAnimationProgress() {
        viewGroupInfo.setVisibility(View.VISIBLE);
        loadingProgressBar.setVisibility(View.GONE);
        btnOk.setVisibility(View.VISIBLE);
        btnCancel.setVisibility(View.VISIBLE);
    }



    private boolean isNotEmtyEditText() {

        if ((editTextKI.getText().toString().length() & editTextWI.getText().toString().length() & editTextPI.getText().toString().length()) > 0) {
            return true;
        }
        return false;
    }



    private void showSendTask() {
        String usuarioId = "43";
        String kmInicial = editTextKI.getText().toString();
        String pesoInicial = editTextWI.getText().toString();
        String porcentajeInicial = editTextPI.getText().toString();
        if(location ==null){

            Toast.makeText(getActivity(), "Ubiacion desconocida", Toast.LENGTH_SHORT).show();
            return;
        }
        String stringLiquidacion = usuarioId+"_"+kmInicial+"_"+pesoInicial+"_"+porcentajeInicial+"_"+location.getLatitude()+"_"+location.getLongitude()+"";
        Log.d(TAG,""+stringLiquidacion);
        showAnimation();
       new AsyntaskOpenAccount(this).execute(stringLiquidacion);
    }


    @Override
    public void onLoadSuccess(String message) {
        Log.d("LOADSUCCESS", "kelvin: " + message);
        dismiss();
        locationVehiculeListener.stopLocationUpdates();
        fab.hide();
    }

    @Override
    public void onLoadError(String message) {
        hideAnimationProgress();

    }

    @Override
    public void onLoadErrorProcedure(String message) {
        hideAnimationProgress();
    }



    @Override
    public void setLatAndLong(Location latAndLong) {
        location = latAndLong;
    }

    @Override
    public Context getContextActivity() {
        return getActivity();
    }
}
