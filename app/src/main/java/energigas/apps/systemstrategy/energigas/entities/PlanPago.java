package energigas.apps.systemstrategy.energigas.entities;

import android.util.Log;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.orm.SugarRecord;
import com.orm.dsl.Ignore;
import com.orm.dsl.Unique;

import java.util.List;

/**
 * Created by kelvi on 10/08/2016.
 */

public class PlanPago extends SugarRecord {
    @Unique
    private long planPaId;

    private long compId;

    private String fechaPago;

    private String serie;

    private String numDoc;

    private String glosa;

    private boolean estado;

    private double porcentajeInteresMes;

    private int usuarioAccion;

    private String fechaAccion;
    @Ignore
    private List<PlanPagoDetalle> detalle;


    public PlanPago() {
    }

    public PlanPago(long planPaId, long compId, String fechaPago, String serie, String numDoc, String glosa, boolean estado, double porcentajeInteresMes, int usuarioAccion, String fechaAccion, List<PlanPagoDetalle> detalle) {
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
        this.detalle = detalle;
    }


    public List<PlanPagoDetalle> getDetalle() {
        return detalle;
    }

    public void setDetalle(List<PlanPagoDetalle> detalle) {
        this.detalle = detalle;
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

    public String getNumDoc() {
        return numDoc;
    }

    public void setNumDoc(String numDoc) {
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

    public static PlanPago getPlanPago(String compId) {

        List<PlanPago> planPagoList = PlanPago.listAll(PlanPago.class);
        for (PlanPago planPago : planPagoList) {
            Log.d("PLAN_PAGO", "plan_pago" + planPago.getCompId() + "_" + compId);
        }

        if (PlanPago.find(PlanPago.class, " comp_Id=? ", new String[]{compId}).size() > 0) {

            PlanPago planPago = PlanPago.find(PlanPago.class, " comp_Id=? ", new String[]{compId}).get(0);
            planPago.setDetalle(PlanPagoDetalle.getPlanPagoDetalles(planPago.getPlanPaId() + ""));


            return planPago;
        } else {

            return null;
        }

    }
}
