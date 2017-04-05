package br.unisinos.sistemapdv.domain.model;

import javax.persistence.*;

@Entity
@Table(name = "PREVENDAS")
public class PreVenda {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
}