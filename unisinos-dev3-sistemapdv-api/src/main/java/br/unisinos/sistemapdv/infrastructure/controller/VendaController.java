package br.unisinos.sistemapdv.infrastructure.controller;

import br.unisinos.sistemapdv.application.repository.VendaRepository;
import br.unisinos.sistemapdv.domain.model.Venda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class VendaController {

    @Autowired
    private VendaRepository vendaRepository;

    @RequestMapping(value = "/venda/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Venda get(@PathVariable Long id) {
        return vendaRepository.findOne(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Long post(@RequestBody Venda venda) {
        Venda vendaSalva = vendaRepository.save(venda);
        return vendaSalva.getId();
    }

    @RequestMapping(value = "/venda/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void delete(@PathVariable long id) {
        vendaRepository.delete(id);
    }

    @RequestMapping(value = "/venda/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public void put(@PathVariable long id, @RequestBody Venda venda) {
        Venda vendaExistente = vendaRepository.findOne(id);
        vendaExistente.atualizar(venda);
        vendaRepository.save(vendaExistente);
    }
}