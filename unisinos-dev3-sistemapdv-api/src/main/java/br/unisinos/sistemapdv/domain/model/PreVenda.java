package br.unisinos.sistemapdv.domain.model;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "PREVENDA")
public class PreVenda {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_CLIENTE")
    private Cliente cliente;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "preVenda")
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

    public float getSubTotal() {
        float subTotal = 0;

        for (PreVendaProduto p : preVendaProdutos)
        {
            subTotal += p.getProduto().getValor() * p.getQuantidade();
        }

        return subTotal;
    }

    public void atualizar(PreVenda preVenda) {
        this.setCliente(preVenda.cliente);
        this.setPreVendaProdutos(preVenda.preVendaProdutos);
    }
}