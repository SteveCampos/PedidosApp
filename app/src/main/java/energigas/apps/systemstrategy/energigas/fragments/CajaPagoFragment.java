package energigas.apps.systemstrategy.energigas.fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.orm.SugarTransactionHelper;

import java.util.List;

import butterknife.ButterKnife;
import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.adapters.CajaPagoAdapter;
import energigas.apps.systemstrategy.energigas.entities.CajaComprobante;
import energigas.apps.systemstrategy.energigas.entities.CajaLiquidacion;
import energigas.apps.systemstrategy.energigas.entities.CajaMovimiento;
import energigas.apps.systemstrategy.energigas.entities.CajaPago;
import energigas.apps.systemstrategy.energigas.entities.Cliente;
import energigas.apps.systemstrategy.energigas.entities.ComprobanteVenta;
import energigas.apps.systemstrategy.energigas.entities.ComprobanteVentaDetalle;
import energigas.apps.systemstrategy.energigas.entities.Establecimiento;
import energigas.apps.systemstrategy.energigas.entities.PlanPago;
import energigas.apps.systemstrategy.energigas.entities.PlanPagoDetalle;
import energigas.apps.systemstrategy.energigas.entities.Usuario;
import energigas.apps.systemstrategy.energigas.utils.Constants;
import energigas.apps.systemstrategy.energigas.utils.Session;
import energigas.apps.systemstrategy.energigas.utils.Utils;

/**
 * Created by Kike on 1/08/2016.
 */

public class CajaPagoFragment extends Fragment implements CajaPagoAdapter.OnCajaPagoClickListener {

    private static final String TAG = CajaPagoFragment.class.getSimpleName();

    private RecyclerView recyclerView;

    public CajaPagoFragment() {

    }

    private List<PlanPagoDetalle> planPagoDetalleList;
    private List<ComprobanteVentaDetalle> comprobanteVentaDetalleList;
    CajaPagoAdapter adapter;
    AlertDialog alertDialog;
    /*Objetos*/
    private Usuario mUsuario;
    private CajaLiquidacion mCajaLiquidacion;
    private Establecimiento mEstablecimiento;
    private Cliente mCliente;
    /*Finz*/
    //private CajaLiquidacion mCajaLiquidacion;

