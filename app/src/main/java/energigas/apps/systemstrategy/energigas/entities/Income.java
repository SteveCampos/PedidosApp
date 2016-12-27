package energigas.apps.systemstrategy.energigas.entities;

/**
 * Created by kelvi on 20/07/2016.
 */

public class Income {

    private int tipo;
    private String descripcion;
    private double cantidad;
    private double cobrado;
    private double emitido;
    private double pagado;
    private String descripcionComprobante;

    public Income() {
    }

    public Income(int tipo, String descripcion, double cantidad, double cobrado, double emitido, double pagado) {
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.cobrado = cobrado;
        this.emitido = emitido;
        this.pagado = pagado;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public double getCobrado() {
        return cobrado;
    }

    public void setCobrado(double cobrado) {
        this.cobrado = cobrado;
    }

    public double getEmitido() {
        return emitido;
    }

    public void setEmitido(double emitido) {
        this.emitido = emitido;
    }

    public double getPagado() {
        return pagado;
    }

    public void setPagado(double pagado) {
        this.pagado = pagado;
    }
}
