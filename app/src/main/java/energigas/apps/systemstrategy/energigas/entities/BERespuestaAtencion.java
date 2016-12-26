package energigas.apps.systemstrategy.energigas.entities;

/**
 * Created by kike on 21/12/2016.
 */

public class BERespuestaAtencion {
    private long idLiq;
    private long idLiqDetalle;
    private int respuesta;

    public BERespuestaAtencion() {
    }

    public BERespuestaAtencion(long idLiq, long idLiqDetalle, int respuesta) {
        this.idLiq = idLiq;
        this.idLiqDetalle = idLiqDetalle;
        this.respuesta = respuesta;
    }

    public long getIdLiq() {
        return idLiq;
    }

    public void setIdLiq(long idLiq) {
        this.idLiq = idLiq;
    }

    public long getIdLiqDetalle() {
        return idLiqDetalle;
    }

    public void setIdLiqDetalle(long idLiqDetalle) {
        this.idLiqDetalle = idLiqDetalle;
    }

    public int getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(int respuesta) {
        this.respuesta = respuesta;
    }
}
