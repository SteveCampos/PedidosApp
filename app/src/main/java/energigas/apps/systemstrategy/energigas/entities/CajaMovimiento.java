package energigas.apps.systemstrategy.energigas.entities;

import com.orm.SugarRecord;
import com.orm.dsl.Ignore;
import com.orm.dsl.Unique;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kelvi on 10/08/2016.
 */

public class CajaMovimiento extends SugarRecord {

    @Unique
    private long cajMovId;

    private long liqId;

    private int catMovId;

    private String moneda;

    private double importe;

    private boolean estado;

    private String fechaHora;

    private String motivoAnulado;

    private String referencia;

    private int usuarioId;

    private String fechaAccion;

    private String referenciaAndroid;

    private int tipoMovId;

    @Ignore
    private CajaPago pago;
    @Ignore
    private CajaComprobante comprobante;



    public CajaMovimiento() {
    }

    public CajaMovimiento(long cajMovId, long liqId, int catMovId, String moneda, double importe, boolean estado, String fechaHora, String motivoAnulado, String referencia, int usuarioId, String fechaAccion, String referenciaAndroid, int tipoMovId, CajaPago pago, CajaComprobante comprobante) {
        this.cajMovId = cajMovId;
        this.liqId = liqId;
        this.catMovId = catMovId;
        this.moneda = moneda;
        this.importe = importe;
        this.estado = estado;
        this.fechaHora = fechaHora;
        this.motivoAnulado = motivoAnulado;
        this.referencia = referencia;
        this.usuarioId = usuarioId;
        this.fechaAccion = fechaAccion;
        this.referenciaAndroid = referenciaAndroid;
        this.tipoMovId = tipoMovId;
        this.pago = pago;
        this.comprobante = comprobante;
    }



    public CajaPago getPago() {
        return pago;
    }

    public void setPago(CajaPago pago) {
        this.pago = pago;
    }

    public CajaComprobante getComprobante() {
        return comprobante;
    }

    public void setComprobante(CajaComprobante comprobante) {
        this.comprobante = comprobante;
    }

    public long getCajMovId() {
        return cajMovId;
    }

    public void setCajMovId(long cajMovId) {
        this.cajMovId = cajMovId;
    }

    public long getLiqId() {
        return liqId;
    }

    public void setLiqId(long liqId) {
        this.liqId = liqId;
    }

    public int getCatMovId() {
        return catMovId;
    }

    public void setCatMovId(int catMovId) {
        this.catMovId = catMovId;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(String fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getMotivoAnulado() {
        return motivoAnulado;
    }

    public void setMotivoAnulado(String motivoAnulado) {
        this.motivoAnulado = motivoAnulado;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
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

    public String getReferenciaAndroid() {
        return referenciaAndroid;
    }

    public void setReferenciaAndroid(String referenciaAndroid) {
        this.referenciaAndroid = referenciaAndroid;
    }


    public int getTipoMovId() {
        return tipoMovId;
    }

    public void setTipoMovId(int tipoMovId) {
        this.tipoMovId = tipoMovId;
    }

    public static CajaMovimiento getCajaMovimiento(String comp_Id) {

        CajaComprobante cajaComprobante = CajaComprobante.getCajaComprobante(comp_Id);

        if (cajaComprobante == null) {
            return null;
        }
        CajaMovimiento cajaMovimiento = CajaMovimiento.find(CajaMovimiento.class, "caj_Mov_Id=?", new String[]{cajaComprobante.getCajMovId() + ""}).get(0);
        CajaPago cajaPago = CajaPago.getCajaPago(cajaMovimiento.getCajMovId() + "");
        cajaMovimiento.setComprobante(cajaComprobante);
        cajaMovimiento.setPago(cajaPago);
        return cajaMovimiento;
    }

    public static CajaMovimiento getCajaMovimientoById(String id) {
        if (CajaMovimiento.find(CajaMovimiento.class, "caj_Mov_Id=?", new String[]{id}).size()>0){
            return CajaMovimiento.find(CajaMovimiento.class, "caj_Mov_Id=?", new String[]{id}).get(0);
        }

        return null;
    }


}
