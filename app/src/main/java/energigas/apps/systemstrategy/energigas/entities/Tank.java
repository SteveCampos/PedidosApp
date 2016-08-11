package energigas.apps.systemstrategy.energigas.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Steve on 10/08/2016.
 */
public class Tank {


    public Tank() {
    }

    public static List<Tank> getList() {
        List<Tank> tanks = new ArrayList<>();

        tanks.add(new Tank());
        tanks.add(new Tank());
        tanks.add(new Tank());

        return tanks;
    }
}
