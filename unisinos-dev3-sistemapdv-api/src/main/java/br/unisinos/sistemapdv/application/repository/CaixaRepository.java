package br.unisinos.sistemapdv.application.repository;

import br.unisinos.sistemapdv.domain.model.Caixa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by lfaitao on 26/03/2017.
 */
@Transactional
@Repository
public interface CaixaRepository extends JpaRepository<Caixa, Long> {

    Caixa findByNumeroCaixa(Integer numeroCaixa);

}
