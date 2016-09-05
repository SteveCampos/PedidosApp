package energigas.apps.systemstrategy.energigas.entities;


import com.orm.SugarRecord;

import java.util.List;

/**
 * Created by kelvi on 15/08/2016.
 */

public class BEGeneral extends SugarRecord {

    private List<Concepto> itemsConceptos;
    private List<Estado> itemsEstados;
    private List<UbicacionGeoreferencia> itemUbigeos;

    public BEGeneral() {
    }

    public BEGeneral(List<Concepto> itemsConceptos, List<Estado> itemsEstados, List<UbicacionGeoreferencia> itemUbigeos) {
        this.itemsConceptos = itemsConceptos;
        this.itemsEstados = itemsEstados;
        this.itemUbigeos = itemUbigeos;
    }

    public List<Concepto> getItemsConceptos() {
        return itemsConceptos;
    }

    public void setItemsConceptos(List<Concepto> itemsConceptos) {
        this.itemsConceptos = itemsConceptos;
    }

    public List<Estado> getItemsEstados() {
        return itemsEstados;
    }

    public void setItemsEstados(List<Estado> itemsEstados) {
        this.itemsEstados = itemsEstados;
    }

    public List<UbicacionGeoreferencia> getItemUbigeos() {
        return itemUbigeos;
    }

    public void setItemUbigeos(List<UbicacionGeoreferencia> itemUbigeos) {
        this.itemUbigeos = itemUbigeos;
    }

}
