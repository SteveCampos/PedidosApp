package energigas.apps.systemstrategy.energigas.ordencarga;

/**
 * Created by Steve on 27/12/2016.
 */

public interface OrdenCargaView {
    void initVies();
    void handleSpinnerTipoCarga();
    void initAutocompleteRuc();
    void initAutoCumpleteNombreComercial();
    void mostrarCompraView();
    void mostrarTrasciegoView();
    void guardarOrdenCarga();

}
