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

    //    public static List<Charges> getListCharges() {
//        List<Charges> chargesList = new ArrayList<>();
//
//        Charges charges = new Charges(
//                new Station("Agropacking Export S.A.", "car. panamericana norte km. 1076", "2.0", "1.4 km", "externo"),
//                "01/08/2016",
//                26.000,
//                "Boleta"
//        );
//        for (int i = 0; i < 3; i++) {
//            chargesList.add(charges);
//        }
//        return chargesList;
//
//    }

    public static List<Charges> getCharges() {
        List<Charges> chargesList = new ArrayList<>();
        chargesList.add(new Charges("F0001","ABCDEF","10/02/16",120.0));
        chargesList.add(new Charges("F0002","ABCDEF","11/02/16",140.0));
        chargesList.add(new Charges("F0003","ABCDEF","12/02/16",160.0));
        return chargesList;

    }


}
