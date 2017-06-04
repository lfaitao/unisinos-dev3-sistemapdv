package br.unisinos.sistemapdv.domain.service.impl;

import br.unisinos.sistemapdv.domain.exception.ValidationException;
import br.unisinos.sistemapdv.domain.model.Caixa;
import br.unisinos.sistemapdv.domain.service.CaixaService;
import br.unisinos.sistemapdv.infrastructure.dto.FeedbackDTO;
import org.springframework.stereotype.Service;

/**
 * Created by lfaitao on 5/7/17.
 */
@Service
public class CaixaServiceImpl implements CaixaService {

    private static final boolean CAIXA_ABERTO = true;
    private static final boolean CAIXA_FECHADO = false;

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
    public void abrirCaixa(Caixa caixa) {
        this.caixa = caixa;
        caixa.setCaixaAberto(CAIXA_ABERTO);
    }

    @Override
    public Caixa fecharCaixa() throws ValidationException {
        if (!isCaixaAberto()) {
            throw new ValidationException("O Caixa já está fechado!");
        } else {
            caixa.setCaixaAberto(CAIXA_FECHADO);
            return this.caixa;
        }
    }

    @Override
    public boolean isCaixaAberto() {
        return caixa.isCaixaAberto();
    }

}
