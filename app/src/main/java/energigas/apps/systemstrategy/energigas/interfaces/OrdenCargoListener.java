package energigas.apps.systemstrategy.energigas.interfaces;

import android.support.v7.app.AlertDialog;
import android.view.View;

import energigas.apps.systemstrategy.energigas.entities.OrdenCargo;

/**
 * Created by Steve on 28/12/2016.
 */

public interface OrdenCargoListener {
    void onOrdenCargoClickListener(OrdenCargo ordenCargo);
    void onOrdenCargoLongClickListener(int position, OrdenCargo ordenCargo, View view, AlertDialog alertDialog);
}
