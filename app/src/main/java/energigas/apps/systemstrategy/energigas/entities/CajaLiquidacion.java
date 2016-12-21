package energigas.apps.systemstrategy.energigas.entities;

import com.orm.SugarRecord;
import com.orm.dsl.Ignore;
import com.orm.dsl.Unique;

import java.util.List;

import energigas.apps.systemstrategy.energigas.entities.fe.CertificadoDigital;
import energigas.apps.systemstrategy.energigas.utils.Constants;
import energigas.apps.systemstrategy.energigas.utils.Session;

/**
 * Created by kelvi on 10/08/2016.
 */

public class CajaLiquidacion extends SugarRecord {

    @Unique
    private long liqId;

    private int usuarioId;

    private String fechaApertura;

    private String fechaCierre;

    private int estadoId;

    private double ingresos;

    private double gastos;

    private double meta;

    private int porcentaje;

    private int kmInicial;

    private int kmFinal;

    private double pesoInicial;

    private double pesoFinal;

    private double pIT;

    private double pFT;

    private String fechaActualizacion;

    private int usuarioActualizacion;

    private int tipoId;

    private String placaVehiculo;

    private int veId;

    private int almId;

    private String latitudInicio;

    private String longitudInicio;

    private String latitudFinal;

    private String longitudFinal;

    private long pdId;

    private int pdfId;

    private double saldoInicial;

    private double saldoFinal;

    private int entidadId;

    @Ignore
    private PlanDistribucion planDistribucionD; //
    @Ignore
    private List<Cliente> itemsClientes; //
    @Ignore
    private List<CajaLiquidacionDetalle> itemsLiquidacion;
    @Ignore
    private List<Serie> itemsSeries; //
    @Ignore
    private List<Pedido> itemsPedidos; //
    @Ignore
    private List<Dispositivo> itemsDispositivos; //
    @Ignore
    private Vehiculo vehiculo; //
    @Ignore
    private List<VehiculoDispositivo> itemsVehiculoDispositivo; //
    @Ignore
    private VehiculoUsuario vehiculoUsuario; //
    @Ignore
    private List<DispositivoSerie> itemsDispositivoSeries; //
    @Ignore
    private DEEntidad entidad; //


    public CajaLiquidacion() {
    }

    public CajaLiquidacion(long liqId, int usuarioId, String fechaApertura, String fechaCierre, int estadoId, double ingresos, double gastos, double meta, int porcentaje, int kmInicial, int kmFinal, double pesoInicial, double pesoFinal, double pIT, double pFT, String fechaActualizacion, int usuarioActualizacion, int tipoId, String placaVehiculo, int veId, int almId, String latitudInicio, String longitudInicio, String latitudFinal, String longitudFinal, long pdId, int pdfId, double saldoInicial, double saldoFinal, int entidadId, PlanDistribucion planDistribucionD, List<Cliente> itemsClientes, List<CajaLiquidacionDetalle> itemsLiquidacion, List<Serie> itemsSeries, List<Pedido> itemsPedidos, List<Dispositivo> itemsDispositivos, Vehiculo vehiculo, List<VehiculoDispositivo> itemsVehiculoDispositivo, VehiculoUsuario vehiculoUsuario, List<DispositivoSerie> itemsDispositivoSeries, DEEntidad entidad) {
        this.liqId = liqId;
        this.usuarioId = usuarioId;
        this.fechaApertura = fechaApertura;
        this.fechaCierre = fechaCierre;
        this.estadoId = estadoId;
        this.ingresos = ingresos;
        this.gastos = gastos;
        this.meta = meta;
        this.porcentaje = porcentaje;
        this.kmInicial = kmInicial;
        this.kmFinal = kmFinal;
        this.pesoInicial = pesoInicial;
        this.pesoFinal = pesoFinal;
        this.pIT = pIT;
        this.pFT = pFT;
        this.fechaActualizacion = fechaActualizacion;
        this.usuarioActualizacion = usuarioActualizacion;
        this.tipoId = tipoId;
        this.placaVehiculo = placaVehiculo;
        this.veId = veId;
        this.almId = almId;
        this.latitudInicio = latitudInicio;
        this.longitudInicio = longitudInicio;
        this.latitudFinal = latitudFinal;
        this.longitudFinal = longitudFinal;
        this.pdId = pdId;
        this.pdfId = pdfId;
        this.saldoInicial = saldoInicial;
        this.saldoFinal = saldoFinal;
        this.entidadId = entidadId;
        this.planDistribucionD = planDistribucionD;
        this.itemsClientes = itemsClientes;
        this.itemsLiquidacion = itemsLiquidacion;
        this.itemsSeries = itemsSeries;
        this.itemsPedidos = itemsPedidos;
        this.itemsDispositivos = itemsDispositivos;
        this.vehiculo = vehiculo;
        this.itemsVehiculoDispositivo = itemsVehiculoDispositivo;
        this.vehiculoUsuario = vehiculoUsuario;
        this.itemsDispositivoSeries = itemsDispositivoSeries;
        this.entidad = entidad;
    }

