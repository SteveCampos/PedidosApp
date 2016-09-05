package energigas.apps.systemstrategy.energigas.entities;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

/**
 * Created by kelvi on 10/08/2016.
 */

public class Serie extends SugarRecord{

    @Unique
    private int compIComprobanteId;

    private int compITipoComprobanteId;

    private String compVSerie;

    private int compICorrelativo;

    private String compVCodigo;

    private int compIUsuarioCreacionId;

    private int compIUsuarioActualizacionId;

    private String compDTFechaCreacion;

    private String compDTFechaActualizacion;

    private int compIEmpresaId;

    private boolean compBEstado;

    private int parametro;

    public Serie() {
    }

    public Serie(int compIComprobanteId, int compITipoComprobanteId, String compVSerie, int compICorrelativo, String compVCodigo, int compIUsuarioCreacionId, int compIUsuarioActualizacionId, String compDTFechaCreacion, String compDTFechaActualizacion, int compIEmpresaId, boolean compBEstado, int parametro) {
        this.compIComprobanteId = compIComprobanteId;
        this.compITipoComprobanteId = compITipoComprobanteId;
        this.compVSerie = compVSerie;
        this.compICorrelativo = compICorrelativo;
        this.compVCodigo = compVCodigo;
        this.compIUsuarioCreacionId = compIUsuarioCreacionId;
        this.compIUsuarioActualizacionId = compIUsuarioActualizacionId;
        this.compDTFechaCreacion = compDTFechaCreacion;
        this.compDTFechaActualizacion = compDTFechaActualizacion;
        this.compIEmpresaId = compIEmpresaId;
        this.compBEstado = compBEstado;
        this.parametro = parametro;
    }

    public int getParametro() {
        return parametro;
    }

    public void setParametro(int parametro) {
        this.parametro = parametro;
    }

    public int getCompIComprobanteId() {
        return compIComprobanteId;
    }

    public void setCompIComprobanteId(int compIComprobanteId) {
        this.compIComprobanteId = compIComprobanteId;
    }

    public int getCompITipoComprobanteId() {
        return compITipoComprobanteId;
    }

    public void setCompITipoComprobanteId(int compITipoComprobanteId) {
        this.compITipoComprobanteId = compITipoComprobanteId;
    }

    public String getCompVSerie() {
        return compVSerie;
    }

    public void setCompVSerie(String compVSerie) {
        this.compVSerie = compVSerie;
    }

    public int getCompICorrelativo() {
        return compICorrelativo;
    }

    public void setCompICorrelativo(int compICorrelativo) {
        this.compICorrelativo = compICorrelativo;
    }

    public String getCompVCodigo() {
        return compVCodigo;
    }

    public void setCompVCodigo(String compVCodigo) {
        this.compVCodigo = compVCodigo;
    }

    public int getCompIUsuarioCreacionId() {
        return compIUsuarioCreacionId;
    }

    public void setCompIUsuarioCreacionId(int compIUsuarioCreacionId) {
        this.compIUsuarioCreacionId = compIUsuarioCreacionId;
    }

    public int getCompIUsuarioActualizacionId() {
        return compIUsuarioActualizacionId;
    }

    public void setCompIUsuarioActualizacionId(int compIUsuarioActualizacionId) {
        this.compIUsuarioActualizacionId = compIUsuarioActualizacionId;
    }

    public String getCompDTFechaCreacion() {
        return compDTFechaCreacion;
    }

    public void setCompDTFechaCreacion(String compDTFechaCreacion) {
        this.compDTFechaCreacion = compDTFechaCreacion;
    }

    public String getCompDTFechaActualizacion() {
        return compDTFechaActualizacion;
    }

    public void setCompDTFechaActualizacion(String compDTFechaActualizacion) {
        this.compDTFechaActualizacion = compDTFechaActualizacion;
    }

    public int getCompIEmpresaId() {
        return compIEmpresaId;
    }

    public void setCompIEmpresaId(int compIEmpresaId) {
        this.compIEmpresaId = compIEmpresaId;
    }

    public boolean isCompBEstado() {
        return compBEstado;
    }

    public void setCompBEstado(boolean compBEstado) {
        this.compBEstado = compBEstado;
    }
}
