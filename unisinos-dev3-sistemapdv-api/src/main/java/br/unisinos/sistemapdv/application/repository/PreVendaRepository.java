package br.unisinos.sistemapdv.application.repository;

import br.unisinos.sistemapdv.domain.model.PreVenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface PreVendaRepository extends JpaRepository<PreVenda, Long> {

}
