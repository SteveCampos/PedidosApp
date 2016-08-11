package energigas.apps.systemstrategy.energigas.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import energigas.apps.systemstrategy.energigas.entities.CajaMovimiento;
import energigas.apps.systemstrategy.energigas.utils.Constants;

/**
 * Created by KelvinThony on 11/08/2016.
 */
public class DB_CajaMovimiento {
    public static final String _id = "_id";
    public static final String cajMovId = "cajMovId";
    public static final String liqId = "liqId";
    public static final String catMovId = "catMovId";
    public static final String moneda = "moneda";
    public static final String importe = "importe";
    public static final String estado = "estado";
    public static final String fechaHora = "fechaHora";
    public static final String motivoAnulado = "motivoAnulado";
    public static final String referencia = "referencia";
    public static final String usuarioId = "usuarioId";
    public static final String fechaAccion = "fechaAccion";
    public static final String referenciaAndroid = "referenciaAndroid";
    public static final String tipoMovId = "tipoMovId";


    private DbHelper mDbHelper;
    private SQLiteDatabase mDb;
    private static final String SQLITE_TABLA_DB_CAJAMOVIMIENTO = "DB_CajaMovimiento";
    private final Context mCtx;
    public static final String CREATE_TABLA_DB_CAJAMOVIMIENTO =
            "create table " + SQLITE_TABLA_DB_CAJAMOVIMIENTO + " ("
                    + _id + " integer primary key autoincrement,"
                    + cajMovId + " integer ,"
                    + liqId + " integer ,"
                    + catMovId + " integer ,"
                    + moneda + " text ,"
                    + importe + " real ,"
                    + estado + " integer ,"
                    + fechaHora + " text ,"
                    + motivoAnulado + " text ,"
                    + referencia + " text ,"
                    + usuarioId + " integer ,"
                    + fechaAccion + " text ,"
                    + referenciaAndroid + " text ,"
                    + tipoMovId + " integer ,"
                    + Constants._CLMEXPORT + " integer );";
    public static final String DELETE_TABLA_DB_CAJAMOVIMIENTO = "DROP TABLE IF EXISTS " + SQLITE_TABLA_DB_CAJAMOVIMIENTO;

    public DB_CajaMovimiento(Context ctx) {
        this.mCtx = ctx;
    }

    public DB_CajaMovimiento open() {
        mDbHelper = new DbHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        if (mDbHelper != null) {
            mDbHelper.close();
        }
    }

    public long createCajaMovimiento(@NonNull CajaMovimiento cajamovimiento) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(cajMovId, cajamovimiento.getCajMovId());
        initialValues.put(liqId, cajamovimiento.getLiqId());
        initialValues.put(catMovId, cajamovimiento.getCatMovId());
        initialValues.put(moneda, cajamovimiento.getMoneda());
        initialValues.put(importe, cajamovimiento.getImporte());
        initialValues.put(estado, cajamovimiento.isEstado());
        initialValues.put(fechaHora, cajamovimiento.getFechaHora());
        initialValues.put(motivoAnulado, cajamovimiento.getMotivoAnulado());
        initialValues.put(referencia, cajamovimiento.getReferencia());
        initialValues.put(usuarioId, cajamovimiento.getUsuarioId());
        initialValues.put(fechaAccion, cajamovimiento.getFechaAccion());
        initialValues.put(referenciaAndroid, cajamovimiento.getReferenciaAndroid());
        initialValues.put(tipoMovId, cajamovimiento.getTipoMovId());
        initialValues.put(Constants._CLMEXPORT, Constants._CREADO);
        return mDb.insert(SQLITE_TABLA_DB_CAJAMOVIMIENTO, null, initialValues);
    }

    public long updateCajaMovimiento(@NonNull CajaMovimiento cajamovimiento) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(liqId, cajamovimiento.getLiqId());
        initialValues.put(catMovId, cajamovimiento.getCatMovId());
        initialValues.put(moneda, cajamovimiento.getMoneda());
        initialValues.put(importe, cajamovimiento.getImporte());
        initialValues.put(estado, cajamovimiento.isEstado());
        initialValues.put(fechaHora, cajamovimiento.getFechaHora());
        initialValues.put(motivoAnulado, cajamovimiento.getMotivoAnulado());
        initialValues.put(referencia, cajamovimiento.getReferencia());
        initialValues.put(usuarioId, cajamovimiento.getUsuarioId());
        initialValues.put(fechaAccion, cajamovimiento.getFechaAccion());
        initialValues.put(referenciaAndroid, cajamovimiento.getReferenciaAndroid());
        initialValues.put(tipoMovId, cajamovimiento.getTipoMovId());
        initialValues.put(Constants._CLMEXPORT, Constants._CREADO);
        return mDb.update(SQLITE_TABLA_DB_CAJAMOVIMIENTO, initialValues,
                cajMovId + "=?", new String[]{"" + cajamovimiento.getCajMovId()});
    }
}
