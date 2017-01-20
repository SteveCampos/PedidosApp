package energigas.apps.systemstrategy.energigas.entities;

import android.util.Log;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

import java.util.List;

/**
 * Created by kelvi on 10/08/2016.
 */

public class Unidad extends SugarRecord {
    @Unique
    private int unId;

    private String descripcion;

    private String abreviatura;

    private boolean estado;

    public Unidad() {
    }

    public Unidad(int unId, String descripcion, String abreviatura, boolean estado) {
        this.unId = unId;
        this.descripcion = descripcion;
        this.abreviatura = abreviatura;
        this.estado = estado;
    }

    public int getUnId() {
        return unId;
    }

    public void setUnId(int unId) {
        this.unId = unId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public static List<Unidad> getAllUnidad() {
        return Unidad.find(Unidad.class, "estado = ?", new String[]{"1"});
    }

    public static Unidad getUnidadProducto(String productoId) {

        ProductoUnidad productoUnidad = ProductoUnidad.find(ProductoUnidad.class, "pro_Id=?", new String[]{productoId}).get(0);
        return Unidad.find(Unidad.class, "un_Id = ?", new String[]{productoUnidad.getUnId() + ""}).get(0);
    }

    public static Unidad getUnidadProductobyUnidadMedidaId(String unidadMedidaId) {
        List<Unidad> unidadList = Unidad.find(Unidad.class, " un_Id = ? ", new String[]{unidadMedidaId});
        Log.d("UNIDAD_MEDIDA_ERROR", unidadList.size() + "");
        if (unidadList.size() > 0) {
            return unidadList.get(0);
        }

        return null;
    }
}
