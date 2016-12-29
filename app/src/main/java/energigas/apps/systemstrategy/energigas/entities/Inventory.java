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
    public String nombre;
    public double cantidadInicial;
    public double cantidadVendida;
    public double cantidadFinal;
    List<Inventory> inventoriesList;

    public Inventory() {
    }

    public Inventory(String nombre, double cantidadInicial, double cantidadVendida, double cantidadFinal) {
        this.nombre = nombre;
        this.cantidadInicial = cantidadInicial;
        this.cantidadVendida = cantidadVendida;
        this.cantidadFinal = cantidadFinal;
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

    public double getCantidadVendida() {
        return cantidadVendida;
    }

    public void setCantidadVendida(double cantidadVendida) {
        this.cantidadVendida = cantidadVendida;
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
                double stockVenta = (cajaLiquidacion.getStockInicial() + almacen.getStockActual()) - almacen.getStockActual();
                Inventory inventory = new Inventory(producto.getNombre(), cajaLiquidacion.getStockInicial() + almacen.getStockActual(), stockVenta, almacen.getStockActual());
                inventoryList.add(inventory);
            }
        }


     /*   inventoriesList.add(new Inventory("Gas licuado de petroleo", 2000, 500, 1500));
        inventoriesList.add(new Inventory("Gas natural vehicular", 2000, 500, 1500));*/

        return inventoryList;
    }
}
