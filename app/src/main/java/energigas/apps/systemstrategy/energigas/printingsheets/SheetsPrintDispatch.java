package energigas.apps.systemstrategy.energigas.printingsheets;

import com.sewoo.jpos.POSPrinterService;
import com.sewoo.jpos.command.CPCL;
import com.sewoo.jpos.command.ESCPOS;
import com.sewoo.jpos.request.RequestQueue;

import java.text.DecimalFormat;

import energigas.apps.systemstrategy.energigas.entities.Almacen;
import energigas.apps.systemstrategy.energigas.entities.BeDocElectronico;
import energigas.apps.systemstrategy.energigas.entities.CajaLiquidacion;
import energigas.apps.systemstrategy.energigas.entities.Cliente;
import energigas.apps.systemstrategy.energigas.entities.ComprobanteVenta;
import energigas.apps.systemstrategy.energigas.entities.ComprobanteVentaDetalle;
import energigas.apps.systemstrategy.energigas.entities.Costs;
import energigas.apps.systemstrategy.energigas.entities.Despacho;
import energigas.apps.systemstrategy.energigas.entities.Dispatch;
import energigas.apps.systemstrategy.energigas.entities.Establecimiento;
import energigas.apps.systemstrategy.energigas.entities.Persona;
import energigas.apps.systemstrategy.energigas.entities.Producto;
import energigas.apps.systemstrategy.energigas.entities.Summary;
import energigas.apps.systemstrategy.energigas.entities.SummaryIncome;
import energigas.apps.systemstrategy.energigas.entities.Usuario;
import energigas.apps.systemstrategy.energigas.entities.Vehiculo;
import energigas.apps.systemstrategy.energigas.utils.Constants;
import energigas.apps.systemstrategy.energigas.utils.Session;
import energigas.apps.systemstrategy.energigas.utils.Utils;
import jpos.JposException;
import jpos.POSPrinterConst;

/**
 * Created by kelvi on 26/07/2016.
 */

public class SheetsPrintDispatch {

    private static String lineas = "------------------------------------------------------".substring(0, 48);
    private POSPrinterService posPtr;
    private CPCL cpclPtr;
    private RequestQueue rq;
    private static char ESC = ESCPOS.ESC;
    private static char LF = ESCPOS.LF;
    private Double igv;

    public SheetsPrintDispatch() {
        posPtr = new POSPrinterService();
        cpclPtr = new CPCL();
        rq = RequestQueue.getInstance();
    }


