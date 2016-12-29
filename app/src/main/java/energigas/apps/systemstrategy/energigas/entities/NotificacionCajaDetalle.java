package energigas.apps.systemstrategy.energigas.entities;

/**
 * Created by kelvi on 27/12/2016.
 */

public class NotificacionCajaDetalle {

    private int EstablecimientoId;
    private int EstadoFacId;
    private int EstadoId;
    private String Fecha;
    private int LiId;
    private int LidId;
    private int OrdenAtencion;
    private int PeId;
    private double PorDespacho;
    private double PorEntrega;
    private double PorFacturado;

    public NotificacionCajaDetalle() {
    }

    public NotificacionCajaDetalle(int establecimientoId, int estadoFacId, int estadoId, String fecha, int liId, int lidId, int ordenAtencion, int peId, double porDespacho, double porEntrega, double porFacturado) {
        EstablecimientoId = establecimientoId;
        EstadoFacId = estadoFacId;
        EstadoId = estadoId;
        Fecha = fecha;
        LiId = liId;
        LidId = lidId;
        OrdenAtencion = ordenAtencion;
        PeId = peId;
        PorDespacho = porDespacho;
        PorEntrega = porEntrega;
        PorFacturado = porFacturado;
    }

    public int getEstablecimientoId() {
        return EstablecimientoId;
    }

    public void setEstablecimientoId(int establecimientoId) {
        EstablecimientoId = establecimientoId;
    }

    public int getEstadoFacId() {
        return EstadoFacId;
    }

    public void setEstadoFacId(int estadoFacId) {
        EstadoFacId = estadoFacId;
    }

    public int getEstadoId() {
        return EstadoId;
    }

    public void setEstadoId(int estadoId) {
        EstadoId = estadoId;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    public int getLiId() {
        return LiId;
    }

    public void setLiId(int liId) {
        LiId = liId;
    }

    public int getLidId() {
        return LidId;
    }

    public void setLidId(int lidId) {
        LidId = lidId;
    }

    public int getOrdenAtencion() {
        return OrdenAtencion;
    }

    public void setOrdenAtencion(int ordenAtencion) {
        OrdenAtencion = ordenAtencion;
    }

    public int getPeId() {
        return PeId;
    }

    public void setPeId(int peId) {
        PeId = peId;
    }

    public double getPorDespacho() {
        return PorDespacho;
    }

    public void setPorDespacho(double porDespacho) {
        PorDespacho = porDespacho;
    }

    public double getPorEntrega() {
        return PorEntrega;
    }

    public void setPorEntrega(double porEntrega) {
        PorEntrega = porEntrega;
    }

    public double getPorFacturado() {
        return PorFacturado;
    }

    public void setPorFacturado(double porFacturado) {
        PorFacturado = porFacturado;
    }
}
