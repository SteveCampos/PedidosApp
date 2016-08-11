package energigas.apps.systemstrategy.energigas.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import energigas.apps.systemstrategy.energigas.entities.ProcesoOperacion;
import energigas.apps.systemstrategy.energigas.utils.Constants;

/**
 * Created by KelvinThony on 11/08/2016.
 */
public class DB_ProcesoOperacion {
    public static final String _id = "_id";
    public static final String opId = "opId";
    public static final String faId = "faId";
    public static final String nombre = "nombre";
    public static final String tipo = "tipo";
    public static final String estado = "estado";
    public static final String usuarioCreacion = "usuarioCreacion";
    public static final String usuarioActualizacion = "usuarioActualizacion";
    public static final String fechaCreacion = "fechaCreacion";
    public static final String fechaActualizacion = "fechaActualizacion";
    public static final String orden = "orden";
    public static final String descripcion = "descripcion";
    private DbHelper mDbHelper;
    private SQLiteDatabase mDb;
    private static final String SQLITE_TABLA_DB_PROCESOOPERACION = "DB_ProcesoOperacion";
    private final Context mCtx;
    public static final String CREATE_TABLA_DB_PROCESOOPERACION =
            "create table " + SQLITE_TABLA_DB_PROCESOOPERACION + " ("
                    + _id + " integer primary key autoincrement,"
                    + opId + " integer ,"
                    + faId + " integer ,"
                    + nombre + " text ,"
                    + tipo + " text ,"
                    + estado + " integer ,"
                    + usuarioCreacion + " integer ,"
                    + usuarioActualizacion + " integer ,"
                    + fechaCreacion + " text ,"
                    + fechaActualizacion + " text ,"
                    + orden + " integer ,"
                    + descripcion + " text ,"
                    + Constants._CLMEXPORT + " integer );";
    public static final String DELETE_TABLA_DB_PROCESOOPERACION = "DROP TABLE IF EXISTS " + SQLITE_TABLA_DB_PROCESOOPERACION;

    public DB_ProcesoOperacion(Context ctx) {
        this.mCtx = ctx;
    }

    public DB_ProcesoOperacion open() {
        mDbHelper = new DbHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        if (mDbHelper != null) {
            mDbHelper.close();
        }
    }

    public long createProcesoOperacion(@NonNull ProcesoOperacion procesooperacion) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(opId, procesooperacion.getOpId());
        initialValues.put(faId, procesooperacion.getFaId());
        initialValues.put(nombre, procesooperacion.getNombre());
        initialValues.put(tipo, procesooperacion.getTipo());
        initialValues.put(estado, procesooperacion.isEstado());
        initialValues.put(usuarioCreacion, procesooperacion.getUsuarioCreacion());
        initialValues.put(usuarioActualizacion, procesooperacion.getUsuarioActualizacion());
        initialValues.put(fechaCreacion, procesooperacion.getFechaCreacion());
        initialValues.put(fechaActualizacion, procesooperacion.getFechaActualizacion());
        initialValues.put(orden, procesooperacion.getOrden());
        initialValues.put(descripcion, procesooperacion.getDescripcion());
        initialValues.put(Constants._CLMEXPORT, Constants._CREADO);
        return mDb.insert(SQLITE_TABLA_DB_PROCESOOPERACION, null, initialValues);
    }

    public long updateProcesoOperacion(@NonNull ProcesoOperacion procesooperacion) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(faId, procesooperacion.getFaId());
        initialValues.put(nombre, procesooperacion.getNombre());
        initialValues.put(tipo, procesooperacion.getTipo());
        initialValues.put(estado, procesooperacion.isEstado());
        initialValues.put(usuarioCreacion, procesooperacion.getUsuarioCreacion());
        initialValues.put(usuarioActualizacion, procesooperacion.getUsuarioActualizacion());
        initialValues.put(fechaCreacion, procesooperacion.getFechaCreacion());
        initialValues.put(fechaActualizacion, procesooperacion.getFechaActualizacion());
        initialValues.put(orden, procesooperacion.getOrden());
        initialValues.put(descripcion, procesooperacion.getDescripcion());
        initialValues.put(Constants._CLMEXPORT, Constants._CREADO);
        return mDb.update(SQLITE_TABLA_DB_PROCESOOPERACION, initialValues,
                opId + "=?", new String[]{"" + procesooperacion.getOpId()});
    }
}
