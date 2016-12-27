package energigas.apps.systemstrategy.energigas.entities;

import com.orm.SugarRecord;
import com.orm.dsl.Ignore;
import com.orm.dsl.Unique;

/**
 * Created by kelvi on 10/08/2016.
 */

public class Vehiculo extends SugarRecord {
    @Unique
    private int veId;

    private int tipoVehiculoId;

    private int claseId;

    private int tamanoId;

    private int tipoConductorId;

    private String modelo;

    private double capacidadPeso;

    private String placa;

    private int marcaId;

    private double costoHora;

    private double costoKm;

    private double costoSeguro;

    private double costoSOAT;

    private int usuarioCreacion;

    private String fechaCreacion;

    private int usuarioActualizacion;

    private String fechaActualizacion;

    private int empresaId;

    private int estadoId;

    @Ignore
    private Almacen almacen;

    public Vehiculo() {
    }

    public Vehiculo(int veId, int tipoVehiculoId, int claseId, int tamanoId, int tipoConductorId, String modelo, double capacidadPeso, String placa, int marcaId, double costoHora, double costoKm, double costoSeguro, double costoSOAT, int usuarioCreacion, String fechaCreacion, int usuarioActualizacion, String fechaActualizacion, int empresaId, int estadoId, Almacen almacen) {
        this.veId = veId;
        this.tipoVehiculoId = tipoVehiculoId;
        this.claseId = claseId;
        this.tamanoId = tamanoId;
        this.tipoConductorId = tipoConductorId;
        this.modelo = modelo;
        this.capacidadPeso = capacidadPeso;
        this.placa = placa;
        this.marcaId = marcaId;
        this.costoHora = costoHora;
        this.costoKm = costoKm;
        this.costoSeguro = costoSeguro;
        this.costoSOAT = costoSOAT;
        this.usuarioCreacion = usuarioCreacion;
        this.fechaCreacion = fechaCreacion;
        this.usuarioActualizacion = usuarioActualizacion;
        this.fechaActualizacion = fechaActualizacion;
        this.empresaId = empresaId;
        this.estadoId = estadoId;
        this.almacen = almacen;
    }

    public Almacen getAlmacen() {
        return almacen;
    }

    public void setAlmacen(Almacen almacen) {
        this.almacen = almacen;
    }

    public int getVeId() {
        return veId;
    }

    public void setVeId(int veId) {
        this.veId = veId;
    }

    public int getTipoVehiculoId() {
        return tipoVehiculoId;
    }

    public void setTipoVehiculoId(int tipoVehiculoId) {
        this.tipoVehiculoId = tipoVehiculoId;
    }

    public int getClaseId() {
        return claseId;
    }

    public void setClaseId(int claseId) {
        this.claseId = claseId;
    }

    public int getTamanoId() {
        return tamanoId;
    }

    public void setTamanoId(int tamanoId) {
        this.tamanoId = tamanoId;
    }

    public int getTipoConductorId() {
        return tipoConductorId;
    }

    public void setTipoConductorId(int tipoConductorId) {
        this.tipoConductorId = tipoConductorId;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public double getCapacidadPeso() {
        return capacidadPeso;
    }

    public void setCapacidadPeso(double capacidadPeso) {
        this.capacidadPeso = capacidadPeso;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public int getMarcaId() {
        return marcaId;
    }

    public void setMarcaId(int marcaId) {
        this.marcaId = marcaId;
    }

    public double getCostoHora() {
        return costoHora;
    }

    public void setCostoHora(double costoHora) {
        this.costoHora = costoHora;
    }

    public double getCostoKm() {
        return costoKm;
    }

    public void setCostoKm(double costoKm) {
        this.costoKm = costoKm;
    }

    public double getCostoSeguro() {
        return costoSeguro;
    }

    public void setCostoSeguro(double costoSeguro) {
        this.costoSeguro = costoSeguro;
    }

    public double getCostoSOAT() {
        return costoSOAT;
    }

    public void setCostoSOAT(double costoSOAT) {
        this.costoSOAT = costoSOAT;
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

    public int getEmpresaId() {
        return empresaId;
    }

    public void setEmpresaId(int empresaId) {
        this.empresaId = empresaId;
    }

    public int getEstadoId() {
        return estadoId;
    }

    public void setEstadoId(int estadoId) {
        this.estadoId = estadoId;
    }

    public static Vehiculo getVehiculo(String usuarioId) {
        boolean b = VehiculoUsuario.find(VehiculoUsuario.class, "usuario_Id=?", new String[]{usuarioId}).size() > 0;
        if (b) {
            VehiculoUsuario vehiculoUsuario = VehiculoUsuario.find(VehiculoUsuario.class, "usuario_Id=?", new String[]{usuarioId}).get(0);
            Vehiculo vehiculo = Vehiculo.find(Vehiculo.class, "ve_Id=?", new String[]{vehiculoUsuario.getVeId() + ""}).get(0);
            return vehiculo;
        } else {
            return null;
        }

    }
}
