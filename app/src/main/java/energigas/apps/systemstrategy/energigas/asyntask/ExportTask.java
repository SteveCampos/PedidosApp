package energigas.apps.systemstrategy.energigas.asyntask;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import energigas.apps.systemstrategy.energigas.apiRest.RestAPI;
import energigas.apps.systemstrategy.energigas.entities.BERespuestaCajaEgreso;
import energigas.apps.systemstrategy.energigas.entities.BERespuestaCajaIngreso;
import energigas.apps.systemstrategy.energigas.entities.BERespuestaCpVenta;
import energigas.apps.systemstrategy.energigas.entities.BERespuestaCpVentaDetalle;
import energigas.apps.systemstrategy.energigas.entities.BERespuestaDespacho;
import energigas.apps.systemstrategy.energigas.entities.BERespuestaPlanPagoDetalle;
import energigas.apps.systemstrategy.energigas.entities.CajaComprobante;
import energigas.apps.systemstrategy.energigas.entities.CajaGasto;
import energigas.apps.systemstrategy.energigas.entities.CajaMovimiento;
import energigas.apps.systemstrategy.energigas.entities.CajaPago;
import energigas.apps.systemstrategy.energigas.entities.ComprobanteVenta;
import energigas.apps.systemstrategy.energigas.entities.ComprobanteVentaDetalle;
import energigas.apps.systemstrategy.energigas.entities.Despacho;
import energigas.apps.systemstrategy.energigas.entities.InformeGasto;
import energigas.apps.systemstrategy.energigas.entities.PlanPago;
import energigas.apps.systemstrategy.energigas.entities.PlanPagoDetalle;
import energigas.apps.systemstrategy.energigas.entities.SyncEstado;
import energigas.apps.systemstrategy.energigas.interfaces.ExportObjectsListener;
import energigas.apps.systemstrategy.energigas.utils.Constants;
import energigas.apps.systemstrategy.energigas.utils.NetworkUtil;
import energigas.apps.systemstrategy.energigas.utils.Session;
import energigas.apps.systemstrategy.energigas.utils.Utils;

/**
 * Created by kelvi on 27/10/2016.
 */

public class ExportTask extends AsyncTask<Integer, String, String> {


    private static final String TAG = "ServiceExport";
    private RestAPI restAPI;
    private JSONObject jsonObjectResponse;
    private ObjectMapper mapper;
    private ExportObjectsListener exportObjectsListener;
    private Context context;
    private int mainEstado=0;

    public ExportTask(ExportObjectsListener exportObjectsListener, Context context) {
        this.context = context;
        this.exportObjectsListener = exportObjectsListener;
        this.restAPI = new RestAPI();
        this.mapper = new ObjectMapper();

    }

    @Override
    protected String doInBackground(Integer... params) {

        if (!NetworkUtil.hasActiveInternetConnection(context)) {
            exportObjectsListener.onLoadError("Error de conexion");
            return null;
        }

        int table = params[0];
        int type = params[1];

        switch (table) {

            case Constants.TABLA_DESPACHO:
                exportCreatedDespacho(type);
                break;
            case Constants.TABLA_COMPROBANTE:
                exportCreatedComprobanteVenta(type);
                break;
            case Constants.TABLA_GASTO:
                exportCreatedGasto(type);
                break;

        }


        return null;
    }


