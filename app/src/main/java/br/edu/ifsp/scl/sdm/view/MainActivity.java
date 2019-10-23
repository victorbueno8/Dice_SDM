package br.edu.ifsp.scl.sdm.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toolbar;

import java.util.Random;
import java.util.zip.Inflater;

import br.edu.ifsp.scl.sdm.R;
import br.edu.ifsp.scl.sdm.model.Configuracao;
import br.edu.ifsp.scl.sdm.model.ConfiguracaoService;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    // Simula olancamento de dadoscom Ramdom
    private Random geradorRandomico;
    private MenuInflater menuInflater;
    private final int CONFIGURACOES_REQUEST_CODE = 0;

    // Componentes
    private TextView resultadoTextView;
    private Button jogarDadoButton;
    private ImageView resultadoImageView;
    private ImageView resultado2ImageView;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Toolbar
        androidx.appcompat.widget.Toolbar toolbar = new androidx.appcompat.widget.Toolbar(this);
        toolbar.setTitle(getString(R.string.app_name));
        setSupportActionBar(toolbar);

        // Apos a criacao da tela
        geradorRandomico = new Random(System.currentTimeMillis());

        // Recupera referencia para o resultadoTextView do arquivo de layout
        resultadoTextView = findViewById(R.id.resultadoTextView);

        // Recupera referencia para o jogarDadoButton do arquivo de layout
        jogarDadoButton = findViewById(R.id.jogarDadoButton);
        jogarDadoButton.setOnClickListener(this);

        // Recupera referencia para o resultadoImageView do arqivo de layout
        resultadoImageView = findViewById(R.id.resultadoImageView);

//        numDadosSpinner = findViewById(R.id.numDadosSpinner);
        resultado2ImageView = findViewById(R.id.resultado2ImageView);

        // Recupera referencia para o numFacesEditText do arquivo de layout
//        numFacesEditText = findViewById(R.id.numFacesEditText);
    }

    // Cria o menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        boolean retorno = false;
        if (item.getItemId() == R.id.sairMenuItem) {
            retorno = true;
            finish();
        }
        if (item.getItemId() == R.id.configuracoesMenuItem) {
                retorno = true;
                Intent configuracaoIntent = new Intent(this, ConfiguracaoActivity.class);
                startActivityForResult(configuracaoIntent, CONFIGURACOES_REQUEST_CODE);
        }
        return retorno;
    }

    // Quando setResult na ConfiguracaoActivity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CONFIGURACOES_REQUEST_CODE && resultCode == AppCompatActivity.RESULT_OK) {
            // Pegar configuração retornada
            Configuracao configuracao = data.getParcelableExtra(new ConfiguracaoActivity().CONFIGURACAO);
        }
    }

    @Override
    public void onClick(View view){
        ConfiguracaoService configuracaoService = new ConfiguracaoService(this.getApplicationContext());
        Configuracao configuracao = configuracaoService.getConfiguracao();

        if (view.getId() == R.id.jogarDadoButton) {

            // Recuperando o número de dados selecionados
            int numDados = configuracao.getNumDados();

            // String que armazena números sorteados
            String resultadoText = "Faces sorteadas: ";
            int numFaces;
            try {
                numFaces = configuracao.getNumFaces();
            } catch (NumberFormatException e) {
                // Caso usuário não digite nenhum número de faces
                numFaces = 6;
            }

            if (numFaces > 6) {
                resultadoImageView.setVisibility(View.GONE);
                resultado2ImageView.setVisibility(View.GONE);
            } else {
                resultadoImageView.setVisibility(View.VISIBLE);
                // Visibilidade do resultado2ImageView de acordo com número de dados
                if (numDados == 2) {
                    resultado2ImageView.setVisibility(View.VISIBLE);
                } else {
                    resultado2ImageView.setVisibility(View.GONE);
                    resultadoText = "Face sorteada: ";
                }
            }

            // Sorteando números de acordo com número de dados
            for (int i = 1; i <= numDados; i++) {
                int resultado = geradorRandomico.nextInt(numFaces) + 1;
                resultadoText += resultado + ", ";
                ImageView iv = (i == 1) ? resultadoImageView : resultado2ImageView;
                setImageResource(iv, resultado);
            }
            resultadoTextView.setText(resultadoText.substring(0, resultadoText.lastIndexOf(',')));
        }
    }

    private void setImageResource(ImageView iv, int face) {
        String nomeRes = "dice_" + face;
        int idRes = getResources().getIdentifier(nomeRes, "drawable", getPackageName());
        iv.setImageResource(idRes);
    }

}
