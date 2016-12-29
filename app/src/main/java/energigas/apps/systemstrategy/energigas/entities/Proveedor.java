package energigas.apps.systemstrategy.energigas.entities;

import com.orm.SugarRecord;
import com.orm.dsl.Ignore;
import com.orm.dsl.Unique;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kelvi on 26/12/2016.
 */

public class Proveedor extends SugarRecord {
    @Unique
    private int proveedorId;
    private int personaId;
    private int diasCredito;
    private double montoCredito;
    private int modalidadCreditoId;
    private boolean agenteRetencion;
    private boolean agentePercepcion;
    private int estadoId;
    private int usuarioCreacionId;
    private String fechaCreacion;
    private int usuarioAccionId;
    private String fechaAccion;

    @Ignore
    private Persona persona;


    public Proveedor() {
    }

    public Proveedor(int proveedorId, int personaId, int diasCredito, double montoCredito, int modalidadCreditoId, boolean agenteRetencion, boolean agentePercepcion, int estadoId, int usuarioCreacionId, String fechaCreacion, int usuarioAccionId, String fechaAccion, Persona persona) {
        this.proveedorId = proveedorId;
        this.personaId = personaId;
        this.diasCredito = diasCredito;
        this.montoCredito = montoCredito;
        this.modalidadCreditoId = modalidadCreditoId;
        this.agenteRetencion = agenteRetencion;
        this.agentePercepcion = agentePercepcion;
        this.estadoId = estadoId;
        this.usuarioCreacionId = usuarioCreacionId;
        this.fechaCreacion = fechaCreacion;
        this.usuarioAccionId = usuarioAccionId;
        this.fechaAccion = fechaAccion;
        this.persona = persona;
    }

    public int getProveedorId() {
        return proveedorId;
    }

    public void setProveedorId(int proveedorId) {
        this.proveedorId = proveedorId;
    }

    public int getPersonaId() {
        return personaId;
    }

    public void setPersonaId(int personaId) {
        this.personaId = personaId;
    }

    public int getDiasCredito() {
        return diasCredito;
    }

    public void setDiasCredito(int diasCredito) {
        this.diasCredito = diasCredito;
    }

    public double getMontoCredito() {
        return montoCredito;
    }

    public void setMontoCredito(double montoCredito) {
        this.montoCredito = montoCredito;
    }

    public int getModalidadCreditoId() {
        return modalidadCreditoId;
    }

    public void setModalidadCreditoId(int modalidadCreditoId) {
        this.modalidadCreditoId = modalidadCreditoId;
    }

    public boolean isAgenteRetencion() {
        return agenteRetencion;
    }

    public void setAgenteRetencion(boolean agenteRetencion) {
        this.agenteRetencion = agenteRetencion;
    }

    public boolean isAgentePercepcion() {
        return agentePercepcion;
    }

    public void setAgentePercepcion(boolean agentePercepcion) {
        this.agentePercepcion = agentePercepcion;
    }

    public int getEstadoId() {
        return estadoId;
    }

    public void setEstadoId(int estadoId) {
        this.estadoId = estadoId;
    }

    public int getUsuarioCreacionId() {
        return usuarioCreacionId;
    }

    public void setUsuarioCreacionId(int usuarioCreacionId) {
        this.usuarioCreacionId = usuarioCreacionId;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public int getUsuarioAccionId() {
        return usuarioAccionId;
    }

    public void setUsuarioAccionId(int usuarioAccionId) {
        this.usuarioAccionId = usuarioAccionId;
    }

    public String getFechaAccion() {
        return fechaAccion;
    }

    public void setFechaAccion(String fechaAccion) {
        this.fechaAccion = fechaAccion;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public static List<Proveedor> getProveedorList() {
        List<Proveedor> list = Proveedor.listAll(Proveedor.class);
        List<Proveedor> proveedorList = new ArrayList<>();
        for (Proveedor proveedor : list) {
            if (proveedor != null) {
                Persona persona = Persona.getPersona("" + proveedor.getPersonaId());
                if (persona != null) {
                    if (persona.getPerVRazonSocial() != null && persona.getPerVDocIdentidad() != null) {
                        proveedor.setPersona(persona);
                        proveedorList.add(proveedor);
                    }
                }
            }
        }
        return proveedorList;
    }

    public static Proveedor getProveedorById(int proveedorId) {
        List<Proveedor> list = Proveedor.listAll(Proveedor.class);
        List<Proveedor> proveedorList = new ArrayList<>();
        for (Proveedor proveedor : list) {

            if (proveedorId == proveedor.getProveedorId()) {
                if (proveedor != null) {
                    Persona persona = Persona.getPersona("" + proveedor.getPersonaId());
                    if (persona != null) {

                        proveedor.setPersona(persona);
                        proveedorList.add(proveedor);

                    }
                }
            }


        }
        return proveedorList.get(0);
    }
}
