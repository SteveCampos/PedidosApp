package energigas.apps.systemstrategy.energigas.entities;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kelvi on 10/08/2016.
 */

public class PedidoDetalle extends SugarRecord {


    @Unique
    private String idDetalle;

    private int pdId;

    private long peId;

    private int productoId;//PRODUCTO NOMBRE, UNIDAD DE MEDIDA

    private double cantidad;//

    private double cantidadAtendida;

    private double importe;

    private int usuarioId;

    private String fechaAccion;

    private double precioUnitario;//

    private double costoVenta;//

    private int unidadId;

    private int estadoAtencionId;

    private double precio;

    private String golpe;

    private int almId;

    public PedidoDetalle() {
    }


    public PedidoDetalle(String idDetalle, int pdId, long peId, int productoId, double cantidad, double cantidadAtendida, double importe, int usuarioId, String fechaAccion, double precioUnitario, double costoVenta, int unidadId, int estadoAtencionId, double precio, String golpe, int almId) {
        this.idDetalle = idDetalle;
        this.pdId = pdId;
        this.peId = peId;
        this.productoId = productoId;
        this.cantidad = cantidad;
        this.cantidadAtendida = cantidadAtendida;
        this.importe = importe;
        this.usuarioId = usuarioId;
        this.fechaAccion = fechaAccion;
        this.precioUnitario = precioUnitario;
        this.costoVenta = costoVenta;
        this.unidadId = unidadId;
        this.estadoAtencionId = estadoAtencionId;
        this.precio = precio;
        this.golpe = golpe;
        this.almId = almId;
    }



    public int getAlmId() {
        return almId;
    }

    public void setAlmId(int almId) {
        this.almId = almId;
    }

    public String getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(String idDetalle) {
        this.idDetalle = idDetalle;
    }

    public long getPeId() {
        return peId;
    }

    public void setPeId(long peId) {
        this.peId = peId;
    }

    public int getPdId() {
        return pdId;
    }

    public void setPdId(int pdId) {
        this.pdId = pdId;
    }

    public int getProductoId() {
        return productoId;
    }

    public void setProductoId(int productoId) {
        this.productoId = productoId;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public double getCantidadAtendida() {
        return cantidadAtendida;
    }

    public void setCantidadAtendida(double cantidadAtendida) {
        this.cantidadAtendida = cantidadAtendida;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getFechaAccion() {
        return fechaAccion;
    }

    public void setFechaAccion(String fechaAccion) {
        this.fechaAccion = fechaAccion;
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

    public int getUnidadId() {
        return unidadId;
    }

    public void setUnidadId(int unidadId) {
        this.unidadId = unidadId;
    }

    public int getEstadoAtencionId() {
        return estadoAtencionId;
    }

    public void setEstadoAtencionId(int estadoAtencionId) {
        this.estadoAtencionId = estadoAtencionId;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getGolpe() {
        return golpe;
    }

    public void setGolpe(String golpe) {
        this.golpe = golpe;
    }

    public static List<PedidoDetalle> getList() {
        List<PedidoDetalle> list = new ArrayList<>();
        list.add(new PedidoDetalle());
        return list;
    }


    public static List<PedidoDetalle> getPedidoDetalleByPedido(String pedidoId){
        List<PedidoDetalle>  pedidoDetalles = PedidoDetalle.find(PedidoDetalle.class,"pe_Id=?",new String[]{pedidoId});
        if (pedidoDetalles.size()>0){
            return  pedidoDetalles;
        }
        return null;
    }
}
