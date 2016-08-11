package energigas.apps.systemstrategy.energigas.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kelvi on 10/08/2016.
 */

public class CajaGasto {
    private long cajGasId;

    private long cajMoId;

    private double igv;

    private double valorVenta;

    private double importe;

    private int tipoGastoId;

    private int usuarioActualizacion;

    private int usuarioCreacion;

    private String fechaCreacion;

    private String fechaActualizacion;

    public CajaGasto() {
    }

    public CajaGasto(long cajGasId, long cajMoId, double igv, double valorVenta, double importe, int tipoGastoId, int usuarioActualizacion, int usuarioCreacion, String fechaCreacion, String fechaActualizacion) {
        this.cajGasId = cajGasId;
        this.cajMoId = cajMoId;
        this.igv = igv;
        this.valorVenta = valorVenta;
        this.importe = importe;
        this.tipoGastoId = tipoGastoId;
        this.usuarioActualizacion = usuarioActualizacion;
        this.usuarioCreacion = usuarioCreacion;
        this.fechaCreacion = fechaCreacion;
        this.fechaActualizacion = fechaActualizacion;
    }

    public long getCajGasId() {
        return cajGasId;
    }

    public void setCajGasId(long cajGasId) {
        this.cajGasId = cajGasId;
    }

    public long getCajMoId() {
        return cajMoId;
    }

    public void setCajMoId(long cajMoId) {
        this.cajMoId = cajMoId;
    }

    public double getIgv() {
        return igv;
    }

    public void setIgv(double igv) {
        this.igv = igv;
    }

    public double getValorVenta() {
        return valorVenta;
    }

    public void setValorVenta(double valorVenta) {
        this.valorVenta = valorVenta;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public int getTipoGastoId() {
        return tipoGastoId;
    }

    public void setTipoGastoId(int tipoGastoId) {
        this.tipoGastoId = tipoGastoId;
    }

    public int getUsuarioActualizacion() {
        return usuarioActualizacion;
    }

    public void setUsuarioActualizacion(int usuarioActualizacion) {
        this.usuarioActualizacion = usuarioActualizacion;
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

    public String getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(String fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public static List<CajaGasto> getListCajaGastos(){
        List<CajaGasto> cajaGastos = new ArrayList<>();
        cajaGastos.add(new CajaGasto(10,20,30,40,50,50,70,80,"28/07/2016","Quinua Power"));
        cajaGastos.add(new CajaGasto(10,20,30,40,50,50,70,80,"28/07/2016","Quinua Power"));
        cajaGastos.add(new CajaGasto(10,20,30,40,50,50,70,80,"28/07/2016","Quinua Power"));
        return cajaGastos;
    }
}
