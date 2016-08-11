package energigas.apps.systemstrategy.energigas.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import energigas.apps.systemstrategy.energigas.entities.CajaComprobante;
import energigas.apps.systemstrategy.energigas.utils.Constants;

/**
 * Created by KelvinThony on 11/08/2016.
 */
public class DB_CajaComprobante {
    public static final String _id = "_id";
    public static final String cajCompId = "cajCompId";
    public static final String cajMovId = "cajMovId";
    public static final String compId = "compId";
    public static final String importe = "importe";
    public static final String usuarioActualizacion = "usuarioActualizacion";
    public static final String fechaActualizacion = "fechaActualizacion";
    private DbHelper mDbHelper;
    private SQLiteDatabase mDb;
    private static final String SQLITE_TABLA_DB_CAJACOMPROBANTE = "DB_CajaComprobante";
    private final Context mCtx;
    public static final String CREATE_TABLA_DB_CAJACOMPROBANTE =
            "create table " + SQLITE_TABLA_DB_CAJACOMPROBANTE + " ("
                    + _id + " integer primary key autoincrement,"
                    + cajCompId + " integer ,"
                    + cajMovId + " integer ,"
                    + compId + " integer ,"
                    + importe + " real ,"
                    + usuarioActualizacion + " integer ,"
                    + fechaActualizacion + " text ,"
                    + Constants._CLMEXPORT + " integer );";
    public static final String DELETE_TABLA_DB_CAJACOMPROBANTE = "DROP TABLE IF EXISTS " + SQLITE_TABLA_DB_CAJACOMPROBANTE;

    public DB_CajaComprobante(Context ctx) {
        this.mCtx = ctx;
    }

    public DB_CajaComprobante open() {
        mDbHelper = new DbHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        if (mDbHelper != null) {
            mDbHelper.close();
        }
    }

    public long createCajaComprobante(@NonNull CajaComprobante cajacomprobante) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(cajCompId, cajacomprobante.getCajCompId());
        initialValues.put(cajMovId, cajacomprobante.getCajMovId());
        initialValues.put(compId, cajacomprobante.getCompId());
        initialValues.put(importe, cajacomprobante.getImporte());
        initialValues.put(usuarioActualizacion, cajacomprobante.getUsuarioActualizacion());
        initialValues.put(fechaActualizacion, cajacomprobante.getFechaActualizacion());
        initialValues.put(Constants._CLMEXPORT, Constants._CREADO);
        return mDb.insert(SQLITE_TABLA_DB_CAJACOMPROBANTE, null, initialValues);
    }

    public long updateCajaComprobante(@NonNull CajaComprobante cajacomprobante) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(cajMovId, cajacomprobante.getCajMovId());
        initialValues.put(compId, cajacomprobante.getCompId());
        initialValues.put(importe, cajacomprobante.getImporte());
        initialValues.put(usuarioActualizacion, cajacomprobante.getUsuarioActualizacion());
        initialValues.put(fechaActualizacion, cajacomprobante.getFechaActualizacion());
        initialValues.put(Constants._CLMEXPORT, Constants._CREADO);
        return mDb.update(SQLITE_TABLA_DB_CAJACOMPROBANTE, initialValues,
                cajCompId + "=?", new String[]{"" + cajacomprobante.getCajCompId()});
    }
}
