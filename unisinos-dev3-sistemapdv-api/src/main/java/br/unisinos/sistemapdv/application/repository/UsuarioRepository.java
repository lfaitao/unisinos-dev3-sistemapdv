package br.unisinos.sistemapdv.application.repository;

import br.unisinos.sistemapdv.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Faitão on 26/03/2017.
 */
@Transactional
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByEmail(String email);

}
