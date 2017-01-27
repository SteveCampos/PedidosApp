package energigas.apps.systemstrategy.energigas.entities;

import com.orm.SugarRecord;
import com.orm.dsl.Ignore;
import com.orm.dsl.Unique;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import energigas.apps.systemstrategy.energigas.utils.Utils;

/**
 * Created by Steve on 21/07/2016.
 */

public class Pedido extends SugarRecord {

    @Unique
    private long peId;

    private String serie;

    private int numero;

    private int tipoId;

    private int prioridadId;

    private int clienteId;

    private String fechaPedido;

    private String fechaEntrega;

    private String fechaEntregaProgramada;

    private int estadoId;

    private double total;

    private boolean consolidado;

    private int usuarioAccion;

    private String fechaAccion;

    private int establecimientoId;

    private String direccionEntrega;

    private String fechaRealEntrega;

    private int usuarioEntrega;

    private String fechaCreacion;

    private int usuarioCreacion;

    private double baseImponible;

    private double iGV;

    private int modalidadCreditoId;

    private int veId;

    private String scop;

    private String horaInicio;

    private String horaFin;

    private String horaEntrega;

    private boolean horario;

    private int vehiculoId;

    private String motivoCancelado;

    private String motivoRevertido;

    private String fechaAsignacionVehiculo;

    private int usuarioAsignacionVehiculo;

    private String horaLlegada;

    private String horaSalida;

    private boolean inclusion;

    private String comprobanteVenta;

    private String guiaRemision;

    private String horaProgramada;

    private int agenteId;

    private boolean scopCerrado;

    private long compId;

    private long greId;

    private double porImpuesto;


    @Ignore
    private List<PedidoDetalle> items;


    @Ignore
    private Estado estado;

    private Long cajaLiquidacionId;

    public Pedido() {
    }

    public Pedido(long peId, String serie, int numero, int tipoId, int prioridadId, int clienteId, String fechaPedido, String fechaEntrega, String fechaEntregaProgramada, int estadoId, double total, boolean consolidado, int usuarioAccion, String fechaAccion, int establecimientoId, String direccionEntrega, String fechaRealEntrega, int usuarioEntrega, String fechaCreacion, int usuarioCreacion, double baseImponible, double iGV, int modalidadCreditoId, int veId, String scop, String horaInicio, String horaFin, String horaEntrega, boolean horario, int vehiculoId, String motivoCancelado, String motivoRevertido, String fechaAsignacionVehiculo, int usuarioAsignacionVehiculo, String horaLlegada, String horaSalida, boolean inclusion, String comprobanteVenta, String guiaRemision, String horaProgramada, int agenteId, boolean scopCerrado, long compId, long greId, double porImpuesto, List<PedidoDetalle> items, Estado estado, Long cajaLiquidacionId) {
        this.peId = peId;
        this.serie = serie;
        this.numero = numero;
        this.tipoId = tipoId;
        this.prioridadId = prioridadId;
        this.clienteId = clienteId;
        this.fechaPedido = fechaPedido;
        this.fechaEntrega = fechaEntrega;
        this.fechaEntregaProgramada = fechaEntregaProgramada;
        this.estadoId = estadoId;
        this.total = total;
        this.consolidado = consolidado;
        this.usuarioAccion = usuarioAccion;
        this.fechaAccion = fechaAccion;
        this.establecimientoId = establecimientoId;
        this.direccionEntrega = direccionEntrega;
        this.fechaRealEntrega = fechaRealEntrega;
        this.usuarioEntrega = usuarioEntrega;
        this.fechaCreacion = fechaCreacion;
        this.usuarioCreacion = usuarioCreacion;
        this.baseImponible = baseImponible;
        this.iGV = iGV;
        this.modalidadCreditoId = modalidadCreditoId;
        this.veId = veId;
        this.scop = scop;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.horaEntrega = horaEntrega;
        this.horario = horario;
        this.vehiculoId = vehiculoId;
        this.motivoCancelado = motivoCancelado;
        this.motivoRevertido = motivoRevertido;
        this.fechaAsignacionVehiculo = fechaAsignacionVehiculo;
        this.usuarioAsignacionVehiculo = usuarioAsignacionVehiculo;
        this.horaLlegada = horaLlegada;
        this.horaSalida = horaSalida;
        this.inclusion = inclusion;
        this.comprobanteVenta = comprobanteVenta;
        this.guiaRemision = guiaRemision;
        this.horaProgramada = horaProgramada;
        this.agenteId = agenteId;
        this.scopCerrado = scopCerrado;
        this.compId = compId;
        this.greId = greId;
        this.porImpuesto = porImpuesto;
        this.items = items;
        this.estado = estado;
        this.cajaLiquidacionId = cajaLiquidacionId;
    }

