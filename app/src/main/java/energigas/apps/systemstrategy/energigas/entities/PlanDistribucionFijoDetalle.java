package energigas.apps.systemstrategy.energigas.entities;

/**
 * Created by kelvi on 10/08/2016.
 */

public class PlanDistribucionFijoDetalle {

    private int pdrId;

    private int ruId;

    private int pdId;

    private String diaSemana;

    private double meta;

    private int porcentaje;

    private int usuarioAccion;

    private String fechaAccion;

    private boolean estado;

    public PlanDistribucionFijoDetalle() {
    }

    public PlanDistribucionFijoDetalle(int pdrId, int ruId, int pdId, String diaSemana, double meta, int porcentaje, int usuarioAccion, String fechaAccion, boolean estado) {
        this.pdrId = pdrId;
        this.ruId = ruId;
        this.pdId = pdId;
        this.diaSemana = diaSemana;
        this.meta = meta;
        this.porcentaje = porcentaje;
        this.usuarioAccion = usuarioAccion;
        this.fechaAccion = fechaAccion;
        this.estado = estado;
    }

    public int getPdrId() {
        return pdrId;
    }

    public void setPdrId(int pdrId) {
        this.pdrId = pdrId;
    }

    public int getRuId() {
        return ruId;
    }

    public void setRuId(int ruId) {
        this.ruId = ruId;
    }

    public int getPdId() {
        return pdId;
    }

    public void setPdId(int pdId) {
        this.pdId = pdId;
    }

    public String getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(String diaSemana) {
        this.diaSemana = diaSemana;
    }

    public double getMeta() {
        return meta;
    }

    public void setMeta(double meta) {
        this.meta = meta;
    }

    public int getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(int porcentaje) {
        this.porcentaje = porcentaje;
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

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}
