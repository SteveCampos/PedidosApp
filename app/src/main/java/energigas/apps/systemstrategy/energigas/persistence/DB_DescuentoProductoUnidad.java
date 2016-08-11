package energigas.apps.systemstrategy.energigas.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import energigas.apps.systemstrategy.energigas.entities.DescuentoProductoUnidad;
import energigas.apps.systemstrategy.energigas.utils.Constants;

/**
 * Created by KelvinThony on 11/08/2016.
 */
public class DB_DescuentoProductoUnidad {
    public static final String _id = "_id";
    public static final String descuentoId = "descuentoId";
    public static final String puId = "puId";
    private DbHelper mDbHelper;
    private SQLiteDatabase mDb;
    private static final String SQLITE_TABLA_DB_DESCUENTOPRODUCTOUNIDAD = "DB_DescuentoProductoUnidad";
    private final Context mCtx;
    public static final String CREATE_TABLA_DB_DESCUENTOPRODUCTOUNIDAD =
            "create table " + SQLITE_TABLA_DB_DESCUENTOPRODUCTOUNIDAD + " ("
                    + _id + " integer primary key autoincrement,"
                    + descuentoId + " integer ,"
                    + puId + " integer ,"
                    + Constants._CLMEXPORT + " integer );";
    public static final String DELETE_TABLA_DB_DESCUENTOPRODUCTOUNIDAD = "DROP TABLE IF EXISTS " + SQLITE_TABLA_DB_DESCUENTOPRODUCTOUNIDAD;

    public DB_DescuentoProductoUnidad(Context ctx) {
        this.mCtx = ctx;
    }

    public DB_DescuentoProductoUnidad open() {
        mDbHelper = new DbHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        if (mDbHelper != null) {
            mDbHelper.close();
        }
    }

    public long createDescuentoProductoUnidad(@NonNull DescuentoProductoUnidad descuentoproductounidad) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(descuentoId, descuentoproductounidad.getDescuentoId());
        initialValues.put(puId, descuentoproductounidad.getPuId());
        initialValues.put(Constants._CLMEXPORT, Constants._CREADO);
        return mDb.insert(SQLITE_TABLA_DB_DESCUENTOPRODUCTOUNIDAD, null, initialValues);
    }

    public long updateDescuentoProductoUnidad(@NonNull DescuentoProductoUnidad descuentoproductounidad) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(puId, descuentoproductounidad.getPuId());
        initialValues.put(Constants._CLMEXPORT, Constants._CREADO);
        return mDb.update(SQLITE_TABLA_DB_DESCUENTOPRODUCTOUNIDAD, initialValues,
                descuentoId + "=?", new String[]{"" + descuentoproductounidad.getDescuentoId()});
    }
}
