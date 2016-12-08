package energigas.apps.systemstrategy.energigas.apiRest;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.orm.SugarRecord;

import java.util.ArrayList;
import java.util.List;

import energigas.apps.systemstrategy.energigas.entities.Almacen;
import energigas.apps.systemstrategy.energigas.entities.CajaLiquidacion;
import energigas.apps.systemstrategy.energigas.entities.Cliente;
import energigas.apps.systemstrategy.energigas.entities.Despacho;
import energigas.apps.systemstrategy.energigas.entities.Dispositivo;
import energigas.apps.systemstrategy.energigas.entities.DispositivoSerie;
import energigas.apps.systemstrategy.energigas.entities.Establecimiento;
import energigas.apps.systemstrategy.energigas.entities.GeoUbicacion;
import energigas.apps.systemstrategy.energigas.entities.Pedido;
import energigas.apps.systemstrategy.energigas.entities.PedidoDetalle;
import energigas.apps.systemstrategy.energigas.entities.Persona;
import energigas.apps.systemstrategy.energigas.entities.PlanDistribucion;
import energigas.apps.systemstrategy.energigas.entities.PlanDistribucionDetalle;
import energigas.apps.systemstrategy.energigas.entities.Product;
import energigas.apps.systemstrategy.energigas.entities.Producto;
import energigas.apps.systemstrategy.energigas.entities.Serie;
import energigas.apps.systemstrategy.energigas.entities.VehiculoDispositivo;
import energigas.apps.systemstrategy.energigas.utils.Constants;
import energigas.apps.systemstrategy.energigas.utils.Session;

/**
 * Created by kelvi on 05/09/2016.
 */

public class ManipuleData {
    private static final String TAG = "ManipuleData";

    public void saveLiquidacion(CajaLiquidacion cajaLiquidacion) {

        Long insert = cajaLiquidacion.save();
        SugarRecord.saveInTx(cajaLiquidacion.getItemsLiquidacion());
        boolean estadoB = true;
        if (insert > 0) {
            PlanDistribucion planDistribucion = cajaLiquidacion.getPlanDistribucionD();
            Long a = planDistribucion.save();
            Log.d(TAG, "ID PlanDistribucion " + a);

            List<PlanDistribucionDetalle> planDistribucionDetalles = planDistribucion.getItems();
            for (PlanDistribucionDetalle detalle : planDistribucionDetalles) {

                Long deta = detalle.save();
                if (deta < 0) {
                    estadoB = false;
                }
                Log.d(TAG, " PlanDistribucionDetalle " + deta);

            }

            Log.d(TAG, " INSERTO PLAN DISTRIBUCION DETALLE " + estadoB);
            estadoB = true;
            boolean estadoAl = true;
            boolean estadoEst = true;
            List<Cliente> clientes = cajaLiquidacion.getItemsClientes();

            for (Cliente cliente : clientes) {
                Long cli = cliente.save();
                if (cli < 0) {
                    estadoB = false;
                }
                Log.d(TAG, " INSERTO CLIENTE " + cli);
                /**Insertar Establecimientos**/

                for (Establecimiento establecimiento : cliente.getItemsEstablecimientos()) {
                    Long est = establecimiento.save();
                    if (est < 0) {
                        estadoEst = false;
                    }
                    Log.d(TAG, " INSERTO ESTABLECIMIENTO " + est);

                    /**Insertar Almacen**/

                    List<Almacen> almacens = establecimiento.getItemsAlmacen();
                    for (Almacen almacen : almacens) {
                        Long alm = almacen.save();
                        if (est < 0) {
                            estadoAl = false;
                        }
                        Log.d(TAG, " INSERTO ALMACEN " + alm);

                        Long geo = establecimiento.getUbicacion().save();
                        Log.d(TAG, " INSERTO GEOUBICACION " + geo);
                    }
                }


            }

            Log.d(TAG, " INSERTO CLIENTE " + estadoB);
            Log.d(TAG, " INSERTO ESTABLECIMIENTO " + estadoEst);
            Log.d(TAG, " INSERTO ALMACEN " + estadoAl);

            for (Cliente cliente : cajaLiquidacion.getItemsClientes()) {


                List<Establecimiento> establecimientos = cliente.getItemsEstablecimientos();
                SugarRecord.saveInTx(establecimientos);


                for (Establecimiento establecimiento : establecimientos) {

                    List<Almacen> almacens = establecimiento.getItemsAlmacen();
                    SugarRecord.saveInTx(almacens);
                    GeoUbicacion geoUbicacion = establecimiento.getUbicacion();
                    geoUbicacion.save();
                }

                Persona persona = cliente.getPersona();
                persona.save();
            }
            boolean estaPed = true;
            boolean estPedDet = true;
            int count = 0;
            boolean estSer = true;
            for (Serie serie : cajaLiquidacion.getItemsSeries()) {
                long inseSer = serie.save();
                if (inseSer < 0) {
                    estSer = false;
                }
            }
            Log.d(TAG, "Serie: " + estSer);

            for (Pedido pedido : cajaLiquidacion.getItemsPedidos()) {
                Long ped = pedido.save();
                if (ped < 0) {
                    estaPed = false;
                } else {
                    for (PedidoDetalle pedidoDetalle : pedido.getItems()) {
                        Long pediDet = pedidoDetalle.save();
                        if (pediDet < 0) {

                            estPedDet = false;
                        }
                        count++;
                    }
                }
            }
            List<PedidoDetalle> pedidoDetallea = PedidoDetalle.listAll(PedidoDetalle.class);
            for (PedidoDetalle pedido : pedidoDetallea) {
                Log.d(TAG, "idPedidoDetalle: " + pedido.getPdId() + " - idPedido :" + pedido.getPeId());
            }
            Log.d(TAG, " Pedido" + estaPed);
            Log.d(TAG, " Pedido detalle" + estPedDet + "-  " + count);
            boolean esDis = true;
            List<Dispositivo> dispositivoList = cajaLiquidacion.getItemsDispositivos();
            for (Dispositivo dispositivo : dispositivoList) {
                long inDis = dispositivo.save();
                if (inDis < 0) {
                    esDis = false;
                }
            }
            Log.d(TAG, " Dispositivo" + esDis);

            Long inserVeh = cajaLiquidacion.getVehiculo().save();
            Log.d(TAG, " Vehiculo" + (inserVeh));

            cajaLiquidacion.getVehiculo().getAlmacen().save();


            boolean estVeDis = true;

            for (VehiculoDispositivo vehiculoDispositivo : cajaLiquidacion.getItemsVehiculoDispositivo()) {
                long inDis = vehiculoDispositivo.save();
                if (inDis < 0) {
                    estVeDis = false;
                }
            }
            Log.d(TAG, " VehiculoDispositivo" + (estVeDis));
            Long inserVehUsu = cajaLiquidacion.getVehiculoUsuario().save();
            Log.d(TAG, " VehiculoUsuario" + (inserVehUsu));
            boolean estSerD = true;
            for (DispositivoSerie dispositivoSerie : cajaLiquidacion.getItemsDispositivoSeries()) {
                long insdisser = dispositivoSerie.save();
                if (insdisser < 0) {
                    estSerD = false;
                }
            }
            Log.d(TAG, " DispositivoSerie" + (estSerD));

        }
    }

