package energigas.apps.systemstrategy.energigas.entities.fe;



import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Steve on 23/11/2016.
 */


public class DocumentoElectronico {

    public String tipoDocumento;

    public Contribuyente emisor;
    public Contribuyente receptor;

    public String idDocumento;
    public String fechaEmision;
    public String moneda;

    public BigDecimal gravadas;
    public BigDecimal gratuitas;
    public BigDecimal inafectas;
    public BigDecimal exoneradas;

    public BigDecimal descuentoGlobal;

    public BigDecimal totalVenta;
    public BigDecimal totalIgv;
    public BigDecimal totalIsc;
    public BigDecimal totalOtrosTributos;

    public String montoEnLetras;
    public String tipoOperacion;
    public String placaVehiculo;

    public BigDecimal calculoIgv;
    public BigDecimal calculoIsc;
    public BigDecimal calculoDetraccion;

    public BigDecimal montoPercepcion;
    public BigDecimal montoDetraccion;

    public String tipoDocAnticipo;
    public String docAnticipo;
    public String monedaAnticipo;
    public BigDecimal montoAnticipo;

    public List<DatoAdicional> datoAdicionales;
    public List<DocumentoRelacionado> relacionados;
    public List<DetalleDocumento> items;
    public DatosGuia datosGuiaTransportista;
    public List<Discrepancia> discrepancias;



    public DocumentoElectronico() {
        emisor = new Contribuyente
                (
                        tipoDocumento = "6" // RUC.
                );
        receptor = new Contribuyente
                (
                        tipoDocumento = "6" // RUC.
                );
        calculoIgv = new BigDecimal(0.18);
        calculoIsc = new BigDecimal(0.10);
        calculoDetraccion = new BigDecimal(0.04);
        items = new ArrayList<>();
        datoAdicionales = new ArrayList<>();
        relacionados = new ArrayList<>();
        discrepancias = new ArrayList<>();
        tipoDocumento = "01"; // Factura.
        tipoOperacion = "01"; // Venta Interna.
        moneda = "PEN"; // Soles.
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public Contribuyente getEmisor() {
        return emisor;
    }

    public void setEmisor(Contribuyente emisor) {
        this.emisor = emisor;
    }

    public Contribuyente getReceptor() {
        return receptor;
    }

    public void setReceptor(Contribuyente receptor) {
        this.receptor = receptor;
    }

    public String getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(String idDocumento) {
        this.idDocumento = idDocumento;
    }

    public String getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(String fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public BigDecimal getGravadas() {
        return gravadas;
    }

    public void setGravadas(BigDecimal gravadas) {
        this.gravadas = gravadas;
    }

    public BigDecimal getGratuitas() {
        return gratuitas;
    }

    public void setGratuitas(BigDecimal gratuitas) {
        this.gratuitas = gratuitas;
    }

    public BigDecimal getInafectas() {
        return inafectas;
    }

    public void setInafectas(BigDecimal inafectas) {
        this.inafectas = inafectas;
    }

    public BigDecimal getExoneradas() {
        return exoneradas;
    }

    public void setExoneradas(BigDecimal exoneradas) {
        this.exoneradas = exoneradas;
    }

    public BigDecimal getDescuentoGlobal() {
        return descuentoGlobal;
    }

    public void setDescuentoGlobal(BigDecimal descuentoGlobal) {
        this.descuentoGlobal = descuentoGlobal;
    }

    public BigDecimal getTotalVenta() {
        return totalVenta;
    }

    public void setTotalVenta(BigDecimal totalVenta) {
        this.totalVenta = totalVenta;
    }

    public BigDecimal getTotalIgv() {
        return totalIgv;
    }

    public void setTotalIgv(BigDecimal totalIgv) {
        this.totalIgv = totalIgv;
    }

    public BigDecimal getTotalIsc() {
        return totalIsc;
    }

    public void setTotalIsc(BigDecimal totalIsc) {
        this.totalIsc = totalIsc;
    }

    public BigDecimal getTotalOtrosTributos() {
        return totalOtrosTributos;
    }

    public void setTotalOtrosTributos(BigDecimal totalOtrosTributos) {
        this.totalOtrosTributos = totalOtrosTributos;
    }

    public String getMontoEnLetras() {
        return montoEnLetras;
    }

    public void setMontoEnLetras(String montoEnLetras) {
        this.montoEnLetras = montoEnLetras;
    }

    public String getTipoOperacion() {
        return tipoOperacion;
    }

    public void setTipoOperacion(String tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }

    public BigDecimal getCalculoIgv() {
        return calculoIgv;
    }

    public void setCalculoIgv(BigDecimal calculoIgv) {
        this.calculoIgv = calculoIgv;
    }

    public BigDecimal getCalculoIsc() {
        return calculoIsc;
    }

    public void setCalculoIsc(BigDecimal calculoIsc) {
        this.calculoIsc = calculoIsc;
    }

    public BigDecimal getCalculoDetraccion() {
        return calculoDetraccion;
    }

    public void setCalculoDetraccion(BigDecimal calculoDetraccion) {
        this.calculoDetraccion = calculoDetraccion;
    }

    public BigDecimal getMontoPercepcion() {
        return montoPercepcion;
    }

    public void setMontoPercepcion(BigDecimal montoPercepcion) {
        this.montoPercepcion = montoPercepcion;
    }

    public BigDecimal getMontoDetraccion() {
        return montoDetraccion;
    }

    public void setMontoDetraccion(BigDecimal montoDetraccion) {
        this.montoDetraccion = montoDetraccion;
    }

    public String getTipoDocAnticipo() {
        return tipoDocAnticipo;
    }

    public void setTipoDocAnticipo(String tipoDocAnticipo) {
        this.tipoDocAnticipo = tipoDocAnticipo;
    }

    public String getDocAnticipo() {
        return docAnticipo;
    }

    public void setDocAnticipo(String docAnticipo) {
        this.docAnticipo = docAnticipo;
    }

    public String getMonedaAnticipo() {
        return monedaAnticipo;
    }

    public void setMonedaAnticipo(String monedaAnticipo) {
        this.monedaAnticipo = monedaAnticipo;
    }

    public BigDecimal getMontoAnticipo() {
        return montoAnticipo;
    }

    public void setMontoAnticipo(BigDecimal montoAnticipo) {
        this.montoAnticipo = montoAnticipo;
    }

    public List<DatoAdicional> getDatoAdicionales() {
        return datoAdicionales;
    }

    public void setDatoAdicionales(List<DatoAdicional> datoAdicionales) {
        this.datoAdicionales = datoAdicionales;
    }

    public List<DocumentoRelacionado> getRelacionados() {
        return relacionados;
    }

    public void setRelacionados(List<DocumentoRelacionado> relacionados) {
        this.relacionados = relacionados;
    }

    public List<DetalleDocumento> getItems() {
        return items;
    }

    public void setItems(List<DetalleDocumento> items) {
        this.items = items;
    }

    public DatosGuia getDatosGuiaTransportista() {
        return datosGuiaTransportista;
    }

    public void setDatosGuiaTransportista(DatosGuia datosGuiaTransportista) {
        this.datosGuiaTransportista = datosGuiaTransportista;
    }

    public List<Discrepancia> getDiscrepancias() {
        return discrepancias;
    }

    public void setDiscrepancias(List<Discrepancia> discrepancias) {
        this.discrepancias = discrepancias;
    }

    public String getPlacaVehiculo() {
        return placaVehiculo;
    }

    public void setPlacaVehiculo(String placaVehiculo) {
        this.placaVehiculo = placaVehiculo;
    }
}
