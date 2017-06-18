package br.unisinos.sistemapdv.domain.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by eduardopereira on 24/05/2017.
 */
@Entity
@Table(name = "PREVENDA_PRODUTO")
public class PreVendaProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_PREVENDA")
    private PreVenda preVenda;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_PRODUTO")
    private Produto produto;

    @NotNull
    private Long quantidade;

    private double percentualDesconto;

    public PreVendaProduto(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPreVenda(PreVenda preVenda) {
        this.preVenda = preVenda;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Long getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Long quantidade) {
        this.quantidade = quantidade;
    }

    public double getPercentualDesconto() {
        return percentualDesconto;
    }

    public void setPercentualDesconto(double percentualDesconto) {
        this.percentualDesconto = percentualDesconto;
    }

    public double getSubTotal() {
        double subTotal = 0;

        subTotal = (quantidade * produto.getValor()) - ((quantidade * produto.getValor()) * (percentualDesconto / 100) );

        return subTotal;
    }
}