    public void printNowComprobante(Cliente cliente, ComprobanteVenta comprobanteVenta, Usuario usuario, BeDocElectronico beDocElectronico) {
        String codigoVenta = beDocElectronico.getResumenFirma();
        String url = "www.energigas.com";
        DecimalFormat df = new DecimalFormat("#.00");
        String tipoDocumento = "FACTURA ELECTRONICA";
        if (comprobanteVenta.getTipoComprobanteId() != Constants.TIPO_DOCUMENTO_FACTURA) {
            tipoDocumento = "BOLETA ELECTRONICA";
        }
        igv = Double.parseDouble(Session.getConceptoIGV().getDescripcion());

        String textTipoVenta = "VENTA AL CONTADO";
        if (comprobanteVenta.getPlanPago() != null) {
            textTipoVenta = "VENTA AL CREDITO";
        }

        try {
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + "" + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + "Energigas SAC" + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + "Av. Santo Toribio # 173, cruce con Av. Vía Central, Centro Empresarial, Edificio Real 8 Of. 502" + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + "San Isidro Lima" + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + "Tel: (511) 2033001" + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + "RUC: 20506151547" + LF);//RUC
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + tipoDocumento + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + "COMPROBANTE: " + comprobanteVenta.getSerie() + "-" + comprobanteVenta.getNumDoc() + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + "FECHA: " + comprobanteVenta.getFechaCreacion() + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + "CLIENTE: " + cliente.getPersona().getPerVRazonSocial() + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + "DNI/RUC: " + cliente.getPersona().getPerVDocIdentidad() + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + "DIRECCION: " + cliente.getPersona().getUbicacion().getDescripcion() + LF);
            printLineas();
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + String.format("%-6s", "CANT") + String.format("%-30s", "Producto") + String.format("%-5s", "") + String.format("%-7s", "IMPORTE") + LF + "");
            printLineas();
            double importeTotal = 0.0;
            for (ComprobanteVentaDetalle comprobanteVentaDetalle : comprobanteVenta.getItemsDetalle()) {
                posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + String.format("%-6s", comprobanteVentaDetalle.getCantidad()) + String.format("%-30s", Producto.getNameProducto(comprobanteVentaDetalle.getProId() + "")) + String.format("%-5s", "") + String.format("%-9s", df.format(comprobanteVentaDetalle.getImporte())) + LF + "");

                importeTotal = importeTotal + comprobanteVentaDetalle.getImporte();
            }
            Double importeIgv = importeTotal * igv;
            Double importeTotalCIgv = importeIgv + importeTotal;
            String gravada = ESC + "|lA" + String.format("%-18s", "OP. GRAVADA") + String.format("%-21s", "S/.") + String.format("%1$9s", Utils.formatDouble(importeTotalCIgv)) + LF;
            String inafecta = ESC + "|lA" + String.format("%-18s", "OP. INAFECTA") + String.format("%-21s", "S/.") + String.format("%1$9s", Utils.formatDouble(0.00)) + LF;
            String exonerada = ESC + "|lA" + String.format("%-18s", "OP. EXONERADA") + String.format("%-21s", "S/.") + String.format("%1$9s", Utils.formatDouble(0.00)) + LF;
            String gratuita = ESC + "|lA" + String.format("%-18s", "OP. GRATUITA") + String.format("%-21s", "S/.") + String.format("%1$9s", Utils.formatDouble(0.00)) + LF;
            String descuentos = ESC + "|lA" + String.format("%-18s", "DESCUENTOS") + String.format("%-21s", "S/.") + String.format("%1$9s", Utils.formatDouble(0.00)) + LF;
            String IGV = ESC + "|lA" + String.format("%-18s", "I.G.V.") + String.format("%-21s", "S/.") + String.format("%1$9s", Utils.formatDouble(importeIgv)) + LF;
            String rayaTotal = ESC + "|rA" + "---------" + LF;
            String precioVenta = String.format("%-18s", "PRECIO VENTA") + String.format("%-21s", "S/.") + String.format("%1$9s", Utils.formatDouble(importeTotalCIgv)) + LF;
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, gravada);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, inafecta);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, exonerada);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, gratuita);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, descuentos);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, IGV);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, rayaTotal);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, precioVenta);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + "" + LF);
            //posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + comprobanteVenta.getValorResumen() + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + "" + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + textTipoVenta + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + "AGENTE: " + usuario.getPersona().getPerVNombres() + " " + usuario.getPersona().getPerVApellidoPaterno() + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + "" + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + codigoVenta + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + "REPRESENTACION IMPRESA DE LA FACTURA DE VENTA ELECTRONICA" + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + "Visualice este documento en" + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + url + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + "" + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + "" + LF);
        } catch (JposException e) {
            e.printStackTrace();
        }
    }


    public void printNow(Despacho dispatch, Almacen almacen, Establecimiento establecimiento, Vehiculo vehiculo, Persona agente) {
        try {
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + "" + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + "Energigas SAC" + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + "Av. Santo Toribio # 173, cruce con Av. Vía Central, Centro Empresarial, Edificio Real 8 Of. 502" + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + "San Isidro Lima" + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + "Tel: (511) 2033001" + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + "RUC: 20506151547" + LF);//RUC
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + "Nombre: " + establecimiento.getEstVDescripcion() + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + "Direccion: " + establecimiento.getUbicacion().getDescripcion() + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + "Instalacion: " + almacen.getNombre() + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + "Coordenadas: " + dispatch.getLatitud() + ", " + dispatch.getLongitud() + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + "Placa: " + vehiculo.getPlaca() + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + "Chofer: " + agente.getPerVNombres() + ", " + agente.getPerVApellidoPaterno() + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + "Serie Nro: " + dispatch.getSerie() + "-" + dispatch.getNumero() + LF);
            printLineas();
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + "DESPACHO" + LF);
            printLineas();
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + "Fecha: " + dispatch.getFechaDespacho() + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + "Hora Inicio: " + dispatch.getHoraInicio() + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + "Hora Fin: " + dispatch.getHoraFin() + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + "Contador Inicial: " + dispatch.getContadorInicialDestino() + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + "Contador Final: " + dispatch.getContadorFinalDestino() + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + "Cantidad Despachada: " + dispatch.getCantidadDespachada() + LF);
            printLineas();
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + "Porcentaje Tanque Inicial: " + dispatch.getpITDestino() + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + "Porcentaje Tanque Final: " + dispatch.getpFTDestino() + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + "Serie Tanque: " + almacen.getPlaca() + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + "Nro Transporte: " + vehiculo.getVeId() + LF);
            printLineas();
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + "Recibido por" + "" + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + "" + "" + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + "Nombre :" + "............        ...................." + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + "" + "" + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + "DNI :" + "..................................." + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + "" + "" + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + "Firma :" + "................................." + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + "Documento sin valor tributario" + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + "Consultar su comprobante de pago electronico en" + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + "www.energigas.com" + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + "" + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + "" + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + "" + LF);

            // posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + String.format("%-6s", "CANT") + String.format("%-30s", "DESCRIPCION") + String.format("%1$12s", "P.U.") + LF);


        } catch (JposException e) {
            e.printStackTrace();
        }
    }


    public void printResumen(CajaLiquidacion cajaLiquidacion, Usuario usuario, Summary summary) {
        try {
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + "" + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + "Energigas SAC" + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + "Av. Santo Toribio # 173, cruce con Av. Vía Central, Centro Empresarial, Edificio Real 8 Of. 502" + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + "San Isidro Lima" + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + "Tel: (511) 2033001" + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + "RUC: 20506151547" + LF);//RUC
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + "" + LF);
            printLineas();
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + ESC + "|4C" + "RESUMEN DEL DIA" + LF);
            printLineas();
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + "" + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + "" + "AGENTE     : " + usuario.getPersona().getPerVNombres() + ", " + usuario.getPersona().getPerVApellidoPaterno() + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + "" + "LIQUIDACION: " + cajaLiquidacion.getLiqId() + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + "" + "FECHA      : " + Utils.getDateDescription(cajaLiquidacion.getFechaApertura()) + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + "" + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + "" + "SALDO INICIAL    : " + summary.getSaldoInicial() + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + "" + "INGRESOS TOTALES : " + summary.getIngresosTotales() + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + "" + "EN EFECTIVO      : " + summary.getIngresosTotales() + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + "GASTOS TOTALES   : " + summary.getGastos() + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + "TOTAL A RENDIR   : " + summary.getEfectivoRendir() + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + "" + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + "" + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + ESC + "|2C" + "INGRESOS" + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + String.format("%-18s", "DESCR.") + String.format("%1$6s", "CANT.") + String.format("%1$8s", "EMIT.") + String.format("%1$8s", "PAG.") + String.format("%1$8s", "COBR.") + LF);

            Double sumaCantidad = 0.00;
            Double sumaTotalEmitidos = 0.00;
            Double sumaTotalPagados = 0.00;
            Double sumaTotalCobradas = 0.00;


            for (int i = 0; i < summary.getSummaryIncomeList().size(); i++) {

                SummaryIncome income = summary.getSummaryIncomeList().get(i);
                sumaCantidad = sumaCantidad + Double.parseDouble(income.getCantidad());
                sumaTotalEmitidos = sumaTotalEmitidos + Double.parseDouble(income.getEmitidos());
                sumaTotalPagados = sumaTotalPagados + Double.parseDouble(income.getPagados());
                sumaTotalCobradas = sumaTotalCobradas + Double.parseDouble(income.getCobrados());

                Double aCantidad = (Double.parseDouble(income.getCantidad()));
                posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + String.format("%-18s", income.getConcepto()) + String.format("%1$2s", aCantidad.intValue() + "") + String.format("%1$10s", Utils.formatDouble(Double.parseDouble(income.getEmitidos()))) + String.format("%1$8s", Utils.formatDouble(Double.parseDouble(income.getPagados()))) + String.format("%1$8s", Utils.formatDouble(Double.parseDouble(income.getCobrados()))) + LF);


            }
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + String.format("%-18s", "TOTAL") + String.format("%1$2s", sumaCantidad.intValue() + "") + String.format("%1$10s", Utils.formatDouble(sumaTotalEmitidos)) + String.format("%1$8s", Utils.formatDouble(sumaTotalPagados)) + String.format("%1$8s", Utils.formatDouble(sumaTotalCobradas)) + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + "" + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + "" + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + ESC + "|2C" + "GASTOS" + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + String.format("%-18s", "DESCR.") + String.format("%1$6s", "") + String.format("%1$8s", "") + String.format("%1$8s", "") + String.format("%1$8s", "IMP.") + LF);

            int sumaCountGastos = 0;
            double sumaImporte = 0.00;

            for (int i = 0; i < summary.getCostsList().size(); i++) {
                Costs costs = summary.getCostsList().get(i);
                sumaImporte = sumaImporte + costs.getTotal();
                int count = i + 1;
                sumaCountGastos = count;

                //  posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + String.format("%-18s", costs.getDescripcion()) + String.format("%1$6s", "") + String.format("%1$8s", "") + String.format("%1$8s", "") + String.format("%1$8s", Utils.formatDouble(costs.getTotal())) + LF);


            }

            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + String.format("%-18s", "TOTAl.") + String.format("%1$6s", "") + String.format("%1$8s", "") + String.format("%1$8s", "") + String.format("%1$8s", Utils.formatDouble(sumaImporte)) + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + "" + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + "" + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + String.format("%-18s", "PRODUCTO.") + String.format("%1$8s", "S. INICIAL") + String.format("%1$6s", "") + String.format("%1$4s", "S. VENDIDO") + String.format("%1$10s", "S. FINAL") + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + String.format("%-18s", "PRODUCTO.") + String.format("%1$8s", "S. INICIAL") + String.format("%1$6s", "") + String.format("%1$4s", "S. VENDIDO") + String.format("%1$10s", "S. FINAL") + LF);

            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + "" + LF);

        } catch (JposException e) {
            e.printStackTrace();
        }

    }

    private void printLineas() throws JposException {
        posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + lineas + LF);
    }

}
