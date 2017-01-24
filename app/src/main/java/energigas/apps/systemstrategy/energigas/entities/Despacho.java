package energigas.apps.systemstrategy.energigas.entities;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

import java.util.List;

/**
 * Created by kelvi on 10/08/2016.
 */

public class Despacho extends SugarRecord {

    @Unique
    private long despachoId;

    private long peId;

    private int pdId;

    private int clienteId;

    private int establecimientoId;

    private int almacenEstId;

    private int usuarioId;

    private String placa;

    private double contadorInicialOrigen;

    private double contadorFinalOrigen;

    private double cantidadDespachada;

    private String horaInicio;

    private String horaFin;

    private String fechaDespacho;

    private int proId;

    private int unId;

    private double pITOrigen;

    private double pFTOrigen;

    private String latitud;

    private String longitud;

    private int almacenVehId;

    private String serie;

    private String numero;

    private String fechaCreacion;

    private int usuarioCreacion;

    private int estadoId;

    private int veId;

    private String guiaRemision;

    private long liqId;

    private double precioUnitarioSIGV;

    private double precioUnitarioCIGV;

    private double porImpuesto;

    private double costoVenta;

    private double importe;

    private double contadorInicialDestino;

    private double contadorFinalDestino;

    private double pITDestino;

    private double pFTDestino;

    private long compId;


    public Despacho() {
    }

    public Despacho(long despachoId, long peId, int pdId, int clienteId, int establecimientoId, int almacenEstId, int usuarioId, String placa, double contadorInicialOrigen, double contadorFinalOrigen, double cantidadDespachada, String horaInicio, String horaFin, String fechaDespacho, int proId, int unId, double pITOrigen, double pFTOrigen, String latitud, String longitud, int almacenVehId, String serie, String numero, String fechaCreacion, int usuarioCreacion, int estadoId, int veId, String guiaRemision, long liqId, double precioUnitarioSIGV, double precioUnitarioCIGV, double porImpuesto, double costoVenta, double importe, double contadorInicialDestino, double contadorFinalDestino, double pITDestino, double pFTDestino, long compId) {
        this.despachoId = despachoId;
        this.peId = peId;
        this.pdId = pdId;
        this.clienteId = clienteId;
        this.establecimientoId = establecimientoId;
        this.almacenEstId = almacenEstId;
        this.usuarioId = usuarioId;
        this.placa = placa;
        this.contadorInicialOrigen = contadorInicialOrigen;
        this.contadorFinalOrigen = contadorFinalOrigen;
        this.cantidadDespachada = cantidadDespachada;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.fechaDespacho = fechaDespacho;
        this.proId = proId;
        this.unId = unId;
        this.pITOrigen = pITOrigen;
        this.pFTOrigen = pFTOrigen;
        this.latitud = latitud;
        this.longitud = longitud;
        this.almacenVehId = almacenVehId;
        this.serie = serie;
        this.numero = numero;
        this.fechaCreacion = fechaCreacion;
        this.usuarioCreacion = usuarioCreacion;
        this.estadoId = estadoId;
        this.veId = veId;
        this.guiaRemision = guiaRemision;
        this.liqId = liqId;
        this.precioUnitarioSIGV = precioUnitarioSIGV;
        this.precioUnitarioCIGV = precioUnitarioCIGV;
        this.porImpuesto = porImpuesto;
        this.costoVenta = costoVenta;
        this.importe = importe;
        this.contadorInicialDestino = contadorInicialDestino;
        this.contadorFinalDestino = contadorFinalDestino;
        this.pITDestino = pITDestino;
        this.pFTDestino = pFTDestino;
        this.compId = compId;
    }

    public double getContadorInicialOrigen() {
        return contadorInicialOrigen;
    }

    public void setContadorInicialOrigen(double contadorInicialOrigen) {
        this.contadorInicialOrigen = contadorInicialOrigen;
    }

    public double getContadorFinalOrigen() {
        return contadorFinalOrigen;
    }

    public void setContadorFinalOrigen(double contadorFinalOrigen) {
        this.contadorFinalOrigen = contadorFinalOrigen;
    }

    public double getpITOrigen() {
        return pITOrigen;
    }

    public void setpITOrigen(double pITOrigen) {
        this.pITOrigen = pITOrigen;
    }

