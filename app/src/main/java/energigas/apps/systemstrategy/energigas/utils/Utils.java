package energigas.apps.systemstrategy.energigas.utils;

import java.util.Locale;

/**
 * Created by Steve on 21/07/2016.
 */

public class Utils {
    public static String capitalize(String input){
        return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
    }

    public static String formatDouble(Double d){
        return String.format(Locale.ENGLISH, "%.2f", d);
    }
}
