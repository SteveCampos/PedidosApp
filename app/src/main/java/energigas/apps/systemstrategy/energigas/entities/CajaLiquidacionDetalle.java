package energigas.apps.systemstrategy.energigas.entities;

/**
 * Created by kelvi on 10/08/2016.
 */

public class CajaLiquidacionDetalle {
    private long lidId;

    private long liId;

    private int establecimientoId;

    private String fecha;

    private String fechaAccion;

    private int motivoNoAtencionId;

    private int estadoId;

    private int orden;

    private String fechaAtencion;

    private long peId;

    public CajaLiquidacionDetalle() {
    }

    public CajaLiquidacionDetalle(long lidId, long liId, int establecimientoId, String fecha, String fechaAccion, int motivoNoAtencionId, int estadoId, int orden, String fechaAtencion,long peId) {
        this.lidId = lidId;
        this.liId = liId;
        this.establecimientoId = establecimientoId;
        this.fecha = fecha;
        this.fechaAccion = fechaAccion;
        this.motivoNoAtencionId = motivoNoAtencionId;
        this.estadoId = estadoId;
        this.orden = orden;
        this.fechaAtencion = fechaAtencion;
        this.peId = peId;
    }

    public long getLidId() {
        return lidId;
    }

    public void setLidId(long lidId) {
        this.lidId = lidId;
    }

    public long getLiId() {
        return liId;
    }

    public void setLiId(long liId) {
        this.liId = liId;
    }

    public int getEstablecimientoId() {
        return establecimientoId;
    }

    public void setEstablecimientoId(int establecimientoId) {
        this.establecimientoId = establecimientoId;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getFechaAccion() {
        return fechaAccion;
    }

    public void setFechaAccion(String fechaAccion) {
        this.fechaAccion = fechaAccion;
    }

    public int getMotivoNoAtencionId() {
        return motivoNoAtencionId;
    }

    public void setMotivoNoAtencionId(int motivoNoAtencionId) {
        this.motivoNoAtencionId = motivoNoAtencionId;
    }

    public int getEstadoId() {
        return estadoId;
    }

    public void setEstadoId(int estadoId) {
        this.estadoId = estadoId;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public String getFechaAtencion() {
        return fechaAtencion;
    }

    public void setFechaAtencion(String fechaAtencion) {
        this.fechaAtencion = fechaAtencion;
    }

    public long getPeId() {
        return peId;
    }

    public void setPeId(long peId) {
        this.peId = peId;
    }
}
