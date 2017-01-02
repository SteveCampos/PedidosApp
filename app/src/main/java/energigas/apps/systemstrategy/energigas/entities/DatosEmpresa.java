package energigas.apps.systemstrategy.energigas.entities;


/**
 * Created by kelvi on 29/12/2016.
 */

public class DatosEmpresa {

    private DEEntidad entidad;
    private String distrito;
    private String provincia;
    private String departamento;
    private String url;


    public DatosEmpresa(DEEntidad deEntidad) {
        UbicacionGeoreferencia georeferencia = UbicacionGeoreferencia.getUbicacionGeoreferencia(deEntidad.getDistritoId() + "");
        UbicacionGeoreferencia georeferenciaProvincia = UbicacionGeoreferencia.getUbicacionGeoreferencia(georeferencia.getParentId() + "");
        UbicacionGeoreferencia georeferenciaDepartamento = UbicacionGeoreferencia.getUbicacionGeoreferencia(georeferenciaProvincia.getParentId() + "");
        setEntidad(deEntidad);
        setDistrito(georeferencia.getDescripcion());
        setProvincia(georeferenciaProvincia.getDescripcion());
        setDepartamento(georeferenciaDepartamento.getDescripcion());
        setUrl(deEntidad.getSite());
    }

    public DatosEmpresa(DEEntidad entidad, String distrito, String provincia, String departamento, String url) {
        this.entidad = entidad;
        this.distrito = distrito;
        this.provincia = provincia;
        this.departamento = departamento;
        this.url = url;
    }

    public DEEntidad getEntidad() {
        return entidad;
    }

    public void setEntidad(DEEntidad entidad) {
        this.entidad = entidad;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
