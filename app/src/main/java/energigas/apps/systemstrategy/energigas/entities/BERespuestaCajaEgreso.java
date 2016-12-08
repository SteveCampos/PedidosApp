package energigas.apps.systemstrategy.energigas.entities;

/**
 * Created by kelvi on 25/11/2016.
 */

public class BERespuestaCajaEgreso {
    private long cajMovIdServer;
    private long cajMovIdAndroid;
    private long cajGasIdServer;
    private long cajGasIdAndroid;
    private long infGasIdServer;
    private long infGasIdAndroid;

    public BERespuestaCajaEgreso() {
    }

    public BERespuestaCajaEgreso(long cajMovIdServer, long cajMovIdAndroid, long cajGasIdServer, long cajGasIdAndroid, long infGasIdServer, long infGasIdAndroid) {
        this.cajMovIdServer = cajMovIdServer;
        this.cajMovIdAndroid = cajMovIdAndroid;
        this.cajGasIdServer = cajGasIdServer;
        this.cajGasIdAndroid = cajGasIdAndroid;
        this.infGasIdServer = infGasIdServer;
        this.infGasIdAndroid = infGasIdAndroid;
    }

    public long getCajMovIdServer() {
        return cajMovIdServer;
    }

    public void setCajMovIdServer(long cajMovIdServer) {
        this.cajMovIdServer = cajMovIdServer;
    }

    public long getCajMovIdAndroid() {
        return cajMovIdAndroid;
    }

    public void setCajMovIdAndroid(long cajMovIdAndroid) {
        this.cajMovIdAndroid = cajMovIdAndroid;
    }

    public long getCajGasIdServer() {
        return cajGasIdServer;
    }

    public void setCajGasIdServer(long cajGasIdServer) {
        this.cajGasIdServer = cajGasIdServer;
    }

    public long getCajGasIdAndroid() {
        return cajGasIdAndroid;
    }

    public void setCajGasIdAndroid(long cajGasIdAndroid) {
        this.cajGasIdAndroid = cajGasIdAndroid;
    }

    public long getInfGasIdServer() {
        return infGasIdServer;
    }

    public void setInfGasIdServer(long infGasIdServer) {
        this.infGasIdServer = infGasIdServer;
    }

    public long getInfGasIdAndroid() {
        return infGasIdAndroid;
    }

    public void setInfGasIdAndroid(long infGasIdAndroid) {
        this.infGasIdAndroid = infGasIdAndroid;
    }
}
