package energigas.apps.systemstrategy.energigas.utils;

import android.support.annotation.NonNull;

import energigas.apps.systemstrategy.energigas.entities.Despacho;

/**
 * Created by kelvi on 09/08/2016.
 */

public class Constants {


    public static final String ACTION_EXPORT_SERVICE = "ACTION_EXPORT_SERVICE";
    public static final Long LONG_DISTANCE_0 = new Long(0);
    public static final String _CLMEXPORT = "CLMEXPORT";

    public static final String URL_FOR_PING = "www.google.com";

    public static final String URL_PRE = "http://";
    public static final String URL_POST_DISTRIBUCION = "/ServiceDistribucion.ashx";
    public static final String URL_POST_FACTURACION = "/RestfulApi.ashx";
    public static final String FIREBASE_CHILD_NOTIFICACIONES = "NOTIFICACIONES";
    public static final String FIREBASE_CHILD_FONDOS = "FONDOS";
    public static final String FIREBASE_CHILD_ATEN_PEDIDO = "ATEN_PEDIDO";
    public static final String FIREBASE_CHILD_ATENCION_PEDIDO = "ATENCION_PEDIDO";

    public static final int _CREADO = 2;
    public static final long MIN_TIME_BW_UPDATES = 1 * 1000;
    public static final long MIN_TIME_BW_UPDATES_5 = (60 * 1000) * 5;
    public static final int TIPO_MONEDA_SOLES = 36;
    public static final int TIPO_MONEDA_DOLARES = 37;
    public static final int PEDIDO_CREADO = -1;

    public static final int DESPACHO_CREADO = 38;

    /**
     * Estados AsyntaskListener
     */
    public static final int ERROR_PROCEDIMIENTO = -1;
    public static final int ERROR_CONEXION = -2;
    public static final int ERROR_GUARDAR = -3;
    public static final int OPERACION_EXITOSA = -4;

    public static final int CURRENT = 0;

    public static final int CAJA_ABIERTA = 31;


    public static final String USUARIO_IMAGEN = "USUARIO_IMAGEN";
    public static final String USUARIO_IMAGEN_PROFILE = "USUARIO_IMAGEN_PROFILE";


    /**
     * Nombres Caja Liquidacion
     **/

    public static final String CAJA_LIQUIDACION = "CAJA_LIQUIDACION";
    public static final String CAJA_LIQUIDACION_ID = "CAJA_LIQUIDACION_ID";
    /**
     * Nombres de la Session
     **/


    public static final String SESSION = "SESSION";
    public static final String IDUSUARIO = "IDUSUARIO";
    public static final String USUARIO = "USUARIO";
    public static final String NOMBRE = "NOMBRE";
    public static final String APELLIDO_PATERNO = "APELLIDO_PATERNO";
    public static final String APELLIDO_MATERNO = "APELLIDO_MATERNO";


    /**
     * Nombres de Establecimiento
     **/

    public static final String SESSION_ESTABLECIMIENTO = "SESSION_ESTABLECIMIENTO";
    public static final String IDESTABLECIMIENTO = "IDESTABLECIMIENTO";
    public static final String ESTABLECIMIENTO = "ESTABLECIMIENTO";

    /**
     * Nombres de Pedido
     **/

    public static final String SESSION_PEDIDO = "SESSION_PEDIDO";
    public static final String IDPEDIDO = "IDPEDIDO";

    /**
     * Pedido detalle
     **/

    public static final String SESSION_PEDIDO_DETALLE = "SESSION_PEDIDO_DETALLE";
    public static final String IDPEDIDODETALLE = "IDPEDIDODETALLE";

    /**
     * Nombres de Almacen
     **/

    public static final String SESSION_ALMACEN = "SESSION_ALMACEN";
    public static final String IDALMACEN = "IDALMACEN";

    /**
     * Nombres de Despacho
     **/

    public static final String SESSION_DESPACHO = "SESSION_DESPACHO";
    public static final String IDDESPACHO = "IDDESPACHO";

    /**
     * ID PARA OBTENER SERIE
     **/

