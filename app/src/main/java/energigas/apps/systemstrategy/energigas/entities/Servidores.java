package energigas.apps.systemstrategy.energigas.entities;

import com.orm.SugarRecord;

/**
 * Created by kelvi on 21/12/2016.
 */

public class Servidores extends SugarRecord {

    private String name;
    private String description;

    public Servidores() {
    }

    public Servidores( String name, String description) {

        this.name = name;
        this.description = description;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
