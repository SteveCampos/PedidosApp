package energigas.apps.systemstrategy.energigas.entities;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

/**
 * Created by kelvi on 10/08/2016.
 */

public class VehiculoDispositivo extends SugarRecord {
    @Unique
    private int vehiculoDispositivoId;

    private int veId;

    private int dmId;

    private boolean estado;

    private String latitud;

    private String longitud;


    public VehiculoDispositivo() {
    }

    public VehiculoDispositivo(int vehiculoDispositivoId, int veId, int dmId, boolean estado, String latitud, String longitud) {
        this.vehiculoDispositivoId = vehiculoDispositivoId;
        this.veId = veId;
        this.dmId = dmId;
        this.estado = estado;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public int getVehiculoDispositivoId() {
        return vehiculoDispositivoId;
    }

    public void setVehiculoDispositivoId(int vehiculoDispositivoId) {
        this.vehiculoDispositivoId = vehiculoDispositivoId;
    }

    public int getVeId() {
        return veId;
    }

    public void setVeId(int veId) {
        this.veId = veId;
    }

    public int getDmId() {
        return dmId;
    }

    public void setDmId(int dmId) {
        this.dmId = dmId;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }
}
