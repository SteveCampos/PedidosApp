package energigas.apps.systemstrategy.energigas.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import energigas.apps.systemstrategy.energigas.entities.PlanPago;
import energigas.apps.systemstrategy.energigas.utils.Constants;

/**
 * Created by KelvinThony on 11/08/2016.
 */
public class DB_PlanPago {
    public static final String _id = "_id";
    public static final String planPaId = "planPaId";
    public static final String compId = "compId";
    public static final String fechaPago = "fechaPago";
    public static final String serie = "serie";
    public static final String numDoc = "numDoc";
    public static final String glosa = "glosa";
    public static final String estado = "estado";
    public static final String porcentajeInteresMes = "porcentajeInteresMes";
    public static final String usuarioAccion = "usuarioAccion";
    public static final String fechaAccion = "fechaAccion";
    private DbHelper mDbHelper;
    private SQLiteDatabase mDb;
    private static final String SQLITE_TABLA_DB_PLANPAGO = "DB_PlanPago";
    private final Context mCtx;
    public static final String CREATE_TABLA_DB_PLANPAGO =
            "create table " + SQLITE_TABLA_DB_PLANPAGO + " ("
                    + _id + " integer primary key autoincrement,"
                    + planPaId + " integer ,"
                    + compId + " integer ,"
                    + fechaPago + " text ,"
                    + serie + " text ,"
                    + numDoc + " integer ,"
                    + glosa + " text ,"
                    + estado + " integer ,"
                    + porcentajeInteresMes + " real ,"
                    + usuarioAccion + " integer ,"
                    + fechaAccion + " text ,"
                    + Constants._CLMEXPORT + " integer );";
    public static final String DELETE_TABLA_DB_PLANPAGO = "DROP TABLE IF EXISTS " + SQLITE_TABLA_DB_PLANPAGO;

    public DB_PlanPago(Context ctx) {
        this.mCtx = ctx;
    }

    public DB_PlanPago open() {
        mDbHelper = new DbHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        if (mDbHelper != null) {
            mDbHelper.close();
        }
    }

    public long createPlanPago(@NonNull PlanPago planpago) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(planPaId, planpago.getPlanPaId());
        initialValues.put(compId, planpago.getCompId());
        initialValues.put(fechaPago, planpago.getFechaPago());
        initialValues.put(serie, planpago.getSerie());
        initialValues.put(numDoc, planpago.getNumDoc());
        initialValues.put(glosa, planpago.getGlosa());
        initialValues.put(estado, planpago.isEstado());
        initialValues.put(porcentajeInteresMes, planpago.getPorcentajeInteresMes());
        initialValues.put(usuarioAccion, planpago.getUsuarioAccion());
        initialValues.put(fechaAccion, planpago.getFechaAccion());
        initialValues.put(Constants._CLMEXPORT, Constants._CREADO);
        return mDb.insert(SQLITE_TABLA_DB_PLANPAGO, null, initialValues);
    }

    public long updatePlanPago(@NonNull PlanPago planpago) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(compId, planpago.getCompId());
        initialValues.put(fechaPago, planpago.getFechaPago());
        initialValues.put(serie, planpago.getSerie());
        initialValues.put(numDoc, planpago.getNumDoc());
        initialValues.put(glosa, planpago.getGlosa());
        initialValues.put(estado, planpago.isEstado());
        initialValues.put(porcentajeInteresMes, planpago.getPorcentajeInteresMes());
        initialValues.put(usuarioAccion, planpago.getUsuarioAccion());
        initialValues.put(fechaAccion, planpago.getFechaAccion());
        initialValues.put(Constants._CLMEXPORT, Constants._CREADO);
        return mDb.update(SQLITE_TABLA_DB_PLANPAGO, initialValues,
                planPaId + "=?", new String[]{"" + planpago.getPlanPaId()});
    }
}
