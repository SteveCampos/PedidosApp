package energigas.apps.systemstrategy.energigas.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import energigas.apps.systemstrategy.energigas.entities.GuiaRemisionDetalle;
import energigas.apps.systemstrategy.energigas.utils.Constants;

/**
 * Created by KelvinThony on 11/08/2016.
 */
public class DB_GuiaRemisionDetalle {
    public static final String _id = "_id";
    public static final String greId = "greId";
    public static final String grdeId = "grdeId";
    public static final String proId = "proId";
    public static final String unId = "unId";
    public static final String cantidad = "cantidad";
    private DbHelper mDbHelper;
    private SQLiteDatabase mDb;
    private static final String SQLITE_TABLA_DB_GUIAREMISIONDETALLE = "DB_GuiaRemisionDetalle";
    private final Context mCtx;
    public static final String CREATE_TABLA_DB_GUIAREMISIONDETALLE =
            "create table " + SQLITE_TABLA_DB_GUIAREMISIONDETALLE + " ("
                    + _id + " integer primary key autoincrement,"
                    + greId + " integer ,"
                    + grdeId + " integer ,"
                    + proId + " integer ,"
                    + unId + " integer ,"
                    + cantidad + " real ,"
                    + Constants._CLMEXPORT + " integer );";
    public static final String DELETE_TABLA_DB_GUIAREMISIONDETALLE = "DROP TABLE IF EXISTS " + SQLITE_TABLA_DB_GUIAREMISIONDETALLE;

    public DB_GuiaRemisionDetalle(Context ctx) {
        this.mCtx = ctx;
    }

    public DB_GuiaRemisionDetalle open() {
        mDbHelper = new DbHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        if (mDbHelper != null) {
            mDbHelper.close();
        }
    }

    public long createGuiaRemisionDetalle(@NonNull GuiaRemisionDetalle guiaremisiondetalle) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(greId, guiaremisiondetalle.getGreId());
        initialValues.put(grdeId, guiaremisiondetalle.getGrdeId());
        initialValues.put(proId, guiaremisiondetalle.getProId());
        initialValues.put(unId, guiaremisiondetalle.getUnId());
        initialValues.put(cantidad, guiaremisiondetalle.getCantidad());
        initialValues.put(Constants._CLMEXPORT, Constants._CREADO);
        return mDb.insert(SQLITE_TABLA_DB_GUIAREMISIONDETALLE, null, initialValues);
    }

    public long updateGuiaRemisionDetalle(@NonNull GuiaRemisionDetalle guiaremisiondetalle) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(grdeId, guiaremisiondetalle.getGrdeId());
        initialValues.put(proId, guiaremisiondetalle.getProId());
        initialValues.put(unId, guiaremisiondetalle.getUnId());
        initialValues.put(cantidad, guiaremisiondetalle.getCantidad());
        initialValues.put(Constants._CLMEXPORT, Constants._CREADO);
        return mDb.update(SQLITE_TABLA_DB_GUIAREMISIONDETALLE, initialValues,
                greId + "=?", new String[]{"" + guiaremisiondetalle.getGreId()});
    }
}
