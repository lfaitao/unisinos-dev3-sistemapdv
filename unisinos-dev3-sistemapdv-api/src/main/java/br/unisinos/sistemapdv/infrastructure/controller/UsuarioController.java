package br.unisinos.sistemapdv.infrastructure.controller;

import br.unisinos.sistemapdv.application.repository.UsuarioRepository;
import br.unisinos.sistemapdv.domain.model.Credencial;
import br.unisinos.sistemapdv.domain.model.Permissao;
import br.unisinos.sistemapdv.domain.model.Usuario;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @CrossOrigin("*")
    @GetMapping("/usuarios/{id}")
    @ResponseBody
    public Usuario get(@PathVariable Long id) {

        return usuarioRepository.findOne(id);
    }


    @CrossOrigin("*")
    @GetMapping("/usuarios")
    @ResponseBody
    public List<Usuario> get(@RequestParam String nome, @RequestParam String login) {

        return usuarioRepository.findByNomeOrCredencialLoginContaining(nome, login);
    }

    @CrossOrigin("*")
    @PostMapping("/usuarios/")
    @ResponseBody
    public Usuario post(@RequestBody Usuario usuario) {
        Usuario usuarioSalva = usuarioRepository.save(usuario);
        if(usuarioSalva != null)
        return usuarioSalva;
        return usuarioSalva;
    }

    @CrossOrigin("*")
    @DeleteMapping("/usuarios/{id}")
    @ResponseBody
    public void delete(@PathVariable long id) {

        usuarioRepository.delete(id);
    }

    @CrossOrigin("*")
    @PutMapping("/usuarios/")
    @ResponseBody
    public void put(@RequestBody Usuario usuario) {
        Usuario usuarioExistente = usuarioRepository.findOne(usuario.getId());
        usuarioExistente.atualizar(usuario);
        usuarioRepository.save(usuarioExistente);
    }
}