    private void exportCreatedDespacho(int estado) {


        /**Use Reflection**/
        ArrayList<Object> inIdDespacho = new ArrayList<Object>(Utils.getListForExIn(Despacho.class, estado));
        Log.d(TAG, "cantidad despacho: " + inIdDespacho.size());
        if (inIdDespacho.size() > 0) {

            String listString = Utils.getListStringFrom(inIdDespacho);
            Log.d(TAG, "JSON: " + listString);
            if (listString != null) {

                try {
                    jsonObjectResponse = restAPI.fins_GuardarDespacho(inIdDespacho);
                    Log.d(TAG, "JSON RESPONSE: " + jsonObjectResponse);
                    if (Utils.isSuccessful(jsonObjectResponse)) {

                        List<Despacho> despachoList = (List<Despacho>) (Object) ((List<Object>) inIdDespacho);
                        List<BERespuestaDespacho> beRespuestaDespachoList = mapper.readValue(Utils.getJsonArResult(jsonObjectResponse), TypeFactory.defaultInstance().constructCollectionType(List.class,
                                BERespuestaDespacho.class));

                        for (BERespuestaDespacho respuestaDespacho : beRespuestaDespachoList) {

                            for (Despacho despacho : despachoList) {

                                if (despacho.getId() == respuestaDespacho.getDespachoIdAndroid()) {
                                    SyncEstado syncEstado = SyncEstado.find(SyncEstado.class, " campo_Id=? and nombre_Tabla = ? ", new String[]{despacho.getId() + "", Utils.separteUpperCase(Despacho.class.getSimpleName())}).get(0);
                                    syncEstado.setSyncIdRemoto(Integer.parseInt(respuestaDespacho.getDespachoIdServer() + ""));
                                    syncEstado.setEstadoSync(Constants.S_IMPORTADO);
                                    syncEstado.save();
                                    despacho.setDespachoId(respuestaDespacho.getDespachoIdServer());
                                    despacho.save();
                                    Log.d(TAG,""+despacho.getId()+"---"+Session.getDespacho(context).getId());
                                    boolean es = despacho.getId().equals(Session.getDespacho(context).getId());
                                    if (es){
                                        Session.saveDespacho(context, despacho);
                                        mainEstado = 1;
                                    }

                                }

                            }

                            if (new ArrayList<Object>(Utils.getListForExIn(Despacho.class, estado)).size() == 0) {
                               // exportObjectsListener.onLoadSuccess("Exportado Exitosamente");
                            }

                        }
                    } else {
                       // exportObjectsListener.onLoadErrorProcedure("Error de Procedimiento");
                    }
                    mainEstado = 1;


                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(TAG, "ERROR: " + e.getMessage());
                    mainEstado = -1;
                 //   exportObjectsListener.onLoadError(e.getMessage());
                }
            }
        }
    }

