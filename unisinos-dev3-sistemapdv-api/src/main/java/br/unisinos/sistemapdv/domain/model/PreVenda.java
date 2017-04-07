package br.unisinos.sistemapdv.domain.model;

import javax.persistence.*;

@Entity
@Table(name = "PREVENDAS")
public class PreVenda {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    public PreVenda() {
    }

    public PreVenda(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void atualizar(PreVenda preVenda) {
    }
}