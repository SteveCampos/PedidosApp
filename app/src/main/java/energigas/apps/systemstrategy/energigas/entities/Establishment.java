package energigas.apps.systemstrategy.energigas.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jairc on 19/07/2016.
 */

public class Establishment {
    private String name;



    public Establishment(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static List<Establishment> getEstablishments(){
        List<Establishment> listEstablishment = new ArrayList<>();
        listEstablishment.add(new Establishment("Energigas"));
        listEstablishment.add(new Establishment("Productos Unión"));
        listEstablishment.add(new Establishment("Universidad Peruana Unión"));
        listEstablishment.add(new Establishment("Stark Company."));
        return listEstablishment;
    }
}
