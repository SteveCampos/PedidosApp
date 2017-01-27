package energigas.apps.systemstrategy.energigas.fragments;

import android.app.Dialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.orm.SugarRecord;
import com.orm.SugarTransactionHelper;

import org.json.JSONObject;

import java.util.List;

import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.apiRest.ManipuleData;
import energigas.apps.systemstrategy.energigas.apiRest.RestAPI;
import energigas.apps.systemstrategy.energigas.asyntask.PreciosAsyntask;
import energigas.apps.systemstrategy.energigas.entities.Almacen;
import energigas.apps.systemstrategy.energigas.entities.CajaLiquidacion;
import energigas.apps.systemstrategy.energigas.entities.Cliente;
import energigas.apps.systemstrategy.energigas.entities.Establecimiento;
import energigas.apps.systemstrategy.energigas.entities.GeoUbicacion;
import energigas.apps.systemstrategy.energigas.entities.Pedido;
import energigas.apps.systemstrategy.energigas.entities.PedidoDetalle;
import energigas.apps.systemstrategy.energigas.entities.Persona;
import energigas.apps.systemstrategy.energigas.entities.PlanDistribucion;
import energigas.apps.systemstrategy.energigas.entities.PlanDistribucionDetalle;
import energigas.apps.systemstrategy.energigas.utils.Utils;

/**
 * Created by kelvi on 29/08/2016.
 */

public class CajaExistenteFragment extends DialogFragment {

    private static final String TAG = "CajaExistenteFragment";


    public static CajaExistenteFragment newIntance(@NonNull String idUsuario) {

        CajaExistenteFragment fragment = new CajaExistenteFragment();
        Bundle args = new Bundle();
        args.putString("idUsuario", idUsuario);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        setCancelable(false);
        String usuario = getArguments().getString("idUsuario");
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.AppTheme);
        new ExisteCaja().execute(usuario);
        return dialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.layout_dialog_progress, container, false);
        return rootView;
    }


    class ExisteCaja extends AsyncTask<String, String, String> implements SugarTransactionHelper.Callback {
        private RestAPI restAPI;
        private JSONObject jsonObject;
        private String message = "";
        private CajaLiquidacion cajaLiquidacion;


        public ExisteCaja() {
            this.restAPI = new RestAPI();
        }

        @Override
        protected String doInBackground(String... strings) {

            try {
                ObjectMapper mapper = new ObjectMapper();
                mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                jsonObject = restAPI.fobj_ObtenerLiquidacion(Integer.parseInt(strings[0]));
                Log.d(TAG, " :" + strings[0]);
                Log.d(TAG, ": " + jsonObject.toString());

                if (Utils.isSuccessful(jsonObject)) {
                    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                    cajaLiquidacion = mapper.readValue(Utils.getJsonObResult(jsonObject), CajaLiquidacion.class);
                    Log.d(TAG, "" + cajaLiquidacion.getLiqId());
                    if (cajaLiquidacion.getLiqId() > 0) {
                        Log.d(TAG, "" + cajaLiquidacion.getFechaActualizacion());
                        SugarTransactionHelper.doInTransaction(this);
                    } else {

                        message = "Error de procedimiento";
                    }

                } else {

                    message = "Error de procedimiento";
                }


            } catch (Exception e) {
                e.printStackTrace();
                Log.d(TAG, ": ERROR : " + e.getMessage());

                message = "" + e.getMessage();
            }


            return null;
        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d(TAG, "LIQ: "+cajaLiquidacion.getLiqId()+"");
            new PreciosAsyntask(null).execute(cajaLiquidacion.getLiqId()+"");
            if (isVisible()){
                dismiss();
            }
        }



        @Override
        public void manipulateInTransaction() {
            ManipuleData manipuleData = new ManipuleData();
            manipuleData.saveLiquidacion(cajaLiquidacion);

        }


        @Override
        public void errorInTransaction(String error) {

        }
    }
}
