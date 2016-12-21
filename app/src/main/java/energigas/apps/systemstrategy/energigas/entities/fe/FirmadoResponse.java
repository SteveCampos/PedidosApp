package energigas.apps.systemstrategy.energigas.entities.fe;



/**
 * Created by Steve on 22/11/2016.
 */


public class FirmadoResponse extends RespuestaComun {

    public String tramaXmlFirmado;
    public String resumenFirma;
    public String valorFirma;

    public FirmadoResponse() {
    }


    public String getTramaXmlFirmado() {
        return tramaXmlFirmado;
    }

    public void setTramaXmlFirmado(String tramaXmlFirmado) {
        this.tramaXmlFirmado = tramaXmlFirmado;
    }

    public String getResumenFirma() {
        return resumenFirma;
    }

    public void setResumenFirma(String resumenFirma) {
        this.resumenFirma = resumenFirma;
    }

    public String getValorFirma() {
        return valorFirma;
    }

    public void setValorFirma(String valorFirma) {
        this.valorFirma = valorFirma;
    }
}
