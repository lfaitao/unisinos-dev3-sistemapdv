package br.unisinos.sistemapdv.application.repository;

import br.unisinos.sistemapdv.domain.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    List<Produto> findByDescricaoContaining(String descricao);

    @Query(value = "select p.estoque - count(pvp.quantidade) as estoqueDisponivel " +
            "from produtos p " +
            "inner join prevenda_produto pvp on p.id = pvp.id_produto " +
            "where p.id = :produtoId " +
            "and pvp.id_prevenda <> :prevendaId " +
            "group by pvp.id_produto", nativeQuery = true)
    Integer CountEstoqueDisponivel(@Param(value = "produtoId") long produtoId, @Param(value = "prevendaId") long prevendaId);
}
