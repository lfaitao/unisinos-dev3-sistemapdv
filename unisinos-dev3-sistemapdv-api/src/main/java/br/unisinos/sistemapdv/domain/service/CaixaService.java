package br.unisinos.sistemapdv.domain.service;

import br.unisinos.sistemapdv.domain.exception.ValidationException;
import br.unisinos.sistemapdv.domain.model.Caixa;
import org.springframework.stereotype.Service;

/**
 * Created by lfaitao on 3/30/17.
 */
@Service
public interface CaixaService {

    void abrirCaixa(Caixa caixa);
    Caixa fecharCaixa() throws ValidationException;
    boolean isCaixaAberto();

}