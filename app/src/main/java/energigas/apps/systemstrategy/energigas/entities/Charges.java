package energigas.apps.systemstrategy.energigas.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kike on 1/08/2016.
 */

public class Charges {

    private String mSerie;
    private String mComprobante;
    private String mDate;
    private Double mTotal;

    public Charges (){

    }
    public Charges(String mSerie, String mComprobante, String mDate, Double mTotal) {
        this.mSerie = mSerie;
        this.mComprobante = mComprobante;
        this.mDate = mDate;
        this.mTotal = mTotal;
    }

    public String getmSerie() {
        return mSerie;
    }

    public void setmSerie(String mSerie) {
        this.mSerie = mSerie;
    }

    public String getmComprobante() {
        return mComprobante;
    }

    public void setmComprobante(String mComprobante) {
        this.mComprobante = mComprobante;
    }

    public String getmDate() {
        return mDate;
    }

    public void setmDate(String mDate) {
        this.mDate = mDate;
    }

    public Double getmTotal() {
        return mTotal;
    }

    public void setmTotal(Double mTotal) {
        this.mTotal = mTotal;
    }


    public static List<Charges> getCharges() {
        List<Charges> chargesList = new ArrayList<>();
        chargesList.add(new Charges("00000024","F001","10/07/2016",120.0));
        chargesList.add(new Charges("00000148","F200","11/07/2016",140.0));
        chargesList.add(new Charges("00001000","B800","12/07/2016",160.0));
        return chargesList;
    }



}
