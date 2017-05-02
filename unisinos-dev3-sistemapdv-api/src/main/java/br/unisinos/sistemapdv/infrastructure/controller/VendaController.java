package br.unisinos.sistemapdv.infrastructure.controller;

import br.unisinos.sistemapdv.application.repository.VendaRepository;
import br.unisinos.sistemapdv.domain.model.Venda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class VendaController {

    @Autowired
    private VendaRepository vendaRepository;

    @RequestMapping(value = "/vendas/{id}", method = RequestMethod.GET)
    @CrossOrigin(origins = "*")
    @ResponseBody
    public Venda get(@PathVariable Long id) {
        return vendaRepository.findOne(id);
    }

    @ResponseBody
    @CrossOrigin(origins = "*")
    @PostMapping("/vendas")
    public Long post(@RequestBody Venda venda) {
        Venda vendaSalva = vendaRepository.save(venda);
        return vendaSalva.getId();
    }

    @RequestMapping(value = "/vendas/{id}", method = RequestMethod.DELETE)
    @CrossOrigin(origins = "*")
    @ResponseBody
    public void delete(@PathVariable long id) {
        vendaRepository.delete(id);
    }

    @RequestMapping(value = "/vendas/{id}", method = RequestMethod.PUT)
    @CrossOrigin(origins = "*")
    @ResponseBody
    public void put(@PathVariable long id, @RequestBody Venda venda) {
        Venda vendaExistente = vendaRepository.findOne(id);
        vendaExistente.atualizar(venda);
        vendaRepository.save(vendaExistente);
    }
}