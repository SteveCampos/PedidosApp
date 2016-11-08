package energigas.apps.systemstrategy.energigas.entities;

/**
 * Created by kelvi on 31/10/2016.
 */
public class BERespuestaPlanPagoDetalle {

    private int planPaDeIdServer;
    private int planPaDeIdAndroid;

    public BERespuestaPlanPagoDetalle() {
    }

    public BERespuestaPlanPagoDetalle(int planPaDeIdServer, int planPaDeIdAndroid) {


        this.planPaDeIdServer = planPaDeIdServer;
        this.planPaDeIdAndroid = planPaDeIdAndroid;
    }

    public int getPlanPaDeIdServer() {
        return planPaDeIdServer;
    }

    public void setPlanPaDeIdServer(int planPaDeIdServer) {
        this.planPaDeIdServer = planPaDeIdServer;
    }

    public int getPlanPaDeIdAndroid() {
        return planPaDeIdAndroid;
    }

    public void setPlanPaDeIdAndroid(int planPaDeIdAndroid) {
        this.planPaDeIdAndroid = planPaDeIdAndroid;
    }
}
