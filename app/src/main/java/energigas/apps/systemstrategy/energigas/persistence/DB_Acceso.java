package energigas.apps.systemstrategy.energigas.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;

import energigas.apps.systemstrategy.energigas.entities.Acceso;
import energigas.apps.systemstrategy.energigas.entities.Rol;
import energigas.apps.systemstrategy.energigas.entities.RolAcceso;
import energigas.apps.systemstrategy.energigas.utils.Constants;

/**
 * Created by KelvinThony on 11/08/2016.
 */
public class DB_Acceso {

    private static final String TAG = "DB_Acceso";


    public static final String _id = "_id";
    public static final String id = "id";
    public static final String parentId = "parentId";
    public static final String abreviacion = "abreviacion";
    public static final String descripcion = "descripcion";
    public static final String item = "item";
    public static final String nivel = "nivel";
    public static final String uRL = "uRL";
    public static final String estado = "estado";
    public static final String fechaCreacion = "fechaCreacion";
    public static final String usuario = "usuario";
    public static final String icono = "icono";
    public static final String fechaActualizacion = "fechaActualizacion";
    public static final String usuarioActualizacion = "usuarioActualizacion";
    public static final String movil = "movil";
    private DbHelper mDbHelper;
    private SQLiteDatabase mDb;
    private static final String SQLITE_TABLA_DB_ACCESO = "DB_Acceso";
    private final Context mCtx;
    public static final String CREATE_TABLA_DB_ACCESO =
            "create table " + SQLITE_TABLA_DB_ACCESO + " ("
                    + _id + " integer primary key autoincrement,"
                    + id + " integer ,"
                    + parentId + " integer ,"
                    + abreviacion + " text ,"
                    + descripcion + " text ,"
                    + item + " integer ,"
                    + nivel + " integer ,"
                    + uRL + " text ,"
                    + estado + " integer ,"
                    + fechaCreacion + " text ,"
                    + usuario + " text ,"
                    + icono + " text ,"
                    + fechaActualizacion + " text ,"
                    + usuarioActualizacion + " text ,"
                    + movil + " integer ,"
                    + Constants._CLMEXPORT + " integer );";
    public static final String DELETE_TABLA_DB_ACCESO = "DROP TABLE IF EXISTS " + SQLITE_TABLA_DB_ACCESO;

    public DB_Acceso(Context ctx) {
        this.mCtx = ctx;
    }

    public DB_Acceso open() {
        mDbHelper = new DbHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        if (mDbHelper != null) {
            mDbHelper.close();
        }
    }

    public long createAcceso(@NonNull Acceso acceso) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(id, acceso.getId());
        initialValues.put(parentId, acceso.getParentId());
        initialValues.put(abreviacion, acceso.getAbreviacion());
        initialValues.put(descripcion, acceso.getDescripcion());
        initialValues.put(item, acceso.getItem());
        initialValues.put(nivel, acceso.getNivel());
        initialValues.put(uRL, acceso.getuRL());
        initialValues.put(estado, acceso.isEstado());
        initialValues.put(fechaCreacion, acceso.getFechaCreacion());
        initialValues.put(usuario, acceso.getUsuario());
        initialValues.put(icono, acceso.getIcono());
        initialValues.put(fechaActualizacion, acceso.getFechaActualizacion());
        initialValues.put(usuarioActualizacion, acceso.getUsuarioActualizacion());
        initialValues.put(movil, acceso.isMovil());
        initialValues.put(Constants._CLMEXPORT, Constants._CREADO);
        return mDb.insert(SQLITE_TABLA_DB_ACCESO, null, initialValues);
    }

    public boolean createAllAcceso(@NonNull Rol  rol, Context context) {
        DB_Privilegio db_privilegio = new DB_Privilegio(context);
        DB_RolAcceso db_rolAcceso = new DB_RolAcceso(context);
        db_privilegio.open();
        db_rolAcceso.open();
        boolean state = true;

        for (Acceso acceso : rol.getItemsAccesos()) {

            Long insert = createAcceso(acceso);
            RolAcceso rolAcceso = new RolAcceso(rol.getId(),acceso.getId(),true);
            Long insertRolAcceso = db_rolAcceso.createRolAcceso(rolAcceso);
            if ((insert & insertRolAcceso) < 0) {
                state = false;
                Log.e(TAG,"ERROR AL INSERTAR ACCESOS");
                break;
            } else {
                state = db_privilegio.createAllPrivilegios(acceso.getItemsPrivielgios(), context,rol);
                if (!state) {
                    break;
                }
            }

        }
        db_privilegio.close();
        return state;
    }

    public long updateAcceso(@NonNull Acceso acceso) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(parentId, acceso.getParentId());
        initialValues.put(abreviacion, acceso.getAbreviacion());
        initialValues.put(descripcion, acceso.getDescripcion());
        initialValues.put(item, acceso.getItem());
        initialValues.put(nivel, acceso.getNivel());
        initialValues.put(uRL, acceso.getuRL());
        initialValues.put(estado, acceso.isEstado());
        initialValues.put(fechaCreacion, acceso.getFechaCreacion());
        initialValues.put(usuario, acceso.getUsuario());
        initialValues.put(icono, acceso.getIcono());
        initialValues.put(fechaActualizacion, acceso.getFechaActualizacion());
        initialValues.put(usuarioActualizacion, acceso.getUsuarioActualizacion());
        initialValues.put(movil, acceso.isMovil());
        initialValues.put(Constants._CLMEXPORT, Constants._CREADO);
        return mDb.update(SQLITE_TABLA_DB_ACCESO, initialValues,
                id + "=?", new String[]{"" + acceso.getId()});
    }
}
