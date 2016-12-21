package energigas.apps.systemstrategy.energigas.entities.fe;



/**
 * Created by Steve on 22/11/2016.
 */

public class DocumentoRelacionado {
    public String NroDocumento;
    public String TipoDocumento;

    public DocumentoRelacionado() {
    }

    public String getNroDocumento() {
        return NroDocumento;
    }

    public void setNroDocumento(String nroDocumento) {
        NroDocumento = nroDocumento;
    }

    public String getTipoDocumento() {
        return TipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        TipoDocumento = tipoDocumento;
    }
}
