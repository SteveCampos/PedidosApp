package energigas.apps.systemstrategy.energigas.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import energigas.apps.systemstrategy.energigas.entities.Cliente;
import energigas.apps.systemstrategy.energigas.utils.Constants;

/**
 * Created by KelvinThony on 11/08/2016.
 */
public class DB_Cliente {
    public static final String _id = "_id";
    public static final String cliIClienteId = "cliIClienteId";
    public static final String cliVCodigo = "cliVCodigo";
    public static final String cliIPersonaId = "cliIPersonaId";
    public static final String cliIUsuarioActualizacion = "cliIUsuarioActualizacion";
    public static final String cliDTFechaActualizacion = "cliDTFechaActualizacion";
    public static final String cliDOMontoCredito = "cliDOMontoCredito";
    public static final String cliIModalidadCreditoId = "cliIModalidadCreditoId";
    public static final String cliIUsuarioCreacion = "cliIUsuarioCreacion";
    public static final String cliDTFechaCreacion = "cliDTFechaCreacion";
    public static final String cliIEstadoId = "cliIEstadoId";
    public static final String cliITipoClienteId = "cliITipoClienteId";
    public static final String cliDOSobreGiro = "cliDOSobreGiro";
    public static final String cliBLineaCredito = "cliBLineaCredito";
    public static final String cliVTelefono = "cliVTelefono";
    public static final String cliVCelular = "cliVCelular";
    public static final String cliDOCreditoDisponible = "cliDOCreditoDisponible";
    public static final String cliVContacto = "cliVContacto";
    private DbHelper mDbHelper;
    private SQLiteDatabase mDb;
    private static final String SQLITE_TABLA_DB_CLIENTE = "DB_Cliente";
    private final Context mCtx;
    public static final String CREATE_TABLA_DB_CLIENTE =
            "create table " + SQLITE_TABLA_DB_CLIENTE + " ("
                    + _id + " integer primary key autoincrement,"
                    + cliIClienteId + " integer ,"
                    + cliVCodigo + " text ,"
                    + cliIPersonaId + " integer ,"
                    + cliIUsuarioActualizacion + " integer ,"
                    + cliDTFechaActualizacion + " text ,"
                    + cliDOMontoCredito + " real ,"
                    + cliIModalidadCreditoId + " integer ,"
                    + cliIUsuarioCreacion + " integer ,"
                    + cliDTFechaCreacion + " text ,"
                    + cliIEstadoId + " integer ,"
                    + cliITipoClienteId + " integer ,"
                    + cliDOSobreGiro + " real ,"
                    + cliBLineaCredito + " integer ,"
                    + cliVTelefono + " text ,"
                    + cliVCelular + " text ,"
                    + cliDOCreditoDisponible + " real ,"
                    + cliVContacto + " text ,"
                    + Constants._CLMEXPORT + " integer );";
    public static final String DELETE_TABLA_DB_CLIENTE = "DROP TABLE IF EXISTS " + SQLITE_TABLA_DB_CLIENTE;

    public DB_Cliente(Context ctx) {
        this.mCtx = ctx;
    }

    public DB_Cliente open() {
        mDbHelper = new DbHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        if (mDbHelper != null) {
            mDbHelper.close();
        }
    }

    public long createCliente(@NonNull Cliente cliente) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(cliIClienteId, cliente.getCliIClienteId());
        initialValues.put(cliVCodigo, cliente.getCliVCodigo());
        initialValues.put(cliIPersonaId, cliente.getCliIPersonaId());
        initialValues.put(cliIUsuarioActualizacion, cliente.getCliIUsuarioActualizacion());
        initialValues.put(cliDTFechaActualizacion, cliente.getCliDTFechaActualizacion());
        initialValues.put(cliDOMontoCredito, cliente.getCliDOMontoCredito());
        initialValues.put(cliIModalidadCreditoId, cliente.getCliIModalidadCreditoId());
        initialValues.put(cliIUsuarioCreacion, cliente.getCliIUsuarioCreacion());
        initialValues.put(cliDTFechaCreacion, cliente.getCliDTFechaCreacion());
        initialValues.put(cliIEstadoId, cliente.getCliIEstadoId());
        initialValues.put(cliITipoClienteId, cliente.getCliITipoClienteId());
        initialValues.put(cliDOSobreGiro, cliente.getCliDOSobreGiro());
        initialValues.put(cliBLineaCredito, cliente.isCliBLineaCredito());
        initialValues.put(cliVTelefono, cliente.getCliVTelefono());
        initialValues.put(cliVCelular, cliente.getCliVCelular());
        initialValues.put(cliDOCreditoDisponible, cliente.getCliDOCreditoDisponible());
        initialValues.put(cliVContacto, cliente.getCliVContacto());
        initialValues.put(Constants._CLMEXPORT, Constants._CREADO);
        return mDb.insert(SQLITE_TABLA_DB_CLIENTE, null, initialValues);
    }

    public long updateCliente(@NonNull Cliente cliente) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(cliVCodigo, cliente.getCliVCodigo());
        initialValues.put(cliIPersonaId, cliente.getCliIPersonaId());
        initialValues.put(cliIUsuarioActualizacion, cliente.getCliIUsuarioActualizacion());
        initialValues.put(cliDTFechaActualizacion, cliente.getCliDTFechaActualizacion());
        initialValues.put(cliDOMontoCredito, cliente.getCliDOMontoCredito());
        initialValues.put(cliIModalidadCreditoId, cliente.getCliIModalidadCreditoId());
        initialValues.put(cliIUsuarioCreacion, cliente.getCliIUsuarioCreacion());
        initialValues.put(cliDTFechaCreacion, cliente.getCliDTFechaCreacion());
        initialValues.put(cliIEstadoId, cliente.getCliIEstadoId());
        initialValues.put(cliITipoClienteId, cliente.getCliITipoClienteId());
        initialValues.put(cliDOSobreGiro, cliente.getCliDOSobreGiro());
        initialValues.put(cliBLineaCredito, cliente.isCliBLineaCredito());
        initialValues.put(cliVTelefono, cliente.getCliVTelefono());
        initialValues.put(cliVCelular, cliente.getCliVCelular());
        initialValues.put(cliDOCreditoDisponible, cliente.getCliDOCreditoDisponible());
        initialValues.put(cliVContacto, cliente.getCliVContacto());
        initialValues.put(Constants._CLMEXPORT, Constants._CREADO);
        return mDb.update(SQLITE_TABLA_DB_CLIENTE, initialValues,
                cliIClienteId + "=?", new String[]{"" + cliente.getCliIClienteId()});
    }
}
