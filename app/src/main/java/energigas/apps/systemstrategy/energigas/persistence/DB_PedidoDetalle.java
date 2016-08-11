package energigas.apps.systemstrategy.energigas.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import energigas.apps.systemstrategy.energigas.entities.PedidoDetalle;
import energigas.apps.systemstrategy.energigas.utils.Constants;

/**
 * Created by KelvinThony on 11/08/2016.
 */
public class DB_PedidoDetalle {
    public static final String _id = "_id";
    public static final String peId = "peId";
    public static final String pdId = "pdId";
    public static final String productoId = "productoId";
    public static final String cantidad = "cantidad";
    public static final String cantidadAtendida = "cantidadAtendida";
    public static final String importe = "importe";
    public static final String usuarioId = "usuarioId";
    public static final String fechaAccion = "fechaAccion";
    public static final String precioUnitario = "precioUnitario";
    public static final String costoVenta = "costoVenta";
    public static final String unidadId = "unidadId";
    public static final String estadoAtencionId = "estadoAtencionId";
    public static final String precio = "precio";
    public static final String golpe = "golpe";
    private DbHelper mDbHelper;
    private SQLiteDatabase mDb;
    private static final String SQLITE_TABLA_DB_PEDIDODETALLE = "DB_PedidoDetalle";
    private final Context mCtx;
    public static final String CREATE_TABLA_DB_PEDIDODETALLE =
            "create table " + SQLITE_TABLA_DB_PEDIDODETALLE + " ("
                    + _id + " integer primary key autoincrement,"
                    + peId + " integer ,"
                    + pdId + " integer ,"
                    + productoId + " integer ,"
                    + cantidad + " real ,"
                    + cantidadAtendida + " real ,"
                    + importe + " real ,"
                    + usuarioId + " integer ,"
                    + fechaAccion + " text ,"
                    + precioUnitario + " real ,"
                    + costoVenta + " real ,"
                    + unidadId + " integer ,"
                    + estadoAtencionId + " integer ,"
                    + precio + " real ,"
                    + golpe + " text ,"
                    + Constants._CLMEXPORT + " integer );";
    public static final String DELETE_TABLA_DB_PEDIDODETALLE = "DROP TABLE IF EXISTS " + SQLITE_TABLA_DB_PEDIDODETALLE;

    public DB_PedidoDetalle(Context ctx) {
        this.mCtx = ctx;
    }

    public DB_PedidoDetalle open() {
        mDbHelper = new DbHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        if (mDbHelper != null) {
            mDbHelper.close();
        }
    }

    public long createPedidoDetalle(@NonNull PedidoDetalle pedidodetalle) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(peId, pedidodetalle.getPeId());
        initialValues.put(pdId, pedidodetalle.getPdId());
        initialValues.put(productoId, pedidodetalle.getProductoId());
        initialValues.put(cantidad, pedidodetalle.getCantidad());
        initialValues.put(cantidadAtendida, pedidodetalle.getCantidadAtendida());
        initialValues.put(importe, pedidodetalle.getImporte());
        initialValues.put(usuarioId, pedidodetalle.getUsuarioId());
        initialValues.put(fechaAccion, pedidodetalle.getFechaAccion());
        initialValues.put(precioUnitario, pedidodetalle.getPrecioUnitario());
        initialValues.put(costoVenta, pedidodetalle.getCostoVenta());
        initialValues.put(unidadId, pedidodetalle.getUnidadId());
        initialValues.put(estadoAtencionId, pedidodetalle.getEstadoAtencionId());
        initialValues.put(precio, pedidodetalle.getPrecio());
        initialValues.put(golpe, pedidodetalle.getGolpe());
        initialValues.put(Constants._CLMEXPORT, Constants._CREADO);
        return mDb.insert(SQLITE_TABLA_DB_PEDIDODETALLE, null, initialValues);
    }

    public long updatePedidoDetalle(@NonNull PedidoDetalle pedidodetalle) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(pdId, pedidodetalle.getPdId());
        initialValues.put(productoId, pedidodetalle.getProductoId());
        initialValues.put(cantidad, pedidodetalle.getCantidad());
        initialValues.put(cantidadAtendida, pedidodetalle.getCantidadAtendida());
        initialValues.put(importe, pedidodetalle.getImporte());
        initialValues.put(usuarioId, pedidodetalle.getUsuarioId());
        initialValues.put(fechaAccion, pedidodetalle.getFechaAccion());
        initialValues.put(precioUnitario, pedidodetalle.getPrecioUnitario());
        initialValues.put(costoVenta, pedidodetalle.getCostoVenta());
        initialValues.put(unidadId, pedidodetalle.getUnidadId());
        initialValues.put(estadoAtencionId, pedidodetalle.getEstadoAtencionId());
        initialValues.put(precio, pedidodetalle.getPrecio());
        initialValues.put(golpe, pedidodetalle.getGolpe());
        initialValues.put(Constants._CLMEXPORT, Constants._CREADO);
        return mDb.update(SQLITE_TABLA_DB_PEDIDODETALLE, initialValues,
                peId + "=?", new String[]{"" + pedidodetalle.getPeId()});
    }
}
