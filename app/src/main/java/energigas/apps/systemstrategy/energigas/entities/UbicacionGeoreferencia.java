package energigas.apps.systemstrategy.energigas.entities;

import android.util.Log;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

import java.util.List;

/**
 * Created by kelvi on 10/08/2016.
 */

public class UbicacionGeoreferencia extends SugarRecord {
    @Unique
    private int idUbicacionGeoRef;

    private String codigo;

    private String descripcion;

    private int parentId;

    private int tipoId;

    public UbicacionGeoreferencia() {
    }

    public UbicacionGeoreferencia(int idUbicacionGeoRef, String codigo, String descripcion, int parentId, int tipoId) {
        this.idUbicacionGeoRef = idUbicacionGeoRef;
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.parentId = parentId;
        this.tipoId = tipoId;
    }

    public int getIdUbicacionGeoRef() {
        return idUbicacionGeoRef;
    }

    public void setIdUbicacionGeoRef(int idUbicacionGeoRef) {
        this.idUbicacionGeoRef = idUbicacionGeoRef;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public int getTipoId() {
        return tipoId;
    }

    public void setTipoId(int tipoId) {
        this.tipoId = tipoId;
    }

    public static UbicacionGeoreferencia getUbicacionGeoreferencia(String ubicacionGeoreferenciadaId) {
        List<UbicacionGeoreferencia> ubicacionGeoreferencias = UbicacionGeoreferencia.find(UbicacionGeoreferencia.class, "id_Ubicacion_Geo_Ref=?", new String[]{ubicacionGeoreferenciadaId});


        for (UbicacionGeoreferencia georeferencia : ubicacionGeoreferencias) {
            Log.d("UbicacionGeoreferencia", "" + georeferencia.getDescripcion() + "-" + georeferencia.getCodigo());
            if (ubicacionGeoreferenciadaId.equals(georeferencia.getIdUbicacionGeoRef())) {
                Log.d("UbicacionGeoreferencia", "ENCONTRADA: " + georeferencia.getDescripcion() + "-" + georeferencia.getCodigo());
            }
        }

        if (ubicacionGeoreferencias.size() > 0) {
            return ubicacionGeoreferencias.get(0);
        }
        return null;
    }
}
