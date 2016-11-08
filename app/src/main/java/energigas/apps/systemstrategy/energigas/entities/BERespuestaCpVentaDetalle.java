package energigas.apps.systemstrategy.energigas.entities;

/**
 * Created by kelvi on 31/10/2016.
 */
public class BERespuestaCpVentaDetalle {
    private int compdIdServer;
    private int compdIdAndroid;

    public BERespuestaCpVentaDetalle() {
    }

    public BERespuestaCpVentaDetalle(int compdIdServer, int compdIdAndroid) {
        this.compdIdServer = compdIdServer;
        this.compdIdAndroid = compdIdAndroid;
    }

    public int getCompdIdServer() {
        return compdIdServer;
    }

    public void setCompdIdServer(int compdIdServer) {
        this.compdIdServer = compdIdServer;
    }

    public int getCompdIdAndroid() {
        return compdIdAndroid;
    }

    public void setCompdIdAndroid(int compdIdAndroid) {
        this.compdIdAndroid = compdIdAndroid;
    }
}
