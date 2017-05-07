package br.unisinos.sistemapdv.domain.model;

import javax.persistence.*;
import java.util.List;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "VENDAS")
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @NotNull
    @Column(name = "valor_total")
    private float valorTotal;

    @ManyToOne()
    private Funcionario vendedor;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "venda_produto", joinColumns = {
            @JoinColumn(name = "ID_VENDA", nullable = false, updatable = false) }, inverseJoinColumns = {
                    @JoinColumn(name = "ID_PRODUTO", nullable = false, updatable = false) })
    private List<Produto> produtos;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "venda_prevenda", joinColumns = {
            @JoinColumn(name = "ID_VENDA", nullable = false, updatable = false) }, inverseJoinColumns = {
                    @JoinColumn(name = "ID_PREVENDA", nullable = false, updatable = false) })
    private List<PreVenda> preVendas;

    public Venda() {
    }

    public Venda(Long id, Float valorTotal, Funcionario vendedor, List<Produto> produtos, List<PreVenda> preVendas) {
        this.id = id;
        this.valorTotal = valorTotal;
        this.vendedor = vendedor;
        this.produtos = produtos;
        this.preVendas = preVendas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(float valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Funcionario getVendedor() {
        return vendedor;
    }

    public void setVendedor(Funcionario vendedor) {
        this.vendedor = vendedor;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public List<PreVenda> getPreVendas() {
        return preVendas;
    }

    public void setPreVendas(List<PreVenda> preVendas) {
        this.preVendas = preVendas;
    }

    public void atualizar(Venda venda) {
    }
}