package energigas.apps.systemstrategy.energigas.entities;

import android.util.Log;

import com.orm.SugarRecord;
import com.orm.dsl.Ignore;
import com.orm.dsl.Unique;

import java.util.List;

/**
 * Created by kelvi on 10/08/2016.
 */

public class Cliente extends SugarRecord {

    @Unique
    private int cliIClienteId;

    private String cliVCodigo;

    private int cliIPersonaId;

    private int cliIUsuarioActualizacion;

    private String cliDTFechaActualizacion;

    private double cliDOMontoCredito;

    private int cliIModalidadCreditoId;

    private int cliIUsuarioCreacion;

    private String cliDTFechaCreacion;

    private int cliIEstadoId;

    private int cliITipoClienteId;

    private double cliDOSobreGiro;

    private boolean cliBLineaCredito;

    private String cliVTelefono;

    private String cliVCelular;

    private double cliDOCreditoDisponible;

    private String cliVContacto;

    @Ignore
    private List<Establecimiento> itemsEstablecimientos;
    @Ignore
    private Persona persona;

    public Cliente() {
    }

    public Cliente(int cliIClienteId, String cliVCodigo, int cliIPersonaId, int cliIUsuarioActualizacion, String cliDTFechaActualizacion, double cliDOMontoCredito, int cliIModalidadCreditoId, int cliIUsuarioCreacion, String cliDTFechaCreacion, int cliIEstadoId, int cliITipoClienteId, double cliDOSobreGiro, boolean cliBLineaCredito, String cliVTelefono, String cliVCelular, double cliDOCreditoDisponible, String cliVContacto, List<Establecimiento> itemsEstablecimientos, Persona persona) {
        this.cliIClienteId = cliIClienteId;
        this.cliVCodigo = cliVCodigo;
        this.cliIPersonaId = cliIPersonaId;
        this.cliIUsuarioActualizacion = cliIUsuarioActualizacion;
        this.cliDTFechaActualizacion = cliDTFechaActualizacion;
        this.cliDOMontoCredito = cliDOMontoCredito;
        this.cliIModalidadCreditoId = cliIModalidadCreditoId;
        this.cliIUsuarioCreacion = cliIUsuarioCreacion;
        this.cliDTFechaCreacion = cliDTFechaCreacion;
        this.cliIEstadoId = cliIEstadoId;
        this.cliITipoClienteId = cliITipoClienteId;
        this.cliDOSobreGiro = cliDOSobreGiro;
        this.cliBLineaCredito = cliBLineaCredito;
        this.cliVTelefono = cliVTelefono;
        this.cliVCelular = cliVCelular;
        this.cliDOCreditoDisponible = cliDOCreditoDisponible;
        this.cliVContacto = cliVContacto;
        this.itemsEstablecimientos = itemsEstablecimientos;
        this.persona = persona;
    }

    public int getCliIClienteId() {
        return cliIClienteId;
    }

    public void setCliIClienteId(int cliIClienteId) {
        this.cliIClienteId = cliIClienteId;
    }

    public String getCliVCodigo() {
        return cliVCodigo;
    }

    public void setCliVCodigo(String cliVCodigo) {
        this.cliVCodigo = cliVCodigo;
    }

    public int getCliIPersonaId() {
        return cliIPersonaId;
    }

    public void setCliIPersonaId(int cliIPersonaId) {
        this.cliIPersonaId = cliIPersonaId;
    }

    public int getCliIUsuarioActualizacion() {
        return cliIUsuarioActualizacion;
    }

    public void setCliIUsuarioActualizacion(int cliIUsuarioActualizacion) {
        this.cliIUsuarioActualizacion = cliIUsuarioActualizacion;
    }

    public String getCliDTFechaActualizacion() {
        return cliDTFechaActualizacion;
    }

    public void setCliDTFechaActualizacion(String cliDTFechaActualizacion) {
        this.cliDTFechaActualizacion = cliDTFechaActualizacion;
    }

