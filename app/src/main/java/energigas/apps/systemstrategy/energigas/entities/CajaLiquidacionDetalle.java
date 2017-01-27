package energigas.apps.systemstrategy.energigas.entities;

import android.util.Log;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

import java.util.List;

import energigas.apps.systemstrategy.energigas.utils.Constants;

/**
 * Created by kelvi on 10/08/2016.
 */

public class CajaLiquidacionDetalle extends SugarRecord {

    @Unique
    private long lidId;

    private long liId;

    private int establecimientoId;

    private String fecha;

    private String fechaAccion;

    private int motivoNoAtencionId;

    private int estadoId;

    private int orden;

    private String fechaAtencion;

    private long peId;

    private double porDespacho;

    private double porEntrega;

    private int estadoFactId;

    private double porFacturado;

    private int ordenAtencion;

    private int estadoFacId;


    public CajaLiquidacionDetalle() {
    }

    public CajaLiquidacionDetalle(long lidId, long liId, int establecimientoId, String fecha, String fechaAccion, int motivoNoAtencionId, int estadoId, int orden, String fechaAtencion, long peId, double porDespacho, double porEntrega, int estadoFactId, double porFacturado, int ordenAtencion, int estadoFacId) {
        this.lidId = lidId;
        this.liId = liId;
        this.establecimientoId = establecimientoId;
        this.fecha = fecha;
        this.fechaAccion = fechaAccion;
        this.motivoNoAtencionId = motivoNoAtencionId;
        this.estadoId = estadoId;
        this.orden = orden;
        this.fechaAtencion = fechaAtencion;
        this.peId = peId;
        this.porDespacho = porDespacho;
        this.porEntrega = porEntrega;
        this.estadoFactId = estadoFactId;
        this.porFacturado = porFacturado;
        this.ordenAtencion = ordenAtencion;
        this.estadoFacId = estadoFacId;
    }

    public int getOrdenAtencion() {
        return ordenAtencion;
    }

    public void setOrdenAtencion(int ordenAtencion) {
        this.ordenAtencion = ordenAtencion;
    }

    public int getEstadoFacId() {
        return estadoFacId;
    }

    public void setEstadoFacId(int estadoFacId) {
        this.estadoFacId = estadoFacId;
    }

    public double getPorDespacho() {
        return porDespacho;
    }

    public void setPorDespacho(double porDespacho) {
        this.porDespacho = porDespacho;
    }

    public double getPorEntrega() {
        return porEntrega;
    }

    public void setPorEntrega(double porEntrega) {
        this.porEntrega = porEntrega;
    }

    public int getEstadoFactId() {
        return estadoFactId;
    }

    public void setEstadoFactId(int estadoFactId) {
        this.estadoFactId = estadoFactId;
    }

    public double getPorFacturado() {
        return porFacturado;
    }

    public void setPorFacturado(double porFacturado) {
        this.porFacturado = porFacturado;
    }

    public long getLidId() {
        return lidId;
    }

    public void setLidId(long lidId) {
        this.lidId = lidId;
    }

    public long getLiId() {
        return liId;
    }

    public void setLiId(long liId) {
        this.liId = liId;
    }

    public int getEstablecimientoId() {
        return establecimientoId;
    }

