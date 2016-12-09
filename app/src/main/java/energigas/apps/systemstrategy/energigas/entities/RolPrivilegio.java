package energigas.apps.systemstrategy.energigas.entities;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

import java.util.List;

/**
 * Created by kelvi on 09/08/2016.
 */

public class RolPrivilegio extends SugarRecord {
    @Unique
    private String idRolPrivilegio;

    private int rolId;

    private int privilegioId;

    public RolPrivilegio() {
    }

    public RolPrivilegio(String idRolPrivilegio, int rolId, int privilegioId) {
        this.idRolPrivilegio = idRolPrivilegio;
        this.rolId = rolId;
        this.privilegioId = privilegioId;
    }

    public String getIdRolPrivilegio() {
        return idRolPrivilegio;
    }

    public void setIdRolPrivilegio(String idRolPrivilegio) {
        this.idRolPrivilegio = idRolPrivilegio;
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

    public static List<RolPrivilegio> getRolPrivilegioRol(String rolId) {
        List<RolPrivilegio> rolPrivilegioList = RolPrivilegio.find(RolPrivilegio.class, "rol_Id=?", new String[]{rolId});
        if (rolPrivilegioList != null) {
            return rolPrivilegioList;
        }
        return null;
    }
}
