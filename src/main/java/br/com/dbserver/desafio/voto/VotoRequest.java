package br.com.dbserver.desafio.voto;

import java.util.UUID;

public record VotoRequest(UUID pautaId, UUID associadoId, String votoValor) {
}
