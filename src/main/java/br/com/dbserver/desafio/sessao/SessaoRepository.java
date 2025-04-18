package br.com.dbserver.desafio.sessao;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SessaoRepository extends JpaRepository<SessaoModel, UUID> {

    Optional<SessaoModel> findByPautaId(UUID pautaId);
    boolean existsByPautaId(UUID pautaId);
}
