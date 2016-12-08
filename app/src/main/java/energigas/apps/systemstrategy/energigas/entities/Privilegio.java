package energigas.apps.systemstrategy.energigas.entities;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kelvi on 09/08/2016.
 */

public class Privilegio extends SugarRecord {
    @Unique
    private int idPrivilegio;

    private int accesoId;

    private String nombre;

    private String descripcion;

    private boolean estado;

    private String fechaCreacion;

    private String fechaActualizacion;

    private int usuarioCreacion;

    private int usuarioActualizacion;

    private String viewAndroidId;

    public Privilegio() {
    }

    public Privilegio(int idPrivilegio, int accesoId, String nombre, String descripcion, boolean estado, String fechaCreacion, String fechaActualizacion, int usuarioCreacion, int usuarioActualizacion, String viewAndroidId) {
        this.idPrivilegio = idPrivilegio;
        this.accesoId = accesoId;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
        this.fechaCreacion = fechaCreacion;
        this.fechaActualizacion = fechaActualizacion;
        this.usuarioCreacion = usuarioCreacion;
        this.usuarioActualizacion = usuarioActualizacion;
        this.viewAndroidId = viewAndroidId;
    }

    public String getViewAndroidId() {
        return viewAndroidId;
    }

    public void setViewAndroidId(String viewAndroidId) {
        this.viewAndroidId = viewAndroidId;
    }

    public int getIdPrivilegio() {
        return idPrivilegio;
    }

    public void setIdPrivilegio(int idPrivilegio) {
        this.idPrivilegio = idPrivilegio;
    }

    public int getAccesoId() {
        return accesoId;
    }

    public void setAccesoId(int accesoId) {
        this.accesoId = accesoId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
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

    public static Privilegio getPrivilegiosId(String privilegioId) {



        List<Privilegio> privilegios = Privilegio.find(Privilegio.class, "id_Privilegio=? ", new String[]{privilegioId});
        if (privilegios.size()>0) {
            return privilegios.get(0);
        }
        return null;
    }

    public static List<Privilegio> getPrivilegios(String accesoId) {

        List<Privilegio> privilegios = Privilegio.find(Privilegio.class, "acceso_Id=?", new String[]{accesoId});
        if (privilegios != null) {
            return privilegios;
        }
        return null;
    }


    public static List<Privilegio> getPrivilegiosByRol(String rolId) {

        List<RolPrivilegio> rolPrivilegioList = RolPrivilegio.getRolPrivilegioRol(rolId);
        List<Privilegio> privilegioList = new ArrayList<>();
        for (RolPrivilegio privilegio : rolPrivilegioList){
            privilegioList.add(Privilegio.getPrivilegiosId(privilegio.getPrivilegioId()+""));
        }

        return privilegioList;
    }



    public static List<Privilegio> getPrivilegiosByNameActivity
            (String accesoId, String nombreActivity) {
        List<Privilegio> privilegios = Privilegio.find(Privilegio.class, "acceso_Id=? and nombre=? ", new String[]{accesoId, nombreActivity});
        if (privilegios != null) {
            return privilegios;
        }
        return null;
    }
}
