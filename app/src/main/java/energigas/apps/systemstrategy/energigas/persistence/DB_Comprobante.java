package energigas.apps.systemstrategy.energigas.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import energigas.apps.systemstrategy.energigas.entities.Comprobante;
import energigas.apps.systemstrategy.energigas.utils.Constants;

/**
 * Created by KelvinThony on 11/08/2016.
 */
public class DB_Comprobante {
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
    private static final String SQLITE_TABLA_DB_COMPROBANTE = "DB_Comprobante";
    private final Context mCtx;
    public static final String CREATE_TABLA_DB_COMPROBANTE =
            "create table " + SQLITE_TABLA_DB_COMPROBANTE + " ("
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
    public static final String DELETE_TABLA_DB_COMPROBANTE = "DROP TABLE IF EXISTS " + SQLITE_TABLA_DB_COMPROBANTE;

    public DB_Comprobante(Context ctx) {
        this.mCtx = ctx;
    }

    public DB_Comprobante open() {
        mDbHelper = new DbHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        if (mDbHelper != null) {
            mDbHelper.close();
        }
    }

    public long createComprobante(@NonNull Comprobante comprobante) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(compIComprobanteId, comprobante.getCompIComprobanteId());
        initialValues.put(compITipoComprobanteId, comprobante.getCompITipoComprobanteId());
        initialValues.put(compVSerie, comprobante.getCompVSerie());
        initialValues.put(compICorrelativo, comprobante.getCompICorrelativo());
        initialValues.put(compVCodigo, comprobante.getCompVCodigo());
        initialValues.put(compIUsuarioCreacionId, comprobante.getCompIUsuarioCreacionId());
        initialValues.put(compIUsuarioActualizacionId, comprobante.getCompIUsuarioActualizacionId());
        initialValues.put(compDTFechaCreacion, comprobante.getCompDTFechaCreacion());
        initialValues.put(compDTFechaActualizacion, comprobante.getCompDTFechaActualizacion());
        initialValues.put(compIEmpresaId, comprobante.getCompIEmpresaId());
        initialValues.put(compBEstado, comprobante.isCompBEstado());
        initialValues.put(Constants._CLMEXPORT, Constants._CREADO);
        return mDb.insert(SQLITE_TABLA_DB_COMPROBANTE, null, initialValues);
    }

    public long updateComprobante(@NonNull Comprobante comprobante) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(compITipoComprobanteId, comprobante.getCompITipoComprobanteId());
        initialValues.put(compVSerie, comprobante.getCompVSerie());
        initialValues.put(compICorrelativo, comprobante.getCompICorrelativo());
        initialValues.put(compVCodigo, comprobante.getCompVCodigo());
        initialValues.put(compIUsuarioCreacionId, comprobante.getCompIUsuarioCreacionId());
        initialValues.put(compIUsuarioActualizacionId, comprobante.getCompIUsuarioActualizacionId());
        initialValues.put(compDTFechaCreacion, comprobante.getCompDTFechaCreacion());
        initialValues.put(compDTFechaActualizacion, comprobante.getCompDTFechaActualizacion());
        initialValues.put(compIEmpresaId, comprobante.getCompIEmpresaId());
        initialValues.put(compBEstado, comprobante.isCompBEstado());
        initialValues.put(Constants._CLMEXPORT, Constants._CREADO);
        return mDb.update(SQLITE_TABLA_DB_COMPROBANTE, initialValues,
                compIComprobanteId + "=?", new String[]{"" + comprobante.getCompIComprobanteId()});
    }
}
