package energigas.apps.systemstrategy.energigas.entities.fe;

/**
 * Created by Steve on 22/11/2016.
 */
public class DocumentoBaja extends DocumentoResumenDetalle{
    public String correlativo;
    public String motivoBaja;

    public DocumentoBaja() {
    }

    public String getCorrelativo() {
        return correlativo;
    }

    public void setCorrelativo(String correlativo) {
        this.correlativo = correlativo;
    }

    public String getMotivoBaja() {
        return motivoBaja;
    }

    public void setMotivoBaja(String motivoBaja) {
        this.motivoBaja = motivoBaja;
    }
}
