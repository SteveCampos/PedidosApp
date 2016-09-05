package energigas.apps.systemstrategy.energigas.printingsheets;

import com.sewoo.jpos.POSPrinterService;
import com.sewoo.jpos.command.CPCL;
import com.sewoo.jpos.command.ESCPOS;
import com.sewoo.jpos.request.RequestQueue;

import energigas.apps.systemstrategy.energigas.entities.Almacen;
import energigas.apps.systemstrategy.energigas.entities.Cliente;
import energigas.apps.systemstrategy.energigas.entities.Despacho;
import energigas.apps.systemstrategy.energigas.entities.Dispatch;
import energigas.apps.systemstrategy.energigas.entities.Establecimiento;
import energigas.apps.systemstrategy.energigas.entities.Persona;
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

    public void printNow(Despacho dispatch, Almacen almacen, Establecimiento establecimiento, Vehiculo vehiculo, Persona agente, Cliente cliente) {
        try {
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + "" + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + "Energigas SAC" + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + "DIRECCION"+ LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + "" + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" +"telefono"+ LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + "Lima" + ", " + "Peru" + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + "RUC" + LF);//RUC
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + "Nombre: " + establecimiento.getEstVDescripcion() + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + "Direccion: " + establecimiento.getUbicacion().getDescripcion() + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + "Instalacion: " + almacen.getNombre() + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + "Coordenadas: " + dispatch.getLatitud()+", "+dispatch.getLongitud() + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + "Placa: " + vehiculo.getPlaca() + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + "Chofer: " + agente.getPerVNombres() +", "+agente.getPerVApellidoPaterno()  + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + "Serie Nro: " + dispatch.getSerie()+"-"+dispatch.getNumero() + LF);
            printLineas();
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + "DESPACHO" + LF);
            printLineas();
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + "Fecha: " + dispatch.getFechaDespacho() + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + "Hora Inicio: " + dispatch.getHoraInicio() + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + "Hora Fin: " + dispatch.getHoraFin() + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + "Contador Inicial: " + dispatch.getContadorInicial() + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + "Contador Final: " + dispatch.getContadorFinal() + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + "Cantidad Despachada: " + dispatch.getCantidadDespachada() + LF);
            printLineas();
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + "Porcentaje Tanque Inicial: " + dispatch.getpIT()+ LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + "Porcentaje Tanque Final: " + dispatch.getpFT() + LF);
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
