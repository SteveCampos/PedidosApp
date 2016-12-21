package energigas.apps.systemstrategy.energigas.entities.fe;



import java.math.BigDecimal;

/**
 * Created by Steve on 22/11/2016.
 */

public class DatosGuia {

    public Contribuyente direccionDestino;
    public Contribuyente direccionOrigen;
    public String rucTransportista;
    public String tipoDocTransportista;
    public String nombreTransportista;
    public String nroLicenciaConducir;
    public String placaVehiculo;
    public String codigoAutorizacion;
    public String marcaVehiculo;
    public String modoTransporte;
    public String unidadMedida;
    public BigDecimal pesoBruto;

    public DatosGuia()
    {
        this.direccionDestino = new Contribuyente();
        this.direccionOrigen = new Contribuyente();
    }
}
