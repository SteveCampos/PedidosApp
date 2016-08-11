package energigas.apps.systemstrategy.energigas.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import energigas.apps.systemstrategy.energigas.entities.Persona;
import energigas.apps.systemstrategy.energigas.utils.Constants;

/**
 * Created by KelvinThony on 11/08/2016.
 */
public class DB_Persona {
    public static final String _id = "_id";
    public static final String perIPersonaId = "perIPersonaId";
    public static final String perVRazonSocial = "perVRazonSocial";
    public static final String perVNombres = "perVNombres";
    public static final String perVApellidoPaterno = "perVApellidoPaterno";
    public static final String perVApellidoMaterno = "perVApellidoMaterno";
    public static final String perVDocIdentidad = "perVDocIdentidad";
    public static final String perBEstado = "perBEstado";
    public static final String perITipoDocIdentidadId = "perITipoDocIdentidadId";
    public static final String perITipoPersonaId = "perITipoPersonaId";
    public static final String perIEmpresaId = "perIEmpresaId";
    public static final String perVEmail = "perVEmail";
    private DbHelper mDbHelper;
    private SQLiteDatabase mDb;
    private static final String SQLITE_TABLA_DB_PERSONA = "DB_Persona";
    private final Context mCtx;
    public static final String CREATE_TABLA_DB_PERSONA =
            "create table " + SQLITE_TABLA_DB_PERSONA + " ("
                    + _id + " integer primary key autoincrement,"
                    + perIPersonaId + " integer ,"
                    + perVRazonSocial + " text ,"
                    + perVNombres + " text ,"
                    + perVApellidoPaterno + " text ,"
                    + perVApellidoMaterno + " text ,"
                    + perVDocIdentidad + " text ,"
                    + perBEstado + " text ,"
                    + perITipoDocIdentidadId + " text ,"
                    + perITipoPersonaId + " text ,"
                    + perIEmpresaId + " text ,"
                    + perVEmail + " text ,"
                    + Constants._CLMEXPORT + " integer );";
    public static final String DELETE_TABLA_DB_PERSONA = "DROP TABLE IF EXISTS " + SQLITE_TABLA_DB_PERSONA;

    public DB_Persona(Context ctx) {
        this.mCtx = ctx;
    }

    public DB_Persona open() {
        mDbHelper = new DbHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        if (mDbHelper != null) {
            mDbHelper.close();
        }
    }

    public long createPersona(@NonNull Persona persona) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(perIPersonaId, persona.getPerIPersonaId());
        initialValues.put(perVRazonSocial, persona.getPerVRazonSocial());
        initialValues.put(perVNombres, persona.getPerVNombres());
        initialValues.put(perVApellidoPaterno, persona.getPerVApellidoPaterno());
        initialValues.put(perVApellidoMaterno, persona.getPerVApellidoMaterno());
        initialValues.put(perVDocIdentidad, persona.getPerVDocIdentidad());
        initialValues.put(perBEstado, persona.getPerBEstado());
        initialValues.put(perITipoDocIdentidadId, persona.getPerITipoDocIdentidadId());
        initialValues.put(perITipoPersonaId, persona.getPerITipoPersonaId());
        initialValues.put(perIEmpresaId, persona.getPerIEmpresaId());
        initialValues.put(perVEmail, persona.getPerVEmail());
        initialValues.put(Constants._CLMEXPORT, Constants._CREADO);
        return mDb.insert(SQLITE_TABLA_DB_PERSONA, null, initialValues);
    }

    public long updatePersona(@NonNull Persona persona) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(perVRazonSocial, persona.getPerVRazonSocial());
        initialValues.put(perVNombres, persona.getPerVNombres());
        initialValues.put(perVApellidoPaterno, persona.getPerVApellidoPaterno());
        initialValues.put(perVApellidoMaterno, persona.getPerVApellidoMaterno());
        initialValues.put(perVDocIdentidad, persona.getPerVDocIdentidad());
        initialValues.put(perBEstado, persona.getPerBEstado());
        initialValues.put(perITipoDocIdentidadId, persona.getPerITipoDocIdentidadId());
        initialValues.put(perITipoPersonaId, persona.getPerITipoPersonaId());
        initialValues.put(perIEmpresaId, persona.getPerIEmpresaId());
        initialValues.put(perVEmail, persona.getPerVEmail());
        initialValues.put(Constants._CLMEXPORT, Constants._CREADO);
        return mDb.update(SQLITE_TABLA_DB_PERSONA, initialValues,
                perIPersonaId + "=?", new String[]{"" + persona.getPerIPersonaId()});
    }
}
