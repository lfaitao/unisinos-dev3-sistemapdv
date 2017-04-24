package br.unisinos.sistemapdv.infrastructure.controller;

import br.unisinos.sistemapdv.application.repository.ProdutoRepository;
import br.unisinos.sistemapdv.domain.model.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @ResponseBody
    @CrossOrigin(origins = "*")
    @GetMapping("/produtos/all")
    public List<Produto> get() {
        return produtoRepository.findAll();
    }

    @ResponseBody
    @CrossOrigin(origins = "*")
    @GetMapping("/produtos")
    public List<Produto> get(@RequestParam String descricao) {
        List<Produto> produtos = produtoRepository.findByDescricaoContaining(descricao);
        return produtos;
    }

    @ResponseBody
    @CrossOrigin(origins = "*")
    @PostMapping("/produtos")
    public Produto post(@RequestBody Produto produto) {
        Produto produtoSalvo = produtoRepository.save(produto);
        return produtoSalvo;
    }

    @ResponseBody
    @CrossOrigin(origins = "*")
    @PutMapping("/produtos")
    public void put(@RequestBody Produto produto) {
        Produto produtoExistente = produtoRepository.findOne(produto.getId());
        produtoExistente.atualizar(produto);
        produtoRepository.save(produtoExistente);
    }

    @ResponseBody
    @CrossOrigin(origins = "*")
    @DeleteMapping("/produtos/{id}")
    public void delete(@PathVariable Long id) {
        produtoRepository.delete(id);
    }
}