    public double getCliDOMontoCredito() {
        return cliDOMontoCredito;
    }

    public void setCliDOMontoCredito(double cliDOMontoCredito) {
        this.cliDOMontoCredito = cliDOMontoCredito;
    }

    public int getCliIModalidadCreditoId() {
        return cliIModalidadCreditoId;
    }

    public void setCliIModalidadCreditoId(int cliIModalidadCreditoId) {
        this.cliIModalidadCreditoId = cliIModalidadCreditoId;
    }

    public int getCliIUsuarioCreacion() {
        return cliIUsuarioCreacion;
    }

    public void setCliIUsuarioCreacion(int cliIUsuarioCreacion) {
        this.cliIUsuarioCreacion = cliIUsuarioCreacion;
    }

    public String getCliDTFechaCreacion() {
        return cliDTFechaCreacion;
    }

    public void setCliDTFechaCreacion(String cliDTFechaCreacion) {
        this.cliDTFechaCreacion = cliDTFechaCreacion;
    }

    public int getCliIEstadoId() {
        return cliIEstadoId;
    }

    public void setCliIEstadoId(int cliIEstadoId) {
        this.cliIEstadoId = cliIEstadoId;
    }

    public int getCliITipoClienteId() {
        return cliITipoClienteId;
    }

    public void setCliITipoClienteId(int cliITipoClienteId) {
        this.cliITipoClienteId = cliITipoClienteId;
    }

    public double getCliDOSobreGiro() {
        return cliDOSobreGiro;
    }

    public void setCliDOSobreGiro(double cliDOSobreGiro) {
        this.cliDOSobreGiro = cliDOSobreGiro;
    }

    public boolean isCliBLineaCredito() {
        return cliBLineaCredito;
    }

    public void setCliBLineaCredito(boolean cliBLineaCredito) {
        this.cliBLineaCredito = cliBLineaCredito;
    }

    public String getCliVTelefono() {
        return cliVTelefono;
    }

    public void setCliVTelefono(String cliVTelefono) {
        this.cliVTelefono = cliVTelefono;
    }

    public String getCliVCelular() {
        return cliVCelular;
    }

    public void setCliVCelular(String cliVCelular) {
        this.cliVCelular = cliVCelular;
    }

    public double getCliDOCreditoDisponible() {
        return cliDOCreditoDisponible;
    }

    public void setCliDOCreditoDisponible(double cliDOCreditoDisponible) {
        this.cliDOCreditoDisponible = cliDOCreditoDisponible;
    }

    public String getCliVContacto() {
        return cliVContacto;
    }

    public void setCliVContacto(String cliVContacto) {
        this.cliVContacto = cliVContacto;
    }

    public List<Establecimiento> getItemsEstablecimientos() {
        return itemsEstablecimientos;
    }

    public void setItemsEstablecimientos(List<Establecimiento> itemsEstablecimientos) {
        this.itemsEstablecimientos = itemsEstablecimientos;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public static Cliente getCliente(String clienteId) {
        Cliente cliente = Cliente.find(Cliente.class, "cli_I_Cliente_Id = ? ", new String[]{clienteId}).get(0);
        cliente.setPersona(Persona.find(Persona.class, "per_I_Persona_Id = ? ", new String[]{cliente.getCliIPersonaId() + ""}).get(0));
        Log.d("IDPERSONA", cliente.getPersona().getPerIPersonaId() + "");

        for (GeoUbicacion geoUbicacion : GeoUbicacion.listAll(GeoUbicacion.class)) {
            Log.d("IDGEOUBICACION", "persona: "+ geoUbicacion.getPersonaId() + " ubicacion: " +geoUbicacion.getUgId());
        }
        cliente.getPersona().setUbicacion(GeoUbicacion.find(GeoUbicacion.class, "persona_Id=?", new String[]{cliente.getPersona().getPerIPersonaId() + ""}).get(0));
        return cliente;
    }
}