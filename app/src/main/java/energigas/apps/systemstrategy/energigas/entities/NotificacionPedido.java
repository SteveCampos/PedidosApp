package energigas.apps.systemstrategy.energigas.entities;

/**
 * Created by kelvi on 26/01/2017.
 */

public class NotificacionPedido {
    private Long cajaLiquidacion;
    private int cantidad;
    private int establecimientoId;

    public NotificacionPedido() {
    }

    public NotificacionPedido(Long cajaLiquidacion, int cantidad, int establecimientoId) {
        this.cajaLiquidacion = cajaLiquidacion;
        this.cantidad = cantidad;
        this.establecimientoId = establecimientoId;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getEstablecimientoId() {
        return establecimientoId;
    }

    public void setEstablecimientoId(int establecimientoId) {
        this.establecimientoId = establecimientoId;
    }

    public Long getCajaLiquidacion() {
        return cajaLiquidacion;
    }

    public void setCajaLiquidacion(Long cajaLiquidacion) {
        this.cajaLiquidacion = cajaLiquidacion;
    }

    public int getEstado() {
        return cantidad;
    }

    public void setEstado(int cantidad) {
        this.cantidad = cantidad;
    }
}
