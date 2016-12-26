package energigas.apps.systemstrategy.energigas.entities;

import android.support.annotation.Nullable;
import android.util.Log;

import com.orm.SugarRecord;
import com.orm.dsl.Ignore;

import java.util.ArrayList;
import java.util.List;

import energigas.apps.systemstrategy.energigas.utils.Utils;

/**
 * Created by kelvi on 10/08/2016.
 */

public class CajaGasto extends SugarRecord {
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

    private int tipoComprobanteId;

    public CajaGasto() {
    }

    public CajaGasto(long cajGasId, long cajMoId, double igv, double valorVenta, double importe, int tipoGastoId, int usuarioActualizacion, int usuarioCreacion, String fechaCreacion, String fechaActualizacion, int tipoComprobanteId) {
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
        this.tipoComprobanteId = tipoComprobanteId;
    }

    public int getTipoComprobanteId() {
        return tipoComprobanteId;
    }

    public void setTipoComprobanteId(int tipoComprobanteId) {
        this.tipoComprobanteId = tipoComprobanteId;
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


    public static List<CajaGasto> getListCajaGastosLiquidacion(String liquidacion) {


        List<CajaMovimiento> cajaMovimientos = CajaMovimiento.getCajaMovimientos(liquidacion);

        List<CajaGasto> cajaGastos = new ArrayList<>();


        for (CajaMovimiento cajaMovimiento : cajaMovimientos) {

            Log.d("ResumentFragment", "CAJ MOV ID : " + cajaMovimiento.getCajMovId() + "");
            CajaGasto cajaGasto = CajaGasto.getCajaGastobyCajMov(cajaMovimiento.getCajMovId() + "");
            if (cajaGasto != null) {
                cajaGastos.add(cajaGasto);
            }

        }

        Log.d("ResumentFragment", "TM: " + cajaGastos.size() + "");
        return cajaGastos;
    }

    public static CajaGasto getCajaGasto(String cajaGasto) {

        if (CajaGasto.find(CajaGasto.class, " caj_Gas_Id = ? ", new String[]{cajaGasto}).size() > 0) {
            return CajaGasto.find(CajaGasto.class, " caj_Gas_Id=? ", new String[]{cajaGasto}).get(0);
        }
        return null;
    }

    public static CajaGasto getCajaGastobyCajMov(String cajaMovId) {

        if (CajaGasto.find(CajaGasto.class, " caj_Mo_Id = ? ", new String[]{cajaMovId}).size() > 0) {
            return CajaGasto.find(CajaGasto.class, " caj_Mo_Id=? ", new String[]{cajaMovId}).get(0);
        }
        return null;
    }


}
