package br.edu.ifsp.scl.sdm.model;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

public class ConfiguracaoSharedPreferences implements ConfiguracaoDAO {
    private final String NOME_ARQUIVO = "configuracoes";
    private final int MODO_ARQUIVO = Context.MODE_PRIVATE;
    private final String TAG_CONFIGURACAO = "configuracoes";
    private SharedPreferences sharedPreferences;
    private Gson gson;
    private GsonBuilder gsonBuilder;

    public ConfiguracaoSharedPreferences(Context context) {
        sharedPreferences = context.getSharedPreferences(NOME_ARQUIVO, MODO_ARQUIVO);
        gson = gsonBuilder.create();
    }

    @Override
    public void createOrUpdateConfiguracao(Configuracao configuracao) throws JSONException {
        JSONObject configuracaoJson = new JSONObject(gson.toJson(configuracao));

        SharedPreferences.Editor spEditor  = sharedPreferences.edit();

        spEditor.putString(TAG_CONFIGURACAO, configuracaoJson.toString());
        spEditor.commit();
    }

    @Override
    public Configuracao readConfiguracao() {
        String configuracaoString = sharedPreferences.getString(TAG_CONFIGURACAO, "");
        if (configuracaoString != "")
            return gson.fromJson(configuracaoString, Configuracao.class);
        else
            return new Configuracao();
    }
}