    private ComprobanteVenta mComprobanteVenta;
    /*Saved Multiples Tables*/
    private CajaMovimiento mCajaMovimiento;
    private PlanPagoDetalle mPlanPagoDetalle;
    private CajaComprobante mCajaComprobante;
    private CajaPago mCajaPago;
    private PlanPago mPlanPago;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragmen_charges, container, false);
        ButterKnife.bind(this, rootView);
        //addInsertCobranza();
       /*Entity*/
        mUsuario = Usuario.find(Usuario.class, "usu_I_Usuario_Id = ? ", new String[]{Session.getSession(getActivity()).getUsuIUsuarioId() + ""}).get(Constants.CURRENT);
        mCajaLiquidacion = CajaLiquidacion.find(CajaLiquidacion.class, "liq_Id = ?", new String[]{Session.getCajaLiquidacion(getActivity()).getLiqId() + ""}).get(Constants.CURRENT);
        mEstablecimiento = Establecimiento.find(Establecimiento.class, "est_I_Establecimiento_Id = ? ", new String[]{Session.getSessionEstablecimiento(getActivity()).getEstIEstablecimientoId() + ""}).get(Constants.CURRENT);
        mCliente = Cliente.find(Cliente.class, " CLI_I_CLIENTE_ID = ? ", new String[]{mEstablecimiento.getEstIClienteId() + ""}).get(Constants.CURRENT);


         /*FinEntity*/
        insertables(mPlanPagoDetalle);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.rv_charges);
        adapter = new CajaPagoAdapter(getPlanPagoDetalleList(), getActivity(), this);
        recyclerView.setAdapter(adapter);
        //recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return rootView;
    }

    @Override
    public void onCajaPagoClickListener(final TextView mEstado, final TextView mtotal, final PlanPago mPlanPago, final ComprobanteVenta mComprobanteVenta, final PlanPagoDetalle ppdetalle, final View view) {

       /*Dialog onClikListener*/
//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final View layout_dialog_cobranzas = View.inflate(getActivity(), R.layout.dialog_cobranza_onclic, null);
//                final Button buttonacept = (Button) layout_dialog_cobranzas.findViewById(R.id.btn_sid);
//                final Button buttondene = (Button) layout_dialog_cobranzas.findViewById(R.id.btn_nod);
//                final EditText editTextmont = (EditText) layout_dialog_cobranzas.findViewById(R.id.dialog_id_pagod);
//
//                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//                builder.setTitle("PAGO TOTAL : S./ " + Math.abs(planPagoDetalle.getMontoAPagar()));
//                editTextmont.setText(Math.abs(planPagoDetalle.getMontoAPagar()) + "");
//                mCajaMovimiento = CajaMovimiento.find(CajaMovimiento.class,"caj_Mov_Id = ?",new String[]{planPagoDetalle.getCajMovId()+""}).get(Constants.CURRENT);
//                // set dialog message
//                builder
//                        .setView(layout_dialog_cobranzas)
//                        .setCancelable(true);
//                buttonacept.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        double rest = planPagoDetalle.getMontoAPagar() - planPagoDetalle.getMontoAPagar();
//                        //  editTextmont.setText(comprobanteVenta.getTotal()+"");//dialog_id_pagod
//                        mtotal.setText("S./ " + Math.abs(rest) + "");
//                        mEstado.setText("COBRADO");
//                        mEstado.setTextColor(Color.GREEN);
//                        planPagoDetalle.setMontoAPagar(rest);
//                        mCajaMovimiento.setEstado(false);
//
//                        planPagoDetalle.save();
//                        mCajaMovimiento.save();
//                        alertDialog.dismiss();
//
//
//                    }
//                });
//                buttondene.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        alertDialog.dismiss();
//                    }
//                });
//                // create alert dialog
//                alertDialog = builder.create();
//                // show it
//                alertDialog.show();
//
//            }
//        });
       /*Dialog OnLogClikListeener*/
        view.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {

                final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        getActivity());
                alertDialogBuilder
                        .setMessage("Desea Anular Cobros?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // anularcobros(cjmovimiento,mPlanPago);
                                /*Anulando Cobros actulizando el TipoMovId=117*/
                                final long idCajaMovimiento = CajaMovimiento.findWithQuery(CajaMovimiento.class, Utils.getQueryNumberCajaMov(), null).get(Constants.CURRENT).getCajMovId();
                               /*cjmovimiento.setTipoMovId(117);
                                cjmovimiento.setReferencia(mPlanPago.getSerie());
                                cjmovimiento.save();*/
                                //El Saldo queda 0 y cambia de EstadoID=23
                                mComprobanteVenta.setSaldo(0);
                                mComprobanteVenta.setEstadoId(23);
                                mComprobanteVenta.save();
                                //Referencia va el numero comprobante que se esta anulando
                                // Log.d(TAG,"CajaMovimiento:"+cjmovimiento.getReferencia());

                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // if this button is clicked, just close
                                // the dialog box and do nothing
                                dialog.cancel();
                            }
                        });


                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();


