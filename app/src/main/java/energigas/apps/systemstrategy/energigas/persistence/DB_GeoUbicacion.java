package energigas.apps.systemstrategy.energigas.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import energigas.apps.systemstrategy.energigas.entities.GeoUbicacion;
import energigas.apps.systemstrategy.energigas.utils.Constants;

/**
 * Created by KelvinThony on 11/08/2016.
 */
public class DB_GeoUbicacion {
    public static final String _id = "_id";
    public static final String ubId = "ubId";
    public static final String ugId = "ugId";
    public static final String descripcion = "descripcion";
    public static final String latitud = "latitud";
    public static final String longitud = "longitud";
    public static final String personaId = "personaId";
    public static final String ubicacionFiscal = "ubicacionFiscal";
    public static final String vehiculoId = "vehiculoId";
    public static final String ubicacionPrincipal = "ubicacionPrincipal";
    public static final String usuarioCreacion = "usuarioCreacion";
    public static final String fechaCreacion = "fechaCreacion";
    public static final String usuarioActualizacion = "usuarioActualizacion";
    public static final String fechaActualizacion = "fechaActualizacion";
    public static final String estado = "estado";
    private DbHelper mDbHelper;
    private SQLiteDatabase mDb;
    private static final String SQLITE_TABLA_DB_GEOUBICACION = "DB_GeoUbicacion";
    private final Context mCtx;
    public static final String CREATE_TABLA_DB_GEOUBICACION =
            "create table " + SQLITE_TABLA_DB_GEOUBICACION + " ("
                    + _id + " integer primary key autoincrement,"
                    + ubId + " integer ,"
                    + ugId + " integer ,"
                    + descripcion + " text ,"
                    + latitud + " text ,"
                    + longitud + " text ,"
                    + personaId + " integer ,"
                    + ubicacionFiscal + " integer ,"
                    + vehiculoId + " integer ,"
                    + ubicacionPrincipal + " integer ,"
                    + usuarioCreacion + " integer ,"
                    + fechaCreacion + " text ,"
                    + usuarioActualizacion + " integer ,"
                    + fechaActualizacion + " text ,"
                    + estado + " integer ,"
                    + Constants._CLMEXPORT + " integer );";
    public static final String DELETE_TABLA_DB_GEOUBICACION = "DROP TABLE IF EXISTS " + SQLITE_TABLA_DB_GEOUBICACION;

    public DB_GeoUbicacion(Context ctx) {
        this.mCtx = ctx;
    }

    public DB_GeoUbicacion open() {
        mDbHelper = new DbHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        if (mDbHelper != null) {
            mDbHelper.close();
        }
    }

    public long createGeoUbicacion(@NonNull GeoUbicacion geoubicacion) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(ubId, geoubicacion.getUbId());
        initialValues.put(ugId, geoubicacion.getUgId());
        initialValues.put(descripcion, geoubicacion.getDescripcion());
        initialValues.put(latitud, geoubicacion.getLatitud());
        initialValues.put(longitud, geoubicacion.getLongitud());
        initialValues.put(personaId, geoubicacion.getPersonaId());
        initialValues.put(ubicacionFiscal, geoubicacion.isUbicacionFiscal());
        initialValues.put(vehiculoId, geoubicacion.getVehiculoId());
        initialValues.put(ubicacionPrincipal, geoubicacion.isUbicacionPrincipal());
        initialValues.put(usuarioCreacion, geoubicacion.getUsuarioCreacion());
        initialValues.put(fechaCreacion, geoubicacion.getFechaCreacion());
        initialValues.put(usuarioActualizacion, geoubicacion.getUsuarioActualizacion());
        initialValues.put(fechaActualizacion, geoubicacion.getFechaActualizacion());
        initialValues.put(estado, geoubicacion.isEstado());
        initialValues.put(Constants._CLMEXPORT, Constants._CREADO);
        return mDb.insert(SQLITE_TABLA_DB_GEOUBICACION, null, initialValues);
    }

    public long updateGeoUbicacion(@NonNull GeoUbicacion geoubicacion) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(ugId, geoubicacion.getUgId());
        initialValues.put(descripcion, geoubicacion.getDescripcion());
        initialValues.put(latitud, geoubicacion.getLatitud());
        initialValues.put(longitud, geoubicacion.getLongitud());
        initialValues.put(personaId, geoubicacion.getPersonaId());
        initialValues.put(ubicacionFiscal, geoubicacion.isUbicacionFiscal());
        initialValues.put(vehiculoId, geoubicacion.getVehiculoId());
        initialValues.put(ubicacionPrincipal, geoubicacion.isUbicacionPrincipal());
        initialValues.put(usuarioCreacion, geoubicacion.getUsuarioCreacion());
        initialValues.put(fechaCreacion, geoubicacion.getFechaCreacion());
        initialValues.put(usuarioActualizacion, geoubicacion.getUsuarioActualizacion());
        initialValues.put(fechaActualizacion, geoubicacion.getFechaActualizacion());
        initialValues.put(estado, geoubicacion.isEstado());
        initialValues.put(Constants._CLMEXPORT, Constants._CREADO);
        return mDb.update(SQLITE_TABLA_DB_GEOUBICACION, initialValues,
                ubId + "=?", new String[]{"" + geoubicacion.getUbId()});
    }
}
