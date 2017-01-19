package energigas.apps.systemstrategy.energigas.printingsheets;

import android.graphics.Typeface;

import com.sewoo.jpos.POSPrinterService;
import com.sewoo.jpos.command.CPCL;
import com.sewoo.jpos.command.ESCPOS;
import com.sewoo.jpos.command.ESCPOSConst;
import com.sewoo.jpos.printer.ESCPOSPrinter;
import com.sewoo.jpos.printer.LKPrint;
import com.sewoo.jpos.request.RequestQueue;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;

import energigas.apps.systemstrategy.energigas.entities.Almacen;
import energigas.apps.systemstrategy.energigas.entities.BeDocElectronico;
import energigas.apps.systemstrategy.energigas.entities.CajaLiquidacion;
import energigas.apps.systemstrategy.energigas.entities.Cliente;
import energigas.apps.systemstrategy.energigas.entities.ComprobanteVenta;
import energigas.apps.systemstrategy.energigas.entities.ComprobanteVentaDetalle;
import energigas.apps.systemstrategy.energigas.entities.Concepto;
import energigas.apps.systemstrategy.energigas.entities.Costs;
import energigas.apps.systemstrategy.energigas.entities.DatosEmpresa;
import energigas.apps.systemstrategy.energigas.entities.Despacho;
import energigas.apps.systemstrategy.energigas.entities.Dispatch;
import energigas.apps.systemstrategy.energigas.entities.Establecimiento;
import energigas.apps.systemstrategy.energigas.entities.Inventory;
import energigas.apps.systemstrategy.energigas.entities.OrdenCargo;
import energigas.apps.systemstrategy.energigas.entities.Persona;
import energigas.apps.systemstrategy.energigas.entities.Producto;
import energigas.apps.systemstrategy.energigas.entities.Proveedor;
import energigas.apps.systemstrategy.energigas.entities.Summary;
import energigas.apps.systemstrategy.energigas.entities.SummaryIncome;
import energigas.apps.systemstrategy.energigas.entities.Unidad;
import energigas.apps.systemstrategy.energigas.entities.Usuario;
import energigas.apps.systemstrategy.energigas.entities.Vehiculo;
import energigas.apps.systemstrategy.energigas.utils.Constants;
import energigas.apps.systemstrategy.energigas.utils.NumberToLetterConverter;
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
    private ESCPOSPrinter escposPrinter;
    private CPCL cpclPtr;
    private RequestQueue rq;
    private static char ESC = ESCPOS.ESC;
    private static char LF = ESCPOS.LF;
    private Double igv;

    public SheetsPrintDispatch() {
        posPtr = new POSPrinterService();
        escposPrinter = new ESCPOSPrinter();
        cpclPtr = new CPCL();
        rq = RequestQueue.getInstance();
    }


    public void printNowComprobante(Cliente cliente, ComprobanteVenta comprobanteVenta, Usuario usuario, BeDocElectronico beDocElectronico, DatosEmpresa datosEmpresa) {
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
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + ESC + "|4C" + datosEmpresa.getEntidad().getRazonSocial() + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + "" + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + datosEmpresa.getEntidad().getDireccionFiscal() + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + datosEmpresa.getDistrito() + ", " + datosEmpresa.getProvincia() + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + datosEmpresa.getDepartamento() + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + "Tel: " + datosEmpresa.getEntidad().getTelefono() + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + "RUC: " + datosEmpresa.getEntidad().getrUC() + LF);//RUC
            printLineas();
            //posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + tipoDocumento + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + ESC + "|4C" + tipoDocumento + LF);
            printLineas();
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + "COMPROBANTE: " + comprobanteVenta.getSerie() + "-" + comprobanteVenta.getNumDoc() + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + "FECHA    : " + comprobanteVenta.getFechaCreacion() + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + "CLIENTE  : " + cliente.getPersona().getPerVRazonSocial() + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + "DNI/RUC  : " + cliente.getPersona().getPerVDocIdentidad() + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + "DIRECCION: " + cliente.getPersona().getUbicacion().getDescripcion() + LF);
            printLineas();
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + String.format("%-6s", "CANT") + String.format("%-30s", "Producto") + String.format("%-5s", "") + String.format("%-7s", "IMPORTE") + LF + "");
            printLineas();
            double importeTotal = 0.0;
            for (ComprobanteVentaDetalle comprobanteVentaDetalle : comprobanteVenta.getItemsDetalle()) {
                posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + String.format("%-6s", comprobanteVentaDetalle.getCantidad()) + String.format("%-30s", Producto.getNameProducto(comprobanteVentaDetalle.getProId() + "")) + String.format("%-5s", "") + String.format("%-9s", Utils.formatDoubleNumber(comprobanteVentaDetalle.getImporte())) + LF + "");

                importeTotal = importeTotal + comprobanteVentaDetalle.getImporte();
            }

            Double importeIgv = importeTotal * igv;
            Double importeTotalCIgv = importeIgv + importeTotal;
            //posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + "" + LF);
            printLineas();
            String gravada = ESC + "|lA" + String.format("%-18s", "OP. GRAVADA") + String.format("%-21s", "S/.") + String.format("%1$9s", Utils.formatDoubleNumber(importeTotalCIgv)) + LF;
            String inafecta = ESC + "|lA" + String.format("%-18s", "OP. INAFECTA") + String.format("%-21s", "S/.") + String.format("%1$9s", Utils.formatDoubleNumber(0.00)) + LF;
            String exonerada = ESC + "|lA" + String.format("%-18s", "OP. EXONERADA") + String.format("%-21s", "S/.") + String.format("%1$9s", Utils.formatDoubleNumber(0.00)) + LF;
            String gratuita = ESC + "|lA" + String.format("%-18s", "OP. GRATUITA") + String.format("%-21s", "S/.") + String.format("%1$9s", Utils.formatDoubleNumber(0.00)) + LF;
            String descuentos = ESC + "|lA" + String.format("%-18s", "DESCUENTOS") + String.format("%-21s", "S/.") + String.format("%1$9s", Utils.formatDoubleNumber(0.00)) + LF;
            String IGV = ESC + "|lA" + String.format("%-18s", "I.G.V.") + String.format("%-21s", "S/.") + String.format("%1$9s", Utils.formatDoubleNumber(importeIgv)) + LF;
            String rayaTotal = ESC + "|rA" + "---------" + LF;
            String precioVenta = String.format("%-18s", "PRECIO VENTA") + String.format("%-21s", "S/.") + String.format("%1$9s", Utils.formatDoubleNumber(importeTotalCIgv)) + LF;
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
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + datosEmpresa.getUrl() + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + "" + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + "" + LF);
            posPtr.printBarCode(POSPrinterConst.PTR_S_RECEIPT,"1234567890", LKPrint.LK_BCS_Code39, 40, 2, LKPrint.LK_ALIGNMENT_CENTER, LKPrint.LK_HRI_TEXT_BELOW);


        } catch (JposException e) {
            e.printStackTrace();
        }
    }


    public void printTipoDoc (Cliente cliente, ComprobanteVenta comprobanteVenta, Usuario usuario, BeDocElectronico beDocElectronico, DatosEmpresa datosEmpresa){


        // escposPrinter.printText("ENERGIGAS", LKPrint.LK_ALIGNMENT_CENTER, LKPrint.LK_FNT_BOLD | LKPrint.LK_FNT_FONTB , LKPrint.LK_TXT_5WIDTH);
           /* escposPrinter.printText("ENERGIGAS                                   \r\n", LKPrint.LK_ALIGNMENT_CENTER,  LKPrint.LK_FNT_BOLD | LKPrint.LK_FNT_FONTB, LKPrint.LK_TXT_5WIDTH);
            escposPrinter.printText("______________________________              \r\n", LKPrint.LK_HRI_TEXT_BELOW, LKPrint.LK_FNT_BOLD, LKPrint.LK_TXT_3HEIGHT);
          */



        String data = "ENERGIGAS";
        String fecha_N =  String.format("%1$-30s", "Fecha:" + comprobanteVenta.getFechaCreacion()) + String.format("%-6s", "N:") + String.format("%1$12s", comprobanteVenta.getSerie() + "-" + comprobanteVenta.getNumDoc()) ;
        String descripcionHead =  String.format("%1$-5s", "COD") + String.format("%1$-10s", "CANT")+String.format("%1$-10s", "U.M.")+String.format("%1$-10s", "DESCRIP.")+String.format("%1$-5s", "P.U")+ String.format("%1$8s", "IMPORTE") ;

        String codigoVenta = beDocElectronico.getResumenFirma();

        String tipoDocumento = "FACTURA ELECTRONICA";
        if (comprobanteVenta.getTipoComprobanteId() != Constants.TIPO_DOCUMENTO_FACTURA) {
            tipoDocumento = "BOLETA ELECTRONICA";
        }
        igv = Double.parseDouble(Session.getConceptoIGV().getDescripcion());

        String textTipoVenta = "VENTA AL CONTADO";
        if (comprobanteVenta.getPlanPago() != null) {
            textTipoVenta = "VENTA AL CREDITO";
        }
        try
        {
            escposPrinter.lineFeed(2);
            escposPrinter.printText(data+"\n", LKPrint.LK_ALIGNMENT_CENTER, LKPrint.LK_FNT_BOLD|LKPrint.LK_FNT_UNDERLINE, LKPrint.LK_TXT_3HEIGHT|LKPrint.LK_TXT_3WIDTH);
           // escposPrinter.printText("───────────────────────────\n", LKPrint.LK_ALIGNMENT_CENTER, LKPrint.LK_FNT_BOLD, LKPrint.LK_TXT_3HEIGHT|LKPrint.LK_TXT_3WIDTH);
            //escposPrinter.printText("------------------------------              \r\n", LKPrint.LK_HRI_TEXT_BELOW, LKPrint.LK_FNT_BOLD,  LKPrint.LK_TXT_3HEIGHT|LKPrint.LK_TXT_3WIDTH);
            escposPrinter.printText(Utils.cleanAcentos(datosEmpresa.getEntidad().getRazonSocial())+"\n", LKPrint.LK_ALIGNMENT_CENTER, LKPrint.LK_FNT_BOLD, LKPrint.LK_TXT_1HEIGHT|LKPrint.LK_TXT_2WIDTH);
            escposPrinter.lineFeed(1);
            escposPrinter.printText(Utils.cleanAcentos(datosEmpresa.getEntidad().getDireccionFiscal())+"\r\n", LKPrint.LK_ALIGNMENT_CENTER, LKPrint.LK_FNT_BOLD, LKPrint.LK_TXT_1WIDTH);
            escposPrinter.printText(Utils.cleanAcentos(datosEmpresa.getDistrito() + ", " + datosEmpresa.getProvincia())+"\r\n", LKPrint.LK_ALIGNMENT_CENTER, LKPrint.LK_FNT_BOLD, LKPrint.LK_TXT_1WIDTH);
            escposPrinter.printText(Utils.cleanAcentos(datosEmpresa.getDepartamento())+"\r\n", LKPrint.LK_ALIGNMENT_CENTER, LKPrint.LK_FNT_BOLD, LKPrint.LK_TXT_1WIDTH);
            escposPrinter.lineFeed(1);
            escposPrinter.printText("Telf:"+datosEmpresa.getEntidad().getTelefono()+"\n", LKPrint.LK_ALIGNMENT_CENTER, LKPrint.LK_FNT_BOLD, LKPrint.LK_TXT_1WIDTH);
            escposPrinter.lineFeed(1);
            escposPrinter.printText("R.U.C:"+datosEmpresa.getEntidad().getrUC()+"                 IMEI:012345645\n", LKPrint.LK_ALIGNMENT_LEFT,LKPrint.LK_FNT_BOLD, LKPrint.LK_TXT_1WIDTH);
            escposPrinter.printText(lineas+"\n", LKPrint.LK_ALIGNMENT_LEFT, LKPrint.LK_FNT_BOLD, LKPrint.LK_TXT_1WIDTH);
            escposPrinter.printText(tipoDocumento+"\n", LKPrint.LK_ALIGNMENT_CENTER, LKPrint.LK_FNT_BOLD, LKPrint.LK_TXT_1WIDTH);
            //escposPrinter.printText("----------------------------------------------------\n", LKPrint.LK_ALIGNMENT_LEFT, LKPrint.LK_FNT_BOLD, LKPrint.LK_TXT_1WIDTH);
            escposPrinter.printText(lineas+"\n", LKPrint.LK_ALIGNMENT_LEFT, LKPrint.LK_FNT_BOLD, LKPrint.LK_TXT_1WIDTH);
            //escposPrinter.printText("Fecha:"+datosEmpresa.getEntidad().getrUC()+"                 IMEI:012345645\n", LKPrint.LK_ALIGNMENT_LEFT,LKPrint.LK_FNT_BOLD, LKPrint.LK_TXT_1WIDTH);
            escposPrinter.printText(fecha_N+"\n", LKPrint.LK_ALIGNMENT_LEFT,LKPrint.LK_FNT_BOLD, LKPrint.LK_TXT_1WIDTH);
            escposPrinter.printText("CLIENTE  : " + cliente.getPersona().getPerVRazonSocial() +"\n", LKPrint.LK_ALIGNMENT_LEFT,LKPrint.LK_FNT_BOLD, LKPrint.LK_TXT_1WIDTH);
            escposPrinter.printText("DNI/RUC  : " + cliente.getPersona().getPerVDocIdentidad()+"\n", LKPrint.LK_ALIGNMENT_LEFT,LKPrint.LK_FNT_BOLD, LKPrint.LK_TXT_1WIDTH);
            escposPrinter.printText("DIRECCION: " + cliente.getPersona().getUbicacion().getDescripcion()+"\n", LKPrint.LK_ALIGNMENT_LEFT,LKPrint.LK_FNT_BOLD, LKPrint.LK_TXT_1WIDTH);
            escposPrinter.printText(lineas+"\n", LKPrint.LK_ALIGNMENT_LEFT, LKPrint.LK_FNT_BOLD, LKPrint.LK_TXT_1WIDTH);
            escposPrinter.printText(descripcionHead+"\n", LKPrint.LK_ALIGNMENT_LEFT, LKPrint.LK_FNT_BOLD, LKPrint.LK_TXT_1WIDTH);
            escposPrinter.printText(lineas+"\n", LKPrint.LK_ALIGNMENT_LEFT, LKPrint.LK_FNT_BOLD, LKPrint.LK_TXT_1WIDTH);
            double importeTotal = 0.0;
            for (ComprobanteVentaDetalle comprobanteVentaDetalle : comprobanteVenta.getItemsDetalle()) {
                //posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + String.format("%-6s", comprobanteVentaDetalle.getCantidad()) + String.format("%-30s", Producto.getNameProducto(comprobanteVentaDetalle.getProId() + "")) + String.format("%-5s", "") + String.format("%-9s", Utils.formatDoubleNumber(comprobanteVentaDetalle.getImporte())) + LF + "");
                String descripcionTotal =  String.format("%1$-5s", comprobanteVentaDetalle.getProId()) + String.format("%1$-10s", comprobanteVentaDetalle.getCantidad())+String.format("%1$-10s", "U.M.")+String.format("%1$-5s", Producto.getNameProducto(comprobanteVentaDetalle.getProId() + ""))+String.format("%1$-10s",Utils.formatDoublePrint(comprobanteVentaDetalle.getPrecioUnitario()))+ String.format("%1$8s", Utils.formatDoublePrint(comprobanteVentaDetalle.getImporte())) ;
                escposPrinter.printText(descripcionTotal+"\n", LKPrint.LK_ALIGNMENT_LEFT, LKPrint.LK_FNT_BOLD, LKPrint.LK_TXT_1WIDTH);
                importeTotal = importeTotal + comprobanteVentaDetalle.getImporte();
            }
            escposPrinter.printText(lineas+"\n", LKPrint.LK_ALIGNMENT_LEFT, LKPrint.LK_FNT_BOLD, LKPrint.LK_TXT_1WIDTH);

            Double importeIgv = importeTotal * igv;
            Double importeTotalCIgv = importeIgv + importeTotal;


            String gravada =String.format("%-18s", "OP. GRAVADA") + String.format("%-21s", "S/.") + String.format("%1$9s", Utils.formatDoubleNumber(importeTotalCIgv)) ;
            String inafecta =  String.format("%-18s", "OP. INAFECTA") + String.format("%-21s", "S/.") + String.format("%1$9s", Utils.formatDoubleNumber(0.00)) ;
            String exonerada =  String.format("%-18s", "OP. EXONERADA") + String.format("%-21s", "S/.") + String.format("%1$9s", Utils.formatDoubleNumber(0.00)) ;
            String gratuita =  String.format("%-18s", "OP. GRATUITA") + String.format("%-21s", "S/.") + String.format("%1$9s", Utils.formatDoubleNumber(0.00)) ;
            String descuentos =  String.format("%-18s", "DESCUENTOS") + String.format("%-21s", "S/.") + String.format("%1$9s", Utils.formatDoubleNumber(0.00)) ;
            String IGV =  String.format("%-18s", "I.G.V.") + String.format("%-21s", "S/.") + String.format("%1$9s", Utils.formatDoubleNumber(importeIgv)) ;
            String rayaTotal =   "---------" ;
            String precioVenta = String.format("%-18s", "IMPORTE TOTAL") + String.format("%-21s", "S/.") + String.format("%1$9s", Utils.formatDoubleNumber(importeTotalCIgv)) ;
            escposPrinter.printText(gravada+"\n", LKPrint.LK_ALIGNMENT_LEFT, LKPrint.LK_FNT_BOLD, LKPrint.LK_TXT_1WIDTH);
            escposPrinter.printText(inafecta+"\n", LKPrint.LK_ALIGNMENT_LEFT, LKPrint.LK_FNT_BOLD, LKPrint.LK_TXT_1WIDTH);
            escposPrinter.printText(exonerada+"\n", LKPrint.LK_ALIGNMENT_LEFT, LKPrint.LK_FNT_BOLD, LKPrint.LK_TXT_1WIDTH);
            escposPrinter.printText(gratuita+"\n", LKPrint.LK_ALIGNMENT_LEFT, LKPrint.LK_FNT_BOLD, LKPrint.LK_TXT_1WIDTH);
            escposPrinter.printText(descuentos+"\n", LKPrint.LK_ALIGNMENT_LEFT, LKPrint.LK_FNT_BOLD, LKPrint.LK_TXT_1WIDTH);
            escposPrinter.printText(IGV+"\n", LKPrint.LK_ALIGNMENT_LEFT, LKPrint.LK_FNT_BOLD, LKPrint.LK_TXT_1WIDTH);
            escposPrinter.printText(rayaTotal+"\n", LKPrint.LK_ALIGNMENT_RIGHT, LKPrint.LK_FNT_BOLD, LKPrint.LK_TXT_1WIDTH);
            escposPrinter.printText(precioVenta+"\n", LKPrint.LK_ALIGNMENT_RIGHT, LKPrint.LK_FNT_BOLD, LKPrint.LK_TXT_1WIDTH);
            escposPrinter.printText(lineas+"\n", LKPrint.LK_ALIGNMENT_LEFT, LKPrint.LK_FNT_BOLD, LKPrint.LK_TXT_1WIDTH);
            escposPrinter.printText("SON:"+ NumberToLetterConverter.convertNumberToLetter(importeTotalCIgv)+"\n", LKPrint.LK_ALIGNMENT_LEFT, LKPrint.LK_FNT_BOLD, LKPrint.LK_TXT_1WIDTH);
            escposPrinter.printText(lineas+"\n", LKPrint.LK_ALIGNMENT_LEFT, LKPrint.LK_FNT_BOLD, LKPrint.LK_TXT_1WIDTH);
            escposPrinter.lineFeed(1);
            escposPrinter.printBarCode("0123456789", LKPrint.LK_BCS_Code39, 40, 512, LKPrint.LK_ALIGNMENT_CENTER, LKPrint.LK_HRI_TEXT_BELOW);
            escposPrinter.printText(Utils.cleanAcentos(tipoDocumento)+"\n", LKPrint.LK_ALIGNMENT_CENTER, LKPrint.LK_FNT_BOLD, LKPrint.LK_TXT_1WIDTH);
            escposPrinter.printText(Utils.cleanAcentos("AGENTE:"+usuario.getPersona().getPerVNombres() + " " + usuario.getPersona().getPerVApellidoPaterno())+"\n", LKPrint.LK_ALIGNMENT_CENTER, LKPrint.LK_FNT_BOLD, LKPrint.LK_TXT_1WIDTH);
            escposPrinter.lineFeed(1);
            escposPrinter.printText(Utils.cleanAcentos(codigoVenta)+"\n", LKPrint.LK_ALIGNMENT_CENTER, LKPrint.LK_FNT_BOLD, LKPrint.LK_TXT_1WIDTH);
            escposPrinter.lineFeed(1);
            escposPrinter.printText("REPRESENTACION IMPRESA DE LA FACTURA DE VENTA ELECTRONICA\n", LKPrint.LK_ALIGNMENT_CENTER, LKPrint.LK_FNT_BOLD, LKPrint.LK_TXT_1WIDTH);
            escposPrinter.lineFeed(1);
            escposPrinter.printText("Visualice este documento en\n", LKPrint.LK_ALIGNMENT_CENTER, LKPrint.LK_FNT_BOLD, LKPrint.LK_TXT_1WIDTH);
            escposPrinter.printText(datosEmpresa.getUrl()+"\n", LKPrint.LK_ALIGNMENT_CENTER, LKPrint.LK_FNT_BOLD, LKPrint.LK_TXT_1WIDTH);
            escposPrinter.lineFeed(3);

            //"SON:"+NumberToLetterConverter.convertNumberToLetter(importeTotalIgv)





            /*
            escposPrinter.printAndroidFont(Typeface.SANS_SERIF, true, true,true,Utils.cleanAcentos(data),0, 70, ESCPOSConst.LK_ALIGNMENT_CENTER);
            //escposPrinter.printAndroidFont(Typeface.SANS_SERIF, true, true, true, Utils.cleanAcentos(data), 0, 70, ESCPOSConst.LK_ALIGNMENT_LEFT);
            // escposPrinter.printText("_________________________________\n", LKPrint.LK_ALIGNMENT_CENTER, LKPrint.LK_FNT_BOLD, LKPrint.LK_TXT_3HEIGHT);
            escposPrinter.printAndroidFont(Typeface.SANS_SERIF, true, false,Utils.cleanAcentos(datosEmpresa.getEntidad().getRazonSocial()), nLineWidth2, 40, ESCPOSConst.LK_ALIGNMENT_CENTER);
            escposPrinter.lineFeed(3);
            //escposPrinter.printText(Utils.cleanAcentos(datosEmpresa.getEntidad().getDireccionFiscal())+"\r\n", LKPrint.LK_ALIGNMENT_CENTER, LKPrint.LK_FNT_BOLD, LKPrint.LK_TXT_1WIDTH);
            //escposPrinter.printText(Utils.cleanAcentos(datosEmpresa.getDistrito() + ", " + datosEmpresa.getProvincia())+"\r\n", LKPrint.LK_ALIGNMENT_CENTER, LKPrint.LK_FNT_BOLD, LKPrint.LK_TXT_1WIDTH);
            //escposPrinter.printText(Utils.cleanAcentos(datosEmpresa.getDepartamento())+"\r\n", LKPrint.LK_ALIGNMENT_CENTER, LKPrint.LK_FNT_BOLD, LKPrint.LK_TXT_1WIDTH);
            //escposPrinter.lineFeed(1);
            //escposPrinter.printAndroidFont(Typeface.SERIF, true, true,"Telf:"+datosEmpresa.getEntidad().getTelefono(), nLineWidth, 28, ESCPOSConst.LK_ALIGNMENT_CENTER);
            escposPrinter.printAndroidFont(Typeface.SANS_SERIF, true, true,Utils.cleanAcentos(data), 0, 70, ESCPOSConst.LK_ALIGNMENT_RIGHT);
            //escposPrinter.printText("_________________________________\n", LKPrint.LK_ALIGNMENT_CENTER, LKPrint.LK_FNT_BOLD, LKPrint.LK_TXT_3HEIGHT);
            escposPrinter.printAndroidFont(Typeface.SANS_SERIF, true, false,Utils.cleanAcentos(datosEmpresa.getEntidad().getRazonSocial()), 0, 40, ESCPOSConst.LK_ALIGNMENT_RIGHT);
            escposPrinter.lineFeed(1);
           // escposPrinter.printText("R.U.C:"+datosEmpresa.getEntidad().getrUC()+"              IMEI:01234564564   \r\n", ESCPOSConst.LK_FNT_DEFAULT, LKPrint.LK_FNT_BOLD|LKPrint.LK_HRI_TEXT_BELOW , LKPrint.LK_TXT_1WIDTH);
*/

/*


            escposPrinter.printAndroidFont(data, nLineWidth, 100, ESCPOSConst.LK_ALIGNMENT_CENTER);
            escposPrinter.lineFeed(2);

            escposPrinter.printAndroidFont("Left Alignment", nLineWidth, 24, ESCPOSConst.LK_ALIGNMENT_LEFT);
            escposPrinter.printAndroidFont("Center Alignment", nLineWidth, 24, ESCPOSConst.LK_ALIGNMENT_CENTER);
            escposPrinter.printAndroidFont("Right Alignment", nLineWidth, 24, ESCPOSConst.LK_ALIGNMENT_RIGHT);

            escposPrinter.lineFeed(2);
            escposPrinter.printAndroidFont(Typeface.SANS_SERIF, "SANS_SERIF : 1234iwIW", nLineWidth, 24, ESCPOSConst.LK_ALIGNMENT_LEFT);
            escposPrinter.printAndroidFont(Typeface.SERIF, "SERIF : 1234iwIW", nLineWidth, 24, ESCPOSConst.LK_ALIGNMENT_LEFT);
            escposPrinter.printAndroidFont(Typeface.MONOSPACE, "MONOSPACE : 1234iwIW", nLineWidth, 24, ESCPOSConst.LK_ALIGNMENT_LEFT);

            escposPrinter.lineFeed(2);
            escposPrinter.printAndroidFont(Typeface.SANS_SERIF, "SANS : 1234iwIW", nLineWidth, 24, ESCPOSConst.LK_ALIGNMENT_LEFT);
            escposPrinter.printAndroidFont(Typeface.SANS_SERIF, true, "SANS BOLD : 1234iwIW", nLineWidth, 24, ESCPOSConst.LK_ALIGNMENT_LEFT);
            escposPrinter.printAndroidFont(Typeface.SANS_SERIF, true, false, "SANS BOLD : 1234iwIW", nLineWidth, 24, ESCPOSConst.LK_ALIGNMENT_LEFT);
            escposPrinter.printAndroidFont(Typeface.SANS_SERIF, false, true, "SANS ITALIC : 1234iwIW", nLineWidth, 24, ESCPOSConst.LK_ALIGNMENT_LEFT);
            escposPrinter.printAndroidFont(Typeface.SANS_SERIF, true, true, "SANS BOLD ITALIC : 1234iwIW", nLineWidth, 24, ESCPOSConst.LK_ALIGNMENT_LEFT);
            escposPrinter.printAndroidFont(Typeface.SANS_SERIF, true, true, true, "SANS B/I/U : 1234iwIW", nLineWidth, 24, ESCPOSConst.LK_ALIGNMENT_LEFT);

            escposPrinter.lineFeed(2);
            escposPrinter.printAndroidFont(Typeface.SERIF, "SERIF : 1234iwIW", nLineWidth, 24, ESCPOSConst.LK_ALIGNMENT_LEFT);
            escposPrinter.printAndroidFont(Typeface.SERIF, true, "SERIF BOLD : 1234iwIW", nLineWidth, 24, ESCPOSConst.LK_ALIGNMENT_LEFT);
            escposPrinter.printAndroidFont(Typeface.SERIF, true, false, "SERIF BOLD : 1234iwIW", nLineWidth, 24, ESCPOSConst.LK_ALIGNMENT_LEFT);
            escposPrinter.printAndroidFont(Typeface.SERIF, false, true, "SERIF ITALIC : 1234iwIW", nLineWidth, 24, ESCPOSConst.LK_ALIGNMENT_LEFT);
            escposPrinter.printAndroidFont(Typeface.SERIF, true, true, "SERIF BOLD ITALIC : 1234iwIW", nLineWidth, 24, ESCPOSConst.LK_ALIGNMENT_LEFT);
            escposPrinter.printAndroidFont(Typeface.SERIF, true, true, true, "SERIF B/I/U : 1234iwIW", nLineWidth, 24, ESCPOSConst.LK_ALIGNMENT_LEFT);

            escposPrinter.lineFeed(2);
            escposPrinter.printAndroidFont(Typeface.MONOSPACE, "MONOSPACE : 1234iwIW", nLineWidth, 24, ESCPOSConst.LK_ALIGNMENT_LEFT);
            escposPrinter.printAndroidFont(Typeface.MONOSPACE, true, "MONO BOLD : 1234iwIW", nLineWidth, 24, ESCPOSConst.LK_ALIGNMENT_LEFT);
            escposPrinter.printAndroidFont(Typeface.MONOSPACE, true, false, "MONO BOLD : 1234iwIW", nLineWidth, 24, ESCPOSConst.LK_ALIGNMENT_LEFT);
            escposPrinter.printAndroidFont(Typeface.MONOSPACE, false, true, "MONO ITALIC : 1234iwIW", nLineWidth, 24, ESCPOSConst.LK_ALIGNMENT_LEFT);
            escposPrinter.printAndroidFont(Typeface.MONOSPACE, true, true, "MONO BOLD ITALIC: 1234iwIW", nLineWidth, 24, ESCPOSConst.LK_ALIGNMENT_LEFT);
            escposPrinter.printAndroidFont(Typeface.MONOSPACE, true, true, true, "MONO B/I/U: 1234iwIW", nLineWidth, 24, ESCPOSConst.LK_ALIGNMENT_LEFT);
*/
            escposPrinter.lineFeed(4);
        } catch (IOException e) {
            e.printStackTrace();
        }


        // escposPrinter.printText("______________________________\n", LKPrint.LK_HRI_TEXT_BELOW, LKPrint.LK_FNT_BOLD, LKPrint.LK_TXT_3HEIGHT);
            /*escposPrinter.printText("\n", LKPrint.LK_ALIGNMENT_CENTER, LKPrint.LK_FNT_BOLD, LKPrint.LK_TXT_1WIDTH);
            escposPrinter.printText(datosEmpresa.getEntidad().getRazonSocial()+"\n", LKPrint.LK_ALIGNMENT_CENTER, LKPrint.LK_FNT_DEFAULT, LKPrint.LK_TXT_1WIDTH);
            escposPrinter.printText(datosEmpresa.getEntidad().getDireccionFiscal() +"\n", LKPrint.LK_ALIGNMENT_CENTER, LKPrint.LK_FNT_DEFAULT, LKPrint.LK_TXT_1WIDTH);
            escposPrinter.printText( datosEmpresa.getDistrito() + ", " + datosEmpresa.getProvincia()+"\n", LKPrint.LK_ALIGNMENT_CENTER, LKPrint.LK_FNT_DEFAULT, LKPrint.LK_TXT_1WIDTH);
            escposPrinter.printText("Receipt\r\n\r\n\r\n", LKPrint.LK_ALIGNMENT_CENTER, LKPrint.LK_FNT_DEFAULT, LKPrint.LK_TXT_1WIDTH);
*/
          /*  escposPrinter.printText("Receipt\r\n\r\n\r\n", LKPrint.LK_ALIGNMENT_CENTER, LKPrint.LK_FNT_DEFAULT, LKPrint.LK_TXT_2WIDTH);
            escposPrinter.printText("TEL (123)-456-7890\r\n", LKPrint.LK_ALIGNMENT_RIGHT, LKPrint.LK_FNT_DEFAULT, LKPrint.LK_TXT_1WIDTH);
            escposPrinter.printText("Thank you for coming to our shop!\r\n", LKPrint.LK_ALIGNMENT_CENTER, LKPrint.LK_FNT_DEFAULT, LKPrint.LK_TXT_1WIDTH);
            escposPrinter.printText("Chicken                             $10.00\r\n", LKPrint.LK_ALIGNMENT_LEFT, LKPrint.LK_FNT_DEFAULT, LKPrint.LK_TXT_1WIDTH);
            escposPrinter.printText("Hamburger                           $20.00\r\n", LKPrint.LK_ALIGNMENT_LEFT, LKPrint.LK_FNT_DEFAULT, LKPrint.LK_TXT_1WIDTH);
            escposPrinter.printText("Pizza                               $30.00\r\n", LKPrint.LK_ALIGNMENT_LEFT, LKPrint.LK_FNT_DEFAULT, LKPrint.LK_TXT_1WIDTH);
            escposPrinter.printText("Lemons                              $40.00\r\n", LKPrint.LK_ALIGNMENT_LEFT, LKPrint.LK_FNT_DEFAULT, LKPrint.LK_TXT_1WIDTH);
            escposPrinter.printText("Drink                               $50.00\r\n\r\n", LKPrint.LK_ALIGNMENT_LEFT, LKPrint.LK_FNT_DEFAULT, LKPrint.LK_TXT_1WIDTH);
            escposPrinter.printText("Excluded tax                       $150.00\r\n", LKPrint.LK_ALIGNMENT_LEFT, LKPrint.LK_FNT_DEFAULT, LKPrint.LK_TXT_1WIDTH);
            escposPrinter.printText("Tax(5%)                              $7.50\r\n", LKPrint.LK_ALIGNMENT_LEFT, LKPrint.LK_FNT_UNDERLINE, LKPrint.LK_TXT_1WIDTH);
            escposPrinter.printText("Total         $157.50\r\n\r\n", LKPrint.LK_ALIGNMENT_LEFT, LKPrint.LK_FNT_DEFAULT, LKPrint.LK_TXT_2WIDTH);
            escposPrinter.printText("Payment                            $200.00\r\n", LKPrint.LK_ALIGNMENT_LEFT, LKPrint.LK_FNT_DEFAULT, LKPrint.LK_TXT_1WIDTH);
            escposPrinter.printText("Change                              $42.50\r\n\r\n", LKPrint.LK_ALIGNMENT_LEFT, LKPrint.LK_FNT_DEFAULT, LKPrint.LK_TXT_1WIDTH);
            */// Reverse print.
        // escposPrinter.printText("Change                              $42.50\r\n\r\n", LKPrint.LK_ALIGNMENT_LEFT, LKPrint.LK_FNT_DEFAULT | LKPrint.LK_FNT_REVERSE, LKPrint.LK_TXT_1WIDTH);
        // escposPrinter.printBarCode("0123456789", LKPrint.LK_BCS_Code39, 40, 512, LKPrint.LK_ALIGNMENT_CENTER, LKPrint.LK_HRI_TEXT_BELOW);

    }
    public void printNow(Cliente cliente, Despacho dispatch, Almacen almacen, Establecimiento establecimiento, Vehiculo vehiculo, Persona agente, DatosEmpresa datosEmpresa,Unidad unidad) {


        try {
            //49 caracteres!!

            String mContadorFinal = ESC + "|lA" + ESC + "|bC" + String.format("%1$-34s", "Contador Final") + String.format("%-6s", ":") + String.format("%1$8s", dispatch.getContadorFinalDestino()) + LF;
            String mTanqueFinal = ESC + "|lA" + ESC + "|bC" + String.format("%1$-34s", "Porcentaje Tanque Final") + String.format("%-6s", ":") + String.format("%1$8s", dispatch.getpFTDestino()) + LF;

            String mPlaca = ESC + "|lA" + ESC + "|bC" + String.format("%1$-34s", "Serie Tanque") + String.format("%-6s", ":") + String.format("%1$8s", dispatch.getPlaca()) + LF;
            String mNrTransporte = ESC + "|lA" + ESC + "|bC" + String.format("%1$-34s", "Nro Transporte") + String.format("%-6s", ":") + String.format("%1$8s", dispatch.getVeId()) + LF;

            String mContandorInicial = ESC + "|lA" + ESC + "|bC" + String.format("%1$-34s", "Contador Inicial") + String.format("%-6s", ":") + String.format("%1$8s", dispatch.getContadorInicialDestino()) + LF;
            String mPTanqueInicial = ESC + "|lA" + ESC + "|bC" + String.format("%1$-34s", "Porcentaje Tanque Inicial") + String.format("%-6s", ":") + String.format("%1$8s", dispatch.getpITDestino()) + LF;
            String mCapacidadTanque = ESC + "|lA" + ESC + "|bC" + String.format("%1$-34s", "Capacidad de Tanque") + String.format("%-6s", ":") + String.format("%1$8s", almacen.getCapacidadReal()) + LF;
            String mDateHours = ESC + "|lA" + ESC + "|bC" + String.format("%1$-23s", "Fecha  :" + dispatch.getFechaDespacho()) + String.format("%-17s", "Hora Inicio:") + String.format("%1$8s", dispatch.getHoraInicio()) + LF;

      /*
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" +ESC+ "|bC"+ "Contador Final                      : " + dispatch.getContadorFinalDestino() + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" +ESC+ "|bC"+ "Porcentaje Tanque Final             : " + dispatch.getpFTDestino() + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" +ESC+ "|bC"+ "Serie Tanque                        : " + almacen.getPlaca() + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" +ESC+ "|bC"+ "Nro Transporte                      : " + vehiculo.getVeId() + LF);

            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + ESC + "|bC" +"0000000000000000000000000000000000000000000000000"+ LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + ESC + "|bC" +"1111111111111111111111111111111111111111111111111"+ LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + ESC + "|bC" +"................................................."+ LF);
        */

            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + "" + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + ESC + "|4C" + datosEmpresa.getEntidad().getRazonSocial() + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + datosEmpresa.getEntidad().getDireccionFiscal() + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + datosEmpresa.getDistrito() + ", " + datosEmpresa.getProvincia() + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + datosEmpresa.getDepartamento() + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + "Tel: " + datosEmpresa.getEntidad().getTelefono() + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + "RUC: " + datosEmpresa.getEntidad().getrUC() + LF);//RUC
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + LF);


            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + ESC + "|bC" + "Nombre     : " + establecimiento.getEstVDescripcion() + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + ESC + "|bC" + "Direccion  : " + establecimiento.getUbicacion().getDescripcion() + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + ESC + "|bC" + "Instalacion: " + almacen.getNombre() + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + ESC + "|bC" + "Coordenadas: " + dispatch.getLatitud() + ", " + dispatch.getLongitud() + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + ESC + "|bC" + "Placa      : " + vehiculo.getPlaca() + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + ESC + "|bC" + "Agente     : " + agente.getPerVNombres() + ", " + agente.getPerVApellidoPaterno() + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + ESC + "|bC" + "Cliente    : " + cliente.getPersona().getPerVRazonSocial() + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + ESC + "|bC" + "NroDespacho: " + dispatch.getSerie() + "-" + dispatch.getNumero() + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + ESC + "|4C" + "DESPACHO" + LF);
            printLineas();
            //posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" +ESC+ "|bC"+ "Fecha : " + dispatch.getFechaDespacho() + "     Hora Inicio :    "+dispatch.getHoraInicio()+ LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, mDateHours);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + ESC + "|bC" + "                  " + "     Hora Final :         " + dispatch.getHoraFin() + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, mContandorInicial);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, mPTanqueInicial);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, mPlaca);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, mNrTransporte);
            printLineas();
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + "" + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + ESC + "|3C" + "Cantidad Despachada "+unidad.getDescripcion()+": " + dispatch.getCantidadDespachada() + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + "" + LF);
            printLineas();
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, mContadorFinal);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, mTanqueFinal);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, mCapacidadTanque);
            printLineas();
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + ESC + "|bC" + "Recibido por" + "" + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + "" + "" + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + ESC + "|bC" + "Nombre    :" + "..............................." + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + "" + "" + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + ESC + "|bC" + "DNI       :" + "..............................." + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + "" + "" + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + ESC + "|bC" + "Firma     :" + "..............................." + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + "" + "" + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + "Documento sin valor tributario" + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + "Consultar su comprobante de pago electronico en" + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + datosEmpresa.getUrl() + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + "" + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + "" + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + "" + LF);

        } catch (JposException e) {
            e.printStackTrace();
        }


        // posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + String.format("%-6s", "CANT") + String.format("%-30s", "DESCRIPCION") + String.format("%1$12s", "P.U.") + LF);


    }


    public void printResumen(CajaLiquidacion cajaLiquidacion, Usuario usuario, Summary summary, Inventory inventory, DatosEmpresa datosEmpresa) {
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
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + "" + "SALDO INICIAL    : " + Utils.formatDoublePrint(summary.getSaldoInicial()) + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + "" + "INGRESOS TOTALES : " + Utils.formatDoublePrint(summary.getIngresosTotales()) + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + "" + "EN EFECTIVO      : " + Utils.formatDoublePrint(summary.getIngresosTotales()) + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + "GASTOS TOTALES   : " + Utils.formatDoublePrint(summary.getGastos()) + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + "TOTAL A RENDIR   : " + Utils.formatDoublePrint(summary.getEfectivoRendir()) + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + "" + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + "" + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + ESC + "|2C" + "VENTA" + LF);
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
                posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + String.format("%-18s", income.getConcepto()) + String.format("%1$2s", aCantidad.intValue() + "") + String.format("%1$10s", Utils.formatDoublePrint(Double.parseDouble(income.getEmitidos()))) + String.format("%1$8s", Utils.formatDoublePrint(Double.parseDouble(income.getPagados()))) + String.format("%1$8s", Utils.formatDoublePrint(Double.parseDouble(income.getCobrados()))) + LF);


            }
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + String.format("%-18s", "TOTAL") + String.format("%1$2s", sumaCantidad.intValue() + "") + String.format("%1$10s", Utils.formatDoublePrint(sumaTotalEmitidos)) + String.format("%1$8s", Utils.formatDoublePrint(sumaTotalPagados)) + String.format("%1$8s", Utils.formatDoublePrint(sumaTotalCobradas)) + LF);
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
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + ESC + "|2C" + "INVENTARIO" + LF);

            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + String.format("%-18s", "PROD.") + String.format("%1$3s", "INI.") + String.format("%1$8s", "VEND.") + String.format("%1$4s", "") + String.format("%1$8s", "FIN.") + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + String.format("%-18s", inventory.getNombre()) + String.format("%1$3s", Utils.formatDoublePrint(inventory.getCantidadInicial())) + String.format("%1$8s", Utils.formatDoublePrint(inventory.getCantidadVendida())) + String.format("%1$4s", "") + String.format("%1$8s", Utils.formatDoublePrint(inventory.getCantidadFinal())) + LF);

            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + "" + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + "" + LF);

        } catch (JposException e) {
            e.printStackTrace();
        }

    }


    public void printCompra(OrdenCargo ordenCargo, Concepto concepto, Proveedor proveedor, Producto producto, Unidad unidad, CajaLiquidacion cajaLiquidacion, Almacen almacen, DatosEmpresa datosEmpresa, Persona persona) {
        {
            try {

                String mUnidadMedida = ESC + "|lA" + ESC + "|bC" + String.format("%1$-30s", "Unidad de Medida") + String.format("%-6s", ":") + String.format("%1$12s", Utils.cleanAcentos(unidad.getDescripcion())) + LF;
                String mCantidad = ESC + "|lA" + ESC + "|bC" + String.format("%1$-30s", "Cantidad") + String.format("%-6s", ":") + String.format("%1$12s", ordenCargo.getCantidadDoc()) + LF;
                String mFactorConversion = ESC + "|lA" + ESC + "|bC" + String.format("%1$-30s", "Factor de Conversion") + String.format("%-6s", ":") + String.format("%1$12s", ordenCargo.getFactorConversion()) + LF;
                String mDensidad = ESC + "|lA" + ESC + "|bC" + String.format("%1$-30s", "Densidad") + String.format("%-6s", ":") + String.format("%1$12s", ordenCargo.getDensidad()) + LF;
                String mPrecio = ESC + "|lA" + ESC + "|bC" + String.format("%1$-30s", "Precio") + String.format("%-6s", ":") + String.format("%1$12s", ordenCargo.getPrecio()) + LF;

                posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + "" + LF);
                posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + ESC + "|4C" + datosEmpresa.getEntidad().getRazonSocial() + LF);
                posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + datosEmpresa.getEntidad().getDireccionFiscal() + LF);
                posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + datosEmpresa.getDistrito() + ", " + datosEmpresa.getProvincia() + LF);
                posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + datosEmpresa.getDepartamento() + LF);
                posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + "Tel: " + datosEmpresa.getEntidad().getTelefono() + LF);
                posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + "RUC: " + datosEmpresa.getEntidad().getrUC() + LF);//RUC
                posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + LF);
                printLineas();
                posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + ESC + "|bC" + ESC + "|3C" + "TIPO DE CARGA : " + LF);
                printLineas();
                posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + ESC + "|4C" + concepto.getDescripcion() + LF);
                printLineas();
                posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + ESC + "|bC" + "Razon Social  : " + proveedor.getPersona().getNombreComercial() + LF);
                posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + ESC + "|bC" + "Ruc           : " + proveedor.getPersona().getPerVDocIdentidad() + LF);
                posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + ESC + "|bC" + "NroComprobante: " + ordenCargo.getNroComprobante() + LF);
                posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + ESC + "|bC" + "NroGuia       : " + ordenCargo.getNroGuia() + LF);
                posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + ESC + "|bC" + "Fecha Creacion: " + ordenCargo.getFechaCreacion() + LF);
                posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + ESC + "|bC" + "Fecha Accion  : " + ordenCargo.getFechaAccion() + LF);
                posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + "" + LF);
                posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + ESC + "|bC" + ESC + "|3C" + "ITEM COMPRA :  " + LF);
                printLineas();
                posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, mUnidadMedida);
                posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, mCantidad);
                posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, mFactorConversion);
                posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, mDensidad);
                posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, mPrecio);
                printLineas();
                posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + ESC + "|3C" + "Agente: " + persona.getPerVNombres() + " " + persona.getPerVApellidoPaterno() + " " + persona.getPerVApellidoMaterno() + LF);
                printLineas();
                posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + ESC + "|bC" + "Recibido por" + "" + LF);
                posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + "" + "" + LF);
                posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + ESC + "|bC" + "Nombre    :" + "..............................." + LF);
                posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + "" + "" + LF);
                posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + ESC + "|bC" + "DNI       :" + "..............................." + LF);
                posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + "" + "" + LF);
                posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + ESC + "|bC" + "Firma     :" + "..............................." + LF);
                posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + "" + "" + LF);
                posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + "Documento sin valor tributario" + LF);
                posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + "Consultar su comprobante de pago electronico en" + LF);
                posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + datosEmpresa.getUrl() + LF);
                posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + "" + LF);
                posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + "" + LF);
                posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + "" + LF);
            } catch (JposException e) {
                e.printStackTrace();
            }

        }

    }


    public void printTrasciego(OrdenCargo ordenCargo,String tipoOrigen,Concepto concepto, Producto producto, Unidad unidad,DatosEmpresa datosEmpresa,Persona persona){
        {
            try {

                String mUnidadMedida = ESC + "|lA" + ESC + "|bC" + String.format("%1$-30s", "Unidad de Medida") + String.format("%-6s", ":") + String.format("%1$12s", Utils.cleanAcentos(unidad.getDescripcion())) + LF;
                String mCantidad = ESC + "|lA" + ESC + "|bC" + String.format("%1$-30s", "Cantidad") + String.format("%-6s", ":") + String.format("%1$12s", ordenCargo.getCantidadDoc()) + LF;
                String mFactorConversion = ESC + "|lA" + ESC + "|bC" + String.format("%1$-30s", "Factor de Conversion") + String.format("%-6s", ":") + String.format("%1$12s", ordenCargo.getFactorConversion()) + LF;
                String mDensidad = ESC + "|lA" + ESC + "|bC" + String.format("%1$-30s", "Densidad") + String.format("%-6s", ":") + String.format("%1$12s", ordenCargo.getDensidad()) + LF;
                String mPrecio = ESC + "|lA" + ESC + "|bC" + String.format("%1$-30s", "Precio") + String.format("%-6s", ":") + String.format("%1$12s", ordenCargo.getPrecio()) + LF;

                posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + "" + LF);
                posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + ESC + "|4C" + datosEmpresa.getEntidad().getRazonSocial() + LF);
                posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + datosEmpresa.getEntidad().getDireccionFiscal() + LF);
                posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + datosEmpresa.getDistrito() + ", " + datosEmpresa.getProvincia() + LF);
                posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + datosEmpresa.getDepartamento() + LF);
                posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + "Tel: " + datosEmpresa.getEntidad().getTelefono() + LF);
                posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + "RUC: " + datosEmpresa.getEntidad().getrUC() + LF);//RUC
                posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + LF);
                printLineas();
                posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + ESC + "|bC" + ESC + "|3C" + "TIPO DE CARGA : " + LF);
                printLineas();
                posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + ESC + "|4C" + concepto.getDescripcion() + LF);
                printLineas();
                posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + ESC + "|bC" + "Tipo de Origen: " + tipoOrigen + LF);
                posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + ESC + "|bC" + "Producto      : " + producto.getDescripcion() + LF);
                posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + ESC + "|bC" + "NroGuia       : " + ordenCargo.getNroGuia() + LF);
                posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + ESC + "|bC" + "Fecha Creacion: " + ordenCargo.getFechaCreacion() + LF);
                posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + ESC + "|bC" + "Fecha Accion  : " + ordenCargo.getFechaAccion() + LF);
                posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + "" + LF);
                posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + ESC + "|bC" + ESC + "|3C" + "ITEM COMPRA :  " + LF);
                printLineas();
                posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, mUnidadMedida);
                posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, mCantidad);
                posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, mFactorConversion);
                posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, mDensidad);
                posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, mPrecio);
                printLineas();
                posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + ESC + "|3C" + "Agente: " + persona.getPerVNombres() + " " + persona.getPerVApellidoPaterno() + " " + persona.getPerVApellidoMaterno() + LF);
                printLineas();
                posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + ESC + "|bC" + "Recibido por" + "" + LF);
                posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + "" + "" + LF);
                posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + ESC + "|bC" + "Nombre    :" + "..............................." + LF);
                posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + "" + "" + LF);
                posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + ESC + "|bC" + "DNI       :" + "..............................." + LF);
                posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + "" + "" + LF);
                posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + ESC + "|bC" + "Firma     :" + "..............................." + LF);
                posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + "" + "" + LF);
                posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + "Documento sin valor tributario" + LF);
                posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + "Consultar su comprobante de pago electronico en" + LF);
                posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + datosEmpresa.getUrl() + LF);
                posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + "" + LF);
                posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + "" + LF);
                posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + "" + LF);
            } catch (JposException e) {
                e.printStackTrace();
            }

        }
    }



    private void printLineas() throws JposException {
        posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|bC" + lineas + LF);
    }


}
