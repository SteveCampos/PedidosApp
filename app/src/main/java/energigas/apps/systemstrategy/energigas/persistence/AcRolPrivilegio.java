package energigas.apps.systemstrategy.energigas.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import energigas.apps.systemstrategy.energigas.entities.RolPrivilegio;
import energigas.apps.systemstrategy.energigas.entities.RolUsuario;
import energigas.apps.systemstrategy.energigas.utils.Constants;

/**
 * Created by kelvi on 09/08/2016.
 */

public class AcRolPrivilegio {

    public static final String _Id = "_id";
    public static final String RolId = "Id";
    public static final String PrivilegioId = "UsuVUsuario";
   public static final String TAG = "AcUsuario";

    private DbHelper mDbHelper;
    private SQLiteDatabase mDb;

    private static final String SQLITE_TABLA_ROL_USUARIO = "AC_ROL";
    private final Context mCtx;

    public static final String CREATE_TABLA_ROL_USUARIO=
            "create table "+ SQLITE_TABLA_ROL_USUARIO +" ("
                    +_Id+" integer primary key autoincrement,"
                    +RolId+" integer,"
                    + PrivilegioId +" integer,"
                    + Constants._CLMEXPORT+" integer );";

    public static final String DELETE_TABLA_ROL_USUARIO= "DROP TABLE IF EXISTS " + SQLITE_TABLA_ROL_USUARIO;
    public AcRolPrivilegio(Context ctx) {
        this.mCtx = ctx;
    }

    public AcRolPrivilegio open()  {
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
