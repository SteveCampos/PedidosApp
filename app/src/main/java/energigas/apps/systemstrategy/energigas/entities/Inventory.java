package energigas.apps.systemstrategy.energigas.entities;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import energigas.apps.systemstrategy.energigas.utils.Session;

/**
 * Created by kelvi on 20/07/2016.
 */

public class Inventory {
    private String nombre;
    private double cantidadInicial;
    private double cantidadDespachada;
    private double cantidadFinal;
    private double cantidadOrden;
    private List<Inventory> inventoriesList;

    public Inventory() {
    }

    public double getCantidadOrden() {
        return cantidadOrden;
    }

    public void setCantidadOrden(double cantidadOrden) {
        this.cantidadOrden = cantidadOrden;
    }

    public List<Inventory> getInventoriesList() {
        return inventoriesList;
    }

    public void setInventoriesList(List<Inventory> inventoriesList) {
        this.inventoriesList = inventoriesList;
    }

    public Inventory(String nombre, double cantidadInicial, double cantidadDespachada, double cantidadFinal, double cantidadOrden, List<Inventory> inventoriesList) {
        this.nombre = nombre;
        this.cantidadInicial = cantidadInicial;
        this.cantidadDespachada = cantidadDespachada;
        this.cantidadFinal = cantidadFinal;
        this.cantidadOrden = cantidadOrden;
        this.inventoriesList = inventoriesList;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getCantidadInicial() {
        return cantidadInicial;
    }

    public void setCantidadInicial(double cantidadInicial) {
        this.cantidadInicial = cantidadInicial;
    }

    public double getCantidadDespachada() {
        return cantidadDespachada;
    }

    public void setCantidadDespachada(double cantidadDespachada) {
        this.cantidadDespachada = cantidadDespachada;
    }

    public double getCantidadFinal() {
        return cantidadFinal;
    }

    public void setCantidadFinal(double cantidadFinal) {
        this.cantidadFinal = cantidadFinal;
    }

    public List<Inventory> instanceListInventory() {
        inventoriesList = new ArrayList<Inventory>();
        return inventoriesList;

    }

    public static List<Inventory> getInventoryList(Context context) {

        CajaLiquidacion cajaLiquidacion = CajaLiquidacion.getCajaLiquidacion(Session.getCajaLiquidacion(context).getLiqId() + "");
        List<Producto> productoList = Producto.getAllProducto();
        Log.d("InventarioFragment", productoList.size() + "");
        Almacen almacen = Almacen.getAlmacenByUser(cajaLiquidacion.getUsuarioId() + "");
        List<Inventory> inventoryList = new ArrayList<>();

        for (Producto producto : productoList) {
            Log.d("InventarioFragment", producto.getProId() + "-" + almacen.getProductoId());
            if (producto.getProId() == almacen.getProductoId()) {


                double cantidadDespachada = 0.0;
                for (Despacho despacho : Despacho.find(Despacho.class, "liq_Id=?", new String[]{cajaLiquidacion.getLiqId() + ""})) {
                    cantidadDespachada = cantidadDespachada + despacho.getCantidadDespachada();
                }
                double cantidadOrdenCargo = 0.0;
                //OrdenCargo.find(OrdenCargo.class, "", new String[]{""})
                for (OrdenCargo ordenCargo : OrdenCargo.listAll(OrdenCargo.class)) {
                    cantidadOrdenCargo = cantidadOrdenCargo + ordenCargo.getCantidadTransformada();
                }
                double cantidadFinal = cantidadOrdenCargo - cantidadDespachada;
                Inventory inventory = new Inventory(producto.getNombre(), cajaLiquidacion.getStockInicial(), cantidadDespachada, cantidadFinal, cantidadOrdenCargo, null);
                inventoryList.add(inventory);
            }
        }


     /*   inventoriesList.add(new Inventory("Gas licuado de petroleo", 2000, 500, 1500));
        inventoriesList.add(new Inventory("Gas natural vehicular", 2000, 500, 1500));*/

        return inventoryList;
    }
}
