package energigas.apps.systemstrategy.energigas.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kelvi on 10/08/2016.
 */

public class CajaPago {

    private long cajPagId;

    private double importe;

    private long cajMovId;

    private int usuarioId;

    private String fechaAccion;

    private boolean exportado;

    private int tipoPagoId;

    private boolean anulado;

    private String comprobante;
    private String serie;

    public CajaPago(long cajPagId, double importe, long cajMovId, int usuarioId, String fechaAccion, boolean exportado, int tipoPagoId, boolean anulado, String comprobante, String serie) {
        this.cajPagId = cajPagId;
        this.importe = importe;
        this.cajMovId = cajMovId;
        this.usuarioId = usuarioId;
        this.fechaAccion = fechaAccion;
        this.exportado = exportado;
        this.tipoPagoId = tipoPagoId;
        this.anulado = anulado;
        this.comprobante = comprobante;
        this.serie = serie;
    }

    public CajaPago() {
    }

    public String getComprobante() {
        return comprobante;
    }

    public void setComprobante(String comprobante) {
        this.comprobante = comprobante;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

//    public CajaPago(long cajPagId, double importe, long cajMovId, int usuarioId, String fechaAccion, boolean exportado, int tipoPagoId, boolean anulado) {
//        this.cajPagId = cajPagId;
//        this.importe = importe;
//        this.cajMovId = cajMovId;
//        this.usuarioId = usuarioId;
//        this.fechaAccion = fechaAccion;
//        this.exportado = exportado;
//        this.tipoPagoId = tipoPagoId;
//        this.anulado = anulado;
//    }

    public long getCajPagId() {
        return cajPagId;
    }

    public void setCajPagId(long cajPagId) {
        this.cajPagId = cajPagId;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public long getCajMovId() {
        return cajMovId;
    }

    public void setCajMovId(long cajMovId) {
        this.cajMovId = cajMovId;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getFechaAccion() {
        return fechaAccion;
    }

    public void setFechaAccion(String fechaAccion) {
        this.fechaAccion = fechaAccion;
    }

    public boolean isExportado() {
        return exportado;
    }

    public void setExportado(boolean exportado) {
        this.exportado = exportado;
    }

    public int getTipoPagoId() {
        return tipoPagoId;
    }

    public void setTipoPagoId(int tipoPagoId) {
        this.tipoPagoId = tipoPagoId;
    }

    public boolean isAnulado() {
        return anulado;
    }

    public void setAnulado(boolean anulado) {
        this.anulado = anulado;
    }

    public static List<CajaPago> getListCajaPago(){
        List<CajaPago> cajaPagoList = new ArrayList<>();
        cajaPagoList.add(new CajaPago(10,20.10,10,20,"10/08/2016",true,201,false,"F001","00000024"));
        cajaPagoList.add(new CajaPago(10,20.10,10,20,"10/08/2016",true,201,false,"F001","00000024"));
        cajaPagoList.add(new CajaPago(10,20.10,10,20,"10/08/2016",true,201,false,"F001","00000024"));
        return cajaPagoList;
    }
}
