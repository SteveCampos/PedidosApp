package energigas.apps.systemstrategy.energigas.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import energigas.apps.systemstrategy.energigas.entities.ProductoUnidad;
import energigas.apps.systemstrategy.energigas.utils.Constants;

/**
 * Created by KelvinThony on 11/08/2016.
 */
public class DB_ProductoUnidad {
    public static final String _id = "_id";
    public static final String puId = "puId";
    public static final String proId = "proId";
    public static final String unId = "unId";
    public static final String unidadBase = "unidadBase";
    private DbHelper mDbHelper;
    private SQLiteDatabase mDb;
    private static final String SQLITE_TABLA_DB_PRODUCTOUNIDAD = "DB_ProductoUnidad";
    private final Context mCtx;
    public static final String CREATE_TABLA_DB_PRODUCTOUNIDAD =
            "create table " + SQLITE_TABLA_DB_PRODUCTOUNIDAD + " ("
                    + _id + " integer primary key autoincrement,"
                    + puId + " integer ,"
                    + proId + " integer ,"
                    + unId + " integer ,"
                    + unidadBase + " integer ,"
                    + Constants._CLMEXPORT + " integer );";
    public static final String DELETE_TABLA_DB_PRODUCTOUNIDAD = "DROP TABLE IF EXISTS " + SQLITE_TABLA_DB_PRODUCTOUNIDAD;

    public DB_ProductoUnidad(Context ctx) {
        this.mCtx = ctx;
    }

    public DB_ProductoUnidad open() {
        mDbHelper = new DbHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        if (mDbHelper != null) {
            mDbHelper.close();
        }
    }

    public long createProductoUnidad(@NonNull ProductoUnidad productounidad) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(puId, productounidad.getPuId());
        initialValues.put(proId, productounidad.getProId());
        initialValues.put(unId, productounidad.getUnId());
        initialValues.put(unidadBase, productounidad.isUnidadBase());
        initialValues.put(Constants._CLMEXPORT, Constants._CREADO);
        return mDb.insert(SQLITE_TABLA_DB_PRODUCTOUNIDAD, null, initialValues);
    }

    public long updateProductoUnidad(@NonNull ProductoUnidad productounidad) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(proId, productounidad.getProId());
        initialValues.put(unId, productounidad.getUnId());
        initialValues.put(unidadBase, productounidad.isUnidadBase());
        initialValues.put(Constants._CLMEXPORT, Constants._CREADO);
        return mDb.update(SQLITE_TABLA_DB_PRODUCTOUNIDAD, initialValues,
                puId + "=?", new String[]{"" + productounidad.getPuId()});
    }
}
