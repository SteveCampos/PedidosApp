package energigas.apps.systemstrategy.energigas.printingsheets;

import com.sewoo.jpos.POSPrinterService;
import com.sewoo.jpos.command.CPCL;
import com.sewoo.jpos.command.ESCPOS;
import com.sewoo.jpos.request.RequestQueue;

import java.text.DecimalFormat;

import energigas.apps.systemstrategy.energigas.entities.Almacen;
import energigas.apps.systemstrategy.energigas.entities.Cliente;
import energigas.apps.systemstrategy.energigas.entities.ComprobanteVenta;
import energigas.apps.systemstrategy.energigas.entities.ComprobanteVentaDetalle;
import energigas.apps.systemstrategy.energigas.entities.Despacho;
import energigas.apps.systemstrategy.energigas.entities.Dispatch;
import energigas.apps.systemstrategy.energigas.entities.Establecimiento;
import energigas.apps.systemstrategy.energigas.entities.Persona;
import energigas.apps.systemstrategy.energigas.entities.Producto;
import energigas.apps.systemstrategy.energigas.entities.Usuario;
import energigas.apps.systemstrategy.energigas.entities.Vehiculo;
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

    public SheetsPrintDispatch() {
        posPtr = new POSPrinterService();
        cpclPtr = new CPCL();
        rq = RequestQueue.getInstance();
    }


    public void printNowComprobante(Cliente cliente, ComprobanteVenta comprobanteVenta, Usuario usuario) {
        String codigoVenta = "MUOy4w/Q6VGqEBNiwQOhMYLCvm8";
        String url = "www.energigas.com";
        DecimalFormat df = new DecimalFormat("#.00");
        String tipoDocumento = "FACTURA ELECTRONICA";
        if (true) {
            tipoDocumento = "BOLETA ELECTRONICA";
        }

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
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + String.format("%-6s", "CANT") + String.format("%-30s", "Producto")  +  String.format("%-5s", "") + String.format("%-7s", "IMPORTE") + LF + "");
            printLineas();
            double importeTotal = 0.0;
            for (ComprobanteVentaDetalle comprobanteVentaDetalle : comprobanteVenta.getItemsDetalle()) {
                posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + String.format("%-6s", comprobanteVentaDetalle.getCantidad()) + String.format("%-30s", Producto.getNameProducto(comprobanteVentaDetalle.getProId() + ""))+ String.format("%-5s", "")+ String.format("%-9s",df.format(comprobanteVentaDetalle.getImporte())) + LF + "");

                importeTotal = importeTotal + comprobanteVentaDetalle.getImporte();
            }
            String gravada = ESC + "|lA" + String.format("%-18s", "OP. GRAVADA") + String.format("%-21s", "S/.") + String.format("%1$9s", df.format(importeTotal)) + LF;
            String inafecta = ESC + "|lA" + String.format("%-18s", "OP. INAFECTA") + String.format("%-21s", "S/.") + String.format("%1$9s", df.format(0.00)) + LF;
            String exonerada = ESC + "|lA" + String.format("%-18s", "OP. EXONERADA") + String.format("%-21s", "S/.") + String.format("%1$9s", df.format(0.00)) + LF;
            String gratuita = ESC + "|lA" + String.format("%-18s", "OP. GRATUITA") + String.format("%-21s", "S/.") + String.format("%1$9s", df.format(0.00)) + LF;
            String descuentos = ESC + "|lA" + String.format("%-18s", "DESCUENTOS") + String.format("%-21s", "S/.") + String.format("%1$9s", df.format(0.00)) + LF;
            String IGV = ESC + "|lA" + String.format("%-18s", "I.G.V.") + String.format("%-21s", "S/.") + String.format("%1$9s", df.format(0.00)) + LF;
            String rayaTotal = ESC + "|rA" + "---------" + LF;
            String precioVenta = String.format("%-18s", "PRECIO VENTA") + String.format("%-21s", "S/.") + String.format("%1$9s", df.format(importeTotal)) + LF;
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, gravada);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, inafecta);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, exonerada);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, gratuita);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, descuentos);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, IGV);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, rayaTotal);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, precioVenta);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + "" + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + comprobanteVenta.getValorResumen() + LF);
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
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + "DIRECCION" + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + "" + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + "telefono" + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + "Lima" + ", " + "Peru" + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + "RUC" + LF);//RUC
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

    private void printLineas() throws JposException {
        posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + lineas + LF);
    }

}
