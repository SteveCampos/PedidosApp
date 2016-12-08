package energigas.apps.systemstrategy.energigas.entities;

import com.orm.SugarRecord;
import com.orm.dsl.Ignore;
import com.orm.dsl.Unique;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kelvi on 10/08/2016.
 */

public class InformeGasto extends SugarRecord {
    @Unique
    private long infGasId;

    private int tipoGastoId;

    private long cajGasId;

    private int usuarioAccion;

    private String fechaAccion;

    private String referencia;

    private int catTipoGastoId;

    private int estadoId;




    public InformeGasto() {
    }

    public int getEstadoId() {
        return estadoId;
    }

    public void setEstadoId(int estadoId) {
        this.estadoId = estadoId;
    }

    public InformeGasto(long infGasId, int tipoGastoId, long cajGasId, int usuarioAccion, String fechaAccion, String referencia, int catTipoGastoId, int estadoId) {
        this.infGasId = infGasId;
        this.tipoGastoId = tipoGastoId;
        this.cajGasId = cajGasId;
        this.usuarioAccion = usuarioAccion;
        this.fechaAccion = fechaAccion;
        this.referencia = referencia;
        this.catTipoGastoId = catTipoGastoId;
        this.estadoId = estadoId;
    }



    public long getInfGasId() {
        return infGasId;
    }

    public void setInfGasId(long infGasId) {
        this.infGasId = infGasId;
    }

    public int getTipoGastoId() {
        return tipoGastoId;
    }

    public void setTipoGastoId(int tipoGastoId) {
        this.tipoGastoId = tipoGastoId;
    }

    public long getCajGasId() {
        return cajGasId;
    }

    public void setCajGasId(long cajGasId) {
        this.cajGasId = cajGasId;
    }

    public int getUsuarioAccion() {
        return usuarioAccion;
    }

    public void setUsuarioAccion(int usuarioAccion) {
        this.usuarioAccion = usuarioAccion;
    }

    public String getFechaAccion() {
        return fechaAccion;
    }

    public void setFechaAccion(String fechaAccion) {
        this.fechaAccion = fechaAccion;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public int getCatTipoGastoId() {
        return catTipoGastoId;
    }

    public void setCatTipoGastoId(int catTipoGastoId) {
        this.catTipoGastoId = catTipoGastoId;
    }


    public static InformeGasto getInformeGasto(String id) {

        if (InformeGasto.find(InformeGasto.class, "", new String[]{id}).size() > 0) {
            return InformeGasto.find(InformeGasto.class, "", new String[]{id}).get(0);
        }
        return null;
    }


    public static InformeGasto getInformeGastoByCajGasto(String cajaGastoid) {

        if (InformeGasto.find(InformeGasto.class, "caj_Gas_Id=?", new String[]{cajaGastoid}).size() > 0) {
            return InformeGasto.find(InformeGasto.class, "caj_Gas_Id=?", new String[]{cajaGastoid}).get(0);
        }
        return null;
    }


}
