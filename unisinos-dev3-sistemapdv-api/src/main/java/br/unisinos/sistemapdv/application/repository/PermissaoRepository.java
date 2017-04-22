package br.unisinos.sistemapdv.application.repository;

import br.unisinos.sistemapdv.domain.model.Permissao;
import br.unisinos.sistemapdv.domain.model.Tela;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Repository
public interface PermissaoRepository extends JpaRepository<Permissao, Long> {

    List<Permissao> findByNomeContaining(String nome);
}
