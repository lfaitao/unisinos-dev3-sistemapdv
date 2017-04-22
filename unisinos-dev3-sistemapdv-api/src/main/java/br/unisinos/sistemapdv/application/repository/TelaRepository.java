package br.unisinos.sistemapdv.application.repository;

import br.unisinos.sistemapdv.domain.model.Tela;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface TelaRepository extends JpaRepository<Tela, Long> {

    List<Tela> findByNomeOrPathContaining(String nome, String path);
}
