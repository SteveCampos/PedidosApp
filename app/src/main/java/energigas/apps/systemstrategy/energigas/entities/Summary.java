package energigas.apps.systemstrategy.energigas.entities;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import energigas.apps.systemstrategy.energigas.utils.Constants;
import energigas.apps.systemstrategy.energigas.utils.Session;
import energigas.apps.systemstrategy.energigas.utils.Utils;

/**
 * Created by kelvi on 20/07/2016.
 */

public class Summary {
    private double saldoInicial;
    private double ingresosTotales;
    private double gastos;
    private double efectivoRendir;
    private List<Costs> costsList;
    private static final String TAG = "Summary";

    private HashMap<Concepto, List<Double>> incomeList;
    private HashMap<String, List<Income>> incomeListHashMap;

    private int type;
    private List<SummaryIncome> summaryIncomeList;

    public Summary(double saldoInicial, double ingresosTotales, double gastos, double efectivoRendir, List<Costs> costsList, HashMap<Concepto, List<Double>> incomeList, HashMap<String, List<Income>> incomeListHashMap, int type, List<SummaryIncome> summaryIncomeList) {
        this.saldoInicial = saldoInicial;
        this.ingresosTotales = ingresosTotales;
        this.gastos = gastos;
        this.efectivoRendir = efectivoRendir;
        this.costsList = costsList;
        this.incomeList = incomeList;
        this.incomeListHashMap = incomeListHashMap;
        this.type = type;
        this.summaryIncomeList = summaryIncomeList;
    }

    public List<Costs> getCostsList() {
        return costsList;
    }

    public void setCostsList(List<Costs> costsList) {
        this.costsList = costsList;
    }

    public HashMap<Concepto, List<Double>> getIncomeList() {
        return incomeList;
    }

    public void setIncomeList(HashMap<Concepto, List<Double>> incomeList) {
        this.incomeList = incomeList;
    }

    public List<SummaryIncome> getSummaryIncomeList() {
        return summaryIncomeList;
    }

