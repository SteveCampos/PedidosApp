package energigas.apps.systemstrategy.energigas.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import energigas.apps.systemstrategy.energigas.entities.Acceso;
import energigas.apps.systemstrategy.energigas.entities.Privilegio;
import energigas.apps.systemstrategy.energigas.utils.Constants;

/**
 * Created by kelvi on 09/08/2016.
 */

public class AcProvilegio {

    public static final String _id = "_id";
    public static final String Id = "Id";
    public static final String AccesoId = "UsuVUsuario";
    public static final String Nombre = "UsuVPassword";
    public static final String Descripcion = "UsuBEstado";
    public static final String Estado = "UsuBEstado";
    public static final String TAG = "AcUsuario";

    private DbHelper mDbHelper;
    private SQLiteDatabase mDb;

    private static final String SQLITE_TABLA_PRIVILEGIO = "AC_ROL";
    private final Context mCtx;

    public static final String CREATE_TABLA_PRIVILEGIO =
            "create table " + SQLITE_TABLA_PRIVILEGIO + " ("
                    + _id + " integer primary key autoincrement,"
                    + Id + " integer,"
                    + AccesoId + " integer,"
                    + Nombre + " text,"
                    + Descripcion + " text,"
                    + Estado + " text,"
                    + Constants._CLMEXPORT + " integer );";

    public static final String DELETE_TABLA_PRIVILEGIO = "DROP TABLE IF EXISTS " + SQLITE_TABLA_PRIVILEGIO;

    public AcProvilegio(Context ctx) {
        this.mCtx = ctx;
    }

    public AcProvilegio open() {
        mDbHelper = new DbHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        if (mDbHelper != null) {
            mDbHelper.close();
        }
    }

}
