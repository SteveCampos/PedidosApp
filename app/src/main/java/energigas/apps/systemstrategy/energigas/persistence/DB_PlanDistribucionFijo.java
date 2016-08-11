package energigas.apps.systemstrategy.energigas.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import energigas.apps.systemstrategy.energigas.entities.PlanDistribucionFijo;
import energigas.apps.systemstrategy.energigas.utils.Constants;

/**
 * Created by KelvinThony on 11/08/2016.
 */
public class DB_PlanDistribucionFijo {
    public static final String _id = "_id";
    public static final String pdId = "pdId";
    public static final String descripcion = "descripcion";
    public static final String estado = "estado";
    public static final String empresaId = "empresaId";
    public static final String usuarioCreacion = "usuarioCreacion";
    public static final String fechaCreacion = "fechaCreacion";
    public static final String usuarioActualizacion = "usuarioActualizacion";
    public static final String fechaActualizacion = "fechaActualizacion";
    public static final String vehiculoId = "vehiculoId";
    public static final String agenteId = "agenteId";
    private DbHelper mDbHelper;
    private SQLiteDatabase mDb;
    private static final String SQLITE_TABLA_DB_PLANDISTRIBUCIONFIJO = "DB_PlanDistribucionFijo";
    private final Context mCtx;
    public static final String CREATE_TABLA_DB_PLANDISTRIBUCIONFIJO =
            "create table " + SQLITE_TABLA_DB_PLANDISTRIBUCIONFIJO + " ("
                    + _id + " integer primary key autoincrement,"
                    + pdId + " integer ,"
                    + descripcion + " text ,"
                    + estado + " integer ,"
                    + empresaId + " integer ,"
                    + usuarioCreacion + " integer ,"
                    + fechaCreacion + " text ,"
                    + usuarioActualizacion + " integer ,"
                    + fechaActualizacion + " text ,"
                    + vehiculoId + " integer ,"
                    + agenteId + " integer ,"
                    + Constants._CLMEXPORT + " integer );";
    public static final String DELETE_TABLA_DB_PLANDISTRIBUCIONFIJO = "DROP TABLE IF EXISTS " + SQLITE_TABLA_DB_PLANDISTRIBUCIONFIJO;

    public DB_PlanDistribucionFijo(Context ctx) {
        this.mCtx = ctx;
    }

    public DB_PlanDistribucionFijo open() {
        mDbHelper = new DbHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        if (mDbHelper != null) {
            mDbHelper.close();
        }
    }

    public long createPlanDistribucionFijo(@NonNull PlanDistribucionFijo plandistribucionfijo) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(pdId, plandistribucionfijo.getPdId());
        initialValues.put(descripcion, plandistribucionfijo.getDescripcion());
        initialValues.put(estado, plandistribucionfijo.isEstado());
        initialValues.put(empresaId, plandistribucionfijo.getEmpresaId());
        initialValues.put(usuarioCreacion, plandistribucionfijo.getUsuarioCreacion());
        initialValues.put(fechaCreacion, plandistribucionfijo.getFechaCreacion());
        initialValues.put(usuarioActualizacion, plandistribucionfijo.getUsuarioActualizacion());
        initialValues.put(fechaActualizacion, plandistribucionfijo.getFechaActualizacion());
        initialValues.put(vehiculoId, plandistribucionfijo.getVehiculoId());
        initialValues.put(agenteId, plandistribucionfijo.getAgenteId());
        initialValues.put(Constants._CLMEXPORT, Constants._CREADO);
        return mDb.insert(SQLITE_TABLA_DB_PLANDISTRIBUCIONFIJO, null, initialValues);
    }

    public long updatePlanDistribucionFijo(@NonNull PlanDistribucionFijo plandistribucionfijo) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(descripcion, plandistribucionfijo.getDescripcion());
        initialValues.put(estado, plandistribucionfijo.isEstado());
        initialValues.put(empresaId, plandistribucionfijo.getEmpresaId());
        initialValues.put(usuarioCreacion, plandistribucionfijo.getUsuarioCreacion());
        initialValues.put(fechaCreacion, plandistribucionfijo.getFechaCreacion());
        initialValues.put(usuarioActualizacion, plandistribucionfijo.getUsuarioActualizacion());
        initialValues.put(fechaActualizacion, plandistribucionfijo.getFechaActualizacion());
        initialValues.put(vehiculoId, plandistribucionfijo.getVehiculoId());
        initialValues.put(agenteId, plandistribucionfijo.getAgenteId());
        initialValues.put(Constants._CLMEXPORT, Constants._CREADO);
        return mDb.update(SQLITE_TABLA_DB_PLANDISTRIBUCIONFIJO, initialValues,
                pdId + "=?", new String[]{"" + plandistribucionfijo.getPdId()});
    }
}
