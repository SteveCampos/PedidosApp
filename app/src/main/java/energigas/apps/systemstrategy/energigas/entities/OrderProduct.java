package energigas.apps.systemstrategy.energigas.entities;

import java.util.ArrayList;
import java.util.List;

import energigas.apps.systemstrategy.energigas.utils.Utils;

/**
 * Created by Steve on 21/07/2016.
 */

public class OrderProduct {

    private Product product;
    private long idSqlite;
    private String idRemoto;
    private double quantityProduct;
    private List<OrderDispatch> dispatchList = new ArrayList<>();

    public OrderProduct() {
    }

    public OrderProduct(Product product, long idSqlite, String idRemoto, double quantityProduct, List<OrderDispatch> dispatchList) {
        this.product = product;
        this.idSqlite = idSqlite;
        this.idRemoto = idRemoto;
        this.quantityProduct = quantityProduct;
        this.dispatchList = dispatchList;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public long getIdSqlite() {
        return idSqlite;
    }

    public void setIdSqlite(long idSqlite) {
        this.idSqlite = idSqlite;
    }

    public String getIdRemoto() {
        return idRemoto;
    }

    public void setIdRemoto(String idRemoto) {
        this.idRemoto = idRemoto;
    }

    public double getQuantityProduct() {
        return quantityProduct;
    }

    public void setQuantityProduct(double quantityProduct) {
        this.quantityProduct = quantityProduct;
    }

    public List<OrderDispatch> getDispatchList() {
        return dispatchList;
    }

    public void setDispatchList(List<OrderDispatch> dispatchList) {
        this.dispatchList = dispatchList;
    }

    public String getProductOrderedTitle(){
        return (getProduct().getName() + " " + Utils.formatDouble(getQuantityProduct()) + " " + getProduct().getUnidadMedida()).toUpperCase();
    }

    public static List<OrderProduct> getList(){
        List<OrderProduct> orderProducts = new ArrayList<>();

        Product glp = new Product(1, "1000", "GLP", " Gal");
        Product gnv = new Product(2, "1001", "GNV", " Gal");

        orderProducts.add(new OrderProduct(glp, 1, "2000", 500.00, OrderDispatch.getList()));
        orderProducts.add(new OrderProduct(gnv, 2, "2001", 500.00, OrderDispatch.getList()));

        return orderProducts;
    }

}
