package energigas.apps.systemstrategy.energigas.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import energigas.apps.systemstrategy.energigas.entities.Proceso;
import energigas.apps.systemstrategy.energigas.utils.Constants;

/**
 * Created by KelvinThony on 11/08/2016.
 */
public class DB_Proceso {
    public static final String _id = "_id";
    public static final String proId = "proId";
    public static final String nombre = "nombre";
    public static final String estado = "estado";
    public static final String usuarioCreacion = "usuarioCreacion";
    public static final String fechaCreacion = "fechaCreacion";
    public static final String usuarioActualizacion = "usuarioActualizacion";
    public static final String fechaActualizacion = "fechaActualizacion";
    private DbHelper mDbHelper;
    private SQLiteDatabase mDb;
    private static final String SQLITE_TABLA_DB_PROCESO = "DB_Proceso";
    private final Context mCtx;
    public static final String CREATE_TABLA_DB_PROCESO =
            "create table " + SQLITE_TABLA_DB_PROCESO + " ("
                    + _id + " integer primary key autoincrement,"
                    + proId + " integer ,"
                    + nombre + " text ,"
                    + estado + " integer ,"
                    + usuarioCreacion + " integer ,"
                    + fechaCreacion + " text ,"
                    + usuarioActualizacion + " integer ,"
                    + fechaActualizacion + " text ,"
                    + Constants._CLMEXPORT + " integer );";
    public static final String DELETE_TABLA_DB_PROCESO = "DROP TABLE IF EXISTS " + SQLITE_TABLA_DB_PROCESO;

    public DB_Proceso(Context ctx) {
        this.mCtx = ctx;
    }

    public DB_Proceso open() {
        mDbHelper = new DbHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        if (mDbHelper != null) {
            mDbHelper.close();
        }
    }

    public long createProceso(@NonNull Proceso proceso) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(proId, proceso.getProId());
        initialValues.put(nombre, proceso.getNombre());
        initialValues.put(estado, proceso.isEstado());
        initialValues.put(usuarioCreacion, proceso.getUsuarioCreacion());
        initialValues.put(fechaCreacion, proceso.getFechaCreacion());
        initialValues.put(usuarioActualizacion, proceso.getUsuarioActualizacion());
        initialValues.put(fechaActualizacion, proceso.getFechaActualizacion());
        initialValues.put(Constants._CLMEXPORT, Constants._CREADO);
        return mDb.insert(SQLITE_TABLA_DB_PROCESO, null, initialValues);
    }

    public long updateProceso(@NonNull Proceso proceso) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(nombre, proceso.getNombre());
        initialValues.put(estado, proceso.isEstado());
        initialValues.put(usuarioCreacion, proceso.getUsuarioCreacion());
        initialValues.put(fechaCreacion, proceso.getFechaCreacion());
        initialValues.put(usuarioActualizacion, proceso.getUsuarioActualizacion());
        initialValues.put(fechaActualizacion, proceso.getFechaActualizacion());
        initialValues.put(Constants._CLMEXPORT, Constants._CREADO);
        return mDb.update(SQLITE_TABLA_DB_PROCESO, initialValues,
                proId + "=?", new String[]{"" + proceso.getProId()});
    }
}
