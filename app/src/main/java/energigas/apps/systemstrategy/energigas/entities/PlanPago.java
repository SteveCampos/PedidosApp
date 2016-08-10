package energigas.apps.systemstrategy.energigas.entities;

/**
 * Created by kelvi on 10/08/2016.
 */

public class PlanPago {
    private long planPaId;

    private long compId;

    private String fechaPago;

    private String serie;

    private int numDoc;

    private String glosa;

    private boolean estado;

    private double porcentajeInteresMes;

    private int usuarioAccion;

    private String fechaAccion;

    public PlanPago() {
    }

    public PlanPago(long planPaId, long compId, String fechaPago, String serie, int numDoc, String glosa, boolean estado, double porcentajeInteresMes, int usuarioAccion, String fechaAccion) {
        this.planPaId = planPaId;
        this.compId = compId;
        this.fechaPago = fechaPago;
        this.serie = serie;
        this.numDoc = numDoc;
        this.glosa = glosa;
        this.estado = estado;
        this.porcentajeInteresMes = porcentajeInteresMes;
        this.usuarioAccion = usuarioAccion;
        this.fechaAccion = fechaAccion;
    }

    public long getPlanPaId() {
        return planPaId;
    }

    public void setPlanPaId(long planPaId) {
        this.planPaId = planPaId;
    }

    public long getCompId() {
        return compId;
    }

    public void setCompId(long compId) {
        this.compId = compId;
    }

    public String getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(String fechaPago) {
        this.fechaPago = fechaPago;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public int getNumDoc() {
        return numDoc;
    }

    public void setNumDoc(int numDoc) {
        this.numDoc = numDoc;
    }

    public String getGlosa() {
        return glosa;
    }

    public void setGlosa(String glosa) {
        this.glosa = glosa;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public double getPorcentajeInteresMes() {
        return porcentajeInteresMes;
    }

    public void setPorcentajeInteresMes(double porcentajeInteresMes) {
        this.porcentajeInteresMes = porcentajeInteresMes;
    }

    public int getUsuarioAccion() {
        return usuarioAccion;
    }

    public void setUsuarioAccion(int usuarioAccion) {
        this.usuarioAccion = usuarioAccion;
    }

    public String getFechaAccion() {
        return fechaAccion;
    }

    public void setFechaAccion(String fechaAccion) {
        this.fechaAccion = fechaAccion;
    }
}
