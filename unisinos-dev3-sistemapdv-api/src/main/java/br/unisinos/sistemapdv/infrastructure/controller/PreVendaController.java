package br.unisinos.sistemapdv.infrastructure.controller;

import br.unisinos.sistemapdv.application.repository.PreVendaRepository;
import br.unisinos.sistemapdv.application.repository.ProdutoRepository;
import br.unisinos.sistemapdv.domain.model.PreVenda;
import br.unisinos.sistemapdv.infrastructure.dto.FeedbackDTO;
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
    public FeedbackDTO post(@RequestBody PreVenda preVenda) {
        PreVenda preVendaSalva;

        CarregarProdutos(preVenda);

        FeedbackDTO feedback = verificarEstoque(preVenda);

        if(feedback.isStatus()) {
            preVendaSalva = preVendaRepository.save(preVenda);
            feedback.setObject(preVendaSalva);
        }

        return feedback;
    }

    @ResponseBody
    @CrossOrigin(origins = "*")
    @PutMapping("/prevendas")
    public FeedbackDTO put(@RequestBody PreVenda preVenda) {
        PreVenda preVendaExistente = preVendaRepository.findOne(preVenda.getId());

        CarregarProdutos(preVenda);

        FeedbackDTO feedback = verificarEstoque(preVenda);

        if(feedback.isStatus()){
            preVendaExistente.atualizar(preVenda);
            preVendaRepository.save(preVendaExistente);
        }

        return feedback;
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

    private FeedbackDTO verificarEstoque(PreVenda preVenda){
        FeedbackDTO feedback = new FeedbackDTO(true, "sucesso", preVenda);

        preVenda.getPreVendaProdutos().stream().forEach(pvp ->{

            Integer estoqueDisponivel = produtoRepository.CountEstoqueDisponivel(pvp.getProduto().getId(), preVenda.getId());

            if(estoqueDisponivel == null)
                estoqueDisponivel = pvp.getProduto().getEstoque();

            if (pvp.getQuantidade() > estoqueDisponivel){
                feedback.setStatus(false);
                feedback.setMessage("O produto " + pvp.getProduto().getDescricao() + " possui apenas " + estoqueDisponivel+ " itens disponiveis");
            }

        });

        return feedback;
    }
}