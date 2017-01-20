package energigas.apps.systemstrategy.energigas.entities;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

/**
 * Created by kelvi on 13/12/2016.
 */

public class BeDocElectronicoDetalle extends SugarRecord {

    private int docElectronicoId;
    @Unique
    private int docdetalleId;
    private Double cantidad;
    private int unidadMedidaId;
    private Double suma;
    private Double totalVenta;
    private Double precioUnitario;
    private int tipoPrecioId;
    private int tipoImpuestoId;
    private Double impuesto;
    private Double impuestoSelectivo;
    private Double otroImpuesto;
    private int productoId;
    private String descripcion;
    private String codigoItem;
    private String precioReferencial;
    private String descuento;
    private String totalVentaBruta;

    public BeDocElectronicoDetalle() {
    }

    public BeDocElectronicoDetalle(int docElectronicoId, int docdetalleId, Double cantidad, int unidadMedidaId, Double suma, Double totalVenta, Double precioUnitario, int tipoPrecioId, int tipoImpuestoId, Double impuesto, Double impuestoSelectivo, Double otroImpuesto, int productoId, String descripcion, String codigoItem, String precioReferencial, String descuento, String totalVentaBruta) {
        this.docElectronicoId = docElectronicoId;
        this.docdetalleId = docdetalleId;
        this.cantidad = cantidad;
        this.unidadMedidaId = unidadMedidaId;
        this.suma = suma;
        this.totalVenta = totalVenta;
        this.precioUnitario = precioUnitario;
        this.tipoPrecioId = tipoPrecioId;
        this.tipoImpuestoId = tipoImpuestoId;
        this.impuesto = impuesto;
        this.impuestoSelectivo = impuestoSelectivo;
        this.otroImpuesto = otroImpuesto;
        this.productoId = productoId;
        this.descripcion = descripcion;
        this.codigoItem = codigoItem;
        this.precioReferencial = precioReferencial;
        this.descuento = descuento;
        this.totalVentaBruta = totalVentaBruta;
    }

    public String getTotalVentaBruta() {
        return totalVentaBruta;
    }

    public void setTotalVentaBruta(String totalVentaBruta) {
        this.totalVentaBruta = totalVentaBruta;
    }

    public int getDocElectronicoId() {
        return docElectronicoId;
    }

    public void setDocElectronicoId(int docElectronicoId) {
        this.docElectronicoId = docElectronicoId;
    }

    public int getDocdetalleId() {
        return docdetalleId;
    }

    public void setDocdetalleId(int docdetalleId) {
        this.docdetalleId = docdetalleId;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public int getUnidadMedidaId() {
        return unidadMedidaId;
    }

    public void setUnidadMedidaId(int unidadMedidaId) {
        this.unidadMedidaId = unidadMedidaId;
    }

    public Double getSuma() {
        return suma;
    }

    public void setSuma(Double suma) {
        this.suma = suma;
    }

    public Double getTotalVenta() {
        return totalVenta;
    }

    public void setTotalVenta(Double totalVenta) {
        this.totalVenta = totalVenta;
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public int getTipoPrecioId() {
        return tipoPrecioId;
    }

    public void setTipoPrecioId(int tipoPrecioId) {
        this.tipoPrecioId = tipoPrecioId;
    }

    public int getTipoImpuestoId() {
        return tipoImpuestoId;
    }

    public void setTipoImpuestoId(int tipoImpuestoId) {
        this.tipoImpuestoId = tipoImpuestoId;
    }

    public Double getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(Double impuesto) {
        this.impuesto = impuesto;
    }

    public Double getImpuestoSelectivo() {
        return impuestoSelectivo;
    }

    public void setImpuestoSelectivo(Double impuestoSelectivo) {
        this.impuestoSelectivo = impuestoSelectivo;
    }

    public Double getOtroImpuesto() {
        return otroImpuesto;
    }

    public void setOtroImpuesto(Double otroImpuesto) {
        this.otroImpuesto = otroImpuesto;
    }

    public int getProductoId() {
        return productoId;
    }

    public void setProductoId(int productoId) {
        this.productoId = productoId;
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

    public String getPrecioReferencial() {
        return precioReferencial;
    }

    public void setPrecioReferencial(String precioReferencial) {
        this.precioReferencial = precioReferencial;
    }

    public String getDescuento() {
        return descuento;
    }

    public void setDescuento(String descuento) {
        this.descuento = descuento;
    }
}
