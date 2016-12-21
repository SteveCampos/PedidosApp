package energigas.apps.systemstrategy.energigas.entities.fe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Steve on 22/11/2016.
 */

public class ResumenDiario extends DocumentoResumen {

    public List<GrupoResumen> resumenes = new ArrayList<>();

    public List<GrupoResumen> getResumenes() {
        return resumenes;
    }

    public void setResumenes(List<GrupoResumen> resumenes) {
        this.resumenes = resumenes;
    }
}
