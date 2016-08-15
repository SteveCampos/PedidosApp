package energigas.apps.systemstrategy.energigas.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import energigas.apps.systemstrategy.energigas.entities.PlanDistribucionDetalle;
import energigas.apps.systemstrategy.energigas.utils.Constants;

/**
 * Created by KelvinThony on 11/08/2016.
 */
public class DB_PlanDistribucionDetalle {
    public static final String _id = "_id";
    public static final String pddId = "pddId";
    public static final String pdId = "pdId";
    public static final String establecimientoId = "establecimientoId";
    public static final String orden = "orden";
    public static final String estado = "estado";
    public static final String fechaActualizacion = "fechaActualizacion";
    public static final String usuarioActualizacion = "usuarioActualizacion";
    private DbHelper mDbHelper;
    private SQLiteDatabase mDb;
    private static final String SQLITE_TABLA_DB_PLANRUTADETALLE = "DB_PlanDistribucionDetalle";
    private final Context mCtx;
    public static final String CREATE_TABLA_DB_PLANRUTADETALLE =
            "create table " + SQLITE_TABLA_DB_PLANRUTADETALLE + " ("
                    + _id + " integer primary key autoincrement,"
                    + pddId + " integer ,"
                    + pdId + " integer ,"
                    + establecimientoId + " integer ,"
                    + orden + " integer ,"
                    + estado + " integer ,"
                    + fechaActualizacion + " text ,"
                    + usuarioActualizacion + " integer ,"
                    + Constants._CLMEXPORT + " integer );";
    public static final String DELETE_TABLA_DB_PLANRUTADETALLE = "DROP TABLE IF EXISTS " + SQLITE_TABLA_DB_PLANRUTADETALLE;

    public DB_PlanDistribucionDetalle(Context ctx) {
        this.mCtx = ctx;
    }

    public DB_PlanDistribucionDetalle open() {
        mDbHelper = new DbHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        if (mDbHelper != null) {
            mDbHelper.close();
        }
    }

    public long createPlanRutaDetalle(@NonNull PlanDistribucionDetalle planrutadetalle) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(pddId, planrutadetalle.getPddId());
        initialValues.put(pdId, planrutadetalle.getPdId());
        initialValues.put(establecimientoId, planrutadetalle.getEstablecimientoId());
        initialValues.put(orden, planrutadetalle.getOrden());
        initialValues.put(estado, planrutadetalle.isEstado());
        initialValues.put(fechaActualizacion, planrutadetalle.getFechaActualizacion());
        initialValues.put(usuarioActualizacion, planrutadetalle.getUsuarioActualizacion());
        initialValues.put(Constants._CLMEXPORT, Constants._CREADO);
        return mDb.insert(SQLITE_TABLA_DB_PLANRUTADETALLE, null, initialValues);
    }

    public long updatePlanRutaDetalle(@NonNull PlanDistribucionDetalle planrutadetalle) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(pdId, planrutadetalle.getPdId());
        initialValues.put(establecimientoId, planrutadetalle.getEstablecimientoId());
        initialValues.put(orden, planrutadetalle.getOrden());
        initialValues.put(estado, planrutadetalle.isEstado());
        initialValues.put(fechaActualizacion, planrutadetalle.getFechaActualizacion());
        initialValues.put(usuarioActualizacion, planrutadetalle.getUsuarioActualizacion());
        initialValues.put(Constants._CLMEXPORT, Constants._CREADO);
        return mDb.update(SQLITE_TABLA_DB_PLANRUTADETALLE, initialValues,
                pddId + "=?", new String[]{"" + planrutadetalle.getPddId()});
    }
}
