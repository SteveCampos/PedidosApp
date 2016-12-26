package energigas.apps.systemstrategy.energigas.entities;

import com.orm.SugarRecord;
import com.orm.dsl.Ignore;
import com.orm.dsl.Unique;

/**
 * Created by kelvi on 10/08/2016.
 */

public class Persona extends SugarRecord {
    @Unique
    private int perIPersonaId;

    private String perVRazonSocial;

    private String perVNombres;

    private String perVApellidoPaterno;

    private String perVApellidoMaterno;

    private String perVDocIdentidad;

    private boolean perBEstado;

    private int perITipoDocIdentidadId;

    private int perITipoPersonaId;

    private int perlUsuarioCreacion;

    private String perDTFechaCreacion;

    private int perIUsuarioActualizacion;

    private String perDTFechaActualizacion;

    private int perIEmpresaId;

    private String perVEmail;


    @Ignore
    private GeoUbicacion ubicacion;

    private String nombreComercial;


    public Persona() {
    }

    public Persona(int perIPersonaId, String perVRazonSocial, String perVNombres, String perVApellidoPaterno, String perVApellidoMaterno, String perVDocIdentidad, boolean perBEstado, int perITipoDocIdentidadId, int perITipoPersonaId, int perlUsuarioCreacion, String perDTFechaCreacion, int perIUsuarioActualizacion, String perDTFechaActualizacion, int perIEmpresaId, String perVEmail, GeoUbicacion ubicacion, String nombreComercial) {
        this.perIPersonaId = perIPersonaId;
        this.perVRazonSocial = perVRazonSocial;
        this.perVNombres = perVNombres;
        this.perVApellidoPaterno = perVApellidoPaterno;
        this.perVApellidoMaterno = perVApellidoMaterno;
        this.perVDocIdentidad = perVDocIdentidad;
        this.perBEstado = perBEstado;
        this.perITipoDocIdentidadId = perITipoDocIdentidadId;
        this.perITipoPersonaId = perITipoPersonaId;
        this.perlUsuarioCreacion = perlUsuarioCreacion;
        this.perDTFechaCreacion = perDTFechaCreacion;
        this.perIUsuarioActualizacion = perIUsuarioActualizacion;
        this.perDTFechaActualizacion = perDTFechaActualizacion;
        this.perIEmpresaId = perIEmpresaId;
        this.perVEmail = perVEmail;
        this.ubicacion = ubicacion;
        this.nombreComercial = nombreComercial;
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public GeoUbicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(GeoUbicacion ubicacion) {
        this.ubicacion = ubicacion;
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

    public boolean isPerBEstado() {
        return perBEstado;
    }

    public void setPerBEstado(boolean perBEstado) {
        this.perBEstado = perBEstado;
    }

    public int getPerITipoDocIdentidadId() {
        return perITipoDocIdentidadId;
    }

    public void setPerITipoDocIdentidadId(int perITipoDocIdentidadId) {
        this.perITipoDocIdentidadId = perITipoDocIdentidadId;
    }

    public int getPerITipoPersonaId() {
        return perITipoPersonaId;
    }

    public void setPerITipoPersonaId(int perITipoPersonaId) {
        this.perITipoPersonaId = perITipoPersonaId;
    }

    public int getPerlUsuarioCreacion() {
        return perlUsuarioCreacion;
    }

    public void setPerlUsuarioCreacion(int perlUsuarioCreacion) {
        this.perlUsuarioCreacion = perlUsuarioCreacion;
    }

    public String getPerDTFechaCreacion() {
        return perDTFechaCreacion;
    }

    public void setPerDTFechaCreacion(String perDTFechaCreacion) {
        this.perDTFechaCreacion = perDTFechaCreacion;
    }

    public int getPerIUsuarioActualizacion() {
        return perIUsuarioActualizacion;
    }

    public void setPerIUsuarioActualizacion(int perIUsuarioActualizacion) {
        this.perIUsuarioActualizacion = perIUsuarioActualizacion;
    }

    public String getPerDTFechaActualizacion() {
        return perDTFechaActualizacion;
    }

    public void setPerDTFechaActualizacion(String perDTFechaActualizacion) {
        this.perDTFechaActualizacion = perDTFechaActualizacion;
    }

    public int getPerIEmpresaId() {
        return perIEmpresaId;
    }

    public void setPerIEmpresaId(int perIEmpresaId) {
        this.perIEmpresaId = perIEmpresaId;
    }

    public String getPerVEmail() {
        return perVEmail;
    }

    public void setPerVEmail(String perVEmail) {
        this.perVEmail = perVEmail;
    }

    public static Persona getPersona(String personaId) {
        return Persona.find(Persona.class, " per_I_Persona_Id = ? ", new String[]{personaId}).get(0);
    }
}
