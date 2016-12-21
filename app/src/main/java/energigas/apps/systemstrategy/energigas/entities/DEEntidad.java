package energigas.apps.systemstrategy.energigas.entities;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

import java.util.List;

import energigas.apps.systemstrategy.energigas.entities.fe.CertificadoDigital;

/**
 * Created by kelvi on 12/12/2016.
 */

public class DEEntidad extends SugarRecord {
    @Unique
    private int entidadId;
    private String razonSocial;
    private String rUC;
    private String telefono;
    private String celular;
    private String correo;
    private String site;
    private Boolean principal;
    private int corporacionId;
    private Boolean estado;
    private int usuarioCreacion;
    private String fechaCreacion;
    private int usuarioActualizacion;
    private String fechaActualizacion;
    private int tipoId;
    private String codigoPais;
    private String direccionFiscal;
    private String urbanizacion;
    private int distritoId;
    private String userSunat;
    private String passwordSunat;
    private String cetificadoDigital;
    private String nombreComercial;
    private int tipoDocumento;
    private Boolean agenteRetencion;
    private Boolean agentePercepcion;
    private String logo;
    private String logo2;
    private CertificadoDigital certificado;



    public DEEntidad() {
    }


    public DEEntidad(int entidadId, String razonSocial, String rUC, String telefono, String celular, String correo, String site, Boolean principal, int corporacionId, Boolean estado, int usuarioCreacion, String fechaCreacion, int usuarioActualizacion, String fechaActualizacion, int tipoId, String codigoPais, String direccionFiscal, String urbanizacion, int distritoId, String userSunat, String passwordSunat, String cetificadoDigital, String nombreComercial, int tipoDocumento, Boolean agenteRetencion, Boolean agentePercepcion, String logo, String logo2, CertificadoDigital certificado) {
        this.entidadId = entidadId;
        this.razonSocial = razonSocial;
        this.rUC = rUC;
        this.telefono = telefono;
        this.celular = celular;
        this.correo = correo;
        this.site = site;
        this.principal = principal;
        this.corporacionId = corporacionId;
        this.estado = estado;
        this.usuarioCreacion = usuarioCreacion;
        this.fechaCreacion = fechaCreacion;
        this.usuarioActualizacion = usuarioActualizacion;
        this.fechaActualizacion = fechaActualizacion;
        this.tipoId = tipoId;
        this.codigoPais = codigoPais;
        this.direccionFiscal = direccionFiscal;
        this.urbanizacion = urbanizacion;
        this.distritoId = distritoId;
        this.userSunat = userSunat;
        this.passwordSunat = passwordSunat;
        this.cetificadoDigital = cetificadoDigital;
        this.nombreComercial = nombreComercial;
        this.tipoDocumento = tipoDocumento;
        this.agenteRetencion = agenteRetencion;
        this.agentePercepcion = agentePercepcion;
        this.logo = logo;
        this.logo2 = logo2;
        this.certificado = certificado;
    }

    public CertificadoDigital getCertificado() {
        return certificado;
    }

    public void setCertificado(CertificadoDigital certificado) {
        this.certificado = certificado;
    }

    public int getEntidadId() {
        return entidadId;
    }

    public void setEntidadId(int entidadId) {
        this.entidadId = entidadId;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getrUC() {
        return rUC;
    }

    public void setrUC(String rUC) {
        this.rUC = rUC;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public Boolean getPrincipal() {
        return principal;
    }

    public void setPrincipal(Boolean principal) {
        this.principal = principal;
    }

    public int getCorporacionId() {
        return corporacionId;
    }

    public void setCorporacionId(int corporacionId) {
        this.corporacionId = corporacionId;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public int getUsuarioCreacion() {
        return usuarioCreacion;
    }

    public void setUsuarioCreacion(int usuarioCreacion) {
        this.usuarioCreacion = usuarioCreacion;
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

    public int getTipoId() {
        return tipoId;
    }

    public void setTipoId(int tipoId) {
        this.tipoId = tipoId;
    }

    public String getCodigoPais() {
        return codigoPais;
    }

    public void setCodigoPais(String codigoPais) {
        this.codigoPais = codigoPais;
    }

    public String getDireccionFiscal() {
        return direccionFiscal;
    }

    public void setDireccionFiscal(String direccionFiscal) {
        this.direccionFiscal = direccionFiscal;
    }

    public String getUrbanizacion() {
        return urbanizacion;
    }

    public void setUrbanizacion(String urbanizacion) {
        this.urbanizacion = urbanizacion;
    }

    public int getDistritoId() {
        return distritoId;
    }

    public void setDistritoId(int distritoId) {
        this.distritoId = distritoId;
    }

    public String getUserSunat() {
        return userSunat;
    }

    public void setUserSunat(String userSunat) {
        this.userSunat = userSunat;
    }

    public String getPasswordSunat() {
        return passwordSunat;
    }

    public void setPasswordSunat(String passwordSunat) {
        this.passwordSunat = passwordSunat;
    }

    public String getCetificadoDigital() {
        return cetificadoDigital;
    }

    public void setCetificadoDigital(String cetificadoDigital) {
        this.cetificadoDigital = cetificadoDigital;
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public int getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(int tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public Boolean getAgenteRetencion() {
        return agenteRetencion;
    }

    public void setAgenteRetencion(Boolean agenteRetencion) {
        this.agenteRetencion = agenteRetencion;
    }

    public Boolean getAgentePercepcion() {
        return agentePercepcion;
    }

    public void setAgentePercepcion(Boolean agentePercepcion) {
        this.agentePercepcion = agentePercepcion;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getLogo2() {
        return logo2;
    }

    public void setLogo2(String logo2) {
        this.logo2 = logo2;
    }

    public static DEEntidad getEntidadById(String liquidacionId) {
        List<DEEntidad> deEntidads = DEEntidad.find(DEEntidad.class, "entidad_Id=?", new String[]{liquidacionId});
        if (deEntidads.size() > 0) {
            return deEntidads.get(0);
        }
        return null;
    }
}
