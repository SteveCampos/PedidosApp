package energigas.apps.systemstrategy.energigas.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import energigas.apps.systemstrategy.energigas.entities.Persona;
import energigas.apps.systemstrategy.energigas.utils.Constants;

/**
 * Created by kelvi on 09/08/2016.
 */

public class SidPersona {

    public static final String _Id = "_id";

    public static final String PerIPersonaId = "PerIPersonaId";
    public static final String PerVRazonSocial = "PerVRazonSocial";
    public static final String PerVNombres = "PerVNombres";
    public static final String PerVApellidoPaterno = "PerVApellidoPaterno";
    public static final String PerVApellidoMaterno = "PerVApellidoMaterno";
    public static final String PerVDocIdentidad = "PerVDocIdentidad";
    public static final String PerBEstado = "PerBEstado";
    public static final String PerITipoDocIdentidadId = "PerITipoDocIdentidadId";
    public static final String PerITipoPersonaId = "PerITipoPersonaId";
    public static final String PerIEmpresaId = "PerIEmpresaId";
    public static final String PerVEmail = "PerVEmail";

    public static final String TAG = "AcUsuario";

    private DbHelper mDbHelper;
    private SQLiteDatabase mDb;

    private static final String SQLITE_TABLA_PERSONA = "AC_USUARIO";
    private final Context mCtx;

    public static final String CREATE_TABLA_PERSONA =
            "create table " + SQLITE_TABLA_PERSONA + " ("
                    + _Id + " integer primary key autoincrement,"
                    + PerIPersonaId + " integer,"
                    + PerVRazonSocial + " text,"
                    + PerVNombres + " text,"
                    + PerVApellidoPaterno + " text,"
                    + PerVApellidoMaterno + " text,"
                    + PerVDocIdentidad + " text,"
                    + PerBEstado + " text,"
                    + PerITipoDocIdentidadId + " text,"
                    + PerITipoPersonaId + " text,"
                    + PerIEmpresaId + " text,"
                    + PerVEmail + " text,"
                    + Constants._CLMEXPORT + " integer );";

    public static final String DELETE_TABLA_PERSONA = "DROP TABLE IF EXISTS " + SQLITE_TABLA_PERSONA;

    public SidPersona(Context ctx) {
        this.mCtx = ctx;
    }

    public SidPersona open() {
        mDbHelper = new DbHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        if (mDbHelper != null) {
            mDbHelper.close();
        }
    }

    public long createPersona(Persona persona) {

        ContentValues initialValues = new ContentValues();
        initialValues.put(PerIPersonaId, persona.getPerIPersonaId());
        initialValues.put(PerVRazonSocial, persona.getPerVRazonSocial());
        initialValues.put(PerVNombres, persona.getPerVNombres());
        initialValues.put(PerVApellidoPaterno, persona.getPerVApellidoPaterno());
        initialValues.put(PerVApellidoMaterno, persona.getPerVApellidoMaterno());
        initialValues.put(PerVDocIdentidad, persona.getPerVDocIdentidad());
        initialValues.put(PerBEstado, persona.getPerBEstado());
        initialValues.put(PerITipoDocIdentidadId, persona.getPerITipoDocIdentidadId());
        initialValues.put(PerITipoPersonaId, persona.getPerITipoPersonaId());
        initialValues.put(PerIEmpresaId, persona.getPerIEmpresaId());
        initialValues.put(PerVEmail, persona.getPerVEmail());
        initialValues.put(Constants._CLMEXPORT, Constants._CREADO);

        return mDb.insert(SQLITE_TABLA_PERSONA, null, initialValues);
    }

    public int updatePersona(Persona persona) {

        ContentValues initialValues = new ContentValues();
        initialValues.put(PerVRazonSocial, persona.getPerVRazonSocial());
        initialValues.put(PerVNombres, persona.getPerVNombres());
        initialValues.put(PerVApellidoPaterno, persona.getPerVApellidoPaterno());
        initialValues.put(PerVApellidoMaterno, persona.getPerVApellidoMaterno());
        initialValues.put(PerVDocIdentidad, persona.getPerVDocIdentidad());
        initialValues.put(PerBEstado, persona.getPerBEstado());
        initialValues.put(PerITipoDocIdentidadId, persona.getPerITipoDocIdentidadId());
        initialValues.put(PerITipoPersonaId, persona.getPerITipoPersonaId());
        initialValues.put(PerIEmpresaId, persona.getPerIEmpresaId());
        initialValues.put(PerVEmail, persona.getPerVEmail());
        initialValues.put(Constants._CLMEXPORT, Constants._CREADO);

        return mDb.update(SQLITE_TABLA_PERSONA, initialValues,
                PerIPersonaId + "=?", new String[]{"" + persona.getPerIPersonaId()});

    }

    public boolean deletePersonaById(Persona persona) {

        int doneDelete = mDb.delete(SQLITE_TABLA_PERSONA, PerIPersonaId + "=?", new String[]{persona.getPerIPersonaId() + ""});
        return doneDelete > 0;

    }


}
