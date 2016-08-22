package energigas.apps.systemstrategy.energigas.entities;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

/**
 * Created by kelvi on 09/08/2016.
 */

public class RolUsuario extends SugarRecord{

    @Unique
    private int rolId;
    @Unique
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
