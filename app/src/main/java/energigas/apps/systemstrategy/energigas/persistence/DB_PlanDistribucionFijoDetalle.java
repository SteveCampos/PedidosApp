package energigas.apps.systemstrategy.energigas.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import energigas.apps.systemstrategy.energigas.entities.PlanDistribucionFijoDetalle;
import energigas.apps.systemstrategy.energigas.utils.Constants;

/**
 * Created by KelvinThony on 11/08/2016.
 */
public class DB_PlanDistribucionFijoDetalle {
    public static final String _id = "_id";
    public static final String pdrId = "pdrId";
    public static final String ruId = "ruId";
    public static final String pdId = "pdId";
    public static final String diaSemana = "diaSemana";
    public static final String meta = "meta";
    public static final String porcentaje = "porcentaje";
    public static final String usuarioAccion = "usuarioAccion";
    public static final String fechaAccion = "fechaAccion";
    public static final String estado = "estado";
    private DbHelper mDbHelper;
    private SQLiteDatabase mDb;
    private static final String SQLITE_TABLA_DB_PLANDISTRIBUCIONFIJODETALLE = "DB_PlanDistribucionFijoDetalle";
    private final Context mCtx;
    public static final String CREATE_TABLA_DB_PLANDISTRIBUCIONFIJODETALLE =
            "create table " + SQLITE_TABLA_DB_PLANDISTRIBUCIONFIJODETALLE + " ("
                    + _id + " integer primary key autoincrement,"
                    + pdrId + " integer ,"
                    + ruId + " integer ,"
                    + pdId + " integer ,"
                    + diaSemana + " text ,"
                    + meta + " real ,"
                    + porcentaje + " integer ,"
                    + usuarioAccion + " integer ,"
                    + fechaAccion + " text ,"
                    + estado + " integer ,"
                    + Constants._CLMEXPORT + " integer );";
    public static final String DELETE_TABLA_DB_PLANDISTRIBUCIONFIJODETALLE = "DROP TABLE IF EXISTS " + SQLITE_TABLA_DB_PLANDISTRIBUCIONFIJODETALLE;

    public DB_PlanDistribucionFijoDetalle(Context ctx) {
        this.mCtx = ctx;
    }

    public DB_PlanDistribucionFijoDetalle open() {
        mDbHelper = new DbHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        if (mDbHelper != null) {
            mDbHelper.close();
        }
    }

    public long createPlanDistribucionFijoDetalle(@NonNull PlanDistribucionFijoDetalle plandistribucionfijodetalle) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(pdrId, plandistribucionfijodetalle.getPdrId());
        initialValues.put(ruId, plandistribucionfijodetalle.getRuId());
        initialValues.put(pdId, plandistribucionfijodetalle.getPdId());
        initialValues.put(diaSemana, plandistribucionfijodetalle.getDiaSemana());
        initialValues.put(meta, plandistribucionfijodetalle.getMeta());
        initialValues.put(porcentaje, plandistribucionfijodetalle.getPorcentaje());
        initialValues.put(usuarioAccion, plandistribucionfijodetalle.getUsuarioAccion());
        initialValues.put(fechaAccion, plandistribucionfijodetalle.getFechaAccion());
        initialValues.put(estado, plandistribucionfijodetalle.isEstado());
        initialValues.put(Constants._CLMEXPORT, Constants._CREADO);
        return mDb.insert(SQLITE_TABLA_DB_PLANDISTRIBUCIONFIJODETALLE, null, initialValues);
    }

    public long updatePlanDistribucionFijoDetalle(@NonNull PlanDistribucionFijoDetalle plandistribucionfijodetalle) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(ruId, plandistribucionfijodetalle.getRuId());
        initialValues.put(pdId, plandistribucionfijodetalle.getPdId());
        initialValues.put(diaSemana, plandistribucionfijodetalle.getDiaSemana());
        initialValues.put(meta, plandistribucionfijodetalle.getMeta());
        initialValues.put(porcentaje, plandistribucionfijodetalle.getPorcentaje());
        initialValues.put(usuarioAccion, plandistribucionfijodetalle.getUsuarioAccion());
        initialValues.put(fechaAccion, plandistribucionfijodetalle.getFechaAccion());
        initialValues.put(estado, plandistribucionfijodetalle.isEstado());
        initialValues.put(Constants._CLMEXPORT, Constants._CREADO);
        return mDb.update(SQLITE_TABLA_DB_PLANDISTRIBUCIONFIJODETALLE, initialValues,
                pdrId + "=?", new String[]{"" + plandistribucionfijodetalle.getPdrId()});
    }
}
