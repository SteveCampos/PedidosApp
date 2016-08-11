package energigas.apps.systemstrategy.energigas.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import energigas.apps.systemstrategy.energigas.entities.Descuento;
import energigas.apps.systemstrategy.energigas.utils.Constants;

/**
 * Created by KelvinThony on 11/08/2016.
 */
public class DB_Descuento {
    public static final String _id = "_id";
    public static final String descuentoId = "descuentoId";
    public static final String descripcion = "descripcion";
    public static final String descuento = "descuento";
    public static final String fechaCreacion = "fechaCreacion";
    public static final String fechaActualizacion = "fechaActualizacion";
    public static final String fechaPublicacion = "fechaPublicacion";
    public static final String usuarioCreacion = "usuarioCreacion";
    public static final String usuarioAccion = "usuarioAccion";
    public static final String modalidadDescuentoId = "modalidadDescuentoId";
    private DbHelper mDbHelper;
    private SQLiteDatabase mDb;
    private static final String SQLITE_TABLA_DB_DESCUENTO = "DB_Descuento";
    private final Context mCtx;
    public static final String CREATE_TABLA_DB_DESCUENTO =
            "create table " + SQLITE_TABLA_DB_DESCUENTO + " ("
                    + _id + " integer primary key autoincrement,"
                    + descuentoId + " integer ,"
                    + descripcion + " text ,"
                    + descuento + " real ,"
                    + fechaCreacion + " text ,"
                    + fechaActualizacion + " text ,"
                    + fechaPublicacion + " text ,"
                    + usuarioCreacion + " integer ,"
                    + usuarioAccion + " integer ,"
                    + modalidadDescuentoId + " integer ,"
                    + Constants._CLMEXPORT + " integer );";
    public static final String DELETE_TABLA_DB_DESCUENTO = "DROP TABLE IF EXISTS " + SQLITE_TABLA_DB_DESCUENTO;

    public DB_Descuento(Context ctx) {
        this.mCtx = ctx;
    }

    public DB_Descuento open() {
        mDbHelper = new DbHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        if (mDbHelper != null) {
            mDbHelper.close();
        }
    }

    public long createDescuento(@NonNull Descuento descuent) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(descuentoId, descuent.getDescuentoId());
        initialValues.put(descripcion, descuent.getDescripcion());
        initialValues.put(descuento, descuent.getDescuento());
        initialValues.put(fechaCreacion, descuent.getFechaCreacion());
        initialValues.put(fechaActualizacion, descuent.getFechaActualizacion());
        initialValues.put(fechaPublicacion, descuent.getFechaPublicacion());
        initialValues.put(usuarioCreacion, descuent.getUsuarioCreacion());
        initialValues.put(usuarioAccion, descuent.getUsuarioAccion());
        initialValues.put(modalidadDescuentoId, descuent.getModalidadDescuentoId());
        initialValues.put(Constants._CLMEXPORT, Constants._CREADO);
        return mDb.insert(SQLITE_TABLA_DB_DESCUENTO, null, initialValues);
    }

    public long updateDescuento(@NonNull Descuento descuent) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(descripcion, descuent.getDescripcion());
        initialValues.put(descuento, descuent.getDescuento());
        initialValues.put(fechaCreacion, descuent.getFechaCreacion());
        initialValues.put(fechaActualizacion, descuent.getFechaActualizacion());
        initialValues.put(fechaPublicacion, descuent.getFechaPublicacion());
        initialValues.put(usuarioCreacion, descuent.getUsuarioCreacion());
        initialValues.put(usuarioAccion, descuent.getUsuarioAccion());
        initialValues.put(modalidadDescuentoId, descuent.getModalidadDescuentoId());
        initialValues.put(Constants._CLMEXPORT, Constants._CREADO);
        return mDb.update(SQLITE_TABLA_DB_DESCUENTO, initialValues,
                descuentoId + "=?", new String[]{"" + descuent.getDescuentoId()});
    }
}
