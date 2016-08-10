package energigas.apps.systemstrategy.energigas.entities;

/**
 * Created by kelvi on 10/08/2016.
 */

public class PedidoOperacion {
    private long peOpId;

    private long peId;

    private int opId;

    private String fechaInicio;

    private String fechaFin;

    private int usuarioId;

    private boolean estado;

    private String fecha;

    private String observacion;

    private int orden;

    public PedidoOperacion() {
    }

    public PedidoOperacion(long peOpId, long peId, int opId, String fechaInicio, String fechaFin, int usuarioId, boolean estado, String fecha, String observacion, int orden) {
        this.peOpId = peOpId;
        this.peId = peId;
        this.opId = opId;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.usuarioId = usuarioId;
        this.estado = estado;
        this.fecha = fecha;
        this.observacion = observacion;
        this.orden = orden;
    }

    public long getPeOpId() {
        return peOpId;
    }

    public void setPeOpId(long peOpId) {
        this.peOpId = peOpId;
    }

    public long getPeId() {
        return peId;
    }

    public void setPeId(long peId) {
        this.peId = peId;
    }

    public int getOpId() {
        return opId;
    }

    public void setOpId(int opId) {
        this.opId = opId;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }
}
