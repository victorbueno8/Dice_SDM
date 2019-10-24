package br.edu.ifsp.scl.sdm.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import org.json.JSONException;

import br.edu.ifsp.scl.sdm.R;
import br.edu.ifsp.scl.sdm.controller.ConfiguracaoController;
import br.edu.ifsp.scl.sdm.model.Configuracao;

public class ConfiguracaoActivity extends AppCompatActivity {
    public final String CONFIGURACAO = "CONFIGURACAO";
    public ConfiguracaoController configuracaoController;
    private Spinner numDadosSpinner;
    private EditText numFacesEditText;
    private Toolbar toolbar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracao);

        // Toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Configuracao");
        this.setSupportActionBar(toolbar);

        configuracaoController = new ConfiguracaoController(this);
        configuracaoController.buscaConfiguracao();
    }

    public void atualizaView(Configuracao configuracao) {
        //Ajusta o leiaute conforme a configuração
        // SETAR RESULTADO PARA MAIN ACTIVITY
        setResult(AppCompatActivity.RESULT_OK, new Intent().putExtra(CONFIGURACAO, configuracao));
    }

    public void onClickSalvarConfiguracao(View v) throws JSONException {
        // Pega dados da tela
        numDadosSpinner = findViewById(R.id.numDadosSpinner);
        numFacesEditText = findViewById(R.id.numFacesEditText);
        int numDados = Integer.parseInt(numDadosSpinner.getSelectedItem().toString());
        int numFaces = Integer.parseInt(numFacesEditText.getText().toString());

        // Cria um objeto Configuração
        Configuracao novaConfiguracao = new Configuracao(numDados, numFaces);

        //Chamar o Controller para Salvar
        configuracaoController.salvaConfiguracao(novaConfiguracao);

        Toast.makeText(this, "Configuração Salva", Toast.LENGTH_SHORT).show();

        this.finish();
    }

}
