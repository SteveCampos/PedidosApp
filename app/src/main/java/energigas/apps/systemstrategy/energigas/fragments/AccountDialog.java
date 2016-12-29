package energigas.apps.systemstrategy.energigas.fragments;


import android.app.Dialog;
import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import energigas.apps.systemstrategy.energigas.LocationVehiculeListener;
import energigas.apps.systemstrategy.energigas.entities.CajaLiquidacion;
import energigas.apps.systemstrategy.energigas.entities.CajaLiquidacionDetalle;
import energigas.apps.systemstrategy.energigas.entities.Establecimiento;
import energigas.apps.systemstrategy.energigas.entities.NotificacionCajaDetalle;
import energigas.apps.systemstrategy.energigas.entities.PlanDistribucion;
import energigas.apps.systemstrategy.energigas.entities.Usuario;
import energigas.apps.systemstrategy.energigas.interfaces.OnAsyntaskListener;
import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.asyntask.AsyntaskOpenAccount;
import energigas.apps.systemstrategy.energigas.interfaces.OnLocationListener;
import energigas.apps.systemstrategy.energigas.utils.Constants;
import energigas.apps.systemstrategy.energigas.utils.Session;
import energigas.apps.systemstrategy.energigas.utils.Utils;

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
    Usuario usuario;
    private LocationVehiculeListener locationVehiculeListener;
    private Location location;
    private AccountDialog.ListenerOpenAccount listener;
    private DatabaseReference mDatabase;
    private DatabaseReference myRef;

    public AccountDialog setFloating(FloatingActionButton fab) {
        this.fab = fab;
        return this;
    }

    public AccountDialog setUser(Usuario user) {
        this.usuario = user;
        return this;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        setCancelable(false);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.AppTheme);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        myRef = mDatabase.child(Constants.FIREBASE_CHILD_ATEN_PEDIDO);
        locationVehiculeListener = new LocationVehiculeListener(this, Constants.MIN_TIME_BW_UPDATES, new Long(0));
        return dialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.layout_dialog_open_account, container, false);
        ButterKnife.bind(this, rootView);
        btnOk.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        editTextPI.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count > 0) {
                    Double aDouble = Double.parseDouble(s.toString());
                    if (aDouble > 100) {
                        Toast.makeText(getActivity(), "Porcentaje invalido", Toast.LENGTH_SHORT).show();
                        editTextPI.setText("");
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        return rootView;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_ok:
                saveIsAvibleData();
                break;
            case R.id.btn_cancel:
                List<CajaLiquidacion> cajaLiquidacions = CajaLiquidacion.find(CajaLiquidacion.class, "estado_Id=?", new String[]{Constants.CAJA_ABIERTA + ""});
                if (cajaLiquidacions.size() <= 0) {
                    Toast.makeText(getActivity(), "Es necesario abrir caja", Toast.LENGTH_SHORT).show();
                } else {
                    dismiss();
                }
                locationVehiculeListener.stopLocationUpdates();

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

        if (editTextKI.getText().toString().length() > 0 && editTextWI.getText().toString().length() > 0 && editTextPI.getText().toString().length() > 0) {
            return true;
        }
        return false;
    }


    private void showSendTask() {

        String usuarioId = usuario.getUsuIUsuarioId() + "";
        String kmInicial = editTextKI.getText().toString();
        String pesoInicial = editTextWI.getText().toString();
        String porcentajeInicial = editTextPI.getText().toString();
        if (location == null) {
            Snackbar.make(btnOk, "Ubicacion desconocida", Snackbar.LENGTH_SHORT).show();
            return;
        }
        String stringLiquidacion = usuarioId + "_" + kmInicial + "_" + pesoInicial + "_" + porcentajeInicial + "_" + location.getLatitude() + "_" + location.getLongitude() + "";
        Log.d(TAG, "" + stringLiquidacion);
        showAnimation();
        new AsyntaskOpenAccount(this).execute(stringLiquidacion);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof EstablecimientoFragment.OnEstablecimientoClickListener) {
            listener = (AccountDialog.ListenerOpenAccount) context;
        } else {
            throw new RuntimeException(context.toString() +
                    " must implement OnEstablecimientoClickListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    public interface ListenerOpenAccount {
        void onSuccessOpenAccount();
    }


    @Override
    public void onLoadSuccess(String message, CajaLiquidacion cajaLiquidacion) {
        Log.d("LOADSUCCESS", "kelvin: " + message);
        dismiss();
        locationVehiculeListener.stopLocationUpdates();
        setMessage(message);
        Session.saveCajaLiquidacion(getActivity(), cajaLiquidacion);
        fab.hide();
        for (CajaLiquidacionDetalle liquidacionDetalle : cajaLiquidacion.getItemsLiquidacion()) {
            NotificacionCajaDetalle notificacionCajaDetalle = new NotificacionCajaDetalle(
                    liquidacionDetalle.getEstablecimientoId(),
                    Constants.NO_FACTURADO,
                    liquidacionDetalle.getEstadoId(),
                    Utils.getDatePhone(),
                    Integer.parseInt(cajaLiquidacion.getLiqId() + ""),
                    Integer.parseInt(liquidacionDetalle.getLidId() + ""),
                    liquidacionDetalle.getOrdenAtencion(),
                    Integer.parseInt(liquidacionDetalle.getPeId() + ""),
                    0.00,
                    0.00,
                    0.00
            );
            myRef.child(cajaLiquidacion.getLiqId() + "-" + liquidacionDetalle.getLidId()).setValue(notificacionCajaDetalle, new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                   // Log.d("FIREBASE_CREATE", " ERROR: " + databaseError.getMessage() + "");
                   // Log.d("FIREBASE_CREATE", databaseReference.getKey());
                }
            });
        }
        listener.onSuccessOpenAccount();
        dismiss();
    }

    @Override
    public void onLoadError(String message) {
        hideAnimationProgress();
        locationVehiculeListener.stopLocationUpdates();
    }

    @Override
    public void onLoadErrorProcedure(String message) {
        hideAnimationProgress();
        setMessage(message);
    }

    private void setMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
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
