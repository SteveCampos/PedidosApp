/* JSON API for android application [Energigas] */
package energigas.apps.systemstrategy.energigas.apiRest;

import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;
import org.json.JSONArray;

import energigas.apps.systemstrategy.energigas.entities.Servidores;
import energigas.apps.systemstrategy.energigas.utils.Constants;
import energigas.apps.systemstrategy.energigas.utils.Session;

public class RestAPI {
    // private final String urlString = "http://192.168.0.158/ServiciosMovil/ServiceDistribucion.ashx";

    Servidores servidores = Servidores.find(Servidores.class, "name = ? ", new String[]{"Distribucion"}).get(0);
    private final String urlString = Constants.URL_PRE + servidores.getDescription() + Constants.URL_POST_DISTRIBUCION;

    private static String convertStreamToUTF8String(InputStream stream) throws IOException {
        String result = "";
        StringBuilder sb = new StringBuilder();
        try {
            InputStreamReader reader = new InputStreamReader(stream, "UTF-8");
            char[] buffer = new char[4096];
            int readedChars = 0;
            while (readedChars != -1) {
                readedChars = reader.read(buffer);
                if (readedChars > 0)
                    sb.append(buffer, 0, readedChars);
            }
            result = sb.toString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }


    private String load(String contents) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setConnectTimeout(60000);
        conn.setDoOutput(true);
        conn.setDoInput(true);
        OutputStreamWriter w = new OutputStreamWriter(conn.getOutputStream());
        w.write(contents);
        w.flush();
        InputStream istream = conn.getInputStream();
        String result = convertStreamToUTF8String(istream);
        return result;
    }


