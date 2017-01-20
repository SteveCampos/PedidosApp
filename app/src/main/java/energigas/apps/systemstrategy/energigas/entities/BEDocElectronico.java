package energigas.apps.systemstrategy.energigas.entities;

import android.util.Log;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

import java.util.List;

/**
 * Created by kelvi on 13/12/2016.
 */

public class BeDocElectronico extends SugarRecord {
    @Unique
    private long docElectronicoId;
    private int tipoDocumentoId;
    private String numeroDoc;
    private String fechaEmision;
    private int monedaId;
    private Double gravadas;
    private Double gratuitas;
    private Double inafectas;
    private Double exoneradas;
    private Double descuentoGlobal;
    private Double totalVenta;
    private Double totalIgv;
    private Double totalIsc;
    private Double totalOtrosTributos;
    private String montoEnLetras;
    private int tipoOperacionId;
    private Double calculoIgv;
    private Double calculoIsc;
    private Double calculoDetraccion;
    private Double montoPercepcion;
    private Double montoDetraccion;
    private int tipoDocAnticipoId;
    private String docAnticipo;
    private int monedaAnticipoId;
    private Double montoAnticipo;
    private int clienteId;
    private String nombreFacturacion;
    private String direccionFacturacion;
    private int corporacionId;
    private int entidadId;
    private int unidadNegocioId;
    private int usuarioCreacionId;
    private String fechaCreacion;
    private int usuarioAccionId;
    private String fechaAccion;
    private int estadoTrackId;
    private int estadoId;
    private int sistemaId;
    private Double totalDescuentos;
    private int correlativo;
    private String serie;
    private String resumenFirma;
    private String scop;
    private String placaVehiculo;
    private List<BeDocElectronicoDetalle> detalle;
    private String datosXml;
    private String rucEntidad;
    private Long comprobanteVentaId;
    private String tipoDocCodigo;
    private String nroDocClienteFacturacion;

    public BeDocElectronico() {
    }

    public BeDocElectronico(long docElectronicoId, int tipoDocumentoId, String numeroDoc, String fechaEmision, int monedaId, Double gravadas, Double gratuitas, Double inafectas, Double exoneradas, Double descuentoGlobal, Double totalVenta, Double totalIgv, Double totalIsc, Double totalOtrosTributos, String montoEnLetras, int tipoOperacionId, Double calculoIgv, Double calculoIsc, Double calculoDetraccion, Double montoPercepcion, Double montoDetraccion, int tipoDocAnticipoId, String docAnticipo, int monedaAnticipoId, Double montoAnticipo, int clienteId, String nombreFacturacion, String direccionFacturacion, int corporacionId, int entidadId, int unidadNegocioId, int usuarioCreacionId, String fechaCreacion, int usuarioAccionId, String fechaAccion, int estadoTrackId, int estadoId, int sistemaId, Double totalDescuentos, int correlativo, String serie, String resumenFirma, String scop, String placaVehiculo, List<BeDocElectronicoDetalle> detalle, String datosXml, String rucEntidad, Long comprobanteVentaId, String tipoDocCodigo, String nroDocClienteFacturacion) {
        this.docElectronicoId = docElectronicoId;
        this.tipoDocumentoId = tipoDocumentoId;
        this.numeroDoc = numeroDoc;
        this.fechaEmision = fechaEmision;
        this.monedaId = monedaId;
        this.gravadas = gravadas;
        this.gratuitas = gratuitas;
        this.inafectas = inafectas;
        this.exoneradas = exoneradas;
        this.descuentoGlobal = descuentoGlobal;
        this.totalVenta = totalVenta;
        this.totalIgv = totalIgv;
        this.totalIsc = totalIsc;
        this.totalOtrosTributos = totalOtrosTributos;
        this.montoEnLetras = montoEnLetras;
        this.tipoOperacionId = tipoOperacionId;
        this.calculoIgv = calculoIgv;
        this.calculoIsc = calculoIsc;
        this.calculoDetraccion = calculoDetraccion;
        this.montoPercepcion = montoPercepcion;
        this.montoDetraccion = montoDetraccion;
        this.tipoDocAnticipoId = tipoDocAnticipoId;
        this.docAnticipo = docAnticipo;
        this.monedaAnticipoId = monedaAnticipoId;
        this.montoAnticipo = montoAnticipo;
        this.clienteId = clienteId;
        this.nombreFacturacion = nombreFacturacion;
        this.direccionFacturacion = direccionFacturacion;
        this.corporacionId = corporacionId;
        this.entidadId = entidadId;
        this.unidadNegocioId = unidadNegocioId;
        this.usuarioCreacionId = usuarioCreacionId;
        this.fechaCreacion = fechaCreacion;
        this.usuarioAccionId = usuarioAccionId;
        this.fechaAccion = fechaAccion;
        this.estadoTrackId = estadoTrackId;
        this.estadoId = estadoId;
        this.sistemaId = sistemaId;
        this.totalDescuentos = totalDescuentos;
        this.correlativo = correlativo;
        this.serie = serie;
        this.resumenFirma = resumenFirma;
        this.scop = scop;
        this.placaVehiculo = placaVehiculo;
        this.detalle = detalle;
        this.datosXml = datosXml;
        this.rucEntidad = rucEntidad;
        this.comprobanteVentaId = comprobanteVentaId;
        this.tipoDocCodigo = tipoDocCodigo;
        this.nroDocClienteFacturacion = nroDocClienteFacturacion;
    }