//                final View layout_dialog_expenses = View.inflate(getActivity(), R.layout.dialog_cobranza, null);
//                final Button btnsave = (Button) layout_dialog_expenses.findViewById(R.id.btn_si);
//                final Button btn_cancel = (Button) layout_dialog_expenses.findViewById(R.id.btn_no);
//                final EditText txtpago = (EditText) layout_dialog_expenses.findViewById(R.id.dialog_id_pago);
//                final EditText txtfechapago = (EditText) layout_dialog_expenses.findViewById(R.id.fecha_pago);
//                txtfechapago.setText(planPagoDetalle.getFechaCobro());
//                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
//                        getActivity());
//                final long idCajaMovimiento = CajaMovimiento.findWithQuery(CajaMovimiento.class, Utils.getQueryNumberCajaMov(), null).get(Constants.CURRENT).getCajMovId();
//                //final long idPlanPagoDetalle= PlanPagoDetalle.findWithQuery(PlanPagoDetalle.class,Utils.getQueryNumberPlanPagoDetalle(),null).get(Constants.CURRENT).getPlanPaDeId();
//                final int idPlanPagoD = PlanPagoDetalle.findWithQuery(PlanPagoDetalle.class, Utils.getQueryNumberPlanPagoDetalle(), null).get(Constants.CURRENT).getPlanPaDeId();
//
//                // set title
//                alertDialogBuilder.setTitle("Deduda Porroga : S./ " + Math.abs(planPagoDetalle.getMontoAPagar()));
//
//                // set dialog message
//                alertDialogBuilder
//                        .setView(layout_dialog_expenses)
//                        .setCancelable(true);
//                btnsave.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        //     double mPagoPorroga = Double.parseDouble(String.valueOf(txtpago.getText().toString().trim()));
//                        if (txtpago.getText().toString().equals("")) {
//                            txtpago.setError("Llene los campos necesarios");
//                        } else {
//                            double mPagoPorroga = Double.parseDouble(String.valueOf(txtpago.getText().toString().trim()));
//                            if (txtpago.getText().toString().trim().length() <= 0) {
//                                txtpago.setError("Llene los campos necesarios");
//                            } else if (mPagoPorroga > planPagoDetalle.getMontoAPagar()) {
//                                txtpago.setError("Ingrese datos Correctos");
//                            } else if (mPagoPorroga == planPagoDetalle.getMontoAPagar()) {
//                                txtpago.setError("Ingrese datos Correctos");
//                            } else {
//                                double resultado = mPagoPorroga - planPagoDetalle.getMontoAPagar();
//
//                                planPagoDetalle.setMontoAPagar(resultado);
//                                planPagoDetalle.save();
//                                mtotal.setText("S./ " + Math.abs(resultado));
//                                mPlanPagoDetalle = new PlanPagoDetalle(12,
//                                        idPlanPagoD,
//                                        "FEcha",
//                                        resultado,
//                                        true,
//                                        10.2,
//                                        7.4,
//                                        40.0,
//                                        "Jueves,10 de Noviembre",
//                                        12,
//                                        "Fecha_Accion",
//                                        idCajaMovimiento
//                                );
//                                mPlanPagoDetalle.save();
//                                saveMultipleTables(resultado, idCajaMovimiento);
//
//                                alertDialog.dismiss();
//
//                            }
//                        }
//                    }
//                });
//                btn_cancel.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        alertDialog.dismiss();
//                    }
//                });
//
//
//                // create alert dialog
//                alertDialog = alertDialogBuilder.create();
//                // show it
//                alertDialog.show();


                return false;
            }
        });
    }

    @Override
    public void onCajaPagoListener(int action, PlanPagoDetalle pagoDetalle, TextView mEstado, TextView mtotal) {

        switch (action) {
            case Constants.CLICK_EDITAR_CAJA_GASTO:
                pagoporroga(pagoDetalle, mtotal);
                break;
            case Constants.CLICK_ELIMINAR_CAJA_GASTO:
                pagototal(pagoDetalle, mEstado, mtotal);
            default:
                break;
        }
    }


    private List<PlanPagoDetalle> getPlanPagoDetalleList() {
        List<PlanPagoDetalle> mPlanPagoDetalles = PlanPagoDetalle.findWithQuery(PlanPagoDetalle.class
                , " SELECT * FROM Plan_Pago_Detalle");

        for (int i = 0; i < mPlanPagoDetalles.size(); i++) {
            PlanPagoDetalle planPagoDetalle = mPlanPagoDetalles.get(i);
            mPlanPagoDetalles.set(i, planPagoDetalle);
        }
        return mPlanPagoDetalles;
    }

