package br.com.dbserver.desafio.voto;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VotoRepository extends JpaRepository<VotoModel, UUID> {

    boolean existsByPautaIdAndAndAssociadoId(UUID pautaId, UUID associadoID);

}
