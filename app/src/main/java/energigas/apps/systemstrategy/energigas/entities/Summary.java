package energigas.apps.systemstrategy.energigas.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kelvi on 20/07/2016.
 */

public class Summary {
    private double saldoInicial;
    private double ingresosTotales;
    private double gastos;
    private double efectivoRendir;
    private Costs costs;
    private Income income;

private int type;

    public Summary(double saldoInicial, double ingresosTotales, double gastos, double efectivoRendir, Costs costs, Income income, int type) {
        this.saldoInicial = saldoInicial;
        this.ingresosTotales = ingresosTotales;
        this.gastos = gastos;
        this.efectivoRendir = efectivoRendir;
        this.costs = costs;
        this.income = income;
        this.type = type;
    }

    public Summary() {
    }

    public double getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(double saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    public double getIngresosTotales() {
        return ingresosTotales;
    }

    public void setIngresosTotales(double ingresosTotales) {
        this.ingresosTotales = ingresosTotales;
    }

    public double getGastos() {
        return gastos;
    }

    public void setGastos(double gastos) {
        this.gastos = gastos;
    }

    public double getEfectivoRendir() {
        return efectivoRendir;
    }

    public void setEfectivoRendir(double efectivoRendir) {
        this.efectivoRendir = efectivoRendir;
    }

    public Costs getCosts() {
        return costs;
    }

    public void setCosts(Costs costs) {
        this.costs = costs;
    }

    public Income getIncome() {
        return income;
    }

    public void setIncome(Income income) {
        this.income = income;
    }

    public static List<Summary> getListSummary (){
        List<Summary> summaries = new ArrayList<Summary>();
        summaries.add(new Summary(0.0,0.0,0.0,0,null,null,0));
        summaries.add(new Summary(0.0,0.0,0.0,1,null,null,1));
        summaries.add(new Summary(0.0,0.0,0.0,1,null,null,2));
        return  summaries;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
