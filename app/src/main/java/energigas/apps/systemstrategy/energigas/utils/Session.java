package energigas.apps.systemstrategy.energigas.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import energigas.apps.systemstrategy.energigas.entities.Almacen;
import energigas.apps.systemstrategy.energigas.entities.CajaLiquidacion;
import energigas.apps.systemstrategy.energigas.entities.ComprobanteVenta;
import energigas.apps.systemstrategy.energigas.entities.Concepto;
import energigas.apps.systemstrategy.energigas.entities.Despacho;
import energigas.apps.systemstrategy.energigas.entities.Establecimiento;
import energigas.apps.systemstrategy.energigas.entities.Pedido;
import energigas.apps.systemstrategy.energigas.entities.PedidoDetalle;
import energigas.apps.systemstrategy.energigas.entities.Persona;
import energigas.apps.systemstrategy.energigas.entities.PlanPagoDetalle;
import energigas.apps.systemstrategy.energigas.entities.Usuario;

/**
 * Created by kelvi on 30/08/2016.
 */

public class Session {

    public static Concepto getConceptoIGV() {
        List<Concepto> conceptos = Concepto.find(Concepto.class, "id_Concepto=?", new String[]{Constants.CONCEPTO_IGV_ID});
        if (conceptos.size() > 0) {
            return conceptos.get(0);
        }
        return null;
    }

    public static boolean saveIdsDespachos(List<String> despachos, Context context) {

        try {

            Set<String> set = new HashSet<>();
            set.addAll(despachos);
            SharedPreferences.Editor editor = context.getSharedPreferences(Constants.DESPACHOS_IDS, Context.MODE_PRIVATE).edit();
            editor.putStringSet(Constants.DESPACHOS_IDS_ITEMS, set);
            editor.commit();
            return true;

        } catch (Exception e) {
            return false;
        }


    }

    public static String[] getIdsDespachos(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(Constants.DESPACHOS_IDS, Context.MODE_PRIVATE);
        Set<String> set = prefs.getStringSet(Constants.DESPACHOS_IDS_ITEMS, null);
        return set.toArray(new String[set.size()]);
    }

    public static void saveDespacho(Context context, Despacho despacho) {


        ObjectMapper mapper = new ObjectMapper();
        StringWriter sw = new StringWriter();
        try {
            mapper.writeValue(sw, despacho);
            String listObject = sw.toString();
            SharedPreferences.Editor editor = context.getSharedPreferences(Constants.SESSION_DESPACHO, Context.MODE_PRIVATE).edit();
            editor.putString(Constants.IDDESPACHO, listObject);
            editor.apply();
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("ServiceExport", "error: " + e.getMessage());
        }


    }


