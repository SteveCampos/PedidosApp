package energigas.apps.systemstrategy.energigas.entities;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

import java.util.List;

/**
 * Created by kelvi on 10/08/2016.
 */

public class ListaPrecioDetalle extends SugarRecord{
    @Unique
    private long lpdId;

    private int lpId;

    private int clienteId;

    private int establecimientoId;

    private int unId;

    private int proId;

    private double precio;

    private double precioImp;

    private String fechaActualizacion;

    private int usuarioActualizacion;

    private double porImpuesto;

    public ListaPrecioDetalle() {
    }

    public ListaPrecioDetalle(double porImpuesto, long lpdId, int lpId, int clienteId, int establecimientoId, int unId, int proId, double precio, double precioImp, String fechaActualizacion, int usuarioActualizacion) {
        this.porImpuesto = porImpuesto;
        this.lpdId = lpdId;
        this.lpId = lpId;
        this.clienteId = clienteId;
        this.establecimientoId = establecimientoId;
        this.unId = unId;
        this.proId = proId;
        this.precio = precio;
        this.precioImp = precioImp;
        this.fechaActualizacion = fechaActualizacion;
        this.usuarioActualizacion = usuarioActualizacion;
    }

    public double getPorImpuesto() {
        return porImpuesto;
    }

    public void setPorImpuesto(double porImpuesto) {
        this.porImpuesto = porImpuesto;
    }

    public long getLpdId() {
        return lpdId;
    }

    public void setLpdId(long lpdId) {
        this.lpdId = lpdId;
    }

    public int getLpId() {
        return lpId;
    }

    public void setLpId(int lpId) {
        this.lpId = lpId;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public int getEstablecimientoId() {
        return establecimientoId;
    }

    public void setEstablecimientoId(int establecimientoId) {
        this.establecimientoId = establecimientoId;
    }

    public int getUnId() {
        return unId;
    }

    public void setUnId(int unId) {
        this.unId = unId;
    }

    public int getProId() {
        return proId;
    }

    public void setProId(int proId) {
        this.proId = proId;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getPrecioImp() {
        return precioImp;
    }

    public void setPrecioImp(double precioImp) {
        this.precioImp = precioImp;
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

    @Override
    public String toString() {
        return ""+precio+"-"+unId;
    }

    public static ListaPrecioDetalle getPrecioDetalle(String establecimientoId,String unidadId,String productoId,String planPrecioId){

        List<ListaPrecioDetalle> listaPrecioDetalles = ListaPrecioDetalle.find(ListaPrecioDetalle.class,"establecimiento_Id=? AND un_Id=? AND pro_Id=? AND lp_Id=?",new String[]{establecimientoId,unidadId,productoId,planPrecioId});
        if (listaPrecioDetalles.size()>0){
         return    listaPrecioDetalles.get(0);
        }
        return null;
    }
}
