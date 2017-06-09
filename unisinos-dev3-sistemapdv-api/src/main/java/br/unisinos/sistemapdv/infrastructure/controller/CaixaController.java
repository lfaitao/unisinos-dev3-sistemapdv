package br.unisinos.sistemapdv.infrastructure.controller;

import br.unisinos.sistemapdv.application.repository.CaixaRepository;
import br.unisinos.sistemapdv.application.service.GerenciarCaixaService;
import br.unisinos.sistemapdv.domain.model.Caixa;
import br.unisinos.sistemapdv.infrastructure.dto.CaixaDTO;
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
     * GET /suprir  --> Supre o caixa com o valor passado e salva estado no banco.
     */
    @RequestMapping("/suprir/{valor}")
    @ResponseBody
    public FeedbackDTO suprirCaixa(@PathVariable Double valor) {
        FeedbackDTO feedback;

        if (valor <= 0) {
            feedback = new FeedbackDTO(false, "O valor a ser suprido não pode ser inferior ou igual à zero.");
        } else {
            feedback = gerenciarCaixaService.suprirCaixa(valor);
        }

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

    /**
     * GET /{numeroCaixa}  --> Retorna o objeto do caixa com as informações atuais no banco.
     */
    @GetMapping(value="/{numeroCaixa}")
    @ResponseBody
    public CaixaDTO getCaixa(@PathVariable Integer numeroCaixa) {
        Caixa caixa = gerenciarCaixaService.getCaixa(numeroCaixa);
        return caixa == null ? null : new CaixaDTO(caixa);
    }

}
