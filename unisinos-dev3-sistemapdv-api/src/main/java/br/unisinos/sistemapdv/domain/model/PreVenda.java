package br.unisinos.sistemapdv.domain.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "PREVENDAS")
public class PreVenda {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_CLIENTE")
    private Cliente cliente;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "PREVENDA_PRODUTO", joinColumns = {
            @JoinColumn(name = "ID_PREVENDA", nullable = false, updatable = false) },
            inverseJoinColumns = {
                    @JoinColumn(name = "ID_PRODUTO", nullable = false, updatable = false) })
    private List<Produto> produtos;

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

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public float getSubTotal() {
        float subTotal = 0;

        for (Produto p : produtos)
        {
            subTotal += p.getValor();
        }

        return subTotal;
    }

    public void atualizar(PreVenda preVenda) {
        this.setCliente(preVenda.cliente);
        this.setProdutos(preVenda.produtos);
    }
}