package energigas.apps.systemstrategy.energigas.entities;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

/**
 * Created by kelvi on 26/12/2016.
 */

public class OrdenCargo extends SugarRecord {
    @Unique
    private long ordenCargaId;

    private long ordeCargaId;

    private String fechaRegistro;

    private String fechaComprobante;

    private int proveedorId;

    private String nroComprobante;

    private String nroGuia;

    private int tipoCargaId;

    private int tipoOrigenId;

    private double factorConversion;

    private String fechaGuia;

    private double densidad;

    private int proId;

    private int unIdComprobante;

    private double cantidadDoc;

    private int unIdTransformada;

    private double cantidadTransformada;

    private int usuarioCreacionId;

    private String fechaCreacion;

    private int estadoId;

    private int usuarioAccionId;

    private String fechaAccion;


    private Double precio;

    private Long liqId;

    public OrdenCargo() {
    }

    public long getOrdeCargaId() {
        return ordeCargaId;
    }

    public void setOrdeCargaId(long ordeCargaId) {
        this.ordeCargaId = ordeCargaId;
    }

    public OrdenCargo(long ordenCargaId, long ordeCargaId, String fechaRegistro, String fechaComprobante, int proveedorId, String nroComprobante, String nroGuia, int tipoCargaId, int tipoOrigenId, double factorConversion, String fechaGuia, double densidad, int proId, int unIdComprobante, double cantidadDoc, int unIdTransformada, double cantidadTransformada, int usuarioCreacionId, String fechaCreacion, int estadoId, int usuarioAccionId, String fechaAccion, Double precio, Long liqId) {
        this.ordenCargaId = ordenCargaId;
        this.ordeCargaId = ordeCargaId;
        this.fechaRegistro = fechaRegistro;
        this.fechaComprobante = fechaComprobante;
        this.proveedorId = proveedorId;
        this.nroComprobante = nroComprobante;
        this.nroGuia = nroGuia;
        this.tipoCargaId = tipoCargaId;
        this.tipoOrigenId = tipoOrigenId;
        this.factorConversion = factorConversion;
        this.fechaGuia = fechaGuia;
        this.densidad = densidad;
        this.proId = proId;
        this.unIdComprobante = unIdComprobante;
        this.cantidadDoc = cantidadDoc;
        this.unIdTransformada = unIdTransformada;
        this.cantidadTransformada = cantidadTransformada;
        this.usuarioCreacionId = usuarioCreacionId;
        this.fechaCreacion = fechaCreacion;
        this.estadoId = estadoId;
        this.usuarioAccionId = usuarioAccionId;
        this.fechaAccion = fechaAccion;
        this.precio = precio;
        this.liqId = liqId;
    }

    public Long getLiqId() {
        return liqId;
    }

    public void setLiqId(Long liqId) {
        this.liqId = liqId;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public long getOrdenCargaId() {
        return ordenCargaId;
    }

    public void setOrdenCargaId(long ordenCargaId) {
        this.ordenCargaId = ordenCargaId;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getFechaComprobante() {
        return fechaComprobante;
    }

    public void setFechaComprobante(String fechaComprobante) {
        this.fechaComprobante = fechaComprobante;
    }

    public int getProveedorId() {
        return proveedorId;
    }

    public void setProveedorId(int proveedorId) {
        this.proveedorId = proveedorId;
    }

    public String getNroComprobante() {
        return nroComprobante;
    }

    public void setNroComprobante(String nroComprobante) {
        this.nroComprobante = nroComprobante;
    }

    public String getNroGuia() {
        return nroGuia;
    }

    public void setNroGuia(String nroGuia) {
        this.nroGuia = nroGuia;
    }

    public int getTipoCargaId() {
        return tipoCargaId;
    }

    public void setTipoCargaId(int tipoCargaId) {
        this.tipoCargaId = tipoCargaId;
    }

    public int getTipoOrigenId() {
        return tipoOrigenId;
    }

    public void setTipoOrigenId(int tipoOrigenId) {
        this.tipoOrigenId = tipoOrigenId;
    }

    public double getFactorConversion() {
        return factorConversion;
    }

    public void setFactorConversion(double factorConversion) {
        this.factorConversion = factorConversion;
    }

    public String getFechaGuia() {
        return fechaGuia;
    }

    public void setFechaGuia(String fechaGuia) {
        this.fechaGuia = fechaGuia;
    }

    public double getDensidad() {
        return densidad;
    }

    public void setDensidad(double densidad) {
        this.densidad = densidad;
    }

    public int getProId() {
        return proId;
    }

    public void setProId(int proId) {
        this.proId = proId;
    }

    public int getUnIdComprobante() {
        return unIdComprobante;
    }

    public void setUnIdComprobante(int unIdComprobante) {
        this.unIdComprobante = unIdComprobante;
    }

    public double getCantidadDoc() {
        return cantidadDoc;
    }

    public void setCantidadDoc(double cantidadDoc) {
        this.cantidadDoc = cantidadDoc;
    }

    public int getUnIdTransformada() {
        return unIdTransformada;
    }

    public void setUnIdTransformada(int unIdTransformada) {
        this.unIdTransformada = unIdTransformada;
    }

    public double getCantidadTransformada() {
        return cantidadTransformada;
    }

    public void setCantidadTransformada(double cantidadTransformada) {
        this.cantidadTransformada = cantidadTransformada;
    }

    public int getUsuarioCreacionId() {
        return usuarioCreacionId;
    }

    public void setUsuarioCreacionId(int usuarioCreacionId) {
        this.usuarioCreacionId = usuarioCreacionId;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public int getEstadoId() {
        return estadoId;
    }

    public void setEstadoId(int estadoId) {
        this.estadoId = estadoId;
    }

    public int getUsuarioAccionId() {
        return usuarioAccionId;
    }

    public void setUsuarioAccionId(int usuarioAccionId) {
        this.usuarioAccionId = usuarioAccionId;
    }

    public String getFechaAccion() {
        return fechaAccion;
    }

    public void setFechaAccion(String fechaAccion) {
        this.fechaAccion = fechaAccion;
    }
}
