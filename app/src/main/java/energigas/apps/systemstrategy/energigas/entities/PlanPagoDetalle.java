package energigas.apps.systemstrategy.energigas.entities;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

import java.util.List;

/**
 * Created by kelvi on 10/08/2016.
 */

public class PlanPagoDetalle extends SugarRecord {


    @Unique
    private int planPaDeId;

    private long planPaId;

    private String fecha;

    private double importe;

    private boolean estado;

    private double importeBase;

    private double interes;

    private double montoAPagar;

    private String fechaCobro;

    private int usuarioAccion;

    private String fechaAccion;

    private long cajMovId;

    public PlanPagoDetalle() {
    }

    public PlanPagoDetalle(long planPaId, int planPaDeId, String fecha, double importe, boolean estado, double importeBase, double interes, double montoAPagar, String fechaCobro, int usuarioAccion, String fechaAccion, long cajMovId) {
        this.planPaId = planPaId;
        this.planPaDeId = planPaDeId;
        this.fecha = fecha;
        this.importe = importe;
        this.estado = estado;
        this.importeBase = importeBase;
        this.interes = interes;
        this.montoAPagar = montoAPagar;
        this.fechaCobro = fechaCobro;
        this.usuarioAccion = usuarioAccion;
        this.fechaAccion = fechaAccion;
        this.cajMovId = cajMovId;
    }

    public long getPlanPaId() {
        return planPaId;
    }

    public void setPlanPaId(long planPaId) {
        this.planPaId = planPaId;
    }

    public int getPlanPaDeId() {
        return planPaDeId;
    }

    public void setPlanPaDeId(int planPaDeId) {
        this.planPaDeId = planPaDeId;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
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

    public double getImporteBase() {
        return importeBase;
    }

    public void setImporteBase(double importeBase) {
        this.importeBase = importeBase;
    }

    public double getInteres() {
        return interes;
    }

    public void setInteres(double interes) {
        this.interes = interes;
    }

    public double getMontoAPagar() {
        return montoAPagar;
    }

    public void setMontoAPagar(double montoAPagar) {
        this.montoAPagar = montoAPagar;
    }

    public String getFechaCobro() {
        return fechaCobro;
    }

    public void setFechaCobro(String fechaCobro) {
        this.fechaCobro = fechaCobro;
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

    public long getCajMovId() {
        return cajMovId;
    }

    public void setCajMovId(long cajMovId) {
        this.cajMovId = cajMovId;
    }

    public static List<PlanPagoDetalle> getPlanPagoDetalles(String planPagoId){
        return PlanPagoDetalle.find(PlanPagoDetalle.class,"plan_Pa_Id=?",new String[]{planPagoId});
    }
}
