package energigas.apps.systemstrategy.energigas.services;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.activities.CuentaResumenActivity;
import energigas.apps.systemstrategy.energigas.activities.MainStationActivity;
import energigas.apps.systemstrategy.energigas.activities.StationOrderActivity;
import energigas.apps.systemstrategy.energigas.broadcast.ScreenReceiver;
import energigas.apps.systemstrategy.energigas.entities.AlertaEntity;
import energigas.apps.systemstrategy.energigas.entities.CajaLiquidacion;
import energigas.apps.systemstrategy.energigas.entities.CajaLiquidacionDetalle;
import energigas.apps.systemstrategy.energigas.entities.Concepto;
import energigas.apps.systemstrategy.energigas.entities.NotificacionCajaDetalle;
import energigas.apps.systemstrategy.energigas.entities.NotificacionPedido;
import energigas.apps.systemstrategy.energigas.entities.NotificacionSaldoInicial;
import energigas.apps.systemstrategy.energigas.entities.Pedido;
import energigas.apps.systemstrategy.energigas.entities.PedidoDetalle;
import energigas.apps.systemstrategy.energigas.interfaces.ExportObjectsListener;
import energigas.apps.systemstrategy.energigas.utils.Constants;
import energigas.apps.systemstrategy.energigas.utils.NotificacionManagerUtils;
import energigas.apps.systemstrategy.energigas.utils.Session;

/**
 * Created by kelvi on 25/12/2016.
 */


public class ServiceSync extends Service {

    private BroadcastReceiver mReceiver;

    private DatabaseReference mDatabase;
    private DatabaseReference myRefFondos;
    private DatabaseReference mAtenPedidosPrecios;
    private DatabaseReference mAtenPedidosAgregados;
    private CajaLiquidacion cajaLiquidacion;
    private static final String TAG = "ServiceSync";
    private NotificacionManagerUtils notificar;
    private ChildEventListener childEventListener;
    private ChildEventListener childEventListenerPrecios;
    private ChildEventListener childEventListenerPedidosAgregados;

    private Concepto conceptoIGV;

    @Override
    public void onCreate() {


        registerListenerExport();
        if (FirebaseApp.getApps(this).isEmpty()) {
            FirebaseApp.initializeApp(getApplicationContext(), FirebaseOptions.fromResource(getApplicationContext()));

        }

        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        cajaLiquidacion = CajaLiquidacion.getCajaLiquidacion(Session.getCajaLiquidacion(this).getLiqId() + "");
        if (cajaLiquidacion != null) {
            listenerFirebaseFondos();
            listenerFirebasePrecios();
            listenerPedidosAgregados();
        }


        return Service.START_STICKY;
    }

    @Override
    public void onDestroy() {
        myRefFondos.removeEventListener(childEventListener);
        mAtenPedidosPrecios.removeEventListener(childEventListenerPrecios);
        mAtenPedidosAgregados.removeEventListener(childEventListenerPedidosAgregados);
    }


    private void registerListenerExport() {
        IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        filter.addAction(Intent.ACTION_USER_PRESENT);
        mReceiver = new ScreenReceiver();
        registerReceiver(mReceiver, filter);
    }

    private void listenerFirebaseFondos() {


        myRefFondos = mDatabase.child(Constants.FIREBASE_CHILD_FONDOS);

        childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Log.d("ServiceSync_service", "EXECUTE: " + dataSnapshot.getKey());
                NotificacionSaldoInicial notificacionSaldoInicial = dataSnapshot.getValue(NotificacionSaldoInicial.class);
                CajaLiquidacion liquidacionGuardar = CajaLiquidacion.getCajaLiquidacion(cajaLiquidacion.getLiqId() + "");
                Long liqFirebase = new Long(notificacionSaldoInicial.getLiqId());
                Long liqCaja = new Long(liquidacionGuardar.getLiqId());
                if (liqFirebase.equals(liqCaja)) {

                    if (!Session.isCantidadSaldoInicial(getApplicationContext(), notificacionSaldoInicial.getSaldoInicial() + "")) {
                        liquidacionGuardar.setSaldoInicial(notificacionSaldoInicial.getSaldoInicial());
                        Long aLong = liquidacionGuardar.save();
                        Log.d(TAG, "ACTUALIZO: " + aLong);
                        AlertaEntity alertaEntity = new AlertaEntity(1, "Saldo Inicial Modificado", "Su saldo inicial se ha modificado a " + liquidacionGuardar.getSaldoInicial(), R.drawable.logo, getApplicationContext(), CuentaResumenActivity.class, "IR", R.drawable.logo, null);
                        notificar(alertaEntity);
                    }


                }


            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };


