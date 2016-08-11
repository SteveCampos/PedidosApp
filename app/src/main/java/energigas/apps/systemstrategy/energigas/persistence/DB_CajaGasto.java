package energigas.apps.systemstrategy.energigas.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import energigas.apps.systemstrategy.energigas.entities.CajaGasto;
import energigas.apps.systemstrategy.energigas.utils.Constants;

/**
 * Created by KelvinThony on 11/08/2016.
 */
public class DB_CajaGasto {
    public static final String _id = "_id";
    public static final String cajGasId = "cajGasId";
    public static final String cajMoId = "cajMoId";
    public static final String igv = "igv";
    public static final String valorVenta = "valorVenta";
    public static final String importe = "importe";
    public static final String tipoGastoId = "tipoGastoId";
    public static final String usuarioActualizacion = "usuarioActualizacion";
    public static final String usuarioCreacion = "usuarioCreacion";
    public static final String fechaCreacion = "fechaCreacion";
    public static final String fechaActualizacion = "fechaActualizacion";
    private DbHelper mDbHelper;
    private SQLiteDatabase mDb;
    private static final String SQLITE_TABLA_DB_CAJAGASTO = "DB_CajaGasto";
    private final Context mCtx;
    public static final String CREATE_TABLA_DB_CAJAGASTO =
            "create table " + SQLITE_TABLA_DB_CAJAGASTO + " ("
                    + _id + " integer primary key autoincrement,"
                    + cajGasId + " integer ,"
                    + cajMoId + " integer ,"
                    + igv + " real ,"
                    + valorVenta + " real ,"
                    + importe + " real ,"
                    + tipoGastoId + " integer ,"
                    + usuarioActualizacion + " integer ,"
                    + usuarioCreacion + " integer ,"
                    + fechaCreacion + " text ,"
                    + fechaActualizacion + " text ,"
                    + Constants._CLMEXPORT + " integer );";
    public static final String DELETE_TABLA_DB_CAJAGASTO = "DROP TABLE IF EXISTS " + SQLITE_TABLA_DB_CAJAGASTO;

    public DB_CajaGasto(Context ctx) {
        this.mCtx = ctx;
    }

    public DB_CajaGasto open() {
        mDbHelper = new DbHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        if (mDbHelper != null) {
            mDbHelper.close();
        }
    }

    public long createCajaGasto(@NonNull CajaGasto cajagasto) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(cajGasId, cajagasto.getCajGasId());
        initialValues.put(cajMoId, cajagasto.getCajMoId());
        initialValues.put(igv, cajagasto.getIgv());
        initialValues.put(valorVenta, cajagasto.getValorVenta());
        initialValues.put(importe, cajagasto.getImporte());
        initialValues.put(tipoGastoId, cajagasto.getTipoGastoId());
        initialValues.put(usuarioActualizacion, cajagasto.getUsuarioActualizacion());
        initialValues.put(usuarioCreacion, cajagasto.getUsuarioCreacion());
        initialValues.put(fechaCreacion, cajagasto.getFechaCreacion());
        initialValues.put(fechaActualizacion, cajagasto.getFechaActualizacion());
        initialValues.put(Constants._CLMEXPORT, Constants._CREADO);
        return mDb.insert(SQLITE_TABLA_DB_CAJAGASTO, null, initialValues);
    }

    public long updateCajaGasto(@NonNull CajaGasto cajagasto) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(cajMoId, cajagasto.getCajMoId());
        initialValues.put(igv, cajagasto.getIgv());
        initialValues.put(valorVenta, cajagasto.getValorVenta());
        initialValues.put(importe, cajagasto.getImporte());
        initialValues.put(tipoGastoId, cajagasto.getTipoGastoId());
        initialValues.put(usuarioActualizacion, cajagasto.getUsuarioActualizacion());
        initialValues.put(usuarioCreacion, cajagasto.getUsuarioCreacion());
        initialValues.put(fechaCreacion, cajagasto.getFechaCreacion());
        initialValues.put(fechaActualizacion, cajagasto.getFechaActualizacion());
        initialValues.put(Constants._CLMEXPORT, Constants._CREADO);
        return mDb.update(SQLITE_TABLA_DB_CAJAGASTO, initialValues,
                cajGasId + "=?", new String[]{"" + cajagasto.getCajGasId()});
    }
}
