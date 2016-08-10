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

    public long createAcceso(@NonNull RolAcceso  rolAcceso){

        ContentValues initialValues = new ContentValues();
        initialValues.put(AccesoId,rolAcceso.getAccesoId());
        initialValues.put(RolId,rolAcceso.getRolId());
        initialValues.put(AccesoDefault,rolAcceso.getAccesoDefault());
        initialValues.put(Constants._CLMEXPORT, Constants._CREADO);

        return mDb.insert(SQLITE_TABLA_ROL_ACCESO, null, initialValues);
    }

    public int updateAcceso(@NonNull RolAcceso rolAcceso){
        ContentValues initialValues = new ContentValues();
        initialValues.put(AccesoDefault,rolAcceso.getAccesoDefault());
        initialValues.put(Constants._CLMEXPORT, Constants._CREADO);

        return mDb.update(SQLITE_TABLA_ROL_ACCESO, initialValues,
                AccesoId + "=? and "+RolId+" =?", new String[]{"" + rolAcceso.getAccesoId(),rolAcceso.getRolId()+""});

    }

    public boolean deleteAccesoById(@NonNull RolAcceso rolAcceso) {

        int doneDelete = mDb.delete(SQLITE_TABLA_ROL_ACCESO,   AccesoId + "=? and "+RolId+" =?", new String[]{"" + rolAcceso.getAccesoId(),rolAcceso.getRolId()+""});
        return doneDelete > 0;

    }


}
