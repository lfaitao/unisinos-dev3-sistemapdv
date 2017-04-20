package br.unisinos.sistemapdv.domain.model;

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

    public Credencial() {
    }

    public Credencial(long id) {
        this.id = id;
    }

    public Credencial(String login, String senha) {
        this.login = login;
        this.senha = senha;
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

}
