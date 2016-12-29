package energigas.apps.systemstrategy.energigas.entities;

import android.util.Log;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

import java.util.List;

import energigas.apps.systemstrategy.energigas.utils.Constants;

/**
 * Created by kelvi on 10/08/2016.
 */

public class Producto extends SugarRecord {
    @Unique
    private int proId;

    private String nombre;

    private String descripcion;

    private boolean estado;

    private String codigo;

    private int empresaId;

    private int usuarioCreacion;

    private String fechaCreacion;

    private int usuarioActualizacion;

    private String fechaActualizacion;

    public Producto() {
    }

    public Producto(int proId, String nombre, String descripcion, boolean estado, String codigo, int empresaId, int usuarioCreacion, String fechaCreacion, int usuarioActualizacion, String fechaActualizacion) {
        this.proId = proId;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
        this.codigo = codigo;
        this.empresaId = empresaId;
        this.usuarioCreacion = usuarioCreacion;
        this.fechaCreacion = fechaCreacion;
        this.usuarioActualizacion = usuarioActualizacion;
        this.fechaActualizacion = fechaActualizacion;
    }

    public int getProId() {
        return proId;
    }

    public void setProId(int proId) {
        this.proId = proId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getEmpresaId() {
        return empresaId;
    }

    public void setEmpresaId(int empresaId) {
        this.empresaId = empresaId;
    }

    public int getUsuarioCreacion() {
        return usuarioCreacion;
    }

    public void setUsuarioCreacion(int usuarioCreacion) {
        this.usuarioCreacion = usuarioCreacion;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public int getUsuarioActualizacion() {
        return usuarioActualizacion;
    }

    public void setUsuarioActualizacion(int usuarioActualizacion) {
        this.usuarioActualizacion = usuarioActualizacion;
    }

    public String getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(String fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public static List<Producto> getAllProducto() {
        return Producto.listAll(Producto.class);
    }

    public static Producto getProductoById(String productoId) {
        List<Producto> producto = Producto.find(Producto.class, "pro_Id=?", new String[]{productoId});
        if (producto.size() > 0) {
            return producto.get(0);
        }
        return null;
    }

    public static String getNameProducto(String productoId) {
        return Producto.find(Producto.class, "pro_Id=?", new String[]{productoId}).get(0).getNombre();
    }
}
