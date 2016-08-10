package energigas.apps.systemstrategy.energigas.entities;

/**
 * Created by kelvi on 10/08/2016.
 */

public class CajaLiquidacion {
    private long liqId;

    private int agenteId;

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

    private int tipo;

    private String placaVehiculo;

    private int vehiculoId;

    private int almacenId;

    private String latitudInicio;

    private String longitudInicio;

    private String latitudFinal;

    private String longitudFinal;

    public CajaLiquidacion() {
    }

    public CajaLiquidacion(long liqId, int agenteId, String fechaApertura, String fechaCierre, int estadoId, double ingresos, double gastos, double meta, int porcentaje, int kmInicial, int kmFinal, double pesoInicial, double pesoFinal, double pIT, double pFT, String fechaActualizacion, int usuarioActualizacion, int tipo, String placaVehiculo, int vehiculoId, int almacenId, String latitudInicio, String longitudInicio, String latitudFinal, String longitudFinal) {
        this.liqId = liqId;
        this.agenteId = agenteId;
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
        this.tipo = tipo;
        this.placaVehiculo = placaVehiculo;
        this.vehiculoId = vehiculoId;
        this.almacenId = almacenId;
        this.latitudInicio = latitudInicio;
        this.longitudInicio = longitudInicio;
        this.latitudFinal = latitudFinal;
        this.longitudFinal = longitudFinal;
    }

    public long getLiqId() {
        return liqId;
    }

    public void setLiqId(long liqId) {
        this.liqId = liqId;
    }

    public int getAgenteId() {
        return agenteId;
    }

    public void setAgenteId(int agenteId) {
        this.agenteId = agenteId;
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

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getPlacaVehiculo() {
        return placaVehiculo;
    }

    public void setPlacaVehiculo(String placaVehiculo) {
        this.placaVehiculo = placaVehiculo;
    }

    public int getVehiculoId() {
        return vehiculoId;
    }

    public void setVehiculoId(int vehiculoId) {
        this.vehiculoId = vehiculoId;
    }

    public int getAlmacenId() {
        return almacenId;
    }

    public void setAlmacenId(int almacenId) {
        this.almacenId = almacenId;
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
}
