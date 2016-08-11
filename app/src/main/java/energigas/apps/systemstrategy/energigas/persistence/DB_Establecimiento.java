package energigas.apps.systemstrategy.energigas.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import energigas.apps.systemstrategy.energigas.entities.Establecimiento;
import energigas.apps.systemstrategy.energigas.utils.Constants;

/**
 * Created by KelvinThony on 11/08/2016.
 */
public class DB_Establecimiento {
    public static final String _id = "_id";
    public static final String estIEstablecimientoId = "estIEstablecimientoId";
    public static final String estVCodigo = "estVCodigo";
    public static final String estVDescripcion = "estVDescripcion";
    public static final String estIClienteId = "estIClienteId";
    public static final String ubId = "ubId";
    public static final String estICategoriaId = "estICategoriaId";
    public static final String estICanalId = "estICanalId";
    public static final String estVTelefono = "estVTelefono";
    public static final String estVCelular = "estVCelular";
    public static final String estIUsuarioCreacion = "estIUsuarioCreacion";
    public static final String estDTFechaCreacion = "estDTFechaCreacion";
    public static final String estIUsuarioActualizacion = "estIUsuarioActualizacion";
    public static final String estDTFechaActualizacion = "estDTFechaActualizacion";
    public static final String estIEstadoId = "estIEstadoId";
    public static final String estIUsuarioAprobacion = "estIUsuarioAprobacion";
    public static final String estDTFechaAprobacion = "estDTFechaAprobacion";
    public static final String estVObservacion = "estVObservacion";
    public static final String estVKeyFireBase = "estVKeyFireBase";
    public static final String estVContacto = "estVContacto";
    private DbHelper mDbHelper;
    private SQLiteDatabase mDb;
    private static final String SQLITE_TABLA_DB_ESTABLECIMIENTO = "DB_Establecimiento";
    private final Context mCtx;
    public static final String CREATE_TABLA_DB_ESTABLECIMIENTO =
            "create table " + SQLITE_TABLA_DB_ESTABLECIMIENTO + " ("
                    + _id + " integer primary key autoincrement,"
                    + estIEstablecimientoId + " integer ,"
                    + estVCodigo + " text ,"
                    + estVDescripcion + " text ,"
                    + estIClienteId + " integer ,"
                    + ubId + " integer ,"
                    + estICategoriaId + " integer ,"
                    + estICanalId + " integer ,"
                    + estVTelefono + " text ,"
                    + estVCelular + " text ,"
                    + estIUsuarioCreacion + " integer ,"
                    + estDTFechaCreacion + " text ,"
                    + estIUsuarioActualizacion + " integer ,"
                    + estDTFechaActualizacion + " text ,"
                    + estIEstadoId + " integer ,"
                    + estIUsuarioAprobacion + " integer ,"
                    + estDTFechaAprobacion + " text ,"
                    + estVObservacion + " text ,"
                    + estVKeyFireBase + " text ,"
                    + estVContacto + " text ,"
                    + Constants._CLMEXPORT + " integer );";
    public static final String DELETE_TABLA_DB_ESTABLECIMIENTO = "DROP TABLE IF EXISTS " + SQLITE_TABLA_DB_ESTABLECIMIENTO;

    public DB_Establecimiento(Context ctx) {
        this.mCtx = ctx;
    }

    public DB_Establecimiento open() {
        mDbHelper = new DbHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        if (mDbHelper != null) {
            mDbHelper.close();
        }
    }

    public long createEstablecimiento(@NonNull Establecimiento establecimiento) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(estIEstablecimientoId, establecimiento.getEstIEstablecimientoId());
        initialValues.put(estVCodigo, establecimiento.getEstVCodigo());
        initialValues.put(estVDescripcion, establecimiento.getEstVDescripcion());
        initialValues.put(estIClienteId, establecimiento.getEstIClienteId());
        initialValues.put(ubId, establecimiento.getUbId());
        initialValues.put(estICategoriaId, establecimiento.getEstICategoriaId());
        initialValues.put(estICanalId, establecimiento.getEstICanalId());
        initialValues.put(estVTelefono, establecimiento.getEstVTelefono());
        initialValues.put(estVCelular, establecimiento.getEstVCelular());
        initialValues.put(estIUsuarioCreacion, establecimiento.getEstIUsuarioCreacion());
        initialValues.put(estDTFechaCreacion, establecimiento.getEstDTFechaCreacion());
        initialValues.put(estIUsuarioActualizacion, establecimiento.getEstIUsuarioActualizacion());
        initialValues.put(estDTFechaActualizacion, establecimiento.getEstDTFechaActualizacion());
        initialValues.put(estIEstadoId, establecimiento.getEstIEstadoId());
        initialValues.put(estIUsuarioAprobacion, establecimiento.getEstIUsuarioAprobacion());
        initialValues.put(estDTFechaAprobacion, establecimiento.getEstDTFechaAprobacion());
        initialValues.put(estVObservacion, establecimiento.getEstVObservacion());
        initialValues.put(estVKeyFireBase, establecimiento.getEstVKeyFireBase());
        initialValues.put(estVContacto, establecimiento.getEstVContacto());
        initialValues.put(Constants._CLMEXPORT, Constants._CREADO);
        return mDb.insert(SQLITE_TABLA_DB_ESTABLECIMIENTO, null, initialValues);
    }

    public long updateEstablecimiento(@NonNull Establecimiento establecimiento) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(estVCodigo, establecimiento.getEstVCodigo());
        initialValues.put(estVDescripcion, establecimiento.getEstVDescripcion());
        initialValues.put(estIClienteId, establecimiento.getEstIClienteId());
        initialValues.put(ubId, establecimiento.getUbId());
        initialValues.put(estICategoriaId, establecimiento.getEstICategoriaId());
        initialValues.put(estICanalId, establecimiento.getEstICanalId());
        initialValues.put(estVTelefono, establecimiento.getEstVTelefono());
        initialValues.put(estVCelular, establecimiento.getEstVCelular());
        initialValues.put(estIUsuarioCreacion, establecimiento.getEstIUsuarioCreacion());
        initialValues.put(estDTFechaCreacion, establecimiento.getEstDTFechaCreacion());
        initialValues.put(estIUsuarioActualizacion, establecimiento.getEstIUsuarioActualizacion());
        initialValues.put(estDTFechaActualizacion, establecimiento.getEstDTFechaActualizacion());
        initialValues.put(estIEstadoId, establecimiento.getEstIEstadoId());
        initialValues.put(estIUsuarioAprobacion, establecimiento.getEstIUsuarioAprobacion());
        initialValues.put(estDTFechaAprobacion, establecimiento.getEstDTFechaAprobacion());
        initialValues.put(estVObservacion, establecimiento.getEstVObservacion());
        initialValues.put(estVKeyFireBase, establecimiento.getEstVKeyFireBase());
        initialValues.put(estVContacto, establecimiento.getEstVContacto());
        initialValues.put(Constants._CLMEXPORT, Constants._CREADO);
        return mDb.update(SQLITE_TABLA_DB_ESTABLECIMIENTO, initialValues,
                estIEstablecimientoId + "=?", new String[]{"" + establecimiento.getEstIEstablecimientoId()});
    }
}
