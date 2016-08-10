package energigas.apps.systemstrategy.energigas.entities;

/**
 * Created by kelvi on 09/08/2016.
 */

public class Privilegio {

    private int id;

    private int accesoId;

    private String nombre;

    private String descripcion;

    private boolean estado;

    private String fechaCreacion;

    private String fechaActualizacion;

    private int usuarioCreacion;

    private int usuarioActualizacion;

    public Privilegio() {
    }

    public Privilegio(int id, int accesoId, String nombre, String descripcion, boolean estado, String fechaCreacion, String fechaActualizacion, int usuarioCreacion, int usuarioActualizacion) {
        this.id = id;
        this.accesoId = accesoId;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
        this.fechaCreacion = fechaCreacion;
        this.fechaActualizacion = fechaActualizacion;
        this.usuarioCreacion = usuarioCreacion;
        this.usuarioActualizacion = usuarioActualizacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
