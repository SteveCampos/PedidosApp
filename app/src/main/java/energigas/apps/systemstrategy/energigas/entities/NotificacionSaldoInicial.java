package energigas.apps.systemstrategy.energigas.entities;

/**
 * Created by kelvi on 27/01/2017.
 */

public class NotificacionSaldoInicial {
    private Long liqId;
    private double saldoInicial;

    public NotificacionSaldoInicial() {
    }

    public NotificacionSaldoInicial(Long liqId, double saldoInicial) {
        this.liqId = liqId;
        this.saldoInicial = saldoInicial;
    }

    public Long getLiqId() {
        return liqId;
    }

    public void setLiqId(Long liqId) {
        this.liqId = liqId;
    }

    public double getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(double saldoInicial) {
        this.saldoInicial = saldoInicial;
    }
}
