package energigas.apps.systemstrategy.energigas.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import energigas.apps.systemstrategy.energigas.entities.Estado;
import energigas.apps.systemstrategy.energigas.utils.Constants;

/**
 * Created by KelvinThony on 11/08/2016.
 */
public class DB_Estado {
    public static final String _id = "_id";
    public static final String id = "id";
    public static final String objeto = "objeto";
    public static final String descripcion = "descripcion";
    public static final String parentId = "parentId";
    private DbHelper mDbHelper;
    private SQLiteDatabase mDb;
    private static final String SQLITE_TABLA_DB_ESTADO = "DB_Estado";
    private final Context mCtx;
    public static final String CREATE_TABLA_DB_ESTADO =
            "create table " + SQLITE_TABLA_DB_ESTADO + " ("
                    + _id + " integer primary key autoincrement,"
                    + id + " integer ,"
                    + objeto + " text ,"
                    + descripcion + " text ,"
                    + parentId + " integer ,"
                    + Constants._CLMEXPORT + " integer );";
    public static final String DELETE_TABLA_DB_ESTADO = "DROP TABLE IF EXISTS " + SQLITE_TABLA_DB_ESTADO;

    public DB_Estado(Context ctx) {
        this.mCtx = ctx;
    }

    public DB_Estado open() {
        mDbHelper = new DbHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        if (mDbHelper != null) {
            mDbHelper.close();
        }
    }

    public long createEstado(@NonNull Estado estado) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(id, estado.getId());
        initialValues.put(objeto, estado.getObjeto());
        initialValues.put(descripcion, estado.getDescripcion());
        initialValues.put(parentId, estado.getParentId());
        initialValues.put(Constants._CLMEXPORT, Constants._CREADO);
        return mDb.insert(SQLITE_TABLA_DB_ESTADO, null, initialValues);
    }

    public long updateEstado(@NonNull Estado estado) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(objeto, estado.getObjeto());
        initialValues.put(descripcion, estado.getDescripcion());
        initialValues.put(parentId, estado.getParentId());
        initialValues.put(Constants._CLMEXPORT, Constants._CREADO);
        return mDb.update(SQLITE_TABLA_DB_ESTADO, initialValues,
                id + "=?", new String[]{"" + estado.getId()});
    }
}
