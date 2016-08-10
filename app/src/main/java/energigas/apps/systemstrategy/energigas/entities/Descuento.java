package energigas.apps.systemstrategy.energigas.entities;

/**
 * Created by kelvi on 10/08/2016.
 */

public class Descuento {

    private int descuentoId;

    private String descripcion;

    private double descuento;

    private String fechaCreacion;

    private String fechaActualizacion;

    private String fechaPublicacion;

    private int usuarioCreacion;

    private int usuarioAccion;

    private int modalidadDescuentoId;

    public Descuento() {
    }

    public Descuento(int descuentoId, String descripcion, double descuento, String fechaCreacion, String fechaActualizacion, String fechaPublicacion, int usuarioCreacion, int usuarioAccion, int modalidadDescuentoId) {
        this.descuentoId = descuentoId;
        this.descripcion = descripcion;
        this.descuento = descuento;
        this.fechaCreacion = fechaCreacion;
        this.fechaActualizacion = fechaActualizacion;
        this.fechaPublicacion = fechaPublicacion;
        this.usuarioCreacion = usuarioCreacion;
        this.usuarioAccion = usuarioAccion;
        this.modalidadDescuentoId = modalidadDescuentoId;
    }

    public int getDescuentoId() {
        return descuentoId;
    }

    public void setDescuentoId(int descuentoId) {
        this.descuentoId = descuentoId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
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

    public String getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(String fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public int getUsuarioCreacion() {
        return usuarioCreacion;
    }

    public void setUsuarioCreacion(int usuarioCreacion) {
        this.usuarioCreacion = usuarioCreacion;
    }

    public int getUsuarioAccion() {
        return usuarioAccion;
    }

    public void setUsuarioAccion(int usuarioAccion) {
        this.usuarioAccion = usuarioAccion;
    }

    public int getModalidadDescuentoId() {
        return modalidadDescuentoId;
    }

    public void setModalidadDescuentoId(int modalidadDescuentoId) {
        this.modalidadDescuentoId = modalidadDescuentoId;
    }
}
