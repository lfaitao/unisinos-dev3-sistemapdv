package br.unisinos.sistemapdv.domain.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "PRODUTOS")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @NotNull
    private String descricao;

    @NotNull
    private float valor;

    @NotNull
    private int estoque;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    public void atualizar(Produto produto)
    {
        this.setDescricao(produto.getDescricao());
        this.setValor(produto.getValor());
    }
}