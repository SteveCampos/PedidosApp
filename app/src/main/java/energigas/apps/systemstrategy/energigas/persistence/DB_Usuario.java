package energigas.apps.systemstrategy.energigas.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import energigas.apps.systemstrategy.energigas.entities.Usuario;
import energigas.apps.systemstrategy.energigas.utils.Constants;

/**
 * Created by KelvinThony on 11/08/2016.
 */
public class DB_Usuario {
    public static final String _id = "_id";
    public static final String usuIUsuarioId = "usuIUsuarioId";
    public static final String usuVUsuario = "usuVUsuario";
    public static final String usuVPassword = "usuVPassword";
    public static final String usuBEstado = "usuBEstado";
    public static final String usuIPersonaId = "usuIPersonaId";
    public static final String usuIUsuarioActualizacion = "usuIUsuarioActualizacion";
    public static final String usuDTFechaActualizacion = "usuDTFechaActualizacion";
    public static final String usuIUsuarioCreacion = "usuIUsuarioCreacion";
    public static final String usuDTFechaCreacion = "usuDTFechaCreacion";
    private DbHelper mDbHelper;
    private SQLiteDatabase mDb;
    private static final String SQLITE_TABLA_DB_USUARIO = "DB_Usuario";
    private final Context mCtx;
    public static final String CREATE_TABLA_DB_USUARIO =
            "create table " + SQLITE_TABLA_DB_USUARIO + " ("
                    + _id + " integer primary key autoincrement,"
                    + usuIUsuarioId + " integer ,"
                    + usuVUsuario + " text ,"
                    + usuVPassword + " text ,"
                    + usuBEstado + " integer ,"
                    + usuIPersonaId + " integer ,"
                    + usuIUsuarioActualizacion + " integer ,"
                    + usuDTFechaActualizacion + " text ,"
                    + usuIUsuarioCreacion + " integer ,"
                    + usuDTFechaCreacion + " text ,"
                    + Constants._CLMEXPORT + " integer );";
    public static final String DELETE_TABLA_DB_USUARIO = "DROP TABLE IF EXISTS " + SQLITE_TABLA_DB_USUARIO;

    public DB_Usuario(Context ctx) {
        this.mCtx = ctx;
    }

    public DB_Usuario open() {
        mDbHelper = new DbHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        if (mDbHelper != null) {
            mDbHelper.close();
        }
    }

    public long createUsuario(@NonNull Usuario usuario) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(usuIUsuarioId, usuario.getUsuIUsuarioId());
        initialValues.put(usuVUsuario, usuario.getUsuVUsuario());
        initialValues.put(usuVPassword, usuario.getUsuVPassword());
        initialValues.put(usuBEstado, usuario.isUsuBEstado());
        initialValues.put(usuIPersonaId, usuario.getUsuIPersonaId());
        initialValues.put(usuIUsuarioActualizacion, usuario.getUsuIUsuarioActualizacion());
        initialValues.put(usuDTFechaActualizacion, usuario.getUsuDTFechaActualizacion());
        initialValues.put(usuIUsuarioCreacion, usuario.getUsuIUsuarioCreacion());
        initialValues.put(usuDTFechaCreacion, usuario.getUsuDTFechaCreacion());
        initialValues.put(Constants._CLMEXPORT, Constants._CREADO);
        return mDb.insert(SQLITE_TABLA_DB_USUARIO, null, initialValues);
    }

    public long updateUsuario(@NonNull Usuario usuario) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(usuVUsuario, usuario.getUsuVUsuario());
        initialValues.put(usuVPassword, usuario.getUsuVPassword());
        initialValues.put(usuBEstado, usuario.isUsuBEstado());
        initialValues.put(usuIPersonaId, usuario.getUsuIPersonaId());
        initialValues.put(usuIUsuarioActualizacion, usuario.getUsuIUsuarioActualizacion());
        initialValues.put(usuDTFechaActualizacion, usuario.getUsuDTFechaActualizacion());
        initialValues.put(usuIUsuarioCreacion, usuario.getUsuIUsuarioCreacion());
        initialValues.put(usuDTFechaCreacion, usuario.getUsuDTFechaCreacion());
        initialValues.put(Constants._CLMEXPORT, Constants._CREADO);
        return mDb.update(SQLITE_TABLA_DB_USUARIO, initialValues,
                usuIUsuarioId + "=?", new String[]{"" + usuario.getUsuIUsuarioId()});
    }
}
