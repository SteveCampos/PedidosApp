package energigas.apps.systemstrategy.energigas.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kelvin on 12/25/16.
 */

public class Historial {
    private int historialId;
    private String mes;
    private String dia;
    private String diaNUmero;
    private String cantidad;
    private String anno;
    private int semaforo;

    public Historial() {
    }

    public Historial(int historialId, String mes, String dia, String diaNUmero, String cantidad, String anno, int semaforo) {
        this.historialId = historialId;
        this.mes = mes;
        this.dia = dia;
        this.diaNUmero = diaNUmero;
        this.cantidad = cantidad;
        this.anno = anno;
        this.semaforo = semaforo;
    }

    public int getHistorialId() {
        return historialId;
    }

    public void setHistorialId(int historialId) {
        this.historialId = historialId;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getDiaNUmero() {
        return diaNUmero;
    }

    public void setDiaNUmero(String diaNUmero) {
        this.diaNUmero = diaNUmero;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getAnno() {
        return anno;
    }

    public void setAnno(String anno) {
        this.anno = anno;
    }

    public int getSemaforo() {
        return semaforo;
    }

    public void setSemaforo(int semaforo) {
        this.semaforo = semaforo;
    }

    public static List<Historial> getHistorialList() {
        List<Historial> historials = new ArrayList<>();
        historials.add(new Historial(1, "Enero", "Lunes", "12", "879", "2016", 1));
        historials.add(new Historial(1, "Enero", "Martes", "13", "631", "2016", 2));
        historials.add(new Historial(1, "Enero", "Miercoles", "14", "1000", "2016", 3));
        historials.add(new Historial(1, "Enero", "Jueves", "15", "127", "2016", 1));
        historials.add(new Historial(1, "Enero", "Viernes", "16", "700", "2016", 1));

        return historials;
    }
}
