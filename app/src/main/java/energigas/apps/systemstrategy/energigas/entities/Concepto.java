package energigas.apps.systemstrategy.energigas.entities;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

/**
 * Created by kelvi on 10/08/2016.
 */

public class Concepto extends SugarRecord {
    @Unique
    private int idConcepto;

    private String objeto;

    private String concepto;

    private String descripcion;

    private int estado;

    private int empresaId;

    public Concepto() {
    }

    public Concepto(int idConcepto, String objeto, String concepto, String descripcion, int estado, int empresaId) {
        this.idConcepto = idConcepto;
        this.objeto = objeto;
        this.concepto = concepto;
        this.descripcion = descripcion;
        this.estado = estado;
        this.empresaId = empresaId;
    }
}
