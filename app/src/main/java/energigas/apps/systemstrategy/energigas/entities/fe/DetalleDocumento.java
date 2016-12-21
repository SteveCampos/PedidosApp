package energigas.apps.systemstrategy.energigas.entities.fe;



import java.math.BigDecimal;

/**
 * Created by Steve on 22/11/2016.
 */


public class DetalleDocumento {

    public int id;
    public BigDecimal cantidad;
    public String unidadMedida;
    public BigDecimal suma;
    public BigDecimal totalVenta;
    public BigDecimal precioUnitario;
    public String tipoPrecio;
    public BigDecimal impuesto;
    public String tipoImpuesto;
    public BigDecimal impuestoSelectivo;
    public BigDecimal otroImpuesto;
    public String descripcion;
    public String codigoItem;
    public BigDecimal precioReferencial;
    public BigDecimal descuento;

    public DetalleDocumento()
    {
        id = 1;
        unidadMedida = "NIU";
        tipoPrecio = "01";
        tipoImpuesto = "10";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public BigDecimal getSuma() {
        return suma;
    }

    public void setSuma(BigDecimal suma) {
        this.suma = suma;
    }

    public BigDecimal getTotalVenta() {
        return totalVenta;
    }

    public void setTotalVenta(BigDecimal totalVenta) {
        this.totalVenta = totalVenta;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public String getTipoPrecio() {
        return tipoPrecio;
    }

    public void setTipoPrecio(String tipoPrecio) {
        this.tipoPrecio = tipoPrecio;
    }

    public BigDecimal getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(BigDecimal impuesto) {
        this.impuesto = impuesto;
    }

    public String getTipoImpuesto() {
        return tipoImpuesto;
    }

    public void setTipoImpuesto(String tipoImpuesto) {
        this.tipoImpuesto = tipoImpuesto;
    }

    public BigDecimal getImpuestoSelectivo() {
        return impuestoSelectivo;
    }

    public void setImpuestoSelectivo(BigDecimal impuestoSelectivo) {
        this.impuestoSelectivo = impuestoSelectivo;
    }

    public BigDecimal getOtroImpuesto() {
        return otroImpuesto;
    }

    public void setOtroImpuesto(BigDecimal otroImpuesto) {
        this.otroImpuesto = otroImpuesto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCodigoItem() {
        return codigoItem;
    }

    public void setCodigoItem(String codigoItem) {
        this.codigoItem = codigoItem;
    }

    public BigDecimal getPrecioReferencial() {
        return precioReferencial;
    }

    public void setPrecioReferencial(BigDecimal precioReferencial) {
        this.precioReferencial = precioReferencial;
    }

    public BigDecimal getDescuento() {
        return descuento;
    }

    public void setDescuento(BigDecimal descuento) {
        this.descuento = descuento;
    }
}
