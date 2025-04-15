package br.com.dbserver.desafio.voto;

import java.util.UUID;

public record VotoRequestDTO(UUID pautaId, UUID associadoId, String votoValor) {
}
