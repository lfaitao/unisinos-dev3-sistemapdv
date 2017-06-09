package br.unisinos.sistemapdv.infrastructure.controller;

import br.unisinos.sistemapdv.application.repository.PreVendaRepository;
import br.unisinos.sistemapdv.application.repository.ProdutoRepository;
import br.unisinos.sistemapdv.domain.model.PreVenda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.stream.Collectors;
import java.util.List;

@RestController
public class PreVendaController {

    @Autowired
    private PreVendaRepository preVendaRepository;
    @Autowired
    private ProdutoRepository produtoRepository;

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
                        pv.getPreVendaProdutos().stream().anyMatch((p) -> p.getProduto().getId() == produtoId)
                    )
                ).collect(Collectors.toList());
    }

    @ResponseBody
    @CrossOrigin(origins = "*")
    @PostMapping("/prevendas")
    public PreVenda post(@RequestBody PreVenda preVenda) {
        CarregarProdutos(preVenda);
        PreVenda preVendaSalva = preVendaRepository.save(preVenda);
        return preVendaSalva;
    }

    @ResponseBody
    @CrossOrigin(origins = "*")
    @PutMapping("/prevendas")
    public void put(@RequestBody PreVenda preVenda) {
        PreVenda preVendaExistente = preVendaRepository.findOne(preVenda.getId());

        CarregarProdutos(preVenda);

        preVendaExistente.atualizar(preVenda);

        preVendaRepository.save(preVendaExistente);
    }

    @ResponseBody
    @CrossOrigin(origins = "*")
    @DeleteMapping("/prevendas/{id}")
    public void delete(@PathVariable Long id) {
        preVendaRepository.delete(id);
    }

    private void CarregarProdutos(PreVenda preVenda)
    {
        preVenda.getPreVendaProdutos().stream().forEach(preVendaProduto ->
        {
            preVendaProduto.setPreVenda(preVenda);
            preVendaProduto.setProduto(produtoRepository.findOne(preVendaProduto.getProduto().getId()));
        });
    }
}