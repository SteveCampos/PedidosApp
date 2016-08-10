package energigas.apps.systemstrategy.energigas.entities;

/**
 * Created by kelvi on 10/08/2016.
 */

public class Cliente {
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

    public Cliente() {
    }

    public Cliente(int cliIClienteId, String cliVCodigo, int cliIPersonaId, int cliIUsuarioActualizacion, String cliDTFechaActualizacion, double cliDOMontoCredito, int cliIModalidadCreditoId, int cliIUsuarioCreacion, String cliDTFechaCreacion, int cliIEstadoId, int cliITipoClienteId, double cliDOSobreGiro, boolean cliBLineaCredito, String cliVTelefono, String cliVCelular, double cliDOCreditoDisponible, String cliVContacto) {
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
}
