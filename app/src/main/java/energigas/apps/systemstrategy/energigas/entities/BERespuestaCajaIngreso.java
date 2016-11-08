package energigas.apps.systemstrategy.energigas.entities;

/**
 * Created by kelvi on 31/10/2016.
 */
public class BERespuestaCajaIngreso {

    private long cajMovIdServer;
    private long cajMovIdAndroid;
    private long cajCompIdServer ;
    private long cajCompIdAndroid ;
    private long cajPagIdAndroid;
    private long cajPagIdServer ;

    public BERespuestaCajaIngreso() {
    }

    public BERespuestaCajaIngreso(long cajMovIdServer, long cajMovIdAndroid, long cajCompIdServer, long cajCompIdAndroid, long cajPagIdAndroid, long cajPagIdServer) {
        this.cajMovIdServer = cajMovIdServer;
        this.cajMovIdAndroid = cajMovIdAndroid;
        this.cajCompIdServer = cajCompIdServer;
        this.cajCompIdAndroid = cajCompIdAndroid;
        this.cajPagIdAndroid = cajPagIdAndroid;
        this.cajPagIdServer = cajPagIdServer;
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

    public long getCajCompIdServer() {
        return cajCompIdServer;
    }

    public void setCajCompIdServer(long cajCompIdServer) {
        this.cajCompIdServer = cajCompIdServer;
    }

    public long getCajCompIdAndroid() {
        return cajCompIdAndroid;
    }

    public void setCajCompIdAndroid(long cajCompIdAndroid) {
        this.cajCompIdAndroid = cajCompIdAndroid;
    }

    public long getCajPagIdAndroid() {
        return cajPagIdAndroid;
    }

    public void setCajPagIdAndroid(long cajPagIdAndroid) {
        this.cajPagIdAndroid = cajPagIdAndroid;
    }

    public long getCajPagIdServer() {
        return cajPagIdServer;
    }

    public void setCajPagIdServer(long cajPagIdServer) {
        this.cajPagIdServer = cajPagIdServer;
    }
}
