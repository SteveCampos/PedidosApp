package energigas.apps.systemstrategy.energigas.activities;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.orm.SugarTransactionHelper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.adapters.ConceptoAdapter;
import energigas.apps.systemstrategy.energigas.adapters.CustomTabsAdapter;

import energigas.apps.systemstrategy.energigas.asyntask.ExportTask;
import energigas.apps.systemstrategy.energigas.entities.CajaGasto;
import energigas.apps.systemstrategy.energigas.entities.CajaLiquidacion;
import energigas.apps.systemstrategy.energigas.entities.CajaMovimiento;
import energigas.apps.systemstrategy.energigas.entities.Concepto;
import energigas.apps.systemstrategy.energigas.entities.InformeGasto;
import energigas.apps.systemstrategy.energigas.entities.SyncEstado;
import energigas.apps.systemstrategy.energigas.entities.Usuario;
import energigas.apps.systemstrategy.energigas.fragments.CajaGastoFragment;
import energigas.apps.systemstrategy.energigas.interfaces.ExportObjectsListener;
import energigas.apps.systemstrategy.energigas.utils.Constants;
import energigas.apps.systemstrategy.energigas.utils.Session;
import energigas.apps.systemstrategy.energigas.utils.Utils;

/**
 * Created by kikerojas on 30/07/2016.
 */

