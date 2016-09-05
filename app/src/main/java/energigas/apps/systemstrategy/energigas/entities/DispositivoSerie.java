package energigas.apps.systemstrategy.energigas.entities;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

/**
 * Created by kelvi on 10/08/2016.
 */

public class DispositivoSerie extends SugarRecord {

    @Unique
    private String dmSeId;
    private int dmId;

    private int seId;

    public DispositivoSerie() {
    }

    public DispositivoSerie(String dmSeId, int dmId, int seId) {
        this.dmSeId = dmSeId;
        this.dmId = dmId;
        this.seId = seId;
    }

    public String getDmSeId() {
        return dmSeId;
    }

    public void setDmSeId(String dmSeId) {
        this.dmSeId = dmSeId;
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
