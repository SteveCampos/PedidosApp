package energigas.apps.systemstrategy.energigas.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import energigas.apps.systemstrategy.energigas.entities.CajaLiquidacion;
import energigas.apps.systemstrategy.energigas.utils.Constants;

/**
 * Created by KelvinThony on 11/08/2016.
 */
public class DB_CajaLiquidacion {
    public static final String _id = "_id";
    public static final String liqId = "liqId";
    public static final String usuarioId = "usuarioId";
    public static final String fechaApertura = "fechaApertura";
    public static final String fechaCierre = "fechaCierre";
    public static final String estadoId = "estadoId";
    public static final String ingresos = "ingresos";
    public static final String gastos = "gastos";
    public static final String meta = "meta";
    public static final String porcentaje = "porcentaje";
    public static final String kmInicial = "kmInicial";
    public static final String kmFinal = "kmFinal";
    public static final String pesoInicial = "pesoInicial";
    public static final String pesoFinal = "pesoFinal";
    public static final String pIT = "pIT";
    public static final String pFT = "pFT";
    public static final String fechaActualizacion = "fechaActualizacion";
    public static final String usuarioActualizacion = "usuarioActualizacion";
    public static final String tipoId = "tipoId";
    public static final String placaVehiculo = "placaVehiculo";
    public static final String veId = "veId";
    public static final String almId = "almId";
    public static final String latitudInicio = "latitudInicio";
    public static final String longitudInicio = "longitudInicio";
    public static final String latitudFinal = "latitudFinal";
    public static final String longitudFinal = "longitudFinal";
    public static final String pdId = "pdId";
    public static final String pdfId = "pdfId";
    public static final String saldoInicial = "saldoInicial";
    public static final String saldoFinal = "saldoFinal";
    private DbHelper mDbHelper;
    private SQLiteDatabase mDb;
    private static final String SQLITE_TABLA_DB_CAJALIQUIDACION = "DB_CajaLiquidacion";
    private final Context mCtx;
    public static final String CREATE_TABLA_DB_CAJALIQUIDACION =
            "create table " + SQLITE_TABLA_DB_CAJALIQUIDACION + " ("
                    + _id + " integer primary key autoincrement,"
                    + liqId + " integer ,"
                    + usuarioId + " integer ,"
                    + fechaApertura + " text ,"
                    + fechaCierre + " text ,"
                    + estadoId + " integer ,"
                    + ingresos + " real ,"
                    + gastos + " real ,"
                    + meta + " real ,"
                    + porcentaje + " integer ,"
                    + kmInicial + " integer ,"
                    + kmFinal + " integer ,"
                    + pesoInicial + " real ,"
                    + pesoFinal + " real ,"
                    + pIT + " real ,"
                    + pFT + " real ,"
                    + fechaActualizacion + " text ,"
                    + usuarioActualizacion + " integer ,"
                    + tipoId + " integer ,"
                    + placaVehiculo + " text ,"
                    + veId + " integer ,"
                    + almId + " integer ,"
                    + latitudInicio + " text ,"
                    + longitudInicio + " text ,"
                    + latitudFinal + " text ,"
                    + longitudFinal + " text ,"
                    + pdId + " integer ,"
                    + pdfId + " integer ,"
                    + saldoInicial + " real ,"
                    + saldoFinal + " real ,"
                    + Constants._CLMEXPORT + " integer );";
    public static final String DELETE_TABLA_DB_CAJALIQUIDACION = "DROP TABLE IF EXISTS " + SQLITE_TABLA_DB_CAJALIQUIDACION;

    public DB_CajaLiquidacion(Context ctx) {
        this.mCtx = ctx;
    }

    public DB_CajaLiquidacion open() {
        mDbHelper = new DbHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        if (mDbHelper != null) {
            mDbHelper.close();
        }
    }

