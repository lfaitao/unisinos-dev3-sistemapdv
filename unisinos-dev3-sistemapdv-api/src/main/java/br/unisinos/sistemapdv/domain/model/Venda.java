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
    private float valorTotal;

    @ManyToOne(cascade = CascadeType.ALL)
    private Funcionario vendedor;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "venda_produto", joinColumns = {
            @JoinColumn(name = "ID_VENDA", nullable = false, updatable = false) }, inverseJoinColumns = {
                    @JoinColumn(name = "ID_PRODUTO", nullable = false, updatable = false) })
    private List<Produto> produtos;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "venda_prevenda", joinColumns = {
            @JoinColumn(name = "ID_VENDA", nullable = false, updatable = false) }, inverseJoinColumns = {
                    @JoinColumn(name = "ID_PRODUTO", nullable = false, updatable = false) })
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

    public void atualizar(Venda venda) {
    }
}