    private Object mapObject(Object o) {
        Object finalValue = null;
        if (o.getClass() == String.class) {
            finalValue = o;
        } else if (Number.class.isInstance(o)) {
            finalValue = String.valueOf(o);
        } else if (Date.class.isInstance(o)) {
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss", new Locale("en", "USA"));
            finalValue = sdf.format((Date) o);
        } else if (Collection.class.isInstance(o)) {
            Collection<?> col = (Collection<?>) o;
            JSONArray jarray = new JSONArray();
            for (Object item : col) {
                jarray.put(mapObject(item));
            }
            finalValue = jarray;
        } else {
            Map<String, Object> map = new HashMap<String, Object>();
            Method[] methods = o.getClass().getMethods();
            for (Method method : methods) {
                if (method.getDeclaringClass() == o.getClass()
                        && method.getModifiers() == Modifier.PUBLIC
                        && method.getName().startsWith("get")) {
                    String key = method.getName().substring(3);
                    try {
                        Object obj = method.invoke(o, null);
                        Object value = mapObject(obj);
                        map.put(key, value);
                        finalValue = new JSONObject(map);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

        }
        return finalValue;
    }

    public JSONObject fobj_ObtenerUsuario(String vstr_Usuario, String vstr_Pass) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "RestAPI");
        o.put("method", "fobj_ObtenerUsuario");
        p.put("vstr_Usuario", mapObject(vstr_Usuario));
        p.put("vstr_Pass", mapObject(vstr_Pass));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject fobj_ObtenerDatosGenerales() throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "RestAPI");
        o.put("method", "fobj_ObtenerDatosGenerales");
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject fins_GuardarLiquidacion(String vstr_Liquidacion) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "RestAPI");
        o.put("method", "fins_GuardarLiquidacion");
        p.put("vstr_Liquidacion", mapObject(vstr_Liquidacion));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject fobj_ObtenerLiquidacionId(long vint_LiquidacionId) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "RestAPI");
        o.put("method", "fobj_ObtenerLiquidacionId");
        p.put("vint_LiquidacionId", mapObject(vint_LiquidacionId));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject fobj_ObtenerLiquidacion(int vint_UsuarioId) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "RestAPI");
        o.put("method", "fobj_ObtenerLiquidacion");
        p.put("vint_UsuarioId", mapObject(vint_UsuarioId));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject fins_GuardarDespacho(ArrayList<Object> vstr_Despacho) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "RestAPI");
        o.put("method", "fins_GuardarDespacho");
        p.put("vstr_Despacho", mapObject(vstr_Despacho));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject fins_SaveComprobanteVenta(ArrayList<Object> vlst_ComprobanteVenta) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "RestAPI");
        o.put("method", "fins_SaveComprobanteVenta");
        p.put("vlst_ComprobanteVenta", mapObject(vlst_ComprobanteVenta));
        o.put("parameters", p);
        Log.d("ServiceExport", "COMPROVENTA: " + p.toString());
        Log.d("ServiceExport", "SEND: " + o.toString());
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject fins_GuardarGasto(String vstr_Gasto) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "RestAPI");
        o.put("method", "fins_GuardarGasto");
        p.put("vstr_Gasto", mapObject(vstr_Gasto));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject fins_SaveGasto(ArrayList<Object> vlst_Gasto) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "RestAPI");
        o.put("method", "fins_SaveGasto");
        p.put("vlst_Gasto", mapObject(vlst_Gasto));
        o.put("parameters", p);
        Log.d("PRAMS", "" + p.toString());
        String s = o.toString();
        Log.d("ServiceExport", "SEND: " + o.toString());
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject fins_GuardarCobro(String vstr_CajaMov, String vstr_CajaComp, String vstr_CajaPago) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "RestAPI");
        o.put("method", "fins_GuardarCobro");
        p.put("vstr_CajaMov", mapObject(vstr_CajaMov));
        p.put("vstr_CajaComp", mapObject(vstr_CajaComp));
        p.put("vstr_CajaPago", mapObject(vstr_CajaPago));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject fupd_EstadoGasto(long vint_infGastoId, int vint_usuarioAcc) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "RestAPI");
        o.put("method", "fupd_EstadoGasto");
        p.put("vint_infGastoId", mapObject(vint_infGastoId));
        p.put("vint_usuarioAcc", mapObject(vint_usuarioAcc));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject flist_ListaPreciosDetalle(long vint_LiquidacionId) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "RestAPI");
        o.put("method", "flist_ListaPreciosDetalle");
        p.put("vint_LiquidacionId", mapObject(vint_LiquidacionId));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject flist_ListaPrecios(long vint_LiquidacionId) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "RestAPI");
        o.put("method", "flist_ListaPrecios");
        p.put("vint_LiquidacionId", mapObject(vint_LiquidacionId));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject flst_UpdateDetalleLiquidacion(ArrayList<Object> vlst_LiqDetalle) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "RestAPI");
        o.put("method", "flst_UpdateDetalleLiquidacion");
        p.put("vlst_LiqDetalle", mapObject(vlst_LiqDetalle));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }


    public JSONObject fupd_CajaLiquidacion(Object vobj_Liquidacion) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "RestAPI");
        o.put("method", "fupd_CajaLiquidacion");
        p.put("vobj_Liquidacion", mapObject(vobj_Liquidacion));
        o.put("parameters", p);
        Log.d("CERRAR_CAJA_P", o.toString());
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject fins_GuardarOrdenCargo(Object vobj_OrdenCargo) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "RestAPI");
        o.put("method", "fins_GuardarOrdenCargo");
        p.put("vobj_OrdenCargo", mapObject(vobj_OrdenCargo));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }


    public JSONObject fupd_ActualizarRecorrido(int vint_AgenteId,String vstr_Latitud,String vstr_Longitud) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface","RestAPI");
        o.put("method", "fupd_ActualizarRecorrido");
        p.put("vint_AgenteId",mapObject(vint_AgenteId));
        p.put("vstr_Latitud",mapObject(vstr_Latitud));
        p.put("vstr_Longitud",mapObject(vstr_Longitud));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

}