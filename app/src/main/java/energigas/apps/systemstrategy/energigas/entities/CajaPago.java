package energigas.apps.systemstrategy.energigas.entities;

/**
 * Created by kelvi on 10/08/2016.
 */

public class CajaPago {

    private long cajPagId;

    private double importe;

    private long cajMovId;

    private int usuarioId;

    private String fechaAccion;

    private boolean exportado;

    private int tipoPagoId;

    private boolean anulado;

    public CajaPago() {
    }

    public CajaPago(long cajPagId, double importe, long cajMovId, int usuarioId, String fechaAccion, boolean exportado, int tipoPagoId, boolean anulado) {
        this.cajPagId = cajPagId;
        this.importe = importe;
        this.cajMovId = cajMovId;
        this.usuarioId = usuarioId;
        this.fechaAccion = fechaAccion;
        this.exportado = exportado;
        this.tipoPagoId = tipoPagoId;
        this.anulado = anulado;
    }

    public long getCajPagId() {
        return cajPagId;
    }

    public void setCajPagId(long cajPagId) {
        this.cajPagId = cajPagId;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public long getCajMovId() {
        return cajMovId;
    }

    public void setCajMovId(long cajMovId) {
        this.cajMovId = cajMovId;
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

    public boolean isExportado() {
        return exportado;
    }

    public void setExportado(boolean exportado) {
        this.exportado = exportado;
    }

    public int getTipoPagoId() {
        return tipoPagoId;
    }

    public void setTipoPagoId(int tipoPagoId) {
        this.tipoPagoId = tipoPagoId;
    }

    public boolean isAnulado() {
        return anulado;
    }

    public void setAnulado(boolean anulado) {
        this.anulado = anulado;
    }
}
