package energigas.apps.systemstrategy.energigas.asyntask;

import android.os.AsyncTask;
import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.orm.SugarRecord;
import com.orm.SugarTransactionHelper;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import energigas.apps.systemstrategy.energigas.apiRest.RestAPI;
import energigas.apps.systemstrategy.energigas.entities.BERespuestaAtencion;
import energigas.apps.systemstrategy.energigas.entities.CajaLiquidacion;
import energigas.apps.systemstrategy.energigas.entities.CajaLiquidacionDetalle;
import energigas.apps.systemstrategy.energigas.entities.CajaMovimiento;
import energigas.apps.systemstrategy.energigas.entities.ListaPrecio;
import energigas.apps.systemstrategy.energigas.entities.ListaPrecioDetalle;
import energigas.apps.systemstrategy.energigas.entities.de.BEDocElectronico;
import energigas.apps.systemstrategy.energigas.utils.Utils;

/**
 * Created by kike on 21/12/2016.
 */

public class AtencionesAsyntask extends AsyncTask<String,String,String> implements SugarTransactionHelper.Callback{


    private RestAPI restAPI;
    private JSONObject jsonObjectResponse;
    private ObjectMapper mapper;
    private List<CajaLiquidacionDetalle> listCajeLiquidacionDetalle;
    private static final String TAG = "AtencionesAsyntask";





    @Override
    protected String doInBackground(String... params) {
        jsonObjectResponse =null;
        restAPI = new RestAPI();
        mapper = new ObjectMapper();
        /* Trae a todo CajaLiquidacionDetalle.listAll(CajaLiquidacionDetalle.class); */
        List<CajaLiquidacionDetalle> mCajaLiquidacionDetalle = CajaLiquidacionDetalle.listAll(CajaLiquidacionDetalle.class);
        Log.d(TAG,"mCajaLiquidacionDetalle"+mCajaLiquidacionDetalle.size());
        if(mCajaLiquidacionDetalle.size()>0){
            for(CajaLiquidacionDetalle liquidacion: mCajaLiquidacionDetalle){
                Log.d(TAG,"CajaLiquidacionDetalle liquidacion: "+liquidacion.getLidId());
            }

            try {
                jsonObjectResponse = restAPI.flst_UpdateDetalleLiquidacion(new ArrayList<Object>(mCajaLiquidacionDetalle));
                Log.d(TAG,"JSON jsonObjectResponse: "+jsonObjectResponse.toString());
                List<BERespuestaAtencion> beRespuestaAtencions = mapper.readValue(Utils.getJsonArResult(jsonObjectResponse), TypeFactory.defaultInstance().constructCollectionType(List.class,
                        BERespuestaAtencion.class));
                Log.d(TAG,"JSON LIQUIDACION: "+jsonObjectResponse);

                for (BERespuestaAtencion beRespuestaAtencion : beRespuestaAtencions) {
                    for (CajaLiquidacionDetalle cajaLiquidacionDetalle : mCajaLiquidacionDetalle) {

                        if (beRespuestaAtencion.getIdLiqDetalle() == cajaLiquidacionDetalle.getLidId()) {
                            cajaLiquidacionDetalle.setLiId(beRespuestaAtencion.getIdLiq());

                            if(beRespuestaAtencion.getRespuesta()<0){
                                Log.d(TAG,"beRespuestaAtencion.getRespuesta()<0"+beRespuestaAtencion.getRespuesta());
                            }
                        }
                    }
                }
            }catch (Exception ex){
                Log.d(TAG,"EXEPTION: "+ex.getMessage());
            }

        }

        return null;
    }

    @Override
    public void manipulateInTransaction() {
        for(int i =0;i<listCajeLiquidacionDetalle.size();i++){
            Log.d(TAG,"OBJETO LIQUIDACION"+listCajeLiquidacionDetalle.get(i).toString());
        }
        SugarRecord.saveInTx(listCajeLiquidacionDetalle);
    }

    @Override
    public void errorInTransaction(String error) {
        Log.d(TAG,"GUARDAR ERROR: "+error);
    }
}