    public long createCajaLiquidacion(@NonNull CajaLiquidacion cajaliquidacion) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(liqId, cajaliquidacion.getLiqId());
        initialValues.put(usuarioId, cajaliquidacion.getUsuarioId());
        initialValues.put(fechaApertura, cajaliquidacion.getFechaApertura());
        initialValues.put(fechaCierre, cajaliquidacion.getFechaCierre());
        initialValues.put(estadoId, cajaliquidacion.getEstadoId());
        initialValues.put(ingresos, cajaliquidacion.getIngresos());
        initialValues.put(gastos, cajaliquidacion.getGastos());
        initialValues.put(meta, cajaliquidacion.getMeta());
        initialValues.put(porcentaje, cajaliquidacion.getPorcentaje());
        initialValues.put(kmInicial, cajaliquidacion.getKmInicial());
        initialValues.put(kmFinal, cajaliquidacion.getKmFinal());
        initialValues.put(pesoInicial, cajaliquidacion.getPesoInicial());
        initialValues.put(pesoFinal, cajaliquidacion.getPesoFinal());
        initialValues.put(pIT, cajaliquidacion.getpIT());
        initialValues.put(pFT, cajaliquidacion.getpFT());
        initialValues.put(fechaActualizacion, cajaliquidacion.getFechaActualizacion());
        initialValues.put(usuarioActualizacion, cajaliquidacion.getUsuarioActualizacion());
        initialValues.put(tipoId, cajaliquidacion.getTipoId());
        initialValues.put(placaVehiculo, cajaliquidacion.getPlacaVehiculo());
        initialValues.put(veId, cajaliquidacion.getVeId());
        initialValues.put(almId, cajaliquidacion.getAlmId());
        initialValues.put(latitudInicio, cajaliquidacion.getLatitudInicio());
        initialValues.put(longitudInicio, cajaliquidacion.getLongitudInicio());
        initialValues.put(latitudFinal, cajaliquidacion.getLatitudFinal());
        initialValues.put(longitudFinal, cajaliquidacion.getLongitudFinal());
        initialValues.put(pdId, cajaliquidacion.getPdId());
        initialValues.put(pdfId, cajaliquidacion.getPdfId());
        initialValues.put(saldoInicial, cajaliquidacion.getSaldoInicial());
        initialValues.put(saldoFinal, cajaliquidacion.getSaldoFinal());
        initialValues.put(Constants._CLMEXPORT, Constants._CREADO);
        return mDb.insert(SQLITE_TABLA_DB_CAJALIQUIDACION, null, initialValues);
    }

    public long updateCajaLiquidacion(@NonNull CajaLiquidacion cajaliquidacion) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(usuarioId, cajaliquidacion.getUsuarioId());
        initialValues.put(fechaApertura, cajaliquidacion.getFechaApertura());
        initialValues.put(fechaCierre, cajaliquidacion.getFechaCierre());
        initialValues.put(estadoId, cajaliquidacion.getEstadoId());
        initialValues.put(ingresos, cajaliquidacion.getIngresos());
        initialValues.put(gastos, cajaliquidacion.getGastos());
        initialValues.put(meta, cajaliquidacion.getMeta());
        initialValues.put(porcentaje, cajaliquidacion.getPorcentaje());
        initialValues.put(kmInicial, cajaliquidacion.getKmInicial());
        initialValues.put(kmFinal, cajaliquidacion.getKmFinal());
        initialValues.put(pesoInicial, cajaliquidacion.getPesoInicial());
        initialValues.put(pesoFinal, cajaliquidacion.getPesoFinal());
        initialValues.put(pIT, cajaliquidacion.getpIT());
        initialValues.put(pFT, cajaliquidacion.getpFT());
        initialValues.put(fechaActualizacion, cajaliquidacion.getFechaActualizacion());
        initialValues.put(usuarioActualizacion, cajaliquidacion.getUsuarioActualizacion());
        initialValues.put(tipoId, cajaliquidacion.getTipoId());
        initialValues.put(placaVehiculo, cajaliquidacion.getPlacaVehiculo());
        initialValues.put(veId, cajaliquidacion.getVeId());
        initialValues.put(almId, cajaliquidacion.getAlmId());
        initialValues.put(latitudInicio, cajaliquidacion.getLatitudInicio());
        initialValues.put(longitudInicio, cajaliquidacion.getLongitudInicio());
        initialValues.put(latitudFinal, cajaliquidacion.getLatitudFinal());
        initialValues.put(longitudFinal, cajaliquidacion.getLongitudFinal());
        initialValues.put(pdId, cajaliquidacion.getPdId());
        initialValues.put(pdfId, cajaliquidacion.getPdfId());
        initialValues.put(saldoInicial, cajaliquidacion.getSaldoInicial());
        initialValues.put(saldoFinal, cajaliquidacion.getSaldoFinal());
        initialValues.put(Constants._CLMEXPORT, Constants._CREADO);
        return mDb.update(SQLITE_TABLA_DB_CAJALIQUIDACION, initialValues,
                liqId + "=?", new String[]{"" + cajaliquidacion.getLiqId()});
    }
}
