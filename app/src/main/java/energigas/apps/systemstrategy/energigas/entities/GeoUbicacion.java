package energigas.apps.systemstrategy.energigas.entities;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

/**
 * Created by kelvi on 10/08/2016.
 */

public class GeoUbicacion extends SugarRecord {

    @Unique
    private int ubId;

    private int ugId;

    private String descripcion;

    private String latitud;

    private String longitud;

    private int personaId;

    private boolean ubicacionFiscal;

    private int vehiculoId;

    private boolean ubicacionPrincipal;

    private int usuarioCreacion;

    private String fechaCreacion;

    private int usuarioActualizacion;

    private String fechaActualizacion;

    private boolean estado;

    public GeoUbicacion() {
    }

    public GeoUbicacion(int ubId, int ugId, String descripcion, String latitud, String longitud, int personaId, boolean ubicacionFiscal, int vehiculoId, boolean ubicacionPrincipal, int usuarioCreacion, String fechaCreacion, int usuarioActualizacion, String fechaActualizacion, boolean estado) {
        this.ubId = ubId;
        this.ugId = ugId;
        this.descripcion = descripcion;
        this.latitud = latitud;
        this.longitud = longitud;
        this.personaId = personaId;
        this.ubicacionFiscal = ubicacionFiscal;
        this.vehiculoId = vehiculoId;
        this.ubicacionPrincipal = ubicacionPrincipal;
        this.usuarioCreacion = usuarioCreacion;
        this.fechaCreacion = fechaCreacion;
        this.usuarioActualizacion = usuarioActualizacion;
        this.fechaActualizacion = fechaActualizacion;
        this.estado = estado;
    }

    public int getUbId() {
        return ubId;
    }

    public void setUbId(int ubId) {
        this.ubId = ubId;
    }

    public int getUgId() {
        return ugId;
    }

    public void setUgId(int ugId) {
        this.ugId = ugId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public int getPersonaId() {
        return personaId;
    }

    public void setPersonaId(int personaId) {
        this.personaId = personaId;
    }

    public boolean isUbicacionFiscal() {
        return ubicacionFiscal;
    }

    public void setUbicacionFiscal(boolean ubicacionFiscal) {
        this.ubicacionFiscal = ubicacionFiscal;
    }

    public int getVehiculoId() {
        return vehiculoId;
    }

    public void setVehiculoId(int vehiculoId) {
        this.vehiculoId = vehiculoId;
    }

    public boolean isUbicacionPrincipal() {
        return ubicacionPrincipal;
    }

    public void setUbicacionPrincipal(boolean ubicacionPrincipal) {
        this.ubicacionPrincipal = ubicacionPrincipal;
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

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public static GeoUbicacion getGeoUbicacion(String geoUbicacionId) {
        if (GeoUbicacion.find(GeoUbicacion.class, "ub_Id=?", new String[]{geoUbicacionId}).size() > 0) {
            return GeoUbicacion.find(GeoUbicacion.class, "ub_Id=?", new String[]{geoUbicacionId}).get(0);
        }
        return null;

    }
}
