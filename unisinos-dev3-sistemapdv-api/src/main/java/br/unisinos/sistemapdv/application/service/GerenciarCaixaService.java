package br.unisinos.sistemapdv.application.service;

import org.springframework.stereotype.Service;

/**
 * Created by lfaitao on 3/30/17.
 */
@Service
public interface GerenciarCaixaService {

    String abrirCaixa(Integer numeroCaixa);

}
