package energigas.apps.systemstrategy.energigas.entities;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

import java.util.List;

/**
 * Created by kelvi on 10/08/2016.
 */

public class VehiculoUsuario extends SugarRecord {

    @Unique
    private String vehiculoUsuarioId;

    private int veId;

    private int usuarioId;

    private boolean responsable;

    public VehiculoUsuario() {
    }

    public VehiculoUsuario(String vehiculoUsuarioId, int veId, int usuarioId, boolean responsable) {
        this.vehiculoUsuarioId = vehiculoUsuarioId;
        this.veId = veId;
        this.usuarioId = usuarioId;
        this.responsable = responsable;
    }

    public String getVehiculoUsuarioId() {
        return vehiculoUsuarioId;
    }

    public void setVehiculoUsuarioId(String vehiculoUsuarioId) {
        this.vehiculoUsuarioId = vehiculoUsuarioId;
    }

    public int getVeId() {
        return veId;
    }

    public void setVeId(int veId) {
        this.veId = veId;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public boolean isResponsable() {
        return responsable;
    }

    public void setResponsable(boolean responsable) {
        this.responsable = responsable;
    }

    public static VehiculoUsuario getVehiculoUsuario(String usuarioId) {
        List<VehiculoUsuario> vehiculoUsuarioList = VehiculoUsuario.find(VehiculoUsuario.class, "usuario_Id=?", new String[]{usuarioId});
        return vehiculoUsuarioList.get(0);
    }
}
