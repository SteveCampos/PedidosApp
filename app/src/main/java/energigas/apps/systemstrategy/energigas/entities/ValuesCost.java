package energigas.apps.systemstrategy.energigas.entities;

/**
 * Created by kelvi on 02/11/2016.
 */

public class ValuesCost {
    double precioUnitario;
    double subTotal;
    double igv;
    double total;

    public ValuesCost() {
    }

    public ValuesCost(double precioUnitario, double subTotal, double igv, double total) {
        this.precioUnitario = precioUnitario;
        this.subTotal = subTotal;
        this.igv = igv;
        this.total = total;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public double getIgv() {
        return igv;
    }

    public void setIgv(double igv) {
        this.igv = igv;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
