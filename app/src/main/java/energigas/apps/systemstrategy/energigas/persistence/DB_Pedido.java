package energigas.apps.systemstrategy.energigas.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import energigas.apps.systemstrategy.energigas.entities.Pedido;
import energigas.apps.systemstrategy.energigas.utils.Constants;

/**
 * Created by KelvinThony on 11/08/2016.
 */
public class DB_Pedido {
    public static final String _id = "_id";
    public static final String peId = "peId";
    public static final String serie = "serie";
    public static final String numero = "numero";
    public static final String tipoId = "tipoId";
    public static final String prioridadId = "prioridadId";
    public static final String clienteId = "clienteId";
    public static final String fechaPedido = "fechaPedido";
    public static final String fechaEntrega = "fechaEntrega";
    public static final String fechaEntregaProgramada = "fechaEntregaProgramada";
    public static final String estadoId = "estadoId";
    public static final String total = "total";
    public static final String consolidado = "consolidado";
    public static final String usuarioAccion = "usuarioAccion";
    public static final String fechaAccion = "fechaAccion";
    public static final String establecimientoId = "establecimientoId";
    public static final String direccionEntrega = "direccionEntrega";
    public static final String fechaRealEntrega = "fechaRealEntrega";
    public static final String usuarioEntrega = "usuarioEntrega";
    public static final String fechaCreacion = "fechaCreacion";
    public static final String usuarioCreacion = "usuarioCreacion";
    public static final String baseImponible = "baseImponible";
    public static final String iGV = "iGV";
    public static final String modalidadCreditoId = "modalidadCreditoId";
    public static final String veId = "veId";
    public static final String scop = "scop";
    public static final String horaInicio = "horaInicio";
    public static final String horaFin = "horaFin";
    public static final String horaEntrega = "horaEntrega";
    public static final String horario = "horario";
    public static final String vehiculoId = "vehiculoId";
    public static final String motivoCancelado = "motivoCancelado";
    public static final String motivoRevertido = "motivoRevertido";
    public static final String fechaAsignacionVehiculo = "fechaAsignacionVehiculo";
    public static final String usuarioAsignacionVehiculo = "usuarioAsignacionVehiculo";
    public static final String horaLlegada = "horaLlegada";
    public static final String horaSalida = "horaSalida";
    public static final String inclusion = "inclusion";
    public static final String comprobanteVenta = "comprobanteVenta";
    public static final String guiaRemision = "guiaRemision";
    public static final String horaProgramada = "horaProgramada";
    public static final String agenteId = "agenteId";
    public static final String scopCerrado = "scopCerrado";
    public static final String compId = "compId";
    public static final String greId = "greId";
    private DbHelper mDbHelper;
    private SQLiteDatabase mDb;
    private static final String SQLITE_TABLA_DB_PEDIDO = "DB_Pedido";
    private final Context mCtx;
    public static final String CREATE_TABLA_DB_PEDIDO =
            "create table " + SQLITE_TABLA_DB_PEDIDO + " ("
                    + _id + " integer primary key autoincrement,"
                    + peId + " integer ,"
                    + serie + " text ,"
                    + numero + " integer ,"
                    + tipoId + " integer ,"
                    + prioridadId + " integer ,"
                    + clienteId + " integer ,"
                    + fechaPedido + " text ,"
                    + fechaEntrega + " text ,"
                    + fechaEntregaProgramada + " text ,"
                    + estadoId + " integer ,"
                    + total + " real ,"
                    + consolidado + " integer ,"
                    + usuarioAccion + " integer ,"
                    + fechaAccion + " text ,"
                    + establecimientoId + " integer ,"
                    + direccionEntrega + " text ,"
                    + fechaRealEntrega + " text ,"
                    + usuarioEntrega + " integer ,"
                    + fechaCreacion + " text ,"
                    + usuarioCreacion + " integer ,"
                    + baseImponible + " real ,"
                    + iGV + " real ,"
                    + modalidadCreditoId + " integer ,"
                    + veId + " integer ,"
                    + scop + " text ,"
                    + horaInicio + " text ,"
                    + horaFin + " text ,"
                    + horaEntrega + " text ,"
                    + horario + " integer ,"
                    + vehiculoId + " integer ,"
                    + motivoCancelado + " text ,"
                    + motivoRevertido + " text ,"
                    + fechaAsignacionVehiculo + " text ,"
                    + usuarioAsignacionVehiculo + " integer ,"
                    + horaLlegada + " text ,"
                    + horaSalida + " text ,"
                    + inclusion + " integer ,"
                    + comprobanteVenta + " text ,"
                    + guiaRemision + " text ,"
                    + horaProgramada + " text ,"
                    + agenteId + " integer ,"
                    + scopCerrado + " integer ,"
                    + compId + " integer ,"
                    + greId + " integer ,"
                    + Constants._CLMEXPORT + " integer );";
    public static final String DELETE_TABLA_DB_PEDIDO = "DROP TABLE IF EXISTS " + SQLITE_TABLA_DB_PEDIDO;

