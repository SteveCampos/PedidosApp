package energigas.apps.systemstrategy.energigas.activities;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.fragments.ProgressDialogFragment;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.input_email)
    EditText editTextEmail;
    @BindView(R.id.input_password)
    EditText editTextPassword;
    @BindView(R.id.btn_login)
    AppCompatButton ButtonLogin;

    private static final String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        ButtonLogin.setOnClickListener(this);

    }

    public void conectlogin() {
        String mEmail = editTextEmail.getText().toString();
        String mPassword = editTextPassword.getText().toString();

        boolean valida = true;
        if (mEmail == null || mEmail.equals("")) {
            valida = false;
            Toast.makeText(getApplicationContext(), "Usuario Incorrecto", Toast.LENGTH_LONG).show();
        }
        if (mPassword == null || mPassword.equals("")) {
            valida = true;
            Toast.makeText(getApplicationContext(), "Clave Incorrecto", Toast.LENGTH_LONG).show();
        }
        if (valida) {
            startLogin(mEmail, mPassword);
            if (mEmail.equals("admin") && mPassword.equals("123")) {


                // startActivity(new Intent(this, MainActivity.class));
                // finish();
            } else {
                Toast.makeText(getApplicationContext(), "Login Incorrecto", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void startLogin(String usuario, String clave) {

        ProgressDialogFragment.newIntance(usuario,clave).show(getSupportFragmentManager(),"xd");

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                conectlogin();
                break;
        }
    }




}
