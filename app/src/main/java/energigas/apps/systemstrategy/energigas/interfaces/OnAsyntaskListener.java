package energigas.apps.systemstrategy.energigas.interfaces;

import energigas.apps.systemstrategy.energigas.entities.CajaLiquidacion;

/**
 * Created by kelvi on 8/08/2016.
 */

public interface OnAsyntaskListener {

    void onLoadSuccess(String message, CajaLiquidacion cajaLiquidacion);

    void onLoadError(String message);

    void onLoadErrorProcedure(String message);
}
