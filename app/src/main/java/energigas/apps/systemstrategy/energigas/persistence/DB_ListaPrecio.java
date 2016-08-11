package energigas.apps.systemstrategy.energigas.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import energigas.apps.systemstrategy.energigas.entities.ListaPrecio;
import energigas.apps.systemstrategy.energigas.utils.Constants;

/**
 * Created by KelvinThony on 11/08/2016.
 */
public class DB_ListaPrecio {
    public static final String _id = "_id";
    public static final String lpId = "lpId";
    public static final String nombre = "nombre";
    public static final String proId = "proId";
    public static final String estadoId = "estadoId";
    public static final String fechaCaducidad = "fechaCaducidad";
    public static final String fechaCreacion = "fechaCreacion";
    public static final String fechaActualizacion = "fechaActualizacion";
    public static final String usuarioCreacion = "usuarioCreacion";
    public static final String usuarioActualizacion = "usuarioActualizacion";
    public static final String empresaId = "empresaId";
    public static final String tipoId = "tipoId";
    private DbHelper mDbHelper;
    private SQLiteDatabase mDb;
    private static final String SQLITE_TABLA_DB_LISTAPRECIO = "DB_ListaPrecio";
    private final Context mCtx;
    public static final String CREATE_TABLA_DB_LISTAPRECIO =
            "create table " + SQLITE_TABLA_DB_LISTAPRECIO + " ("
                    + _id + " integer primary key autoincrement,"
                    + lpId + " integer ,"
                    + nombre + " text ,"
                    + proId + " integer ,"
                    + estadoId + " integer ,"
                    + fechaCaducidad + " text ,"
                    + fechaCreacion + " text ,"
                    + fechaActualizacion + " text ,"
                    + usuarioCreacion + " integer ,"
                    + usuarioActualizacion + " integer ,"
                    + empresaId + " integer ,"
                    + tipoId + " integer ,"
                    + Constants._CLMEXPORT + " integer );";
    public static final String DELETE_TABLA_DB_LISTAPRECIO = "DROP TABLE IF EXISTS " + SQLITE_TABLA_DB_LISTAPRECIO;

    public DB_ListaPrecio(Context ctx) {
        this.mCtx = ctx;
    }

    public DB_ListaPrecio open() {
        mDbHelper = new DbHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        if (mDbHelper != null) {
            mDbHelper.close();
        }
    }

    public long createListaPrecio(@NonNull ListaPrecio listaprecio) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(lpId, listaprecio.getLpId());
        initialValues.put(nombre, listaprecio.getNombre());
        initialValues.put(proId, listaprecio.getProId());
        initialValues.put(estadoId, listaprecio.getEstadoId());
        initialValues.put(fechaCaducidad, listaprecio.getFechaCaducidad());
        initialValues.put(fechaCreacion, listaprecio.getFechaCreacion());
        initialValues.put(fechaActualizacion, listaprecio.getFechaActualizacion());
        initialValues.put(usuarioCreacion, listaprecio.getUsuarioCreacion());
        initialValues.put(usuarioActualizacion, listaprecio.getUsuarioActualizacion());
        initialValues.put(empresaId, listaprecio.getEmpresaId());
        initialValues.put(tipoId, listaprecio.getTipoId());
        initialValues.put(Constants._CLMEXPORT, Constants._CREADO);
        return mDb.insert(SQLITE_TABLA_DB_LISTAPRECIO, null, initialValues);
    }

    public long updateListaPrecio(@NonNull ListaPrecio listaprecio) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(nombre, listaprecio.getNombre());
        initialValues.put(proId, listaprecio.getProId());
        initialValues.put(estadoId, listaprecio.getEstadoId());
        initialValues.put(fechaCaducidad, listaprecio.getFechaCaducidad());
        initialValues.put(fechaCreacion, listaprecio.getFechaCreacion());
        initialValues.put(fechaActualizacion, listaprecio.getFechaActualizacion());
        initialValues.put(usuarioCreacion, listaprecio.getUsuarioCreacion());
        initialValues.put(usuarioActualizacion, listaprecio.getUsuarioActualizacion());
        initialValues.put(empresaId, listaprecio.getEmpresaId());
        initialValues.put(tipoId, listaprecio.getTipoId());
        initialValues.put(Constants._CLMEXPORT, Constants._CREADO);
        return mDb.update(SQLITE_TABLA_DB_LISTAPRECIO, initialValues,
                lpId + "=?", new String[]{"" + listaprecio.getLpId()});
    }
}
