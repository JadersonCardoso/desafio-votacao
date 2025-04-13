package br.com.dbserver.desafio.pauta;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PautaRepository extends JpaRepository<PautaModel, UUID> {
}
