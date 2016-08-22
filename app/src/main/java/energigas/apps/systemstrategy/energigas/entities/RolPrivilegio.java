package energigas.apps.systemstrategy.energigas.entities;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

/**
 * Created by kelvi on 09/08/2016.
 */

public class RolPrivilegio extends SugarRecord{
    @Unique
    private int rolId;
    @Unique
    private int privilegioId;

    public RolPrivilegio() {
    }

    public RolPrivilegio(int rolId, int privilegioId) {
        this.rolId = rolId;
        this.privilegioId = privilegioId;
    }

    public int getRolId() {
        return rolId;
    }

    public void setRolId(int rolId) {
        this.rolId = rolId;
    }

    public int getPrivilegioId() {
        return privilegioId;
    }

    public void setPrivilegioId(int privilegioId) {
        this.privilegioId = privilegioId;
    }
}
