package energigas.apps.systemstrategy.energigas.entities;

import java.util.ArrayList;
import java.util.List;

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

    public List<Inventory> instanceListInventory(){
        inventoriesList = new ArrayList<Inventory>();
        return inventoriesList;

    }

    public   List<Inventory> getInventoryList() {

        inventoriesList.add(new Inventory("Gas licuado de petroleo", 2000, 500, 1500));
        inventoriesList.add(new Inventory("Gas natural vehicular", 2000, 500, 1500));

        return inventoriesList;
    }
}