    public void setSummaryIncomeList(List<SummaryIncome> summaryIncomeList) {
        this.summaryIncomeList = summaryIncomeList;
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

    public HashMap<String, List<Income>> getIncomeListHashMap() {
        return incomeListHashMap;
    }

    public void setIncomeListHashMap(HashMap<String, List<Income>> incomeListHashMap) {
        this.incomeListHashMap = incomeListHashMap;
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


    public HashMap<Concepto, List<Double>> getIncome() {
        return incomeList;
    }

    public void setIncome(HashMap<Concepto, List<Double>> incomeList) {
        this.incomeList = incomeList;
    }

    public static Summary getListSummary(Context context) {
        List<Summary> summaries = new ArrayList<Summary>();

        CajaLiquidacion cajaLiquidacion = CajaLiquidacion.getCajaLiquidacion(Session.getCajaLiquidacion(context).getLiqId() + "");

        List<CajaGasto> cajaGastos = CajaGasto.getListCajaGastosLiquidacion(cajaLiquidacion.getLiqId() + "");


        List<ComprobanteVenta> comprobanteVentaList = ComprobanteVenta.getComprobanteByLiquidacion(cajaLiquidacion.getLiqId() + "");
        List<Costs> costsList = new ArrayList<>();

        double importeGastos = 0.00;

        for (CajaGasto cajaGasto : cajaGastos) {
            InformeGasto informeGasto = InformeGasto.getInformeGasto(cajaGasto.getCajGasId() + "");
            Concepto concepto = Concepto.getConceptoById(informeGasto.getCatTipoGastoId() + "");
            Costs costs = new Costs(concepto.getDescripcion(), informeGasto.getReferencia(), Utils.formatDoubleNumber(cajaGasto.getImporte()));
            costsList.add(costs);
            importeGastos = importeGastos + cajaGasto.getImporte();
        }
        double ingresosImporte = 0.00;

        HashMap<Integer, Concepto> conceptoHashMap = new HashMap<>();
        List<CajaPago> cajaPagos = new ArrayList<>();

        List<Double[]> doubleListFacturas = new ArrayList<>();
        List<Double[]> doubleListBoletas = new ArrayList<>();
        HashMap<String, List<Income>> conceptoListHashMap = new HashMap<>();


        List<Income> incomesBoletasEmitidas = new ArrayList<>();
        List<Income> incomesFacturasEmitidas = new ArrayList<>();

        for (ComprobanteVenta comprobanteVenta : comprobanteVentaList) {

            if (comprobanteVenta.getTipoComprobanteId() == Constants.TIPO_DOCUMENTO_BOLETA) {

                Double[] ingresos = new Double[2];
                ingresos[0] = comprobanteVenta.getTotal();
                ingresos[1] = Double.parseDouble(comprobanteVenta.getTipoComprobanteId() + "");
                doubleListBoletas.add(ingresos);
                //incomesBoletasEmitidas.add()

            }

            if (comprobanteVenta.getTipoComprobanteId() == Constants.TIPO_DOCUMENTO_FACTURA) {


                Double[] ingresos = new Double[2];
                ingresos[0] = comprobanteVenta.getTotal();
                ingresos[1] = Double.parseDouble(comprobanteVenta.getTipoComprobanteId() + "");
                doubleListFacturas.add(ingresos);
            }


            ingresosImporte = ingresosImporte + comprobanteVenta.getTotal();
            CajaMovimiento cajaMovimiento = CajaMovimiento.getCajaMovimiento(comprobanteVenta.getCompId() + "");

            if (cajaMovimiento != null) {
                CajaPago cajaPago = CajaPago.getCajaPago(cajaMovimiento.getCajMovId() + "");
                Concepto concepto = Concepto.getConceptoById(cajaPago.getTipoPagoId() + "");
                conceptoHashMap.put(concepto.getIdConcepto(), concepto);
                cajaPagos.add(cajaPago);
            }


        }


        conceptoListHashMap.put("BOLETAS EMITIDAS", incomesBoletasEmitidas);
        conceptoListHashMap.put("FACTURAS EMITIDAS", incomesFacturasEmitidas);


        HashMap<Concepto, List<Double>> listHashMap = new HashMap<>();

        /**Ingresos**/

        double facSumaEmitidos = 0.00;
        double facSumaPagados = 0.00;

        double bolSumaEmitidos = 0.00;
        double bolSumaPagados = 0.00;

        for (Double[] doubles : doubleListBoletas) {
            bolSumaEmitidos = bolSumaEmitidos + doubles[0];
            if (doubles[1].intValue() == Constants.CONTADO_ID) {
                bolSumaPagados = bolSumaPagados + doubles[0];
            }
        }

        for (Double[] doubles : doubleListFacturas) {
            facSumaEmitidos = facSumaEmitidos + doubles[0];
            if (doubles[1].intValue() == Constants.CONTADO_ID) {
                facSumaPagados = facSumaPagados + doubles[0];
            }
        }

        List<SummaryIncome> summaryIncomeList = new ArrayList<>();
        SummaryIncome summaryFacturasEmitidas = new SummaryIncome("Facturas Emitidas", doubleListFacturas.size() + "", Utils.formatDouble(facSumaEmitidos), Utils.formatDouble(facSumaPagados), "0.00");
        SummaryIncome summaryBoletasEmitidas = new SummaryIncome("Boletas Emitidas", doubleListBoletas.size() + "", Utils.formatDouble(bolSumaEmitidos), Utils.formatDouble(bolSumaPagados), "0.00");
        summaryIncomeList.add(summaryFacturasEmitidas);
        summaryIncomeList.add(summaryBoletasEmitidas);
        /**-----**/


        for (Concepto concepto : new ArrayList<Concepto>(conceptoHashMap.values())) {


            List<Double> ventaList = new ArrayList<>();
            for (CajaPago cajaPago : cajaPagos) {

                CajaMovimiento cajaMovimiento = CajaMovimiento.getCajaMovimientoById(cajaPago.getCajMovId() + "");

                if (cajaPago.getTipoPagoId() == concepto.getIdConcepto()) {
                    CajaComprobante cajaComprobante = CajaComprobante.getCajaComprobanteByMov(cajaMovimiento.getCajMovId() + "");
                    ComprobanteVenta comprobanteVenta = ComprobanteVenta.getComprobanteVentaId(cajaComprobante.getCompId() + "");
                    double importe = comprobanteVenta.getTotal();

                    ventaList.add(importe);


                }
            }

            listHashMap.put(concepto, ventaList);

        }


        double efectivoRendir = ingresosImporte - importeGastos;
        double saldoInicialMain = (cajaLiquidacion.getSaldoInicial() + ingresosImporte) - importeGastos;

        Summary sumaryResumen = new Summary(Utils.formatDoubleNumber(cajaLiquidacion.getSaldoInicial()),
                Utils.formatDoubleNumber(ingresosImporte),
                Utils.formatDoubleNumber(importeGastos),
                Utils.formatDoubleNumber(efectivoRendir),
                costsList,
                listHashMap, null, 0, summaryIncomeList);


        return sumaryResumen;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
