package br.unisinos.sistemapdv.infrastructure.controller;

import br.unisinos.sistemapdv.application.repository.UsuarioRepository;
import br.unisinos.sistemapdv.domain.model.Usuario;
import br.unisinos.sistemapdv.domain.model.Venda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @RequestMapping(value = "/usuario/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Usuario get(@PathVariable Long id) {
        return usuarioRepository.findOne(id);
    }

    @RequestMapping(value = "/usuario/", method = RequestMethod.POST)
    @ResponseBody
    public Long post(@RequestBody Usuario usuario) {
        Usuario usuarioSalva = usuarioRepository.save(usuario);
        return usuarioSalva.getId();
    }

    @RequestMapping(value = "/usuario/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void delete(@PathVariable long id) {
        usuarioRepository.delete(id);
    }

    @RequestMapping(value = "/usuario/", method = RequestMethod.PUT)
    @ResponseBody
    public void put(@RequestBody Usuario usuario) {
        Usuario usuarioExistente = usuarioRepository.findOne(usuario.getId());
        usuarioExistente.atualizar(usuario);
        usuarioRepository.save(usuarioExistente);
    }
}