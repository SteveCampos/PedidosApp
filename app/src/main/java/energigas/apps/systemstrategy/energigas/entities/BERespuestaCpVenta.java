package energigas.apps.systemstrategy.energigas.entities;

import java.util.List;

/**
 * Created by kelvi on 31/10/2016.
 */

public class BERespuestaCpVenta {

    private long compIdServer ;
    private long compIdAndroid ;
    private List<BERespuestaCpVentaDetalle> itemsCpVenta ;
    private BERespuestaCajaIngreso cajaIngreso ;
    private BERespuestaPlanPago planPago ;

    public BERespuestaCpVenta() {
    }

    public BERespuestaCpVenta(long compIdServer, long compIdAndroid, List<BERespuestaCpVentaDetalle> itemsCpVenta, BERespuestaCajaIngreso cajaIngreso, BERespuestaPlanPago planPago) {
        this.compIdServer = compIdServer;
        this.compIdAndroid = compIdAndroid;
        this.itemsCpVenta = itemsCpVenta;
        this.cajaIngreso = cajaIngreso;
        this.planPago = planPago;
    }

    public long getCompIdServer() {
        return compIdServer;
    }

    public void setCompIdServer(long compIdServer) {
        this.compIdServer = compIdServer;
    }

    public long getCompIdAndroid() {
        return compIdAndroid;
    }

    public void setCompIdAndroid(long compIdAndroid) {
        this.compIdAndroid = compIdAndroid;
    }

    public List<BERespuestaCpVentaDetalle> getItemsCpVenta() {
        return itemsCpVenta;
    }

    public void setItemsCpVenta(List<BERespuestaCpVentaDetalle> itemsCpVenta) {
        this.itemsCpVenta = itemsCpVenta;
    }

    public BERespuestaCajaIngreso getCajaIngreso() {
        return cajaIngreso;
    }

    public void setCajaIngreso(BERespuestaCajaIngreso cajaIngreso) {
        this.cajaIngreso = cajaIngreso;
    }

    public BERespuestaPlanPago getPlanPago() {
        return planPago;
    }

    public void setPlanPago(BERespuestaPlanPago planPago) {
        this.planPago = planPago;
    }
}
