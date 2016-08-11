package energigas.apps.systemstrategy.energigas.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import energigas.apps.systemstrategy.energigas.entities.DispositivoSerie;
import energigas.apps.systemstrategy.energigas.utils.Constants;

/**
 * Created by KelvinThony on 11/08/2016.
 */
public class DB_DispositivoSerie {
    public static final String _id = "_id";
    public static final String dmId = "dmId";
    public static final String seId = "seId";
    private DbHelper mDbHelper;
    private SQLiteDatabase mDb;
    private static final String SQLITE_TABLA_DB_DISPOSITIVOSERIE = "DB_DispositivoSerie";
    private final Context mCtx;
    public static final String CREATE_TABLA_DB_DISPOSITIVOSERIE =
            "create table " + SQLITE_TABLA_DB_DISPOSITIVOSERIE + " ("
                    + _id + " integer primary key autoincrement,"
                    + dmId + " integer ,"
                    + seId + " integer ,"
                    + Constants._CLMEXPORT + " integer );";
    public static final String DELETE_TABLA_DB_DISPOSITIVOSERIE = "DROP TABLE IF EXISTS " + SQLITE_TABLA_DB_DISPOSITIVOSERIE;

    public DB_DispositivoSerie(Context ctx) {
        this.mCtx = ctx;
    }

    public DB_DispositivoSerie open() {
        mDbHelper = new DbHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        if (mDbHelper != null) {
            mDbHelper.close();
        }
    }

    public long createDispositivoSerie(@NonNull DispositivoSerie dispositivoserie) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(dmId, dispositivoserie.getDmId());
        initialValues.put(seId, dispositivoserie.getSeId());
        initialValues.put(Constants._CLMEXPORT, Constants._CREADO);
        return mDb.insert(SQLITE_TABLA_DB_DISPOSITIVOSERIE, null, initialValues);
    }

    public long updateDispositivoSerie(@NonNull DispositivoSerie dispositivoserie) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(seId, dispositivoserie.getSeId());
        initialValues.put(Constants._CLMEXPORT, Constants._CREADO);
        return mDb.update(SQLITE_TABLA_DB_DISPOSITIVOSERIE, initialValues,
                dmId + "=?", new String[]{"" + dispositivoserie.getDmId()});
    }
}
