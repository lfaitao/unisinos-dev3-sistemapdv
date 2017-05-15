package br.unisinos.sistemapdv.application.service;

import br.unisinos.sistemapdv.infrastructure.dto.FeedbackDTO;
import org.springframework.stereotype.Service;

/**
 * Created by lfaitao on 3/30/17.
 */
@Service
public interface GerenciarCaixaService {

    FeedbackDTO abrirCaixa(Integer numeroCaixa);

    boolean isCaixaAberto(Integer numeroCaixa);

}
