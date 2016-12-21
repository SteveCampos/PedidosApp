package energigas.apps.systemstrategy.energigas.entities.fe;



/**
 * Created by Steve on 22/11/2016.
 */

public class Discrepancia {

    public String nroReferencia;
    public String tipo;
    public String descripcion;

    public Discrepancia() {
    }

    public String getNroReferencia() {
        return nroReferencia;
    }

    public void setNroReferencia(String nroReferencia) {
        this.nroReferencia = nroReferencia;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
