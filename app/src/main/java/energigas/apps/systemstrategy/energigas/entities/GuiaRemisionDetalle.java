package energigas.apps.systemstrategy.energigas.entities;

/**
 * Created by kelvi on 10/08/2016.
 */

public class GuiaRemisionDetalle {

    private long greId;

    private int grdeId;

    private int proId;

    private int unId;

    private double cantidad;

    public GuiaRemisionDetalle() {
    }

    public GuiaRemisionDetalle(long greId, int grdeId, int proId, int unId, double cantidad) {
        this.greId = greId;
        this.grdeId = grdeId;
        this.proId = proId;
        this.unId = unId;
        this.cantidad = cantidad;
    }

    public long getGreId() {
        return greId;
    }

    public void setGreId(long greId) {
        this.greId = greId;
    }

    public int getGrdeId() {
        return grdeId;
    }

    public void setGrdeId(int grdeId) {
        this.grdeId = grdeId;
    }

    public int getProId() {
        return proId;
    }

    public void setProId(int proId) {
        this.proId = proId;
    }

    public int getUnId() {
        return unId;
    }

    public void setUnId(int unId) {
        this.unId = unId;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }
}
