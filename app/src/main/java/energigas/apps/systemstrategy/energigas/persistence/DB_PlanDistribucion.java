package energigas.apps.systemstrategy.energigas.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import energigas.apps.systemstrategy.energigas.entities.PlanDistribucion ;
import energigas.apps.systemstrategy.energigas.utils.Constants;

/**
 * Created by KelvinThony on 11/08/2016.
 */
public class DB_PlanDistribucion {
    public static final String _id = "_id";
    public static final String pdId = "pdId";
    public static final String fechaInicio = "fechaInicio";
    public static final String fechaFin = "fechaFin";
    public static final String usuarioCreaccion = "usuarioCreaccion";
    public static final String fechaCreacion = "fechaCreacion";
    public static final String usuarioActualizacion = "usuarioActualizacion";
    public static final String fechaActualizacion = "fechaActualizacion";
    public static final String vehiculoId = "vehiculoId";
    public static final String agenteId = "agenteId";
    public static final String periodo = "periodo";
    public static final String estadoId = "estadoId";
    public static final String peId = "peId";
    private DbHelper mDbHelper;
    private SQLiteDatabase mDb;
    private static final String SQLITE_TABLA_DB_PLANDISTRIBUCION = "DB_PlanDistribucion";
    private final Context mCtx;
    public static final String CREATE_TABLA_DB_PLANDISTRIBUCION =
            "create table "+ SQLITE_TABLA_DB_PLANDISTRIBUCION +" ("
                    +_id+" integer primary key autoincrement,"
                    + pdId +" integer ,"
                    + fechaInicio +" text ,"
                    + fechaFin +" text ,"
                    + usuarioCreaccion +" integer ,"
                    + fechaCreacion +" text ,"
                    + usuarioActualizacion +" integer ,"
                    + fechaActualizacion +" text ,"
                    + vehiculoId +" integer ,"
                    + agenteId +" integer ,"
                    + periodo +" text ,"
                    + estadoId +" integer ,"
                    + peId +" integer ,"
                    + Constants._CLMEXPORT+" integer );";
    public static final String DELETE_TABLA_DB_PLANDISTRIBUCION = "DROP TABLE IF EXISTS " + SQLITE_TABLA_DB_PLANDISTRIBUCION ;
    public DB_PlanDistribucion (Context ctx) {
        this.mCtx = ctx;
    }

    public DB_PlanDistribucion open()  {
        mDbHelper = new DbHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this ;
    }
    public void close() {
        if (mDbHelper != null) {
            mDbHelper.close();
        }
    } public long createPlanDistribucion (@NonNull PlanDistribucion plandistribucion) {
        ContentValues initialValues = new ContentValues();
        initialValues.put( pdId ,plandistribucion.getPdId() );
        initialValues.put( fechaInicio ,plandistribucion.getFechaInicio() );
        initialValues.put( fechaFin ,plandistribucion.getFechaFin() );
        initialValues.put( usuarioCreaccion ,plandistribucion.getUsuarioCreaccion() );
        initialValues.put( fechaCreacion ,plandistribucion.getFechaCreacion() );
        initialValues.put( usuarioActualizacion ,plandistribucion.getUsuarioActualizacion() );
        initialValues.put( fechaActualizacion ,plandistribucion.getFechaActualizacion() );
        initialValues.put( vehiculoId ,plandistribucion.getVehiculoId() );
        initialValues.put( agenteId ,plandistribucion.getAgenteId() );
        initialValues.put( periodo ,plandistribucion.getPeriodo() );
        initialValues.put( estadoId ,plandistribucion.getEstadoId() );
        initialValues.put( peId ,plandistribucion.getPeId() );
        initialValues.put(Constants._CLMEXPORT, Constants._CREADO);
        return mDb.insert(SQLITE_TABLA_DB_PLANDISTRIBUCION , null, initialValues);
    } public long updatePlanDistribucion (@NonNull PlanDistribucion plandistribucion) {
        ContentValues initialValues = new ContentValues();
        initialValues.put( fechaInicio ,plandistribucion.getFechaInicio() );
        initialValues.put( fechaFin ,plandistribucion.getFechaFin() );
        initialValues.put( usuarioCreaccion ,plandistribucion.getUsuarioCreaccion() );
        initialValues.put( fechaCreacion ,plandistribucion.getFechaCreacion() );
        initialValues.put( usuarioActualizacion ,plandistribucion.getUsuarioActualizacion() );
        initialValues.put( fechaActualizacion ,plandistribucion.getFechaActualizacion() );
        initialValues.put( vehiculoId ,plandistribucion.getVehiculoId() );
        initialValues.put( agenteId ,plandistribucion.getAgenteId() );
        initialValues.put( periodo ,plandistribucion.getPeriodo() );
        initialValues.put( estadoId ,plandistribucion.getEstadoId() );
        initialValues.put( peId ,plandistribucion.getPeId() );
        initialValues.put(Constants._CLMEXPORT, Constants._CREADO);
        return mDb.update(SQLITE_TABLA_DB_PLANDISTRIBUCION , initialValues,
                pdId  + "=?", new String[]{"" + plandistribucion.getPdId() });
    }  }
