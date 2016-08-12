package energigas.apps.systemstrategy.energigas.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
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

    public static String getJsonObResult(JSONObject jsonObject){
        String s = "";
        try {
            s = jsonObject.getJSONObject("Value").toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return  s;
    }

    public static void showMap(Context context, Uri geoLocation) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(geoLocation);
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent);
        }
    }
}
