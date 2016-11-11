package energigas.apps.systemstrategy.energigas.entities;

/**
 * Created by kelvi on 09/11/2016.
 */

public class CajaExtorno {

    private long cajaExtornoId;

    private long cajaMovimientoId;

    private long cajaMovimientoExtornoId;

    private String fechaCreacion;

    private int usuarioCreacion;

    private String fechaAccion;

    private int usuarioAccion;

    private long liquidacionId;

    public CajaExtorno() {
    }

    public CajaExtorno(long cajaExtornoId, long cajaMovimientoId, long cajaMovimientoExtornoId, String fechaCreacion, int usuarioCreacion, String fechaAccion, int usuarioAccion, long liquidacionId) {
        this.cajaExtornoId = cajaExtornoId;
        this.cajaMovimientoId = cajaMovimientoId;
        this.cajaMovimientoExtornoId = cajaMovimientoExtornoId;
        this.fechaCreacion = fechaCreacion;
        this.usuarioCreacion = usuarioCreacion;
        this.fechaAccion = fechaAccion;
        this.usuarioAccion = usuarioAccion;
        this.liquidacionId = liquidacionId;
    }


    public long getCajaExtornoId() {
        return cajaExtornoId;
    }

    public void setCajaExtornoId(long cajaExtornoId) {
        this.cajaExtornoId = cajaExtornoId;
    }

    public long getCajaMovimientoId() {
        return cajaMovimientoId;
    }

    public void setCajaMovimientoId(long cajaMovimientoId) {
        this.cajaMovimientoId = cajaMovimientoId;
    }

    public long getCajaMovimientoExtornoId() {
        return cajaMovimientoExtornoId;
    }

    public void setCajaMovimientoExtornoId(long cajaMovimientoExtornoId) {
        this.cajaMovimientoExtornoId = cajaMovimientoExtornoId;
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

    public String getFechaAccion() {
        return fechaAccion;
    }

    public void setFechaAccion(String fechaAccion) {
        this.fechaAccion = fechaAccion;
    }

    public int getUsuarioAccion() {
        return usuarioAccion;
    }

    public void setUsuarioAccion(int usuarioAccion) {
        this.usuarioAccion = usuarioAccion;
    }

    public long getLiquidacionId() {
        return liquidacionId;
    }

    public void setLiquidacionId(long liquidacionId) {
        this.liquidacionId = liquidacionId;
    }
}
