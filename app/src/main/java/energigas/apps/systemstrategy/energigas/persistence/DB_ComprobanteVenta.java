package energigas.apps.systemstrategy.energigas.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import energigas.apps.systemstrategy.energigas.entities.ComprobanteVenta;
import energigas.apps.systemstrategy.energigas.utils.Constants;

/**
 * Created by KelvinThony on 11/08/2016.
 */
public class DB_ComprobanteVenta {
    public static final String _id = "_id";
    public static final String compId = "compId";
    public static final String serie = "serie";
    public static final String numDoc = "numDoc";
    public static final String formaPagoId = "formaPagoId";
    public static final String estadoId = "estadoId";
    public static final String fechaDoc = "fechaDoc";
    public static final String baseImponible = "baseImponible";
    public static final String igv = "igv";
    public static final String total = "total";
    public static final String tipoComprobanteId = "tipoComprobanteId";
    public static final String clienteId = "clienteId";
    public static final String comIUsuarioId = "comIUsuarioId";
    public static final String anulado = "anulado";
    public static final String saldo = "saldo";
    public static final String lqId = "lqId";
    public static final String tipoVentaId = "tipoVentaId";
    public static final String establecimientoId = "establecimientoId";
    public static final String exportado = "exportado";
    public static final String docIdentidad = "docIdentidad";
    public static final String valorResumen = "valorResumen";
    public static final String cliente = "cliente";
    public static final String direccionCliente = "direccionCliente";
    public static final String fechaCreacion = "fechaCreacion";
    public static final String fechaActualizacion = "fechaActualizacion";
    private DbHelper mDbHelper;
    private SQLiteDatabase mDb;
    private static final String SQLITE_TABLA_DB_COMPROBANTEVENTA = "DB_ComprobanteVenta";
    private final Context mCtx;
    public static final String CREATE_TABLA_DB_COMPROBANTEVENTA =
            "create table " + SQLITE_TABLA_DB_COMPROBANTEVENTA + " ("
                    + _id + " integer primary key autoincrement,"
                    + compId + " integer ,"
                    + serie + " text ,"
                    + numDoc + " integer ,"
                    + formaPagoId + " integer ,"
                    + estadoId + " integer ,"
                    + fechaDoc + " text ,"
                    + baseImponible + " real ,"
                    + igv + " real ,"
                    + total + " real ,"
                    + tipoComprobanteId + " integer ,"
                    + clienteId + " integer ,"
                    + comIUsuarioId + " integer ,"
                    + anulado + " integer ,"
                    + saldo + " real ,"
                    + lqId + " integer ,"
                    + tipoVentaId + " integer ,"
                    + establecimientoId + " integer ,"
                    + exportado + " integer ,"
                    + docIdentidad + " text ,"
                    + valorResumen + " text ,"
                    + cliente + " text ,"
                    + direccionCliente + " text ,"
                    + fechaCreacion + " text ,"
                    + fechaActualizacion + " text ,"
                    + Constants._CLMEXPORT + " integer );";
    public static final String DELETE_TABLA_DB_COMPROBANTEVENTA = "DROP TABLE IF EXISTS " + SQLITE_TABLA_DB_COMPROBANTEVENTA;

    public DB_ComprobanteVenta(Context ctx) {
        this.mCtx = ctx;
    }

    public DB_ComprobanteVenta open() {
        mDbHelper = new DbHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        if (mDbHelper != null) {
            mDbHelper.close();
        }
    }

    public long createComprobanteVenta(@NonNull ComprobanteVenta comprobanteventa) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(compId, comprobanteventa.getCompId());
        initialValues.put(serie, comprobanteventa.getSerie());
        initialValues.put(numDoc, comprobanteventa.getNumDoc());
        initialValues.put(formaPagoId, comprobanteventa.getFormaPagoId());
        initialValues.put(estadoId, comprobanteventa.getEstadoId());
        initialValues.put(fechaDoc, comprobanteventa.getFechaDoc());
        initialValues.put(baseImponible, comprobanteventa.getBaseImponible());
        initialValues.put(igv, comprobanteventa.getIgv());
        initialValues.put(total, comprobanteventa.getTotal());
        initialValues.put(tipoComprobanteId, comprobanteventa.getTipoComprobanteId());
        initialValues.put(clienteId, comprobanteventa.getClienteId());
        initialValues.put(comIUsuarioId, comprobanteventa.getComIUsuarioId());
        initialValues.put(anulado, comprobanteventa.isAnulado());
        initialValues.put(saldo, comprobanteventa.getSaldo());
        initialValues.put(lqId, comprobanteventa.getLqId());
        initialValues.put(tipoVentaId, comprobanteventa.getTipoVentaId());
        initialValues.put(establecimientoId, comprobanteventa.getEstablecimientoId());
        initialValues.put(exportado, comprobanteventa.isExportado());
        initialValues.put(docIdentidad, comprobanteventa.getDocIdentidad());
        initialValues.put(valorResumen, comprobanteventa.getValorResumen());
        initialValues.put(cliente, comprobanteventa.getCliente());
        initialValues.put(direccionCliente, comprobanteventa.getDireccionCliente());
        initialValues.put(fechaCreacion, comprobanteventa.getFechaCreacion());
        initialValues.put(fechaActualizacion, comprobanteventa.getFechaActualizacion());
        initialValues.put(Constants._CLMEXPORT, Constants._CREADO);
        return mDb.insert(SQLITE_TABLA_DB_COMPROBANTEVENTA, null, initialValues);
    }

    public long updateComprobanteVenta(@NonNull ComprobanteVenta comprobanteventa) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(serie, comprobanteventa.getSerie());
        initialValues.put(numDoc, comprobanteventa.getNumDoc());
        initialValues.put(formaPagoId, comprobanteventa.getFormaPagoId());
        initialValues.put(estadoId, comprobanteventa.getEstadoId());
        initialValues.put(fechaDoc, comprobanteventa.getFechaDoc());
        initialValues.put(baseImponible, comprobanteventa.getBaseImponible());
        initialValues.put(igv, comprobanteventa.getIgv());
        initialValues.put(total, comprobanteventa.getTotal());
        initialValues.put(tipoComprobanteId, comprobanteventa.getTipoComprobanteId());
        initialValues.put(clienteId, comprobanteventa.getClienteId());
        initialValues.put(comIUsuarioId, comprobanteventa.getComIUsuarioId());
        initialValues.put(anulado, comprobanteventa.isAnulado());
        initialValues.put(saldo, comprobanteventa.getSaldo());
        initialValues.put(lqId, comprobanteventa.getLqId());
        initialValues.put(tipoVentaId, comprobanteventa.getTipoVentaId());
        initialValues.put(establecimientoId, comprobanteventa.getEstablecimientoId());
        initialValues.put(exportado, comprobanteventa.isExportado());
        initialValues.put(docIdentidad, comprobanteventa.getDocIdentidad());
        initialValues.put(valorResumen, comprobanteventa.getValorResumen());
        initialValues.put(cliente, comprobanteventa.getCliente());
        initialValues.put(direccionCliente, comprobanteventa.getDireccionCliente());
        initialValues.put(fechaCreacion, comprobanteventa.getFechaCreacion());
        initialValues.put(fechaActualizacion, comprobanteventa.getFechaActualizacion());
        initialValues.put(Constants._CLMEXPORT, Constants._CREADO);
        return mDb.update(SQLITE_TABLA_DB_COMPROBANTEVENTA, initialValues,
                compId + "=?", new String[]{"" + comprobanteventa.getCompId()});
    }
}
