package energigas.apps.systemstrategy.energigas.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by kelvi on 09/08/2016.
 */

public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 3;
    private static final String DATABASE_NAME = "Energigas.sqlite";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        /***Tablas de acceso y privilegios**/
        sqLiteDatabase.execSQL(DB_Persona.CREATE_TABLA_DB_PERSONA);
        sqLiteDatabase.execSQL(DB_Usuario.CREATE_TABLA_DB_USUARIO);
        sqLiteDatabase.execSQL(DB_Rol.CREATE_TABLA_DB_ROL);
        sqLiteDatabase.execSQL(DB_Acceso.CREATE_TABLA_DB_ACCESO);
        sqLiteDatabase.execSQL(DB_Privilegio.CREATE_TABLA_DB_PRIVILEGIO);
        sqLiteDatabase.execSQL(DB_RolAcceso.CREATE_TABLA_DB_ROLACCESO);
        sqLiteDatabase.execSQL(DB_RolUsuario.CREATE_TABLA_DB_ROLUSUARIO);
        sqLiteDatabase.execSQL(DB_RolPrivilegio.CREATE_TABLA_DB_ROLPRIVILEGIO);


        /**Tablas de concepto**/
        sqLiteDatabase.execSQL(DB_Concepto.CREATE_TABLA_DB_CONCEPTO);
        sqLiteDatabase.execSQL(DB_Estado.CREATE_TABLA_DB_ESTADO);
        sqLiteDatabase.execSQL(DB_UbicacionGeoreferencia.CREATE_TABLA_DB_UBICACIONGEOREFERENCIA);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        /***Tablas de acceso y privilegios**/
        sqLiteDatabase.execSQL(DB_Persona.DELETE_TABLA_DB_PERSONA);
        sqLiteDatabase.execSQL(DB_Usuario.DELETE_TABLA_DB_USUARIO);
        sqLiteDatabase.execSQL(DB_Rol.DELETE_TABLA_DB_ROL);
        sqLiteDatabase.execSQL(DB_Acceso.DELETE_TABLA_DB_ACCESO);
        sqLiteDatabase.execSQL(DB_Privilegio.DELETE_TABLA_DB_PRIVILEGIO);
        sqLiteDatabase.execSQL(DB_RolAcceso.DELETE_TABLA_DB_ROLACCESO);
        sqLiteDatabase.execSQL(DB_RolUsuario.DELETE_TABLA_DB_ROLUSUARIO);
        sqLiteDatabase.execSQL(DB_RolPrivilegio.DELETE_TABLA_DB_ROLPRIVILEGIO);


        /**Tablas de concepto**/
        sqLiteDatabase.execSQL(DB_Concepto.DELETE_TABLA_DB_CONCEPTO);
        sqLiteDatabase.execSQL(DB_Estado.DELETE_TABLA_DB_ESTADO);
        sqLiteDatabase.execSQL(DB_UbicacionGeoreferencia.DELETE_TABLA_DB_UBICACIONGEOREFERENCIA);

        onCreate(sqLiteDatabase);
    }
}
