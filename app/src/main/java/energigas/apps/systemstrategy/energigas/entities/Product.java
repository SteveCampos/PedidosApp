package energigas.apps.systemstrategy.energigas.entities;

/**
 * Created by Steve on 21/07/2016.
 */

public class Product {
    private long idSqlite;
    private String idRemoto;
    private String name;
    private String unidadMedida;

    public Product(long idSqlite, String idRemoto, String name, String unidadMedida) {
        this.idSqlite = idSqlite;
        this.idRemoto = idRemoto;
        this.name = name;
        this.unidadMedida = unidadMedida;
    }

    public long getIdSqlite() {
        return idSqlite;
    }

    public void setIdSqlite(long idSqlite) {
        this.idSqlite = idSqlite;
    }

    public String getIdRemoto() {
        return idRemoto;
    }

    public void setIdRemoto(String idRemoto) {
        this.idRemoto = idRemoto;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }
}
