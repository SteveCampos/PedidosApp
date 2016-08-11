package energigas.apps.systemstrategy.energigas.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import energigas.apps.systemstrategy.energigas.entities.Usuario;
import energigas.apps.systemstrategy.energigas.utils.Constants;

/**
 * Created by kelvi on 09/08/2016.
 */

public class AcUsuario {

    public static final String _id = "_id";
    public static final String UsuIUsuarioId = "UsuIUsuarioId";
    public static final String UsuVUsuario = "UsuVUsuario";
    public static final String UsuVPassword = "UsuVPassword";
    public static final String UsuBEstado = "UsuBEstado";
    public static final String UsuIPersonaId = "UsuIPersonaId";
   public static final String TAG = "AcUsuario";

    private DbHelper mDbHelper;
    private SQLiteDatabase mDb;

    private static final String SQLITE_TABLA_USUARIO= "AC_USUARIO";
    private final Context mCtx;

    public static final String CREATE_TABLA_USUARIO=
            "create table "+SQLITE_TABLA_USUARIO+" ("
                    +_id+" integer primary key autoincrement,"
                    +UsuIUsuarioId+" integer,"
                    +UsuVUsuario+" text,"
                    +UsuVPassword+" text,"
                    +UsuBEstado+" integer,"
                    +UsuIPersonaId+" integer,"
                    + Constants._CLMEXPORT+" integer );";

    public static final String DELETE_TABLA_USUARIO= "DROP TABLE IF EXISTS " + SQLITE_TABLA_USUARIO;
    public AcUsuario(Context ctx) {
        this.mCtx = ctx;
    }

    public AcUsuario open()  {
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
