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
import android.widget.DatePicker;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
    public static double getDoubleFormat(double number){

        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.ROOT);
        otherSymbols.setDecimalSeparator('.');
        //otherSymbols.setGroupingSeparator('.');
        DecimalFormat df = new DecimalFormat("#.0000", otherSymbols);
        return Double.parseDouble(df.format(number));
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

    public static String getDatePhoneTime() {
        Date now = new Date();
        Date alsoNow = Calendar.getInstance().getTime();
        String nowAsString = new SimpleDateFormat("dd/MM/yyyy :hh:").format(now);
        return nowAsString;
    }

    public static java.util.Date getDateFromDatePicker(DatePicker datePicker){
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year =  datePicker.getYear();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        return calendar.getTime();
    }

    public static Long getDateFromDatePickerMills(DatePicker datePicker){
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year =  datePicker.getYear();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        return calendar.getTimeInMillis();
    }

    public static Long getDateMills(String fecha){

        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        try {
            date = formatter.parse(fecha);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }
    public static Date getDateFromString(String fecha){

        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        try {
            date = formatter.parse(fecha);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
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

    public static String getQueryForNumberDistPach() {

        return " SELECT ID,USUARIO_CREACION, SERIE,PLACA,LONGITUD,LATITUD,HORA_INICIO,HORA_FIN,GUIA_REMISION,FECHA_DESPACHO,FECHA_CREACION,ESTADO_ID,CANTIDAD_DESPACHADA,DESPACHO_ID,CONTADOR_INICIAL,CONTADOR_FINAL," +
                "PE_ID,P_IT,P_FT,case  when (select count(*) from despacho ) is 0 then (select count(*) from despacho) +1 else max(numero)+1 end as 'NUMERO',PD_ID,CLIENTE_ID,ESTABLECIMIENTO_ID,PRO_ID,ALMACEN_VEH_ID, UN_ID," +
                "ALMACEN_EST_ID, USUARIO_ID,VEHICULO_ID from despacho  ";
    }

    public static String getQueryNumberSell() {

        return "SELECT ID,VALOR_RESUMEN, SERIE,CLIENTE," +
                "CASE  WHEN (SELECT COUNT(*) FROM COMPROBANTE_VENTA  ) IS 0 THEN (SELECT COUNT(*) FROM COMPROBANTE_VENTA  ) +1 ELSE MAX(NUM_DOC)+1 end as 'NUM_DOC' " +
                ",FECHA_DOC,FECHA_CREACION,DIRECCION_CLIENTE,DOC_IDENTIDAD,FECHA_ACTUALIZACION,TOTAL,BASE_IMPONIBLE,SALDO,COMP_ID,LQ_ID,IGV,FORMA_PAGO_ID,COM_I_USUARIO_ID," +
                "CLIENTE_ID,POR_IMPUESTO,ESTABLECIMIENTO_ID,EXPORTADO,TIPO_COMPROBANTE_ID,TIPO_VENTA_ID,ESTADO_ID,ANULADO FROM COMPROBANTE_VENTA ;";
    }

    public static String getQueryNumberCajaMov() {

        return "SELECT ID,REFERENCIA_ANDROID,REFERENCIA,MOTIVO_ANULADO,FECHA_ACCION,FECHA_HORA,MONEDA,USUARIO_ID,IMPORTE,LIQ_ID," +
                "CASE  WHEN (SELECT COUNT(*) FROM CAJA_MOVIMIENTO ) IS 0 THEN (SELECT COUNT(*) FROM CAJA_MOVIMIENTO  ) +1 ELSE MAX(CAJ_MOV_ID)+1 END AS 'CAJ_MOV_ID' " +
                ",CAT_MOV_ID,TIPO_MOV_ID,ESTADO FROM CAJA_MOVIMIENTO  ;";
    }

    public static String getQueryNumberInform(){
        return"SELECT  ID,REFERENCIA,FECHA_ACCION,CAT_TIPO_GASTO_ID,INF_GAS_ID,TIPO_GASTO_ID,USUARIO_ACCION," +
                "CASE WHEN(SELECT COUNT(*) FROM INFORME_GASTO) IS 0 THEN (SELECT COUNT(*)FROM INFORME_GASTO)+1 ELSE MAX(INF_GAS_ID)+1 END AS 'INF_GAS_ID'\n" +
                "FROM INFORME_GASTO   ;";

    }

    public static String getQueryNumberPlanPago() {
        return "SELECT ID,SERIE,NUM_DOC,FECHA_ACCION,FECHA_PAGO,GLOSA,COMP_ID," +
                "CASE  WHEN (SELECT COUNT(*) FROM PLAN_PAGO ) IS 0 THEN (SELECT COUNT(*) FROM PLAN_PAGO ) +1 ELSE MAX(PLAN_PA_ID)+1 END AS 'PLAN_PA_ID' " +
                ",PORCENTAJE_INTERES_MES,ESTADO,USUARIO_ACCION " +
                "FROM PLAN_PAGO ;";
    }

    public static String getQueryNumberPlanPagoDetalle() {
        return "SELECT ID,FECHA_COBRO,FECHA_ACCION,FECHA,PLAN_PA_ID,CAJ_MOV_ID,IMPORTE,IMPORTE_BASE,INTERES,MONTO_A_PAGAR," +
                "CASE  WHEN (SELECT COUNT(*) FROM PLAN_PAGO_DETALLE ) IS 0 THEN (SELECT COUNT(*) FROM PLAN_PAGO_DETALLE  ) +1 ELSE MAX(PLAN_PA_DE_ID)+1 END AS 'PLAN_PA_DE_ID' " +
                ",ESTADO,USUARIO_ACCION FROM PLAN_PAGO_DETALLE ";
    }

    public static String getQueryNumberCajaComprobante() {
        return "SELECT " +
                "ID ,FECHA_ACTUALIZACION,CAJ_MOV_ID ,  COMP_ID, " +
                " CASE  WHEN (SELECT COUNT(*) FROM CAJA_COMPROBANTE) IS 0 THEN (SELECT COUNT(*) FROM CAJA_COMPROBANTE) +1 ELSE MAX(CAJ_COMP_ID)+1 END AS 'CAJ_COMP_ID'  " +
                " ,IMPORTE,USUARIO_ACTUALIZACION FROM CAJA_COMPROBANTE ;";
    }

    public static String getQueryNumberCajaPago() {
        return " SELECT ID,FECHA_ACCION,CAJ_MOV_ID, " +
                " CASE  WHEN (SELECT COUNT(*) FROM CAJA_PAGO) IS 0 THEN (SELECT COUNT(*) FROM CAJA_PAGO) +1 ELSE MAX(CAJ_PAG_ID)+1 END AS 'CAJ_PAG_ID'   " +
                " ,IMPORTE,ANULADO,EXPORTADO,TIPO_PAGO_ID,USUARIO_ID FROM CAJA_PAGO;";
    }

    public static String getQueryNumberComprobanteVentaDetalle() {
        return "SELECT ID,FECHA_ACTUALIZACION,DESPACHO_ID," +
                "CASE  WHEN (SELECT COUNT(*) FROM COMPROBANTE_VENTA_DETALLE ) IS 0 THEN (SELECT COUNT(*) FROM COMPROBANTE_VENTA_DETALLE ) +1 ELSE MAX(COMPD_ID)+1 END AS 'COMPD_ID'," +
                "COSTO_VENTA,COMP_ID,CANTIDAD,IMPORTE,PRECIO,PRECIO_UNITARIO,PRO_ID,UNIDAD_ID,USUARIO_ACTUALIZACION FROM COMPROBANTE_VENTA_DETALLE ;";
    }

    public static String completaZeros(String numero, int largo) {
        String ceros = "";
        int cantidad = largo - numero.length();
        if (cantidad >= 1) {
            for (int i = 0; i < cantidad; i++) {
                ceros += "0";
            }
            return (ceros + numero);
        } else
            return numero;

    }


    public static Date sumarFechasDias(Date fch, int dias) {
        Calendar cal = new GregorianCalendar();
        cal.setTimeInMillis(fch.getTime());
        cal.add(Calendar.DATE, dias);
        return new java.sql.Date(cal.getTimeInMillis());
    }

    public static Date restarFechasDias(Date fch, int dias) {
        Calendar cal = new GregorianCalendar();
        cal.setTimeInMillis(fch.getTime());
        cal.add(Calendar.DATE, (dias*=-1) );
        return new java.sql.Date(cal.getTimeInMillis());
    }

    public static String getStringDate(Date date) {

        Date alsoNow = date;
        String nowAsString = new SimpleDateFormat("dd/MM/yyyy").format(alsoNow);
        return nowAsString;
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

    public static String getQuerDespachoVehiculo(int idUsuario){
        return " SELECT * FROM ALMACEN WHERE VEHICULO_ID=(SELECT VE_ID FROM VEHICULO_USUARIO WHERE USUARIO_ID = "+idUsuario+" )";
    }


}
