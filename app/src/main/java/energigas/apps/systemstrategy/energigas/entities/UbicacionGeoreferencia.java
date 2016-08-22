package energigas.apps.systemstrategy.energigas.entities;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

/**
 * Created by kelvi on 10/08/2016.
 */

public class UbicacionGeoreferencia extends SugarRecord {
    @Unique
    private int idUbicacionGeoRef;

    private String codigo;

    private String descripcion;

    private int parentId;

    private int tipoId;

    public UbicacionGeoreferencia() {
    }

    public UbicacionGeoreferencia(int idUbicacionGeoRef, String codigo, String descripcion, int parentId, int tipoId) {
        this.idUbicacionGeoRef = idUbicacionGeoRef;
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.parentId = parentId;
        this.tipoId = tipoId;
    }

    public int getIdUbicacionGeoRef() {
        return idUbicacionGeoRef;
    }

    public void setIdUbicacionGeoRef(int idUbicacionGeoRef) {
        this.idUbicacionGeoRef = idUbicacionGeoRef;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public int getTipoId() {
        return tipoId;
    }

    public void setTipoId(int tipoId) {
        this.tipoId = tipoId;
    }
}
