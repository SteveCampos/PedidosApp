package energigas.apps.systemstrategy.energigas.asyntask;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.orm.SugarTransactionHelper;

import org.json.JSONObject;


import energigas.apps.systemstrategy.energigas.apiRest.ManipuleData;
import energigas.apps.systemstrategy.energigas.apiRest.RestAPI;
import energigas.apps.systemstrategy.energigas.entities.CajaLiquidacion;
import energigas.apps.systemstrategy.energigas.entities.Concepto;
import energigas.apps.systemstrategy.energigas.entities.Establecimiento;
import energigas.apps.systemstrategy.energigas.fragments.EstablecimientoFragment;
import energigas.apps.systemstrategy.energigas.interfaces.OnAsyntaskListener;
import energigas.apps.systemstrategy.energigas.utils.Constants;
import energigas.apps.systemstrategy.energigas.utils.Utils;

/**
 * Created by kelvi on 3/08/2016.
 */

public class AsyntaskOpenAccount extends AsyncTask<String, Void, Void> implements SugarTransactionHelper.Callback {
    private static final String TAG = "AsyntaskOpenAccount";
    private OnAsyntaskListener onAsyntaskListener;
    private RestAPI restAPI;
    private JSONObject jsonObject;
    private int estado = 0;
    private String message = "";
    private CajaLiquidacion cajaLiquidacion;
    private int codigoId = 0;


    public AsyntaskOpenAccount(OnAsyntaskListener onAsyntaskListener) {
        this.onAsyntaskListener = onAsyntaskListener;
        this.restAPI = new RestAPI();
    }


    @Override
    protected Void doInBackground(String... strings) {

        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            jsonObject = restAPI.fins_GuardarLiquidacion(strings[0]);
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
                    estado = Constants.ERROR_PROCEDIMIENTO;
                    codigoId = Integer.parseInt(cajaLiquidacion.getLiqId() + "");
                    message = "Error de procedimiento";
                }

            } else {
                estado = Constants.ERROR_PROCEDIMIENTO;
                message = "Error de procedimiento";
            }


        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, ": ERROR : " + e.getMessage());
            estado = Constants.ERROR_CONEXION;
            message = "" + e.getMessage();

        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        Concepto concepto = Concepto.getConceptoByCodigo(codigoId + "");
        Log.d("ERRORCONCEPTO", "" + concepto.getDescripcion() + " - " + codigoId);
        switch (estado) {
            case Constants.ERROR_PROCEDIMIENTO:
                onAsyntaskListener.onLoadErrorProcedure(concepto.getDescripcion());
                break;
            case Constants.ERROR_CONEXION:
                onAsyntaskListener.onLoadError(message);
                break;
            case Constants.ERROR_GUARDAR:
                onAsyntaskListener.onLoadError(message);
                break;
            case Constants.OPERACION_EXITOSA:
                onAsyntaskListener.onLoadSuccess(message, cajaLiquidacion);
                break;
            default:
                onAsyntaskListener.onLoadError(message);
                break;
        }

    }


    @Override
    public void manipulateInTransaction() {

        ManipuleData manipuleData = new ManipuleData();
        manipuleData.saveLiquidacion(cajaLiquidacion);

        estado = -4;
        message = "Importancion exitosa.";
    }

    @Override
    public void errorInTransaction(String error) {
        this.message = error;
        estado = Constants.ERROR_GUARDAR;
        Log.d(TAG, "CORRECTAMENTE");
    }


}
