package energigas.apps.systemstrategy.energigas.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import energigas.apps.systemstrategy.energigas.entities.ProcesoFase;
import energigas.apps.systemstrategy.energigas.utils.Constants;

/**
 * Created by KelvinThony on 11/08/2016.
 */
public class DB_ProcesoFase {
    public static final String _id = "_id";
    public static final String faId = "faId";
    public static final String rolId = "rolId";
    public static final String parentId = "parentId";
    public static final String nombre = "nombre";
    public static final String proId = "proId";
    public static final String estado = "estado";
    public static final String usuarioCreacion = "usuarioCreacion";
    public static final String fechaCreacion = "fechaCreacion";
    public static final String usuarioActualizacion = "usuarioActualizacion";
    public static final String fechaActualizacion = "fechaActualizacion";
    private DbHelper mDbHelper;
    private SQLiteDatabase mDb;
    private static final String SQLITE_TABLA_DB_PROCESOFASE = "DB_ProcesoFase";
    private final Context mCtx;
    public static final String CREATE_TABLA_DB_PROCESOFASE =
            "create table " + SQLITE_TABLA_DB_PROCESOFASE + " ("
                    + _id + " integer primary key autoincrement,"
                    + faId + " integer ,"
                    + rolId + " integer ,"
                    + parentId + " integer ,"
                    + nombre + " text ,"
                    + proId + " integer ,"
                    + estado + " integer ,"
                    + usuarioCreacion + " integer ,"
                    + fechaCreacion + " text ,"
                    + usuarioActualizacion + " integer ,"
                    + fechaActualizacion + " text ,"
                    + Constants._CLMEXPORT + " integer );";
    public static final String DELETE_TABLA_DB_PROCESOFASE = "DROP TABLE IF EXISTS " + SQLITE_TABLA_DB_PROCESOFASE;

    public DB_ProcesoFase(Context ctx) {
        this.mCtx = ctx;
    }

    public DB_ProcesoFase open() {
        mDbHelper = new DbHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        if (mDbHelper != null) {
            mDbHelper.close();
        }
    }

    public long createProcesoFase(@NonNull ProcesoFase procesofase) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(faId, procesofase.getFaId());
        initialValues.put(rolId, procesofase.getRolId());
        initialValues.put(parentId, procesofase.getParentId());
        initialValues.put(nombre, procesofase.getNombre());
        initialValues.put(proId, procesofase.getProId());
        initialValues.put(estado, procesofase.isEstado());
        initialValues.put(usuarioCreacion, procesofase.getUsuarioCreacion());
        initialValues.put(fechaCreacion, procesofase.getFechaCreacion());
        initialValues.put(usuarioActualizacion, procesofase.getUsuarioActualizacion());
        initialValues.put(fechaActualizacion, procesofase.getFechaActualizacion());
        initialValues.put(Constants._CLMEXPORT, Constants._CREADO);
        return mDb.insert(SQLITE_TABLA_DB_PROCESOFASE, null, initialValues);
    }

    public long updateProcesoFase(@NonNull ProcesoFase procesofase) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(rolId, procesofase.getRolId());
        initialValues.put(parentId, procesofase.getParentId());
        initialValues.put(nombre, procesofase.getNombre());
        initialValues.put(proId, procesofase.getProId());
        initialValues.put(estado, procesofase.isEstado());
        initialValues.put(usuarioCreacion, procesofase.getUsuarioCreacion());
        initialValues.put(fechaCreacion, procesofase.getFechaCreacion());
        initialValues.put(usuarioActualizacion, procesofase.getUsuarioActualizacion());
        initialValues.put(fechaActualizacion, procesofase.getFechaActualizacion());
        initialValues.put(Constants._CLMEXPORT, Constants._CREADO);
        return mDb.update(SQLITE_TABLA_DB_PROCESOFASE, initialValues,
                faId + "=?", new String[]{"" + procesofase.getFaId()});
    }
}
