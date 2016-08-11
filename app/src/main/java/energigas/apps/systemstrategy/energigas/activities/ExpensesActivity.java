package energigas.apps.systemstrategy.energigas.activities;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
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
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.adapters.CustomTabsAdapter;

import energigas.apps.systemstrategy.energigas.entities.Expenses;
import energigas.apps.systemstrategy.energigas.fragments.ExpensesFragment;
import energigas.apps.systemstrategy.energigas.fragments.InventarioFragment;
import energigas.apps.systemstrategy.energigas.utils.Utils;

/**
 * Created by kikerojas on 30/07/2016.
 */

public class ExpensesActivity extends AppCompatActivity implements View.OnClickListener,ExpensesFragment.OnAddnewExpenses {
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





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_expenses);
        ButterKnife.bind(this);
        btndialog.setOnClickListener(this);
        setTabsAdapterFragment();
        setToolbar();
        setupCollapsingToolbar();
    }

    private void setupCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(
                R.id.collapse_toolbar2);

        collapsingToolbar.setTitleEnabled(false);

    }


    private void setTabsAdapterFragment (){
        tabsAdapter = new CustomTabsAdapter(getSupportFragmentManager());
        tabsAdapter.addFragment(new ExpensesFragment(), getString(R.string.activity_expenses_today));
        tabsAdapter.addFragment(InventarioFragment.newIntance(), getString(R.string.activity_expenses_week));
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
    private AppCompatActivity getActivity(){
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

                if (txtdgdescription.getText().toString().equals("")) {
                    txtdgdescription.setError("Ingrese Descripcion");

                } else if (txtttotal.getText().toString().equals("")) {
                    txtttotal.setError("Ingrese Total");
                } else {
                    Expenses expenses = new Expenses(

                            "BOLETA",
                            "05/08/2016",
                            txtdgdescription.getText().toString(),
                            100.0,
                            Double.valueOf(txtttotal.getText().toString())
                    );
                    if (expensesFragment != null) {
                        ((ExpensesFragment) expensesFragment).addnewExpenses(expenses); //obtenemos la instancia del fragmento
                    }

                    alertDialog.dismiss();
                }
            }

        });
        // create alert dialog
        alertDialog = alertDialogBuilder.create();
        // show it
        alertDialog.show();

    }


    @Override
    public void onAddnewExpenses(String date, Double total) {
        txtotal.setText("S/."+Utils.formatDouble(total));
        txtdate.setText(date);
        Log.d("DATE","date "+date + total);

    }
}
