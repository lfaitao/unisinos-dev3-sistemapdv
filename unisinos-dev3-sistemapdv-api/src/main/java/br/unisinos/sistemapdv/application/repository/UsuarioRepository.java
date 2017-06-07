package br.unisinos.sistemapdv.application.repository;

import br.unisinos.sistemapdv.domain.model.Credencial;
import br.unisinos.sistemapdv.domain.model.Permissao;
import br.unisinos.sistemapdv.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    List<Usuario> findByNomeOrCredencialLoginContaining(String nome, String login);
    List<Usuario> findByCredencialLoginIgnoreCase(String login);
    List<Usuario> findByPermissaoNome(String nome);
    Usuario findById(Long id);
}