    private String getQueryDespacho(Context context) {
        String[] params = Session.getIdsDespachos(context);
        String parmsQuery = "";
        for (int i = 0; i < params.length; i++) {

            if (i == (params.length - 1)) {
                parmsQuery = parmsQuery + params[i] + "";
            } else {
                parmsQuery = parmsQuery + params[i] + ",";
            }

        }

        return parmsQuery;
    }

    public List<Despacho> getDespachosToFactura(Context context) {
        String query = "SELECT * FROM DESPACHO WHERE  DESPACHO_ID IN (" + getQueryDespacho(context) + ") ORDER BY DESPACHO_ID ";
        return Despacho.findWithQuery(Despacho.class, query, null);
    }

    public List<Despacho> getDespachoResumen(Context context) {



        String query = " select id, despacho_Id,pe_Id,pd_Id,cliente_Id,establecimiento_Id,almacen_Est_Id,usuario_Id,placa,contador_Inicial_Origen,contador_Final_Origen,cantidad_Despachada,hora_Inicio,hora_Fin,fecha_Despacho,pro_Id,un_Id,p_IT_Origen,p_FT_Origen,latitud,longitud,almacen_Veh_Id,serie,numero,fecha_Creacion,usuario_Creacion,estado_Id,ve_Id,guia_Remision,liq_Id,SUM(PRECIO_UNITARIO_SIGV)  AS  PRECIO_UNITARIO_SIGV, SUM(precio_Unitario_CIGV)  AS  precio_Unitario_CIGV, SUM(por_Impuesto) as por_Impuesto ,SUM(costo_Venta) as costo_Venta ,SUM(importe) as importe,contador_Inicial_Destino,contador_Final_Destino,p_IT_Destino,p_FT_Destino,comp_Id from despacho  WHERE  DESPACHO_ID IN (" + getQueryDespacho(context) + ")  ; ";


        return Despacho.findWithQuery(Despacho.class, query, null);
    }


}