    public static final int TIPO_ID_DEVICE_CELULAR = 3;

    public static final int TIPO_ID_COMPROBANTE_DESPACHO = 118;
    public static final int TIPO_ID_COMPROBANTE_FACTURA = 51;
    public static final int TIPO_ID_COMPROBANTE_BOLETA = 52;
    public static final int TIPO_ID_COMPROBANTE_GUIA = 76;
    public static final int TIPO_ID_COMPROBANTE_NOTA_CREDITO = 96;


    /**
     * PARA LISTAR OBJETOS
     **/
    public static final String CONCEPTO_TIPO_COMPROBANTE_VENTA = "Comprobante";
    public static final String CONCEPTO_FORMA_PAGO = "Forma Pago";
    public static final String CONCEPTO_FORMA_PAGO_COMPROBANTE_VENTA = "Comprobante Venta";
    public static final String CONCEPTO_TIPO_PAGO = "Tipo Pago";
    public static final String CONCEPTO_CAJA_PAGO = "Caja Pago";
    public static final String CONCEPTO_CAJA_GASTO = "Caja Gastos";
    public static final String CONCEPTO_TIPO_GASTO = "Tipo Gasto";
    public static final String CONCEPTO_CATEGORIA_TIPO_GASTO = "Categoria Tipo Gasto";
    public static final String CONCEPTO_CATEGORIA_TIPO_COMPROBANTE = "Tipo Comprobante";


    /**
     * Guardar los IDS de los despachos seleccionados
     **/

    public static final String DESPACHOS_IDS = "DESPACHOS_IDS";
    public static final String DESPACHOS_IDS_ITEMS = "DESPACHOS_IDS_ITEMS";


    public static final String SERVIDORES = "SERVIDORES";
    public static final String SERVIDORES_DISTRIBUCION = "SERVIDORES_DISTRIBUCION";
    public static final String SERVIDORES_FACTURACION = "SERVIDORES_FACTURACION";

    /**
     * Items Forma de pago
     **/
    public static final String FORMA_PAGO_CONTADO = "Contado";
    public static final String FORMA_PAGO_CREDITO = "Crédito";


    public static final int COMPROBANTE_CREADO_PENDIENTE = 23;

    public static final boolean COMPROBANTE_NO_ANULADO = false;
    public static final boolean COMPROBANTE_ANULADO = true;

    public static final int TIPO_VENTA_NORMAL = 90;

    public static final boolean NO_EXPORTADO = false;
    public static final boolean EXPORTADO = true;


    public static final int CLICK_EDITAR_CAJA_GASTO = 1;
    public static final int CLICK_ELIMINAR_CAJA_GASTO = 2;


    public static final int VENTA_BASE_IMPONIBLE = 0;
    public static final int VENTA_IMPORTE_IGV = 1;
    public static final int VENTA_TOTAL = 2;
    public static final int VENTA_SALDO = 3;
    public static final int VENTA_POR_IMPUESTO = 4;


    public static final int ESTABLECIMIENTO_EXTERNO = 46;


    public static final int CONCEPTO_CAT_MOV_VENTA_CONTADO = 102;
    public static final int CONCEPTO_CAT_MOV_VENTA_PLAN_PAGOS = 103;

    public static final int CONCEPTO_TIPO_MOV_INGRESO = 116;


    public static final boolean ESTADO_TRUE = true;
    public static final boolean ESTADO_FALSE = false;


    /**
     * Obtener porcentaje al mes
     **/
    public static final String CONCEPTO_CONCEPTO_PORCENTAJE_MES = "PorcentajeInteresMes";
    public static final String CONCEPTO_OBJETO_PORCENTAJE_MES = "Plan Pago";
    public static final int CONCEPTO_ESTADO_PORCENTAJE_MES = 1;


    public static final int CREDITO_ID = 106;
    public static final int CONTADO_ID = 105;


    /**
     * Para Exportar
     **/
    public static final int S_CREADO = 1;
    public static final int S_IMPORTADO = 2;
    public static final int S_ACTUALIZADO = 3;