    public void setEstablecimientoId(int establecimientoId) {
        this.establecimientoId = establecimientoId;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getFechaAccion() {
        return fechaAccion;
    }

    public void setFechaAccion(String fechaAccion) {
        this.fechaAccion = fechaAccion;
    }

    public int getMotivoNoAtencionId() {
        return motivoNoAtencionId;
    }

    public void setMotivoNoAtencionId(int motivoNoAtencionId) {
        this.motivoNoAtencionId = motivoNoAtencionId;
    }

    public int getEstadoId() {
        return estadoId;
    }

    public void setEstadoId(int estadoId) {
        this.estadoId = estadoId;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public String getFechaAtencion() {
        return fechaAtencion;
    }

    public void setFechaAtencion(String fechaAtencion) {
        this.fechaAtencion = fechaAtencion;
    }

    public long getPeId() {
        return peId;
    }

    public void setPeId(long peId) {
        this.peId = peId;
    }

    public static CajaLiquidacionDetalle getLiquidacionDetalleByEstablec(String establecimiento) {
        Log.d("ESTABLECIMIENTOID", establecimiento);
        if (CajaLiquidacionDetalle.find(CajaLiquidacionDetalle.class, "establecimiento_Id=?", new String[]{establecimiento}).size() > 0) {
            return CajaLiquidacionDetalle.find(CajaLiquidacionDetalle.class, "establecimiento_Id=?", new String[]{establecimiento}).get(0);
        }
        return null;

    }

    public static CajaLiquidacionDetalle getLiquidacionDetalleByEstablecAndPedido(String establecimiento, String pedidoId) {
        Log.d("ESTABLECIMIENTOID", establecimiento);
        if (CajaLiquidacionDetalle.find(CajaLiquidacionDetalle.class, "establecimiento_Id=? and pe_Id = ? ", new String[]{establecimiento, pedidoId}).size() > 0) {
            return CajaLiquidacionDetalle.find(CajaLiquidacionDetalle.class, "establecimiento_Id=? and pe_Id = ?", new String[]{establecimiento, pedidoId}).get(0);
        }
        return null;

    }

    public static CajaLiquidacionDetalle getLiquidacionDetalleByEstablecAndEstado(String establecimiento) {
        Log.d("ESTABLECIMIENTOID", establecimiento);
        CajaLiquidacionDetalle cajaLiquidacionDetalle = null;

        if (CajaLiquidacionDetalle.find(CajaLiquidacionDetalle.class, "establecimiento_Id=? and estado_Id = ? ", new String[]{establecimiento, Constants.ESTADO_ESTABLECIMIENTO_ATENDIDO + ""}).size() > 0) {
            cajaLiquidacionDetalle = CajaLiquidacionDetalle.find(CajaLiquidacionDetalle.class, "establecimiento_Id=? and estado_Id = ?", new String[]{establecimiento, Constants.ESTADO_ESTABLECIMIENTO_ATENDIDO + ""}).get(0);
        } else if (CajaLiquidacionDetalle.find(CajaLiquidacionDetalle.class, "establecimiento_Id=? and estado_Id = ? ", new String[]{establecimiento, Constants.ESTADO_ESTABLECIMIENTO_NO_ATENDIDO + ""}).size() > 0) {
            cajaLiquidacionDetalle = CajaLiquidacionDetalle.find(CajaLiquidacionDetalle.class, "establecimiento_Id=? and estado_Id = ?", new String[]{establecimiento, Constants.ESTADO_ESTABLECIMIENTO_NO_ATENDIDO + ""}).get(0);
        } else if (CajaLiquidacionDetalle.find(CajaLiquidacionDetalle.class, "establecimiento_Id=? and estado_Id = ? ", new String[]{establecimiento, Constants.ESTADO_ESTABLECIMIENTO_PENDIENTE + ""}).size() > 0) {
            cajaLiquidacionDetalle = CajaLiquidacionDetalle.find(CajaLiquidacionDetalle.class, "establecimiento_Id=? and estado_Id = ?", new String[]{establecimiento, Constants.ESTADO_ESTABLECIMIENTO_PENDIENTE + ""}).get(0);

        }
        return cajaLiquidacionDetalle;

    }

    public static List<CajaLiquidacionDetalle> getLiquidacionDetalle(String liquidacionDetalleId) {
        Log.d("ESTABLECIMIENTOID", liquidacionDetalleId);
        List<CajaLiquidacionDetalle> liquidacionDetalleList = CajaLiquidacionDetalle.findWithQuery(CajaLiquidacionDetalle.class, " select * from Caja_Liquidacion_Detalle where li_Id=? order by orden_Atencion ASC ", new String[]{liquidacionDetalleId});
        if (liquidacionDetalleList != null) {
            return liquidacionDetalleList;
        }
        return null;

    }

    public static int getOrdenAtencion(String liquidacionDetalleId) {
        Log.d("ESTABLECIMIENTOID", liquidacionDetalleId);
        List<CajaLiquidacionDetalle> liquidacionDetalleList = CajaLiquidacionDetalle.findWithQuery(CajaLiquidacionDetalle.class, " select * from Caja_Liquidacion_Detalle where li_Id=? order by orden_Atencion ASC ", new String[]{liquidacionDetalleId});
        Long aLong = new Long(0);
        if (liquidacionDetalleList != null) {
            aLong = new Long(liquidacionDetalleList.get(0).getOrdenAtencion());

        }
        return aLong.intValue();
    }


    public static CajaLiquidacionDetalle getLiquidacionDetalleByPedido(String pedidoId) {
        Log.d("ESTABLECIMIENTOID", pedidoId);
        List<CajaLiquidacionDetalle> liquidacionDetalleList = CajaLiquidacionDetalle.find(CajaLiquidacionDetalle.class, "pe_Id=?", new String[]{pedidoId});
        if (liquidacionDetalleList != null) {
            return liquidacionDetalleList.get(0);
        }
        return null;

    }


}
