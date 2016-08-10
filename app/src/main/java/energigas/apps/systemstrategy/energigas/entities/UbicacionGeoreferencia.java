package energigas.apps.systemstrategy.energigas.entities;

/**
 * Created by kelvi on 10/08/2016.
 */

public class UbicacionGeoreferencia {
    private int id;

    private String codigo;

    private String descripcion;

    private int parentId;

    private int tipoId;

    public UbicacionGeoreferencia() {
    }

    public UbicacionGeoreferencia(int id, String codigo, String descripcion, int parentId, int tipoId) {
        this.id = id;
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.parentId = parentId;
        this.tipoId = tipoId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
