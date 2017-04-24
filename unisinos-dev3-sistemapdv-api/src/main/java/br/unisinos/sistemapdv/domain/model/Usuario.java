package br.unisinos.sistemapdv.domain.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "USUARIOS")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @NotNull
    private String nome;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "usuario_permissao", joinColumns = {
            @JoinColumn(name = "ID_USUARIO", nullable = false, updatable = false) }, inverseJoinColumns = {
            @JoinColumn(name = "ID_PERMISSAO", nullable = false, updatable = false) })
    private List<Permissao> permissao;

    public Usuario() {
    }

    public Usuario(Long id, List<Permissao> permissoes) {
        this.id = id;
        this.permissao = permissoes;
    }

    public Long getId() {
        return id;
    }

    public  String getNome(){return nome;}

    public void atualizar(Usuario usuario) {
        this.nome = usuario.nome;
    }
}