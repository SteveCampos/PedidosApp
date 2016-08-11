package energigas.apps.systemstrategy.energigas.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import energigas.apps.systemstrategy.energigas.entities.InformeGasto;
import energigas.apps.systemstrategy.energigas.utils.Constants;

/**
 * Created by KelvinThony on 11/08/2016.
 */
public class DB_InformeGasto {
    public static final String _id = "_id";
    public static final String infGasId = "infGasId";
    public static final String tipoGastoId = "tipoGastoId";
    public static final String cajGasId = "cajGasId";
    public static final String usuarioAccion = "usuarioAccion";
    public static final String fechaAccion = "fechaAccion";
    public static final String referencia = "referencia";
    public static final String catTipoGastoId = "catTipoGastoId";
    private DbHelper mDbHelper;
    private SQLiteDatabase mDb;
    private static final String SQLITE_TABLA_DB_INFORMEGASTO = "DB_InformeGasto";
    private final Context mCtx;
    public static final String CREATE_TABLA_DB_INFORMEGASTO =
            "create table " + SQLITE_TABLA_DB_INFORMEGASTO + " ("
                    + _id + " integer primary key autoincrement,"
                    + infGasId + " integer ,"
                    + tipoGastoId + " integer ,"
                    + cajGasId + " integer ,"
                    + usuarioAccion + " integer ,"
                    + fechaAccion + " text ,"
                    + referencia + " text ,"
                    + catTipoGastoId + " integer ,"
                    + Constants._CLMEXPORT + " integer );";
    public static final String DELETE_TABLA_DB_INFORMEGASTO = "DROP TABLE IF EXISTS " + SQLITE_TABLA_DB_INFORMEGASTO;

    public DB_InformeGasto(Context ctx) {
        this.mCtx = ctx;
    }

    public DB_InformeGasto open() {
        mDbHelper = new DbHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        if (mDbHelper != null) {
            mDbHelper.close();
        }
    }

    public long createInformeGasto(@NonNull InformeGasto informegasto) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(infGasId, informegasto.getInfGasId());
        initialValues.put(tipoGastoId, informegasto.getTipoGastoId());
        initialValues.put(cajGasId, informegasto.getCajGasId());
        initialValues.put(usuarioAccion, informegasto.getUsuarioAccion());
        initialValues.put(fechaAccion, informegasto.getFechaAccion());
        initialValues.put(referencia, informegasto.getReferencia());
        initialValues.put(catTipoGastoId, informegasto.getCatTipoGastoId());
        initialValues.put(Constants._CLMEXPORT, Constants._CREADO);
        return mDb.insert(SQLITE_TABLA_DB_INFORMEGASTO, null, initialValues);
    }

    public long updateInformeGasto(@NonNull InformeGasto informegasto) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(tipoGastoId, informegasto.getTipoGastoId());
        initialValues.put(cajGasId, informegasto.getCajGasId());
        initialValues.put(usuarioAccion, informegasto.getUsuarioAccion());
        initialValues.put(fechaAccion, informegasto.getFechaAccion());
        initialValues.put(referencia, informegasto.getReferencia());
        initialValues.put(catTipoGastoId, informegasto.getCatTipoGastoId());
        initialValues.put(Constants._CLMEXPORT, Constants._CREADO);
        return mDb.update(SQLITE_TABLA_DB_INFORMEGASTO, initialValues,
                infGasId + "=?", new String[]{"" + informegasto.getInfGasId()});
    }
}
