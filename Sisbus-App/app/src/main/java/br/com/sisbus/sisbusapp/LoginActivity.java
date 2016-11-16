package br.com.sisbus.sisbusapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    static final String emailAdmin = "admin@admin.com";
    static final String loginAdmin = "admin";
    static final String senhaAdmin = "admin";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button entrar = (Button) findViewById(R.id.entrar);
        assert entrar != null;

        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText login = (EditText) findViewById(R.id.login);
                EditText senha = (EditText) findViewById(R.id.senha);

                // Store values at the time of the login attempt.
                String validarLogin = login.getText().toString();
                String validarSenha = senha.getText().toString();

                if(loginAdmin.equals(validarLogin) && senhaAdmin.equals(validarSenha)){
                    finish();   //  Deve entrar numa sessão, apenas para fins de testes
                    Intent intent = new Intent(LoginActivity.this, MapaActivity.class);
                    startActivity(intent);
                }if(!loginAdmin.equals(validarLogin) || !senhaAdmin.equals(validarSenha)){
                    alert("Usuário/Senha incorretos");
                }if(TextUtils.isEmpty(validarLogin) || TextUtils.isEmpty(validarSenha)) {
                    alert("Preencha todos os campos");
                }

            }
        });
    }

    public void alert(String message){
        Toast toast = Toast.makeText(LoginActivity.this, message, Toast.LENGTH_LONG);
        toast.show();
    }

    public void recuperarSenha(View view){

        //  http://stackoverflow.com/questions/30824009/change-edittext-hint-color-when-using-textinputlayout
        //  http://stackoverflow.com/questions/6438478/sethinttextcolor-in-edittext
        //  http://stackoverflow.com/questions/26455919/material-design-not-styling-alert-dialogs

        final EditText editText = new EditText(this);
        editText.setHint("E-mail");
        editText.setSingleLine();
        editText.setMaxLines(1);
        editText.setTextSize(16);

        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle("Recuperação de senha")
                .setMessage("")
                .setView(editText)
                .setPositiveButton("Enviar", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i){
                        final String emailInformado = (String.valueOf(editText.getText()));
                        if(emailInformado.equals(emailAdmin)){
                            alert("Encaminhado para: " + emailInformado);
                        }else {
                            alert("Email invalido!");
                        }
                    }
                })
                .setNegativeButton("Cancelar", null)
                .create();
        alertDialog.show();
    }

}

