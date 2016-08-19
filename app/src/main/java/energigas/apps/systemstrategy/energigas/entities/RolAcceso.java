package energigas.apps.systemstrategy.energigas.entities;

import com.orm.SugarRecord;

/**
 * Created by kelvi on 09/08/2016.
 */

public class RolAcceso  extends SugarRecord{
    private int rolId;

    private int accesoId;

    private boolean accesoDefault;

    public RolAcceso() {
    }

    public RolAcceso(int rolId, int accesoId, boolean accesoDefault) {
        this.rolId = rolId;
        this.accesoId = accesoId;
        this.accesoDefault = accesoDefault;
    }

    public int getRolId() {
        return rolId;
    }

    public void setRolId(int rolId) {
        this.rolId = rolId;
    }

    public int getAccesoId() {
        return accesoId;
    }

    public void setAccesoId(int accesoId) {
        this.accesoId = accesoId;
    }

    public boolean isAccesoDefault() {
        return accesoDefault;
    }

    public void setAccesoDefault(boolean accesoDefault) {
        this.accesoDefault = accesoDefault;
    }
}
