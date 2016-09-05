package energigas.apps.systemstrategy.energigas.utils;

import android.support.annotation.NonNull;

/**
 * Created by kelvi on 09/08/2016.
 */

public class Constants {
    public static final String _CLMEXPORT="CLMEXPORT";

    public static final int _CREADO = 2;

    public static final int PEDIDO_CREADO = 9;

    /**Estados AsyntaskListener*/
    public static final int ERROR_PROCEDIMIENTO=-1;
    public static final int ERROR_CONEXION=-2;
    public static final int ERROR_GUARDAR=-3;
    public static final int OPERACION_EXITOSA=-4;

    public static final int CURRENT =0;


    /**Nombres de la Session**/


    public static final String SESSION="SESSION";
    public static final String IDUSUARIO="IDUSUARIO";
    public static final String USUARIO="USUARIO";
    public static final String NOMBRE="NOMBRE";
    public static final String APELLIDO_PATERNO="APELLIDO_PATERNO";
    public static final String APELLIDO_MATERNO="APELLIDO_MATERNO";


    /**Nombres de Establecimiento**/

    public static final String SESSION_ESTABLECIMIENTO="SESSION_ESTABLECIMIENTO";
    public static final String IDESTABLECIMIENTO="IDESTABLECIMIENTO";
    public static final String ESTABLECIMIENTO="ESTABLECIMIENTO";

    /**Nombres de Pedido**/

    public static final String SESSION_PEDIDO="SESSION_PEDIDO";
    public static final String IDPEDIDO="IDPEDIDO";

    /**Nombres de Almacen**/

    public static final String SESSION_ALMACEN="SESSION_ALMACEN";
    public static final String IDALMACEN="IDALMACEN";

    /**Nombres de Despacho**/

    public static final String SESSION_DESPACHO="SESSION_DESPACHO";
    public static final String IDDESPACHO="IDDESPACHO";

    /** ID PARA OBTENER SERIE**/

    public static  final int TIPO_ID_DEVICE_CELULAR = 3;

    public static  final int TIPO_ID_COMPROBANTE_DESPACHO = 118;
    public static  final int TIPO_ID_COMPROBANTE_FACTURA = 52;
    public static  final int TIPO_ID_COMPROBANTE_GUIA = 76;
    public static  final int TIPO_ID_COMPROBANTE_NOTA_CREDITO = 96;



}
