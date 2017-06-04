package br.unisinos.sistemapdv.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by lfaitao on 4/19/17.
 */
@Entity
@Table(name = "CREDENCIAIS")
public class Credencial {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @NotNull
    private String login;

    @NotNull
    private String senha;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "usuario_credencial", joinColumns = {
            @JoinColumn(name = "ID_CREDENCIAL", nullable = false, updatable = false) }, inverseJoinColumns = {
            @JoinColumn(name = "ID_USUARIO", nullable = false, updatable = false) })
    private Usuario usuario;

    public Credencial() {
    }

    public Credencial(long id) {
        this.id = id;
    }

    public Credencial(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public Credencial(String login, String senha, Usuario  usuario) {
        this.login = login;
        this.senha = senha;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @JsonIgnore
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