    private void exportCreatedComprobanteVenta(int estado) {
        jsonObjectResponse = null;

        /**Use Reflection**/
        List<ComprobanteVenta> inAComprobanteVenta = ComprobanteVenta.getComprobanteVentas(new ArrayList<ComprobanteVenta>(Utils.getListForExIn(ComprobanteVenta.class, estado)));
        Log.d(TAG, "cantidad venta: " + inAComprobanteVenta.size());


        if (inAComprobanteVenta.size() > 0) {
            try {
                jsonObjectResponse = restAPI.fins_SaveComprobanteVenta(new ArrayList<Object>(inAComprobanteVenta));
                Log.d(TAG, "JSON RESPONSE: " + jsonObjectResponse);
                if (Utils.isSuccessful(jsonObjectResponse)) {

                    List<BERespuestaCpVenta> beRespuestaCpVentas = mapper.readValue(Utils.getJsonArResult(jsonObjectResponse), TypeFactory.defaultInstance().constructCollectionType(List.class,
                            BERespuestaCpVenta.class));
                    for (BERespuestaCpVenta beRespuestaCpVenta : beRespuestaCpVentas) {

                        for (ComprobanteVenta comprobanteVenta : inAComprobanteVenta) { /**Comprobante de Venta**/

                            if (comprobanteVenta.getId() == beRespuestaCpVenta.getCompIdAndroid()) {
                                saveEstado(comprobanteVenta.getId() + "", beRespuestaCpVenta.getCompIdServer() + "", ComprobanteVenta.class);
                                comprobanteVenta.setCompId(beRespuestaCpVenta.getCompIdServer());
                                Long es = comprobanteVenta.save();
                                Log.d(TAG, "ComprobanteVenta " + es);
                            }

                            for (BERespuestaCpVentaDetalle respuestaCpVentaDetalle : beRespuestaCpVenta.getItemsCpVenta()) {

                                for (ComprobanteVentaDetalle ventaDetalle : comprobanteVenta.getItemsDetalle()) { /**Comprobante Venta Detalle**/

                                    if (ventaDetalle.getId() == respuestaCpVentaDetalle.getCompdIdAndroid()) {

                                        ventaDetalle.setCompId(beRespuestaCpVenta.getCompIdServer());
                                        ventaDetalle.setCompdId(respuestaCpVentaDetalle.getCompdIdServer());
                                        Long sD = ventaDetalle.save();
                                        saveEstado(ventaDetalle.getId() + "", respuestaCpVentaDetalle.getCompdIdServer() + "", ComprobanteVentaDetalle.class);
                                        Log.d(TAG, "ComprobanteVentaDetalle " + sD);
                                    }
                                }

                            }

                            //Es al credito
                            if (comprobanteVenta.getPlanPago() != null) {

                                /**Plan Pago**/
                                PlanPago planPago = comprobanteVenta.getPlanPago();
                                saveEstado(planPago.getId() + "", beRespuestaCpVenta.getPlanPago().getPlanPaIdServer() + "", PlanPago.class);
                                planPago.setCompId(beRespuestaCpVenta.getCompIdServer());
                                planPago.setPlanPaId(beRespuestaCpVenta.getPlanPago().getPlanPaIdServer());
                                Long pp = planPago.save();
                                Log.d(TAG, "PlanPago " + pp);

                                for (BERespuestaPlanPagoDetalle beRespuestaPlanPagoDetalle : beRespuestaCpVenta.getPlanPago().getItemsPlan()) { /**Plan pago detalle**/

                                    for (PlanPagoDetalle planPagoDetalle : planPago.getDetalle()) {

                                        if (planPagoDetalle.getId() == beRespuestaPlanPagoDetalle.getPlanPaDeIdAndroid()) {
                                            saveEstado(planPagoDetalle.getId() + "", beRespuestaPlanPagoDetalle.getPlanPaDeIdServer() + "", PlanPagoDetalle.class);
                                            planPagoDetalle.setPlanPaId(planPago.getPlanPaId());
                                            planPagoDetalle.setPlanPaDeId(beRespuestaPlanPagoDetalle.getPlanPaDeIdServer());
                                            Long ppd = planPagoDetalle.save();
                                            Log.d(TAG, "PlanPagoDetalle " + ppd);

                                        }

                                    }

                                }


                            } else {//Es al contado

                                /**Caja Movimiento**/
                                CajaMovimiento cajaMovimiento = comprobanteVenta.getCajaMovimiento();
                                saveEstado(cajaMovimiento.getId() + "", beRespuestaCpVenta.getCajaIngreso().getCajMovIdServer() + "", CajaMovimiento.class);
                                cajaMovimiento.setCajMovId(beRespuestaCpVenta.getCajaIngreso().getCajMovIdServer());
                                cajaMovimiento.save();

                                /**Caja pago**/
                                CajaPago cajaPago = comprobanteVenta.getCajaMovimiento().getPago();
                                saveEstado(cajaPago.getId() + "", beRespuestaCpVenta.getCajaIngreso().getCajPagIdServer() + "", CajaPago.class);
                                cajaPago.setCajMovId(beRespuestaCpVenta.getCajaIngreso().getCajMovIdServer());
                                cajaPago.setCajPagId(beRespuestaCpVenta.getCajaIngreso().getCajPagIdServer());
                                cajaPago.save();

                                /**Caja Comprobante**/

                                CajaComprobante cajaComprobante = comprobanteVenta.getCajaMovimiento().getComprobante();
                                saveEstado(cajaComprobante.getId() + "", beRespuestaCpVenta.getCajaIngreso().getCajCompIdServer() + "", CajaComprobante.class);
                                cajaComprobante.setCajMovId(beRespuestaCpVenta.getCajaIngreso().getCajMovIdServer());
                                cajaComprobante.setCajCompId(beRespuestaCpVenta.getCajaIngreso().getCajCompIdServer());
                                cajaComprobante.save();


                            }


                        }

                        if (new ArrayList<Object>(Utils.getListForExIn(ComprobanteVenta.class, estado)).size() == 0) {
                            exportObjectsListener.onLoadSuccess("Exportado Exitosamente");
                        }


                    }


                } else {
                    exportObjectsListener.onLoadErrorProcedure("Error de Procedimiento");
                }


            } catch (Exception e) {
                e.printStackTrace();
                Log.e(TAG, "ERROR: " + e.getMessage());
                exportObjectsListener.onLoadError(e.getMessage());
            }
        }


    }