    public static Despacho getDespacho(Context context) {

        SharedPreferences prefs = context.getSharedPreferences(Constants.SESSION_DESPACHO, Context.MODE_PRIVATE);
        String json = prefs.getString(Constants.IDDESPACHO, null);
        ObjectMapper mapper = new ObjectMapper();
        try {
            Despacho myObjects = mapper.readValue(json, Despacho.class);
            return myObjects;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }


    public static boolean saveAlmacen(Context context, Almacen almacen) {

        try {

            SharedPreferences.Editor editor = context.getSharedPreferences(Constants.SESSION_ALMACEN, Context.MODE_PRIVATE).edit();
            editor.putInt(Constants.IDALMACEN, almacen.getAlmId());
            editor.commit();
            return true;

        } catch (Exception e) {
            return false;
        }


    }


    public static Almacen getAlmacen(Context context) {

        Almacen almacen = new Almacen();
        SharedPreferences prefs = context.getSharedPreferences(Constants.SESSION_ALMACEN, Context.MODE_PRIVATE);
        almacen.setAlmId(prefs.getInt(Constants.IDALMACEN, 0));
        return almacen;

    }


    public static void savePedido(Context context, Pedido pedido) {

        ObjectMapper mapper = new ObjectMapper();
        StringWriter sw = new StringWriter();
        try {
            mapper.writeValue(sw, pedido);
            String listObject = sw.toString();
            SharedPreferences.Editor editor = context.getSharedPreferences(Constants.SESSION_PEDIDO, Context.MODE_PRIVATE).edit();
            editor.putString(Constants.IDPEDIDO, listObject);
            editor.commit();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public static Pedido getPedido(Context context) {

        SharedPreferences prefs = context.getSharedPreferences(Constants.SESSION_PEDIDO, Context.MODE_PRIVATE);
        String json = prefs.getString(Constants.IDPEDIDO, null);
        ObjectMapper mapper = new ObjectMapper();
        try {
            Pedido myObjects = mapper.readValue(json, Pedido.class);
            return myObjects;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static void savePedidoDetalle(Context context, PedidoDetalle pedidoDetalle) {

        ObjectMapper mapper = new ObjectMapper();

        StringWriter sw = new StringWriter();
        try {

            mapper.writeValue(sw, pedidoDetalle);

            String listObject = sw.toString();

            SharedPreferences.Editor editor = context.getSharedPreferences(Constants.SESSION_PEDIDO_DETALLE, Context.MODE_PRIVATE).edit();

            editor.putString(Constants.IDPEDIDODETALLE, listObject);

            editor.commit();

        } catch (IOException e) {

            e.printStackTrace();
        }


    }

    public static PedidoDetalle getPedidoDetalle(Context context) {

        SharedPreferences prefs = context.getSharedPreferences(Constants.SESSION_PEDIDO_DETALLE, Context.MODE_PRIVATE);

        String json = prefs.getString(Constants.IDPEDIDODETALLE, null);

        ObjectMapper mapper = new ObjectMapper();
        try {

            PedidoDetalle myObjects = mapper.readValue(json, PedidoDetalle.class);

            return myObjects;

        } catch (IOException e) {

            e.printStackTrace();
            return null;
        }

    }

    public static void setDefineCuotas(Context context, boolean estado, String list) {


        SharedPreferences.Editor editor = context.getSharedPreferences(Constants.DEFINE_CUOTAS, Context.MODE_PRIVATE).edit();
        editor.putBoolean(Constants.DEFINE_CUOTAS_ESTADO, estado);
        editor.putString(Constants.OBJECTS_LIST_DETALLE_CUOTAS, list);
        editor.commit();


    }

    public static boolean getTipoDespachoSN(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(Constants.DEFINE_TIPO_DESPACHO_SN, Context.MODE_PRIVATE);
        return prefs.getBoolean(Constants.DEFINE_TIPO_DESPACHO_SN_ESTADO, false);
    }


    public static void saveTipoDespachoSN(Context context, boolean estado) {


        SharedPreferences.Editor editor = context.getSharedPreferences(Constants.DEFINE_TIPO_DESPACHO_SN, Context.MODE_PRIVATE).edit();
        editor.putBoolean(Constants.DEFINE_TIPO_DESPACHO_SN_ESTADO, estado);
        editor.commit();

    }


    public static boolean getDefineCuotas(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(Constants.DEFINE_CUOTAS, Context.MODE_PRIVATE);
        return prefs.getBoolean(Constants.DEFINE_CUOTAS_ESTADO, false);
    }

    public static List<PlanPagoDetalle> getListCuotas(Context context) {


        SharedPreferences prefs = context.getSharedPreferences(Constants.DEFINE_CUOTAS, Context.MODE_PRIVATE);
        String json = prefs.getString(Constants.OBJECTS_LIST_DETALLE_CUOTAS, null);

        if (!prefs.getString(Constants.OBJECTS_LIST_DETALLE_CUOTAS, null).equals("0"))
        {
            ObjectMapper mapper = new ObjectMapper();
            try {
                List<PlanPagoDetalle> myObjects = mapper.readValue(json, new TypeReference<List<PlanPagoDetalle>>() {
                });
                return myObjects;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }

        }
        return null;
    }


    public static boolean saveEstablecimiento(Context context, Establecimiento establecimiento) {

        try {

            SharedPreferences.Editor editor = context.getSharedPreferences(Constants.SESSION_ESTABLECIMIENTO, Context.MODE_PRIVATE).edit();
            editor.putInt(Constants.IDESTABLECIMIENTO, establecimiento.getEstIEstablecimientoId());
            editor.putString(Constants.ESTABLECIMIENTO, establecimiento.getEstVDescripcion());
            editor.commit();
            return true;

        } catch (Exception e) {
            return false;
        }


    }


    public static Establecimiento getSessionEstablecimiento(Context context) {

        Establecimiento establecimiento = new Establecimiento();
        SharedPreferences prefs = context.getSharedPreferences(Constants.SESSION_ESTABLECIMIENTO, Context.MODE_PRIVATE);
        establecimiento.setEstIEstablecimientoId(prefs.getInt(Constants.IDESTABLECIMIENTO, 0));
        establecimiento.setEstVDescripcion(prefs.getString(Constants.ESTABLECIMIENTO, null));
        return establecimiento;

    }


    public static boolean saveSession(Context context, Usuario usuario) {

        try {

            SharedPreferences.Editor editor = context.getSharedPreferences(Constants.SESSION, Context.MODE_PRIVATE).edit();
            editor.putInt(Constants.IDUSUARIO, usuario.getUsuIUsuarioId());
            editor.putString(Constants.USUARIO, usuario.getUsuVUsuario() + "");
            editor.putString(Constants.NOMBRE, usuario.getPersona().getPerVNombres());
            editor.putString(Constants.APELLIDO_PATERNO, usuario.getPersona().getPerVApellidoPaterno());
            editor.putString(Constants.APELLIDO_MATERNO, usuario.getPersona().getPerVApellidoMaterno());
            editor.apply();
            return true;

        } catch (Exception e) {
            return false;
        }


    }

    public static Usuario getSession(Context context) {

        Usuario usuario = new Usuario();
        Persona persona = new Persona();
        SharedPreferences prefs = context.getSharedPreferences(Constants.SESSION, Context.MODE_PRIVATE);
        usuario.setUsuIUsuarioId(prefs.getInt(Constants.IDUSUARIO, 0));
        usuario.setUsuVUsuario(prefs.getString(Constants.USUARIO, null));
        persona.setPerVNombres(prefs.getString(Constants.NOMBRE, null));
        persona.setPerVApellidoPaterno(prefs.getString(Constants.APELLIDO_PATERNO, null));
        persona.setPerVApellidoMaterno(prefs.getString(Constants.APELLIDO_MATERNO, null));
        usuario.setPersona(persona);
        return usuario;

    }

    public static boolean saveCajaLiquidacion(Context context, CajaLiquidacion cajaLiquidacion) {
        try {

            SharedPreferences.Editor editor = context.getSharedPreferences(Constants.CAJA_LIQUIDACION, Context.MODE_PRIVATE).edit();
            editor.putLong(Constants.CAJA_LIQUIDACION_ID, cajaLiquidacion.getLiqId());
            editor.commit();
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    public static CajaLiquidacion getCajaLiquidacion(Context context) {

        SharedPreferences prefs = context.getSharedPreferences(Constants.CAJA_LIQUIDACION, Context.MODE_PRIVATE);
        CajaLiquidacion cajaLiquidacion = new CajaLiquidacion();
        cajaLiquidacion.setLiqId(prefs.getLong(Constants.CAJA_LIQUIDACION_ID, 0));
        return cajaLiquidacion;
    }

    public static boolean saveComprobanteVenta(Context context, ComprobanteVenta comprobanteVenta) {
        try {

            SharedPreferences.Editor editor = context.getSharedPreferences(Constants.SESSION_COMPROBANTE_VENTA, Context.MODE_PRIVATE).edit();
            editor.putLong(Constants.SESSION_COMPROBANTE_VENTA_ID, comprobanteVenta.getCompId());
            editor.commit();
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    public static ComprobanteVenta getComprobanteVenta(Context context) {

        SharedPreferences prefs = context.getSharedPreferences(Constants.SESSION_COMPROBANTE_VENTA, Context.MODE_PRIVATE);
        ComprobanteVenta comprobanteVenta = new ComprobanteVenta();
        comprobanteVenta.setCompId(prefs.getLong(Constants.SESSION_COMPROBANTE_VENTA_ID, 0));
        return comprobanteVenta;
    }
}
