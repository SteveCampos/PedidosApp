package energigas.apps.systemstrategy.energigas.asyntask;

import android.os.AsyncTask;
import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.orm.SugarRecord;
import com.orm.SugarTransactionHelper;

import org.json.JSONObject;

import energigas.apps.systemstrategy.energigas.apiRest.RestAPI;
import energigas.apps.systemstrategy.energigas.entities.BEGeneral;
import energigas.apps.systemstrategy.energigas.entities.GeoUbicacion;
import energigas.apps.systemstrategy.energigas.entities.Persona;
import energigas.apps.systemstrategy.energigas.entities.Proveedor;
import energigas.apps.systemstrategy.energigas.interfaces.OnAsyntaskListener;
import energigas.apps.systemstrategy.energigas.utils.Utils;

/**
 * Created by kelvi on 26/12/2016.
 */

public class AsynObtenerDatosGenerales extends AsyncTask<String, String, String> implements SugarTransactionHelper.Callback {


    private static final String TAG = "DatoGenerales";
    private JSONObject jsonObjectConceptos = null;
    private BEGeneral objGeneral;
    private int estado = -1;
    private String estadoDescripcion = "";
    private OnAsyntaskListener loginAsyntaskListener;


    public AsynObtenerDatosGenerales(OnAsyntaskListener loginAsyntaskListener) {
        this.loginAsyntaskListener = loginAsyntaskListener;

    }

    @Override
    protected String doInBackground(String... params) {
        RestAPI restAPI = new RestAPI();
        ObjectMapper mapper = new ObjectMapper();
        try {
            jsonObjectConceptos = restAPI.fobj_ObtenerDatosGenerales();


            if (Utils.isSuccessful(jsonObjectConceptos)) {
                objGeneral = mapper.readValue(Utils.getJsonObResult(jsonObjectConceptos), BEGeneral.class);
                SugarTransactionHelper.doInTransaction(this);
            }
        } catch (Exception e) {
            e.printStackTrace();
            estadoDescripcion = e.getMessage();
            estado = 1;
        }

        return null;
    }

    @Override
    public void manipulateInTransaction() {
        /**DATOS GENERALES**/
        SugarRecord.saveInTx(objGeneral.getItemsConceptos());
        SugarRecord.saveInTx(objGeneral.getItemsEstados());
        SugarRecord.saveInTx(objGeneral.getItemUbigeos());
        SugarRecord.saveInTx(objGeneral.getItemsProductos());
        SugarRecord.saveInTx(objGeneral.getItemsUnidades());
        SugarRecord.saveInTx(objGeneral.getItemsProductoUnidad());
        SugarRecord.saveInTx(objGeneral.getItemsTipos());
        SugarRecord.saveInTx(objGeneral.getProveedoresList());

        for (Proveedor proveedor : objGeneral.getProveedoresList()) {
            Persona persona = proveedor.getPersona();
            GeoUbicacion geoUbicacion = persona.getUbicacion();
            geoUbicacion.save();
            persona.save();
        }

        estado = 2;
        estadoDescripcion = "Importacion Exitosa";
    }

    @Override
    public void errorInTransaction(String error) {
        estado = 3;
        estadoDescripcion = error;
        Log.d(TAG,"error:" + error);
    }

    @Override
    protected void onPostExecute(String s) {
        switch (estado) {
            case 1:
                loginAsyntaskListener.onLoadError(estadoDescripcion);
                break;
            case 2:
                loginAsyntaskListener.onLoadSuccess(estadoDescripcion, null);
                break;
            case 3:
                loginAsyntaskListener.onLoadErrorProcedure(estadoDescripcion);
                break;
        }
    }
}