    public DB_Pedido(Context ctx) {
        this.mCtx = ctx;
    }

    public DB_Pedido open() {
        mDbHelper = new DbHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        if (mDbHelper != null) {
            mDbHelper.close();
        }
    }

    public long createPedido(@NonNull Pedido pedido) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(peId, pedido.getPeId());
        initialValues.put(serie, pedido.getSerie());
        initialValues.put(numero, pedido.getNumero());
        initialValues.put(tipoId, pedido.getTipoId());
        initialValues.put(prioridadId, pedido.getPrioridadId());
        initialValues.put(clienteId, pedido.getClienteId());
        initialValues.put(fechaPedido, pedido.getFechaPedido());
        initialValues.put(fechaEntrega, pedido.getFechaEntrega());
        initialValues.put(fechaEntregaProgramada, pedido.getFechaEntregaProgramada());
        initialValues.put(estadoId, pedido.getEstadoId());
        initialValues.put(total, pedido.getTotal());
        initialValues.put(consolidado, pedido.isConsolidado());
        initialValues.put(usuarioAccion, pedido.getUsuarioAccion());
        initialValues.put(fechaAccion, pedido.getFechaAccion());
        initialValues.put(establecimientoId, pedido.getEstablecimientoId());
        initialValues.put(direccionEntrega, pedido.getDireccionEntrega());
        initialValues.put(fechaRealEntrega, pedido.getFechaRealEntrega());
        initialValues.put(usuarioEntrega, pedido.getUsuarioEntrega());
        initialValues.put(fechaCreacion, pedido.getFechaCreacion());
        initialValues.put(usuarioCreacion, pedido.getUsuarioCreacion());
        initialValues.put(baseImponible, pedido.getBaseImponible());
        initialValues.put(iGV, pedido.getiGV());
        initialValues.put(modalidadCreditoId, pedido.getModalidadCreditoId());
        initialValues.put(veId, pedido.getVeId());
        initialValues.put(scop, pedido.getScop());
        initialValues.put(horaInicio, pedido.getHoraInicio());
        initialValues.put(horaFin, pedido.getHoraFin());
        initialValues.put(horaEntrega, pedido.getHoraEntrega());
        initialValues.put(horario, pedido.isHorario());
        initialValues.put(vehiculoId, pedido.getVehiculoId());
        initialValues.put(motivoCancelado, pedido.getMotivoCancelado());
        initialValues.put(motivoRevertido, pedido.getMotivoRevertido());
        initialValues.put(fechaAsignacionVehiculo, pedido.getFechaAsignacionVehiculo());
        initialValues.put(usuarioAsignacionVehiculo, pedido.getUsuarioAsignacionVehiculo());
        initialValues.put(horaLlegada, pedido.getHoraLlegada());
        initialValues.put(horaSalida, pedido.getHoraSalida());
        initialValues.put(inclusion, pedido.isInclusion());
        initialValues.put(comprobanteVenta, pedido.getComprobanteVenta());
        initialValues.put(guiaRemision, pedido.getGuiaRemision());
        initialValues.put(horaProgramada, pedido.getHoraProgramada());
        initialValues.put(agenteId, pedido.getAgenteId());
        initialValues.put(scopCerrado, pedido.isScopCerrado());
        initialValues.put(compId, pedido.getCompId());
        initialValues.put(greId, pedido.getGreId());
        initialValues.put(Constants._CLMEXPORT, Constants._CREADO);
        return mDb.insert(SQLITE_TABLA_DB_PEDIDO, null, initialValues);
    }

    public long updatePedido(@NonNull Pedido pedido) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(serie, pedido.getSerie());
        initialValues.put(numero, pedido.getNumero());
        initialValues.put(tipoId, pedido.getTipoId());
        initialValues.put(prioridadId, pedido.getPrioridadId());
        initialValues.put(clienteId, pedido.getClienteId());
        initialValues.put(fechaPedido, pedido.getFechaPedido());
        initialValues.put(fechaEntrega, pedido.getFechaEntrega());
        initialValues.put(fechaEntregaProgramada, pedido.getFechaEntregaProgramada());
        initialValues.put(estadoId, pedido.getEstadoId());
        initialValues.put(total, pedido.getTotal());
        initialValues.put(consolidado, pedido.isConsolidado());
        initialValues.put(usuarioAccion, pedido.getUsuarioAccion());
        initialValues.put(fechaAccion, pedido.getFechaAccion());
        initialValues.put(establecimientoId, pedido.getEstablecimientoId());
        initialValues.put(direccionEntrega, pedido.getDireccionEntrega());
        initialValues.put(fechaRealEntrega, pedido.getFechaRealEntrega());
        initialValues.put(usuarioEntrega, pedido.getUsuarioEntrega());
        initialValues.put(fechaCreacion, pedido.getFechaCreacion());
        initialValues.put(usuarioCreacion, pedido.getUsuarioCreacion());
        initialValues.put(baseImponible, pedido.getBaseImponible());
        initialValues.put(iGV, pedido.getiGV());
        initialValues.put(modalidadCreditoId, pedido.getModalidadCreditoId());
        initialValues.put(veId, pedido.getVeId());
        initialValues.put(scop, pedido.getScop());
        initialValues.put(horaInicio, pedido.getHoraInicio());
        initialValues.put(horaFin, pedido.getHoraFin());
        initialValues.put(horaEntrega, pedido.getHoraEntrega());
        initialValues.put(horario, pedido.isHorario());
        initialValues.put(vehiculoId, pedido.getVehiculoId());
        initialValues.put(motivoCancelado, pedido.getMotivoCancelado());
        initialValues.put(motivoRevertido, pedido.getMotivoRevertido());
        initialValues.put(fechaAsignacionVehiculo, pedido.getFechaAsignacionVehiculo());
        initialValues.put(usuarioAsignacionVehiculo, pedido.getUsuarioAsignacionVehiculo());
        initialValues.put(horaLlegada, pedido.getHoraLlegada());
        initialValues.put(horaSalida, pedido.getHoraSalida());
        initialValues.put(inclusion, pedido.isInclusion());
        initialValues.put(comprobanteVenta, pedido.getComprobanteVenta());
        initialValues.put(guiaRemision, pedido.getGuiaRemision());
        initialValues.put(horaProgramada, pedido.getHoraProgramada());
        initialValues.put(agenteId, pedido.getAgenteId());
        initialValues.put(scopCerrado, pedido.isScopCerrado());
        initialValues.put(compId, pedido.getCompId());
        initialValues.put(greId, pedido.getGreId());
        initialValues.put(Constants._CLMEXPORT, Constants._CREADO);
        return mDb.update(SQLITE_TABLA_DB_PEDIDO, initialValues,
                peId + "=?", new String[]{"" + pedido.getPeId()});
    }
}
