package energigas.apps.systemstrategy.energigas.entities;

/**
 * Created by kelvi on 10/08/2016.
 */

public class ListaPrecioDetalle {

    private long lpdId;

    private int lpId;

    private int clienteId;

    private int establecimientoId;

    private int unId;

    private int proId;

    private double precio;

    private double precioImp;

    private String fechaActualizacion;

    private int usuarioActualizacion;

    public ListaPrecioDetalle() {
    }

    public ListaPrecioDetalle(long lpdId, int lpId, int clienteId, int establecimientoId, int unId, int proId, double precio, double precioImp, String fechaActualizacion, int usuarioActualizacion) {
        this.lpdId = lpdId;
        this.lpId = lpId;
        this.clienteId = clienteId;
        this.establecimientoId = establecimientoId;
        this.unId = unId;
        this.proId = proId;
        this.precio = precio;
        this.precioImp = precioImp;
        this.fechaActualizacion = fechaActualizacion;
        this.usuarioActualizacion = usuarioActualizacion;
    }

    public long getLpdId() {
        return lpdId;
    }

    public void setLpdId(long lpdId) {
        this.lpdId = lpdId;
    }

    public int getLpId() {
        return lpId;
    }

    public void setLpId(int lpId) {
        this.lpId = lpId;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public int getEstablecimientoId() {
        return establecimientoId;
    }

    public void setEstablecimientoId(int establecimientoId) {
        this.establecimientoId = establecimientoId;
    }

    public int getUnId() {
        return unId;
    }

    public void setUnId(int unId) {
        this.unId = unId;
    }

    public int getProId() {
        return proId;
    }

    public void setProId(int proId) {
        this.proId = proId;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getPrecioImp() {
        return precioImp;
    }

    public void setPrecioImp(double precioImp) {
        this.precioImp = precioImp;
    }

    public String getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(String fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public int getUsuarioActualizacion() {
        return usuarioActualizacion;
    }

    public void setUsuarioActualizacion(int usuarioActualizacion) {
        this.usuarioActualizacion = usuarioActualizacion;
    }
}
