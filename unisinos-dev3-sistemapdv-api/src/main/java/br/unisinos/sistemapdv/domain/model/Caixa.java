package br.unisinos.sistemapdv.domain.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by lfaitao on 26/03/2017.
 */
@Entity
@Table(name = "CAIXAS")
public class Caixa {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @NotNull
    private String email;

    @NotNull
    private String name;

    public Caixa() { }

    public Caixa(long id) {
        this.id = id;
    }

    public Caixa(String email, String name) {
        this.email = email;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