/*--------------*/
    private void saveMultipleTables(final Double mImporte, final long idCajaMovimiento) {
//        idComprobante_venta=ComprobanteVenta.findWithQuery(ComprobanteVenta.class,Utils.getQueryNumber(),null).get(Constants.CURRENT).getCompId();

        SugarTransactionHelper.doInTransaction(new SugarTransactionHelper.Callback() {
            @Override
            public void manipulateInTransaction() {
                //save here!! multple tables
                     /*Table Comprobante_Venta*/
/*
                String serie ="";
                     mComprobanteVenta = new ComprobanteVenta(idComprobante_venta,
                        serie,
                        "numDoc",
                        12,
                        12,//Aqui es es el EstadoId
                        Utils.getDatePhone(),
                        12.2,
                        12.2,
                        20.2,
                        12,
                        12,//ClienteID
                        12,
                        true,
                        20.2,
                        12,//LiquidacionId
                        12,//TipoVentaId
                        12,//Establecimiento ID
                        false,
                        "String docIdentidad",
                        "Valor de Resumen",
                        "Juanito",
                        "Direccion Cliente",
                        Utils.getDatePhone(),
                        "Fecha de Actualizacion",
                        20.2,
                        comprobanteVentaDetalleList,
                        mCajaMovimiento,
                        mPlanPago
                );
                mComprobanteVenta.save();
                Log.d(TAG,"COUNT COMPROBANTE_VENTA"+mComprobanteVenta);


*/

                /*Table Caja_Movimiento*/
                mCajaMovimiento = new CajaMovimiento(idCajaMovimiento,
                        1,
                        1,
                        "Soles",
                        mImporte,
                        true,
                        Utils.getDatePhone(),
                        "MotivoAnulado",
                        "Referencia",
                        1,
                        "Fecha_Accion",
                        "Referencia_Android",
                        117,//TipoMovId=117
                        mCajaPago,
                        mCajaComprobante
                );
                mCajaMovimiento.save();
                /*Table Caja_Comprobante */
                final long idCajaComprobante = CajaComprobante.findWithQuery(CajaComprobante.class, Utils.getQueryNumberCajaComprobante(), null).get(Constants.CURRENT).getCajCompId();
                mCajaComprobante = new CajaComprobante(idCajaComprobante,
                        idCajaMovimiento,
                        12,
                        mImporte,
                        12,
                        Utils.getDatePhone()
                );
                mCajaComprobante.save();
                /*Table Caja_Pago*/
                final long idCajaPago = CajaPago.findWithQuery(CajaPago.class, Utils.getQueryNumberCajaPago(), null).get(Constants.CURRENT).getCajPagId();
                mCajaPago = new CajaPago(idCajaPago,
                        mImporte,
                        idCajaMovimiento,
                        12,
                        "String Fecha_Accion",
                        false,
                        12,
                        true);
                mCajaPago.save();
                /*Table ComprobanteVenta*/
                final long idComprobante_venta = ComprobanteVenta.findWithQuery(ComprobanteVenta.class, Utils.getQueryNumber(), null).get(Constants.CURRENT).getCompId();
                mComprobanteVenta = new ComprobanteVenta(idComprobante_venta,
                        "",
                        "numDoc",
                        12,
                        12,//Aqui es es el EstadoId
                        Utils.getDatePhone(),
                        12.2,
                        12.2,
                        20.2,
                        12,
                        12,//ClienteID
                        12,
                        true,
                        20.2,
                        mCajaLiquidacion.getLiqId(),//LiquidacionId
                        12,//TipoVentaId
                        mEstablecimiento.getEstIEstablecimientoId(),//Establecimiento ID
                        false,
                        "String docIdentidad",
                        "Valor de Resumen",
                        "Juanito",
                        "Direccion Cliente",
                        Utils.getDatePhone(),
                        "Fecha de Actualizacion",
                        20.2,
                        comprobanteVentaDetalleList,
                        mCajaMovimiento,
                        mPlanPago
                );
                mComprobanteVenta.save();


            }

            @Override
            public void errorInTransaction(String error) {

            }
        });
    }

    /*Insertacion mediante metodo*/
    public void addInsertCobranza() {

        long idComprobante_venta = ComprobanteVenta.findWithQuery(ComprobanteVenta.class, Utils.getQueryNumber(), null).get(Constants.CURRENT).getCompId();


        String serie = "F001-000216";
          /*Table Plan_Pago*/
        final long idPlanPago = PlanPago.findWithQuery(PlanPago.class, Utils.getQueryNumberPlanPago(), null).get(Constants.CURRENT).getPlanPaId();
        mPlanPago = new PlanPago(idPlanPago,
                idComprobante_venta,
                Utils.getDatePhone(),
                serie,
                "String NumDoc",
                "String Glosa",
                true,
                20.1,
                12,
                "String Fecha Accion",
                planPagoDetalleList);
        mPlanPago.save();
        /*Table PLan_Pago_Detalle*/
        final int idPlanPagoD = PlanPagoDetalle.findWithQuery(PlanPagoDetalle.class, Utils.getQueryNumberPlanPagoDetalle(), null).get(Constants.CURRENT).getPlanPaDeId();
        final long idCajaMovimiento = CajaMovimiento.findWithQuery(CajaMovimiento.class, Utils.getQueryNumberCajaMov(), null).get(Constants.CURRENT).getCajMovId();
        double resultado = 23.1 - 10.2;
        mPlanPagoDetalle = new PlanPagoDetalle(idPlanPago,
                idPlanPagoD,
                "FEcha",
                20.2,
                true,
                10.2,
                7.4,
                40.0,
                "Jueves,10 de Noviembre",
                12,
                "Fecha_Accion",
                idCajaMovimiento
        );
        mPlanPagoDetalle.save();
        saveMultipleTables(resultado, idCajaMovimiento);

    }

    private void pagototal(final PlanPagoDetalle detallepago, final TextView mestado, final TextView mtotal) {
        final View layout_dialog_cobranzas = View.inflate(getActivity(), R.layout.dialog_cobranza_onclic, null);
        final Button buttonacept = (Button) layout_dialog_cobranzas.findViewById(R.id.btn_sid);
        final Button buttondene = (Button) layout_dialog_cobranzas.findViewById(R.id.btn_nod);
        final EditText editTextmont = (EditText) layout_dialog_cobranzas.findViewById(R.id.dialog_id_pagod);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("PAGO TOTAL : S./ " + Math.abs(detallepago.getMontoAPagar()));
        editTextmont.setText(Math.abs(detallepago.getMontoAPagar()) + "");
        // mCajaMovimiento = CajaMovimiento.find(CajaMovimiento.class, "caj_Mov_Id = ?", new String[]{detallepago.getCajMovId() + ""}).get(Constants.CURRENT);
        // set dialog message
        builder
                .setView(layout_dialog_cobranzas)
                .setCancelable(true);
        buttonacept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double rest = detallepago.getMontoAPagar() - detallepago.getMontoAPagar();
                //  editTextmont.setText(comprobanteVenta.getTotal()+"");//dialog_id_pagod
                mtotal.setText("S./ " + Math.abs(rest) + "");
                /*mestado.setText("COBRADO");
                mestado.setTextColor(Color.GREEN);*/
                detallepago.setMontoAPagar(rest);
                mCajaMovimiento.setEstado(false);

                detallepago.save();
                mCajaMovimiento.save();
                alertDialog.dismiss();


            }
        });
        buttondene.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        // create alert dialog
        alertDialog = builder.create();
        // show it
        alertDialog.show();

    }

    private void pagoporroga(final PlanPagoDetalle detallepago, final TextView mtotal) {
        final View layout_dialog_expenses = View.inflate(getActivity(), R.layout.dialog_cobranza, null);
        final Button btnsave = (Button) layout_dialog_expenses.findViewById(R.id.btn_si);
        final Button btn_cancel = (Button) layout_dialog_expenses.findViewById(R.id.btn_no);
        final EditText txtpago = (EditText) layout_dialog_expenses.findViewById(R.id.dialog_id_pago);
        final EditText txtfechapago = (EditText) layout_dialog_expenses.findViewById(R.id.fecha_pago);
        txtfechapago.setText(detallepago.getFechaCobro());
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                getActivity());
        final long idCajaMovimiento = CajaMovimiento.findWithQuery(CajaMovimiento.class, Utils.getQueryNumberCajaMov(), null).get(Constants.CURRENT).getCajMovId();
        //final long idPlanPagoDetalle= PlanPagoDetalle.findWithQuery(PlanPagoDetalle.class,Utils.getQueryNumberPlanPagoDetalle(),null).get(Constants.CURRENT).getPlanPaDeId();
        final int idPlanPagoD = PlanPagoDetalle.findWithQuery(PlanPagoDetalle.class, Utils.getQueryNumberPlanPagoDetalle(), null).get(Constants.CURRENT).getPlanPaDeId();

        // set title
        alertDialogBuilder.setTitle("Deduda Porroga : S./ " + Utils.formatDouble(detallepago.getMontoAPagar()));

        // set dialog message
        alertDialogBuilder
                .setView(layout_dialog_expenses)
                .setCancelable(true);
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //     douesble mPagoPorroga = Double.parseDouble(String.valueOf(txtpago.getText().toString().trim()));
                if (txtpago.getText().toString().equals("")) {
                    txtpago.setError("Llene los campos necesarios");
                } else {
                    double mPagoPorroga = Double.parseDouble(String.valueOf(txtpago.getText().toString().trim()));
                    if (txtpago.getText().toString().trim().length() <= 0) {
                        txtpago.setError("Llene los campos necesarios");
                    } else if (mPagoPorroga > detallepago.getMontoAPagar()) {
                        txtpago.setError("Ingrese datos Correctos");
                    } else if (mPagoPorroga == detallepago.getMontoAPagar()) {
                        txtpago.setError("Ingrese datos Correctos");
                    } else {
                        double resultado = mPagoPorroga - detallepago.getMontoAPagar();

                        detallepago.setMontoAPagar(resultado);
                        detallepago.save();
                        mtotal.setText("S./ " + Utils.formatDouble(resultado));
                        mPlanPagoDetalle = new PlanPagoDetalle(12,
                                idPlanPagoD,
                                "FEcha",
                                resultado,
                                true,
                                10.2,
                                7.4,
                                40.0,
                                "Jueves,10 de Noviembre",
                                12,
                                "Fecha_Accion",
                                idCajaMovimiento
                        );
                        mPlanPagoDetalle.save();
                        saveMultipleTables(resultado, idCajaMovimiento);

                        alertDialog.dismiss();

                    }
                }
            }
        });
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });


        // create alert dialog
        alertDialog = alertDialogBuilder.create();
        // show it
        alertDialog.show();

    }


    private void anularcobros(final CajaMovimiento movimiento, final PlanPago pago) {
        SugarTransactionHelper.doInTransaction(new SugarTransactionHelper.Callback() {
            @Override
            public void manipulateInTransaction() {
                movimiento.setReferencia(pago.getSerie());
                final long idCajaMovimiento = CajaMovimiento.findWithQuery(CajaMovimiento.class, Utils.getQueryNumberCajaMov(), null).get(Constants.CURRENT).getCajMovId();
                mCajaMovimiento = new CajaMovimiento(idCajaMovimiento,
                        12,
                        12,
                        "Soles",
                        0.0,
                        true,
                        "",
                        "",
                        pago.getSerie(),//Se registra pero con el numero de Serie
                        12,
                        "",
                        "",
                        117,//guardando tipmoV117
                        mCajaPago,
                        mCajaComprobante
                );
            }

            @Override
            public void errorInTransaction(String error) {
            }
        });
        /*Anulando Cobros actulizando el TipoMovId=117*/
        mCajaMovimiento.setTipoMovId(117);
        mCajaMovimiento.setReferencia(mPlanPago.getSerie());
        mCajaMovimiento.save();
        /*El Saldo queda 0 y cambia de EstadoID=23*/
        mComprobanteVenta.setSaldo(0);
        mComprobanteVenta.setEstadoId(23);
        mComprobanteVenta.save();
        /*Referencia va el numero comprobante que se esta anulando*/

        Log.d(TAG, "mCajaMovimiento:" + mCajaMovimiento + "mComprobanteVenta" + mComprobanteVenta);

    }
