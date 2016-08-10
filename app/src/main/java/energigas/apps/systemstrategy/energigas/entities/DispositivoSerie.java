package energigas.apps.systemstrategy.energigas.entities;

/**
 * Created by kelvi on 10/08/2016.
 */

public class DispositivoSerie {

    private int dmId;

    private int seId;

    public DispositivoSerie() {
    }

    public DispositivoSerie(int dmId, int seId) {
        this.dmId = dmId;
        this.seId = seId;
    }

    public int getDmId() {
        return dmId;
    }

    public void setDmId(int dmId) {
        this.dmId = dmId;
    }

    public int getSeId() {
        return seId;
    }

    public void setSeId(int seId) {
        this.seId = seId;
    }
}