    public Long getCajaLiquidacionId() {
        return cajaLiquidacionId;
    }

    public void setCajaLiquidacionId(Long cajaLiquidacionId) {
        this.cajaLiquidacionId = cajaLiquidacionId;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }


    public long getPeId() {
        return peId;
    }

    public void setPeId(long peId) {
        this.peId = peId;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getTipoId() {
        return tipoId;
    }

    public void setTipoId(int tipoId) {
        this.tipoId = tipoId;
    }

    public int getPrioridadId() {
        return prioridadId;
    }

    public void setPrioridadId(int prioridadId) {
        this.prioridadId = prioridadId;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public String getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(String fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public String getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(String fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public String getFechaEntregaProgramada() {
        return fechaEntregaProgramada;
    }

    public void setFechaEntregaProgramada(String fechaEntregaProgramada) {
        this.fechaEntregaProgramada = fechaEntregaProgramada;
    }

    public int getEstadoId() {
        return estadoId;
    }

    public void setEstadoId(int estadoId) {
        this.estadoId = estadoId;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public boolean isConsolidado() {
        return consolidado;
    }

    public void setConsolidado(boolean consolidado) {
        this.consolidado = consolidado;
    }

    public int getUsuarioAccion() {
        return usuarioAccion;
    }

    public void setUsuarioAccion(int usuarioAccion) {
        this.usuarioAccion = usuarioAccion;
    }

    public String getFechaAccion() {
        return fechaAccion;
    }

    public void setFechaAccion(String fechaAccion) {
        this.fechaAccion = fechaAccion;
    }

    public int getEstablecimientoId() {
        return establecimientoId;
    }

    public void setEstablecimientoId(int establecimientoId) {
        this.establecimientoId = establecimientoId;
    }

    public String getDireccionEntrega() {
        return direccionEntrega;
    }

    public void setDireccionEntrega(String direccionEntrega) {
        this.direccionEntrega = direccionEntrega;
    }

    public String getFechaRealEntrega() {
        return fechaRealEntrega;
    }

    public void setFechaRealEntrega(String fechaRealEntrega) {
        this.fechaRealEntrega = fechaRealEntrega;
    }

    public int getUsuarioEntrega() {
        return usuarioEntrega;
    }

    public void setUsuarioEntrega(int usuarioEntrega) {
        this.usuarioEntrega = usuarioEntrega;
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

    public double getBaseImponible() {
        return baseImponible;
    }

    public void setBaseImponible(double baseImponible) {
        this.baseImponible = baseImponible;
    }

    public double getiGV() {
        return iGV;
    }

    public void setiGV(double iGV) {
        this.iGV = iGV;
    }

    public int getModalidadCreditoId() {
        return modalidadCreditoId;
    }

    public void setModalidadCreditoId(int modalidadCreditoId) {
        this.modalidadCreditoId = modalidadCreditoId;
    }

    public int getVeId() {
        return veId;
    }

    public void setVeId(int veId) {
        this.veId = veId;
    }

    public String getScop() {
        return scop;
    }

    public void setScop(String scop) {
        this.scop = scop;
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

    public String getHoraEntrega() {
        return horaEntrega;
    }

    public void setHoraEntrega(String horaEntrega) {
        this.horaEntrega = horaEntrega;
    }

    public boolean isHorario() {
        return horario;
    }

    public void setHorario(boolean horario) {
        this.horario = horario;
    }

    public int getVehiculoId() {
        return vehiculoId;
    }

    public void setVehiculoId(int vehiculoId) {
        this.vehiculoId = vehiculoId;
    }

    public String getMotivoCancelado() {
        return motivoCancelado;
    }

    public void setMotivoCancelado(String motivoCancelado) {
        this.motivoCancelado = motivoCancelado;
    }

    public String getMotivoRevertido() {
        return motivoRevertido;
    }

    public void setMotivoRevertido(String motivoRevertido) {
        this.motivoRevertido = motivoRevertido;
    }

    public String getFechaAsignacionVehiculo() {
        return fechaAsignacionVehiculo;
    }

    public void setFechaAsignacionVehiculo(String fechaAsignacionVehiculo) {
        this.fechaAsignacionVehiculo = fechaAsignacionVehiculo;
    }

    public int getUsuarioAsignacionVehiculo() {
        return usuarioAsignacionVehiculo;
    }

    public void setUsuarioAsignacionVehiculo(int usuarioAsignacionVehiculo) {
        this.usuarioAsignacionVehiculo = usuarioAsignacionVehiculo;
    }

    public String getHoraLlegada() {
        return horaLlegada;
    }

    public void setHoraLlegada(String horaLlegada) {
        this.horaLlegada = horaLlegada;
    }

    public String getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(String horaSalida) {
        this.horaSalida = horaSalida;
    }

    public boolean isInclusion() {
        return inclusion;
    }

    public void setInclusion(boolean inclusion) {
        this.inclusion = inclusion;
    }

    public String getComprobanteVenta() {
        return comprobanteVenta;
    }

    public void setComprobanteVenta(String comprobanteVenta) {
        this.comprobanteVenta = comprobanteVenta;
    }

    public String getGuiaRemision() {
        return guiaRemision;
    }

    public void setGuiaRemision(String guiaRemision) {
        this.guiaRemision = guiaRemision;
    }

    public String getHoraProgramada() {
        return horaProgramada;
    }

    public void setHoraProgramada(String horaProgramada) {
        this.horaProgramada = horaProgramada;
    }

    public int getAgenteId() {
        return agenteId;
    }

    public void setAgenteId(int agenteId) {
        this.agenteId = agenteId;
    }

    public boolean isScopCerrado() {
        return scopCerrado;
    }

    public void setScopCerrado(boolean scopCerrado) {
        this.scopCerrado = scopCerrado;
    }

    public long getCompId() {
        return compId;
    }

    public void setCompId(long compId) {
        this.compId = compId;
    }

    public long getGreId() {
        return greId;
    }

    public void setGreId(long greId) {
        this.greId = greId;
    }

    public double getPorImpuesto() {
        return porImpuesto;
    }

    public void setPorImpuesto(double porImpuesto) {
        this.porImpuesto = porImpuesto;
    }

    public List<PedidoDetalle> getItems() {
        return items;
    }

    public void setItems(List<PedidoDetalle> items) {
        this.items = items;
    }

    public static Pedido getPedido(String establecimientoId) {
        List<Pedido> pedidoList = Pedido.find(Pedido.class, "establecimiento_Id=?", new String[]{establecimientoId});
        if (pedidoList != null) {
            return pedidoList.get(0);
        }
        return null;
    }

    public static Pedido getPedidoById(String pedidoId) {
        List<Pedido> pedidoList = Pedido.find(Pedido.class, "pe_Id=?", new String[]{pedidoId});
        if (pedidoList != null) {
            List<PedidoDetalle> pedidoDetalle = PedidoDetalle.getPedidoDetalleByPedido(pedidoId);
            pedidoList.get(0).setItems(pedidoDetalle);
            return pedidoList.get(0);
        }
        return null;
    }

    public static Pedido getPedidobyCompId(String comprobanteVentaId) {
        List<Pedido> pedidoList = Pedido.find(Pedido.class, "comp_Id=?", new String[]{comprobanteVentaId});
        if (pedidoList != null) {
            return pedidoList.get(0);
        }
        return null;
    }

    public static List<Pedido> getPedidosByLiqui(String liquidacionId) {
        List<Pedido> pedidoList = Pedido.find(Pedido.class, "caja_Liquidacion_Id=?", new String[]{liquidacionId});
        if (pedidoList != null) {
            return pedidoList;
        }
        return null;
    }


}
