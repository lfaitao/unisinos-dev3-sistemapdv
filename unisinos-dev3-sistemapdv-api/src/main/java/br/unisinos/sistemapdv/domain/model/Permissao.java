package br.unisinos.sistemapdv.domain.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "PERMISSOES")
public class Permissao {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @NotNull
    private String nome;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "permissao_tela", joinColumns = {
            @JoinColumn(name = "ID_PERMISSAO", nullable = false, updatable = false) }, inverseJoinColumns = {
            @JoinColumn(name = "ID_TELA", nullable = false, updatable = false) })
    private List<Tela> telas;

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Tela> getTelas() {
        return telas;
    }

    public void setTelas(List<Tela> telas) {
        this.telas = telas;
    }

    public Permissao() {
    }

    public Permissao(Long id, List<Tela> telas) {
        this.id = id;
        this.telas = telas;
    }

    public void atualizar(Permissao permissao) {
        this.nome = permissao.nome;
        this.setTelas(permissao.getTelas());

    }
}