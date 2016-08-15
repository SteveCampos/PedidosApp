package energigas.apps.systemstrategy.energigas.entities;

import java.util.List;

/**
 * Created by kelvi on 10/08/2016.
 */

public class CajaLiquidacion {

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

    private PlanDistribucion planDistribucionD ;
    private List<Cliente> itemsClientes ;
    private List<CajaLiquidacionDetalle> itemsLiquidacion;
    private List<Serie> itemsSeries;


    public CajaLiquidacion() {
    }

    public CajaLiquidacion(long liqId, int usuarioId, String fechaApertura, String fechaCierre, int estadoId, double ingresos, double gastos, double meta, int porcentaje, int kmInicial, int kmFinal, double pesoInicial, double pesoFinal, double pIT, double pFT, String fechaActualizacion, int usuarioActualizacion, int tipoId, String placaVehiculo, int veId, int almId, String latitudInicio, String longitudInicio, String latitudFinal, String longitudFinal, long pdId, int pdfId, double saldoInicial, double saldoFinal, PlanDistribucion planDistribucionD, List<Cliente> itemsClientes, List<CajaLiquidacionDetalle> itemsLiquidacion, List<Serie> itemsSeries) {
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
        this.planDistribucionD = planDistribucionD;
        this.itemsClientes = itemsClientes;
        this.itemsLiquidacion = itemsLiquidacion;
        this.itemsSeries = itemsSeries;
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
}
