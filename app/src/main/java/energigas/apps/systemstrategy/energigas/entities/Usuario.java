package energigas.apps.systemstrategy.energigas.entities;

import android.util.Log;

import com.orm.SugarRecord;
import com.orm.dsl.Ignore;
import com.orm.dsl.Unique;

import java.util.List;

import energigas.apps.systemstrategy.energigas.utils.Session;

/**
 * Created by kelvi on 09/08/2016.
 */

public class Usuario extends SugarRecord {

    @Unique
    private int usuIUsuarioId;

    private String usuVUsuario;

    private String usuVPassword;

    private boolean usuBEstado;

    private int usuIPersonaId;

    private int usuIUsuarioActualizacion;

    private String usuDTFechaActualizacion;

    private int usuIUsuarioCreacion;

    private String usuDTFechaCreacion;

    @Ignore
    private List<Rol> itemsRoles;
    @Ignore
    private Persona persona;

    public Usuario() {
    }

    public Usuario(int usuIUsuarioId, String usuVUsuario, String usuVPassword, boolean usuBEstado, int usuIPersonaId, int usuIUsuarioActualizacion, String usuDTFechaActualizacion, int usuIUsuarioCreacion, String usuDTFechaCreacion, List<Rol> itemsRoles, Persona persona) {
        this.usuIUsuarioId = usuIUsuarioId;
        this.usuVUsuario = usuVUsuario;
        this.usuVPassword = usuVPassword;
        this.usuBEstado = usuBEstado;
        this.usuIPersonaId = usuIPersonaId;
        this.usuIUsuarioActualizacion = usuIUsuarioActualizacion;
        this.usuDTFechaActualizacion = usuDTFechaActualizacion;
        this.usuIUsuarioCreacion = usuIUsuarioCreacion;
        this.usuDTFechaCreacion = usuDTFechaCreacion;
        this.itemsRoles = itemsRoles;
        this.persona = persona;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public List<Rol> getItemsRoles() {
        return itemsRoles;
    }

    public void setItemsRoles(List<Rol> itemsRoles) {
        this.itemsRoles = itemsRoles;
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

    public static Usuario getUsuario(String usuario) {
        Log.d("MainActivity", "USUARIO SIZE: " + Usuario.listAll(Usuario.class).size());
        for (Usuario usuario1 : Usuario.listAll(Usuario.class)) {
            Log.d("MainActivity", "USUARIO: " + usuario1.getUsuIUsuarioId());
        }

        Usuario usuario1 = Usuario.find(Usuario.class, " usu_I_Usuario_Id = ? ", new String[]{usuario}).get(0);
        usuario1.setPersona(Persona.getPersona(usuario1.getUsuIPersonaId() + ""));
        return usuario1;
    }
}
