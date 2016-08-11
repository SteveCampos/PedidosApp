package energigas.apps.systemstrategy.energigas.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import energigas.apps.systemstrategy.energigas.entities.RolPrivilegio;
import energigas.apps.systemstrategy.energigas.utils.Constants;

/**
 * Created by KelvinThony on 11/08/2016.
 */
public class DB_RolPrivilegio {
    public static final String _id = "_id";
    public static final String rolId = "rolId";
    public static final String privilegioId = "privilegioId";
    private DbHelper mDbHelper;
    private SQLiteDatabase mDb;
    private static final String SQLITE_TABLA_DB_ROLPRIVILEGIO = "DB_RolPrivilegio";
    private final Context mCtx;
    public static final String CREATE_TABLA_DB_ROLPRIVILEGIO =
            "create table " + SQLITE_TABLA_DB_ROLPRIVILEGIO + " ("
                    + _id + " integer primary key autoincrement,"
                    + rolId + " integer ,"
                    + privilegioId + " integer ,"
                    + Constants._CLMEXPORT + " integer );";
    public static final String DELETE_TABLA_DB_ROLPRIVILEGIO = "DROP TABLE IF EXISTS " + SQLITE_TABLA_DB_ROLPRIVILEGIO;

    public DB_RolPrivilegio(Context ctx) {
        this.mCtx = ctx;
    }

    public DB_RolPrivilegio open() {
        mDbHelper = new DbHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        if (mDbHelper != null) {
            mDbHelper.close();
        }
    }

    public long createRolPrivilegio(@NonNull RolPrivilegio rolprivilegio) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(rolId, rolprivilegio.getRolId());
        initialValues.put(privilegioId, rolprivilegio.getPrivilegioId());
        initialValues.put(Constants._CLMEXPORT, Constants._CREADO);
        return mDb.insert(SQLITE_TABLA_DB_ROLPRIVILEGIO, null, initialValues);
    }

    public long updateRolPrivilegio(@NonNull RolPrivilegio rolprivilegio) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(privilegioId, rolprivilegio.getPrivilegioId());
        initialValues.put(Constants._CLMEXPORT, Constants._CREADO);
        return mDb.update(SQLITE_TABLA_DB_ROLPRIVILEGIO, initialValues,
                rolId + "=?", new String[]{"" + rolprivilegio.getRolId()});
    }
}
