package br.unisinos.sistemapdv.infrastructure.controller;

import br.unisinos.sistemapdv.application.repository.CaixaRepository;
import br.unisinos.sistemapdv.application.service.GerenciarCaixaService;
import br.unisinos.sistemapdv.infrastructure.dto.FeedbackDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
     * GET /abrir/{numeroCaixa}  --> Abre o caixa e salva estado no banco.
     */
    @RequestMapping("/abrir/{caixaNumero}")
    @ResponseBody
    public FeedbackDTO abrirCaixa(@PathVariable Integer caixaNumero) {
        FeedbackDTO feedback;

        if (caixaNumero == null || caixaNumero <= 0) {
            feedback = new FeedbackDTO(false, "O número do caixa é obrigatório e deve ser maior que zero!");
        } else {
            feedback = gerenciarCaixaService.abrirCaixa(caixaNumero);
        }

        return feedback;
    }


    /**
     * GET /fechar  --> Fecha o caixa e salva estado no banco.
     */
    @RequestMapping("/fechar")
    @ResponseBody
    public FeedbackDTO fecharCaixa() {
        FeedbackDTO feedback;

        feedback = gerenciarCaixaService.fecharCaixa();

        return feedback;
    }

    /**
     * GET /isAberto/{numeroCaixa}  --> Verifica no banco se o respectivo caixa está aberto.
     */
    @GetMapping(value="/isAberto/{numeroCaixa}")
    @ResponseBody
    public boolean isCaixaAberto(@PathVariable Integer numeroCaixa) {
        return gerenciarCaixaService.isCaixaAberto(numeroCaixa);
    }

}
