package br.com.dbserver.desafio.voto;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record VotoRequestDTO(@NotNull UUID pautaId,@NotNull UUID associadoId, String votoValor) {
}
