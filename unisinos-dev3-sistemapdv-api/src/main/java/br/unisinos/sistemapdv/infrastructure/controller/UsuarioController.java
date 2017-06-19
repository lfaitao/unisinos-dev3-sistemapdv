package br.unisinos.sistemapdv.infrastructure.controller;

import br.unisinos.sistemapdv.application.repository.UsuarioRepository;
import br.unisinos.sistemapdv.domain.model.Credencial;
import br.unisinos.sistemapdv.domain.model.Permissao;
import br.unisinos.sistemapdv.domain.model.Usuario;

import java.util.ArrayList;
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
        List<Usuario> existe = usuarioRepository.findByNomeOrCredencialLoginIgnoreCase(usuario.getNome(),usuario.getCredencial().getLogin());
        if(existe.size() > 0){
            return null;
        }

        Usuario usuarioSalva = usuarioRepository.save(usuario);
        return usuarioSalva;
    }

    @CrossOrigin("*")
    @DeleteMapping("/usuarios/{id}")
    @ResponseBody
    public String delete(@PathVariable long id) {
        Usuario usuario = usuarioRepository.findById(id);
        List<Usuario> admins = usuarioRepository.findByPermissaoNome("Administrador");
        List<Usuario> all = usuarioRepository.findAll();

        boolean isAdmin = false;
        for(int i = 0; i<usuario.getPermissao().size(); i++) {
            if(usuario.getPermissao().get(i).getNome().equalsIgnoreCase("Administrador")){
                isAdmin = true;
            }
        }

        if((!isAdmin  || admins.size() > 1) && all.size() > 1){
            usuario.setPermissao(null);
            usuarioRepository.delete(usuario);
            return "S";
        }
        return "N";
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