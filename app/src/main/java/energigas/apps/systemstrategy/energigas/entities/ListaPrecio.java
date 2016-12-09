package energigas.apps.systemstrategy.energigas.entities;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

import energigas.apps.systemstrategy.energigas.utils.Utils;

/**
 * Created by kelvi on 10/08/2016.
 */

public class ListaPrecio extends SugarRecord {
    @Unique
    private int lpId;

    private String nombre;

    private int proId;

    private int estadoId;

    private String fechaCaducidad;

    private String fechaCreacion;

    private String fechaActualizacion;

    private int usuarioCreacion;

    private int usuarioActualizacion;

    private int empresaId;

    private int tipoId;

    public ListaPrecio() {
    }

    public ListaPrecio(int lpId, String nombre, int proId, int estadoId, String fechaCaducidad, String fechaCreacion, String fechaActualizacion, int usuarioCreacion, int usuarioActualizacion, int empresaId, int tipoId) {
        this.lpId = lpId;
        this.nombre = nombre;
        this.proId = proId;
        this.estadoId = estadoId;
        this.fechaCaducidad = fechaCaducidad;
        this.fechaCreacion = fechaCreacion;
        this.fechaActualizacion = fechaActualizacion;
        this.usuarioCreacion = usuarioCreacion;
        this.usuarioActualizacion = usuarioActualizacion;
        this.empresaId = empresaId;
        this.tipoId = tipoId;
    }

    public int getLpId() {
        return lpId;
    }

    public void setLpId(int lpId) {
        this.lpId = lpId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getProId() {
        return proId;
    }

    public void setProId(int proId) {
        this.proId = proId;
    }

    public int getEstadoId() {
        return estadoId;
    }

    public void setEstadoId(int estadoId) {
        this.estadoId = estadoId;
    }

    public String getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(String fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(String fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public int getUsuarioCreacion() {
        return usuarioCreacion;
    }

    public void setUsuarioCreacion(int usuarioCreacion) {
        this.usuarioCreacion = usuarioCreacion;
    }

    public int getUsuarioActualizacion() {
        return usuarioActualizacion;
    }

    public void setUsuarioActualizacion(int usuarioActualizacion) {
        this.usuarioActualizacion = usuarioActualizacion;
    }

    public int getEmpresaId() {
        return empresaId;
    }

    public void setEmpresaId(int empresaId) {
        this.empresaId = empresaId;
    }

    public int getTipoId() {
        return tipoId;
    }

    public void setTipoId(int tipoId) {
        this.tipoId = tipoId;
    }

    @Override
    public String toString() {
        return nombre + "-" + empresaId;
    }

    public static ListaPrecio getPrecioByProductoId(String productoId) {

        if (ListaPrecio.find(ListaPrecio.class, " pro_Id =? and fecha_Caducidad = ?", new String[]{productoId, Utils.getDatePhoneWithTime()}).size() > 0) {
            return ListaPrecio.find(ListaPrecio.class, " pro_Id =? and fecha_Caducidad = ?", new String[]{productoId, Utils.getDatePhoneWithTime()}).get(0);
        }

        return null;
    }
}
