package energigas.apps.systemstrategy.energigas.entities;

/**
 * Created by kelvi on 27/10/2016.
 */

public class BERespuestaDespacho
{
    private long despachoIdServer ;
    private long despachoIdAndroid ;

    public BERespuestaDespacho() {
    }

    public BERespuestaDespacho(long despachoIdServer, long despachoIdAndroid) {
        this.despachoIdServer = despachoIdServer;
        this.despachoIdAndroid = despachoIdAndroid;
    }

    public long getDespachoIdServer() {
        return despachoIdServer;
    }

    public void setDespachoIdServer(long despachoIdServer) {
        this.despachoIdServer = despachoIdServer;
    }

    public long getDespachoIdAndroid() {
        return despachoIdAndroid;
    }

    public void setDespachoIdAndroid(long despachoIdAndroid) {
        this.despachoIdAndroid = despachoIdAndroid;
    }
}
