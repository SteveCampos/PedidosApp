package energigas.apps.systemstrategy.energigas.entities;

/**
 * Created by Steve on 21/07/2016.
 */

public class OrderDispatch {



    private Product product;
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

    public OrderDispatch(Product product, double quantityDisptach, String serieTanque, String nroTransporte, double percentStart, double percentEnd, long date, long startTime, long endTime, double startCounter, double endCounter) {
        this.product = product;
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
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
}
