package br.unisinos.sistemapdv.infrastructure.controller;

import br.unisinos.sistemapdv.application.repository.PreVendaRepository;
import br.unisinos.sistemapdv.domain.model.PreVenda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PreVendaController {

    @Autowired
    private PreVendaRepository preVendaRepository;

    @RequestMapping(value = "/prevenda/{id}", method = RequestMethod.GET)
    @ResponseBody
    public int get(@PathVariable int id) {
        return  id * 2;
    }
/*
    @RequestMapping(value = "/prevenda/{id}", method = RequestMethod.GET)
    @ResponseBody
    public PreVenda get(@PathVariable Long id) {
        return preVendaRepository.findOne(id);
    }
*/

    @RequestMapping(value = "/prevenda/", method = RequestMethod.POST)
    @ResponseBody
    public Long post(@RequestBody PreVenda preVenda) {
        PreVenda novaPreVenda = preVendaRepository.save(preVenda);
        return novaPreVenda.getId();
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public void put(@RequestBody PreVenda preVenda) {
        PreVenda preVendaExistente = preVendaRepository.findOne(preVenda.getId());
        preVendaExistente.atualizar(preVenda);
        preVendaRepository.save(preVendaExistente);
    }

    @RequestMapping(value = "/prevenda/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void delete(@PathVariable long id) {
        preVendaRepository.delete(id);
    }
}