    private void exportCreatedGasto(int estado) {
        jsonObjectResponse = null;
        List<CajaMovimiento> informeGastoList = CajaMovimiento.getListCajaMovimiento(new ArrayList<CajaMovimiento>(Utils.getListForExIn(CajaMovimiento.class, estado)));
        if (informeGastoList.size()>0){
            Log.d(TAG, "cantidad gasto: " + informeGastoList.get(0).getInfGasto().getInfGasId() + "-" + informeGastoList.get(0).getGasto().getCajGasId());
        }
        if (informeGastoList.size()>0) {
            try {
                jsonObjectResponse = restAPI.fins_SaveGasto(new ArrayList<Object>(informeGastoList));
                Log.d(TAG, "JSON RESPONSE: " + jsonObjectResponse.toString());
                if (Utils.isSuccessful(jsonObjectResponse)) {
                    List<BERespuestaCajaEgreso> beRespuestaCpVentas = mapper.readValue(Utils.getJsonArResult(jsonObjectResponse), TypeFactory.defaultInstance().constructCollectionType(List.class,
                            BERespuestaCajaEgreso.class));
                    for (BERespuestaCajaEgreso respuestaCajaEgreso : beRespuestaCpVentas) {
                        for (CajaMovimiento cajaMovimiento : informeGastoList) {
                            if (respuestaCajaEgreso.getCajMovIdAndroid() == cajaMovimiento.getGasto().getCajMoId()) {
                                CajaMovimiento movimiento = cajaMovimiento;
                                CajaGasto cajaGasto = cajaMovimiento.getGasto();
                                InformeGasto informeGasto = cajaMovimiento.getInfGasto();
                                movimiento.setCajMovId(respuestaCajaEgreso.getCajMovIdServer());
                                cajaGasto.setCajGasId(respuestaCajaEgreso.getCajGasIdServer());
                                cajaGasto.setCajMoId(respuestaCajaEgreso.getCajMovIdServer());
                                informeGasto.setInfGasId(respuestaCajaEgreso.getInfGasIdServer());
                                informeGasto.setCajGasId(respuestaCajaEgreso.getCajGasIdServer());

                                Long lMov = movimiento.save();
                                Long lCaj = cajaGasto.save();
                                Long lInfGas = informeGasto.save();

                                Log.d(TAG, "lMov: " + lMov + " lCaj: " + lCaj + " lInfGas: " + lInfGas);

                            }

                        }
                    }
                }

            } catch (Exception e) {
                Log.d(TAG, "ERROR: " + e.getMessage());
                e.printStackTrace();
            }

        }
    }

    private void saveEstado(String idAndroid, String idRemoto, Class aClass) {
        SyncEstado estado = SyncEstado.find(SyncEstado.class, " campo_Id=? and nombre_Tabla = ? ", new String[]{idAndroid, Utils.separteUpperCase(aClass.getSimpleName())}).get(0);
        estado.setSyncIdRemoto(Integer.parseInt(idRemoto));
        estado.setEstadoSync(Constants.S_IMPORTADO);
        estado.save();
    }


    @Override
    protected void onPostExecute(String s) {

        Log.d(TAG,"ESTADO: "+mainEstado);
      switch (mainEstado){
          case 0:
              exportObjectsListener.onLoadErrorProcedure("error");
              break;
          case -1:
              exportObjectsListener.onLoadErrorProcedure("error");
              break;
          case 1:
              exportObjectsListener.onLoadSuccess("Exito");
              break;
      }
    }
}
