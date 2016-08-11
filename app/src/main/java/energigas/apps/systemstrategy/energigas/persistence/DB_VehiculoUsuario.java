package energigas.apps.systemstrategy.energigas.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import energigas.apps.systemstrategy.energigas.entities.VehiculoUsuario;
import energigas.apps.systemstrategy.energigas.utils.Constants;

/**
 * Created by KelvinThony on 11/08/2016.
 */
public class DB_VehiculoUsuario {
    public static final String _id = "_id";
    public static final String veId = "veId";
    public static final String usuarioId = "usuarioId";
    public static final String responsable = "responsable";
    private DbHelper mDbHelper;
    private SQLiteDatabase mDb;
    private static final String SQLITE_TABLA_DB_VEHICULOUSUARIO = "DB_VehiculoUsuario";
    private final Context mCtx;
    public static final String CREATE_TABLA_DB_VEHICULOUSUARIO =
            "create table " + SQLITE_TABLA_DB_VEHICULOUSUARIO + " ("
                    + _id + " integer primary key autoincrement,"
                    + veId + " integer ,"
                    + usuarioId + " integer ,"
                    + responsable + " integer ,"
                    + Constants._CLMEXPORT + " integer );";
    public static final String DELETE_TABLA_DB_VEHICULOUSUARIO = "DROP TABLE IF EXISTS " + SQLITE_TABLA_DB_VEHICULOUSUARIO;

    public DB_VehiculoUsuario(Context ctx) {
        this.mCtx = ctx;
    }

    public DB_VehiculoUsuario open() {
        mDbHelper = new DbHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        if (mDbHelper != null) {
            mDbHelper.close();
        }
    }

    public long createVehiculoUsuario(@NonNull VehiculoUsuario vehiculousuario) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(veId, vehiculousuario.getVeId());
        initialValues.put(usuarioId, vehiculousuario.getUsuarioId());
        initialValues.put(responsable, vehiculousuario.isResponsable());
        initialValues.put(Constants._CLMEXPORT, Constants._CREADO);
        return mDb.insert(SQLITE_TABLA_DB_VEHICULOUSUARIO, null, initialValues);
    }

    public long updateVehiculoUsuario(@NonNull VehiculoUsuario vehiculousuario) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(usuarioId, vehiculousuario.getUsuarioId());
        initialValues.put(responsable, vehiculousuario.isResponsable());
        initialValues.put(Constants._CLMEXPORT, Constants._CREADO);
        return mDb.update(SQLITE_TABLA_DB_VEHICULOUSUARIO, initialValues,
                veId + "=?", new String[]{"" + vehiculousuario.getVeId()});
    }
}
