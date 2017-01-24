package energigas.apps.systemstrategy.energigas.entities;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kelvi on 10/08/2016.
 */

public class ComprobanteVentaDetalle extends SugarRecord {

    @Unique
    private int compdId;

    private long compId;

    private int proId;

    private int unidadId;

    private double cantidad;

    private double precio;

    private double precioUnitario;

    private double costoVenta;

    private double importe;

    private int usuarioActualizacion;

    private String fechaActualizacion;

    private Long despachoId;

    public ComprobanteVentaDetalle() {
    }

    public ComprobanteVentaDetalle(int compdId, long compId, int proId, int unidadId, double cantidad, double precio, double precioUnitario, double costoVenta, double importe, int usuarioActualizacion, String fechaActualizacion, Long despachoId) {
        this.compdId = compdId;
        this.compId = compId;
        this.proId = proId;
        this.unidadId = unidadId;
        this.cantidad = cantidad;
        this.precio = precio;
        this.precioUnitario = precioUnitario;
        this.costoVenta = costoVenta;
        this.importe = importe;
        this.usuarioActualizacion = usuarioActualizacion;
        this.fechaActualizacion = fechaActualizacion;
        this.despachoId = despachoId;
    }

    public Long getDespachoId() {
        return despachoId;
    }

    public void setDespachoId(Long despachoId) {
        this.despachoId = despachoId;
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

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
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

    public static List<ComprobanteVentaDetalle> comprobanteVentaDetalles(String compdId) {

        List<ComprobanteVentaDetalle> ventaDetalles = new ArrayList<>();

        ventaDetalles = ComprobanteVentaDetalle.find(ComprobanteVentaDetalle.class, " comp_Id=? ", new String[]{compdId});



       for (int i = 0; i < ventaDetalles.size(); i++) {
            Despacho despacho = Despacho.getDespachoByDespachoId(ventaDetalles.get(i).getDespachoId() + "");
            if (despacho !=null){
                ventaDetalles.get(i).setDespachoId(despacho.getDespachoId());
            }

        }


        return ventaDetalles;
    }
}
