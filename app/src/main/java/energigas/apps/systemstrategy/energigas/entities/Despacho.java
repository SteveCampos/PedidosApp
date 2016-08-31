package energigas.apps.systemstrategy.energigas.entities;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kelvi on 10/08/2016.
 */

public class Despacho extends SugarRecord{

    @Unique
    private long despachoId;

    private long peId;

    private int pdId;

    private int clienteId;

    private int establecimientoId;

    private int almacenEstId;

    private int usuarioId;

    private String placa;

    private double contadorInicial;

    private double contadorFinal;

    private double cantidadDespachada;

    private String horaInicio;

    private String horaFin;

    private String fechaDespacho;

    private int proId;

    private int unId;

    private double pIT;

    private double pFT;

    private String latitud;

    private String longitud;

    private int almacenVehId;

    private String serie;

    private double numero;

    private String fechaCreacion;

    private String usuarioCreacion;

    private int estadoId;

    private int vehiculoId;

    private String guiaRemision;

    public Despacho() {
    }

    public Despacho(long despachoId, long peId, int pdId, int clienteId, int establecimientoId, int almacenEstId, int usuarioId, String placa, double contadorInicial, double contadorFinal, double cantidadDespachada, String horaInicio, String horaFin, String fechaDespacho, int proId, int unId, double pIT, double pFT, String latitud, String longitud, int almacenVehId, String serie, double numero, String fechaCreacion, String usuarioCreacion, int estadoId, int vehiculoId,String guiaRemision) {
        this.despachoId = despachoId;
        this.peId = peId;
        this.pdId = pdId;
        this.clienteId = clienteId;
        this.establecimientoId = establecimientoId;
        this.almacenEstId = almacenEstId;
        this.usuarioId = usuarioId;
        this.placa = placa;
        this.contadorInicial = contadorInicial;
        this.contadorFinal = contadorFinal;
        this.cantidadDespachada = cantidadDespachada;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.fechaDespacho = fechaDespacho;
        this.proId = proId;
        this.unId = unId;
        this.pIT = pIT;
        this.pFT = pFT;
        this.latitud = latitud;
        this.longitud = longitud;
        this.almacenVehId = almacenVehId;
        this.serie = serie;
        this.numero = numero;
        this.fechaCreacion = fechaCreacion;
        this.usuarioCreacion = usuarioCreacion;
        this.estadoId = estadoId;
        this.vehiculoId = vehiculoId;
        this.guiaRemision = guiaRemision;
    }

    public String getGuiaRemision() {
        return guiaRemision;
    }

    public void setGuiaRemision(String guiaRemision) {
        this.guiaRemision = guiaRemision;
    }

    public long getDespachoId() {
        return despachoId;
    }

    public void setDespachoId(long despachoId) {
        this.despachoId = despachoId;
    }

    public long getPeId() {
        return peId;
    }

    public void setPeId(long peId) {
        this.peId = peId;
    }

    public int getPdId() {
        return pdId;
    }

    public void setPdId(int pdId) {
        this.pdId = pdId;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public int getEstablecimientoId() {
        return establecimientoId;
    }

    public void setEstablecimientoId(int establecimientoId) {
        this.establecimientoId = establecimientoId;
    }

    public int getAlmacenEstId() {
        return almacenEstId;
    }

    public void setAlmacenEstId(int almacenEstId) {
        this.almacenEstId = almacenEstId;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public double getContadorInicial() {
        return contadorInicial;
    }

    public void setContadorInicial(double contadorInicial) {
        this.contadorInicial = contadorInicial;
    }

    public double getContadorFinal() {
        return contadorFinal;
    }

    public void setContadorFinal(double contadorFinal) {
        this.contadorFinal = contadorFinal;
    }

    public double getCantidadDespachada() {
        return cantidadDespachada;
    }

    public void setCantidadDespachada(double cantidadDespachada) {
        this.cantidadDespachada = cantidadDespachada;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public String getFechaDespacho() {
        return fechaDespacho;
    }

    public void setFechaDespacho(String fechaDespacho) {
        this.fechaDespacho = fechaDespacho;
    }

    public int getProId() {
        return proId;
    }

    public void setProId(int proId) {
        this.proId = proId;
    }

    public int getUnId() {
        return unId;
    }

    public void setUnId(int unId) {
        this.unId = unId;
    }

    public double getpIT() {
        return pIT;
    }

    public void setpIT(double pIT) {
        this.pIT = pIT;
    }

    public double getpFT() {
        return pFT;
    }

    public void setpFT(double pFT) {
        this.pFT = pFT;
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

    public int getAlmacenVehId() {
        return almacenVehId;
    }

    public void setAlmacenVehId(int almacenVehId) {
        this.almacenVehId = almacenVehId;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public double getNumero() {
        return numero;
    }

    public void setNumero(double numero) {
        this.numero = numero;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getUsuarioCreacion() {
        return usuarioCreacion;
    }

    public void setUsuarioCreacion(String usuarioCreacion) {
        this.usuarioCreacion = usuarioCreacion;
    }

    public int getEstadoId() {
        return estadoId;
    }

    public void setEstadoId(int estadoId) {
        this.estadoId = estadoId;
    }

    public int getVehiculoId() {
        return vehiculoId;
    }

    public void setVehiculoId(int vehiculoId) {
        this.vehiculoId = vehiculoId;
    }

    public static List<Despacho> getList() {
        List<Despacho> list = new ArrayList<>();
        list.add(new Despacho(
                1, 1, 1, 1, 1, 1, 1, "PLACA", 500.0, 1300.0, 800.0, "12:00", "12:00", "12/08/2016", 1, 1, 12.5, 65.0, "-11.656565", "-51.82525", 1,
                "SERIE", 0.0, "12/08/2016", "", 1, 1,""
        ));
        return list;
    }
}
