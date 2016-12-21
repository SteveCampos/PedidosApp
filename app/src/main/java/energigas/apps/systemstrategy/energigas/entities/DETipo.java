package energigas.apps.systemstrategy.energigas.entities;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

import java.util.List;

/**
 * Created by kelvi on 12/12/2016.
 */

public class DETipo extends SugarRecord {
    @Unique
    private int tipoId;
    private String objeto;
    private String concepto;
    private String abreviatura;
    private String descripcion;
    private Boolean estado;
    private String codigo;
    private int parentId;

    public DETipo() {
    }

    public DETipo(int tipoId, String objeto, String concepto, String abreviatura, String descripcion, Boolean estado, String codigo, int parentId) {
        this.tipoId = tipoId;
        this.objeto = objeto;
        this.concepto = concepto;
        this.abreviatura = abreviatura;
        this.descripcion = descripcion;
        this.estado = estado;
        this.codigo = codigo;
        this.parentId = parentId;
    }


    public int getTipoId() {
        return tipoId;
    }

    public void setTipoId(int tipoId) {
        this.tipoId = tipoId;
    }

    public String getObjeto() {
        return objeto;
    }

    public void setObjeto(String objeto) {
        this.objeto = objeto;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public static DETipo getDeTipo(String tipoId) {
        List<DETipo> deTipos = DETipo.find(DETipo.class, "tipo_Id = ? ", new String[]{tipoId});
        if (deTipos.size() > 0) {
            return deTipos.get(0);
        }
        return null;
    }
}
