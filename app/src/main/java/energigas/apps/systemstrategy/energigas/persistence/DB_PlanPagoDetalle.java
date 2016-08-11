package energigas.apps.systemstrategy.energigas.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import energigas.apps.systemstrategy.energigas.entities.PlanPagoDetalle;
import energigas.apps.systemstrategy.energigas.utils.Constants;

/**
 * Created by KelvinThony on 11/08/2016.
 */
public class DB_PlanPagoDetalle {
    public static final String _id = "_id";
    public static final String planPaId = "planPaId";
    public static final String planPaDeId = "planPaDeId";
    public static final String fecha = "fecha";
    public static final String importe = "importe";
    public static final String estado = "estado";
    public static final String importeBase = "importeBase";
    public static final String interes = "interes";
    public static final String montoAPagar = "montoAPagar";
    public static final String fechaCobro = "fechaCobro";
    public static final String usuarioAccion = "usuarioAccion";
    public static final String fechaAccion = "fechaAccion";
    public static final String cajMovId = "cajMovId";
    private DbHelper mDbHelper;
    private SQLiteDatabase mDb;
    private static final String SQLITE_TABLA_DB_PLANPAGODETALLE = "DB_PlanPagoDetalle";
    private final Context mCtx;
    public static final String CREATE_TABLA_DB_PLANPAGODETALLE =
            "create table " + SQLITE_TABLA_DB_PLANPAGODETALLE + " ("
                    + _id + " integer primary key autoincrement,"
                    + planPaId + " integer ,"
                    + planPaDeId + " integer ,"
                    + fecha + " text ,"
                    + importe + " real ,"
                    + estado + " integer ,"
                    + importeBase + " real ,"
                    + interes + " real ,"
                    + montoAPagar + " real ,"
                    + fechaCobro + " text ,"
                    + usuarioAccion + " integer ,"
                    + fechaAccion + " text ,"
                    + cajMovId + " integer ,"
                    + Constants._CLMEXPORT + " integer );";
    public static final String DELETE_TABLA_DB_PLANPAGODETALLE = "DROP TABLE IF EXISTS " + SQLITE_TABLA_DB_PLANPAGODETALLE;

    public DB_PlanPagoDetalle(Context ctx) {
        this.mCtx = ctx;
    }

    public DB_PlanPagoDetalle open() {
        mDbHelper = new DbHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        if (mDbHelper != null) {
            mDbHelper.close();
        }
    }

    public long createPlanPagoDetalle(@NonNull PlanPagoDetalle planpagodetalle) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(planPaId, planpagodetalle.getPlanPaId());
        initialValues.put(planPaDeId, planpagodetalle.getPlanPaDeId());
        initialValues.put(fecha, planpagodetalle.getFecha());
        initialValues.put(importe, planpagodetalle.getImporte());
        initialValues.put(estado, planpagodetalle.isEstado());
        initialValues.put(importeBase, planpagodetalle.getImporteBase());
        initialValues.put(interes, planpagodetalle.getInteres());
        initialValues.put(montoAPagar, planpagodetalle.getMontoAPagar());
        initialValues.put(fechaCobro, planpagodetalle.getFechaCobro());
        initialValues.put(usuarioAccion, planpagodetalle.getUsuarioAccion());
        initialValues.put(fechaAccion, planpagodetalle.getFechaAccion());
        initialValues.put(cajMovId, planpagodetalle.getCajMovId());
        initialValues.put(Constants._CLMEXPORT, Constants._CREADO);
        return mDb.insert(SQLITE_TABLA_DB_PLANPAGODETALLE, null, initialValues);
    }

    public long updatePlanPagoDetalle(@NonNull PlanPagoDetalle planpagodetalle) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(planPaDeId, planpagodetalle.getPlanPaDeId());
        initialValues.put(fecha, planpagodetalle.getFecha());
        initialValues.put(importe, planpagodetalle.getImporte());
        initialValues.put(estado, planpagodetalle.isEstado());
        initialValues.put(importeBase, planpagodetalle.getImporteBase());
        initialValues.put(interes, planpagodetalle.getInteres());
        initialValues.put(montoAPagar, planpagodetalle.getMontoAPagar());
        initialValues.put(fechaCobro, planpagodetalle.getFechaCobro());
        initialValues.put(usuarioAccion, planpagodetalle.getUsuarioAccion());
        initialValues.put(fechaAccion, planpagodetalle.getFechaAccion());
        initialValues.put(cajMovId, planpagodetalle.getCajMovId());
        initialValues.put(Constants._CLMEXPORT, Constants._CREADO);
        return mDb.update(SQLITE_TABLA_DB_PLANPAGODETALLE, initialValues,
                planPaId + "=?", new String[]{"" + planpagodetalle.getPlanPaId()});
    }
}
