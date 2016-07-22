package energigas.apps.systemstrategy.energigas.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import energigas.apps.systemstrategy.energigas.utils.Utils;

/**
 * Created by Steve on 21/07/2016.
 */

public class Order {
    private Station station;
    private int priority;
    private long idSqlite;
    private String idRemoto;
    private long startTime;
    private long endTime;
    private long dispatchTime;

    private long orderDate;
    private long dispatchDate;

    private List<OrderProduct> productList = new ArrayList<>();

    public Order() {
    }

    public Order(Station station, int priority, long idSqlite, String idRemoto, long startTime, long endTime, long dispatchTime, long orderDate, long dispatchDate, List<OrderProduct> productList) {
        this.station = station;
        this.priority = priority;
        this.idSqlite = idSqlite;
        this.idRemoto = idRemoto;
        this.startTime = startTime;
        this.endTime = endTime;
        this.dispatchTime = dispatchTime;
        this.orderDate = orderDate;
        this.dispatchDate = dispatchDate;
        this.productList = productList;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
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

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public long getDispatchTime() {
        return dispatchTime;
    }

    public void setDispatchTime(long dispatchTime) {
        this.dispatchTime = dispatchTime;
    }

    public long getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(long orderDate) {
        this.orderDate = orderDate;
    }

    public long getDispatchDate() {
        return dispatchDate;
    }

    public void setDispatchDate(long dispatchDate) {
        this.dispatchDate = dispatchDate;
    }

    public List<OrderProduct> getProductList() {
        return productList;
    }

    public void setProductList(List<OrderProduct> productList) {
        this.productList = productList;
    }


    public String getProductsName(){
        String productsName = "";

        List<OrderProduct> orderProducts =  getProductList();

        int i= 0;
        int size = orderProducts.size();
        for (OrderProduct product: orderProducts) {
            i++;
            productsName += Utils.capitalize(product.getProduct().getName()) + " " + Utils.formatDouble(product.getQuantityProduct()) + " "+ product.getProduct().getUnidadMedida();
            if (i!=size){
                productsName += "\n";
            }
        }

        return productsName;
    }

    public static List<Order> getOrders() {
        List<Order> listOrders = new ArrayList<>();

        long time = new Date().getTime();

        Product glp = new Product(1, "1000", "GLP", "GALONES");
        Product gnv = new Product(2, "1001", "GNV", "LITROS");

        List<OrderDispatch> orderDispatches = new ArrayList<>();
        orderDispatches.add(new OrderDispatch(100.00, "TANQUE 100", "0001261796", 55.00, 79.00, time, time, time, 199.1, 439.1 ));
        orderDispatches.add(new OrderDispatch(200.00, "TANQUE 200", "0001261796", 55.00, 79.00, time, time, time, 199.1, 439.1 ));


        List<OrderProduct> orderProducts = new ArrayList<>();
        orderProducts.add(new OrderProduct(glp, 1, "2000", 300, orderDispatches));
        orderProducts.add(new OrderProduct(gnv, 2, "2001", 300, orderDispatches));


        Order order = new Order(
                new Station("Agropacking Export S.A.","car. panamericana norte km. 1076","2.0","1.4 km","externo"),
                1,
                1,
                "3000",
                time,
                time,
                time,
                time,
                time,
                orderProducts
        );


        for (int i= 0; i< 20; i++){
            listOrders.add(order);
        }

        return listOrders;
    }


}
