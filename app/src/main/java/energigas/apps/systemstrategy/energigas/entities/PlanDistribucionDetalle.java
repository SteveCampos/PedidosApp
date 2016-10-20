package energigas.apps.systemstrategy.energigas.entities;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

/**
 * Created by kelvi on 10/08/2016.
 */

public class PlanDistribucionDetalle extends SugarRecord {

    @Unique
    private long pddId;

    private long pdId;

    private int establecimientoId;

    private int orden;

    private int estadoId;

    private String fechaCreacion;

    private int usuarioCreacion;

    private String fechaActualizacion;

    private int usuarioActualizacion;

    private long peId;

    private int agenteId;

    private int veId;

    public PlanDistribucionDetalle() {
    }

    public PlanDistribucionDetalle(long pddId, long pdId, int establecimientoId, int orden, int estadoId, String fechaCreacion, int usuarioCreacion, String fechaActualizacion, int usuarioActualizacion, long peId, int agenteId, int veId) {
        this.pddId = pddId;
        this.pdId = pdId;
        this.establecimientoId = establecimientoId;
        this.orden = orden;
        this.estadoId = estadoId;
        this.fechaCreacion = fechaCreacion;
        this.usuarioCreacion = usuarioCreacion;
        this.fechaActualizacion = fechaActualizacion;
        this.usuarioActualizacion = usuarioActualizacion;
        this.peId = peId;
        this.agenteId = agenteId;
        this.veId = veId;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public int getUsuarioCreacion() {
        return usuarioCreacion;
    }

    public void setUsuarioCreacion(int usuarioCreacion) {
        this.usuarioCreacion = usuarioCreacion;
    }

    public long getPddId() {
        return pddId;
    }

    public void setPddId(long pddId) {
        this.pddId = pddId;
    }

    public long getPdId() {
        return pdId;
    }

    public void setPdId(long pdId) {
        this.pdId = pdId;
    }

    public int getEstablecimientoId() {
        return establecimientoId;
    }

    public void setEstablecimientoId(int establecimientoId) {
        this.establecimientoId = establecimientoId;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public int isEstado() {
        return estadoId;
    }

    public void setEstado(int estado) {
        this.estadoId = estado;
    }

    public String getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(String fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public int getUsuarioActualizacion() {
        return usuarioActualizacion;
    }

    public void setUsuarioActualizacion(int usuarioActualizacion) {
        this.usuarioActualizacion = usuarioActualizacion;
    }

    public long getPeId() {
        return peId;
    }

    public void setPeId(long peId) {
        this.peId = peId;
    }

    public int getAgenteId() {
        return agenteId;
    }

    public void setAgenteId(int agenteId) {
        this.agenteId = agenteId;
    }

    public int getVeId() {
        return veId;
    }

    public void setVeId(int veId) {
        this.veId = veId;
    }
}