    public String getNroDocClienteFacturacion() {
        return nroDocClienteFacturacion;
    }

    public void setNroDocClienteFacturacion(String nroDocClienteFacturacion) {
        this.nroDocClienteFacturacion = nroDocClienteFacturacion;
    }

    public String getTipoDocCodigo() {
        return tipoDocCodigo;
    }

    public void setTipoDocCodigo(String tipoDocCodigo) {
        this.tipoDocCodigo = tipoDocCodigo;
    }

    public Long getComprobanteVentaId() {
        return comprobanteVentaId;
    }

    public void setComprobanteVentaId(Long comprobanteVentaId) {
        this.comprobanteVentaId = comprobanteVentaId;
    }

    public long getDocElectronicoId() {
        return docElectronicoId;
    }

    public void setDocElectronicoId(long docElectronicoId) {
        this.docElectronicoId = docElectronicoId;
    }

    public int getTipoDocumentoId() {
        return tipoDocumentoId;
    }

    public void setTipoDocumentoId(int tipoDocumentoId) {
        this.tipoDocumentoId = tipoDocumentoId;
    }

    public String getNumeroDoc() {
        return numeroDoc;
    }

    public void setNumeroDoc(String numeroDoc) {
        this.numeroDoc = numeroDoc;
    }

    public String getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(String fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public int getMonedaId() {
        return monedaId;
    }

    public void setMonedaId(int monedaId) {
        this.monedaId = monedaId;
    }

    public Double getGravadas() {
        return gravadas;
    }

    public void setGravadas(Double gravadas) {
        this.gravadas = gravadas;
    }

    public Double getGratuitas() {
        return gratuitas;
    }

    public void setGratuitas(Double gratuitas) {
        this.gratuitas = gratuitas;
    }

    public Double getInafectas() {
        return inafectas;
    }

    public void setInafectas(Double inafectas) {
        this.inafectas = inafectas;
    }

    public Double getExoneradas() {
        return exoneradas;
    }

    public void setExoneradas(Double exoneradas) {
        this.exoneradas = exoneradas;
    }

    public Double getDescuentoGlobal() {
        return descuentoGlobal;
    }

    public void setDescuentoGlobal(Double descuentoGlobal) {
        this.descuentoGlobal = descuentoGlobal;
    }

    public Double getTotalVenta() {
        return totalVenta;
    }

    public void setTotalVenta(Double totalVenta) {
        this.totalVenta = totalVenta;
    }

    public Double getTotalIgv() {
        return totalIgv;
    }

    public void setTotalIgv(Double totalIgv) {
        this.totalIgv = totalIgv;
    }

    public Double getTotalIsc() {
        return totalIsc;
    }

    public void setTotalIsc(Double totalIsc) {
        this.totalIsc = totalIsc;
    }

    public Double getTotalOtrosTributos() {
        return totalOtrosTributos;
    }

    public void setTotalOtrosTributos(Double totalOtrosTributos) {
        this.totalOtrosTributos = totalOtrosTributos;
    }

    public String getMontoEnLetras() {
        return montoEnLetras;
    }

    public void setMontoEnLetras(String montoEnLetras) {
        this.montoEnLetras = montoEnLetras;
    }

    public int getTipoOperacionId() {
        return tipoOperacionId;
    }

    public void setTipoOperacionId(int tipoOperacionId) {
        this.tipoOperacionId = tipoOperacionId;
    }

    public Double getCalculoIgv() {
        return calculoIgv;
    }

    public void setCalculoIgv(Double calculoIgv) {
        this.calculoIgv = calculoIgv;
    }

    public Double getCalculoIsc() {
        return calculoIsc;
    }

    public void setCalculoIsc(Double calculoIsc) {
        this.calculoIsc = calculoIsc;
    }

    public Double getCalculoDetraccion() {
        return calculoDetraccion;
    }

    public void setCalculoDetraccion(Double calculoDetraccion) {
        this.calculoDetraccion = calculoDetraccion;
    }

    public Double getMontoPercepcion() {
        return montoPercepcion;
    }

    public void setMontoPercepcion(Double montoPercepcion) {
        this.montoPercepcion = montoPercepcion;
    }

    public Double getMontoDetraccion() {
        return montoDetraccion;
    }

    public void setMontoDetraccion(Double montoDetraccion) {
        this.montoDetraccion = montoDetraccion;
    }

    public int getTipoDocAnticipoId() {
        return tipoDocAnticipoId;
    }

    public void setTipoDocAnticipoId(int tipoDocAnticipoId) {
        this.tipoDocAnticipoId = tipoDocAnticipoId;
    }

    public String getDocAnticipo() {
        return docAnticipo;
    }

    public void setDocAnticipo(String docAnticipo) {
        this.docAnticipo = docAnticipo;
    }

    public int getMonedaAnticipoId() {
        return monedaAnticipoId;
    }

    public void setMonedaAnticipoId(int monedaAnticipoId) {
        this.monedaAnticipoId = monedaAnticipoId;
    }

    public Double getMontoAnticipo() {
        return montoAnticipo;
    }

    public void setMontoAnticipo(Double montoAnticipo) {
        this.montoAnticipo = montoAnticipo;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public String getNombreFacturacion() {
        return nombreFacturacion;
    }

    public void setNombreFacturacion(String nombreFacturacion) {
        this.nombreFacturacion = nombreFacturacion;
    }

    public String getDireccionFacturacion() {
        return direccionFacturacion;
    }

    public void setDireccionFacturacion(String direccionFacturacion) {
        this.direccionFacturacion = direccionFacturacion;
    }

    public int getCorporacionId() {
        return corporacionId;
    }

    public void setCorporacionId(int corporacionId) {
        this.corporacionId = corporacionId;
    }

    public int getEntidadId() {
        return entidadId;
    }

    public void setEntidadId(int entidadId) {
        this.entidadId = entidadId;
    }

    public int getUnidadNegocioId() {
        return unidadNegocioId;
    }

    public void setUnidadNegocioId(int unidadNegocioId) {
        this.unidadNegocioId = unidadNegocioId;
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

    public int getEstadoTrackId() {
        return estadoTrackId;
    }

    public void setEstadoTrackId(int estadoTrackId) {
        this.estadoTrackId = estadoTrackId;
    }

    public int getEstadoId() {
        return estadoId;
    }

    public void setEstadoId(int estadoId) {
        this.estadoId = estadoId;
    }

    public int getSistemaId() {
        return sistemaId;
    }

    public void setSistemaId(int sistemaId) {
        this.sistemaId = sistemaId;
    }

    public Double getTotalDescuentos() {
        return totalDescuentos;
    }

    public void setTotalDescuentos(Double totalDescuentos) {
        this.totalDescuentos = totalDescuentos;
    }

    public int getCorrelativo() {
        return correlativo;
    }

    public void setCorrelativo(int correlativo) {
        this.correlativo = correlativo;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getResumenFirma() {
        return resumenFirma;
    }

    public void setResumenFirma(String resumenFirma) {
        this.resumenFirma = resumenFirma;
    }

    public String getScop() {
        return scop;
    }

    public void setScop(String scop) {
        this.scop = scop;
    }

    public String getPlacaVehiculo() {
        return placaVehiculo;
    }

    public void setPlacaVehiculo(String placaVehiculo) {
        this.placaVehiculo = placaVehiculo;
    }

    public List<BeDocElectronicoDetalle> getDetalle() {
        return detalle;
    }

    public void setDetalle(List<BeDocElectronicoDetalle> detalle) {
        this.detalle = detalle;
    }

    public String getDatosXml() {
        return datosXml;
    }

    public void setDatosXml(String datosXml) {
        this.datosXml = datosXml;
    }

    public String getRucEntidad() {
        return rucEntidad;
    }

    public void setRucEntidad(String rucEntidad) {
        this.rucEntidad = rucEntidad;
    }

    public static BeDocElectronico getBeDocElectronico(String comprobanteVentaId) {
        List<BeDocElectronico> beDocElectronicoList = BeDocElectronico.find(BeDocElectronico.class, "comprobante_Venta_Id=?", new String[]{comprobanteVentaId});

        for (BeDocElectronico beDocElectronico : BeDocElectronico.listAll(BeDocElectronico.class)) {
            Log.d("FACTURASID", "" + beDocElectronico.getComprobanteVentaId() + "- PARM" + comprobanteVentaId);
        }
        if (beDocElectronicoList.size() > 0) {
            return beDocElectronicoList.get(0);
        }
        return null;
    }

    public static List<BeDocElectronico> beDocElectronicoList(List<BeDocElectronico> beDocElectronicos) {

        for (int i = 0; i < beDocElectronicos.size(); i++) {
            List<BeDocElectronicoDetalle> beDocElectronicoDetalles = BeDocElectronicoDetalle.find(BeDocElectronicoDetalle.class, "doc_Electronico_Id=?", new String[]{beDocElectronicos.get(0).getDocElectronicoId() + ""});
            beDocElectronicos.get(0).setDetalle(beDocElectronicoDetalles);
        }
        return beDocElectronicos;
    }
}
