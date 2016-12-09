package energigas.apps.systemstrategy.energigas.asyntask;

import android.os.AsyncTask;
import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.orm.SugarRecord;
import com.orm.SugarTransactionHelper;

import org.json.JSONObject;

import java.util.List;

import energigas.apps.systemstrategy.energigas.apiRest.RestAPI;
import energigas.apps.systemstrategy.energigas.entities.ListaPrecio;
import energigas.apps.systemstrategy.energigas.entities.ListaPrecioDetalle;
import energigas.apps.systemstrategy.energigas.interfaces.OnAsyntaskListener;
import energigas.apps.systemstrategy.energigas.utils.Utils;

/**
 * Created by kelvi on 01/11/2016.
 */

public class PreciosAsyntask extends AsyncTask<String, String, String> implements SugarTransactionHelper.Callback {

    private OnAsyntaskListener onAsyntaskListener;
    private RestAPI restAPI;
    private JSONObject jsonObjectResponse;
    private ObjectMapper mapper;
    private List<ListaPrecio> listaPrecios;
    private List<ListaPrecioDetalle> listaPrecioDetalles;
    private static final String TAG = "PreciosAsyntask";

    public PreciosAsyntask(OnAsyntaskListener onAsyntaskListener) {
        this.onAsyntaskListener = onAsyntaskListener;
        restAPI = new RestAPI();
        mapper = new ObjectMapper();

    }

    @Override
    protected String doInBackground(String... params) {
        Long liquidacionId = Long.parseLong(params[0]);
        try {
            jsonObjectResponse = restAPI.flist_ListaPrecios(liquidacionId);
            Log.d(TAG," LIQUIDACION "+liquidacionId+"");
            listaPrecios = mapper.readValue(Utils.getJsonArResult(jsonObjectResponse), TypeFactory.defaultInstance().constructCollectionType(List.class,
                    ListaPrecio.class));
            Log.d(TAG,"JSON PRECIO: "+jsonObjectResponse);
            jsonObjectResponse = restAPI.flist_ListaPreciosDetalle(liquidacionId);
            Log.d(TAG,"JSON PRECIO DETALLE: "+jsonObjectResponse);
            listaPrecioDetalles = mapper.readValue(Utils.getJsonArResult(jsonObjectResponse), TypeFactory.defaultInstance().constructCollectionType(List.class,
                    ListaPrecioDetalle.class));

            if (listaPrecios!=null && listaPrecioDetalles!=null ){
                SugarTransactionHelper.doInTransaction(this);
            }



        } catch (Exception e) {
            Log.d(TAG,"EXEPTION: "+e.getMessage());
        }
        return null;
    }

    @Override
    public void manipulateInTransaction() {

        for(int i =0;i<listaPrecios.size();i++){
            Log.d(TAG,"OBJETO PRECIO"+listaPrecios.get(i).toString());
        }
        for(int i =0;i<listaPrecioDetalles.size();i++){
            Log.d(TAG,"OBJECTO PRECIO DETALLE: "+listaPrecioDetalles.get(i).toString());
        }

        SugarRecord.saveInTx(listaPrecios);
        SugarRecord.saveInTx(listaPrecioDetalles);
    }

    @Override
    public void errorInTransaction(String error) {
        Log.d(TAG,"GUARDAR ERROR: "+error);
    }
}
