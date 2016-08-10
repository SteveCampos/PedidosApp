package energigas.apps.systemstrategy.energigas.interfaces;

import android.support.annotation.NonNull;

/**
 * Created by kelvi on 09/08/2016.
 */

public interface OnLoginAsyntaskListener {

    void onError(@NonNull String message);

    void onSuccess();

    void onErrorProcedure(@NonNull String message);

    void onCredentialsFail();

}
