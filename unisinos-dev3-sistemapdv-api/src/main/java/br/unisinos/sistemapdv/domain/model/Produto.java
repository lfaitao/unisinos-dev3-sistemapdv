package br.unisinos.sistemapdv.domain.model;

import javax.persistence.*;

@Entity
@Table(name = "PRODUTOS")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
}