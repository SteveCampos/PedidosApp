package energigas.apps.systemstrategy.energigas.entities;

import com.orm.SugarRecord;
import com.orm.dsl.Ignore;
import com.orm.dsl.Unique;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jairc on 19/07/2016.
 */

public class Establecimiento extends SugarRecord {
    @Unique
    private int estIEstablecimientoId;

    private String estVCodigo;

    private String estVDescripcion;

    private int estIClienteId;

    //GEOUBICACION
    private int ubId;

    private int estICategoriaId;

    private int estICanalId;

    private String estVTelefono;

    private String estVCelular;

    private int estIUsuarioCreacion;

    private String estDTFechaCreacion;

    private int estIUsuarioActualizacion;

    private String estDTFechaActualizacion;

    private int estIEstadoId;

    private int estIUsuarioAprobacion;

    private String estDTFechaAprobacion;

    private String estVObservacion;

    private String estVKeyFireBase;

    private String estVContacto;

    @Ignore
    private List<Almacen> itemsAlmacen;
    @Ignore
    private GeoUbicacion ubicacion;

    private int ordenAtencionAndroid;

    private int liquidacionIdAndroid;


    public Establecimiento() {

    }

    public Establecimiento(int estIEstablecimientoId, String estVCodigo, String estVDescripcion, int estIClienteId, int ubId, int estICategoriaId, int estICanalId, String estVTelefono, String estVCelular, int estIUsuarioCreacion, String estDTFechaCreacion, int estIUsuarioActualizacion, String estDTFechaActualizacion, int estIEstadoId, int estIUsuarioAprobacion, String estDTFechaAprobacion, String estVObservacion, String estVKeyFireBase, String estVContacto, List<Almacen> itemsAlmacen, GeoUbicacion ubicacion, int ordenAtencionAndroid, int liquidacionIdAndroid) {
        this.estIEstablecimientoId = estIEstablecimientoId;
        this.estVCodigo = estVCodigo;
        this.estVDescripcion = estVDescripcion;
        this.estIClienteId = estIClienteId;
        this.ubId = ubId;
        this.estICategoriaId = estICategoriaId;
        this.estICanalId = estICanalId;
        this.estVTelefono = estVTelefono;
        this.estVCelular = estVCelular;
        this.estIUsuarioCreacion = estIUsuarioCreacion;
        this.estDTFechaCreacion = estDTFechaCreacion;
        this.estIUsuarioActualizacion = estIUsuarioActualizacion;
        this.estDTFechaActualizacion = estDTFechaActualizacion;
        this.estIEstadoId = estIEstadoId;
        this.estIUsuarioAprobacion = estIUsuarioAprobacion;
        this.estDTFechaAprobacion = estDTFechaAprobacion;
        this.estVObservacion = estVObservacion;
        this.estVKeyFireBase = estVKeyFireBase;
        this.estVContacto = estVContacto;
        this.itemsAlmacen = itemsAlmacen;
        this.ubicacion = ubicacion;
        this.ordenAtencionAndroid = ordenAtencionAndroid;
        this.liquidacionIdAndroid = liquidacionIdAndroid;
    }

    public int getLiquidacionIdAndroid() {
        return liquidacionIdAndroid;
    }

    public void setLiquidacionIdAndroid(int liquidacionIdAndroid) {
        this.liquidacionIdAndroid = liquidacionIdAndroid;
    }

    public int getOrdenAtencionAndroid() {
        return ordenAtencionAndroid;
    }

    public void setOrdenAtencionAndroid(int ordenAtencionAndroid) {
        this.ordenAtencionAndroid = ordenAtencionAndroid;
    }

    public int getEstIEstablecimientoId() {
        return estIEstablecimientoId;
    }

    public void setEstIEstablecimientoId(int estIEstablecimientoId) {
        this.estIEstablecimientoId = estIEstablecimientoId;
    }

    public String getEstVCodigo() {
        return estVCodigo;
    }

    public void setEstVCodigo(String estVCodigo) {
        this.estVCodigo = estVCodigo;
    }

    public String getEstVDescripcion() {
        return estVDescripcion;
    }

    public void setEstVDescripcion(String estVDescripcion) {
        this.estVDescripcion = estVDescripcion;
    }

    public int getEstIClienteId() {
        return estIClienteId;
    }

