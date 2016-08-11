package energigas.apps.systemstrategy.energigas.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import energigas.apps.systemstrategy.energigas.entities.GuiaRemision;
import energigas.apps.systemstrategy.energigas.utils.Constants;

/**
 * Created by KelvinThony on 11/08/2016.
 */
public class DB_GuiaRemision {
    public static final String _id = "_id";
    public static final String greId = "greId";
    public static final String serie = "serie";
    public static final String numero = "numero";
    public static final String transInvId = "transInvId";
    public static final String fecha = "fecha";
    public static final String usuarioId = "usuarioId";
    public static final String fechaAccion = "fechaAccion";
    private DbHelper mDbHelper;
    private SQLiteDatabase mDb;
    private static final String SQLITE_TABLA_DB_GUIAREMISION = "DB_GuiaRemision";
    private final Context mCtx;
    public static final String CREATE_TABLA_DB_GUIAREMISION =
            "create table " + SQLITE_TABLA_DB_GUIAREMISION + " ("
                    + _id + " integer primary key autoincrement,"
                    + greId + " integer ,"
                    + serie + " text ,"
                    + numero + " integer ,"
                    + transInvId + " integer ,"
                    + fecha + " text ,"
                    + usuarioId + " integer ,"
                    + fechaAccion + " text ,"
                    + Constants._CLMEXPORT + " integer );";
    public static final String DELETE_TABLA_DB_GUIAREMISION = "DROP TABLE IF EXISTS " + SQLITE_TABLA_DB_GUIAREMISION;

    public DB_GuiaRemision(Context ctx) {
        this.mCtx = ctx;
    }

    public DB_GuiaRemision open() {
        mDbHelper = new DbHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        if (mDbHelper != null) {
            mDbHelper.close();
        }
    }

    public long createGuiaRemision(@NonNull GuiaRemision guiaremision) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(greId, guiaremision.getGreId());
        initialValues.put(serie, guiaremision.getSerie());
        initialValues.put(numero, guiaremision.getNumero());
        initialValues.put(transInvId, guiaremision.getTransInvId());
        initialValues.put(fecha, guiaremision.getFecha());
        initialValues.put(usuarioId, guiaremision.getUsuarioId());
        initialValues.put(fechaAccion, guiaremision.getFechaAccion());
        initialValues.put(Constants._CLMEXPORT, Constants._CREADO);
        return mDb.insert(SQLITE_TABLA_DB_GUIAREMISION, null, initialValues);
    }

    public long updateGuiaRemision(@NonNull GuiaRemision guiaremision) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(serie, guiaremision.getSerie());
        initialValues.put(numero, guiaremision.getNumero());
        initialValues.put(transInvId, guiaremision.getTransInvId());
        initialValues.put(fecha, guiaremision.getFecha());
        initialValues.put(usuarioId, guiaremision.getUsuarioId());
        initialValues.put(fechaAccion, guiaremision.getFechaAccion());
        initialValues.put(Constants._CLMEXPORT, Constants._CREADO);
        return mDb.update(SQLITE_TABLA_DB_GUIAREMISION, initialValues,
                greId + "=?", new String[]{"" + guiaremision.getGreId()});
    }
}
