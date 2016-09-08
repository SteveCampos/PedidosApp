package energigas.apps.systemstrategy.energigas.entities;

import com.orm.SugarRecord;

/**
 * Created by kelvi on 10/08/2016.
 */

public class ProductoUnidad extends SugarRecord {
    private int puId;

    private int proId;

    private int unId;

    private boolean unidadBase;

    public ProductoUnidad() {
    }

    public ProductoUnidad(int puId, int proId, int unId, boolean unidadBase) {
        this.puId = puId;
        this.proId = proId;
        this.unId = unId;
        this.unidadBase = unidadBase;
    }

    public int getPuId() {
        return puId;
    }

    public void setPuId(int puId) {
        this.puId = puId;
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

    public boolean isUnidadBase() {
        return unidadBase;
    }

    public void setUnidadBase(boolean unidadBase) {
        this.unidadBase = unidadBase;
    }
}
