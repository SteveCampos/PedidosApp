package energigas.apps.systemstrategy.energigas.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jairc on 19/07/2016.
 */

public class Establishment {

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

    public Establishment(String estVName, String estVDescription, String estVObservation , String estVTelephone,String estTipoOperacion) {
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

    public static List<Establishment> getEstablishments(){
        List<Establishment> listEstablishment = new ArrayList<>();
        for (int i= 0; i<= 10; i++){
            listEstablishment.add(new Establishment("Agropacking Export S.A.","car. panamericana norte km. 1076","2.0","1.4 km","externo"));
            listEstablishment.add(new Establishment("Alsur Peru S.A.C.","av.Juan Pablo II N°  1130 MZA","4.0","7 km","interno"));
            listEstablishment.add(new Establishment("America Textil Peru","Sector Peruano Zona II MZA.","3.0","24 km","externo"));
        }
        return listEstablishment;
    }


}
