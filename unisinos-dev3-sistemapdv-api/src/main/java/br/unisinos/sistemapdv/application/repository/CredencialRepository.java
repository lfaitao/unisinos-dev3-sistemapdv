package br.unisinos.sistemapdv.application.repository;

import br.unisinos.sistemapdv.domain.model.Credencial;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by lfaitao on 4/19/17.
 */
public interface CredencialRepository extends JpaRepository<Credencial, Long> {

    Credencial findByLoginAndSenha(String login, String senha);

}
