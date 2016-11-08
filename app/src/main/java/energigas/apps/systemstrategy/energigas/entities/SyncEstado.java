package energigas.apps.systemstrategy.energigas.entities;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

/**
 * Created by kelvi on 29/09/2016.
 */

public class SyncEstado extends SugarRecord {

    private int syncIdRemoto;
    private String nombreTabla;
    private int campoId;
    private int estadoSync;

    public SyncEstado() {
    }
    public SyncEstado(int syncIdRemoto, String nombreTabla, int campoId, int estadoSync) {
        this.syncIdRemoto = syncIdRemoto;
        this.nombreTabla = nombreTabla;
        this.campoId = campoId;
        this.estadoSync = estadoSync;
    }

    public int getSyncIdRemoto() {
        return syncIdRemoto;
    }

    public void setSyncIdRemoto(int syncIdRemoto) {
        this.syncIdRemoto = syncIdRemoto;
    }

    public String getNombreTabla() {
        return nombreTabla;
    }

    public void setNombreTabla(String nombreTabla) {
        this.nombreTabla = nombreTabla;
    }

    public int getCampoId() {
        return campoId;
    }

    public void setCampoId(int campoId) {
        this.campoId = campoId;
    }

    public int isEstadoSync() {
        return estadoSync;
    }

    public void setEstadoSync(int estadoSync) {
        this.estadoSync = estadoSync;
    }
}
