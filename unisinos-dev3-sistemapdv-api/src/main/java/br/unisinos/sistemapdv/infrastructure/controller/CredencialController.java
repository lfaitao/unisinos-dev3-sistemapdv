package br.unisinos.sistemapdv.infrastructure.controller;

import br.unisinos.sistemapdv.application.repository.CredencialRepository;
import br.unisinos.sistemapdv.application.repository.UsuarioRepository;
import br.unisinos.sistemapdv.domain.model.Credencial;
import br.unisinos.sistemapdv.domain.model.Permissao;
import br.unisinos.sistemapdv.domain.model.Usuario;
import br.unisinos.sistemapdv.infrastructure.dto.CredencialDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by lfaitao on 4/19/17.
 */
@RestController
public class CredencialController {

    @Autowired
    private CredencialRepository credencialRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * POST /autenticar --> Verifica se as credenciais existem no sistema.
     */
    @RequestMapping(value="/autenticar", method= RequestMethod.POST)
    @ResponseBody
    public List<Permissao> autenticar(CredencialDTO credencialDTO) {
        List<Permissao> usuarioPermissao = null;

        Credencial credenciais = credencialRepository.findByLoginAndSenha(credencialDTO.getLogin(), credencialDTO.getSenha());

        if (credenciais != null) {
            usuarioPermissao = credenciais.getUsuario().getPermissao();
            return usuarioPermissao;

        }

        return usuarioPermissao;
    }

}