    public static final String DEFINE_CUOTAS = "DEFINE_CUOTAS";
    public static final String DEFINE_TIPO_DESPACHO_SN = "DEFINE_TIPO_DESPACHO_SN";
    public static final String DEFINE_TIPO_DESPACHO_SN_ESTADO = "DEFINE_TIPO_DESPACHO_SN_ESTADO";
    public static final String DEFINE_CUOTAS_ESTADO = "DEFINE_CUOTAS_ESTADO";
    public static final String OBJECTS_LIST_DETALLE_CUOTAS = "OBJECTS_LIST_DETALLE_CUOTAS";

    public static final String OBTENER_CUOTAS = "OBTENER_CUOTAS";

    public static final int CLICK_EDITAR = 100;
    public static final int CLICK_ELIMINAR = 101;

    public static final int TABLA_DESPACHO = 1;
    public static final int TABLA_COMPROBANTE = 2;
    public static final int TABLA_GASTO = 3;

    /**
     * Tipo movimiento
     **/

    public static final int TIPO_MOV_EGRESO = 117;
    public static final int TIPO_MOV_INGRESO = 116;

    /**
     * Gastos
     **/
    public static final int CATEGORIA_MOV_GASTO = 104;
    public static final int GASTO_ESTADO_CREADO = 58;
    public static final int GASTO_ESTADO_ELIMINADO = 59;
    public static final int GASTO_EN_PLANTA = 101;


    public static final String SESSION_COMPROBANTE_VENTA = "SESSION_COMPROBANTE_VENTA";
    public static final String SESSION_COMPROBANTE_VENTA_ID = "SESSION_COMPROBANTE_VENTA_ID";


    public static final String SHA = "SHA";
    /**
     * Intent codigos
     **/
    public static final String INTENT_COME = "INTENT_COME";
    public static final int INTENT_COME_DESPACHO = 1;
    public static final int INTENT_COME_ORDER_STATION = 2;
    /*Cobranza Estados*/

    public static final String CONCEPTO_IGV_ID = "93";


    public static final int COBRANZA_PENDIENTE = 60;
    public static final int COBRANZA_COBRADO = 61;
    public static final int COBRANZA_ANULADO = 62;
    public static final int COBRANZA_PARCIAL = 63;
    public static final int ESTADO_ESTABLECIMIENTO_ATENDIDO = 42;
    public static final int ESTADO_ESTABLECIMIENTO_NO_ATENDIDO = 43;
    public static final int ESTADO_ESTABLECIMIENTO_PENDIENTE = 41;
    public static final int ESTADO_DESPACHO_ATENDIDO = 18;

    /**
     * ESTADO FACTURADO
     **/
    public static final int NO_FACTURADO = 64;
    public static final int FACTURADO = 65;
    public static final int FACTURADO_TOTAL = 66;


    public static final int TIPO_DOCUMENTO_RUC = 43;
    public static final int TIPO_DOCUMENTO_DNI = 42;

    public static final int TIPO_DOCUMENTO_BOLETA = 52;
    public static final int TIPO_DOCUMENTO_FACTURA = 51;


    public static final String TIPO_DOCUMENTO_ELECTRONICO_RUC = "3";
    public static final String TIPO_DOCUMENTO_ELECTRONICO_DNI = "4";

    public static final String TIPO_DOCUMENTO_ELECTRONICO_BOLETA = "55";
    public static final String TIPO_DOCUMENTO_ELECTRONICO_FACTURA = "54";


    public static final String DOCUMENTO_ELECTRONICO_NIU = "38";


    public static final String DOCUMENTO_ELECTRONICO_TIPO_PRECIO = "01";

    public static final String DOCUMENTO_ELECTRONICO_PEN = "PEN";


    public static final int DOCUMENTO_ELECTRONICO_OPERACION_ONEROSA = 8;


    public static final int EXPORTAR_TODO = 1000;


    /*Cargar Orden */


    public static final int ORDENCARGA_COMPRA = 141;
    public static final String ORDENCARGA_TRASCIEGO = "ORDENCARGA_TRASCIEGO";
    /**/

}
