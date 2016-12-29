package energigas.apps.systemstrategy.energigas.entities;

import android.app.AlarmManager;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kelvi on 10/08/2016.
 */

public class Almacen extends SugarRecord {
    @Unique
    private int almId;

    private String nombre;

    private int productoId;

    private double capacidadNeta;

    private double politica;

    private double capacidadReal;

    private double stockMinimo;

    private double stockActual;

    private double stockPermanente;

    private int usuarioCreacion;

    private String fechaCreacion;

    private int usuarioActualizacion;

    private String fechaActualizacion;

    private int establecimientoId;

    private int vehiculoId;

    private boolean estado;

    private double politicaSP;

    private String placa;

    private boolean rigido;

    public Almacen() {
    }

    public Almacen(int almId, String nombre, int productoId, double capacidadNeta, double politica, double capacidadReal, double stockMinimo, double stockActual, double stockPermanente, int usuarioCreacion, String fechaCreacion, int usuarioActualizacion, String fechaActualizacion, int establecimientoId, int vehiculoId, boolean estado, double politicaSP, String placa, boolean rigido) {
        this.almId = almId;
        this.nombre = nombre;
        this.productoId = productoId;
        this.capacidadNeta = capacidadNeta;
        this.politica = politica;
        this.capacidadReal = capacidadReal;
        this.stockMinimo = stockMinimo;
        this.stockActual = stockActual;
        this.stockPermanente = stockPermanente;
        this.usuarioCreacion = usuarioCreacion;
        this.fechaCreacion = fechaCreacion;
        this.usuarioActualizacion = usuarioActualizacion;
        this.fechaActualizacion = fechaActualizacion;
        this.establecimientoId = establecimientoId;
        this.vehiculoId = vehiculoId;
        this.estado = estado;
        this.politicaSP = politicaSP;
        this.placa = placa;
        this.rigido = rigido;
    }

    public int getAlmId() {
        return almId;
    }

    public void setAlmId(int almId) {
        this.almId = almId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getProductoId() {
        return productoId;
    }

    public void setProductoId(int productoId) {
        this.productoId = productoId;
    }

    public double getCapacidadNeta() {
        return capacidadNeta;
    }

    public void setCapacidadNeta(double capacidadNeta) {
        this.capacidadNeta = capacidadNeta;
    }

    public double getPolitica() {
        return politica;
    }

    public void setPolitica(double politica) {
        this.politica = politica;
    }

    public double getCapacidadReal() {
        return capacidadReal;
    }

    public void setCapacidadReal(double capacidadReal) {
        this.capacidadReal = capacidadReal;
    }

    public double getStockMinimo() {
        return stockMinimo;
    }

    public void setStockMinimo(double stockMinimo) {
        this.stockMinimo = stockMinimo;
    }

    public double getStockActual() {
        return stockActual;
    }

    public void setStockActual(double stockActual) {
        this.stockActual = stockActual;
    }

    public double getStockPermanente() {
        return stockPermanente;
    }

    public void setStockPermanente(double stockPermanente) {
        this.stockPermanente = stockPermanente;
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

    public int getEstablecimientoId() {
        return establecimientoId;
    }

    public void setEstablecimientoId(int establecimientoId) {
        this.establecimientoId = establecimientoId;
    }

    public int getVehiculoId() {
        return vehiculoId;
    }

    public void setVehiculoId(int vehiculoId) {
        this.vehiculoId = vehiculoId;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public double getPoliticaSP() {
        return politicaSP;
    }

    public void setPoliticaSP(double politicaSP) {
        this.politicaSP = politicaSP;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public boolean isRigido() {
        return rigido;
    }

    public void setRigido(boolean rigido) {
        this.rigido = rigido;
    }

    public static List<Almacen> getList() {
        List<Almacen> list = new ArrayList<>();
        list.add(new Almacen());
        list.add(new Almacen());
        list.add(new Almacen());
        return list;
    }

    public static List<Almacen> getListTanque(String idEstablecimiento) {
        List<Almacen> list = Almacen.find(Almacen.class, " establecimiento_Id = ? ", new String[]{idEstablecimiento});
        return list;
    }

    public static Almacen getAlmacenByUser(String usuarioId) {
        VehiculoUsuario vehiculoUsuario = VehiculoUsuario.getVehiculoUsuario(usuarioId);
        Almacen almacen = null;
        if (vehiculoUsuario != null) {
            almacen = Almacen.find(Almacen.class, "vehiculo_Id=?", new String[]{vehiculoUsuario.getVeId() + ""}).get(0);
            return almacen;
        }
        return null;
    }

    public static Almacen getAlmacenById(String almacenId) {
        List<Almacen> almacenList = Almacen.find(Almacen.class, "alm_Id=?", new String[]{almacenId});
        if (almacenList.size() > 0) {
            return almacenList.get(0);
        }
        return null;
    }


}
