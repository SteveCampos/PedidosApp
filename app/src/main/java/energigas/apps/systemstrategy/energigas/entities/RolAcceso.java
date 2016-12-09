package energigas.apps.systemstrategy.energigas.entities;

import android.util.Log;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

import java.util.List;

/**
 * Created by kelvi on 09/08/2016.
 */

public class RolAcceso  extends SugarRecord{
    @Unique
    private String idRolAcceso;

    private int rolId;

    private int accesoId;

    private boolean accesoDefault;

    public RolAcceso() {
    }

    public RolAcceso(String idRolAcceso, int rolId, int accesoId, boolean accesoDefault) {
        this.idRolAcceso = idRolAcceso;
        this.rolId = rolId;
        this.accesoId = accesoId;
        this.accesoDefault = accesoDefault;
    }

    public String getIdRolAcceso() {
        return idRolAcceso;
    }

    public void setIdRolAcceso(String idRolAcceso) {
        this.idRolAcceso = idRolAcceso;
    }

    public int getRolId() {
        return rolId;
    }

    public void setRolId(int rolId) {
        this.rolId = rolId;
    }

    public int getAccesoId() {
        return accesoId;
    }

    public void setAccesoId(int accesoId) {
        this.accesoId = accesoId;
    }

    public boolean isAccesoDefault() {
        return accesoDefault;
    }

    public void setAccesoDefault(boolean accesoDefault) {
        this.accesoDefault = accesoDefault;
    }

    public static List<RolAcceso> getRolAcceso(String rolId){
        Log.d("MainActivity", "getRolAcceso: " + rolId);
        List<RolAcceso> rolAccesos = RolAcceso.find(RolAcceso.class," rol_Id = ? ",new String[]{rolId});
        Log.d("MainActivity", "size: " + rolAccesos.size());
        if (rolAccesos !=null){
            return rolAccesos;
        }
        return null;
    }
}
