package energigas.apps.systemstrategy.energigas.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import energigas.apps.systemstrategy.energigas.entities.Unidad;
import energigas.apps.systemstrategy.energigas.utils.Constants;

/**
 * Created by KelvinThony on 11/08/2016.
 */
public class DB_Unidad {
    public static final String _id = "_id";
    public static final String unId = "unId";
    public static final String descripcion = "descripcion";
    public static final String abreviatura = "abreviatura";
    public static final String estado = "estado";
    private DbHelper mDbHelper;
    private SQLiteDatabase mDb;
    private static final String SQLITE_TABLA_DB_UNIDAD = "DB_Unidad";
    private final Context mCtx;
    public static final String CREATE_TABLA_DB_UNIDAD =
            "create table " + SQLITE_TABLA_DB_UNIDAD + " ("
                    + _id + " integer primary key autoincrement,"
                    + unId + " integer ,"
                    + descripcion + " text ,"
                    + abreviatura + " text ,"
                    + estado + " integer ,"
                    + Constants._CLMEXPORT + " integer );";
    public static final String DELETE_TABLA_DB_UNIDAD = "DROP TABLE IF EXISTS " + SQLITE_TABLA_DB_UNIDAD;

    public DB_Unidad(Context ctx) {
        this.mCtx = ctx;
    }

    public DB_Unidad open() {
        mDbHelper = new DbHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        if (mDbHelper != null) {
            mDbHelper.close();
        }
    }

    public long createUnidad(@NonNull Unidad unidad) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(unId, unidad.getUnId());
        initialValues.put(descripcion, unidad.getDescripcion());
        initialValues.put(abreviatura, unidad.getAbreviatura());
        initialValues.put(estado, unidad.isEstado());
        initialValues.put(Constants._CLMEXPORT, Constants._CREADO);
        return mDb.insert(SQLITE_TABLA_DB_UNIDAD, null, initialValues);
    }

    public long updateUnidad(@NonNull Unidad unidad) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(descripcion, unidad.getDescripcion());
        initialValues.put(abreviatura, unidad.getAbreviatura());
        initialValues.put(estado, unidad.isEstado());
        initialValues.put(Constants._CLMEXPORT, Constants._CREADO);
        return mDb.update(SQLITE_TABLA_DB_UNIDAD, initialValues,
                unId + "=?", new String[]{"" + unidad.getUnId()});
    }
}
