package energigas.apps.systemstrategy.energigas.entities;

/**
 * Created by kelvi on 20/07/2016.
 */

public class Costs {
    private String descripcion;
    private double total;

    public Costs() {
    }

    public Costs(String descripcion, double total) {
        this.descripcion = descripcion;
        this.total = total;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }


}
