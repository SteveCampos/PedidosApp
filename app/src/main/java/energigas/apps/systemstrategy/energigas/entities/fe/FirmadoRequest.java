package energigas.apps.systemstrategy.energigas.entities.fe;

/**
 * Created by Steve on 22/11/2016.
 */

public class FirmadoRequest {
    public CertificadoDigital certificadoDigital;
    public String tramaXmlSinFirma;
    public boolean unSoloNodoExtension;

    public CertificadoDigital getCertificadoDigital() {
        return certificadoDigital;
    }

    public void setCertificadoDigital(CertificadoDigital certificadoDigital) {
        this.certificadoDigital = certificadoDigital;
    }

    public String getTramaXmlSinFirma() {
        return tramaXmlSinFirma;
    }

    public void setTramaXmlSinFirma(String tramaXmlSinFirma) {
        this.tramaXmlSinFirma = tramaXmlSinFirma;
    }

    public boolean isUnSoloNodoExtension() {
        return unSoloNodoExtension;
    }

    public void setUnSoloNodoExtension(boolean unSoloNodoExtension) {
        this.unSoloNodoExtension = unSoloNodoExtension;
    }
}
