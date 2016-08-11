package energigas.apps.systemstrategy.energigas.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import energigas.apps.systemstrategy.energigas.entities.Acceso;
import energigas.apps.systemstrategy.energigas.entities.RolAcceso;
import energigas.apps.systemstrategy.energigas.utils.Constants;

/**
 * Created by kelvi on 09/08/2016.
 */

public class AcRolAcceso {

    public static final String _Id = "_id";
    public static final String AccesoId = "Id";
    public static final String RolId = "UsuVUsuario";
    public static final String AccesoDefault = "UsuVPassword";
   public static final String TAG = "AcUsuario";

    private DbHelper mDbHelper;
    private SQLiteDatabase mDb;

    private static final String SQLITE_TABLA_ROL_ACCESO = "ROL_ACCESO";
    private final Context mCtx;

    public static final String CREATE_TABLA_ROL_ACCESO=
            "create table "+ SQLITE_TABLA_ROL_ACCESO +" ("
                    +_Id+" integer primary key autoincrement,"
                    +AccesoId+" integer,"
                    +RolId+" integer,"
                    +AccesoDefault+" text,"
                    + Constants._CLMEXPORT+" integer );";

    public static final String DELETE_TABLA_ROL_ACCESO= "DROP TABLE IF EXISTS " + SQLITE_TABLA_ROL_ACCESO;
    public AcRolAcceso(Context ctx) {
        this.mCtx = ctx;
    }

    public AcRolAcceso open()  {
        mDbHelper = new DbHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this ;
    }
    public void close() {
        if (mDbHelper != null) {
            mDbHelper.close();
        }
    }

}
