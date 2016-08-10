package energigas.apps.systemstrategy.energigas.entities;

/**
 * Created by kelvi on 10/08/2016.
 */

public class GuiaRemision {
    private long greId;

    private String serie;

    private int numero;

    private int transInvId;

    private String fecha;

    private int usuarioId;

    private String fechaAccion;

    public GuiaRemision() {
    }

    public GuiaRemision(long greId, String serie, int numero, int transInvId, String fecha, int usuarioId, String fechaAccion) {
        this.greId = greId;
        this.serie = serie;
        this.numero = numero;
        this.transInvId = transInvId;
        this.fecha = fecha;
        this.usuarioId = usuarioId;
        this.fechaAccion = fechaAccion;
    }

    public long getGreId() {
        return greId;
    }

    public void setGreId(long greId) {
        this.greId = greId;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getTransInvId() {
        return transInvId;
    }

    public void setTransInvId(int transInvId) {
        this.transInvId = transInvId;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getFechaAccion() {
        return fechaAccion;
    }

    public void setFechaAccion(String fechaAccion) {
        this.fechaAccion = fechaAccion;
    }
}
