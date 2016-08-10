package energigas.apps.systemstrategy.energigas.entities;

/**
 * Created by kelvi on 10/08/2016.
 */

public class DescuentoProductoUnidad {

    private int descuentoId;

    private int puId;

    public DescuentoProductoUnidad() {
    }

    public DescuentoProductoUnidad(int descuentoId, int puId) {
        this.descuentoId = descuentoId;
        this.puId = puId;
    }

    public int getDescuentoId() {
        return descuentoId;
    }

    public void setDescuentoId(int descuentoId) {
        this.descuentoId = descuentoId;
    }

    public int getPuId() {
        return puId;
    }

    public void setPuId(int puId) {
        this.puId = puId;
    }
}
