package energigas.apps.systemstrategy.energigas.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import energigas.apps.systemstrategy.energigas.entities.Producto;
import energigas.apps.systemstrategy.energigas.utils.Constants;

/**
 * Created by KelvinThony on 11/08/2016.
 */
public class DB_Producto {
    public static final String _id = "_id";
    public static final String proId = "proId";
    public static final String nombre = "nombre";
    public static final String descripcion = "descripcion";
    public static final String estado = "estado";
    public static final String codigo = "codigo";
    public static final String empresaId = "empresaId";
    public static final String usuarioCreacion = "usuarioCreacion";
    public static final String fechaCreacion = "fechaCreacion";
    public static final String usuarioActualizacion = "usuarioActualizacion";
    public static final String fechaActualizacion = "fechaActualizacion";
    private DbHelper mDbHelper;
    private SQLiteDatabase mDb;
    private static final String SQLITE_TABLA_DB_PRODUCTO = "DB_Producto";
    private final Context mCtx;
    public static final String CREATE_TABLA_DB_PRODUCTO =
            "create table " + SQLITE_TABLA_DB_PRODUCTO + " ("
                    + _id + " integer primary key autoincrement,"
                    + proId + " integer ,"
                    + nombre + " text ,"
                    + descripcion + " text ,"
                    + estado + " integer ,"
                    + codigo + " text ,"
                    + empresaId + " integer ,"
                    + usuarioCreacion + " integer ,"
                    + fechaCreacion + " text ,"
                    + usuarioActualizacion + " integer ,"
                    + fechaActualizacion + " text ,"
                    + Constants._CLMEXPORT + " integer );";
    public static final String DELETE_TABLA_DB_PRODUCTO = "DROP TABLE IF EXISTS " + SQLITE_TABLA_DB_PRODUCTO;

    public DB_Producto(Context ctx) {
        this.mCtx = ctx;
    }

    public DB_Producto open() {
        mDbHelper = new DbHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        if (mDbHelper != null) {
            mDbHelper.close();
        }
    }

    public long createProducto(@NonNull Producto producto) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(proId, producto.getProId());
        initialValues.put(nombre, producto.getNombre());
        initialValues.put(descripcion, producto.getDescripcion());
        initialValues.put(estado, producto.isEstado());
        initialValues.put(codigo, producto.getCodigo());
        initialValues.put(empresaId, producto.getEmpresaId());
        initialValues.put(usuarioCreacion, producto.getUsuarioCreacion());
        initialValues.put(fechaCreacion, producto.getFechaCreacion());
        initialValues.put(usuarioActualizacion, producto.getUsuarioActualizacion());
        initialValues.put(fechaActualizacion, producto.getFechaActualizacion());
        initialValues.put(Constants._CLMEXPORT, Constants._CREADO);
        return mDb.insert(SQLITE_TABLA_DB_PRODUCTO, null, initialValues);
    }

    public long updateProducto(@NonNull Producto producto) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(nombre, producto.getNombre());
        initialValues.put(descripcion, producto.getDescripcion());
        initialValues.put(estado, producto.isEstado());
        initialValues.put(codigo, producto.getCodigo());
        initialValues.put(empresaId, producto.getEmpresaId());
        initialValues.put(usuarioCreacion, producto.getUsuarioCreacion());
        initialValues.put(fechaCreacion, producto.getFechaCreacion());
        initialValues.put(usuarioActualizacion, producto.getUsuarioActualizacion());
        initialValues.put(fechaActualizacion, producto.getFechaActualizacion());
        initialValues.put(Constants._CLMEXPORT, Constants._CREADO);
        return mDb.update(SQLITE_TABLA_DB_PRODUCTO, initialValues,
                proId + "=?", new String[]{"" + producto.getProId()});
    }
}
