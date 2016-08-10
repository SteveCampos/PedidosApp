package energigas.apps.systemstrategy.energigas.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import energigas.apps.systemstrategy.energigas.entities.Acceso;
import energigas.apps.systemstrategy.energigas.utils.Constants;

/**
 * Created by kelvi on 09/08/2016.
 */

public class AcAcceso {

    public static final String _id = "_id";
    public static final String Id = "Id";
    public static final String ParentId = "UsuVUsuario";
    public static final String Abreviacion = "UsuVPassword";
    public static final String Descripcion = "UsuBEstado";
    public static final String Item = "UsuBEstado";
    public static final String Nivel = "UsuBEstado";
    public static final String URL = "UsuBEstado";
    public static final String Estado = "UsuBEstado";
    public static final String Movil = "UsuBEstado";
   public static final String TAG = "AcUsuario";

    private DbHelper mDbHelper;
    private SQLiteDatabase mDb;

    private static final String SQLITE_TABLA_ACCESO = "AC_ROL";
    private final Context mCtx;

    public static final String CREATE_TABLA_ACCESO=
            "create table "+ SQLITE_TABLA_ACCESO +" ("
                    +_id+" integer primary key autoincrement,"
                    +Id+" integer,"
                    +ParentId+" integer,"
                    +Abreviacion+" text,"
                    +Descripcion+" text,"
                    +Item+" text,"
                    +Nivel+" text,"
                    +URL+" text,"
                    +Estado+" text,"
                    +Movil+" text,"
                    + Constants._CLMEXPORT+" integer );";

    public static final String DELETE_TABLA_ACCESO= "DROP TABLE IF EXISTS " + SQLITE_TABLA_ACCESO;
    public AcAcceso(Context ctx) {
        this.mCtx = ctx;
    }

    public AcAcceso open()  {
        mDbHelper = new DbHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this ;
    }
    public void close() {
        if (mDbHelper != null) {
            mDbHelper.close();
        }
    }

    public long createAcceso(@NonNull Acceso acceso){

        ContentValues initialValues = new ContentValues();
        initialValues.put(Id,acceso.getId());
        initialValues.put(ParentId,acceso.getParentId());
        initialValues.put(Abreviacion,acceso.getAbreviacion());
        initialValues.put(Descripcion,acceso.getDescripcion());
        initialValues.put(Item,acceso.getItem());
        initialValues.put(Nivel,acceso.getNivel());
        initialValues.put(URL,acceso.getUrl());
        initialValues.put(Estado,acceso.getEstado());
        initialValues.put(Movil,acceso.getMovil());
        initialValues.put(Constants._CLMEXPORT, Constants._CREADO);

        return mDb.insert(SQLITE_TABLA_ACCESO, null, initialValues);
    }

    public int updateAcceso(@NonNull Acceso acceso){
        ContentValues initialValues = new ContentValues();
        initialValues.put(ParentId,acceso.getParentId());
        initialValues.put(Abreviacion,acceso.getAbreviacion());
        initialValues.put(Descripcion,acceso.getDescripcion());
        initialValues.put(Item,acceso.getItem());
        initialValues.put(Nivel,acceso.getNivel());
        initialValues.put(URL,acceso.getUrl());
        initialValues.put(Estado,acceso.getEstado());
        initialValues.put(Movil,acceso.getMovil());
        initialValues.put(Constants._CLMEXPORT, Constants._CREADO);

        return mDb.update(SQLITE_TABLA_ACCESO, initialValues,
                Id + "=?", new String[]{"" + acceso.getId()});

    }

    public boolean deleteAccesoById(@NonNull Acceso acceso) {

        int doneDelete = mDb.delete(SQLITE_TABLA_ACCESO, Id+"=?", new String[]{acceso.getId()+""});
        return doneDelete > 0;

    }


}
