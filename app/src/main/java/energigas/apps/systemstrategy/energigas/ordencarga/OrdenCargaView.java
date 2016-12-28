package energigas.apps.systemstrategy.energigas.ordencarga;

/**
 * Created by Steve on 27/12/2016.
 */

public interface OrdenCargaView {
    void initVies();
    void initSpinnerTipoCarga();
    void initSpinnerTipoOrigen();
    void initSpinnerProducto();
    void initSpinnerUnidadMedia();
    void initAutocompleteRuc();
    void initAutoCumpleteNombreComercial();
    void mostrarCompraView();
    void mostrarTrasciegoView();
    void guardarOrdenCarga();

}
