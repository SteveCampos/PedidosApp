package energigas.apps.systemstrategy.energigas.activities;


import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.fragments.ProgressDialogFragment;

public class LoginActivity extends AppCompatActivity{
    @BindView(R.id.input_email)
    EditText editTextEmail;
    @BindView(R.id.input_password)
    EditText editTextPassword;
    @BindView(R.id.btn_login)
    AppCompatButton btnLogin;

    private static final String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

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
            startLogin();
            if (mEmail.equals("admin") && mPassword.equals("123")) {


                // startActivity(new Intent(this, MainActivity.class));
                // finish();
            } else {
                Toast.makeText(getApplicationContext(), "Login Incorrecto", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void startLogin() {
        ProgressDialogFragment.newIntance("","").show(getSupportFragmentManager(),"xd");
    }


    @OnClick(R.id.btn_login)
    public void login(){
        Toast.makeText(LoginActivity.this, R.string.login_action_login, Toast.LENGTH_SHORT).show();
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
    }






}