/*------------------*/

    private void insertables(final PlanPagoDetalle detallepago) {
        SugarTransactionHelper.doInTransaction(new SugarTransactionHelper.Callback() {
            @Override
            public void manipulateInTransaction() {
                //save here!
                final long idComp = PlanPago.findWithQuery(PlanPago.class, Utils.getQueryNumberPlanPago(), null).get(Constants.CURRENT).getPlanPaId();
                /*Table CajaMovimiento , PlanPagoDetalle*/
                final long idCajaMovimiento = CajaMovimiento.findWithQuery(CajaMovimiento.class, Utils.getQueryNumberCajaMov(), null).get(Constants.CURRENT).getCajMovId();
                mCajaMovimiento = new CajaMovimiento(idCajaMovimiento,
                        mCajaLiquidacion.getLiqId(),//CajaLiquidacion
                        12,
                        "Soles",
                        detallepago.getImporte(),
                        true,
                        "",
                        "",
                        "",//Se registra pero con el numero de Serie
                        mUsuario.getUsuIUsuarioId(),//UsuarioID
                        "",
                        "",
                        117,//guardando tipmoV117
                        null,
                        null
                );
                mCajaMovimiento.save();
                Log.d(TAG, "mCajaMovimiento Insert: "+ mCajaMovimiento.getImporte());
                detallepago.setCajMovId(idCajaMovimiento);
                detallepago.save();
                /*Table CajaPago*/
                final long idCajaPago = CajaPago.findWithQuery(CajaPago.class, Utils.getQueryNumberCajaPago(), null).get(Constants.CURRENT).getCajPagId();
                mCajaPago = new CajaPago(idCajaPago,
                        detallepago.getImporte(),
                        idCajaMovimiento,
                        mUsuario.getUsuIUsuarioId(),
                        "FEcha_Accion",
                        false,
                        12,//TipoPagoId
                        false
                );
                mCajaPago.save();
                /*Table ComprobanteVenta*/
                final long idComprobante_venta = ComprobanteVenta.findWithQuery(ComprobanteVenta.class, Utils.getQueryNumber(), null).get(Constants.CURRENT).getCompId();
                mComprobanteVenta = new ComprobanteVenta(idComprobante_venta,
                        detallepago.getFechaCobro(),
                        "numDoc",
                        12,
                        Constants.COBRANZA_PENDIENTE,//Aqui es es el EstadoId
                        Utils.getDatePhone(),
                        12.2,
                        12.2,
                        20.2,
                        12,
                        mCliente.getCliIClienteId(),//ClienteID
                        12,
                        true,
                        20.2,
                        mCajaLiquidacion.getLiqId(),//LiquidacionId
                        12,//TipoVentaId
                        mEstablecimiento.getEstIEstablecimientoId(),//Establecimiento ID
                        false,
                        "String docIdentidad",
                        "Valor de Resumen",
                        "Juanito",
                        "Direccion Cliente",
                        Utils.getDatePhone(),
                        "Fecha de Actualizacion",
                        20.2,
                        null,
                        null,
                        null
                );
                mComprobanteVenta.save();
                Log.d(TAG,"mComprobanteVenta"+mComprobanteVenta.getEstadoId());
            }

            @Override
            public void errorInTransaction(String error) {

            }
        });
    }


}
