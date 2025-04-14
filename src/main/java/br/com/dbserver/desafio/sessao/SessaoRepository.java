package br.com.dbserver.desafio.sessao;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SessaoRepository extends JpaRepository<SessaoModel, UUID> {
}
