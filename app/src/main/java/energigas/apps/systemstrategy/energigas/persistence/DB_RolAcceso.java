package energigas.apps.systemstrategy.energigas.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import energigas.apps.systemstrategy.energigas.entities.RolAcceso;
import energigas.apps.systemstrategy.energigas.utils.Constants;

/**
 * Created by KelvinThony on 11/08/2016.
 */
public class DB_RolAcceso {
    public static final String _id = "_id";
    public static final String rolId = "rolId";
    public static final String accesoId = "accesoId";
    public static final String accesoDefault = "accesoDefault";
    private DbHelper mDbHelper;
    private SQLiteDatabase mDb;
    private static final String SQLITE_TABLA_DB_ROLACCESO = "DB_RolAcceso";
    private final Context mCtx;
    public static final String CREATE_TABLA_DB_ROLACCESO =
            "create table " + SQLITE_TABLA_DB_ROLACCESO + " ("
                    + _id + " integer primary key autoincrement,"
                    + rolId + " integer ,"
                    + accesoId + " integer ,"
                    + accesoDefault + " integer ,"
                    + Constants._CLMEXPORT + " integer );";
    public static final String DELETE_TABLA_DB_ROLACCESO = "DROP TABLE IF EXISTS " + SQLITE_TABLA_DB_ROLACCESO;

    public DB_RolAcceso(Context ctx) {
        this.mCtx = ctx;
    }

    public DB_RolAcceso open() {
        mDbHelper = new DbHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        if (mDbHelper != null) {
            mDbHelper.close();
        }
    }

    public long createRolAcceso(@NonNull RolAcceso rolacceso) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(rolId, rolacceso.getRolId());
        initialValues.put(accesoId, rolacceso.getAccesoId());
        initialValues.put(accesoDefault, rolacceso.isAccesoDefault());
        initialValues.put(Constants._CLMEXPORT, Constants._CREADO);
        return mDb.insert(SQLITE_TABLA_DB_ROLACCESO, null, initialValues);
    }

    public long updateRolAcceso(@NonNull RolAcceso rolacceso) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(accesoId, rolacceso.getAccesoId());
        initialValues.put(accesoDefault, rolacceso.isAccesoDefault());
        initialValues.put(Constants._CLMEXPORT, Constants._CREADO);
        return mDb.update(SQLITE_TABLA_DB_ROLACCESO, initialValues,
                rolId + "=?", new String[]{"" + rolacceso.getRolId()});
    }
}
