package br.unisinos.sistemapdv.infrastructure.controller;

import br.unisinos.sistemapdv.application.repository.PreVendaRepository;
import br.unisinos.sistemapdv.domain.model.PreVenda;
import br.unisinos.sistemapdv.domain.model.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.stream.Collectors;
import java.util.List;

@RestController
public class PreVendaController {

    @Autowired
    private PreVendaRepository preVendaRepository;

    @ResponseBody
    @CrossOrigin(origins = "*")
    @GetMapping("/prevendas/all")
    public List<PreVenda> get() {
        return preVendaRepository.findAll();
    }

    @ResponseBody
    @CrossOrigin(origins = "*")
    @GetMapping("/prevendas")
    public List<PreVenda> get(@RequestParam Long produtoId, @RequestParam Long clienteId) {
        List<PreVenda> preVendas = preVendaRepository.findAll();

        //TODO: Ter paciencia de descobrir como faz no hibernate
        return preVendas.stream().filter(
                (pv) ->
                    (
                        clienteId == null
                        ||
                        pv.getCliente().getId() == clienteId
                    )
                    ||
                    (
                        produtoId == null
                        ||
                        pv.getProdutos().stream().anyMatch((p) -> p.getId() == produtoId)
                    )
                ).collect(Collectors.toList());
    }

    @ResponseBody
    @CrossOrigin(origins = "*")
    @PostMapping("/prevendas")
    public PreVenda post(@RequestBody PreVenda preVenda) {
        PreVenda preVendaSalva = preVendaRepository.save(preVenda);
        return preVendaSalva;
    }

    @ResponseBody
    @CrossOrigin(origins = "*")
        @PutMapping("/prevendas")
    public void put(@RequestBody PreVenda preVenda) {
        PreVenda preVendaExistente = preVendaRepository.findOne(preVenda.getId());
        preVendaExistente.atualizar(preVenda);
        preVendaRepository.save(preVendaExistente);
    }

    @ResponseBody
    @CrossOrigin(origins = "*")
    @DeleteMapping("/prevendas/{id}")
    public void delete(@PathVariable Long id) {
        preVendaRepository.delete(id);
    }
}