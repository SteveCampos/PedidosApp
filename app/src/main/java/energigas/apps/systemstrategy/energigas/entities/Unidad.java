package energigas.apps.systemstrategy.energigas.entities;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

/**
 * Created by kelvi on 10/08/2016.
 */

public class Unidad extends SugarRecord{
    @Unique
    private int unId;

    private String descripcion;

    private String abreviatura;

    private boolean estado;

    public Unidad() {
    }

    public Unidad(int unId, String descripcion, String abreviatura, boolean estado) {
        this.unId = unId;
        this.descripcion = descripcion;
        this.abreviatura = abreviatura;
        this.estado = estado;
    }

    public int getUnId() {
        return unId;
    }

    public void setUnId(int unId) {
        this.unId = unId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}
