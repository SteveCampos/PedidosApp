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
    private List<Almacen> itemsAlmacen ;
    @Ignore
    private GeoUbicacion ubicacion;

    public Establecimiento() {

    }

    public Establecimiento(int estIEstablecimientoId, String estVCodigo, String estVDescripcion, int estIClienteId, int ubId, int estICategoriaId, int estICanalId, String estVTelefono, String estVCelular, int estIUsuarioCreacion, String estDTFechaCreacion, int estIUsuarioActualizacion, String estDTFechaActualizacion, int estIEstadoId, int estIUsuarioAprobacion, String estDTFechaAprobacion, String estVObservacion, String estVKeyFireBase, String estVContacto, List<Almacen> itemsAlmacen, GeoUbicacion ubicacion) {
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

    public static List<Establecimiento> getList(){
        List<Establecimiento> listEstablecimiento = new ArrayList<>();
        for (int i= 0; i<= 10; i++){
            listEstablecimiento.add(new Establecimiento(100, "1000", "Descripción", 1, 1, 1, 1, "993061806", "993061806",
                    1, "16/02/16", 1, "16/02/16", 1, 1, "126/06/16", "Observacion", "KEyFirebase", "Contacto",null,null));
            /*
            listEstablecimiento.add(new Establecimiento("Agropacking Export S.A.","car. panamericana norte km. 1076","2.0","1.4 km","externo"));
            listEstablecimiento.add(new Establecimiento("Alsur Peru S.A.C.","av.Juan Pablo II N°  1130 MZA","4.0","7 km","interno"));
            listEstablecimiento.add(new Establecimiento("America Textil Peru","Sector Peruano Zona II MZA.","3.0","24 km","externo"));*/
        }
        return listEstablecimiento;
    }
}
