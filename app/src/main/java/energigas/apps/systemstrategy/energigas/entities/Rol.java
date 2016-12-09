package energigas.apps.systemstrategy.energigas.entities;

import com.orm.SugarRecord;
import com.orm.dsl.Ignore;
import com.orm.dsl.Unique;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kelvi on 09/08/2016.
 */

public class Rol extends SugarRecord {
    @Unique
    private int idRol;

    private String nombre;

    private boolean estado;

    private int parentId;

    private String fechaCreacion;

    private String fechaActualizacion;

    private int usuarioCreacion;

    private int usuarioActualizacion;

    @Ignore
    private List<Acceso> itemsAccesos;

    public Rol() {
    }

    public Rol(int idRol, String nombre, boolean estado, int parentId, String fechaCreacion, String fechaActualizacion, int usuarioCreacion, int usuarioActualizacion, List<Acceso> itemsAccesos) {
        this.idRol = idRol;
        this.nombre = nombre;
        this.estado = estado;
        this.parentId = parentId;
        this.fechaCreacion = fechaCreacion;
        this.fechaActualizacion = fechaActualizacion;
        this.usuarioCreacion = usuarioCreacion;
        this.usuarioActualizacion = usuarioActualizacion;
        this.itemsAccesos = itemsAccesos;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(String fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public int getUsuarioCreacion() {
        return usuarioCreacion;
    }

    public void setUsuarioCreacion(int usuarioCreacion) {
        this.usuarioCreacion = usuarioCreacion;
    }

    public int getUsuarioActualizacion() {
        return usuarioActualizacion;
    }

    public void setUsuarioActualizacion(int usuarioActualizacion) {
        this.usuarioActualizacion = usuarioActualizacion;
    }

    public List<Acceso> getItemsAccesos() {
        return itemsAccesos;
    }

    public void setItemsAccesos(List<Acceso> itemsAccesos) {
        this.itemsAccesos = itemsAccesos;
    }


    public static List<Rol> getRol(String usuarioId) {

        List<RolUsuario> rolUsuarioList = RolUsuario.getRolUsuario(usuarioId + "");

        List<Rol> rolList = new ArrayList<>();

        for (RolUsuario rolUsuario : rolUsuarioList) {

            rolList.add(Rol.getRolById(rolUsuario.getRolId()+""));

        }

        return rolList;

    }

    public static Rol getRolById(String rolId) {
        List<Rol> rolList = Rol.find(Rol.class, "id_Rol=?", new String[]{rolId});
        if (rolList.size() > 0) {
            return rolList.get(0);
        }
        return null;
    }
}
