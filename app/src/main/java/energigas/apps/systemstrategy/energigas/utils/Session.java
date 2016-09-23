package energigas.apps.systemstrategy.energigas.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import energigas.apps.systemstrategy.energigas.entities.Almacen;
import energigas.apps.systemstrategy.energigas.entities.CajaLiquidacion;
import energigas.apps.systemstrategy.energigas.entities.Despacho;
import energigas.apps.systemstrategy.energigas.entities.Establecimiento;
import energigas.apps.systemstrategy.energigas.entities.Pedido;
import energigas.apps.systemstrategy.energigas.entities.Persona;
import energigas.apps.systemstrategy.energigas.entities.Usuario;

/**
 * Created by kelvi on 30/08/2016.
 */

public class Session {

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
        return set.toArray(new String[set.size()]) ;
    }

    public static boolean saveDespacho(Context context, Despacho despacho) {

        try {

            SharedPreferences.Editor editor = context.getSharedPreferences(Constants.SESSION_DESPACHO, Context.MODE_PRIVATE).edit();
            editor.putLong(Constants.IDDESPACHO, despacho.getDespachoId());
            editor.commit();
            return true;

        } catch (Exception e) {
            return false;
        }


    }


    public static Despacho getDespacho(Context context) {

        Despacho despacho = new Despacho();
        SharedPreferences prefs = context.getSharedPreferences(Constants.SESSION_DESPACHO, Context.MODE_PRIVATE);
        despacho.setDespachoId(prefs.getLong(Constants.IDDESPACHO, 0));
        return despacho;

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


    public static boolean savePedido(Context context, Pedido pedido) {

        try {

            SharedPreferences.Editor editor = context.getSharedPreferences(Constants.SESSION_PEDIDO, Context.MODE_PRIVATE).edit();
            editor.putLong(Constants.IDPEDIDO, pedido.getPeId());
            editor.commit();
            return true;

        } catch (Exception e) {
            return false;
        }


    }


    public static Pedido getPedido(Context context) {

        Pedido pedido = new Pedido();
        SharedPreferences prefs = context.getSharedPreferences(Constants.SESSION_PEDIDO, Context.MODE_PRIVATE);
        pedido.setPeId(prefs.getLong(Constants.IDPEDIDO, 0));
        return pedido;

    }


    public static boolean saveEstablecimiento(Context context, Establecimiento establecimiento) {

        try {

            SharedPreferences.Editor editor = context.getSharedPreferences(Constants.SESSION_ESTABLECIMIENTO, Context.MODE_PRIVATE).edit();
            editor.putInt(Constants.IDESTABLECIMIENTO, establecimiento.getEstIEstablecimientoId());
            editor.putString(Constants.ESTABLECIMIENTO, establecimiento.getEstVDescripcion());
            editor.apply();
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
            editor.putString(Constants.USUARIO, usuario.getUsuVUsuario());
            editor.putString(Constants.NOMBRE, usuario.getPersona().getPerVNombres());
            editor.putString(Constants.APELLIDO_PATERNO, usuario.getPersona().getPerVApellidoPaterno());
            editor.putString(Constants.APELLIDO_MATERNO, usuario.getPersona().getPerVApellidoMaterno());
            editor.commit();
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

    public static boolean saveCajaLiquidacion (Context context,CajaLiquidacion cajaLiquidacion){
        try {

            SharedPreferences.Editor editor = context.getSharedPreferences(Constants.CAJA_LIQUIDACION, Context.MODE_PRIVATE).edit();
            editor.putLong(Constants.CAJA_LIQUIDACION_ID,cajaLiquidacion.getLiqId());
            editor.apply();
            return true;

        } catch (Exception e) {
            return false;
        }
    }
    public static CajaLiquidacion getCajaLiquidacion (Context context){

        SharedPreferences prefs = context.getSharedPreferences(Constants.CAJA_LIQUIDACION, Context.MODE_PRIVATE);
        CajaLiquidacion cajaLiquidacion =  new CajaLiquidacion();
        cajaLiquidacion.setLiqId(prefs.getLong(Constants.CAJA_LIQUIDACION_ID,0));
        return cajaLiquidacion;
    }
}
