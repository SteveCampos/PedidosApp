package energigas.apps.systemstrategy.energigas.entities;

/**
 * Created by kelvi on 20/07/2016.
 */

public class Costs {
    private String enRuta;
    private String descripcion;
    private double total;

    public Costs() {
    }

    public Costs(String enRuta, String descripcion, double total) {
        this.enRuta = enRuta;
        this.descripcion = descripcion;
        this.total = total;
    }

    public String getEnRuta() {
        return enRuta;
    }

    public void setEnRuta(String enRuta) {
        this.enRuta = enRuta;
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