        myRefFondos.addChildEventListener(childEventListener);

    }

    private void listenerFirebasePrecios() {
        mAtenPedidosPrecios = mDatabase.child(Constants.FIREBASE_CHILD_ATEN_PEDIDO).child(cajaLiquidacion.getLiqId() + "");
        childEventListenerPrecios = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                //Log.d(TAG, "PRECIO: -> " + dataSnapshot.getKey());
                //Log.d(TAG, "PRECIO: -> " + s);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Log.d(TAG, "PRECIO: -> " + dataSnapshot.getKey());
                Log.d(TAG, "PRECIO: -> " + s);
                System.out.print("Hola Objeto");
                System.out.print(dataSnapshot);
                conceptoIGV = Session.getConceptoIGV();
                NotificacionCajaDetalle notificacionCajaDetalle = dataSnapshot.getValue(NotificacionCajaDetalle.class);

                if (!Session.isUpdatePrecing(getApplicationContext(), notificacionCajaDetalle.getPrecio() + "")) {
                    Pedido pedido = Pedido.getPedidoById(notificacionCajaDetalle.getPeId() + "");
                    Bundle bundle = new Bundle();
                    bundle.putLong("ESTABLECIMIENTO", pedido.getEstablecimientoId());
                    double precioUnitario = (Double.parseDouble(conceptoIGV.getDescripcion()) * notificacionCajaDetalle.getPrecio()) + notificacionCajaDetalle.getPrecio();
                    PedidoDetalle pedidoDetalle = PedidoDetalle.getPedidoDetalleByPedido(pedido.getPeId() + "").get(0);
                    pedidoDetalle.setPrecio(notificacionCajaDetalle.getPrecio());
                    pedidoDetalle.setPrecio(precioUnitario);
                    Long estadoPedidoDetalle = pedidoDetalle.save();
                    // bundle.putLong("PEDIDO", pedido.getPeId());
                    if (estadoPedidoDetalle > 0) {
                        notificar(new AlertaEntity(2, "Precio Modificado", "Nuevo precio para el pedido: " + pedido.getSerie() + "-" + pedido.getNumero(), R.drawable.logo, getApplicationContext(), MainStationActivity.class, "Ir", R.drawable.logo, bundle));
                    }
                }


            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        mAtenPedidosPrecios.addChildEventListener(childEventListenerPrecios);
    }

    private void listenerPedidosAgregados() {

        mAtenPedidosAgregados = mDatabase.child(Constants.FIREBASE_CHILD_ATEN_ADD_PEDIDO);
        childEventListenerPedidosAgregados = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Log.d(TAG, "CantidadEjecuacion: KEY " + dataSnapshot.getKey());
                Long cajaLiqui = new Long(cajaLiquidacion.getLiqId());
                NotificacionPedido notificacionPedido = dataSnapshot.getValue(NotificacionPedido.class);

                if (notificacionPedido.getCajaLiquidacion().equals(cajaLiqui)) {
                    if (!Session.isAddPedido(getApplicationContext(), notificacionPedido.getCantidad())) {
                        Intent intentExportService = new Intent(getApplicationContext(), ServiceImport.class);
                        intentExportService.setAction(Constants.ACTION_IMPORT_SERVICE);
                        startService(intentExportService);
                    }

                }


            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        mAtenPedidosAgregados.addChildEventListener(childEventListenerPedidosAgregados);
    }

    private void notificar(AlertaEntity alertaEntity) {
        notificar = new NotificacionManagerUtils(alertaEntity);
        notificar.showNotificationCustom();
    }


}
