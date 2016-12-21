package energigas.apps.systemstrategy.energigas.entities.fe;

/**
 * Created by Steve on 22/11/2016.
 */

public class EnviarDocumentoResponse extends RespuestaComun {

    public String codigoRespuesta;
    public String mensajeRespuesta;
    public String tramaZipCdr;

    public String getCodigoRespuesta() {
        return codigoRespuesta;
    }

    public void setCodigoRespuesta(String codigoRespuesta) {
        this.codigoRespuesta = codigoRespuesta;
    }

    public String getMensajeRespuesta() {
        return mensajeRespuesta;
    }

    public void setMensajeRespuesta(String mensajeRespuesta) {
        this.mensajeRespuesta = mensajeRespuesta;
    }

    public String getTramaZipCdr() {
        return tramaZipCdr;
    }

    public void setTramaZipCdr(String tramaZipCdr) {
        this.tramaZipCdr = tramaZipCdr;
    }
}
