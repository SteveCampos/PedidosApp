package energigas.apps.systemstrategy.energigas.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.util.Log;

import energigas.apps.systemstrategy.energigas.entities.BEGeneral;
import energigas.apps.systemstrategy.energigas.entities.UbicacionGeoreferencia;
import energigas.apps.systemstrategy.energigas.utils.Constants;

/**
 * Created by KelvinThony on 11/08/2016.
 */
public class DB_UbicacionGeoreferencia {
    public static final String _id = "_id";
    public static final String id = "id";
    public static final String codigo = "codigo";
    public static final String descripcion = "descripcion";
    public static final String parentId = "parentId";
    public static final String tipoId = "tipoId";
    private DbHelper mDbHelper;
    private SQLiteDatabase mDb;

    private static final String TAG = "DB_UbicacionGeor";

    private static final String SQLITE_TABLA_DB_UBICACIONGEOREFERENCIA = "DB_UbicacionGeoreferencia";
    private final Context mCtx;
    public static final String CREATE_TABLA_DB_UBICACIONGEOREFERENCIA =
            "create table " + SQLITE_TABLA_DB_UBICACIONGEOREFERENCIA + " ("
                    + _id + " integer primary key autoincrement,"
                    + id + " integer ,"
                    + codigo + " text ,"
                    + descripcion + " text ,"
                    + parentId + " integer ,"
                    + tipoId + " integer ,"
                    + Constants._CLMEXPORT + " integer );";
    public static final String DELETE_TABLA_DB_UBICACIONGEOREFERENCIA = "DROP TABLE IF EXISTS " + SQLITE_TABLA_DB_UBICACIONGEOREFERENCIA;

    public DB_UbicacionGeoreferencia(Context ctx) {
        this.mCtx = ctx;
    }

    public DB_UbicacionGeoreferencia open() {
        mDbHelper = new DbHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        if (mDbHelper != null) {
            mDbHelper.close();
        }
    }

    public long createUbicacionGeoreferencia(@NonNull UbicacionGeoreferencia ubicaciongeoreferencia) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(id, ubicaciongeoreferencia.getId());
        initialValues.put(codigo, ubicaciongeoreferencia.getCodigo());
        initialValues.put(descripcion, ubicaciongeoreferencia.getDescripcion());
        initialValues.put(parentId, ubicaciongeoreferencia.getParentId());
        initialValues.put(tipoId, ubicaciongeoreferencia.getTipoId());
        initialValues.put(Constants._CLMEXPORT, Constants._CREADO);
        return mDb.insert(SQLITE_TABLA_DB_UBICACIONGEOREFERENCIA, null, initialValues);
    }

    public boolean createUbicacionAllGeoreferencia(@NonNull BEGeneral beGeneral) {
        boolean state = true;
        for (UbicacionGeoreferencia ubicacionGeoreferencia : beGeneral.getItemUbigeos()) {

            Long insert = createUbicacionGeoreferencia(ubicacionGeoreferencia);

            if ((insert) < 0) {
                Log.e(TAG, "ERROR AL INSERTAR ROLES");
                state = false;
                break;
            }

        }
        return state;
    }

    public long updateUbicacionGeoreferencia(@NonNull UbicacionGeoreferencia ubicaciongeoreferencia) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(codigo, ubicaciongeoreferencia.getCodigo());
        initialValues.put(descripcion, ubicaciongeoreferencia.getDescripcion());
        initialValues.put(parentId, ubicaciongeoreferencia.getParentId());
        initialValues.put(tipoId, ubicaciongeoreferencia.getTipoId());
        initialValues.put(Constants._CLMEXPORT, Constants._CREADO);
        return mDb.update(SQLITE_TABLA_DB_UBICACIONGEOREFERENCIA, initialValues,
                id + "=?", new String[]{"" + ubicaciongeoreferencia.getId()});
    }
}
