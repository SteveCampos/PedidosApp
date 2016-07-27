package energigas.apps.systemstrategy.energigas.printingsheets;

import com.sewoo.jpos.POSPrinterService;
import com.sewoo.jpos.command.CPCL;
import com.sewoo.jpos.command.ESCPOS;
import com.sewoo.jpos.request.RequestQueue;

import energigas.apps.systemstrategy.energigas.entities.Dispatch;
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

    public void printNow(Dispatch dispatch) {
        try {
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + "" + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + dispatch.getNameCompany() + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + dispatch.getAddressCompany() + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + dispatch.getDistrictCompany() + " " + dispatch.getDepartmentCompany() + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + dispatch.getTelephoneCompany() + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + dispatch.getDepartmentCompany() + " " + dispatch.getCountryCompany() + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + dispatch.getRucCompany() + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + "Nombre: " + dispatch.getNameOfDispacth() + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + "Direccion: " + dispatch.getAddressOfDispacth() + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + "Instalacion: " + dispatch.getInstalationOdDispatch() + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + "Coordenadas: " + dispatch.getCoordinatesLatLon() + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + "Placa: " + dispatch.getNumberPlate() + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + "Chofer: " + dispatch.getDriverCar() + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + "Nro Comprobante: " + dispatch.getNumberPayment() + LF);
            printLineas();
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" + "DESPACHO" + LF);
            printLineas();
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + "Fecha: " + dispatch.getDate() + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + "Hora Inicio: " + dispatch.getTimeStart() + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + "Hora Fin: " + dispatch.getTimeEnd() + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + "Contador Inicial: " + dispatch.getCountStart() + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + "Contador Final: " + dispatch.getCountEnd() + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + "Cantidad Despachada: " + dispatch.getQuantityDispatch() + LF);
            printLineas();
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + "Porcentaje Tanque Inicial: " + dispatch.getPercentageStart() + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + "Porcentaje Tanque Final: " + dispatch.getPercentageEnd() + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + "Serie Tanque: " + dispatch.getSerialTank() + LF);
            posPtr.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|lA" + "Nro Transporte: " + dispatch.getNumberTransport() + LF);
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
