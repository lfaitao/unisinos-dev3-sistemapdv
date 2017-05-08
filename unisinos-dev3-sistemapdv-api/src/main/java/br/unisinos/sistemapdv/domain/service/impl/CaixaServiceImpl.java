package br.unisinos.sistemapdv.domain.service.impl;

import br.unisinos.sistemapdv.domain.model.Caixa;
import br.unisinos.sistemapdv.domain.service.CaixaService;
import org.springframework.stereotype.Service;

/**
 * Created by lfaitao on 5/7/17.
 */
@Service
public class CaixaServiceImpl implements CaixaService {

    private static final boolean CAIXA_ABERTO = true;

    /*
     * Atributos
     */

    private Caixa caixa;

    /*
     * Construtores
     */

    private CaixaServiceImpl() {
        // Necessário por causa do Spring
    }

    /*
     * Métodos
     */

    @Override
    public void iniciarCaixa(Caixa caixa) {
        this.caixa = caixa;
    }

    @Override
    public void abrirCaixa() {
        caixa.setCaixaAberto(CAIXA_ABERTO);
    }

    @Override
    public boolean isCaixaAberto() {
        return caixa.isCaixaAberto();
    }

}
