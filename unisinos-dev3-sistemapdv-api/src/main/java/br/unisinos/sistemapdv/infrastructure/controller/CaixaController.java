package br.unisinos.sistemapdv.infrastructure.controller;

import br.unisinos.sistemapdv.application.repository.CaixaRepository;
import br.unisinos.sistemapdv.application.service.GerenciarCaixaService;
import br.unisinos.sistemapdv.domain.model.Caixa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lfaitao on 26/03/2017.
 */
@RestController
@RequestMapping("/caixa")
public class CaixaController {

    @Autowired
    private CaixaRepository caixaRepository;

    @Autowired
    private GerenciarCaixaService gerenciarCaixaService;

    /**
     * GET /create  --> Create a new user and save it in the database.
     */
    @RequestMapping("/abrir/{numeroCaixa}")
    @ResponseBody
    public String abrirCaixa(@PathVariable Integer numeroCaixa) {
        String feedback;

        if (numeroCaixa == null) {
            feedback = "O número do caixa é obrigatório!";
        } else {
            feedback = gerenciarCaixaService.abrirCaixa(numeroCaixa);
        }

        return feedback;
    }

}
