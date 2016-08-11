package energigas.apps.systemstrategy.energigas.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import energigas.apps.systemstrategy.energigas.entities.Rol;
import energigas.apps.systemstrategy.energigas.utils.Constants;

/**
 * Created by KelvinThony on 11/08/2016.
 */
public class DB_Rol {
    public static final String _id = "_id";
    public static final String id = "id";
    public static final String nombre = "nombre";
    public static final String estado = "estado";
    public static final String parentId = "parentId";
    public static final String fechaCreacion = "fechaCreacion";
    public static final String fechaActualizacion = "fechaActualizacion";
    public static final String usuarioCreacion = "usuarioCreacion";
    public static final String usuarioActualizacion = "usuarioActualizacion";
    private DbHelper mDbHelper;
    private SQLiteDatabase mDb;
    private static final String SQLITE_TABLA_DB_ROL = "DB_Rol";
    private final Context mCtx;
    public static final String CREATE_TABLA_DB_ROL =
            "create table " + SQLITE_TABLA_DB_ROL + " ("
                    + _id + " integer primary key autoincrement,"
                    + id + " integer ,"
                    + nombre + " text ,"
                    + estado + " integer ,"
                    + parentId + " integer ,"
                    + fechaCreacion + " text ,"
                    + fechaActualizacion + " text ,"
                    + usuarioCreacion + " integer ,"
                    + usuarioActualizacion + " integer ,"
                    + Constants._CLMEXPORT + " integer );";
    public static final String DELETE_TABLA_DB_ROL = "DROP TABLE IF EXISTS " + SQLITE_TABLA_DB_ROL;

    public DB_Rol(Context ctx) {
        this.mCtx = ctx;
    }

    public DB_Rol open() {
        mDbHelper = new DbHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        if (mDbHelper != null) {
            mDbHelper.close();
        }
    }

    public long createRol(@NonNull Rol rol) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(id, rol.getId());
        initialValues.put(nombre, rol.getNombre());
        initialValues.put(estado, rol.isEstado());
        initialValues.put(parentId, rol.getParentId());
        initialValues.put(fechaCreacion, rol.getFechaCreacion());
        initialValues.put(fechaActualizacion, rol.getFechaActualizacion());
        initialValues.put(usuarioCreacion, rol.getUsuarioCreacion());
        initialValues.put(usuarioActualizacion, rol.getUsuarioActualizacion());
        initialValues.put(Constants._CLMEXPORT, Constants._CREADO);
        return mDb.insert(SQLITE_TABLA_DB_ROL, null, initialValues);
    }

    public long updateRol(@NonNull Rol rol) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(nombre, rol.getNombre());
        initialValues.put(estado, rol.isEstado());
        initialValues.put(parentId, rol.getParentId());
        initialValues.put(fechaCreacion, rol.getFechaCreacion());
        initialValues.put(fechaActualizacion, rol.getFechaActualizacion());
        initialValues.put(usuarioCreacion, rol.getUsuarioCreacion());
        initialValues.put(usuarioActualizacion, rol.getUsuarioActualizacion());
        initialValues.put(Constants._CLMEXPORT, Constants._CREADO);
        return mDb.update(SQLITE_TABLA_DB_ROL, initialValues,
                id + "=?", new String[]{"" + rol.getId()});
    }
}
