package energigas.apps.systemstrategy.energigas.entities;

/**
 * Created by kelvi on 09/08/2016.
 */

public class Usuario {

    private int usuIUsuarioId;

    private String usuVUsuario;

    private String usuVPassword;

    private boolean usuBEstado;

    private int usuIPersonaId;

    private int usuIUsuarioActualizacion;

    private String usuDTFechaActualizacion;

    private int usuIUsuarioCreacion;

    private String usuDTFechaCreacion;

    public Usuario() {
    }

    public Usuario(int usuIUsuarioId, String usuVUsuario, String usuVPassword, boolean usuBEstado, int usuIPersonaId, int usuIUsuarioActualizacion, String usuDTFechaActualizacion, int usuIUsuarioCreacion, String usuDTFechaCreacion) {
        this.usuIUsuarioId = usuIUsuarioId;
        this.usuVUsuario = usuVUsuario;
        this.usuVPassword = usuVPassword;
        this.usuBEstado = usuBEstado;
        this.usuIPersonaId = usuIPersonaId;
        this.usuIUsuarioActualizacion = usuIUsuarioActualizacion;
        this.usuDTFechaActualizacion = usuDTFechaActualizacion;
        this.usuIUsuarioCreacion = usuIUsuarioCreacion;
        this.usuDTFechaCreacion = usuDTFechaCreacion;
    }

    public int getUsuIUsuarioId() {
        return usuIUsuarioId;
    }

    public void setUsuIUsuarioId(int usuIUsuarioId) {
        this.usuIUsuarioId = usuIUsuarioId;
    }

    public String getUsuVUsuario() {
        return usuVUsuario;
    }

    public void setUsuVUsuario(String usuVUsuario) {
        this.usuVUsuario = usuVUsuario;
    }

    public String getUsuVPassword() {
        return usuVPassword;
    }

    public void setUsuVPassword(String usuVPassword) {
        this.usuVPassword = usuVPassword;
    }

    public boolean isUsuBEstado() {
        return usuBEstado;
    }

    public void setUsuBEstado(boolean usuBEstado) {
        this.usuBEstado = usuBEstado;
    }

    public int getUsuIPersonaId() {
        return usuIPersonaId;
    }

    public void setUsuIPersonaId(int usuIPersonaId) {
        this.usuIPersonaId = usuIPersonaId;
    }

    public int getUsuIUsuarioActualizacion() {
        return usuIUsuarioActualizacion;
    }

    public void setUsuIUsuarioActualizacion(int usuIUsuarioActualizacion) {
        this.usuIUsuarioActualizacion = usuIUsuarioActualizacion;
    }

    public String getUsuDTFechaActualizacion() {
        return usuDTFechaActualizacion;
    }

    public void setUsuDTFechaActualizacion(String usuDTFechaActualizacion) {
        this.usuDTFechaActualizacion = usuDTFechaActualizacion;
    }

    public int getUsuIUsuarioCreacion() {
        return usuIUsuarioCreacion;
    }

    public void setUsuIUsuarioCreacion(int usuIUsuarioCreacion) {
        this.usuIUsuarioCreacion = usuIUsuarioCreacion;
    }

    public String getUsuDTFechaCreacion() {
        return usuDTFechaCreacion;
    }

    public void setUsuDTFechaCreacion(String usuDTFechaCreacion) {
        this.usuDTFechaCreacion = usuDTFechaCreacion;
    }
}
