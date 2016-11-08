package energigas.apps.systemstrategy.energigas.utils;

import android.support.annotation.NonNull;

import energigas.apps.systemstrategy.energigas.entities.Despacho;

/**
 * Created by kelvi on 09/08/2016.
 */

public class Constants {
    public static final String _CLMEXPORT="CLMEXPORT";

    public static final int _CREADO = 2;

    public static final int PEDIDO_CREADO = -1;

    public static final int DESPACHO_CREADO = 38;

    /**Estados AsyntaskListener*/
    public static final int ERROR_PROCEDIMIENTO=-1;
    public static final int ERROR_CONEXION=-2;
    public static final int ERROR_GUARDAR=-3;
    public static final int OPERACION_EXITOSA=-4;

    public static final int CURRENT =0;


    /**Nombres Caja Liquidacion**/

    public static final String CAJA_LIQUIDACION="CAJA_LIQUIDACION";
    public static final String CAJA_LIQUIDACION_ID="CAJA_LIQUIDACION_ID";
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

    /**
     * Nombres de Despacho
     **/

    public static final String SESSION_DESPACHO="SESSION_DESPACHO";
    public static final String IDDESPACHO="IDDESPACHO";

    /** ID PARA OBTENER SERIE**/

    public static  final int TIPO_ID_DEVICE_CELULAR = 3;

    public static  final int TIPO_ID_COMPROBANTE_DESPACHO = 118;
    public static  final int TIPO_ID_COMPROBANTE_FACTURA = 51;
    public static  final int TIPO_ID_COMPROBANTE_BOLETA = 52;
    public static  final int TIPO_ID_COMPROBANTE_GUIA = 76;
    public static  final int TIPO_ID_COMPROBANTE_NOTA_CREDITO = 96;


    /** PARA LISTAR OBJETOS**/
    public static final String CONCEPTO_TIPO_COMPROBANTE_VENTA = "Comprobante";
    public static final String CONCEPTO_FORMA_PAGO = "Forma Pago";
    public static final String CONCEPTO_FORMA_PAGO_COMPROBANTE_VENTA = "Comprobante Venta";
    public static final String CONCEPTO_TIPO_PAGO = "Tipo Pago";
    public static final String CONCEPTO_CAJA_PAGO = "Caja Pago";
    public static final String CONCEPTO_CAJA_GASTO= "Caja Gastos";
    public static final String CONCEPTO_TIPO_GASTO = "Tipo Gasto";
    public static final String CONCEPTO_CATEGORIA_TIPO_GASTO ="Categoria Tipo Gasto";


    /**Guardar los IDS de los despachos seleccionados**/

    public static final String DESPACHOS_IDS = "DESPACHOS_IDS";
    public static final String DESPACHOS_IDS_ITEMS = "DESPACHOS_IDS_ITEMS";

    /** Items Forma de pago**/
    public static final String FORMA_PAGO_CONTADO = "Contado";
    public static final String FORMA_PAGO_CREDITO = "Crédito";


    public static final int COMPROBANTE_CREADO = 20;

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

    public static final int CONCEPTO_CAT_MOV_VENTA_CONTADO = 102;
    public static final int CONCEPTO_CAT_MOV_VENTA_PLAN_PAGOS = 103;

    public static final int CONCEPTO_TIPO_MOV_INGRESO = 116;


    public static final boolean ESTADO_TRUE = true;
    public static final boolean ESTADO_FALSE = false;


    /**Obtener porcentaje al mes**/
    public static final String CONCEPTO_CONCEPTO_PORCENTAJE_MES = "PorcentajeInteresMes";
    public static final String CONCEPTO_OBJETO_PORCENTAJE_MES = "Plan Pago";
    public static final int CONCEPTO_ESTADO_PORCENTAJE_MES = 1;


    public static final int CREDITO_ID=106;


    /**Para Exportar**/
    public static final int S_CREADO = 1;
    public static final int S_IMPORTADO = 2;
    public static final int S_ACTUALIZADO = 3;


    public static final String DEFINE_CUOTAS = "DEFINE_CUOTAS";
    public static final String DEFINE_CUOTAS_ESTADO = "DEFINE_CUOTAS_ESTADO";
    public static final String OBJECTS_LIST_DETALLE_CUOTAS = "OBJECTS_LIST_DETALLE_CUOTAS";

    public static final String OBTENER_CUOTAS = "OBTENER_CUOTAS";
    public static final int CLICK_EDITAR = 100;
    public static final int CLICK_ELIMINAR = 101;

    public static final int TABLA_DESPACHO = 1;
    public static final int TABLA_COMPROBANTE= 2;
    public static final int TABLA_GASTO= 3;

    /**Tipo movimiento**/

    public static final int TIPO_MOV_EGRESO = 117;
    public static final int TIPO_MOV_INGRESO = 116;

    /**Gastos**/
    public static final int CATEGORIA_MOV_GASTO = 104;
    public static final int GASTO_ESTADO_CREADO = 58;
    public static final int GASTO_ESTADO_ELIMINADO= 59;
    public static final int GASTO_EN_PLANTA = 101;

}
