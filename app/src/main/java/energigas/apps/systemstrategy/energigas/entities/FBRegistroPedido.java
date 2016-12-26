package energigas.apps.systemstrategy.energigas.entities;

/**
 * Created by kelvi on 30/11/2016.
 */

public class FBRegistroPedido {

    private Long liquidacionId;
    private Long liquidaciondetalleId;
    private Long pedidoId;
    private Long estabecimientoId;
    private Long estadoId;
    private Long agenteId;


    public FBRegistroPedido() {
    }

    public FBRegistroPedido(Long liquidacionId, Long liquidaciondetalleId, Long pedidoId, Long estabecimientoId, Long estadoId, Long agenteId) {
        this.liquidacionId = liquidacionId;
        this.liquidaciondetalleId = liquidaciondetalleId;
        this.pedidoId = pedidoId;
        this.estabecimientoId = estabecimientoId;
        this.estadoId = estadoId;
        this.agenteId = agenteId;
    }

    public Long getLiquidacionId() {
        return liquidacionId;
    }

    public void setLiquidacionId(Long liquidacionId) {
        this.liquidacionId = liquidacionId;
    }

    public Long getLiquidaciondetalleId() {
        return liquidaciondetalleId;
    }

    public void setLiquidaciondetalleId(Long liquidaciondetalleId) {
        this.liquidaciondetalleId = liquidaciondetalleId;
    }

    public Long getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(Long pedidoId) {
        this.pedidoId = pedidoId;
    }

    public Long getEstabecimientoId() {
        return estabecimientoId;
    }

    public void setEstabecimientoId(Long estabecimientoId) {
        this.estabecimientoId = estabecimientoId;
    }

    public Long getEstadoId() {
        return estadoId;
    }

    public void setEstadoId(Long estadoId) {
        this.estadoId = estadoId;
    }

    public Long getAgenteId() {
        return agenteId;
    }

    public void setAgenteId(Long agenteId) {
        this.agenteId = agenteId;
    }
}
