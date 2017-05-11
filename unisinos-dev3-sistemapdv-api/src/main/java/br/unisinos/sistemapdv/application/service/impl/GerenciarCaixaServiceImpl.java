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

    /*
     * Métodos
     */

    @Override
    public String abrirCaixa(Integer numeroCaixa) {
        String feedback;

        Caixa caixa = caixaRepository.findByNumeroCaixa(numeroCaixa);
        if (caixa == null) {
            caixa = caixaRepository.save(new Caixa(numeroCaixa));
        }

        if (!caixa.isCaixaAberto()) {
            caixaService.abrirCaixa(caixa);
            caixaRepository.save(caixa);
            feedback = "Caixa aberto com sucesso!";
        } else {
            feedback = "Este caixa já está aberto!";
        }

        return feedback;
    }

}