    public int getEntidadId() {
        return entidadId;
    }

    public void setEntidadId(int entidadId) {
        this.entidadId = entidadId;
    }

    public DEEntidad getEntidad() {
        return entidad;
    }

    public void setEntidad(DEEntidad entidad) {
        this.entidad = entidad;
    }

    public long getLiqId() {
        return liqId;
    }

    public void setLiqId(long liqId) {
        this.liqId = liqId;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(String fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    public String getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(String fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    public int getEstadoId() {
        return estadoId;
    }

    public void setEstadoId(int estadoId) {
        this.estadoId = estadoId;
    }

    public double getIngresos() {
        return ingresos;
    }

    public void setIngresos(double ingresos) {
        this.ingresos = ingresos;
    }

    public double getGastos() {
        return gastos;
    }

    public void setGastos(double gastos) {
        this.gastos = gastos;
    }

    public double getMeta() {
        return meta;
    }

    public void setMeta(double meta) {
        this.meta = meta;
    }

    public int getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(int porcentaje) {
        this.porcentaje = porcentaje;
    }

    public int getKmInicial() {
        return kmInicial;
    }

    public void setKmInicial(int kmInicial) {
        this.kmInicial = kmInicial;
    }

    public int getKmFinal() {
        return kmFinal;
    }

    public void setKmFinal(int kmFinal) {
        this.kmFinal = kmFinal;
    }

    public double getPesoInicial() {
        return pesoInicial;
    }

    public void setPesoInicial(double pesoInicial) {
        this.pesoInicial = pesoInicial;
    }

    public double getPesoFinal() {
        return pesoFinal;
    }

    public void setPesoFinal(double pesoFinal) {
        this.pesoFinal = pesoFinal;
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

    public String getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(String fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public int getUsuarioActualizacion() {
        return usuarioActualizacion;
    }

    public void setUsuarioActualizacion(int usuarioActualizacion) {
        this.usuarioActualizacion = usuarioActualizacion;
    }

    public int getTipoId() {
        return tipoId;
    }

    public void setTipoId(int tipoId) {
        this.tipoId = tipoId;
    }

    public String getPlacaVehiculo() {
        return placaVehiculo;
    }

    public void setPlacaVehiculo(String placaVehiculo) {
        this.placaVehiculo = placaVehiculo;
    }

    public int getVeId() {
        return veId;
    }

    public void setVeId(int veId) {
        this.veId = veId;
    }

    public int getAlmId() {
        return almId;
    }

    public void setAlmId(int almId) {
        this.almId = almId;
    }

    public String getLatitudInicio() {
        return latitudInicio;
    }

    public void setLatitudInicio(String latitudInicio) {
        this.latitudInicio = latitudInicio;
    }

    public String getLongitudInicio() {
        return longitudInicio;
    }

    public void setLongitudInicio(String longitudInicio) {
        this.longitudInicio = longitudInicio;
    }

    public String getLatitudFinal() {
        return latitudFinal;
    }

    public void setLatitudFinal(String latitudFinal) {
        this.latitudFinal = latitudFinal;
    }

    public String getLongitudFinal() {
        return longitudFinal;
    }

    public void setLongitudFinal(String longitudFinal) {
        this.longitudFinal = longitudFinal;
    }

    public long getPdId() {
        return pdId;
    }

    public void setPdId(long pdId) {
        this.pdId = pdId;
    }

    public int getPdfId() {
        return pdfId;
    }

    public void setPdfId(int pdfId) {
        this.pdfId = pdfId;
    }

    public double getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(double saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    public double getSaldoFinal() {
        return saldoFinal;
    }

    public void setSaldoFinal(double saldoFinal) {
        this.saldoFinal = saldoFinal;
    }

    public PlanDistribucion getPlanDistribucionD() {
        return planDistribucionD;
    }

    public void setPlanDistribucionD(PlanDistribucion planDistribucionD) {
        this.planDistribucionD = planDistribucionD;
    }

    public List<Cliente> getItemsClientes() {
        return itemsClientes;
    }

    public void setItemsClientes(List<Cliente> itemsClientes) {
        this.itemsClientes = itemsClientes;
    }

    public List<CajaLiquidacionDetalle> getItemsLiquidacion() {
        return itemsLiquidacion;
    }

    public void setItemsLiquidacion(List<CajaLiquidacionDetalle> itemsLiquidacion) {
        this.itemsLiquidacion = itemsLiquidacion;
    }

    public List<Serie> getItemsSeries() {
        return itemsSeries;
    }

    public void setItemsSeries(List<Serie> itemsSeries) {
        this.itemsSeries = itemsSeries;
    }

    public List<Pedido> getItemsPedidos() {
        return itemsPedidos;
    }

    public void setItemsPedidos(List<Pedido> itemsPedidos) {
        this.itemsPedidos = itemsPedidos;
    }

    public List<Dispositivo> getItemsDispositivos() {
        return itemsDispositivos;
    }

    public void setItemsDispositivos(List<Dispositivo> itemsDispositivos) {
        this.itemsDispositivos = itemsDispositivos;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public List<VehiculoDispositivo> getItemsVehiculoDispositivo() {
        return itemsVehiculoDispositivo;
    }

    public void setItemsVehiculoDispositivo(List<VehiculoDispositivo> itemsVehiculoDispositivo) {
        this.itemsVehiculoDispositivo = itemsVehiculoDispositivo;
    }

    public VehiculoUsuario getVehiculoUsuario() {
        return vehiculoUsuario;
    }

    public void setVehiculoUsuario(VehiculoUsuario vehiculoUsuario) {
        this.vehiculoUsuario = vehiculoUsuario;
    }

    public List<DispositivoSerie> getItemsDispositivoSeries() {
        return itemsDispositivoSeries;
    }

    public void setItemsDispositivoSeries(List<DispositivoSerie> itemsDispositivoSeries) {
        this.itemsDispositivoSeries = itemsDispositivoSeries;
    }

    public static CajaLiquidacion getCajaLiquidacion(String liquidacionId) {
        CajaLiquidacion cajaLiquidacion = CajaLiquidacion.find(CajaLiquidacion.class, " liq_Id=? ", new String[]{liquidacionId}).get(Constants.CURRENT);
        DEEntidad deEntidad = DEEntidad.getEntidadById(cajaLiquidacion.getEntidadId() + "");
        deEntidad.setCertificado(CertificadoDigital.find(CertificadoDigital.class, "entidad_Id=?", new String[]{deEntidad.getEntidadId() + ""}).get(0));
        cajaLiquidacion.setEntidad(deEntidad);
        return cajaLiquidacion;
    }

}
