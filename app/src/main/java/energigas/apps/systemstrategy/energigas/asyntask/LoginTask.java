package energigas.apps.systemstrategy.energigas.asyntask;


import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import energigas.apps.systemstrategy.energigas.interfaces.OnLoginAsyntaskListener;

/**
 * Created by kelvi on 09/08/2016.
 */

public class LoginTask extends AsyncTaskLoader<String> {

    private OnLoginAsyntaskListener loginAsyntaskListener;

    public LoginTask(Context context, OnLoginAsyntaskListener loginAsyntaskListener) {
        super(context);
        this.loginAsyntaskListener = loginAsyntaskListener;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
    }


    @Override
    public String loadInBackground() {
        try {
            Thread.sleep(1600);
            Log.d("LoginActivity", "loadInBackground " );
            loginAsyntaskListener.onErrorProcedure("ERROR");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "hola Kelvin";
    }

    @Override
    public void waitForLoader() {
        super.waitForLoader();
        Log.d("LoginActivity", "waitForLoader " );
    }
}
