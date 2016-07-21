package energigas.apps.systemstrategy.energigas.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Steve on 21/07/2016.
 */

public class Order {
    private Station station;
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

    public Order(Station station, long idSqlite, String idRemoto, long startTime, long endTime, long dispatchTime, long orderDate, long dispatchDate, List<OrderProduct> productList) {
        this.station = station;
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
}
