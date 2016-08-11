package energigas.apps.systemstrategy.energigas.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import energigas.apps.systemstrategy.energigas.entities.PedidoOperacion;
import energigas.apps.systemstrategy.energigas.utils.Constants;

/**
 * Created by KelvinThony on 11/08/2016.
 */
public class DB_PedidoOperacion {
    public static final String _id = "_id";
    public static final String peOpId = "peOpId";
    public static final String peId = "peId";
    public static final String opId = "opId";
    public static final String fechaInicio = "fechaInicio";
    public static final String fechaFin = "fechaFin";
    public static final String usuarioId = "usuarioId";
    public static final String estado = "estado";
    public static final String fecha = "fecha";
    public static final String observacion = "observacion";
    public static final String orden = "orden";
    private DbHelper mDbHelper;
    private SQLiteDatabase mDb;
    private static final String SQLITE_TABLA_DB_PEDIDOOPERACION = "DB_PedidoOperacion";
    private final Context mCtx;
    public static final String CREATE_TABLA_DB_PEDIDOOPERACION =
            "create table " + SQLITE_TABLA_DB_PEDIDOOPERACION + " ("
                    + _id + " integer primary key autoincrement,"
                    + peOpId + " integer ,"
                    + peId + " integer ,"
                    + opId + " integer ,"
                    + fechaInicio + " text ,"
                    + fechaFin + " text ,"
                    + usuarioId + " integer ,"
                    + estado + " integer ,"
                    + fecha + " text ,"
                    + observacion + " text ,"
                    + orden + " integer ,"
                    + Constants._CLMEXPORT + " integer );";
    public static final String DELETE_TABLA_DB_PEDIDOOPERACION = "DROP TABLE IF EXISTS " + SQLITE_TABLA_DB_PEDIDOOPERACION;

    public DB_PedidoOperacion(Context ctx) {
        this.mCtx = ctx;
    }

    public DB_PedidoOperacion open() {
        mDbHelper = new DbHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        if (mDbHelper != null) {
            mDbHelper.close();
        }
    }

    public long createPedidoOperacion(@NonNull PedidoOperacion pedidooperacion) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(peOpId, pedidooperacion.getPeOpId());
        initialValues.put(peId, pedidooperacion.getPeId());
        initialValues.put(opId, pedidooperacion.getOpId());
        initialValues.put(fechaInicio, pedidooperacion.getFechaInicio());
        initialValues.put(fechaFin, pedidooperacion.getFechaFin());
        initialValues.put(usuarioId, pedidooperacion.getUsuarioId());
        initialValues.put(estado, pedidooperacion.isEstado());
        initialValues.put(fecha, pedidooperacion.getFecha());
        initialValues.put(observacion, pedidooperacion.getObservacion());
        initialValues.put(orden, pedidooperacion.getOrden());
        initialValues.put(Constants._CLMEXPORT, Constants._CREADO);
        return mDb.insert(SQLITE_TABLA_DB_PEDIDOOPERACION, null, initialValues);
    }

    public long updatePedidoOperacion(@NonNull PedidoOperacion pedidooperacion) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(peId, pedidooperacion.getPeId());
        initialValues.put(opId, pedidooperacion.getOpId());
        initialValues.put(fechaInicio, pedidooperacion.getFechaInicio());
        initialValues.put(fechaFin, pedidooperacion.getFechaFin());
        initialValues.put(usuarioId, pedidooperacion.getUsuarioId());
        initialValues.put(estado, pedidooperacion.isEstado());
        initialValues.put(fecha, pedidooperacion.getFecha());
        initialValues.put(observacion, pedidooperacion.getObservacion());
        initialValues.put(orden, pedidooperacion.getOrden());
        initialValues.put(Constants._CLMEXPORT, Constants._CREADO);
        return mDb.update(SQLITE_TABLA_DB_PEDIDOOPERACION, initialValues,
                peOpId + "=?", new String[]{"" + pedidooperacion.getPeOpId()});
    }
}
