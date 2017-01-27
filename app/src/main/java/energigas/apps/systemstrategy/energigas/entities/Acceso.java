package energigas.apps.systemstrategy.energigas.entities;


import android.util.Log;

import com.orm.SugarRecord;
import com.orm.dsl.Ignore;
import com.orm.dsl.Unique;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kelvi on 09/08/2016.
 */

public class Acceso extends SugarRecord {
    @Unique
    private int idAcceso;

    private int parentId;

    private String abreviacion;

    private String descripcion;

    private int item;

    private int nivel;

    private String uRL;

    private boolean estado;

    private String fechaCreacion;

    private String usuario;

    private String icono;

    private String fechaActualizacion;

    private String usuarioActualizacion;

    private boolean movil;




    @Ignore
    private List<Privilegio> itemsPrivielgios;


    public Acceso() {
    }

    public Acceso(int idAcceso, int parentId, String abreviacion, String descripcion, int item, int nivel, String uRL, boolean estado, String fechaCreacion, String usuario, String icono, String fechaActualizacion, String usuarioActualizacion, boolean movil, List<Privilegio> itemsPrivielgios, int viewAndroidId) {
        this.idAcceso = idAcceso;
        this.parentId = parentId;
        this.abreviacion = abreviacion;
        this.descripcion = descripcion;
        this.item = item;
        this.nivel = nivel;
        this.uRL = uRL;
        this.estado = estado;
        this.fechaCreacion = fechaCreacion;
        this.usuario = usuario;
        this.icono = icono;
        this.fechaActualizacion = fechaActualizacion;
        this.usuarioActualizacion = usuarioActualizacion;
        this.movil = movil;
        this.itemsPrivielgios = itemsPrivielgios;
    }

    public int getIdAcceso() {
        return idAcceso;
    }

    public void setIdAcceso(int idAcceso) {
        this.idAcceso = idAcceso;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getAbreviacion() {
        return abreviacion;
    }

    public void setAbreviacion(String abreviacion) {
        this.abreviacion = abreviacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getItem() {
        return item;
    }

    public void setItem(int item) {
        this.item = item;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public String getuRL() {
        return uRL;
    }

    public void setuRL(String uRL) {
        this.uRL = uRL;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }

    public String getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(String fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public String getUsuarioActualizacion() {
        return usuarioActualizacion;
    }

    public void setUsuarioActualizacion(String usuarioActualizacion) {
        this.usuarioActualizacion = usuarioActualizacion;
    }

    public boolean isMovil() {
        return movil;
    }

    public void setMovil(boolean movil) {
        this.movil = movil;
    }

    public List<Privilegio> getItemsPrivielgios() {
        return itemsPrivielgios;
    }

    public void setItemsPrivielgios(List<Privilegio> itemsPrivielgios) {
        this.itemsPrivielgios = itemsPrivielgios;
    }

    public static List<Acceso> getAccesos(String rolId) {
        List<RolAcceso> rolAccesos = RolAcceso.getRolAcceso(rolId);
        List<Acceso> accesos = new ArrayList<>();

        for (RolAcceso rolAcceso : rolAccesos) {
            accesos.add(Acceso.getAccesoById(rolAcceso.getAccesoId() + ""));
        }
        return accesos;
    }

    public static List<Acceso> getAccesosFor(String rolId, String nombreActivity) {

        List<RolAcceso> rolAccesos = RolAcceso.getRolAcceso(rolId);
        List<Acceso> accesoList = new ArrayList<>();
        for (RolAcceso rolAcceso : rolAccesos) {
            Acceso acceso = Acceso.getAccesoByIdANameActivity(rolAcceso.getAccesoId() + "", nombreActivity);
            if (acceso !=null){
                accesoList.add(acceso);
            }

        }
        return accesoList;
    }

    public static Acceso getAccesoByIdANameActivity(String id, String nombre) {
        List<Acceso> accesoList = Acceso.find(Acceso.class, "id_Acceso=? and u_RL=?", new String[]{id, nombre});

        Log.d("MainActivity", "ACCESO: " +accesoList.size());
        if (accesoList.size() > 0) {
            return accesoList.get(0);
        }
        return null;
    }

    public static Acceso getAccesoById(String id) {

        List<Acceso> accesoList = Acceso.find(Acceso.class, "id_Acceso=? and abreviacion=?", new String[]{id});
        if (accesoList.size() > 0) {
            return accesoList.get(0);

        }
        return null;
    }
}
