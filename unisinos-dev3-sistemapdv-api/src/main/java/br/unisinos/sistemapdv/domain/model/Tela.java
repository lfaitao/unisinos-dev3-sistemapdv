package br.unisinos.sistemapdv.domain.model;

import javax.persistence.*;

@Entity
@Table(name = "TELAS")
public class Tela {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    public Tela() {
    }

    public Tela(Long id) {
        this.id = id;
    }
}