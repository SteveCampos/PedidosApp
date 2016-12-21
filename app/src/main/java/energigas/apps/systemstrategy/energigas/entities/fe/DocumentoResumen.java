package energigas.apps.systemstrategy.energigas.entities.fe;

/**
 * Created by Steve on 22/11/2016.
 */

public class DocumentoResumen {
    public Contribuyente emisor;
    public String idDocumento;
    public String fechaEmision;
    public String fechaReferencia;

    public Contribuyente getEmisor() {
        return emisor;
    }

    public void setEmisor(Contribuyente emisor) {
        this.emisor = emisor;
    }

    public String getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(String idDocumento) {
        this.idDocumento = idDocumento;
    }

    public String getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(String fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public String getFechaReferencia() {
        return fechaReferencia;
    }

    public void setFechaReferencia(String fechaReferencia) {
        this.fechaReferencia = fechaReferencia;
    }
}
