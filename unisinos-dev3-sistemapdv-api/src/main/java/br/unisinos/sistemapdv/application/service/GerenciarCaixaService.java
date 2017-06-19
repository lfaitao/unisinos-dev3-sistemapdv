package br.unisinos.sistemapdv.application.service;

import br.unisinos.sistemapdv.domain.model.Caixa;
import br.unisinos.sistemapdv.infrastructure.dto.FeedbackDTO;
import org.springframework.stereotype.Service;

/**
 * Created by lfaitao on 3/30/17.
 */
@Service
public interface GerenciarCaixaService {

    FeedbackDTO abrirCaixa(Integer numeroCaixa);
    FeedbackDTO fecharCaixa();
    FeedbackDTO suprirCaixa(Double valor);
    FeedbackDTO sangrarCaixa(Double valor);
    FeedbackDTO abrirDiaFiscal();
    FeedbackDTO fecharDiaFiscal();
    boolean isCaixaAberto(Integer numeroCaixa);
    Caixa getCaixa(Integer numeroCaixa);

}
