package energigas.apps.systemstrategy.energigas.entities;

/**
 * Created by kelvi on 21/12/2016.
 */

public class BERespFacturaM {

    private long idServer;
    private long idAndroid;

    public BERespFacturaM(long idServer, long idAndroid) {
        this.idServer = idServer;
        this.idAndroid = idAndroid;
    }

    public BERespFacturaM() {
    }

    public long getIdServer() {
        return idServer;
    }

    public void setIdServer(long idServer) {
        this.idServer = idServer;
    }

    public long getIdAndroid() {
        return idAndroid;
    }

    public void setIdAndroid(long idAndroid) {
        this.idAndroid = idAndroid;
    }
}
