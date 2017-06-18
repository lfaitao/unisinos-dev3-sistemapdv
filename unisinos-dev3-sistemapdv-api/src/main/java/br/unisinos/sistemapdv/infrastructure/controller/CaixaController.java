package br.unisinos.sistemapdv.infrastructure.controller;

import br.unisinos.sistemapdv.application.repository.CaixaRepository;
import br.unisinos.sistemapdv.application.repository.CredencialRepository;
import br.unisinos.sistemapdv.application.repository.PermissaoRepository;
import br.unisinos.sistemapdv.application.repository.UsuarioRepository;
import br.unisinos.sistemapdv.application.service.GerenciarCaixaService;
import br.unisinos.sistemapdv.domain.model.Caixa;
import br.unisinos.sistemapdv.domain.model.Credencial;
import br.unisinos.sistemapdv.domain.model.Permissao;
import br.unisinos.sistemapdv.domain.model.Usuario;
import br.unisinos.sistemapdv.infrastructure.dto.CaixaDTO;
import br.unisinos.sistemapdv.infrastructure.dto.CredencialDTO;
import br.unisinos.sistemapdv.infrastructure.dto.FeedbackDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lfaitao on 26/03/2017.
 */
@RestController
@RequestMapping("/caixa")
public class CaixaController {

    @Autowired
    private CaixaRepository caixaRepository;

    @Autowired
    private CredencialRepository credencialRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PermissaoRepository permissaoRepository;

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
    @RequestMapping("/suprir/{valor}/credenciais/{login}/{senha}")
    @ResponseBody
    public FeedbackDTO suprirCaixa(@PathVariable Double valor, @PathVariable String login, @PathVariable String senha) {
        FeedbackDTO feedback;

        // Verifica se as credenciais estão corretas
        Credencial credenciais = credencialRepository.findByLoginAndSenha(login, senha);
        if(credenciais == null) {
            feedback = new FeedbackDTO(false, "Credenciais informadas invalidas. Por favor, tente novamente.");
            return feedback;
        }

        // Verifica o nível de permissão do usuario que está suprindo o caixa
        Usuario usuario = credenciais.getUsuario();
        boolean hasPermission = false;
        for (Permissao permissao : usuario.getPermissao()) {
            if ("Administrador".equals(permissao.getNome()) || "Gerente".equals(permissao.getNome())) {
                hasPermission = true;
            }
        }

        if (!hasPermission) {
            feedback = new FeedbackDTO(false, "O suprimento deve ser aprovado pelas credenciais de um Administrador ou Gerente. Por favor, tente novamente.");
        } else if (valor <= 0) {
            feedback = new FeedbackDTO(false, "O valor a ser suprido não pode ser inferior ou igual à zero. Por favor, tente novamente.");
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
