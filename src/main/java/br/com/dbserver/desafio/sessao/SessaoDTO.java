package br.com.dbserver.desafio.sessao;

import java.time.Instant;
import java.util.UUID;

public record SessaoDTO(UUID id, Instant inicio, Instant fim) {
}
