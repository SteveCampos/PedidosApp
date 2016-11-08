package energigas.apps.systemstrategy.energigas.entities;

import java.util.List;

/**
 * Created by kelvi on 31/10/2016.
 */
public class BERespuestaPlanPago {

    private long planPaIdServer;
    private long planPaIdAndroid ;
    private List<BERespuestaPlanPagoDetalle> itemsPlan ;

    public BERespuestaPlanPago() {
    }

    public BERespuestaPlanPago(long planPaIdServer, long planPaIdAndroid, List<BERespuestaPlanPagoDetalle> itemsPlan) {
        this.planPaIdServer = planPaIdServer;
        this.planPaIdAndroid = planPaIdAndroid;
        this.itemsPlan = itemsPlan;
    }

    public long getPlanPaIdServer() {
        return planPaIdServer;
    }

    public void setPlanPaIdServer(long planPaIdServer) {
        this.planPaIdServer = planPaIdServer;
    }

    public long getPlanPaIdAndroid() {
        return planPaIdAndroid;
    }

    public void setPlanPaIdAndroid(long planPaIdAndroid) {
        this.planPaIdAndroid = planPaIdAndroid;
    }

    public List<BERespuestaPlanPagoDetalle> getItemsPlan() {
        return itemsPlan;
    }

    public void setItemsPlan(List<BERespuestaPlanPagoDetalle> itemsPlan) {
        this.itemsPlan = itemsPlan;
    }
}
