package br.unisinos.sistemapdv.application.repository;

import br.unisinos.sistemapdv.domain.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    List<Produto> findByDescricaoContaining(String descricao);
}