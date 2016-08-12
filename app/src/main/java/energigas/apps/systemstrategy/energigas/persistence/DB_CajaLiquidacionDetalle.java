package energigas.apps.systemstrategy.energigas.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import energigas.apps.systemstrategy.energigas.entities.CajaLiquidacionDetalle;
import energigas.apps.systemstrategy.energigas.utils.Constants;

/**
 * Created by KelvinThony on 11/08/2016.
 */
public class DB_CajaLiquidacionDetalle {
    public static final String _id = "_id";
    public static final String lidId = "lidId";
    public static final String liId = "liId";
    public static final String establecimientoId = "establecimientoId";
    public static final String fecha = "fecha";
    public static final String fechaAccion = "fechaAccion";
    public static final String motivoNoAtencionId = "motivoNoAtencionId";
    public static final String estadoId = "estadoId";
    public static final String orden = "orden";
    public static final String fechaAtencion = "fechaAtencion";
    public static final String peId = "peId";
    private DbHelper mDbHelper;
    private SQLiteDatabase mDb;
    private static final String SQLITE_TABLA_DB_CAJALIQUIDACIONDETALLE = "DB_CajaLiquidacionDetalle";
    private final Context mCtx;
    public static final String CREATE_TABLA_DB_CAJALIQUIDACIONDETALLE =
            "create table " + SQLITE_TABLA_DB_CAJALIQUIDACIONDETALLE + " ("
                    + _id + " integer primary key autoincrement,"
                    + lidId + " integer ,"
                    + liId + " integer ,"
                    + establecimientoId + " integer ,"
                    + fecha + " text ,"
                    + fechaAccion + " text ,"
                    + motivoNoAtencionId + " integer ,"
                    + estadoId + " integer ,"
                    + orden + " integer ,"
                    + fechaAtencion + " text ,"
                    + peId + " integer ,"
                    + Constants._CLMEXPORT + " integer );";
    public static final String DELETE_TABLA_DB_CAJALIQUIDACIONDETALLE = "DROP TABLE IF EXISTS " + SQLITE_TABLA_DB_CAJALIQUIDACIONDETALLE;

    public DB_CajaLiquidacionDetalle(Context ctx) {
        this.mCtx = ctx;
    }

    public DB_CajaLiquidacionDetalle open() {
        mDbHelper = new DbHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        if (mDbHelper != null) {
            mDbHelper.close();
        }
    }

    public long createCajaLiquidacionDetalle(@NonNull CajaLiquidacionDetalle cajaliquidaciondetalle) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(lidId, cajaliquidaciondetalle.getLidId());
        initialValues.put(liId, cajaliquidaciondetalle.getLiId());
        initialValues.put(establecimientoId, cajaliquidaciondetalle.getEstablecimientoId());
        initialValues.put(fecha, cajaliquidaciondetalle.getFecha());
        initialValues.put(fechaAccion, cajaliquidaciondetalle.getFechaAccion());
        initialValues.put(motivoNoAtencionId, cajaliquidaciondetalle.getMotivoNoAtencionId());
        initialValues.put(estadoId, cajaliquidaciondetalle.getEstadoId());
        initialValues.put(orden, cajaliquidaciondetalle.getOrden());
        initialValues.put(fechaAtencion, cajaliquidaciondetalle.getFechaAtencion());
        initialValues.put(peId, cajaliquidaciondetalle.getPeId());
        initialValues.put(Constants._CLMEXPORT, Constants._CREADO);
        return mDb.insert(SQLITE_TABLA_DB_CAJALIQUIDACIONDETALLE, null, initialValues);
    }

    public long updateCajaLiquidacionDetalle(@NonNull CajaLiquidacionDetalle cajaliquidaciondetalle) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(liId, cajaliquidaciondetalle.getLiId());
        initialValues.put(establecimientoId, cajaliquidaciondetalle.getEstablecimientoId());
        initialValues.put(fecha, cajaliquidaciondetalle.getFecha());
        initialValues.put(fechaAccion, cajaliquidaciondetalle.getFechaAccion());
        initialValues.put(motivoNoAtencionId, cajaliquidaciondetalle.getMotivoNoAtencionId());
        initialValues.put(estadoId, cajaliquidaciondetalle.getEstadoId());
        initialValues.put(orden, cajaliquidaciondetalle.getOrden());
        initialValues.put(fechaAtencion, cajaliquidaciondetalle.getFechaAtencion());
        initialValues.put(peId, cajaliquidaciondetalle.getPeId());
        initialValues.put(Constants._CLMEXPORT, Constants._CREADO);
        return mDb.update(SQLITE_TABLA_DB_CAJALIQUIDACIONDETALLE, initialValues,
                lidId + "=?", new String[]{"" + cajaliquidaciondetalle.getLidId()});
    }
}
