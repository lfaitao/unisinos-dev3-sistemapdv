package br.unisinos.sistemapdv.infrastructure.controller;

import br.unisinos.sistemapdv.application.repository.TelaRepository;
import br.unisinos.sistemapdv.domain.model.Tela;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TelaController {

    @Autowired
    private TelaRepository telaController;

    @CrossOrigin("*")
    @GetMapping("/telas/all")
    @ResponseBody
    public List<Tela> get() {

        return telaController.findAll();
    }

    @CrossOrigin("*")
    @GetMapping("/telas/{id}")
    @ResponseBody
    public Tela get(@PathVariable Long id) {

        return telaController.findOne(id);
    }

    @CrossOrigin("*")
    @GetMapping("/telas")
    @ResponseBody
    public List<Tela> get(@RequestParam String path, @RequestParam String nome) {

        return telaController.findByNomeOrPathContaining(nome, path);
    }

    @CrossOrigin("*")
    @PostMapping("/telas/")
    @ResponseBody
    public Tela post(@RequestBody Tela tela) {
        Tela telaSalva = telaController.save(tela);
        return telaSalva;
    }

    @CrossOrigin("*")
    @DeleteMapping("/telas/{id}")
    @ResponseBody
    public void delete(@PathVariable long id) {

        telaController.delete(id);
    }

    @CrossOrigin("*")
    @PutMapping("/telas/")
    @ResponseBody
    public void put(@RequestBody Tela tela) {
        Tela telaExistente = telaController.findOne(tela.getId());
        telaExistente.atualizar(tela);
        telaController.save(telaExistente);
    }
}