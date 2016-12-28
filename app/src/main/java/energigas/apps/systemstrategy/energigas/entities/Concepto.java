package energigas.apps.systemstrategy.energigas.entities;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

import java.util.List;

/**
 * Created by kelvi on 10/08/2016.
 */

public class Concepto extends SugarRecord {
    @Unique
    private int idConcepto;

    private String objeto;

    private String concepto;

    private String descripcion;

    private int estado;

    private int empresaId;

    private String fechaInicio;

    private String fechaFin;

    private String abreviatura;
    private int codigo;


    public Concepto() {
    }

    public Concepto(int idConcepto, String objeto, String concepto, String descripcion, int estado, int empresaId, String fechaInicio, String fechaFin, String abreviatura, int codigo) {
        this.idConcepto = idConcepto;
        this.objeto = objeto;
        this.concepto = concepto;
        this.descripcion = descripcion;
        this.estado = estado;
        this.empresaId = empresaId;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.abreviatura = abreviatura;
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    public int getIdConcepto() {
        return idConcepto;
    }

    public void setIdConcepto(int idConcepto) {
        this.idConcepto = idConcepto;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getEmpresaId() {
        return empresaId;
    }

    public void setEmpresaId(int empresaId) {
        this.empresaId = empresaId;
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

    public static Concepto getConceptoById(String id) {
        return Concepto.find(Concepto.class, " id_Concepto=?  ", new String[]{id}).get(0);
    }

    public static Concepto getConceptoByCodigo(String codigo) {
        return Concepto.find(Concepto.class, " codigo=?  ", new String[]{codigo}).get(0);
    }

    public static Concepto getConcepto(String codigo) {
        return Concepto.find(Concepto.class, " id_Concepto=?  ", new String[]{codigo}).get(0);
    }

    public static List<Concepto> getConceptos(String objeto, String concepto){
        return Concepto.find(Concepto.class, "objeto = ? AND concepto = ?", objeto, concepto);
    }
}
