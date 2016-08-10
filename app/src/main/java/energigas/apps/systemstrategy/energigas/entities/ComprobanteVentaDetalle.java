package energigas.apps.systemstrategy.energigas.entities;

/**
 * Created by kelvi on 10/08/2016.
 */

public class ComprobanteVentaDetalle {
    private long compId;

    private int compdId;

    private int proId;

    private int unidadId;

    private int cantidad;

    private double precio;

    private double precioUnitario;

    private double costoVenta;

    private double importe;

    private int usuarioActualizacion;

    private String fechaActualizacion;

    public ComprobanteVentaDetalle() {
    }

    public ComprobanteVentaDetalle(long compId, int compdId, int proId, int unidadId, int cantidad, double precio, double precioUnitario, double costoVenta, double importe, int usuarioActualizacion, String fechaActualizacion) {
        this.compId = compId;
        this.compdId = compdId;
        this.proId = proId;
        this.unidadId = unidadId;
        this.cantidad = cantidad;
        this.precio = precio;
        this.precioUnitario = precioUnitario;
        this.costoVenta = costoVenta;
        this.importe = importe;
        this.usuarioActualizacion = usuarioActualizacion;
        this.fechaActualizacion = fechaActualizacion;
    }

    public long getCompId() {
        return compId;
    }

    public void setCompId(long compId) {
        this.compId = compId;
    }

    public int getCompdId() {
        return compdId;
    }

    public void setCompdId(int compdId) {
        this.compdId = compdId;
    }

    public int getProId() {
        return proId;
    }

    public void setProId(int proId) {
        this.proId = proId;
    }

    public int getUnidadId() {
        return unidadId;
    }

    public void setUnidadId(int unidadId) {
        this.unidadId = unidadId;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public double getCostoVenta() {
        return costoVenta;
    }

    public void setCostoVenta(double costoVenta) {
        this.costoVenta = costoVenta;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public int getUsuarioActualizacion() {
        return usuarioActualizacion;
    }

    public void setUsuarioActualizacion(int usuarioActualizacion) {
        this.usuarioActualizacion = usuarioActualizacion;
    }

    public String getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(String fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }
}