    public double getpFTOrigen() {
        return pFTOrigen;
    }

    public void setpFTOrigen(double pFTOrigen) {
        this.pFTOrigen = pFTOrigen;
    }

    public double getContadorInicialDestino() {
        return contadorInicialDestino;
    }

    public void setContadorInicialDestino(double contadorInicialDestino) {
        this.contadorInicialDestino = contadorInicialDestino;
    }

    public double getContadorFinalDestino() {
        return contadorFinalDestino;
    }

    public void setContadorFinalDestino(double contadorFinalDestino) {
        this.contadorFinalDestino = contadorFinalDestino;
    }

    public double getpITDestino() {
        return pITDestino;
    }

    public void setpITDestino(double pITDestino) {
        this.pITDestino = pITDestino;
    }

    public double getpFTDestino() {
        return pFTDestino;
    }

    public void setpFTDestino(double pFTDestino) {
        this.pFTDestino = pFTDestino;
    }

    public long getLiqId() {
        return liqId;
    }

    public void setLiqId(long liqId) {
        this.liqId = liqId;
    }

    public double getPrecioUnitarioSIGV() {
        return precioUnitarioSIGV;
    }

    public void setPrecioUnitarioSIGV(double precioUnitarioSIGV) {
        this.precioUnitarioSIGV = precioUnitarioSIGV;
    }

    public double getPrecioUnitarioCIGV() {
        return precioUnitarioCIGV;
    }

    public void setPrecioUnitarioCIGV(double precioUnitarioCIGV) {
        this.precioUnitarioCIGV = precioUnitarioCIGV;
    }

    public double getPorImpuesto() {
        return porImpuesto;
    }

    public void setPorImpuesto(double porImpuesto) {
        this.porImpuesto = porImpuesto;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public double getCostoVenta() {
        return costoVenta;
    }

    public void setCostoVenta(double costoVenta) {
        this.costoVenta = costoVenta;
    }

    public long getCompId() {
        return compId;
    }

    public void setCompId(long compId) {
        this.compId = compId;
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

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public int getUsuarioCreacion() {
        return usuarioCreacion;
    }

    public void setUsuarioCreacion(int usuarioCreacion) {
        this.usuarioCreacion = usuarioCreacion;
    }

    public int getEstadoId() {
        return estadoId;
    }

    public void setEstadoId(int estadoId) {
        this.estadoId = estadoId;
    }

    public int getVeId() {
        return veId;
    }

    public void setVeId(int veId) {
        this.veId = veId;
    }


    public static List<Despacho> getListDespachoByPedido(String pedidoId) {

        List<Despacho> despachoList = Despacho.find(Despacho.class, "pe_Id=?", new String[]{pedidoId});
        if (despachoList.size() > 0) {

            return despachoList;
        }

        return null;
    }


    public static List<Despacho> getListDespachoByPedidoNoCompId(String pedidoId) {

        List<Despacho> despachoList = Despacho.find(Despacho.class, "pe_Id=? ", new String[]{pedidoId});
        if (despachoList.size() > 0) {
            return despachoList;
        }
        return null;
    }


    public static List<Despacho> getListDespachoByPedidoNoCompIdEstablec(String establecimientoId) {

        List<Despacho> despachoList = Despacho.find(Despacho.class, "establecimiento_Id=? ", new String[]{establecimientoId});
        if (despachoList.size() > 0) {
            return despachoList;
        }
        return null;
    }

    public static List<Despacho> getListDespachoByPedidoNoCompIdFacturado(String establecimientoId) {

        List<Despacho> despachoList = Despacho.find(Despacho.class, "establecimiento_Id=? and comp_Id != ?  ", new String[]{establecimientoId, "0"});
        if (despachoList.size() > 0) {
            return despachoList;
        }
        return null;
    }

    public static List<Despacho> getDespachoByCompro(String compId) {
        List<Despacho> despachoList = Despacho.find(Despacho.class, "comp_Id=? ", new String[]{compId});
        if (despachoList.size() > 0) {
            return despachoList;
        }
        return null;
    }

    public static Despacho getDespachoByDespachoId(String despachoId) {
        List<Despacho> despachoList = Despacho.find(Despacho.class, "despacho_Id=? ", new String[]{despachoId});
        if (despachoList.size() > 0) {
            return despachoList.get(0);
        }
        return null;
    }

}
