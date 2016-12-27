package energigas.apps.systemstrategy.energigas.activities;


import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;

import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import butterknife.BindView;
import butterknife.ButterKnife;
import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.entities.Concepto;
import energigas.apps.systemstrategy.energigas.entities.Servidores;
import energigas.apps.systemstrategy.energigas.fragments.ProgressDialogFragment;
import energigas.apps.systemstrategy.energigas.utils.Session;
import energigas.apps.systemstrategy.energigas.utils.Utils;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.input_email)
    EditText editTextEmail;
    @BindView(R.id.input_password)
    EditText editTextPassword;
    @BindView(R.id.btn_login)
    AppCompatButton buttonLogin;


    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private static final String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (!Session.getsaveServidores(this)) {

            Servidores distribucion = new Servidores("Distribucion", "192.168.0.158/ServiciosMovil");
            Servidores facturacion = new Servidores("Facturacion", "192.168.0.100/SW");
            distribucion.save();
            facturacion.save();
            Session.setServidores(this, true);
        }


        ButterKnife.bind(this);


        setToolbar();
        getSupportActionBar().setTitle((Html.fromHtml("<font color=\"#FFFFFF\">" + "Energigas" + "</font>")));
        buttonLogin.setOnClickListener(this);
        checkLogin();
    }


    private void setToolbar() {
        setSupportActionBar(toolbar);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(getApplicationContext(), MainConfiguraciones.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void conectlogin() {
        String mEmail = editTextEmail.getText().toString();
        String mPassword = editTextPassword.getText().toString();

        boolean valida = true;

        if (Concepto.listAll(Concepto.class).size() <= 0) {
            valida = false;
            Toast.makeText(getApplicationContext(), "Por favor, importe datos generales", Toast.LENGTH_LONG).show();
        }


        if (mEmail.length() <= 0 || mPassword.length() <= 0) {
            valida = false;
            Toast.makeText(getApplicationContext(), "Usuario o password Incorrecto", Toast.LENGTH_LONG).show();
        }

        if (valida) {
            startLogin(mEmail, mPassword);

        }
    }

    private void startLogin(String usuario, String clave) {
        ProgressDialogFragment.newIntance(usuario, clave).show(getSupportFragmentManager(), "xd");
    }

    private void checkLogin() {
        if (Utils.isSignin(this)) {
            startActivity(new Intent(this, MainActivity.class));
            LoginActivity.this.finish();
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                conectlogin();
                break;
        }
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "onStop");
        super.onStop();
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume");
        super.onResume();
    }
}
