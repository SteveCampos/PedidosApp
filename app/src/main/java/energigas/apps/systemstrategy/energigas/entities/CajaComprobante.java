package energigas.apps.systemstrategy.energigas.entities;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

/**
 * Created by kelvi on 10/08/2016.
 */

public class CajaComprobante extends SugarRecord {
    @Unique
    private long cajCompId;

    private long cajMovId;

    private long compId;

    private double importe;

    private int usuarioActualizacion;

    private String fechaActualizacion;

    public CajaComprobante() {
    }

    public CajaComprobante(long cajCompId, long cajMovId, long compId, double importe, int usuarioActualizacion, String fechaActualizacion) {
        this.cajCompId = cajCompId;
        this.cajMovId = cajMovId;
        this.compId = compId;
        this.importe = importe;
        this.usuarioActualizacion = usuarioActualizacion;
        this.fechaActualizacion = fechaActualizacion;
    }

    public long getCajCompId() {
        return cajCompId;
    }

    public void setCajCompId(long cajCompId) {
        this.cajCompId = cajCompId;
    }

    public long getCajMovId() {
        return cajMovId;
    }

    public void setCajMovId(long cajMovId) {
        this.cajMovId = cajMovId;
    }

    public long getCompId() {
        return compId;
    }

    public void setCompId(long compId) {
        this.compId = compId;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
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
}
