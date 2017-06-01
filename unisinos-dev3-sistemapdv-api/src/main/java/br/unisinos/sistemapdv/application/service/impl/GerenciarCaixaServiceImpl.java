package br.unisinos.sistemapdv.application.service.impl;

import br.unisinos.sistemapdv.domain.exception.ValidationException;
import br.unisinos.sistemapdv.application.repository.CaixaRepository;
import br.unisinos.sistemapdv.application.service.GerenciarCaixaService;
import br.unisinos.sistemapdv.domain.model.Caixa;
import br.unisinos.sistemapdv.domain.service.CaixaService;
import br.unisinos.sistemapdv.infrastructure.dto.FeedbackDTO;
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

    /**
     * Abre o caixa e salva estado no banco.
     * Se o caixa não existe, ele é criado e persistido.
     *
     * @param numeroCaixa
     * @return feedback
     */
    @Override
    public FeedbackDTO abrirCaixa(Integer numeroCaixa) {
        FeedbackDTO feedback;

        Caixa caixa = caixaRepository.findByNumeroCaixa(numeroCaixa);
        if (caixa == null) {
            caixa = caixaRepository.save(new Caixa(numeroCaixa));
        }

        if (!caixa.isCaixaAberto()) {
            caixaService.abrirCaixa(caixa);
            caixaRepository.save(caixa);
            feedback = new FeedbackDTO(true, "Caixa aberto com sucesso!");
        } else {
            feedback = new FeedbackDTO(false, "O caixa " + caixa.getNumeroCaixa() + " já está aberto!");
        }

        return feedback;
    }

    /**
     * Fecha o caixa e salva estado no banco.
     *
     * @return feedback
     */
    @Override
    public FeedbackDTO fecharCaixa() {
        FeedbackDTO feedback;

        try {
            Caixa caixa = caixaService.fecharCaixa();
            caixaRepository.save(caixa);
            feedback = new FeedbackDTO(true, "Caixa fechado com sucesso!");
        } catch (ValidationException e) {
            feedback = e.getFeedback();
        }

        return feedback;
    }

    /**
     * Verifica se o caixa está aberto.
     *
     * @param numeroCaixa
     * @return false se fechado, true se aberto
     */
    @Override
    public boolean isCaixaAberto(Integer numeroCaixa) {
        Caixa caixa = caixaRepository.findByNumeroCaixa(numeroCaixa);
        if (caixa == null) {
            return false;
        } else {
            return caixa.isCaixaAberto();
        }
    }

}
