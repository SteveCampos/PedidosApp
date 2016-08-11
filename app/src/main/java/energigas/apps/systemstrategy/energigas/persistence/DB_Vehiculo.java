package energigas.apps.systemstrategy.energigas.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import energigas.apps.systemstrategy.energigas.entities.Vehiculo;
import energigas.apps.systemstrategy.energigas.utils.Constants;

/**
 * Created by KelvinThony on 11/08/2016.
 */
public class DB_Vehiculo {
    public static final String _id = "_id";
    public static final String veId = "veId";
    public static final String tipoVehiculoId = "tipoVehiculoId";
    public static final String claseId = "claseId";
    public static final String tamanoId = "tamanoId";
    public static final String tipoConductorId = "tipoConductorId";
    public static final String modelo = "modelo";
    public static final String capacidadPeso = "capacidadPeso";
    public static final String placa = "placa";
    public static final String marcaId = "marcaId";
    public static final String costoHora = "costoHora";
    public static final String costoKm = "costoKm";
    public static final String costoSeguro = "costoSeguro";
    public static final String costoSOAT = "costoSOAT";
    public static final String usuarioCreacion = "usuarioCreacion";
    public static final String fechaCreacion = "fechaCreacion";
    public static final String usuarioActualizacion = "usuarioActualizacion";
    public static final String fechaActualizacion = "fechaActualizacion";
    public static final String empresaId = "empresaId";
    public static final String estadoId = "estadoId";
    private DbHelper mDbHelper;
    private SQLiteDatabase mDb;
    private static final String SQLITE_TABLA_DB_VEHICULO = "DB_Vehiculo";
    private final Context mCtx;
    public static final String CREATE_TABLA_DB_VEHICULO =
            "create table " + SQLITE_TABLA_DB_VEHICULO + " ("
                    + _id + " integer primary key autoincrement,"
                    + veId + " integer ,"
                    + tipoVehiculoId + " integer ,"
                    + claseId + " integer ,"
                    + tamanoId + " integer ,"
                    + tipoConductorId + " integer ,"
                    + modelo + " text ,"
                    + capacidadPeso + " real ,"
                    + placa + " text ,"
                    + marcaId + " integer ,"
                    + costoHora + " real ,"
                    + costoKm + " real ,"
                    + costoSeguro + " real ,"
                    + costoSOAT + " real ,"
                    + usuarioCreacion + " integer ,"
                    + fechaCreacion + " text ,"
                    + usuarioActualizacion + " integer ,"
                    + fechaActualizacion + " text ,"
                    + empresaId + " integer ,"
                    + estadoId + " integer ,"
                    + Constants._CLMEXPORT + " integer );";
    public static final String DELETE_TABLA_DB_VEHICULO = "DROP TABLE IF EXISTS " + SQLITE_TABLA_DB_VEHICULO;

    public DB_Vehiculo(Context ctx) {
        this.mCtx = ctx;
    }

    public DB_Vehiculo open() {
        mDbHelper = new DbHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        if (mDbHelper != null) {
            mDbHelper.close();
        }
    }

    public long createVehiculo(@NonNull Vehiculo vehiculo) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(veId, vehiculo.getVeId());
        initialValues.put(tipoVehiculoId, vehiculo.getTipoVehiculoId());
        initialValues.put(claseId, vehiculo.getClaseId());
        initialValues.put(tamanoId, vehiculo.getTamanoId());
        initialValues.put(tipoConductorId, vehiculo.getTipoConductorId());
        initialValues.put(modelo, vehiculo.getModelo());
        initialValues.put(capacidadPeso, vehiculo.getCapacidadPeso());
        initialValues.put(placa, vehiculo.getPlaca());
        initialValues.put(marcaId, vehiculo.getMarcaId());
        initialValues.put(costoHora, vehiculo.getCostoHora());
        initialValues.put(costoKm, vehiculo.getCostoKm());
        initialValues.put(costoSeguro, vehiculo.getCostoSeguro());
        initialValues.put(costoSOAT, vehiculo.getCostoSOAT());
        initialValues.put(usuarioCreacion, vehiculo.getUsuarioCreacion());
        initialValues.put(fechaCreacion, vehiculo.getFechaCreacion());
        initialValues.put(usuarioActualizacion, vehiculo.getUsuarioActualizacion());
        initialValues.put(fechaActualizacion, vehiculo.getFechaActualizacion());
        initialValues.put(empresaId, vehiculo.getEmpresaId());
        initialValues.put(estadoId, vehiculo.getEstadoId());
        initialValues.put(Constants._CLMEXPORT, Constants._CREADO);
        return mDb.insert(SQLITE_TABLA_DB_VEHICULO, null, initialValues);
    }

    public long updateVehiculo(@NonNull Vehiculo vehiculo) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(tipoVehiculoId, vehiculo.getTipoVehiculoId());
        initialValues.put(claseId, vehiculo.getClaseId());
        initialValues.put(tamanoId, vehiculo.getTamanoId());
        initialValues.put(tipoConductorId, vehiculo.getTipoConductorId());
        initialValues.put(modelo, vehiculo.getModelo());
        initialValues.put(capacidadPeso, vehiculo.getCapacidadPeso());
        initialValues.put(placa, vehiculo.getPlaca());
        initialValues.put(marcaId, vehiculo.getMarcaId());
        initialValues.put(costoHora, vehiculo.getCostoHora());
        initialValues.put(costoKm, vehiculo.getCostoKm());
        initialValues.put(costoSeguro, vehiculo.getCostoSeguro());
        initialValues.put(costoSOAT, vehiculo.getCostoSOAT());
        initialValues.put(usuarioCreacion, vehiculo.getUsuarioCreacion());
        initialValues.put(fechaCreacion, vehiculo.getFechaCreacion());
        initialValues.put(usuarioActualizacion, vehiculo.getUsuarioActualizacion());
        initialValues.put(fechaActualizacion, vehiculo.getFechaActualizacion());
        initialValues.put(empresaId, vehiculo.getEmpresaId());
        initialValues.put(estadoId, vehiculo.getEstadoId());
        initialValues.put(Constants._CLMEXPORT, Constants._CREADO);
        return mDb.update(SQLITE_TABLA_DB_VEHICULO, initialValues,
                veId + "=?", new String[]{"" + vehiculo.getVeId()});
    }
}
