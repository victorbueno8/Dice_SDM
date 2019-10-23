package br.edu.ifsp.scl.sdm.model;

import org.json.JSONException;

public interface ConfiguracaoDAO {
    public void createOrUpdateConfiguracao(Configuracao configuracao) throws JSONException;
    public Configuracao readConfiguracao();
}
