package energigas.apps.systemstrategy.energigas.entities.fe;

import java.math.BigDecimal;

/**
 * Created by Steve on 22/11/2016.
 */

public class GrupoResumen extends DocumentoResumenDetalle {
    public int correlativoInicio;
    public int correlativoFin;
    public String moneda;
    public BigDecimal totalVenta;
    public BigDecimal totalDescuentos;
    public BigDecimal totalIgv;
    public BigDecimal totalIsc;
    public BigDecimal totalOtrosImpuestos;
    public BigDecimal gravadas;
    public BigDecimal exoneradas;
    public BigDecimal inafectas;
    public BigDecimal exportacion;
    public BigDecimal gratuitas;

    public int getCorrelativoInicio() {
        return correlativoInicio;
    }

    public void setCorrelativoInicio(int correlativoInicio) {
        this.correlativoInicio = correlativoInicio;
    }

    public int getCorrelativoFin() {
        return correlativoFin;
    }

    public void setCorrelativoFin(int correlativoFin) {
        this.correlativoFin = correlativoFin;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public BigDecimal getTotalVenta() {
        return totalVenta;
    }

    public void setTotalVenta(BigDecimal totalVenta) {
        this.totalVenta = totalVenta;
    }

    public BigDecimal getTotalDescuentos() {
        return totalDescuentos;
    }

    public void setTotalDescuentos(BigDecimal totalDescuentos) {
        this.totalDescuentos = totalDescuentos;
    }

    public BigDecimal getTotalIgv() {
        return totalIgv;
    }

    public void setTotalIgv(BigDecimal totalIgv) {
        this.totalIgv = totalIgv;
    }

    public BigDecimal getTotalIsc() {
        return totalIsc;
    }

    public void setTotalIsc(BigDecimal totalIsc) {
        this.totalIsc = totalIsc;
    }

    public BigDecimal getTotalOtrosImpuestos() {
        return totalOtrosImpuestos;
    }

    public void setTotalOtrosImpuestos(BigDecimal totalOtrosImpuestos) {
        this.totalOtrosImpuestos = totalOtrosImpuestos;
    }

    public BigDecimal getGravadas() {
        return gravadas;
    }

    public void setGravadas(BigDecimal gravadas) {
        this.gravadas = gravadas;
    }

    public BigDecimal getExoneradas() {
        return exoneradas;
    }

    public void setExoneradas(BigDecimal exoneradas) {
        this.exoneradas = exoneradas;
    }

    public BigDecimal getInafectas() {
        return inafectas;
    }

    public void setInafectas(BigDecimal inafectas) {
        this.inafectas = inafectas;
    }

    public BigDecimal getExportacion() {
        return exportacion;
    }

    public void setExportacion(BigDecimal exportacion) {
        this.exportacion = exportacion;
    }

    public BigDecimal getGratuitas() {
        return gratuitas;
    }

    public void setGratuitas(BigDecimal gratuitas) {
        this.gratuitas = gratuitas;
    }
}
