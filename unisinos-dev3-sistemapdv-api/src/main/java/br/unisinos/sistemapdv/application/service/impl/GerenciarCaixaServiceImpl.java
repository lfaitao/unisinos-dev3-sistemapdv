package br.unisinos.sistemapdv.application.service.impl;

import br.unisinos.sistemapdv.application.repository.CaixaRepository;
import br.unisinos.sistemapdv.application.service.GerenciarCaixaService;
import br.unisinos.sistemapdv.domain.model.Caixa;
import br.unisinos.sistemapdv.domain.service.CaixaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lfaitao on 3/27/17.
 */
@Service
public class GerenciarCaixaServiceImpl implements GerenciarCaixaService {

    /*
     * Atributos
     */

    @Autowired
    private CaixaRepository caixaRepository;

    @Autowired
    private CaixaService caixaService;

    private boolean caixaIniciado;

    /*
     * Métodos
     */

    @Override
    public String iniciarCaixa(Integer numeroCaixa) {
        String feedback;

        if (!caixaIniciado) {
            Caixa caixa = caixaRepository.findByNumeroCaixa(numeroCaixa);
            if (caixa == null) {
                caixa = caixaRepository.save(new Caixa(numeroCaixa));
            }
            caixaService.iniciarCaixa(caixa);
            caixaIniciado = true;
            feedback = "Caixa iniciado com sucesso!";
        } else {
            feedback = "O caixa já está iniciado!";
        }

        return feedback;
    }

    @Override
    public String abrirCaixa() {
        String feedback;

        if (!caixaService.isCaixaAberto()) {
            caixaService.abrirCaixa();
            feedback = "Caixa aberto com sucesso!";
        } else {
            feedback = "O caixa já está aberto!";
        }

        return feedback;
    }

    @Override
    public boolean verificarEstadoCaixa() {
        return caixaService.isCaixaAberto();
    }
}
