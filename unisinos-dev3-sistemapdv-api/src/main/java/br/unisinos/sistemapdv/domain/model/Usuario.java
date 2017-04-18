package br.unisinos.sistemapdv.domain.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "USUARIOS")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "usuario_acesso", joinColumns = {
            @JoinColumn(name = "ID_USUARIO", nullable = false, updatable = false) }, inverseJoinColumns = {
            @JoinColumn(name = "ID_ACESSO", nullable = false, updatable = false) })
    private List<Acesso> acessos;

    public Usuario() {
    }

    public Usuario(Long id, List<Acesso> acessos) {
        this.id = id;
        this.acessos = acessos;
    }

    public Long getId() {
        return id;
    }

    public void atualizar(Usuario usuario) {
    }
}