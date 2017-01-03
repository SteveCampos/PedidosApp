package energigas.apps.systemstrategy.energigas.entities;

/**
 * Created by kelvi on 02/01/2017.
 */

public class EstablecimientoOrden {

    private Establecimiento establecimiento;
    private int orden;

    public EstablecimientoOrden() {
    }

    public EstablecimientoOrden(Establecimiento establecimiento, int orden) {
        this.establecimiento = establecimiento;
        this.orden = orden;
    }

    public Establecimiento getEstablecimiento() {
        return establecimiento;
    }

    public void setEstablecimiento(Establecimiento establecimiento) {
        this.establecimiento = establecimiento;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }
}
