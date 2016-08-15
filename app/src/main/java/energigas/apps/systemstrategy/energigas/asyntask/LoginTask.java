package energigas.apps.systemstrategy.energigas.asyntask;


import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONObject;

import energigas.apps.systemstrategy.energigas.apiRest.RestAPI;
import energigas.apps.systemstrategy.energigas.entities.BEGeneral;
import energigas.apps.systemstrategy.energigas.entities.Usuario;
import energigas.apps.systemstrategy.energigas.interfaces.OnLoginAsyntaskListener;
import energigas.apps.systemstrategy.energigas.persistence.DB_Concepto;
import energigas.apps.systemstrategy.energigas.persistence.DB_Estado;
import energigas.apps.systemstrategy.energigas.persistence.DB_UbicacionGeoreferencia;
import energigas.apps.systemstrategy.energigas.persistence.DB_Usuario;
import energigas.apps.systemstrategy.energigas.utils.Utils;

/**
 * Created by kelvi on 09/08/2016.
 */

public class LoginTask extends AsyncTask<String, String, String> {
    private static final String TAG = "LoginTask";

    private JSONObject jsonObject = null;
    private OnLoginAsyntaskListener aListener;
    private DB_Usuario db_usuario;
    private DB_Concepto db_concepto;
    private DB_Estado db_estado;
    private DB_UbicacionGeoreferencia db_ubicacionGeoreferencia;
    private Context context;
    private boolean aBoolean;

    public LoginTask(OnLoginAsyntaskListener loginAsyntaskListener) {

        this.aListener = loginAsyntaskListener;
        this.context = aListener.getContextActivity();
        this.db_usuario = new DB_Usuario(context);
        this.db_concepto = new DB_Concepto(context);
        this.db_estado = new DB_Estado(context);
        this.db_ubicacionGeoreferencia = new DB_UbicacionGeoreferencia(context);
        db_usuario.open();
        db_concepto.open();
        db_estado.open();
        db_ubicacionGeoreferencia.open();

    }

    @Override
    protected String doInBackground(String... strings) {
        RestAPI restAPI = new RestAPI();
        String usuario = strings[0];
        String clave = strings[1];
        ObjectMapper mapper = new ObjectMapper();
        try {
            jsonObject = restAPI.fobj_ObtenerUsuario(usuario, clave);
            Usuario objUsuario = mapper.readValue(Utils.getJsonObResult(jsonObject), Usuario.class);
            jsonObject = restAPI.fobj_ObtenerDatosGenerales();
            BEGeneral beGeneral = mapper.readValue(Utils.getJsonObResult(jsonObject), BEGeneral.class);
            db_concepto.createAllConcepto(beGeneral);
            db_estado.createAllEstado(beGeneral);
            db_ubicacionGeoreferencia.createUbicacionAllGeoreferencia(beGeneral);



            if (objUsuario.getUsuIUsuarioId() < 0) {
                aListener.onCredentialsFail();
                aBoolean = false;
            } else {
                Log.d(TAG, " USUARIO: " + objUsuario.getUsuIUsuarioId());
                aBoolean = db_usuario.createWithPrivilegesUsuario(objUsuario, context);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "Error: " + e.getMessage());
            aListener.onError(e.getMessage());
        }

        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        db_usuario.close();
        if (aBoolean) {
            aListener.onSuccess();
        }
    }


}
