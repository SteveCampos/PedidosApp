package energigas.apps.systemstrategy.energigas.entities.fe;

/**
 * Created by Steve on 22/11/2016.
 */

public class EnviarDocumentoRequest extends EnvioDocumentoComun {
    public String tramaXmlFirmado;

    public String getTramaXmlFirmado() {
        return tramaXmlFirmado;
    }

    public void setTramaXmlFirmado(String tramaXmlFirmado) {
        this.tramaXmlFirmado = tramaXmlFirmado;
    }
}
