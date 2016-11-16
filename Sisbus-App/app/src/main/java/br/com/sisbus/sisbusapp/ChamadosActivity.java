package br.com.sisbus.sisbusapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ChamadosActivity extends AppCompatActivity {

    private ArrayAdapter<String> spinnerOpcoes;
    private ArrayAdapter<String> spinnerOpcoesAvaria;
    static final String opcao0 = "--Prioridade--";
    static final String opcao1 = "NORMAL";
    static final String opcao2 = "URGENTE";
    static final String opcaoAvaria0 = "--Avaria--";
    static final String opcaoAvaria1 = "COLISÃO";
    static final String opcaoAvaria2 = "DEFEITO";
    static final String opcaoAvaria3 = "TRANSITO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chamados);

        final Spinner idPrioridade = (Spinner) findViewById(R.id.idPrioridade);
        final Spinner idAvaria = (Spinner) findViewById(R.id.idAvaria);

        //  Acessa um layout padrão que existe na plataforma android, (classe R padrão do android)
        spinnerOpcoes = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        spinnerOpcoesAvaria = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);

        //  Ao clicar no Spinner, como os dados serão exibidos na tela
        spinnerOpcoes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOpcoesAvaria.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //  Para cada Spinner ou ListView deve criar uma referência pra cada um, e depois realizar a vinculação desse objeto
        idPrioridade.setAdapter(spinnerOpcoes);
        idAvaria.setAdapter(spinnerOpcoesAvaria);

        //  Adicionando opções no meu Spinner
        spinnerOpcoes.add(opcao0);
        spinnerOpcoes.add(opcao1);
        spinnerOpcoes.add(opcao2);
        spinnerOpcoesAvaria.add(opcaoAvaria0);
        spinnerOpcoesAvaria.add(opcaoAvaria1);
        spinnerOpcoesAvaria.add(opcaoAvaria2);
        spinnerOpcoesAvaria.add(opcaoAvaria3);

        Button solicitacao = (Button) findViewById(R.id.button_solicitacao);

        final EditText idLocalizacao = (EditText) findViewById(R.id.idLocalizacao);
        final EditText idNumero = (EditText) findViewById(R.id.idNumero);
        final EditText idReferencia = (EditText) findViewById(R.id.idReferencia);
        final EditText idCodigoVeiculo = (EditText) findViewById(R.id.idCodigoVeiculo);

            solicitacao.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Thread thread = new Thread();

                    if(TextUtils.isEmpty(idLocalizacao.getText().toString()) || TextUtils.isEmpty(idNumero.getText().toString())
                            || TextUtils.isEmpty(idReferencia.getText().toString()) || TextUtils.isEmpty(idCodigoVeiculo.getText().toString())
                            || idPrioridade.getSelectedItem().toString().equals(opcao0)
                            || idAvaria.getSelectedItem().toString().equals(opcaoAvaria0)){
                        toastError();
                    }else {
                        toastSuccess();
                        Intent intent = new Intent(ChamadosActivity.this, MapaActivity.class);
                        startActivity(intent);
                    }
                }
            });
    }

    public Toast toastError(){
        LayoutInflater layoutInflater = getLayoutInflater();

        View layout = layoutInflater.inflate(R.layout.custom_toast_error, (ViewGroup) findViewById(R.id.layout_custom));

        TextView textView = (TextView) layout.findViewById(R.id.text_toast);
        textView.setText("Preencha todos os campos!");

        Toast toast = new Toast(ChamadosActivity.this);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
        return toast;
    }

    public Toast toastSuccess(){
        LayoutInflater layoutInflater = getLayoutInflater();

        View layout = layoutInflater.inflate(R.layout.custom_toast_success, (ViewGroup) findViewById(R.id.layout_custom));

        TextView textView = (TextView) layout.findViewById(R.id.text_toast);
        textView.setText("Solicitação enviada com sucesso!");

        Toast toast = new Toast(ChamadosActivity.this);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
        return toast;
    }

}