    public void setEstIClienteId(int estIClienteId) {
        this.estIClienteId = estIClienteId;
    }

    public int getUbId() {
        return ubId;
    }

    public void setUbId(int ubId) {
        this.ubId = ubId;
    }

    public int getEstICategoriaId() {
        return estICategoriaId;
    }

    public void setEstICategoriaId(int estICategoriaId) {
        this.estICategoriaId = estICategoriaId;
    }

    public int getEstICanalId() {
        return estICanalId;
    }

    public void setEstICanalId(int estICanalId) {
        this.estICanalId = estICanalId;
    }

    public String getEstVTelefono() {
        return estVTelefono;
    }

    public void setEstVTelefono(String estVTelefono) {
        this.estVTelefono = estVTelefono;
    }

    public String getEstVCelular() {
        return estVCelular;
    }

    public void setEstVCelular(String estVCelular) {
        this.estVCelular = estVCelular;
    }

    public int getEstIUsuarioCreacion() {
        return estIUsuarioCreacion;
    }

    public void setEstIUsuarioCreacion(int estIUsuarioCreacion) {
        this.estIUsuarioCreacion = estIUsuarioCreacion;
    }

    public String getEstDTFechaCreacion() {
        return estDTFechaCreacion;
    }

    public void setEstDTFechaCreacion(String estDTFechaCreacion) {
        this.estDTFechaCreacion = estDTFechaCreacion;
    }

    public int getEstIUsuarioActualizacion() {
        return estIUsuarioActualizacion;
    }

    public void setEstIUsuarioActualizacion(int estIUsuarioActualizacion) {
        this.estIUsuarioActualizacion = estIUsuarioActualizacion;
    }

    public String getEstDTFechaActualizacion() {
        return estDTFechaActualizacion;
    }

    public void setEstDTFechaActualizacion(String estDTFechaActualizacion) {
        this.estDTFechaActualizacion = estDTFechaActualizacion;
    }

    public int getEstIEstadoId() {
        return estIEstadoId;
    }

    public void setEstIEstadoId(int estIEstadoId) {
        this.estIEstadoId = estIEstadoId;
    }

    public int getEstIUsuarioAprobacion() {
        return estIUsuarioAprobacion;
    }

    public void setEstIUsuarioAprobacion(int estIUsuarioAprobacion) {
        this.estIUsuarioAprobacion = estIUsuarioAprobacion;
    }

    public String getEstDTFechaAprobacion() {
        return estDTFechaAprobacion;
    }

    public void setEstDTFechaAprobacion(String estDTFechaAprobacion) {
        this.estDTFechaAprobacion = estDTFechaAprobacion;
    }

    public String getEstVObservacion() {
        return estVObservacion;
    }

    public void setEstVObservacion(String estVObservacion) {
        this.estVObservacion = estVObservacion;
    }

    public String getEstVKeyFireBase() {
        return estVKeyFireBase;
    }

    public void setEstVKeyFireBase(String estVKeyFireBase) {
        this.estVKeyFireBase = estVKeyFireBase;
    }

    public String getEstVContacto() {
        return estVContacto;
    }

    public void setEstVContacto(String estVContacto) {
        this.estVContacto = estVContacto;
    }

    public List<Almacen> getItemsAlmacen() {
        return itemsAlmacen;
    }

    public void setItemsAlmacen(List<Almacen> itemsAlmacen) {
        this.itemsAlmacen = itemsAlmacen;
    }

    public GeoUbicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(GeoUbicacion ubicacion) {
        this.ubicacion = ubicacion;
    }


    public static Establecimiento getEstablecimientoById(String id) {
        return Establecimiento.find(Establecimiento.class, " est_I_Establecimiento_Id = ? ", new String[]{id}).get(0);
    }

    public static List<Establecimiento> getListEstablecimiento(String liquidacionId) {
        String query = "Select es.* from Establecimiento es, Caja_Liquidacion_Detalle cd where cd.li_Id=? and es.est_I_Establecimiento_Id=cd.establecimiento_Id order by es.orden_Atencion_Android "; //GROUP BY cd.establecimiento_Id

        List<Establecimiento> list = Establecimiento.findWithQuery(Establecimiento.class, query, new String[]{liquidacionId + ""});
        return list;
    }
}
