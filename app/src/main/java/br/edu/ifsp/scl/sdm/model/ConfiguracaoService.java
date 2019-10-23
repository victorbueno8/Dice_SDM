package br.edu.ifsp.scl.sdm.model;

import android.content.Context;

import org.json.JSONException;

public class ConfiguracaoService {
    private ConfiguracaoDAO configuracaoDAO;

    public ConfiguracaoService(Context context) {
        configuracaoDAO = new ConfiguracaoSharedPreferences(context);
    }

    public void setConfiguracao(Configuracao configuracao) throws JSONException {
        configuracaoDAO.createOrUpdateConfiguracao(configuracao);
    }

    public Configuracao getConfiguracao() {
        return configuracaoDAO.readConfiguracao();
    }
}
