package energigas.apps.systemstrategy.energigas.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kikerojas on 30/07/2016.
 */

public class Expenses {

    private String mType;
    private String mDate;
    private String mDescription;
    private Double mSbTotal;
    private Double mTotal;

    public Expenses(String mType, String mDate, String mDescription, Double mSbTotal, Double mTotal) {
        this.mType = mType;
        this.mDate = mDate;
        this.mDescription = mDescription;
        this.mSbTotal = mSbTotal;
        this.mTotal = mTotal;
    }

    public String getmType() {
        return mType;
    }

    public void setmType(String mType) {
        this.mType = mType;
    }

    public String getmDate() {
        return mDate;
    }

    public void setmDate(String mDate) {
        this.mDate = mDate;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public Double getmSbTotal() {
        return mSbTotal;
    }

    public void setmSbTotal(Double mSbTotal) {
        this.mSbTotal = mSbTotal;
    }

    public Double getmTotal() {
        return mTotal;
    }

    public void setmTotal(Double mTotal) {
        this.mTotal = mTotal;
    }

    public static List<Expenses> getListExpenses(){
        List<Expenses> expenses = new ArrayList<>();
        expenses.add(new Expenses("Desayuno","28/07/2016","Quinua Power",10.10,20.40));
        expenses.add(new Expenses("Almuerzo","28/07/2016","Arroz con Pollo",20.10,40.40));
        expenses.add(new Expenses("Cena","28/07/2016","Pan con Pollo",30.10,60.40));
        return expenses;
    }
}
