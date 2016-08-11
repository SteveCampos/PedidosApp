package energigas.apps.systemstrategy.energigas.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import energigas.apps.systemstrategy.energigas.entities.RolUsuario;
import energigas.apps.systemstrategy.energigas.utils.Constants;

/**
 * Created by KelvinThony on 11/08/2016.
 */
public class DB_RolUsuario {
    public static final String _id = "_id";
    public static final String rolId = "rolId";
    public static final String usuarioId = "usuarioId";
    private DbHelper mDbHelper;
    private SQLiteDatabase mDb;
    private static final String SQLITE_TABLA_DB_ROLUSUARIO = "DB_RolUsuario";
    private final Context mCtx;
    public static final String CREATE_TABLA_DB_ROLUSUARIO =
            "create table " + SQLITE_TABLA_DB_ROLUSUARIO + " ("
                    + _id + " integer primary key autoincrement,"
                    + rolId + " integer ,"
                    + usuarioId + " integer ,"
                    + Constants._CLMEXPORT + " integer );";
    public static final String DELETE_TABLA_DB_ROLUSUARIO = "DROP TABLE IF EXISTS " + SQLITE_TABLA_DB_ROLUSUARIO;

    public DB_RolUsuario(Context ctx) {
        this.mCtx = ctx;
    }

    public DB_RolUsuario open() {
        mDbHelper = new DbHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        if (mDbHelper != null) {
            mDbHelper.close();
        }
    }

    public long createRolUsuario(@NonNull RolUsuario rolusuario) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(rolId, rolusuario.getRolId());
        initialValues.put(usuarioId, rolusuario.getUsuarioId());
        initialValues.put(Constants._CLMEXPORT, Constants._CREADO);
        return mDb.insert(SQLITE_TABLA_DB_ROLUSUARIO, null, initialValues);
    }

    public long updateRolUsuario(@NonNull RolUsuario rolusuario) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(usuarioId, rolusuario.getUsuarioId());
        initialValues.put(Constants._CLMEXPORT, Constants._CREADO);
        return mDb.update(SQLITE_TABLA_DB_ROLUSUARIO, initialValues,
                rolId + "=?", new String[]{"" + rolusuario.getRolId()});
    }
}
