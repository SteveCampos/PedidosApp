package energigas.apps.systemstrategy.energigas.utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Steve on 21/07/2016.
 */

public class Utils {

    public static String TAG = "EnergigasApp";

    public static String capitalize(String input){
        return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
    }

    public static String formatDouble(Double d){
        return String.format(Locale.ENGLISH, "%.2f", d);
    }

    public static String getNameOfDay(Date date){
        SimpleDateFormat parseFormat = new SimpleDateFormat("EEEE dd", Locale.getDefault());
        return Utils.capitalize(parseFormat.format(date));
    }

    public static String getDatePhone(){
        Date now = new Date();
        Date alsoNow = Calendar.getInstance().getTime();
        String nowAsString = new SimpleDateFormat("dd/MM/yyyy").format(now);
        return nowAsString;
    }

    public static String getJsonObResult(JSONObject jsonObject){
        String s = "";
        try {
            s = jsonObject.getJSONObject("Value").toString();
            Log.d("JSON RESULT",""+jsonObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return  s;
    }

    public static boolean isSuccessful(JSONObject jsonObject){
        boolean s = false;
        try {
            s = jsonObject.getBoolean("Successful");
            Log.d("JSON RESULT",""+jsonObject.toString());
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

    public static void saveStateLogin(Context context, boolean state){
        SharedPreferences mSettings = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = mSettings.edit();
        editor.putBoolean("state_login", state);
        editor.apply();
    }

    public static boolean isLogin(Context context){
        SharedPreferences mSettings = PreferenceManager.getDefaultSharedPreferences(context);
        return mSettings.getBoolean("state_login", false);
    }
}
