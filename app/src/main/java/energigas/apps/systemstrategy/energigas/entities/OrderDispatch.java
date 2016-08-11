package energigas.apps.systemstrategy.energigas.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import energigas.apps.systemstrategy.energigas.utils.Utils;

/**
 * Created by Steve on 21/07/2016.
 */

public class OrderDispatch {


    private double quantityDisptach;

    //INFORMACIÓN SOBRE EL TANQUE,
    //SI SE PUEDE AGREGAR UN OBJETO TANQUE
    private String serieTanque;
    private String nroTransporte;


    private double percentStart;
    private double percentEnd;

    private long date;
    private long startTime;
    private long endTime;
    private double startCounter;
    private double endCounter;

    public OrderDispatch() {
    }

    public OrderDispatch(double quantityDisptach, String serieTanque, String nroTransporte, double percentStart, double percentEnd, long date, long startTime, long endTime, double startCounter, double endCounter) {
        this.quantityDisptach = quantityDisptach;
        this.serieTanque = serieTanque;
        this.nroTransporte = nroTransporte;
        this.percentStart = percentStart;
        this.percentEnd = percentEnd;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.startCounter = startCounter;
        this.endCounter = endCounter;
    }

    public double getQuantityDisptach() {
        return quantityDisptach;
    }

    public void setQuantityDisptach(double quantityDisptach) {
        this.quantityDisptach = quantityDisptach;
    }

    public String getSerieTanque() {
        return serieTanque;
    }

    public void setSerieTanque(String serieTanque) {
        this.serieTanque = serieTanque;
    }

    public String getNroTransporte() {
        return nroTransporte;
    }

    public void setNroTransporte(String nroTransporte) {
        this.nroTransporte = nroTransporte;
    }

    public double getPercentStart() {
        return percentStart;
    }

    public void setPercentStart(double percentStart) {
        this.percentStart = percentStart;
    }

    public double getPercentEnd() {
        return percentEnd;
    }

    public void setPercentEnd(double percentEnd) {
        this.percentEnd = percentEnd;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
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

    public double getStartCounter() {
        return startCounter;
    }

    public void setStartCounter(double startCounter) {
        this.startCounter = startCounter;
    }

    public double getEndCounter() {
        return endCounter;
    }

    public void setEndCounter(double endCounter) {
        this.endCounter = endCounter;
    }

    public String getTitleTank(){
        return Utils.capitalize(getSerieTanque()) + " - " + getNroTransporte().toUpperCase();
    }


    public static List<OrderDispatch> getList(){
        List<OrderDispatch> orderDispatches = new ArrayList<>();
        long time = new Date().getTime();
        OrderDispatch orderDispatch = new OrderDispatch(100.00, "TANQUE 100", "10056235832", 55.00, 60.00, time, time, time +6000, 500.00, 600.00);
        OrderDispatch orderDispatch1 = new OrderDispatch(100.00, "TANQUE 201", "10055621122", 60.00, 70.00, time, time, time +6000, 300.00, 400.00);
        OrderDispatch orderDispatch2 = new OrderDispatch(300.00, "TANQUE TB09", "20053312962", 45.00, 75.00, time, time, time +6000, 900.00, 1200.00);

        orderDispatches.add(orderDispatch);
        orderDispatches.add(orderDispatch1);
        orderDispatches.add(orderDispatch2);

        return orderDispatches;
    }
}
