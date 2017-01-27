package energigas.apps.systemstrategy.energigas.services;

import android.app.IntentService;
import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.orm.SugarTransactionHelper;

import org.json.JSONObject;

import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.activities.MainActivity;
import energigas.apps.systemstrategy.energigas.apiRest.ManipuleData;
import energigas.apps.systemstrategy.energigas.apiRest.RestAPI;
import energigas.apps.systemstrategy.energigas.apiRest.RestFEAPI;
import energigas.apps.systemstrategy.energigas.entities.AlertaEntity;
import energigas.apps.systemstrategy.energigas.entities.CajaLiquidacion;
import energigas.apps.systemstrategy.energigas.utils.Constants;
import energigas.apps.systemstrategy.energigas.utils.NotificacionManagerUtils;
import energigas.apps.systemstrategy.energigas.utils.Session;
import energigas.apps.systemstrategy.energigas.utils.Utils;

/**
 * Created by kelvi on 26/01/2017.
 */

public class ServiceImport extends IntentService implements SugarTransactionHelper.Callback {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    private CajaLiquidacion cajaLiquidacion;
    private ObjectMapper mapper;
    private Context context;
    private JSONObject jsonObject;
    private RestAPI restAPI;
    private static final String TAG = "ServiceImport";

    public ServiceImport() {
        super(TAG);

    }

    public ServiceImport(String name) {
        super(TAG);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        restAPI = new RestAPI();
        jsonObject = null;
        mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        if (intent != null) {
            final String action = intent.getAction();
            if (Constants.ACTION_IMPORT_SERVICE.equals(action)) {
                importarPedidos();
            }
        }


    }

    private void importarPedidos() {


        try {
            jsonObject = restAPI.fobj_ObtenerLiquidacion(Integer.parseInt(Session.getSession(getApplicationContext()).getUsuIUsuarioId() + ""));
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            Log.d(TAG, "" + jsonObject.toString());
            if (Utils.isSuccessful(jsonObject)) {

                cajaLiquidacion = mapper.readValue(Utils.getJsonObResult(jsonObject), CajaLiquidacion.class);

                SugarTransactionHelper.doInTransaction(this);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void manipulateInTransaction() {
        new ManipuleData().saveLiquidacionNotificacion(cajaLiquidacion, getApplicationContext());
    }


    @Override
    public void errorInTransaction(String error) {

    }
}
