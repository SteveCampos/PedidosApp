package energigas.apps.systemstrategy.energigas.entities;

/**
 * Created by kelvi on 10/08/2016.
 */

public class VehiculoUsuario {

    private int veId;

    private int usuarioId;

    private boolean responsable;

    public VehiculoUsuario() {
    }

    public VehiculoUsuario(int veId, int usuarioId, boolean responsable) {
        this.veId = veId;
        this.usuarioId = usuarioId;
        this.responsable = responsable;
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
}