public class CajaGastoActivity extends AppCompatActivity implements View.OnClickListener, CajaGastoFragment.OnCajaGastoClickListener, ExportObjectsListener {
    private static final String TAG = CajaGastoActivity.class.getSimpleName();
    CajaLiquidacion cajaLiquidacion;
    CajaGasto expenses;
    Usuario usuario;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.viewCharges)
    ViewPager mainViewPager;
    @BindView(R.id.tablayout)
    TabLayout tabLayout;
    @BindView(R.id.tv_total)
    TextView txtotal;
    @BindView(R.id.tv_date)
    TextView txtdate;
    @BindView(R.id.btndialog)
    FloatingActionButton btndialog;


    private CustomTabsAdapter tabsAdapter;
    AlertDialog alertDialog;
    private List<Concepto> conceptoList = new ArrayList<>();
    private List<Concepto> conceptoTipoCatList = new ArrayList<>();

    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    Date date = new Date();
    int idCajagasto = 0;
    Concepto conceptoTipoGasto;
    Concepto conceptoTipoCateGasto;
    Concepto conceptoIgv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_expenses);
        ButterKnife.bind(this);
        cajaLiquidacion = CajaLiquidacion.find(CajaLiquidacion.class, " liq_Id = ? ", new String[]{Session.getCajaLiquidacion(this).getLiqId() + ""}).get(Constants.CURRENT);
        usuario = Usuario.find(Usuario.class, "usu_I_Usuario_Id = ? ", new String[]{Session.getSession(this).getUsuIUsuarioId() + ""}).get(Constants.CURRENT);
        conceptoIgv = Concepto.getConceptoById(Constants.CONCEPTO_IGV_ID);
        btndialog.setOnClickListener(this);
        setTabsAdapterFragment();
        setToolbar();
        setupCollapsingToolbar();
        initCabecera();

    }

    private void initCabecera() {
        List<CajaGasto> cajaGastos = CajaGasto.getListCajaGastosLiquidacion(cajaLiquidacion.getLiqId() + "");


        double importeGastos = 0.0;

        for (CajaGasto cajaGasto : cajaGastos) {

            importeGastos = importeGastos + cajaGasto.getImporte();
        }
        onAddnewCajaGasto(Utils.getDatePhone(), importeGastos);
    }

    private void setupCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(
                R.id.collapse_toolbar2);

        collapsingToolbar.setTitleEnabled(false);

    }


    private void setTabsAdapterFragment() {
        tabsAdapter = new CustomTabsAdapter(getSupportFragmentManager());
        tabsAdapter.addFragment(new CajaGastoFragment(), "");// getString(R.string.activity_expenses_today)
//        tabsAdapter.addFragment(new CajaGastoFragment(), getString(R.string.activity_expenses_week));
        mainViewPager.setAdapter(tabsAdapter);
        tabLayout.setupWithViewPager(mainViewPager);
    }

    private void setToolbar() {
        // Añadir la Toolbar
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) //
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_menu_accountsummary, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btndialog:
                alertDialog = null;
                inflate_dialog();
                break;
            case R.id.btn_save:
                alertDialog = null;

                inflate_dialog();
                break;
            case R.id.btn_cancel:
                alertDialog.dismiss();
                break;
        }

    }

    private AppCompatActivity getActivity() {
        return this;
    }

    //Return current fragment on basis of Position
    public Fragment getFragment(int pos) {
        return tabsAdapter.getItem(pos);
    }

    public void inflate_dialog() {

        final View layout_dialog_expenses = View.inflate(this, R.layout.fragment_dialog_new_expense, null);

        final Button btnsave = (Button) layout_dialog_expenses.findViewById(R.id.btn_save);
        final Button btn_cancel = (Button) layout_dialog_expenses.findViewById(R.id.btn_cancel);
        final EditText txtttotal = (EditText) layout_dialog_expenses.findViewById(R.id.et_total);
        final EditText txtdgdescription = (EditText) layout_dialog_expenses.findViewById(R.id.et_description);
        final Spinner sp_tiposgastos = (Spinner) layout_dialog_expenses.findViewById(R.id.sp_tiposgastos);
        final Spinner sp_catTGasto = (Spinner) layout_dialog_expenses.findViewById(R.id.sp_tcGasto);


        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);

        // set title
        alertDialogBuilder.setTitle("Nuevo Gasto");

        // set dialog message
        final AlertDialog.Builder builder = alertDialogBuilder
                .setView(layout_dialog_expenses)
                .setCancelable(true);
        btnsave.setOnClickListener(this);
        btn_cancel.setOnClickListener(this);


        conceptoList = Concepto.find(Concepto.class, "OBJETO = ? AND  CONCEPTO = ? AND  ESTADO = ? ", new String[]{Constants.CONCEPTO_CAJA_GASTO, Constants.CONCEPTO_TIPO_GASTO, String.valueOf(Constants.CLICK_EDITAR_CAJA_GASTO)});
        Log.d(TAG, "SIZE: " + conceptoList.size());
        ConceptoAdapter conceptoArrayAdapter = new ConceptoAdapter(this, 0, conceptoList);
        sp_tiposgastos.setAdapter(conceptoArrayAdapter);

        conceptoTipoCatList = Concepto.find(Concepto.class, "OBJETO = ? AND  CONCEPTO = ? AND  ESTADO = ? ", new String[]{Constants.CONCEPTO_CAJA_GASTO, Constants.CONCEPTO_CATEGORIA_TIPO_GASTO, String.valueOf(Constants.CLICK_EDITAR_CAJA_GASTO)});
        sp_catTGasto.setAdapter(new ConceptoAdapter(this, 0, conceptoTipoCatList));


        btnsave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                String description = txtdgdescription.getText().toString();

                conceptoTipoGasto = (Concepto) sp_tiposgastos.getSelectedItem();
                conceptoTipoCateGasto = (Concepto) sp_catTGasto.getSelectedItem();

                Log.d(TAG, "Concepto: " + conceptoTipoGasto.getId() + "-" + conceptoTipoGasto.getDescripcion());


                Fragment expensesFragment = getFragment(0);
                if (txtttotal.getText().toString().equals("")) {
                    txtttotal.setError("Ingrese Total");
                } else {
                    double impporte = Double.valueOf(txtttotal.getText().toString());
                    double igv = impporte * Double.parseDouble(conceptoIgv.getDescripcion());
                    double valor = impporte - igv;

                    idCajagasto = idCajagasto + 1;
                    expenses = new CajaGasto(idCajagasto,
                            idCajagasto,
                            Utils.formatDoubleNumber(igv),
                            Utils.formatDoubleNumber(valor),
                            Utils.formatDoubleNumber(impporte),
                            conceptoTipoGasto.getIdConcepto(),
                            usuario.getUsuIUsuarioId(),
                            usuario.getUsuIUsuarioId(),
                            Utils.getDatePhoneTime(),
                            Utils.getDatePhoneTime()
                    );
                    Log.d(TAG, "CountID: " + idCajagasto);
//                        Log.d(TAG, "CountID: " +  mMovimiento.getCajMovId());
                    if (expensesFragment != null) {
                        ((CajaGastoFragment) expensesFragment).addnewExpenses(expenses); //obtenemos la instancia del fragmento
                    }

                    alertDialog.dismiss();
                    //  expenses.save();
                    save(expenses, description);


                }


            }


        });
        // create alert dialog
        alertDialog = alertDialogBuilder.create();
        // show it
        alertDialog.show();

    }


    private void save(final CajaGasto mcajaGasto, final String mDescription) {


        SugarTransactionHelper.doInTransaction(new SugarTransactionHelper.Callback() {
            @Override
            public void manipulateInTransaction() {

                /** Caja Gasto **/
                Long idCajaGasto = mcajaGasto.save();
                mcajaGasto.setCajGasId(idCajaGasto);
                mcajaGasto.save();


                /**Informe de gasto**/

                InformeGasto informeGasto = new InformeGasto(0,
                        conceptoTipoGasto.getIdConcepto(),
                        mcajaGasto.getCajGasId(),
                        usuario.getUsuIUsuarioId(),
                        Utils.getDatePhoneTimeSQLSERVER(),
                        mDescription,
                        conceptoTipoCateGasto.getIdConcepto(),
                        Constants.GASTO_ESTADO_CREADO);
                long idInformeGasto = informeGasto.save();
                informeGasto.setInfGasId(idInformeGasto);
                informeGasto.save();

                /**Caja Movimiento**/

                CajaMovimiento cajaMovimiento = new CajaMovimiento(-1,
                        cajaLiquidacion.getLiqId(),
                        Constants.CATEGORIA_MOV_GASTO,
                        "Soles",
                        expenses.getImporte(),
                        Constants.ESTADO_TRUE,
                        Utils.getDatePhoneTime(),
                        "",
                        mDescription,
                        usuario.getUsuIUsuarioId(),
                        Utils.getDatePhoneTime(),
                        "Android",
                        Constants.TIPO_MOV_EGRESO, null, null, null, null);
                Long idCajaMovimiento = cajaMovimiento.save();
                cajaMovimiento.setCajMovId(idCajaMovimiento);
                cajaMovimiento.save();


                informeGasto.setCajGasId(idCajaGasto);
                mcajaGasto.setCajMoId(idCajaMovimiento);
                mcajaGasto.save();
                informeGasto.save();


                new SyncEstado(0, Utils.separteUpperCase(InformeGasto.class.getSimpleName()), Integer.parseInt(idInformeGasto + ""), Constants.S_CREADO).save();
                new SyncEstado(0, Utils.separteUpperCase(CajaMovimiento.class.getSimpleName()), Integer.parseInt(idCajaMovimiento + ""), Constants.S_CREADO).save();
                new SyncEstado(0, Utils.separteUpperCase(CajaGasto.class.getSimpleName()), Integer.parseInt(idCajaGasto + ""), Constants.S_CREADO).save();
                new ExportTask(CajaGastoActivity.this, CajaGastoActivity.this).execute(Constants.TABLA_GASTO, Constants.S_CREADO);


            }

            @Override
            public void errorInTransaction(String error) {

            }
        });
    }


    private void deleteItem(final CajaGasto cajaGasto) {
        SugarTransactionHelper.doInTransaction(new SugarTransactionHelper.Callback() {
            @Override
            public void manipulateInTransaction() {

                CajaMovimiento cajaMovimiento = CajaMovimiento.getCajaMovimientoById(cajaGasto.getCajMoId() + "");
                cajaMovimiento.setEstado(Constants.ESTADO_FALSE);
                cajaMovimiento.save();

                SyncEstado syncEstadoCM = SyncEstado.find(SyncEstado.class, " nombre_Tabla=? and campo_Id=? ", new String[]{Utils.separteUpperCase(CajaMovimiento.class.getSimpleName()), cajaMovimiento.getId() + ""}).get(0);
                syncEstadoCM.setEstadoSync(Constants.S_ACTUALIZADO);
                syncEstadoCM.save();


                InformeGasto mInformeGasto = InformeGasto.find(InformeGasto.class, "caj_Gas_Id = ?", new String[]{cajaGasto.getCajGasId() + ""}).get(Constants.CURRENT);
                mInformeGasto.setEstadoId(Constants.GASTO_ESTADO_ELIMINADO);
                mInformeGasto.save();
                Log.d(TAG, "  CajaMovimiento mCajaMovimiento " + mInformeGasto);
                SyncEstado syncEstado = SyncEstado.find(SyncEstado.class, " nombre_Tabla=? and campo_Id=? ", new String[]{Utils.separteUpperCase(InformeGasto.class.getSimpleName()), mInformeGasto.getId() + ""}).get(0);
                syncEstado.setEstadoSync(Constants.S_ACTUALIZADO);
                syncEstado.save();
            }

            @Override
            public void errorInTransaction(String error) {

            }
        });

    }

    @Override
    public void onAddnewCajaGasto(String date, Double total) {
        txtotal.setText("S/." + Utils.formatDouble(total));
        txtdate.setText(Utils.getDatePhone());
        Log.d("DATE", "date " + date + total);
    }


    @Override
    public void onCajaGastoClickListener(int action, CajaGasto expenses, View view) {
        switch (action) {
            case Constants.CLICK_EDITAR_CAJA_GASTO:
                update_dialog(expenses, view);
                break;
            case Constants.CLICK_ELIMINAR_CAJA_GASTO:

                Fragment expensesFragment = getFragment(0);

                if (expensesFragment != null) {
                    boolean success = expenses.delete();
                    Snackbar.make(toolbar, "expensesFragment != null, expenses.delete(): " + success, Snackbar.LENGTH_LONG).show();
                    if (success) {
                        ((CajaGastoFragment) expensesFragment).removeExpense(expenses, view);
                        deleteItem(expenses);

                    }
                    //obtenemos la instancia del fragmento
                }
                break;
            default:
                break;
        }
    }

    public void update_dialog(final CajaGasto cajaGasto, final View viewCaja) {

        final View layout_dialog_expenses = View.inflate(this, R.layout.dialog_expenses_update, null);

        final Button btnsave = (Button) layout_dialog_expenses.findViewById(R.id.btn_save);
        final Button btn_cancel = (Button) layout_dialog_expenses.findViewById(R.id.btn_cancel);
        final EditText txtttotal = (EditText) layout_dialog_expenses.findViewById(R.id.et_total);
        final EditText txtdgdescription = (EditText) layout_dialog_expenses.findViewById(R.id.et_description);
        //final Spinner sp_tiposgastos = (Spinner) layout_dialog_expenses.findViewById(R.id.sp_tiposgastos);

        //SETEAMOS
        txtttotal.setText(Utils.formatDouble(cajaGasto.getImporte()));

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);

        // set title
        alertDialogBuilder.setTitle("New Expenses");

        // set dialog message
        final AlertDialog.Builder builder = alertDialogBuilder
                .setView(layout_dialog_expenses)
                .setCancelable(true);
        btnsave.setOnClickListener(this);
        btn_cancel.setOnClickListener(this);


        btnsave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {


                Fragment expensesFragment = getFragment(0); //Capturamos la posicion del fragmento

//                if (txtdgdescription.getText().toString().equals("")) {
//                    txtdgdescription.setError("Ingrese Descripcion");
                if (txtttotal.getText().toString().equals("")) {
                    txtttotal.setError("Ingrese Total");

//                } else if (txtttotal.getText().toString().equals("")) {
//                    txtttotal.setError("Ingrese Total");
                } else {

                    cajaGasto.setImporte(Double.parseDouble(txtttotal.getText().toString()));


                    cajaGasto.save();
                    alertDialog.dismiss();
                    //Log.d(TAG, "SPINNER ITEMS" + selectedVal);


                    if (expensesFragment != null) {
                        ((CajaGastoFragment) expensesFragment).updateNewExpenses(viewCaja); //obtenemos la instancia del fragmento
                    }

                }
            }

        });
        // create alert dialog
        alertDialog = alertDialogBuilder.create();
        // show it
        alertDialog.show();

    }


    @Override
    public void onLoadSuccess(String message) {

    }

    @Override
    public void onLoadError(String message) {

    }

    @Override
    public void onLoadErrorProcedure(String message) {

    }


}
