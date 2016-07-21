package energigas.apps.systemstrategy.energigas.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jairc on 19/07/2016.
 */

public class Station {

    private String estVDescription;

    private String estVObservation;
    private String estVTelephone;
    private String estVName;
    private String estTipoOperacion;



    public String getEstVName() {
        return estVName;
    }

    public void setEstVName(String estVName) {
        this.estVName = estVName;
    }

    public String getEstTipoOperacion() {
        return estTipoOperacion;
    }

    public void setEstTipoOperacion(String estTipoOperacion) {
        this.estTipoOperacion = estTipoOperacion;
    }

    public Station(String estVName, String estVDescription, String estVObservation , String estVTelephone, String estTipoOperacion) {
        this.estVName = estVName;

        this.estVDescription = estVDescription;
        this.estVObservation = estVObservation;
        this.estVTelephone = estVTelephone;
        this.estTipoOperacion=estTipoOperacion;
    }

    public String getEstVObservation() {

        return estVObservation;
    }

    public void setEstVObservation(String estVObservation) {
        this.estVObservation = estVObservation;
    }

    public String getEstVDescription() {
        return estVDescription;
    }

    public void setEstVDescription(String estVDescription) {
        this.estVDescription = estVDescription;
    }


    public String getEstVTelephone() {
        return estVTelephone;
    }

    public void setEstVTelephone(String estVTelephone) {
        this.estVTelephone = estVTelephone;
    }

    public static List<Station> getEstablishments(){
        List<Station> listStation = new ArrayList<>();
        for (int i= 0; i<= 10; i++){
            listStation.add(new Station("Agropacking Export S.A.","car. panamericana norte km. 1076","2.0","1.4 km","externo"));
            listStation.add(new Station("Alsur Peru S.A.C.","av.Juan Pablo II N°  1130 MZA","4.0","7 km","interno"));
            listStation.add(new Station("America Textil Peru","Sector Peruano Zona II MZA.","3.0","24 km","externo"));
        }
        return listStation;
    }


}
