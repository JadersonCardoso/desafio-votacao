package br.com.dbserver.desafio.associado;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AssociadoRepository extends JpaRepository<AssociadoModel, UUID> {
}
