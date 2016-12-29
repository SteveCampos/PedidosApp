package energigas.apps.systemstrategy.energigas.entities;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

/**
 * Created by kelvi on 10/08/2016.
 */

public class Estado extends SugarRecord {
    @Unique
    private int idEstado;

    private String objeto;

    private String descripcion;

    private int parentId;

    public Estado() {
    }

    public Estado(int idEstado, String objeto, String descripcion, int parentId) {
        this.idEstado = idEstado;
        this.objeto = objeto;
        this.descripcion = descripcion;
        this.parentId = parentId;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public String getObjeto() {
        return objeto;
    }

    public void setObjeto(String objeto) {
        this.objeto = objeto;
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


    public static Estado getEstado(String estadoId) {
        return Estado.find(Estado.class, "id_Estado=?", new String[]{estadoId}).get(0);
    }
}
