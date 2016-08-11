package energigas.apps.systemstrategy.energigas.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import energigas.apps.systemstrategy.energigas.entities.Almacen;
import energigas.apps.systemstrategy.energigas.utils.Constants;

/**
 * Created by KelvinThony on 11/08/2016.
 */
public class DB_Almacen {
    public static final String _id = "_id";
    public static final String almId = "almId";
    public static final String nombre = "nombre";
    public static final String productoId = "productoId";
    public static final String capacidadNeta = "capacidadNeta";
    public static final String politica = "politica";
    public static final String capacidadReal = "capacidadReal";
    public static final String stockMinimo = "stockMinimo";
    public static final String stockActual = "stockActual";
    public static final String stockPermanente = "stockPermanente";
    public static final String usuarioCreacion = "usuarioCreacion";
    public static final String fechaCreacion = "fechaCreacion";
    public static final String usuarioActualizacion = "usuarioActualizacion";
    public static final String fechaActualizacion = "fechaActualizacion";
    public static final String establecimientoId = "establecimientoId";
    public static final String vehiculoId = "vehiculoId";
    public static final String estado = "estado";
    public static final String politicaSP = "politicaSP";
    public static final String placa = "placa";
    public static final String rigido = "rigido";
    private DbHelper mDbHelper;
    private SQLiteDatabase mDb;
    private static final String SQLITE_TABLA_DB_ALMACEN = "DB_Almacen";
    private final Context mCtx;
    public static final String CREATE_TABLA_DB_ALMACEN =
            "create table " + SQLITE_TABLA_DB_ALMACEN + " ("
                    + _id + " integer primary key autoincrement,"
                    + almId + " integer ,"
                    + nombre + " text ,"
                    + productoId + " integer ,"
                    + capacidadNeta + " real ,"
                    + politica + " real ,"
                    + capacidadReal + " real ,"
                    + stockMinimo + " real ,"
                    + stockActual + " real ,"
                    + stockPermanente + " real ,"
                    + usuarioCreacion + " integer ,"
                    + fechaCreacion + " text ,"
                    + usuarioActualizacion + " integer ,"
                    + fechaActualizacion + " text ,"
                    + establecimientoId + " integer ,"
                    + vehiculoId + " integer ,"
                    + estado + " integer ,"
                    + politicaSP + " real ,"
                    + placa + " text ,"
                    + rigido + " integer ,"
                    + Constants._CLMEXPORT + " integer );";
    public static final String DELETE_TABLA_DB_ALMACEN = "DROP TABLE IF EXISTS " + SQLITE_TABLA_DB_ALMACEN;

    public DB_Almacen(Context ctx) {
        this.mCtx = ctx;
    }

    public DB_Almacen open() {
        mDbHelper = new DbHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        if (mDbHelper != null) {
            mDbHelper.close();
        }
    }

    public long createAlmacen(@NonNull Almacen almacen) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(almId, almacen.getAlmId());
        initialValues.put(nombre, almacen.getNombre());
        initialValues.put(productoId, almacen.getProductoId());
        initialValues.put(capacidadNeta, almacen.getCapacidadNeta());
        initialValues.put(politica, almacen.getPolitica());
        initialValues.put(capacidadReal, almacen.getCapacidadReal());
        initialValues.put(stockMinimo, almacen.getStockMinimo());
        initialValues.put(stockActual, almacen.getStockActual());
        initialValues.put(stockPermanente, almacen.getStockPermanente());
        initialValues.put(usuarioCreacion, almacen.getUsuarioCreacion());
        initialValues.put(fechaCreacion, almacen.getFechaCreacion());
        initialValues.put(usuarioActualizacion, almacen.getUsuarioActualizacion());
        initialValues.put(fechaActualizacion, almacen.getFechaActualizacion());
        initialValues.put(establecimientoId, almacen.getEstablecimientoId());
        initialValues.put(vehiculoId, almacen.getVehiculoId());
        initialValues.put(estado, almacen.isEstado());
        initialValues.put(politicaSP, almacen.getPoliticaSP());
        initialValues.put(placa, almacen.getPlaca());
        initialValues.put(rigido, almacen.isRigido());
        initialValues.put(Constants._CLMEXPORT, Constants._CREADO);
        return mDb.insert(SQLITE_TABLA_DB_ALMACEN, null, initialValues);
    }

    public long updateAlmacen(@NonNull Almacen almacen) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(nombre, almacen.getNombre());
        initialValues.put(productoId, almacen.getProductoId());
        initialValues.put(capacidadNeta, almacen.getCapacidadNeta());
        initialValues.put(politica, almacen.getPolitica());
        initialValues.put(capacidadReal, almacen.getCapacidadReal());
        initialValues.put(stockMinimo, almacen.getStockMinimo());
        initialValues.put(stockActual, almacen.getStockActual());
        initialValues.put(stockPermanente, almacen.getStockPermanente());
        initialValues.put(usuarioCreacion, almacen.getUsuarioCreacion());
        initialValues.put(fechaCreacion, almacen.getFechaCreacion());
        initialValues.put(usuarioActualizacion, almacen.getUsuarioActualizacion());
        initialValues.put(fechaActualizacion, almacen.getFechaActualizacion());
        initialValues.put(establecimientoId, almacen.getEstablecimientoId());
        initialValues.put(vehiculoId, almacen.getVehiculoId());
        initialValues.put(estado, almacen.isEstado());
        initialValues.put(politicaSP, almacen.getPoliticaSP());
        initialValues.put(placa, almacen.getPlaca());
        initialValues.put(rigido, almacen.isRigido());
        initialValues.put(Constants._CLMEXPORT, Constants._CREADO);
        return mDb.update(SQLITE_TABLA_DB_ALMACEN, initialValues,
                almId + "=?", new String[]{"" + almacen.getAlmId()});
    }
}
