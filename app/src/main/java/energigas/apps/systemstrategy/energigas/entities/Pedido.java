package energigas.apps.systemstrategy.energigas.entities;

/**
 * Created by kelvi on 10/08/2016.
 */

public class Pedido {

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

    private double horaInicio;

    private double horaFin;

    private double horaEntrega;

    private boolean horario;

    private int vehiculoId;

    private String motivoCancelado;

    private String motivoRevertido;

    private String fechaAsignacionVehiculo;

    private int usuarioAsignacionVehiculo;

    private double horaLlegada;

    private double horaSalida;

    private boolean inclusion;

    private String comprobanteVenta;

    private String guiaRemision;

    private double horaProgramada;

    private int agenteId;

    private boolean scopCerrado;

    private long compId;

    private long greId;

    public Pedido() {
    }

    public Pedido(long peId, String serie, int numero, int tipoId, int prioridadId, int clienteId, String fechaPedido, String fechaEntrega, String fechaEntregaProgramada, int estadoId, double total, boolean consolidado, int usuarioAccion, String fechaAccion, int establecimientoId, String direccionEntrega, String fechaRealEntrega, int usuarioEntrega, String fechaCreacion, int usuarioCreacion, double baseImponible, double iGV, int modalidadCreditoId, int veId, String scop, double horaInicio, double horaFin, double horaEntrega, boolean horario, int vehiculoId, String motivoCancelado, String motivoRevertido, String fechaAsignacionVehiculo, int usuarioAsignacionVehiculo, double horaLlegada, double horaSalida, boolean inclusion, String comprobanteVenta, String guiaRemision, double horaProgramada, int agenteId, boolean scopCerrado, long compId, long greId) {
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

    public double getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(double horaInicio) {
        this.horaInicio = horaInicio;
    }

    public double getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(double horaFin) {
        this.horaFin = horaFin;
    }

    public double getHoraEntrega() {
        return horaEntrega;
    }

    public void setHoraEntrega(double horaEntrega) {
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

    public double getHoraLlegada() {
        return horaLlegada;
    }

    public void setHoraLlegada(double horaLlegada) {
        this.horaLlegada = horaLlegada;
    }

    public double getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(double horaSalida) {
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

    public double getHoraProgramada() {
        return horaProgramada;
    }

    public void setHoraProgramada(double horaProgramada) {
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
}
