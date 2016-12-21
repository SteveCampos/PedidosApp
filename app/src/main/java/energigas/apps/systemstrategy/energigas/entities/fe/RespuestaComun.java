package energigas.apps.systemstrategy.energigas.entities.fe;



/**
 * Created by Steve on 22/11/2016.
 */

public class RespuestaComun {

    public boolean exito;
    public String mensajeError;
    public String pila;
    public String nombreArchivo;

    public RespuestaComun() {
    }

    public boolean isExito() {
        return exito;
    }

    public void setExito(boolean exito) {
        this.exito = exito;
    }

    public String getMensajeError() {
        return mensajeError;
    }

    public void setMensajeError(String mensajeError) {
        this.mensajeError = mensajeError;
    }

    public String getPila() {
        return pila;
    }

    public void setPila(String pila) {
        this.pila = pila;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }
}
