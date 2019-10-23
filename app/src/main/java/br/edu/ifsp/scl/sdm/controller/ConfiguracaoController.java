package br.edu.ifsp.scl.sdm.controller;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;

import br.edu.ifsp.scl.sdm.model.Configuracao;
import br.edu.ifsp.scl.sdm.model.ConfiguracaoService;
import br.edu.ifsp.scl.sdm.view.ConfiguracaoActivity;

public class ConfiguracaoController {
    private ConfiguracaoActivity configuracaoActivity;
    private ConfiguracaoService model;

    public ConfiguracaoController(@NotNull ConfiguracaoActivity view) {
        this.configuracaoActivity = view;
        this.model = new ConfiguracaoService(view.getApplicationContext());
    }

    public void salvaConfiguracao(Configuracao configuracao) throws JSONException {
        model.setConfiguracao(configuracao);
        configuracaoActivity.atualizaView(configuracao);
    }

    public void buscaConfiguracao() {
        Configuracao configuracao = model.getConfiguracao();
        configuracaoActivity.atualizaView(configuracao);
    }

}
