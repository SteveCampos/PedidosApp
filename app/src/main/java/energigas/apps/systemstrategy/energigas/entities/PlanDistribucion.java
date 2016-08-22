package energigas.apps.systemstrategy.energigas.entities;

import com.orm.SugarRecord;
import com.orm.dsl.Ignore;
import com.orm.dsl.Unique;

import java.util.List;

/**
 * Created by kelvi on 10/08/2016.
 */

public class PlanDistribucion extends SugarRecord{

    @Unique
    private long pdId;

    private String fechaInicio;

    private String fechaFin;

    private int usuarioCreaccion;

    private String fechaCreacion;

    private int usuarioActualizacion;

    private String fechaActualizacion;

    private int vehiculoId;

    private int agenteId;

    private String periodo;

    private int estadoId;

    private double meta;

    private double porcentaje;


    @Ignore
    private List<PlanDistribucionDetalle> items;

    public PlanDistribucion() {
    }

    public PlanDistribucion(long pdId, String fechaInicio, String fechaFin, int usuarioCreaccion, String fechaCreacion, int usuarioActualizacion, String fechaActualizacion, int vehiculoId, int agenteId, String periodo, int estadoId, double meta, double porcentaje, List<PlanDistribucionDetalle> items) {
        this.pdId = pdId;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.usuarioCreaccion = usuarioCreaccion;
        this.fechaCreacion = fechaCreacion;
        this.usuarioActualizacion = usuarioActualizacion;
        this.fechaActualizacion = fechaActualizacion;
        this.vehiculoId = vehiculoId;
        this.agenteId = agenteId;
        this.periodo = periodo;
        this.estadoId = estadoId;
        this.meta = meta;
        this.porcentaje = porcentaje;
        this.items = items;
    }

    public long getPdId() {
        return pdId;
    }

    public void setPdId(long pdId) {
        this.pdId = pdId;
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

    public int getUsuarioCreaccion() {
        return usuarioCreaccion;
    }

    public void setUsuarioCreaccion(int usuarioCreaccion) {
        this.usuarioCreaccion = usuarioCreaccion;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public int getUsuarioActualizacion() {
        return usuarioActualizacion;
    }

    public void setUsuarioActualizacion(int usuarioActualizacion) {
        this.usuarioActualizacion = usuarioActualizacion;
    }

    public String getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(String fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public int getVehiculoId() {
        return vehiculoId;
    }

    public void setVehiculoId(int vehiculoId) {
        this.vehiculoId = vehiculoId;
    }

    public int getAgenteId() {
        return agenteId;
    }

    public void setAgenteId(int agenteId) {
        this.agenteId = agenteId;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public int getEstadoId() {
        return estadoId;
    }

    public void setEstadoId(int estadoId) {
        this.estadoId = estadoId;
    }

    public double getMeta() {
        return meta;
    }

    public void setMeta(double meta) {
        this.meta = meta;
    }

    public double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(double porcentaje) {
        this.porcentaje = porcentaje;
    }

    public List<PlanDistribucionDetalle> getItems() {
        return items;
    }

    public void setItems(List<PlanDistribucionDetalle> items) {
        this.items = items;
    }
}