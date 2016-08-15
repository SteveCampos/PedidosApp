package energigas.apps.systemstrategy.energigas.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import energigas.apps.systemstrategy.energigas.entities.Serie;
import energigas.apps.systemstrategy.energigas.utils.Constants;

/**
 * Created by KelvinThony on 11/08/2016.
 */
public class DB_Serie {
    public static final String _id = "_id";
    public static final String compIComprobanteId = "compIComprobanteId";
    public static final String compITipoComprobanteId = "compITipoComprobanteId";
    public static final String compVSerie = "compVSerie";
    public static final String compICorrelativo = "compICorrelativo";
    public static final String compVCodigo = "compVCodigo";
    public static final String compIUsuarioCreacionId = "compIUsuarioCreacionId";
    public static final String compIUsuarioActualizacionId = "compIUsuarioActualizacionId";
    public static final String compDTFechaCreacion = "compDTFechaCreacion";
    public static final String compDTFechaActualizacion = "compDTFechaActualizacion";
    public static final String compIEmpresaId = "compIEmpresaId";
    public static final String compBEstado = "compBEstado";
    private DbHelper mDbHelper;
    private SQLiteDatabase mDb;
    private static final String SQLITE_TABLA_DB_SERIE = "DB_Serie";
    private final Context mCtx;
    public static final String CREATE_TABLA_DB_SERIE =
            "create table " + SQLITE_TABLA_DB_SERIE + " ("
                    + _id + " integer primary key autoincrement,"
                    + compIComprobanteId + " integer ,"
                    + compITipoComprobanteId + " integer ,"
                    + compVSerie + " text ,"
                    + compICorrelativo + " integer ,"
                    + compVCodigo + " text ,"
                    + compIUsuarioCreacionId + " integer ,"
                    + compIUsuarioActualizacionId + " integer ,"
                    + compDTFechaCreacion + " text ,"
                    + compDTFechaActualizacion + " text ,"
                    + compIEmpresaId + " integer ,"
                    + compBEstado + " integer ,"
                    + Constants._CLMEXPORT + " integer );";
    public static final String DELETE_TABLA_DB_COMPROBANTE = "DROP TABLE IF EXISTS " + SQLITE_TABLA_DB_SERIE;

    public DB_Serie(Context ctx) {
        this.mCtx = ctx;
    }

    public DB_Serie open() {
        mDbHelper = new DbHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        if (mDbHelper != null) {
            mDbHelper.close();
        }
    }

    public long createSerie(@NonNull Serie serie) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(compIComprobanteId, serie.getCompIComprobanteId());
        initialValues.put(compITipoComprobanteId, serie.getCompITipoComprobanteId());
        initialValues.put(compVSerie, serie.getCompVSerie());
        initialValues.put(compICorrelativo, serie.getCompICorrelativo());
        initialValues.put(compVCodigo, serie.getCompVCodigo());
        initialValues.put(compIUsuarioCreacionId, serie.getCompIUsuarioCreacionId());
        initialValues.put(compIUsuarioActualizacionId, serie.getCompIUsuarioActualizacionId());
        initialValues.put(compDTFechaCreacion, serie.getCompDTFechaCreacion());
        initialValues.put(compDTFechaActualizacion, serie.getCompDTFechaActualizacion());
        initialValues.put(compIEmpresaId, serie.getCompIEmpresaId());
        initialValues.put(compBEstado, serie.isCompBEstado());
        initialValues.put(Constants._CLMEXPORT, Constants._CREADO);
        return mDb.insert(SQLITE_TABLA_DB_SERIE, null, initialValues);
    }

    public long updateSerie(@NonNull Serie serie) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(compITipoComprobanteId, serie.getCompITipoComprobanteId());
        initialValues.put(compVSerie, serie.getCompVSerie());
        initialValues.put(compICorrelativo, serie.getCompICorrelativo());
        initialValues.put(compVCodigo, serie.getCompVCodigo());
        initialValues.put(compIUsuarioCreacionId, serie.getCompIUsuarioCreacionId());
        initialValues.put(compIUsuarioActualizacionId, serie.getCompIUsuarioActualizacionId());
        initialValues.put(compDTFechaCreacion, serie.getCompDTFechaCreacion());
        initialValues.put(compDTFechaActualizacion, serie.getCompDTFechaActualizacion());
        initialValues.put(compIEmpresaId, serie.getCompIEmpresaId());
        initialValues.put(compBEstado, serie.isCompBEstado());
        initialValues.put(Constants._CLMEXPORT, Constants._CREADO);
        return mDb.update(SQLITE_TABLA_DB_SERIE, initialValues,
                compIComprobanteId + "=?", new String[]{"" + serie.getCompIComprobanteId()});
    }
}
