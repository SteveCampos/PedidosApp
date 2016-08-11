package energigas.apps.systemstrategy.energigas.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import energigas.apps.systemstrategy.energigas.entities.VehiculoDispositivo;
import energigas.apps.systemstrategy.energigas.utils.Constants;

/**
 * Created by KelvinThony on 11/08/2016.
 */
public class DB_VehiculoDispositivo {
    public static final String _id = "_id";
    public static final String id = "id";
    public static final String veId = "veId";
    public static final String dmId = "dmId";
    public static final String estado = "estado";
    public static final String latitud = "latitud";
    public static final String longitud = "longitud";
    private DbHelper mDbHelper;
    private SQLiteDatabase mDb;
    private static final String SQLITE_TABLA_DB_VEHICULODISPOSITIVO = "DB_VehiculoDispositivo";
    private final Context mCtx;
    public static final String CREATE_TABLA_DB_VEHICULODISPOSITIVO =
            "create table " + SQLITE_TABLA_DB_VEHICULODISPOSITIVO + " ("
                    + _id + " integer primary key autoincrement,"
                    + id + " integer ,"
                    + veId + " integer ,"
                    + dmId + " integer ,"
                    + estado + " integer ,"
                    + latitud + " text ,"
                    + longitud + " text ,"
                    + Constants._CLMEXPORT + " integer );";
    public static final String DELETE_TABLA_DB_VEHICULODISPOSITIVO = "DROP TABLE IF EXISTS " + SQLITE_TABLA_DB_VEHICULODISPOSITIVO;

    public DB_VehiculoDispositivo(Context ctx) {
        this.mCtx = ctx;
    }

    public DB_VehiculoDispositivo open() {
        mDbHelper = new DbHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        if (mDbHelper != null) {
            mDbHelper.close();
        }
    }

    public long createVehiculoDispositivo(@NonNull VehiculoDispositivo vehiculodispositivo) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(id, vehiculodispositivo.getId());
        initialValues.put(veId, vehiculodispositivo.getVeId());
        initialValues.put(dmId, vehiculodispositivo.getDmId());
        initialValues.put(estado, vehiculodispositivo.isEstado());
        initialValues.put(latitud, vehiculodispositivo.getLatitud());
        initialValues.put(longitud, vehiculodispositivo.getLongitud());
        initialValues.put(Constants._CLMEXPORT, Constants._CREADO);
        return mDb.insert(SQLITE_TABLA_DB_VEHICULODISPOSITIVO, null, initialValues);
    }

    public long updateVehiculoDispositivo(@NonNull VehiculoDispositivo vehiculodispositivo) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(veId, vehiculodispositivo.getVeId());
        initialValues.put(dmId, vehiculodispositivo.getDmId());
        initialValues.put(estado, vehiculodispositivo.isEstado());
        initialValues.put(latitud, vehiculodispositivo.getLatitud());
        initialValues.put(longitud, vehiculodispositivo.getLongitud());
        initialValues.put(Constants._CLMEXPORT, Constants._CREADO);
        return mDb.update(SQLITE_TABLA_DB_VEHICULODISPOSITIVO, initialValues,
                id + "=?", new String[]{"" + vehiculodispositivo.getId()});
    }
}
