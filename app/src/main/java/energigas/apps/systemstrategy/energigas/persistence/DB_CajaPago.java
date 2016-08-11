package energigas.apps.systemstrategy.energigas.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import energigas.apps.systemstrategy.energigas.entities.CajaPago;
import energigas.apps.systemstrategy.energigas.utils.Constants;

/**
 * Created by KelvinThony on 11/08/2016.
 */
public class DB_CajaPago {
    public static final String _id = "_id";
    public static final String cajPagId = "cajPagId";
    public static final String importe = "importe";
    public static final String cajMovId = "cajMovId";
    public static final String usuarioId = "usuarioId";
    public static final String fechaAccion = "fechaAccion";
    public static final String exportado = "exportado";
    public static final String tipoPagoId = "tipoPagoId";
    public static final String anulado = "anulado";
    private DbHelper mDbHelper;
    private SQLiteDatabase mDb;
    private static final String SQLITE_TABLA_DB_CAJAPAGO = "DB_CajaPago";
    private final Context mCtx;
    public static final String CREATE_TABLA_DB_CAJAPAGO =
            "create table " + SQLITE_TABLA_DB_CAJAPAGO + " ("
                    + _id + " integer primary key autoincrement,"
                    + cajPagId + " integer ,"
                    + importe + " real ,"
                    + cajMovId + " integer ,"
                    + usuarioId + " integer ,"
                    + fechaAccion + " text ,"
                    + exportado + " integer ,"
                    + tipoPagoId + " integer ,"
                    + anulado + " integer ,"
                    + Constants._CLMEXPORT + " integer );";
    public static final String DELETE_TABLA_DB_CAJAPAGO = "DROP TABLE IF EXISTS " + SQLITE_TABLA_DB_CAJAPAGO;

    public DB_CajaPago(Context ctx) {
        this.mCtx = ctx;
    }

    public DB_CajaPago open() {
        mDbHelper = new DbHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        if (mDbHelper != null) {
            mDbHelper.close();
        }
    }

    public long createCajaPago(@NonNull CajaPago cajapago) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(cajPagId, cajapago.getCajPagId());
        initialValues.put(importe, cajapago.getImporte());
        initialValues.put(cajMovId, cajapago.getCajMovId());
        initialValues.put(usuarioId, cajapago.getUsuarioId());
        initialValues.put(fechaAccion, cajapago.getFechaAccion());
        initialValues.put(exportado, cajapago.isExportado());
        initialValues.put(tipoPagoId, cajapago.getTipoPagoId());
        initialValues.put(anulado, cajapago.isAnulado());
        initialValues.put(Constants._CLMEXPORT, Constants._CREADO);
        return mDb.insert(SQLITE_TABLA_DB_CAJAPAGO, null, initialValues);
    }

    public long updateCajaPago(@NonNull CajaPago cajapago) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(importe, cajapago.getImporte());
        initialValues.put(cajMovId, cajapago.getCajMovId());
        initialValues.put(usuarioId, cajapago.getUsuarioId());
        initialValues.put(fechaAccion, cajapago.getFechaAccion());
        initialValues.put(exportado, cajapago.isExportado());
        initialValues.put(tipoPagoId, cajapago.getTipoPagoId());
        initialValues.put(anulado, cajapago.isAnulado());
        initialValues.put(Constants._CLMEXPORT, Constants._CREADO);
        return mDb.update(SQLITE_TABLA_DB_CAJAPAGO, initialValues,
                cajPagId + "=?", new String[]{"" + cajapago.getCajPagId()});
    }
}
