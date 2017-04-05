package br.unisinos.sistemapdv.application.repository;

import br.unisinos.sistemapdv.domain.model.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by lfaitao on 26/03/2017.
 */
@Transactional
@Repository
public interface VendaRepository extends JpaRepository<Venda, Long> {

}
