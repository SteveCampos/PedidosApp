package energigas.apps.systemstrategy.energigas.entities;

/**
 * Created by kelvi on 10/08/2016.
 */

public class Persona {

   private int  perIPersonaId;
    private String perVRazonSocial;
    private String perVNombres;
    private String perVApellidoPaterno;
    private String perVApellidoMaterno;
    private String perVDocIdentidad;
    private String perBEstado;
    private String perITipoDocIdentidadId;
    private String perITipoPersonaId;
    private String perIEmpresaId;
    private String perVEmail;

    public Persona() {
    }

    public Persona(int perIPersonaId, String perVRazonSocial, String perVNombres, String perVApellidoPaterno, String perVApellidoMaterno, String perVDocIdentidad, String perBEstado, String perITipoDocIdentidadId, String perITipoPersonaId, String perIEmpresaId, String perVEmail) {
        this.perIPersonaId = perIPersonaId;
        this.perVRazonSocial = perVRazonSocial;
        this.perVNombres = perVNombres;
        this.perVApellidoPaterno = perVApellidoPaterno;
        this.perVApellidoMaterno = perVApellidoMaterno;
        this.perVDocIdentidad = perVDocIdentidad;
        this.perBEstado = perBEstado;
        this.perITipoDocIdentidadId = perITipoDocIdentidadId;
        this.perITipoPersonaId = perITipoPersonaId;
        this.perIEmpresaId = perIEmpresaId;
        this.perVEmail = perVEmail;
    }

    public int getPerIPersonaId() {
        return perIPersonaId;
    }

    public void setPerIPersonaId(int perIPersonaId) {
        this.perIPersonaId = perIPersonaId;
    }

    public String getPerVRazonSocial() {
        return perVRazonSocial;
    }

    public void setPerVRazonSocial(String perVRazonSocial) {
        this.perVRazonSocial = perVRazonSocial;
    }

    public String getPerVNombres() {
        return perVNombres;
    }

    public void setPerVNombres(String perVNombres) {
        this.perVNombres = perVNombres;
    }

    public String getPerVApellidoPaterno() {
        return perVApellidoPaterno;
    }

    public void setPerVApellidoPaterno(String perVApellidoPaterno) {
        this.perVApellidoPaterno = perVApellidoPaterno;
    }

    public String getPerVApellidoMaterno() {
        return perVApellidoMaterno;
    }

    public void setPerVApellidoMaterno(String perVApellidoMaterno) {
        this.perVApellidoMaterno = perVApellidoMaterno;
    }

    public String getPerVDocIdentidad() {
        return perVDocIdentidad;
    }

    public void setPerVDocIdentidad(String perVDocIdentidad) {
        this.perVDocIdentidad = perVDocIdentidad;
    }

    public String getPerBEstado() {
        return perBEstado;
    }

    public void setPerBEstado(String perBEstado) {
        this.perBEstado = perBEstado;
    }

    public String getPerITipoDocIdentidadId() {
        return perITipoDocIdentidadId;
    }

    public void setPerITipoDocIdentidadId(String perITipoDocIdentidadId) {
        this.perITipoDocIdentidadId = perITipoDocIdentidadId;
    }

    public String getPerITipoPersonaId() {
        return perITipoPersonaId;
    }

    public void setPerITipoPersonaId(String perITipoPersonaId) {
        this.perITipoPersonaId = perITipoPersonaId;
    }

    public String getPerIEmpresaId() {
        return perIEmpresaId;
    }

    public void setPerIEmpresaId(String perIEmpresaId) {
        this.perIEmpresaId = perIEmpresaId;
    }

    public String getPerVEmail() {
        return perVEmail;
    }

    public void setPerVEmail(String perVEmail) {
        this.perVEmail = perVEmail;
    }
}
