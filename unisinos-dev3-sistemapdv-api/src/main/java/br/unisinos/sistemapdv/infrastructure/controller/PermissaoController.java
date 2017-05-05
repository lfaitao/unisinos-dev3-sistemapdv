package br.unisinos.sistemapdv.infrastructure.controller;

import br.unisinos.sistemapdv.application.repository.PermissaoRepository;
import br.unisinos.sistemapdv.domain.model.Permissao;
import br.unisinos.sistemapdv.domain.model.Tela;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PermissaoController {

    @Autowired
    private PermissaoRepository permissaoController;

    @CrossOrigin("*")
    @GetMapping("/permissoes/{id}")
    @ResponseBody
    public Permissao get(@PathVariable Long id) {

        return permissaoController.findOne(id);
    }

    @CrossOrigin("*")
    @GetMapping("/permissoes/all")
    @ResponseBody
    public List<Permissao> get() {

        return permissaoController.findAll();
    }

    @CrossOrigin("*")
    @GetMapping("/permissoes")
    @ResponseBody
    public List<Permissao> get(@RequestParam String nome) {

        return permissaoController.findByNomeContaining(nome);
    }

    @CrossOrigin("*")
    @PostMapping("/permissoes/")
    @ResponseBody
    public Permissao post(@RequestBody Permissao permissao) {
        Permissao permissaoSalva = permissaoController.save(permissao);
        return permissaoSalva;
    }

    @CrossOrigin("*")
    @DeleteMapping("/permissoes/{id}")
    @ResponseBody
    public void delete(@PathVariable long id) {

        permissaoController.delete(id);
    }

    @CrossOrigin("*")
    @PutMapping("/permissoes/")
    @ResponseBody
    public void put(@RequestBody Permissao permissao) {
        Permissao permissaoExistente = permissaoController.findOne(permissao.getId());
        permissaoExistente.atualizar(permissao);
        permissaoController.save(permissaoExistente);
    }
}