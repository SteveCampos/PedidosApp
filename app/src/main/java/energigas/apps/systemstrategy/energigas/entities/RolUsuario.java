package energigas.apps.systemstrategy.energigas.entities;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

import java.util.List;

/**
 * Created by kelvi on 09/08/2016.
 */

public class RolUsuario extends SugarRecord {
    @Unique
    private String idRolUsuario;

    private int rolId;
    private int usuarioId;

    public RolUsuario() {
    }

    public RolUsuario(String idRolUsuario, int rolId, int usuarioId) {
        this.idRolUsuario = idRolUsuario;
        this.rolId = rolId;
        this.usuarioId = usuarioId;
    }

    public String getIdRolUsuario() {
        return idRolUsuario;
    }

    public void setIdRolUsuario(String idRolUsuario) {
        this.idRolUsuario = idRolUsuario;
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

    public static List<RolUsuario>getRolUsuario(String usuarioId) {

        List<RolUsuario> rolUsuarios = RolUsuario.find(RolUsuario.class, "usuario_Id=?", new String[]{usuarioId});

        if (rolUsuarios !=null) {

            return rolUsuarios;
        }

        return null;
    }
}
