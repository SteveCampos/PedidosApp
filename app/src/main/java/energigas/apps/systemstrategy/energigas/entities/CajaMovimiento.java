package energigas.apps.systemstrategy.energigas.entities;

/**
 * Created by kelvi on 10/08/2016.
 */

public class CajaMovimiento {
    private long cajMovId;

    private long liqId;

    private int catMovId;

    private String moneda;

    private double importe;

    private boolean estado;

    private String fechaHora;

    private String motivoAnulado;

    private String referencia;

    private int usuarioId;

    private String fechaAccion;

    private String referenciaAndroid;

    public CajaMovimiento() {
    }

    public CajaMovimiento(long cajMovId, long liqId, int catMovId, String moneda, double importe, boolean estado, String fechaHora, String motivoAnulado, String referencia, int usuarioId, String fechaAccion, String referenciaAndroid) {
        this.cajMovId = cajMovId;
        this.liqId = liqId;
        this.catMovId = catMovId;
        this.moneda = moneda;
        this.importe = importe;
        this.estado = estado;
        this.fechaHora = fechaHora;
        this.motivoAnulado = motivoAnulado;
        this.referencia = referencia;
        this.usuarioId = usuarioId;
        this.fechaAccion = fechaAccion;
        this.referenciaAndroid = referenciaAndroid;
    }

    public long getCajMovId() {
        return cajMovId;
    }

    public void setCajMovId(long cajMovId) {
        this.cajMovId = cajMovId;
    }

    public long getLiqId() {
        return liqId;
    }

    public void setLiqId(long liqId) {
        this.liqId = liqId;
    }

    public int getCatMovId() {
        return catMovId;
    }

    public void setCatMovId(int catMovId) {
        this.catMovId = catMovId;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(String fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getMotivoAnulado() {
        return motivoAnulado;
    }

    public void setMotivoAnulado(String motivoAnulado) {
        this.motivoAnulado = motivoAnulado;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
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

    public String getReferenciaAndroid() {
        return referenciaAndroid;
    }

    public void setReferenciaAndroid(String referenciaAndroid) {
        this.referenciaAndroid = referenciaAndroid;
    }
}
