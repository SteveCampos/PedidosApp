package energigas.apps.systemstrategy.energigas.interfaces;

import android.content.Context;
import android.location.Location;

/**
 * Created by kelvi on 15/08/2016.
 */

public interface OnLocationListener {
    void setLatAndLong(Location latAndLong);
    Context getContextActivity();
}
