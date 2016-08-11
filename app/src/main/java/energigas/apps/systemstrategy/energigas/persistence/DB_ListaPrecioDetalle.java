package energigas.apps.systemstrategy.energigas.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import energigas.apps.systemstrategy.energigas.entities.ListaPrecioDetalle;
import energigas.apps.systemstrategy.energigas.utils.Constants;

/**
 * Created by KelvinThony on 11/08/2016.
 */
public class DB_ListaPrecioDetalle {
    public static final String _id = "_id";
    public static final String lpdId = "lpdId";
    public static final String lpId = "lpId";
    public static final String clienteId = "clienteId";
    public static final String establecimientoId = "establecimientoId";
    public static final String unId = "unId";
    public static final String proId = "proId";
    public static final String precio = "precio";
    public static final String precioImp = "precioImp";
    public static final String fechaActualizacion = "fechaActualizacion";
    public static final String usuarioActualizacion = "usuarioActualizacion";
    private DbHelper mDbHelper;
    private SQLiteDatabase mDb;
    private static final String SQLITE_TABLA_DB_LISTAPRECIODETALLE = "DB_ListaPrecioDetalle";
    private final Context mCtx;
    public static final String CREATE_TABLA_DB_LISTAPRECIODETALLE =
            "create table " + SQLITE_TABLA_DB_LISTAPRECIODETALLE + " ("
                    + _id + " integer primary key autoincrement,"
                    + lpdId + " integer ,"
                    + lpId + " integer ,"
                    + clienteId + " integer ,"
                    + establecimientoId + " integer ,"
                    + unId + " integer ,"
                    + proId + " integer ,"
                    + precio + " real ,"
                    + precioImp + " real ,"
                    + fechaActualizacion + " text ,"
                    + usuarioActualizacion + " integer ,"
                    + Constants._CLMEXPORT + " integer );";
    public static final String DELETE_TABLA_DB_LISTAPRECIODETALLE = "DROP TABLE IF EXISTS " + SQLITE_TABLA_DB_LISTAPRECIODETALLE;

    public DB_ListaPrecioDetalle(Context ctx) {
        this.mCtx = ctx;
    }

    public DB_ListaPrecioDetalle open() {
        mDbHelper = new DbHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        if (mDbHelper != null) {
            mDbHelper.close();
        }
    }

    public long createListaPrecioDetalle(@NonNull ListaPrecioDetalle listapreciodetalle) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(lpdId, listapreciodetalle.getLpdId());
        initialValues.put(lpId, listapreciodetalle.getLpId());
        initialValues.put(clienteId, listapreciodetalle.getClienteId());
        initialValues.put(establecimientoId, listapreciodetalle.getEstablecimientoId());
        initialValues.put(unId, listapreciodetalle.getUnId());
        initialValues.put(proId, listapreciodetalle.getProId());
        initialValues.put(precio, listapreciodetalle.getPrecio());
        initialValues.put(precioImp, listapreciodetalle.getPrecioImp());
        initialValues.put(fechaActualizacion, listapreciodetalle.getFechaActualizacion());
        initialValues.put(usuarioActualizacion, listapreciodetalle.getUsuarioActualizacion());
        initialValues.put(Constants._CLMEXPORT, Constants._CREADO);
        return mDb.insert(SQLITE_TABLA_DB_LISTAPRECIODETALLE, null, initialValues);
    }

    public long updateListaPrecioDetalle(@NonNull ListaPrecioDetalle listapreciodetalle) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(lpId, listapreciodetalle.getLpId());
        initialValues.put(clienteId, listapreciodetalle.getClienteId());
        initialValues.put(establecimientoId, listapreciodetalle.getEstablecimientoId());
        initialValues.put(unId, listapreciodetalle.getUnId());
        initialValues.put(proId, listapreciodetalle.getProId());
        initialValues.put(precio, listapreciodetalle.getPrecio());
        initialValues.put(precioImp, listapreciodetalle.getPrecioImp());
        initialValues.put(fechaActualizacion, listapreciodetalle.getFechaActualizacion());
        initialValues.put(usuarioActualizacion, listapreciodetalle.getUsuarioActualizacion());
        initialValues.put(Constants._CLMEXPORT, Constants._CREADO);
        return mDb.update(SQLITE_TABLA_DB_LISTAPRECIODETALLE, initialValues,
                lpdId + "=?", new String[]{"" + listapreciodetalle.getLpdId()});
    }
}
