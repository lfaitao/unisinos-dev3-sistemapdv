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

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "usuario_credencial", joinColumns = {
            @JoinColumn(name = "ID_USUARIO", nullable = false, updatable = false) }, inverseJoinColumns = {
            @JoinColumn(name = "ID_CREDENCIAL", nullable = false, updatable = false) })
    private Credencial credencial;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "usuario_permissao", joinColumns = {
            @JoinColumn(name = "ID_USUARIO", nullable = false, updatable = true) }, inverseJoinColumns = {
            @JoinColumn(name = "ID_PERMISSAO", nullable = false, updatable = true) })
    private List<Permissao> permissao;

    public Usuario() {
    }

    public Usuario(Long id, List<Permissao> permissoes, Credencial credencial) {
        this.id = id;
        this.permissao = permissoes;
        this.credencial = credencial;
    }

    public Long getId() {
        return id;
    }

    public  String getNome(){return nome;}

    public Credencial getCredencial() {
        return credencial;
    }

    public List<Permissao> getPermissao() {
        return permissao;
    }

    public void setPermissao(List<Permissao> permissao) {
        this.permissao = permissao;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCredencial(Credencial credencial) {
        this.credencial = credencial;
    }

    public void atualizar(Usuario usuario) {
        this.setNome(usuario.nome);
        this.setCredencial(usuario.getCredencial());
        this.setPermissao(usuario.getPermissao());
    }
}