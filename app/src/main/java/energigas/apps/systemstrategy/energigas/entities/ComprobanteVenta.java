package energigas.apps.systemstrategy.energigas.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kelvi on 10/08/2016.
 */

public class ComprobanteVenta {
    private long compId;

    private String serie;

    private int numDoc;

    private int formaPagoId;

    private int estadoId;

    private String fechaDoc;

    private double baseImponible;

    private double igv;

    private double total;

    private int tipoComprobanteId;

    private int clienteId;

    private int comIUsuarioId;

    private boolean anulado;

    private double saldo;

    private long lqId;

    private int tipoVentaId;

    private int establecimientoId;

    private boolean exportado;

    private String docIdentidad;

    private String valorResumen;

    private String cliente;

    private String direccionCliente;

    private String fechaCreacion;

    private String fechaActualizacion;
    private static List<ComprobanteVenta> list;

    public ComprobanteVenta() {
    }

    public ComprobanteVenta(long compId, String serie, int numDoc, int formaPagoId, int estadoId, String fechaDoc, double baseImponible, double igv, double total, int tipoComprobanteId, int clienteId, int comIUsuarioId, boolean anulado, double saldo, long lqId, int tipoVentaId, int establecimientoId, boolean exportado, String docIdentidad, String valorResumen, String cliente, String direccionCliente, String fechaCreacion, String fechaActualizacion) {
        this.compId = compId;
        this.serie = serie;
        this.numDoc = numDoc;
        this.formaPagoId = formaPagoId;
        this.estadoId = estadoId;
        this.fechaDoc = fechaDoc;
        this.baseImponible = baseImponible;
        this.igv = igv;
        this.total = total;
        this.tipoComprobanteId = tipoComprobanteId;
        this.clienteId = clienteId;
        this.comIUsuarioId = comIUsuarioId;
        this.anulado = anulado;
        this.saldo = saldo;
        this.lqId = lqId;
        this.tipoVentaId = tipoVentaId;
        this.establecimientoId = establecimientoId;
        this.exportado = exportado;
        this.docIdentidad = docIdentidad;
        this.valorResumen = valorResumen;
        this.cliente = cliente;
        this.direccionCliente = direccionCliente;
        this.fechaCreacion = fechaCreacion;
        this.fechaActualizacion = fechaActualizacion;
    }

    public static List<ComprobanteVenta> getList() {
        List<ComprobanteVenta> mList = new ArrayList<>();
        mList.add(new ComprobanteVenta());
        return mList;
    }

    public long getCompId() {
        return compId;
    }

    public void setCompId(long compId) {
        this.compId = compId;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public int getNumDoc() {
        return numDoc;
    }

    public void setNumDoc(int numDoc) {
        this.numDoc = numDoc;
    }

    public int getFormaPagoId() {
        return formaPagoId;
    }

    public void setFormaPagoId(int formaPagoId) {
        this.formaPagoId = formaPagoId;
    }

    public int getEstadoId() {
        return estadoId;
    }

    public void setEstadoId(int estadoId) {
        this.estadoId = estadoId;
    }

    public String getFechaDoc() {
        return fechaDoc;
    }

    public void setFechaDoc(String fechaDoc) {
        this.fechaDoc = fechaDoc;
    }

    public double getBaseImponible() {
        return baseImponible;
    }

    public void setBaseImponible(double baseImponible) {
        this.baseImponible = baseImponible;
    }

    public double getIgv() {
        return igv;
    }

    public void setIgv(double igv) {
        this.igv = igv;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getTipoComprobanteId() {
        return tipoComprobanteId;
    }

    public void setTipoComprobanteId(int tipoComprobanteId) {
        this.tipoComprobanteId = tipoComprobanteId;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public int getComIUsuarioId() {
        return comIUsuarioId;
    }

    public void setComIUsuarioId(int comIUsuarioId) {
        this.comIUsuarioId = comIUsuarioId;
    }

    public boolean isAnulado() {
        return anulado;
    }

    public void setAnulado(boolean anulado) {
        this.anulado = anulado;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public long getLqId() {
        return lqId;
    }

    public void setLqId(long lqId) {
        this.lqId = lqId;
    }

    public int getTipoVentaId() {
        return tipoVentaId;
    }

    public void setTipoVentaId(int tipoVentaId) {
        this.tipoVentaId = tipoVentaId;
    }

    public int getEstablecimientoId() {
        return establecimientoId;
    }

    public void setEstablecimientoId(int establecimientoId) {
        this.establecimientoId = establecimientoId;
    }

    public boolean isExportado() {
        return exportado;
    }

    public void setExportado(boolean exportado) {
        this.exportado = exportado;
    }

    public String getDocIdentidad() {
        return docIdentidad;
    }

    public void setDocIdentidad(String docIdentidad) {
        this.docIdentidad = docIdentidad;
    }

    public String getValorResumen() {
        return valorResumen;
    }

    public void setValorResumen(String valorResumen) {
        this.valorResumen = valorResumen;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getDireccionCliente() {
        return direccionCliente;
    }

    public void setDireccionCliente(String direccionCliente) {
        this.direccionCliente = direccionCliente;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(String fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }
}
