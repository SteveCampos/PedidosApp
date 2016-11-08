package energigas.apps.systemstrategy.energigas.interfaces;

/**
 * Created by kelvi on 28/10/2016.
 */

public interface ExportObjectsListener {

    void onLoadSuccess(String message);

    void onLoadError(String message);

    void onLoadErrorProcedure(String message);
}
