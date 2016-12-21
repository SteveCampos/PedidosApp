package energigas.apps.systemstrategy.energigas.entities.fe;

/**
 * Created by Steve on 22/11/2016.
 */

public class EnviarResumenResponse extends RespuestaComun {
    public String nroTicket;

    public String getNroTicket() {
        return nroTicket;
    }

    public void setNroTicket(String nroTicket) {
        this.nroTicket = nroTicket;
    }
}
