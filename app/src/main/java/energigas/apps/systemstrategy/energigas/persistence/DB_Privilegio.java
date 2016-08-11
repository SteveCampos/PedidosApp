package energigas.apps.systemstrategy.energigas.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import energigas.apps.systemstrategy.energigas.entities.Privilegio;
import energigas.apps.systemstrategy.energigas.utils.Constants;

/**
 * Created by KelvinThony on 11/08/2016.
 */
public class DB_Privilegio {
    public static final String _id = "_id";
    public static final String id = "id";
    public static final String accesoId = "accesoId";
    public static final String nombre = "nombre";
    public static final String descripcion = "descripcion";
    public static final String estado = "estado";
    public static final String fechaCreacion = "fechaCreacion";
    public static final String fechaActualizacion = "fechaActualizacion";
    public static final String usuarioCreacion = "usuarioCreacion";
    public static final String usuarioActualizacion = "usuarioActualizacion";
    private DbHelper mDbHelper;
    private SQLiteDatabase mDb;
    private static final String SQLITE_TABLA_DB_PRIVILEGIO = "DB_Privilegio";
    private final Context mCtx;
    public static final String CREATE_TABLA_DB_PRIVILEGIO =
            "create table " + SQLITE_TABLA_DB_PRIVILEGIO + " ("
                    + _id + " integer primary key autoincrement,"
                    + id + " integer ,"
                    + accesoId + " integer ,"
                    + nombre + " text ,"
                    + descripcion + " text ,"
                    + estado + " integer ,"
                    + fechaCreacion + " text ,"
                    + fechaActualizacion + " text ,"
                    + usuarioCreacion + " integer ,"
                    + usuarioActualizacion + " integer ,"
                    + Constants._CLMEXPORT + " integer );";
    public static final String DELETE_TABLA_DB_PRIVILEGIO = "DROP TABLE IF EXISTS " + SQLITE_TABLA_DB_PRIVILEGIO;

    public DB_Privilegio(Context ctx) {
        this.mCtx = ctx;
    }

    public DB_Privilegio open() {
        mDbHelper = new DbHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        if (mDbHelper != null) {
            mDbHelper.close();
        }
    }

    public long createPrivilegio(@NonNull Privilegio privilegio) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(id, privilegio.getId());
        initialValues.put(accesoId, privilegio.getAccesoId());
        initialValues.put(nombre, privilegio.getNombre());
        initialValues.put(descripcion, privilegio.getDescripcion());
        initialValues.put(estado, privilegio.isEstado());
        initialValues.put(fechaCreacion, privilegio.getFechaCreacion());
        initialValues.put(fechaActualizacion, privilegio.getFechaActualizacion());
        initialValues.put(usuarioCreacion, privilegio.getUsuarioCreacion());
        initialValues.put(usuarioActualizacion, privilegio.getUsuarioActualizacion());
        initialValues.put(Constants._CLMEXPORT, Constants._CREADO);
        return mDb.insert(SQLITE_TABLA_DB_PRIVILEGIO, null, initialValues);
    }

    public long updatePrivilegio(@NonNull Privilegio privilegio) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(accesoId, privilegio.getAccesoId());
        initialValues.put(nombre, privilegio.getNombre());
        initialValues.put(descripcion, privilegio.getDescripcion());
        initialValues.put(estado, privilegio.isEstado());
        initialValues.put(fechaCreacion, privilegio.getFechaCreacion());
        initialValues.put(fechaActualizacion, privilegio.getFechaActualizacion());
        initialValues.put(usuarioCreacion, privilegio.getUsuarioCreacion());
        initialValues.put(usuarioActualizacion, privilegio.getUsuarioActualizacion());
        initialValues.put(Constants._CLMEXPORT, Constants._CREADO);
        return mDb.update(SQLITE_TABLA_DB_PRIVILEGIO, initialValues,
                id + "=?", new String[]{"" + privilegio.getId()});
    }
}
