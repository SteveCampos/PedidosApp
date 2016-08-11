package energigas.apps.systemstrategy.energigas.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import energigas.apps.systemstrategy.energigas.entities.Concepto;
import energigas.apps.systemstrategy.energigas.utils.Constants;

/**
 * Created by KelvinThony on 11/08/2016.
 */
public class DB_Concepto {
    public static final String _id = "_id";
    public static final String id = "id";
    public static final String objeto = "objeto";
    public static final String concepto = "concepto";
    public static final String descripcion = "descripcion";
    public static final String estado = "estado";
    public static final String empresaId = "empresaId";
    private DbHelper mDbHelper;
    private SQLiteDatabase mDb;
    private static final String SQLITE_TABLA_DB_CONCEPTO = "DB_Concepto";
    private final Context mCtx;
    public static final String CREATE_TABLA_DB_CONCEPTO =
            "create table " + SQLITE_TABLA_DB_CONCEPTO + " ("
                    + _id + " integer primary key autoincrement,"
                    + id + " integer ,"
                    + objeto + " text ,"
                    + concepto + " text ,"
                    + descripcion + " text ,"
                    + estado + " integer ,"
                    + empresaId + " integer ,"
                    + Constants._CLMEXPORT + " integer );";
    public static final String DELETE_TABLA_DB_CONCEPTO = "DROP TABLE IF EXISTS " + SQLITE_TABLA_DB_CONCEPTO;

    public DB_Concepto(Context ctx) {
        this.mCtx = ctx;
    }

    public DB_Concepto open() {
        mDbHelper = new DbHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        if (mDbHelper != null) {
            mDbHelper.close();
        }
    }

    public long createConcepto(@NonNull Concepto concept) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(id, concept.getId());
        initialValues.put(objeto, concept.getObjeto());
        initialValues.put(concepto, concept.getConcepto());
        initialValues.put(descripcion, concept.getDescripcion());
        initialValues.put(estado, concept.getEstado());
        initialValues.put(empresaId, concept.getEmpresaId());
        initialValues.put(Constants._CLMEXPORT, Constants._CREADO);
        return mDb.insert(SQLITE_TABLA_DB_CONCEPTO, null, initialValues);
    }

    public long updateConcepto(@NonNull Concepto concept) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(objeto, concept.getObjeto());
        initialValues.put(concepto, concept.getConcepto());
        initialValues.put(descripcion, concept.getDescripcion());
        initialValues.put(estado, concept.getEstado());
        initialValues.put(empresaId, concept.getEmpresaId());
        initialValues.put(Constants._CLMEXPORT, Constants._CREADO);
        return mDb.update(SQLITE_TABLA_DB_CONCEPTO, initialValues,
                id + "=?", new String[]{"" + concept.getId()});
    }
}
