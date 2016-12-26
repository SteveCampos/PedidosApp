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
    private List<Producto> itemsProductos;
    private List<Unidad> itemsUnidades;
    private List<ProductoUnidad> itemsProductoUnidad;
    private List<DETipo> itemsTipos;
    private List<Proveedor> proveedoresList;


    public BEGeneral() {
    }

    public BEGeneral(List<Concepto> itemsConceptos, List<Estado> itemsEstados, List<UbicacionGeoreferencia> itemUbigeos, List<Producto> itemsProductos, List<Unidad> itemsUnidades, List<ProductoUnidad> itemsProductoUnidad, List<DETipo> itemsTipos, List<Proveedor> proveedoresList) {
        this.itemsConceptos = itemsConceptos;
        this.itemsEstados = itemsEstados;
        this.itemUbigeos = itemUbigeos;
        this.itemsProductos = itemsProductos;
        this.itemsUnidades = itemsUnidades;
        this.itemsProductoUnidad = itemsProductoUnidad;
        this.itemsTipos = itemsTipos;
        this.proveedoresList = proveedoresList;
    }

    public List<Proveedor> getProveedoresList() {
        return proveedoresList;
    }

    public void setProveedoresList(List<Proveedor> proveedoresList) {
        this.proveedoresList = proveedoresList;
    }

    public List<DETipo> getItemsTipos() {
        return itemsTipos;
    }

    public void setItemsTipos(List<DETipo> itemsTipos) {
        this.itemsTipos = itemsTipos;
    }

    public List<Producto> getItemsProductos() {
        return itemsProductos;
    }

    public void setItemsProductos(List<Producto> itemsProductos) {
        this.itemsProductos = itemsProductos;
    }

    public List<Unidad> getItemsUnidades() {
        return itemsUnidades;
    }

    public void setItemsUnidades(List<Unidad> itemsUnidades) {
        this.itemsUnidades = itemsUnidades;
    }

    public List<ProductoUnidad> getItemsProductoUnidad() {
        return itemsProductoUnidad;
    }

    public void setItemsProductoUnidad(List<ProductoUnidad> itemsProductoUnidad) {
        this.itemsProductoUnidad = itemsProductoUnidad;
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
