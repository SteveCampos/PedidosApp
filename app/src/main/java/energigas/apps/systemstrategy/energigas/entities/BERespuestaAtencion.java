package energigas.apps.systemstrategy.energigas.entities;

/**
 * Created by kike on 21/12/2016.
 */

public class BERespuestaAtencion {
    private long IdLiq;
    private long IdLiqDetalle;
    private int Respuesta;

    public BERespuestaAtencion() {
    }

    public BERespuestaAtencion(long idLiq, long idLiqDetalle, int respuesta) {
        IdLiq = idLiq;
        IdLiqDetalle = idLiqDetalle;
        Respuesta = respuesta;
    }

    public long getIdLiq() {
        return IdLiq;
    }

    public void setIdLiq(long idLiq) {
        IdLiq = idLiq;
    }

    public long getIdLiqDetalle() {
        return IdLiqDetalle;
    }

    public void setIdLiqDetalle(long idLiqDetalle) {
        IdLiqDetalle = idLiqDetalle;
    }

    public int getRespuesta() {
        return Respuesta;
    }

    public void setRespuesta(int respuesta) {
        Respuesta = respuesta;
    }
}
