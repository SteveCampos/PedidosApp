package energigas.apps.systemstrategy.energigas.utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import energigas.apps.systemstrategy.energigas.activities.BluetoothActivity;

/**
 * Created by Steve on 21/07/2016.
 */

public class Utils {

    public static String TAG = "EnergigasApp";

    public static String capitalize(String input) {
        return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
    }

    public static String formatDouble(Double d) {
        return String.format(Locale.ENGLISH, "%.2f", d);
    }

    public static String getNameOfDay(Date date) {
        SimpleDateFormat parseFormat = new SimpleDateFormat("EEEE dd", Locale.getDefault());
        return Utils.capitalize(parseFormat.format(date));
    }

    public static String getDatePhone() {
        Date now = new Date();
        Date alsoNow = Calendar.getInstance().getTime();
        String nowAsString = new SimpleDateFormat("dd/MM/yyyy").format(now);
        return nowAsString;
    }

    public static String getJsonObResult(JSONObject jsonObject) {
        String s = "";
        try {
            s = jsonObject.getJSONObject("Value").toString();
            Log.d("JSON RESULT", "" + jsonObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return s;
    }

    public static boolean isSuccessful(JSONObject jsonObject) {
        boolean s = false;
        try {
            s = jsonObject.getBoolean("Successful");
            Log.d("JSON RESULT", "" + jsonObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return s;
    }

    public static void showMap(Context context, Uri geoLocation) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(geoLocation);
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent);
        }
    }

    public static void saveStateLogin(Context context, boolean state) {
        SharedPreferences mSettings = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = mSettings.edit();
        editor.putBoolean("state_login", state);
        editor.apply();
    }

    public static boolean isSignin(Context context) {
        SharedPreferences mSettings = PreferenceManager.getDefaultSharedPreferences(context);
        return mSettings.getBoolean("state_login", false);
    }

    public static String getQueryForSerie(@NonNull int idUsuario, @NonNull int tipoDevice, @NonNull int tipoComprobante) {

        return "SELECT  S.* FROM SERIE S , DISPOSITIVO_SERIE DS WHERE S.COMP_I_COMPROBANTE_ID = DS.SE_ID  AND DS.DM_ID = (" +
                "SELECT D.DM_ID FROM VEHICULO_DISPOSITIVO VD,  DISPOSITIVO D WHERE  VD.VE_ID = (" +
                "SELECT VE_ID FROM VEHICULO_USUARIO WHERE USUARIO_ID=" + idUsuario + " ) AND D.DM_ID = VD.DM_ID AND D.TIPO_ID = " + tipoDevice + " )  AND S.COMP_I_TIPO_COMPROBANTE_ID = " + tipoComprobante + " ;";
    }

    public static String getQueryForNumberDistPach(@NonNull int idUsuario) {

        return " SELECT ID,USUARIO_CREACION, SERIE,PLACA,LONGITUD,LATITUD,HORA_INICIO,HORA_FIN,GUIA_REMISION,FECHA_DESPACHO,FECHA_CREACION,ESTADO_ID,CANTIDAD_DESPACHADA,DESPACHO_ID,CONTADOR_INICIAL,CONTADOR_FINAL," +
                "PE_ID,P_IT,P_FT,case  when (select count(*) from despacho where  USUARIO_ID = " + idUsuario + " ) is 0 then (select count(*) from despacho where  USUARIO_ID = " + idUsuario + ") +1 else max(numero)+1 end as 'NUMERO',PD_ID,CLIENTE_ID,ESTABLECIMIENTO_ID,PRO_ID,ALMACEN_VEH_ID, UN_ID," +
                "ALMACEN_EST_ID, USUARIO_ID,VEHICULO_ID from despacho where  USUARIO_ID = " + idUsuario + " ";
    }

    public static String completaZeros(String string, int largo) {
        String ceros = "";
        int cantidad = largo - string.length();
        if (cantidad >= 1) {
            for (int i = 0; i < cantidad; i++)
            {
                ceros += "0";
            }
            return (ceros + string);
        } else
            return string;

    }


    public static String getAndroidID(Context context){
        String android_id;
        android_id = get_android_id(context);
        if (TextUtils.isEmpty(android_id)){
            Log.d(TAG, "android_id is empty");
            android_id = get_android_id(context);
        }
        return android_id;
    }
    private static String get_android_id(Context context){
        return Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.ANDROID_ID);
    }

    public static byte[] clearByte(byte[] data, int position){
        byte[] x = new byte[data.length - position];
        for(int i=position; i < data.length; i++){
            x[i-position] = data[i];
        }
        return x;
    }

    public static byte[] clearUnsigned(byte[] x){
        for(int i= 0; i< x.length; i++){
            int signed = x[i] & 0xFF;
            if(signed >= 128){
                x[i] = (byte) 32;
            }
        }
        return x;
    }



}
