package br.unisinos.sistemapdv.infrastructure.controller;

import br.unisinos.sistemapdv.application.repository.CredencialRepository;
import br.unisinos.sistemapdv.domain.model.Credencial;
import br.unisinos.sistemapdv.infrastructure.dto.CredencialDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lfaitao on 4/19/17.
 */
@RestController
public class CredencialController {

    @Autowired
    private CredencialRepository credencialRepository;

    /**
     * POST /autenticar --> Verifica se as credenciais existem no sistema.
     */
    @RequestMapping(value="/autenticar", method= RequestMethod.POST)
    @ResponseBody
    public boolean autenticar(CredencialDTO credencialDTO) {
        boolean autenticado = false;

        Credencial credenciais = credencialRepository.findByLoginAndSenha(credencialDTO.getLogin(), credencialDTO.getSenha());

        if (credenciais != null) {
            autenticado = true;
        }

        return autenticado;
    }

}
