package energigas.apps.systemstrategy.energigas.entities;

import com.orm.SugarRecord;

/**
 * Created by kelvi on 09/08/2016.
 */

public class RolUsuario extends SugarRecord{
    private int rolId;

    private int usuarioId;

    public RolUsuario() {
    }

    public RolUsuario(int usuarioId, int rolId) {
        this.usuarioId = usuarioId;
        this.rolId = rolId;
    }

    public int getRolId() {
        return rolId;
    }

    public void setRolId(int rolId) {
        this.rolId = rolId;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }
}
