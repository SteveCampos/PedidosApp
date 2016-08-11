package energigas.apps.systemstrategy.energigas.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import energigas.apps.systemstrategy.energigas.entities.Rol;
import energigas.apps.systemstrategy.energigas.utils.Constants;

/**
 * Created by kelvi on 09/08/2016.
 */

public class AcRol {

    public static final String _id = "_id";
    public static final String Id = "Id";
    public static final String Nombre = "UsuVUsuario";
    public static final String Estado = "UsuVPassword";
    public static final String ParentId = "UsuBEstado";
   public static final String TAG = "AcUsuario";

    private DbHelper mDbHelper;
    private SQLiteDatabase mDb;

    private static final String SQLITE_TABLA_ROL = "AC_ROL";
    private final Context mCtx;

    public static final String CREATE_TABLA_ROL=
            "create table "+ SQLITE_TABLA_ROL +" ("
                    +_id+" integer primary key autoincrement,"
                    +Id+" integer,"
                    +Nombre+" text,"
                    +Estado+" text,"
                    +ParentId+" integer,"
                    + Constants._CLMEXPORT+" integer );";

    public static final String DELETE_TABLA_ROL= "DROP TABLE IF EXISTS " + SQLITE_TABLA_ROL;
    public AcRol(Context ctx) {
        this.mCtx = ctx;
    }

    public AcRol open()  {
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
