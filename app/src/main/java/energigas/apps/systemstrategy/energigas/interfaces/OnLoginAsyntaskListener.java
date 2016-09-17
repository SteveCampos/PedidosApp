package energigas.apps.systemstrategy.energigas.interfaces;

import android.content.Context;
import android.support.annotation.NonNull;

import energigas.apps.systemstrategy.energigas.entities.CajaLiquidacion;

/**
 * Created by kelvi on 09/08/2016.
 */

public interface OnLoginAsyntaskListener {


    void onError(@NonNull String message);

    void onSuccess(CajaLiquidacion cajaLiquidacion);

    void onErrorProcedure(@NonNull String message);

    void onCredentialsFail();

    Context getContextActivity();

}
