package energigas.apps.systemstrategy.energigas.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import energigas.apps.systemstrategy.energigas.entities.Despacho;
import energigas.apps.systemstrategy.energigas.utils.Constants;

/**
 * Created by KelvinThony on 11/08/2016.
 */
public class DB_Despacho {
    public static final String _id = "_id";
    public static final String despachoId = "despachoId";
    public static final String peId = "peId";
    public static final String pdId = "pdId";
    public static final String clienteId = "clienteId";
    public static final String establecimientoId = "establecimientoId";
    public static final String almacenEstId = "almacenEstId";
    public static final String usuarioId = "usuarioId";
    public static final String placa = "placa";
    public static final String contadorInicial = "contadorInicial";
    public static final String contadorFinal = "contadorFinal";
    public static final String cantidadDespachada = "cantidadDespachada";
    public static final String horaInicio = "horaInicio";
    public static final String horaFin = "horaFin";
    public static final String fechaDespacho = "fechaDespacho";
    public static final String proId = "proId";
    public static final String unId = "unId";
    public static final String pIT = "pIT";
    public static final String pFT = "pFT";
    public static final String latitud = "latitud";
    public static final String longitud = "longitud";
    public static final String almacenVehId = "almacenVehId";
    public static final String serie = "serie";
    public static final String numero = "numero";
    public static final String fechaCreacion = "fechaCreacion";
    public static final String usuarioCreacion = "usuarioCreacion";
    public static final String estadoId = "estadoId";
    public static final String vehiculoId = "vehiculoId";
    private DbHelper mDbHelper;
    private SQLiteDatabase mDb;
    private static final String SQLITE_TABLA_DB_DESPACHO = "DB_Despacho";
    private final Context mCtx;
    public static final String CREATE_TABLA_DB_DESPACHO =
            "create table " + SQLITE_TABLA_DB_DESPACHO + " ("
                    + _id + " integer primary key autoincrement,"
                    + despachoId + " integer ,"
                    + peId + " integer ,"
                    + pdId + " integer ,"
                    + clienteId + " integer ,"
                    + establecimientoId + " integer ,"
                    + almacenEstId + " integer ,"
                    + usuarioId + " integer ,"
                    + placa + " text ,"
                    + contadorInicial + " real ,"
                    + contadorFinal + " real ,"
                    + cantidadDespachada + " real ,"
                    + horaInicio + " text ,"
                    + horaFin + " text ,"
                    + fechaDespacho + " text ,"
                    + proId + " integer ,"
                    + unId + " integer ,"
                    + pIT + " real ,"
                    + pFT + " real ,"
                    + latitud + " text ,"
                    + longitud + " text ,"
                    + almacenVehId + " integer ,"
                    + serie + " text ,"
                    + numero + " real ,"
                    + fechaCreacion + " integer ,"
                    + usuarioCreacion + " text ,"
                    + estadoId + " integer ,"
                    + vehiculoId + " integer ,"
                    + Constants._CLMEXPORT + " integer );";
    public static final String DELETE_TABLA_DB_DESPACHO = "DROP TABLE IF EXISTS " + SQLITE_TABLA_DB_DESPACHO;

    public DB_Despacho(Context ctx) {
        this.mCtx = ctx;
    }

    public DB_Despacho open() {
        mDbHelper = new DbHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        if (mDbHelper != null) {
            mDbHelper.close();
        }
    }

    public long createDespacho(@NonNull Despacho despacho) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(despachoId, despacho.getDespachoId());
        initialValues.put(peId, despacho.getPeId());
        initialValues.put(pdId, despacho.getPdId());
        initialValues.put(clienteId, despacho.getClienteId());
        initialValues.put(establecimientoId, despacho.getEstablecimientoId());
        initialValues.put(almacenEstId, despacho.getAlmacenEstId());
        initialValues.put(usuarioId, despacho.getUsuarioId());
        initialValues.put(placa, despacho.getPlaca());
        initialValues.put(contadorInicial, despacho.getContadorInicial());
        initialValues.put(contadorFinal, despacho.getContadorFinal());
        initialValues.put(cantidadDespachada, despacho.getCantidadDespachada());
        initialValues.put(horaInicio, despacho.getHoraInicio());
        initialValues.put(horaFin, despacho.getHoraFin());
        initialValues.put(fechaDespacho, despacho.getFechaDespacho());
        initialValues.put(proId, despacho.getProId());
        initialValues.put(unId, despacho.getUnId());
        initialValues.put(pIT, despacho.getpIT());
        initialValues.put(pFT, despacho.getpFT());
        initialValues.put(latitud, despacho.getLatitud());
        initialValues.put(longitud, despacho.getLongitud());
        initialValues.put(almacenVehId, despacho.getAlmacenVehId());
        initialValues.put(serie, despacho.getSerie());
        initialValues.put(numero, despacho.getNumero());
        initialValues.put(fechaCreacion, despacho.getFechaCreacion());
        initialValues.put(usuarioCreacion, despacho.getUsuarioCreacion());
        initialValues.put(estadoId, despacho.getEstadoId());
        initialValues.put(vehiculoId, despacho.getVehiculoId());
        initialValues.put(Constants._CLMEXPORT, Constants._CREADO);
        return mDb.insert(SQLITE_TABLA_DB_DESPACHO, null, initialValues);
    }

    public long updateDespacho(@NonNull Despacho despacho) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(peId, despacho.getPeId());
        initialValues.put(pdId, despacho.getPdId());
        initialValues.put(clienteId, despacho.getClienteId());
        initialValues.put(establecimientoId, despacho.getEstablecimientoId());
        initialValues.put(almacenEstId, despacho.getAlmacenEstId());
        initialValues.put(usuarioId, despacho.getUsuarioId());
        initialValues.put(placa, despacho.getPlaca());
        initialValues.put(contadorInicial, despacho.getContadorInicial());
        initialValues.put(contadorFinal, despacho.getContadorFinal());
        initialValues.put(cantidadDespachada, despacho.getCantidadDespachada());
        initialValues.put(horaInicio, despacho.getHoraInicio());
        initialValues.put(horaFin, despacho.getHoraFin());
        initialValues.put(fechaDespacho, despacho.getFechaDespacho());
        initialValues.put(proId, despacho.getProId());
        initialValues.put(unId, despacho.getUnId());
        initialValues.put(pIT, despacho.getpIT());
        initialValues.put(pFT, despacho.getpFT());
        initialValues.put(latitud, despacho.getLatitud());
        initialValues.put(longitud, despacho.getLongitud());
        initialValues.put(almacenVehId, despacho.getAlmacenVehId());
        initialValues.put(serie, despacho.getSerie());
        initialValues.put(numero, despacho.getNumero());
        initialValues.put(fechaCreacion, despacho.getFechaCreacion());
        initialValues.put(usuarioCreacion, despacho.getUsuarioCreacion());
        initialValues.put(estadoId, despacho.getEstadoId());
        initialValues.put(vehiculoId, despacho.getVehiculoId());
        initialValues.put(Constants._CLMEXPORT, Constants._CREADO);
        return mDb.update(SQLITE_TABLA_DB_DESPACHO, initialValues,
                despachoId + "=?", new String[]{"" + despacho.getDespachoId()});
    }
}
