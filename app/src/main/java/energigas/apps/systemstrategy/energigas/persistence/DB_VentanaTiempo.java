package energigas.apps.systemstrategy.energigas.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import energigas.apps.systemstrategy.energigas.entities.VentanaTiempo;
import energigas.apps.systemstrategy.energigas.utils.Constants;

/**
 * Created by KelvinThony on 11/08/2016.
 */
public class DB_VentanaTiempo {
    public static final String _id = "_id";
    public static final String veId = "veId";
    public static final String establecimientoId = "establecimientoId";
    public static final String dia = "dia";
    public static final String horaInicio = "horaInicio";
    public static final String horaFin = "horaFin";
    public static final String usuarioCreacion = "usuarioCreacion";
    public static final String usuarioActualizacion = "usuarioActualizacion";
    public static final String fechaCreacion = "fechaCreacion";
    public static final String fechaActualizacion = "fechaActualizacion";
    public static final String estado = "estado";
    public static final String preferido = "preferido";
    private DbHelper mDbHelper;
    private SQLiteDatabase mDb;
    private static final String SQLITE_TABLA_DB_VENTANATIEMPO = "DB_VentanaTiempo";
    private final Context mCtx;
    public static final String CREATE_TABLA_DB_VENTANATIEMPO =
            "create table " + SQLITE_TABLA_DB_VENTANATIEMPO + " ("
                    + _id + " integer primary key autoincrement,"
                    + veId + " integer ,"
                    + establecimientoId + " integer ,"
                    + dia + " text ,"
                    + horaInicio + " text ,"
                    + horaFin + " text ,"
                    + usuarioCreacion + " integer ,"
                    + usuarioActualizacion + " integer ,"
                    + fechaCreacion + " text ,"
                    + fechaActualizacion + " text ,"
                    + estado + " integer ,"
                    + preferido + " integer ,"
                    + Constants._CLMEXPORT + " integer );";
    public static final String DELETE_TABLA_DB_VENTANATIEMPO = "DROP TABLE IF EXISTS " + SQLITE_TABLA_DB_VENTANATIEMPO;

    public DB_VentanaTiempo(Context ctx) {
        this.mCtx = ctx;
    }

    public DB_VentanaTiempo open() {
        mDbHelper = new DbHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        if (mDbHelper != null) {
            mDbHelper.close();
        }
    }

    public long createVentanaTiempo(@NonNull VentanaTiempo ventanatiempo) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(veId, ventanatiempo.getVeId());
        initialValues.put(establecimientoId, ventanatiempo.getEstablecimientoId());
        initialValues.put(dia, ventanatiempo.getDia());
        initialValues.put(horaInicio, ventanatiempo.getHoraInicio());
        initialValues.put(horaFin, ventanatiempo.getHoraFin());
        initialValues.put(usuarioCreacion, ventanatiempo.getUsuarioCreacion());
        initialValues.put(usuarioActualizacion, ventanatiempo.getUsuarioActualizacion());
        initialValues.put(fechaCreacion, ventanatiempo.getFechaCreacion());
        initialValues.put(fechaActualizacion, ventanatiempo.getFechaActualizacion());
        initialValues.put(estado, ventanatiempo.isEstado());
        initialValues.put(preferido, ventanatiempo.isPreferido());
        initialValues.put(Constants._CLMEXPORT, Constants._CREADO);
        return mDb.insert(SQLITE_TABLA_DB_VENTANATIEMPO, null, initialValues);
    }

    public long updateVentanaTiempo(@NonNull VentanaTiempo ventanatiempo) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(establecimientoId, ventanatiempo.getEstablecimientoId());
        initialValues.put(dia, ventanatiempo.getDia());
        initialValues.put(horaInicio, ventanatiempo.getHoraInicio());
        initialValues.put(horaFin, ventanatiempo.getHoraFin());
        initialValues.put(usuarioCreacion, ventanatiempo.getUsuarioCreacion());
        initialValues.put(usuarioActualizacion, ventanatiempo.getUsuarioActualizacion());
        initialValues.put(fechaCreacion, ventanatiempo.getFechaCreacion());
        initialValues.put(fechaActualizacion, ventanatiempo.getFechaActualizacion());
        initialValues.put(estado, ventanatiempo.isEstado());
        initialValues.put(preferido, ventanatiempo.isPreferido());
        initialValues.put(Constants._CLMEXPORT, Constants._CREADO);
        return mDb.update(SQLITE_TABLA_DB_VENTANATIEMPO, initialValues,
                veId + "=?", new String[]{"" + ventanatiempo.getVeId()});
    }
}
