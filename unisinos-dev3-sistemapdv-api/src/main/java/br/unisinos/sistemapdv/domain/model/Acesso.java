package br.unisinos.sistemapdv.domain.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ACESSOS")
public class Acesso {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "acesso_tela", joinColumns = {
            @JoinColumn(name = "ID_ACESSO", nullable = false, updatable = false) }, inverseJoinColumns = {
            @JoinColumn(name = "ID_TELA", nullable = false, updatable = false) })
    private List<Tela> telas;

    public Acesso() {
    }

    public Acesso(Long id, List<Tela> telas) {
        this.id = id;
        this.telas = telas;
    }
}