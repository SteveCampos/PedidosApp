package energigas.apps.systemstrategy.energigas.interfaces;

/**
 * Created by kelvi on 8/08/2016.
 */

public interface OnAsyntaskListener {

    void onLoadSuccess(String message);

    void onLoadError(String message);

    void onLoadErrorProcedure(String message);
}
