package energigas.apps.systemstrategy.energigas.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import energigas.apps.systemstrategy.energigas.entities.ComprobanteVentaDetalle;
import energigas.apps.systemstrategy.energigas.utils.Constants;

/**
 * Created by KelvinThony on 11/08/2016.
 */
public class DB_ComprobanteVentaDetalle {
    public static final String _id = "_id";
    public static final String compId = "compId";
    public static final String compdId = "compdId";
    public static final String proId = "proId";
    public static final String unidadId = "unidadId";
    public static final String cantidad = "cantidad";
    public static final String precio = "precio";
    public static final String precioUnitario = "precioUnitario";
    public static final String costoVenta = "costoVenta";
    public static final String importe = "importe";
    public static final String usuarioActualizacion = "usuarioActualizacion";
    public static final String fechaActualizacion = "fechaActualizacion";
    private DbHelper mDbHelper;
    private SQLiteDatabase mDb;
    private static final String SQLITE_TABLA_DB_COMPROBANTEVENTADETALLE = "DB_ComprobanteVentaDetalle";
    private final Context mCtx;
    public static final String CREATE_TABLA_DB_COMPROBANTEVENTADETALLE =
            "create table " + SQLITE_TABLA_DB_COMPROBANTEVENTADETALLE + " ("
                    + _id + " integer primary key autoincrement,"
                    + compId + " integer ,"
                    + compdId + " integer ,"
                    + proId + " integer ,"
                    + unidadId + " integer ,"
                    + cantidad + " integer ,"
                    + precio + " real ,"
                    + precioUnitario + " real ,"
                    + costoVenta + " real ,"
                    + importe + " real ,"
                    + usuarioActualizacion + " integer ,"
                    + fechaActualizacion + " text ,"
                    + Constants._CLMEXPORT + " integer );";
    public static final String DELETE_TABLA_DB_COMPROBANTEVENTADETALLE = "DROP TABLE IF EXISTS " + SQLITE_TABLA_DB_COMPROBANTEVENTADETALLE;

    public DB_ComprobanteVentaDetalle(Context ctx) {
        this.mCtx = ctx;
    }

    public DB_ComprobanteVentaDetalle open() {
        mDbHelper = new DbHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        if (mDbHelper != null) {
            mDbHelper.close();
        }
    }

    public long createComprobanteVentaDetalle(@NonNull ComprobanteVentaDetalle comprobanteventadetalle) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(compId, comprobanteventadetalle.getCompId());
        initialValues.put(compdId, comprobanteventadetalle.getCompdId());
        initialValues.put(proId, comprobanteventadetalle.getProId());
        initialValues.put(unidadId, comprobanteventadetalle.getUnidadId());
        initialValues.put(cantidad, comprobanteventadetalle.getCantidad());
        initialValues.put(precio, comprobanteventadetalle.getPrecio());
        initialValues.put(precioUnitario, comprobanteventadetalle.getPrecioUnitario());
        initialValues.put(costoVenta, comprobanteventadetalle.getCostoVenta());
        initialValues.put(importe, comprobanteventadetalle.getImporte());
        initialValues.put(usuarioActualizacion, comprobanteventadetalle.getUsuarioActualizacion());
        initialValues.put(fechaActualizacion, comprobanteventadetalle.getFechaActualizacion());
        initialValues.put(Constants._CLMEXPORT, Constants._CREADO);
        return mDb.insert(SQLITE_TABLA_DB_COMPROBANTEVENTADETALLE, null, initialValues);
    }

    public long updateComprobanteVentaDetalle(@NonNull ComprobanteVentaDetalle comprobanteventadetalle) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(compdId, comprobanteventadetalle.getCompdId());
        initialValues.put(proId, comprobanteventadetalle.getProId());
        initialValues.put(unidadId, comprobanteventadetalle.getUnidadId());
        initialValues.put(cantidad, comprobanteventadetalle.getCantidad());
        initialValues.put(precio, comprobanteventadetalle.getPrecio());
        initialValues.put(precioUnitario, comprobanteventadetalle.getPrecioUnitario());
        initialValues.put(costoVenta, comprobanteventadetalle.getCostoVenta());
        initialValues.put(importe, comprobanteventadetalle.getImporte());
        initialValues.put(usuarioActualizacion, comprobanteventadetalle.getUsuarioActualizacion());
        initialValues.put(fechaActualizacion, comprobanteventadetalle.getFechaActualizacion());
        initialValues.put(Constants._CLMEXPORT, Constants._CREADO);
        return mDb.update(SQLITE_TABLA_DB_COMPROBANTEVENTADETALLE, initialValues,
                compId + "=?", new String[]{"" + comprobanteventadetalle.getCompId()});
    }
}
