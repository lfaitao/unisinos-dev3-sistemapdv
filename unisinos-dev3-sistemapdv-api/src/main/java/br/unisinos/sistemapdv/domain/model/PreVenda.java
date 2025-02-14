package br.unisinos.sistemapdv.domain.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PREVENDA")
public class PreVenda {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_CLIENTE")
    private Cliente cliente;

    @OneToMany(orphanRemoval = true, cascade = {CascadeType.ALL}, fetch = FetchType.EAGER, mappedBy = "preVenda")
    private List<PreVendaProduto> preVendaProdutos;

    public PreVenda()
    {}

    public PreVenda(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<PreVendaProduto> getPreVendaProdutos() {
        return preVendaProdutos;
    }

    public void setPreVendaProdutos(List<PreVendaProduto> preVendaProdutos) {
        this.preVendaProdutos = preVendaProdutos;
    }

    public double getSubTotal() {
        double subTotal = 0;

        for (PreVendaProduto p : preVendaProdutos)
        {
            subTotal += p.getSubTotal();
        }

        return subTotal;
    }

    public void atualizar(PreVenda preVenda) {
        this.setCliente(preVenda.cliente);

        List<PreVendaProduto> removeList = new ArrayList<PreVendaProduto>();

        this.getPreVendaProdutos().stream().forEach(p ->
        {
            if(! preVenda.getPreVendaProdutos().stream().anyMatch(pp -> pp.getProduto().getId() == p.getProduto().getId()))
            {
                removeList.add(p);
            }
            else
            {
                PreVendaProduto pvpUpdate = preVenda.preVendaProdutos.stream().filter(pp ->
                        pp.getProduto().getId() == p.getProduto().getId() ).findFirst().get();

                p.setQuantidade(pvpUpdate.getQuantidade());
                p.setPercentualDesconto(pvpUpdate.getPercentualDesconto());
                preVenda.getPreVendaProdutos().remove(pvpUpdate);
            }
        });

        this.preVendaProdutos.removeAll(removeList);

        preVenda.getPreVendaProdutos().stream().forEach(p ->
        {
            p.setPreVenda(this);
            this.getPreVendaProdutos().add(p);
        });
    }
}