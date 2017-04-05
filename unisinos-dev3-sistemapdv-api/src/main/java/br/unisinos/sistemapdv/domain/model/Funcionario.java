package br.unisinos.sistemapdv.domain.model;

import javax.persistence.*;

@Entity
@Table(name = "FUNCIONARIOS")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
}