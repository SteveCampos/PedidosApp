package energigas.apps.systemstrategy.energigas.entities.fe;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.orm.SugarRecord;
import com.orm.dsl.Unique;


/**
 * Created by Steve on 12/12/2016.
 */

public class CertificadoDigital extends SugarRecord {
    private String trama;
    private String password;
    private String privateEntry;
    private String passwordEntry;
    @Unique
    private int entidadId;

    public CertificadoDigital() {
    }

    public CertificadoDigital(String trama, String password, String privateEntry, String passwordEntry, int entidadId) {
        this.trama = trama;
        this.password = password;
        this.privateEntry = privateEntry;
        this.passwordEntry = passwordEntry;
        this.entidadId = entidadId;
    }

    public int getEntidadId() {
        return entidadId;
    }

    public void setEntidadId(int entidadId) {
        this.entidadId = entidadId;
    }

    public String getTrama() {
        return trama;
    }

    public void setTrama(String trama) {
        this.trama = trama;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPrivateEntry() {
        return privateEntry;
    }

    public void setPrivateEntry(String privateEntry) {
        this.privateEntry = privateEntry;
    }

    public String getPasswordEntry() {
        return passwordEntry;
    }

    public void setPasswordEntry(String passwordEntry) {
        this.passwordEntry = passwordEntry;
    }
}
