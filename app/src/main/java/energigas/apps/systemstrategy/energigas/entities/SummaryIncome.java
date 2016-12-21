package energigas.apps.systemstrategy.energigas.entities;

/**
 * Created by kelvi on 16/12/2016.
 */

public class SummaryIncome {

    private String concepto;
    private String cantidad;
    private String emitidos;
    private String pagados;
    private String cobrados;

    public SummaryIncome() {
    }

    public SummaryIncome(String concepto, String cantidad, String emitidos, String pagados, String cobrados) {
        this.concepto = concepto;
        this.cantidad = cantidad;
        this.emitidos = emitidos;
        this.pagados = pagados;
        this.cobrados = cobrados;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getEmitidos() {
        return emitidos;
    }

    public void setEmitidos(String emitidos) {
        this.emitidos = emitidos;
    }

    public String getPagados() {
        return pagados;
    }

    public void setPagados(String pagados) {
        this.pagados = pagados;
    }

    public String getCobrados() {
        return cobrados;
    }

    public void setCobrados(String cobrados) {
        this.cobrados = cobrados;
    }
}
