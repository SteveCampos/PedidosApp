package energigas.apps.systemstrategy.energigas.entities.fe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Steve on 22/11/2016.
 */

public class ComunicacionBaja extends DocumentoResumen {
    public List<DocumentoBaja> bajas = new ArrayList<>();
    public List<DocumentoBaja> getBajas() {
        return bajas;
    }
    public void setBajas(List<DocumentoBaja> bajas) {
        this.bajas = bajas;
    }
}
